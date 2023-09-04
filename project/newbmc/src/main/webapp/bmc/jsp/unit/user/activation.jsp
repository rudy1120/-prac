<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/common/jsp/component/authentication.jsp" %>
<%
	/**
	 * 휴면 회원 활성화 페이지
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.02.09		J.Ryeon Lee		최초 생성 및 코딩
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.10.28
	 */
%>
<jsp:include page="./mIds.jsp"/>

<div class="board_head">
	<span class="ico_basic"></span>
	<p class="head_tit">회원 본인 인증 안내</p>
	<p>귀하께서는 1년 이상 장기 미로그인으로 휴면 처리된 회원입니다.</p>
	<ul>
		<li>본인 인증 후 회원 정보 관리 및 회원 탈퇴, 게시판 글쓰기 등 본 사이트를 이용하실 수 있습니다.</li>
		<li>휴대폰 및 공공 아이핀(I-PIN)을 통해 본인 인증 절차를 완료하실 수 있습니다.</li>
		<li>정보통신과: 031-828-4912~3</li>
	</ul>
</div>

<form:form commandName="userVO" name="form" id="form" methodParam="post">
	<div class="cal_form  mT20">
		<dl>
			<dt>회원ID</dt>
			<dd>${userVO.userId}</dd>
		</dl>
		<dl>
			<dt>성명</dd>
			<dd>${userVO.userName}</dd>
		</dl>
	</div>

	<div class="bod_btn">
		<a href="#" onclick="open_namecheck('/portal/user/activationProc.do?userId=${userVO.userId}'); return false;" class="btn_blue">본인 인증</a>
		<a href="#" onclick="requestGpin('/portal/user/activationProc.do?userId=${userVO.userId}'); return false;" class="btn_gray">공공 아이핀(I-PIN) 인증</a>
	</div>
</form:form>
