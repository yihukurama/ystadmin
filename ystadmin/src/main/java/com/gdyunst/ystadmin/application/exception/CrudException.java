package com.gdyunst.ystadmin.application.exception;

/**
 * 功能描述:仅向外层抛出执行数据库时的异常 
 * @Author:denghsuai
 * @Date:2017年1月16日 下午4:10:16
 */
public class CrudException extends Exception {
	public CrudException(String message) {
        super(message);
    }
}
