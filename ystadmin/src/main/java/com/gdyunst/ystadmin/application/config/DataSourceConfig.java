package com.gdyunst.ystadmin.application.config;

import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;


/**
 * 功能描述:数据源配置;
 * @Author:liujun
 * @Date:2017年2月22日 下午3:21:42
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig implements EnvironmentAware
{
	private Environment environment; 
    private RelaxedPropertyResolver propertyResolver; 
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
    
    @Override 
    public void setEnvironment(Environment environment) 
    { 
    	this.environment = environment; 
    	this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource."); 
    } 
    
    /**
     * 功能描述:注册aibaba Druid数据源;
     * @return DataSource对象
     * @throws SQLException
     * @Author:liujun
     * @Date:2017年2月22日 下午3:23:00
     */
    @Bean(initMethod="init", destroyMethod="close") 
    public DruidDataSource dataSource() throws SQLException
    { 
    	if (StringUtils.isEmpty(propertyResolver.getProperty("url"))) 
    	{ 
    		LOGGER.error("数据源配置错误,检查以下系统配置文件是否正确:" + Arrays.toString(environment.getActiveProfiles())); 
        
    		throw new ApplicationContextException("数据源配置错误"); 
    	} 
    	
    	DruidDataSource dataSource = new DruidDataSource(); 
    	dataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name")); 
    	dataSource.setUrl(propertyResolver.getProperty("url")); 
    	dataSource.setUsername(propertyResolver.getProperty("username")); 
    	dataSource.setPassword(propertyResolver.getProperty("password")); 
    	
    	dataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize"))); 
    	dataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("minIdle"))); 
    	dataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("maxActive"))); 
    	
    	dataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("maxWait"))); 
    	dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty("timeBetweenEvictionRunsMillis"))); 
    	dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getProperty("minEvictableIdleTimeMillis"))); 
    	
    	dataSource.setValidationQuery(propertyResolver.getProperty("validationQuery")); 
    	dataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("testWhileIdle"))); 
    	dataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("testOnBorrow"))); 
    	dataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("testOnReturn"))); 
    	
    	dataSource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("poolPreparedStatements"))); 
    	dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize"))); 
    	dataSource.setFilters(propertyResolver.getProperty("filters")); 
    	dataSource.setConnectionProperties(propertyResolver.getProperty("connectionProperties"));
      
    	return dataSource; 
    } 
    
}
