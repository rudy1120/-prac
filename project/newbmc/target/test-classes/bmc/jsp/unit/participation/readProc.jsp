<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 조회 정보입력 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
 */
%>
<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<div id="contents"><div class="content">
<form:form commandName="participantVO" id="list" name="list" action="/bmc/participation/readResult.do?mId=${menuVO.mId}" onsubmit="return validator() && confirm('조회하시겠습니까?');" methodParam="post" enctype="multipart/form-data" htmlEscape="false">
<input type="hidden" name="usernm" value="${user.userName}"/>
<div class="bod_wrap">
	<fieldset>
	<div class="gap20"></div>
		<div class="bod_write">
			<dl>
				<dt><label for="usernm">이름</label></dt>
				<dd>
					<span class="spantxt">${user.userName}</span>
				</dd>
			</dl>
			
			<dl>
				<dt><label for="tel">휴대폰</label></dt>
				<dd>
					<form:input path="tel" maxlength="50" data-validator="on" data-inputType="phone" data-required="y" data-fieldName="휴대전화" />&nbsp;&nbsp;
					<span class="tip">01012345678 형식으로 입력해주세요.</span>
				</dd>
			</dl>
		</div>
	</fieldset>
</div>

<div class="btnall_wrap">
	<div class="btn_wrap">
		<div class="btn_in">
			<input id="submitBtn" type="submit" class="btn_blue" value="조회"/>
			<input type="button" class="btn-blue" onclick="location.href='/bmc/participation/list.do?mId=${menuVO.mId}'" value="취소"/>
		</div>
	</div>
</div>
</form:form></div></div>
 
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>