<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ taglib uri="/tlds/SecuKeyPad.tld" prefix="secuKeypad"%>
<%@ taglib uri="/tlds/SecuKeypadMobile.tld" prefix="secuKeypadMobile"%>
<%--
*********************************************************************
* 파  일  명 : boardPwd.jsp
* 설      명 : 비밀번호 작성창
* 작  성  자 : 김혜민
* 작  성  일 : 2014.11.10
* -------------------------------------------------------------------
* 수정일			작업자				작업내용
* --------------	----------------	-----------------------------
* 2016.08.26		권태성				모바일 가상키패드 적용
* 2016.08.26		권태성				가상키패드 초기화부분 분리
* 2017.06.28		권태성				가상키패드 사용옵션 적용
*********************************************************************
--%>
<% Map<String, Object> map = new HashMap<String, Object>();
map.put("mobileKeyPadLeftPos", "20");
map.put("mobileKeyPadTopPos", "0");%>
<%@include file="../ySecukeyPad/initSecukeyPad.jsp" %>

<script type="text/javascript">
//<![CDATA[

	var loadUrl = "";

	$(document).ready(function() {
		if( "${searchVO.bbsMode}"== "edit" ){
			loadUrl = "/${siteCode}/bbs/modifyView.do?bIdx=${searchVO.bIdx}&ptIdx=${searchVO.ptIdx}&mId=${menuVO.mId}";
		}else if( "${searchVO.bbsMode}" == "delete" ){
			loadUrl = "/${siteCode}/bbs/deleteProc.do?bIdx=${searchVO.bIdx}&ptIdx=${searchVO.ptIdx}&mId=${menuVO.mId}";
		}

	});

	function goPwdProc(ptIdx, bIdx) {
		if (jQuery("#<%=inputPasswdName%>").val()) {
			document.getElementById("<%=formName%>").submit();
		} else {
			alert("비밀번호를 입력하세요.");
			jQuery("#<%=inputPasswdName%>").focus();
			return false;
		}
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


<!-- 비밀번호 확인 -->
<h4 class="mT_im0">비밀번호 확인</h4>
<%-- <form:form commandName="searchVO" name="<%=formName%>" id="<%=formName%>" action="javascript:goPwdProc('${searchVO.ptIdx}', '${searchVO.bIdx}');" methodParam="post"> --%>
<form:form commandName="searchVO" name="<%=formName%>" id="<%=formName%>" action="/${siteCodeFull}/bbs/boardPwdProc.do?mId=${menuVO.mId}" methodParam="post">
<form:hidden path="ptIdx" />
<form:hidden path="bIdx" />
<form:hidden path="bbsMode" />
<form:hidden path="searchType" />
<form:hidden path="searchTxt" />
<form:hidden path="searchCategory" />
<form:hidden path="searchDept" />
<form:hidden path="page" />
<form:hidden path="siteCode" />

<input type="hidden" id="viewGubun" name="viewGubun" value=""/> <%-- y-SecuKeypad Hidden Object --%>
<c:if test="${yh:getProperty('Globals.secukeypad') eq 'Y' }"><secuKeypad:SecuKeypadHidden tagParam="<%=ske %>" /><secuKeypadMobile:SecuKeypadHidden tagParam="<%=skeMobile %>"/></c:if>

	<div class="password_box">
		<div class="input_area">
			<p>※ 작성하실 때 입력하신 비밀번호를 입력해 주세요.</p>

			<fieldset>
				<legend class="hidden">비밀번호입력</legend>

				<div class="keypadWrap">
					<label for="<%=inputPasswdName%>" class="hidden">비밀번호 </label>
					<input type="password" name="<%=inputPasswdName%>" id="<%=inputPasswdName%>" maxlength="50" title="비밀번호를 입력하세요" />
					<c:if test="${yh:getProperty('Globals.secukeypad') eq 'Y' }">
						<div id="DIV_SECU_KEYPAD">
							<secuKeypad:SecuKeypadMap tagParam="<%=ske %>" />
						</div>
						<div id="DIV_SECU_KEYPAD_MOBILE" style="position:absolute;margin:0 auto;">
							<secuKeypadMobile:SecuKeypadMap tagParam="<%=skeMobile %>" />
						</div>
					</c:if>
				</div>
				<c:if test="${yh:getProperty('Globals.secukeypad') eq 'Y' }">
					<div class="mT20">
						<label for="<%=inputPasswdUseYnName%>">가상키패드 사용여부</label>
						<input type="checkbox" id="<%=inputPasswdUseYnName%>" name="<%=inputPasswdUseYnName%>" value="Y" />
					</div>
				</c:if>
			</fieldset>
		</div>
		<!-- 버튼영역 -->
		<div class="btnboxC">
			<a href="#" onclick="goPwdProc('${searchVO.ptIdx}', '${searchVO.bIdx}'); return false;" class="btn_blue">확인</a>
			<a href="#" onclick="go_prev_page(); return false;" class="btn_white">취소</a>
		</div>
	</div>
</form:form>
