<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 관리자 계정 관리 페이지
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.03.29		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.03.29
	 */
%>

<h2>내 계정 관리</h2>
<form:form commandName="searchVO" name="detailForm" id="detailForm" onsubmit="return validator() && additional_validator();" action="/sys/${siteCode}/adminMng/modifyProc.do?mId=${menuVO.mId}" methodParam="post" htmlEscape="false">

	<form:hidden path="id" />
	<form:hidden path="adminId" value="${searchVO.id}" />

	<table class="tableSt_write">
		<caption></caption>
		<tbody>
			<tr>
				<th scope="row" class="w20">아이디</th>
				<td>${searchVO.adminId}</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="name">성명</label></th>
				<td>
					<form:input path="name" maxlength="50" data-validator="on" data-required="y" data-fieldName="성명" />
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="pwd">현재 비밀번호</label></th>
				<td>
					<form:password path="pwd" data-validator="on" data-required="y" data-fieldName="현재 비밀번호" />
				</td>
			</tr>
			<tr>
				<th>
					<label for="newPwd">새 비밀번호</label>
				</th>
				<td>
					<form:password path="newPwd" maxlength="15" data-validator="on" data-minLength="9" data-fieldName="새 비밀번호" />
					<p class="red mT5">※ 비밀번호를 변경하시는 경우에만 입력해주세요.</p>
					<p class="red">※ 비밀번호는 특수 문자, 숫자, 영문자를 포함하는 9자리 이상의 문자열이어야 합니다.</p>
					<p class="red">※ 사용 가능한 특수 문자는 <strong>~!@#$%^*?_+=;:</strong> 입니다.</p>
				</td>
			</tr>
			<tr>
				<th>
					<label for="retypePwd">새 비밀번호<br />(재입력)</label>
				</th>
				<td>
					<form:password path="retypePwd" maxlength="15" data-validator="on" data-fieldName="새 비밀번호(재입력)" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="explan">설명</label></th>
				<td><form:input path="explan" class="input400" maxlength="400" data-validator="on" data-fieldName="설명" /></td>
			</tr>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장" />
	</div>

</form:form>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">

	function additional_validator() {
		var matched = false;
		$.ajax({
			type: "post", dataType: "json", async: false, data: $("#detailForm").serialize(),
			url: "/sys/${siteCode}/adminMng/chkPasswordProc.do?mId=${menuVO.mId}",
			success: function(result) {
				matched = result.matched;
			}
		}).fail(function() {
			alert("검증을 위해 서버와 통신 중 오류가 발생했습니다.");
			return false;
		});

		if (!matched) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#pwd").focus();
			return false;
		}

		if ($("#pwd").val() == $("#newPwd").val()) {
			alert("기존과 동일한 비밀번호입니다.");
			$("#newPwd").focus();
			return false;
		}

		if ($("#newPwd").val()) {
			return pw_validator("#newPwd", "#retypePwd");
		}

		return true;
	}

</script>
