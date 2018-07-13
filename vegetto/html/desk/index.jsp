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
        <jsp:include page="/common/include/jquery.jsp" flush="true" />
        <jsp:include page="/common/include/easyui.jsp" flush="true" />
        <script type="text/javascript" >
        function getDate(){
        	$.ajax({ 
				type : "POST", 
				url:'<%=basePath%>PersonalTemplateManage_getPersonalTemplate.do', 
                dataType:'text',
				traditional: true,//在struts2下该属性必须有 
                data:{tempid:$('#templateid').val()},
				success:function(result){ 
                	 selectedRadio(result);
				}
			});
        }
        function selectedRadio(id){
        	$("#"+id).attr("checked","true");
        	changeLoc(id);
        }
        function changeLoc(id){
            var  url="<%=basePath%>desk/template/temp"+id+".jsp";
            $("#tempIframe").attr("src",url);
        }
        $(function() {
             getDate();
        });
        </script>
    </head>
    <body style="background-color: #f3f8fd">
    <div id="DeskTemplatePanel" class="easyui-panel" style="width:'100%';padding:6px; text-align: center; line-height: 40px;" data-options="title:'模版',iconCls:'icon-search',minimizable:false,maximizable:false,closable:false">
          <input type="radio"  name="1"  id="1"  value="1"  checked="checked"  onclick="javascript:changeLoc(this.id)" />模版1
          <input type="radio"  name="1"  id="2"  value="2"   onclick="javascript:changeLoc(this.id)" />模版2
          <input type="radio"  name="1"  id="3"  value="3"   onclick="javascript:changeLoc(this.id)" />模版3
          <input type="radio"  name="1"  id="4"  value="4"   onclick="javascript:changeLoc(this.id)" />模版4
          <input type="radio"  name="1"  id="5"  value="5"   onclick="javascript:changeLoc(this.id)" />模版5
    </div>
    <div class="easyui-layout" fit="true">
        <div data-options="region:'center',split:true" id="title" title="模版显示">
                <iframe name="tempIframe" id="tempIframe" src="<%=basePath%>/desk/template/temp1.jsp" frameborder="0"   style="background-color: ( 219, 238, 253); scroll-x: none; scroll-y: none;"    scrolling="auto" width="100%" height="390px"></iframe>
        </div>
    </div>    
    </body>
</html>
