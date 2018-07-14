package org.seeker.common.util;

public class StringUtils {
	
	/**
	 * 转换参数
	 * @param val
	 * @return
	 */
	public static String isEmpty(String val){
		if(val!=null&&!"".equals(val)&&!"null".equals(val)&&!"undefined".equals(val)){
			return val;
		}
		return "";
	}
	/**
	 * 是否为null
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val){
		return !isNotNull(val);
	}
	/**
	 * 是否不为null
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val){
		if(val!=null&&!"".equals(val.trim())&&!"null".equals(val.trim())&&!"undefined".equals(val.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * @description 去掉换行符 10：换行符 13：回车
	 * @param str
	 * @return
	 */
	private static String filter(String str) {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13)
				sb.append(str.subSequence(i, i + 1));
		}
		output = new String(sb);
		return output;
	}

}
