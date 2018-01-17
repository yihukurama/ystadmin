package com.gdyunst.ystadmin.framework.service.domainservice.admin.impl;

import com.gdyunst.ystadmin.application.cache.AppCache;
import com.gdyunst.ystadmin.application.cache.RedisUtils;
import com.gdyunst.ystadmin.application.constant.Constant;
import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.application.utils.EncrUtil;
import com.gdyunst.ystadmin.application.utils.LogUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.ISecurity;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.ISystem;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.CacheDto;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.ChangePasswordRequest;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService implements ISystem {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ISecurity security;

    @Override
    public Result cleanCache(CacheDto cDto) {
        String suffix = cDto.getSuffix();
        String prex = cDto.getPrex();
        if (suffix != null) {
            redisUtils.removeBySuffix(suffix);
        }
        if (prex != null) {
            redisUtils.removeByPrex(prex);
        }


        return Result.successed("清空缓存成功！");
    }

    @Override
    public Result changePassword(ChangePasswordRequest cpRequest) throws TipsException {
        Result result = new Result();

        if (EmptyUtil.isEmpty(cpRequest.getOldPassword()) || EmptyUtil.isEmpty(cpRequest.getNewPassword()) ||
                EmptyUtil.isEmpty(cpRequest.getUserId())) {
            result.setSuccess(false);
            result.setMsg("参数错误");

            return result;
        }

        String oldPwd = security.pwdEncrypt(cpRequest.getOldPassword());
        String newPwd = security.pwdEncrypt(cpRequest.getNewPassword());
        String uid = cpRequest.getUserId();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(uid);
        userEntity.load();
        if(!userEntity.getPassword().equals(oldPwd)){
            return Result.failed("旧密码不正确");
        }

        userEntity.setPassword(newPwd);
        userEntity.update();
        return Result.successed("修改成功");

    }


    /**
     * @Description: 退出登录
     * @Author: dengshuai
     * @Date: Created in 14:12 2018/1/17
     * @Modified: by autor in 14:12 2018/1/17
     */
    @Override
    public Result logout(Request<UserEntity> request) {
        UserEntity userEntity = request.getData();
        userEntity = userEntity.load();
        if(userEntity == null){
            return Result.failed("无此用户，退出登录失败");
        }
        if(redisUtils.exists(userEntity.getId()+Constant.encryptKey)){
            String secert = EncrUtil.GetMD5Code(userEntity.getToken());
            redisUtils.remove(secert+Constant.encryptKey);
            redisUtils.remove(userEntity.getId()+Constant.encryptKey);
        }
        User user = new User();
        user.setId(userEntity.getId());
        if(AppCache.loginTree.contains(user)){
            AppCache.loginTree.remove(user);
        }
        LogUtil.DebugLog(this,"有用户退出，当前登录人数为："+AppCache.loginTree.size());
        return Result.successed("退出登录成功");
    }
}
