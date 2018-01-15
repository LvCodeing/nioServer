package com.freePorter.nio.process;

import com.freePorter.nio.constant.*;
import com.freePorter.nio.entity.Message;
import com.freePorter.nio.entity.MessageHeader;
import com.freePorter.nio.server.ChannelManager;
import com.freePorter.nio.utils.JedisUtils;
import com.freePorter.nio.utils.PropertyUtils;
import io.netty.channel.Channel;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author martin
 *
 * 时间 2016年5月15日 下午4:00:54
 *
 * 说明 监控消息队列
 */
public class MessageMonitor implements Runnable {

	private static final Logger logger = LogManager.getLogger(ChannelManager.class);


	public void run() {
		
		    Map<String,Channel> channelMap = ChannelManager.getChannelMap();

		    if (channelMap.size() == 0) {
		      return;
		    }

		    Jedis jedis = JedisUtils.getResource();

		    List<String> list = null ;
		    
		    try{
		    	list = jedis.brpop(2, RedisKey.NOTIFY_MESSAGE);
		    	
		    	if(list == null)
		    		return ;

		    }catch(Exception e){
				logger.error(e.getMessage());
		    }finally{
				if (jedis != null) {
					jedis.close();
				}
		    }
		    
		    String msgId = list.get(1);

		    logger.debug(Thread.currentThread().getName());
			
			if(msgId.equals(RedisKey.NOTIFY_MESSAGE))
				return ;
			
			Jedis jedis2 = JedisUtils.getResource();
			
			String msgContent = jedis2.hget(RedisKey.MESSAGE_DETAIL, msgId);
			
			if (jedis2 != null) {
				jedis2.close();
			}
			
			
			byte[] content = null;
		
			try {
				content = new String(msgContent.getBytes(), SysConstant.DEFAULT_ENCODE).getBytes();
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
		
			// 消息体长度
			final int length = content.length;
		
			MessageHeader msgHeader = new MessageHeader(PropertyUtils.VERSION,
					                                    ChatType.BROADCAST,
					                                    MsgType.TEXT,
					                                    SysConstant.ADMIN_WAITER, 
					                                    SysConstant.ALL_USERS, 
					                                    length);
		
			Message msg = new Message();
			msg.setHeader(msgHeader);
			msg.setContent(content);
			
			// 循环向在线用户发送消息
			for (String key : channelMap.keySet()) {
				
				SysThreadPool.getInstance().getExecutor().execute(new Runnable(){
					
					public void run() {
						
						Channel ch = channelMap.get(key);
						
						//删除未激活的通道
						if(!ch.isActive()){
							ChannelManager.removeChannel(ch);
						}else{
							ch.writeAndFlush(msg);
						}
					}
				});
				
				
			}
			
			ReferenceCountUtil.release(msg);
	}

}
