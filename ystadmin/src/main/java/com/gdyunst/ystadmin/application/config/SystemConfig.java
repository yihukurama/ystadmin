package com.gdyunst.ystadmin.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "ystsystemconfig")
@Configuration
public class SystemConfig {
    // 写日志
    private Boolean isWriteLog; // 是否写日志
    private String urlLogFile;
    
    
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
