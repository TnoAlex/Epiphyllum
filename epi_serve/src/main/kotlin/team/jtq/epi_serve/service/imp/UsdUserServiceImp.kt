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
        val noticeId = linkService.selectLinkinBeans(
            UsdUserNoticeMapper::class,
            UsdUserNotice::class,
            listOf(Pair(UsdUserNotice::uid, json["user_id"] as String))
        )?.map { it.noticeId } ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val notices = linkService.batchSelectLinkBeansInListOnPage(
            UsdNoticeMapper::class,
            UsdNotice::class,
            Pair(UsdNotice::id, noticeId),
            page
        ) ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        return Result.ok(notices)
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

    override fun getUserCommonInfo(token: String): Result {
        val json = tokenService.getUserInfo(token)!!
        val query = KtQueryWrapper(UsdUser::class.java)
        query.eq(UsdUser::uid, json["user_id"] as String)
        val user = this.baseMapper.selectOne(query) ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        return Result.ok(user)
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
            (json["user_identification"] as String)
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

}