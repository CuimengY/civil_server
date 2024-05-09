package com.exam.civil.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final String SUCCESS = "success";

    private Integer code;
    private String message;
    private T data;

    public static <T> Result success(){
        return new Result(0,SUCCESS,null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(0,SUCCESS,data);
    }

    public static <T> Result failed(String message) {
        return new Result(1,message,null);
    }

}
