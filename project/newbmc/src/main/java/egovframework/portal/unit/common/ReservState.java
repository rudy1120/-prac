package egovframework.portal.unit.common;

/**
 * 예약 신청자 플래그
 *
 * @author J.Ryeon Lee
 * @since 2016.06.30
 */
public enum ReservState {

	APPLIED(0, "신청완료"),
	APPROVED(1, "승인"),
	REJECTED(2, "거절"), ;

	private int code;
	private String text;

	private ReservState(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

	public static ReservState toType(int code) {
		for (ReservState appState : ReservState.values()) {
			if (appState.getCode() == code) {
				return appState;
			}
		}

		return APPLIED;
	}
}
