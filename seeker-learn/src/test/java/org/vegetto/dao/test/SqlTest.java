package org.vegetto.dao.test;

import org.apache.log4j.MDC;
import org.seeker.sys.datadic.biz.DataDicBiz;
import org.seeker.sys.datadic.po.DataDicPo;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.seeker.common.base.spring3.SpringContextUtil;

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
