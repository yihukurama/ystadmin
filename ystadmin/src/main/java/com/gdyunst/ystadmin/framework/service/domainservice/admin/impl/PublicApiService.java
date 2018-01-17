package com.gdyunst.ystadmin.framework.service.domainservice.admin.impl;

import java.util.Date;
import java.util.List;

import com.gdyunst.ystadmin.application.cache.AppCache;
import com.gdyunst.ystadmin.application.cache.RedisUtils;
import com.gdyunst.ystadmin.application.constant.Constant;
import com.gdyunst.ystadmin.application.security.JwtTokenGenerator;
import com.gdyunst.ystadmin.application.utils.EncrUtil;
import com.gdyunst.ystadmin.application.utils.LogUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.RoleprivilegeEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.SubsystemEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.Subsystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.IPublicApi;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.ISecurity;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class PublicApiService implements IPublicApi{

    @Autowired
    ISecurity securityService;
    @Autowired
    RedisUtils redisUtils;
    @Override
    public Result login(Request<User> request) throws TipsException {
        UserEntity user = request.getData();
        SubsystemEntity currentSubsystem = request.getData().getSubsystemEntity();
        if(currentSubsystem == null || EmptyUtil.isEmpty(currentSubsystem.getCode())){
            return Result.failed("参数错误，请检查要登录的系统编号");
        }
        Subsystem loginSubsystem = new Subsystem();
        loginSubsystem.setCode(currentSubsystem.getCode());
        currentSubsystem = loginSubsystem.findByCode();
        if (currentSubsystem==null) {
            return Result.failed("无此系统");
        }
        if(user == null || EmptyUtil.isEmpty(user.getUsername()) || EmptyUtil.isEmpty(user.getPassword())){
            return Result.failed("参数错误，请检查接口参数");
        }
        //密码加密
        String encryptPwd = securityService.pwdEncrypt(user.getPassword());
        user.setPassword(encryptPwd);
        
        List<UserEntity> userEntitys = user.list();
        if(userEntitys == null || userEntitys.size()!=1){
            return Result.failed("请检查用户名密码");
        }
        user = userEntitys.get(0);
        User loginUser = new User();
        loginUser.setSubsystemEntity(currentSubsystem);
        loginUser.setId(user.getId());
        loginUser.load();
        loginUser.doGetRelationData();
        LogUtil.DebugLog(this,"当前登录的系统是"+currentSubsystem.getId());
        boolean canLogin = securityService.hasAuthor(loginUser,currentSubsystem.getId());
        if(!canLogin){
            return Result.failed("用户无此系统权限");
        }

        String token = JwtTokenGenerator.generateToken(loginUser, Constant.JWTSECRET);
        LogUtil.DebugLog(this,"token is"+token);
        loginUser.setToken(token);
        loginUser.setLastLoginDate(new Date());
        loginUser.setLoginCount(loginUser.getLoginCount() + 1);
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httprequest = sra.getRequest();
        loginUser.setIpAddr(LogUtil.getIpAddr(httprequest));
        loginUser.update();
        String secret = EncrUtil.GetMD5Code(token);
        loginUser.setSecret(secret);
        redisUtils.set(loginUser.getId()+Constant.encryptKey, token, Constant.LOGIN_EXPIRETIME);
        redisUtils.set(secret+Constant.encryptKey, loginUser, Constant.LOGIN_EXPIRETIME);
        if(!AppCache.loginTree.contains(loginUser)){
            AppCache.loginTree.add(loginUser);
        }
        LogUtil.DebugLog(this,"当前登录人数为："+AppCache.loginTree.size());
        return Result.successed(loginUser, "登录成功");
    }

    @Override
    public Result doGetSMSCode(Request<String> request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result getAuths(Request<User> request) {
        User loginUser = request.getData();
        String secret = loginUser.getSecret();
        if (EmptyUtil.isEmpty(secret)){
            return Result.failed("secret 不能为空");
        }
        User authorsUser = (User) redisUtils.get(secret+Constant.encryptKey);
        if(authorsUser == null){
            return Result.failed("获取用户权限失败");
        }
        return Result.successed(authorsUser);
    }

}
