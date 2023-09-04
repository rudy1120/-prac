/**
 * pollMng.js
 * 
 * @version 1.0
 * @author taeseong
 * @since 2017-07-20
 * @description pollMng js
 * ---------------------------------------------------------
 * 수정일			수정자				수정내용
  * --------------------------------------------------------
 * 2017.07.20	  권태성			  최초 생성
 * ---------------------------------------------------------
 */
/** require setting */
requirejs.config({
	paths: { "commonProcess": yh.contextPath + "/common/js/commonProcess" },
	shim: { "commonProcess": { exports: "commonProcess" } }
});

requirejs(["commonProcess"], function (commonProcess) { });

var resultMng = {
	showComment: function (targetIdx, questionType) {
		window.open("/sys/pollMng/result/comment.do?target=" + targetIdx + "&type=" + questionType,"showComment", "width=544, height=700, scrollbars=yes");
	}
};