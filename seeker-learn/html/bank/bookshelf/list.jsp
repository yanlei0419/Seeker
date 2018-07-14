<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page = "/common/include/doctype.jsp" flush = "true"/>
<html>  
	<head>
	    <title>客户管理列表</title>
	    <base href="<%=basePath%>">
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery-ui.custom.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.core.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.widget.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.sortable.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.mouse.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>plugins/jquery/jquery.json-2.4.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/jquery.blockUI.js"></script>
        
        <script type="text/javascript" src="<%=basePath%>bank/bookshelf/js/base.js?ve=1"></script>
        <link href="<%=basePath%>bank/bookshelf/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
        var basePath="<%=basePath %>";
        var curPage=1,rows=12;

		$(function() {
			 initData(1);
			 initSorTable();
			// createPagesLi(7,5);
		});
		</script>
        <style type="text/css">
        body{padding: 0px ;margin: 0px;}
        #ve{padding: 5px; text-align: center; margin: 5px 0 0 0 ;height: 100%;min-height: 430px;}
        #veUl{margin: auto ; width: 1060px; padding: 25px; min-height: 410px;}
        #veUl>li{cursor:pointer;  border: 3px solid #fff; display: inline-block; width: 130px; height: 170px; overflow:hidden; text-align: center;vertical-align: middle; padding:0px; margin:10px; position: relative; }
        .saLi{ border: 0px solid #fff; display: inline-block;background-color:#ccc; width: 130px; height: 170px; overflow:hidden; text-align: center;vertical-align: middle; padding:0px; margin:10px; position: relative; }
        #veUl>li div{ color: #000;}
        #veUl>li div.book-bottom{ position: absolute;bottom: 0px; left: 0px; z-index: 3; background: #ccc; width: 110px; height: 80px;  padding:10px  10px  0 10px;text-align: center; -moz-opacity: 0.7; opacity:.70; filter: alpha(opacity=70); ;font-weight: bold;}
        #veUl>li img{position: absolute; width: 110px;height:150px; z-index: 2; top: 0px; left: 0px;margin: 10px;} 
        .book-border{border: 1px solid #ccc;}
        .book-border-blue{border: 1px solid #95B8E7;}
        #myMain{width: 100%;height: 100%;margin: 0px; padding: 0px;}
        #page{ line-height: 50px; vertical-align: middle;text-align: center; margin: 5px 0px;padding: 0px; display: inline-block; list-style-type:none;width: 100%; max-width: 100%;  }
        #page>li.pageLi{ cursor:pointer; padding: 0 10px;margin:0 3px ;line-height:32px; height: 30px; border: 1px #ccc solid;display: inline-block; font-size: 12px;}
        #page>li.selLi{  padding: 0 10px;margin:0 3px ;line-height:32px; height: 30px; border: 1px #ff5050 solid;display: inline-block; font-size: 12px;}
        #page>li.pageLi:hover{border: 1px #ff5050 solid;}
       #veUl .cTitle{font-size: 12px;padding: 6px 0px;text-align: center;}
       #veUl .cName{ font-size: 12px;padding: 6px 0px;;text-align: center;}
        </style>
	</head>
	<body> 
		<div id="BankCustomerPanel" class="easyui-panel" style="width:'100%';padding:6px;" data-options="title:'条件查询',iconCls:'icon-search',collapsible:true,minimizable:false,maximizable:false,closable:false">
        客户编号：<input type="text"  id="customer_no"  name="customer_no" class="clear">
        客户名称：<input type="text"  id="name"  name="name" class="clear">
<%--        身份证号：<input type="text"  id="idno"  name="idno" class="clear">--%>
			<a href="javascript:void(0)" onclick="javascript:initData(1)" class="easyui-linkbutton" plain="true" icon="icon-search">查询</a>
			<a href="javascript:void(0)" onclick="javascript:Clear()" class="easyui-linkbutton" plain="true" icon="icon-clear">清除</a>
		</div>
        <div id="ve" class="book-border ">
            <ul  id="page" class="book-border-blue ">
            &nbsp;
            </ul>
            <ul id="veUl" class=" shujiabg li-border ">
            </ul>
        </div>
	</body>
    <script type="text/javascript">
    </script>
</html>
