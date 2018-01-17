package com.gdyunst.ystadmin.framework.web.restful.admin;

import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.ISystem;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.CacheDto;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.ChangePasswordRequest;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/api")
@Api(value = "/system/api", description = "系统管理接口")
public class SystemManageController {

    @Autowired
    ISystem iSystem;

    @ApiOperation(httpMethod="POST",value="清空指定用户的缓存",notes="清空指定用户的缓存")
    @RequestMapping(value="/cleanCache", method= RequestMethod.POST)
    public Result cleanCache(@RequestBody Request<CacheDto> request)
    {
        return iSystem.cleanCache(request.getData());
    }

    /**
     * 功能描述:修改用户密码;
     * @param request 请求参数
     * @return 操作成功返回1,操作失败返回0
     * @Author:liujun
     * @Date:2017年1月13日 下午8:11:03
     */
    @ApiOperation(httpMethod="POST", value="修改账号密码", notes="")
    @RequestMapping(value="/changePassword", method=RequestMethod.POST)
    public Result changePassword(@RequestBody Request<ChangePasswordRequest> request) throws  TipsException {
        ChangePasswordRequest cpRequest = request.getData();

        return iSystem.changePassword(cpRequest);
    }

    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public Result logout(@RequestBody Request<UserEntity> request) {

        return iSystem.logout(request);
    }
}
