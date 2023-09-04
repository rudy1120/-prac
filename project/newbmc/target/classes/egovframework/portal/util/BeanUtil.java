package egovframework.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import egovframework.portal.common.Constant;

/**
 * 빈 유틸
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 4. 24.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 4. 24.
 */
public enum BeanUtil {

	INSTANCE,;

	/**
	 * {@link #copy(Object, Object, String[])} wrapper method
	 * 해당 메소드 호출 전 기본값 세팅
	 *
	 * @param from
	 * @param to
	 */
	public static <T> T copy(Object from, T to) {
		return copy(from, to, Constant.IGNORE_PROPERTIES);
	}

	/**
	 * {@link #copy(Object, Object, String[])} wrapper method
	 * 해당 메소드 호출 전 기본값 세팅
	 *
	 * @param from
	 * @param to
	 */
	public static <T> T copy(Object from, T to, Boolean defaultAppend, String... additionalIgnoreProps) {
		List<String> ignorePropes = new ArrayList<String>();
		for (String prop : additionalIgnoreProps) {
			ignorePropes.add(prop);
		}

		if (defaultAppend) {
			for (String prop : Constant.IGNORE_PROPERTIES) {
				ignorePropes.add(prop);
			}
		}

		return copy(from, to, CollectionUtil.allElementToString(ignorePropes).split(","));
	}

	/**
	 * {@link BeanUtils#copyProperties(Object, Object)} wrapper method
	 * 해당 메소드 호출 전 널값 검증
	 *
	 * @param from
	 * @param to
	 */
	public static <T> T copy(Object from, T to, String[] ignoreProps) {
		if (from != null && to != null) {
			BeanUtils.copyProperties(from, to, ignoreProps);
		}

		return to;
	}
}
