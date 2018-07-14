package org.seeker.coding.util;

import java.util.HashMap;
import java.util.Map;

public class CodeDefaultConfig {
	public static Map<String,String> typedic = null;
	public static Map<String,String> valdic = null;
	static {
		// 有其他类型的 再添加即可
		typedic = new HashMap<String,String>();
		typedic.put("int", "Integer");
		typedic.put("string", "String");
		typedic.put("date", "Date");
		typedic.put("double", "Double");
		typedic.put("float", "Double");
		typedic.put("Integer", "Integer");
		typedic.put("String", "String");
		typedic.put("Date", "Date");
		typedic.put("Double", "Double");
		typedic.put("Double", "Double");

		valdic = new HashMap<String,String>();
		valdic.put("int", "-1");
		valdic.put("string", "\"\"");
		valdic.put("date", "new java.util.Date()");
		valdic.put("double", "0.0");
		valdic.put("float", "0.0");
		
		valdic.put("String", "\"\"");
	}
}
