$(function(){
	
    //서브 메뉴
    /*subNavTouchScroll();
    function subNavTouchScroll(){
        var menuNum = $("#subNav").find(".subNavMenu").find(">a").length;
        $("#subNav").find(".subNavMenu").slick({
            arrows: false,
            dots: false,
            autoplay: false, //true
            slidesToShow: menuNum,
	  	    slidesToScroll: 1,
            speed: 500,
            infinite: false,
            responsive:[{
                breakpoint: 640,
                settings: {
                    slidesToShow: 3
                }
            }]
        });
    }*/

    //산업단지 조성단지
    listPlanLintener();
    function listPlanLintener(){

        var speed = 300;
        var delay = 100;

      $(".listPlan").find(".mapView").on("click", function(e){
            if($(this).parents(".item").is(".active")){
                $(this).parents(".item").find(".view").slideUp(speed);
                $(this).parents(".item").removeClass("active");
                setTimeout(function(){
                    removeAttr($(e.currentTarget));
                },(speed+delay));
                
            } else {
                $(this).parents(".item").find(".view").slideDown(speed);
                $(this).parents(".item").find(".map2").attr("tabindex", 0).focus();
                $(this).parents(".item").addClass("active");
                
            }
        });

        $(".listPlan").find(".btn_hide").on("click", function(){
            if($(this).parents(".item").is(".active")){
                $(this).parents(".item").find(".view").slideUp(speed);
                $(this).parents(".item").removeClass("active");
                $(this).parents(".item").find(".mapView").attr("tabindex", 0).focus();
               
                setTimeout(function(){
                    removeAttr($(e.currentTarget));
                },(speed+delay));
            }
            $(this).parents(".item").find(".mapView").removeAttr("tabindex");
        });

        function removeAttr(e){
            $(e).parents(".item").find(".view").removeAttr("style");
        };
    }
});


/*$(document).ready(function(){
   
    if ( $(".product-tab").length > 0 ) {
        $(window).scroll(function  () {
            var scroll_Top = $(window).scrollTop();
            var startTop = $("#productContent").offset().top - 80;
 
            if ( scroll_Top > startTop ) {
                $(".product-tab").addClass("fixed");
            }else {
                $(".product-tab").removeClass("fixed");
            }
         
         
            var menuCount=$(".product-tab li").size();
            var nav= new Array();
            for(var i=0;i < menuCount;i++){
                nav[i]="nav"+i;
                nav[i]=$($(".product-tab li").eq(i).find("a").attr("href")).offset().top - 131;
            }
             
            $(".product-tab li").each(function  (idx) {
                if( scroll_Top >= nav[idx] ){
                    $('.product-tab li').removeClass('on');
                    $('.product-tab li').eq(idx).addClass('on');
                };
            });
        });
        $(".product-tab li a").click(function  () {
            var goDiv = $($(this).attr("href")).offset().top - 130;
            $("html, body").animate({scrollTop:goDiv},300,"swing");
             
            return false;
        });
    }
});
*/



  
