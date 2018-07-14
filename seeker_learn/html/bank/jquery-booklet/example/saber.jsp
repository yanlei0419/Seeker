<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    pageContext.setAttribute("a","\\");
    pageContext.setAttribute("b","/");
%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8" />
    <title>[${customerName }]-客户资料</title>
    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link href="<%=basePath %>/bank/jquery-booklet/booklet/jquery.booklet.latest.css" type="text/css" rel="stylesheet" media="screen, projection, tv" />
    <style type="text/css">
        body {background:#ccc; font:normal 12px/1.2 arial, verdana, sans-serif;}
          .enlarge>li{ line-height: 35px; max-width: 400px; list-style-type: none; overflow: hidden;  padding: 0px 30px ; }
         a{ text-decoration: none;}
          .enlarge>li a span.number{ line-height: 30px;  width:35px; max-width:40px; margin-right:5px;  display: inline-block; }
          .enlarge>li a span.ve-text{ line-height: 25px; width:350px; max-width: 350px; display: inline-block;overflow: hidden; }
          .mlTitle{text-align: center;  margin: 5px 30px 5px 60px; width: 400px; font-size: 24px; line-height: 100px;}
          .doubleBorder{border-bottom: double 5px black;}
          .myVe {width:550px;height: 700px;}
          .myImg{position: absolute;width: 100%;height:100%; z-index: -1;left: 0px;top: 0px;}
          #lend{width: 240px;right:3px;top:3px; line-height: 50px; background-color:black;  position: absolute; z-index: 2;  text-align: center; color: #46B641;cursor: pointer; font-size: 20px;-moz-opacity: 0.5; opacity:.50; filter: alpha(opacity=50);}
          #lend:hover{width: 238px; line-height: 48px;border: 1px solid #ff5050;}
          </style>
           <script type="text/javascript" src="<%=basePath %>/bank/jquery-booklet/booklet/jquery-2.1.0.min.js"></script>
           <script type="text/javascript" src="<%=basePath%>desk/js/jquery.blockUI.js"></script>
           <script src="<%=basePath %>/bank/jquery-booklet/booklet/jquery-ui-1.10.4.min.js"></script>
            <script src="<%=basePath %>/bank/jquery-booklet/booklet/jquery.easing.1.3.js"></script>
            <script src="<%=basePath %>./bank/jquery-booklet/booklet/jquery.booklet.latest.js"></script>
            
            <script type="text/javascript" src="<%=basePath%>plugins/dialog/dialog.min.js"></script>
            <script type="text/javascript" src="<%= basePath %>plugins/dialog/dialog.extends.js"></script>
        <jsp:include page="/bank/include/ve.jsp" flush="true"/>
           <script type="text/javascript" >
        function initBook(page){
            if(page==undefined){
                page=3;
            }
            $("#mybook").booklet({
                    name :"[${customerName }]-客户资料",
                    width:  1100,height: 650,speed:600,
                    startingPage: page,
                    pageNumbers: true,
                    closed: true,
                    autoCenter: true,
                    covers: true,
                    menu: '#custom-menu',
                    pageSelector: true,
                    closedFrontTitle:'封面',
                    closedFrontChapter: '封面',             
                    closedBackTitle :'The End',
                    closedBackChapter: 'The End', 
                    arrows: true,
                    arrowsHide: true,
                    chapterSelector:true,
                    start: function(event, data) {//翻页开始
                            //alert("start");
                    },
                    change: function(event, data) {//翻页结束
                            //alert("change");
                     },
                     create: function(event, data) {
                         //   alert("create");
<%--                         $('#ve').unblock();--%>
                     }
                });
        }
  	  $(function () {		
			initBook();
	    });
		
           </script>
</head>
                      <c:set var="yl"   scope="request" value="0"/>
           <c:set var="count" scope="request" value="0"></c:set>
<body>
<div id="ve" >
    <div id="custom-menu" style="width: 1100px; margin: auto;" >
    </div>
	    <div id="mybook">
            <div title="first page" class="myVe ">
                     <img src="<%=basePath %>/bank/bookshelf/bg2.png" class="myImg" />
                    <h3 style="line-height: 300px; text-align: center; font-size: 24px;margin: 30px 0px;  vertical-align: middle;">题目：</h3>
                    <div style="height: 300px;max-height: 400px; margin: 10px 0px; text-align: center; font-size: 24px; vertical-align: middle; color: #000;" >客户名称：${customerName }</div>
            </div>
              <c:if test="${empty lstBusiness}">
                                <div class="page-html" style="line-height:  490px; color: #ff5050 ;text-align: center; font-size: 20px; ">
                                此客户没有建立文档信息!!!!
                                </div>
             </c:if>
                <c:if test="${!empty lstBusiness}">
                    <c:forEach items="${bookPages}" var="boosPage">
                      <c:set var="count"  value="${count+1}"/>
<%--                                    <div title="page-${count }" >--%>
                                    <div title="目录" >
                                    <c:if test="${!empty boosPage.title}">
                                         <div class="mlTitle doubleBorder">${boosPage.title }</div></c:if><c:if test="${empty boosPage.title}">
                                         <div class="mlTitle">&nbsp;</div></c:if>
                                        <ul class="toc enlarge"> <c:forEach  items="${boosPage.logs}" var="log">
                                          <li>
                                            <a href="javascript:void(0)" onclick="javascript:setPage(${log.tPage})">
                                                <span class="ve-text" title="${log.spanTitle}">
                                                  ${log.title}
                                                </span>
                                                <span class="number">
                                                 ${log.page}
                                                </span>
                                            </a>
                                          </li> </c:forEach>
                                        </ul>
                                    </div>
                            </c:forEach>
                      <c:forEach items="${lstBusiness}" var="busPo">
                      <c:set var="yl"   value="0"/>
                      <c:forEach items="${busPo.attachs}" var="attach"><c:set var="count"  value="${count+1}"/> 
                      <c:if test="${yl==0}">
                      <div title="${busPo.spanTitle }" rel="${busPo.spanTitle }"  style="position: relative;">
                            <div id="lend"  style="" onclick="javascript:lendBook('${busPo.id}')">借阅此份档案</div>
                      </c:if>
                      <c:if test="${yl!=0}"><div title="${busPo.spanTitle }"  ></c:if><c:set var="yl"   value="${yl+1}"/>
                                        <img src="<%=basePath %>${fn:replace(attach.path,a,b)}" style="width: 530px;height:630px;" ondblclick="javascript:imgOnclick(this)" title="双击图片放大"   />
                                      </div> 
                      </c:forEach></c:forEach>   
                     
                         <c:forEach items="${updatelogs}" var="boosPage"> <c:set var="count"  value="${count+1}"/>
                                    <div class=""  title="修订内容">
                                    <c:if test="${!empty boosPage.title}">
                                     <div class="mlTitle doubleBorder">${boosPage.title }</div>
                                    </c:if>
                                    <c:if test="${empty boosPage.title}">
                                      <div class="mlTitle">&nbsp; </div>
                                    </c:if>
                                        <ul class="toc enlarge"> <c:forEach  items="${boosPage.logs}" var="log">
                                          <li>
                                              <div  style="font-weight: bold; line-height: 25px;">
                                                     <a href="javascript:void(0)" onclick="javascript:selUpdateContent('${log.busid}')">${log.title}</a>
                                              </div>
                                              <div style="line-height: 25px;">
                                                      ${log.content}
                                              </div>
                                          </li> </c:forEach>
                                        </ul>
                                    </div>
                            </c:forEach>
                              </c:if>
	        <div title="eighth page" class="myVe  pageHtml" style="background-color: #fff; padding: 0px;">
<%--                <img src="<%=basePath %>bank/demo/images/001/back-cover.jpg" />--%>
	            <h3 style="line-height: 650px;text-align: center; font-size: 28px;font-weight: bold;color: #000; margin: 0px;padding: 0px;">The End</h3>
	        </div>
	    </div>
</div>   

	<script>
        function setPage(pageNumber){
            if(pageNumber==undefined|| typeof pageNumber!="number"){
                alert("页码有问题,请联系管理员!!!!!");
                return;
                }
        	$("#mybook").booklet(pageNumber);
        }
        function imgOnclick(obj){
            $obj=$(obj);
            var url="<%=basePath %>bank/rotation/rotation.jsp?imgUrl="+ encodeURIComponent($obj.attr("src"));
            window.open(url);
        }
        function selUpdateContent(id){
        	   var url="<%=basePath %>BankBusinessChangeManage_getBusList.do?businessid="+id;
               window.open(url);
        }
        function lendBook(busId){
             //首先判断是否有查看资料的权限
             $.ajax({ 
                 type : "POST", 
                 url:'<%=basePath%>FilePageBorrowInfoManage_checkBookLend.do', 
                 traditional: true,//在struts2下该属性必须有 
                 dataType:'text',
                 data:{busId:busId}, 
                 success:function(result){ 
                	 if(result.indexOf("false")>-1){
                         alert("此资料没有形成纸质档案,请联系管理员解决");
                         return;
                  	 }else if(result.indexOf("true")>-1){
                     	 var saber="<%=basePath%>FilePageBorrowInfoManage_toFilePageBorrowInfoAdd.do?busId="+busId;
                         openWin(saber);
                  	 }else{
                         alert('此档案已经借出,目前尚未归还,请联系管理员解决这个问题'); 
                     }
                 }
             });
        }
    </script>
</body>
</html>