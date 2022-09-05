package team.jtq.epi_serve.config

import com.google.code.kaptcha.impl.DefaultKaptcha
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.google.code.kaptcha.util.Config
import java.util.*


@Configuration
class KaptchaConfig {
    @Bean
    fun producer(): DefaultKaptcha {
        val properties = Properties()
        //图片边框
        properties.put("kaptcha.border", "no")
        //边框的颜色
        properties.put("kaptcha.textproducer.font.color", "black")
        properties.put("kaptcha.image.width","130")
        properties.put("kaptcha.image.height","45")
        //验证码大小
        properties.put("kaptcha.textproducer.char.space", "5")
        //字体
        properties.put("kaptcha.textproducer.font.names", "Arial,Courier,cmr10,宋体,楷体,微软雅黑")
        //提交设定到 Kaptcha 中的Config中
        val config = Config(properties)
        val defaultKaptcha = DefaultKaptcha()
        defaultKaptcha.config = config
        return defaultKaptcha
    }
}

