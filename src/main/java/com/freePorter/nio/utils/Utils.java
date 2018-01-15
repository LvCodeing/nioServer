package com.freePorter.nio.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author martin
 *
 * 时间  2016年4月28日 下午10:37:32
 *
 * 说明
 */
public class Utils {
	
	public static void formatPrint(String msg) {
		StringBuilder info = new StringBuilder();

		info.append("[");
		info.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		info.append("]------->");
		info.append(msg);

		System.out.println(info.toString());
	}
	
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim()))
			return true ;
		
		return false ;
	}
	
	public static String getFormatTime(Long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(new Date(time));
	}
	
	public static String objToStr(Object obj) {

		try {
			String msg = JSON.toJSONString(obj);
			return new String(msg.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	
	public static byte[] toByte(String msg){
		
		try {
			return new String(msg.getBytes(),"UTF-8").getBytes();
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	
}
