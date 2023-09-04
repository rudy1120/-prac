package egovframework.portal.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 컬렉션 UTIL
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 4. 4.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 4. 4.
 */
public enum CollectionUtil {

	INSTANCE,;

	public static <T> List<T> copyToNewArrayList(List<T> origin) {
		List<T> copied = new ArrayList<T>();
		if (origin != null) {
			for (T element : origin) {
				copied.add(element);
			}
		}

		return copied;
	}

	public static <T> boolean hasElement(List<T> elements) {
		if (elements != null) {
			for (T element : elements) {
				if (element != null) {
					return Boolean.TRUE;
				}
			}
		}

		return Boolean.FALSE;
	}

	public static <T> boolean hasNotElement(List<T> elements) {
		return !hasElement(elements);
	}

	public static String allElementToString(List<String> elements) {
		String rtn = null;
		if (elements != null && elements.size() > 0) {
			rtn = "";
			for (String element : elements) {
				if (StringUtil.isNotEmpty(rtn)) {
					rtn += ",";
				}
				rtn += element;
			}
		}

		return rtn;
	}

	/**
	 * 맵을 새로운 맵으로 복사
	 *
	 * @param paramMap 복사 대상 맵
	 * @return 복사된 맵
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> copyMapToMap(Map<String, ? extends Object> paramMap) {
		Map<String, Object> retMap = new HashMap<String, Object>();
//		String[] exceptArr = except.split(",");

		Iterator iterator = paramMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
//			String key = (String) entry.getKey();
//			String value = (String) entry.getValue();
//			boolean chk = true;
//			for (String ex : exceptArr) {
//				if (key.equals(ex)) {
//					chk = false;
//				}
//			}
//			if (chk) {
			retMap.put((String) entry.getKey(), entry.getValue());
//			}
		}
		return retMap;
	}

}
