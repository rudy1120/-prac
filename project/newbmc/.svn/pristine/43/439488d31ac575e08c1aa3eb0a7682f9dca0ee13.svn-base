/**
 * yhPaging.js
 *
 * @version 1.0
 * @author 권태성
 * @since 2017. 09. 22
 * @description ajax 페이징 출력용 JS
 * ---------------------------------------------------------
 * 수정일		수정자			수정내용
  * ---------------------------------------------------------
 * 2017.09.22	권태성			최초 생성
 * 2017.12.08	권태성			링크 타이틀 수정
 * 2018.01.15	권태성			yhPaging으로 변경
 * ---------------------------------------------------------
 * javascript에서 페이징 생성을 위해 만든 JS입니다.
 * <<사용 예>>
	$(".box_page").yhPaging({
		totalRecordCount	:	totalCnt,
		currentPageNo		:	page,
		pageSize			:	10,
		moveFunc			:	"move"
	});
 * ---------------------------------------------------------
 * 페이지 이동을 위해서 사용해야하는 함수를 moveFunc에 파라미터로
 * 함수 이름을 담아주면 페이지 이동시에 사용합니다.
 * 기본적으로 moveFunc에서 사용하는 함수는 move(page) 처럼 page
 * 파라미터만 사용합니다. 필요한 경우 이 JS를 수정하거나 page 파라미터만
 * 받아서 다른 파라미터를 추가해서 실행하는 함수를 만들어서 사용하시면됩니다.
 * ---------------------------------------------------------
 */
$.fn.yhPaging = function(option) {
	paging.target = this;
	$.extend(paging.options, option);
	paging.create();
};

var paging = {};
paging.options = {
	"currentPageNo": 1,
	"recordCountPerPage": 10,		// 한 페이지당 보여질 '데이터' 수
	"pageSize": 10,					// 한 페이지당 보여질 '페이지' 수
	"totalRecordCount": 0,			// 전체 데이터 수
	"totalPageCount": 0,			// 페이지 개수
	"firstPageNoOnPageList": 0,		// 페이지 리스트의 첫 페이지 번호
	"lastPageNoOnPageList": 0,		// 페이지 리스트의 마지막 페이지 번호
	"firstRecordIndex": 0,
	"lastRecordIndex": 0,
	"firstPageNo": 1,
	"listOrder": 0,
	"moveFunc": ""					// 페이지 이동 시 사용하는 함수
};

paging.calc = function() {
	paging.options.totalPageCount = Math.floor(((paging.options.totalRecordCount - 1) / paging.options.recordCountPerPage) + 1);
	if (paging.options.currentPageNo <= 10) {
		paging.options.firstPageNoOnPageList = 1;
	} else {
		paging.options.firstPageNoOnPageList = (paging.options.currentPageNo % paging.options.pageSize == 0 ? (paging.options.currentPageNo - (paging.options.pageSize - 1)) : (((Math.floor(paging.options.currentPageNo / paging.options.pageSize)) * 10) + 1));
	}
	paging.options.lastPageNoOnPageList = paging.options.firstPageNoOnPageList + paging.options.pageSize - 1;
	if (paging.options.lastPageNoOnPageList > paging.options.totalPageCount) {
		paging.options.lastPageNoOnPageList = paging.options.totalPageCount;
	}
	paging.options.firstRecordIndex = (paging.options.currentPageNo - 1) * paging.options.recordCountPerPage;
	paging.options.lastRecordIndex = paging.options.currentPageNo * paging.options.recordCountPerPage;
	paging.options.listOrder = paging.options.totalRecordCount - ((paging.options.currentPageNo - 1) * paging.options.recordCountPerPage);
};

paging.create = function() {
	var options = paging.options;
	paging.calc();
	var html = "";
	html += "<a href=\"#\" onclick=\"" + options.moveFunc + "(" + options.firstPageNo + "); return false;\" class=\"btn_frist\" title=\"맨처음페이지\">맨처음페이지</a>";
	html += "<a href=\"#\" onclick=\"" + options.moveFunc + "(" + (options.firstPageNoOnPageList > options.pageSize ? parseInt(options.firstPageNoOnPageList - 1) : options.firstPageNo) + "); return false;\" class=\"btn_10prev\" title=\"10페이지앞으로\">10페이지앞으로</a>";
	for (var i = options.firstPageNoOnPageList; i <= options.lastPageNoOnPageList; i++) {
		if (options.currentPageNo == i) {
			html += "<span>" + i + "</span>";
		} else {
			html += "<a href=\"#\" onclick=\"" + options.moveFunc + "(" + i + "); return false;\" title=\"" + i + "페이지로 이동\">" + i + "</a>";
		}
	}
	html += "<a href=\"#\" onclick=\"" + options.moveFunc + "(" + (options.lastPageNoOnPageList < options.totalPageCount ? parseInt(options.firstPageNoOnPageList + options.pageSize) : options.totalPageCount) + "); return false;\" class=\"btn_10next\" title=\"다음 10페이지\">10페이지뒤로</a>";
	html += "<a href=\"#\" onclick=\"" + options.moveFunc + "(" + options.totalPageCount + "); return false;\" class=\"btn_end\" title=\"맨끝페이지\">맨끝페이지</a>";
	$(paging.target).html(html);
};
