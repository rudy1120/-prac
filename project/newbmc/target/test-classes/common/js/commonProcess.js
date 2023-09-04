/**
 * 공통 프로세스 JS
 *
 * ##### req.post usage #####
 * data-action: (필수) POST URL
 * data-formname: (선택) POST 대상 form name. 기본값 list
 * data-keyset: (선택) POST시 추가적으로 세팅해야하는 값의 키-값 JSON. data-formname에 해당하는 form에 key를 name으로 하는 input이 없는 경우 태그를 생성해 세팅함.
 *
 * ##### req.ajax #####
 * 주) ajaxReq의 요청을 처리하는 Controller는 response에 Boolean값을 가지는 success 변수를 반드시 포함해야 함
 * data-action: (필수) AJAX 요청 URL
 * data-keyset: (필수) AJAX 요청시 추가적으로 세팅해야하는 값의 키-값 JSON
 * data-confirmmsg: (선택) confirm 함수용 메세지
 * data-successmsg: (선택) ajax 요청 후 result.success시 출력할 메세지
 * data-failedmsg: (선택) ajax 요청 후 처리 실패시 출력할 메세지
 * data-formserialize: (선택) y 설정시 data-keyset 무시, form 데이터를 전체 시리얼라이징 후 ajax request 처리. ajax 요청시 사용할 formid은 data-formid 참고
 * data-formid: (선택) ajax 요청시 이용할 form id. 기본값 write
 * data-stay: (선택) ajax 처리/성공 후 페이지 머무름 플래그. 기본값 N
 *
 * 2017.11.30 권태성
 * IE 스크립트 오류 대응
 *
 * @author J.Ryeon Lee
 * @since 2017.6.15
 */
 var req = {
	open: function(el) {
		window.open(
			jQuery(el).data("action"),
			(jQuery(el).data("winnm") ? jQuery(el).data("winnm") : "새창 열림"),
			"width=" + (jQuery(el).data("width") ? jQuery(el).data("width") : "500")  +
			", height=" + (jQuery(el).data("height") ? jQuery(el).data("height") : "450")  +
			", scrollbars=yes, resizable=yes"
		);
	},
	post: function(el) { // 페이지 포스트 이동 함수
		var keyset = jQuery(el).data("keyset"); // parameter map
		keyset = keyset ? jQuery.parseJSON(keyset.replace(/'/g, "\"")) : {};

		var actionUrl = jQuery(el).data("action"); // post url
		if (actionUrl.indexOf("mId") < 0) {
			actionUrl = actionUrl.indexOf("?") > -1 ? actionUrl + "&mId=" + yh.mId : actionUrl + "?mId=" + yh.mId;
		}

		var frmName = jQuery(el).data("formname"); // form name
		frmName = frmName ? frmName : "list";

		var frm = document.getElementById(frmName);
		for (var i in keyset) {
			if (frm[i]) {
				frm[i].value = keyset[i];
			} else { // 신규 노드 생성
				jQuery("<input type='hidden' name='" + i + "' value='" + keyset[i] + "'/>").insertBefore(jQuery(el));
			}
		}
		frm.action = actionUrl;
		frm.submit();
	},
	ajax: function(el) { // 요청
		var $el = jQuery(el);
		var stay = $el.data("stay") && $el.data("stay") == "y" ? true : false;
		var formid = $el.data("formid") ? $el.data("formid") : "write";
		var confirmMsg = $el.data("confirmmsg") ? $el.data("confirmmsg") : "처리하시겠습니까?";
		var successMsg = $el.data("successmsg") ? $el.data("successmsg") : "처리되었습니다.";
		var failedMsg = $el.data("failedmsg") ? $el.data("failedmsg") : "처리 중 오류가 발생했습니다.";
		var params = $el.data("formserialize") && $el.data("formserialize") == "y"
			? jQuery("#" + formid).serialize() : jQuery.parseJSON(jQuery(el).data("keyset").replace(/'/g, "\""));

		if (confirm(confirmMsg)) {
			jQuery.ajax({
				data: params, dataType: "json", url: jQuery(el).data("action"), type: "post",
				success: function(result) {
					alert(result.success ? successMsg : failedMsg);
					if (result.success) {
						if (!stay) {
							location.href = jQuery(el).data("success")
								? jQuery(el).data("success")
								: ("/" + yh.siteCode + "/" + (yh.siteCode == "sys" ? "sysContents" : "contents") + ".do?mId=" + yh.mId);
						}
					}
				}
			}).fail(function() { alert("서버와 통신 중 오류가 발생했습니다."); });
		}
	}
 };

 function goPage(page) { // 페이징 함수
	var formId;
	try {
		formId = yh.formId ? yh.formId : "list";
	} catch(e) {
		formId = "list";
	}

	var frm = document.getElementById(formId);
	frm.page.value = page;
	frm.submit();
 }
