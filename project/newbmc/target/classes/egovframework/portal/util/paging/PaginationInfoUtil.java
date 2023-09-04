package egovframework.portal.util.paging;

import static egovframework.portal.common.Constant.ADMIN_LIST_CUTRECORD;
import static egovframework.portal.common.Constant.LIST_CUTPAGE;
import static egovframework.portal.common.Constant.LIST_CUTRECORD;
import static egovframework.portal.common.Constant.RESPONSIVE_LIST_CUTPAGE;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * PAGINATION UTIL
 *
 * @author J.Ryeon Lee
 * @since 2015.10.10
 * @see egovframework.portal.common.Constant
 */
public enum PaginationInfoUtil {

	INSTANCE,; // TODO 단순히 기존 코드를 분리했으므로 쓸모없는 부분은 리팩토링해야 함

	/** 사용자 페이징 정보 리턴 */
	public static PaginationInfo calUserPaginationInfo(int page, int totalCnt) {
		return calPaginationInfo(page, LIST_CUTRECORD, LIST_CUTPAGE, totalCnt);
	}

	/** 사용자 페이징 정보 리턴 (반응형) */
	public static PaginationInfo calResponsiveUserPaginationInfo(int page, int totalCnt) {
		return calPaginationInfo(page, LIST_CUTRECORD, RESPONSIVE_LIST_CUTPAGE, totalCnt);
	}

	/** 관리자 페이징 정보 리턴 */
	public static PaginationInfo calPaginationInfo(int page, int totalCnt) {
		return calPaginationInfo(page, ADMIN_LIST_CUTRECORD, LIST_CUTPAGE, totalCnt);
	}

	/**
	 * 페이징 정보 리턴
	 *
	 * @param page 현재 페이지
	 * @param listCutRecord 페이지당 출력 데이터 건수
	 * @param listCutpage 페이지 출력 건수 (next, prev 버튼 사이 출력 숫자 범위)
	 * @param totalCnt 전체 데이터 건수
	 * @return PaginationInfo
	 */
	public static PaginationInfo calPaginationInfo(int page, int listCutRecord, int listCutpage, int totalCnt) {
		PaginationInfo paginationInfo = new PaginationInfo();
		if (page > 0) {
			paginationInfo.setCurrentPageNo(page);
		} else {
			paginationInfo.setCurrentPageNo(1);
		}
		if (listCutRecord > 0) {
			paginationInfo.setRecordCountPerPage(listCutRecord);
		} else {
			paginationInfo.setRecordCountPerPage(ADMIN_LIST_CUTRECORD);
		}

		if (listCutpage > 0) {
			paginationInfo.setPageSize(listCutpage);
		} else {
			paginationInfo.setPageSize(LIST_CUTPAGE);
		}

		if (totalCnt > 0) {
			paginationInfo.setTotalRecordCount(totalCnt);
		} else {
			paginationInfo.setTotalRecordCount(0);
		}

		return paginationInfo;
	}

	/** 사용자용 firstIndex 계산 */
	public static int calUserFirstIndex(int page) {
		return calFirstIndex(page, LIST_CUTRECORD);
	}

	/** 관리자용 firstIndex 계산 */
	public static int calFirstIndex(int page) {
		return calFirstIndex(page, ADMIN_LIST_CUTRECORD);
	}

	/**
	 * firstIndex 계산
	 *
	 * @param page 현재 페이지
	 * @param listCutRecord 페이지당 출력 데이터 건수
	 * @return int
	 */
	public static int calFirstIndex(int page, int listCutRecord) {
		int cpage = 1;
		if (page > 1) {
			cpage = (page - 1) * listCutRecord + 1;
		}
		return cpage;
	}

	/** 사용자용 LastIndex 계산 */
	public static int calUserLastIndex(int page) {
		return calLastIndex(page, LIST_CUTRECORD);
	}

	/** 관리자용 LastIndex 계산 */
	public static int calLastIndex(int page) {
		return calLastIndex(page, ADMIN_LIST_CUTRECORD);
	}

	/**
	 * LastIndex 계산
	 *
	 * @param page 현재 페이지
	 * @param listCutRecord 페이지당 출력 데이터 건수
	 * @return int
	 */
	public static int calLastIndex(int page, int listCutRecord) {
		return page * listCutRecord;
	}

	/** 사용자용 listOrder 계산 */
	public static int calUserListOrder(int totalCnt, int page) {
		return calListOrder(totalCnt, page, LIST_CUTRECORD);
	}

	/** 관리자용 listOrder 계산 */
	public static int calListOrder(int totalCnt, int page) {
		return calListOrder(totalCnt, page, ADMIN_LIST_CUTRECORD);
	}

	/**
	 * listOrder 계산
	 *
	 * @param totalCnt 전체 데이터 건수
	 * @param page 현재 페이지
	 * @param listCutRecord 페이지당 출력할 데이터 건수
	 * @return int
	 */
	public static int calListOrder(int totalCnt, int page, int listCutRecord) {
		return totalCnt - ((page - 1) * listCutRecord);
	}

	@Deprecated
	public static int calCurrentPage(int page) {
		return calCurrentPage(page, LIST_CUTRECORD);
	}

	@Deprecated
	public static int calCurrentPage(int page, int perPage) {
		int cpage = 1;
		if (page > 1) {
			cpage = (page - 1) * perPage + 1;
		}
		return cpage;
	}
}
