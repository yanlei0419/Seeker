package org.vegetto.test;

public class JinZhiTest {
	public static void main(String[] args) {
		int b=14;
		
		System.out.println(Integer.toHexString(b));//16进制
		System.out.println(Integer.toOctalString(b));//8进制
		System.out.println(Integer.toBinaryString(b));//2进制
		System.out.println(b>>2);//左移
		System.out.println(b<<2);//右移
		System.out.println(Integer.valueOf("FFFF", 16));//16进制转换成10进制
		System.out.println(Integer.valueOf("111000", 2));//2进制转换成10进制
	}
}
