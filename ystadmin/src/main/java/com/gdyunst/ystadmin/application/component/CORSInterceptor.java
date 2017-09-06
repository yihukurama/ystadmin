package com.gdyunst.ystadmin.application.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:开启跨域请求的拦截器 
 * @Author:denghsuai
 * @Date:2017年1月10日 下午3:03:39
 */
public class CORSInterceptor implements  HandlerInterceptor  {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		//内网服务器可通过其它端口访问
		arg1.addHeader("Access-Control-Allow-Origin", "*");

        return true;
	}

}
