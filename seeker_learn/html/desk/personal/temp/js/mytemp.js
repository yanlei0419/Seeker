        //构建菜单数据
        function build(data){
             var json =eval("("+data+")");
             for(var i=0;i<json.length;i++){
            	 createLiDiv(json[i].locationId,json[i].itemId,json[i].name, json[i].code, json[i].url, json[i].show_rows);
             }
       }
        //添加新的桌面模块
        function createLiDiv(locationId,itemId,name,code,url,show_rows){
            var obj='<li>';
	            obj+='<div class="infoBox" >';
		            obj+='<div class="infoBox_title">';
			            obj+='<span class="ico1"></span>'+name;
			                //改变的
			            obj+='<a href="javascript:void(0);" onclick="javascript:detail'+code+'();" class="infoBox_more">>>更多</a>';
		            obj+=' </div>';
		                //以上是title
		            obj+='<div id="'+code+'Div" style="overflow: hidden;">';
			            obj+='<div id="design1" style="overflow: hidden;">';
			               // 每个ul刷新数据会变的 id会变
			            obj+='<ul class="message2List" id="'+code+'"></ul>';
		            obj+='</div>';
		            obj+='<div id="design2"></div>';
	            obj+='</div>';
            obj+='</li>';
			$('#'+locationId).append(obj);
		}
        //新闻公告
		function newList(){
			$.ajax({ 
				url :basePath+'NewsInfoManage_getIndexNewsList.do',
				type :"post",
                dataType:'text',
				traditional: true,//在struts2下该属性必须有 
				success : function(data) {
					$('#XWGG').html(data); 
				}
			});
		}
		//内部通讯录
		function companyList(){
			$.ajax({ 
				url :basePath+'AddressBookManage_getIndexCompanyAddressBook.do',
				type :"post",
                dataType:'text',
				traditional: true,//在struts2下该属性必须有 
				success : function(data) {
					$('#TXL').html(data); 
					addCompanyDiv();
				}
			});
		}
		//查询内部通讯录加载数据
		function searchCompanyList(){
			$.ajax({ 
				url :basePath+'AddressBookManage_getIndexCompanyAddressBook.do',
				type :"post",
                dataType:'text',
				traditional: true,//在struts2下该属性必须有 
				data:{firstName:$("#firstName").val()},
				success : function(data) {
					//alert(data);
					//alert($('#TXL').html());
					//$('#TXL').empty(); 
					$('#TXL').html('');
					$('#TXL').prepend(data); 
					//alert($('#TXL').html());
				}
			});
		}
		function addCompanyDiv(){
			var str='<div  class="txldiv">姓&nbsp;&nbsp;名： <input type="text" id="firstName"/> <a href="javascript:void(0)" onclick="searchCompanyList();" class="easyui-linkbutton" plain="true"  icon="icon-search">查询</a></div>';
			$('#TXLDiv').prepend(str); 
		}
		 function getDate(){
	        	$.ajax({ 
					type : "POST", 
                    async : false,
                    dataType:'text',
					url:basePath+'PersonalTemplateManage_getPersonalTemplateOrDeskTemplate.do', 
					traditional: true,//在struts2下该属性必须有 
					success:function(result){ 
						build(result);
					}
				});
	        }
		 //新闻模块更多
		 function detailXWGG(){
			 top.window.frames["frmleft"].onMenuClick('09544242-9490-4b21-8997-47a46a406f22','通知公告',basePath+'hrm/news/list.jsp');
		 }
		 //通讯录模块更多
		 function detailTXL(){
			 top.window.frames["frmleft"].onMenuClick('1307399c-304f-4393-8930-67edabf6a13c','内部通讯录',basePath+'hrm/addressbook/clist.jsp');
		 }
		 //查看内容detail方法
		 function openDetail(menuId,name,url){
			 openWin(basePath+url);
			 //top.window.frames["frmleft"].onMenuClick(menuId,name,url);
		 }
		  $(function() {
	            getDate();
	           // newList();
	            //companyList();
	        });
