package egovframework.portal.sys.content.vo;

import egovframework.portal.common.vo.CommonVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 공통 - 관리자 메뉴조회 서비스를 위한 VO 클래스
 *
 * <pre>
 * &lt;&lt;개정이력(Modification Information)&gt;&gt;
 * 2014.12.02 엄동건
 * 최초 생성
 *
 * </pre>
 *
 * @author 개발팀 엄동건
 * @since 2014.12.02
 * @version 1.0
 */
public class SysMenuVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 7100801586915356628L;

	public SysMenuVO() {
	}

	public SysMenuVO(String mId) {
		setmId(mId);
	}

	private int idx;
	private String mId = "";
	private String menuName = "";
	private String programUrl = "";
	private String target = "";
	private String isUse = "";
	private int parentIdx = 0;
	private int menuOrder = 0;
	private int menuLevel = 0;
	private Date regDt;
	private Date modDt;
	private String siteCode = "";
	private int accessLevelCode = 0;
	private String menuOrderCode;
	private String menuSummary;
	private String privacyDataYn = ""; // 개인정보 취급 여부

	private List<SysMenuVO> depth2MenuList;
	private List<SysMenuVO> depth3MenuList;
	private List<SysMenuVO> depth4MenuList;
	private List<SysMenuVO> depth5MenuList;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getProgramUrl() {
		return programUrl;
	}

	public void setProgramUrl(String programUrl) {
		this.programUrl = programUrl;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public int getParentIdx() {
		return parentIdx;
	}

	public void setParentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public List<SysMenuVO> getDepth2MenuList() {
		return depth2MenuList;
	}

	public int getAccessLevelCode() {
		return accessLevelCode;
	}

	public void setAccessLevelCode(int accessLevelCode) {
		this.accessLevelCode = accessLevelCode;
	}

	public void setDepth2MenuList(List<SysMenuVO> depth2MenuList) {
		List<SysMenuVO> _from = depth2MenuList;
		List<SysMenuVO> _to = new ArrayList<SysMenuVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth2MenuList = _to;
	}

	public List<SysMenuVO> getDepth3MenuList() {
		return depth3MenuList;
	}

	public void setDepth3MenuList(List<SysMenuVO> depth3MenuList) {
		List<SysMenuVO> _from = depth3MenuList;
		List<SysMenuVO> _to = new ArrayList<SysMenuVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth3MenuList = _to;
	}

	public List<SysMenuVO> getDepth4MenuList() {
		return depth4MenuList;
	}

	public void setDepth4MenuList(List<SysMenuVO> depth4MenuList) {
		List<SysMenuVO> _from = depth4MenuList;
		List<SysMenuVO> _to = new ArrayList<SysMenuVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth4MenuList = _to;
	}

	public List<SysMenuVO> getDepth5MenuList() {
		return depth5MenuList;
	}

	public void setDepth5MenuList(List<SysMenuVO> depth5MenuList) {
		List<SysMenuVO> _from = depth5MenuList;
		List<SysMenuVO> _to = new ArrayList<SysMenuVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth5MenuList = _to;
	}

	public String getMenuOrderCode() {
		return menuOrderCode;
	}

	public void setMenuOrderCode(String menuOrderCode) {
		this.menuOrderCode = menuOrderCode;
	}

	public String getMenuSummary() {
		return menuSummary;
	}

	public void setMenuSummary(String menuSummary) {
		this.menuSummary = menuSummary;
	}

	public String getPrivacyDataYn() {
		return privacyDataYn;
	}

	public void setPrivacyDataYn(String privacyDataYn) {
		this.privacyDataYn = privacyDataYn;
	}




}
