//Common
$(function() {
	
	var $topSearchBar = $(".topSearchBar");
	
	//skipNav
	$("#skipNav a").click(function(e){
		e.preventDefault();
    	/*var skipTo="#"+this.href.split('#')[1];*/
		var skipTo= $(this).attr("href");
		$(skipTo).attr("tabindex",0).focus().removeAttr("tabindex");
		$("html, body").scrollTop(0);
		/*$("html, body").scrollTop($(skipTo).offset().top - $("#header").height());*/
	});

	//familySite select btn
	$(".familySite").find(".btnGo").click(function(){
		if($("select[name="+$(this).attr("id")+"]").val() == "") {
			e.preventDefault;
		} else {
			window.open($("select[name="+$(this).attr("id")+"]").val(), "_blank", "");
		}
	});


	
});

//230405 lnb-1depth_item 클릭시 lnb-2depth_item 표출
$(function () {
  const lnbItem = $(".lnb-1depth_item > a");
  const lnb2depth = $(".lnb-2depth");

  lnbItem.each(function (idx) {
    $(this).on("click", function (e) {
      e.preventDefault();
      if ($(this).next(lnb2depth).hasClass("on")) {
        $(this).next(lnb2depth).removeClass("on");
      } else {
        $(this).next(lnb2depth).addClass("on");
      }
    });
  });
});

//230406 .map_tab ul li a 클릭시 sub_map tab_content 표출
$(function () {
  $(".map_tab ul li a").on("click", function (e) {
    e.preventDefault();
    const activeTab = $(this).attr("href");
    const $title = $(this).children("span").text();

    if ($(".map_tab ul li a").hasClass("on")) {
      $(".map_tab ul li a").removeClass("on");
      $(".map_tab ul li a").removeAttr("title");
      $(".map_tab ul li a").children("em").remove();
      $(".tab_content").removeClass("on");

      $(this).addClass("on");
      $(activeTab).addClass("on");
      $(this).attr("title", $title + " 선택됨");
      $(this).append("<em>" + $title + " 선택됨</em>");
    }
  });
});

//topNav
$(function() {
	var $header = $('#header');
	var $nav = $('#topNav');
	var $navDepth1 = $nav.find('ul.topNavMenus > li');
	var $navDepth1a = $nav.find('ul.topNavMenus > li > a');
	//btnNav
	$header.find('.btnNav').on('click', function() {
		if(!$(this).is(".opened")){
			$(this).addClass("opened");
			
			$nav.addClass('opened').attr({'tabindex':'0','hidefocus':'true'}).css('outline','0').focus();
			
			if (!$header.is(':has(.topNavBackDv)')) {
				$header.append('<div class="topNavBackDv" />');
			}
			
			$('body').addClass('opened-topNav');
			
			$('.topSearchBar').removeClass('opened');
			
			setTimeout(function(){
				$nav.addClass("fadein")
			},0);
		} else {
			$(this).removeClass("opened");
			
			$nav.removeClass('fadein');
			
			$('.topNavBackDv').remove();
			
			$('body').removeClass('opened-topNav');
			
			$header.find('.btnNav').focus();
			
			setTimeout(function(){
				$nav.removeClass("opened");
			},250);
		}
	});
	
	$(document).on('click', '.topNavBackDv', function() {
		$nav.find('.btnNav.opened').trigger('click');
	});

	//btnSearch
	$header.find('.btnSearch').off('click').on('click', function() {
		$nav.removeClass('opened fadein');
		$header.find('.btnNav.opened').removeClass("opened");
		$('.topNavBackDv').remove();
		$('body').removeClass('opened-topNav');
		$('.topSearchBar').addClass('opened');
		setTimeout(function(){
			$('.topSearchBar').addClass("fadein");
		},10);
	}).end().find('.topSearchBar .btnSearchClose').on('click', function() {
		$('.topSearchBar').removeClass('fadein');
		setTimeout(function(){
			$('.topSearchBar').removeClass('opened');
		},200);
	});
	
	
	//btnMenuDropDown
	$navDepth1.removeClass('hover').css('height','').find(' > .btnMenuDropDown').on('click', function() {
		if ($(this).parent('li').is('.active') || $(this).parent('li').is('.activeHover')) {
			$(this).parent('li').find(".topNavSub").stop().slideUp(100, function(){
				$(this).parent('li').removeClass('active activeHover').removeAttr("style");
			});
			$(this).html("하위메뉴 축소됨");
		} else {
			$navDepth1.not(".topNavArea").removeClass('active activeHover').removeAttr("style").find(".topNavSub").slideUp(100);
			$(this).parent('li').find(".topNavSub").stop().slideDown(100, function(){
				$(this).parent("li").addClass("active activeHover");
			});
			$(this).html("하위메뉴 확장됨");
		}
	});
	
	$navDepth1.find(".btnMenuDropDown").each(function(){
		if($(this).parent("li").is(".active") || $(this).parent("li").is(".activeHover")){
			$(this).text("하위메뉴 확장됨");
		} else {
			$(this).text("하위메뉴 축소됨");
		}
	});

	//keyboard accessibility
	$nav.find('li:first >a').on('keydown', function(e) {
		if ($header.find('.btnNav').is(':hidden')) {
			if (e.shiftKey && e.keyCode == 9) {
				$(this).focusout(function() {
					$navDepth1.removeClass('hover');
					$('.topNavBackDv').removeClass('opened');
				});
			} else if (!e.shiftKey && e.keyCode == 9){
				$(this).off('focusout');
			}
		} else {
			$(this).off('focusout');
		}
	});
	
	$nav.find('ul.topNavMenus>li:not(.topNavSearch) li:last >a').on('keydown', function(e) {
		if ($header.find('.btnNav').is(':hidden')) {
			if (!e.shiftKey && e.keyCode == 9) {
				$(this).focusout(function() {
					$navDepth1.removeClass('hover').find(".topNavSub").removeAttr("style");
					$('.topNavBackDv').removeClass('opened');
				});
			} else if (e.shiftKey && e.keyCode == 9){
				$(this).off('focusout');
			}
		} else {
			$(this).off('focusout');
		}
	});
	
	$navDepth1.on("blur fucusout", function(){
		$navDepth1.removeClass('hover');
		$('.topNavBackDv').removeClass('opened');
	});
	
	respondTopNav();
	
	function respondTopNav() {
		var mqWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
		var topNavScroll;
			
		//topnav mobile ver
		if (mqWidth < 769) {
			
			//console.log('mobile');
			if (!$nav.is(':has(ul.topNavMenus>li.hover)')) {
				if ($nav.is(':has(ul.topNavMenus>li.active)')) {
					$navDepth1.removeClass('hover').css('height','');
					$nav.find('ul.topNavMenus>li.active').addClass('activeHover');
				} else {
					funcTopNavMobileInit();
				}
			}

			$header.find('.btnNav').off('click.topNavEventTablet click.topNavEventMobile');
			$header.find('.btnNav').on('click.topNavEventMobile', function() {
				if ($nav.is(':has(ul.topNavMenus>li.active)')) {
					$navDepth1.removeClass('hover').css('height','');
					$nav.find('ul.topNavMenus>li.active').addClass('activeHover');
				} else {
					funcTopNavMobileInit();
				}
			});
			
			$navDepth1.find('>a').off('mouseenter.topNavEventPc focus.topNavEventPc click.topNavEventTablet focus.topNavEventTablet');
			$navDepth1.css('height','').find('.topNavSub>ul>li').css('height','');
			$nav.off('mouseleave.topNavEventPc');

			function funcTopNavMobileInit() {
				/*$navDepth1.removeClass('hover').css('height','').find(".topNavSub").removeAttr("style");*/
			}
		}

		//topnav tablet ver
		else if (mqWidth >= 769 && mqWidth < 1200) {
			//console.('tablet');
			

			if (!$nav.is(':has(ul.topNavMenus>li.hover)')) {
				if ($nav.is(':has(ul.topNavMenus>li.active)')) {
					$navDepth1.removeClass('hover').find(".topNavSub").removeAttr("style");
					$nav.find('ul.topNavMenus>li.active').addClass('activeHover');
				}
			}

			$header.find(".topNavSub").removeAttr("style");
			$header.find('.btnNav').off('click.topNavEventMobile click.topNavEventTablet');
			$header.find('.btnNav').on('click.topNavEventTablet', function() {
				if ($header.find('.btnNav').hasClass("opened")) {
					$navDepth1.removeClass('hover').find(".topNavSub").removeAttr("style");
					
					if(!$header.is(":has(.topNavBackDv)")){
						$header.append("<div class='topNavBackDv'/>");
						$header.find(".topNavBackDv").addClass("opened");
					} else {
						$header.find(".topNavBackDv").addClass("opened");
					}
				}
			});

			$navDepth1.find('>a').off('mouseenter.topNavEventPc focus.topNavEventPc');
			
			$nav.off('mouseleave.topNavEventPc');

		}

		//topnav pc ver
		else if (mqWidth >= 1200) {
			//console.log('pc');
			var speed = 200;
			$navDepth1a.off('click');
			
			$navDepth1.find(".tobNavSub").removeAttr("style").find(">ul>li").removeAttr("style");
				
			$('.topNavBackDv').removeClass('opened');
			$navDepth1.find('>a').off('click.topNavEventTablet focus.topNavEventTablet mouseenter.topNavEventPc focus.topNavEventPc');
			$navDepth1.removeClass('hover').removeAttr("style").find('>a').on('mouseenter.topNavEventPc focus.topNavEventPc', function() {
				$header.addClass("white");
				if (!$header.is(':has(.topNavBackDv)')) {
					$header.append('<div class="topNavBackDv"></div>');
				}
				if(!$header.find(".topNavBackDv").is(":has(.topNavTit)")){
					$header.find(".topNavBackDv").append("<div class='topNavTit'><h2></h2></div>");
				}
				var topNavTit = $(this).children().text();
				$header.find(".topNavTit h2").text(topNavTit);
				
				var topNavTitWidth = (mqWidth - $(".headerInnr").width()) / 2;
				
				$(".topNavTit").width(360 + topNavTitWidth).children().css("left",topNavTitWidth + 20);/* 하위 펼침메뉴 1Depth 넓이 지정 */
				
				$(this).parent('li').addClass('hover').find(".topNavSub").css("dispaly","block");
				
				var navHeight = $(this).parent('li').find(".topNavSub").outerHeight();
				
				$navDepth1.removeClass('hover').find(".topNavSub").removeAttr("style");
				
				$(this).parent('li').addClass('hover').find(".topNavSub").stop().slideDown(speed);
				
				
				var menuNo = $(this).parent().prevAll().length;
				
				/* 하위 펼침메뉴 높이 지정 */
				if(navHeight < 340) {
					navHeight = 340;
				}
				
				/* 하위 펼침메뉴 바탕 이미지 지정 */
				if (!$(this).parent('li').is(':has(.topNavSub)')) {
					$('.topNavBackDv').stop().animate({"height":0},speed).removeClass('opened areaType');
				} else if ($(this).parent('li').is('.topNavArea')) {
					$('.topNavBackDv').addClass('areaType').stop().animate({"height":navHeight},speed).addClass('opened').find(".topNavTit");
				} else {
					$('.topNavBackDv').removeClass('areaType').stop().animate({"height":navHeight},speed).addClass('opened').find(".topNavTit");
					var topNavSubHeight1 = new Array();
					var topNavSubHeight2 = new Array();
					for(var i = 0; i < 5; i++){
						topNavSubHeight1.push($(this).parent().find(".topNavSub > ul > li").eq(i).height());
						topNavSubHeight1.sort(function(a, b){
							return b - a;
						});
					}
					for(var i = 5; i < 10; i++){
						topNavSubHeight2.push($(this).parent().find(".topNavSub > ul > li").eq(i).height());
						topNavSubHeight2.sort(function(a, b){
							return b - a;
						});
					}
					for(var i = 0; i < 5; i++){
						$(this).parent().find(".topNavSub > ul > li").eq(i).height(topNavSubHeight1[0]);
					}
					for(var i = 5; i < 10; i++){
						$(this).parent().find(".topNavSub > ul > li").eq(i).height(topNavSubHeight2[0]);
					}
					
				}
				
				$navDepth1.find('.topSearchBar').removeClass('opened');
				
			}).end().find('.topNavSub>ul>li').css('height','');
			
			$nav.on('mouseleave.topNavEventPc', function() {
				$navDepth1.removeClass('hover').find(".topNavSub").stop().slideUp(speed).removeAttr("style");
				$('.topNavBackDv').stop().animate({"height":0},speed, function(){
					$('.topNavBackDv').removeClass('opened areaType').removeAttr("style").find(".topNavTit").removeAttr("style");
					if($(window).scrollTop() == 0){
						$header.removeClass("white");
					}
				});
			});
		}
	}
	
	respondHeader();
	
	function respondHeader(){
		var mqWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
		var page = location.href;
		page = page.replace(location.origin, "");
		
		if(mqWidth < 1200) {
			
			$navDepth1a.off('click').on('click', function(e) {
				if ($(this).parent('li').find(".btnMenuDropDown").length > 0) {
					e.preventDefault();
				}
				$(this).parent('li').find('.btnMenuDropDown').first().trigger('click');
				
			});
			
			if(page == "/index"){
				if(!$(".headerBody").is(":has(.topSearchBar)") || !$(".headerBody").is(":has(.mainTabPageScl)")){
					$(".mainTabPageScl").insertAfter("#header .logo");
					$(".topSearchBar").insertAfter("#header .logo");
					$(".topSearch").insertAfter("#header .logo");
				}
			} else {
				if(!$(".headerBody").is(":has(.topSearchBar)")){
					$(".topSearchBar").insertAfter("#header .logo");
					$(".topSearch").insertAfter("#header .logo");
				}
			}
		} else {
			if(page == "/index"){
				if(!$(".headerTop").is(":has(.topSearchBar)") || !$(".headerTop").is(":has(.mainTabPageScl)")){
					$(".mainTabPageScl").insertAfter(".language");
					$(".topSearchBar").insertAfter(".language");
					$(".topSearch").insertAfter(".language");
				}
			} else {
				if(!$(".headerTop").is(":has(.topSearchBar)")){
					$(".topSearchBar").insertAfter(".language");
					$(".topSearch").insertAfter(".language");
				}
			}
		}
			
	}
	
	$(window).on("resize", function() {
		respondTopNav();
		respondHeader();
	});
});

//sidenav
$(function() {
	var $sideNav = $('#sideNav');
	var $location = $('#location');
	$location.find('.btnSideNav').on('click', function() {
		if (!$('body').is(':has(.sideNavBackDv)')) {
			$sideNav.after('<div class="sideNavBackDv" />');
		}
		$sideNav.addClass('opened').attr({'tabindex':'0','hidefocus':'true'}).css('outline','0').focus();
		return false;
	});
	$sideNav.find('.btnClose').on('click', function() {
		$sideNav.removeClass('opened');
		$location.find('.btnSideNav').attr('tabindex','0').focus();
	});
	$(document).on('click', '.sideNavBackDv', function() {
		$sideNav.find('.btnClose').trigger('click');
	});

	var $sideNavMenu = $('.sideNavMenu');
	
	$sideNavMenu.find('.btnMenuDropDown').on('click', function() {
		if ($(this).parent('li').is('.active')) {
			$(this).siblings('ul').slideUp(100, function() {
				$(this).parent('li').removeClass('active');
			});
			$(this).text("하위메뉴 축소됨");
		} else {
			$(this).closest('ul').find('li').removeClass('active').find('>ul').hide();
			$(this).siblings('ul').hide().slideDown(100, function() {
				$(this).parent('li').addClass('active');
			});
			$(this).text("하위메뉴 확장됨");
		}
	});
	
	$sideNavMenu.find('.btnMenuDropDown').each(function(){
		if($(this).parent("li").is(".active")){
			$(this).text("하위메뉴 확장됨");
		} else {
			$(this).text("하위메뉴 축소됨");
		}
	});

	//location scrollTop
	function funcLocationSclTop() {
		if ($(window).scrollTop() > 0) {
			$location.addClass('locationScl');
		} else {
			$location.removeClass('locationScl');
		}
	}
	$(window).scroll(function() {
		funcLocationSclTop()
	});
	
});




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

//사업추진경과 탭
$(function () {
  const listHistoryTabs = document.querySelectorAll(".history_tab > ul > li");
  const listHistoryTabsLink = document.querySelectorAll(
    ".history_tab > ul > li > a"
  );
  const listHistoryContents = document.querySelectorAll(".history_list");
  listHistoryContents[0].classList.add("active");

  listHistoryTabs.forEach((item) => item.removeAttribute("tabindex"));

  for (let i = 0; i < listHistoryTabsLink.length; i++) {
    listHistoryTabsLink[i].addEventListener("click", function (e) {
      for (let j = 0; j < listHistoryTabsLink.length; j++) {
        listHistoryTabsLink[j].classList.remove("active");
        listHistoryTabsLink[j].removeAttribute("title", "선택됨");
        listHistoryTabsLink[j].parentNode.classList.remove("activeClass");
        listHistoryContents[j].classList.remove("active");
        listHistoryContents[j].removeAttribute("style");
      }
      this.classList.add("active");
      this.setAttribute("title", "선택됨");
      this.parentNode.classList.add("activeClass");
      listHistoryContents[i].classList.add("active");
    });
  }
  listHistoryTabsLink.forEach((item) =>
    item.setAttribute("href", "javascript:void(0);")
  );
});


