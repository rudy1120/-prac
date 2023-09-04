package egovframework.portal.common;

import java.util.HashMap;
import java.util.Map;

import egovframework.portal.unit.common.web.YHDCalendarController;

/**
 * 신청자 신청 상태 관리 플래그
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 6.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 6.
 * @see YHDCalendarController
 */
public enum AppStateType {

	REQUESTED("0", "신청"),
	ACCEPTED("1", "접수"),
	PROCESSING("2", "처리 중"),
	APPROVED("3", "승인"),
	REJECTED("4", "기각"),
	;

	private String code;
	private String name;

	private AppStateType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static Map<String, String> asMap() {
		Map<String, String> rtn = new HashMap<>();
		for (AppStateType type : AppStateType.values()) {
			rtn.put(type.getCode(), type.getName());
		}

		return rtn;
	}

}
