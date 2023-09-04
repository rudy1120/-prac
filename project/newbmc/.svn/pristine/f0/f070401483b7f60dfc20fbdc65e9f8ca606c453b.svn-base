/**
 * 페이지 이동 관련 JS
 *
 * @author J.Ryeon Lee
 * @since 2017.04.26
 */
var goTo = {
	view: function(formId, bIdx, ptIdx, mId) {
		var frm = document.getElementById(formId);
		frm.action = "/" + yh.siteCodeFull + "/bbs/view.do" +
			"?bIdx=" + bIdx +
			"&ptIdx=" + ptIdx +
			"&mId=" + mId;
		frm.submit();
	},
	secured_view: function(formId, bIdx) {
		var frm = document.getElementById(formId);
		frm.bIdx.value = bIdx;
		frm.action = "/" + yh.siteCodeFull + "/bbs/secured/view.do" +
			"?ptIdx=" + yh.ptIdx +
			"&mId=" + yh.mId;
		frm.submit();
	},
	write: function(formId) {
		var frm = document.getElementById(formId);
		frm.action = "/" + yh.siteCodeFull + "/bbs/write.do" +
			"?ptIdx=" + yh.ptIdx +
			"&mId=" + yh.mId;
		frm.submit();
	},
	modify: function(formId, bIdx) {
		var frm = document.getElementById(formId);
		frm.bIdx.value = bIdx;
		frm.bbsMode.value = "edit";
		frm.action = "/" + yh.siteCodeFull + "/bbs/boardPwd.do" +
			"?ptIdx=" + yh.ptIdx +
			"&mId=" + yh.mId;
		frm.submit();
	},
	delet: function(formId, bIdx) {
		var frm = document.getElementById(formId);
		frm.bIdx.value = bIdx;
		frm.bbsMode.value = "delete";
		frm.action = "/" + yh.siteCodeFull + "/bbs/boardPwd.do" +
			"?ptIdx=" + yh.ptIdx +
			"&mId=" + yh.mId;
		frm.submit();
	},
	reply: function(formId, bIdx) {
		var frm = document.getElementById(formId);
		frm.bIdx.value = bIdx;
		frm.action = "/" + yh.siteCodeFull + "/bbs/replyWrite.do" +
			"?ptIdx=" + yh.ptIdx +
			"&mId=" + yh.mId;
		frm.submit();
	}
}
