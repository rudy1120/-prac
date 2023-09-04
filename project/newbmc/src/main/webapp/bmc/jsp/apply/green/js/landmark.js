$(document).ready(function(){

/* =============================================================
    기능설명 : slick slider declaration
============================================================= */


function landmarkSlider(landmarkSlider){

    var sliderSingle = landmarkSlider.find('.landmark-slider-single');    
    var sliderNav = landmarkSlider.find('.landmark-slider-nav'); 
    
    sliderSingle.slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: true,
        fade: false,
        adaptiveHeight: true,
        infinite: false,
        useTransform: true,
        speed: 400,
        cssEase: 'cubic-bezier(0.77, 0, 0.18, 1)',
    });
   
    sliderNav
        .on('init', function(event, slick) {
            sliderNav.find('.slick-slide.slick-current').find('img').addClass('is-active');
        })
        .slick({
            slidesToShow: 4,
            slidesToScroll: 1,
            dots: false,
            arrows: true,
            focusOnSelect: false,
            infinite: false,
            responsive: [{
                breakpoint: 1140,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1,
                }
            }, {
                breakpoint: 768,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1,
               }
            }, {
                breakpoint: 576,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
           }
            }]
        });
   
    /*sliderSingle.on('afterChange', function(event, slick, currentSlide) {
        console.log(1);
    	sliderNav.slick('slickGoTo', currentSlide);
    });*/
   
    sliderNav.on('click', '.slick-slide', function(event) {
        event.preventDefault();
    	var goToSingleSlide = $(this).data('slick-index');
    	var currrentNavSlideElem = '.slick-slide[data-slick-index="' + goToSingleSlide + '"]';
    	sliderNav.find('.slick-slide').find('img.is-active').removeClass('is-active');
    	sliderNav.find(currrentNavSlideElem).find('img').addClass('is-active');
        sliderSingle.slick('slickGoTo', goToSingleSlide);
    });
    
    sliderNav.on('click', '.slick-next', function(event){
    	event.preventDefault();
    	var total = $(this).parent(sliderNav).find('img').length;
    	var n = $(this).parent(sliderNav).find('.is-active').parent('div').parent('div').parent('.slick-slide').attr('data-slick-index');
    	if (parseInt(n) < parseInt(total) - 1) {
    		var goToSingleSlide = parseInt(n) + 1;
            landmarkSlider.find('.landmark-slider-nav img').removeClass('is-active');
            $(this).parent(sliderNav).find('.slick-slide[data-slick-index=' +  goToSingleSlide + ']').find('img').addClass('is-active');
            sliderSingle.slick('slickGoTo', goToSingleSlide);
    	}  
    });
    
    sliderNav.on('click', '.slick-prev', function(event){
    	event.preventDefault();
    	var n = $(this).parent(sliderNav).find('.is-active').parent('div').parent('div').parent('.slick-slide').attr('data-slick-index');
    	if (parseInt(n) > 0) {
    		var goToSingleSlide = parseInt(n) - 1;
            landmarkSlider.find('.landmark-slider-nav img').removeClass('is-active');
            $(this).parent(sliderNav).find('.slick-slide[data-slick-index=' +  goToSingleSlide + ']').find('img').addClass('is-active');
            sliderSingle.slick('slickGoTo', goToSingleSlide);
    	} 
    });
  

}
//오시리아관광단지
landmarkSlider($('#landSlider0101'));
landmarkSlider($('#landSlider0102'));
landmarkSlider($('#landSlider0103'));
landmarkSlider($('#landSlider0104'));
landmarkSlider($('#landSlider0105'));
//해운대관광리조트
landmarkSlider($('#landSlider0201'));
//국제산업물류
landmarkSlider($('#landSlider0301'));
//오리일반산업단지
landmarkSlider($('#landSlider0401'));
//일광지구도시
landmarkSlider($('#landSlider0501'));
landmarkSlider($('#landSlider0502'));
landmarkSlider($('#landSlider0503'));
landmarkSlider($('#landSlider0504'));
//임대주택 조경공간
landmarkSlider($('#landSlider0601'));


/* =============================================================
    기능설명 : cont inner height
============================================================= */

    var landmarkTabs = $('li.active').find('a');
    var landmarkTab = landmarkTabs.attr('href');
    $('.landmark-inner').height($(landmarkTab).height()+200);
    $(window).resize(function(){
    	// $('.landmark-inner').height($(landmarkTab).height()+200);
    });

/* =============================================================
    기능설명 : .tab menu function
============================================================= */
    
    function tab_menu2(){
        $('.landmark-cont').eq(0).css({'z-index': 4});
        $('.landmark-tab').click(function(e){
            e.preventDefault();
            if($(this).parent().hasClass('active')){
                return;
            }else{
                $('.landmark-tab').parent().removeClass('active');
                $(this).parent().addClass('active');
            }
            $('.landmark-cont').css({'z-index': -2});
            $(this.hash).css({'z-index': 4});
            $('.landmark-inner').height($(this.hash).height()+200);
        });

    }
    tab_menu2();
    
  
});
    