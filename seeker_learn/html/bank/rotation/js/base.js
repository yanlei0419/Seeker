var 	userAgent = navigator.userAgent,
	isIE = /msie/i.test(userAgent) && !window.opera,
	isWebKit = /webkit/i.test(userAgent),
	isFirefox = /firefox/i.test(userAgent),
	isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null;
function rotate(target, degree) {
	if (isWebKit) {
		target.style.webkitTransform = "rotate(" + degree + "deg)";
	} else if (isFirefox) {
		target.style.MozTransform = "rotate(" + degree + "deg)";
	} else if (isIE) {
		//chessDiv.style.filter = "progid:DXImageTransform.Microsoft.BasicImage(rotation=" + degree + ")";
		degree = degree / 180 * Math.PI;
		var sinDeg = Math.sin(degree);
		var cosDeg = Math.cos(degree);
		
		target.style.filter = "progid:DXImageTransform.Microsoft.Matrix(" +
				"M11=" + cosDeg + ",M12=" + (-sinDeg) + ",M21=" + sinDeg + ",M22=" + cosDeg + ",SizingMethod='auto expand')";
	} else {
		target.style.transform = "rotate(" + degree + "deg)";
	}
}

function zoom(param){
    if(param==9999){
    	zoomParam=1;
    	count=0;
    	$("#zoomImg").width(width); 
        $("#zoomImg").height(height); 
        initWindow();
        $('#rotate').css({"top":90});
        rotate(document.getElementById('zoomImg'), 0);
        return ;
    }
	zoomParam+=param;
    if(zoomParam>1.5){
    	zoomParam=1.5;
    	return ;
    }
    if(zoomParam<0.1){
    	zoomParam=0.1;
    	return ;
    }
    var myWidth=width,myHeight=height;
    myWidth*=zoomParam;
    myHeight*=zoomParam;
    $("#zoomImg").width(myWidth); 
    $("#zoomImg").height(myHeight); 
    initWindow();
}
$(function(){

 })
function initVegetto(){
	$("#rotate").sortable( {
	    tolerance : 'intersect',
	    items : 'li',
	    stop: function(event, ui) { 
	        var li=ui.item;
	        var top=ui.offset.top,left=ui.offset.left;
	        li.parent('#rotate').css({"top":top,"left":left});
	        //alert(ui.position.top);
	      //  alert(ui.offset.top);
	        //alert(ui.position.left);
	        //alert(ui.offset.left);
	    },
	    placeholder : 'saLi'//移动开始后原位置css样式
	});
	$("#rotate").disableSelection();
}
function doSaber(param){
    count+=param;
    if(count>360){
        count-=360;
    }else if(count<-360){
        count+=360;
    }
    rotate(document.getElementById('zoomImg'), count);
    initWindow()
}
function initWindow(){
	var imgWidth=$("#zoomImg").width(),winWidth=$(window).width(); 
//	alert("imgWidth"+imgWidth);
//	alert("winWidth"+winWidth);
    var left=(winWidth-imgWidth)/2;
    $('#rotate').css({"left":left-15});
}