package team.jtq.epi_serve.tools

import java.io.PrintWriter
import javax.servlet.http.HttpServletResponse

fun returnJson(response: HttpServletResponse, msg:String, code:String) {
    response.characterEncoding = "UTF-8"
    response.contentType = "application/json; charset=utf-8"
    val writer: PrintWriter = response.writer
    val result:MutableMap<String,Any?> = mutableMapOf(Pair(code,msg))
    writer.print(result)
}
