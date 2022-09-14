package team.jtq.auth.oauth_serve.tools;

import lombok.Data;
import team.jtq.auth.oauth_serve.entity.ResultStatusCode;


import java.io.Serializable;

/**
 * 接口返回数据格式
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean success = true;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private String msg = "ok";

    private Integer code = 0;

    private Object data;


    public Result() {

    }
    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultStatusCode resultStatusCode, Object data) {
        this(resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
    }

    public Result(int code, String msg) {
        this(code, msg, null);
    }

    public Result(ResultStatusCode resultStatusCode) {
        this(resultStatusCode, null);
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    public static Result ok() {
        return new Result(ResultStatusCode.OK);
    }

    public static Result ok(Object obj) {
        return new Result(ResultStatusCode.OK, obj);
    }

    public static Result error(ResultStatusCode resultStatusCode){
        return new Result(resultStatusCode);
    }

    public static Result error(ResultStatusCode resultStatusCode, Object obj){
        return new Result(resultStatusCode, obj);
    }

    public static Result error(String msg){
        return new Result(5000, msg, null);
    }
}