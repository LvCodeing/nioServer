package com.freePorter.nio.handler;

import java.util.List;

import com.freePorter.nio.constant.SysConstant;
import com.freePorter.nio.entity.Message;
import com.freePorter.nio.entity.MessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 
 * @author martin
 *
 * 时间 2016年5月2日 下午4:25:18 
 * 
 * 消息头
 * +—----—+—-----—+——----——+——----——+——-----——+——----——+——-----——+——-----———+
 * | 包头     | 版本号    | 会话类型  | 消息类型   | FromId  | ToId   |  数据长度   | 当前毫秒数   |
 * +—----—+—-----—+——----——+——----——+——-----——+——----——+——-----——+——-----———+
 * 说明 自定义解码器
 */
public class MsgDecoder extends ByteToMessageDecoder {

	public static final int HEADER_LEN = 28;

	protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
		
		//可读长度必须大于包头长度
		if(buf.readableBytes() >= HEADER_LEN){
			
			//记录包头开始的index
			int beginReader = buf.readerIndex();
			
			while(true){
				if(buf.readInt() == SysConstant.PKG_HEADER){
					break;
				}
				
				//未读到包头，略过一个字节
				buf.resetReaderIndex();
				buf.readByte();
				
				//长度小于包头长度，丢弃
				if(buf.readableBytes() < HEADER_LEN){
					return ;
				}
			}
			
			int pkgHeader = SysConstant.PKG_HEADER;
			short version = buf.readShort();
			byte chatType = buf.readByte();
			byte msgType = buf.readByte();
			int fromId = buf.readInt();
			
			int toId = buf.readInt();
			int  length = buf.readInt();
			long currTime = buf.readLong();
			
			//判断请求数据包是否到齐
			if(buf.readableBytes() < length){
				
				//重新设置读取位置
				buf.readerIndex(beginReader);
				return ;
			}
			
			byte[] content = null;
			
			if(length > 0){
				content = new byte[length];
				buf.readBytes(content);
			}
			
			//重新设置读取位置
			buf.readerIndex(HEADER_LEN+length);
			
			MessageHeader header = new MessageHeader(pkgHeader, version, chatType, msgType, fromId, toId, length,currTime);

			Message msg = new Message(header, content);
			out.add(msg);
		}
		
	}

}
