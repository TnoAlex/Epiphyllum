package team.jtq.epi_serve.service.imp

import com.alibaba.excel.EasyExcel
import com.alibaba.excel.context.AnalysisContext
import com.alibaba.excel.event.AnalysisEventListener
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.DigestUtils
import org.springframework.web.multipart.MultipartFile
import team.jtq.epi_serve.config.AppResourceConfig
import team.jtq.epi_serve.config.BeanContext
import team.jtq.epi_serve.entity.*
import team.jtq.epi_serve.entity.ao.RaceInfo
import team.jtq.epi_serve.entity.ao.RaceResults
import team.jtq.epi_serve.entity.ao.RaceUpLoadEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.entity.to.FileChunkInfo
import team.jtq.epi_serve.entity.to.TFileInfo
import team.jtq.epi_serve.mapper.*
import team.jtq.epi_serve.service.MongoService
import team.jtq.epi_serve.service.RaceService
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.MapperReflectionService
import team.jtq.epi_serve.tools.Result
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.servlet.http.HttpServletResponse


@Service
class RaceServiceImp : ServiceImpl<UsdRaceMapper, UsdRace>(), RaceService {

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var linkService: MapperReflectionService

    @Autowired
    private lateinit var mongoService: MongoService


    @Transactional
    override fun addRace(entity: RaceUpLoadEntity, token: String): Result {
        val obj = UsdRace::class.java
        val instant = obj.newInstance()
        val json = tokenService.getUserInfo(token)!!
        instant.raceStartTime =
            Instant.ofEpochMilli(entity.startTime.toLong()).atZone(ZoneOffset.of(AppResourceConfig.UTCTimeZone))
                .toLocalDateTime()
        instant.raceEndTime =
            Instant.ofEpochMilli(entity.endTime.toLong()).atZone(ZoneOffset.of(AppResourceConfig.UTCTimeZone))
                .toLocalDateTime()
        instant.raceAddition = entity.raceAddition
        instant.raceDescription = entity.raceDescription
        instant.raceName = entity.raceName
        val systemTime = LocalDateTime.now()
        if (instant.raceStartTime > systemTime)
            instant.status = -1
        if (instant.raceStartTime < systemTime)
            instant.status = 0
        instant.organizer = json["user_id"] as String
        super<ServiceImpl>.save(instant)
        return Result.ok()
    }

    override fun selectAllRace(token: String, pageIndex: String, pageItems: String): Result {
        val page = Page<UsdRace>(pageIndex.toLong(), pageItems.toLong())
        val mouthBefore = LocalDateTime.now().minusMonths(1)
        val query = KtQueryWrapper(UsdRace::class.java)
        query.ge(UsdRace::raceStartTime, mouthBefore)
        val races = this.baseMapper.selectPage(page, query)
        return Result.ok(races.records)
    }

    @Transactional
    override fun registrationRace(token: String, rid: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val res = linkService.addLinkinBeans(
            UsdUserRaceMapper::class,
            UsdUserRace::class,
            Pair(UsdUserRace::raceId.name, rid),
            Pair(UsdUserRace::uid.name, json["user_id"] as String)
        )
        return if (res)
            Result.ok()
        else
            throw RuntimeException("Sql Error!")
    }

    override fun selectUserJoinedRace(token: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val res = linkService.selectLinkinBeans(
            UsdUserRaceMapper::class,
            UsdUserRace::class,
            listOf(Pair(UsdUserRace::uid, json["user_id"] as String))
        ) ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        return Result.ok(res)
    }

    @Transactional
    override fun cancelRegistration(token: String, rid: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val res = linkService.deleteLinkinBeans(
            UsdUserRaceMapper::class,
            UsdUserRace::class,
            listOf(Pair(UsdUserRace::uid, json["user_id"] as String), Pair(UsdUserRace::raceId, rid))
        )
        return if (res)
            Result.ok()
        else
            throw RuntimeException("Sql Error!")
    }

    override fun uploadAnnex(rid: String, token: String, chunk: FileChunkInfo): Result {
        return try {
            val json = tokenService.getUserInfo(token)!!
            val chunkPath =
                AppResourceConfig.appCachePath + File.separator + (json["user_id"] as String) + File.separator + "RACE" + File.separator + rid +File.separator + "part" + File.separator + chunk.chunkNumber + ".part"
            val outFile = File(chunkPath)
            val stream = chunk.file.inputStream
            FileUtils.copyInputStreamToFile(stream, outFile)
            Result.ok()
        } catch (e: Exception) {
            Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        }
    }

    override fun finishUpload(rid: String, token: String, fileInfo: TFileInfo): Result {
        val json = tokenService.getUserInfo(token)!!
        val chunkPath =
            AppResourceConfig.appCachePath + File.separator + (json["user_id"] as String) + File.separator + "RACE" + File.separator + rid+ File.separator +"part"
        val file = File(chunkPath)
        val filename = DigestUtils.md5DigestAsHex(((json["user_id"] as String)+"&"+rid).toByteArray(Charsets.UTF_8))
        val partFile= File(file.parent + File.separator + filename)
        if(file.isDirectory){
            val files = file.listFiles()
            if(files!=null && files.isNotEmpty()){
                val desFile = FileOutputStream(partFile,true)
                files.forEach {
                    FileUtils.copyFile(it,desFile)
                }
                desFile.close()
                FileUtils.deleteDirectory(file)

            }
        }
        val inputStream = FileInputStream(partFile)
        val md5 = DigestUtils.md5DigestAsHex(inputStream)
        if(fileInfo.uniqueIdentifier!=md5)
            return Result.error(ResultStatusCode.FILE_CHECK_ERR)
        val fileDocument = FileDocument(UUID.randomUUID().toString().replace("-",""),
            filename,fileInfo.size,System.currentTimeMillis().toString(),md5,null,fileInfo.fileType,"RACE",Pair(json["user_id"] as String,rid),null
        )
        mongoService.saveFile(FileInputStream(partFile),fileInfo.fileType,fileDocument)
        val mapper = BeanContext.getBeanbyClazz(UsdRaceAnnexMapper::class.java)
        val obj = UsdRaceAnnex::class.java.newInstance()
        obj.raceId = rid
        obj.uid = json["user_id"] as String
        obj.annexId = fileDocument.id
        mapper.insert(obj)
        return Result.ok()
    }

    @Transactional
    override fun deleteAnnex(rid: String, token: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val annex = linkService.selectLinkinBeans(
            UsdRaceAnnexMapper::class,
            UsdRaceAnnex::class,
            listOf(Pair(UsdRaceAnnex::uid,json["user_id"] as String),Pair(UsdRaceAnnex::raceId,rid))
        )?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        var res = linkService.deleteLinkinBeans(
            UsdRaceAnnexMapper::class,
            UsdRaceAnnex::class,
            listOf(Pair(UsdRaceAnnex::uid,json["user_id"] as String),Pair(UsdRaceAnnex::raceId,rid))
        )
        if(!res)
            throw RuntimeException("Sql Error")
        res = mongoService.deleteFile(annex[0].annexId)
        if(!res)
            throw RuntimeException("Sql Error")
        return Result.ok()
    }

    override fun downloadAnnexList(rid: String, token: String, pageIndex: String, pageItems: String): Result {
        val page = Page<UsdRaceAnnex>(pageIndex.toLong(),pageItems.toLong())
        val annexs = linkService.selectLinkinBeansonPage(
            UsdRaceAnnexMapper::class,
            UsdRaceAnnex::class,
            listOf(Pair(UsdRaceAnnex::raceId,rid)),
            page
        )?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val res = annexs.records.map { it.annexId }
        return Result.ok(res)
    }

    override fun selectUserHoldRace(token: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val res = linkService.selectLinkinBeans(
            UsdRaceMapper::class,
            UsdUserRace::class,
            listOf(Pair(UsdRace::organizer,json["user_id"] as String))
        )?:return Result.ok()
        return Result.ok(res)
    }

    override fun cancelRace(token: String, rid: String): Result {
        val query = KtQueryWrapper(UsdRace::class.java)
        query.eq(UsdRace::Id,rid)
        val res = this.baseMapper.selectOne(query)
        return if(res.status!=-1)
            Result.error("比赛已经开始或结束，无法删除")
        else
            Result.ok()
    }

    override fun selectRaceInfo(token: String, rid: String): Result {
        val personNumber = linkService.countLinkBeans(
            UsdUserRaceMapper::class,
            UsdUserRace::class,
            listOf(Pair(UsdUserRace::raceId,rid))
        )?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val itemsNumber = linkService.countLinkBeans(
            UsdRaceAnnexMapper::class,
            UsdRaceAnnex::class,
            listOf(Pair(UsdRaceAnnex::raceId,rid))
        )?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val raceinfo = RaceInfo(personNumber.toInt(),itemsNumber.toInt())
        return Result.ok(raceinfo)
    }

    @Transactional
    override fun noticeEntrants(token: String, rid: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val person = linkService.selectLinkinBeans(
            UsdUserRaceMapper::class,
            UsdUserRace::class,
            listOf(Pair(UsdUserRace::raceId,rid))
        )?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val res = linkService.batchSelectLinkBeansNotInList(
            UsdRaceAnnexMapper::class,
            UsdRaceAnnex::class,
            Pair(UsdRaceAnnex::raceId,person.map { it.raceId })
        )?.map { it.uid }?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val query = KtQueryWrapper(UsdRace::class.java)
        query.eq(UsdRace::Id,rid)
        val race = this.baseMapper.selectOne(query)?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val notice = UsdNotice::class.java.newInstance()
        notice.context = race.raceName + "产物提交即将截止，请尽快提交"
        notice.createTime = LocalDateTime.now()
        notice.createBy = json["user_id"] as String
        val mapper = BeanContext.getBeanbyClazz(UsdNoticeMapper::class.java)
        mapper.insert(notice)
        for (i in res){
            val add = linkService.addLinkinBeans(
                UsdUserRaceMapper::class,
                UsdUserRace::class,
                Pair(UsdUserRace::raceId.name,rid),
                Pair(UsdUserRace::uid.name,json["user_id"] as String)
            )
            if (!add)
                throw RuntimeException("Sql Error")
        }
        return Result.ok()
    }

    override fun downloadAnnex(fid: String, rid: String, token: String, response: HttpServletResponse): Result {
        val fileDocument = mongoService.findByID(fid)?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        response.contentType = fileDocument.contentType
        response.setHeader("Content-Disposition", "attachment; filename=" + fileDocument.fileName)
        response.setHeader("Content-Length",fileDocument.fileSize.toString())
        return try{
            val out = response.outputStream
            IOUtils.copy(fileDocument.content!!.inputStream(),out)
            Result.ok()
        }catch (e:Exception) {
            Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        }
    }

    override fun getAnnexExcel(rid: String, token: String, response: HttpServletResponse): Result {
        val res = linkService.selectLinkinBeans(
            UsdRaceAnnexMapper::class,
            UsdRaceAnnex::class,
            listOf(Pair(UsdRaceAnnex::raceId,rid))
        )?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val infoList = ArrayList<RaceResults>()
        res.forEach {
            infoList.add(RaceResults(it.annexId,null))
        }
        response.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        response.characterEncoding = "utf-8"
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''$rid.xlsx")
        EasyExcel.write(response.outputStream,RaceResults::class.java).sheet(rid).doWrite(infoList)
        return Result.ok()
    }

    @Transactional
    override fun uploadExcel(rid: String, token: String, file: MultipartFile): Result {
        val resList = ArrayList<RaceResults>(100)
        val mapper = BeanContext.getBeanbyClazz(UsdUserAwardsMapper::class.java)

        EasyExcel.read(file.inputStream,RaceResults::class.java, object:AnalysisEventListener<RaceResults>(){
            val BATCH_COUNT = 100

            override fun invoke(data: RaceResults, context: AnalysisContext) {
                resList.add(data)
                if(resList.size>=BATCH_COUNT){
                    save(resList)
                    resList.clear()
                }
            }

            override fun doAfterAllAnalysed(context: AnalysisContext) {
                save(resList)
            }

            private fun save(list:List<RaceResults>){
                val res = linkService.batchSelectLinkBeansInList(
                    UsdRaceAnnexMapper::class,
                    UsdRaceAnnex::class,
                    Pair(UsdRaceAnnex::annexId,list.map { it.annexId })
                )?:throw RuntimeException("Sql Error")
                val obj = UsdUserAwards::class.java
                val listMap = list.map { it.annexId to it.award }.toMap()
                res.forEach {
                    if(listMap.containsKey(it.annexId)){
                        val award = obj.newInstance()
                        award.raceId = rid
                        award.uid = it.uid
                        award.award = listMap[it.annexId]!!
                        mapper.insert(award)
                    }
                }
            }

        })
        return Result.ok()
    }

}