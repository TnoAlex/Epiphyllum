package team.jtq.auth.oauth_serve.service.imp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.service.EmailService
import org.springframework.mail.javamail.MimeMessageHelper
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import javax.annotation.Resource

@Service
class EmailServiceImp: EmailService {
    @Autowired
    private lateinit var templateEngine:TemplateEngine

    @Resource
    private lateinit var mailSender: JavaMailSender

    @Value("spring.mail.username")
    private lateinit var from:String

    override fun sendConfirmationEmail(address: String, target: String, subject: String): Boolean {
        val message = mailSender.createMimeMessage()
        return try {
            val helper = MimeMessageHelper(message,true)
            helper.setFrom(from)
            helper.setTo(target)
            helper.setSubject(subject)
            val context = Context()
            context.setVariable("confirmAddress", address)
            val text = templateEngine.process("mailTemplate",context)
            helper.setText(text,true)
            mailSender.send(message)
            true
        }catch (e:Exception){
            false
        }
    }
}