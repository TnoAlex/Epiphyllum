package team.jtq.auth.oauth_serve.tools

import com.alibaba.fastjson.JSON
import org.springframework.http.MediaType
import java.io.IOException
import javax.servlet.http.HttpServletResponse


fun writeJavaScript(response: HttpServletResponse, obj: Any?) {
    response.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE
    response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate")
    response.addHeader("Cache-Control", "post-check=0, pre-check=0")
    response.setHeader("Pragma", "no-cache")
    try {
        val out = response.writer
        out.write(JSON.toJSONString(obj))
        out.flush()
        out.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}