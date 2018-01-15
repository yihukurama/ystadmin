package com.gdyunst.ystadmin.application.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.framework.service.domain.admin.Systemlog;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;

/**
 * 
 * 
 * @author Jieyq
 * @date 2017年6月27日 下午2:27:01
 */
public class LogUtil {

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
				String ip=getIpAddr(request);
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
				File file=new File(path+"/cftj_access.txt");
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
				new Systemlog().addSystemLog(ip, url,jsonStr);
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
    
    
    public static void systemLog(HttpServletRequest request,Object[] object,String path){
        new Thread(){
            @Override
            public void run() {
                //获取IP地址
                String ip=getIpAddr(request);
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
                new Systemlog().addSystemLog(ip, url,jsonStr);
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
    
    /**
     * 
     * 功能描述:获取IP地址
     * @param request
     * @return
     * @Author:Jieyq
     * @Date:2016年10月14日 下午3:21:16
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        // ipAddress = this.getRequest().getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                                                            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        //地址过长截取前100位
        if(ipAddress!=null&&ipAddress.length()>100){
            ipAddress=ipAddress.substring(0, 100);
        }
        return ipAddress;
    }
}
