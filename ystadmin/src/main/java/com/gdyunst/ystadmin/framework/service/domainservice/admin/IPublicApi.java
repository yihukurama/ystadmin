package com.gdyunst.ystadmin.framework.service.domainservice.admin;

import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;

public interface IPublicApi {

    //登录
    public Result login(Request<User> request) throws Exception;
    
    //获取短信验证码
    public Result doGetSMSCode(Request<String> request);

    //获取用户权限
    public Result getAuths(Request<User> request);
}
