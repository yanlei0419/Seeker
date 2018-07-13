package org.vegetto.dao.test;

import org.apache.log4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vegetto.common.base.spring3.SpringContextUtil;
import org.vegetto.sys.datadic.biz.DataDicBiz;
import org.vegetto.sys.datadic.po.DataDicPo;

public class SqlTest {
	public static void main(String[] args) throws Exception {
		MDC.put("userName", "yanlei");
		 new ClassPathXmlApplicationContext("spring.xml");

		DataDicBiz biz=(DataDicBiz) SpringContextUtil.getBean("DataDicBiz");
		DataDicPo po=new DataDicPo();
		po.setCode("CD");
		po.setName("CD");
		po.setContent("CD");
		po.setTypecode("CD");
		biz.addDataDic(po);
		System.out.println(MDC.get("userName"));
		
		
	}

}
