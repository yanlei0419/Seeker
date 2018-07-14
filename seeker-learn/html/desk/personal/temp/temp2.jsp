<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base href="<%=basePath%>" />
        <title>我的办公桌设置</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <link href="<%=basePath%>index/Css/main.css" type="text/css" rel="stylesheet" />
       
		<jsp:include page="/common/include/jquery.jsp" flush="true"/>
		<jsp:include page = "/common/include/easyui.jsp" flush = "true"/>
        <jsp:include page = "/common/include/dialog.jsp" flush = "true"/>
        <script type="text/javascript" src="<%=basePath%>desk/personal/temp/js/mytemp.js"></script>
        <script type="text/javascript" >
        </script>
        <style type="text/css">
        .sortable1 li{margin: 5px 3px 5px 3px;}
        #le td{vertical-align: top;}
        #TXL>li{float: left; margin: 5px 6px 5px 6px;width: 155px;}
        .txldiv{line-height: 30px;  text-align: center;}
    </style>
    </head>
    <body style="background-color: #f3f8fd">
    <table id="le" border="0" width="100%">
        <tr>
            <td width="50%">
                <ul id="ve1" class="sortable1">
                </ul>
            </td>
            <td width="50%">
                <ul  id="ve2" class="sortable1">
                </ul>
            </td>
        </tr>
    </table>
    </body>
</html>
