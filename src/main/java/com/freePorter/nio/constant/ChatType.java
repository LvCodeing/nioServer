package com.freePorter.nio.constant;

public interface ChatType {
	
	/** 心跳 */
	byte PING = 0;
	
	/** 登录 */
	byte LOGIN = 1;
	
	/** 登出 */
	byte LOGIN_OUT = 2;
	
	/** 单聊 */
	byte CHAT = 3;
	
	/** 群聊 */
	byte GROUP_CHAT = 4;
	
	/** 广播 */
	byte BROADCAST = 5;
	
}
