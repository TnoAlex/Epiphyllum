package team.jtq.auth.oauth_serve.service


interface EmailService {
    fun sendConfirmationEmail(address: String, target: String, subject: String):Boolean
}