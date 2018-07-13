package org.vegetto.coding.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vegetto.coding.pdm.xml.MyColumn;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.file.WriteFileUtil;

public class JSUtil {
	public static Map<String,String> typedic = CodeDefaultConfig.typedic;
	public static Map<String,String> valdic = CodeDefaultConfig.valdic;

	public static String checkEditJs(MyTable table, String classNameUp, String formName, String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("			function senfe(o,a,b,c,d){" + "\r\n");
		sb.append("				var t=document.getElementById(o).getElementsByTagName(\"tr\");" + "\r\n");
		sb.append("				for(var i=0;i<t.length;i++){" + "\r\n");
		sb.append("					t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;" + "\r\n");
		sb.append("				}" + "\r\n");
		sb.append("			}" + "\r\n");
		sb.append("			$(document).ready(function(){" + "\r\n");
		sb.append("				senfe(\"" + classNameUp + "\",\"#f3f8fd\",\"#ffffff\",\"#ADADAD\",\"#f391a9\");" + "\r\n");
		sb.append("				$.formValidator.initConfig( {" + "\r\n");
		sb.append("					formID : \"" + formName + "\"," + "\r\n");
		sb.append("					theme : 'SolidBox'," + "\r\n");
		sb.append("					mode : 'AutoTip'," + "\r\n");
		sb.append("					inIframe : true" + "\r\n");
		sb.append("				});" + "\r\n");

		Map<String, MyColumn> columnMap = table.getColumnMap();// 获得字段信息
		Set<Map.Entry<String, MyColumn>> set = columnMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>) it.next();
			MyColumn column = entry.getValue();
			// 首先要是可编辑的字段
			if (!column.getEditStyle().contains("hidden")) {
				// 根据字段数据类型不同，验证也不同
				String pType = typedic.get(column.getPropertyType()).toString();
				String pName = CodeUtil.caseConversionInitial(column.getPropertyName(), false);
				boolean isEmpty = column.getEditStyle().contains("*") ? false : true;
				if (pType.equals("Date") || column.getPropertyCnName().contains("日期") || column.getPropertyCnName().contains("时间")) {// 日期类型
					sb.append("				$(\"#" + pName + "\")" + "\r\n");
					sb.append("					.formValidator({" + "\r\n");
					sb.append("						empty:" + (isEmpty ? "true" : "false") + "," + "\r\n");
					sb.append("						onShow:\"请选择" + column.getPropertyCnName() + "\"," + "\r\n");
					sb.append("						onFocus:\"请选择" + column.getPropertyCnName() + "\"," + "\r\n");
					sb.append("						onCorrect:\"您选择的" + column.getPropertyCnName() + "合法\"" + "\r\n");
					sb.append("					})" + "\r\n");
					sb.append("					.inputValidator({" + "\r\n");
					sb.append("						onError:\"" + column.getPropertyCnName() + "有误,请确认\"" + "\r\n");
					sb.append("					});" + "\r\n");

					sb.append("		" + "\r\n");
					// sb.append("				$(\"#"+pName+"\").formValidator({onFocus:\"请输入"+column.getPropertyCnName()+"\",onCorrect:\"\"}).inputValidator({"+(isEmpty?"":"min:1,")+"onError:\""+column.getPropertyCnName()+"有误,请确认\"});"
					// +"\r\n");
				} else if (pType.equals("Integer")) {// 整数类型
					sb.append("				$(\"#" + pName + "\")" + "\r\n");
					sb.append("					.formValidator({" + "\r\n");
					sb.append("						empty:" + (isEmpty ? "true" : "false") + "," + "\r\n");
					sb.append("						onShow:\"请输入" + column.getPropertyCnName() + "\"," + "\r\n");
					sb.append("						onFocus:\"请输入" + column.getPropertyCnName() + "\"," + "\r\n");
					sb.append("						onCorrect:\"您输入的" + column.getPropertyCnName() + "合法\"" + "\r\n");
					sb.append("					})" + "\r\n");
					sb.append("					.inputValidator({" + "\r\n");
					sb.append("						onError:\"" + column.getPropertyCnName() + "有误,请确认\"" + "\r\n");
					sb.append("					})" + "\r\n");
					sb.append("					.regexValidator({" + "\r\n");
					sb.append("						regExp:[\"intege\"]//intege1正整数 intege2负整数 " + "\r\n");
					sb.append("					});" + "\r\n");

					sb.append("		" + "\r\n");
				} else if (pType.equals("Double")) {// 小数类型
					sb.append("				$(\"#" + pName + "\")" + "\r\n");
					sb.append("					.formValidator({" + "\r\n");
					sb.append("						empty:" + (isEmpty ? "true" : "false") + "," + "\r\n");
					sb.append("						onShow:\"请输入" + column.getPropertyCnName() + "\"," + "\r\n");
					sb.append("						onFocus:\"请输入" + column.getPropertyCnName() + "\"," + "\r\n");
					sb.append("						onCorrect:\"您输入的" + column.getPropertyCnName() + "合法\"" + "\r\n");
					sb.append("					})" + "\r\n");
					sb.append("					.inputValidator({" + "\r\n");
					sb.append("						onError:\"" + column.getPropertyCnName() + "有误,请确认\"" + "\r\n");
					sb.append("					})" + "\r\n");
					sb.append("					.regexValidator({" + "\r\n");
					sb.append("						regExp:[\"num\"]//double1 小数后1为，decmal1 正浮点 " + "\r\n");
					sb.append("					});" + "\r\n");

					sb.append("		" + "\r\n");
					// sb.append("				$(\"#"+pName+"\").formValidator({empty:"+(isEmpty?"true":"false")+",onFocus:\"请输入"+column.getPropertyCnName()+"\",onCorrect:\"\"}).inputValidator({onError:\""+column.getPropertyCnName()+"有误,请确认\"}).regexValidator({regExp:\"^(?:[0-9][0-9]*(?:\\.[0-9]+)?|0\\.(?!0+$)[0-9]+)$\", onError:\""+column.getPropertyCnName()+"有误,请确认\"});"
					// +"\r\n");
				} else {// 字符串类型
					sb.append("				$(\"#" + pName + "\")" + "\r\n");
					sb.append("					.formValidator({" + "\r\n");
					sb.append("						empty:" + (isEmpty ? "true" : "false") + "," + "\r\n");
					sb.append("						onShow:\"请输入" + column.getPropertyCnName() + "\"," + "\r\n");
					sb.append("						onFocus:\"请输入" + column.getPropertyCnName() + "\"," + "\r\n");
					sb.append("						onCorrect:\"您输入的" + column.getPropertyCnName() + "合法\"" + "\r\n");
					sb.append("					})" + "\r\n");
					sb.append("					.inputValidator({" + "\r\n");
					sb.append("						" + (isEmpty ? "//" : "") + "min:1," + "\r\n");
					sb.append("						max:" + column.getFieldLength() + "," + "\r\n");
					sb.append("						onError:\"" + column.getPropertyCnName() + "有误,请确认\"" + "\r\n");
					sb.append("					});" + "\r\n");

					sb.append("		" + "\r\n");
					// sb.append("				$(\"#"+pName+"\").formValidator({onFocus:\"请输入"+column.getPropertyCnName()+"\",onCorrect:\"\"}).inputValidator({"+(isEmpty?"":"min:1,")+"max:"+column.getFieldLength()+",onError:\""+column.getPropertyCnName()+"有误,请确认\"});"
					// +"\r\n");
				}
			}
		}
		sb.append("		" + "\r\n");
		sb.append("			});" + "\r\n");
		sb.append("			$(window).resize(function(){" + "\r\n");
		sb.append("				$.formValidator.reloadAutoTip();" + "\r\n");
		sb.append("				$('#" + classNameUp + "Panel').panel('resize');" + "\r\n");
		sb.append("			});" + "\r\n");
		WriteFileUtil.writeFile(filePath + "/js/", formName + ".js", sb.toString());
		return sb.toString();
	}

	public static String getPageListJs(MyTable table, String classNameUp, List<MyColumn> searchColumn, String filePath) throws IOException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("		function fillsize(percent) {" + "\r\n");
		sb.append("			var bodyWidth = document.body.clientWidth;" + "\r\n");
		sb.append("			return (bodyWidth - 90) * percent;" + "\r\n");
		sb.append("		}" + "\r\n");
		sb.append("" + "\r\n");

		sb.append("		function initTable(){" + "\r\n");
		sb.append("			$('#" + classNameUp + "').datagrid({" + "\r\n");
		sb.append("				//title:'列表显示',//标题文字 " + "\r\n");
		sb.append("				iconCls:'icon-save',//一个css类，将提供一个背景图片作为标题图标 " + "\r\n");
		sb.append("				width:'100%',//fillsize(0.99)" + "\r\n");
		sb.append("				height:'auto'," + "\r\n");
		sb.append("				nowrap: false,//是否在一行显示数据 " + "\r\n");
		sb.append("				striped: true,//奇偶行使用不同背景色，默认为false " + "\r\n");
		sb.append("				collapsible:true," + "\r\n");
		sb.append("				url:basePath+'" + classNameUp + "Manage_getList.do',//从远程站点请求数据的URL " + "\r\n");
		sb.append("				//sortName: '" + CodeUtil.caseConversionInitial(table.getPrimaryKey(), false) + "',//可以排序的列 " + "\r\n");
		sb.append("				//sortOrder: 'desc',//定义列的排序顺序，只能用 'asc' 或 'desc' " + "\r\n");
		sb.append("				remoteSort: true,//定义是否从服务器给数据排序" + "\r\n");
		sb.append("				//singleSelect:true,//只允许选中一行 " + "\r\n");
		sb.append("				//checkOnSelect:false,//选中一行时同时选中checkbox " + "\r\n");
		sb.append("				//selectOnCheck:true,//选中checkbox时同时选中改行 " + "\r\n");
		sb.append("				fitColumns: true,//自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动 " + "\r\n");
		sb.append("				pageNumber:1,//当设置了 pagination 特性时，初始化页码 " + "\r\n");
		sb.append("				pageSize: 10,//当设置了 pagination 特性时，初始化页码尺寸，即每页显示的记录条数，默认为10" + "\r\n");
		sb.append("				pageList:[10,15,20,30,50,100],//当设置了pagination 特性时，初始化页面尺寸的选择列表，可以设置每页记录条数的列表 " + "\r\n");
		sb.append("				idField:'" + CodeUtil.caseConversionInitial(table.getPrimaryKey(), false) + "',//标识字段 " + "\r\n");
		sb.append("				frozenColumns:[[" + "\r\n");
		sb.append("					{field:'ck',checkbox:true}" + "\r\n");
		sb.append("				]]," + "\r\n");
		sb.append("				queryParams:{" + "\r\n");

		Map<String, MyColumn> columnMap = table.getColumnMap();//
		Set<Map.Entry<String, MyColumn>> set = columnMap.entrySet();
		Iterator it = set.iterator();

		List<MyColumn> showColumn = new ArrayList<MyColumn>();
		List<MyColumn> hiddenColumn = new ArrayList<MyColumn>();
		while (it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>) it.next();
			MyColumn column = entry.getValue();
			// ShowStyle 带有*的为查询条件
			if (column.getShowStyle().contains(InitCodeConfig.searchSign)) {
				searchColumn.add(column);
			}
			// ShowStyle 不是hidden的，都要在列表中显示
			if (!column.getShowStyle().contains("hidden")) {
				showColumn.add(column);
			} else {
				hiddenColumn.add(column);
			}
		}
		for (int i = 1; i <= searchColumn.size(); i++) {
			MyColumn column = searchColumn.get(i - 1);
			String fieldName = CodeUtil.caseConversionInitial(column.getFieldName(), false);
			String douhao = i == searchColumn.size() ? "" : ",";//
			sb.append("						" + fieldName + ":document.getElementById(\"" + fieldName + "\").value" + douhao + "" + "\r\n");
		}

		sb.append("				},	" + "\r\n");
		sb.append("				columns:[[				" + "\r\n");

		sb.append("					{field:'" + CodeUtil.caseConversionInitial(table.getPrimaryKey(), false) + "',title:'ID',width:fillsize(33),align:'center',hidden:true,sortable:true}," + "\r\n");
		// 输出显示字段
		for (int i = 1; i <= showColumn.size(); i++) {
			MyColumn column = showColumn.get(i - 1);
			if (table.getPrimaryKey().equals(column.getPropertyName())) {
				continue;
			}
			String fieldName = CodeUtil.caseConversionInitial(column.getFieldName(), false);
			String douhao = ",";
			if (hiddenColumn.size() == 0)// 如果有要输出的隐藏字段，则补充一个逗号
				douhao = i == showColumn.size() ? "" : ",";//
			sb.append("					{field:'" + fieldName + "',title:'" + column.getPropertyCnName() + "',width:fillsize(1),align:'center',hidden:false,sortable:true}" + douhao + "" + "\r\n");
		}

		sb.append("						" + "\r\n");

		// 输出不显示字段
		for (int i = 1; i <= hiddenColumn.size(); i++) {
			MyColumn column = hiddenColumn.get(i - 1);
			if (table.getPrimaryKey().equals(column.getPropertyName())) {
				continue;
			}
			String fieldName = CodeUtil.caseConversionInitial(column.getFieldName(), false);
			String douhao = i == hiddenColumn.size() ? "" : ",";//
			sb.append("					{field:'" + fieldName + "',title:'" + column.getPropertyCnName() + "',width:fillsize(1),align:'center',hidden:true}" + douhao + "" + "\r\n");
		}
		sb.append("						" + "\r\n");

		sb.append("				]]," + "\r\n");

		sb.append("				toolbar:[" + "\r\n");
		sb.append("					{" + "\r\n");
		sb.append("						text:'新建'," + "\r\n");
		sb.append("						disabled:false,//加上disabled:true属性就能使该按钮变灰不可用 " + "\r\n");
		sb.append("						iconCls:'icon-add'," + "\r\n");
		sb.append("						handler:Add" + "\r\n");
		sb.append("					},'-',{" + "\r\n");
		sb.append("						text:'删除'," + "\r\n");
		sb.append("						disabled:false," + "\r\n");
		sb.append("						iconCls:'icon-remove'," + "\r\n");
		sb.append("						handler:Delete" + "\r\n");
		sb.append("					},'-',{" + "\r\n");
		sb.append("						text:'修改'," + "\r\n");
		sb.append("						disabled:false," + "\r\n");
		sb.append("						iconCls:'icon-edit'," + "\r\n");
		sb.append("						handler:Update" + "\r\n");
		sb.append("					},'-',{" + "\r\n");
		sb.append("						text:'查看'," + "\r\n");
		sb.append("						disabled:false," + "\r\n");
		sb.append("						iconCls:'icon-search'," + "\r\n");
		sb.append("						handler:Detail" + "\r\n");
		sb.append("					}" + "\r\n");
		sb.append("				]," + "\r\n");
		sb.append("				onHeaderContextMenu: function(e, field){" + "\r\n");
		sb.append("					e.preventDefault();" + "\r\n");
		sb.append("					if (!$('#tmenu').length){" + "\r\n");
		sb.append("						createColumnMenu();" + "\r\n");
		sb.append("					}" + "\r\n");
		sb.append("					$('#tmenu').menu('show', {" + "\r\n");
		sb.append("						left:e.pageX," + "\r\n");
		sb.append("						top:e.pageY" + "\r\n");
		sb.append("					});" + "\r\n");
		sb.append("				}," + "\r\n");
		sb.append("				onRowContextMenu: function(e,row){" + "\r\n");
		sb.append("					e.preventDefault();" + "\r\n");
		sb.append("					$('#" + classNameUp + "ContextMenu').menu('show', {" + "\r\n");
		sb.append("						left: e.pageX," + "\r\n");
		sb.append("						top: e.pageY" + "\r\n");
		sb.append("					});" + "\r\n");
		sb.append("				}," + "\r\n");
		sb.append("				pagination : true,//在 datagrid 的底部显示分页栏 " + "\r\n");
		sb.append("				rownumbers : true,//显示行号 " + "\r\n");
		sb.append("				loadMsg : '查询中，请稍后……'//当从远程站点加载数据时，显示的提示信息 " + "\r\n");
		sb.append("			});" + "\r\n");
		sb.append("			clearSelections();//取消已经选择的多选框" + "\r\n");
		sb.append("			var p = $('#" + classNameUp + "').datagrid('getPager');" + "\r\n");
		sb.append("			$(p).pagination( {" + "\r\n");
		sb.append("				onBeforeRefresh : function() {" + "\r\n");
		sb.append("					//alert('before refresh');" + "\r\n");
		sb.append("					//initTable();" + "\r\n");
		sb.append("				}," + "\r\n");
		sb.append("				beforePageText : '第',//页数文本框前显示的汉字 " + "\r\n");
		sb.append("				afterPageText : '共{pages}页'," + "\r\n");
		sb.append("				displayMsg : '共{total}条记录，本页显示第{from}-{to}条'" + "\r\n");
		sb.append("			});" + "\r\n");
		sb.append("		}" + "\r\n");

		sb.append("		function Add(){" + "\r\n");
		sb.append("			window.location.href=basePath+'" + table.getUrlPath().replaceAll("\\.", "/") + "/add.jsp';" + "\r\n");
		sb.append("		}" + "\r\n");

		sb.append("		function Delete(){" + "\r\n");
		sb.append("			var ids =[];" + "\r\n");
		sb.append("			var rows = $('#" + classNameUp + "').datagrid('getSelections');" + "\r\n");
		sb.append("			if(rows.length<1){" + "\r\n");
		sb.append("				$.messager.alert('提示','请选择一条记录！','warning');" + "\r\n");
		sb.append("				return;" + "\r\n");
		sb.append("			}" + "\r\n");
		sb.append("			for(var i=0;i<rows.length;i++){" + "\r\n");
		sb.append("				ids.push(rows[i]." + CodeUtil.caseConversionInitial(table.getPrimaryKey(), false) + ");" + "\r\n");
		sb.append("			}" + "\r\n");
		sb.append("			$.messager.confirm('确认','确认删除选中的记录？',function(result){  " + "\r\n");
		sb.append("				if(result){ " + "\r\n");
		sb.append("					$.ajax({ " + "\r\n");
		sb.append("						type : \"POST\", " + "\r\n");
		sb.append("						url:basePath+'" + classNameUp + "Manage_delete" + classNameUp + ".do', " + "\r\n");
		sb.append("						traditional: true,//在struts2下该属性必须有 " + "\r\n");
		sb.append("						data:{ids:ids}, " + "\r\n");
		sb.append("						success:function(result){ " + "\r\n");
		sb.append("							$.messager.alert('提示',result); " + "\r\n");
		sb.append("							initTable(); " + "\r\n");
		sb.append("						}" + "\r\n");
		sb.append("					});" + "\r\n");
		sb.append("				}" + "\r\n");
		sb.append("			})" + "\r\n");
		sb.append("		}" + "\r\n");

		sb.append("		function Update(){" + "\r\n");
		sb.append("			var rows = $('#" + classNameUp + "').datagrid('getSelections');" + "\r\n");
		sb.append("			if(rows.length<1){" + "\r\n");
		sb.append("				$.messager.alert('提示','请选择一条记录！','warning');" + "\r\n");
		sb.append("				return;" + "\r\n");
		sb.append("			}" + "\r\n");
		sb.append("			else if(rows.length>1){" + "\r\n");
		sb.append("				$.messager.alert('提示','只能选择一条记录！','warning');" + "\r\n");
		sb.append("				return;" + "\r\n");
		sb.append("			}" + "\r\n");
		sb.append("			window.location.href=basePath+'" + classNameUp + "Manage_to" + classNameUp + "Update.do?" + CodeUtil.caseConversionInitial(table.getPrimaryKey(), false) + "='+rows[0]." + CodeUtil.caseConversionInitial(table.getPrimaryKey(), false) + ";" + "\r\n");
		sb.append("		}" + "\r\n");

		sb.append("		function Detail() {" + "\r\n");
		sb.append("			var rows = $('#" + classNameUp + "').datagrid('getSelections');" + "\r\n");
		sb.append("			if (rows.length < 1) {" + "\r\n");
		sb.append("				$.messager.alert('提示','请选择一条记录！','warning');" + "\r\n");
		sb.append("				return;" + "\r\n");
		sb.append("			} else if (rows.length > 1) {" + "\r\n");
		sb.append("				$.messager.alert('提示','只能选择一条记录！', 'warning');" + "\r\n");
		sb.append("				return;" + "\r\n");
		sb.append("			}" + "\r\n");
		sb.append("			window.location.href = basePath+'" + classNameUp + "Manage_to" + classNameUp + "Detail.do?" + CodeUtil.caseConversionInitial(table.getPrimaryKey(), false) + "='+rows[0]." + CodeUtil.caseConversionInitial(table.getPrimaryKey(), false) + ";" + "\r\n");
		sb.append("		}" + "\r\n");

		sb.append("		$(function() {" + "\r\n");
		sb.append("			initTable();" + "\r\n");
		sb.append("		});" + "\r\n");

		sb.append("		$(window).resize(function(){" + "\r\n");
		sb.append("			$('#" + classNameUp + "').datagrid('resize');" + "\r\n");
		sb.append("			$('#" + classNameUp + "Panel').panel('resize');" + "\r\n");
		sb.append("		});" + "\r\n");

		sb.append("		function clearSelections(){" + "\r\n");
		sb.append("			$('#" + classNameUp + "').datagrid('clearSelections');" + "\r\n");
		sb.append("		}" + "\r\n");
		// sb.append("		function createColumnMenu(){" +"\r\n");
		// sb.append("			var tmenu = $('<div id=\"tmenu\" style=\"width:100px;\"></div>').appendTo('body');"
		// +"\r\n");
		// sb.append("			var fields = $('#"+classNameUp+"').datagrid('getColumnFields');"
		// +"\r\n");
		// sb.append("			for(var i=0; i<fields.length; i++){ " +"\r\n");
		// sb.append("				var opts=$('#"+classNameUp+"').datagrid('getColumnOption',fields[i]);"
		// +"\r\n");
		// sb.append("				var muit = !opts.hidden?$('<div iconCls=\"icon-ok\"/>'):$('<div iconCls=\"icon-empty\"/>');"
		// +"\r\n");
		// sb.append("				muit.attr('id', fields[i]);" +"\r\n");
		// sb.append("				muit.html(opts.title).appendTo(tmenu); " +"\r\n");
		// sb.append("			}" +"\r\n");
		// sb.append("			tmenu.menu({" +"\r\n");
		// sb.append("				onClick: function(item){" +"\r\n");
		// sb.append("					if (item.iconCls=='icon-ok'){" +"\r\n");
		// sb.append("						$('#"+classNameUp+"').datagrid('hideColumn', item.id);"
		// +"\r\n");
		// sb.append("						tmenu.menu('setIcon', {" +"\r\n");
		// sb.append("							target: item.target," +"\r\n");
		// sb.append("							iconCls: 'icon-empty'" +"\r\n");
		// sb.append("						});" +"\r\n");
		// sb.append("					} else {" +"\r\n");
		// sb.append("						$('#"+classNameUp+"').datagrid('showColumn', item.id);"
		// +"\r\n");
		// sb.append("						tmenu.menu('setIcon', {" +"\r\n");
		// sb.append("							target: item.target," +"\r\n");
		// sb.append("							iconCls: 'icon-ok'" +"\r\n");
		// sb.append("						});" +"\r\n");
		// sb.append("					}" +"\r\n");
		// sb.append("				}" +"\r\n");
		// sb.append("			});" +"\r\n");
		// sb.append("		}" +"\r\n");
		WriteFileUtil.writeFile(filePath + "/js/", "list.js", sb.toString());
		return sb.toString();
	}

	public static String toDateJs() {
		StringBuffer sb = new StringBuffer();
		sb.append("		<script type=\"text/javascript\" src=\"<%= basePath %>plugins/My97DatePicker/WdatePicker.js\"></script>" + "\r\n");

		return sb.toString();
	}

}
