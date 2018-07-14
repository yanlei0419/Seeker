package org.seeker.common.util.print;

import org.apache.log4j.Logger;
import org.seeker.common.util.BaseUtils;

public abstract class PrintUtils extends LoggerUtils{
	protected static Logger logger = Logger.getLogger(BaseUtils.class.getName());
	public static void print(String val) {
		Console(val, logger);
	}
	public static void print(Object obj) {
		Console(obj.toString(), logger);
	}
	
	public static void print(String name,String val) {
		print("{ "+name+" : "+val+" }");
	}
	

	/**
	 * 打印到控制台
	 */
	private static void Console(String content,Logger logger){
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
