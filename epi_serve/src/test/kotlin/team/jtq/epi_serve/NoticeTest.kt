package team.jtq.epi_serve

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import team.jtq.epi_serve.entity.UsdNotice
import team.jtq.epi_serve.entity.UsdUserNotice
import team.jtq.epi_serve.mapper.UsdNoticeMapper
import team.jtq.epi_serve.mapper.UsdUserNoticeMapper
import java.time.LocalDateTime

@SpringBootTest
class NoticeTest {
    @Autowired
    private lateinit var userNoticeMapper: UsdUserNoticeMapper
    @Autowired
    private lateinit var noticeMapper: UsdNoticeMapper

    @Test
    fun addNotice(){
        val list = ArrayList<String>()
        for(i in 0..20){
            list.add("这是测试文本$i")
        }
        val obj = UsdNotice::class.java
        val obj1 = UsdUserNotice::class.java
        for(i in list){
            val notice = obj.newInstance()
            notice.createBy ="System"
            notice.context = i
            notice.createTime = LocalDateTime.now()
            noticeMapper.insert(notice)
            val link = obj1.newInstance()
            link.noticeId=notice.id
            link.status=0
            link.uid = "1574411372584873985"
            userNoticeMapper.insert(link)
        }
        println()
    }
}