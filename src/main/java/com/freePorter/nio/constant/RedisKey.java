package com.freePorter.nio.constant;

/**
 * 
 * @author martin
 *
 * 时间  2016年5月18日 上午9:46:45
 *
 * 说明  redis缓存系统key
 */
public interface RedisKey {
	
	/** 在线消息KEY */
	String MESSAGE_ONLINE_LIST = "eShare.message.online.list";
	
	/** 离线消息KEY */
	String MESSAGE_OFFLINE_LIST = "eShare.message.offline.list";
	
	/**  通知消息  */
	String NOTIFY_MESSAGE = "eShare.notify.message.list";
	
	/**  所有用户 */
	String ALL_USERS = "eShare.all.user.set";
	
	/** 
	 * 在线用户
	 * 用户断开连接时，删除
	 */
	String ONLINE_USERS = "eShare.online.user.set";
	
	/**  
	 * 用户token(android手机，token为null)
	 * 用户断开时，删除该tokent  
	 */
	String USER_TOKEN = "eShare.user.token.hash";
	
	/** 
	 * 用户userAgent
	 * 用户登录系统时，上传userAgent,
	 * 格式：ios/android;mobile-x.x.x
	 */
	String USER_AGENT = "eShare.userAgent.hash";
	
	/**  消息内容  */
	String MESSAGE_DETAIL = "eShare.message.hash";
	
	
	
	
	
	

}
