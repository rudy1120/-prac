/**
 * YHD-Calendar Data Fetch script
 *
 * ##### 개정이력(Modification Information) #####
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.07.05		sonys				최초 생성 및 코딩 v 1.0
 * 2016.08.29		J.Ryeon Lee			마스터 테이블 연계 처리
 * 2017.07.06		J.Ryeon Lee			YHDCalendarController에 맞춰 스크립트 리팩토링 및 new versioning v 1.1
 *
 * @author sonys
 * @since 2016.07.05
 * @version 1.1
 */

$.camelCase = function(str) {
 	return str.replace(/_([a-z])/g, function(g) {
 		return g[1].toUpperCase();
 	});
};
$.pick = function(arr, key) {
	return arr && key ? (arr[$.camelCase(key)] ? arr[$.camelCase(key)] : "-") : "-";
};
$.concatPeriod = function(data, tableInfo) {
	var sday = tableInfo.colDispSdayKey ? $.pick(data,tableInfo.colDispSdayKey) : $.pick(data, tableInfo.colSdayKey); // 시작일
	var stime = tableInfo.colDispStimeKey ? $.pick(data, tableInfo.colDispStimeKey) : $.pick(data, tableInfo.colStimeKey); // 시작 시각
	var eday = tableInfo.colDispEdayKey ? $.pick(data, tableInfo.colDispEdayKey) : $.pick(data, tableInfo.colEdayKey); // 종료일
	var etime = tableInfo.colDispEtimeKey ? $.pick(data, tableInfo.colDispEtimeKey) : $.pick(data, tableInfo.colEtimeKey); // 종료 시각
	return sday + "~" + eday;
};
$.appendUrlParam = function(url, key, value) {
	var appender = url && key ? (url.indexOf("?") < 0 ? "?" : "&") : "";
	return appender ? (url + appender + key + "=" + value) : url;
};

var yhdc = {
	title: function(data, tableInfo, day) {
		var url = tableInfo.colLinkUrl;
		var pkey = tableInfo.colParamKey;
		url = $.appendUrlParam(url, pkey, (pkey ? $.pick(data, pkey) : ""));
		url = $.appendUrlParam(url, "goTo", day);
		url = $.appendUrlParam(url, "mId", params.mId);

		var prefix = tableInfo.colOptTitleKey ? "[" + $.pick(data, tableInfo.colOptTitleKey) + "] " : "";
		var title = prefix + $.pick(data, tableInfo.colTitleKey);

		return tableInfo.colLinkUrl ? "<a href=\"" + url + "\">" + title + "</a>" : title;
	}
};

var daily = {};
daily.list = function(day) { // 사용자 선택일 일정 목록 조회
	$("#loading_failed").hide();
	$("#loading").show();

	$.ajax({
		cache: false, type: "get", dataType: "json",
		url: params.prefixUrl + "/daily/data/fetch.do", data: { "start": day },
		success: function(result) {
			$("#yhc-dataList").children().remove(); // 일정 초기화

			var trs = [];
			var $tr = null;
			var tableInfo = result.tableInfo;
			if (result.list && result.list.length > 0) {
				var datas = result.list; // 일정 목록
				var data = null; // 일정
				for (var i = 0; i < datas.length; i++) {
					data = datas[i];
					$tr = $("<tr>");

					if (tableInfo.colNoYn == "Y") { // 번호
						$tr.append("<td class='list_num'>"+ (i + 1) + "</td>");
					}

					$tr.append("<td>" + yhdc.title(data, tableInfo, day) + "</td>"); // 일정명

					if (tableInfo.colOptKeys) { // 추가 항목
						var optKeys = tableInfo.colOptKeys.split(",");
						for (var j = 0; j < optKeys.length; j++) {
							$tr.append("<td>" + $.pick(data, optKeys[j]) + "</td>");
						}
					}

					if (tableInfo.colDateYn == "Y") { // 일자
						$tr.append("<td>" + $.concatPeriod(data, tableInfo) + "</td>");
					}

					if (tableInfo.colStateKey) { // 접수 상태
						$tr.append("<td>" + result.stateTypeList[$.pick(data, tableInfo.colStateKey)] + "</td>");
					}

					trs.push($tr);
				}
			} else {
				$tr = $("<tr>").append("<td colspan=\"" + params.totalColSpan + "\">등록된 일정이 없습니다.</td>");
				trs.push($tr);
			}

			$("#seach_title").html(day + " " + tableInfo.colCaptionTitle + "(총 " + result.list.length + "건)");
			$("#yhc-dataList").html(trs.reduce(function(sum, thiz) { return sum + "<tr>" + $(thiz).html() + "</tr>"; }, "")); // 일정 목록
			$("#day_of_list").text(day); // 현재 선택된 날짜 caption 추가 처리 (웹 접근성 준수)
			$("#loading").hide(); // 로딩 마크 숨김
		}
	}).fail(function() {
		$("#loading").hide();
		$("#loading_failed").show();
	});
};
