/**
 * questionMng.js
 * 
 * @version 1.0
 * @author taeseong
 * @since 2017-08-31
 * @description poll questionMng js
 * ---------------------------------------------------------
 * 수정일			수정자				수정내용
  * --------------------------------------------------------
 * 2017.08.31	  권태성			  최초 생성
 * ---------------------------------------------------------
 */
/** require setting **/
requirejs.config({
	paths: { "inputFormValidator": yh.contextPath + "/common/js/inputFormValidator", "commonProcess": yh.contextPath + "/common/js/commonProcess" },
	shim: { "inputFormValidator": { exports: "inputFormValidator" }, "commonProcess": { exports: "commonProcess" }, }
});
requirejs(["inputFormValidator", "commonProcess"], function (inputFormValidator, commonProcess) { });

$.fn.appendEtcBtn = function () {
	if ($(this).length == 4) {
		$(this).eq(2).after($("<a>", { href: "#", class: "btn_etc_plus", onclick: "questionMng.answer.addEtc(this); return false;", text: "기타" }));
	} else if ($(this).length == 3) {
		$(this).eq(1).after($("<a>", { href: "#", class: "btn_etc_plus", onclick: "questionMng.answer.addEtc(this); return false;", text: "기타" }));
	}
};

var questionMng = {
	mode: "",
	init: function () {
		$("input[id*='ableYn']").click(function () {
			($(this).val() === "Y" ? $(this).next().next("div").show().next("p").show() : $(this).next().next().next().next("div").hide().next("p").hide());
		});

		/** 건너뛰기 값 변경시 처리 */
		$("input[type='text'][id*='questionNum']").change(function () {
			var targetType = $("#questionDiv" + (Number($(this).val()) - 1)).find("table > tbody > tr").first().find("td > select").val();
			if (targetType == undefined) {
				alert($(this).val() + "번 질문이 없습니다.");
				$(this).val("");
				$(this).focus();
				return false;				
			} else if (targetType != "2") {
				alert("문항 건너뛰기는 객관식 단답만 적용할 수 있습니다.");
				$(this).val("");
				$(this).focus();
				return false;
			} else {
				$(this).parent().parent().find("input[type='hidden'][id*='questionNum']").val($(this).val());
			}
		});

		/** question Type change Bind **/
		$("select[id*=type]").change(function () {
			var $div = $(this).parent().parent().parent().parent().parent();
			$div.children().remove();
			var $clone = $("#template" + $(this).val()).clone();
			$clone.children("h3").text("질문" + (Number($div.attr("data-idx")) + 1));
			$clone.find("table").children("tbody").children("tr").find("input[type='radio'][id*='ableYn1']").prop("checked", true);
			$div.append($clone.children());
			questionMng.question.renumbering();
			questionMng.init();
		});

		/** questionImg **/
		$("input[id*='questionAtchfile']").change(function () {
			var $span = $(this).parent().children("span");
			if (!$(this).val().match(/\.(gif|jpg|jpeg|png)$/i)) {
				alert("이미지 파일만 첨부할 수 있습니다.");
				$(this).val("");
				$span.text("");
				$(this).parent().children("div").remove();
				return false;
			} else {
				$span.text($(this).val().substring($(this).val().lastIndexOf("\\") + 1, $(this).val().length));
				if ($(this).parent().children("div").length == 0) {
					$(this).parent().append($("<div>"));
					$(this).parent().children("div").append($("<input>", { type: "text", id: $(this).attr("id").replace("questionAtchfile", "questionFileCn"), name: $(this).attr("name").replace("questionAtchfile", "questionFileCn"), class: "input500 mT5" }));
					$(this).parent().children("div").append($("<p>").append($("<span>", { class: "tip red", text: "접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요." })));
					//$(this).parent().children("div").append($("<p>").append($("<span>", { class: "tip red", text: "질문 이미지는 최대 804 x 536 사이즈를 권장합니다." })));
				}
			}
			if ($(this).next().next().text() != "개별삭제") {
				$(this).next().after($("<a>", { href: "#", onclick: "questionMng.question.delImg(this); return false;", class: "btn_list_img_delete mL5", text: "개별삭제" }));
			}
			$(this).parent().children("div").children("input").val("");
			$(this).parent().children("div").show();
		});

		$("#submitBtn").click(function (event) {
			if (validator("#write")) {
				var valid = true;
				valid = questionMng.valid();
				if (valid) {
					var options = {
						url: yh.contextPath + "/sys/pollMng/question/" + (questionMng.mode == "update" ? "updateProc" : "writeProc") + ".do?mId=" + yh.mId,
						type: "post",
						dataType: "json",
						resetForm: false,
						enctype: "multipart/form-data",
						beforeSubmit: function () {
							return valid;
						},
						success: function (data) {
							alert(data.msg);
							if (data.flag == "success") {
								document.location.href = yh.contextPath + "/sys/pollMng/list.do?mId=" + yh.mId;
							} else {
								return false;
							}
						},
						error: function (e) {
							alert("저장 처리중 오류가 발생하였습니다. 창을 닫지 마시고 관리자에게 문의하세요.");
							console.log(e);
							console.log(e.responseText);
							return false;
						}
					};
					$("#write").ajaxForm(options);
				}
			} else {
				(event.preventDefault ? event.preventDefault() : (event.returnValue = false));
				return false;
			}
		});
		/** answerImg **/
		questionMng.answer.fileChange();

		if (yh.error) {
			alert("처리중 오류가 발생하였습니다. 입력하신 데이터를 확인해주세요. (" + yh.error + ")");
			return false;
		}
		//console.log("========== questionMng.js loaded (" + new Date().getFullYear() + "/" + (new Date().getMonth() + 1) + "/" + new Date().getDate() + " " + new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getMilliseconds() + ")");
	},
	valid: function () {
		var valid = true;
		var PLEASE_RM_MSG = " 사용하지 않을 경우 제거하고 저장하시기 바랍니다.";
		var QUESTION_IMG_MSG = "웹 접근성 준수를 위해 질문 이미지 설명글을 입력하세요.";
		var ANSWER_IMG_MSG = "웹 접근성 준수를 위해 보기 이미지 설명글을 입력하세요.";
		try {
			/** able check **/
			if (valid) {
				$("input[id*='ableYn']:checked").each(function () {
					if ($(this).val() == "N") {
						$(this).parent().children("div").remove();
					} else {
						var $div = $(this).parent().children("div");
						$div.children("p").each(function (idx) {
							$(this).find("input").each(function (idx2) {
								if ($(this).val() == "") {
									alert("문항 건너뛰기 항목을 입력하세요." + PLEASE_RM_MSG);
									$(this).focus();
									valid = false;
									questionMng.prevent();
									return false;
								}
							});
						});
					}
				});
			}
			/** answer check **/
			if (valid) {
				$("#write").find("input[id$='answer']").each(function () {
					if ($(this).val() === "") {
						alert("보기 정보를 입력하세요." + PLEASE_RM_MSG);
						$(this).focus();
						valid = false;
						questionMng.prevent();
						return false;
					}
				});
			}
			/** matrix check */
			if (valid) {
				$("#write").find("input[id$='matrixQuestion']").each(function () {
					if ($(this).val() === "") {
						alert("행 정보를 입력하세요." + PLEASE_RM_MSG);
						$(this).focus();
						valid = false;
						questionMng.prevent();
						return false;
					}
				});
			}
			if (valid) {
				$("#write").find("input[id$='matrix']").each(function () {
					if ($(this).val() === "") {
						alert("열 정보를 입력하세요." + PLEASE_RM_MSG);
						$(this).focus();
						valid = false;
						questionMng.prevent();
						return false;
					}
				});
			}
			/** file check */
			if (valid) {
				$("#write").find("input[id$='questionAtchfile']").each(function () {
					if ($(this).val() != "" && $(this).val() != undefined) {
						var $questionFileCn = $(this).parent().children("div").find("input[id$='questionFileCn']");
						if (!questionMng.validFileExt($(this).val())) {
							alert("질문 이미지는 이미지 파일만 첨부할 수 있습니다.");
							$(this).next().focus();
							valid = false;
							questionMng.prevent();
							return false;
						}
						if ($questionFileCn.val() == "" || $questionFileCn.val() == undefined) {
							alert(QUESTION_IMG_MSG);
							$questionFileCn.focus();
							valid = false;
							questionMng.prevent();
							return false;
						}
					}
				});
			}
			if (valid) {
				$("#write").find("input[id$='questionAtchfile']").each(function () {
					var $oldAtchFileId = $(this).parent().find("input[id$='oldAtchFileId']");
					var $questionFileCn = $(this).parent().find("input[id$='questionFileCn']");
					if (($oldAtchFileId.val() != "" && $oldAtchFileId.val() != undefined) && ($questionFileCn.val() == "" || $questionFileCn.val() == undefined)) {
						alert(QUESTION_IMG_MSG);
						$questionFileCn.focus();
						valid = false;
						questionMng.prevent();
						return false;
					}
				});
			}
			/** answer file check */
			if (valid) {
				$("#write").find("input[id$='answerAtchfile']").each(function () {
					if ($(this).val() != "" && $(this).val() != undefined) {
						var $answerFileCn = $(this).parent().children("div").find("input[id$='answerFileCn']");
						if ($answerFileCn.val() == "" || $answerFileCn.val() == undefined) {
							alert(ANSWER_IMG_MSG);
							$answerFileCn.focus();
							valid = false;
							questionMng.prevent();
							return false;
						}
					}
				});	
			}
			if (valid) {
				$("#write").find("input[id$='answerAtchfile']").each(function () {
					var $oldAtchFileId = $(this).parent().find("input[id$='oldAtchFileId']");
					var $answerFileCn = $(this).parent().find("input[id$='answerFileCn']");
					if (($oldAtchFileId.val() != "" && $oldAtchFileId.val() != undefined) && ($answerFileCn.val() == "" || $answerFileCn.val() == undefined)) {
						alert(ANSWER_IMG_MSG);
						console.log($answerFileCn);
						$answerFileCn.focus();
						valid = false;
						questionMng.prevent();
						return false;
					}
				});
			}
		} catch(e) {
			console.log(e);
			valid = false;
			questionMng.prevent();
			return false;
		}
		return valid;
	},
	validFileExt: function(name) {
		if (name != "" && name != undefined) {
			var ext = name.slice(name.lastIndexOf(".") + 1).toLowerCase();
			return (ext == "png" || ext == "jpg" || ext == "jpeg" || ext == "gif" || ext == "bmp");
		} else {
			return false;
		}
	},
	prevent: function() {
		(event.preventDefault ? event.preventDefault() : (event.returnValue = false));
	},
	ajax: function (url, data, success) {
		$.ajax({
			url: yh.contextPath + url,
			dataType: "json",
			data: data,
			success: success,
			error: function (e) {
				alert("서버와의 통신 중 오류가 발생하였습니다.");
				console.log(e);
				return false;
			}
		});
	},
	question: {
		setImg: function (obj) {
			var $file = $(obj).parent().children("input[type=file]");
			$file.trigger("click");
		},
		delImg: function (obj) {
			$(obj).prev().prev().val("");
			$(obj).parent().children("span").text("");
			$(obj).next().children("input").val("");
			if (questionMng.mode == "update") {
				$(obj).parent().children("input[id*='oldAtchFileId']").val("");
				$(obj).parent().children("input[id*='atchFileDel']").val("Y");
			}
			$(obj).parent().children("div").hide();
			$(obj).remove();
		},
		add: function (obj) {
			var $questionDiv = $(obj).parent().parent();
			var selectVal = $questionDiv.children("table").eq(0).find("tbody > tr > td > select").val();
			var $template = $("#template" + selectVal).clone();
			/** 값 변경 **/
			var thisIdx = $("div[id*='questionDiv']").length;
			$template.removeAttr("style").attr("id", "questionDiv" + thisIdx).attr("data-idx", thisIdx);
			$template.find("table").children("tbody").children("tr").find("input[type='radio'][id*='ableYn1']").prop("checked", true);
			$questionDiv.after($template);
			questionMng.question.renumbering();
			questionMng.init();
		},
		del: function (obj) {
			if ($("#write").children("div").length > 2) {
				if ($(this).attr("class") != "btnboxC") {
					var $questionDiv = $(obj).parent().parent();
					var selectVal = $questionDiv.children("table").eq(0).find("tbody > tr > td > select").val();
					var $template = $("#template" + selectVal).clone();
					var $form = $(obj).parent().parent().parent();
					var questionDivLen = $form.children("div").length - 1;
					$questionDiv.remove();
					questionMng.question.renumbering();
					questionMng.init();
				}
			} else {
				alert("질문은 1개 이상 있어야합니다.");
				return false;
			}
		},
		renumbering: function () {
			$("#write").children("div").each(function (idx) {
				if ($(this).attr("class") != "btnboxC") {
					$(this).children("h3").text("질문" + (idx + 1));
					var currentIdx = idx;
					$(this).attr("id", "questionDiv" + currentIdx);
					$(this).attr("data-idx", currentIdx);
					$(this).find("[id*='questionList']").each(function () {
						var tmp = $(this).attr("id").replace("questionList", "");
						tmp = tmp.substring(0, tmp.indexOf(".")); //current idx
						$(this).attr("id", $(this).attr("id").replace("questionList" + tmp, "questionList" + currentIdx));
						$(this).attr("name", $(this).attr("name").replace("questionList[" + tmp + "]", "questionList[" + currentIdx + "]"));
						$(this).attr("data-idx", currentIdx);
					});
					$(this).find("label[for*='questionList']").each(function () {
						var tmp = $(this).attr("for").replace("questionList", "");
						tmp = tmp.substring(0, tmp.indexOf(".")); //current idx
						$(this).attr("for", $(this).attr("for").replace("questionList" + tmp, "questionList" + currentIdx));
					});
				}
			});
		}
	},
	answer: {
		etc: false,
		addImg: function (obj) {
			var $file = $(obj).parent().children("input[type=file]");
			$file.trigger("click").promise().done(questionMng.answer.fileChange);
		},
		delImg: function (obj) {
			$(obj).parent().parent().hide();
			$(obj).parent().children("span").text("");
			$(obj).parent().parent().children("input[type=text]").val("");
			$(obj).parent().parent().parent().children("input[type=file]").val("");
			$(obj).parent().parent().parent().children("input[id*='oldAtchFileId']").val("");
			$(obj).parent().parent().parent().children("input[id*='atchFileDel']").val("Y");
		},
		add: function (obj) {
			var $parent = $(obj).parent().parent();
			var $clone = $parent.clone();
			$clone.find("td > input").each(function (idx) { $(this).val(""); });
			$clone.children("td").find("span").eq(0).text("");
			$clone.children("td").find("div").hide();
			$clone.children("td").find("div > input").val("");
			$parent.after($clone);
			/** del setting **/
			if ($clone.find("a[class=btn_list_delete]").length == 0) {
				$clone.find("a[class=btn_list_plus]").after($("<a>", { href: "#", class: "btn_list_delete", onclick: "questionMng.answer.del(this); return false;", text: "보기 삭제" }));
			}
			/** etc setting **/
			var $plus = $parent.parent().find("a[class=btn_etc_plus]");
			var plusLng = $parent.parent().find("a[class=btn_etc_plus]").length;
			for (var i = 0; i < plusLng; i++) {
				if ($parent.parent().find("a[class=btn_etc_plus]").length > 1) {
					$plus.eq(i).remove();
				}
			}
			/** setting **/
			questionMng.answer.renumbering($parent.parent());
		},
		addEtc: function (obj) {
			var $parent = $(obj).parent().parent();
			var $tbody = $(obj).parent().parent().parent();
			var $clone = $(obj).parent().parent().clone();
			/** 기타 항목은 첨부파일이 없음 **/
			$clone.children("th").text("보기" + ($tbody.children("tr").length + 1));
			$clone.children("td").find("a[class=btn_list_img_plus]").remove();
			$clone.children("td").find("a[class=btn_list_plus]").remove();
			if ($clone.children("td").find("a[class=btn_list_delete]").length != 0) {
				$clone.children("td").find("a[class=btn_list_delete]").attr("onclick", "questionMng.answer.delEtc(this); return false;");
			} else {
				$clone.children("td").append($("<a>", { href: "#", class: "btn_list_delete", onclick: "questionMng.answer.del(this); return false;", text: "보기 삭제" }));
			}
			$clone.children("td").children("div").remove();
			$clone.children("td").find("input").each(function (idx) {
				var length = $tbody.children("tr").length;
				(idx == 0 ? $(this).val(length) : "");
				$(this).attr("id", $(this).attr("id").replace("answerList" + $(this).attr("data-idx"), "answerList" + length));
				$(this).attr("name", $(this).attr("name").replace("answerList[" + $(this).attr("data-idx") + "]", "answerList[" + length + "]"));
				$(this).attr("data-idx", length);
				if ($(this).attr("type") == "text") {
					$(this).val("기타");
					$(this).attr("readonly", "readonly");
				}
			});
			$tbody.append($clone);
			$tbody.children("tr").each(function (idx) {
				if ($(this).children("td").find("a[class=btn_etc_plus]").length != 0) {
					$(this).children("td").find("a[class=btn_etc_plus]").remove();
				}
			});
			questionMng.answer.renumbering($parent.parent());
		},
		del: function (obj) {
			var $tbody = $(obj).parent().parent().parent();
			var $tr = $(obj).parent().parent();
			$tr.remove();
			var isEtc = false;
			$tbody.children("tr").each(function (idx) {
				$(this).children("th").html("<span class=\"red\">*</span> 보기" + (idx + 1));
				$(this).find("input").each(function (idx2) {
					(idx2 == 0 ? $(this).val(idx) : "");
					$(this).attr("id", $(this).attr("id").replace("answerList" + $(this).attr("data-idx"), "answerList" + idx));
					$(this).attr("name", $(this).attr("name").replace("answerList[" + $(this).attr("data-idx") + "]", "answerList[" + idx + "]"));
					$(this).attr("data-idx", idx);
				});
				($(this).find("a[class=btn_etc_plus]").length != 0 ? isEtc = true : isEtc = false);
			});
			/** btn_etc_plus append **/
			if ($tbody.children("tr").last().find("a[class=btn_etc_plus]").length == 0 && !isEtc) {
				$tbody.children("tr").last().children("td").find("a").appendEtcBtn();
			}
		},
		delEtc: function (obj) {
			var $tbody = $(obj).parent().parent().parent();
			$(obj).parent().parent().remove();
			$tbody.children("tr").last().children("td").find("a").appendEtcBtn();
		},
		fileChange: function () {
			/** answerImg **/
			$("input[id*='answerAtchfile']").change(function () {
				//console.log("파일 변경");
				var $span = $(this).parent().children("div").find("span").eq(0);
				if (!$(this).val().toLowerCase().match(/\.(gif|jpg|jpeg|png)$/i)) {
					alert("이미지 파일만 첨부할 수 있습니다.");
					$(this).val("");
					$span.text("");
					$(this).parent().children("div").hide();
					return false;
				} else {
					$span.text($(this).val().substring($(this).val().lastIndexOf("\\") + 1, $(this).val().length));
					$(this).parent().children("div").show();
				}
			});
		},
		renumbering: function ($tbody) {
			$tbody.children("tr").each(function (idx) {
				$(this).children("th").html("<span class=\"red\">*</span> 보기" + (idx + 1));
				var $td = $(this).children("td");
				$td.find("input").each(function () {
					if ($(this).attr("id").indexOf("answerOrder") > -1) {
						$(this).val(idx);
					}
					var tmp = $(this).attr("id").replace("questionList", "");
					tmp = tmp.substring(tmp.indexOf(".") + 1, tmp.length);
					tmp = tmp.replace("answerList", "");
					tmp = tmp.substring(0, tmp.indexOf(".")); //current idx
					$(this).attr("id", $(this).attr("id").replace("answerList" + tmp, "answerList" + idx));
					$(this).attr("name", $(this).attr("name").replace("answerList[" + tmp + "]", "answerList[" + idx + "]"));
					$(this).attr("data-idx", idx);
				});
			});
		}
	},
	answerAble: {
		add: function (obj) {
			var $div = $(obj).parent().parent();
			var $clone = $(obj).parent().clone();
			var pLength = $(obj).parent().parent().children("p").length;

			/** 추가되는 문항 건너뛰기 입력 폼 세팅 */
			$clone.find("input").val("");
			$clone.find("input").eq(0).attr("type", "hidden");
			$clone.find("input").eq(0).val($(obj).parent().parent().children("p").first().find("input").first().val());
			$clone.find("input").eq(1).attr("style", "margin-left:146px; margin-top:10px;");
			$clone.html($clone.html().replace(" 번 질문에 보기&nbsp;", ""));

			if ($clone.children("a").length == 1) {
				$clone.children("a").after($("<a>", { href: "#", onclick: "questionMng.answerAble.del(this); return false;", class: "btn_list_delete", text: "보기 제거" }));
			}
			$(obj).parent().parent().append($clone);
			questionMng.answerAble.renumbering($div);
		},
		del: function (obj) {
			var $div = $(obj).parent().parent();
			var $parent = $(obj).parent().parent();
			$(obj).parent().remove();
			questionMng.answerAble.renumbering($div);
		},
		renumbering: function ($div) {
			$div.children("p").each(function (idx) {
				$(this).find("input").each(function () {
					var tmp = $(this).attr("id").replace("questionList", "");
					tmp = tmp.substring(tmp.indexOf(".") + 1, tmp.length).replace("ableList", "").substring(0, tmp.indexOf(".")); //current idx
					$(this).attr("id", $(this).attr("id").replace("ableList" + tmp, "ableList" + idx));
					$(this).attr("name", $(this).attr("name").replace("ableList[" + tmp + "]", "ableList[" + idx + "]"));
					$(this).attr("data-idx", idx);
				});
			});
		}
	},
	matrix: {
		/** 행과 열 공통 함수 **/
		add: function (obj, type) {
			var $parent = $(obj).parent().parent();
			var $tr = $parent.clone();
			$tr.children("td").find("input").val("");
			if ($tr.children("td").find("a[class='btn_list_delete']").length == 0) {
				$tr.children("td").append($("<a>", { href: "#", onclick: "questionMng.matrix." + type + ".del(this); return false;", class: "btn_list_delete", text: "보기 삭제" }));
			}
			$parent.after($tr);
			questionMng.matrix.renumbering($parent.parent(), type);
		},
		del: function (obj, type) {
			var $parent = $(obj).parent().parent().parent();
			$(obj).parent().parent().remove();
			questionMng.matrix.renumbering($parent, type);
		},
		renumbering: function ($parent, type) {
			var title = (type == "row" ? "행" : "열");
			var target = (type == "row" ? "matrixList" : "matrixColList");
			$parent.children("tr").each(function (idx) {
				$(this).children("th").text(title + (idx + 1));
				$(this).children("td").find("input").each(function () {
					var tmp = $(this).attr("id").replace("questionList", "");
					tmp = tmp.substring(tmp.indexOf(".") + 1, tmp.length).replace(target, "").substring(0, tmp.indexOf("."));
					$(this).attr("id", $(this).attr("id").replace(target + tmp, target + idx));
					$(this).attr("name", $(this).attr("name").replace(target + "[" + tmp + "]", target + "[" + idx + "]"));
				});
			});
		},
		/** 행 함수 **/
		row: {
			add: function (obj) {
				questionMng.matrix.add(obj, "row");
			},
			del: function (obj) {
				questionMng.matrix.del(obj, "row");
			}
		},
		/** 열 함수 **/
		col: {
			add: function (obj) {
				questionMng.matrix.add(obj, "col");
			},
			del: function (obj) {
				questionMng.matrix.del(obj, "col");
			}
		}
	}
};

$(document).ready(function () {
	questionMng.init();
});
