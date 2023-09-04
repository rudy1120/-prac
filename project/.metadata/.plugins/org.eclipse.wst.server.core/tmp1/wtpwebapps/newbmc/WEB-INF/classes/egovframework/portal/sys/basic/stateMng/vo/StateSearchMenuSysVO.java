package egovframework.portal.sys.basic.stateMng.vo;

import egovframework.portal.common.vo.CommonVO;

/**
 * 통계 관리 로그 있는 메뉴 목록 조회용 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.01.22		엄동건				최초 생성 및 코딩
 * </pre>
 *
 * @author 엄동건
 * @since 2015.01.22
 */
public class StateSearchMenuSysVO extends CommonVO {

	private String mId;
	private String menuName;
	private String parentName;
	private String ParamCode;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getParamCode() {
		return ParamCode;
	}

	public void setParamCode(String paramCode) {
		ParamCode = paramCode;
	}

}
