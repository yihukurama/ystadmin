package com.gdyunst.ystadmin.application.component;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.application.cache.AppCache;
import com.gdyunst.ystadmin.application.msg.BaseMsg;
import com.gdyunst.ystadmin.application.msg.BaseWebSocket;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map.Entry;


@ServerEndpoint("/websocket")
@Component
public class YstWebSocket extends BaseWebSocket {
	private final static Logger logger = LoggerFactory.getLogger(YstWebSocket.class);
	private String key;

	@OnOpen
	@Override
	public void onOpen(Session session) {
		super.onOpen(session);
		this.key = session.getQueryString();

		logger.debug("onOpen key:{}",key);

		if(key == null) {
			logger.info("该连接无id");
			return;
		}
		AppCache.socketMap.put(key, this);
		
	}

	@OnClose
	@Override
	public void onClose() {
		logger.debug("onClose key:{}",key);
		AppCache.socketMap.remove(key);
		UserEntity user = new User();
		user.setId(key);
		if(AppCache.loginTree.contains(user)){
			logger.info("清楚登录缓存:{}",key);
			AppCache.loginTree.remove(user);
		}
		super.onClose();
	}

	@OnMessage
	@Override
	public void onMessage(String message, Session session) throws IOException {
		
			
			broadcast(message);
		
	}

	// 向所有连接上来的socket广播消息
	public static void broadcast(String message) {
		for (Entry<String, BaseWebSocket> entry : AppCache.socketMap.entrySet()) {
			try {
				logger.info("广播消息");
				entry.getValue().sendMessage(message);

			} catch (IOException e) {
				logger.error("广播消息失败");
				e.printStackTrace();
			}
		}
	}

	// 向指定的socket发送消息
	public static void sendMsgByUserId(String message, String key) {
		if(!AppCache.socketMap.containsKey(key)){
			logger.info("无此用户连接" + key);
			return;
		}
		try {
			logger.info("向指定的用户发送webSocket消息" + key);
			AppCache.socketMap.get(key).sendMessage(message);
		} catch (Exception e) {
			logger.error("向指定的用户发送webSocket消息失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能描述:通知用户跟进
	 * @param id 用户id
	 * @param data 事故单Accorder,案件Cases,鉴定单Apporder
	 * @param cmd 各种socket命令
	 * @Author:dengshuai
	 * @Date:2017年2月9日 上午11:23:03
	 */
	public static void notifyUser(String id,Object data,String cmd){
		if(id == null || id.equals("") || data == null){
			logger.info("用户id为空或data数据为空,socket消息发送失败");
			return;
		}
		logger.info("Socket通知用户{}",id);
		BaseMsg baseMsg = new BaseMsg(cmd,"通知用户跟进处理",data);
		sendMsgByUserId(JSON.toJSONString(baseMsg),id);
	}

	/**
	 * 功能描述:广播通知
	 * @param id  数据id
	 * @param data  数据
	 * @param cmd  各种socket命令
	 * @Author:dengshuai
	 * @Date:2017年2月14日 下午3:58:57
	 */
	public static void broadcast(String id,Object data,String cmd){
		if(id == null || id.equals("") || id.length()!=32 || data == null){
			logger.info("用户id为空或data数据为空,socket消息发送失败");
			return;
		}
		
		BaseMsg baseMsg = new BaseMsg(cmd,"通知用户跟进处理",data);
		YstWebSocket.broadcast(JSON.toJSONString(baseMsg));
	}
	

}