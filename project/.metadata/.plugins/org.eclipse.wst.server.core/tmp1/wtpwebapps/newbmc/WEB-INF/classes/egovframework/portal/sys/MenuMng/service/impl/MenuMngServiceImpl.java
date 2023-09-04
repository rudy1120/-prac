package egovframework.portal.sys.MenuMng.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.yhdatabase.ysmartcmslinkage.main.SmartCMS;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.sys.MenuMng.mapper.MenuChargeMngMapper;
import egovframework.portal.sys.MenuMng.mapper.MenuMngMapper;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenuChargeVO;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.MenuMng.vo.MenusChargeHistoryMngVO;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.vo.SysSiteAuthVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2014. 9. 11.			엄동건				최초 생성 및 코딩
 * 2017. 4. 24.			권태성				getNext 메소드 수정 (메뉴 개수 10개 이상 늘어나면 하위 메뉴ID 발급 오류 수정)
 * 2017. 6. 19.			권태성				다중 담당자 설정 가능하도록 수정
 * </pre>
 *
 * @author 엄동건
 * @since 2014. 9. 11.
 */
@Service("menuMngService")
public class MenuMngServiceImpl extends EgovAbstractServiceImpl implements MenuMngService {

	@Resource(name = "menuMngMapper")
	private MenuMngMapper menuMngMapper;

	@Resource(name = "menuChargeMngMapper")
	private MenuChargeMngMapper menuChargeMngMapper;

	private final Logger LOGGER = LogManager.getLogger();

	/**
	 * 사이트 수 조회
	 *
	 * @return
	 */
	public int getSiteListCnt() {
		int result = menuMngMapper.getSiteListCnt();
		return result;
	}

	/**
	 * 사이트 전체목록 조회
	 *
	 * @return
	 */
	public List<MenusMngVO> getMenusMngListAll(HttpServletRequest request) {

		// 권한 설정을 윈한 로그인 정보 조회
		AdminLoginVO adminLoginVO = SessionUtil.getAdminInstance(request);

		String searchQuery = "";
		// 전체관리자가 아닌경우
		if (adminLoginVO.getAdminAccessLevelCode() != 10) {
			List<SysSiteAuthVO> sysSiteAuthList = adminLoginVO.getAdminSiteAuths();

			if (sysSiteAuthList != null) {
				// 사이트 조회용 쿼리 생섵
				StringBuilder siteSearchQuery = new StringBuilder();
				for (int i = 0; i < sysSiteAuthList.size(); i++) {
					SysSiteAuthVO sysSiteAuthVO = sysSiteAuthList.get(i);

					if (i > 0) {
						siteSearchQuery.append(" or ");
					}

					siteSearchQuery.append(" idx = ").append(Integer.toString(sysSiteAuthVO.getSiteIdx()));
				}
				searchQuery = siteSearchQuery.toString();
				if (!searchQuery.equals("")) {
					searchQuery = " and (" + searchQuery + ")";
				}
			}
		}

		return menuMngMapper.getMenusMngListAll(searchQuery);
	}

	/**
	 * 메뉴 목록 조회
	 *
	 * @param siteCode
	 * @return
	 */
	public Map<String, Object> getMenuListMap(String siteCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MenuVO searchVO = new MenuVO();
		searchVO.setSiteCode(siteCode);

		List<MenuVO> menuList = menuMngMapper.getMenuListAddChargeNms(searchVO);
		List<MenuVO> menuResultList = new ArrayList<MenuVO>();

		for (MenuVO depth1Menu : menuList) {
			if (depth1Menu.getMenuLevel() == 1) {
				List<MenuVO> depth2MenuList = new ArrayList<MenuVO>();
				for (MenuVO depth2Menu : menuList) {
					if (depth2Menu.getMenuLevel() == 2 && depth1Menu.getIdx() == depth2Menu.getParentIdx()) {
						List<MenuVO> depth3MenuList = new ArrayList<MenuVO>();
						for (MenuVO depth3Menu : menuList) {
							if (depth3Menu.getMenuLevel() == 3 && depth2Menu.getIdx() == depth3Menu.getParentIdx()) {
								List<MenuVO> depth4MenuList = new ArrayList<MenuVO>();
								for (MenuVO depth4Menu : menuList) {
									if (depth4Menu.getMenuLevel() == 4 && depth3Menu.getIdx() == depth4Menu.getParentIdx()) {
										List<MenuVO> depth5MenuList = new ArrayList<MenuVO>();
										for (MenuVO depth5Menu : menuList) {
											if (depth5Menu.getMenuLevel() == 5 && depth4Menu.getIdx() == depth5Menu.getParentIdx()) {
												depth5MenuList.add(depth5Menu);
											}
										}
										depth4Menu.setDepth5MenuList(depth5MenuList);
										depth4MenuList.add(depth4Menu);
									}
								}
								depth3Menu.setDepth4MenuList(depth4MenuList);
								depth3MenuList.add(depth3Menu);
							}
						}
						depth2Menu.setDepth3MenuList(depth3MenuList);
						depth2MenuList.add(depth2Menu);
					}
				}
				depth1Menu.setDepth2MenuList(depth2MenuList);
				menuResultList.add(depth1Menu);
			}
		}

		resultMap.put("siteMenuList", menuResultList);
		resultMap.put("siteMenuSize", menuResultList.size());

		return resultMap;
	}

	/**
	 * 메뉴점검목록 카운트
	 *
	 * @return
	 */
	public int getMenuCheckTotalCnt(MenuVO searchVO) {
		if("ACK".equals(searchVO.getSearchType())) {
			return menuMngMapper.getMenuCheckAckTotalCnt(searchVO);
		} else {
			return menuMngMapper.getMenuCheckTotalCnt(searchVO);
		}
	}

	/**
	 * 메뉴점검목록 조회
	 *
	 * @return
	 */
	public List<MenuVO> getMenuCheckList(MenuVO searchVO) {

		return menuMngMapper.getMenuCheckList(searchVO);
	}

	/**
	 * 메뉴정보 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public MenuVO getMenu(MenuVO searchVO) {
		return menuMngMapper.getMenu(searchVO);
	}

	public String getMenuInBbsName(MenuVO searchVO) {
		return menuMngMapper.getMenuInBbsName(searchVO);
	}

	/**
	 * 메뉴추가시 추가할 메뉴의 메뉴ID를 가져온다.
	 *
	 * @param searchVO
	 * @return
	 */
	public String getNextMid(MenuVO searchVO) {
		String nextMid = "";
		if (searchVO != null) {
			String orgMid = searchVO.getmId();
			MenuVO resultVO = setSearchMaxMid(searchVO);
			String maxMid = menuMngMapper.getMaxMid(resultVO);
			if (maxMid == null) {
				maxMid = orgMid;
			}
			return getNext(maxMid, resultVO.getMenuLevel());
		} else {
			return nextMid;
		}
	}

	/**
	 * 메뉴추가시 추가할 메뉴의 레벨을 가져온다.
	 *
	 * @param menuId
	 * @return
	 */
	public int getNextMenuLevel(String menuId) {
		int menuLevel = 0;
		if (menuId.equals("0000000000")) {
			menuLevel = 1; // level 1
		} else if (menuId.substring(2, 10).equals("00000000")) {
			menuLevel = 2;
		} else if (menuId.substring(4, 10).equals("000000")) {
			menuLevel = 3;
		} else if (menuId.substring(6, 10).equals("0000")) {
			menuLevel = 4;
		} else if (menuId.substring(8, 10).equals("00")) {
			menuLevel = 5;
		}

		return menuLevel;
	}

	public MenuVO setSearchMaxMid(MenuVO searchVO) {
		MenuVO resultVO = searchVO;
		if (!"".equals(resultVO.getmId()) && resultVO.getmId() != null) {
			if (resultVO.getmId().equals("0000000000")) {
				resultVO.setMenuLevel(1);
				resultVO.setmId("");
			} else if (resultVO.getmId().substring(2, 10).equals("00000000")) {
				resultVO.setMenuLevel(2);
				resultVO.setmId(resultVO.getmId().substring(0, 2));
			} else if (resultVO.getmId().substring(4, 10).equals("000000")) {
				resultVO.setMenuLevel(3);
				resultVO.setmId(resultVO.getmId().substring(0, 4));
			} else if (resultVO.getmId().substring(6, 10).equals("0000")) {
				resultVO.setMenuLevel(4);
				resultVO.setmId(resultVO.getmId().substring(0, 6));
			} else if (resultVO.getmId().substring(8, 10).equals("00")) {
				resultVO.setMenuLevel(5);
				resultVO.setmId(resultVO.getmId().substring(0, 8));
			}
			return resultVO;
		} else {
			return null;
		}

	}

	/**
	 * 메뉴 추가시 다음에 사용할 메뉴아이디를 생성
	 *
	 * @param maxMid
	 * @param menuLevel
	 * @return
	 */
	public String getNext(String maxMid, int menuLevel) {
		String nextMid = "";
		if (!"".equals(maxMid)) {
			if (menuLevel == 2) {
				//메뉴 개수 10개 이상 늘어났을때 생성되는 메뉴ID 오류 수정 2017. 4. 24 권태성
				nextMid = (Integer.parseInt(maxMid.substring(0, 1)) > 0) ? String.valueOf((Integer.parseInt(maxMid) + 1000000)) : maxMid.substring(0, 1) + (Integer.parseInt(maxMid) + 1000000);
			} else if (menuLevel == 3) {
				nextMid = (Integer.parseInt(maxMid.substring(0, 1)) > 0) ? String.valueOf((Integer.parseInt(maxMid) + 10000)) : maxMid.substring(0, 1) + (Integer.parseInt(maxMid) + 10000);
			} else if (menuLevel == 4) {
				nextMid = (Integer.parseInt(maxMid.substring(0, 1)) > 0) ? String.valueOf((Integer.parseInt(maxMid) + 100)) : maxMid.substring(0, 1) + (Integer.parseInt(maxMid) + 100);
			} else if (menuLevel == 5) {
				nextMid = (Integer.parseInt(maxMid.substring(0, 1)) > 0) ? String.valueOf((Integer.parseInt(maxMid) + 1)) : maxMid.substring(0, 1) + (Integer.parseInt(maxMid) + 1);
			} else if (menuLevel == 1) {
				if ((Integer.parseInt(maxMid) + 100000000) >= 1000000000) {
					nextMid = "" + (Integer.parseInt(maxMid) + 100000000);
				} else {
					nextMid = "0" + (Integer.parseInt(maxMid) + 100000000);
				}
			}
		}

		return nextMid;
	}

	/**
	 * 메뉴 등록처리
	 *
	 * @param insertVO
	 */
	public void insertMenu(MenuVO insertVO, HttpServletRequest request) {
		if (insertVO != null) {
			int nextIdx = menuMngMapper.getMenuNextSeq();
			String orgMId = insertVO.getmId();
			MenuVO searchVO = new MenuVO();
			searchVO.setmId(insertVO.getmId());
			searchVO.setSiteCode(insertVO.getSiteCode());
			searchVO = this.setInsertSearchMid(searchVO);
			int nextMenuOrder = menuMngMapper.getMenuNextMenuOrder(searchVO);
			searchVO.setMenuLevel(searchVO.getMenuLevel() - 1);

			int nextParentIdx = 0;
			if (searchVO.getMenuLevel() > 0) {
				nextParentIdx = menuMngMapper.getMenuNextParentIdx(searchVO);
			}
			insertVO.setParentIdx(nextParentIdx);

			if (insertVO.getIsFirst() == 1) {
				menuMngMapper.updateSiblingsFirst(insertVO);
			}

			MenuVO nextSearchMenuOrderCodeVO = setInsertSearchMid(insertVO);
			String addNextValue = getMoveDepth(searchVO.getMenuLevel() + 1);
			String siteCode = searchVO.getSiteCode();
			String mId = nextSearchMenuOrderCodeVO.getmId();
			int menuLevel = searchVO.getMenuLevel() + 1;

			MenuVO param = new MenuVO();
			param.setSiteCode(siteCode);
			param.setMenuLevel(menuLevel);
			param.setmId(orgMId);
			param.setParentIdx(nextParentIdx);

			String menuOrderCode = menuMngMapper.getMenuNextMenuOrderCode(param);
			menuOrderCode = getNext(menuOrderCode, menuLevel);

			searchVO.setAddNextValue(addNextValue);
			searchVO.setSiteCode(siteCode);
			searchVO.setmId(mId);
			searchVO.setMenuLevel(menuLevel);
			insertVO.setMenuOrderCode(menuOrderCode);
			insertVO.setIdx(nextIdx);
			insertVO.setmId(orgMId);
			insertVO.setMenuOrder(nextMenuOrder);

			menuMngMapper.insertMenu(insertVO);

			//담당자 정보 저장
			if (request.getParameterValues("chargeId") != null && request.getParameterValues("chargeDepCode") != null && request.getParameterValues("chargeFnm") != null && request.getParameterValues("chargeTel") != null && request.getParameterValues("chargeTel") != null && request.getParameterValues("chargeDepNm") != null) {
				menuChargeMngMapper.setDeleteOld(new MenuChargeVO(insertVO.getSiteCode(), insertVO.getmId())); //기존 담당자 정보 삭제
				/************************* y-SmartCMS 컨텐츠 관리자 데이터 세팅 (2017.06.19 권태성) *************************/
				String ret = "";
				if("Y".equals(EgovProperties.getProperty("Globals.use.cms.yn"))) {
					ret = SmartCMS.setDeleteContentsAuth(insertVO.getSiteCode(), insertVO.getmId()); //CMS 담당자 정보 삭제
					if (!"success".equals(ret)) {
						LOGGER.error(">> y-SmartCMS 컨텐츠 관리자 등록 처리가 실패했습니다. / SITE_CODE : " + insertVO.getSiteCode() + ", mId : " + insertVO.getmId());
					}
				}
				/**********************************************************************************************************/
				for (int i = 0; i < request.getParameterValues("chargeId").length; i++) {
					menuChargeMngMapper.setInsertCharge(new MenuChargeVO(insertVO.getSiteCode(), insertVO.getmId(), request.getParameterValues("chargeId")[i], request.getParameterValues("chargeFnm")[i], request.getParameterValues("chargeDepCode")[i], request.getParameterValues("chargeDepNm")[i], request.getParameterValues("chargeTel")[i], Integer.parseInt(request.getParameterValues("sortNum")[i])));
					insertMenuChargeHistory(new MenuVO(insertVO.getSiteCode(), insertVO.getmId(), request.getParameterValues("chargeFnm")[i], request.getParameterValues("chargeId")[i], request.getParameterValues("chargeDepCode")[i], request.getParameterValues("chargeDepNm")[i]));
					/************************* y-SmartCMS 컨텐츠 관리자 데이터 세팅 (2017.06.19 권태성) *************************/
					if("Y".equals(EgovProperties.getProperty("Globals.use.cms.yn"))) {
						ret = SmartCMS.setContentsAuth(request.getParameterValues("chargeId")[i], insertVO.getSiteCode(), insertVO.getmId()); //y-SmartCMS 컨텐츠 관리자 등록 처리
						if (!"success".equals(ret)) {
							LOGGER.error(">> y-SmartCMS 컨텐츠 관리자 삭제 처리가 실패했습니다. / ID : " + request.getParameterValues("chargeId")[i] + ", SITE_CODE : " + insertVO.getSiteCode() + ", mId : " + insertVO.getmId());
						}
					}
					/**********************************************************************************************************/
				}
			}
		}
	}

	/**
	 * 메뉴추가시 추가할 메뉴 아이디로 MenuOrder, ParentIdx 를 생성하기 위한 조회용 메뉴 아이디
	 *
	 * @param searchVO
	 * @return
	 */
	public MenuVO setInsertSearchMid(MenuVO searchVO) {
		MenuVO resultVO = searchVO;
		if (!"".equals(resultVO.getmId()) && resultVO.getmId() != null) {
			if (resultVO.getmId().substring(2, 10).equals("00000000")) {
				resultVO.setMenuLevel(1);
				resultVO.setmId("0000000000");
			} else if (resultVO.getmId().substring(4, 10).equals("000000")) {
				resultVO.setMenuLevel(2);
				resultVO.setmId(resultVO.getmId().substring(0, 2));
			} else if (resultVO.getmId().substring(6, 10).equals("0000")) {
				resultVO.setMenuLevel(3);
				resultVO.setmId(resultVO.getmId().substring(0, 4));
			} else if (resultVO.getmId().substring(8, 10).equals("00")) {
				resultVO.setMenuLevel(4);
				resultVO.setmId(resultVO.getmId().substring(0, 6));
			} else {
				resultVO.setMenuLevel(5);
				resultVO.setmId(resultVO.getmId().substring(0, 8));
			}
			return resultVO;
		} else {
			return null;
		}
	}

	public MenuVO setMaxSearchMenuOrderCode(MenuVO searchVO) {
		MenuVO resultVO = searchVO;
		if (!"".equals(resultVO.getMenuOrderCode()) && resultVO.getMenuOrderCode() != null) {
			if (resultVO.getMenuOrderCode().substring(4, 10).equals("000000")) {
				resultVO.setMenuLevel(2);
				resultVO.setMenuOrderCode(resultVO.getMenuOrderCode().substring(0, 2));
			} else if (resultVO.getmId().substring(6, 10).equals("0000")) {
				resultVO.setMenuLevel(3);
				resultVO.setMenuOrderCode(resultVO.getMenuOrderCode().substring(0, 4));
			} else if (resultVO.getmId().substring(8, 10).equals("00")) {
				resultVO.setMenuLevel(4);
				resultVO.setMenuOrderCode(resultVO.getMenuOrderCode().substring(0, 6));
			} else {
				resultVO.setMenuLevel(5);
				resultVO.setMenuOrderCode(resultVO.getMenuOrderCode().substring(0, 8));
			}
			return resultVO;
		} else {
			return null;
		}
	}

	public MenuVO setMaxSearchMid(MenuVO searchVO) {
		MenuVO resultVO = searchVO;
		if (!"".equals(resultVO.getmId()) && resultVO.getmId() != null) {
			if (resultVO.getmId().substring(2, 10).equals("00000000")) {
				resultVO.setMenuLevel(1);
				resultVO.setmId("");
			} else if (resultVO.getmId().substring(4, 10).equals("000000")) {
				resultVO.setMenuLevel(2);
				resultVO.setmId(resultVO.getmId().substring(0, 2));
			} else if (resultVO.getmId().substring(6, 10).equals("0000")) {
				resultVO.setMenuLevel(3);
				resultVO.setmId(resultVO.getmId().substring(0, 4));
			} else if (resultVO.getmId().substring(8, 10).equals("00")) {
				resultVO.setMenuLevel(4);
				resultVO.setmId(resultVO.getmId().substring(0, 6));
			} else {
				resultVO.setMenuLevel(5);
				resultVO.setmId(resultVO.getmId().substring(0, 8));
			}
			return resultVO;
		} else {
			return null;
		}
	}

	/**
	 * 메뉴 수정처리
	 *
	 * @param updateVO
	 */
	public void updateMenu(MenuVO updateVO, HttpServletRequest request) {

		if (updateVO != null) {
			//메뉴가 게시판 타입이 아닐시 게시판 인덱스 초기화
			if (!"B".equals(updateVO.getMenuType())) {
				updateVO.setBbsMstIdx(0);
			}

			//메뉴가 기간설정이 아닐시 메뉴노출기간 초기화
			if (!"Y".equals(updateVO.getIsTermset())) {
				updateVO.setTermStartDt(null);
				updateVO.setTermEndDt(null);
			}

			if (updateVO.getIsFirst() == 1) {
				MenuVO searchVO = new MenuVO();
				searchVO.setmId(updateVO.getmId());
				searchVO.setSiteCode(updateVO.getSiteCode());
				searchVO = this.setInsertSearchMid(searchVO);
				searchVO.setMenuLevel(searchVO.getMenuLevel() - 1);

				int nextParentIdx = 0;
				if (searchVO.getMenuLevel() > 0) {
					nextParentIdx = menuMngMapper.getMenuNextParentIdx(searchVO);
				}
				updateVO.setParentIdx(nextParentIdx);

				/*menuMngMapper.updateSiblingsFirst(updateVO);*/
			}

			menuMngMapper.updateMenu(updateVO);

			//담당자 정보 저장
			if (request.getParameterValues("chargeId") != null && request.getParameterValues("chargeDepCode") != null && request.getParameterValues("chargeFnm") != null && request.getParameterValues("chargeTel") != null && request.getParameterValues("chargeTel") != null && request.getParameterValues("chargeDepNm") != null) {
				menuChargeMngMapper.setDeleteOld(new MenuChargeVO(updateVO.getSiteCode(), updateVO.getmId()));
				/************************* y-SmartCMS 컨텐츠 관리자 데이터 세팅 (2017.06.19 권태성) *************************/
				String ret = "";
				if("Y".equals(EgovProperties.getProperty("Globals.use.cms.yn"))) {
					ret = SmartCMS.setDeleteContentsAuth(updateVO.getSiteCode(), updateVO.getmId()); //CMS 담당자 정보 삭제
					if (!"success".equals(ret)) {
						LOGGER.error(">> y-SmartCMS 컨텐츠 관리자 등록 처리가 실패했습니다. / SITE_CODE : " + updateVO.getSiteCode() + ", mId : " + updateVO.getmId());
					}
				}
				/**********************************************************************************************************/
				for (int i = 0; i < request.getParameterValues("chargeId").length; i++) {
					menuChargeMngMapper.setInsertCharge(new MenuChargeVO(updateVO.getSiteCode(), updateVO.getmId(), request.getParameterValues("chargeId")[i], request.getParameterValues("chargeFnm")[i], request.getParameterValues("chargeDepCode")[i], request.getParameterValues("chargeDepNm")[i], request.getParameterValues("chargeTel")[i], Integer.parseInt(request.getParameterValues("sortNum")[i])));
					insertMenuChargeHistory(new MenuVO(updateVO.getSiteCode(), updateVO.getmId(), request.getParameterValues("chargeFnm")[i], request.getParameterValues("chargeId")[i], request.getParameterValues("chargeDepCode")[i], request.getParameterValues("chargeDepNm")[i]));
					/************************* y-SmartCMS 컨텐츠 관리자 데이터 세팅 (2017.06.19 권태성) *************************/
					if("Y".equals(EgovProperties.getProperty("Globals.use.cms.yn"))) {
						ret = SmartCMS.setContentsAuth(request.getParameterValues("chargeId")[i], updateVO.getSiteCode(), updateVO.getmId(), Boolean.TRUE); //y-SmartCMS 컨텐츠 관리자 등록 처리
						if (!"success".equals(ret)) {
							LOGGER.error(">> y-SmartCMS 컨텐츠 관리자 삭제 처리가 실패했습니다. / ID : " + request.getParameterValues("chargeId")[i] + ", SITE_CODE : " + updateVO.getSiteCode() + ", mId : " + updateVO.getmId());
						}
					}
					/**********************************************************************************************************/
				}
			} else {
				menuChargeMngMapper.setDeleteOld(new MenuChargeVO(updateVO.getSiteCode(), updateVO.getmId()));
				/************************* y-SmartCMS 컨텐츠 관리자 데이터 세팅 (2017.06.19 권태성) *************************/
				if("Y".equals(EgovProperties.getProperty("Globals.use.cms.yn"))) {
					String ret = SmartCMS.setDeleteContentsAuth(updateVO.getSiteCode(), updateVO.getmId()); //CMS 담당자 정보 삭제
					if (!"success".equals(ret)) {
						LOGGER.error(">> y-SmartCMS 컨텐츠 관리자 등록 처리가 실패했습니다. / SITE_CODE : " + updateVO.getSiteCode() + ", mId : " + updateVO.getmId());
					}
				}
			}
		}
	}

	/**
	 * 메뉴 삭제처리
	 *
	 * @param deleteVO
	 */
	public void deleteMenu(MenuVO deleteVO) {
		if (deleteVO != null) {
			MenuVO deleteSearchVO = this.getMenu(deleteVO);
			String deleteMid = this.getSearchMoveMid(deleteSearchVO.getMenuLevel(), deleteSearchVO.getmId());
			deleteVO.setmId(deleteMid);
			menuMngMapper.deleteMenu(deleteVO);
		}
	}

	/**
	 * 메뉴순서올림 처리
	 *
	 * @param searchVO
	 */
	public void moveMenuUp(MenuVO searchVO) {
		// 현재 메뉴
		MenuVO curMenuVO = this.getMenu(searchVO);

		String siteCode = curMenuVO.getSiteCode();
		String menuLevel = String.valueOf(curMenuVO.getMenuLevel());
		String parentIdx = String.valueOf(curMenuVO.getParentIdx());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("moveMenuOrderCode", curMenuVO.getMenuOrderCode());
		params.put("siteCode", siteCode);
		params.put("parentIdx", parentIdx);
		// 교체대상 메뉴
		MenuVO targetMenuVO = menuMngMapper.getUpMenu(params);

		params = new HashMap<String, String>();
		params.put("moveMenuOrderCode", targetMenuVO.getMenuOrderCode());
		params.put("siteCode", siteCode);
		params.put("moveMid", curMenuVO.getmId());
		// 선택한 메뉴를 한단계 위로 이동
		menuMngMapper.moveMenu(params);

		params.put("moveMenuOrderCode", curMenuVO.getMenuOrderCode());
		params.put("siteCode", siteCode);
		params.put("moveMid", targetMenuVO.getmId());
		// 선택한 메뉴의 위쪽에 있던 메뉴 아래로 이동
		menuMngMapper.moveMenu(params);

		params = new HashMap<String, String>();
		params.put("siteCode", siteCode);
		params.put("menuLevel", menuLevel);
		params.put("moveMenuOrderCode", targetMenuVO.getMenuOrderCode());
		params.put("curMid", curMenuVO.getmId());
		// 선택한 메뉴의 하위메뉴 이동
		menuMngMapper.moveMenuChildren(params);

		params.put("moveMenuOrderCode", curMenuVO.getMenuOrderCode());
		params.put("curMid", targetMenuVO.getmId());
		// 교체대상 메뉴의 하위메뉴 이동
		menuMngMapper.moveMenuChildren(params);

	}

	/**
	 * 메뉴순서내림 처리
	 *
	 * @param searchVO
	 */
	public void moveMenuDown(MenuVO searchVO) {
		// 현재 메뉴
		MenuVO curMenuVO = this.getMenu(searchVO);

		String siteCode = curMenuVO.getSiteCode();
		String menuLevel = String.valueOf(curMenuVO.getMenuLevel());
		String parentIdx = String.valueOf(curMenuVO.getParentIdx());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("moveMenuOrderCode", curMenuVO.getMenuOrderCode());
		params.put("siteCode", siteCode);
		params.put("parentIdx", parentIdx);
		// 교체대상 메뉴
		MenuVO targetMenuVO = menuMngMapper.getDownMenu(params);

		params = new HashMap<String, String>();
		params.put("moveMenuOrderCode", targetMenuVO.getMenuOrderCode());
		params.put("siteCode", siteCode);
		params.put("moveMid", curMenuVO.getmId());
		// 선택한 메뉴를 한단계 위로 이동
		menuMngMapper.moveMenu(params);

		params.put("moveMenuOrderCode", curMenuVO.getMenuOrderCode());
		params.put("siteCode", siteCode);
		params.put("moveMid", targetMenuVO.getmId());
		// 선택한 메뉴의 위쪽에 있던 메뉴 아래로 이동
		menuMngMapper.moveMenu(params);

		params = new HashMap<String, String>();
		params.put("siteCode", siteCode);
		params.put("menuLevel", menuLevel);
		params.put("moveMenuOrderCode", targetMenuVO.getMenuOrderCode());
		params.put("curMid", curMenuVO.getmId());
		// 선택한 메뉴의 하위메뉴 이동
		menuMngMapper.moveMenuChildren(params);

		params.put("moveMenuOrderCode", curMenuVO.getMenuOrderCode());
		params.put("curMid", targetMenuVO.getmId());
		// 교체대상 메뉴의 하위메뉴 이동
		menuMngMapper.moveMenuChildren(params);

	}

	public String getSearchMoveMid(int menuLevel, String mId) {
		String result = "";
		if (!"".equals(mId) && mId != null) {
			if (menuLevel == 1) {
				result = mId.substring(0, 2);
			} else if (menuLevel == 2) {
				result = mId.substring(0, 4);
			} else if (menuLevel == 3) {
				result = mId.substring(0, 6);
			} else if (menuLevel == 4) {
				result = mId.substring(0, 8);
			} else if (menuLevel == 5) {
				result = mId.substring(0, 10);
			}
		}
		return result;
	}

	public String getMoveDepth(int menuLevel) {
		if (menuLevel == 1) {
			return "0100000000";
		} else if (menuLevel == 2) {
			return "0001000000";
		} else if (menuLevel == 3) {
			return "0000010000";
		} else if (menuLevel == 4) {
			return "0000000100";
		} else if (menuLevel == 5) {
			return "0000000001";
		} else {
			return "";
		}
	}

	public boolean chkIsTop(MenuVO searchVO) {
		MenuVO currentMenuVO = this.getMenu(searchVO);
		String currentMenuOrderCode = currentMenuVO.getMenuOrderCode();
		MenuVO chkSearchVO = this.setMaxSearchMid(currentMenuVO);
		String minMenuOrderCode = menuMngMapper.getMinMenuOrderCode(chkSearchVO);
		if (currentMenuOrderCode.equals(minMenuOrderCode)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean chkIsBottom(MenuVO searchVO) {
		MenuVO currentMenuVO = this.getMenu(searchVO);
		String currentMenuOrderCode = currentMenuVO.getMenuOrderCode();
		MenuVO chkSearchVO = this.setMaxSearchMid(currentMenuVO);

		String maxMenuOrderCode = menuMngMapper.getMaxMenuOrderCode(chkSearchVO);
		if (currentMenuOrderCode.equals(maxMenuOrderCode)) {
			return true;
		} else {
			return false;
		}
	}

	public List<MenuVO> getMenuNotInBbsList(MenuVO searchVO) {
		return menuMngMapper.getMenuNotInBbsList(searchVO);
	}

	public int getMenuNextSeq() {
		return menuMngMapper.getMenuNextSeq();
	}

	public void insertMenuChargeHistory(MenuVO mVO) {
		MenusChargeHistoryMngVO mChargeHistoryVO = new MenusChargeHistoryMngVO();

		mChargeHistoryVO.setSiteCode(mVO.getSiteCode());
		mChargeHistoryVO.setMid(mVO.getmId());
		mChargeHistoryVO.setChargeId(mVO.getChargeId());
		mChargeHistoryVO.setChargeFnm(mVO.getChargeFnm());
		mChargeHistoryVO.setChargeDepCode(mVO.getChargeDepCode());
		mChargeHistoryVO.setChargeDepNm(mVO.getChargeDepNm());

		menuMngMapper.insertMenuChargeHistory(mChargeHistoryVO);

	}

	/**
	 * 해당 사이트의 메뉴 전체 삭제
	 *
	 * @Method Name : deleteMenuTargetSite
	 * @param siteCode
	 */
	public void deleteMenuTargetSite(String siteCode) {
		menuMngMapper.deleteMenuTargetSite(siteCode);
	}

	/*** 사용자단 ***/

	public List<MenuVO> getHierarchicalMenuList(String siteCode, String optionalQuery) {
		return getHierarchicalMenuList(1, 0, siteCode, optionalQuery);
	}

	public List<MenuVO> getHierarchicalMenuList(int rootDepthLevel, int rootIdx, String siteCode, String optionalQuery) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("siteCode", siteCode);
		conditions.put("optionalQuery", optionalQuery);

		List<MenuVO> sorted = hierarchicalSorting(rootDepthLevel, rootIdx, 0, menuMngMapper.getHierarchicalMenuList(conditions));
		combineAndSetMenuName(sorted, null, null, null);
		return sorted;
	}

	public List<MenuVO> hierarchicalSorting(int currentDepthLevel, int parentIdx, int menuListIdx, List<MenuVO> menuList) {
		List<MenuVO> sorted = new ArrayList<MenuVO>();
		menuList = menuList != null ? menuList : new ArrayList<MenuVO>();
		MenuVO menu = null;
		for (int i = menuListIdx; i < menuList.size(); i++) {
			menu = menuList.get(i);
			if (isCurrentDepthLevelMenu(parentIdx, currentDepthLevel, menu)) {
				menu.setSubMenu(hierarchicalSorting(currentDepthLevel + 1, menu.getIdx(), i, menuList));
				sorted.add(menu);
			}
		}

		return sorted;
	}

	public boolean isCurrentDepthLevelMenu(int parentIdx, int currentDepthLevel, MenuVO menu) {
		return menu.getParentIdx() == parentIdx && menu.getMenuLevel() == currentDepthLevel;
	}

	public void combineAndSetMenuName(List<MenuVO> menus, MenuVO parent, String pMenuName, String originName) {
		for (MenuVO menu : menus) {
			if (StringUtil.isBlank(originName) || menu.getMenuLevel() == 1) {
				originName = menu.getMenuName();
			}

			// Current Menu
			menu.setSubHeadTitle(StringUtil.isBlank(pMenuName) ? menu.getMenuName() : combineMenuName(menu.getMenuType(), menu.getMenuName(), pMenuName));
			menu.setSubHeadTitle2(reverse(menu.getSubHeadTitle()));
			menu.setSpotTitle(originName);
			menu.setParentMenu(parent);

			// Subordinate Menu
			if (menu.getSubMenu().size() > 0) {
				combineAndSetMenuName(menu.getSubMenu(), menu, combineMenuName(menu.getMenuType(), menu.getMenuName(), pMenuName), originName);
			}
		}
	}

	private String reverse(String subHeadTitle) {
		String[] titles = subHeadTitle.split(" > ");
		String titleReversed = null;
		for (int i = titles.length - 1; i > -1; i--) {
			if (StringUtil.isBlank(titleReversed)) {
				titleReversed = titles[i];
			} else {
				titleReversed += " | " + titles[i];
			}
		}

		return titleReversed;
	}

	private String combineMenuName(String menuType, String target, String add) {
		return StringUtil.isBlank(add) ? target : add + " > " + target;
	}

	public List<MenuVO> extractLastDepthMenu(List<MenuVO> menus) {
		for (MenuVO menu : menus) {
			if (menu.getSubMenu().size() > 0) {
				menu.setSubMenu(extractLastDepthMenu(menu.getSubMenu()));
			}
			menu.setLastMenu(extractLastDepthMenu(menu));
		}

		return menus;
	}

	public MenuVO extractLastDepthMenu(MenuVO menu) {
		if (menu.getMenuLevel() == 1) { // 하위 우선 노출 메뉴를 세팅하기 위해 헤더 메뉴의 경우는 따로 last depth menu를 찾지 않음
			return menu;
		}

		return menu.getSubMenu().size() > 0 ? extractLastDepthMenu(menu.getSubMenu().get(0)) : menu;
	}

	public MenuVO getActiveMenu(String mId, List<MenuVO> menus) {
		for (MenuVO menu : menus) {
			if (menu.getmId().equals(mId)) {
				return menu;
			} else if (menu.getmId().substring(0, menu.getMenuLevel() * 2).equals(mId.substring(0, menu.getMenuLevel() * 2)) && menu.getSubMenu().size() > 0) {
				return getActiveMenu(mId, menu.getSubMenu());
			}
		}

		return null;
	}

	public Map<String, Object> getMenuListMap(ModelMap model, String mId, String siteCode, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		MenuVO menuVO = getMenuInfo(mId, siteCode);
		resultMap.put("menuVO", menuVO);
		resultMap.put("curMid", menuVO != null ? menuVO.getmId() : mId);

		List<MenuVO> menus = getHierarchicalMenuList(siteCode, null);
		menus = extractLastDepthMenu(menus);
		resultMap.put("menus", menus);
		resultMap.put("menusSize", menus.size());

		return resultMap;
	}

	public MenuVO getMenuInfo(String mId, String siteCode) {
		MenuVO searchVO = new MenuVO();
		searchVO.setSiteCode(siteCode);

		MenuVO menuVO = new MenuVO();
		String reqMid = mId;
		String curMid = mId;

		if (StringUtil.isNotBlank(reqMid)) {
			MenuVO curMidSearchVO = getCurMidSearchVO(reqMid, siteCode);
			curMid = menuMngMapper.getCurMid(curMidSearchVO);
			searchVO.setmId(curMid);

			menuVO = menuMngMapper.getMenuVO(searchVO);
		}

		return menuVO;
	}

	public MenuVO getTmpMenuInfo(String mId, String siteCode) {
		MenuVO searchVO = new MenuVO();
		searchVO.setSiteCode(siteCode);

		MenuVO menuVO = new MenuVO();
		String reqMid = mId;
		// String curMid = mId;

		if (reqMid != null && !"".equals(reqMid)) {
			searchVO.setmId(mId);
			menuVO = menuMngMapper.getMenuVO(searchVO);
		}

		return menuVO;
	}

	protected MenuVO getCurMidSearchVO(String curMid, String siteCode) {
		MenuVO searchVO = new MenuVO();
		searchVO.setSiteCode(siteCode);

		if (curMid.substring(2, 10).equals("00000000")) {
			searchVO.setMenuLevel(1);
			searchVO.setmId(curMid.substring(0, 2));
		} else if (curMid.substring(4, 10).equals("000000")) {
			searchVO.setMenuLevel(2);
			searchVO.setmId(curMid.substring(0, 4));
		} else if (curMid.substring(6, 10).equals("0000")) {
			searchVO.setMenuLevel(3);
			searchVO.setmId(curMid.substring(0, 6));
		} else if (curMid.substring(8, 10).equals("00")) {
			searchVO.setMenuLevel(4);
			searchVO.setmId(curMid.substring(0, 8));
		} else {
			searchVO.setMenuLevel(5);
			searchVO.setmId(curMid.substring(0, 10));
		}

		return searchVO;
	}

	public String getSiteDeptCode(String siteCode) {
		return menuMngMapper.getSiteDeptCode(siteCode);
	}

	/**
	 * 유효한 MID인지 확인
	 *
	 * @Method Name : getAbailableMid
	 * @param paramMap
	 * @return
	 */
	public int getAbailableMid(MenuVO paramVO) {
		return menuMngMapper.getAbailableMid(paramVO);
	}

	/**
	 * 메뉴 담당자 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<MenuChargeVO> getMenuCharge(MenuChargeVO vo) {
		return menuChargeMngMapper.getMenuCharge(vo);
	}

	/**
	 * 현재 메뉴 하위에 사용 상태의 하위 메뉴가 있는지 확인
	 *
	 * @param paramVO
	 * @return
	 */
	public String getAvailableChildMid(MenuVO paramVO) {
		String tmp = menuMngMapper.getAvailableChildMid(paramVO);
		return TUtil.nullToBlank(tmp);
	}

	/**
	 * 해당 사이트의 그룹 코드를 조회
	 *
	 * @param siteCode
	 * @return
	 */
	public String getSiteGroup(String siteCode) {
		return menuMngMapper.getSiteGroup(siteCode);
	}

	public List<Map<String, Object>> getURLContentsList() {
		return menuMngMapper.getURLContentsList();
	}

	public String getURLContents(Map<String, Object> param) {
		return menuMngMapper.getURLContents(param);
	}

	public List<Map<String, Object>> getURLmenuList() {
		return menuMngMapper.getURLmenuList();
	}

	public String getURLmenu(Map<String, Object> param) {
		return menuMngMapper.getURLmenu(param);
	}

	/**
	 * 메뉴 담당자 히스트로 리스트 조회
	 *
	 * @return
	 */
	public List<MenusChargeHistoryMngVO> getMenuChargeHistoryList(MenuVO searchVO) {
		return menuMngMapper.getMenuChargeHistoryList(searchVO);
	}

}