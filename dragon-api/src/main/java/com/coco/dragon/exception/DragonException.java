package com.coco.dragon.exception;

import com.coco.dragon.exception.DragonExceptionEnum;

/**
 * @author liaoshen
 */
public class DragonException extends RuntimeException{
    private DragonExceptionEnum e;

    public DragonException(DragonExceptionEnum e) {
        this.e = e;
    }

    public DragonExceptionEnum getE() {
        return e;
    }

    public void setE(DragonExceptionEnum e) {
        this.e = e;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
