package com.gdyunst.ystadmin.framework.service.domainservice.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdyunst.ystadmin.application.exception.CrudException;
import com.gdyunst.ystadmin.application.utils.StringUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.IPublicApi;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.ISecurity;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;
@Service
public class PublicApiService implements IPublicApi{

    @Autowired
    ISecurity securityService;
    
    @Override
    public Result login(Request<UserEntity> request) throws CrudException {
        UserEntity userEntity = request.getData();
        if(userEntity == null || StringUtil.isEmpty(userEntity.getUsername()) || StringUtil.isEmpty(userEntity.getPassword())){
            return Result.failed("参数错误，请检查接口参数");
        }
        //密码加密
        String encryptPwd = securityService.pwdEncrypt(userEntity.getPassword());
        userEntity.setPassword(encryptPwd);
        
        List<UserEntity> userEntitys = userEntity.list();
        if(userEntitys == null || userEntitys.size()!=1){
            return Result.failed("请检查用户名密码");
        }
        userEntity = userEntitys.get(0);
        User user = new User();
        user.setId(userEntity.getId());
        user.load();
        user.doGetRelationData();
        
        
        return Result.successed(user, "登录成功");
    }

}
