package com.gdyunst.ystadmin.framework.service.domainservice.admin;

import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.CacheDto;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.ChangePasswordRequest;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;
/**
 * @Description: 系统服务
 * @Author: dengshuai
 * @Date: Created in 17:56 2018/1/16
 * @Modified: by autor in 17:56 2018/1/16
 */
public interface ISystem {
    /**
     * @Description: 清除缓存
     * @Author: dengshuai
     * @Date: Created in 17:55 2018/1/16
     * @Modified: by autor in 17:55 2018/1/16
     */
    public Result cleanCache(CacheDto cacheDto);

    /**
     * @Description: 修改密码
     * @Author: dengshuai
     * @Date: Created in 11:44 2018/1/17
     * @Modified: by autor in 11:44 2018/1/17
     */
    Result changePassword(ChangePasswordRequest cpRequest) throws TipsException;

    /**
     * @Description: 退出登录
     * @Author: dengshuai
     * @Date: Created in 11:44 2018/1/17
     * @Modified: by autor in 11:44 2018/1/17
     */
    Result logout(Request<UserEntity> request);
}
