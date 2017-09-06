package com.gdyunst.ystadmin.application.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;



/**
 * 功能描述:字符串处理工具类;
 * 创建日期:2016年9月6日
 * @author liujun
 * Created by Eclipse
 */
public abstract class StringUtil
{
    
    /**
     * 判断中文个数
     * @param str
     * @return
     */
    public static int chineseCount(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 判断是否是xml文件
     * @param value
     * @return
     */
    public static boolean isXML(String value) {
        try {
            DocumentHelper.parseText(value);
        } catch (DocumentException e) {
            return false;
        }
        return true;
    }
    
	//base64编码字符集
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };
	
	
	/**
	 * 功能描述:判断字符串是否为空,判定标准是:给定字符串为null或者为空串"";
	 * @param str 待检测的字符串
	 * @return true,字符串为空;false,字符串不为空
	 * 创建时间:2016年9月6日
	 * @Author liujun
	 */
	public static boolean isEmpty(String str)
	{
		if(str == null || "".equals(str.trim()))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 功能描述:将给定字符串转换为全大写字母形式(仅限英文字符);
	 * @param str 字符串对象
	 * @return 转换后的字符串;如果给定字符串为空,则返回原字符串
	 * 创建时间:2016年9月7日
	 * @Author liujun
	 */
	public static String toUpperCase(String str) 
	{
		if (!isEmpty(str)) 
		{
			return str.trim().toUpperCase();
		}
		return str;
	}
	
	/**
	 * 功能描述:将给定字符串转换为全小写字母形式(仅限英文字符);
	 * @param str 字符串对象
	 * @return 转换后的字符串;如果给定字符串为空,则返回原字符串
	 * 创建时间:2016年9月7日
	 * @Author liujun
	 */
	public static String toLowerCase(String str)
	{
		if (!isEmpty(str)) 
		{
			return str.trim().toLowerCase();
		}
		return str;
	}
	
	/**
	 * 功能描述:将数据元素为字符串的List集合转换成为一个普通的字符串,各个字符串之间以','(英文)分隔;
	 * @param strs List集合
	 * @return 字符串
	 * 创建时间:2016年9月6日
	 * @Author liujun
	 */
	public static String convertToString(List<String> items)
	{
		StringBuffer buffer = new StringBuffer();
		for(String item : items)
		{
			buffer.append(item + ",");
		}
		
		return buffer.deleteCharAt(buffer.length() - 1).toString();
	}
	
	/**
	 * 
	 * 功能描述:获取12位uuid
	 * @return
	 * @Author:Jieyq
	 * @Date:2016年10月10日 上午11:00:27
	 */
	public static String generateUuid() {
	    StringBuffer shortBuffer = new StringBuffer();
	    String uuid = UUID.randomUUID().toString().replace("-", "");
	    for (int i = 0; i < 4; i++) {
	    	String str = uuid.substring(i * 8, i * 8 + 8);
	    	long x = Long.parseLong(str, 16);
//	    	System.out.println(x);
	    	shortBuffer.append(chars[(int) (x % 0x3E)]);
	    }
	    for(int i = 0; i < 8; i++) {
	        String str = uuid.substring(i * 4, i * 4 + 4);
	        int x = Integer.parseInt(str, 16);
//	        System.out.println(x);
	        shortBuffer.append(chars[x % 0x3E]);
	    }
//	    System.out.println(shortBuffer);
	    return shortBuffer.toString();
	  
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
	
	/**
	 * 
	 * 功能描述:获取单号  由日期时分加5位随机数
	 * @return
	 * @Author:Jieyq
	 * @Date:2016年12月1日 下午9:13:55
	 */
	public static String getNum(){
		return DateUtil.toString(new Date(), "yyyyMMddHHmm")+NumberUtil.getRandNum(5);
	}
	
}
