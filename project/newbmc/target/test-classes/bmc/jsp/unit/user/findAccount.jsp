<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/common/jsp/component/authentication.jsp" %>
<%
	/**
	 * 대표 포탈 회원정보찾기
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.12.27		J.Ryeon Lee		최초 생성 및 코드 작성
	 * 2016.12.27		신수지			디자인 작업
	 * 2017.08.04		J.Ryeon Lee		본인인증창 컴포넌트 처리
	 * 2019.06.03		김선영			마크업수정
	 * @author J.Ryeon Lee
	 * @since 2016.12.27
	 */
%>
<div class="bod_guide">
	<ul>
		<li>회원 가입시 본인인증을 하지 않으셨던 분께서는 관리자에게 문의해주세요.</li>
		<li>핸드폰 인증과 공공아이핀 중에 회원가입 당시 인증하신 인증 방법을 선택해주셔야 조회가 됩니다.</li>
	</ul>
</div>

<ul class="find_idpwd clFix">
	<li>
		<h4>아이디 찾기</h4>
		<p>본인인증 및 공공 아이핀(I-PIN) 인증을 통해 통합 회원 계정을 찾을 수 있습니다.</p>
		<p>인증 방법을 선택해주세요.</p>
		<div class="btn_wrap taC">
			<a href="#" onclick="open_namecheck('/portal/user/findAccountResult.do'); return false;" class="btn_blue">본인 인증</a>
			<a href="#" onclick="open_gpin('/portal/user/findAccountResult.do'); return false;" class="btn_gray">공공 아이핀(I-PIN) 인증</a>
		</div>
	</li>
	<li>
		<h4>임시 비밀번호 발급</h4>
		<p>본인인증 및 공공 아이핀(I-PIN) 인증을 통해 통합 임시 비밀번호를 발급받으실 수 있습니다.</p>
		<p>인증 방법을 선택해주세요.</p>
		<div class="btn_wrap taC">
			<a href="#" onclick="open_namecheck('/portal/user/resetPasswordProc.do'); return false;" class="btn_blue">본인 인증</a>
			<a href="#" onclick="open_gpin('/portal/user/resetPasswordProc.do'); return false;" class="btn_gray">공공 아이핀(I-PIN) 인증</a>
		</div>
	</li>
</ul>