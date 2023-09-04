package egovframework.portal.unit.bmc.opendoc.service;

import java.util.List;

import egovframework.portal.unit.bmc.opendoc.vo.OpendocVO;

public interface OpendocService {
	
	/**
	 * 결재문서 목록 카운트
	 * @param opendocVO
	 * @return
	 */
	public int getListCnt(OpendocVO opendocVO);

	/**
	 * 결재문서 목록 리스트
	 * @param opendocVO
	 * @return
	 */
	public List<OpendocVO> getList(OpendocVO opendocVO);
	
	/**
	 * 결제문서 상세 보기
	 * @param opendocVO
	 * @return
	 */
	public OpendocVO getContent(OpendocVO opendocVO);

}
