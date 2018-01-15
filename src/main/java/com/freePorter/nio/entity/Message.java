package com.freePorter.nio.entity;

import java.io.Serializable;

/**
 * @author zoushaohua
 * @version 创建时间：2016年1月13日 下午3:23:35 类说明
 */
public class Message implements Serializable{

	/** 消息表头  */
	public MessageHeader header ;
	
	/** 消息内容 
	 *Map(randomStr,userAgent,deviceToken,content)
	 */
	public byte[] content ;
	
	public Message(){
	}
	
	public Message(MessageHeader header, byte[] content){
		this.header = header;
		this.content = content ;
	}

	public MessageHeader getHeader() {
		return header;
	}

	public void setHeader(MessageHeader header) {
		this.header = header;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
}
