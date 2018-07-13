package org.vegetto.coding.mark.page.excel;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.vegetto.coding.pdm.xml.MyColumn;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.CodeDefaultConfig;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.IncludeUtil;
import org.vegetto.coding.util.InitCodeConfig;
import org.vegetto.coding.util.JSPUtil;
import org.vegetto.coding.util.JSUtil;
import org.vegetto.coding.util.file.WriteFileUtil;

@SuppressWarnings("unchecked")
public class JspAddEl {
	public static Map typedic = CodeDefaultConfig.typedic;
	public static Map valdic = CodeDefaultConfig.valdic;
	// 生成list.jsp
	public static void createAdd(MyTable table, String filePath) throws IOException {
		filePath += InitCodeConfig.web_path+table.getUrlPath().replaceAll("\\.", "/")+"/";
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Bo";
		
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
		sb.append("%>"    +"\r\n");
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"    +"\r\n");
		sb.append("<html>"    +"\r\n");
		sb.append("	<head>"    +"\r\n");
		sb.append("		<base href=\"<%=basePath%>\">"    +"\r\n");
		sb.append("		<title>添加"+table.getClassDesc()+"</title>"    +"\r\n");
		sb.append(IncludeUtil.toAddInclude());
		sb.append(JSUtil.toDateJs());
		
		JSUtil.checkEditJs(table,classNameUp,"add",filePath);
		sb.append("		<script type=\"text/javascript\" src=\"<%= basePath %>"+table.getUrlPath().replaceAll("\\.", "/")+"/js/add.js?ve=1\"></script>"    +"\r\n");
		
		sb.append("		"    +"\r\n");
		sb.append("		<style  type=\"text/css\"> "    +"\r\n");
		sb.append("		</style>"    +"\r\n");
		sb.append("		"    +"\r\n");
		
		
		
		sb.append("		<script language=\"javascript\">"    +"\r\n");
		
//		sb.append(JSUtil.checkEditJs(table, typedic, valdic, classNameUp,"add",filePath));
		
		sb.append("		</script>"    +"\r\n");
		
		sb.append("		"    +"\r\n");
		
		sb.append("	</head>"    +"\r\n");
		sb.append("	<body>"    +"\r\n");
		sb.append("		<div id=\""+ classNameUp +"Panel\" class=\"easyui-panel\" style=\"width:'100%';padding:6px;\" data-options=\"title:'新建"+table.getClassDesc()+"',iconCls:'icon-add',collapsible:true,minimizable:false,maximizable:false,closable:false\">"    +"\r\n");
		sb.append("		<form id=\"add\" name=\"add\" action=\"<%=basePath%>"+classNameUp+"Manage_add"+ classNameUp +".do\" method=\"post\">"    +"\r\n");
		sb.append("			<table id=\""+ classNameUp +"\" cellspacing=\"1\" cellpadding=\"0\" border=\"0\" bgcolor=\"#95a5d2\" width=\"100%\">"    +"\r\n");
		
		
		while(it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>)it.next();
			MyColumn column = entry.getValue();
			String pName = CodeUtil.caseConversionInitial(column.getPropertyName(),false);
			//首先要是可编辑的字段
			if(column.getEditStyle().contains("hidden")){
				sb.append("			<input type=\"hidden\" name=\""+ pName+"\" id=\""+ pName +"\"/>"    +"\r\n");
			}else if(!column.getEditStyle().contains("hidden")) {
				//根据字段数据类型不同，验证也不同
				String pType = typedic.get(column.getPropertyType()).toString();
				boolean isEmpty = column.getEditStyle().contains("*")?false:true;
				sb.append("				<tr height=\"30\" align=\"left\" valign=\"middle\">"    +"\r\n");
				sb.append(JSPUtil.tdPropertyCnName(column.getPropertyCnName()));
				
				sb.append("					<td  align=\"left\" width=\"70%\">"    +"\r\n");
				
				if(pType.equals("Date") || column.getPropertyCnName().contains("日期") ) {//日期类型
					sb.append("						<input type=\"text\" id=\""+pName+"\" name=\""+pName+"\" onClick=\"WdatePicker({el:'"+pName+"', dateFmt:'yyyy-MM-dd'})\"  readonly=\"readonly\" class=\"Wdate\" />");
				}else if(column.getPropertyCnName().contains("时间")) {//日期类型
					sb.append("						<input type=\"text\" id=\""+pName+"\" name=\""+pName+"\"  onClick=\"WdatePicker({el:'"+pName+"', dateFmt:'yyyy-MM-dd HH:mm:ss'})\"  readonly=\"readonly\" class=\"Wdate\" />");
				}else{
					sb.append("						<input type=\"text\" id=\""+pName+"\" name=\""+pName+"\" value=\"\"/>");
				}
				if(!isEmpty)
					sb.append("<font color='red'><b>*</b></font>");
				sb.append("\r\n");
				
				sb.append("					</td>"    +"\r\n");
				sb.append("				</tr>"    +"\r\n");
				
				
				
				
				/*if(pType.equals("Date")) {//日期类型
					sb.append("				<tr height=\"30\" align=\"left\" valign=\"middle\">"    +"\r\n");
					sb.append("					<td bgcolor=\"#f3f8fd\" align=\"right\" width=\"30%\"><strong>"+column.getPropertyCnName()+"</strong></td>"    +"\r\n");
					sb.append("					<td bgcolor=\"#f3f8fd\" align=\"left\" width=\"70%\">"    +"\r\n");
					sb.append("						<input type=\"text\" id=\""+pName+"\" name=\""+pName+"\" readonly onClick=\"WdatePicker({el:'"+pName+"', dateFmt:'yyyy-MM-dd HH:mm:ss'})\" />"    +"\r\n");
					sb.append("					</td>"    +"\r\n");
					sb.append("				</tr>"    +"\r\n");
				}else {
					sb.append("				<tr height=\"30\" bgcolor=\"#FFFFFF\">"    +"\r\n");
					sb.append("					<td width=\"100\" align=\"center\">"+column.getPropertyCnName()+"</td>"    +"\r\n");
					sb.append("					<td>"    +"\r\n");
					sb.append("						<input type=\"text\" id=\""+pName+"\" name=\""+pName+"\" value=\"\"/>"    +"\r\n");
					sb.append("					</td>"    +"\r\n");
					sb.append("				</tr>"    +"\r\n");
				}*/
			}
		}
		
		sb.append("				<tr height=\"30\" align=\"left\" valign=\"middle\">"    +"\r\n");
		sb.append("					<td  colspan=\"2\" align=\"center\" width=\"100%\">"    +"\r\n");
		sb.append("						<a href=\"javascript:void(0)\" onclick=\"$('#add').submit()\" class=\"easyui-linkbutton\" id=\"prsBtn\" plain=\"true\" icon=\"icon-save\">提交</a>"    +"\r\n");
		sb.append("						<a href=\"javascript:void(0)\" onclick=\"$('#add')[0].reset()\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-reload\">重置</a>"    +"\r\n");
		sb.append("						<a href=\"javascript:void(0)\" onclick=\"javascript:window.location.href='<%=listpageUrl %>';\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-back\">返回</a>"    +"\r\n");
		sb.append("					</td>"    +"\r\n");
		sb.append("				</tr>"    +"\r\n");
		
		
		/*sb.append("				<tr height=\"30\" bgcolor=\"#FFFFFF\" align=\"center\">"    +"\r\n");
		sb.append("					<td colspan=\"2\">"    +"\r\n");
		sb.append("						<input type=\"submit\" value=\"提交\" />"    +"\r\n");
		sb.append("						<input type=\"button\" onclick=\"window.location.href='<%=basePath %>"+table.getUrlPath().replaceAll("\\.", "/")+"/list.jsp';\" value=\"返回\" />"    +"\r\n");
		sb.append("					</td>"    +"\r\n");
		sb.append("				</tr>"    +"\r\n");*/
		
		sb.append("			</table>"    +"\r\n");
		sb.append("		</form>"    +"\r\n");
		sb.append("	</body>"    +"\r\n");
		sb.append("</html>"    +"\r\n");
		
		
		//TODO web-add-path
		
		WriteFileUtil.writeFile(filePath, "add.jsp",sb.toString());
	}

}
