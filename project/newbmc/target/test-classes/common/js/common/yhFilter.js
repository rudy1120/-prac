/**
 * yhFilter.js
 * 
 * @version 1.0
 * @author 권태성
 * @since 2018. 01. 15
 * @description 페이지 내 특정 요소 필터링을 위한 JS
 * ---------------------------------------------------------
 * 수정일			수정자				수정내용
  * ---------------------------------------------------------
 * 2018.01.15	  권태성			  최초 생성
 * ---------------------------------------------------------
 * javascript로 페이지 내 요소 필터링을 위해 만든 JS입니다.
 * <<사용 예>>
	$("#txtFilter").yhFilter({
		targetId	:	"user",
		dataName	:	"data-check-name",
	});
 * ---------------------------------------------------------
 * txtFilter에 입력한 검색어를 targetId(ul) 하위 li 내에서
 * dataName이 동일한 데이터를 찾아서 표시합니다.
 * ---------------------------------------------------------
 */
$.fn.yhFilter = function(option) {
	yhFilter.target = this;
	$.extend(yhFilter.options, option);
	yhFilter.create();
};

var yhFilter = {
	target: null,
	options: {
		"targetId": "",		//검색 할 데이터가 있는 li id
		"dataName" : ""		//데이터 이름
	},
	create: function () {
		$(yhFilter.target).keyup(function () {
			if ($(yhFilter.target).val() == "") {
				$("#" + yhFilter.options.targetId + " li").css("display", "");
			} else {
				var checkName = $(yhFilter.target).val();
				$("[" + yhFilter.options.dataName + "]").each(function (index, el) {
					if ($(this).attr(yhFilter.options.dataName).indexOf(checkName) != -1) {
						$(this).css("display", "");
						$(this).parentsUntil().css("display", "");
					} else {
						$(this).css("display", "none");
					}
				});
			}
			return false;
		});
	}
};
