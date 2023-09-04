$(function(){
    //메인/서브 공통 스크립트
    
    var device = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth; //현재 창 가로길이

    //헤더/푸터 셀렉트 박스 링크이동 
	//[2019.10.22] 웹접근성 작업. 이동버튼을 통해서 새창 열리도록 수정.
    /*$("#language, .selectLinkList1, .selectLinkList2").on("change", function(){
        if($(this).val() != ""){
            location.href = $(this).val();
        }
    });*/
    
    //개인정보처리방침 select 이동 
    $("#GoPre").on("click", function(){
    	var lnk = $("#privacy_select").val();
    	if(lnk != "") window.open(lnk, '_blank', 'width=800,height=800,scrollbars=yes');
    });
    //최상단 바로가기 버튼
    $("#top").on("click", function(){
        $("html, body").animate({scrollTop:0},'fest');
    });
    
    //[2019.10.22] 웹접근성 작업. 키보드로 메뉴 이동시 마지막메뉴 포커스아웃과 함께 헤더 사라져야.
    $(".topNavSub").last().find('li').last().focusout(function(){
    	$(this).mouseleave();
    });
    
    //[2019.10.22] 웹접근성 작업.
    $("#btnGo1").on("click", function(){
    	var lnk = $("#selectLinkList1").val();
    	if(lnk != "") window.open(lnk, '_blank');
    });
    $("#btnGo2").on("click", function(){
    	var lnk = $("#selectLinkList2").val();
    	if(lnk != "") window.open(lnk, '_blank');
    });
    
    //[2019.11.08] print 기능 추가
    $("#print").click(function () {
        /*var $container = $("#subContents").clone(); //프린트 할 특정 영역 복사
        var cssText = ""; //스타일 복사
        for (const node of $("style")) {
            cssText += node.innerHTML
        };
        /** 팝업 */
        /*var innerHtml = $container[0].innerHTML;
        var popupWindow = window.open("", "_blank", "width=700,height=800");
        popupWindow.document.write("<!DOCTYPE html>"+
          "<html>"+
            "<head>"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/bmc/css/base.css\">"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/bmc/css/board2.css\">"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/bmc/css/common.css\">"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/bmc/css/common2.css\">"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/bmc/css/content.css\">"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/bmc/css/elastislide.css\">"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/bmc/css/layout.css\">"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/bmc/css/main.css\">"+
            "<style>"+cssText+"</style>"+
            "</head>"+
            "<body>"+innerHtml+"</body>"+
          "</html>");
       
        popupWindow.document.close();
        popupWindow.focus();

        setTimeout(() => {
            popupWindow.print(); //팝업의 프린트 도구 시작
            popupWindow.close(); // 프린트 도구 닫혔을 경우 팝업 닫기
        }, 1000);*/
    	
    	var popupWindow = window.open("/common/printPage.jsp", "_blank", "width=1250,height=800");
        
    });
    
    //hearderTop 주요사업사이트 셀렉트박스 이벤트
	$('.familySite > a.btn_family').click(function(ignore) {
		if($(this).hasClass('open')){
			$(this).removeClass('open');
			$(this).next().removeClass('open');
		}else{
			$(this).addClass('open');
			$(this).next().addClass('open');
		}
	    ignore.preventDefault();
	});

    //반응형 스크립트
    responseNav(); //초기 실행 
    function responseNav(){
        //엘리멘트 변수
        var $header = $("#header");
        var $nav = $("#topNav");
        var $menu = $nav.find(".topNavMenu>li>a");
        var $back = $("#header").find(".topNavBack");
        var $navBtn = $("#navBtn");
        var $gnbWrap = $(".gnb_wrap");
        var $srchBtn = $("#srchBtn");
        var $search = $("#search");
        var $engBtn = $('#engBtn');
        
        //데이터 변수
        var responseWidth = 1200;
        var mobile = 960;
        var slideSpeed = 200;
        
        //인라인 스타일 리셋
        $back.removeAttr("style");
        $nav.find(".menuOpenBtn").parent().find(">ul").removeAttr("style");
        $(".topNavSub").removeAttr("style");

        //활성화 리셋
        $header.removeClass("active");
        $back.removeClass("active");
        $nav.removeClass("active");
        $nav.find(".topNavMenu li").removeClass("active");
        $(".topNavSub").removeClass("active");
        $gnbWrap.removeClass("active");
        
        //반응형 스크립트 구분
        if(device < responseWidth){ //모바일 & 태블릿
        	//pc에서 적용되어있던 이벤트 제거
            $menu.off("mouseenter.pc");
            $menu.off("focus.pc");
            $nav.off("mouseleave.pc");
            $srchBtn.show();
            
            //20191203 모바일 검색 창 이벤트 추가
            $srchBtn.off("click").on({
            	"click" : function(){
                  if($search.is(".active")){
                	  //$("#headerInnrDiv").remove("#search");
                	  $("#search").hide();
                	  $search.removeClass("active");
                  }else{
                	  $("#search").show();
                	  $("#headerInnrDiv").append($header.find("#search"));
                	  $search.addClass("active");
                  }
                }
            });
            
            //모바일 메뉴 이벤트
            $navBtn.off("click").on({
                "click.toggle" : function(){
                    if($header.is(".active")){
                        $(this).text("메뉴열기");
                        $header.removeClass("show");
                        $nav.removeClass("active");
                        $gnbWrap.removeClass("active");
                        setTimeout(function(){
                            $header.removeClass("active");
                        },300);
                        // body 스크롤 풀기
                        $('body').removeClass('scrollOff').off('scroll touchmove mousewheel');
                        //검색창 보이게
                	    $srchBtn.show();
                       

                    } else {
                        $(this).text("메뉴닫기");
                        $header.addClass("active");
                        $nav.addClass("active");
                        $gnbWrap.addClass("active");
                        setTimeout(function(){
                            $header.addClass("show");
                        },10);
                        // body 스크롤 막음
                	    $('body').addClass('scrollOff').on('scroll touchmove mousewheel', function(e){
                	       e.preventDefault();
                	    });
                	    //검색창 숨기기
                	    $srchBtn.hide();
                	    $("#search").hide();
                  	    $search.removeClass("active");
                    }
                }
            });

            //검은 바탕 클릭시 메뉴 닫기
            $back.off("click").on({
                "click" : function(){
                    $navBtn.trigger("click");
                }
            });
            
            //모바일 3,4뎁스 없애기
            $(".NavSub").find(".depth3").remove();
            $(".NavSub").find(".depth4").remove();
            
            $(".topNavMenu li a").off("click").on("click", function(e) {
            	if ($(this).parent().find(".menuOpenBtn").length > 0) {
            		e.preventDefault();
            	}
            	$(this).parent().find(".menuOpenBtn").first().trigger("click");
            });
            
            //하위메뉴 버튼 이벤트
            $nav.find(".menuOpenBtn").off("click").on("click", function(){
            	if($(this).parent().is(".active")){
                    $(this).parent().removeClass("active");
                    $(this).parent().find(">ul").stop().slideUp(slideSpeed);
                } else {
                    $(this).parent().addClass("active");
                    $(this).parent().find(">ul").stop().slideDown(slideSpeed);
                }
                if($(this).parent().siblings().is(".active")){
                    $(this).parent().siblings().removeClass("active");
                    $(this).parent().siblings().find(">ul").stop().slideUp(slideSpeed);
                }
            });

            //only 모바일 : 로그인 버튼, 검색 창 이동
            if(device <= mobile){
                //$nav.prepend($header.find("#search"));
                $nav.prepend($header.find("#utils"));
            } else {
                $header.find(".headerTop .headerInnr").append($header.find("#utils"));
                $header.find(".headerTop .headerInnr").append($header.find("#search"));
            }

            //20221103 모바일 영문 홈페이지 이동 이벤트 추가
            $engBtn.off('click').on({
            	'click' : function(){
            		location.href='/eng/main.do';
                }
            });
            
        } else {
            //테블릿 & 모바일 이벤트 제거
            $navBtn.off("click.toggle");
            $back.off("click");
            $nav.find(".menuOpenBtn").off("click");
            $(".topNavMenu li a").off("click");
            
            //수정!!
            $srchBtn.hide();
            //pc메뉴 이벤트
            $menu.off("mouseenter").on({
                "mouseenter.pc" : function(){
                    $(".topNavSub").stop().slideUp(slideSpeed);
                    $(".topNavSub").removeClass("active");
                    // $back.stop().slideUp(slideSpeed);
                    $back.removeClass("active");

                    $menu.parent().removeClass("active");
                    $(this).parent().addClass("active");

                    if($(this).parent().is(":has(.topNavSub)")){
                        var $sub = $(this).parent().find(".topNavSub");

                        $sub.css("display","block");

                        ///$back.height($sub.outerHeight());
                        $back.height(560);
                        $back.stop().slideDown(slideSpeed);
                        $back.addClass("active");

                        $sub.removeAttr("style");

                        $sub.stop().slideDown(slideSpeed);
                        $sub.addClass("active");
                    }
                }
            });

            //웹 접근성 메뉴 탭이동시 동작
            $menu.off("focus").on({
                "focus.pc": function(){
                    $(this).trigger("mouseenter");
                }
            });

            //메뉴 감추기
            $nav.off("mouseleave").on({
                "mouseleave.pc" : function(){
                    $(".topNavSub").stop().slideUp(slideSpeed, function(){
                        $(this).removeClass("active").removeAttr("style");
                    });
                    $(".topNavSub").removeClass("active");
                    $back.stop().slideUp(slideSpeed, function(){
                        $(this).removeClass("active").removeAttr("style");
                    });
                }
            })

            //로그인 및 검색창 이동
            $header.find(".headerTop .headerInnr").append($header.find("#utils"));
            $header.find(".headerTop .headerInnr").append($header.find("#search"));
        }
    }

    //이벤트 중복 방지 :: 과부화 방지 :: 리사이징이 끝나고 0.5초후 리사이즈 이벤트 실행
    var responseTimer = null;

    $(window).on("resize", function(){
        if(device != (window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth)){
            clearTimeout(responseTimer);
            responseTimer = setTimeout(responseNav, 500);
            device = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth; //현재 창 가로길이
        }
    });
    
    
});

//-----------------------------------------------------------------------------------------
//select box(related site box)
//-----------------------------------------------------------------------------------------
var SelectBox2 = function(){
    var self;
    var $relatedTitle, $relatedList;
    return {
        init: function(){
            self = this;
            $relatedTitle = $(".related-site-title");
            $relatedList = $(".related-site");
            $relatedTitle.click(self.onClick);

            $(".related-site-wrap").each(function(){
                var $thisBox = $(this);

                $(document).mouseup(function(e){
                    if($thisBox.has(e.target).length === 0){
                        $thisBox.find(".related-site-title").removeClass("active");
                        $thisBox.children("ul").hide();
                    }
                });

            });

        },
        onClick: function(){
            $relatedList.toggle();
            $relatedTitle.toggleClass("active", "");
        }
    }
}();

//-----------------------------------------------------------------------------------------
//select box(path)
//-----------------------------------------------------------------------------------------
var SelectBoxPath = function () {
    var self;
    var $path;
    return {
        init: function () {
            self = this;

            $path = $(".path-depth-wrap > ul > li");
            $path.each(function () {
                var $thisBox = $(this);
                $thisBox.find(".path-selected").click(self.onClick);

                $(document).click(function(e){
                    if($thisBox.has(e.target).length === 0){
                        $(".path-selected").parent().removeClass("active");
                        $(".path-depth").slideUp(200);
                        $(".path-selected").attr("title","펼치기");
                    }
                });
            });

        },
        onClick: function () {
        	 //var x = $('.path-depth').css('display');
             var x =  $(this).parents("li").find(".path-depth").css('display');
             if(x !='block'){
             	$(this).attr("title","접기");
             	
             }else{
            	 $(this).attr("title","펼치기");
            	 
             }
            $(this).parents("li").find(".path-depth").slideToggle(200);
            $(this).parents("li").toggleClass("active");
           
            
            return false;
        }
    }
}();


$(document).ready(function() {  
    if(window.innerWidth <= 1024) {
        $(".intro-box").hide();
        $(".wrap").removeClass("active");
        $(".wrap").removeClass("over-hidden");
    }


    setTimeout(function(){
        $('html').removeClass('no-js');
    }, 300);

    
    SelectBox2.init();
    SelectBoxPath.init();   
   
});

/*//////////////////////////////////////////////////////////////*/

// JavaScript Document
// $(document).ready(function() {      

//     $('#topNav ul > li > a').on('mouseover',function(){
//         $(this).next().addClass('open');
//         $(this).parent().siblings('li').find('div').removeClass('open');
//     });
//     $('#topNav ul > li:last-child > div > div > ul > li:last-child  > a').on('focusout',function(){
//         $('.sub_menu_wrap').removeClass('open');
//     });
//     $('#topNav ul > li > a').on('focus',function(){
//         $(this).next().addClass('open');
//         $(this).parent().siblings('li').find('div').removeClass('open');
//     });
    
//     $('.tnb_wrap,section').on('mouseover',function(){
//         $('.sub_menu_wrap').removeClass('open');
//     });
//     $('.sub_menu_wrap').on('mouseleave',function(){     
//         $(this).removeClass('open');
//     });
// });
