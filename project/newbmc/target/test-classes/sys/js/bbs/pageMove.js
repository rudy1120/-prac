/**
 * 페이지 이동 관련 JS
 *
 * @author J.Ryeon Lee
 * @since 2017.04.26
 */
var goTo = {
	list: function(formId) {
		var frm = document.getElementById(formId);
		frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/list.do" +
			"?mId=" + yh.mId;
		frm.submit();
	},
	view: function(formId, bIdx, bPublic) {
//		if (bPublic == 'N') {
//			alert("비공개글입니다.");
//		}

		var frm = document.getElementById(formId);
		frm.bIdx.value = bIdx;
		frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/view.do" +
			"?mId=" + yh.mId;
		frm.submit();
	},
	write: function(formId) {
		var frm = document.getElementById(formId);
		frm.ptIdx.value = yh.ptIdx;
		frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/write.do" +
			"?mId=" + yh.mId;
		frm.submit();
	},
	modify: function(formId, bIdx) {
		var frm = document.getElementById(formId);
		frm.bIdx.value = bIdx;
		frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/modifyView.do" +
			"?mId=" + yh.mId;
		frm.submit();
	},
	delet: function(formId, bIdx) {
		if (confirm("해당 게시물을 삭제하시겠습니까?")) {
			var frm = document.getElementById(formId);
			frm.bIdx.value = bIdx;
			frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/deleteProc.do" +
				"?mId=" + yh.mId;
			frm.submit();
		}
	},
	restore: function(formId, bIdx) {
		if (confirm("해당 게시물을 복원하시겠습니까?")) {
			var frm = document.getElementById(formId);
			frm.bIdx.value = bIdx;
			frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/reDeleteProc.do" +
				"?mId=" + yh.mId;
			frm.submit();
		}
	},
	reply: function(formId, bIdx) {
		var frm = document.getElementById(formId);
		frm.bIdx.value = bIdx;
		frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/replyWrite.do" +
			"?mId=" + yh.mId;
		frm.submit();
	},
	reply_modify: function(formId, bIdx) {
		var frm = document.getElementById(formId);
		frm.bIdx.value = bIdx;
		frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/replyModifyView.do" +
			"?mId=" + yh.mId;
		frm.submit();
	},
	move: function(formId, bIdx, bNotice) {
		if (bNotice == "Y") {
			alert("공지글은 이동할 수 없습니다.");
			return false;
		} else if (yh.ptCategoryYn == "Y") {
			alert("카테고리를 사용 중인 게시판은 게시물 이동 기능을 지원하지 않습니다.");
			return false;
		}

		if (confirm("해당 게시물을 이동하시겠습니까?")) {
			$.ajax({
				type : "post", dataType: "json", data : { ptIdx : yh.ptIdx, bIdx : bIdx },
				url  : "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/moveCheck.do",
				success : function (result) {
					if (0 < result.replyCount) {
						alert("답글이 작성된 글은 이동할 수 없습니다.");
					} else if (0 < result.isChild) {
						alert("답글은 이동할 수 없습니다.");
					} else {
						var frm = document.getElementById(formId);
						frm.bIdx.value = bIdx;
						frm.action = "/sys/" + yh.siteCodeFull + "/bbs/bbsMng/boardMoveForm.do" +
						"?mId=" + yh.mId;
						frm.submit();
					}
				}
			});
		}
	}
}
