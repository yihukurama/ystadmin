package com.gdyunst.ystadmin.application.utils;

import java.util.Collection;

public class BaseParamValidator
{

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
