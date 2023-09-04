/**
* advanceInfoMng.js
*
* @author taeseong
* @since 2017.05.31
*/

function brmChange(type, obj, init) {
	if ($(obj).val()) {
		var depth = parseInt(type) + 1;
		$.ajax({
			url: yh.contextPath + "/sys/advanceInfoMng/brm.do",
			dataType: 'json',
			data: { depth: depth, uppIdx: $(obj).val().split(",")[0] },
			success: function (data) {
				$("#brm" + depth).html("");
				$("#brm" + depth).append($("<option>", { value: "", text: "선택하세요" }));
				$(data).each(function (i) {
					$("#brm" + depth).append($("<option>", { value: data[i].brmIdx + "," + data[i].name, text: data[i].name }));
				});

				if (init) {
					$("#brm" + depth).val((depth == 2 ? brm2 : brm3) + "," + (depth == 2 ? brm2Name : brm3Name)).prop("selected", true);
					if ($(obj).get(0) == $("#brm1").get(0)) {
						brmChange(2, $("#brm2"), true);
					}
				}
			}
		});
	}
}

$(document).ready(function () {
	/** 셀렉트 박스 */
	if (advance.edit) {
		$("#depts").fastselect();

		/** 수정 시에만 brm 초기 세팅 */
		if (yh.isNew != "Y") {
			brmChange(1, $("#brm1"), true);
		}
	}

	/** 공표 방법에 따른 처리 */
	$("#typeTab > ul > li > a").click(function (event) {
		$("#typeTab > ul > li > a").each(function (index, el) {
			$(this).removeClass("on");
		});
		$(this).addClass("on");
		$("#type").val($(this).attr("data-value"));
		if ($(this).attr("data-value") == "0") {
			$("#dataFileTable").fadeIn(500);
			$("#dataLinkTable").hide();
			$("#infoTxt > p").text("공표 자료가 파일인 경우에 추가해주세요.");
		} else {
			$("#infoTxt > p").text("공표 자료가 링크인 경우에 추가해주세요.");
			$("#dataFileTable").hide();
			$("#dataLinkTable").fadeIn(500);
		}
		return false;
	});

	/** 취소 버튼 처리 */
	$("#backBtn").click(function (event) {
		document.getElementById("list").submit();
		return false;
	});

	$("#submitBtn").click(function (event) {
		if (validator()) {
			if ($("#type").val() == "0") {
				$("#dataLinkTable").remove();
			} else {
				$("#dataFileTable").remove();
			}
			return true;
		} else {
			return false;
		}
	});

	/** 공표 항목 추가 */
	$("#addField").click(function (event) {
		var tableId = ($("#type").val() == "0" ? "dataFileTable" : "dataLinkTable");
		var date = new Date();
		var today = date.getFullYear() + "-" + ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "-" + ((date.getDate() + 1) < 10 ? "0" + (date.getDate()) : date.getDate());
		var trLength = $("#" + tableId + " > tbody > tr").length;

		if (trLength > 0) {
			var $tr = $("#" + tableId + " > tbody > tr").last().clone();
			$tr.find("td").eq(0).find("[type=input]").remove();
			$tr.find("td").eq(0).text(trLength + 1);
			$tr.find("select").attr("id", "dataDept" + (trLength + 1));
			$tr.find("select").attr("name", "dataList[" + trLength + "].dept");
			$tr.find("select").val("").prop("selected", true);
			$tr.find("td").eq(4).text(today);
			if ($("#type").val() == "0") {
				if (yh.isNew == "Y") {
					$tr.find("[type=file]").attr("id", "dataFile" + (trLength + 1));
					$tr.find("[type=file]").attr("name", "dataList[" + trLength + "].dataFile");
				} else {
					$tr.find("td").eq(2).html($("<input>", { type: "file", id: "dataFile" + (trLength + 1), name: "dataList[" + trLength + "].dataFile", class: "input300 mB10 mT10", value: "" })[0].outerHTML);
				}
			} else {
				$tr.find("td").eq(1).find("[type=text]").attr("id", "dataName" + (trLength + 1));
				$tr.find("td").eq(1).find("[type=text]").attr("name", "dataList[" + trLength + "].name");
				$tr.find("td").eq(1).find("[type=text]").val("");
				$tr.find("td").eq(2).find("[type=text]").attr("id", "dataUrl" + (trLength + 1));
				$tr.find("td").eq(2).find("[type=text]").attr("name", "dataList[" + trLength + "].url");
				$tr.find("td").eq(2).find("[type=text]").val("");
			}
			$("#" + tableId + " > tbody").append($tr);
		} else {
			var $tr = $("<tr>");
			if (tableId == "dataFileTable") {
				$tr.append($("<td>", { text: "1" }));
				$tr.append($("<td>", { text: "파일등록" }));
				$tr.append($("<td>").append($("<input>", { type: "file", id: "dataFile1", name: "dataList[0].dataFile", class: "input300 mB10 mT10" })));
				$tr.append($("<td>").append($("<select>", { id: "dataDept1", name: "dataList[0].dept" }).html(deptOptions)));
				$tr.append($("<td>", { text: today }));
				$tr.append($("<td>", { text: "0" }));
			} else {
				$tr.append($("<td>", { text: "1" }));
				$tr.append($("<td>").append($("<input>", { type: "text", id: "dataName1", name: "dataList[0].name", class: "input150", value: "" })));
				$tr.append($("<td>").append($("<input>", { type: "text", id: "dataUrl1", name: "dataList[0].url", class: "input150", value: "" })));
				$tr.append($("<td>").append($("<select>", { id: "dataDept1", name: "dataList[0].dept" }).html(deptOptions)));
				$tr.append($("<td>", { text: today }));
				$tr.append($("<td>", { text: "-" }));
			}
			$("#" + tableId + " > tbody").append($tr);
		}
	});

	/** 공표 항목 삭제 */
	$("#rmField").click(function () {
		var tableId = ($("#type").val() == "0" ? "dataFileTable" : "dataLinkTable");
		var trLength = $("#" + tableId + " > tbody > tr").length;	//공표 항목을 모두 제거할 수도 있기 때문에 일단 조건을 걸지 않음.
		var $tr = $("#" + tableId + " > tbody > tr").last().clone();
		if ($tr.find("td").eq(0).find("input")) {
			//기존에 있는 데이터라면 삭제 대상에 포함
			$("#rmIdx").val(($("#rmIdx").val() == "" ? $tr.find("td").eq(0).find("input").val() : $("#rmIdx").val() + "," + $tr.find("td").eq(0).find("input").val()));
		}
		$("#" + tableId + " > tbody > tr").last().remove();
	});

	if (yh.error) {
		alert("처리중 오류가 발생하였습니다. 입력하신 데이터를 확인해주세요.");
		return false;
	}

	/** 검색 옵션 */
	$("#searchType").change(function () {
		if ($(this).val() == "category") {
			$("#searchCat1").show();
			$("#searchCat2").val("").hide();
		} else if ($(this).val() == "dept") {
			$("#searchCat2").show();
			$("#searchCat1").val("").hide();
		} else {
			$("#searchCat2").val("").hide();
			$("#searchCat1").val("").hide();
		}
	});

	if (!advance.edit && advance.searchType != "") {
		if (advance.searchType == "category") {
			$("#searchCat1").show().val(searchMethod).prop("selected", true);
		} else {
			$("#searchCat2").show().val(searchDept).prop("selected", true);
		}
	}

});