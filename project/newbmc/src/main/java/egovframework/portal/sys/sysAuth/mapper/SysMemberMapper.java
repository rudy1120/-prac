package egovframework.portal.sys.sysAuth.mapper;

import java.util.List;

import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.DeptMngVO;
import egovframework.portal.sys.sysAuth.vo.PwChangeVO;
import egovframework.portal.sys.sysAuth.vo.SessionChangeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("sysMemberMapper")
public interface SysMemberMapper {

	public int getSysMemberListCnt(AdminLoginVO searchVO);

	public List<AdminLoginVO> getSysMemberList(AdminLoginVO searchVO);

	public AdminLoginVO getSysMember(AdminLoginVO searchVO);

	public List<DeptMngVO> getDeptAllList();

	public void insertSysMember(AdminLoginVO insertVO);

	public void modifySysMember(AdminLoginVO modifyVO);

	public void deleteSysMember(AdminLoginVO deleteVO);

	/**
	 * 관리자 정보 세부내용 조회
	 *
	 * @param inputVO
	 * @return
	 */
	public AdminLoginVO selectSysMemberDataDetail(AdminLoginVO inputVO);

	/**
	 * 관리자 정보 리스트 조회
	 *
	 * @param inputVO
	 * @return
	 */
	/*
	 * 땡겨온 게시판 구조가 다르므로 테이블들이 다를거 .. 일단 보류
	 * public List<AdminLoginVO> selectSysMemberDataList(BbsCommonVO inputVO)
	 * {
	 * return selectList("sysMemberMapper.selectSysMemberDataList",inputVO);
	 * }
	 */

	/**
	 * 관리자 정보 리스트 조회(부서기준)
	 *
	 * @param inputVO
	 * @return
	 */
	public List<AdminLoginVO> selectSysMemberDataListByDept(String inputVO);

	/**
	 * 게시판 관리자 정보 조회
	 *
	 * @param inputVO
	 * @return
	 */
	/*
	 * 땡겨온 게시판 구조가 다르므로 테이블들이 다를거 .. 일단 보류
	 * public List<AdminLoginVO> selectBbsAuthList(BbsCommonVO inputVO)
	 * {
	 * // 반환용 변수 생성
	 * List<AdminLoginVO> bbsAuthList = new ArrayList<AdminLoginVO>();
	 * // 게시판 권한 아이디 조회
	 * String bbsAuthId = sysMemberMapper.selectBbsAuthId(inputVO);
	 * String[] tempStr = null;
	 * // 게시판 권한 아이디 있을경우 조회
	 * if(!StrUtil.isEmpty(bbsAuthId)
	 * && bbsAuthId.indexOf("/") > -1){
	 * tempStr = bbsAuthId.split("/");
	 * for (int i = 0; i < tempStr.length; i++) {
	 * AdminLoginVO tempBbsAuthVO = new AdminLoginVO();
	 * tempBbsAuthVO.setId(tempStr[i]);
	 * tempBbsAuthVO.setName( sysMemberMapper.selectBbsAuthName(tempStr[i]) );
	 * bbsAuthList.add(tempBbsAuthVO);
	 * }
	 * }
	 * return bbsAuthList;
	 * }
	 */

	/**
	 * 게시판 관리권한 삭제
	 *
	 * @param inputVO
	 */
	/*
	 * public void deleteBbsAuthProc(BbsVO inputVO, String id)
	 * {
	 * String tempStr[] = null;
	 * String updateId = "";
	 * String bbsAuthId = sysMemberMapper.selectBbsAuthId(inputVO);
	 * if(bbsAuthId.indexOf(bbsAuthId) > -1){
	 * tempStr = bbsAuthId.split("/");
	 * for (int i = 0; i < tempStr.length; i++) {
	 * if(tempStr[i].indexOf(id)>-1){
	 * tempStr[i] = tempStr[i].replaceAll(id, "");
	 * }
	 * if(!StrUtil.isEmpty(tempStr[i])) {
	 * if(StrUtil.isEmpty(updateId)){
	 * updateId = tempStr[i];
	 * }else{
	 * updateId = updateId + "/"+ tempStr[i];
	 * }
	 * }
	 * }
	 * inputVO.setRegId(updateId);
	 * }
	 * sysMemberMapper.updateBbsAuthProc(inputVO);
	 * }
	 */

	/**
	 * 게시판 관리자 아이디 조회
	 *
	 * @param inputVO
	 * @return
	 */
	/*
	 * public String selectBbsAuthId(BbsCommonVO inputVO)
	 * {
	 * return sysMemberMapper.selectBbsAuthId(inputVO);
	 * }
	 */

	/**
	 * 게시판 관리자 이름 조회
	 *
	 * @param inputStr
	 * @return
	 */
	/*
	 * public String selectBbsAuthName(String inputStr)
	 * {
	 * String resultStr = "";
	 * String[] tempStr = null;
	 * if(!StrUtil.isEmpty(inputStr)
	 * && inputStr.indexOf("/") > -1){
	 * tempStr = inputStr.split("/");
	 * for (int i = 0; i < tempStr.length; i++) {
	 * if (i==0) {
	 * resultStr = sysMemberMapper.selectBbsAuthName(tempStr[i]);
	 * }else{
	 * resultStr = resultStr + "/" + sysMemberMapper.selectBbsAuthName(tempStr[i]);
	 * }
	 * }
	 * }
	 * return resultStr;
	 * }
	 */

	public int chkSysMemberSsoId(AdminLoginVO searchVO);

	public AdminLoginVO getSsoUser(AdminLoginVO searchVO);

	public int chkAccessLog(AdminLoginVO searchVO);

	public List<PwChangeVO> getPwPeriodInfo(PwChangeVO searchVO);

	public void updatePwPeriod(PwChangeVO searchVO);

	public List<SessionChangeVO> getSiPeriodInfo(SessionChangeVO searchVO);

	public void updateSiPeriod(SessionChangeVO searchVO);

}
