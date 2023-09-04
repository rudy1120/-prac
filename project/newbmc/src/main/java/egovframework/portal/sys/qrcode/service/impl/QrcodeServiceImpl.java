package egovframework.portal.sys.qrcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.qrcode.mapper.QrcodeServiceMapper;
import egovframework.portal.sys.qrcode.service.QrcodeService;
import egovframework.portal.sys.qrcode.vo.QrcodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @ Author : 권태성
 * @ Date : 2014. 12. 13.
 * @ Comment :
 * @ File Name : QrcodeServiceImpl.java
 * << 개정이력(Modification Information) >>
 * 수정일 수정자 수정내용
 * ------- -------- ---------------------------
 * 2014. 12. 13. 최초 생성
 */
@Service("QrcodeService")
public class QrcodeServiceImpl extends EgovAbstractServiceImpl implements QrcodeService {

	@Resource(name = "qrcodeServiceMapper")
	QrcodeServiceMapper qrcodeServiceMapper;

	/**
	 * 등록된 QR코드 수 조회
	 *
	 * @return
	 */
	public int selectQrcodeDataCnt() {
		return qrcodeServiceMapper.selectQrcodeDataCnt();
	}

	/**
	 * 등록된 QR코드 리스트 조회
	 *
	 * @return
	 */
	public List<QrcodeVO> selectQrcodeDataList() {
		return qrcodeServiceMapper.selectQrcodeDataList();
	}

	/**
	 * QR코드 정보 조회
	 *
	 * @param qrcIdx
	 * @return
	 */
	public QrcodeVO selectQrcodeDataDetail(int qrcIdx) {
		return qrcodeServiceMapper.selectQrcodeDataDetail(qrcIdx);
	}

	/**
	 * QR코드 정보 등록
	 *
	 * @param vo
	 */
	public void insertQrcodeDataProc(QrcodeVO vo) {
		qrcodeServiceMapper.insertQrcodeDataProc(vo);
	}

	/**
	 * QR코드 정보 수정
	 *
	 * @param vo
	 */
	public void updateQrcodeDataProc(QrcodeVO vo) {
		qrcodeServiceMapper.updateQrcodeDataProc(vo);
	}

	/**
	 * QR코드정보 삭제
	 *
	 * @param vo
	 */
	public void deleteQrcodeDataProc(QrcodeVO vo) {
		qrcodeServiceMapper.deleteQrcodeDataProc(vo);
	}

}
