package team.jtq.auth.oauth_serve.tools

import java.util.Random


fun randomGen(place: Int): String {
    val base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789"
    val sb = StringBuffer()
    val rd = Random()
    for (i in 0 until place) {
        sb.append(base[rd.nextInt(base.length)])
    }
    return sb.toString()
}