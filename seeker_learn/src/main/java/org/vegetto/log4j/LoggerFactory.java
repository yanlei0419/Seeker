package org.vegetto.log4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PatternLayout;

/**
 * Logger工厂类
 * @author 林少强
 *
 */
public class LoggerFactory {
	private static final Layout business_layout1 = new PatternLayout("Operate[%-8X{operateNo}] %-d{yyyy-MM-dd HH:mm:ss} [%p] %X{className}.%M(%L) | %m%n");  
	private static final Layout business_layout2 = new PatternLayout("Operate[%-8X{operateNo}] %-d{yyyy-MM-dd HH:mm:ss} [%p] %c.%M(%L) | %m%n");  
	private static final Layout system_layout = new PatternLayout("%-d{yyyy-MM-dd HH:mm:ss} [%p] %c.%M(%L) | %m%n");  
	private static final String basePath = "/home/pmb/logs/";
	
	/**
	 * 创建业务日志实例
	 * @param orgId 机构ID
	 * @param tellerId 柜员ID
	 * @param operateNo 接口号
	 * @return org.apache.log4j.Logger
	 */
	public static Logger newBusinessLoggerInstance(Object orgId,Object tellerId, Object operateNo) {
		String org = "other";
		if(orgId != null && !"".equals(orgId.toString().trim())){
			org = orgId.toString().trim();
		}
		String teller = "other";
		if(tellerId != null && !"".equals(tellerId.toString().trim())){
			teller = tellerId.toString().trim();
		}
		Logger logger=Logger.getLogger(org + "_" + teller);
		logger.removeAppender("BUSINESS");
		String filename = basePath + "/"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+org+"/"+teller+"/Business.log";
		Appender appender = null;
		try {
//			appender = new DailyRollingFileAppender(business_layout1,filename ,"'.'yyyy-MM-dd");
			appender = new FileAppender(business_layout1, filename);
			appender.setName("BUSINESS");
			MDC.put("operateNo", operateNo.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.addAppender(appender);
		logger.setAdditivity(false);
		return logger;
	}
	
	/**
	 * 创建业务日志实例
	 * @param clazz 类
	 * @param orgId 机构ID
	 * @param tellerId 柜员ID
	 * @param operateNo 接口号
	 * @return org.apache.log4j.Logger
	 */
	public static Logger newBusinessLoggerInstance(Class clazz, Object orgId,Object tellerId, Object operateNo) {
		String org = "other";
		if(orgId != null && !"".equals(orgId.toString().trim())){
			org = orgId.toString().trim();
		}
		String teller = "other";
		if(tellerId != null && !"".equals(tellerId.toString().trim())){
			teller = tellerId.toString().trim();
		}
		Logger logger=Logger.getLogger(clazz);
		logger.removeAppender("BUSINESS");
		String filename = basePath + "/"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+org+"/"+teller+"/Business.log";
		Appender appender = null;
		try {
//			appender = new DailyRollingFileAppender(business_layout2, filename, "'.'yyyy-MM-dd");
			appender = new FileAppender(business_layout2, filename);
			appender.setName("BUSINESS");
			MDC.put("operateNo", operateNo.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.addAppender(appender);
		logger.setAdditivity(false);
		return logger;
	}
	
//	/**
//	 * 创建系统日志实例
//	 * @param clazz 类
//	 * @return org.apache.log4j.Logger
//	 */
//	public static Logger newSystemLoggerInstance(Class clazz){
//		Logger logger=Logger.getLogger(clazz);
//		logger.removeAppender("SYSTEM");
//		String filename = basePath + "/System.log";
//		Appender appender = null;
//		try {
//			appender = new DailyRollingFileAppender(system_layout, filename, "'.'yyyy-MM-dd");
//			appender.setName("SYSTEM");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		logger.addAppender(appender);
//		return logger;
//	}
	
}
