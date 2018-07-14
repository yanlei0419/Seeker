package org.seeker.coding.mark.page.excel;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.seeker.coding.pdm.xml.MyTable;
import org.seeker.coding.util.CodeUtil;
import org.seeker.coding.util.IncludeUtil;
import org.seeker.coding.util.InitCodeConfig;
import org.seeker.coding.util.JSPUtil;
import org.seeker.coding.util.file.WriteFileUtil;
import org.seeker.coding.pdm.xml.MyColumn;
import org.seeker.coding.util.CodeDefaultConfig;

@SuppressWarnings("unchecked")
public class JspDetailEl {
	public static Map typedic = CodeDefaultConfig.typedic;
	public static Map valdic = CodeDefaultConfig.valdic;
	// 生成list.jsp
	public static void createDetail(MyTable table, String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Bo";
		String poClassName = classNameUp+"Po";
		Map<String, MyColumn> columnMap = table.getColumnMap();// 获得字段信息
		Set<Map.Entry<String, MyColumn>> set = columnMap.entrySet();
		Iterator it = set.iterator();
		it = set.iterator();
		
		sb.append("<%@ page language=\"java\" import=\"java.util.*\" contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>"    +"\r\n");
		sb.append("<%"    +"\r\n");
		sb.append("	String path = request.getContextPath();"    +"\r\n");
		sb.append("	String basePath = request.getScheme() + \"://\"+ request.getServerName() + \":\" + request.getServerPort()+ path + \"/\";"    +"\r\n");
		sb.append("	String pageUrl = request.getRequestURL().toString();"    +"\r\n");
		sb.append("	String listpageUrl = pageUrl.substring(0,pageUrl.lastIndexOf(\"/\")+1)+\"list.jsp\";"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("%>"    +"\r\n");
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"    +"\r\n");
		sb.append("<html>"    +"\r\n");
		sb.append("	<head>"    +"\r\n");
		sb.append("		<base href=\"<%=basePath%>\">"    +"\r\n");
		sb.append("		<title>查看"+table.getClassDesc()+"</title>"    +"\r\n");
	
		sb.append(IncludeUtil.toDetailInclude());
		
		sb.append("		"    +"\r\n");
		sb.append("		<style  type=\"text/css\">"    +"\r\n");
		sb.append("		</style>"    +"\r\n");
		sb.append("		"    +"\r\n");
		
		
		sb.append("	</head>"    +"\r\n");
		sb.append("	<body>"    +"\r\n");
		sb.append("		<div id=\""+ classNameUp +"Panel\" class=\"easyui-panel\" style=\"width:'100%';padding:6px;\" data-options=\"title:'查看"+table.getClassDesc()+"',iconCls:'icon-add',collapsible:true,minimizable:false,maximizable:false,closable:false\">"    +"\r\n");
//		sb.append("		<form id=\"update\" name=\"update\" action=\"<%=basePath%>"+classNameUp+"_update"+ classNameUp +".do\" method=\"post\">"    +"\r\n");
		sb.append("			<table id=\""+ classNameUp +"\" cellspacing=\"1\" cellpadding=\"0\" border=\"0\"  width=\"100%\">"    +"\r\n");
		

		while(it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>)it.next();
			MyColumn column = entry.getValue();
			sb.append("				<tr height=\"30\" align=\"left\" valign=\"middle\">"    +"\r\n");
			sb.append(JSPUtil.tdPropertyCnName(column.getPropertyCnName()));
//			sb.append("					<td bgcolor=\"#f3f8fd\" align=\"left\" width=\"70%\"><%= po.get"+CodeUtil.caseConversionInitial(column.getPropertyName(),true)+"()!=null?po.get"+CodeUtil.caseConversionInitial(column.getPropertyName(),true)+"():\"\" %></td>"    +"\r\n");
			sb.append("					<td  align=\"left\" width=\"70%\">${"+poClassName+"."+CodeUtil.caseConversionInitial(column.getPropertyName(),false)+"}</td>"    +"\r\n");
			sb.append("				</tr>"    +"\r\n");
		}
		
		sb.append("				<tr height=\"30\" align=\"left\" valign=\"middle\">"    +"\r\n");
		sb.append("					<td colspan=\"2\" align=\"center\" width=\"100%\">"    +"\r\n");
		sb.append("						<a href=\"javascript:void(0)\" onclick=\"javascript:window.location.href='<%=listpageUrl %>';\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-back\">返回</a>"    +"\r\n");
		sb.append("					</td>"    +"\r\n");
		sb.append("				</tr>"    +"\r\n");
		
		sb.append("			</table>"    +"\r\n");
//		sb.append("		</form>"    +"\r\n");
		sb.append("	</body>"    +"\r\n");
		sb.append("</html>"    +"\r\n");
		
		
		//TODO web-detail-path
		filePath += InitCodeConfig.web_path+table.getUrlPath().replaceAll("\\.", "/")+"/";
		WriteFileUtil.writeFile(filePath, "detail.jsp",sb.toString());
	}

}
