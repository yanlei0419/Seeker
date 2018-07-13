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
        <jsp:include page = "/common/include/dialog.jsp" flush = "true"/>
        <script type="text/javascript" >
        var defaultLocationId="ve1";
      //  计数器
        var count=0;
        $(function() {
            $( ".sortable1" ).sortable({
                tolerance: 'intersect',
                connectWith: '.sortable1',
                items: 'li:not(.lastli)',
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
				success:function(result){ 
					build(result);
					$('#myTemplate').unblock();
				}
			});
        }
        //构建数据
        function build(data){
             var json =eval("("+data+")");
             for(var i=0;i<json.length;i++){
            	 createLiDiv(json[i].locationId,json[i].itemId,json[i].name);
             }
       }
        //添加新的桌面模块
        function createLiDiv(locationId,itemId,name){
            if(locationId==""||locationId==null||locationId=='undefined'){
            	locationId=defaultLocationId;
            }
            var obj='<li><div class="infoBox" >'+
                '<div class="infoBox_title">'+
                '<span class="ico1"></span>'+name+'--'+count+
                ' <input type="hidden"  name="listPo['+count+'].name"  value="'+name+'"/>'+
                ' <input type="hidden"  name="listPo['+count+'].itemId"  value="'+itemId+'"/>'+
                ' <input type="hidden"  name="listPo['+count+'].locationId"  value="'+locationId+'"/>'+
                ' </div></div></li>';
			$('#'+locationId).prepend(obj);
            count++;
		}
        function addDeskDiv(itemid,name){
        	createLiDiv('',itemid,name);
        }
        function selDeskItem(locationId){
        	 if(locationId!=""&&locationId!=null&&locationId!='undefined'){
        		 defaultLocationId=locationId;
             }
           var  url="<%=basePath%>desk/item/list.jsp";
           openWin(url);
        }
        </script>
        <style type="text/css">
        .sortable1 li{margin: 5px;}
        #le td{vertical-align: top;}
        .lastli{border:dashed 2px #CCC ;  height: 35px; line-height: 35px; text-align: center;}
        .lastli:hover{ cursor:pointer;}
        .sort_placeholder{ height:40px;background: #bba;}
        .infoBox{height: 40px; cursor:move; }
        .infoBox:hover { cursor:move; }
    </style>
    </head>
    <body style="background-color: #f3f8fd">
    <div id='myTemplate' style="height: 100%" >
    <form  action="<%=basePath%>PersonalTemplateManage_addPersonalTemplate.do" method="post">
    <input type="hidden"  name="templateid"  value="1"/>
    <table id="le" border="0" width="100%">
        <tr>
            <td colspan="2">
                 <ul id="ve1" class="sortable1">
                    <li class="lastli"     onclick='selDeskItem("ve1")' >此处可以添加菜单</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>
                <ul id="ve2" class="sortable1">
                    <li class="lastli"  onclick='selDeskItem("ve2")' >此处可以添加菜单</li>
                </ul>
            
            </td>
            <td>
                <ul  id="ve3" class="sortable1">
                    <li class="lastli" onclick='selDeskItem("ve3")' >此处可以添加菜单</li>
                </ul>
           
            </td>
        </tr>
         <tr>
            <td colspan="2">
                <ul id="ve4" class="sortable1">
                    <li class="lastli"  onclick='selDeskItem("ve4")' >此处可以添加菜单</li>
                </ul>
            </td>
        </tr>
    </table>
    <input type="submit"  value="提交"/>
     </form>
     </div>
    </body>
</html>
