/**
 * @author J.Ryeon Lee
 * @since 2017.09.22
 */

var dp = {
	init: function(dateFormat, yearRange) {
		(yearRange ? dp.opt.yearRange = yearRange : '');
		(dateFormat ? dp.opt.dateFormat = dateFormat : '');
	},
	opt: {
		closeText : "닫기",
		prevText : "이전",
		nextText : "다음",
		currentText : "오늘",
		monthNames : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		monthNamesShort : [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" ],
		dayNames : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesShort : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
		weekHeader : "Wk",
		dateFormat : "yy-mm-dd",
		firstDay : 0,
		showMonthAfterYear : true,
		changeMonth : true,
		changeYear : true,
		yearRange: "-9:+5",
		onSelect : function(selectedDate) {
			jQuery(this).val(selectedDate);
		}
	},
	waOpt: {
		showOn: "button",
		buttonImage: "/common/yhdcalendar-1.1/img/ico_calendar.png",
		buttonText: "작은 캘린더 이미지",
		buttonImageOnly: true
	},
	bind: function($targets, dateFormat, yearRange) {
		
		if (!$targets) {
			$targets = jQuery("[data-date=y]");
		}

		if ($targets) {
		
			dp.init(dateFormat, yearRange);
			$targets.each(function() {
				if (jQuery(this).data("wa") == "y") { // 웹접근성 옵션 추가
					jQuery.extend(dp.opt, dp.waOpt);
				}
				dp.opt.onClose = function(selectedDate) {
					if (jQuery(this).data("ismindatefor")) {
						 jQuery("#" + jQuery(this).data("ismindatefor")).datepicker("option", "minDate", selectedDate);
					}
					if (jQuery(this).data("ismaxdatefor")) {
						jQuery("#" + jQuery(this).data("ismaxdatefor")).datepicker("option", "maxDate", selectedDate);
					}
				}
				jQuery(this).datepicker(dp.opt);
			});
		}
	}
 }

/******************* 일광행복주택 이사날짜 *******************/
var dp2 = {
	init: function(dateFormat, yearRange) {
		(yearRange ? dp2.opt.yearRange = yearRange : '');
		(dateFormat ? dp2.opt.dateFormat = dateFormat : '');
	},
	opt: {
		closeText : "닫기",
		prevText : "이전",
		nextText : "다음",
		currentText : "오늘",
		monthNames : [ "10월", "11월" ],
		minDate: "2023-10-01",
        maxDate: "2023-11-30",
		dayNames : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesShort : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
		weekHeader : "Wk",
		dateFormat : "yy-mm-dd",
		firstDay : 0,
		showMonthAfterYear : true,
		changeMonth : true,
		changeYear : true,
		yearRange: "2023:2023",
		onSelect : function(selectedDate) {
			jQuery(this).val(selectedDate);
			jQuery("#searchTxt").val(selectedDate);
		}
	},
	waOpt: {
		showOn: "button",
		buttonImage: "/common/yhdcalendar-1.1/img/ico_calendar.png",
		buttonText: "작은 캘린더 이미지",
		buttonImageOnly: true
	},
	bind: function($targets, dateFormat, yearRange) {
		
		if (!$targets) {
			$targets = jQuery("[data-date=y]");
		}

		if ($targets) {
		
			dp2.init(dateFormat, yearRange);
			$targets.each(function() {
				if (jQuery(this).data("wa") == "y") { // 웹접근성 옵션 추가
					jQuery.extend(dp2.opt, dp2.waOpt);
				}
				dp2.opt.onClose = function(selectedDate) {
					if (jQuery(this).data("ismindatefor")) {
						 jQuery("#" + jQuery(this).data("ismindatefor")).datepicker("option", "minDate", selectedDate);
					}
					if (jQuery(this).data("ismaxdatefor")) {
						jQuery("#" + jQuery(this).data("ismaxdatefor")).datepicker("option", "maxDate", selectedDate);
					}
				}
				jQuery(this).datepicker(dp2.opt);
			});
		}
	}
 }

var dp3 = {
	init: function(dateFormat, yearRange) {
		(yearRange ? dp3.opt.yearRange = yearRange : '');
		(dateFormat ? dp3.opt.dateFormat = dateFormat : '');
	},
	opt: {
		closeText : "닫기",
		prevText : "이전",
		nextText : "다음",
		currentText : "오늘",
		monthNames : [ "10월", "11월" ],
		minDate: "2023-10-01",
        maxDate: "2023-11-30",
		dayNames : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesShort : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
		weekHeader : "Wk",
		dateFormat : "yy-mm-dd",
		firstDay : 0,
		showMonthAfterYear : true,
		changeMonth : true,
		changeYear : true,
		yearRange: "2023:2023",
		onSelect : function(selectedDate) {
			jQuery(this).val(selectedDate);
			jQuery("#dateSel").val(selectedDate);
			fn_chkTime();
		}
	},
	waOpt: {
		showOn: "button",
		buttonImage: "/common/yhdcalendar-1.1/img/ico_calendar.png",
		buttonText: "작은 캘린더 이미지",
		buttonImageOnly: true
	},
	bind: function($targets, dateFormat, yearRange) {
		
		if (!$targets) {
			$targets = jQuery("[data-date=y]");
		}

		if ($targets) {
		
			dp3.init(dateFormat, yearRange);
			$targets.each(function() {
				if (jQuery(this).data("wa") == "y") { // 웹접근성 옵션 추가
					jQuery.extend(dp3.opt, dp3.waOpt);
				}
				dp2.opt.onClose = function(selectedDate) {
					if (jQuery(this).data("ismindatefor")) {
						 jQuery("#" + jQuery(this).data("ismindatefor")).datepicker("option", "minDate", selectedDate);
					}
					if (jQuery(this).data("ismaxdatefor")) {
						jQuery("#" + jQuery(this).data("ismaxdatefor")).datepicker("option", "maxDate", selectedDate);
					}
				}
				jQuery(this).datepicker(dp3.opt);
			});
		}
	}
 }

/******************* 더파크 이기대 이사날짜 *******************/
var dp4 = {
	init: function(dateFormat, yearRange) {
		(yearRange ? dp4.opt.yearRange = yearRange : '');
		(dateFormat ? dp4.opt.dateFormat = dateFormat : '');
	},
	opt: {
		closeText : "닫기",
		prevText : "이전",
		nextText : "다음",
		currentText : "오늘",
		monthNames : [ "6월", "7월" ],
		minDate: 0,
        maxDate: "2023-07-15",
		dayNames : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesShort : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
		weekHeader : "Wk",
		dateFormat : "yy-mm-dd",
		firstDay : 0,
		showMonthAfterYear : true,
		changeMonth : true,
		changeYear : true,
		yearRange: "2023:2023",
		onSelect : function(selectedDate) {
			jQuery(this).val(selectedDate);
			jQuery("#searchTxt").val(selectedDate);
		}
	},
	waOpt: {
		showOn: "button",
		buttonImage: "/common/yhdcalendar-1.1/img/ico_calendar.png",
		buttonText: "작은 캘린더 이미지",
		buttonImageOnly: true
	},
	bind: function($targets, dateFormat, yearRange) {
		
		if (!$targets) {
			$targets = jQuery("[data-date=y]");
		}

		if ($targets) {
		
			dp4.init(dateFormat, yearRange);
			$targets.each(function() {
				if (jQuery(this).data("wa") == "y") { // 웹접근성 옵션 추가
					jQuery.extend(dp4.opt, dp4.waOpt);
				}
				dp4.opt.onClose = function(selectedDate) {
					if (jQuery(this).data("ismindatefor")) {
						 jQuery("#" + jQuery(this).data("ismindatefor")).datepicker("option", "minDate", selectedDate);
					}
					if (jQuery(this).data("ismaxdatefor")) {
						jQuery("#" + jQuery(this).data("ismaxdatefor")).datepicker("option", "maxDate", selectedDate);
					}
				}
				jQuery(this).datepicker(dp4.opt);
			});
		}
	}
 }

var dp5 = {
	init: function(dateFormat, yearRange) {
		(yearRange ? dp5.opt.yearRange = yearRange : '');
		(dateFormat ? dp5.opt.dateFormat = dateFormat : '');
	},
	opt: {
		closeText : "닫기",
		prevText : "이전",
		nextText : "다음",
		currentText : "오늘",
		monthNames : [ "6월", "7월" ],
		minDate: 0,
        maxDate: "2023-07-15",
		dayNames : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesShort : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
		weekHeader : "Wk",
		dateFormat : "yy-mm-dd",
		firstDay : 0,
		showMonthAfterYear : true,
		changeMonth : true,
		changeYear : true,
		yearRange: "2023:2023",
		onSelect : function(selectedDate) {
			jQuery(this).val(selectedDate);
			jQuery("#dateSel").val(selectedDate);
			fn_chkTime();
		}
	},
	waOpt: {
		showOn: "button",
		buttonImage: "/common/yhdcalendar-1.1/img/ico_calendar.png",
		buttonText: "작은 캘린더 이미지",
		buttonImageOnly: true
	},
	bind: function($targets, dateFormat, yearRange) {
		
		if (!$targets) {
			$targets = jQuery("[data-date=y]");
		}

		if ($targets) {
		
			dp5.init(dateFormat, yearRange);
			$targets.each(function() {
				if (jQuery(this).data("wa") == "y") { // 웹접근성 옵션 추가
					jQuery.extend(dp5.opt, dp5.waOpt);
				}
				dp5.opt.onClose = function(selectedDate) {
					if (jQuery(this).data("ismindatefor")) {
						 jQuery("#" + jQuery(this).data("ismindatefor")).datepicker("option", "minDate", selectedDate);
					}
					if (jQuery(this).data("ismaxdatefor")) {
						jQuery("#" + jQuery(this).data("ismaxdatefor")).datepicker("option", "maxDate", selectedDate);
					}
				}
				jQuery(this).datepicker(dp5.opt);
			});
		}
	}
 }

