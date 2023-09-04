package egovframework.portal.main;

public enum SnsType {

	FACEBOOK("페이스북"),
	TWITTER("트위터"),
	KAKAO_STORY("카카오 스토리"),
	YOUTUBE("유튜브"),
	BLOG("블로그"),
	;

	private String name;

	private SnsType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
