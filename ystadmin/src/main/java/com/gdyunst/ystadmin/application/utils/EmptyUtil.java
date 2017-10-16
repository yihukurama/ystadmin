package com.gdyunst.ystadmin.application.utils;

import java.util.Collection;
/**
 * 功能描述:判空工具类，数组判空，字符串判空，集合判空 
 * @Author:denghsuai
 * @Date:2017年10月16日 上午10:30:36
 */
public class EmptyUtil
{
    
    /**
     * 功能描述:字符串判空
     * @param str
     * @return
     * @Author:dengshuai
     * @Date:2017年10月16日 上午10:31:57
     */
    public static boolean isEmpty(String str)
    {
        if(str == null || str.trim().equals(""))
        {
            return true;
        }
        return false;
    }

	/**
	 * 功能描述:校验数组是否为空;
	 * @param array 数组对象
	 * @return true,数组为空;false,数组不为空
	 * @Author:liujun
	 * @Date:2016年12月18日 下午1:43:16
	 */
	public static boolean isEmpty(Object[] array)
	{
		if(array == null || array.length < 1)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 功能描述:校验集合对象是否为空;
	 * @param collection Collection对象
	 * @return true,Collection对象为空;false,Collection对象不为空
	 * @Author:liujun
	 * @Date:2016年12月18日 下午1:45:40
	 */
	public static <T> boolean isEmpty(Collection<T> collection)
	{
		if(collection == null || collection.size() < 1)
		{
			return true;
		}
		return false;
	}
	
}
