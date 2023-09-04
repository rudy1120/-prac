//콘텐츠 전용 스크립트

$(function(){
    //텝 스타일 data-function = tab
    $(".tab_style[data-function='tab'] > li > a").on({
        "click" : function(e){
            e.preventDefault();

            var tab = $(this).attr("href");

            if(!$(tab).is(".active") && !$(this).parent().is(".active")){
                
                $(this).parents(".tab_style").find(">li").removeClass("active");
                $(this).parent().addClass("active");
                
                $(".tab_content").removeClass("show");

                setTimeout(function(){
                    $(".tab_content").removeClass("active");
                    $(tab).addClass("active");

                    if($(tab).is(":has('.slide_style')")){
                        $(tab).find(".slide_style").slick('refresh');
                    }

                    setTimeout(function(){
                        $(tab).addClass("show");
                    },100);
                },300);
            }
        }
    });

    $(".slide_style").slick({
        dots: false,
        arrows: true
    });
});