package egovframework.portal.dept.vo;

import java.util.ArrayList;
import java.util.List;

import egovframework.portal.common.vo.CommonVO;

public class DeptVO extends CommonVO {

	private String deptId = "";
	private String deptName = "";
	private String parentDeptId = "";
	private String deptLevel = "";
	private String deptGubun = "";
	private String fullChildPath = "";

	private List<DeptVO> depth1List = null;
	private List<DeptVO> depth2List = null;
	private List<DeptVO> depth3List = null;
	private List<DeptVO> depth4List = null;

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

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public List<DeptVO> getDepth1List() {
		return depth1List;
	}

	public void setDepth1List(List<DeptVO> depth1List) {
		List<DeptVO> _from = depth1List;
		List<DeptVO> _to = new ArrayList<DeptVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}

		this.depth1List = _to;
	}

	public List<DeptVO> getDepth2List() {
		return depth2List;
	}

	public void setDepth2List(List<DeptVO> depth2List) {
		List<DeptVO> _from = depth2List;
		List<DeptVO> _to = new ArrayList<DeptVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth2List = _to;
	}

	public List<DeptVO> getDepth3List() {
		return depth3List;
	}

	public void setDepth3List(List<DeptVO> depth3List) {
		List<DeptVO> _from = depth3List;
		List<DeptVO> _to = new ArrayList<DeptVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth3List = _to;
	}

	public List<DeptVO> getDepth4List() {
		return depth4List;
	}

	public void setDepth4List(List<DeptVO> depth4List) {
		List<DeptVO> _from = depth4List;
		List<DeptVO> _to = new ArrayList<DeptVO>();
		if (_from != null) {
			for (int i = 0; i < _from.size(); i++) {
				_to.add(_from.get(i));
			}
		}
		this.depth4List = _to;
	}

	public String getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getFullChildPath() {
		return fullChildPath;
	}

	public void setFullChildPath(String fullChildPath) {
		this.fullChildPath = fullChildPath;
	}

	public String getDeptGubun() {
		return deptGubun;
	}

	public void setDeptGubun(String deptGubun) {
		this.deptGubun = deptGubun;
	}

}
