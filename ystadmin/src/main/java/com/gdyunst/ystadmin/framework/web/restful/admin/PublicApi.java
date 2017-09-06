package com.gdyunst.ystadmin.framework.web.restful.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.IPublicApi;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/public/api")
@Api(value = "/public/api", description = "公开的api接口")
public class PublicApi {

    @Autowired
    IPublicApi publicApiService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result login(@RequestBody Request<UserEntity> request) throws Exception {
        
        
       
        return publicApiService.login(request);
    }
    
    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public Result logout(@RequestBody Request<UserEntity> request) {

        return Result.failed("注销失败");
    }
}
