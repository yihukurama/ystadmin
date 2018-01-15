package com.gdyunst.ystadmin.application.exception;

/**
 * 功能描述:向前端返回的提示异常,例如参数不正确,业务条件不满足等 
 * @Author:denghsuai
 * @Date:2017年11月29日 下午3:42:00
 */
public class TipsException extends Exception {
    private static final long serialVersionUID = 1L;

    public TipsException(String message) {
        super(message);
    }
}
