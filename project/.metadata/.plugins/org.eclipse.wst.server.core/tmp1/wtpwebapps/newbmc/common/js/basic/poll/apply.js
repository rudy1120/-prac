/**
 * apply.js
 * 
 * @version 1.0
 * @author taeseong
 * @since 2017-10-13
 * @description poll apply js
 * ---------------------------------------------------------
 * 수정일			수정자				수정내용
 * --------------------------------------------------------
 * 2017.10.13		권태성				최초 생성
 * 2018.03.21		권태성				모든 유형에 대한 처리 수정
 * ---------------------------------------------------------
 */
/** require setting **/
requirejs.config({
	paths: { inputFormValidator: yh.contextPath + "/common/js/inputFormValidator", commonProcess: yh.contextPath + "/common/js/commonProcess" },
	shim: { inputFormValidator: { exports: "inputFormValidator" }, commonProcess: { exports: "commonProcess" } }
});
requirejs(["inputFormValidator", "commonProcess"], function (inputFormValidator, commonProcess) { });

/** 배열 내에서 특정 값의 카운트를 구하기 위해 선언 */
Array.prototype.dupCnt = function (element) {
	var cnt = 0;
	for (var i = 0; i < this.length; i++) {
		if (this[i] == element) {
			cnt++;
		}
	}
	return cnt;
}

var apply = {
	idx: "",
	dupType: "",
	able: [],
	limit: [],
	test: false,
	prevent: function () {
		(event.preventDefault ? event.preventDefault() : (event.returnValue = false));
	},
	questionValid: function () {
		var qValid = true;
		/** 순위 선정형 체크 */
		$("dl[data-questiontype='5']").each(function (index) {
			var qName = "(" + $(this).find("dt > span[class='tit']").text() + ") ";
			var $dl = $(this);
			var values = new Array();
			var $select = $(this).children("dd").children("ul").find("li > select");
			var require = ($(this).attr("data-require") == "Y");
			var length = $(this).children("dd").children("ul").find("li > select").length;
			$(this).children("dd").children("ul").find("li > select").each(function () {
				if ($(this).attr("disabled") != "disabled") {
					values.push(Number($(this).val()));
				}
			});
			/** 전체 순위를 모두 선택하지 않고 일부만 선택 한 경우 확인 */
			if (values.length > 0) {
				var valid = {
					blank: false,
					blankIdx: 0,
					dup: false,
					dupIdx: 0,
					zeroCnt: 0
				};
				$(values).each(function (idx) {
					if (valid.blank == false) {
						valid.blank = (this == 0);
						valid.blankIdx = idx;
					}
					if (values.dupCnt(this) > 1) {
						valid.dup = true;
						valid.dupIdx = idx;
					}
					if (this == 0) {
						valid.zeroCnt++;
					}
				});
				if ((require && valid.blank) || (valid.zeroCnt != length && valid.blank)) {
					alert(qName + "순위를 모두 선택하세요.");
					apply.prevent();
					$select.eq(valid.blankIdx).focus();
					qValid = false;
					return false;
				}
				if (valid.zeroCnt != length && valid.dup) {
					alert(qName + "순위 값은 중복될 수 없습니다.");
					apply.prevent();
					$select.eq(valid.dupIdx).focus();
					qValid = false;
					return false;
				}
			}
		});

		/** 선택 개수 제한 */
		if (qValid) {
			if (apply.limit.length > 0) {
				$(apply.limit).each(function () {
					if (this.limit > 0) {
						var valid = {
							limit: this.limit,
							option: this.option,
							msg: "",
							$target: null
						};
						$("dl[data-questionidx='" + this.idx + "']").each(function () {
							var qName = "(" + $(this).find("dt > span[class='tit']").text() + ") ";
							if (!$(this).children("dd").children("ul").find("li > input").prop("disabled") && ($(this).data("require") == "Y" || $(this).children("dd").children("ul").find("li > input:checked").length > 0)) {
								var $inputs = $(this).children("dd").children("ul").find("li > input:checked");
								var checked = $inputs.length;
								if (valid.option == "L") { 
									if (checked > valid.limit) {
										valid.msg = qName + "응답 문항을 " + valid.limit + "개 이하 선택해주세요.";
										valid.$target = $inputs.first().focus();
									}
								} else if (valid.option == "U") {
									if (checked < valid.limit) {
										valid.msg = qName + "응답 문항을 " + valid.limit + "개 이상 선택해주세요.";
										valid.$target = $inputs.first().focus();
									}
								} else if (valid.option == "NONE") {
									if (checked != valid.limit) {
										valid.msg = qName + "응답 문항을 " + valid.limit + "개 선택해주세요.";
										valid.$target = $inputs.first().focus();
									}
								}
							}
						});
						if (valid.msg != "") {
							alert(valid.msg);
							apply.prevent();
							valid.$target.focus();
							qValid = false;
							return false;
						}
					}
				});
			}
		}

		/** 기타 항목 입력 체크 */
		if (qValid) {
			$("input[id*='answerIdx']").each(function () {
				if ($(this).prop("checked")) {
					if ($(this).next().text() == "기타") {
						var qName = "(" + $(this).parent().parent().parent().parent().find("dt > span[class='tit']").text() + ") ";
						if ($(this).next().next().val() == "") {
							alert(qName + "기타 항목을 입력하세요.");
							apply.prevent();
							$(this).next().next().focus();
							qValid = false;
							return false;
						}
					}
				}
			});
		}
		
	},
	init: function () {
		/** apply event binding */
		if (apply.able.length > 0) {
			// apply.able = [
			// 	{
			// 		"questionNum": 1,
			// 		"target": 1,
			// 		"questionIdx": 166
			// 	},
			// 	{
			// 		"questionNum": 1,
			// 		"target": 3,
			// 		"questionIdx": 166
			// 	},
			// 	{
			// 		"questionNum": 3,
			// 		"target": 1,
			// 		"questionIdx": 166
			// 	},
			// 	{
			// 		"questionNum": 1,
			// 		"target": 2,
			// 		"questionIdx": 167
			// 	},
			// 	{
			// 		"questionNum": 1,
			// 		"target": 3,
			// 		"questionIdx": 168
			// 	},
			// 	{
			// 		"questionNum": 1,
			// 		"target": 5,
			// 		"questionIdx": 168
			// 	}
			// ];
			
			/** 응답 조건 메시지 생성 */
			var nowQuestion = "";
			var nowIdx = "";
			//console.log(apply.able);
			$(apply.able).each(function () {
				var able = this;
				var $able = $("dl[data-questionidx='" + this.questionIdx + "']");
				//기존 객관식 단답인 경우에만 처리했으나 요청으로 모든 유형에 적용되도록 수정 (18.03.21 - 권태성)
				//if ($able.data("questiontype") == "2") {
					if (nowIdx != able.questionIdx) {
						nowQuestion = "";
					}
					var msg = able.questionNum + "번 질문에 ";
					var ret = "";
					if (nowQuestion != able.questionNum) {
						nowQuestion = able.questionNum;
						nowIdx = able.questionIdx;
						$(apply.able).each(function () {
							if (this.questionIdx == able.questionIdx) {			//현재 질문에 대한 조건이라면
								if (this.questionNum == able.questionNum) {		//질문이 동일 할 경우
									if (ret == "") {
										ret += this.target;
									} else {
										ret += ", " + this.target;
									}
								}
							}
						});
						msg += ret + "번으로 응답한 경우에만 응답이 가능합니다.";
						$able.children("dd").eq(0).after($("<p>", { "class": "tip mL20 red", "text": msg, "style": "margin-left: 90px;" }));
						$able.next("ul").find("li").each(function () {
							$(this).children("input").attr("data-required", "n");
							$(this).children("input").attr("disabled", true);
							$(this).children("textarea").attr("data-required", "n");
							$(this).children("textarea").attr("disabled", true);
							$(this).children("select").first().attr("data-required", "n");
							$(this).children("select").first().attr("disabled", true);
							$(this).children().find("table > tbody > tr > td > input[type='radio']").attr("disabled", true);
							$(this).children().find("table > tbody > tr > td > input[type='radio']").attr("data-required", "n");
						});
					}
				//}
			});

			/** input disabled proc */
			$(apply.able).each(function () {
				var able = this;
				var $able = $("dl[data-questionidx='" + this.questionIdx + "']").next("ul");
				//기존 객관식 단답인 경우에만 처리했으나 요청으로 모든 유형에 적용되도록 수정 (18.03.21 - 권태성)
				//if ($able.data("questiontype") == "2") {
				console.log("$able",$able);
					var require = $("dl[data-questionidx='" + this.questionIdx + "']").data("require");
					console.log("require33:",require);
					$("dl[data-questionnum='" + able.questionNum + "']").next("ul").find("li input").click(function () {
						console.log("require:",require);
						console.log("able.questionNum:", able.questionNum);
						$able.find("li > input").attr("disabled", true);
						$able.find("li > input").prop("checked", false);
						$able.find("li > input").attr("data-required", "n");
						$able.find("li > textarea").val("");
						$able.find("li > textarea").attr("data-required", "n");
						$able.find("li > textarea").attr("disabled", true);
						$able.find("li > input").attr(("type") == "text" ? $able.find("li > input").val("") : "");
						$able.find("li > select").attr("disabled", true);
						$able.find("li > select").attr("data-required", "n");
						$able.find("li > select > option").attr("selected", false);
						$able.find("table > tbody > tr > td > input[type='radio']").attr("disabled", true);
						$able.find("table > tbody > tr > td > input[type='radio']").attr("data-required", "n");

						var idx = $(this).data("idx");
						$(apply.able).each(function () {
							if (able.questionIdx == this.questionIdx && idx == this.target) {
								if ($.makeArray($(apply.able).map(function() { return this.target })).indexOf(parseInt(idx)) > -1) {
									$able.find("li > input").prop("checked", false);
									$able.find("li > input").attr("disabled", false);
									$able.find("li > input").attr("data-required", require.toLowerCase());
									$able.find("li > textarea").attr("disabled", false);
									$able.find("li > textarea").attr("data-required", require.toLowerCase());
									$able.find("li > select").attr("disabled", false);
									$able.find("li > select").first().attr("data-required", require.toLowerCase());
									$able.find("table > tbody > tr > td > input[type='radio']").attr("disabled", false);
									$able.find("table > tbody > tr > td > input[type='radio']").attr("data-required", require.toLowerCase());
									console.log("ddddddd",$able.find("li > input").attr("data-required"));
								}
							}
						});
					});
				//}
			});

		}

		$("#tel2").keypress(function (event) {
			if ((event.keyCode < 48) || (event.keyCode > 57)) {
				return false;
			}
		});

		$("#tel3").keypress(function (event) {
			if ((event.keyCode < 48) || (event.keyCode > 57)) {
				return false;
			}
		});

		/** 기타 처리 - 라디오 버튼 */
		$("input[name*='answerIdx']").click(function () {
			var $parent = $(this).parent().parent();
			if ($(this).attr("type") == "radio" && $(this).parent().children("label").text() != "기타") {
				if ($parent.next().prop("tagName") != null && $parent.next().prop("tagName") != undefined && $parent.next().prop("tagName").toLowerCase() == "ul") {
					if ($(this).attr("type") == "radio" ) {
						var $next = $parent.next();
						if ($next.children().children("input[type='radio']")) {
							$next.children().children("input[type='text']").val("");
							$next.children().children("input[type='text']").attr("readonly", true);
						}
					}	
				}
			} else if ($(this).attr("type") == "radio" && $(this).parent().children("label").text() == "기타") {
				$(this).parent().children("input[type='text']").attr("readonly", false);
			}
		});

		/** 기타 처리 - 체크 박스 */
		$("input[id*='answerIdxList']").click(function () {
			var $next = $(this).next();
			if ($next.text() == "기타" && $(this).prop("checked")) {
				$next.next().attr("readonly", false);
			} else if ($next.text() == "기타" && !$(this).prop("checked")) {
				$next.next().val("");
				$next.next().attr("readonly", true);
			}
		});


		$("#submitBtn").click(function (event) {
			//apply.init();
			$("#tel").val($("#tel1").val() + "-" + $("#tel2").val() + "-" + $("#tel3").val());
			if (validator("#apply")) {
				apply.questionValid();
				var options = {
					url: yh.contextPath + "/" + yh.siteCodeFull + "/poll/applyProc.do",
					type: "post",
					dataType: "json",
					resetForm: false,
					beforeSubmit: function () {
					},
					success: function (data) {
						alert(data.msg);
						if (data.flag === "success") {
							document.location.href = yh.contextPath + "/" + yh.siteCodeFull + "/poll/list.do?mId=" + yh.mId;
						} else {
							return false;
						}
					},
					error: function (e) {
						alert("저장 처리중 오류가 발생하였습니다.\n관리자에게 문의하세요.");
						console.log(e);
						console.log(e.responseText);
						return false;
					}
				};
				if (apply.test) {
					apply.prevent();
					return false;
				} else {
					$("#apply").ajaxForm(options);
				}
			} else {
				apply.prevent();
				return false;
			}
		});
	}
};

$(document).ready(function () {
	apply.init();
});