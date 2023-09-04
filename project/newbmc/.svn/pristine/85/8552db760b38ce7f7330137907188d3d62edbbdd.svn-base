<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 회원 정보 현황 수정
	 *
	 * 수정일		수정자			수정내용
	 * ------------	------------	-----------------------------
	 * 2015.10.12	J.Ryeon Lee		최초 생성 및 코드 작성
	 * 2016.08.17	J.Ryeon Lee		의정부시 회원 관리 기능 추가
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.10.12
	 */
%>
<h2>회원 상세 확인 및 수정</h2>
<form:form commandName="searchVO" name="cancel" id="cancel" action="/sys/portal/user/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchType"/>
	<form:hidden path="page"/>
</form:form>
<form:form commandName="searchVO" name="modify" id="modify" action="${pageContext.request.contextPath}/sys/portal/user/modifyProc.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" enctype="multipart/form-data">
	<form:hidden path="userId"/>

	<p class="info">상세 확인 및 수정이 가능합니다. <span class="red">*</span>는 필수 항목입니다.</p>

	<table class="tableSt_write mB10">
		<caption>회원 정보 현황 수정. 아이디, 수요자구분 상세 및 이름, 사용여부 항목별 입력을 제공하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width:15%"/>
			<col span="1"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span>이름</th>
				<td>${searchVO.userName}</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>성별</th>
				<td>${searchVO.gender == 'M' ? '남' : '여'}</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>회원 ID</th>
				<td>${searchVO.userId}</td>
			</tr>
			<tr>
				<th scope="row"><label for="password">비밀번호</label></th>
				<td>
					<input type="password" name="password" cssClass="input100" maxlength="50" data-validator="on" data-fieldName="비밀번호"/>
					<span class="tip">※ 비밀번호를 변경하시는 경우에만 입력해주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="tel1">전화번호</label></th>
				<td>${searchVO.tel1}-${searchVO.tel2}-${searchVO.tel3}</td>
			</tr>
			<tr>
				<th scope="row">가입일</th>
				<td><fmt:formatDate value="${searchVO.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>
			<tr>
				<th scope="row">최종 로그인 일자</th>
				<td><fmt:formatDate value="${element.lastestLoginDt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>

</form:form>
<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
