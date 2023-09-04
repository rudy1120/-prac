/*!
 * YHDCalendar v1.0
 * Docs & License: http://www.yhdatabase.com/
 * (c) 2016 Sonys & Leejr
 */

/* <캘린더 초기화>
 *
 * 	ex)
 *
	<head>
		<script type="text/javascript" src="/common/yhdcalendar-1.0/yhdcalendar.lib.js"></script>
		<script type="text/javascript" src="/common/yhdcalendar-1.0/yhdcalendar.js"></script>

		<script>
			$(document).ready(function(){
				//데이타 조회 주소
				YHDCalendar.url   = "/tour/geumocamp/reservation/countJsonData.do";
				//오늘날짜 매핑
				YHDCalendar.today = "${'2016-07-11'}";
				//캘린더 초기화
				$("#yhdsample").yhdCalendar();
			});
		</script>
	</head>

	<body>
		<h4>일정</h4>
		<div id="yhdsample"></div>
	</body>
 *
 */
$.fn.yhdCalendar = function(option) {

	//캘린더 객체에 현재 DIV 담기
	YHDCalendar.calendar = this;

	//옵션초기화
	YHDCalendar.initOptions(option);

	//캘린더 생성
	YHDCalendar.initalRender(YHDCalendar.options.today);

	//데이타 조회
	YHDCalendar.search(YHDCalendar.options.today);

	return YHDCalendar;
};



//캘린더 기본옵션
var YHDCalendar = {

	options: {
		url: null,
		json: [],

		today: new Date().format("yyyy-MM-dd"),

		title: new Date().format("yyyy. MM"),
		btnPrev: "이전 월",
		btnNext: "다음 월",
		btnToday: "오늘",
		dayName: "일,월,화,수,목,금,토",

		iconUse: false,
		iconPrev: "",
		iconNext: "",
		iconToay: "",

		caption: params.caption,

		headClass: "calendarHead",
		calendarClass: "calendar",
		todayClass: "todayTitle",
		holidayClass: "holidayTitle",
		scheduleClass: "schedule",
		btnPrevClass: "month prev",
		btnNextClass: "month next",
		btnTodayClass: "today",

		dayClick: null,
		scheduleClick: null

	},



	initOptions: function(option) {
		$.extend(YHDCalendar.options, option);
	},


	btnFocus: "btnToday",


	addStylesheet: function() {
		var stylesheet = "";
		stylesheet += "<style id='yhdcStylesheet'>";
		stylesheet += ".calendarHead{}";
		stylesheet += ".calendar{}";
		stylesheet += ".todayTitle{}";
		stylesheet += ".holidayTitle{}";
		stylesheet += ".scheduleTitle{}";
		stylesheet += ".month .prev{}";
		stylesheet += ".month .next{}";
		stylesheet += ".btnToday{}";
		stylesheet += "</style>";
		$("head").append(stylesheet);
	},



	list: null,



	search: function(paramDate) {

		if (YHDCalendar.options.url === null)
			return;

		//조회할 날짜생성
		var ym = new Date(paramDate).format("yyyy-MM-");
		var start = ym + "01";
		var end = ym + new Date(paramDate).getLastDate();

		$.ajax({
			url: YHDCalendar.options.url,
			cache: false,
			type: "get",
			data: { "start": start, "end": end, "category": params.category, "category2": params.category2 },
			success: function(rs) {

				var list = eval("[" + rs + "]")[0].list;
				var td = $(".calendar table tbody * td");

				//기존 스케줄 삭제
				$("."+YHDCalendar.options.scheduleClass).remove();

				//스케줄등록
				var schedule;
				for (var i = 0; i < list.length; i++) {
					for (var j = 0; j < td.length; j++) {

						//console.log(list[i].start +" : td>"+ $(td[j]).attr("yhdc-data")+" : "+list[i].end);
						if (list[i].start <= $(td[j]).attr("yhdc-data") && list[i].end >= $(td[j]).attr("yhdc-data")) {
							schedule = '<div class="' + YHDCalendar.options.scheduleClass + '">';
							schedule += '<a href="javascript:void(0)" yhdc-url="' + list[i].url + '" yhdc-title="' + list[i].title + '">' + list[i].title + '</a>';
							schedule += '</div>';
							$(td[j]).append(schedule);
						}
					}
				} //end of for


				//스케줄 클릭 이벤트
				$("div.schedule a").on("click", function(e) {
					var obj = {
						schedule: this,
						url: $(this).attr("yhdc-url"),
						title: $(this).attr("yhdc-title"),
						date: $(this).parent().parent().attr("yhdc-data")
					};
					YHDCalendar.options.scheduleClick(obj);

				});
			},
			error: function(e) {
				console.log(e);
			}
		});

	},



	//캘린더 생성
	initalRender: function(paramDate) {

		//오늘날짜 초기화
		if (typeof paramDate == "undefined") {
			YHDCalendar.options.title = new Date(YHDCalendar.options.today).format(
				"yyyy. MM");
			paramDate = YHDCalendar.options.today;
		} else {
			YHDCalendar.options.title = new Date(paramDate).format("yyyy. MM");
		}

		if ($("#yhdcStylesheet").length < 1) {
			YHDCalendar.addStylesheet();
		}

		//기존캘린더 삭제
		$("#yhdcalendar").remove();

		var yhdcalendar = $('<div id="yhdcalendar">');


		/************************************
		 * 툴바 초기화
		 ************************************/
		var calendarHead = $('<div>').addClass(YHDCalendar.options.headClass);
		//아이콘사용체크
		var toolbar = "";
		if (YHDCalendar.options.iconUse) {
			$(calendarHead).append('<img id="btnPrevMonth" src="' + YHDCalendar.options
				.iconPrev + '" class="iconPrev">');
			$(calendarHead).append(' <span>' + YHDCalendar.options.title + '</span> ');
			$(calendarHead).append('<img id="btnNextMonth" src="' + YHDCalendar.options
				.iconNext + '" class="iconNext"> ');
			$(calendarHead).append('<img id="btnToday" src="' + YHDCalendar.options.iconToday +
				'" class="iconToday">');
		} else {
			$(calendarHead).append(
				'<a id="btnPrevMonth" href="javascript:void(0)" class="' + YHDCalendar.options
				.btnPrevClass + '">' + YHDCalendar.options.btnPrev + '</a>');
			$(calendarHead).append(' <span>' + YHDCalendar.options.title + '</span> ');
			$(calendarHead).append(
				'<a id="btnNextMonth" href="javascript:void(0)" class="' + YHDCalendar.options
				.btnNextClass + '">' + YHDCalendar.options.btnNext + '</a> ');
			$(calendarHead).append(
				'<a id="btnToday" href="javascript:void(0)" class="' + YHDCalendar.options
				.btnTodayClass + '">' + YHDCalendar.options.btnToday + '</a>');
		}

		//캘린더에 calendarHead추가
		$(yhdcalendar).append(calendarHead);


		/************************************
		 * 달력 초기화
		 ************************************/
		//달력초기화
		var calendar = $('<div>').addClass(YHDCalendar.options.calendarClass);

		//table생성
		var cTable = $('<table id=\"yhd_calendar\">');

		//table에 caption추가
		$(cTable).append('<caption>' + moment().format("YYYY년 MM월") + " " + YHDCalendar.options.caption + '</caption>');

		//table에 head추가
		var cThead = $('<thead>');
		var cHtr = $('<tr>');
		//head내용 추가
		var cHth = "";
		var dayname = YHDCalendar.options.dayName.split(",");
		for (var i = 0; i < dayname.length; i++) {
			cHth += '<th scope="col">' + dayname[i] + '<span>요일</span></th>';
		}
		$(cHtr).append(cHth);
		$(cThead).append(cHtr);
		$(cTable).append(cThead);



		//body생성
		var cTbody = $('<tbody>');

		var y = new Date(paramDate).format("yyyy");
		var m = new Date(paramDate).format("MM");
		var d = new Date(paramDate).format("dd");
		var nowDay = moment().format("YYYY-MM-DD");

		//년월
		var ym = y + "-" + m + "-";
		//요일구하기
		//달의 1일의 요일구하기
		var sDay = new Date(ym + "01").getDay();
		//달의 마지막일자 구하기
		var eNum = new Date(paramDate).getLastDate();
		var wNum = Math.ceil((sDay + eNum) / 7);

		//달 생성
		var dnum = 1;
		var td;
		for (i = 1; i <= wNum; i++) {

			//주 생성
			var cBtr = $('<tr>');
			var calday;
			for (var j = 1; j <= 7; j++) {
				calday = (ym + (dnum < 10 ? "0" + dnum : dnum));

				if (i == 1 && j <= sDay || dnum > eNum) {
					$(cBtr).append('<td>&nbsp;</td>');
				} else {
					//공휴일 체크
					if (typeof eventDay[m + "-" + (dnum < 10 ? "0" + dnum : dnum)] != "undefined" ||
						typeof eventChangeDay[y + "-" + m + "-" + (dnum < 10 ? "0" + dnum : dnum)] != "undefined") {

						var dayName;
						//국가지정 공휴일과 일반휴일 입력
						if (typeof eventDay[m + "-" + (dnum < 10 ? "0" + dnum : dnum)] != "undefined")
							dayName = eventDay[m + "-" + (dnum < 10 ? "0" + dnum : dnum)];
						else
							dayName = eventChangeDay[y + "-" + m + "-" + (dnum < 10 ? "0" + dnum :  dnum)];

						td = '<td id="yhdc-' + (ym + (dnum < 10 ? "0" + dnum : dnum)) +
							'" yhdc-data="' + ym + (dnum < 10 ? "0" + dnum : dnum) +
							'" class="yhdc-dayCon holiday' + (nowDay == calday ? ' today' : '') + '">';
						td += dnum + "&nbsp;" + dayName;
						td += '</td>';
					} else {
						var targetDate = ym + (dnum < 10 ? "0" + dnum : dnum);
						td = '<td id="yhdc-' + (ym + (dnum < 10 ? "0" + dnum : dnum)) + '" yhdc-data="' + targetDate + '" class="yhdc-dayCon' + (nowDay == calday ? ' today' : '') + '">';
						td += isReservable(targetDate) && params.useWriteLink == "Y"
								? '<a href="' + params.prefixUrl + '/write.do?mId=' + params.mId + '&amp;day=' + targetDate + '" class="yhdc-dayBox" title="신청하기">' + dnum + '</a>'
								: dnum;
						td += '</td>';
					}
					$(cBtr).append(td);
					dnum++;
				}
			} //end of for

			$(cTbody).append(cBtr);
		}
		$(cTable).append(cTbody);
		$(calendar).append(cTable);

		//캘린더에 calendar추가
		$(yhdcalendar).append(calendar);

		//캘린더 DIV에 캘린더 추가
		$(YHDCalendar.calendar).append(yhdcalendar);

		//포커스 처리
		$("#" + YHDCalendar.btnFocus).focus();


		//날짜 클릭 이벤트
		$(".yhdc-dayBox").on("click", function(e) {
			var obj = {
				daybox: this,
				date: $(this).parent().attr("yhdc-data")
			};
			YHDCalendar.options.dayClick(obj);
		});


		//툴바버튼 이벤트
		$("#btnPrevMonth,#btnNextMonth,#btnToday").on("click", function() {

			var searchDate;
			switch (this.id) {

				//이전달
				case "btnPrevMonth":
					if (m == 1) {
						y = y * 1 - 1;
						m = 12;
					} else {
						m = m * 1 - 1;
						m = m < 10 ? "0" + m : m;
					}
					searchDate = y + "-" + m + "-" + d;
					break;

					//다음달
				case "btnNextMonth":
					if (m == 12) {
						y = y * 1 + 1;
						m = "01";
					} else {
						m = m * 1 + 1;
						m = m < 10 ? "0" + m : m;
					}
					searchDate = y + "-" + m + "-" + d;
					break;

					//이번달(오늘버튼)
				case "btnToday":
				default:
					searchDate = YHDCalendar.options.today;
			}

			YHDCalendar.btnFocus = this.id;
			YHDCalendar.initalRender(searchDate);
			YHDCalendar.search(searchDate);

			var cDayToken = moment().format().split("-");
			var dayToken = searchDate.split("-");

			if ((cDayToken[0] - dayToken[0]) > 0) { // 과거 연도
				$("#btnPrevMonth").hide();
			} else if (cDayToken[0] == dayToken[0]) { // 현재 연도
				if ((cDayToken[1] - dayToken[1]) >= 1) {
					$("#btnPrevMonth").hide();
				}
			}

			$("#yhd_calendar > caption").text(dayToken[0] + "년 " + dayToken[1] + "월 " + YHDCalendar.options.caption);
		});
	}
};

function isReservable(targetDate) {
	var _targetDate = new Date(targetDate);
	if (_targetDate.getDay() == 0 || _targetDate.getDay() == 6) { // 공휴일은 제외
		return false;
	} else if (moment().format().substring(0, 10) >= targetDate) { // 과거
		return false;
	}

	return true;
}
