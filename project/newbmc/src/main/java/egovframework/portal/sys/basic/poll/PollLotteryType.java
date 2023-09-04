package egovframework.portal.sys.basic.poll;

import egovframework.portal.util.StringUtil;

/**
 * 응답 결과 추첨 여부 구분
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017.07.20		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 7. 20.
 */
public enum PollLotteryType {

	/** 참여 완료 */
	COMPLETE("1", "참여 완료"),
	/** 당첨 */
	WIN("2", "당첨"),
	/** 당첨(취소) */
	CANCEL("3", "당첨(취소)"),
	/** 대기 */
	WAITING("4", "대기"),
	;

	private String code = "";
	private String desc = "";

	private PollLotteryType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static PollLotteryType toType(String code) {
		if (StringUtil.isNotBlank(code)) {
			for (PollLotteryType type : PollLotteryType.values()) {
				if (type.getCode().equals(code)) {
					return type;
				}
			}
		}

		return COMPLETE;
	}

}
