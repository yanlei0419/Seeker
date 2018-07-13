package org.vegetto.coding.mark.page.pdm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vegetto.coding.pdm.xml.MyColumn;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.InitCodeConfig;
@SuppressWarnings("unchecked")
@Deprecated
public class JspListMaker {
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
	public static void createList(MyTable table, String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Bo";
		
		
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
		sb.append("		<jsp:include page=\"/common/include/jquery.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/easyui.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<jsp:include page=\"/common/include/dialog.jsp\" flush=\"true\"/>"    +"\r\n");
		sb.append("		<script type=\"text/javascript\" src=\"<%= basePath %>plugins/My97DatePicker/WdatePicker.js\"></script>"    +"\r\n");
		

		
		
		
		
		
		sb.append("		<script type=\"text/javascript\">"    +"\r\n");
		sb.append("		function fillsize(percent) {"    +"\r\n");
		sb.append("			var bodyWidth = document.body.clientWidth;"    +"\r\n");
		sb.append("			return (bodyWidth - 90) * percent;"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("		function initTable(){"    +"\r\n");
		sb.append("			$('#"+classNameUp+"').datagrid({"    +"\r\n");
		sb.append("				//title:'列表显示',//标题文字 "    +"\r\n");
		sb.append("				iconCls:'icon-save',//一个css类，将提供一个背景图片作为标题图标 "    +"\r\n");
		sb.append("				width:'100%',//fillsize(0.99)"    +"\r\n");
		sb.append("				height:'auto',"    +"\r\n");
		sb.append("				nowrap: false,//是否在一行显示数据 "    +"\r\n");
		sb.append("				striped: true,//奇偶行使用不同背景色，默认为false "    +"\r\n");
		sb.append("				collapsible:true,"    +"\r\n");
		sb.append("				url:'<%=basePath%>"+classNameUp+"Manage_getList.do',//从远程站点请求数据的URL "    +"\r\n");
		sb.append("				//sortName: '"+ CodeUtil.caseConversionInitial(table.getPrimaryKey(),false) +"',//可以排序的列 "    +"\r\n");
		sb.append("				//sortOrder: 'desc',//定义列的排序顺序，只能用 'asc' 或 'desc' "    +"\r\n");
		sb.append("				remoteSort: true,//定义是否从服务器给数据排序"    +"\r\n");
		sb.append("				//singleSelect:true,//只允许选中一行 "    +"\r\n");
		sb.append("				//checkOnSelect:false,//选中一行时同时选中checkbox "    +"\r\n");
		sb.append("				//selectOnCheck:true,//选中checkbox时同时选中改行 "    +"\r\n");
		sb.append("				fitColumns: true,//自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动 "    +"\r\n");
		sb.append("				pageNumber:1,//当设置了 pagination 特性时，初始化页码 "    +"\r\n");
		sb.append("				pageSize: 10,//当设置了 pagination 特性时，初始化页码尺寸，即每页显示的记录条数，默认为10"    +"\r\n");
		sb.append("				pageList:[10,15,20,30,50,100],//当设置了pagination 特性时，初始化页面尺寸的选择列表，可以设置每页记录条数的列表 "    +"\r\n");
		sb.append("				idField:'"+ CodeUtil.caseConversionInitial(table.getPrimaryKey(),false) +"',//标识字段 "    +"\r\n");
		sb.append("				frozenColumns:[["    +"\r\n");
		sb.append("					{field:'ck',checkbox:true}"    +"\r\n");
		sb.append("				]],"    +"\r\n");
		sb.append("				queryParams:{"    +"\r\n");
		
		Map<String, MyColumn> columnMap = table.getColumnMap();//
		Set<Map.Entry<String, MyColumn>> set = columnMap.entrySet();
		Iterator it = set.iterator();
		List<MyColumn> searchColumn = new ArrayList<MyColumn>();
		List<MyColumn> showColumn = new ArrayList<MyColumn>();
		List<MyColumn> hiddenColumn = new ArrayList<MyColumn>();
		while(it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>)it.next();
			MyColumn column = entry.getValue();
			//ShowStyle 带有*的为查询条件
			if(column.getShowStyle().contains("*")) {
				searchColumn.add(column);
			}
			//ShowStyle 不是hidden的，都要在列表中显示
			if(!column.getShowStyle().contains("hidden")) {
				showColumn.add(column);
			}else {
				hiddenColumn.add(column);
			}
		}
		for(int i=1; i<=searchColumn.size(); i++) {
			MyColumn column = searchColumn.get(i-1);
			String fieldName = CodeUtil.caseConversionInitial(column.getFieldName(),false);
			String douhao = i==searchColumn.size()?"":",";//
			sb.append("						"+fieldName+":document.getElementById(\""+fieldName+"\").value"+douhao+""    +"\r\n");
		}
		
		sb.append("				},	"    +"\r\n");
		sb.append("				columns:[[				"    +"\r\n");
		
		sb.append("					{field:'"+ CodeUtil.caseConversionInitial(table.getPrimaryKey(),false) +"',title:'ID',width:fillsize(33),align:'center',hidden:true,sortable:true},"    +"\r\n");
		//输出显示字段
		for(int i=1; i<=showColumn.size(); i++) {
			MyColumn column = showColumn.get(i-1);
			if(table.getPrimaryKey().equals(column.getPropertyName())) {
				continue;
			}
			String fieldName = CodeUtil.caseConversionInitial(column.getFieldName(),false);
			String douhao = ",";
			if(hiddenColumn.size()==0)//如果有要输出的隐藏字段，则补充一个逗号
				douhao = i==showColumn.size()?"":",";//
			sb.append("					{field:'"+ fieldName +"',title:'"+ column.getPropertyCnName() +"',width:fillsize(1),align:'center',hidden:false,sortable:true}"+douhao+""    +"\r\n");
		}
		
		sb.append("						"    +"\r\n");
		
		
		//输出不显示字段
		for(int i=1; i<=hiddenColumn.size(); i++) {
			MyColumn column = hiddenColumn.get(i-1);
			if(table.getPrimaryKey().equals(column.getPropertyName())) {
				continue;
			}
			String fieldName = CodeUtil.caseConversionInitial(column.getFieldName(),false);
			String douhao = i==hiddenColumn.size()?"":",";//
			sb.append("					{field:'"+ fieldName +"',title:'"+ column.getPropertyCnName() +"',width:fillsize(1),align:'center',hidden:true}"+douhao+""    +"\r\n");
		}
		sb.append("						"    +"\r\n");
		
		sb.append("				]],"    +"\r\n");
		
		
		sb.append("				toolbar:["    +"\r\n");
		sb.append("					{"    +"\r\n");
		sb.append("						text:'新建',"    +"\r\n");
		sb.append("						disabled:false,//加上disabled:true属性就能使该按钮变灰不可用 "    +"\r\n");
		sb.append("						iconCls:'icon-add',"    +"\r\n");
		sb.append("						handler:Add"    +"\r\n");
		sb.append("					},'-',{"    +"\r\n");
		sb.append("						text:'删除',"    +"\r\n");
		sb.append("						disabled:false,"    +"\r\n");
		sb.append("						iconCls:'icon-remove',"    +"\r\n");
		sb.append("						handler:Delete"    +"\r\n");
		sb.append("					},'-',{"    +"\r\n");
		sb.append("						text:'修改',"    +"\r\n");
		sb.append("						disabled:false,"    +"\r\n");
		sb.append("						iconCls:'icon-edit',"    +"\r\n");
		sb.append("						handler:Update"    +"\r\n");
		sb.append("					},'-',{"    +"\r\n");
		sb.append("						text:'查看',"    +"\r\n");
		sb.append("						disabled:false,"    +"\r\n");
		sb.append("						iconCls:'icon-search',"    +"\r\n");
		sb.append("						handler:Detail"    +"\r\n");
		sb.append("					}"    +"\r\n");
		sb.append("				],"    +"\r\n");
		sb.append("				onHeaderContextMenu: function(e, field){"    +"\r\n");
		sb.append("					e.preventDefault();"    +"\r\n");
		sb.append("					if (!$('#tmenu').length){"    +"\r\n");
		sb.append("						createColumnMenu();"    +"\r\n");
		sb.append("					}"    +"\r\n");
		sb.append("					$('#tmenu').menu('show', {"    +"\r\n");
		sb.append("						left:e.pageX,"    +"\r\n");
		sb.append("						top:e.pageY"    +"\r\n");
		sb.append("					});"    +"\r\n");
		sb.append("				},"    +"\r\n");
		sb.append("				onRowContextMenu: function(e,row){"    +"\r\n");
		sb.append("					e.preventDefault();"    +"\r\n");
		sb.append("					$('#"+classNameUp+"ContextMenu').menu('show', {"    +"\r\n");
		sb.append("						left: e.pageX,"    +"\r\n");
		sb.append("						top: e.pageY"    +"\r\n");
		sb.append("					});"    +"\r\n");
		sb.append("				},"    +"\r\n");
		sb.append("				pagination : true,//在 datagrid 的底部显示分页栏 "    +"\r\n");
		sb.append("				rownumbers : true,//显示行号 "    +"\r\n");
		sb.append("				loadMsg : '查询中，请稍后……'//当从远程站点加载数据时，显示的提示信息 "    +"\r\n");
		sb.append("			});"    +"\r\n");
		sb.append("			clearSelections();//取消已经选择的多选框"    +"\r\n");
		sb.append("			var p = $('#"+classNameUp+"').datagrid('getPager');"    +"\r\n");
		sb.append("			$(p).pagination( {"    +"\r\n");
		sb.append("				onBeforeRefresh : function() {"    +"\r\n");
		sb.append("					//alert('before refresh');"    +"\r\n");
		sb.append("					//initTable();"    +"\r\n");
		sb.append("				},"    +"\r\n");
		sb.append("				beforePageText : '第',//页数文本框前显示的汉字 "    +"\r\n");
		sb.append("				afterPageText : '共{pages}页',"    +"\r\n");
		sb.append("				displayMsg : '共{total}条记录，本页显示第{from}-{to}条'"    +"\r\n");
		sb.append("			});"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		function Add(){"    +"\r\n");
		sb.append("			window.location.href='<%=basePath%>"+ table.getUrlPath().replaceAll("\\.", "/") +"/add.jsp';"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		function Delete(){"    +"\r\n");
		sb.append("			var ids =[];"    +"\r\n");
		sb.append("			var rows = $('#"+classNameUp+"').datagrid('getSelections');"    +"\r\n");
		sb.append("			if(rows.length<1){"    +"\r\n");
		sb.append("				$.messager.alert('提示','请选择一条记录！','warning');"    +"\r\n");
		sb.append("				return;"    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("			for(var i=0;i<rows.length;i++){"    +"\r\n");
		sb.append("				ids.push(rows[i]."+ CodeUtil.caseConversionInitial(table.getPrimaryKey(),false) +");"    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("			$.messager.confirm('确认','确认删除选中的记录？',function(result){  "    +"\r\n");
		sb.append("				if(result){ "    +"\r\n");
		sb.append("					$.ajax({ "    +"\r\n");
		sb.append("						type : \"POST\", "    +"\r\n");
		sb.append("						url:'<%=basePath%>"+classNameUp+"Manage_delete"+classNameUp+".do', "    +"\r\n");
		sb.append("						traditional: true,//在struts2下该属性必须有 "    +"\r\n");
		sb.append("						data:{ids:ids}, "    +"\r\n");
		sb.append("						success:function(result){ "    +"\r\n");
		sb.append("							$.messager.alert('提示',result); "    +"\r\n");
		sb.append("							initTable(); "    +"\r\n");
		sb.append("						}"    +"\r\n");
		sb.append("					});"    +"\r\n");
		sb.append("				}"    +"\r\n");
		sb.append("			})"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		function Update(){"    +"\r\n");
		sb.append("			var rows = $('#"+classNameUp+"').datagrid('getSelections');"    +"\r\n");
		sb.append("			if(rows.length<1){"    +"\r\n");
		sb.append("				$.messager.alert('提示','请选择一条记录！','warning');"    +"\r\n");
		sb.append("				return;"    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("			else if(rows.length>1){"    +"\r\n");
		sb.append("				$.messager.alert('提示','只能选择一条记录！','warning');"    +"\r\n");
		sb.append("				return;"    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("			window.location.href='<%=basePath%>"+classNameUp+"Manage_to"+classNameUp+"Update.do?"+ CodeUtil.caseConversionInitial(table.getPrimaryKey(),false) +"='+rows[0]."+ CodeUtil.caseConversionInitial(table.getPrimaryKey(),false) +";"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		function Detail() {"    +"\r\n");
		sb.append("			var rows = $('#"+classNameUp+"').datagrid('getSelections');"    +"\r\n");
		sb.append("			if (rows.length < 1) {"    +"\r\n");
		sb.append("				$.messager.alert('提示','请选择一条记录！','warning');"    +"\r\n");
		sb.append("				return;"    +"\r\n");
		sb.append("			} else if (rows.length > 1) {"    +"\r\n");
		sb.append("				$.messager.alert('提示','只能选择一条记录！', 'warning');"    +"\r\n");
		sb.append("				return;"    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("			window.location.href = '<%=basePath%>"+classNameUp+"Manage_to"+classNameUp+"Detail.do?"+ CodeUtil.caseConversionInitial(table.getPrimaryKey(),false) +"='+rows[0]."+ CodeUtil.caseConversionInitial(table.getPrimaryKey(),false) +";"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		$(function() {"    +"\r\n");
		sb.append("			initTable();"    +"\r\n");
		sb.append("		});"    +"\r\n");
		sb.append("		$(window).resize(function(){"    +"\r\n");
		sb.append("			$('#"+classNameUp+"').datagrid('resize');"    +"\r\n");
		sb.append("			$('#"+classNameUp+"Panel').panel('resize');"    +"\r\n");
		sb.append("		});"    +"\r\n");
		sb.append("		function clearSelections(){"    +"\r\n");
		sb.append("			$('#"+classNameUp+"').datagrid('clearSelections');"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		function createColumnMenu(){"    +"\r\n");
		sb.append("			var tmenu = $('<div id=\"tmenu\" style=\"width:100px;\"></div>').appendTo('body');"    +"\r\n");
		sb.append("			var fields = $('#"+classNameUp+"').datagrid('getColumnFields');"    +"\r\n");
		sb.append("			for(var i=0; i<fields.length; i++){ "    +"\r\n");
		sb.append("				var opts=$('#"+classNameUp+"').datagrid('getColumnOption',fields[i]);"    +"\r\n");
		sb.append("				var muit = !opts.hidden?$('<div iconCls=\"icon-ok\"/>'):$('<div iconCls=\"icon-empty\"/>');"    +"\r\n");
		sb.append("				muit.attr('id', fields[i]);"    +"\r\n");
		sb.append("				muit.html(opts.title).appendTo(tmenu); "    +"\r\n");
		sb.append("			}"    +"\r\n");
		sb.append("			tmenu.menu({"    +"\r\n");
		sb.append("				onClick: function(item){"    +"\r\n");
		sb.append("					if (item.iconCls=='icon-ok'){"    +"\r\n");
		sb.append("						$('#"+classNameUp+"').datagrid('hideColumn', item.id);"    +"\r\n");
		sb.append("						tmenu.menu('setIcon', {"    +"\r\n");
		sb.append("							target: item.target,"    +"\r\n");
		sb.append("							iconCls: 'icon-empty'"    +"\r\n");
		sb.append("						});"    +"\r\n");
		sb.append("					} else {"    +"\r\n");
		sb.append("						$('#"+classNameUp+"').datagrid('showColumn', item.id);"    +"\r\n");
		sb.append("						tmenu.menu('setIcon', {"    +"\r\n");
		sb.append("							target: item.target,"    +"\r\n");
		sb.append("							iconCls: 'icon-ok'"    +"\r\n");
		sb.append("						});"    +"\r\n");
		sb.append("					}"    +"\r\n");
		sb.append("				}"    +"\r\n");
		sb.append("			});"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		</script>"    +"\r\n");
		sb.append("	</head>"    +"\r\n");
		sb.append("	<body>"    +"\r\n");
		sb.append("		<div id=\""+classNameUp+"ContextMenu\" class=\"easyui-menu\" style=\"width:120px;\">"    +"\r\n");
		sb.append("			<div data-options=\"iconCls:'icon-add'\" onclick=\"javascript:Add()\">新建</div>"    +"\r\n");
		sb.append("			<div data-options=\"iconCls:'icon-remove'\" onclick=\"javascript:Delete()\">删除</div>"    +"\r\n");
		sb.append("			<div data-options=\"iconCls:'icon-edit'\" onclick=\"javascript:Update()\">修改</div>"    +"\r\n");
		sb.append("			<div data-options=\"iconCls:'icon-search'\" onclick=\"javascript:Detail()\">查看</div>"    +"\r\n");
		sb.append("			<div data-options=\"iconCls:'icon-reload'\" onclick=\"javascript:initTable()\">刷新</div>"    +"\r\n");
		sb.append("		</div>"    +"\r\n");
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
		filePath +=InitCodeConfig.web_path+table.getUrlPath().replaceAll("\\.", "/")+"/";
		File dir = new File(filePath);
		if(!dir.exists())
			dir.mkdirs();
		File boFile = new File(filePath+"list.jsp");
		if(!boFile.exists())
			boFile.createNewFile();
		PrintWriter pw = new PrintWriter(boFile, "utf-8");
		pw.write(sb.toString());
		pw.flush();
		pw.close();
	}

}
