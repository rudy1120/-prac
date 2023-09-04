package egovframework.portal.util;

public enum IntegerUtil {

	INSTANCE,;

	public static Integer parse(String target, Integer init) {
		return StringUtil.isNumber(target) ? Integer.parseInt(target) : init;
	}

	public static Integer parse(String target, Integer min, Integer init) {
		Integer rtn = parse(target, init);
		return min <= rtn ? rtn : init;
	}

	public static Integer parse(String target, Integer min, Integer max, Integer init) {
		Integer rtn = parse(target, min, init);
		return max < rtn ? max : rtn;
	}

}
