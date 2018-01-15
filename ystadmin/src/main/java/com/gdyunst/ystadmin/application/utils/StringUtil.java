package com.gdyunst.ystadmin.application.utils;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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

	//base64编码字符集
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };

	
	/**
	 * 功能描述:将给定字符串转换为全大写字母形式(仅限英文字符);
	 * @param str 字符串对象
	 * @return 转换后的字符串;如果给定字符串为空,则返回原字符串
	 * 创建时间:2016年9月7日
	 * @Author liujun
	 */
	public static String toUpperCase(String str) 
	{
		if (!EmptyUtil.isEmpty(str)) 
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
		if (!EmptyUtil.isEmpty(str)) 
		{
			return str.trim().toLowerCase();
		}
		return str;
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
	 * 功能描述:获取单号  由日期时分加5位随机数
	 * @return
	 * @Author:Jieyq
	 * @Date:2016年12月1日 下午9:13:55
	 */
	public static String getNum(){
		return DateUtil.toString(new Date(), "yyyyMMddHHmm")+NumberUtil.getRandNum(5);
	}
	
	public static String replaceBlanks(String str) {  
        String dest = "";  
        if (str!=null) {  
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
            Matcher m = p.matcher(str);  
            dest = m.replaceAll("");  
        }  
        return dest.trim();  
    }  
}
