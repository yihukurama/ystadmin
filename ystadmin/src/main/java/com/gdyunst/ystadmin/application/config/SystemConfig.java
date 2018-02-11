package com.gdyunst.ystadmin.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "ystsystemconfig")
@Configuration
public class SystemConfig {
    // 写日志
    private Boolean isWriteLog; // 是否写日志
    private String urlLogFile;
    private String staticUrl;   //系统静态文件存储路径
    
    //系统id
    private String adminSystemId;	//管理后台

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public String getAdminSystemId() {
		return adminSystemId;
	}

	public void setAdminSystemId(String adminSystemId) {
		this.adminSystemId = adminSystemId;
	}

	public String getUrlLogFile() {
        return urlLogFile;
    }

    public void setUrlLogFile(String urlLogFile) {
        this.urlLogFile = urlLogFile;
    }

    public Boolean getIsWriteLog() {
        return isWriteLog;
    }

    public void setIsWriteLog(Boolean isWriteLog) {
        this.isWriteLog = isWriteLog;
    }

}
