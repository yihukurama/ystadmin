package com.gdyunst.ystadmin.application.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;

public class BaseWebSocket {
	private final static Logger logger = LoggerFactory.getLogger(BaseWebSocket.class);
	protected static int onlineCount = 0;
	protected Session session;

	public void onOpen(Session session) {
		this.session = session;
		logger.info("onopen  id--------------------->" + session.getId());
		logger.info("pv--------------------->" + session.getProtocolVersion());
		logger.info("mbmb--------------------->" + session.getMaxBinaryMessageBufferSize());
		logger.info("mit--------------------->" + session.getMaxIdleTimeout());
		logger.info("mtmbs--------------------->" + session.getMaxTextMessageBufferSize());
		logger.info("qs--------------------->" + session.getQueryString());
		logger.info("ru--------------------->" + session.getRequestURI().toString());
		addOnlineCount();
		
		
		logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
	}


	public void onClose() {
		logger.info("close  id--------------------->" + session.getId());
		subOnlineCount();
		logger.info("有连接断开！当前在线人数为" + getOnlineCount());
	}


	public void onMessage(String message, Session session) throws IOException {
		logger.info("收到消息"+message);
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return BaseWebSocket.onlineCount;
	}

	public static synchronized void addOnlineCount() {
		BaseWebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		BaseWebSocket.onlineCount--;
	}

}