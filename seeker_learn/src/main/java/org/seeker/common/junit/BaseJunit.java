package org.seeker.common.junit;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseJunit {
	public static BeanFactory factory;

	@BeforeClass
	public static void init() {
		System.out.println("加载spring配置开始 ............");

		ArrayList<String> list = new ArrayList<String>();
		list.add("spring.xml"); // 将Sprint配置文件加入待加载列表
		// list.add("Spring_jndi_test.xml"); // 将测试用的数据源配置文件加入待加载列表
		try {
			factory = new ClassPathXmlApplicationContext(list.toArray(new String[list.size()]));
			// 保证虚拟机退出之前 spring中singtleton对象自定义销毁方法会执行
			((AbstractApplicationContext) factory).registerShutdownHook();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载配置文件时发生错误" + e);
		}

		System.out.println("加载spring配置结束.............");
	}
}
