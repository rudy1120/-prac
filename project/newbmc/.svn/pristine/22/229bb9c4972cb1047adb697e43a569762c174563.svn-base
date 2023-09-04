/**
 * 동적 테이블 TR 생성/삭제 스크립트
 * 다른 기능에 이식하는 경우 name 속성의 필드명 변경해야 함.
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2017.04.05		권태성			컬럼정보 tr 변경에 따라 스크립트에서도 변경된 tr 정보를 처리하도록 수정
 * @author J.Ryeon Lee
 * @since 2016.05.03
 */

/* 입력폼 검증 (중복 컬럼값 체크) */
function validate_table() {

	/* 중복 컬럼명 체크 */

	var columnNames = $.map($("input[name^='columnDefList']"), function (item) {
		if ($(item).attr("name").indexOf("name") > -1) {
			return $(item);
		}
	});

	var columnNames_length = columnNames.length;
	var columnName_value = null;
	for (var i = 0; i < columnNames_length; i++) {
		columnName_value = $(columnNames[i]).val();
		for (var j = 0; j < columnNames_length; j++) {
			if (i != j && columnName_value == $(columnNames[j]).val()) { // 중복값이 존재하는 경우
				alert("컬럼명이 중복됩니다.");
				$(columnNames[j]).focus();
				return false;
			}
		}
	}

	/* 링크표시컬럼값 존재 체크 */

	if ($("#linkColName").val()) {
		var exist = false;
		for (var i = 0; i < columnNames_length; i++) {
			if ($(columnNames[i]).val().toLowerCase() == $("#linkColName").val().toLowerCase()) {
				exist = true;
			}
		}

		if (!exist) {
			alert("컬럼 정의에 존재하지 않는 컬럼명입니다.");
			$("#linkColName").focus();
			return false;
		}
	}

	/* 중복 순서값 체크 */

	var orders = $.map($("input[name^='columnDefList']"), function (item) {
		if ($(item).attr("name").indexOf("order") > -1) {
			return $(item);
		}
	});

	var orders_length = orders.length;
	var orders_value = null;
	for (var i = 0; i < orders_length; i++) {
		orders_value = $(orders[i]).val();
		for (var j = 0; j < orders_length; j++) {
			if (i != j && orders_value == $(orders[j]).val()) { // 중복값이 존재하는 경우
				alert("순서가 중복됩니다.");
				$(orders[j]).focus();
				return false;
			}
		}
	}

	return true;
}

/* 입력폼 html 생성 */
function create_new_tr(idx, $tr) {
	var $clone = $tr.clone();
	$clone.children().first().text(idx + 2);
	cloneAttrSetting($clone);

	var html = $clone.html();
	var rtn = "<tr data-idx='" + (idx + 1) + "'>"
		+ html.replace(new RegExp("columnDefList" + idx, "g"), "columnDefList" + (idx + 1)).replace(new RegExp(idx + "]", "g"), (idx + 1) + "]")
		+ "</tr>";

	//추가 옵션 tr에 대한 처리
	var $cloneAddItem = $($tr).next().clone();
	cloneAttrSetting($cloneAddItem);
	html = $cloneAddItem.html();
	rtn += "<tr style=\"display:none;\">"
		+ html.replace(new RegExp("columnDefList" + idx, "g"), "columnDefList" + (idx + 1)).replace(new RegExp(idx + "]", "g"), (idx + 1) + "]")
		+ "</tr>";
	return rtn;
}

function cloneAttrSetting(clone) {
	clone.children().find("input").each(function() { // input tag init
		$(this).removeAttr("value");
		$(this).removeAttr("disabled");
	});
	clone.children().find("select").each(function() { // select tag init
		$(this).find("option:selected").removeAttr("selected");
		$(this).removeAttr("disabled");
	});
}

/* 입력폼 추가 */
function add_column_def(el) {
	var childrenLength = $("tr[data-idx]").length;
	if (childrenLength == 30) {
		alert("등록 가능한 컬럼수는 최대 30개입니다.");
		return false;
	}

	var $tr = $(el).parent().parent();
	var idx = parseInt($tr.attr("data-idx"));

	var $prev = $("#column_def_container tr:nth-child(" + (idx + 1) + ")");

	$(create_new_tr(idx, $tr)).insertAfter($tr.next()); // 입력폼 추가
	childrenLength++;

	$("tr[data-idx]").each(function(index, el) {
		$(this).attr("data-idx", index);
		$(this).children().first().text(index + 1);
		$(this).children().find("input, select").each(function() {
			$(this).attr("id", $(this).attr("id").replace(new RegExp((index - 1)), index));
			$(this).attr("name", $(this).attr("name").replace(new RegExp((index - 1) + "]", "g"), index + "]"));
		});
		$(this).next().children().find("input, select").each(function() {
			$(this).attr("id", $(this).attr("id").replace(new RegExp((index - 1)), index));
			$(this).attr("name", $(this).attr("name").replace(new RegExp((index - 1) + "]", "g"), index + "]"));
		});
	});

}

/* 입력폼 제거 */
function remove_column_def(el) {
	var childrenLength = $("tr[data-idx]").length;
	if (childrenLength == 1) {
		alert("최소 1개 이상의 컬럼을 정의해야 합니다.");
		return false;
	}

	var $tr = $(el).parent().parent();
	var idx = parseInt($tr.attr("data-idx"));

	if (childrenLength != (idx + 1)) {
		$tr.next().remove();
		$tr.remove();

		$("tr[data-idx]").each(function(index, el) {
			$(this).attr("data-idx", index);
			$(this).children().first().text(index + 1);
			$(this).children().find("input, select").each(function() {
				var target = $(this).attr("id");
				$(this).attr("id", $(this).attr("id").replace(target.replace(new RegExp(/[^0-9]/g),""), index));
				$(this).attr("name", $(this).attr("name").replace(target.replace(new RegExp(/[^0-9]/g),""), index));
			});
			$(this).next().children().find("input, select").each(function() {
				var target = $(this).attr("id");
				$(this).attr("id", $(this).attr("id").replace(target.replace(new RegExp(/[^0-9]/g),""), index));
				$(this).attr("name", $(this).attr("name").replace(target.replace(new RegExp(/[^0-9]/g),""), index));
			});
		});
	} else {
		$tr.prev().find("a").show(); // 수정 페이지의 경우 마지막 컬럼이 삭제될 경우 + 기능이 없어지는 걸 방지
		$tr.next().remove();
		$tr.remove();
	}
}
