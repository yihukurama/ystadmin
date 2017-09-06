package com.gdyunst.ystadmin.application.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

/**
 * 功能描述:MyBatis初始化配置;
 * @Date:2016年9月19日 下午1:59:11
 * @author liujun 
 * Created by Eclipse
 */
@Configuration
public class MyBatisConfig implements TransactionManagementConfigurer
{
	@Autowired
	private DruidDataSource dataSource; 
	private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisConfig.class);

	/**
	 * 功能描述:配置MyBatis的SqlSessionFactory;
	 * @return SqlSessionFactory对象
	 * @Date:2016年9月19日 下午2:04:59
	 * @Author liujun
	 */
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean()
	{
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		
		// 数据源信息;
		bean.setDataSource(dataSource);

		// 分页插件;
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "none");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);

		// 分页拦截器;
		bean.setPlugins(new Interceptor[]{pageHelper});

		try
		{
			// 自动加载XML格式的SQL映射文件;
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[] baseRes = resolver.getResources("classpath*:mapper/basemapper/**/*.xml");
			Resource[] res = baseRes;
//			Resource[] clientRes = resolver.getResources("classpath:adminmapper/*.xml");
//			Resource[] serviceRes = resolver.getResources("classpath:servicemapper/*.xml");
//			Resource[] res1 = ArrayUtils.addAll(baseRes, clientRes);
//			Resource[] res = ArrayUtils.addAll(res1, serviceRes);
			bean.setMapperLocations(res);
			bean.getObject().getConfiguration().setLogImpl(Log4jImpl.class);
				
			return bean.getObject();
		}
		catch(IOException e)
		{
			LOGGER.error("加载MyBatis SQL语句映射文件失败", e);
			
			return null;
		}
		catch(Exception e)
		{
			LOGGER.error("创建MyBatis SqlSessionFactory对象失败", e);

			return null;
		}
	}

	/**
	 * 功能描述:配置MyBatis SqlSessionTemplate;
	 * @param sqlSessionFactory 
	 * @return
	 * @Author:liujun
	 * @Date:2017年3月3日 下午8:38:43
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)
	{
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	/**
	 * 配置事务管理器;
	 */
	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager()
	{
		return new DataSourceTransactionManager(dataSource);
	}
	
}
