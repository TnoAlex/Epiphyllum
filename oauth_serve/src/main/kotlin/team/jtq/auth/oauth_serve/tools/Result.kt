package team.jtq.auth.oauth_serve.tools

import lombok.Data
import team.jtq.auth.oauth_serve.entity.ResultStatusCode

import java.io.Serializable


@Data
class Result : Serializable {
    val success: Boolean
        get() = this.code == 0
    private var msg = "ok"

    private var code = 0

    private var data: Any? = null
    constructor() {}

    @JvmOverloads
    constructor(code: Int, msg: String, data: Any? = null) {
        this.code = code
        this.msg = msg
        this.data = data
    }

    @JvmOverloads
    constructor(resultStatusCode: ResultStatusCode, data: Any? = null) : this(
        resultStatusCode.code,
        resultStatusCode.msg,
        data
    ) {
    }

    companion object {
        private const val serialVersionUID = 1L
        @JvmStatic
        fun ok(): Result {
            return Result(ResultStatusCode.OK)
        }
        @JvmStatic
        fun ok(obj: Any?): Result {
            return Result(ResultStatusCode.OK, obj)
        }
        @JvmStatic
        fun error(resultStatusCode: ResultStatusCode): Result {
            return Result(resultStatusCode)
        }
        @JvmStatic
        fun error(resultStatusCode: ResultStatusCode, obj: Any?): Result {
            return Result(resultStatusCode, obj)
        }
        @JvmStatic
        fun error(msg: String): Result {
            return Result(5000, msg, null)
        }
    }
}