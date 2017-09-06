package com.gdyunst.ystadmin.application.constant;

/**
 * 
 * 功能描述:websocket命令枚举类 
 * @Author:denghsuai
 * @Date:2017年2月24日 上午10:41:42
 */
public enum ResponseEnum {
	ERROR_CODE_4003("4003","用户未登录或登录过期")

	;	

    private String code;
    private String desc;

    ResponseEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

   
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static ResponseEnum buildByType(String code){
        if (code == null){
            return null;
        }
        for (ResponseEnum socketCmdEnum : ResponseEnum.values()){
            if (socketCmdEnum.getCode().equals(code)){
                return socketCmdEnum;
            }
        }
        return null;
    }
}
