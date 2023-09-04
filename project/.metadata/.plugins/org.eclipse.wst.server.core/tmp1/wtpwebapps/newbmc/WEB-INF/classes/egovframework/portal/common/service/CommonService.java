package egovframework.portal.common.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

/**
 * 공용기능
 *
 * <pre>
 * &lt;&lt;개정이력(Modification Information)&gt;&gt;
 * 2014.10.10 엄동건
 * 최초 생성
 * </pre>
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.10.10		엄동건				최초 생성 및 코딩
 * 2017.05.31		J.Ryeon Lee			공통 처리 AOP 호출 분기 (redirect:/를 주로 쓰는 controller가 아닌 이상 해당 서비스의 코드는 기본적으로 정해진 AOP에서만 호출해주세요.)
 * </pre>
 *
 * @author 엄동건
 * @since 2014.10.10
 * @version 1.0
 * @see egovframework.portal.aop.MenuFetcher
 */
public interface CommonService {

	/** 공통 컨텐츠 로딩 */
	public ModelMap commonDataCreater(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException;

	/** 현재 메뉴 정보 세팅 (메뉴 세팅 후 sub title 정보를 변경하는 경우 호출) */
	public void addActiveMenuTo(ModelMap model, HttpServletRequest request, String mId) throws UnsupportedEncodingException;

}