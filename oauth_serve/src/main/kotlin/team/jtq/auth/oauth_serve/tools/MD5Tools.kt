package team.jtq.auth.oauth_serve.tools

import java.security.MessageDigest

fun byteArrayToHexString(b: ByteArray): String {
    val resultSb = StringBuffer()
    for (i in b.indices) {
        resultSb.append(byteToHexString(b[i]))
    }
    return resultSb.toString()
}

private fun byteToHexString(b: Byte): String {
    var n = b.toInt()
    if (n < 0) {
        n += 256
    }
    val d1 = n / 16
    val d2 = n % 16
    return hexDigits[d1] + hexDigits[d2]
}

fun MD5Encode(origin: String, charsetname: String): String {
    var resultString = origin
    val md: MessageDigest = MessageDigest.getInstance("MD5")
    resultString = if ("" == charsetname) {
        byteArrayToHexString(md.digest(resultString.toByteArray()))
    } else {
        byteArrayToHexString(md.digest(resultString.toByteArray(charset(charsetname))))
    }
    return resultString
}

private val hexDigits = arrayOf(
    "0", "1", "2", "3", "4", "5",
    "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
)