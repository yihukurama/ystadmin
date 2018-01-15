package com.gdyunst.ystadmin.framework.service.domainservice.prepare.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    public Logger LOGGER = LoggerFactory.getLogger(LogUtil.class);
    
    
    public static void DebugLog(Object o,String msg){
        Logger logger = LoggerFactory.getLogger(o.getClass());
        logger.debug(msg);
    }
    
    public static void InfoLog(Object o,String msg){
        Logger logger = LoggerFactory.getLogger(o.getClass());
        logger.info(msg);
    }
    
    public static void ErrorLog(Object o,String msg){
        Logger logger = LoggerFactory.getLogger(o.getClass());
        logger.error(msg);
    }
    
    public static void DebugLog(Class<?> o,String msg){
        Logger logger = LoggerFactory.getLogger(o);
        logger.debug(msg);
    }
    
    public static void InfoLog(Class<?> o,String msg){
        Logger logger = LoggerFactory.getLogger(o);
        logger.info(msg);
    }
    
    public static void ErrorLog(Class<?> o,String msg){
        Logger logger = LoggerFactory.getLogger(o);
        logger.error(msg);
    }
}
