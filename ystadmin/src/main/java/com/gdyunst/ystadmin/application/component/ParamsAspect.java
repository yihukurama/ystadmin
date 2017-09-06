package com.gdyunst.ystadmin.application.component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.application.cache.RedisUtils;
import com.gdyunst.ystadmin.application.config.SystemConfig;
import com.gdyunst.ystadmin.application.constant.Constant;
import com.gdyunst.ystadmin.application.constant.ResponseEnum;
import com.gdyunst.ystadmin.application.utils.LogUtil;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;

/**
 * 功能描述:事务拦截器
 *
 * @Author:liujun
 * @Date:2016年12月16日 上午9:54:44
 */
@Aspect
@Component
public class ParamsAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParamsAspect.class);
	String methodName;

	@Autowired
	RedisUtils redisUtils;
	@Autowired
	SystemConfig urlConfig;

	/**
	 * 功能描述:开启事务;
	 *
	 * @param joinPoint
	 *            连接点
	 * @Author:liujun
	 * @Date:2016年12月16日 上午10:08:38
	 */

	@Before("execution (com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result com..web..*(..)) ")
	public void before(JoinPoint joinPoint) {
		try {
			methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

			System.out.println(methodName + "方法开始");

			Object[] args = joinPoint.getArgs();
			StringBuilder sb = new StringBuilder();
			if (args != null || args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					Object arg = args[i];
					if (arg instanceof Integer || arg instanceof String || arg instanceof Boolean) {
						sb.append(arg);
						if (i != args.length - 1) {
							sb.append(",");
						}
					} else if (arg instanceof List || arg instanceof Request) {
						sb.append(JSON.toJSONString(arg));
						if (i != args.length - 1) {
							sb.append(",");
						}
					}
				}
			}

			LOGGER.debug("methodName:{} params:{} ", methodName, sb.toString());
		} catch (Exception e) {
			LOGGER.error("controller aop before error:{}", e);
		}

	}

    @Around("execution (* com..web..*(..)) ")
	public Object around(ProceedingJoinPoint pjp) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest hsRequest = sra.getRequest();
		String url = hsRequest.getRequestURL().toString();
		LOGGER.debug("当前请求的URL是:{}", url);

		String simpleMethodName = pjp.getSignature().getName();
		Object[] os = pjp.getArgs();
		LogUtil.writeLog(hsRequest, os, urlConfig.getUrlLogFile());
		for (int i = 0; i < os.length; i++) {
			if (os[i] instanceof Request) {
				Request request = (Request) os[i];
				if (request == null) {
					return Result.failed("请求参数request不能为空");
				}
				if (request.getUid() == null) {
					return Result.failed(JSON.toJSONString(request),"请求参数中没有uid!收到的请求是>>>>>"+JSON.toJSONString(request));
				}

				// 判断用户是否登录
				if (!url.contains("/public/") && !redisUtils.exists(request.getUid())) {
				    LOGGER.debug("获得的请求数据是>>>>>>",JSON.toJSONString(request));
					return Result.failed(ResponseEnum.ERROR_CODE_4003.getCode());
				}else{
					//更新缓存
					if (redisUtils.exists(request.getUid())) {
						String token = (String) redisUtils.get(request.getUid());
						redisUtils.set(request.getUid(), token, Constant.LOGIN_EXPIRETIME);
						LOGGER.debug("更新登录状态...延长{}",Constant.LOGIN_EXPIRETIME);
					}
				}

				if (simpleMethodName.startsWith("list")) {
					if (request.getPage() == null || request.getPage() < 0) {
						
						return Result.failed("非法的page参数");
					}
				}

				if (request.getPage() != null && request.getPage() > 0 && request.getLimit() != null
						&& request.getLimit() <= 0) {
					return Result.failed("page参数大于0时,limit参数必须大于0");
				}

				break;
			}
		}

		Object object = null;
		String tips = "";
		try {
			object = pjp.proceed();

		} catch (Throwable e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			e.printStackTrace();
			if (e instanceof ClassNotFoundException) {
				tips = "请检查路径是否合法!";
			}

			if (e != null && e.getMessage() != null && !e.getMessage().equals("")) {
				tips += e.getMessage();
			} else {
				tips += "后台异常信息为:" + sw.toString();
				LOGGER.error(tips);
			}

			return Result.failed(tips);

		}
		return object;
	}

	/**
	 * 功能描述:提交事务;
	 *
	 * @Author:liujun
	 * @Date:2016年12月16日 上午10:09:10
	 */
	@AfterReturning("execution (com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result com..controller..*(..)) ")
	public void afterReturning() {

		LOGGER.info(methodName + "方法结束");
	}

}
