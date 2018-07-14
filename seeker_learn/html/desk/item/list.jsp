<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dhcc.common.util.SysConfig"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <title>桌面项目列表</title>
	    <base href="<%=basePath%>">
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<jsp:include page="/common/include/jquery.jsp" flush="true"/>
		<jsp:include page="/common/include/easyui.jsp" flush="true"/>
		<jsp:include page="/common/include/dialog.jsp" flush="true"/>
		<script type="text/javascript" src="<%= basePath %>plugins/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		function fillsize(percent) {
			var bodyWidth = document.body.clientWidth;
			return (bodyWidth - 90) * percent;
		}

		function initTable(){
			$('#DeskItem').datagrid({
                title:'桌面项目列表',
				width:'100%',//fillsize(0.99)
				height:'auto',
				url:'<%=basePath%>DeskItemManage_getList.do',//从远程站点请求数据的URL 
                singleSelect:true,//只允许选中一行 
                checkOnSelect:true,//选中一行时同时选中checkbox 
				pageSize: <%=SysConfig.PAGESIZE%>,//当设置了 pagination 特性时，初始化页码尺寸，即每页显示的记录条数，默认为10
				pageList:<%=SysConfig.PAGELIST%>,//当设置了pagination 特性时，初始化页面尺寸的选择列表，可以设置每页记录条数的列表 
				idField:'id',//标识字段 
				frozenColumns:[[
					{field:'ck',checkbox:true}
				]],
				queryParams:{
				},	
				columns:[[				
					{field:'id',title:'ID',width:fillsize(1),align:'center',hidden:true,sortable:true},
					{field:'code',title:'编号',width:fillsize(1),align:'center',hidden:true,sortable:true},
					{field:'name',title:'名称',width:fillsize(1),align:'center',hidden:false,sortable:true},
					{field:'show_rows',title:'显示行数',width:fillsize(1),align:'center',hidden:false,sortable:true},
					{field:'sortnum',title:'排序',width:fillsize(1),align:'center',hidden:false,sortable:true},
					{field:'url',title:'访问地址',width:fillsize(1),align:'center',hidden:false,sortable:true}
				]],
				toolbar:[
					{
                        text:'选择',
                        disabled:false,
                        iconCls:'icon-add',
                        handler:addObj
                    },'-',
					{
                        text:'关闭',
                        disabled:false,
                        iconCls:'icon-back',
                        handler:function(){window.close();}
                    }
				]
			});
			clearSelections();
		}
		function Add(){
			window.location.href='<%=basePath%>desk/item/add.jsp';
		}
        function addObj(){
        	var rows = $('#DeskItem').datagrid('getSelections');
        	if (rows.length < 1) {
			    alert('请选择一条记录！');
				return;
			} else if (rows.length > 1) {
				alert('只能选择一条记录！');
				return;
			}
            
        	window.opener.addDeskDiv(rows[0].id,rows[0].name,rows[0].code,rows[0].url,rows[0].show_rows);
 			window.close();
        }
		function Detail() {
			var rows = $('#DeskItem').datagrid('getSelections');
			if (rows.length < 1) {
			    alert('请选择一条记录！');
				return;
			} else if (rows.length > 1) {
				alert('只能选择一条记录！');
				return;
			}
			window.location.href = '<%=basePath%>DeskItemManage_toDeskItemDetail.do?id='+rows[0].id;
		}
		$(function() {
			initTable();
		});
		$(window).resize(function(){
			$('#DeskItem').datagrid('resize');
			$('#DeskItemPanel').panel('resize');
		});
		function clearSelections(){
			$('#DeskItem').datagrid('clearSelections');
		}
		</script>
	</head>
	<body class="main">
        <!-- 页面容器 start -->
        <div id="mContainer">
            <table cellpadding="0" cellspacing="0" width="100%">

                <!-- 页面标题、提交按钮 start -->
                <tr id="path">
                    <td>
                        <div class="path">
                            <div class="menublock" id="menublock">
                                <div class="nav">
                                    <a href="javascript:" class="hover" style="left: 0px; z-index: 99">添加桌面选项</a>
                                    <div class="clear"></div>
                                </div>
                            </div>
                            <div class="btnblock" id="btnblock" style="top: -5px">	
                                <a href="javascript:void(0)" onclick="javascript:window.close();" class="easyui-linkbutton" plain="true" icon="icon-back">关闭</a>
                        	</div>
                        </div>
                    </td>
                </tr>
                <!-- 页面标题、提交按钮 end -->
                <!-- 页面主体内容 start -->
                <tr>
                    <td class="content" id="tdcontent">
                        <div class="form" id="formblock">
		                  <table id="DeskItem"></table>
                        <!-- 表单 end -->
						</div>
					</td>
				</tr>
				<!-- 页面主体内容 end -->
			</table>
		</div>
		<!-- 页面容器 end -->
</html>
