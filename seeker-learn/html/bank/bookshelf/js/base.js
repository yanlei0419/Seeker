function initData(curPage) {
	$('#ve').block( {
		css : {
			border : 'none',
			padding : '15px',
			backgroundColor : '#000',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : .5,
			color : '#fff'
		},
		message : "正在努力的加载中……"
	});
	if (typeof curPage != "number") {
		curPage = 1;
	}
	$.ajax( {
		type : "POST",
		url : basePath + 'BankCustomerManage_getBookShelfCustomerList.do',
		dataType : 'text',
		traditional : true,//在struts2下该属性必须有 
		data : {
			customer_no : $("#customer_no").val(),
			name : $("#name").val(),
			page : curPage,
			rows : rows
		},
		success : function(result) {
			var json = eval("(" + result + ")");
			var data = json.rows;
			createPages(json.total, curPage);
			$("#veUl").empty();
			for ( var i = 0; i < data.length; i++) {
				$("#veUl").append(createLi(data[i]));
			}
			$('#ve').unblock();
		}
	});
}
function createLi(data) {
	var li = "<li ondblclick='javascript:searchCustomerPower(\"" + data.id
			+ "\")' title='" + data.customer_no + "-" + data.name + "'>";
	li += " <div class='book-bottom '>";
	li += " <div class='cTitle'>客户名称 </div>";
	li += "<div class='cName'>";
	li += data.name;
	li += "</div></div>";
	li += " <img  src='" + basePath + "/bank/bookshelf/bg1.png' />";
	li += " </li>";
	return li;
}
function initSorTable() {
	$("#veUl").sortable( {
		tolerance : 'intersect',
		items : 'li',
		delay :100,
		tolerance :'pointer',
		stop : function(event, ui) {
		},
		placeholder : 'saLi'//移动开始后原位置css样式
	});
	$("#veUl").disableSelection();
}
$(window).resize(function() {
	$('#BankCustomerPanel').panel('resize');
});

function searchCustomerPower(customerid) {
	var saber = basePath
	+ "UserReadPowerManage_getBankBusinessListByBooks.do?customerid="
	+ customerid;
	window.open(saber);
	return;
	//首先判断是否有查看资料的权限
	$.ajax( {
				type : "POST",
				url : basePath + 'UserReadPowerManage_getCurUserReadPower.do',
				dataType : 'text',
				traditional : true,//在struts2下该属性必须有 
				data : {
					customerid : customerid
				},
				success : function(result) {
					//  alert(result)
					if (result.indexOf("true") > -1) {
						var saber = basePath
								+ "UserReadPowerManage_getBankBusinessListByBooks.do?customerid="
								+ customerid;
						if (isChrome) {
							document.onclick = function() {
							
							}
							return;
						} else {
							window.open(saber);
							return;
						}
					} else {
						alert('您没有此用户的查看权限');
					}
				}
			});
}

function createPages(total, curPage) {
	if (typeof total != "number") {
		alert("查询数据有问题,请联系管理员!!!!!");
		return;
	}
	var count = total % rows;
	var pages = 0;
	if (count == 0) {
		pages = total / rows;
	} else {
		pages = (total - count) / rows + 1
	}
	createPagesLi(pages, curPage);
}
function createPagesLi(pages, curpage) {
	var index = 0, size = 0;
	if (typeof curpage != "number") {
		curpage = 1;
	}
	if (pages > 8) {
		size = 8;
	} else {
		size = pages;
	}
	if (curpage == pages) {
		index = curpage - 7;
		size = pages;
	} else if (curpage > 6) {
		index = curpage - 3;
		size = curpage + 7;
	}
	if (size > pages) {
		size = pages;
	}
	if (index < 0) {
		index = 0;
	}
	if (size == 0) {
		size = 1;
	}
	$("#page").empty();
	//  var veLi="<li class='pageLi' onclick='javascript:initData(1)'>首页</li>";
	var indexPage = document.createElement("li");
	$indexPage = $(indexPage);
	$indexPage.attr("class", "pageLi");
	$indexPage.attr("onclick", 'javascript:initData(1)');
	$indexPage.html("首页");
	$("#page").append($indexPage);

	for ( var i = index; i < size; i++) {
		var li = document.createElement("li");
		$li = $(li);
		if (i + 1 == curpage) {
			$li.attr("class", "selLi");
			$li.html((i + 1));
			//   veLi+="<li class='selLi' >"+(i+1)+"</li>";
		} else {
			$li.attr("class", "pageLi");
			$li.attr("onclick", 'javascript:initData(' + (i + 1) + ')');
			$li.html((i + 1));
			//   veLi+="<li class='pageLi' onclick='javascript:initData("+(i+1)+")'>"+(i+1)+"</li>";
		}
		$("#page").append($li);
	}
	//  veLi+="<li class='pageLi' onclick='javascript:initData("+pages+")'>末页</li>";
	//$("#page").html(veLi);

	if (pages > 7) {
		var endPage = document.createElement("li");
		$endPage = $(endPage);
		$endPage.attr("class", "pageLi");
		$endPage.attr("onclick", 'javascript:initData(' + pages + ')');
		$endPage.html("末页");
		$("#page").append($endPage);
	}
}
