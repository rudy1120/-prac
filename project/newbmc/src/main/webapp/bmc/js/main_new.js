
    $(function () {
  	
/*	common.js 로 옮김
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
*/
	
    $(".visual_slide").slick({
        dots: true,
        arrows: false,
        autoplay: true,
        accessibility: false //슬라이더 포커싱 제외
    });

    $(".section2_slide").slick({
        dots: false,
        arrows: true,
        autoplay: true
    });


    $(".menu_content").slick({
        dots: false,
        arrows: true,
        infinite: false
    });

//    $(".osiria_banner").slick({
//    	dots: true,
//        arrows: false,
//        slidesToShow: 1,
//        slidesToScroll: 1,
//        autoplay: true
//    });
    
    $(".businessIcon_slide").slick({
        arrows: true,
        slidesToShow: 6,
        slidesToScroll: 1,
        autoplay: true,
        responsive: [{
            breakpoint: 1024,
            settings: {
                slidesToShow: 5
            }
        },{
            breakpoint: 768,
            settings: {
                slidesToShow: 3
            }         
        },{
            breakpoint: 400,
            settings: {
                slidesToShow: 2
            }
        }]
    });

    $(".mainNotice .noticeTab button").on("click", function () {
        $this = $(this);

        $(".noticeTab > li").removeClass("show");
        $('.noticeTab > li > button').each(function(index, item) {
        	$(item).attr('title', $(item).html());
        });
        $(this).attr('title', $(this).html() + ' 선택됨');
        setTimeout(function () {
            $(".noticeTab > li").removeClass("active");
            $this.parent().addClass("active");

            setTimeout(function () {
                $this.parent().addClass("show");
            }, 200);
        }, 200);
    });


    visualControlls();
    function visualControlls() {
        if (!$(".visual_slide").is(":has(.slick-controls)")) {
            $(".visual_slide").append("<div class='slick-controls'><span>1 / " + $(".visual_slide").find(".banner").not(".slick-cloned").length + "</span><button class='slick-play play'>자동재생 정지하기</button></div>");
            $(".visual_slide").find(".slick-controls").prepend($(".visual_slide").find(".slick-prev")).append($(".visual_slide").find(".slick-next"));
            $(".visual_slide").on("afterChange", function (event, slick, currentSlide, nextSlide) {
                $(this).find(".slick-controls > span").text((currentSlide + 1) + " / " + $(".visual_slide").find(".banner").not(".slick-cloned").length);
            });
        }
    }

    visualmenuControlls();
    function visualmenuControlls() {
        if (!$(".menu_content").is(":has(.slick-controls)")) {
            $(".menu_content").append("<div class='slick-controls'><span>1 / " + $(".menu_content").find(".menu_box").not(".slick-cloned").length);
            $(".menu_content").find(".slick-controls").prepend($(".menu_content").find(".slick-prev")).append($(".menu_content").find(".slick-next"));
            $(".menu_content").on("afterChange", function (event, slick, currentSlide, nextSlide) {
                $(this).find(".slick-controls > span").text((currentSlide + 1) + " / " + $(".menu_content").find(".menu_box").not(".slick-cloned").length);
            });
        }
    }
    section2Controlls();
    function section2Controlls() {
        if (!$(".section2_slide").is(":has(.slick-controls)")) {
            $(".section2_slide").append("<div class='slick-controls'><span>1 / " + $(".section2_slide").find(".banner").not(".slick-cloned").length + "</span><button class='slick-play play'>자동재생 정지하기</button></div>");
            $(".section2_slide").find(".slick-controls").prepend($(".section2_slide").find(".slick-prev")).append($(".section2_slide").find(".slick-next"));
            $(".section2_slide").on("afterChange", function (event, slick, currentSlide, nextSlide) {
                $(this).find(".slick-controls > span").text((currentSlide + 1) + " / " + $(".section2_slide").find(".banner").not(".slick-cloned").length);
            });
        }
    }

    section03Controlls();
    function section03Controlls() {
        if (!$(".businessIcon_slideWrap").is(":has(.slick-play)")) {
            $(".businessIcon_slideWrap").find(".section_wrap").append("<div class='slick-controls'><button class='slick-play play'>자동재생 정지하기</button></div>");
        }
        $(".businessIcon_slideWrap").find(".slick-controls").prepend($(".businessIcon_slide").find(".slick-prev")).append($(".businessIcon_slide").find(".slick-next"));
    }

    slidePlayAndPause(".visual_slide");
    slidePlayAndPause(".section2_slide");
    slidePlayAndPause(".businessIcon_slideWrap");
    function slidePlayAndPause(event) {
        $(event).find(".slick-play").off("click").on("click", function () {
            if ($(this).is(".play")) {
                $(this).removeClass("play").text("자동재생하기").parents(".slick-slider").slick("slickPause");
            } else {
                $(this).addClass("play").text("자동재생 정지하기").parents(".slick-slider").slick("slickPlay");
            }
        })
    }

    function groupEvents(){
        //section04Controlls();
        slidePlayAndPause(".businessIcon_slide");
        slidePlayAndPause(".section2_slide");
        slidePlayAndPause(".visual_slide");
    }

    var eventTimer = null;

    $(window).on("resize", function (){
        clearTimeout(eventTimer);
        setTimeout(groupEvents, 500);
    });
    
    $('.menu_box').removeAttr('tabindex');
    
    $('.mainSection1_btn').focus(function() {
    	$('.mainSection1_btn').attr('title', $(this).html());
    	$(this).attr('title', $(this).html() + ' 선택됨');
    });
    
    $('.mainSection1_btn').blur(function() {
    	$(this).attr('title', $(this).html());
    });
});




