package com.gdyunst.ystadmin.application.component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:保存上下文context
 * 
 * @Author:denghsuai
 * @Date:2017年3月21日 下午5:09:52
 */
@Configuration
public class SpringBeanTools implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext context) {
    applicationContext = context;
  }

  public static Object getBean(Class classname) {
    try {
      Object _restTemplate = applicationContext.getBean(classname);
      return _restTemplate;
    } catch (Exception e) {
      return "";
    }
  }

  public static void setApplicationContext1(ApplicationContext context) {
    applicationContext = context;
  }
}