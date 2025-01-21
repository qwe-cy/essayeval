package com.cccy.essayeval.common;

import lombok.Data;

@Data
public class Result<T> {

    private String status;

    private String message;

    // 这里的 T 表示 Data 字段可以是任何类型，具体类型由创建 Result 实例时指定
    private T data;

    private long timestamp;

    public Result result(String status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public static <T> Result success(String message, T data){
        Result result = new Result();
        result.setStatus("200");
        result.setMessage(message);
        result.setData(data);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    public static <T> Result success(T data){
        Result result = new Result();
        result.setStatus("200");
        result.setMessage("success");
        result.setData(data);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    public static Result error(String message){
        Result result = new Result();
        result.setStatus("500");
        result.setMessage(message);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }
}
