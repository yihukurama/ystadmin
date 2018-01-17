package com.gdyunst.ystadmin.application.msg;

import java.io.Serializable;

/**
 * 功能描述:基础消息封装类 
 * @Author:denghsuai
 * @Date:2016年12月17日 下午7:08:41
 */
public class BaseMsg implements Serializable {


		
	private static final long serialVersionUID = 1L;
	private String cmd;
	private String tips;
	private Object data;
	
	
	public BaseMsg(String cmd, String tips, Object data) {
		super();
		this.cmd = cmd;
		this.tips = tips;
		this.data = data;
	}
	
	
	public BaseMsg() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}


	@Override
	public String toString() {
		return "BaseMsg [cmd=" + cmd + ", tips=" + tips + ", data=" + data + "]";
	}

	
	
}
