<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String pageUrl = request.getRequestURL().toString();
	String listpageUrl = pageUrl.substring(0,pageUrl.lastIndexOf("/")+1)+"list.jsp";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<title>添加桌面项目</title>
		<jsp:include page="/common/include/jquery.jsp" flush="true"/>
		<jsp:include page="/common/include/easyui.jsp" flush="true"/>
		<jsp:include page="/common/include/dialog.jsp" flush="true"/>
		<jsp:include page="/common/include/formValidator.jsp" flush="true"/>
		<script type="text/javascript" src="<%= basePath %>plugins/My97DatePicker/WdatePicker.js"></script>
		
		
		<script language="javascript">
			function senfe(o,a,b,c,d){
				var t=document.getElementById(o).getElementsByTagName("tr");
				for(var i=0;i<t.length;i++){
					t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
				}
			}
			$(document).ready(function(){
				senfe("DeskItem","#f3f8fd","#ffffff","#ADADAD","#f391a9");
				$.formValidator.initConfig( {
					formID : "add",
					theme : 'SolidBox',
					mode : 'AutoTip',
					inIframe : true
				});
				$("#code")
					.formValidator({
						empty:true,
						onShow:"请输入编号",
						onFocus:"请输入编号",
						onCorrect:"您输入的编号合法"
					})
					.inputValidator({
						//min:1,
						max:50,
						onError:"编号有误,请确认"
					});
		
				$("#name")
					.formValidator({
						empty:true,
						onShow:"请输入名称",
						onFocus:"请输入名称",
						onCorrect:"您输入的名称合法"
					})
					.inputValidator({
						//min:1,
						max:50,
						onError:"名称有误,请确认"
					});
		
				$("#show_rows")
					.formValidator({
						empty:true,
						onShow:"请输入显示行数",
						onFocus:"请输入显示行数",
						onCorrect:"您输入的显示行数合法"
					})
					.inputValidator({
						onError:"显示行数有误,请确认"
					})
					.regexValidator({
						regExp:["intege"]//intege1正整数 intege2负整数 
					});
		
				$("#sortnum")
					.formValidator({
						empty:true,
						onShow:"请输入排序",
						onFocus:"请输入排序",
						onCorrect:"您输入的排序合法"
					})
					.inputValidator({
						onError:"排序有误,请确认"
					})
					.regexValidator({
						regExp:["intege"]//intege1正整数 intege2负整数 
					});
		
				$("#url")
					.formValidator({
						empty:true,
						onShow:"请输入访问地址",
						onFocus:"请输入访问地址",
						onCorrect:"您输入的访问地址合法"
					})
					.inputValidator({
						//min:1,
						max:0,
						onError:"访问地址有误,请确认"
					});
		
		
			});
			$(window).resize(function(){
				$.formValidator.reloadAutoTip();
				$('#DeskItemPanel').panel('resize');
			});
		</script>
		
	</head>
	<body>
		<div id="DeskItemPanel" class="easyui-panel" style="width:'100%';padding:6px;" data-options="title:'新建桌面项目',iconCls:'icon-add',collapsible:true,minimizable:false,maximizable:false,closable:false">
		<form id="add" name="add" action="<%=basePath%>DeskItemManage_addDeskItem.do" method="post">
			<table id="DeskItem" cellspacing="1" cellpadding="0" border="0" bgcolor="#95a5d2" width="100%">
				<tr height="30" align="left" valign="middle">
					<td bgcolor="#f3f8fd" align="center" width="30%"><strong>编号</strong></td>
					<td bgcolor="#f3f8fd" align="left" width="70%">
						<input type="text" id="code" name="code" value=""/>
					</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td bgcolor="#f3f8fd" align="center" width="30%"><strong>名称</strong></td>
					<td bgcolor="#f3f8fd" align="left" width="70%">
						<input type="text" id="name" name="name" value=""/>
					</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td bgcolor="#f3f8fd" align="center" width="30%"><strong>显示行数</strong></td>
					<td bgcolor="#f3f8fd" align="left" width="70%">
						<input type="text" id="show_rows" name="show_rows" value=""/>
					</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td bgcolor="#f3f8fd" align="center" width="30%"><strong>排序</strong></td>
					<td bgcolor="#f3f8fd" align="left" width="70%">
						<input type="text" id="sortnum" name="sortnum" value=""/>
					</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td bgcolor="#f3f8fd" align="center" width="30%"><strong>访问地址</strong></td>
					<td bgcolor="#f3f8fd" align="left" width="70%">
						<input type="text" id="url" name="url" value=""/>
					</td>
				</tr>
				<tr height="30" align="left" valign="middle">
					<td bgcolor="#f3f8fd" colspan="2" align="center" width="100%">
						<a href="javascript:void(0)" onclick="$('#add').submit()" class="easyui-linkbutton" id="prsBtn" plain="true" icon="icon-save">提交</a>
						<a href="javascript:void(0)" onclick="javascript:window.location.href='<%=listpageUrl %>';" class="easyui-linkbutton" plain="true" icon="icon-back">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
