package team.jtq.auth.oauth_serve.entity

enum class ResultStatusCode(var code: Int, var msg: String) {
    OK(0, "OK"),
    BAD_REQUEST(400, "参数解析失败"),
    INVALID_TOKEN(401, "无效的Access-Token"),
    METHOD_NOT_ALLOWED(
        405,
        "不支持当前请求方法"
    ),

    SYSTEM_ERR(500, "服务器运行异常"),
    PERMISSION_DENIED(10001, "权限不足"),
    TOKEN_MISS(10002, "Token缺失"),
    VERIFICATIONCODE_ERROR(1003,"验证码错误"),
    UNKONW_ERROR(1004,"未知错误")
}