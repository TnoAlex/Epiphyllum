package team.jtq.epi_serve.entity.ao

enum class ResultStatusCode(var code: Int, var msg: String) {
    OK(200, "OK"),
    BAD_REQUEST(400, "无效的请求"),
    INVALID_TOKEN(401, "无效的Access-Token"),
    METHOD_NOT_ALLOWED(
        405,
        "不支持当前请求方法"
    ),
    SERVICE_INNER_ERR(500,"服务器内部错误"),
    AUTHENTICATION_ERR(403,"用户名或密码错误"),
    AUTHENTICATION_CONNECT_ERR(500, "认证服务连接异常"),
    PERMISSION_DENIED(401, "权限不足"),
    TOKEN_MISS(403, "Token缺失"),
    VERIFICATIONCODE_ERROR(406,"验证码错误"),
    UNKONW_ERROR(500,"未知错误"),
    TOKEN_EXPIRED(403,"Token已过期"),
    FILE_CHECK_ERR(500,"文件校验失败"),
    FILE_NOT_FIND(500,"文件未找到"),
    INVALID_OPERATION(409,"无效操作")
}