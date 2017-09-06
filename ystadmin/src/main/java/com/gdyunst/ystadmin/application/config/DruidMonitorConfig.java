package com.gdyunst.ystadmin.application.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 功能描述:Druid数据库连接池监控配置;
 * @Author:liujun
 * @Date:2016年9月29日 下午2:11:18
*/
@Configuration
public class DruidMonitorConfig
{
	/**
	 * 功能描述:注册Druid监控页面Servlet;
	 * @return
	 * @Author:liujun
	 * @Date:2016年9月29日 下午3:18:26
	 */
	@Bean 
	public ServletRegistrationBean druidServlet() 
	{
		ServletRegistrationBean servletRegistrationBean = new 
			ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		// 可以访问Druid监控界面的ip;
		// servletRegistrationBean.addInitParameter("allow","127.0.0.1,192.168.0.1/24");
		// 不可以访问Druid监控界面的ip;
	    //servletRegistrationBean.addInitParameter("deny","192.168.1.73");
	   
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
	    servletRegistrationBean.addInitParameter("loginPassword", "admin123");
	    servletRegistrationBean.addInitParameter("resetEnable", "false");
		
		return servletRegistrationBean;
	}

	/**
	 * 功能描述:Druid监控过滤器;
	 * @return
	 * @Author:liujun
	 * @Date:2016年9月29日 下午3:22:50
	 */
	@Bean 
	public FilterRegistrationBean filterRegistrationBean()
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    filterRegistrationBean.setFilter(new WebStatFilter());
	    filterRegistrationBean.addUrlPatterns("/*");
	    filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	    
	    return filterRegistrationBean;
	}

}
