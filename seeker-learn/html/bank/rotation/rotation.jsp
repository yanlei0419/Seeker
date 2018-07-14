<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    String imgUrl=request.getParameter("imgUrl");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>客户资料单张展示</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<style type="text/css">
body{background-color: #999;padding: 0px; margin: 0px;}
<%--#container{min-width:1040px; text-align: center;padding: 5px;}--%>
.mySa{min-width:1040px; text-align: center;padding: 5px;}
.mySa1{min-width:1040px; text-align: center;padding: 5px;margin: 70px 0px;}
.mySa2{text-align: left;padding: 0px 30px;min-width:1040px;margin: 70px 0px;}
#container .imgVe { cursor:pointer; }
#veMenu{line-height:50px; text-align: center;width:100%; margin: 0px auto;position: fixed; top:0px; background-color: #000; -moz-opacity: 0.5; opacity:.50; filter: alpha(opacity=50);clear: both;visibility:visible;; z-index: 999 }
.border{border: 2px #ff5050 solid;}
.border2{border: 2px #fff solid;}
#rotate{list-style-type: none;display: block;position: absolute;}
#rotate li{list-style-type: none;cursor: move;}
</style>

        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery-ui.custom.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.core.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.widget.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.sortable.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.mouse.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>plugins/jquery/jquery.json-2.4.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/jquery.blockUI.js"></script>
<%--<script type="text/javascript" src="<%=basePath %>/plugins/jquery/jquery-1.8.0.min.js"></script>--%>
<script type="text/javascript" src="<%=basePath %>/bank/rotation/js/base.js?ve="></script>
<script type="text/javascript" >


</script>

</head>
<body>
<div id="veMenu" > 
    <input  type="button"  onclick="javascript:doSaber(-90)" value="左旋转"/>
    <input  type="button" onclick="javascript:doSaber(90)" value="右旋转"/>
    <input  type="button"  onclick="javascript:zoom(0.1)" value="放大"/>
    <input  type="button"  onclick="javascript:zoom(-0.1)" value="缩小"/>
    <input  type="button"  onclick="javascript:zoom(9999)" value="重置"/>
</div>
<div id="container" class="mySa1">
        <ul id="rotate">
            <li>
            <img id="zoomImg" src="<%=imgUrl %>" />
            </li>
        </ul>
</div>
</body> 
<script type="text/javascript">
    var count=0;var zoomParam=1;//计数
    var width=0,height=0;
    $(window).load(function(){
    	width=$("#zoomImg").width();
        height=$("#zoomImg").height();
        initWindow();
        initVegetto();
    });
</script>
</html>