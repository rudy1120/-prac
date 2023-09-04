$(function(){
    //메인 스크립트
	$(".page-link").click(function (e) {
	    e.preventDefault();
	    
	    var sectionTop = $(".mainSection1");
	    $("html, body").animate(
	      {
	        scrollTop: $(sectionTop).offset().top + (-100)
	      },
	      1000, 'swing'
	    );
	    var btnStop = $("#whole");
	    $(btnStop).attr("tabindex", 0).focus().attr("tabindex", "0");
	});
	/*
	  웹접근성
	 : 페이지의 제목을 적절하게 제공해야 함으로 title tag 안에 popup이 아닌 제목을 써야 함 
	*/
	cookiedata = document.cookie;

/* 일광행복주택 팝업 */
	if(cookiedata.indexOf("close=Y")<0){
        $("#pop1").show();
    }else{
        $("#pop1").hide();
    }
    $("#close").click(function(){
        couponClose();
    });
    
    // 레이어 팝업 키보드 접근 시
    $("#close").on("keydown", function(e){
		if(e.key === "Enter" || e.key === " "){
			couponClose();
			$(".page-link").focus();
			e.preventDefault();
		}
	});

/* 집중안전점검 팝업 */
    if(cookiedata.indexOf("close2=Y")<0){
        $("#pop2").show();
    }else{
        $("#pop2").hide();
    }
    $("#close2").click(function(){
        couponClose2();
    });
    
    // 레이어 팝업 키보드 접근 시
    $("#close2").on("keydown", function(e){
		if(e.key === "Enter" || e.key === " "){
			couponClose2();
			$("#close").focus();
			e.preventDefault();
		}
	});
    
/* 기능인재 팝업 */
    if(cookiedata.indexOf("close3=Y")<0){
        $("#pop3").show();
    }else{
        $("#pop3").hide();
    }
    $("#close3").click(function(){
        couponClose3();
    });
    
    // 레이어 팝업 키보드 접근 시
    $("#close3").on("keydown", function(e){
		if(e.key === "Enter" || e.key === " "){
			couponClose3();
			$(".page-link").focus();
			e.preventDefault();
		}
	});
    
/*	if(cookiedata.indexOf("popup_cookie1=done")<0){
		var imageWin;
		imageWin = window.open("", "", "top=" + 50 + ",left=" + 50 + ",width=" + 567 + ",height=" + 596 +",scrollbars=yes,resizable=yes,status=no");
		imageWin.document.write("<!DOCTYPE html>"); 
		imageWin.document.write("<html lang='ko'><title>이태원 사고 관련 깊은 애도를 표하며, 삼가 고인의 명복을 빕니다.</title><body style='margin:0;' >"); 
		imageWin.document.write("<div><img src='./images/main/20221102_popup.jpg' onclick='void();' style='cursor:pointer;width:100%;' alt='이태원 사고 관련 깊은 애도를 표하며, 삼가 고인의 명복을 빕니다.'/></div>");
		//imageWin.document.write("<div><img src='./images/main/20221102_popup.jpg' onclick='window.opener.jump(1); window.close();' style='cursor:pointer;width:100%;' alt='이태원 사고 관련 깊은 애도를 표하며, 삼가 고인의 명복을 빕니다.'/></div>");
		imageWin.document.write("<p style='text-align:right; margin:0;background: #333;padding: 0px 10px 0px 10px;font-size: 13px;font-weight: 300; line-height: 25px;'><a href='javascript:window.opener.todaycloseWin(1); window.close();' style='background: #666;color: #f2f2f2;border-radius: 3px;padding: 0px 10px;'>24시간동안 보지않기</a></p>");
		imageWin.document.write("</body></html>");	
		
		//게시기간 - ~ -
	} */

    //메인 슬라이드
    $(".mainBanner").slick({
        arrows: false,
        dots: true,
        autoplay: true, //true
        autoplaySpeed: 3000,
        speed: 1000,
        fade:true,
        pauseOnHover: true,
        infinite: true
    });

    //메인 슬라이드 타이틀 및 내용 연계 width 세팅
    $(".mainBannerInfo .info").each(function(){
        $(this).css("left",$(this).prevAll().length * 25+"%");
    });

    //메인 슬라이드 동작시
    $(".mainBanner").on("afterChange.pc", function(event, slick, currentSlide, nextSlide){

        var $current = $(".mainBannerInfo").find("button[data-tab="+currentSlide+"]").parents(".info");
        var prevLength = $current.prevAll().length;

        $(".mainBannerInfo").find(".info").removeClass("show");

        setTimeout(function(){
            $(".mainBannerInfo").find(".info").removeClass("active");
            $current.addClass("active");
        },250);

        setTimeout(function(){
            $(".mainBannerInfo").find(".info").addClass("show");

            for(var i = 0; i < prevLength;  i++ ){
                $('.mainBannerInfo').find(".track").append($('.mainBannerInfo').find(".info").eq(0));
            }

            for (var i = 0; i < $('.mainBannerInfo').find('.info').length; i++){
                $('.mainBannerInfo').find('.info').eq(i).css("left",25 * i + "%");
            }
        },250);
    });

    //메인 슬라이드 타이틀 및 내용 연계 버튼 동작
    $(".mainBannerInfo").find("button[data-tab]").on("click", function(){
        $(".mainBanner").find("#slick-slide-control0"+$(this).attr("data-tab")).trigger("click");
        $(this).focus();
    });

    //닷지 이벤트(모바일에서 확인가능)
    $(".mainContent1").find(".dots>li>button").on("click", function(){
        var left = $(".mainContent1").find(".list>li").eq($(this).attr("data-tab")).position().left;
        $(".mainContent1").find(".dots>li").removeClass("active");
        $(this).parent().addClass("active");
        $(".mainContent1").find(".list").css("left",-left);
    });
    
    //소식 탭 이벤트
    $(".mainNotice1 .noticeTab1").find("button").on("click", function(){
        var $this = $(this);
        $(".noticeTab1>li").removeClass("show");
        setTimeout(function(){
            $(".noticeTab1>li").removeClass("active");
            $this.parent().addClass("active");
            setTimeout(function(){
                $this.parent().addClass("show");
            },10);
        },300);
    });
    $(".mainNotice2 .noticeTab2").find("button").on("click", function(){
        var $this = $(this);
        $(".noticeTab2>li").removeClass("show");
        setTimeout(function(){
            $(".noticeTab2>li").removeClass("active");
            $this.parent().addClass("active");
            setTimeout(function(){
                $this.parent().addClass("show");
            },10);
        },300);
    });
    
    //웹 접근성 탭키로 동작가능
    $(".noticeTab").find("button").on("focus", function(){
        $(this).trigger("click");
    });

    //공지사항 first게시물 글자수로 자르기(다른 방식으로 잘라올때는 해당 스크립트 제거)
    $(".noticeTab").find(".first").each(function(){
        if($(this).find(".sumry").text().length > 70) {
            $(this).find(".sumry").text($(this).find(".sumry").text().substring(0,70)+'...');
        }
    });

    //이벤트 슬라이드
    $(".mainEvent").slick({
        arrows: true,
        dots: false,
        autoplay: false,
        slidesToShow: 3,
	  	slidesToScroll: 1,
        pauseOnHover: true,
        infinite: false,
        responsive:[{
            breakpoint: 1199,
            settings: {
                slidesToShow: 2,
	        	slidesToScroll: 1,
            }
        },{
            breakpoint: 640,
            settings: {
                dots: true,
                slidesToShow: 1,
	        	slidesToScroll: 1,
            }
        }]
    });

    //바로가기 서비스 창크기 작을시 슬라이드
    $(".mainService").slick({
        arrows: false,
        dots: false,
        autoplay: false,
        slidesToShow: 4,
	  	slidesToScroll: 1,
        pauseOnHover: true,
        infinite: false,
        responsive:[{
            breakpoint: 1199,
            settings: {
                arrows: true,
                slidesToShow: 2,
	        	slidesToScroll: 1,
            }
        },{
            breakpoint: 640,
            settings: {
                arrows: true,
                dots:true,
                slidesToShow: 1,
	        	slidesToScroll: 1,
            }
        }]
    });
});

/******** 레이어 팝업 관련 ********/
var getCookie = function (cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
    }
    return "";
}

// 24시간 기준 쿠키 설정하기  
var setCookie = function (cname, cvalue, exdays) {
    var todayDate = new Date();
    todayDate.setTime(todayDate.getTime() + (exdays * 24 * 60 * 60 * 1000));    
    var expires = "expires=" + todayDate.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

var couponClose = function(){
    if($("input[name='chkbox']").is(":checked") == true){
        setCookie("close","Y",1);   //기간( ex. 1은 하루, 7은 일주일)
    }
    $("#pop1").hide();
}
var couponClose2 = function(){
    if($("input[name='chkbox']").is(":checked") == true){
        setCookie("close2","Y",1);   //기간( ex. 1은 하루, 7은 일주일)
    }
    $("#pop2").hide();
}
var couponClose3 = function(){
    if($("input[name='chkbox']").is(":checked") == true){
        setCookie("close3","Y",1);   //기간( ex. 1은 하루, 7은 일주일)
    }
    $("#pop3").hide();
}

/******** 사용안함 2023.04.13 ********/ 
//function jump(num){
//	
//	//imageWin.close();
//	if(num == 1){
//		//window.open("https://www.elife.co.kr/Posm_list01.action?commonMap.CD_BIZ_LND=200026");			
//	}	
//	else if(num == 2){
//		window.open("https://naver.me/GlmPY1fm");
//	}
//	else if(num == 3){
//		window.open("https://bmc.busan.kr/bmc/moveRes/dateConfirm.do");
//	}		
///*	else if(num ==4){
//		window.open("https://www.bmc.busan.kr/bmc/poll/view.do?mId=0309000000&idx=235");
//	}*/
//	else if(num == 5){
//		window.open("https://bmc.busan.kr/bmc/moveRes/ilgwangMove.do");
//	}
//}
//
////팝업
//function Pop_close(num){
//	$("#pop"+num).hide();
//}
//
//
//function todaycloseWin(num){
//
//	if(num ==1){
//		setCookie("popup_cookie1","done",1);
//	}
//	else if(num ==2){
//		setCookie("popup_cookie2","done",1);
//	}
//	else if(num ==3){
//		setCookie("popup_cookie3","done",1);
//	}	
///*	else if(num ==4){
//		setCookie("popup_cookie4","done",1);
//	}*/
//	else if(num ==5){
//		setCookie("popup_cookie5","done",1);
//	}
//	
//	$("#pop1").hide();
//}
///*function setCookie(c_name,value,exdays)
//{
//	var exdate=new Date();
//	exdate.setDate (todayDate.getDate() + exdays);
//	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + exdate.toGMTString() + ";";
//
//	document.cookie=c_name + "=" + c_value;
//}
//*/
//function setCookie( name, value, expire ) {   
//
//	var todayValue = new Date();
//
//	todayValue.setDate(todayValue.getDate() + expire);
//	document.cookie = name + "=" + encodeURI(value) + "; expires=" + todayValue.toGMTString() + "; path=/;";
//
//}

