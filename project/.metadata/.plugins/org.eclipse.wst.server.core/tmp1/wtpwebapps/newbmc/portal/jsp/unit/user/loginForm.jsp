<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib uri="/tlds/SecuKeyPad.tld" prefix="secuKeypad"%>
<%@ taglib uri="/tlds/SecuKeypadMobile.tld" prefix="secuKeypadMobile"%>
<%
	/**
	 * 회원 로그인폼
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.12.24		J.Ryeon Lee		최초 생성 및 코드 작성
	 * 2017.02.17		권태성			로그인시 포털 메인으로 이동하도록 수정
	 * 2019.06.03		김선영			마크업수정
	 * @author J.Ryeon Lee
	 * @since 2016.12.24
	 */
%>
<%
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("formName","form");
	map.put("inputPasswdName", "password");
	map.put("pcKeyPadLeftPos", "50");
	map.put("pcKeyPadTopPos", "15");
	map.put("mobileKeyPadLeftPos", "-43");
	map.put("mobileKeyPadTopPos", "-40");
%>
<%@include file="/common/jsp/ySecukeyPad/initSecukeyPad.jsp" %>

<jsp:include page="./mIds.jsp"/>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[

	function fn_login_proc() {
		if (validator("#form")) {
			$.ajax({
				type : "post", dataType: "json", url : "/portal/login", data : $("#form").serialize(),
				success : function (result) {
					if (result.success) {
						switch (result.procCode) {
							case "NOT_CERTIFICATED":
								alert("미인증된 회원입니다. 이용에 앞서 본인인증을 진행해주세요.");
								location.href = "/portal/user/certification.do?mId=" + mIds.modify;
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
								location.href = $("#rtnUrl").val() ? $("#rtnUrl").val() : "/portal/main.do";
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

//]]>
</script>
<div class="bod_guide">
	<ul>
		<li>회원 계정이 있으신 분에 한해 이용하실 수 있습니다.</li>
		<li>최종 로그인 후 2년이 지난 계정은 자동적으로 탈퇴 처리됩니다.</li>
		<li>아이디와 비밀번호를 잊어버리신 경우는 페이지 하단 담당자에게 문의해 주세요.</li>
	</ul>
</div>
<form:form commandName="userVO" name="form" id="form" onsubmit="fn_login_proc();" methodParam="post">
	<input name="TOKEN_KEY" type="hidden" value="<%=request.getAttribute("TOKEN_KEY")%>"/><%-- 중복방지 Token Parameter --%>
	<input type="hidden" id="viewGubun" name="viewGubun" value=""/> <%-- y-SecuKeypad Hidden Object --%>
	<secuKeypad:SecuKeypadHidden tagParam="<%=ske %>"/> <%-- y-SecuKeypad Hidden Object --%>
	<secuKeypadMobile:SecuKeypadHidden tagParam="<%=skeMobile %>"/> <%-- y-SecuKeypad Mobile Hidden Object --%>
	<fieldset form="form">
		<legend>회원로그인</legend>
		<div class="uj_login_box clFix">
			<div>
				<ul>
					<li><label for="userId">아이디</label><input type="text" id="userId" name="userId" maxlength="30" data-validator="on" data-required="y" data-fieldName="아이디" placeholder="아이디 입력"/></li>
					<li><label for="password">비밀번호</label><input type="password" id="password" name="password" maxlength="15" data-validator="on" data-required="y" data-fieldName="비밀번호" placeholder="비밀번호 입력"/></li>
				</ul>
				<input type="hidden" id="rtnUrl" name="rtnUrl" value="<%=ServletRequestUtils.getStringParameter(request, "successUrl", "")%>"/>
				<input type="submit" onclick="fn_login_proc(); return false;" class="btn_login" value="로그인"/>
				<div class="keypad_box mT20">
					<input type="checkbox" id="<%=inputPasswdUseYnName %>" name="<%=inputPasswdUseYnName %>" value="Y"/><label for="<%=inputPasswdUseYnName %>">가상키패드 사용여부</label>
				</div>
				<%-- y-SecuKeypad 키패드 출력 { ---%>
				<div id="DIV_SECU_KEYPAD">
					<secuKeypad:SecuKeypadMap tagParam="<%=ske %>"/>
				</div>
				<%-- y-SecuKeypad 키패드 출력 } ---%>
				<%-- y-SecuKeypad Mobile 키패드 출력 { ---%>
				<div id="DIV_SECU_KEYPAD_MOBILE" style="position:absolute;margin:0 auto;">
					<secuKeypadMobile:SecuKeypadMap tagParam="<%=skeMobile %>"/>
				</div>
				<%-- y-SecuKeypad Mobile 키패드 출력 } ---%>
			</div>
			<div>
				<p>아이디와 비밀번호를 잊어버리셨나요?</p>
				<a href="#" onclick="location.href = '/portal/contents.do?mId=' + mIds.find;" class="btn">통합계정찾기페이지로 이동</a>
			</div>
		</div>
	</fieldset>
</form:form>