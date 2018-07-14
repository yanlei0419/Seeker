package org.seeker.log4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Logger工厂类
 * @author 林少强
 *
 */
public class LoggerFactory_1 {
	private static final Layout dayend_layout = new PatternLayout("%-d{yyyy-MM-dd HH:mm:ss} [%p] %X{className}.%M(%L) | %m%n");  
	private static final String basePath = "/home/pmb/PMB_BATCH/logs/";
	
	/**
	 * 创建日终日志实例
	 * @param clazz 类
	 * @return org.apache.log4j.Logger
	 */
	public static Logger newDayEndLoggerInstance(){
		Logger logger=Logger.getLogger(LoggerFactory_1.class);
		logger.setLevel(Level.INFO);
		logger.removeAppender("DAYEND");
		String filename = basePath + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/DAY_END_JAVA.log";
		Appender appender = null;
		try {
			appender = new FileAppender(dayend_layout, filename);
			appender.setName("DAYEND");
		} catch (IOException e) {			e.printStackTrace();
		}
		logger.addAppender(appender);
		return logger;
	}
	
}
