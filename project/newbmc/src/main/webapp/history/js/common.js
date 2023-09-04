//사이트 공통 스크립트

$(function(){
    var $header = $("#header");
    var $footer = $("#footer");
    var $top = $("#top_nav");
    var $side = $("#side_nav");
    var $back = $("#back");
    var speed = 300;

    var $active = $top.find(">ul>li.active");

    $("#top_nav_btn").on({
        "click" : function(){
            if(!$header.is(".open")){
                $header.addClass("open");
                $back.fadeIn(speed);
            } else {
                $header.removeClass("open");
                $back.fadeOut(speed);
                setTimeout(function(){
                    reset();
                },speed);
            }
        }
    });

    $side.find("button").on({
        "click" : function(){
            if(!$(this).parent().is(".active")){
                $(this).parent().addClass("active");
                $(this).parent().find(".side_nav_sub").stop().slideDown(speed);
            } else {
                $(this).parent().removeClass("active");
                $(this).parent().find(".side_nav_sub").stop().slideUp(speed);
            }
        }
    });

    $footer.find(".family_site > select").on({
        "change" : function(){
            if($(this).val()){
                window.open($(this).val());
            }
        }
    });

    responsive();
    function responsive(){
        var deviceWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        
        $back.off("click").on({
            "click" : function(){
            	$("#top_nav_btn").trigger("click");
            }
        });
        
        if(deviceWidth > 1024){

            $active.addClass("active");

            //Desktop
            $top.find(">ul>li>a").off('mouseenter click').on({
                'mouseenter' : function(){
                    if($(this).parent().is(":has('.top_nav_sub')")){

                        /*$top.find(".top_nav_sub").show();*/

                        var heightArray = new Array();
                        for(var i = 0; i < $top.find(".top_nav_sub").length; i++){
                            heightArray.push($top.find(".top_nav_sub").eq(i).height());
                        }

                        heightArray.sort(function(a, b){
                            return b - a;
                        });

                       /* $top.find(".top_nav_sub").hide();*/
                        $top.find(".top_nav_sub").height(heightArray[0]);
                        $back.height(heightArray[0]).stop().slideDown(speed);

                        $top.find(".top_nav_sub").stop().slideDown(speed);
                    } else {
                    	$top.find(".top_nav_sub").stop().slideUp(speed);
                        $back.stop().slideUp(speed);

                       /* setTimeout(function(){
                            reset();
                        }, speed);*/

                    }
                   $top.find(">ul>li").removeClass("active");
                    $(this).parent().addClass("active");
                }
            });

           $top.off('mouseleave').on({
                'mouseleave' : function(){
                    $top.find(".top_nav_sub").stop().slideUp(speed);
                    $top.find(">ul>li").removeClass("active");
                    $back.stop().slideUp(speed);
                    
                   /* setTimeout(function(){
                        reset();
                    }, speed);*/
                }
            });

        } else
        if(deviceWidth <= 1024) {
            //Tablet & Mobile
        	
            $active.removeClass("active");
            $top.find(">ul>li>a").off('mouseenter click');
            $top.off('mouseleave');
           $top.find(">ul>li>a").off('click').on({
                "click" : function(e){
                    if($(this).parent().is(":has(.top_nav_sub)")){
                        e.preventDefault();
                        if(!$(this).parent().is(".active")){
                            $(this).parent().addClass("active").find(".top_nav_sub").stop().slideDown(speed);
                        } else {
                            $(this).parent().removeClass("active").find(".top_nav_sub").stop().slideUp(speed);
                        }
                    }
                }
            });
           $top.find(">ul>li>.top_nav_sub_btn").off('click').on({
        	   "click" : function(e){
                   if($(this).parent().is(":has(.top_nav_sub)")){
                       
                       if(!$(this).parent().is(".active")){
                           $(this).parent().addClass("active").find(".top_nav_sub").stop().slideDown(speed);
                       } else {
                           $(this).parent().removeClass("active").find(".top_nav_sub").stop().slideUp(speed);
                       }
                   }
               }
           });
           
        }
    }

    function reset(){
        $header.removeClass("open");
      /* $top.find(">ul>li").removeClass("active");*/
       $top.find(".top_nav_sub").removeAttr("style");
        $back.removeAttr("style");
    }


    var timer = null;

    $(window).on("resize", function(){
        reset();
        
        clearTimeout(timer);
        timer = setTimeout( responsive, speed );
    });
    
});