/**
 * Copyright (c) www.bugull.com
 */
package com.freePorter.nio.entity;

import com.freePorter.nio.constant.SysConstant;

import java.io.Serializable;

/**
 * @author zoush(zoush@hadlinks.com)
 *         <p>
 * CreateTime 2017-03-10 11:37
 */

/**
 *
 * @author martin
 *
 * 时间  2016年5月2日 下午4:28:17
 *
 * +—----—+—-----—+——----——+——----——+——-----——+——----——+——-----——+——-----———+——-----—————---
 * | 包头     | 版本号    | 会话类型  | 消息类型   | FromId  | ToId   |  数据长度   | 当前毫秒数    |
 * +—----—+—-----—+——----——+——----——+——-----——+——----——+——-----——+——-----———+——-----—————---
 * 说明   消息表头:4+2+1+1+4+4+4+8 = 28bit
 */
public class MessageHeader  implements Serializable{

    /** 包头  */
    private int pkgHeader ;

    /** 版本号  */
    private short version ;

    /** 会话类型  */
    public byte chatType ;

    /** 消息类型 */
    public byte msgType ;

    /** 发送方Id  */
    public int fromId ;

    /** 接收方Id  */
    public int toId;

    /** 数据长度  */
    private int length ;

    /** 当前时间  */
    private long currTime ;

    public MessageHeader(){
        this.pkgHeader = SysConstant.PKG_HEADER ;
        this.currTime = System.currentTimeMillis();
    }

    public MessageHeader(int pkgHeader,short version,byte chatType,byte msgType,int fromId,int toId,int length,long currTime){
        this.pkgHeader = SysConstant.PKG_HEADER;
        this.version = version ;
        this.chatType = chatType;
        this.msgType = msgType ;
        this.fromId = fromId;
        this.toId = toId ;
        this.length = length ;
        this.currTime = currTime ;
    }

    public MessageHeader(short version,byte chatType,byte msgType,int fromId,int toId,int length){
        this.pkgHeader = SysConstant.PKG_HEADER;
        this.version = version ;
        this.chatType = chatType;
        this.msgType = msgType ;
        this.fromId = fromId;
        this.toId = toId ;
        this.length = length ;
        this.currTime = System.currentTimeMillis() ;
    }

    public int getPkgHeader() {
        return pkgHeader;
    }

    public void setPkgHeader(int pkgHeader) {
        this.pkgHeader = pkgHeader;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public byte getChatType() {
        return chatType;
    }

    public void setChatType(byte chatType) {
        this.chatType = chatType;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(long currTime) {
        this.currTime = currTime;
    }

}

