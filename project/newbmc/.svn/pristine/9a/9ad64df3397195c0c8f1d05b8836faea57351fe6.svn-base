package egovframework.portal.sys.sysAuth.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import egovframework.portal.common.vo.CommonVO;

/**
 * 공통 - 부서조회 서비스를 위한 VO 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.01.05		엄동건				최초 생성 및 코딩
 * 2017.07.17		J.Ryeon Lee			리팩키징
 * </pre>
 *
 * @author 엄동건
 * @since 2015.01.05
 */
public class DeptMngVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deptId = "";
	private String deptName = "";
	private String deptUpperId = "";
	private int level = 0;

	private List<DeptMngVO> depth1List = null;
	private List<DeptMngVO> depth2List = null;
	private List<DeptMngVO> depth3List = null;
	private List<DeptMngVO> depth4List = null;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptUpperId() {
		return deptUpperId;
	}

	public void setDeptUpperId(String deptUpperId) {
		this.deptUpperId = deptUpperId;
	}

	public List<DeptMngVO> getDepth1List() {
		return depth1List;
	}

	public void setDepth1List(List<DeptMngVO> depth1List) {
		List<DeptMngVO> _from = depth1List;
		List<DeptMngVO> _to = new ArrayList<DeptMngVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth1List = _to;
	}

	public List<DeptMngVO> getDepth2List() {
		return depth2List;
	}

	public void setDepth2List(List<DeptMngVO> depth2List) {
		List<DeptMngVO> _from = depth2List;
		List<DeptMngVO> _to = new ArrayList<DeptMngVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth2List = _to;
	}

	public List<DeptMngVO> getDepth3List() {
		return depth3List;
	}

	public void setDepth3List(List<DeptMngVO> depth3List) {
		List<DeptMngVO> _from = depth3List;
		List<DeptMngVO> _to = new ArrayList<DeptMngVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth3List = _to;
	}

	public List<DeptMngVO> getDepth4List() {
		return depth4List;
	}

	public void setDepth4List(List<DeptMngVO> depth4List) {
		List<DeptMngVO> _from = depth4List;
		List<DeptMngVO> _to = new ArrayList<DeptMngVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth4List = _to;
	}

}
