package egovframework.portal.sys.qrcode.mapper;

import java.util.List;

import egovframework.portal.sys.qrcode.vo.QrcodeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("qrcodeServiceMapper")
public interface QrcodeServiceMapper {

	/**
	 * 등록된 QR코드 수 조회
	 *
	 * @return
	 */
	public int selectQrcodeDataCnt();

	/**
	 * 등록된 QR코드 리스트 조회
	 *
	 * @return
	 */
	public List<QrcodeVO> selectQrcodeDataList();

	/**
	 * QR코드 정보 조회
	 *
	 * @param qrcIdx
	 * @return
	 */
	public QrcodeVO selectQrcodeDataDetail(int qrcIdx);

	/**
	 * QR코드 정보 등록
	 *
	 * @param vo
	 */
	public void insertQrcodeDataProc(QrcodeVO vo);

	/**
	 * QR코드 정보 수정
	 *
	 * @param vo
	 */
	public void updateQrcodeDataProc(QrcodeVO vo);

	/**
	 * QR코드정보 삭제
	 *
	 * @param vo
	 */
	public void deleteQrcodeDataProc(QrcodeVO vo);

}
