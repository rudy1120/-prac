/**
 * 외부 관리자 등록/수정/열람 관련
 *
 * @author J.Ryeon Lee
 * @since 2017-11-07
 */

/** 게시판 권한 설정 모달창 제어 */
var modal = {
	checked: new Set(),
	params: {
		ajax: true,
		perPage: 10,
		pageCut: 8,
		page: 1
	},
	init: function () {
		$("#bbs_container tbody tr").remove();
		($("#ptIdxs").val().trim() ? modal.checked = new Set($("#ptIdxs").val().trim().split(",")) : ""); // 저장된 값 복원
		modal.paging(1);
	},
	open: function () {
		modal.init();
		$(".modal").fadeIn();
	},
	save: function() {
		$("#ptIdxs").val(Array.from(modal.checked).toString());
		(modal.checked.size > 0
			? $("#bbs_guide").text(modal.checked.size + "개의 게시판이 선택되어 있습니다.")
			: $("#bbs_guide").text("선택된 게시판이 없습니다.")
		);
		modal.checked.clear();
		$("#title").val("");
		$(".modal").fadeOut();
	},
	close: function () {
		$("#title").val("");
		modal.checked.clear();
		$(".modal").fadeOut();
	},
	paging: function (page) {
		try {
			page = page < 1 ? 1 : page;
			modal.params.page = page;
			modal.params.title = $("#title").val();
			$.ajax({
				data: modal.params, dataType: "json", url: "/sys/bbs/list/json.do?mId=" + yh.mId, type: "post",
				success: function (result) {
					if (result.success) {
						$("#bbs_container tbody tr").hide();
						$("#bbs_paging a").remove();
						if (result.list && result.list.length > 0) {
							var e = null;
							var tr = null;
							var trs = "";

							// data
							for (var i = 0; i < result.list.length; i++) {
								e = result.list[i];
								tr =
									"<tr data-page=\"" + page + "\">" +
										"<td>" +
											"<input type=\"checkbox\" id=\"ptIdxList" + e.ptIdx + "\" "+
												"name=\"ptIdxList\" value=\"" + e.ptIdx + "\"" +
												(modal.checked.has(e.ptIdx) ? " checked=\"checked\" " : "") +
											"/>" +
										"</td>" +
										"<td class=\"taL\">" +
											"<label for=\"ptIdxList" + e.ptIdx + "\">" +
												"<span class=\"blue\">[" + (e.siteName ? e.siteName : "-") + "]</span> " + e.ptTitle +
											"</label>" +
										"</td>" +
									"</tr>";
								trs += tr;
							}
							$("#bbs_container tbody").prepend(trs);


							// paging
							$("#bbs_paging a").remove()
							$("#bbs_paging").append("<a href=\"#\" onclick=\"modal.paging(1); return false;\" class=\"btn_white\">first</a>");
							$("#bbs_paging").append("<a href=\"#\" onclick=\"modal.paging(" + (page - 1) + "); return false;\" class=\"btn_white\">prev</a>");
							var min = result.pagingInfo.firstPageNoOnPageList;
							var max = result.pagingInfo.lastPageNoOnPageList;
							for (var i = min; i <= max; i++) {
								$("#bbs_paging").append("<a href=\"#\" onclick=\"modal.paging(" + i + "); return false;\" class=\"" + (i == page ? "btn_cyan" : "off") + "\">" + i + "</a>");
							}
							var next = result.pagingInfo.totalPageCount < page + 1 ? result.pagingInfo.totalPageCount : page + 1;
							$("#bbs_paging").append("<a href=\"#\" onclick=\"modal.paging(" + next + "); return false;\" class=\"btn_white\">next</a>");
							$("#bbs_paging").append("<a href=\"#\" onclick=\"modal.paging(" + result.pagingInfo.totalPageCount + "); return false;\" class=\"btn_white\">last</a>");

							// checkbox listner
							$("[name=ptIdxList]").click(function() {
								if ($(this).is(":checked")) {
									modal.checked.add($(this).val());
								} else {
									modal.checked.delete($(this).val());
								}
							});
						} else {
							$("#bbs_container tbody").append("<tr><td colspan=\"2\">검색 결과가 없습니다.</td></tr>");
						}
					}
				}
			}).fail(function () { alert("서버와 통신 중 오류가 발생했습니다."); });
		} catch (e) {
			alert("서버와 통신 중 오류가 발생했습니다. 모든 처리를 초기화합니다.");
			modal.init();
		}
	}
}
