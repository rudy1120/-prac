/**
 * 양산 대표 포털 메인 스크립트
 *
 *
 * @author J.Ryeon Lee
 * @since 2015.12.01 *
 */
var main = {

	/* ===================================== 메인 이미지 관련 ===================================== */

	visual_box_selector : {
		container : "#box_visual_container",
		page : "#current_visual_idx",
		pause : "#visual_pause",
		resume : "#visual_resume",
	},

	visual_box_attr_def : {
		idx : "data-visual-idx",
	},

	visual_pause_flag : false,
	max_visual_idx : -1,
	active_visual_idx : 1,

	activate_visual : function(idx) {
		$(main.visual_box_selector.container).children().each(function() {
			$(this).children().first().removeAttr("class").next().hide().end();
			if ($(this).attr(main.visual_box_attr_def.idx) == idx) {
				$(this).children().first().addClass("on").next().show().end();
				main.active_visual_idx = idx;
			}
		});
	},

	pause_visual : function() {
		$(main.visual_box_selector.pause).hide();
		$(main.visual_box_selector.resume).show();
		main.visual_pause_flag = true;
	},

	resume_visual : function() {
		$(main.visual_box_selector.pause).show();
		$(main.visual_box_selector.resume).hide();
		main.visual_pause_flag = false;
	},

	/* ===================================== POPUP 관련 ===================================== */

	popup_box_selector : {
		container : "#box_popup_container",
		page : "#active_popup_idx",
		pause : "#popup_pause",
		resume : "#popup_resume",
		prev : "#popup_prev",
		next : "#popup_next",
	},

	popup_box_attr_def : {
		idx : "data-popup-idx",
	},

	popup_pause_flag : false,
	max_popup_idx : -1,
	active_popup_idx : 1,

	pause_popup : function() {
		main.popup_pause_flag = true;
		$(main.popup_box_selector.pause).removeAttr("class").addClass("play");
		$(main.popup_box_selector.pause).attr("onclick", "main.resume_popup(); return false;");
	},

	resume_popup : function() {
		main.popup_pause_flag = false;
		$(main.popup_box_selector.pause).removeAttr("class").addClass("stop");
		$(main.popup_box_selector.pause).attr("onclick", "main.pause_popup(); return false;");
	},

	next_popup : function() {
		if ($(main.popup_box_selector.container).children().length > 1) {
			$(main.popup_box_selector.container).children().first().hide();
			$(main.popup_box_selector.container).children().first().next().show().end().appendTo(main.popup_box_selector.container);
			main.change_active_popup_page();
		}
	},

	prev_popup : function() {
		$(main.popup_box_selector.container).children().first().hide();
		$(main.popup_box_selector.container).children().last().show().prependTo(main.popup_box_selector.container);
		main.change_active_popup_page();
	},

	change_active_popup_page : function() {
		$(main.popup_box_selector.page).text($(main.popup_box_selector.container).children().first().attr(main.popup_box_attr_def.idx));
	},

	/* ===================================== BOARD TAB 관련 ===================================== */

	bbs_tab_selector : {
		container : "#bbs_tab_container",
	},

	bbs_tab_attr_def : {
		idx : "data-tab-idx",
	},

	activate_bbs_tab : function(index) {
		$(main.bbs_tab_selector.container).children().each(function() {
			$(this).children().find("a").first().removeAttr("class");
			$(this).children().first().next().removeAttr("class").next().hide().next().hide().end();
		});

		var $parent = $("[" + main.bbs_tab_attr_def.idx + "=" + index + "]").parent();
		$parent.children().first().next().addClass("on").next().show().next().show().end();
		$parent.children().first().children().first().addClass("onhover");
	},
};

/** MAIN VISUAL */
$(document).ready(function() {
	main.max_visual_idx = $(main.visual_box_selector.container).children().length;

	setInterval(function() {
		if (!main.visual_pause_flag && $(main.visual_box_selector.container).children().length > 1) {
			main.active_visual_idx = main.active_visual_idx == main.max_visual_idx
				? 1 : main.active_visual_idx + 1;
			main.activate_visual(main.active_visual_idx);
		}
	}, 5000);
});

/** MAIN POPUP */
$(document).ready(function() {
	main.max_popup_idx = $(main.popup_box_selector.container).children().length;

	setInterval(function() {
		if (!main.popup_pause_flag && $(main.popup_box_selector.container).children().length > 1) {
			$(main.popup_box_selector.container).children().first().hide().next().show().end().appendTo(main.popup_box_selector.container);
			main.change_active_popup_page();
		}
	}, 3000);
});


/* ===================================== BOARD ANCHOR 관련 ===================================== */

var anchor_flower = {

	prev : function(selector) {
		var root_html = !!$(selector).children().first().attr("class")
			? "<ul class=\"" + $(selector).children().first().attr("class") + "\">"
			: "<ul>";
		$(selector).children().last().after($(root_html).html($(selector).children().first().html()));
		$(selector).children().first().remove();
	},

	next : function(selector) {
		var root_html = !!$(selector).children().last().attr("class")
			? "<ul class=\"" + $(selector).children().last().attr("class") + "\">"
			: "<ul>";
		$(selector).children().first().before($(root_html).html($(selector).children().last().html()));
		$(selector).children().last().remove();
	},

};
