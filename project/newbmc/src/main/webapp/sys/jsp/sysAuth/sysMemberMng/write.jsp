<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 관리자 관리 등록/수정
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.01.06		엄동건			최초 생성 및 코드 수정
	 * 2017.04.24		J.Ryeon Lee		코드 리팩토링, 보안 지침 적용, 등록/수정 jsp 파일 통합
	 *
	 *
	 *
	 * @author 엄동건
	 * @since 2015.01.06
	 */
%>
<c:set var="isNew" value="${empty searchVO.id}" />
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}" />

<h2>${menuName}</h2>
<form:form commandName="searchVO" id="listForm" name="listForm" action="/sys/totalAdminMng/sysMemberMng/list.do?mId=${menuVO.mId}" method="post">
	<form:hidden path="page" />
	<form:hidden path="searchType" />
	<form:hidden path="searchTxt" />
</form:form>
<form:form commandName="searchVO" id="writeForm" name="writeForm" action="/sys/totalAdminMng/sysMemberMng/${postUrl}.do" method="post" onsubmit="return validator() && additional_validator();" htmlEscape="false">
	<table class="tableSt_write">
		<caption>관리자 등록</caption>
		<colgroup>
			<col span="1" style="width:20%;"/>
			<col span="1" style="width:80%;"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span>아이디</th>
				<td><form:input path="id" maxlength="50" data-validator="on" data-required="y" data-fieldName="아이디" /></td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>성명</th>
				<td><form:input path="name" maxlength="50" data-validator="on" data-required="y" data-fieldName="성명" /></td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>패스워드</th>
				<td>
					<input type="password" id="pwd" name="pwd" maxlength="15" class="mB5" data-validator="on" data-fieldName="패스워드" <c:if test="${isNew}">data-required="y"</c:if> />
					<c:if test="${!isNew}">
						<span class="tip red">※ 비밀번호를 변경하는 경우에만 입력해주세요.</span>
					</c:if>
					<p class="red">※ 비밀번호는 특수 문자, 숫자, 영문자의 조합으로 구성된 9자리 이상의 문자열이어야 합니다.</p>
					<p class="red">※ 사용 가능한 특수 문자는 <strong>~!@#$%^*?_+=;:</strong> 입니다.</p>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>레벨</th>
				<td>
					<form:select path="adminAccessLevelCode">
						<form:option value="5">서브관리자</form:option>
						<form:option value="10">최고관리자</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">소속부서</th>
				<td>
					<form:select path="deptId">
						<form:option value="">선택하세요</form:option>
						<c:forEach var="depts" items="${deptAllList}">
							<form:option value="${depts.deptId}">${depts.deptName}</form:option>
						</c:forEach>
					</form:select>
					<span class="tip red">※최고 관리자에는 소속 부서를 지정하지 마십시요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row">설명</th>
				<td><form:input path="explan" class="input400" maxlength="400" data-validator="on" data-fieldName="설명" /></td>
			</tr>
		</tbody>
	</table>
	<div class="btn_boxR">
		<input type="submit" id="submitBtn" value="등록" class="btn_cyan" />
		<input type="button" onclick="document.getElementById('listForm').submit(); return false;" value="취소" class="btn_dblue" />
	</div>
</form:form>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[

	$(document).ready(function(){
		$("#submitBtn").click(function() {
			var options = {
				url: "/sys/totalAdminMng/sysMemberMng/${postUrl}.do",
				type: "post",
				success: resultResponse,
				dataType: 'json',
				resetForm: false
			};

			$("#writeForm").ajaxForm(options);
		});

		function resultResponse(data) {
			if(data.flag == "success") {
				alert(data.message);
				location.href = "/sys/totalAdminMng/sysMemberMng/list.do?mId=${curMid}";
			} else {
				alert(data.message);
			}
		}
	});

	function additional_validator() {
		if ($("#adminAccessLevelCode").val() == "10" && $("#deptId").val()) {
			alert("최고 관리자는 소속 부서를 가질 수 없습니다.");
			$("#deptId").focus();
			return false;
		}

		if ($("#adminAccessLevelCode").val() == "5" && !$("#deptId").val()) {
			alert("소속부서를 선택하시거나 최고 관리자로 설정해주세요.");
			$("#deptId").focus();
			return false;
		}

		if ($("#pwd").val()) {
			return pw_validator("#pwd" ,"#pwd");
		}

		return true;
	}

//]]>
</script>