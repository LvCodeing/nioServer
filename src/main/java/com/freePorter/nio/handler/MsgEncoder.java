package com.freePorter.nio.handler;

import com.freePorter.nio.entity.Message;
import com.freePorter.nio.entity.MessageHeader;
import com.freePorter.nio.utils.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 
 * @author martin
 *
 * 时间  2016年5月2日 下午4:24:46
 * 
 * 消息头
 * +—----—+—-----—+——----——+——----——+——-----——+——----——+——-----——+——-----———+
 * | 包头     | 版本号    | 会话类型  | 消息类型   | FromId  | ToId   |  数据长度   | 当前毫秒数   |
 * +—----—+—-----—+——----——+——----——+——-----——+——----——+——-----——+——-----———+
 * 说明  自定义编码器
 */
public class MsgEncoder extends MessageToByteEncoder<Message> {

	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		
		
		if(msg == null)
			return ;
		
		MessageHeader header = msg.getHeader();
		
		if(header == null)
			return ;
		
		
		out.writeBytes(ByteUtils.intToBytes(header.getPkgHeader()));
		out.writeBytes(ByteUtils.shortToBytes(header.getVersion()));
		out.writeBytes(ByteUtils.byteToBytes(header.getChatType()));
		out.writeBytes(ByteUtils.byteToBytes(header.getMsgType()));
		
		out.writeBytes(ByteUtils.intToBytes(header.getFromId()));
		out.writeBytes(ByteUtils.intToBytes(header.getToId()));
		out.writeBytes(ByteUtils.intToBytes(header.getLength()));
		out.writeBytes(ByteUtils.longToBytes(header.getCurrTime()));
		
		out.writeBytes(msg.getContent());
	}

	

}
