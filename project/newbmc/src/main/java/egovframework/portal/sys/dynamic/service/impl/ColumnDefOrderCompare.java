package egovframework.portal.sys.dynamic.service.impl;

import java.util.Comparator;

import egovframework.portal.sys.dynamic.vo.ColumnDefVO;

/**
 * {@link ColumnDefVO} 목록 SORT 클래스
 *
 * @author J.Ryeon Lee
 * @since 2016.05.18
 */
public class ColumnDefOrderCompare implements Comparator<ColumnDefVO> {

	@Override
	public int compare(ColumnDefVO o1, ColumnDefVO o2) {
		return ((Integer)Integer.parseInt(o1.getOrder())).compareTo((Integer)Integer.parseInt(o2.getOrder()));
	}

}
