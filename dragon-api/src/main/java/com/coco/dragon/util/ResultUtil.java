package com.coco.dragon.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultUtil {
    private int statusCode;
    private String message;
    private Object data;

    private ResultUtil(int statusCode,String message,Object data){
        this.statusCode=statusCode;
        this.message=message;
        this.data=data;
    }

    public static ResultUtil successResult(Object object){
        return new ResultUtil(600,"",object);
    }

    public static ResultUtil failResult(Object object){
        return new ResultUtil(900,"",null);
    }

}
