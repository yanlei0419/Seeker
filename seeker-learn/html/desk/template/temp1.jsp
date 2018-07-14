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
        <link href="<%=basePath%>/common/css/common.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery-ui.custom.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.core.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.widget.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.sortable.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/sortable/jquery.ui.mouse.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>plugins/jquery/jquery.json-2.4.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>desk/js/jquery.blockUI.js"></script>
		<jsp:include page = "/common/include/easyui.jsp" flush = "true"/>
        <jsp:include page = "/common/include/dialog.jsp" flush = "true"/>
        <script type="text/javascript" src="<%=basePath%>desk/template/js/temp.js"></script>
        <link href="<%=basePath%>desk/template/css/temp.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" >
        var defaultLocationId="ve1";
        var basePath='<%=basePath%>';
      //  计数器
        var count=0;
        $(function() {
            $( ".sortable1" ).sortable({
                tolerance: 'intersect',
                connectWith: '.sortable1',
                items: 'li:not(.lastli)',
                stop: function(event, ui) { 
                    var id=ui.item.parent('.sortable1').attr("id");
                    ui.item.find('.locationId').attr("value",id);
                },
                placeholder: 'sort_placeholder'//移动开始后原位置css样式
            });
            $( ".sortable1" ).disableSelection();
            getDate();
            $('#myTemplate').block({css: { 
                    border: 'none', 
                    padding: '15px', 
                    backgroundColor: '#000', 
                    '-webkit-border-radius': '10px', 
                    '-moz-border-radius': '10px', 
                    opacity: .5, 
                    color: '#fff' 
                     },message:"正在努力的加载中……"});
        });

        function getDate(){
        	$.ajax({ 
				type : "POST", 
				url:'<%=basePath%>PersonalTemplateManage_getPersonalTemplateOrDeskTemplate.do', 
				traditional: true,//在struts2下该属性必须有 
                dataType:'text',
                data:{tempid:$('#templateid').val()},
				success:function(result){ 
                	clearLi();
					build(result);
					$('#myTemplate').unblock();
				}
			});
        }

        </script>
        <style type="text/css">
   
    </style>
    </head>
    <body style="background-color: #f3f8fd">
    <div id='myTemplate' style="height: 100%" >
    <form id="add"  action="<%=basePath%>PersonalTemplateManage_addPersonalTemplate.do" method="post">
    <input type="hidden"  name="templateid"   id="templateid" value="1"/>
    <table id="le" border="0" width="100%">
        <tr>
            <td width="60%">
                <ul id="ve1" class="sortable1">
                    <li class="lastli"  onclick='selDeskItem("ve1")' >此处可以添加菜单</li>
                </ul>
            </td>
            <td width="40%">
                <ul  id="ve2" class="sortable1">
                    <li class="lastli" onclick='selDeskItem("ve2")' >此处可以添加菜单</li>
                </ul>
            </td>
        </tr>
    </table>
        <div style="margin: 5;text-align: center;line-height: 40px;">
            <a href="javascript:void(0)" onclick="$('#add').submit()"  class="easyui-linkbutton" plain="true" icon="icon-save">提交</a>
            <a href="javascript:void(0)" onclick="javascript:getTemplateDate();"  class="easyui-linkbutton" plain="true" icon="icon-reload">恢复默认</a>
            <a href="javascript:void(0)" onclick="javascript:getDate()"  class="easyui-linkbutton" plain="true" icon="icon-back">取消</a>
       </div>
     </form>
     </div>
    </body>
</html>
