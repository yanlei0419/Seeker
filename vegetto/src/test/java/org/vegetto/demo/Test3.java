package org.vegetto.demo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.vegetto.common.util.DateUtil;

public class Test3 {
	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		Date date=DateUtil.addDay(new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-30"),99);
		
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
		
		int count =0;
		System.out.println(count++==0);
//		System.out.println("中国".getBytes("utf-8").length);
//		System.out.println("中国".getBytes("gb2312").length);
//		System.out.println("我".getBytes("gbk").length);
		
		double d=0.01;
		double d1=0.01;
		
		for (int i = 0; i <30; i++) {
			d=d*2;
			d1*=2;
		}
		System.out.println(d);
		System.out.println(d1);
	}
}
