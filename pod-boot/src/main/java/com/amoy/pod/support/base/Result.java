package com.amoy.pod.support.base;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/1
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public Result() {

    }

    public Result(boolean success) {
        put("success", success);
    }

    public Result put(String key, Object value){
        super.put(key, value);
        return this;
    }

    public static Result ok(){
        return new Result(true).put("status", HttpStatus.OK.value());
    }

    public static Result ok(String message){
        return Result.ok().put("message", message);
    }

    public static Result error(){
        return new Result(false);
    }

    public static Result error(String message){
        return Result.error().put("message", message);
    }

    public static Result error(String message, HttpStatus httpStatus){
        return Result.error(message).put("status", httpStatus.value());
    }

    public boolean isSuccess(){
        return (Boolean)get("success");
    }
}
