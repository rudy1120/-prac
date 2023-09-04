<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 의정부 대표 포탈 회원정보찾기
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.12.27		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.12.27
	 */
%>
<div class="uj_con_header mB20">
	<img src="/common/img/content/bg_uj_idsearch.png" alt="회원ID검색 및 비밀번호 재발급 안내" height="98" width="99">
	<div>
		<h4>회원ID검색 및 비밀번호 재발급 안내</h4>
		<p><span class="blue">회원 가입시 본인인증을 하지 않으셨던 분께서는 의정부시로 문의해주세요.</span></p>
		<p class="mT_im0">핸드폰 인증과 공공아이핀 중에 회원가입 당시 인증하신 인증 방법을 선택해주셔야 조회가 됩니다.</p>
	</div>
</div>

<div class="wrap_tipox">
	<c:choose>
		<c:when test="${empty userVO}">
			<ul>
				<li>${userName}님의 가입 정보가 존재하지 않습니다.</li>
				<li><strong>게시판 이용 등 의정부시에서 제공하는 모든 서비스는 휴대폰 본인 인증 및 공공 I-PIN을 통해서도 이용하실 수 있습니다.</strong></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul>
				<li>인증이 완료되었습니다.</li>
				<li>${userVO.userName} 님의 회원 ID는 <span class="blue">${userVO.userId}</span>입니다.</li>
				<c:if test="${not empty tmpPw}">
					<li>발급된 임시비밀번호는 <span class="red">${tmpPw}</span>입니다.</li>
					<li>로그인 후 비밀번호를 변경해주세요.</li>
				</c:if>
				<li>왼쪽 메뉴의 로그인 페이지를 통해 로그인하실 수 있습니다.</li>
			</ul>

			<c:if test="${empty tmpPw}">
				<div class="bod_btn taC">
					<a href="/portal/contents.do?mId=${menuVO.mId}" class="btn_gray">임시비밀번호받기</a>
				</div>
			</c:if>
		</c:otherwise>
	</c:choose>
</div>
