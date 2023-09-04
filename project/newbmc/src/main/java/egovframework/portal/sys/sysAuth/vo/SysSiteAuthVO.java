package egovframework.portal.sys.sysAuth.vo;

import egovframework.portal.common.vo.CommonVO;

import java.io.Serializable;
import java.util.Date;

/**
 * 사이트권한 - 사이트권한관리 서비스를 위한 VO 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.11.17		엄동건				최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author 엄동건
 * @since 2014.11.17
 */
public class SysSiteAuthVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deptId = "";
	private String siteCode = "";
	private int siteIdx = 0;
	private Date regDt = null;
	private Date modDt = null;

	private String[] siteCodes = null;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getSiteIdx() {
		return siteIdx;
	}

	public void setSiteIdx(int siteIdx) {
		this.siteIdx = siteIdx;
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

	public String[] getSiteCodes() {
		int _length = 0;
		if (siteCodes != null) _length = siteCodes.length;
		String[] _from = siteCodes;
		String[] _to = new String[_length];
		if (_from != null) {
			for (int i = 0; i < _from.length; i++) {
				_to[i] = _from[i];
			}
		}
		return _to;
	}

	public void setSiteCodes(String[] siteCodes) {
		int _length = 0;
		if (siteCodes != null) _length = siteCodes.length;
		String[] _from = siteCodes;
		String[] _to = new String[_length];
		if (_from != null) {
			for (int i = 0; i < _from.length; i++) {
				_to[i] = _from[i];
			}
		}
		this.siteCodes = _to;
	}
}
