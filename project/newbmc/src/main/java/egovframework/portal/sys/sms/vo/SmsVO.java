package egovframework.portal.sys.sms.vo;
/**
 * SMS을 관리하는 VO
 * @author SM
 *
 */
public class SmsVO {

	/*문자발송 일시*/
	private String bTermSdate;
	
	/*문자 내용*/
	private String smsMsg;
	
	/*문자 발신번호*/
	private String smsPhone;
	
	/*선택 공급용도*/
	private String usesCk;


	public String getbTermSdate() {
		return bTermSdate;
	}

	public void setbTermSdate(String bTermSdate) {
		this.bTermSdate = bTermSdate;
	}

	public String getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}

	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public String getUsesCk() {
		return usesCk;
	}

	public void setUsesCk(String usesCk) {
		this.usesCk = usesCk;
	}
	
	
	
}
