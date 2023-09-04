package egovframework.portal.sys.qrcode.vo;

import java.util.Date;

/**
 * @ Author : 권태성
 * @ Date : 2014. 12. 13.
 * @ Comment : QR코드 VO
 * @ File Name : QrcodeVO.java
 * << 개정이력(Modification Information) >>
 * 수정일 수정자 수정내용
 * ------- -------- ---------------------------
 * 2014. 12. 13. 최초 생성
 */
public class QrcodeVO {

	private int qrcIdx = 0;
	private String qrcTitle = "";
	private String qrcName = "";
	private String qrcCompany = "";
	private String qrcTel = "";
	private String qrcEmail = "";
	private String qrcAddress = "";
	private String qrcLink = "";
	private String qrcMemo = "";
	private int qrcSize = 0;
	private String qrcWebFileName = "";
	private String qrcPrintFileName = "";
	private Date regDt = null;

	private String qrcType = "";

	public int getQrcIdx() {
		return qrcIdx;
	}

	public void setQrcIdx(int qrcIdx) {
		this.qrcIdx = qrcIdx;
	}

	public String getQrcTitle() {
		return qrcTitle;
	}

	public void setQrcTitle(String qrcTitle) {
		this.qrcTitle = qrcTitle;
	}

	public String getQrcName() {
		return qrcName;
	}

	public void setQrcName(String qrcName) {
		this.qrcName = qrcName;
	}

	public String getQrcCompany() {
		return qrcCompany;
	}

	public void setQrcCompany(String qrcCompany) {
		this.qrcCompany = qrcCompany;
	}

	public String getQrcTel() {
		return qrcTel;
	}

	public void setQrcTel(String qrcTel) {
		this.qrcTel = qrcTel;
	}

	public String getQrcEmail() {
		return qrcEmail;
	}

	public void setQrcEmail(String qrcEmail) {
		this.qrcEmail = qrcEmail;
	}

	public String getQrcAddress() {
		return qrcAddress;
	}

	public void setQrcAddress(String qrcAddress) {
		this.qrcAddress = qrcAddress;
	}

	public String getQrcLink() {
		return qrcLink;
	}

	public void setQrcLink(String qrcLink) {
		this.qrcLink = qrcLink;
	}

	public String getQrcMemo() {
		return qrcMemo;
	}

	public void setQrcMemo(String qrcMemo) {
		this.qrcMemo = qrcMemo;
	}

	public int getQrcSize() {
		return qrcSize;
	}

	public void setQrcSize(int qrcSize) {
		this.qrcSize = qrcSize;
	}

	public String getQrcWebFileName() {
		return qrcWebFileName;
	}

	public void setQrcWebFileName(String qrcWebFileName) {
		this.qrcWebFileName = qrcWebFileName;
	}

	public String getQrcPrintFileName() {
		return qrcPrintFileName;
	}

	public void setQrcPrintFileName(String qrcPrintFileName) {
		this.qrcPrintFileName = qrcPrintFileName;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getQrcType() {
		return qrcType;
	}

	public void setQrcType(String qrcType) {
		this.qrcType = qrcType;
	}

}