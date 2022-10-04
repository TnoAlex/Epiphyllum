package team.jtq.epi_serve

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import team.jtq.epi_serve.entity.UsdUserPost
import team.jtq.epi_serve.mapper.UsdUserPostMapper
import team.jtq.epi_serve.service.MapperReflectionService

@SpringBootTest
class ReflectTest {

    @Autowired
    private lateinit var linkService: MapperReflectionService
    @Test
    fun test(){
        linkService.addLinkinBeans(UsdUserPostMapper::class,UsdUserPost::class,Pair(UsdUserPost::postId.name,"123"),Pair(UsdUserPost::uid.name,"321"))
    }
}