(function(e) {
	"function" == typeof define && define.amd ? define([ "jquery", "moment" ],
			e) : e(jQuery, moment)
})(function(e, t) {
	(t.defineLocale || t.lang).call(t, "ko", {
		months : "1월_2월_3월_4월_5월_6월_7월_8월_9월_10월_11월_12월".split("_"),
		monthsShort : "1월_2월_3월_4월_5월_6월_7월_8월_9월_10월_11월_12월".split("_"),
		weekdays : "일요일_월요일_화요일_수요일_목요일_금요일_토요일".split("_"),
		weekdaysShort : "일_월_화_수_목_금_토".split("_"),
		weekdaysMin : "일_월_화_수_목_금_토".split("_"),
		longDateFormat : {
			LT : "A h시 m분",
			LTS : "A h시 m분 s초",
			L : "YYYY.MM.DD",
			LL : "YYYY년 MMMM D일",
			LLL : "YYYY년 MMMM D일 LT",
			LLLL : "YYYY년 MMMM D일 dddd LT"
		},
		calendar : {
			sameDay : "오늘 LT",
			nextDay : "내일 LT",
			nextWeek : "dddd LT",
			lastDay : "어제 LT",
			lastWeek : "지난주 dddd LT",
			sameElse : "L"
		},
		relativeTime : {
			future : "%s 후",
			past : "%s 전",
			s : "몇초",
			ss : "%d초",
			m : "일분",
			mm : "%d분",
			h : "한시간",
			hh : "%d시간",
			d : "하루",
			dd : "%d일",
			M : "한달",
			MM : "%d달",
			y : "일년",
			yy : "%d년"
		},
		ordinalParse : /\d{1,2}일/,
		ordinal : "%d일",
		meridiemParse : /오전|오후/,
		isPM : function(e) {
			return "오후" === e
		},
		meridiem : function(e) {
			return 12 > e ? "오전" : "오후"
		}
	}), e.fullCalendar.datepickerLang("ko", "ko", {
		closeText : "닫기",
		prevText : "이전",
		nextText : "다음",
		currentText : "현재",
		monthNames : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월",
				"10월", "11월", "12월"],
		monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월",
				"9월", "10월", "11월", "12월" ],
		dayNames : [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ],
		dayNamesShort : [ "일", "월", "화", "수", "목", "금", "토" ],
		dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
		weekHeader : "Wk",
		dateFormat : "yy-mm-dd",
		firstDay : 0,
		isRTL : !1,
		showMonthAfterYear : !0,
		yearSuffix : "년"
	}), e.fullCalendar.lang("ko", {
		buttonText : {
			month : "월간",
			week : "주",
			day : "일",
			list : "일정목록"
		},
		allDayText : "종일",
		eventLimitText : "개"
	})
});





eventDay = new Array();
eventDay["01-01"] = "신정";
eventDay["03-01"] = "삼일절";
eventDay["05-05"] = "어린이날";
eventDay["06-06"] = "현충일";
eventDay["08-15"] = "광복절";
eventDay["10-03"] = "개천절";
eventDay["10-09"] = "한글날";
eventDay["12-25"] = "성탄절";

eventChangeDay = new Array();
eventChangeDay["2015-02-18"] = "";
eventChangeDay["2015-02-19"] = "설날";
eventChangeDay["2015-02-20"] = "";
eventChangeDay["2015-05-25"] = "석가탄신일";
eventChangeDay["2015-08-14"] = "임시공휴일";
eventChangeDay["2015-09-26"] = "";
eventChangeDay["2015-09-27"] = "추석";
eventChangeDay["2015-09-28"] = "";
eventChangeDay["2015-09-29"] = "대체 휴일";

eventChangeDay["2016-02-07"] = "";
eventChangeDay["2016-02-08"] = "설날";
eventChangeDay["2016-02-09"] = "";
eventChangeDay["2016-02-10"] = "대체 휴일";
eventChangeDay["2016-05-14"] = "석가탄신일";
eventChangeDay["2016-09-14"] = "";
eventChangeDay["2016-09-15"] = "추석";
eventChangeDay["2016-09-16"] = "";

eventChangeDay["2017-01-27"] = "";
eventChangeDay["2017-01-28"] = "설날";
eventChangeDay["2017-01-29"] = "";
eventChangeDay["2017-01-30"] = "대체 휴일";
eventChangeDay["2017-05-03"] = "석가탄신일";
eventChangeDay["2017-10-03"] = "";
eventChangeDay["2017-10-04"] = "추석";
eventChangeDay["2017-10-05"] = "";

eventChangeDay["2018-01-15"] = "";
eventChangeDay["2018-01-16"] = "설날";
eventChangeDay["2018-01-17"] = "";
eventChangeDay["2018-05-22"] = "석가탄신일";
eventChangeDay["2018-09-23"] = "";
eventChangeDay["2018-09-24"] = "추석";
eventChangeDay["2018-09-25"] = "";

eventChangeDay["2019-02-04"] = "";
eventChangeDay["2019-02-05"] = "설날";
eventChangeDay["2019-02-06"] = "";
eventChangeDay["2019-05-12"] = "석가탄신일";
eventChangeDay["2019-09-12"] = "";
eventChangeDay["2019-09-13"] = "추석";
eventChangeDay["2019-09-14"] = "";

eventChangeDay["2019-02-04"] = "";
eventChangeDay["2019-02-05"] = "설날";
eventChangeDay["2019-02-06"] = "";
eventChangeDay["2019-05-12"] = "석가탄신일";
eventChangeDay["2019-09-12"] = "";
eventChangeDay["2019-09-13"] = "추석";
eventChangeDay["2019-09-14"] = "";

eventChangeDay["2020-01-24"] = "";
eventChangeDay["2020-01-25"] = "설날";
eventChangeDay["2020-01-26"] = "";
eventChangeDay["2020-01-27"] = "대체공휴일";
eventChangeDay["2020-04-30"] = "석가탄신일";
eventChangeDay["2020-09-30"] = "";
eventChangeDay["2020-10-01"] = "추석";
eventChangeDay["2020-10-02"] = "";

eventChangeDay["2021-02-11"] = "";
eventChangeDay["2021-02-12"] = "설날";
eventChangeDay["2021-02-13"] = "";
eventChangeDay["2021-02-15"] = "대체공휴일";
eventChangeDay["2021-05-19"] = "석가탄신일";
eventChangeDay["2021-09-20"] = "";
eventChangeDay["2021-09-21"] = "추석";
eventChangeDay["2021-09-22"] = "";

function isEventDay(date) {
	return typeof eventChangeDay[date] != "undefined" || typeof eventDay[date.substring(5, 10)] != "undefined";
}