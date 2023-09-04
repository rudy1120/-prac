/**
 * 게시판 등록/수정 관련 JS 분리
 *
 * @author J.Ryeon Lee
 * @since 2017.6.15
 */
// var J = jQuery.noConflict();
$(document).ready(function() {
	jQuery("#ptMngIds").fastselect(); // 게시판담당자 멀티 셀렉트 디자인 적용
	jQuery("#ptMngDeptIds").fastselect(); // 게시판담당부서 멀티 셀렉트 디자인 적용

	// accordion 적용 및 부가 섹션 숨김 처리
	$(".accordion").click(function() { $(this).next().slideToggle("slow"); });
	$(".accordion").not(":first, :eq(1)").trigger("click");

	/* ##### 사이트 코드 리스너 ##### */
	$("#ptSiteCode").click(function() {
		if ($("#ptSiteCode option:selected").val() == "common") {
			$("[name=ptCategoryYn][value=Y]").trigger("click");
		}

		$("#siteName").val($.trim($("#ptSiteCode option:selected").text()));
	});
	$("#ptSiteCode").change(function() { $(this).trigger("click"); });

	/* ##### 게시판 타입 리스너 ##### */
	$("#ptType").click(function() {
		$(".accordion_view:eq(0) tr").not("#category_container").show();
		$("#publicYnTip_container").hide();
		$("#termYnTip_container").hide();
		$("#ntermYnTip_container").hide();

		var selected = $("#ptType :selected").val();
		switch(selected) {
			case "BRD001": // 일반 게시판
				config.minimize_privacy_section();
				break;
			case "BRD002": // 포토 게시판
				$("#replyYn_container").hide();
				$("#publicYn_container").hide();
				$("#reportYn_container").hide();
				$("#rssYn_container").hide();
				config.minimize_privacy_section();
				break;
			case "BRD003": // 동영상 게시판
				$("#replyAuth_container").hide();
				$("#replyYn_container").hide();
				$("#publicYn_container").hide();
				$("#reportYn_container").hide();
				$("#rssYn_container").hide();
				$("[name=ptPublicYn][value=N]").trigger("click");
				$("[name=ptLevelInput][value=YN]").trigger("click");
				break;
			case "BRD004": // 블로그 게시판
				$("#replyYn_container").hide();
				$("#publicYn_container").hide();
				$("#reportYn_container").hide();
				$("#rssYn_container").hide();
				config.minimize_privacy_section();
				break;
			case "BRD005": // 민원형 게시판
				$("#replyYn_container").hide();
				$("#publicYn_container").hide();
				$("#publicYnTip_container").show();
				$("#termYn_container").hide();
				$("#ntermYn_container").hide();
				$("#termYnTip_container").show();
				$("#ntermYnTip_container").show();
				$("[name=ptTermYn][value=N]").trigger("click");
				$("[name=ptNtermYn][value=N]").trigger("click");
				$("[name=ptPublicYn][value=N]").trigger("click");
				$("#rssYn_container").hide();
				$("[name=ptSaveColList]").closest("tr").show();
				$("[name=ptSaveOptColList]").closest("tr").show();
				if (yh.isNew) { $("[name=ptSaveOptColList]").not(":checked").trigger("click"); } // 등록시 선택 항목 기본값 처리
				$("[name=ptSaveAutoColList]").closest("tr").show();
				break;
			case "BRD006": // FAQ
				$("#fileCnt_container").hide();
				$("#replyAuth_container").hide();
				$("#replyYn_container").hide();
				$("#publicYn_container").hide();
				$("#reportYn_container").hide();
				$("#commentYn_container").hide();
				$("#rssYn_container").hide();
				$("#viewOpt_container").hide();
				$("[name=ptPublicYn][value=N]").trigger("click");
				$("[name=ptLevelInput][value=YN]").trigger("click");
				break;
		}
	});
	$("#ptType").change(function() { $(this).trigger("click"); });

	/* ##### 글쓰기 권한 리스너 ##### */
	$("[name=ptLevelInput]").click(function() {
		if ($(this).val() == "YY") {
			$("#privacy_setting_container").prev().show();
			$("#termYn_container").hide();
			$("#termYnTip_container").show();
			$("#ntermYn_container").hide();
			$("#ntermYnTip_container").show();
			$("[name=ptTermYn][value=N]").trigger("click");
			$("[name=ptNtermYn][value=N]").trigger("click");
		} else {
			$("#privacy_setting_container").prev().hide();
			$("#privacy_setting_container").hide();
			$("#termYn_container").show();
			$("#termYnTip_container").hide();
			$("#ntermYn_container").show();
			$("#ntermYnTip_container").hide();
		}
	});

	/* ##### 카테고리 사용 여부 리스너 ##### */
	$("[name=ptCategoryYn]").click(function() {
		$("#category_container").hide();
		if ($("[name=ptCategoryYn]:checked").val() == "Y") {
			$("#category_container").show();
			var gubun = $("[name=ptCategoryGubun]:checked").val();
			$("[name=ptCategoryGubun][value=" + gubun + "]").trigger("click");
		}
	});
	$("[name=ptCategoryYn]").change(function() {
		if ($("[name=ptCategoryYn]:checked").val() == "N" && $("#ptSiteCode").val() == "common") { // 카테고리를 사용하지 않는 경우 공통 사이트는 선택 불가
			$("#ptSiteCode option:first").prop("selected", true);
			$("[name=ptCategoryYn]").parent().find("span").hide();
			for (var i = 0; i < 5; i++) {
				$("[name=ptCategoryYn]").parent().find("span").fadeIn("slow");
				$("[name=ptCategoryYn]").parent().find("span").fadeOut("slow");
			}
			$("[name=ptCategoryYn]").parent().find("span").hide();
		}
	});

	/* ##### 카테고리 구분 리스너 ##### */
	$("[name=ptCategoryGubun]").click(function() {
		var gubun = $("[name=ptCategoryGubun]:checked").val();
		if (gubun == "B") {
			$("#category_container div").hide();
		} else { // 커스텀 카테고리를 지원하는 경우
			$("#category_container div").show();
		}
	});

	/* ##### 개인정보 목적 리스너 ##### */
	$("#ptSavePurpose").on("input change keyup", function() {
		if ($(this).val()) { $("#li_ptSavePurpose").text("개인 정보 수집·이용 목적 : " + $(this).val()); }
		else { $("#li_ptSavePurpose").text("개인 정보 수집·이용 목적 : 민원 접수·처리·사후 관리 서비스 제공"); }
	});

	/* ##### 개인정보 필수/선택 항목 리스너 ##### */
	$("[name=ptSaveColList], [name=ptSaveOptColList]").click(function() {
		var $this = $(this);
		var pairName = $this.attr("name") == "ptSaveColList" ? "ptSaveOptColList" : "ptSaveColList";
		$("[name=" + pairName + "]:checked").each(function() {
			if ($this.val() == $(this).val()) {
				$(this).prop("checked", false);
			}
		});

		$("#li_ptSaveCols").text("필수 항목 : " + $("[name=ptSaveColList]:checked").map(function() { return $(this).next().text(); }).get().join(",")); // 사용자 선택 사항을 반영
		$("#li_ptSaveOptCols").text("부가 항목 : " + $("[name=ptSaveOptColList]:checked").map(function() { return $(this).next().text(); }).get().join(","));
		if ($("[name=ptSaveOptColList]:checked").length > 0) { $("#li_ptSaveOptCols").show(); } else { $("#li_ptSaveOptCols").hide(); };
	});

	/* ##### 보유 기간 리스너 ##### */
	$("#ptSavePeriod").click(function() {
		$("#li_ptSavePeriod").text("개인 정보의 보유 및 이용 기간 : " + $("#ptSavePeriod option:selected").text());
	});
	$("#ptSavePeriod").change(function() { $(this).trigger("click"); });

	/* ##### 개인정보 가이드 리스너 ##### */
	$("#ptSaveGuide").on("input change keyup", function() {
		$("#guide_conts_container > li").not(":lt(3), :last").remove();
		var lines = $(this).val().split("\n");
		for (var i in lines) {
			$("<li>" + lines[i] + "</li>").insertBefore($("#guide_preview > div > ul > li:last"));
		}
	});

	if (yh.isNew) { // case 등록: 기본값 설정
		$("[name=ptLevelInput][value=YN]").trigger("click");
		$("[name=ptReplyYn][value=N]").trigger("click");
		$("[name=ptPublicYn][value=N]").trigger("click");
		$("[name=ptCommentYn][value=N]").trigger("click");
		$("[name=ptIsolateYn][value=N]").trigger("click");
		$("[name=ptReportYn][value=N]").trigger("click");
		$("[name=ptRssYn][value=N]").trigger("click");
		$("[name=ptBundleDelYn][value=N]").trigger("click");
		$("[name=ptTermYn][value=N]").trigger("click");
		$("[name=ptNtermYn][value=N]").trigger("click");
		$("[name=ptCategoryYn][value=N]").trigger("click");
		$("[name=ptCategoryGubun][value=B]").trigger("click");
		$("[name=ptOutFields][value=NN]").trigger("click");
		$("#ptSavePeriod option:last").prop("selected", true);
		$("#li_ptSaveOptCols").hide();
	} else { // case 수정: 값에 따라 변경되어야 하는 element click event 발생
		$("#ptSiteCode :selected").trigger("click");
		$("#ptType :selected").trigger("click");
		$("#ptType").prop("disabled", true);
		$("[name=ptLevelInput]").prop("disabled", true);
		if ($("[name=ptLevelInput]:checked").val() == "YY") {
			$("#privacy_setting_container").prev().show();
			$("#termYn_container").hide();
			$("#termYnTip_container").show();
			$("#ntermYn_container").hide();
			$("#ntermYnTip_container").show();
		}
		$("[name=ptCategoryYn]:checked").trigger("click");
		$("[name=ptCategoryGubun]:checked").trigger("click");
		$("#ptSavePurpose").keyup();
		$("[name=ptSaveColList]:checked").trigger("click").trigger("click"); // 한 번만 클릭하면 기존 선택된 값이 해제되어 버리므로 두 번 반복. (dbclick은 listner가 없으므로 동작하지 않음)
		$("[name=ptSaveOptColList]:checked").trigger("click").trigger("click");
		$("#ptSavePeriod").trigger("click");
		$("#ptSaveGuide").keyup();
	}
});

var config = {
	additional_validator: function() {
		try {
			var ptType = $("#ptType :selected").val();
			if ((ptType == "BRD002" || ptType == "BRD003") && (!$("#ptFileCnt").val() || $("#ptFileCnt").val() == 0)) {
				alert("파일 첨부 허용 개수를 입력해주세요.");
				$("#ptFileCnt").focus();
				return false;
			} else if (ptType == "BRD005" && $("[name=ptLevelInput]:checked").val() == "YY") { // case 민원형 && 사용자 입력 허가:
				if (($("[name=ptSaveColList]:checked").length + $("[name=ptSaveOptColList]:checked").length) < 4) { // case 미설정된 항목 존재:
					alert("개인 정보 수집 정책을 정확하게 설정해주세요.\n수집 필수·선택 여부가 설정되지 않은 항목이 있습니다.");
					$("[name=ptSaveOptColList]").not(":checked").first().focus();
					return false;
				}
			}

			if ($("[name=ptCategoryYn]:checked").val() == "Y") {
				if ($("[name=ptCategoryGubun]:checked").val() != "B" && !$("#ptCategory").val().trim()) { // 커스텀 카테고리 값이 없는 경우
					alert("커스텀 카테고리 목록을 작성해주세요.");
					$("#ptCategory").focus();
					return false;
				}

				if ($("#ptCategory").val()) {
					var categories = $("#ptCategory").val().replace(/\r\n/g, "\n").split("\n");
					for (var i in categories) {
						if (!$.trim(categories[i])) {
							alert("추가하신 카테고리 중 스페이스 또는 엔터로만 구성된 값이 있습니다. 입력 양식을 확인해주세요.");
							$("#ptCategory").focus();
							return false;
						}
					}
				}
			}

			return true;
		} catch (e) {
			alert("잘못된 입력이 있습니다.");
			return false;
		}
	},
	minimize_privacy_section: function() {
		$("[name=ptSaveColList]").closest("tr").hide();
		$("[name=ptSaveColList]:checked").not(":first").trigger("click");
		$("[name=ptSaveOptColList]").closest("tr").hide();
		$("[name=ptSaveOptColList]:checked").trigger("click");
		$("[name=ptSaveAutoColList]").closest("tr").hide();
	}
};
