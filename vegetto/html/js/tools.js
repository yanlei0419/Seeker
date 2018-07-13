function initOdd(t,s){
	t.find('.children a:odd').each(function(){$(this).append('<div class="saW sa1"></div><div class="saW sa2"></div><div class="saH sa3"></div><div class="saH sa4"></div>')});
	var op='0';
	t.find('.children a div.sa1').css({opacity:op});
	t.find('.children a div.sa2').css({opacity:op});
	t.find('.children a div.sa3').css({opacity:op});
	t.find('.children a div.sa4').css({opacity:op});
	t.find('.children a').hover(function (){
			var o=1,l='0px';
			$(this).find('.sa1').stop().animate({top:l,opacity:o},s);
			$(this).find('.sa2').stop().animate({bottom:l,opacity:o},s);
			$(this).find('.sa3').stop().animate({right:l,opacity:o},s);
			$(this).find('.sa4').stop().animate({left:l,opacity:o},s)
	},function(){
			var o='0';
			$(this).find('.sa1').stop().animate({top:'-10px',opacity:o},s);
			$(this).find('.sa2').stop().animate({bottom:'-10px',opacity:o},s);
			$(this).find('.sa3').stop().animate({right:'-18px',opacity:o},s);
			$(this).find('.sa4').stop().animate({left:'-18px',opacity:o},s);
	});
}
//翻转
var initEven = function(t,d){
	t.find('.children .ve2').css({'height':0,'top':'25px'});
	if(t.find('.children .ve2').length != 0){return false;}
	t.find('.children a:even').each(function(){$(this).append('<div class="ve2 ">' + $(this).attr('href') + '</div>')});
	t.find('.children a:even').hover(function(){
		$(this).find('.ve1').stop().animate({height:0,	top:'25px'	},d,function(){
			$(this).hide().next().show();
			$(this).next().animate({height:'40px',top:'0'},d);
		});
	},function(){
		$(this).find('.ve2').animate({height:0,top:'25px'},d,function(){
			$(this).hide().prev().show().animate({height:'40px',top:0},d);
		});
	});
}

function initLe(t,s){
	t.each(function(){
		$(this).append('<div class="le1"></div><div class="le2"></div><div class="le3"></div><div class="le4"></div>')
		$(this).append('<div class="le le5"></div><div class="le le6"></div><div class="le le7"></div><div class="le le8"></div>')
	});
	var op='0';
	t.find('div.le1').css({opacity:op});
	t.find('div.le2').css({opacity:op});
	t.find('div.le3').css({opacity:op});
	t.find('div.le4').css({opacity:op});
	t.find('div.le5').css({opacity:op});
	t.find('div.le6').css({opacity:op});
	t.find('div.le7').css({opacity:op});
	t.find('div.le8').css({opacity:op});
	t.hover(function (){
			var o=1,mb='0px';
			$(this).find('.le1').stop().animate({top:mb,opacity:o},s);
			$(this).find('.le2').stop().animate({bottom:mb,	opacity:o},s);
			$(this).find('.le3').stop().animate({right:mb,opacity:o},s);
			$(this).find('.le4').stop().animate({left:mb,opacity:o},s)
			$(this).find('.le5').stop().animate({top:mb,left:mb,opacity:o},s);
			$(this).find('.le6').stop().animate({top:mb,right:mb,opacity:o},s);
			$(this).find('.le7').stop().animate({bottom:mb,right:mb,opacity:o},s);
			$(this).find('.le8').stop().animate({bottom:mb,left:mb,opacity:o},s);
	},function(){
			var o=0,ma='-100px';
			$(this).find('.le1').stop().animate({top:ma,opacity:o},s);
			$(this).find('.le2').stop().animate({bottom:ma,opacity:o},s);
			$(this).find('.le3').stop().animate({right:ma,	opacity:o},s);
			$(this).find('.le4').stop().animate({left:ma,opacity:o},s);
			$(this).find('.le5').stop().animate({top:ma,left:ma,opacity:o},s);
			$(this).find('.le6').stop().animate({top:ma,right:ma,opacity:o	},s);
			$(this).find('.le7').stop().animate({bottom:ma,right:ma,	opacity:o},s);
			$(this).find('.le8').stop().animate({bottom:ma,left:ma,opacity:o},s);
	});
}