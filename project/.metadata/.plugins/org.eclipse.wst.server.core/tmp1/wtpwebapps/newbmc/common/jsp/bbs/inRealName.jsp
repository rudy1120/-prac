<%@page import="java.net.URLEncoder"%>
<%@page import="egovframework.portal.util.TUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.*,java.text.SimpleDateFormat" %>
<%@ page import = "java.util.*" %>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/common/jsp/component/authentication.jsp" %>
<%@ taglib uri="/tlds/SecuKeyPad.tld" prefix="secuKeypad"%>
<%@ taglib uri="/tlds/SecuKeypadMobile.tld" prefix="secuKeypadMobile"%>
<%
	response.setHeader("Pragma", "no-cache" );
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-store");
	response.setHeader("Cache-Control", "no-cache" );

	String remoteAddr = (String)request.getRemoteAddr();

	HttpSession s = request.getSession(true);
	s.removeAttribute("CheckRealName");
	s.removeAttribute("CheckRealCode");
	s.removeAttribute("CheckRealBirth");
	s.removeAttribute("successUrl");
	s.removeAttribute("cancelUrl");
	String successUrl = URLEncoder.encode(request.getParameter("successUrl"), "UTF-8");
	String cancelUrl = request.getParameter("cancelUrl");
	if (cancelUrl != null && "".equals(cancelUrl)) {
		cancelUrl = URLEncoder.encode(cancelUrl, "UTF-8");
	}

	Map<String, Object> map = new HashMap<String, Object>();
	map.put("formName","loginForm");
	map.put("inputPasswdName", "password");
	map.put("pcKeyPadLeftPos", "123");
	map.put("pcKeyPadTopPos", "100");
	map.put("mobileKeyPadLeftPos", "-43");
	map.put("mobileKeyPadTopPos", "-70");
%>
<%@include file="/common/jsp/ySecukeyPad/initSecukeyPad.jsp" %>
<jsp:include page="/portal/jsp/unit/user/mIds.jsp" />

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[
	function replaceAll(str, searchStr, replaceStr) {
		return str.split(searchStr).join(replaceStr);
	}

	function switchTabRealName(type){
		if (type == "1") {
			$("#realName2").removeClass("on");
			$("#realName3").removeClass("on");

			$("#realName1").removeClass("off");
			$("#realName1").addClass("on");

			$("#realContent1").removeClass("hide");
			$("#realContent2").addClass("hide");
			$("#realContent3").addClass("hide");
		} else if (type == "2") {
 			$("#realName1").removeClass("on");
 			$("#realName3").removeClass("on");

 			$("#realName2").removeClass("off");
 			$("#realName2").addClass("on");

 			$("#realContent2").removeClass("hide");
 			$("#realContent1").addClass("hide");
 			$("#realContent3").addClass("hide");
		} else {
			$("#realName1").removeClass("on");
			$("#realName2").removeClass("on");

			$("#realName3").addClass("on");

			$("#realContent3").removeClass("hide");
			$("#realContent1").addClass("hide");
			$("#realContent2").addClass("hide");
		}
	}

	/** 로그인 처리 */
	function fn_login_proc() {
		if (validator()) {
			$.ajax({
				type : "post", dataType: "json",
				url : "/portal/login?mId=${menuVO.mId}",
				data :$("#loginForm").serialize(),
				success : function (result) {
					if (result.success) {
						switch (result.procCode) {
							case "NOT_CERTIFICATED":
								alert("미인증된 회원입니다. 이용에 앞서 본인인증을 진행해주세요.");
								location.href = "/portal/contents.do?mId=" + mIds.modify;
								break;
							case "DORMANT_USER":
								alert("휴면 회원입니다. 이용에 앞서 본인인증을 진행해주세요.");
								location.href = "/portal/user/activation.do?mId=" + mIds.modify;
								break;
							case "EXPRIRED_PW":
								alert("비밀번호를 변경한 지 6개월이 경과했습니다. 비밀번호를 변경해주세요.");
								location.href = "/portal/contents.do?mId=" + mIds.modify;
								break;
							default:
								location.href = "${successUrl}" ? replaceAll(replaceAll(decodeURIComponent("${successUrl}"), "&amp;", "&"),"%26amp%3B", "&") : "/main.do";
								break;
						}

						return false;
					}

					alert("ID/비밀번호를 확인해주세요.");
					return false;
				}
			}).fail(function() {
				alert("서버와 통신 중 에러가 발생했습니다.");
			});
		}

		return false;
	}

	function go_prev_page() {
		var cancelUrl = "${cancelUrl}";
		if (cancelUrl) {
			location.href = cancelUrl;
		} else {
			location.href = "/${siteCodeFull}/contents.do?mId=${menuVO.mId}";
			// history.back();
		}
	}

//]]>
</script>
<c:set var="notUsableUserTab" value="${fn:indexOf(successUrl, 'user') > -1 && fn:indexOf(successUrl, 'join') > -1}" />
<div class="tab_depth05" id="realNameTab">
	<ul class="${notUsableUserTab ? 'list02' : 'list03'}">
		<li><a id="realName1" href="#" onclick="javascript:switchTabRealName('1'); return false;" class="on">본인인증</a>
			<div id="realContent1">
				<div class="uj_con_header">
					<img src="/common/img/content/bg_uj_confirm.png" alt="본인인증" height="98" width="99"/>
					<div>
						<h4>본인인증</h4>
						<form name="reqSIRENForm" id="frmRealName" title="본인인증" action="#" onsubmit="return false;" method="post">
							<div>
								<p>본인인증 후 이용이 가능합니다.</p>
								<p>개인정보는 본인확인 후 DB에는 저장되지 않습니다.</p>
								<div class="btn_wrap taR mT20">
									<input type="submit" onclick="open_namecheck('<%=successUrl %>');" value="본인확인" title="새창 열림" />
									<input type="button" onclick="go_prev_page(); return false;" class="cancel" value="취소" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</li>
		<li><a href="#" onClick="javascript:switchTabRealName('2'); return false;" class="off" id="realName2">공공 아이핀(I-PIN)인증</a>
			<div class="hide" id="realContent2">
				<div class="uj_con_header">
					<img src="/common/img/content/bg_uj_ipin.png" alt="공공아이핀 인증" height="98" width="99"/>
					<div>
						<h4>공공 아이핀(I-PIN)</h4>
						<p>공공 아이핀(I-PIN)은 인터넷상의 개인식별번호를 의미하며, 대면확인이 어려운 인터넷에서 주민등록번호를 사용하지 않고도 본인임을 확인할 수 있는 수단입니다. 공공아이핀은 공공아이핀 홈페이지에서 발급받을 수 있습니다.</p>
						<div class="btnboxC">
							<a href="http://www.gpin.go.kr" target="_blank" class="btn_white_blank" title="새창열림">공공아이핀 홈페이지로 이동</a>
							<input type="submit"  onclick="open_gpin('<%=successUrl %>');" class="btn_blue"  value="공공아이핀으로 인증하기"  title="새창으로 공공 아이핀(I-PIN)인증화면창 연결"/>
							<input type="button" onclick="go_prev_page(); return false;" class="ico_cancel" value="취소" />
						</div>
					</div>
				</div>
			</div>
		</li>
		<c:if test="${!notUsableUserTab}">
		<li><a href="#" onClick="switchTabRealName('3'); return false;" class="off" id="realName3">회원 로그인</a>
			<div class="hide" id="realContent3">
				<div class="uj_con_header">
					<img src="/common/img/content/bg_uj_login.png" alt="회원 로그인" height="98" width="99"/>
					<div>
						<h4>회원 로그인</h4>
						<p><span class="blue">의정부시 회원 계정이 있으신 분에 한해 이용하실 수 있습니다.</span></p>
						<p class="mT_im0">아이디와 비밀번호를 잊어버리신 경우는 의정부시 정보통신과(031-828-4912~3)로 문의해 주세요.</p>
					</div>
				</div>
				<form:form commandName="userInfoVO" name="loginForm" id="loginForm" onsubmit="fn_login_proc();">
					<input type="hidden" id="viewGubun" name="viewGubun" value=""/> <%-- y-SecuKeypad Hidden Object --%>
					<secuKeypad:SecuKeypadHidden tagParam="<%=ske %>" /> <%-- y-SecuKeypad Hidden Object --%>
					<secuKeypadMobile:SecuKeypadHidden tagParam="<%=skeMobile %>"/> <%-- y-SecuKeypad Mobile Hidden Object --%>
					<div class="uj_login_box">
						<div class="wrap_input mT10">
							<ul>
								<li><label for="userId">아이디</label><input type="text" name="userId" id="userId" data-validator="on" data-required="y" data-fieldName="아이디" placeholder="아이디 입력" /></li>
								<li><label for="password">비밀번호</label><input type="password" name="password" id="password" data-validator="on" data-required="y" data-fieldName="비밀번호" placeholder="비밀번호 입력" /></li>
							</ul>
							<div>
								<input type="submit" onclick="fn_login_proc(); return false;" class="btn_blue" value="로그인" />
							</div>
							<div class="keypad_box">
								<label for="<%=inputPasswdUseYnName %>">가상키패드<br/>사용여부</label><input type="checkbox" id="<%=inputPasswdUseYnName %>" name="<%=inputPasswdUseYnName %>" value="Y" />
							</div>
						</div>
						<%-- y-SecuKeypad 키패드 출력 { ---%>
						<div id="DIV_SECU_KEYPAD">
							<secuKeypad:SecuKeypadMap tagParam="<%=ske %>" />
						</div>
						<%-- y-SecuKeypad 키패드 출력 } ---%>
						<%-- y-SecuKeypad Mobile 키패드 출력 { ---%>
						<div id="DIV_SECU_KEYPAD_MOBILE" style="position:absolute;margin:0 auto;">
							<secuKeypadMobile:SecuKeypadMap tagParam="<%=skeMobile %>"/>
						</div>
						<%-- y-SecuKeypad Mobile 키패드 출력 } ---%>
						<div class="wrap_search">
							<p>아이디와 비밀번호를 잊어버리셨나요?</p>
							<div class="btnboxC mT10">
								<a href="/portal/contents.do?mId=0701040000" class="btn_white_blank">통합계정찾기페이지로 이동</a>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</li>
		</c:if>
	</ul>
</div>
