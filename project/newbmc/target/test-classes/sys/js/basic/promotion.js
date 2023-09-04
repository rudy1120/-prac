/**
 * 홍보 자료 관련 JS
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2017.07.18		J.Ryeong Lee	최초 생성 및 코딩
 *
 *
 * @author J.Ryeong Lee
 * @since 2017.07.18
 */

jQuery(document).ready(function(){
	dp.bind(jQuery("[data-date=y]"));
	//jQuery("#siteIdxs").fastselect(); // 사이트 멀티 셀렉트 디자인 적용

	if (yh.isNew) { // 최초 등록시 기본값 세팅
		jQuery("[name=useYn][value=Y]").trigger("click"); // 홍보 자료 사용
		jQuery("[name=blankYn][value=Y]").trigger("click"); // 새창 열림
		jQuery("[name=prmtTemplate]:first").trigger("click"); // 템플릿 사용 안 함
	}
});

var promotion = {
	addtional_validator: function() {
		try {
			switch (yh.prmtType) {
				case yh.banner: // BANNER
					break;
				case yh.popupzone: // POPUPZONE
					if (jQuery("[name=prmtTemplate]:first").is(":checked") && jQuery("[name=file_cn]").length === 0) { // case 템플릿 미선택: 첨부 파일 필수 처리
						alert("첨부 파일을 첨부하시거나 템플릿을 선택하세요.");
						return false;
					}

					break;
				default: // VISUALZONE AND ETC
					if (jQuery("[name=file_cn]").length === 0) { // 첨부 파일 필수 처리
						alert("첨부 파일을 첨부하세요.");
						return false;
					}

					break;
			}

			if (jQuery("#prmtUrl").val() && jQuery("#prmtUrl").val().substring(0, 7) != "http://") { // http:// 부터 입력하지 않은 경우
				alert("http:// 부터 입력해주세요.");
				jQuery("#prmtUrl").focus();
				return false;
			}

			return true;
		} catch (e) {
			return false;
		}
	}
};
