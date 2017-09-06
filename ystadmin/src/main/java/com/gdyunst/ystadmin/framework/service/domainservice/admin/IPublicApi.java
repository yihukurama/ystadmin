package com.gdyunst.ystadmin.framework.service.domainservice.admin;

import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;

public interface IPublicApi {

    public Result login(Request<UserEntity> request) throws Exception;
}
