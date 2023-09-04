package egovframework.portal.util.paging;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class BasePaginationRenderer extends AbstractPaginationRenderer {

	public BasePaginationRenderer() {
		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"btn_first\">처음 페이지</a>";
		previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"btn_10prev\" title=\"이전 10페이지\">이전 10페이지</a>";
		currentPageLabel = "<span>{0}</span>";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" title=\"{2}페이지로 이동\">{2}</a>";
		nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false; \" class=\"btn_10next\" title=\"다음 10페이지\">다음 10페이지</a>";
		lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false; \" class=\"btn_end\" title=\"끝 페이지\">끝 페이지</a>&#160;";
	}
}
