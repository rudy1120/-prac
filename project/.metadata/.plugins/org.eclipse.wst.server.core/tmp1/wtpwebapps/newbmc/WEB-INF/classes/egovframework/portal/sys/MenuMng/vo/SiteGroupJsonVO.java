package egovframework.portal.sys.MenuMng.vo;

import java.util.List;

/**
 * @author TAE
 *
 */
public class SiteGroupJsonVO {

	private String flag;
	private String siteOrderUseYN;
	private String siteGroupUseYN;
	private String message;
	List<SiteGroupVO> groups;


	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSiteOrderUseYN() {
		return siteOrderUseYN;
	}
	public void setSiteOrderUseYN(String siteOrderUseYN) {
		this.siteOrderUseYN = siteOrderUseYN;
	}
	public String getSiteGroupUseYN() {
		return siteGroupUseYN;
	}
	public void setSiteGroupUseYN(String siteGroupUseYN) {
		this.siteGroupUseYN = siteGroupUseYN;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SiteGroupVO> getGroups() {
		return groups;
	}
	public void setGroups(List<SiteGroupVO> groups) {
		this.groups = groups;
	}
	
	
	

}
