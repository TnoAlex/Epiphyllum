package team.jtq.auth.oauth_serve.tools

import com.google.code.kaptcha.Producer
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.util.*
import javax.annotation.Resource
import javax.imageio.ImageIO

@Component
class VerificationCodeGenerater {

    @Resource
    private lateinit var producer:Producer
    fun generate(): Array<String> {
        val code =  producer.createText()
        val image = producer.createImage(code)
        val stream = ByteArrayOutputStream()
        ImageIO.write(image,"jpg",stream)
        val base64Image = Base64.getEncoder().encode(stream.toByteArray())
        return arrayOf(code , "data:image/jpeg;base64,$base64Image")
    }
}