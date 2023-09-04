<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<script>
if (document.location.protocol == 'http:') {
    document.location.href = document.location.href.replace('http:', 'https:');
}
</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<div id="contents">
	<div class="content">
		<div class="gap20"></div>
		
		<div class="subSec cpProfile clearfix">
			<div class="ceoInfo aniBox active">
				<div class="txtBox">
					<strong>
						빠르고 편리한 부산도시공사의 임대료 개인별 수납내역 조회
					</strong>
				</div>	
			</div>
		</div>
		<div class="gap"></div>
		<p>본 서비스를 통해 조회하는 임대료 수납내역은 전월 기준의 데이터입니다.</p>
		
		<form:form commandName="lookCustomPayVO" id="list" name="list" action="/bmc/look/receiptResult.do?mId=${menuVO.mId}" onsubmit="return validator() && confirm('조회하시겠습니까?');" methodParam="post" htmlEscape="false">
			<input type="hidden" name="custNm" value="${user.userName}"/>
			<input type="hidden" name="regiNo" value="${fn:substring(user.birthday, 2, 8)}"/>
			<input type="hidden" name="phonNo" value="${user.tel1}-${user.tel2}-${user.tel3}"/>
			<!-- 차후 핸드폰번호 인증으로 변경 -->
		
		<c:choose>
			<c:when test="${not empty user}">
			
			<div class="gap20"></div>
			<p class="depth2-title">임대료 수납내역 조회신청 정보</p>
				<!-- 본인인증 정보존재 -->
				<div class="bod_wrap">
					<fieldset>
						<div class="bod_write">
							<dl>
								<dt><label for="name">이름</label></dt>
								<dd>
									<span class="spantxt">${user.userName}</span>
									<%-- <form:input path="name" maxlength="50" data-validator="on" data-required="y" data-fieldName="이름"/> --%>
								</dd>
							</dl>
							<dl>
								<dt><label for="juminId">생년월일</label></dt>
								<dd>
								<span class="spantxt">${fn:substring(user.birthday, 2, 8)}</span> 
									<%-- <form:input path="juminId" maxlength="50" data-validator="on" data-inputType="birth" data-required="y" data-fieldName="생년월일" />&nbsp;&nbsp;
									<span class="tip">앞자리 6자리를 입력해주세요.</span> --%>
								</dd>
							</dl>
							<dl>
								<dt><label for="tel">휴대폰번호</label></dt>
								<dd>
									<span class="spantxt">${user.tel1}-${user.tel2}-${user.tel3}</span> 
									<%-- <form:input path="tel" maxlength="50" data-validator="on" data-inputType="tel" data-required="y" data-fieldName="휴대폰번호" />&nbsp;&nbsp;
									<span class="tip">010-1234-1234 형식으로 입력해주세요.</span> --%>
								</dd>
							</dl>
							<dl>
								<dt><label for="tel">검색일자</label></dt>
								<dd>
									<form:input path="schSdate" id="schSdate" cssClass="input100" data-isMinDateFor="schSdate" data-date="y" data-validator="on" autocomplete="off" /> ~
									<form:input path="schEdate" id="schEdate" cssClass="input100" data-isMaxDateFor="schEdate" data-date="y" data-validator="on" autocomplete="off" />
								</dd>
							</dl>
						</div>
					</fieldset>
				</div>
				
				<div class="btnall_wrap">
					<div class="btn_wrap">
						<div class="btn_in">
							<input id="submitBtn" type="submit" class="btn_blue" value="조회"/>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<!-- 본인인증 정보없음 -->
				<div class="btn_wrap btn_wrap2">
					<a href="/bmc/user/inRealName.do?successUrl=/bmc/look/receiptList.do&mId=${menuVO.mId}&sAuthType=M" onclick="req.post(this); return false;">임대료 수납내역 조회 화면 이동</a>
				</div>
			</c:otherwise>
		</c:choose>
		
		</form:form>

	</div>
</div>

<link rel="stylesheet" type="text/css" href="/common/css/jquery-ui.1.12.1.min.css"/>
<script type="text/javascript" src="/common/js/jquery/jquery-ui-1.10.0.custom.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>

<script>
	jQuery(document).ready(function(){
		/*dp.bind(jQuery("[data-date=y]"), "yy-mm-dd");*/
		
		var sDate = new Date().getFullYear()+"-01-01";
		var eDate = new Date();

		$("#schSdate").datepicker({ dateFormat: 'yy-mm-dd' });
		$("#schEdate").datepicker({ dateFormat: 'yy-mm-dd' });
		$("#schSdate").datepicker("setDate", sDate);
		$("#schEdate").datepicker("setDate", eDate);

	});
</script>