package org.seeker.coding.util;

public class IncludeUtil {
	
	public static String toListInclude(){
		StringBuffer sb = new StringBuffer();
		sb.append("		<jsp:include page=\"/common/include/jquery.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/easyui.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/dialog.jsp\" flush=\"true\"/>"    +"\r\n");
		return sb.toString();
	}
	public static String toAddInclude(){
		StringBuffer sb = new StringBuffer();
		sb.append("		<jsp:include page=\"/common/include/jquery.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/easyui.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/dialog.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/formValidator.jsp\" flush=\"true\"/>"    +"\r\n");
	
		return sb.toString();
	}
	public static String toUpdateInclude(){
		StringBuffer sb = new StringBuffer();
		sb.append("		<jsp:include page=\"/common/include/jquery.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/easyui.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/dialog.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/formValidator.jsp\" flush=\"true\"/>"    +"\r\n");
	
		return sb.toString();
	}
	public static String toDetailInclude(){
		StringBuffer sb = new StringBuffer();
		sb.append("		<jsp:include page=\"/common/include/jquery.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/easyui.jsp\" flush=\"true\"/>"    +"\r\n");
		return sb.toString();
	}

}
