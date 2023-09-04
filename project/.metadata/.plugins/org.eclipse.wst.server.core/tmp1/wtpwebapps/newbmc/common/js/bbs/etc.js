/**
 * 기타 게시판 관련 JS
 *
 * @author J.Ryeon Lee
 * @since 2017.04.26
 */
jQuery(document).ready(function() {
	jQuery("#bDeptGroup").change(function() {
		jQuery("#bDeptNm").val(jQuery("#bDeptGroup option:selected").text());
	});
});

var bbs = {
	sys_additional_validator: function() {
		try {
			if (yh.ptCheckWord) {
				var hit = bbs.forbiddenWordChecker(yh.ptCheckWord, jQuery("#bTitle").val());
				if (hit) {
					alert("[" + hit + "](은)는 규제된 단어입니다.");
					jQuery("#bTitle").focus();
					return false;
				}

				hit = bbs.forbiddenWordChecker(yh.ptCheckWord, jQuery("#bContent").val());
				if (hit) {
					alert("[" + hit + "](은)는 규제된 단어입니다.");
					jQuery("#bContent").focus();
					return false;
				}
			}

			return bbs.file_validator();
		} catch (e) {
			return false;
		}
	},
	additional_validator: function() {
		try {
			return bbs.sys_additional_validator() && chk_privacy_policy();
		} catch (e) {
			return false;
		}
	},
	file_validator: function() {
		try {
			if (yh.isPhoto) {
				if (jQuery("[name=file_cn]").length == 0) {
					alert("이미지 파일을 첨부해주세요.");
					return false;
				}
			} else if (yh.isMovie && $('input[data-inputType="file"]').attr('data-validator') == 'on') {
				if (jQuery("#updateFileList li").length == 0 && (jQuery("#egovComFileList li").length == 0 || jQuery("#egovComFileList li").text().indexOf("선택된 파일이 없습니다") > -1)) {
					alert("영상 파일을 등록하세요.");
					return false;
				}
			}

			return bbs.file_size_validator();
		} catch (e) {
			return false;
		}
	},
	file_size_validator: function () {
		var validated = true;
		var isIE = navigator.appName == 'Microsoft Internet Explorer';
		var limitFileSizeKB = yh.limitFileSize * 1024 * 1024;
		$('[type=file][name*=file_]').each(function (i, e) {
			var fileSize = isIE ? new ActiveXObject('Scripting.FileSystemObject').getFile(e.value).size : (e.files.length > 0 ? e.files[0].size : 0);
			if (fileSize > limitFileSizeKB) {
				alert(yh.limitFileSize + 'mb를 초과하는 파일은 첨부하실 수 없습니다.');
				validated = false;
				return false;
			}
		});

		return validated;
	},
	sys_additional_warning: function() {
		try {
			//webfilter 적용
			if(wf_flag == "off"){
				if (confirm("등록하기 전 첨부파일 내 개인정보가 존재하는지 확인바랍니다.\r\n해당 첨부파일에 개인정보가 존재할경우 개인정보가 노출되므로\r\n글을 작성하는 담당자분께서는 한번더 점검바랍니다.\r\n등록하시겠습니까?")) {
					submitWebFilter('detailForm');
					return false;
				
				}else{
					return false;
				}
			}else{
				return true;
			}
			
			
		/*	if (confirm("등록하기 전 첨부파일 내 개인정보가 존재하는지 확인바랍니다.\r\n해당 첨부파일에 개인정보가 존재할경우 개인정보가 노출되므로\r\n글을 작성하는 담당자분께서는 한번더 점검바랍니다.\r\n등록하시겠습니까?")) {
				return true;
			}
			return false;*/
			
		} catch (e) {
			return false;
		}
	},
	/**
	 * 규제 단어 검출 함수
	*
	* @param forbidden_word_list [;]로 연결된 규제 단어 string
	* @param target 검증 대상값
	* @author J.Ryeon Lee
	* @returns 검증 대상값에 존재하는 규제 단어
	*/
	forbiddenWordChecker: function (forbidden_word_list, target) {
		if (target && $.trim(forbidden_word_list)) {
			var forbidden_word_array = forbidden_word_list.split(";"); // 규제 단어 배열
			var msg = ""; // 입력값에 존재하는 규제 단어
			for (var i in forbidden_word_array) {
				if (forbidden_word_array[i] && target.toLowerCase().indexOf(forbidden_word_array[i].toLowerCase()) > -1) {
					msg += msg.length > 0 ? ", " + forbidden_word_array[i] : forbidden_word_array[i]; // 규제 단어 추가
				}
			}

			return msg;
		}
	}
}
