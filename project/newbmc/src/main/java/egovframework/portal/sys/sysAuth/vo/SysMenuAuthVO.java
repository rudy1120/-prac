package egovframework.portal.sys.sysAuth.vo;

import egovframework.portal.common.vo.CommonVO;

import java.io.Serializable;
import java.util.Date;

/**
 * 메뉴권한 - 메뉴권한관리 서비스를 위한 VO 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.10.07		엄동건				최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2014.10.07
 */
public class SysMenuAuthVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deptId = "";
	private String mId = "";
	private Date regDt = null;
	private Date modDt = null;
	private String programUrl = "";

	//개인권한 추가 16.05.09 손영식
	private String usrId = "";
	private int idx = 0;

	//권한조회 추가 16.05.17 손영식
	private String searchKind = "";
	private String dataMid = "";

	private String[] mIds = null;

	public void setProgramUrl(String programUrl) {
		this.programUrl = programUrl;
	}

	public String getProgramUrl() {
		return programUrl;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
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

	public String[] getmIds() {
		int _length = 0;
		if (mIds != null) {
			_length = mIds.length;
		}
		String[] _from = mIds;
		String[] _to = new String[_length];
		if (_from != null) {
			for (int i = 0; i < _from.length; i++) {
				_to[i] = _from[i];
			}
		}
		return _to;
	}

	public void setmIds(String[] mIds) {
		int _length = 0;
		if (mIds != null) {
			_length = mIds.length;
		}
		String[] _from = mIds;
		String[] _to = new String[_length];
		if (_from != null) {
			for (int i = 0; i < _from.length; i++) {
				_to[i] = _from[i];
			}
		}
		this.mIds = _to;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getDataMid() {
		return dataMid;
	}

	public void setDataMid(String dataMid) {
		this.dataMid = dataMid;
	}

	public String getSearchKind() {
		return searchKind;
	}

	public void setSearchKind(String searchKind) {
		this.searchKind = searchKind;
	}
}
