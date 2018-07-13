//构建数据
        function build(data){
             var json =eval("("+data+")");
             for(var i=0;i<json.length;i++){
                 if(json[i].locationId==''){
                     count
                   }
            	 createLiDiv(json[i].locationId,json[i].itemId,json[i].name,json[i].code,json[i].url,json[i].show_rows);
             }
       }
        //添加新的桌面模块
        function createLiDiv(locationId,itemId,name,code,url,show_rows){
            if(locationId==""||locationId==null||locationId=='undefined'){
            	locationId=defaultLocationId;
            }
            var obj='<li>';
	             obj+= '<div class="infoBox" >';
		             obj+= '<div class="infoBox_title">';
			             obj+= '<span class="ico1"></span>'+name;
			             obj+= ' <input type="hidden"  name="name"  value="'+name+'"/>';
			             obj+= ' <input type="hidden"  name="itemId"  value="'+itemId+'"/>';
			             obj+= ' <input type="hidden"  name="code"  value="'+code+'"/>';
			             obj+= ' <input type="hidden"  name="url"  value="'+url+'"/>';
			             obj+= ' <input type="hidden"  name="show_rows"  value="'+show_rows+'"/>';
			             obj+= ' <input type="hidden"  name="locationId" class="locationId" value="'+locationId+'"/>';
			             obj+= ' <div style="float: right;display: inline-block;margin-right: 5px;">';
			             	obj+= ' <a href="javascript:void(0)" onclick="javascript:menuColse(this)">×</a>';
			             obj+= ' </div>';
		             obj+= ' </div>';
	             obj+= ' </div>';
             obj+= ' </li>';
			$('#'+locationId+' .lastli').before(obj);
            count++;
		}
        function clearLi(){
        	$('.sortable1 li:not([class="lastli"])').remove();
        }
        function menuColse(obj){
                $objLi=$(obj);
                $objLi.parents('.sortable1 li').remove();
        }
        function selDeskItem(locationId){
       	 if(locationId!=""&&locationId!=null&&locationId!='undefined'){
       		 defaultLocationId=locationId;
            }
          var  url=basePath+"desk/item/list.jsp";
          openWin(url);
       }
        function getTemplateDate(){
        	$.ajax({ 
				type : "POST", 
				url:basePath+'PersonalTemplateManage_getDeskTemplate.do', 
				traditional: true,//在struts2下该属性必须有 
                data:{tempid:$('#templateid').val()},
				success:function(result){ 
                	clearLi()
					build(result);
					$('#myTemplate').unblock();
				}
			});
        }
        function addDeskDiv(itemid,name,code,url,show_rows){
        	createLiDiv('',itemid,name,code,url,show_rows);
        }

      