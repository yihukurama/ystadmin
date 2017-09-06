package com.gdyunst.ystadmin.application.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;

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
    
    public static void writeLog(HttpServletRequest request,Object[] object,String path){
        new Thread(){
            @Override
            public void run() {
                //获取IP地址
                String ip=StringUtil.getIpAddr(request);
                //获取请求链接
                String url = request.getRequestURL().toString();//request.getRequestURI();
                //转换参数到json字符串
                String jsonStr="null";
                try {
                    if(object!=null&&object.length>0){
                        for (Object object2 : object) {
                            if (object2 instanceof Request) {
                                Request request = (Request) object2;
                                jsonStr=JSON.toJSONString(object2);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                //日期字符串
                String date=DateUtil.toString(new Date(),"yyyy-MM-dd HH:mm:ss.SSS");
                File file=new File(path+"/jqzy_access.txt");
                File pFile=file.getParentFile();
                if(!pFile.exists()){
                    pFile.mkdirs();
                }
                if(!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                String conent="\r\n"+ip+"\t"+date+"\t"+url+"\t"+jsonStr;
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
                    out.write(conent);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(out != null){
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }
}
