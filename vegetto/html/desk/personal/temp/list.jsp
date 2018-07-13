<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dhcc.desk.personal.po.PersonalTemplatePo"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
	<head>
	    <title></title>
	    <base href="<%=basePath%>">
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<jsp:include page="/common/include/jquery.jsp" flush="true"/>
		<script type="text/javascript">
        function doLocation(){
            window.location.href='<%=basePath%>/desk/template/temp${personalTemp.templateid}.jsp';
         }
        doLocation();
		</script>
	</head>
	<body>
	</body>
</html>
