package team.jtq.epi_serve.resourceTest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import team.jtq.epi_serve.config.AppResourceConfig

@SpringBootTest
class ResourceTest {

    @Test
    fun test(){
        println(AppResourceConfig.appResourcesId)
    }
}