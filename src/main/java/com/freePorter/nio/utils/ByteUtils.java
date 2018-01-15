package com.freePorter.nio.utils;

import java.io.UnsupportedEncodingException;

public class ByteUtils {
	
	private static final String DEFAULT_ENCODE = "UTF-8";
	
	
	public static byte[] byteToBytes(byte number) { 
    	byte temp = number; 
        byte[] b = new byte[1]; 
        for (int i = 0; i < b.length; i++) { 
            b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位 
        } 
        return b; 
    } 
	
	public static byte[] shortToBytes(short number) { 
        int temp = number; 
        byte[] b = new byte[2]; 
        for (int i = 0; i < b.length; i++) { 
            b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位 
            temp = temp >> 8; // 向右移8位 
        } 
        return b; 
    } 
	
	public static short bytesToShort(byte[] b) { 
        short s = 0; 
        short s0 = (short) (b[0] & 0xff);// 最低位 
        short s1 = (short) (b[1] & 0xff); 
        s1 <<= 8; 
        s = (short) (s0 | s1); 
        return s; 
    }
	
	/**
	 * 将一个整数转换位字节数组(4个字节)，b[0]存储高位字符，大端
	 * 
	 * @param i
	 *            整数
	 * @return 代表整数的字节数组
	 */
	public static byte[] intToBytes(int i) {
		byte[] b = new byte[4];
		b[0] = (byte) (i >>> 24);
		b[1] = (byte) (i >>> 16);
		b[2] = (byte) (i >>> 8);
		b[3] = (byte) i;
		return b;
	}
	
	/**
	 * 将一个4位字节数组转换为4整数。<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b		字节数组
	 * @param offset	偏移量
	 * @return 整数
	 */
	public static int bytesToInt(byte[] b, int offset) {
		int i = (b[0 + offset] << 24) & 0xFF000000;
		i |= (b[1 + offset] << 16) & 0xFF0000;
		i |= (b[2 + offset] << 8) & 0xFF00;
		i |= b[3 + offset] & 0xFF;
		return i;
	}
	
	/**
	 * 将一个8位字节数组转换为长整数。<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b		字节数组
	 * @param offset	偏移量
	 * @return 长整数
	 */
	public static long bytesToLong(byte[] b, int offset) {
		// 如果不强制转换为long，那么默认会当作int，导致最高32位丢失
		long l = ((long) b[0 + offset] << 56) & 0xFF00000000000000L;
		l |= ((long) b[1 + offset] << 48) & 0xFF000000000000L;
		l |= ((long) b[2 + offset] << 40) & 0xFF0000000000L;
		l |= ((long) b[3 + offset] << 32) & 0xFF00000000L;
		l |= ((long) b[4 + offset] << 24) & 0xFF000000L;
		l |= ((long) b[5 + offset] << 16) & 0xFF0000L;
		l |= ((long) b[6 + offset] << 8) & 0xFF00L;
		l |= (long) b[7 + offset] & 0xFFL;
		return l;
	}
	
	
	/**
	 * 将一个长整数转换位字节数组(8个字节)，b[0]存储高位字符，大端
	 * 
	 * @param l
	 *            长整数
	 * @return 代表长整数的字节数组
	 */
	public static byte[] longToBytes(long l) {
		byte[] b = new byte[8];
		b[0] = (byte) (l >>> 56);
		b[1] = (byte) (l >>> 48);
		b[2] = (byte) (l >>> 40);
		b[3] = (byte) (l >>> 32);
		b[4] = (byte) (l >>> 24);
		b[5] = (byte) (l >>> 16);
		b[6] = (byte) (l >>> 8);
		b[7] = (byte) (l);
		return b;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	/**
	 * 将一个2位字节数组转换为char字符。<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b		字节数组
	 * @param offset	偏移量
	 * @return char字符
	 */
	public static char bytesToChar(byte[] b, int offset) {
		char c = (char) ((b[0 + offset] << 8) & 0xFF00L);
		c |= (char) (b[1 + offset] & 0xFFL);
		return c;
	}

	/**
	 * 将一个8位字节数组转换为双精度浮点数。<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b		字节数组
	 * @param offset	偏移量
	 * @return 双精度浮点数
	 */
	public static double bytesToDouble(byte[] b, int offset) {
		return Double.longBitsToDouble(bytesToLong(b, offset));
	}

	/**
	 * 将一个4位字节数组转换为浮点数。<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b		字节数组
	 * @param offset	偏移量
	 * @return 浮点数
	 */
	public static float bytesToFloat(byte[] b, int offset) {
		return Float.intBitsToFloat(bytesToInt(b, offset));
	}

	

	
	
	/**
	 * 将一个字节数组按照默认的字符编码转换为字符串
	 * @param b		字节数组
	 * @return		字符串
	 * @throws UnsupportedEncodingException		编码错误抛出异常
	 */
	public static String bytesToString(byte[] b) throws UnsupportedEncodingException {
		return new String(b, DEFAULT_ENCODE);
	}

	/**
	 * 将一个char字符转换位字节数组（2个字节），b[0]存储高位字符，大端
	 * 
	 * @param c
	 *            字符（java char 2个字节）
	 * @return 代表字符的字节数组
	 */
	public static byte[] charToBytes(char c) {
		byte[] b = new byte[8];
		b[0] = (byte) (c >>> 8);
		b[1] = (byte) c;
		return b;
	}

	/**
	 * 将一个双精度浮点数转换位字节数组（8个字节），b[0]存储高位字符，大端
	 * 
	 * @param d
	 *            双精度浮点数
	 * @return 代表双精度浮点数的字节数组
	 */
	public static byte[] doubleToBytes(double d) {
		return longToBytes(Double.doubleToLongBits(d));
	}

	/**
	 * 将一个浮点数转换为字节数组（4个字节），b[0]存储高位字符，大端
	 * 
	 * @param f
	 *            浮点数
	 * @return 代表浮点数的字节数组
	 */
	public static byte[] floatToBytes(float f) {
		return intToBytes(Float.floatToIntBits(f));
	}

	

	
	
    public static int bytesToInt(byte[] b) { 
        int s = 0; 
        int s0 = b[0] & 0xff;// 最低位 
        int s1 = b[1] & 0xff; 
        int s2 = b[2] & 0xff; 
        int s3 = b[3] & 0xff; 
        s3 <<= 24; 
        s2 <<= 16; 
        s1 <<= 8; 
        s = s0 | s1 | s2 | s3; 
        return s; 
    } 
 
     
    
    
    
 
     
    
}
