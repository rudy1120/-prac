package egovframework.portal.util.paging;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class SysPaginationRenderer extends AbstractPaginationRenderer {

	public SysPaginationRenderer() {
		firstPageLabel = "\t<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"btn_frist\">맨첨으로</a>\n";
		previousPageLabel = "\t<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"btn_10prev\">이전10페이지</a>\n";
		currentPageLabel = "<a class=\"on\"><b>{0}</b></a>";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>";
		nextPageLabel = "\t<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"btn_10next\">다음10페이지</a>\n";
		lastPageLabel = "\t<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"btn_end\">맨끝으로</a>\n";
	}
}
