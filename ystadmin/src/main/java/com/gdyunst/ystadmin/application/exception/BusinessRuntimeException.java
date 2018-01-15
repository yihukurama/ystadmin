package com.gdyunst.ystadmin.application.exception;

/**
 * 功能描述:业务运行时异常,用于中断业务 
 * @Author:denghsuai
 * @Date:2017年11月29日 下午3:40:28
 */
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessRuntimeException(String message) {
        super(message);
    }
    
}
