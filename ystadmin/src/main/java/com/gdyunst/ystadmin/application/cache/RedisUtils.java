package com.gdyunst.ystadmin.application.cache;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * 功能描述:redicache 工具类 
 * @Author:denghsuai
 * @Date:2016年9月29日 下午6:09:53
 */
@SuppressWarnings("unchecked")
@Component
@ComponentScan
public class RedisUtils {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 功能描述:批量删除对应的value
	 * @param keys
	 * @Author:dengshuai
	 * @Date:2016年9月29日 下午6:09:36
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 功能描述:批量删除key
	 * @param pattern
	 * @Author:dengshuai
	 * @Date:2016年9月29日 下午6:09:16
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 功能描述:删除对应的value
	 * @param key
	 * @Author:dengshuai
	 * @Date:2016年9月29日 下午6:08:59
	 */
	public void remove(final String key){
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 功能描述:判断缓存中是否有对应的value
	 * @param key
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年9月29日 下午6:08:36
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}


	/**
	 * 功能描述:获取key所对应的值
	 * @param key
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年9月29日 下午5:40:28
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 
	 * 功能描述:写入缓存
	 * @param key 键
	 * @param value 值
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年9月29日 下午5:39:04
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 指定key增量
	 * @param key
	 * @param num
	 * @return
	 */
	public boolean incr(final String key,double num){
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.increment(key,num);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * 功能描述:写入缓存
	 * @param key 键
	 * @param value 值
	 * @param expireTime 过期时间单位/秒
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年9月29日 下午5:39:04
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 功能描述:以前缀删除
	 * @param prex
	 * @return
	 * @Author:dengshuai
	 * @Date:2017年2月28日 下午7:24:33
	 */
	public boolean removeByPrex(String prex){
		boolean result = false;
		try{
			Set<String> keys = redisTemplate.keys(prex+"*");
			redisTemplate.delete(keys);
			result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 功能描述:以后缀删除
	 * @param prex
	 * @return
	 * @Author:dengshuai
	 * @Date:2017年2月28日 下午7:24:33
	 */
	public boolean removeBySuffix(String suffix){
		boolean result = false;
		try{
			Set<String> keys = redisTemplate.keys("*"+suffix);
			redisTemplate.delete(keys);
			result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
