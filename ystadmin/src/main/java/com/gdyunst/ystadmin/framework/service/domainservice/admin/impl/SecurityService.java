package com.gdyunst.ystadmin.framework.service.domainservice.admin.impl;

import com.gdyunst.ystadmin.framework.domain.entity.admin.RoleprivilegeEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.Roleprivilege;
import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import org.springframework.stereotype.Service;

import com.gdyunst.ystadmin.application.constant.Constant;
import com.gdyunst.ystadmin.application.utils.EncrUtil;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.ISecurity;

import java.util.List;

@Service
public class SecurityService implements ISecurity{

    
    
    @Override
    public String pwdEncrypt(String pwd) {
        //避免撞库
        pwd=pwd+Constant.encryptKey;
        pwd = EncrUtil.GetMD5Code(pwd);
       
        return pwd;
    }

    @Override
    public String tokenEncrypt(String token) {
        String encryptFirst = EncrUtil.AESEncrypt(token);
        String result = EncrUtil.AESEncrypt(encryptFirst);
        return result;
    }

    @Override
    public String tokenDecrypt(String encryptToken) {
        String encryptFirst = EncrUtil.AESDecrypt(encryptToken);
        String result = EncrUtil.AESDecrypt(encryptFirst);
        return result;
    }

    @Override
    public boolean hasAuthor(User loginUser, String previlegeId) {
        if(loginUser==null || loginUser.load()==null){
            return false;
        }
        if(loginUser.getUsername().equals("admin")){
            return true;
        }
        loginUser.doGetRelationData();
        List<RoleprivilegeEntity> roleprivilegeEntities = loginUser.getPrivileges();
        for (RoleprivilegeEntity roleprivilege:roleprivilegeEntities
             ) {
            if(roleprivilege.getPrivilegeId().equals(previlegeId)){
                return true;
            }
        }
        return false;
    }


}
