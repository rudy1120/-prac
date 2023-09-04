package egovframework.portal.sys.MenuMng;

import java.util.ArrayList;
import java.util.List;

import egovframework.portal.util.StringUtil;

/**
 * 사이트 구분값
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017.04.24		상천규				최초 생성 및 코딩
 * 2017.05.12		J.Ryeon Lee			게시판 연동
 * </pre>
 *
 * @author 상천규
 * @since 2017.04.24
 */
public enum SiteGubun {

	CIVIL("0", "대표(공통/메인/열린시장실)"),
	DEPT("1", "부서 및 빌트인"),
	UMD("2", "읍면동"),
	ETC("3", "기타"),
	;

	private String code;
	private String name;

	private SiteGubun(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static SiteGubun toType(String code) {
		if (StringUtil.isNotBlank(code)) {
			for (SiteGubun gubun : SiteGubun.values()) {
				if (gubun.getCode().equals(code)) {
					return gubun;
				}
			}
		}

		return null;
	}

	public static List<SiteGubun> toTypeList(String gubuns) {
		List<SiteGubun> rtn = new ArrayList<>();
		if (StringUtil.isNotBlank(gubuns)) {
			String gubunArr[] = gubuns.split(",");
			for (String gubun : gubunArr) {
				rtn.add(toType(gubun));
			}
		}

		return rtn;
	}
}
