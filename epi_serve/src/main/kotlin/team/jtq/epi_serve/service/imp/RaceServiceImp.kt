package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson2.JSON
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import team.jtq.epi_serve.config.AppResourceConfig
import team.jtq.epi_serve.entity.UsdRace
import team.jtq.epi_serve.entity.UsdUserRace
import team.jtq.epi_serve.entity.ao.RaceUpLoadEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.mapper.UsdRaceMapper
import team.jtq.epi_serve.mapper.UsdUserRaceMapper
import team.jtq.epi_serve.service.RaceService
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdLinkService
import team.jtq.epi_serve.tools.Result
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class RaceServiceImp : ServiceImpl<UsdRaceMapper, UsdRace>(), RaceService {

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var linkService: UsdLinkService

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    private val RACE_CACHE = "RACE_CACHE"

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
        val page = Page<UsdRace>(pageIndex.toLong(),pageItems.toLong())
        val mouthBefore = LocalDateTime.now().minusMonths(1)
        val query = KtQueryWrapper(UsdRace::class.java)
        query.ge(UsdRace::raceStartTime, mouthBefore)
        val races = this.baseMapper.selectPage(page,query)
        return Result.ok(races.records)
    }

    override fun registrationRace(token: String, rid: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val res = linkService.addLinkinBeans(
            UsdUserRaceMapper::class,
            UsdUserRace::class,
            Pair(UsdUserRace::raceId.name,rid),
            Pair(UsdUserRace::uid.name,json["user_id"] as String)
        )
        return if(res)
            Result.ok()
        else
            Result.error(ResultStatusCode.SERVICE_INNER_ERR)
    }

}