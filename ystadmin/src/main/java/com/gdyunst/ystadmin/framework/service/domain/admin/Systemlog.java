package com.gdyunst.ystadmin.framework.service.domain.admin;

import java.util.Date;

import com.gdyunst.ystadmin.application.exception.TipsException;
import org.springframework.transaction.annotation.Transactional;

import com.gdyunst.ystadmin.framework.domain.entity.admin.SystemlogEntity;


/**
 * 功能描述:保存系统日志数据;实体类
 * 
 * @author CBQ
 * @date 2017年01月14日 11:28:28
 */
public class Systemlog extends SystemlogEntity{

    @Transactional
    public void addSystemLog(String requestIP, String interfaceAddress,String jsonStr) throws TipsException {
        // 插入日志数据
        Date now = new Date();
        Systemlog caseLog = new Systemlog();
        
        caseLog.setRequestIP(requestIP);
        caseLog.setUrl(interfaceAddress);
        caseLog.setContent(jsonStr);
        caseLog.create();
  
    }
}
