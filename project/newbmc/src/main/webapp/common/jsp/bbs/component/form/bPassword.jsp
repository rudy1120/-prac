<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/tlds/SecuKeyPad.tld" prefix="secuKeypad"%>
<%@taglib uri="/tlds/SecuKeypadMobile.tld" prefix="secuKeypadMobile"%>
<%@include file="/common/jsp/ySecukeyPad/initSecukeyPad.jsp" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<input type="hidden" id="viewGubun" name="viewGubun" value=""/>
<c:if test="${yh:getProperty('Globals.secukeypad') eq 'Y' }"><secuKeypad:SecuKeypadHidden tagParam="<%=ske %>" /><secuKeypadMobile:SecuKeypadHidden tagParam="<%=skeMobile %>" /></c:if>

<%-- ============================== 비밀번호 ============================== --%>
<dl class="pwWrap">
	<dt><span class="red" title="필수입력">*</span><label for="<%=inputPasswdName%>">비밀번호</label></dt>
	<dd>
		<input type="password" id="<%=inputPasswdName%>" name="<%=inputPasswdName%>" maxlength="50" data-validator="on" data-required="y" data-fieldName="비밀번호"/>
		<c:if test="${yh:getProperty('Globals.secukeypad') eq 'Y' }">
			<div id="DIV_SECU_KEYPAD">
				<secuKeypad:SecuKeypadMap tagParam="<%=ske %>"/>
			</div>
			<div id="DIV_SECU_KEYPAD_MOBILE" style="position:absolute;margin:0 auto;">
				<secuKeypadMobile:SecuKeypadMap tagParam="<%=skeMobile %>"/>
			</div>
			<div>
				<label for="<%=inputPasswdUseYnName%>">가상키패드 사용여부</label>
				<input type="checkbox" id="<%=inputPasswdUseYnName%>" name="<%=inputPasswdUseYnName%>" value="Y"/>
			</div>
		</c:if>
		<span class="orange">※ 수정/삭제시 필요하오니 반드시 기억하시기 바랍니다.</span>
	</dd>
</dl>
