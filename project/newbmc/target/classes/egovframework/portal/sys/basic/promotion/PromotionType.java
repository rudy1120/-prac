package egovframework.portal.sys.basic.promotion;

import egovframework.portal.util.StringUtil;

/**
 * 홍보 자료 타입 (인스턴스 순서 변경 금지, jsp에서 사용중)
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 19.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 19.
 */
public enum PromotionType {

	BANNER("B", "banner", "배너"),
	VISUALZONE("V", "visualzone", "홍보 이미지"),
	POPUPZONE("P", "popupzone", "팝업존"),
	;

	private String code;
	private String path;
	private String desc;

	private PromotionType(String code, String path, String desc) {
		this.code = code;
		this.path = path;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getPath() {
		return path;
	}

	public String getDesc() {
		return desc;
	}

	public static PromotionType toType(String path) {
		if (StringUtil.isNotBlank(path)) {
			for (PromotionType type : PromotionType.values()) {
				if (type.getPath().equals(path)) {
					return type;
				}
			}
		}

		return PromotionType.BANNER;
	}

}
