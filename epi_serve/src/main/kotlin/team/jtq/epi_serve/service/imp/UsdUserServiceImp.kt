package team.jtq.epi_serve.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jtq.epi_serve.config.BeanContext
import team.jtq.epi_serve.entity.UsdNotice
import team.jtq.epi_serve.entity.UsdUser
import team.jtq.epi_serve.entity.UsdUserAwards
import team.jtq.epi_serve.entity.UsdUserNotice
import team.jtq.epi_serve.entity.ao.ModifyUserEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.entity.ao.UserProtectedInfo
import team.jtq.epi_serve.entity.vo.NoticeView
import team.jtq.epi_serve.entity.vo.UserInfoView
import team.jtq.epi_serve.mapper.UsdNoticeMapper
import team.jtq.epi_serve.mapper.UsdUserAwardsMapper
import team.jtq.epi_serve.mapper.UsdUserMapper
import team.jtq.epi_serve.mapper.UsdUserNoticeMapper
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdLinkService
import team.jtq.epi_serve.service.UsdUserService
import team.jtq.epi_serve.tools.Result

@Service
class UsdUserServiceImp : ServiceImpl<UsdUserMapper, UsdUser>(), UsdUserService {

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var linkService: UsdLinkService

    override fun selectUserNotice(token: String, pageIndex: String, pageItems: String): Result {
        val json = tokenService.getUserInfo(token)!!

        val page = Page<UsdNotice>(pageIndex.toLong(), pageItems.toLong())
        val noticelink = linkService.selectLinkinBeansonPage(
            UsdUserNoticeMapper::class,
            UsdUserNotice::class,
            listOf(Pair(UsdUserNotice::uid, json["user_id"] as String)),
            Page<UsdUserNotice>(pageIndex.toLong(),pageItems.toLong())
        )?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val noticeStatus = noticelink.records.map { it.noticeId to it.status }.toMap()
        val notices = linkService.batchSelectLinkBeansInListOnPage(
            UsdNoticeMapper::class,
            UsdNotice::class,
            Pair(UsdNotice::id, noticelink.records.map { it.noticeId }),
            page
        ) ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val userNotice = notices.records.map { it.id  to it.createBy}.toMap()
        val query = KtQueryWrapper(UsdUser::class.java)
        query.`in`(UsdUser::uid,userNotice.values)
        val user =  this.baseMapper.selectPage(Page(pageIndex.toLong(),pageItems.toLong()),query)
        val userPortrait =user.records.map { it.uid to it.portrait}.toMap()
        val userNickName = user.records.map { it.uid to it.nickName }.toMap()
        val obj = NoticeView::class.java
        val res = ArrayList<NoticeView>(pageItems.toInt())
        notices.records.forEach {
            val view = obj.newInstance()
            view.context = it.context
            view.createTime = it.createTime.toString()
            view.createrPortrait = userPortrait[userNotice[it.id]!!]!!
            view.status = noticeStatus[it.id]!!
            view.createBy = userNickName[userNotice[it.id]!!]!!
            view.nid = it.id
            res.add(view)
        }
        return Result.ok(res)
    }

    @Transactional
    override fun markNotice(token: String, nid: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val mapper = BeanContext.getBeanbyClazz(UsdUserNoticeMapper::class.java)
        val query = KtUpdateWrapper(UsdUserNotice::class.java)
        query.eq(UsdUserNotice::uid, json["user_id"] as String).eq(UsdUserNotice::noticeId, nid)
            .set(UsdUserNotice::status, 1)
        mapper.update(null, query)
        return Result.ok()
    }

    @Transactional
    override fun removeNotice(token: String, nid: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val mapper = BeanContext.getBeanbyClazz(UsdUserNoticeMapper::class.java)
        val query = KtQueryWrapper(UsdUserNotice::class.java)
        query.eq(UsdUserNotice::uid,json["user_id"] as String).eq(UsdUserNotice::noticeId,nid)
        mapper.delete(query)
        return Result.ok()
    }

    override fun getUserCommonInfo(token: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val query = KtQueryWrapper(UsdUser::class.java)
        query.eq(UsdUser::uid, json["user_id"] as String)
        val user = this.baseMapper.selectOne(query) ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val obj = UserInfoView::class.java.newInstance()
        obj.nickName = user.nickName
        obj.portrait = user.portrait
        obj.occupation = user.occupation
        obj.signature = user.signature
        obj.uid = user.uid
        return Result.ok(obj)
    }

    private fun idCardMask(idCardNum: String): String {
        var res = ""
        val stringBuilder = StringBuilder(idCardNum)
        res = stringBuilder.replace(6, 14, "********").toString()
        return res
    }

    private fun phoneMask(phone: String): String {
        var res = ""
        val stringBuilder = StringBuilder(phone)
        res = stringBuilder.replace(3, 7, "****").toString()
        return res
    }

    override fun getUserProtectedInfo(token: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val userProtectedInfo = UserProtectedInfo(
            phoneMask(json["user_phone"] as String), (json["user_account"] as String), (json["user_gender"] as String),
            idCardMask(json["user_identification"] as String)
        )
        return Result.ok(userProtectedInfo)
    }

    @Transactional
    override fun modfiyUser(token: String, entity: ModifyUserEntity): Result {
        val json = tokenService.getUserInfo(token)!!
        val query = KtUpdateWrapper(UsdUser::class.java)
        query.eq(UsdUser::uid, json["user_id"] as String)
        entity.nickName?.let { query.set(UsdUser::nickName, it) }
        entity.occupation?.let { query.set(UsdUser::occupation, it) }
        entity.portrait?.let { query.set(UsdUser::portrait, it) }
        entity.signature?.let { query.set(UsdUser::signature, it) }
        this.baseMapper.update(null,query)
        return Result.ok()
    }

    override fun selectUserRaceResult(token: String, pageIndex: String, pageItems: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val page = Page<UsdUserAwards>(pageIndex.toLong(),pageItems.toLong())
        val mapper = BeanContext.getBeanbyClazz(UsdUserAwardsMapper::class.java)
        val query = KtQueryWrapper(UsdUserAwards::class.java)
        query.eq(UsdUserAwards::uid,json["user_id"] as String)
        val res = mapper.selectPage(page,query)
        return Result.ok(res.records)
    }

    override fun markAllNotice(token: String, list: List<String>): Result {
        val json = tokenService.getUserInfo(token)!!
        val query = KtUpdateWrapper(UsdUserNotice::class.java)
        query.eq(UsdUserNotice::uid,json["user_id"] as String)
            .`in`(UsdUserNotice::noticeId,list).set(UsdUserNotice::status,1)
        val mapper = BeanContext.getBeanbyClazz(UsdUserNoticeMapper::class.java)
        mapper.update(null,query)
        return Result.ok()
    }

}