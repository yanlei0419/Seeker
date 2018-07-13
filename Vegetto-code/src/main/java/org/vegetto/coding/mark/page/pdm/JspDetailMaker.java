package org.vegetto.coding.mark.page.pdm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.vegetto.coding.pdm.xml.MyColumn;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.InitCodeConfig;
@Deprecated
@SuppressWarnings("unchecked")
public class JspDetailMaker {
	public static Map typedic = null;
	public static Map valdic = null;
	static {
		// 有其他类型的 再添加即可
		typedic = new HashMap();
		typedic.put("int", "Integer");
		typedic.put("string", "String");
		typedic.put("date", "Date");
		typedic.put("double", "Double");
		typedic.put("float", "Double");

		valdic = new HashMap();
		valdic.put("int", "-1");
		valdic.put("string", "\"\"");
		valdic.put("date", "new java.util.Date()");
		valdic.put("double", "0.0");
		valdic.put("float", "0.0");
	}
	// 生成list.jsp
	public static void createDetail(MyTable table, String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Bo";
		
		
		sb.append("<%@ page language=\"java\" import=\"java.util.*\" contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>"    +"\r\n");
		sb.append("<%@ page import=\""+InitCodeConfig.pack+  table.getPackagePath() +".po."+classNameUp+"Po\"%>"    +"\r\n");
		sb.append("<%"    +"\r\n");
		sb.append("	String path = request.getContextPath();"    +"\r\n");
		sb.append("	String basePath = request.getScheme() + \"://\"+ request.getServerName() + \":\" + request.getServerPort()+ path + \"/\";"    +"\r\n");
		sb.append("	String pageUrl = request.getRequestURL().toString();"    +"\r\n");
		sb.append("	String listpageUrl = pageUrl.substring(0,pageUrl.lastIndexOf(\"/\")+1)+\"list.jsp\";"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	"+classNameUp+"Po po = ("+classNameUp+"Po)request.getAttribute(\""+classNameUp+"Po\");"    +"\r\n");
		sb.append("%>"    +"\r\n");
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"    +"\r\n");
		sb.append("<html>"    +"\r\n");
		sb.append("	<head>"    +"\r\n");
		sb.append("		<base href=\"<%=basePath%>\">"    +"\r\n");
		sb.append("		<title>查看"+table.getClassDesc()+"</title>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/jquery.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/easyui.jsp\" flush=\"true\"/>"    +"\r\n");
		
		sb.append("		"    +"\r\n");
		/*
		sb.append("		<style>"    +"\r\n");
		sb.append("			body {"    +"\r\n");
		sb.append("				margin: 0px;"    +"\r\n");
		sb.append("				padding: 0px;"    +"\r\n");
		sb.append("				background: #f7f7f7;"    +"\r\n");
		sb.append("				background-repeat: repeat-x;"    +"\r\n");
		sb.append("				background-color: #FFFFFF;"    +"\r\n");
		sb.append("				height:100%;"    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("			td, div, body {"    +"\r\n");
		sb.append("				color:#465886;"    +"\r\n");
		sb.append("				font-family:Arial,Helvetica,sans-serif;"    +"\r\n");
		sb.append("				font-size:9pt;"    +"\r\n");
		sb.append("				line-height:20px;"    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("		</style>"    +"\r\n");
		
		*/
		sb.append("		"    +"\r\n");
		
		
		sb.append("	</head>"    +"\r\n");
		sb.append("	<body>"    +"\r\n");
		sb.append("		<div id=\""+ classNameUp +"Panel\" class=\"easyui-panel\" style=\"width:'100%';padding:6px;\" data-options=\"title:'查看"+table.getClassDesc()+"',iconCls:'icon-add',collapsible:true,minimizable:false,maximizable:false,closable:false\">"    +"\r\n");
		sb.append("		<form id=\"update\" name=\"update\" action=\"<%=basePath%>"+classNameUp+"_update"+ classNameUp +".do\" method=\"post\">"    +"\r\n");
		sb.append("			<table id=\""+ classNameUp +"\" cellspacing=\"1\" cellpadding=\"0\" border=\"0\" bgcolor=\"#95a5d2\" width=\"100%\">"    +"\r\n");
		
		Map<String, MyColumn> columnMap = table.getColumnMap();// 获得字段信息
		Set<Map.Entry<String, MyColumn>> set = columnMap.entrySet();
		Iterator it = set.iterator();
		it = set.iterator();
		while(it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>)it.next();
			MyColumn column = entry.getValue();
			sb.append("				<tr height=\"30\" align=\"left\" valign=\"middle\">"    +"\r\n");
			sb.append("					<td bgcolor=\"#f3f8fd\" align=\"center\" width=\"30%\"><strong>"+column.getPropertyCnName()+"</strong></td>"    +"\r\n");
			sb.append("					<td bgcolor=\"#f3f8fd\" align=\"left\" width=\"70%\"><%= po.get"+CodeUtil.caseConversionInitial(column.getPropertyName(),true)+"()!=null?po.get"+CodeUtil.caseConversionInitial(column.getPropertyName(),true)+"():\"\" %></td>"    +"\r\n");
			sb.append("				</tr>"    +"\r\n");
		}
		
		sb.append("				<tr height=\"30\" align=\"left\" valign=\"middle\">"    +"\r\n");
		sb.append("					<td bgcolor=\"#f3f8fd\" colspan=\"2\" align=\"center\" width=\"100%\">"    +"\r\n");
		sb.append("						<a href=\"javascript:void(0)\" onclick=\"javascript:window.location.href='<%=listpageUrl %>';\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-back\">返回</a>"    +"\r\n");
		sb.append("					</td>"    +"\r\n");
		sb.append("				</tr>"    +"\r\n");
		
		sb.append("			</table>"    +"\r\n");
		sb.append("		</form>"    +"\r\n");
		sb.append("	</body>"    +"\r\n");
		sb.append("</html>"    +"\r\n");
		
		
		//TODO web-detail-path
		filePath += InitCodeConfig.web_path+table.getUrlPath().replaceAll("\\.", "/")+"/";
		File dir = new File(filePath);
		if(!dir.exists())
			dir.mkdirs();
		File boFile = new File(filePath+"detail.jsp");
		if(!boFile.exists())
			boFile.createNewFile();
		PrintWriter pw = new PrintWriter(boFile, "utf-8");
		pw.write(sb.toString());
		pw.flush();
		pw.close();
	}

}
