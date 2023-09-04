$(document).ready(function () {

	/* =============================================================
	    기능설명 : mouse:focus remove
	============================================================= */
	
	function usingMouse_lineNone(a){
		$(a).mousedown(function(){
			$(this).addClass('using-mouse');
		});
		$(a).keydown(function(){
			$(this).removeClass('using-mouse');
		});
	}
	usingMouse_lineNone('a');
	usingMouse_lineNone('button');
	
	


/* =============================================================
	    기능설명 : header gnb menu
	============================================================= */


	$('.sideBtn_box').click(function(){
			$(this).attr("title", "전체 메뉴 열기");
			$('.side-bar').show();
			$(this).removeClass('active');

	});

	$('.sideBtn_box').hover(function() {
       $(this).addClass('active');
	}, function() {
       $(this).removeClass('active');
	});
	
	
	
	$('.side-close-btn').click(function(){
		$('.side-bar').fadeOut(300);
	});
	


	// sidemenu pc & mobile
	var eventPresent = false;
	function sidemenu(){
		if ($(window).width()<	1400 && eventPresent == false) {
			
			$('.side-menu .gnb-2depth').hide();
			$('.side-menu .gnb-1depth > li > a').removeClass('active');
			$('.side-menu .gnb-1depth > li > a').unbind().each(function(){
				$(this).click(function(e){
					e.preventDefault();

					// $(this).toggleClass('active');
					if($(this).hasClass('active')){
						$(this).removeClass('active');
						$(this).next('.gnb-2depth').stop().slideUp();
					}else{
						$('.side-menu .gnb-1depth > li > a').removeClass('active');
						$('.side-menu .gnb-2depth').stop().slideUp();
						$(this).addClass('active');
						$(this).next('.gnb-2depth').stop().slideDown();
					}
				});
			});

			eventPresent  = true;
	
		}else if ($(window).width()>= 1400 && eventPresent == true){   
			$('.side-bar').hide();         
			eventPresent = false;     
		}
	}

	sidemenu();
	$(window).resize(function(){
		sidemenu();
	});

	$(".lag").on('mouseenter', function() {
		$('.lag_slide').css("display","block");
	});
	$(".lag").on('mouseleave', function() {
		$('.lag_slide').css("display","none");
	});



// pc open .gnb-2depth
	$('.gnb .gnb-1depth > li').each(function(){
		$(this).mouseenter(function(){
			$(this).find('.gnb-2depth').stop().slideDown(400,function(){
				$(this).addClass('active');
			});
		});
		$(this).focusin(function(){
			$(this).find('.gnb-2depth').stop().slideDown(400,function(){
				$(this).addClass('active');
			});
		});
        $(this).mouseleave(function(){
			$(this).find('.gnb-2depth').stop().slideUp(300,function(){
				$(this).removeClass('active');
			});
		});
		$(this).focusout(function(){
			$(this).find('.gnb-2depth').stop().slideUp(300,function(){
				$(this).removeClass('active');
			});
		});
		
	});




/* =============================================================
	   메인 슬로건 색상변경
	============================================================= */

 var swiper1 = new Swiper(".swiper1", {
        spaceBetween: 30,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
		autoplay: {
        delay: 2500,
        disableOnInteraction: false,
		 },effect: 'fade',
        fadeEffect: {
        crossFade: true
		},
		navigation: {
	      nextEl: ".swiper-button-next",
	      prevEl: ".swiper-button-prev",
	    },
		autoplay:false,
		autoHeight : true
      });

	var swiper2 = new Swiper(".swiper2", {
        spaceBetween: 30,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
		autoplay: {
        delay: 2500,
        disableOnInteraction: false,
		 },effect: 'fade',
        fadeEffect: {
        crossFade: true
		},
		autoplay:false,
      });

    var swiper3 = new Swiper(".swiper3", {
        spaceBetween: 30,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
		autoplay: {
        delay: 2500,
        disableOnInteraction: false,
		 },effect: 'fade',
        fadeEffect: {
        crossFade: true
		},
		autoplay:false,
      });
	var swiper4 = new Swiper(".swiper4", {
        spaceBetween: 30,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
		autoplay: {
        delay: 2500,
        disableOnInteraction: false,
		 },effect: 'fade',
        fadeEffect: {
        crossFade: true
		},
		autoplay:false,
      });

  $('.swiper1 .btn-stop').click(function(){
      if($(this).hasClass('on')){
        swiper1.autoplay.start();
        $(this).removeClass('on');
        return false;
      }else{
        swiper1.autoplay.stop();
        $(this).addClass('on');
        return false;
      }
    });
	$('.swiper2 .btn-stop').click(function(){
      if($(this).hasClass('on')){
        swiper2.autoplay.start();
        $(this).removeClass('on');
        return false;
      }else{
        swiper2.autoplay.stop();
        $(this).addClass('on');
        return false;
      }
    });
	$('.swiper3 .btn-stop').click(function(){
      if($(this).hasClass('on')){
        swiper3.autoplay.start();
        $(this).removeClass('on');
        return false;
      }else{
        swiper3.autoplay.stop();
        $(this).addClass('on');
        return false;
      }
    });
	$('.swiper4 .btn-stop').click(function(){
      if($(this).hasClass('on')){
        swiper4.autoplay.start();
        $(this).removeClass('on');
        return false;
      }else{
        swiper4.autoplay.stop();
        $(this).addClass('on');
        return false;
      }
    });



	$(window).load(function(){
	  	$('.sVisual').addClass('on');
	});


$('.sNav').mouseenter(function() {
	$(this).addClass('active');
	$(this).find('li:nth-child(2)').addClass('on');
}); 
$('.sNav').mouseleave(function() {
	$(this).removeClass('active');
	$(this).find('li:nth-child(2)').removeClass('on');
});

var controller = new ScrollMagic.Controller();
  
  var tween1 = TweenMax.to('.ceo', 0.5, {
	  opacity:1,
   x:-70,
  });
  var tween2 = TweenMax.to('.ky', 0.5, {
opacity:1,
   x:-20,
    });
  var scene = new ScrollMagic.Scene({
    triggerElement: "#trg",
		offset:-50,
    duration: "30%"
  })
  .setTween(tween1)
  .addTo(controller); 
   var scene = new ScrollMagic.Scene({
    triggerElement: "#trg",
		offset:120,
    duration: "30%"
  })
  .setTween(tween2)
  .addTo(controller); 




});

function openModal() {
  document.getElementById("myModal").style.display = "block";
}

function closeModal() {
  document.getElementById("myModal").style.display = "none";
}

var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  var captionText = document.getElementById("caption");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
  captionText.innerHTML = dots[slideIndex-1].alt;
}
