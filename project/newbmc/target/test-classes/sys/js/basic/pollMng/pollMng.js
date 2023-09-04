/**
 * pollMng.js
 *
 * @version 1.0
 * @author taeseong
 * @since 2017-07-20
 * @description pollMng js
 * ---------------------------------------------------------
 * 수정일			수정자				수정내용
 * ---------------------------------------------------------
 * 2017.07.20		권태성				최초 생성
 * 2017.11.24		권태성				추가 수정사항 반영
 * ---------------------------------------------------------
 */
/** require setting */
requirejs.config({
	paths: {
		"datePicker": yh.contextPath + "/common/js/datePicker",
		"inputFormValidator": yh.contextPath + "/common/js/inputFormValidator",
		"commonProcess": yh.contextPath + "/common/js/commonProcess"
	},
	shim: {
		"datePicker": { exports: "datePicker" },
		"inputFormValidator": { exports: "inputFormValidator" },
		"commonProcess": { exports: "commonProcess" },
	}
});

requirejs(["datePicker", "inputFormValidator", "commonProcess"], function (datePicker, inputFormValidator, commonProcess) {
	dp.bind($("[data-date=y]"));
});

var pollMng = {
	mode: "",
	init: function () {
		if (pollMng.mode === "list") {
			$("table > tbody > tr > td > select").change(function() {
				pollMng.viewChange($(this).data("idx"), this.value);
			});
		}
		if (pollMng.mode === "update") {
			//첨부파일 삭제 이벤트
			$("td[data-fileName]").each(function(idx, ele) {
				try {
					var attachId = $(ele).children("div").children("ul").children("li").children("input").attr("onclick").replace("fn_egov_deleteFile('", "").substring(0, 20);
					var tmp = $(ele).children("div").children("ul").children("li").children("input").attr("onclick").replace("fn_egov_deleteFile('"+attachId+"','", "");
					var fileSn = tmp.substring(0, tmp.indexOf("'"));
					var type = (idx === 0 ? "header" : "main");
					//console.log(attachId + " / " + fileSn);
					$(ele).children("div").children("ul").children("li").children("input").attr("onclick", "pollMng.fileDelete('"+attachId+"', '"+fileSn+"', '"+type+"'); return false;");
				} catch (e) {
					console.log(e);
				}
			});
			$("#dupType").attr("disabled", "disabled");
			if ($("#joinStAge").val() != "") {
				$("#joinStAge").attr("readonly", false);
			}
			if ($("#joinEdAge").val() != "") {
				$("#joinEdAge").attr("readonly", false);
			}
			if ($("#lotteryYn1").prop("checked")) {
				$("#lotteryNum").val("0");
				$("#lotteryNum").attr("readonly", true);
			}
			if ($("#lotteryYn2").prop("checked")) {
				$("#lotteryNum").attr("readonly", false);
			}
		}
		/** 본인 인증 확인 */
		$("#joinGender").change(function () {
			if ($(this).val() != "A") {
				if ($("#dupType").val() != "4") {
					alert("참여대상 상세 설정은 본인 인증 체크를 사용할 때만 사용할 수 있습니다.");
					$(this).val("A");
					$("#dupType").focus();
					return false;
				}
			}
		});
		$("#joinAgeYn1").click(function() {
			$("#joinStAge").val("");
			$("#joinStAge").attr("readonly", true);
			$("#joinEdAge").val("");
			$("#joinEdAge").attr("readonly", true);
		});
		$("#joinAgeYn2").click(function () {
			if ($("#dupType").val() != "4") {
				alert("참여대상 상세 설정은 본인 인증 체크를 사용할 때만 사용할 수 있습니다.");
				$("input[name='joinAgeYn']").val("N");
				$("#dupType").focus();
				return false;
			} else {
				$("#joinStAge").attr("readonly", false);
				$("#joinEdAge").attr("readonly", false);
			}
		});
		$("#lotteryYn1").click(function () {
			if ($("#lotteryYn1").prop("checked")) {
				$("#lotteryNum").val("0");
				$("#lotteryNum").attr("readonly", true);
			}
		});
		$("#lotteryYn2").click(function () {
			if ($("#lotteryYn2").prop("checked")) {
				$("#lotteryNum").attr("readonly", false);
			}
			if ($("#dupType").val() != "4") {
				alert("참여자 추첨 설정은 본인 인증 체크를 사용할 때만 사용할 수 있습니다.");
				$("[name=lotteryYn][value='N']").prop("checked", true);
				$("#dupType").focus();
				return false;
			}
		});
		$("#dupType").change(function () {
			if ($(this).val() != "4") {
				$("#joinGender").val("A");
				$("#lotteryYn1").prop("checked", true);
				$("#joinAgeYn1").prop("checked", true);
				$("#joinStAge").val("");
				$("#joinEdAge").val("");
			} else {
				$("#joinGender").val("A");
				$("#joinAgeYn1").prop("checked", true);
			}
		});
		/** 기존 질문 불러오기 */
		$("#questionYn1").click(function () {
			$("#beforeIdx").hide();
			$("#beforeIdx").val("");
		});
		$("#questionYn2").click(function () {
			$("#beforeIdx").show();
			$("#beforeIdx").val("");
		});
		if (yh.error) {
			alert("처리중 오류가 발생하였습니다. 입력하신 데이터를 확인해주세요.");
			return false;
		}
	},
	viewChange: function(idx, val) {
		pollMng.ajax("/sys/pollMng/viewChange.do", {"idx" : idx, "val" : val}, function(data) {
			if (data.flag == "success") {
				alert("변경되었습니다.");
				return false;
			} else {
				alert("변경 처리중 오류가 발생하였습니다.");
				return false;
			}
		});
	},
	valueValid: function() {
		try {
			if ($("#joinAgeYn2").prop("checked") && ($("#joinStAge").val() == "" || $("#joinEdAge").val() == "")) {
				alert("참여대상 연령 기간을 입력하세요.");
				if ($("#joinStAge").val() == "") {
					$("#joinStAge").focus();
				} else {
					$("#joinEdAge").focus();
				}
				return false;
			}
			if ($("#lotteryYn2").prop("checked") && ($("#lotteryNum").val() == "0" || $("#lotteryNum").val() == "0")) {
				alert("추첨인원은 1명 이상으로 입력해야합니다.");
				$("#lotteryNum").focus();
				return false;
			}
			if ($("#header_file").val() != "" && $("#header_file").val() != undefined) {
				if (!pollMng.validFileExt($("#header_file").val())) {
					alert("머릿말 첨부파일은 이미지 파일만 등록가능합니다.");
					$("#header_file").focus();
					return false;
				}
				if ($("#headerFileCn").val() == "") {
					alert("머릿말 첨부파일 설명글을 입력하세요.");
					$("#headerFileCn").focus();
					return false;
				}
			}
			if ($("#main_file").val() != "" && $("#main_file").val() != undefined) {
				if (!pollMng.validFileExt($("#main_file").val())) {
					alert("대표 이미지는 이미지 파일만 등록가능합니다.");
					$("#main_file").focus();
					return false;
				}
				if ($("#mainFileCn").val() == "") {
					alert("대표 이미지 첨부파일 설명글을 입력하세요.");
					$("#mainFileCn").focus();
					return false;
				}
			}
			if ($("#dupType").val() == "4" && $("#joinAgeYn2").prop("checked")) {
				if ($("#joinStAge").val() == "") {
					alert("시작 연령을 입력하세요.");
					return false;
				}
				if ($("#joinEdAge").val() == "") {
					alert("종료 연령을 입력하세요.");
					return false;
				}
				if (parseInt($("#joinStAge").val()) > parseInt($("#joinEdAge").val())) {
					alert("시작 연령이 종료 연령보다 클 수 없습니다.");
					$("#joinStAge").focus();
					return false;
				}
			}
		} catch(e) {
			console.log(e);
			return false;
		}
		return true;
	},
	validFileExt: function(name) {
		if (name != "" && name != undefined) {
			var ext = name.slice(name.lastIndexOf(".") + 1).toLowerCase();
			return (ext == "png" || ext == "jpg" || ext == "jpeg" || ext == "gif" || ext == "bmp");
		} else {
			return false;
		}
	},
	validProc: function() {
		if (pollMng.valueValid() && validator() && confirm("등록하시겠습니까?")) {
			$("#dupType").removeAttr("disabled");
			return true;
		}
		return false;
	},
	fileDelete: function(attachId, fileSn, type) {
		//alert(attachId);
		 pollMng.ajax("/sys/pollMng/attachDel.do", {"attachId": attachId}, function(data) {
		 	if (data.flag == "success") {
				if (fn_egov_deleteFile(attachId, fileSn)) {	//기존 파일 삭제 함수 호출
				//if (true) {
					pollMng.fileFormHTML(type, attachId);
				}
		 	} else {
		 		alert("파일이 삭제되지 않았습니다. 관리자에게 문의하세요.");
		 		return false;
		 	}
		});
	},
	fileFormHTML: function(type, attachId) {
		if (type === "header") {
			var $headerFile = $("<input>", {"type": "file", "id": "header_file", "name": "header_file", "data-validator": "on", "data-fieldName": "머릿말 첨부파일", "class": "input300 mB10", "value": ""});
			var $div = $("<div>", {class: "pT5"});
			var $label = $("<label>", {for: "headerFileCn", text: "설명글" });
			var $headerFileCn = $("<input>", {type: "text", id: "headerFileCn", name: "headerFileCn", class: "input400", value: ""});
			var $p = $("<p>", {class: "tip", text: "※ 접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요(확장자 jpg, gif, png, bmp 파일)"});
			var $p2 = $("<p>", {class: "tip", text: "* 머릿말 이미지는 783 x 522 사이즈를 권장합니다."});
			$("td[data-fileName='"+attachId+"']").html("").append($headerFile).append($div.append($label).append(": ").append($headerFileCn)).append($p).append($p2);
		} else if (type === "main") {
			var $mainFile = $("<input>", {"type": "file", "id": "main_file", "name": "main_file", "data-validator": "on", "data-fieldName": "대표 이미지", "class": "input300 mB10", "value": ""});
			var $descDiv = $("<div>", {class: "pT5"});
			var $label = $("<label>", {for: "mainFileCn", text: "설명글" });
			var $mainFileCn = $("<input>", {type: "text", id: "mainFileCn", name: "mainFileCn", class: "input400", value: ""});
			var $pDiv = $("<div>");
			var $p1 = $("<p>", {text: "* 설문조사 리스트 화면에 보여질 이미지를 등록해 주세요."});
			var $p2 = $("<p>", {text: "* 대표 이미지는 314 x 245 사이즈를 권장합니다."});
			var $p3 = $("<p>", {text: "※ 이미지 파일 설명글 작성 방법 (시각장애인의 웹 접근성 확보)"});
			var $p4 = $("<p>", {text: "- 사진 : 사진의 내용을 자세히 묘사 (예시. 청사 정문에 활짝 핀 매화 꽃 사진입니다.)"});
			var $p5 = $("<p>", {text: "- 리플릿 및 포스터 : 리플릿 등에 기재되어 있는 모든 내용(제목, 기간, 장소, 내용, 문의전화 등)을 한글로 작성"});
			var $p6 = $("<p>", {text: "※ 파일은 최대 1개까지 첨부하실 수 있습니다. 첨부 가능한 하나의 파일 사이즈는 최대 100Mbyte 입니다."});
			$("td[data-fileName='"+attachId+"']").html("").append($mainFile).append($descDiv.append($label).append(": ").append($mainFileCn)).append($pDiv.append($p1).append($p2).append($p3).append($p4).append($p5).append($p6));
		}
	},
	ajax: function(url, data, success) {
		$.ajax({
			url: yh.contextPath + url,
			dataType: "json",
			data: data,
			success: success,
			error : function(e) {
				alert("서버와의 통신 중 오류가 발생하였습니다.");
				console.log(e);
				return false;
			}
		});
	}
};

$(document).ready(function() {
	pollMng.init();
});
