package com.gdyunst.ystadmin.framework.web.restful.admin.dto;

/**
 * 功能描述:清空权限所传入的dto 
 * @Author:denghsuai
 * @Date:2017年2月28日 下午7:34:22
 */
public class CacheDto {
	String prex;         //前缀
	String suffix;       //后缀
	
	
	public String getPrex() {
		return prex;
	}
	public void setPrex(String prex) {
		this.prex = prex;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
}
