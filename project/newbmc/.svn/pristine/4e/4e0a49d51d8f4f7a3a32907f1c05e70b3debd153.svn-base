package egovframework.portal.common.aop;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.Constant;
import egovframework.portal.main.SiteCode;
import egovframework.portal.sys.basic.stateMng.service.StateSysService;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;

/**
 * 접속자 통계 관리
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.01.22		엄동건				최초 생성 및 코딩
 * 2017.01.05.		J.Ryeon Lee			시큐어 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 1. 5.
 */
@Component("logging")
public class Logger {

	@Autowired
	protected StateSysService stateSysService;

	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

	public void log(JoinPoint joinPoint) {
		if (joinPoint == null) {
			return;
		}

		try {
			HttpServletRequest request = null;
			Object[] args = joinPoint.getArgs();
			int len = args.length;
			for (int i = 0; i < len; i++) {
				if (args[i] instanceof HttpServletRequest) {
					request = (HttpServletRequest) args[i];
				}
			}

			if (request != null) {
				// 값 획득
				String year = TUtil.getToday("yyyy");
				String today = TUtil.getToday("yyyy-MM-dd");
				String uri = request.getRequestURI();
				String sessionId = (request.getSession()).getId();
				String referer = request.getHeader("REFERER"); // null or url http://61.85.36.154:8311/portal/calencar/list.do
				// String refererFull = request.getHeader("REFERER"); // 전체 경로 저장
				String userAgent = "기타";
				String userAgentFull = request.getHeader("User-Agent");
				String mId = request.getParameter("mId");
				String siteCode = "portal";

				// 조건별 처리
				// 접근대상이 로봇으로 의심되는 경우 제외
				if (userAgentFull.contains("bot") || userAgentFull.contains("http://")) {
					return;
				}

				// 관련 프로시저 자동 생성 쿼리 밀어넣으니 오류가 발생, 기생성해둔 한도 내에서 작동하도록 구현 2015-2019
				int yearMax = 2023;
				int curYear = Integer.parseInt(year);
				if (curYear > yearMax) {
					year = Integer.toString(yearMax);
				}

				// 20160810 J.Ryeon Lee 사이트 관련 정보 SiteCode.java로 이동
				String[] split_uri = uri.split("/");
				SiteCode _siteCode = SiteCode.toType(split_uri[1]);
				if (_siteCode != SiteCode.NONE && split_uri.length > 2) {
					siteCode = _siteCode.isTwoDepthBuiltIn() ? split_uri[2] : _siteCode.getCode();
				}

				// 특정 사이트 코드에 대한 처리
				if (siteCode.equals("sys")) {
					return; // 관리자 접속할 경우 로그 남길 필요없음
				} else if (siteCode.equals("main")) {
					siteCode = "portal"; // main단의 컨텐츠 view의 경우
				}

				// 내부에서 이동한 경우 저장하지 않도록..
				if (referer != null && referer.contains(request.getServerName())) {
					referer = null;
				}

				String refererAll = null; // 전체경로 저장

				if (referer != null) {
					refererAll = referer;
					Pattern urlPattern = Pattern.compile("^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$");
					Matcher mc = urlPattern.matcher(referer);
					if (mc.matches()) {
						// 외부유입 제외할 도메인 그룹
						ArrayList<String> skipReferer = new ArrayList<String>();
						String fullDomain = EgovProperties.getProperty(Constant.FULL_DOMAIN);
						skipReferer.add(fullDomain);
						skipReferer.add(fullDomain.replace("www.", ""));

						if (!skipReferer.contains(mc.group(2))) {
							referer = mc.group(2);
						} else {
							referer = null;
						}
					}
				}
				// 사용자 브라우저
				if (userAgentFull.indexOf("iPhone") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("iPod") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("BlackBerry") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("Android") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("Windows CE") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("LG") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("MOT") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("SAMSUNG") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("SonyEricsson") > -1) {
					userAgent = "mobile";
				} else if (userAgentFull.indexOf("Chrome") > -1) {
					userAgent = "Chrome";
				} else if (userAgentFull.indexOf("Firefox") > -1) {
					userAgent = "FireFox";
				} else if (userAgentFull.indexOf("Opera") > -1) {
					userAgent = "Opera";
				} else if (userAgentFull.indexOf("Safari") > -1) {
					userAgent = "Safari";
				} else if (userAgentFull.indexOf("MSIE 6") > -1) {
					userAgent = "IE6";
				} else if (userAgentFull.indexOf("MSIE 7") > -1) {
					userAgent = "IE7";
				} else if (userAgentFull.indexOf("MSIE 8") > -1) {
					userAgent = "IE8";
				} else if (userAgentFull.indexOf("MSIE 9") > -1) {
					userAgent = "IE9";
				} else if (userAgentFull.indexOf("MSIE 10") > -1) {
					userAgent = "IE10";
				} else if (userAgentFull.indexOf("Trident/7.0") > -1) {
					userAgent = "IE11";
				}

				// 로그 저장
				StateSysVO inputVo = new StateSysVO();
				inputVo.setYear(year); // 년도
				inputVo.setToday(today); // yyyy-MM-dd
				inputVo.setSiteCode(siteCode); // site
				inputVo.setMenuId(mId); // menu
				inputVo.setIp(request.getRemoteAddr()); // ip
				inputVo.setUserAgent(userAgent); // 브라우저
				inputVo.setUserAgentFull(userAgentFull); // 브라우저
				inputVo.setReferer(referer); // domain
				inputVo.setRefererFull(refererAll); // 전체경로 // referer가 남으므로 REFERER_FULL 제거 처리
				inputVo.setSessionId(sessionId); // session

				//20230517 주석처리
//				if (StringUtil.isBlank(mId) || mId.matches("^[0-9]{10}$")) { // 20170302 J.Ryeon Lee ADD mid가 없거나 적절할 때만 로그를 남김
//					stateSysService.procLogSite(inputVo);
//				}
//				if (StringUtil.isNotBlank(mId) && mId.matches("^[0-9]{10}$")) { // mid가 적절할 때만 로그를 남김
//					stateSysService.procLogMenu(inputVo);
//				}
				// mId 있는 경우에 호출
			}

		} catch (Exception e) {
			LOGGER.error(">> logger/log/Exception", e);
		}

	}
}