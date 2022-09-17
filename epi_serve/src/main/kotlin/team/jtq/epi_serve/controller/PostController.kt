package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import team.jtq.epi_serve.entity.ao.PostUpLoadeEntity
import team.jtq.epi_serve.service.UsdPostService
import team.jtq.epi_serve.tools.Result

@Controller
class PostController {

    @Autowired
    private lateinit var postService: UsdPostService


    @PostMapping("/usd/post_upload")
    @ResponseBody
    fun upLoadPost(@RequestBody entity: PostUpLoadeEntity): Result {
        return postService.addUserPost(entity)
    }
}