package com.gdyunst.ystadmin.application.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述:正则表达式工具类;
 * 创建日期:2016年9月9日
 * @author dengshuai
 * Created by Eclipse
 */
public abstract class RegexUtils {

	/**
	 * 功能描述:判断字符串是否是url
	 * @param url  传入字符串
	 * @return
	 */
	public static boolean isUrl(String url){
		
		if(url == null) return false;
		return url.matches("http://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
	}
	/**
	 * 功能描述:判断字符串是否数字,字母和中文的合法字符
	 * @param ch  传入字符串
	 * @return
	 */
	public static boolean isLegelChar(String ch){
		if(ch == null) return false;
		return Pattern.compile("[A-Z,a-z,0-9,\u4E00-\u9FA5]").matcher(ch).find();
	}
	/**
	 * 功能描述:匹配出给定文本中的车牌号码;
	 * @param context 待匹配文本
	 * @return 车牌号码的集合
	 * 创建时间:2016年9月6日
	 * @Author liujun
	 */
	public static List<String> matchCarPlate(String context)
	{
		// 车牌格式:汉字 + [A-Z] + [A-Z_0-9];
		// 京、津、冀、晋、蒙、辽、吉、黑、沪、苏、浙、皖、闽、赣、湘、粤
        // 桂、琼、渝、川、贵、云、藏、陕、甘、青、宁、新、鲁、豫、鄂;
	    String carnumRegex = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}";  // 车牌号码正则;
	    List<String> CarPlates = new ArrayList<>();
        Pattern pattern = Pattern.compile(carnumRegex);
        Matcher matcher = pattern.matcher(context);
        
        while(matcher.find()) 
        {
            CarPlates.add(matcher.group());            
        } 
        
        //去掉汉子的匹配
        if(CarPlates==null||CarPlates.size()<=0){
        	String carnumRegex1 = "[A-Z]{1}[A-Z_0-9]{5}";  // 车牌号码正则;
        	Pattern pattern1 = Pattern.compile(carnumRegex1);
        	Matcher matcher1 = pattern1.matcher(context);
        	while(matcher1.find()) 
        	{
        		CarPlates.add("粤"+matcher1.group());            
        	} 
        }
	     
	    return CarPlates;
	}
	
	/**
	 * 功能描述:判断给定字符串是否是身份证号码;
	 * @param idNum 身份证号码字符串
	 * @return 匹配成功返回true,匹配失败返回false
	 * @Author:liujun
	 * @Date:2016年11月15日 下午4:53:01
	 */
	public static boolean isIdNum(String idNum)
	{
		// 身份证号码正则,可匹配15或18位身份证号码;
		final Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
        Matcher idNumMatcher = idNumPattern.matcher(idNum);  
        if(idNumMatcher.matches())
        {  
        	return true;
        }
        
        return false;
	}
	
}
