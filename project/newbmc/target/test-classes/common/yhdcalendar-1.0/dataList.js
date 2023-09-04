/**
 * YHCalendar Data Fetch script
 *
 * 2016.08.29 J.Ryeon Lee 마스터 테이블 연계 처리
 *
 * @author sonys
 * @since 2016.07.05
 */

var default_colSpan = 2;
//var default_colSpan = 3;
var gc = {};
var rsv = {};

$(document).ready(function() {
	yhdc = $("#yhdCalendar").yhdCalendar({ // 캘린터 초기화
		url: params.fetchMonthlyDataUrl,
		today: params.today,
		scheduleClick: function(obj) {
			gc.list(obj.date);
		},
	});

	gc.list(params.today); // 오늘 데이타 조회
});


//선택일 예약현황 조회
gc.list = function(day) {
	$("#loading_failed").hide();
	$("#loading").show();
	//해당일 예약 데이타 조회
	$.ajax({
		url: params.fetchDailyDataUrl,
		data: { "start": day, "category": params.category, "category2": params.category2 },
		type: "get",
		success: function(rs) {
			rs = eval("[" + rs + "]")[0];
			rsv = rs;
			var rows = "";
			var colSpan = rs.tableInfo.colCol3UseYn == 'Y' ? default_colSpan + 1 : default_colSpan;
			if (rs.tableInfo.colOptCols) {
				colSpan = colSpan + rs.tableInfo.colOptCols.split(",").length;
			}

			var optColVal = "";
			if (rs.list.length > 0) {
				for (var i = 0, num = rs.list.length; i < rs.list.length; i++) {
					rows += "<tr>";
//					rows += "<td class=\"taC\">" + (num--) + "</td>";
					if (rs.tableInfo.colUseTitleLink == 'Y') {
						rows += "<td class=\"taC\"><a href=\"" + params.prefixUrl + "/" + rs.tableInfo.colUrl + ".do" +
						"?mId=" + params.mId +
						"&amp;goTo=" + day +
						"&amp;idx=" + rs.list[i].idx + "\" title=\"" + rs.tableInfo.colUrlDesc + "\">" +
							title(rs.list[i], rs.tableInfo) +
						"</a></td>";
					} else {
						rows += "<td class=\"taC\">" + title(rs.list[i], rs.tableInfo) + "</td>";
					}

					if (rs.tableInfo.colOptCols) {
						var colOptCols = rs.tableInfo.colOptCols.split(",");
						for (var cidx = 0; cidx < colOptCols.length; cidx++) {
							optColVal = rs.list[i][camelCase(colOptCols[cidx])];
							rows += "<td class=\"taC\">" + (optColVal ? optColVal : "-") + "</td>";
						}
					}

					rows += "<td class=\"taC\">" + usingTime(rs.list[i], rs.tableInfo) + "</td>";
					rows += rs.tableInfo.colCol3UseYn == 'Y' ? "<td>" +
						stateName(rs.list[i], rs.tableInfo) + "</td>" : ""; // 세 번째 항목 사용을 선택한 경우에만
					rows += "</tr>";
				}
			} else {
				rows += "<tr>";
				rows += "<td class=\"taC\" colspan=\"" + colSpan + "\">등록된 정보가 없습니다.</td>";
				rows += "</tr>";
			}

			$("#seach_title").html(day + " " + rs.tableInfo.colTableGuide + "(총 " + rs.list.length + "건)");
			$("#yhc-dataList").html(rows);
			$("#day_of_list").text(day);
			$("#loading").hide();
		}
	}).fail(function() {
		$("#loading").hide();
		$("#loading_failed").show();
	});

	//검색 초기화
	$("#camp_name,#size_name,#position_name,#use_day").val("");
};

function title(element, tableInfo) {
	if (tableInfo.colAddColName && element[camelCase(tableInfo.colAddColName)]) {
		return "[" + element[camelCase(tableInfo.colAddColName)] + "]" + element[camelCase(tableInfo.colCol1Name)];
	} else {
		return element[camelCase(tableInfo.colCol1Name)];
	}
}

function camelCase(str) {
	return str.replace(/_([a-z])/g, function(g) {
		return g[1].toUpperCase();
	});
}

function usingTime(element, tableInfo) {
	var start = element[camelCase(tableInfo.colStartColName)];
	if (tableInfo.colDispSttCol) {
		start = element[camelCase(tableInfo.colDispSttCol)];
	}

	var start_time = tableInfo.colStartTimeColName ? element[camelCase(tableInfo.colStartTimeColName)] : null;
	start_time = start_time && /^[0-9]+$/.exec(start_time) ? start_time + ":00" : start_time;
	var end = tableInfo.colStartColName == tableInfo.colEndColName ? "" : element[camelCase(tableInfo.colEndColName)];
	if (tableInfo.colDispEndCol) {
		end = element[camelCase(tableInfo.colDispEndCol)];
	}

	var end_time = tableInfo.colEndTimeColName ? element[camelCase(tableInfo.colEndTimeColName)] : null;
	end_time = end_time && /^[0-9]+$/.exec(end_time) ? end_time + ":00" : end_time;

	if (tableInfo.colDateDispYn == "Y") {
		if (start && end && start_time && end_time) {
			return start + " " + start_time + " ~ " + end + " " + end_time;
		} else if (start && end) {
			return start + " ~ " + end;
		}
	} else {
		if (start_time && end_time) {
			return start_time + " ~ " + end_time;
		}
	}

	return start;
}

function stateName(element, tableInfo) {
	var codes = tableInfo.colCol3StateCodes.split(",");
	var values = tableInfo.colCol3StateValues.split(",");
	for (var i = 0; i < codes.length; i++) {
		if (element[camelCase(tableInfo.colCol3Name)] == codes[i]) {
			return values[i];
		}
	}

	return "?";
}
