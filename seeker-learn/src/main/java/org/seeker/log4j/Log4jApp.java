package org.seeker.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 1.2. 主要组件
Log4j 有三个主要组件： loggers、 appenders 和 layouts。这三个组件协同工作，使开发
人员能够根据消息类型和级别来记录消息，并且在程序运行期控制消息的输出格式位置。
 Logger： 日志记录器
Logger 负责处理日志记录的大部分操作。
 Appender：日志信息的输出目的地
Appender 负责控制日志记录操作的输出。
 Layout： 日志格式化器
Layout 负责格式化 Appender 的输出。
%p 输出优先级，即 DEBUG， INFO， WARN， ERROR， FATAL
%r 输出自应用启动到输出该 log 信息耗费的毫秒数
%c 输出所属的类目，通常就是所在类的全名
%t 输出产生该日志事件的线程名
%n 输出一个回车换行符， Windows 平台为"\r\n"， Unix 平台为"\n"
%d 输出日志时间点的日期或时间，默认格式为 ISO8601，也可以在其后指定格式，比
如： %d{yyy MMM dd HH:mm:ss,SSS}，输出类似： 2002 年 10 月 18 日 22： 10： 28， 921
%l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：
Testlog4.main(TestLog4.java:10)
 * 
 * 
 * */
public class Log4jApp {
	static{
		String path="d:/log4j/";
//		${test2.path}/${test2.fileName}.log
		System.setProperty("test1.path", path);
		System.setProperty("test1.fileName", "test1");
		System.setProperty("test2.path", path);
		System.setProperty("test2.fileName", "test2");
	}

	public void printLog() {
		Logger log = Logger.getLogger(Log4jApp.class.getClass());
		log.info("测试info");
		log.debug("测试debug");  
		log.error("测试error");
	}

	public static void main(String[] args) {
//		String url = Log4jApp.class.getResource("/log4j.xml").getPath();
		String url = Log4jApp.class.getResource("/log4j/log4j.properties").getPath();
		System.out.println(url);
//		DOMConfigurator.configure(url);
		 PropertyConfigurator.configure(url);

//		Log4jApp app = new Log4jApp();
//		app.printLog();
		
//		MDC.put("test1.path", path);
//		MDC.put("test1.fileName", "test1");
//		MDC.put("test2.path", path);
//		MDC.put("test2.fileName", "test2");
//		Test1 t1=new Test1();
//		Test2 t2=new Test2();
		
		
//		Thread t3=new Thread(runable);
//		t3.start();
		
		
//		t1.printLog();
//		t2.printLog();

//		ApplicationContext ctx= new ClassPathXmlApplicationContext("spring.xml");
	}
}
