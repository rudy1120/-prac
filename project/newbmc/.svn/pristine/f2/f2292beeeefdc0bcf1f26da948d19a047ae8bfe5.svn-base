
$(document).ready(function() {

	$('.depth02').hide();
	$('#lnb').css('height','54px');


	/* 주메뉴 */
	$('#lnb_wrap > li > a ').each(function(){
		
		$('.depth02');
		$('#lnb').css('height','54px');


		$(this).bind("mouseover focusin",function(){
			$('.depth02').hide();
			$(this).parent().find('.depth02').show().stop(true,false).delay(150);
			var ul_height = ($(this).parent().find('div').outerHeight());
			var gnb_height = ul_height + 100;

			$('#lnb').show().stop(true,false).animate({height:gnb_height},{duration:510,easing:"easeOutExpo"});
		});
	});

	 $('#lnb').mouseleave(function() { 
		$('#lnb').stop(true,false).animate({height:'54px'},{duration:500,easing:"easeOutExpo"});		
		$('.depth02').hide();

	}); 
	 
	// 포커스 잃을 때
	$("a").filter(":not(#lnb_wrap > li  a)").focus(function(){
		$('#lnb_wrap > li > div').hide();
		$('#lnb').stop(true,false).animate({height:'54px'},{duration:500,easing:"easeOutExpo"});
	});
	 
	// top navigation 3차 탑메뉴
	$('#lnb_wrap > li > div > div > ul > li > a').mouseover(function(){		
		$('#lnb_wrap > li > div > div > ul > li > ul').hide();
		$(this).next('.depth03').show().removeClass("hidden");
		$(this).addClass("on");
		$(" #lnb_wrap > li > div > div > ul > li > a").not(this).removeClass("on");
	});

	$('#lnb_wrap > li > div > div > ul > li > a').focus(function(){	
		$('#lnb_wrap > li > div > div > ul > li > ul').hide();
		$(this).next('.depth03').show().removeClass("hidden");
		$(this).addClass("on");
		$(" #lnb_wrap > li > div > div > ul > li > a").not(this).removeClass("on");
	});
	 

});

