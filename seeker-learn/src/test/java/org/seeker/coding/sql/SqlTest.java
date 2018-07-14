package org.seeker.coding.sql;

import org.apache.log4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SqlTest {
	static{
		System.setProperty("yanlei", "saber");
	}
	public static void main(String[] args) throws Exception {
		MDC.put("userName", "yanlei");
		ApplicationContext ctx= new ClassPathXmlApplicationContext("spring.xml");
		SQLCode.tableDesc("emp");
//		System.out.println(DbUtil.getDriverName());
	}

}
