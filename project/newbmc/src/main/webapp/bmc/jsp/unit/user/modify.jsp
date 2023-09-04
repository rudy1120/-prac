<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 회원 정보 수정 페이지
	 *
	 * 수정일		수정자			수정내용
	 * ------------	------------	-----------------------------
	 * 2016.12.24	J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.12.24
	 */
%>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function () {
		$("#modifyForm").submit(function (e) {
			e.preventDefault();
			if (validator("#modifyForm") && additional_validator()) {
				$.ajax({ type: "post", dataType: "json", url: "/portal/user/modifyProc.do", data: $("#modifyForm").serialize(),
					success: function (result) {
						if (result.success) {
							alert("회원 정보를 수정했습니다.");
							location.href = "/portal/user/modify.do?mId=${menuVO.mId}";
						} else if (result.selector) {
							alert(result.errMsg ? result.errMsg : "잘못된 입력값이 있습니다.");
							$(result.selector).focus();
						} else {
							alert(errMsg(result.errCode));
							if (result.redirect) {
								location.href = result.redirect;
							}
						}
						return false;
					}
				}).fail(function () { alert("서버와 통신 중 오류가 발생했습니다."); });
			}
			return false;
		});

		$("#delete").click(function (e) {
			if (validator("#modifyForm") && confirm("회원 계정을 삭제합니다. 정말 탈퇴하시겠습니까?")) {
				$.ajax({ type: "post", dataType: "json", url: "/portal/user/deleteAccountProc.do", data: { "password": $("#password").val() },
					success: function (result) {
						if (result.success) {
							alert("탈퇴되었습니다. 메인으로 이동합니다.");
							location.href = "/main.do";
							return false;
						}
						alert(errMsg(result.errCode));
					}
				}).fail(function() { alert("서버와 통신 중 오류가 발생했습니다."); });
			}
			return false;
		});
	});

	function errMsg(errCode) {
		switch (errCode) {
			case -1: return "회원 계정이 존재하지 않습니다. 다시 로그인 후 시도해주세요.";
			case 0: return "비밀번호가 일치하지 않습니다.";
			default: return "탈퇴 처리 중 오류가 발생했습니다. 계속해서 오류가 발생하는 경우 관리자에게 문의해주시기 바랍니다.";
		}
	}

	function additional_validator() {
		if ($("#modifyForm #password").val() == $("#newPassword").val()) {
			alert("기존과 동일한 비밀번호입니다.");
			$("#newPassword").focus();
			return false;
		}

		if ($("#newPassword").val()) {
			return pw_validator("#newPassword", "#retypePw");
		}

		return true;
	}

//]]>
</script>
<security:authentication var="user" property="principal"/>
<form:form commandName="userVO" name="modifyForm" id="modifyForm" action="/portal/user/modifyProc.do?mId=${menuVO.mId}" methodParam="post">
	<fieldset>
		<legend class="hidden">회원 정보 수정</legend>
		<div class="tbl_form">
			<dl>
				<dt>
					<label for="userId">아이디(ID)</label>
					<span title="필수입력">*</span>
				</dt>
				<dd>
					${user.userId}
					<%--
						hibernate validator을 통과하기 위한 입력값으로 단순 VO 입력값 검증 외의 처리에는 이용되지 않음.
						실제 처리는 모두 session에 저장된 로그인 정보를 이용.
					--%>
					<input type="hidden" name="userId" value="${user.userId}"/>
				</dd>
			</dl>
			<dl>
				<dt>
					<label for="password">비밀번호</label>
					<span title="필수입력">*</span>
				</dt>
				<dd>
					<input id="password" type="password" name="password" maxlength="15" data-validator="on" data-required="y" data-fieldName="비밀번호"/>
				</dd>
			</dl>
			<dl>
				<dt>
					<label for="newPassword">새 비밀번호</label>
				</dt>
				<dd>
					<form:password path="newPassword" maxlength="15" data-validator="on" data-minLength="9" data-fieldName="새 비밀번호"/>
					<p class="red mT5">※ 비밀번호를 변경하시는 경우에만 입력해주세요.</p>
					<p class="red">※ 비밀번호는 특수 문자, 숫자, 영문자의 조합으로 구성된 9자리 이상의 문자열이어야 합니다.</p>
					<p class="red">※ 사용 가능한 특수 문자는 <strong>~!@#$%^*?_+=;:</strong> 입니다.</p>
				</dd>
			</dl>
			<dl>
				<dt>
					<label for="retypePw">새 비밀번호<br />(재입력)</label>
				</dt>
				<dd>
					<form:password path="retypePw" maxlength="15" data-validator="on" data-fieldName="새 비밀번호(재입력)"/>
				</dd>
			</dl>
			<dl>
				<dt>이름</dt>
				<dd>${user.userName}</dd>
			</dl>
			<dl>
				<dt>성별</dt>
				<dd>${user.gender == 'M' ? '남' : '여'}</dd>
			</dl>
			<dl>
				<dt>
					<label for="tel1">전화번호</label>
				</dt>
				<dd>
					<form:select path="tel1" title="전화번호 앞자리 입력" data-validator="on" data-required="y" data-fieldName="전화번호 앞자리">
						<form:option value="010">010</form:option>
						<form:option value="011">011</form:option>
						<form:option value="016">016</form:option>
						<form:option value="017">017</form:option>
						<form:option value="018">018</form:option>
						<form:option value="019">019</form:option>
						<form:option value="02">02</form:option>
						<form:option value="031">031</form:option>
						<form:option value="032">032</form:option>
						<form:option value="033">033</form:option>
						<form:option value="041">041</form:option>
						<form:option value="042">042</form:option>
						<form:option value="043">043</form:option>
						<form:option value="051">051</form:option>
						<form:option value="052">052</form:option>
						<form:option value="053">053</form:option>
						<form:option value="054">054</form:option>
						<form:option value="055">055</form:option>
						<form:option value="061">061</form:option>
						<form:option value="062">062</form:option>
						<form:option value="063">063</form:option>
						<form:option value="064">064</form:option>
						<form:option value="070">070</form:option>
					</form:select>
					-
					<form:input path="tel2" cssClass="wp10" maxlength="4" title="전화번호 가운데 자리 입력" data-validator="on" data-required="y" data-inputType="num" data-fieldName="전화번호 가운데 자리"/>
					-
					<form:input path="tel3" cssClass="wp10" maxlength="4" title="전화번호 마지막 자리 입력" data-validator="on" data-required="y" data-inputType="num" data-fieldName="전화번호 마지막 자리"/>
				</dd>
			</dl>
		</div>
	</fieldset>

	<div class="bod_foot">
		<div class="bod_btn txtC">
			<input type="submit" class="btn_blue" value="저장"/>
			<a id="delete" href="#" class="btn_gray">회원 탈퇴</a>
		</div>
	</div>

</form:form>
