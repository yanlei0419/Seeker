package org.seeker.coding.util;


public class CodeUtil {
	//首字母大小写转换 flag:true 大写 false 小写
	public static String caseConversionInitial(String srcString, boolean flag) {  
		if(srcString==null||"".equals(srcString)){
			System.out.println("大小写转换有问题srcString为null");
			return "";
		}
		StringBuilder sb = new StringBuilder();  
	    if (flag) {  
	    	sb.append(Character.toUpperCase(srcString.charAt(0)));  
	    } else {  
	    	sb.append(Character.toLowerCase(srcString.charAt(0)));  
	    }  
	    sb.append(srcString.substring(1));  
	    return sb.toString();  
	} 
	
	public static String getDaoJdbcPage(){
		StringBuilder sb = new StringBuilder();  
		sb.append("import org.apache.commons.dbutils.handlers.BeanHandler;" + "\r\n");
		sb.append("import org.apache.commons.dbutils.handlers.BeanListHandler;" + "\r\n");
		sb.append("" + "\r\n");
		// TODO daoImpl-修改-daoImpl
		sb.append("import org.vegetto.common.base.id.Uuid;" + "\r\n");
		sb.append("import org.vegetto.common.base.db.BaseDao;" + "\r\n");
		sb.append("import org.vegetto.common.util.StringUtils;" + "\r\n");
		 return sb.toString();  
	}
	//首字母大小写转换 flag:true 大写 false 小写
	public static String wordCaseConvert(String srcString, boolean flag) {  
		StringBuilder sb = new StringBuilder();  
	    if (flag) {  
	    	sb.append(srcString.toUpperCase());  
	    } else {  
	    	sb.append(srcString.toLowerCase());  
	    }  
	    return sb.toString();  
	} 
}
