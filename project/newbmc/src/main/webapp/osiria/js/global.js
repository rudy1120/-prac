var mqWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
var mqWidth_mobile = 320;
var mqWidth_tablet = 768;
var mqWidth_pc = 1024;

//검색 keyword 불가 입력시 조치
$(window).load(function(){
	$("form[name=srchFrm]").bind("submit",	function() {
		// keydown 주석처리 - 모바일에서 중복 alert 나옴
		$("#searchText").bind("keyup",	function() {
			return searchKeywordCheck($(this));
		});
//		$("#searchText").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#searchText").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});

		$("#txtSrchKeyword1").bind("keyup",	function() {
			return searchKeywordCheck($(this));
		});
//		$("#txtSrchKeyword1").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#txtSrchKeyword1").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});
	});
	
	$("form[name=srchFrm2]").bind("submit",	function() {
		$("#searchText2").bind("keyup",	function() {
			return searchKeywordCheck($(this));
		});
//		$("#searchText2").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#searchText2").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});
	});

	$("form[name=searchForm1]").bind("submit",	function() {
		$("#searchText1").bind("keyup",	function() {
			return searchKeywordCheck($(this));
	    });
//		$("#searchText1").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#searchText1").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});
	});

	$("form[name=mainSrchFrm]").bind("submit",	function() {
		$("#searchText1").bind("keyup",	function() {
			return searchKeywordCheck($(this));
	    });
//		$("#searchText1").bind("keydown",	function() {
//			return searchKeywordCheck($(this));
//		});
		$("#searchText1").bind("focus",	function() {
			return searchKeywordCheck($(this));
		});
		
	});


	$("#searchText").bind("keyup",	function() {
		searchKeywordCheck($(this));
	});
//	$("#searchText").bind("keydown",	function() {
//		searchKeywordCheck($(this));
//	});
	$("#searchText").bind("focus",	function() {
		fn_forceKorModeInput($(this));
		searchKeywordCheck($(this));
	});

	$("#searchText1").bind("keyup",	function() {
		searchKeywordCheck($(this));
	});
//	$("#searchText1").bind("keydown",	function() {
//		searchKeywordCheck($(this));
//	});
	$("#searchText1").bind("focus",	function() {
		fn_forceKorModeInput($(this));
		searchKeywordCheck($(this));
	});
	
	$("#searchText2").bind("keyup",	function() {
		searchKeywordCheck($(this));
	});
//	$("#searchText2").bind("keydown",	function() {
//		searchKeywordCheck($(this));
//	});
	$("#searchText2").bind("focus",	function() {
		fn_forceKorModeInput($(this));
		searchKeywordCheck($(this));
	});

	$("#txtSrchKeyword1").bind("keyup",	function() {
		searchKeywordCheck($(this));
	});
//	$("#txtSrchKeyword1").bind("keydown",	function() {
//		searchKeywordCheck($(this));
//	});
	$("#txtSrchKeyword1").bind("focus",	function() {
		fn_forceKorModeInput($(this));
		searchKeywordCheck($(this));
	});
	
});

//강제 입력창 한글입력(IE 빼고 안됨, 외국어사이트 제외)
function fn_forceKorModeInput(obj) {
	if ($('html').attr('lang') == "ko" || $('html').attr('lang') == "" || $('html').attr('lang') == undefined) {
		obj.css('ime-mode', 'active');
	}
}

//게시판, 프로그램 등 입력부분 공통으로 한글입력 우선 적용(IE 빼고 안됨, 외국어사이트 제외)
function fn_forceKorModeInputV2() {
	if ($('html').attr('lang') == "ko" || $('html').attr('lang') == "" || $('html').attr('lang') == undefined) {
		$('input[name="srchText"]').each(function(){
			$(this).css('ime-mode', 'active');
		});
	}
}

function searchKeywordCheck(obj){
	 var arr_char = new Array();
	 arr_char.push("<");
	 arr_char.push(">");
	 arr_char.push("onstop");
	 arr_char.push("layer");
	 arr_char.push("javascript");
	 arr_char.push("eval");
	 arr_char.push("onactivae");
	 arr_char.push("onfocusin");
	 arr_char.push("applet");
	 arr_char.push("document");
	 arr_char.push("onclick");
	 arr_char.push("onkeydown");
	 arr_char.push("create");
	 arr_char.push("onbeforecut");
	 arr_char.push("onkeyup");
	 arr_char.push("link");
	 arr_char.push("binding");
	 arr_char.push("ondeactivate");
	 arr_char.push("onload");
	 arr_char.push("script");
	 arr_char.push("msgbox");
	 arr_char.push("ondragend");
	 arr_char.push("onbounce");
	 arr_char.push("object");
	 arr_char.push("embed");
	 arr_char.push("ondragleave");
	 arr_char.push("onmovestart");
	 arr_char.push("frame");
	 arr_char.push("ondragstart");
	 arr_char.push("onmouseout");
	 arr_char.push("ilayer");
	 arr_char.push("onerror");
	 arr_char.push("onmouseup");
	 arr_char.push("bgsound");
	 arr_char.push("href");
	 arr_char.push("onabort");
	 arr_char.push("base");
	 arr_char.push("onstart");
	 arr_char.push("onfocus");
	 arr_char.push("onmove");
	 arr_char.push("innerHTML");
	 arr_char.push("onpaste");
	 arr_char.push("ondblclick");
	 arr_char.push("vbscript");
	 arr_char.push("charset");
	 arr_char.push("onresize");
	 arr_char.push("ondrag");
	 arr_char.push("expression");
	 arr_char.push("string");
	 arr_char.push("onselect");
	 arr_char.push("ondragenter");
	 arr_char.push("onchange");
	 arr_char.push("append");
	 arr_char.push("onscroll");
	 arr_char.push("ondragover");
	 arr_char.push("meta");
	 arr_char.push("alert");
	 arr_char.push("title");
	 arr_char.push("ondrop");
	 arr_char.push("void");
	 arr_char.push("refresh");
	 arr_char.push("iframe");
	 arr_char.push("oncopy");
	 arr_char.push("oncut");
	 arr_char.push("blink");
	 arr_char.push("onfinish");
	 arr_char.push("frameset");
	 arr_char.push("cookie");
	 arr_char.push("style");
	 arr_char.push("onreset");
	 arr_char.push("onselectstart");

	 var host = $(location).attr('host');
	 var rtn = true;

	 if(obj.val().length != 0 )
	 {
		for(var i =0; i<arr_char.length; i++)
		{
			if(obj.val().indexOf(arr_char[i]) != -1)
			{
				
				if ($('#Lang').val() == 'en'){
					alert("Your keywords include restricted words.");
				} else if ($('#Lang').val() == 'jp'){
					alert("入力することができない単語が含まれています。");
				} else if ($('#Lang').val() == 'cn'){
					alert("包括无输入单词。");
				} else if ($('#Lang').val() == 'tc'){
					alert("包括無輸入單詞。");
				} else {
					alert("입력할 수 없는 단어를 포함하고 있습니다.");
				}
				
				rtn = false;
				obj.val("");
				break;
			}
		}
	 }

	 return rtn;
}



//btnMenuDropDownSub
$(function() {
	var $topNavSub = $('.topNavSub');
	$topNavSub.find('.btnMenuDropDown').on('click', function() {
		if ($(this).parent('li').is('.active')) {
			$(this).siblings('ul').stop().slideUp(100, function() {
				$(this).parent('li').removeClass('active');
			});
			$(this).html("하위메뉴 축소됨");
		} else {
			$(this).closest('ul').find('li').removeClass('active').find('>ul').hide();
			$(this).siblings('ul').hide().stop().slideDown(100, function() {
				$(this).parent('li').addClass('active');
			});
			$(this).html("하위메뉴 확장됨");
		}
	});
});

// select
$(function() {
	$('.select').each(function() {
		var $select = $(this);
		$select.find('.selected-text').text($select.find('option:selected').text());
		$select.find('select').on({
			'click focus' : function() {
				$(this).parent('.select').addClass('active');
			},
			'focusout' : function() {
				$(this).parent('.select').removeClass('active');
			},
			'change' : function() {
				$(this).parent('.select').find('.selected-text').text($(this).find('option:selected').text());
			}
		});
	});
});


// focus outline
$(function() {
	$('input[type=file].hidden, input[type=radio].hidden, input[type=checkbox].hidden').each(function(i, self) {
		var thisId = $(self).attr('id');
		$(self).focusin(function() {
			$('label[for='+thisId+']').addClass('focusOutline');
		}).focusout(function() {
			$('label[for='+thisId+']').removeClass('focusOutline');
		});
	});

	$('.textFormFile input[type=file]').each(function(i, self) {
		$(self).change(function() {
			$(this).prev('.textForm').val($(this).val());
		});
	});
});


// flexslider
$(function() {
	// flexslider default
	if ($.flexslider !== undefined) {
		$('.flexslider').addClass('not-load').prepend('<div class="flex-control-container" />');

		$.extend($.flexslider.defaults, {
			start: function(s) {
				try {
					var $sliderWrap = $(s.slides).parents('.flexslider');
					var sliderTitle;
					if ($sliderWrap.data('flexslider-title') !== undefined) {
						sliderTitle = $sliderWrap.data('flexslider-title');
					} else {
						sliderTitle = '';
					}

					$sliderWrap.removeClass('not-load');
					$sliderWrap.find('.flex-viewport').wrap('<div class="flex-viewport-wrap" />');
					$sliderWrap.find('.flex-control-paging li a').prepend('<span class="hidden">'+sliderTitle+' 목록</span>');
					$sliderWrap.find('.flex-direction-nav .flex-prev, .flex-direction-nav .flex-next').prepend('<span class="hidden">'+sliderTitle+'</span>');

					//pause/play
					$sliderWrap.find('.flex-control-nav').wrap('<div class="flex-control-nav-wrap" />');
					$sliderWrap.find('.flex-pauseplay').appendTo($sliderWrap.find('.flex-control-nav-wrap'));

					//paging number
					if ($sliderWrap.data('flexslider-controlpaging') == 'number') {
						$sliderWrap.prepend('<div class="flex-control-paging-number"><span class="flex-paging-current"><span class="hidden">현재</span><b>'+(s.currentSlide+1)+'</b><span class="hidden">번째 목록</span></span> / <span class="hidden">전체</span>'+s.count+'<span class="hidden">개 목록</span></div>');
					}

					//tabkey focus
					if (s.vars.direction == 'vertical') {
						$(s.slides).find('a').focus(function() {
							$(s.viewport).scrollTop(0);
							var focusIdx = $(this).parents(s.vars.selector).index();
							if (s.cloneOffset == 1) {
								s.flexAnimate(focusIdx-s.cloneOffset, true);
							} else {
								s.flexAnimate(focusIdx, true);
							}
						});
					} else {
						$(s.slides).find('a').focus(function() {
							$(s.viewport).scrollLeft(0);
							var focusIdx = $(this).parents(s.vars.selector).index();
							if (s.cloneOffset == 1) {
								s.flexAnimate(focusIdx-s.cloneOffset, true);
							} else {
								s.flexAnimate(Math.floor(focusIdx/s.visible), true);
							}
						});
					}
				} catch (e) {
					// TODO: handle exception
				}
			}
		});
	}

	$(window).on('load', function(){

		$('[data-function=flexslider]').each(function() {
			var $thisSlider = $(this);

			var silderItemWidth;
			if ($thisSlider.data('flexslider-itemswidth') !== undefined && typeof $thisSlider.data('flexslider-itemswidth') == 'number') {
				silderItemWidth = $thisSlider.data('flexslider-itemswidth');
			} else {
				silderItemWidth = 100;
			}
			//console.log(silderItemWidth);

			var silderItemLen;
			if ($thisSlider.data('flexslider-items') !== undefined && typeof $thisSlider.data('flexslider-items') == 'number') {
				if (mqWidth < mqWidth_tablet) {
					silderItemLen = $thisSlider.data('flexslider-minitems');
				} else if (mqWidth >= mqWidth_tablet && mqWidth < mqWidth_pc) {
					silderItemLen = $thisSlider.data('flexslider-items');
				} else if (mqWidth >= mqWidth_pc) {
					silderItemLen = $thisSlider.data('flexslider-maxitems');
				}
			} else {
				silderItemLen = 1;
			}
			//console.log(silderItemLen);

			var silderAnimate;
			var silderAnimateSpeed;
			if ($thisSlider.data('flexslider-animation') == 'fade') {
				silderAnimate = 'fade';
				silderAnimateSpeed = 800;
			} else {
				silderAnimate = 'slide';
				silderAnimateSpeed = 600;
			}

			var silderSlideshow;
			var silderPausePlay = false;
			if ($thisSlider.data('flexslider-slideshow') == true) {
				silderSlideshow = true;
				silderPausePlay = true;
			} else {
				silderSlideshow = false;
			}

			var silderDirectionNav;
			if ($thisSlider.data('flexslider-directionnav') == false) {
				silderDirectionNav = false;
			} else {
				silderDirectionNav = true;
			}

			var silderControlNav;
			if ($thisSlider.data('flexslider-controlnav') == false) {
				silderControlNav = false;
			} else {
				silderControlNav = true;
			}

			var silderSelector;
			if ($thisSlider.data('flexslider-selector') == 'slides-item') {
				silderSelector = '.slides-item';
			} else {
				silderSelector = 'li';
			}

			$thisSlider.flexslider({
				selector: '.slides >'+silderSelector,
				animation: silderAnimate,
				animationSpeed: silderAnimateSpeed,
				slideshow: silderSlideshow,
				pausePlay: silderPausePlay,
				itemWidth: silderItemWidth,
				minItems: silderItemLen,
				maxItems: silderItemLen,
				prevText: '이전 '+silderItemLen+'개 목록',
				nextText: '다음 '+silderItemLen+'개 목록',
				pauseText: '슬라이드쇼 정지',
				playText: '슬라이드쇼 재생',
				directionNav: silderDirectionNav,
				controlNav: silderControlNav,
				controlsContainer: $thisSlider.find('.flex-control-container'),
				after: function(s) {
					if ($thisSlider.data('flexslider-controlpaging') == 'number') {
						$thisSlider.find('.flex-control-paging-number .flex-paging-current >b').text(s.currentSlide+1);
					}
				}
			});
		});

		$('[data-function=flexsliderTicker]').each(function() {
			var $thisSliderTicker = $(this);

			$thisSliderTicker.flexslider({
				animation: 'slide',
				slideshow: true,
				slideshowSpeed: 5000,
				direction: 'vertical',
				touch: false,
				pausePlay: true,
				prevText: '이전 1개 목록',
				nextText: '다음 1개 목록',
				pauseText: '슬라이드쇼 정지',
				playText: '슬라이드쇼 재생',
				controlNav: false,
				controlsContainer: $thisSliderTicker.find('.flex-control-container')
			});
		});
	});
});


// table scroll (As-is)
$(function() {
	/* $('.popupCont').find('table').addClass('not-wrap');
	$('table').find('table').addClass('not-wrap');
	$('table').not('[class*="board"]').not('[class *="not-wrap"]').not('[class *="schedule"]').wrap('<div class="srcoll-table"><div></div></div>'); */
	$('table[class*="tableCol"], table[class*="tableRow"], table[class*="tableMt"]').wrap('<div class="srcoll-table"><div></div></div>');
});


// scrollbox
$(function() {
	$('.termsTxt').attr('tabindex',0);
});



$(function() {
	if ($("#pageSatisfy").length > 0) {
		loadStsfdy();
	}

	$('.shareArea .share a').on('click.share', function(e){
		e.preventDefault();
		var $target = $(this).parent('.share');
		$target.toggleClass('on');
		if($target.hasClass('on')){
			$(this).text('공유하기 닫기');
		} else {
			$(this).text('공유하기 열기');
		}
	});
	
	$(".shareArea .print a").on("click", function(e){
		e.preventDefault();
		window.print();
	});

	//select
	$("#selcSiteLanguage").on("click", function(e){
		if($("#selcLanguage").val() == "") {
			e.preventDefault;
		} else {
			window.open($("#selcLanguage").val(), "_blank", "");
		}
	});

});

function loadStsfdy(){
	$.ajax({
		url: "/comm/stsfdg/list", // 평균 및 의견목록 가져오기
		type: "post",
		data: {
			menuNo:$("#satisfactionForm input[name='menuNo']").val()
			, listDoNot:"Y"					//listDoNot값을 Y를 넣어주면 리스트객제를 가져오지 않는다. 아무값도 없거나 N일경우는 리스트객체를 가져온다.
		},
		success: function(data){
			if (data.listVO != null) {
				$("#stsfdg_avgScore").text(data.avgScore);
				$("#stsfdg_totCnt").text(data.listVO.totalCount);
				/*$("#satisfactionList tr").remove();
				var list = data.listVO.listObject;
				for(var i=0; i<list.length; i++) {
					var btnDel = "";
					if (list[i].crtrUniqueId == "${sessMber.uniqueId}"){
						btnDel = ' <input type="button" class="btnTypeS btnLineType1" onclick="javascript:saveStsfdy("DELETE","'+list[i].satsfcNo+'");" value="삭제">';
					}
					var tr = "<tr><td>"+list[i].opinion+btnDel+"</td><td>"+list[i].crtrNm+" "+list[i].creatDttm+"</td></tr>";
					$("#satisfactionList").append(tr);
				}*/
			}
		},
		error: function(xhr, status, err){
			alert("[error] " + ":" + err + "(" + status + ")");
		}
	});
}
function saveStsfdy(actionTy,satsfcNo){

	$("#satisfactionForm input[name='crud']").val(actionTy);
	if (actionTy == "DELETE") {
		$("#satisfactionForm input[name='satsfcNo']").val(Number(satsfcNo));
	}
	$.ajax({
		url: "/comm/stsfdg/action", // 의견 추가,삭제
		type: "post",
		data: $("#satisfactionForm").serialize(),
		success: function(data){
			alert(data.msg);
			if (data.success > 0) {
				loadStsfdy();
			} else if (data.redirectUrl != null) {
				window.location = data.redirectUrl;
			}
		},
		error: function(xhr, status, err){
			alert("[error] " + ":" + err + "(" + status + ")");
		}
	});
}


$(function(){

	/** fn_isEmpty **/
	window.fn_isEmpty = function(obj) {
		if (obj == undefined || obj == null || obj === "") return true;
		return false;
	};

	/** 이미지 크게보기(일반팝업) **/
	window.fn_imageBigPopView = function(url) {
		var src = url;
		if(!fn_isEmpty(src)) {
			var win = window.open('', 'imagePop', 'width=0, height=0, menubar=0, toolbar=0, directories=0, scrollbars=1, status=0, location=0, resizable=1');

			var html = new Array();
			html.push("<!doctype html><head><meta charset='utf-8'><title>:: 크게 보기 ::</title></head>");
			html.push("<body leftmargin='0' topmargin='0'>");
			html.push("<img src='"+ src +"' border='0' style='display:block;cursor:pointer;position:absolute;top:0;left:0;' alt='이미지 크게 보기' onclick='javascript:window.close();'");
			html.push("onload='window.resizeTo(this.width+20, this.height+70);");
			html.push("window.moveTo( (screen.width-this.width)/2 ,  (screen.height-this.height)/2-50 )'>");
			html.push("</body></html>");
			win.document.write(html.join(""));
		}
	};

	/** 이미지 크게보기(fancyBox) **/
	var prev_click_object = null;
	$('a').on('click', function() {
		prev_click_object = $(this);
	});
	window.fn_imageBigFancyBoxView = function(url) {
		var src = url;
		var click_obj = '';
		if(prev_click_object != null) {
			click_obj = prev_click_object;
		}
		if(!fn_isEmpty(src)) {
			$.fancybox({
				content     : "<img src='"+ src +"' style='cursor:pointer' alt='이미지 크게 보기' onclick='javascript:$.fancybox.close();'/>",
				fitToView	: false,
				autoSize	: true,
				closeClick	: false,
				openEffect	: 'none',
				closeEffect	: 'none',
				afterShow: function(){
					$('.fancybox-overlay-fixed').css({'position':'fixed','top':'0','left':'0','bottom':'0','right':'0','z-index':'9999999','background':'rgba(0,0,0,.6)'});
					$('body').css("overflow","hidden");
					$('*[tabIndex = -1]').attr('tabIndex', '');
					$('.fancybox-outer').attr('tabIndex', -1);
					$('.fancybox-outer').focus();
					$('.fancybox-wrap').css('z-index','9999999999');
					
				},
				afterClose : function(){
					$('body').css("overflow","");
					$('*[tabIndex = -1]').attr('tabIndex', '');
					prev_click_object.focus();
				},
				helpers : {
					title : {
						type : 'inside'
					}
				}
			});
		}
	};

	/** 컨텐츠 팝업처리 for fancyBox **/
	window.fn_contentsFancyBox = function(url, mWidth, mHeight) {
		$.fancybox.open({
			href 		: url,
			type 		: 'iframe',
			padding 	: 0,
			maxWidth	: mWidth,
			maxHeight	: mHeight,
			fitToView	: false,
			width		: '95%',
			height		: '95%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterShow: function(){
				$('*[tabIndex = -1]').attr('tabIndex', '');
				$('.fancybox-outer').attr('tabIndex', -1);
				$('.fancybox-outer').focus();
			},
			afterClose : function(){
				$('*[tabIndex = -1]').attr('tabIndex', '');
				prev_click_object.focus();
			},
		});
	};

	/** 이미지 팝업처리 for fancyBox **/
	window.fn_imageFancyBox = function(url, mWidth, mHeight) {
		$.fancybox({
			content		: "<img src='"+ url +"' style='cursor:pointer' alt='이미지 크게 보기' onclick='javascript:$.fancybox.close();'/>",
			maxWidth	: mWidth,
			maxHeight	: mHeight,
			fitToView	: false,
			width		: '95%',
			height		: '95%',
			autoSize	: true,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterShow: function(){
					$('*[tabIndex = -1]').attr('tabIndex', '');
					$('.fancybox-outer').attr('tabIndex', -1);
					$('.fancybox-outer').focus();
			},
			afterClose : function(){
				$('*[tabIndex = -1]').attr('tabIndex', '');
				prev_click_object.focus();
			},
		});
	};

});

jQuery(function($){
	set_Tabbox(true);
	window.onresize = function() {
		set_Tabbox(false);
	};

	function set_Tabbox(set_event) {
		var tab = $('.tabCont');
		var client_width = window.innerWidth;

		tab.find('> ul > li').removeClass('on');
		tab.each(function(){
			var tabBox = $(this);
			tabBox.removeClass('js_off');

			if(client_width > 640){
				tabBox.removeClass('mobile');
				onSelectTab(tabBox.find('> ul > li.selected > a'), tabBox);
			} else {
				if(!tabBox.hasClass('mobile')){
					tabBox.addClass('mobile');
					tabBox.find('> ul > li.on').removeClass('on')
				}
			}

			if(set_event) {
				tabBox.find('> ul > li > a').click(function(){
					onSelectTab(this, tabBox);
					return false;
				});
			}
		});
	};

	// Click Event :: Tab a element = obj
	function onSelectTab(obj, box){
		var t = $(obj);
		var myClass = 'on';

		if(!box.hasClass('mobile'))	{
			// PC, Tblete Mode Event
			t.parents('li').siblings().removeClass(myClass);
			t.parents('li').addClass(myClass);
		} else {
			// Mobile Event
			if(!t.parents('li').hasClass(myClass)){
				t.parents('li').addClass(myClass);
			} else {
				t.parents('li').removeClass(myClass);
			}
			t.parents('li').siblings().removeClass(myClass);
		}
		box.css('padding-bottom', t.next('div').height());
	};

	window.fn_bookMark = function(url, name, sMsg, fMsg, iMsg, e) {
        var triggerDefault = false;

        if (window.sidebar && window.sidebar.addPanel) {
            // Firefox version < 23
            window.sidebar.addPanel(name, url, '');
            alert(sMsg);
            var triggerDefault = true;
        } else if ((window.sidebar && (navigator.userAgent.toLowerCase().indexOf('firefox') > -1)) || (window.opera && window.print)) {
            // Firefox version >= 23 and Opera Hotlist
            var $this = $(this);
            $this.attr('href', url);
            $this.attr('title', name);
            $this.attr('rel', 'sidebar');
            $this.off(e);
            triggerDefault = true;
            alert(sMsg);
        } else if (window.external && ('AddFavorite' in window.external)) {
            // IE Favorite
            window.external.AddFavorite(url, name);
            triggerDefault = true;
        } else {
            // WebKit - Safari/Chrome
            //Alert("Information", (navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd' : 'Ctrl') + '+D 키를 눌러 즐겨찾기에 등록하실 수 있습니다.');
       	 alert(iMsg);
       	 triggerDefault = true;
        }

	};
});


