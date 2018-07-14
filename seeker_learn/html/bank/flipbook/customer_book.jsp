<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<!--[if IE 6]>
  <html id="ie6" dir="ltr" lang="en-US">
  <![endif]-->
  <!--[if IE 7]>
    <html id="ie7" dir="ltr" lang="en-US">
    <![endif]-->
    <!--[if IE 8]>
      <html id="ie8" dir="ltr" lang="en-US">
      <![endif]-->
      <!--[if !(IE 6) | !(IE 7) | !(IE 8) ]>
        <!-->
        <html dir="ltr" lang="en-US">
        <!--<![endif]-->
        
        <head>
          <meta charset="UTF-8" />
          <meta name="viewport" content="width=device-width" />
          <title> [${customerName }]-客户资料</title>
          <meta name="keywords" content="" />
          <meta name="description" content=" " />
          <link rel="stylesheet" type="text/css" media="all" href="<%=basePath %>/bank/flipbook/css/style.css"/>
             <script type='text/javascript'>
<%--         function add(){--%>
<%--             $.ajax({ --%>
<%--                 type : "POST", --%>
<%--                 url:'<%=basePath %>UserReadPowerManage_addPowerNum.do', --%>
<%--                 traditional: true,//在struts2下该属性必须有 --%>
<%--                 data:{customerid:${customerid}}, --%>
<%--                 success:function(result){ --%>
<%--                 }--%>
<%--             });--%>
<%--         }--%>
<%--         $(function(){--%>
<%--             add();--%>
<%--             });--%>


        function veClick(obj){
            alert(1);
            $obj=$(obj);
            var src=$obj.attr("src");
            var str="<img src='<%=basePath%>"+src+"'/>"
            $('#ve').html(str);
            $('#ve').toggleClass('index');
        }
          </script>
          <!--[if lt IE 9]>
            <script src="images/js/html5.js" type="text/javascript">
            </script>
          <![endif]-->
          <style type="text/css">
          .enlarge>li{ line-height: 35px; max-width: 260px; list-style-type: none; overflow: hidden; overflow: hidden;  }
          .enlarge>li a span.number{ line-height: 30px;  width:35px; max-width:40px; margin-right:5px;  display: inline-block; text-align: center;}
          .enlarge>li a span.text{ line-height: 25px; width:180px; max-width: 200px; display: inline-block; }
          .index{z-index: 3;}
          #ve{ position: relative; z-index: 0; }
          #sa{ position: relative; z-index: 1;}
          </style>
        </head>
        
        <body class="home page page-id-2 page-template-default single-author singular two-column right-sidebar" style="background: #ccc;">
        <div id="ve">
         </div>
         <div id="sa">
          <div id="page" class="hfeed">
            <!-- #branding -->
            <div id="mpc-styles-switcher">
              <!-- <img src="images/bg/logo.jpg"/>
              -->
              <!-- end ss-content -->
            </div>
            <!-- end mpc-styles-switcher -->
            <div id="main">
              <div id="primary">
                <div id="content" role="main">
                  <article id="post-2" class="post-2 page type-page status-publish hentry">
                    <div class="entry-content">
                      <div id="flipbook-container-0" class="flipbook-container">
                      <!--  flipbook  start   -->
                        <div id="flipbook-0" class="flipbook">
                        
                          <div class="fb-page single">
                            <div class="page-content">
                              <div class="container">
                                <div class="page-html">
                                </div>
                                <img src="<%=basePath %>bank/demo/images/001/cover.jpg" class="bg-img" />
                              </div>
                            </div>
                          </div>
                          <c:forEach items="${bookPages}" var="boosPage">
                            <div class="fb-page single">
                                <div class="page-content">
                                  <div class="container">
                                    <div class="page-html" >
                                    <c:if test="${!empty boosPage.title}">
                                     <h2 class="enlarge" style="text-align: center;">
                                         <div style="text-align: center; border-bottom: double 5px black; margin: 5px 30px 5px 60px; width: 150px;">${boosPage.title }</div>
                                     </h2>
                                    </c:if>
                                    <c:if test="${empty boosPage.title}">
                                     <h2 class="enlarge">&nbsp;</h2>
                                    </c:if>
                                        <ul class="toc enlarge"> <c:forEach  items="${boosPage.logs}" var="log">
                                          <li>
                                            <a href="#${log.tPage }">
                                                <span class="number">
                                                  ${log.showPage }
                                                </span>
                                                <span class="text">
                                                  ${log.title}
                                                </span>
                                            </a>
                                          </li> </c:forEach>
                                        </ul>
                                   
                                    </div>
                                    <img src="<%=basePath %>/bank/flipbook/img/default.png"   class="bg-img" />
                                  </div>
                                </div>
                              </div>
                            </c:forEach>
                       <c:forEach items="${lstBusiness}" var="busPo">
                       <c:forEach items="${busPo.attachs}" var="attach">
                          <div class="fb-page single">
                                <div class="page-content">
                                  <div class="container">
                                    <div class="page-html">
                                    </div>
                                    <img src="<%=basePath %>${attach.path}" class="bg-img" onclick="javascript:veClick(this)" />
                                  </div>
                                </div>
                              </div>
                       
                       </c:forEach>
                       </c:forEach>   
                       <c:if test="${empty lstBusiness}">
                         <div class="fb-page double">
                           <div class="page-content">
                            <div class="container">
                                <div class="page-html" style="line-height:  490px; color: #ff5050 ;text-align: center; font-size: 20px; ">
                                此客户没有建立文档信息!!!!
                                </div>
<%--                                <img src="images/001/06-07.jpg" class="bg-img" />--%>
                              </div>
                            </div>
                          </div>
                       
                       </c:if>
                       
                       
                       
                         <c:forEach items="${updatelogs}" var="boosPage">
                            <div class="fb-page single">
                                <div class="page-content">
                                  <div class="container">
                                    <div class="page-html" >
                                    <c:if test="${!empty boosPage.title}">
                                     <h2 class="enlarge" style="text-align: center;">
                                         <div style="text-align: center; border-bottom: double 5px black; margin: 5px 30px 5px 60px; width: 150px;">${boosPage.title }</div>
                                     </h2>
                                    </c:if>
                                    <c:if test="${empty boosPage.title}">
                                     <h2 class="enlarge">&nbsp;</h2>
                                    </c:if>
                                        <ul class="toc enlarge"> <c:forEach  items="${boosPage.logs}" var="log">
                                          <li>
                                          <div  style="font-weight: bold; line-height: 25px;">
                                                  ${log.title}
                                          </div>
                                          <div style="line-height: 25px;">
                                                  ${log.content}
                                          </div>
                                          </li> </c:forEach>
                                        </ul>
                                   
                                    </div>
                                    <img src="<%=basePath %>/bank/flipbook/img/default.png"   class="bg-img" />
                                  </div>
                                </div>
                              </div>
                            </c:forEach>
                            
                          
                      
                          <div class="fb-page single">
                            <div class="page-content">
                              <div class="container">
                                <div class="page-html">
                                </div>
                                <img src="<%=basePath %>bank/demo/images/001/back-cover.jpg" class="bg-img" />
                              </div>
                            </div>
                          </div>
                        </div>
                      <!--  flipbook  start   -->
                      </div>
                    </div>
                    <!-- .entry-content -->
                    <footer class="entry-meta">
                    </footer>
                    <!-- .entry-meta -->
                  </article>
                  <!-- #post-2 -->
                </div>
                <!-- #content -->
              </div>
              <!-- #primary -->
            </div>
            <!-- #main -->
            <!-- #colophon -->
          </div>
          <!-- #page -->
          <center>
            <div style="font-size:15px; font-weight:bold; text-align:center; line-height:25px;">
              <br />
            </div>
          </center>
          <script type='text/javascript' src='<%=basePath %>/bank/flipbook/js/jquery.js?ver=1.7.2'>
          </script>
          <script type='text/javascript' src='<%=basePath %>/bank/flipbook/js/jquery.easing.1.3.js?ver=3.4.1'>
          </script>
          <script type='text/javascript' src='<%=basePath %>/bank/flipbook/js/jquery.doubletap.js?ver=3.4.1'>
          </script>
          <script type='text/javascript' src='<%=basePath %>/bank/flipbook/js/page-js.js?ver=3.4.1'>
          </script>
          <script type='text/javascript' src='<%=basePath %>/bank/flipbook/js/jquery.color.js?ver=2.0-4561m'>
          </script>
          <script type='text/javascript' src='<%=basePath %>/bank/flipbook/js/turn.js?ver=3.4.1'>
          </script>
          <script type='text/javascript' src='<%=basePath %>/bank/flipbook/js/flipbook.js?ver=3.4.1'>
          </script>
         </div>
        </body>
        
        </html>
        
       