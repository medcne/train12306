package com.leoliu.train.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum exceptionEnum;
    public BusinessException(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }



}
