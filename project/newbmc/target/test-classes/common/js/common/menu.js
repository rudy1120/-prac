
$(document).ready(function () {
	$("#lnb > li > a").each(function () {
		$(this).on("mouseenter focus", function () {
			$("#lnbWrap > .layer").addClass('on');
			$("#lnbWrap > .layer").removeAttr('style');
			$("#lnbWrap .lnb_sub").removeClass('on');
			$(this).next(".lnb_sub").addClass('on');

			if ($('#lnbWrap').attr('class') == 'type02') {
				$("#lnbWrap > .layer").attr('style', 'height: ' + ($(this).next().height() + 70) + 'px');
			}
		});
	});

	$("#lnbWrap >.layer").on("mouseleave blur", function () {
		$("#lnbWrap >.layer").removeClass('on');
		$("#lnbWrap >.layer").removeAttr('style');
	});

	$("#openSearch").click(function () {
		if ($(this).hasClass('close')) {
			$(this).removeClass('close');
			$("#openMenu").css('display', 'block');
		} else {
			$(this).addClass('close');
			$("#openMenu").css('display', 'none');
		}
	});

	$("#openMenu").click(function () {
		mobileNav.toggle();
		return false;
	});
});

var mobileNav = (function () {
	var closed = true;
	return {
		toggle: function () {
			closed ? this.open() : this.close();
		},
		open: function () {
			$("#mask_mn").fadeTo("fast", 1);
			$("#lnbWrap").stop().animate({ left: '0' });
			$('#openMenu').removeClass('close');
			$("body").addClass("stop_scrolling");
			closed = false;
		},
		close: function () {
			$("#mask_mn").fadeTo("fast", 0);
			$("#lnbWrap").stop().animate({ left: '-491' });
			$('#openMenu').addClass('close');
			$("body").removeClass("stop_scrolling");
			closed = true;
		}
	}
})(jQuery);