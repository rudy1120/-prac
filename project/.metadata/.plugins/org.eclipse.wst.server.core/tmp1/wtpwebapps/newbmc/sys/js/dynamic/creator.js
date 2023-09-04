/**
 * 현황 생성화면 스크립트
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2017.04.11		권태성			최초 생성
 *
 * @author 권태성
 * @since 2016.04.11
 */
function fn_add_proc() {
	$("#save").hide();
	var msg = (isNew ? "등록" : "수정");
	var form_validate = $("#tableDefJson").val() ? jsonDataValidator() : validator();
	if (form_validate && additional_validator() && validate_table() && confirm(msg+"하시겠습니까?")) {
		$.ajax({ type : "post", data : $("#form").serialize(), url : "/sys/dataMng/"+(isNew ? "writeProc" : "modifyProc")+".do",
			success : function (data) {
				var result = $.parseJSON(data);
				if (result.success) {
					alert("현황관리 정보를 " + msg + "했습니다.");
					location.href = "/sys/dataMng/list.do?mId=" + mId;
					return false;
				} else if (result.selector) {
					alert("잘못된 입력값이 존재합니다.");
					$(result.selector).focus();
				} else {
					alert("처리 중 에러가 발생했습니다. 관리자에게 보고해주세요.");
				}
			}
		}).fail(function() { alert("서버와 통신 중 에러가 발생했습니다. 관리자에게 보고해주세요."); });
	}

	$("#save").show();
}

function jsonDataValidator() {
	var columnDefContainerClone = $("#column_def_container").clone();
	$("#column_def_container").remove(); // 컬럼 테이블 비활성화

	var rtn = validator();
	if (rtn) {
		try {
			JSON.parse($("#tableDefJson").val());
			rtn = true;
		} catch(e) {
			alert("json 파싱 중 오류가 발생했습니다. 형식을 확인해주세요.");
			$("#tableDefJson").focus();
			rtn = false;
		}
	}

	$("#column_def_table").children().last().after(columnDefContainerClone);
	return rtn;
}

function fn_is_unique_tableName() {
	if ($("#tableName").val()) {
		$.ajax({ type : "post", data : { "tableName" : $("#tableName").val() }, url : "/sys/dataMng/isUniqueTableName.do",
				success : function (data) {
					var result = $.parseJSON(data);
					if (result.isUniqueTableName) {
						alert("사용 가능한 테이블명입니다.");
					} else {
						alert("사용 중인 테이블명입니다. 다른 이름을 지정해주세요.");
						$("#tableName").val("");
					}
				}
		}).fail(function() { alert("서버와 통신 중 에러가 발생했습니다. 관리자에게 보고해주세요."); });

		return false;
	} else {
		alert("테이블명을 입력해주세요.");
	}

	$("#tableName").focus();
}

function additional_validator() {

	if (($("#category1Length").val() != 0 || $("#category2Length").val() != 0) && $("#category").val() == "") {
		alert("카테고리 이름을 확인하세요.");
		$("#category").focus();
		return false;
	}

	return true;
}

function sectionViewToggle(idx) {
	$("#accordion_view" + idx).slideToggle("slow");
}

function showAddItem(obj) {
	if ($(obj).parent().parent().next().css("display") == "none") {
		$(obj).parent().parent().next().show();
	} else {
		$(obj).parent().parent().next().hide();
	}
}

function useFileToggle() {
	if ($("#useFile").val() == "Y") {
		$("#fileCnt").parent().parent().show();
		$("#imageOnly1").parent().parent().show();
	} else {
		$("#fileCnt").parent().parent().hide();
		$("#imageOnly1").parent().parent().hide();
	}
}

function disableSize(obj) {
	if (obj.value == "file") {
		$(obj).parent().next().children("input").val("20");							//첨부파일의 경우 필드길이 20고정
		$(obj).parent().next().children("input").attr("disabled", "disabled");		//첨부파일의 경우 길이 수정 비대상
		$("#columnDefList"+$(obj).parent().parent().attr("data-idx")+"\\.isSearch option:eq(0)").removeAttr("selected");
		$("#columnDefList"+$(obj).parent().parent().attr("data-idx")+"\\.isSearch option:eq(1)").attr("selected", "selected");	//첨부파일의 경우 검색비대상
		$("#columnDefList"+$(obj).parent().parent().attr("data-idx")+"\\.isSearch").attr("disabled", "disabled");
	} else {
		$(obj).parent().next().children("input").val("");
		$(obj).parent().next().children("input").removeAttr("disabled");
		$("#columnDefList"+$(obj).parent().parent().attr("data-idx")+"\\.isSearch option:eq(1)").removeAttr("selected");
		$("#columnDefList"+$(obj).parent().parent().attr("data-idx")+"\\.isSearch option:eq(0)").attr("selected", "selected");
		$("#columnDefList"+$(obj).parent().parent().attr("data-idx")+"\\.isSearch").removeAttr("disabled");
	}
}