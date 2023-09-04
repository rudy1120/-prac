/**
 * 금일 예약 정보 조회
 * 참고: 금오산 예약
 *
 * @author sonys
 * @since 2016.07.05
 */
Number.prototype.pad = function(size) {
	var s = String(this);
	while (s.length < (size || 2)) {
		s = "0" + s;
	}
	return s;
}


var gc = {};
var rsv = {};

$(document).ready(function() {

	// 달력 초기화
	$("#calendar").fullCalendar({
		header: {
			left: "prev,next",
			center: "title",
			right: "today month",
		},
		height: "auto",
		defaultDate: today,
		lang: "ko",
		buttonIcons: true,
		editable: true,
		eventLimit: true,
		eventClick: function(calEvent, jsEvent, view) {
			gc.list(calEvent.start.format()); // 선택일 예약 현황 조회
		},
		events: {
				url: "/portal/" + varDef.facName + "/reserv/month/list.do",
				data : "",
				type: "get" ,
				success: function(rs) {
					$("#loading").toggle();
					//검색 초기화
					$("#camp_name,#size_name,#position_name,#use_day").val("");
					return rs.list;
				},
				color: "#d7d7d7",	 // an option!
				textColor: "#000000" // an option!
		},
		loading: function(bool) {
			$("#loading").toggle(bool);
		},
//		dayClick: function(date, jsEvent, view) {
//			if (date._d.getDay() == 0 || date._d.getDay() == 6 || isEventDay(date.format())) { // 공휴일은 제외
//				alert("공휴일은 예약하실 수 없습니다.");
//			} else if (moment().format().substring(0, 10) >= date.format()) {
//				alert("금일 이후 날짜부터 예약이 가능합니다.");
//			} else {
//				location.href = "/" + varDef.siteCodeFull + "/" + varDef.facName + "/reserv.do?mId=" + varDef.mId + "&rStartDay=" + date.format();
//			}
//		},
		eventAfterAllRender : function(view) {
			// 법정 공휴일 색상변경
			$("#calendar td.fc-day-number").each(function(i, td) {
				var temp = $(td).html();
				if( temp.length>2 ) return;

				var day = $(td).attr("data-date").substring(5,10);
				if(typeof eventDay[day] != "undefined"){
					$(td).addClass("fc-sun").removeClass("fc-sat");
					temp = "<span style=\"font-size:8pt\">" + eventDay[day] + "</span> " + temp;
				}

				// 공휴일 색상변경
				day = $(td).attr("data-date").substring(0, 10);
				if (typeof eventChangeDay[day] != "undefined") {
					$(td).addClass("fc-sun").removeClass("fc-sat");
					temp = "<span style=\"font-size:8pt\">" + eventChangeDay[day] +"</span> " + temp;
				}

				$(td).html(temp);

				return;
			});

			var year = parseInt($("#calendar").fullCalendar("getDate")._d.getFullYear());
			var month = parseInt($("#calendar").fullCalendar("getDate")._d.getMonth()) + 1;
			$("div .fc-content-skeleton").find("tbody").each(function(i) {
				$(this).children().children().each(function(j, td) {
					var sday = $($($("div .fc-content-skeleton").find("thead")[i]).children().children()[j]).text();
					var day = parseInt(sday);
					if (!isNaN(day)) {
						var cdate = year + "-" + month.pad(2) + "-" + day.pad(2);
						var date = new Date(cdate);
						if (i == 0 && sday.length == 2) { // 지난 달 일자 정보
							// ignore
						} else if (i > 3 && day < 20) { // 다음 달 일자 정보
							// ignore
						} else if (date.getDay() == 0 || date.getDay() == 6 || isEventDay(cdate)) { // 공휴일은 제외
							// ignore
						} else if (moment().format().substring(0, 10) >= cdate) {
							// ignore
						} else {
							var apply_link = "<a href=\"/portal/" + varDef.facName + "/reserv.do" +
									"?mId=" + varDef.mId +
									"&rStartDay=" + year + "-" + month.pad(2) + "-" + day.pad(2) +
								"\">예약하기</a>";
							$(this).prepend(apply_link);
						}
					}

				});
			});
		}
	});

	// 오늘 데이터 조회
	gc.list(today);
});

// 선택일 예약현황 조회
gc.list = function(day) {
	$("#loading").show();
	$.ajax({ // 해당일 예약 데이타 조회
		url : "/portal/" + varDef.facName + "/reserv/day/list.do",
		data : { "day" : day },
		success : function(rs) {
			rs = $.parseJSON(rs);
			rsv = rs;
			var rows = "";
			if (rs.length > 0) {
				for (var i = 0, num = rs.length; i < rs.length; i++) {
					rows += "<tr>";
					rows += "<td>" + (num--) + "</td>";
					rows += "<td>" +
							"<a href=\"/" + varDef.siteCodeFull + "/" + varDef.facName + "/reserv/modify.do?mId=" + varDef.mId + "&idx=" + rs[i].idx + "\">" +
								rs[i].rName +
							"</a></td>";
					rows += "<td>" + rs[i].rTitle + "</td>";
					rows += "<td>" + rs[i].rStartDay + " " + rs[i].rStartTime + "시 ~ " + rs[i].rEndDay + " " + rs[i].rEndTime + "시</td>";
					rows += "<td>" + rs[i].rStateText + "</td>";
					rows += "</tr>";
				}
			} else {
				rows += "<tr>";
				rows += "<td colspan=\"5\">예약 내역이 없습니다</td>";
				rows += "</tr>";
			}

			$("#seach_title").html(day.substring(0, 10) + " 현재 예약 현황(총 " + rs.length + "건)");
			$("#reservationList").html(rows);
			$("#loading").hide();
		}
	});

}


