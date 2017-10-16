package com.gdyunst.ystadmin.framework.service.domainservice.admin.impl;

import org.springframework.stereotype.Service;

import com.gdyunst.ystadmin.application.utils.EncrUtil;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.ISecurity;
@Service
public class SecurityService implements ISecurity{

    
    
    @Override
    public String pwdEncrypt(String pwd) {
        //避免撞库
        pwd = EncrUtil.GetMD5Code(pwd);
       
        return pwd;
    }


}
