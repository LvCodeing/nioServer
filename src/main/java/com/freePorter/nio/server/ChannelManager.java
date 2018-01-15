package com.freePorter.nio.server;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author martin
 *
 * 时间  2016年4月28日 下午10:37:22
 *
 * 说明
 */
public class ChannelManager {

	private static final Logger logger = LogManager.getLogger(ChannelManager.class);


	/** values(userId,channel) */
	private static volatile Map<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();

	public static Map<String, Channel> getChannelMap(){
		return channelMap ;
	}

	
	public static void addChannel(String accountNo,Channel channel) {
		
		Channel oldChannel = channelMap.get(accountNo);
		
		if(oldChannel != null){
			channelMap.remove(accountNo);
		}
		
		channelMap.put(accountNo, channel);
	}
	
	public static String getAccountNo(Channel channel){
		
		Set<Entry<String, Channel>> channelSet = channelMap.entrySet();

		Iterator<Entry<String, Channel>> it = channelSet.iterator();
		
		String accountNo = null ;
		
		while (it.hasNext()) {
			Entry<String, Channel> entry = it.next();

			if (entry.getValue() == channel) {
				accountNo = entry.getKey();
				
				break;
			}
		}
		return accountNo;
	}

	public static void removeChannel(Channel channel) {
		
		Set<Entry<String, Channel>> channelSet = ChannelManager.getChannelMap().entrySet();
		
		logger.debug(JSON.toJSONString(channelSet));
		
		
		Iterator<Entry<String,Channel>> it = channelSet.iterator();
		
		while(it.hasNext()){
			Entry<String,Channel> entry = it.next();
			
			if(entry.getValue() == channel){
				channelMap.remove(entry.getKey());
				break;
			}
		}
		
		channel.disconnect();
	}

}
