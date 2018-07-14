package org.vegetto.demo;

import java.util.Properties;

import org.vegetto.properties.SysConfig;

public class Test {
	public static void main(String[] args) throws InterruptedException {
//		String s="12";
//		byte[] b=s.getBytes();
//		for (int i = 0; i < b.length; i++) {
//			System.out.println(b[i]);
//		}
		
//		byte b=-42;
//		int result=b&0xff;
//		System.out.println(result);
//		System.out.println(Integer.toBinaryString(result));
		
//		C c=new C();
//		c.map.put("1", "2");
//		System.out.println(c.map);
//		System.out.println(c.l);
		
		Properties properties=SysConfig.getProp();
		System.setProperties(properties);
		System.out.println(System.getProperty("jdbcType"));
		System.out.println(System.getProperty("projectName"));
		System.out.println(System.getProperty("sessionUserName"));
		System.out.println(System.getProperty("ve.DBType"));
		System.out.println(System.getProperty("ve.uniqueResourceName"));
//		Thread.sleep(60*1000*60);
	}
}
