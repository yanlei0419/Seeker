package org.vegetto.coding.util;


public class JSPUtil {
	public static String tdPropertyCnName(String PropertyCnName){
		StringBuffer sb = new StringBuffer();
		sb.append("					<td  align=\"center\" width=\"30%\">" +"\r\n");
//		sb.append("						<strong>");
		sb.append("						"+PropertyCnName);
//		sb.append(PropertyCnName);
//		sb.append("</strong>" +"\r\n");
		sb.append("\r\n");
		sb.append("					</td>"    +"\r\n");
		return sb.toString();
	}

}
