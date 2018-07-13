package org.vegetto.coding.mark.page.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vegetto.coding.pdm.xml.MyColumn;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.CodeDefaultConfig;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.IncludeUtil;
import org.vegetto.coding.util.InitCodeConfig;
import org.vegetto.coding.util.JSUtil;
import org.vegetto.coding.util.file.WriteFileUtil;
@SuppressWarnings("unchecked")
public class JspListEl {
	public static Map typedic = CodeDefaultConfig.typedic;
	public static Map valdic = CodeDefaultConfig.valdic;
	
	// 生成list.jsp
	public static void createList(MyTable table, String filePath) throws IOException {
		filePath +=InitCodeConfig.web_path+table.getUrlPath().replaceAll("\\.", "/")+"/";
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Bo";
		List<MyColumn> searchColumn = new ArrayList<MyColumn>();
		
		sb.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>"    +"\r\n");
		sb.append("<%"    +"\r\n");
		sb.append("	String path = request.getContextPath();"    +"\r\n");
		sb.append("	String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";"    +"\r\n");
		sb.append("%>"    +"\r\n");
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"    +"\r\n");
		sb.append("<html>  "    +"\r\n");
		sb.append("	<head>"    +"\r\n");
		sb.append("	    <title>"+table.getClassDesc()+"列表</title>"    +"\r\n");
		sb.append("	    <base href=\"<%=basePath%>\">"    +"\r\n");
		sb.append("	    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>"    +"\r\n");
		
		sb.append(IncludeUtil.toListInclude());
		sb.append(JSUtil.toDateJs());		
		
		sb.append("		<script type=\"text/javascript\" src=\"<%= basePath %>"+table.getUrlPath().replaceAll("\\.", "/")+"/js/list.js\"></script>"    +"\r\n");
		JSUtil.getPageListJs(table, classNameUp,searchColumn,filePath);
		
		sb.append("		<style  type=\"text/css\">"    +"\r\n");
		sb.append("		</style>"    +"\r\n");
		
		
		
		
		sb.append("		<script type=\"text/javascript\">"    +"\r\n");
		sb.append("			var basePath='<%=basePath%>';" +"\r\n");

		sb.append("		</script>"    +"\r\n");
		sb.append("	</head>"    +"\r\n");
		sb.append("	<body>"    +"\r\n");
//		sb.append("		<div id=\""+classNameUp+"ContextMenu\" class=\"easyui-menu\" style=\"width:120px;\">"    +"\r\n");
//		sb.append("			<div data-options=\"iconCls:'icon-add'\" onclick=\"javascript:Add()\">新建</div>"    +"\r\n");
//		sb.append("			<div data-options=\"iconCls:'icon-remove'\" onclick=\"javascript:Delete()\">删除</div>"    +"\r\n");
//		sb.append("			<div data-options=\"iconCls:'icon-edit'\" onclick=\"javascript:Update()\">修改</div>"    +"\r\n");
//		sb.append("			<div data-options=\"iconCls:'icon-search'\" onclick=\"javascript:Detail()\">查看</div>"    +"\r\n");
//		sb.append("			<div data-options=\"iconCls:'icon-reload'\" onclick=\"javascript:initTable()\">刷新</div>"    +"\r\n");
//		sb.append("		</div>"    +"\r\n");
		sb.append("		<div id=\""+classNameUp+"Panel\" class=\"easyui-panel\" style=\"width:'100%';padding:6px;\" data-options=\"title:'条件查询',iconCls:'icon-search',collapsible:true,minimizable:false,maximizable:false,closable:false\">"    +"\r\n");
		
		//输出查询条件
		for(int i=1; i<=searchColumn.size(); i++) {
			MyColumn column = searchColumn.get(i-1);
			String pType = typedic.get(column.getPropertyType()).toString();
			String fieldName = CodeUtil.caseConversionInitial(column.getFieldName(),false);
			if(pType.equals("Date") || column.getPropertyCnName().contains("日期") || column.getPropertyCnName().contains("时间")) {//日期类型
				sb.append("			<label>"+column.getPropertyCnName()+"</label><input type=\"text\" id=\""+fieldName+"\" name=\""+fieldName+"\" readonly onClick=\"WdatePicker({el:'"+fieldName+"', dateFmt:'yyyy-MM-dd HH:mm:ss'})\" />"    +"\r\n");
			}else {
				sb.append("			<label>"+column.getPropertyCnName()+"</label><input type=\"text\" id=\""+fieldName+"\" name=\""+fieldName+"\" />"    +"\r\n");
			}
		}
		sb.append("			<a href=\"javascript:void(0)\" onclick=\"initTable()\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-search\">查询</a>"    +"\r\n");
		sb.append("		</div>"    +"\r\n");
		sb.append("		<table id=\""+classNameUp+"\"></table>"    +"\r\n");
		sb.append("	</body>"    +"\r\n");
		sb.append("</html>"    +"\r\n");
		
		//TODO web-list-path
		
		WriteFileUtil.writeFile(filePath, "list.jsp",sb.toString());
	}

}
