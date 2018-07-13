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

@SuppressWarnings("unchecked")
@Deprecated
public class JspAddMaker {
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
	public static void createAdd(MyTable table, String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Bo";
		
		
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
		sb.append("		<jsp:include page=\"/common/include/jquery.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/easyui.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/dialog.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/formValidator.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<script type=\"text/javascript\" src=\"<%= basePath %>plugins/My97DatePicker/WdatePicker.js\"></script>"    +"\r\n");
		
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
		
		sb.append("		<script language=\"javascript\">"    +"\r\n");
		sb.append("			function senfe(o,a,b,c,d){"    +"\r\n");
		sb.append("				var t=document.getElementById(o).getElementsByTagName(\"tr\");"    +"\r\n");
		sb.append("				for(var i=0;i<t.length;i++){"    +"\r\n");
		sb.append("					t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;"    +"\r\n");
		sb.append("				}"    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("			$(document).ready(function(){"    +"\r\n");
		sb.append("				senfe(\""+ classNameUp +"\",\"#f3f8fd\",\"#ffffff\",\"#ADADAD\",\"#f391a9\");"    +"\r\n");
		sb.append("				$.formValidator.initConfig( {"    +"\r\n");
		sb.append("					formID : \"add\","    +"\r\n");
		sb.append("					theme : 'SolidBox',"    +"\r\n");
		sb.append("					mode : 'AutoTip',"    +"\r\n");
		sb.append("					inIframe : true"    +"\r\n");
		sb.append("				});"    +"\r\n");
		
		Map<String, MyColumn> columnMap = table.getColumnMap();// 获得字段信息
		Set<Map.Entry<String, MyColumn>> set = columnMap.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>)it.next();
			MyColumn column = entry.getValue();
			//首先要是可编辑的字段
			if(!column.getEditStyle().contains("hidden")) {
				//根据字段数据类型不同，验证也不同
				String pType = typedic.get(column.getPropertyType()).toString();
				String pName = CodeUtil.caseConversionInitial(column.getPropertyName(),false);
				boolean isEmpty = column.getEditStyle().contains("*")?false:true;
				if(pType.equals("Date") || column.getPropertyCnName().contains("日期") || column.getPropertyCnName().contains("时间")) {//日期类型
					sb.append("				$(\"#"+pName+"\")"    +"\r\n");
					sb.append("					.formValidator({"    +"\r\n");
					sb.append("						empty:"+(isEmpty?"true":"false")+","    +"\r\n");
					sb.append("						onShow:\"请选择"+column.getPropertyCnName()+"\","    +"\r\n");
					sb.append("						onFocus:\"请选择"+column.getPropertyCnName()+"\","    +"\r\n");
					sb.append("						onCorrect:\"您选择的"+column.getPropertyCnName()+"合法\""    +"\r\n");
					sb.append("					})"    +"\r\n");
					sb.append("					.inputValidator({"    +"\r\n");
					sb.append("						onError:\""+column.getPropertyCnName()+"有误,请确认\""    +"\r\n");
					sb.append("					});"    +"\r\n");
					
					sb.append("		"    +"\r\n");
					//sb.append("				$(\"#"+pName+"\").formValidator({onFocus:\"请输入"+column.getPropertyCnName()+"\",onCorrect:\"\"}).inputValidator({"+(isEmpty?"":"min:1,")+"onError:\""+column.getPropertyCnName()+"有误,请确认\"});"    +"\r\n");
				}else if(pType.equals("Integer")) {//整数类型
					sb.append("				$(\"#"+pName+"\")"    +"\r\n");
					sb.append("					.formValidator({"    +"\r\n");
					sb.append("						empty:"+(isEmpty?"true":"false")+","    +"\r\n");
					sb.append("						onShow:\"请输入"+column.getPropertyCnName()+"\","    +"\r\n");
					sb.append("						onFocus:\"请输入"+column.getPropertyCnName()+"\","    +"\r\n");
					sb.append("						onCorrect:\"您输入的"+column.getPropertyCnName()+"合法\""    +"\r\n");
					sb.append("					})"    +"\r\n");
					sb.append("					.inputValidator({"    +"\r\n");
					sb.append("						onError:\""+column.getPropertyCnName()+"有误,请确认\""    +"\r\n");
					sb.append("					})"    +"\r\n");
					sb.append("					.regexValidator({"    +"\r\n");
					sb.append("						regExp:[\"intege\"]//intege1正整数 intege2负整数 "    +"\r\n");
					sb.append("					});"    +"\r\n");
					
					sb.append("		"    +"\r\n");
				}else if(pType.equals("Double")) {//小数类型
					sb.append("				$(\"#"+pName+"\")"    +"\r\n");
					sb.append("					.formValidator({"    +"\r\n");
					sb.append("						empty:"+(isEmpty?"true":"false")+","    +"\r\n");
					sb.append("						onShow:\"请输入"+column.getPropertyCnName()+"\","    +"\r\n");
					sb.append("						onFocus:\"请输入"+column.getPropertyCnName()+"\","    +"\r\n");
					sb.append("						onCorrect:\"您输入的"+column.getPropertyCnName()+"合法\""    +"\r\n");
					sb.append("					})"    +"\r\n");
					sb.append("					.inputValidator({"    +"\r\n");
					sb.append("						onError:\""+column.getPropertyCnName()+"有误,请确认\""    +"\r\n");
					sb.append("					})"    +"\r\n");
					sb.append("					.regexValidator({"    +"\r\n");
					sb.append("						regExp:[\"num\"]//double1 小数后1为，decmal1 正浮点 "    +"\r\n");
					sb.append("					});"    +"\r\n");
					
					sb.append("		"    +"\r\n");
					//sb.append("				$(\"#"+pName+"\").formValidator({empty:"+(isEmpty?"true":"false")+",onFocus:\"请输入"+column.getPropertyCnName()+"\",onCorrect:\"\"}).inputValidator({onError:\""+column.getPropertyCnName()+"有误,请确认\"}).regexValidator({regExp:\"^(?:[0-9][0-9]*(?:\\.[0-9]+)?|0\\.(?!0+$)[0-9]+)$\", onError:\""+column.getPropertyCnName()+"有误,请确认\"});"    +"\r\n");
				}else {//字符串类型
					sb.append("				$(\"#"+pName+"\")"    +"\r\n");
					sb.append("					.formValidator({"    +"\r\n");
					sb.append("						empty:"+(isEmpty?"true":"false")+","    +"\r\n");
					sb.append("						onShow:\"请输入"+column.getPropertyCnName()+"\","    +"\r\n");
					sb.append("						onFocus:\"请输入"+column.getPropertyCnName()+"\","    +"\r\n");
					sb.append("						onCorrect:\"您输入的"+column.getPropertyCnName()+"合法\""    +"\r\n");
					sb.append("					})"    +"\r\n");
					sb.append("					.inputValidator({"    +"\r\n");
					sb.append("						"+(isEmpty?"//":"")+"min:1,"    +"\r\n");
					sb.append("						max:"+column.getFieldLength()+","    +"\r\n");
					sb.append("						onError:\""+column.getPropertyCnName()+"有误,请确认\""    +"\r\n");
					sb.append("					});"    +"\r\n");
					
					sb.append("		"    +"\r\n");
					//sb.append("				$(\"#"+pName+"\").formValidator({onFocus:\"请输入"+column.getPropertyCnName()+"\",onCorrect:\"\"}).inputValidator({"+(isEmpty?"":"min:1,")+"max:"+column.getFieldLength()+",onError:\""+column.getPropertyCnName()+"有误,请确认\"});"    +"\r\n");
				}
			}
		}
		sb.append("		"    +"\r\n");
		sb.append("			});"    +"\r\n");
		sb.append("			$(window).resize(function(){"    +"\r\n");
		sb.append("				$.formValidator.reloadAutoTip();"    +"\r\n");
		sb.append("				$('#"+ classNameUp +"Panel').panel('resize');"    +"\r\n");
		sb.append("			});"    +"\r\n");
		sb.append("		</script>"    +"\r\n");
		
		sb.append("		"    +"\r\n");
		
		sb.append("	</head>"    +"\r\n");
		sb.append("	<body>"    +"\r\n");
		sb.append("		<div id=\""+ classNameUp +"Panel\" class=\"easyui-panel\" style=\"width:'100%';padding:6px;\" data-options=\"title:'新建"+table.getClassDesc()+"',iconCls:'icon-add',collapsible:true,minimizable:false,maximizable:false,closable:false\">"    +"\r\n");
		sb.append("		<form id=\"add\" name=\"add\" action=\"<%=basePath%>"+classNameUp+"Manage_add"+ classNameUp +".do\" method=\"post\">"    +"\r\n");
		sb.append("			<table id=\""+ classNameUp +"\" cellspacing=\"1\" cellpadding=\"0\" border=\"0\" bgcolor=\"#95a5d2\" width=\"100%\">"    +"\r\n");
		
		
		it = set.iterator();
		while(it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>)it.next();
			MyColumn column = entry.getValue();
			//首先要是可编辑的字段
			if(!column.getEditStyle().contains("hidden")) {
				//根据字段数据类型不同，验证也不同
				String pType = typedic.get(column.getPropertyType()).toString();
				String pName = CodeUtil.caseConversionInitial(column.getPropertyName(),false);
				boolean isEmpty = column.getEditStyle().contains("*")?false:true;
				
				sb.append("				<tr height=\"30\" align=\"left\" valign=\"middle\">"    +"\r\n");
				
				sb.append("					<td bgcolor=\"#f3f8fd\" align=\"center\" width=\"30%\"><strong>"+column.getPropertyCnName()+"</strong></td>"    +"\r\n");
				
				sb.append("					<td bgcolor=\"#f3f8fd\" align=\"left\" width=\"70%\">"    +"\r\n");
				
				if(pType.equals("Date") || column.getPropertyCnName().contains("日期") || column.getPropertyCnName().contains("时间")) {//日期类型
					sb.append("						<input type=\"text\" id=\""+pName+"\" name=\""+pName+"\" readonly onClick=\"WdatePicker({el:'"+pName+"', dateFmt:'yyyy-MM-dd HH:mm:ss'})\" />");
				}else {
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
		sb.append("					<td bgcolor=\"#f3f8fd\" colspan=\"2\" align=\"center\" width=\"100%\">"    +"\r\n");
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
		filePath += InitCodeConfig.web_path+table.getUrlPath().replaceAll("\\.", "/")+"/";
		File dir = new File(filePath);
		if(!dir.exists())
			dir.mkdirs();
		File boFile = new File(filePath+"add.jsp");
		if(!boFile.exists())
			boFile.createNewFile();
		PrintWriter pw = new PrintWriter(boFile, "utf-8");
		pw.write(sb.toString());
		pw.flush();
		pw.close();
	}

}
