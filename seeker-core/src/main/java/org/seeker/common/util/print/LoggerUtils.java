package org.seeker.common.util.print;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class LoggerUtils {

	public static void info(String content, Logger logger) {
		logger.info(handleValue(content));
	}

	public static void error(String content, Logger logger) {
		logger.error(handleValue(content));
	}

	public static void debug(String content, Logger logger) {
		logger.debug(handleValue(content));
	}

	public static void fatal(String content, Logger logger) {
		logger.fatal(handleValue(content));
	}

	public static void trace(String content, Logger logger) {
		logger.trace(handleValue(content));
	}

	public static void warn(String content, Logger logger) {
		logger.warn(handleValue(content));
	}

	private static String handleValue(String content) {
		StringBuffer sb = new StringBuffer();
//		sb.append("\n");
//		sb.append("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=[start]-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
//		sb.append("\n");
		sb.append("%1$s");
//		sb.append("\n");
//		sb.append("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-[end]=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		return sb.toString().format(sb.toString(), content);
	}

	public static void setMDCByMap(Map<String, Object> map) {
		for (String key : map.keySet()) {
			MDC.put(key, map.get(key));
		}
	}

	public static void setMDCByMap(String key, Object o) {
		MDC.put(key, o);
	}

	public static void removeMDCAll(List<String> list) {
		for (String key : list) {
			removeMDC(key);
		}
	}

	public static void removeMDC(String key) {
		MDC.remove(key);
	}

}
