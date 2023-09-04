<%@page import="egovframework.com.cmm.service.EgovProperties"%>
<%@page import="egovframework.rte.fdl.string.EgovStringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="/tlds/SecuKeyPad.tld" prefix="secuKeypad"%>
<%@ taglib uri="/tlds/SecuKeypadMobile.tld" prefix="secuKeypadMobile"%>
<%@ page import="com.yhdb.solution.secukeypad.pcweb.*" %>
<%@ page import="com.yhdb.solution.secukeypad.mweb.*"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파  일  명 : initSecukeyPad.jsp
* 설      명 : 가상 키패드 초기화
* 작  성  자 : 권태성
* 작  성  일 : 2016.08.26
* -------------------------------------------------------------------
* 수정일			작업자				작업내용
* --------------	----------------	-----------------------------
* 2016.08.26		권태성				PC/Mobile 가상키패드 적용
* 2017.06.28		권태성				가상키패드 사용옵션 적용
*********************************************************************
--%>
<%

String formName = nullToBase(map.get("formName"), "detailForm");							// 입력폼 이름
String inputPasswdName = nullToBase(map.get("inputPasswdName"), "bPassword");					// 키패드 입력 태그 이름
String inputPasswdUseYnName = nullToBase(map.get("inputPasswdUseYnName"), "loginPasswdInputUseYn");	// 키패드 사용여부 태그 이름
String pcKeyPadLeftPos = nullToBase(map.get("pcKeyPadLeftPos"), "12");							//	PC용 키패드 left 위치
String pcKeyPadTopPos = nullToBase(map.get("pcKeyPadTopPos"), "41");							// PC용 키패드 Top 위치
String mobileKeyPadLeftPos = nullToBase(map.get("mobileKeyPadLeftPos"), "-90");						// Mobile용 키패드 left 위치
String mobileKeyPadTopPos = nullToBase(map.get("mobileKeyPadTopPos"), "0");						// Mobile용 키패드 Top 위치

String secukeyPadYN = EgovProperties.getProperty("Globals.secukeypad");
SecuKeypadEncoder ske = null;
MSecuKeypadEncoder skeMobile = null;

if ("Y".equals(secukeyPadYN)) {
	ske = new SecuKeypadEncoder(request, response, getServletContext());

	try {
		/* PC용 가상키패드 설정 */
		ske.addSecuKeypad(SecuKeypadConstant.SECU_KEYPAD_TYPE_ALPHABET, formName);
		ske.setInputForm(SecuKeypadConstant.SECU_KEYPAD_TYPE_ALPHABET, new String[]{inputPasswdName});
		ske.setInputUseYN(SecuKeypadConstant.SECU_KEYPAD_TYPE_ALPHABET, SecuKeypadConstant.USEYN_TYPE_CHECKBOX_POP, inputPasswdUseYnName);

		HashMap<Object, Object> params = new HashMap<Object, Object>();

		// 가상키패드 이미지 경로 설정
		params.put(SecuKeypadConstant.DEFAULT_IMAGE_PATH, request.getContextPath() + "/common/img/ySecuKeypad/pc");

		// 가상키패드 보일지 여부(기본값 : DISPLAY_HIDE)
		params.put(SecuKeypadConstant.SECU_KEYPAD_DISPLAY_TYPE, SecuKeypadConstant.DISPLAY_HIDE);

		// 가상키패드 표시를 위한 DIV 태그 설정
		params.put(SecuKeypadConstant.SECU_KEYPAD_DIVISION_NAME, "DIV_SECU_KEYPAD");

		// 닫기 or 확인 버튼 클릭 시 다음 이동할 focus 객체 설정
		params.put(SecuKeypadConstant.SECU_KEYPAD_NEXT_FOCUS_NAME, inputPasswdUseYnName);

		// Ajax Mode 실행(기본값 : SECU_KEYPAD_FALSE) : 키패드 스크립트 오류가 발생하면 설정
		//params.put(SecuKeypadConstant.SECU_KEYPAD_AJAX_MODE, SecuKeypadConstant.SECU_KEYPAD_TRUE);

		// 문자 가상키패드 위치(기본값 : -20)
		params.put(SecuKeypadConstant.SECU_KEYPAD_LEFT_POS, pcKeyPadLeftPos);

		// 문자 가상키패드 Top 위치(기본값 : 100)
		params.put(SecuKeypadConstant.SECU_KEYPAD_TOP_POS, pcKeyPadTopPos);

		ske.init(params);
	} catch(SecuKeypadException e) {
		System.out.println("SecuKeypadException occurred");
	}
	skeMobile = new MSecuKeypadEncoder(request, response, getServletContext());
	try {
		//Mobile용 가상키패드 설정
		skeMobile.addSecuKeypad(MSecuKeypadConstant.SECU_KEYPAD_TYPE_ALPHABET, formName);
		skeMobile.setInputForm(MSecuKeypadConstant.SECU_KEYPAD_TYPE_ALPHABET, new String[]{inputPasswdName});
		skeMobile.setInputUseYN(MSecuKeypadConstant.SECU_KEYPAD_TYPE_ALPHABET, MSecuKeypadConstant.USEYN_TYPE_CHECKBOX_POP, inputPasswdUseYnName);

		HashMap<Object, Object> paramsMobile = new HashMap<Object, Object>();

		// 가상키패드 보일지 여부(기본값 : DISPLAY_HIDE)
		paramsMobile.put(MSecuKeypadConstant.SECU_KEYPAD_DISPLAY_TYPE, MSecuKeypadConstant.DISPLAY_HIDE);

		// 가상키패드 기본 이미지 경로
		paramsMobile.put(MSecuKeypadConstant.DEFAULT_IMAGE_PATH, request.getContextPath() + "/common/img/ySecuKeypad/mobile");

		// 가상키패드 표시를 위한 DIV 태그 설정
		paramsMobile.put(MSecuKeypadConstant.SECU_KEYPAD_DIVISION_NAME, "DIV_SECU_KEYPAD_MOBILE");

		// 닫기 or 확인 버튼 클릭 시 다음 이동할 focus 객체 설정
		paramsMobile.put(MSecuKeypadConstant.SECU_KEYPAD_NEXT_FOCUS_NAME, inputPasswdUseYnName);

		// Ajax Mode 실행(기본값 : SECU_KEYPAD_FALSE) : 키패드 스크립트 오류가 발생하면 설정
		//paramsMobile.put(SecuKeypadConstant.SECU_KEYPAD_AJAX_MODE, SecuKeypadConstant.SECU_KEYPAD_TRUE);

		// 문자 가상키패드 위치(기본값 : -20)
		paramsMobile.put(MSecuKeypadConstant.SECU_KEYPAD_LEFT_POS, mobileKeyPadLeftPos);

		// 문자 가상키패드 Top 위치(기본값 : 0)
		paramsMobile.put(MSecuKeypadConstant.SECU_KEYPAD_TOP_POS, mobileKeyPadTopPos);

		skeMobile.init(paramsMobile);
	} catch(MSecuKeypadException e) {
		System.out.println("MSecuKeypadException occurred");
	}
}
%>
<c:if test="${yh:getProperty('Globals.secukeypad') eq 'Y' }">
	<secuKeypad:SecuKeyPadScript tagParam="<%=ske %>" /> <%-- y-SecuKeypad Javascript --%>
	<secuKeypadMobile:SecuKeyPadScript tagParam="<%=skeMobile %>"/> <%-- y-SecuKeypad Mobile Javascript --%>

	<script type="text/javascript">
	//<![CDATA[
		jQuery(document).ready(function() {

			var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);

			var pcKeypad = document.getElementById("DIV_SECU_KEYPAD");
			var mobileKeypad = document.getElementById("DIV_SECU_KEYPAD_MOBILE");
			var formName = document.<%=formName%>;

			if(w > 462) {
				formName.viewGubun.value = "pc";
				pcKeypad.style.visibility = "";
				mobileKeypad.style.visibility = "hidden";

				$("#<%=inputPasswdUseYnName%>").click(function() {
					vSecuKpd.hideAll();
					if($('input[name=<%=inputPasswdUseYnName%>]').is(':checked')) {
						 $("#<%=inputPasswdName%>").attr("readonly", true);
					} else {
						$("#<%=inputPasswdName%>").removeAttr("readonly");
					}
				});
			} else {
				formName.viewGubun.value = "mobile";
				pcKeypad.style.visibility = "hidden";
				mobileKeypad.style.visibility = "";

				$("#<%=inputPasswdUseYnName%>").click(function() {
					vSecuKpdMobile.hideAll();
					if($('input[name=<%=inputPasswdUseYnName%>]').is(':checked')) {
						 $("#<%=inputPasswdName%>").attr("readonly", true);
					} else {
						$("#<%=inputPasswdName%>").removeAttr("readonly");
					}
				});
			}

		});

		window.onresize = function() {
			vSecuKpd.hideAll();
			vSecuKpdMobile.hideAll();

			var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);

			var pcKeypad = document.getElementById("DIV_SECU_KEYPAD");
			var mobileKeypad = document.getElementById("DIV_SECU_KEYPAD_MOBILE");
			var formName = document.<%=formName%>;
			if(w > 462) {
				formName.viewGubun.value = "pc";
				pcKeypad.style.visibility = "";
				mobileKeypad.style.visibility = "hidden";

				$("#<%=inputPasswdUseYnName%>").click(function() {
					vSecuKpd.hideAll();
					if($('input[name=<%=inputPasswdUseYnName%>]').is(':checked')) {
						 $("#<%=inputPasswdName%>").attr("readonly", true);
					} else {
						$("#<%=inputPasswdName%>").removeAttr("readonly");
					}
				});
			} else {
				formName.viewGubun.value = "mobile";
				pcKeypad.style.visibility = "hidden";
				mobileKeypad.style.visibility = "";

				$("#<%=inputPasswdUseYnName%>").click(function() {
					vSecuKpdMobile.hideAll();
					if($('input[name=<%=inputPasswdUseYnName%>]').is(':checked')) {
						 $("#<%=inputPasswdName%>").attr("readonly", true);
					} else {
						$("#<%=inputPasswdName%>").removeAttr("readonly");
					}
				});
			}
		}
	//]]>
	</script>
</c:if>

<%!
	public String nullToBase(Object obj, String base){

		if(obj != null){
			return obj.toString();
		}else{
			return base;
		}

	}
%>