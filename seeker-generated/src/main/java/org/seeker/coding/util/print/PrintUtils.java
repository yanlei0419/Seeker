package org.seeker.coding.util.print;

import org.apache.log4j.Logger;


public abstract class PrintUtils extends LoggerUtils{
	protected static Logger logger = Logger.getLogger(PrintUtils.class.getClass());
	
	public PrintUtils(){}
	public static void S(Object o) {
		System.out.println("{}"+o);
	}

	public static void print(String val) {
		Console(val, logger);
	}
	public static void print(Object obj) {
		Console(obj.toString(), logger);
	}
	
	public static void print(String name,String val) {
		print("{ "+name+" : "+val+" }");
	}
	
	public static void info(String str){
		info(str, logger);
	}

	/**
	 * 打印到控制台
	 */
	public static void Console(String content,Logger logger){
		info(content, logger);
	}
	
	public static void print(int val) {
		print(val+"");
	}
	public static void print(long val) {
		print(val+"");
	}
	public static void print(short val) {
		print(val+"");
	}
	public static void print(float val) {
		print(val+"");
	}
	public static void print(double val) {
		print(val+"");
	}
	public static void print(boolean val) {
		print(val+"");
	}
	public static void print(char val) {
		print(val);
	}
	public static void print(byte val) {
		print(val);
	}
	


}
