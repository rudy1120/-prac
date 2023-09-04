package egovframework.portal.sys.qrcode.service;

import java.util.List;

import egovframework.portal.sys.qrcode.vo.QrcodeVO;

/**
 * QR코드 생성 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014. 12. 13.	권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2014. 12. 13.
 */
public interface QrcodeService {

	public int selectQrcodeDataCnt();

	public List<QrcodeVO> selectQrcodeDataList();

	public QrcodeVO selectQrcodeDataDetail(int qrcIdx);

	public void insertQrcodeDataProc(QrcodeVO vo);

	public void updateQrcodeDataProc(QrcodeVO vo);

	public void deleteQrcodeDataProc(QrcodeVO vo);

}