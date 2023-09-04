<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 회원 신청 페이지
	 *
	 * 수정일		수정자			수정내용
	 * ------------	------------	-----------------------------
	 * 2016.08.11	J.Ryeon Lee		최초 생성 및 코딩
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.08.11
	 */
%>
<jsp:include page="./mIds.jsp"/>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[

	$(document).ready(function () {
		$("#userId").change(function() {
			$("#flag_check_unique_id").val("N");
		});

		$("#join").submit(function (e) {
			e.preventDefault;

			if (validator("#join") && additional_validator()) {
				$.ajax({
					type: "post", dataType: "json", data: $("#join").serialize(), url: "/portal/user/joinProc.do",
					success: function (result) {
						if (result.success) {
							alert("가입되었습니다. 감사합니다.");
							location.href = "/portal/contents.do?mId=" + mIds.login;
						} else {
							switch (result.errCode) {
								case 0:
									alert("잘못된 접근입니다.");
									location.href = "/portal/contents.do?mId="+mIds.policy;
									break;
								case 1:
									alert("잘못된 입력값이 있습니다.");
									$(result.selector).focus();
									break;
								case 2:
									if (result.selector == "#privatecode") {
										alert("이미 가입되어 있습니다. 로그인 후 이용해주세요.");
										location.href = "/portal/content.do?mId=" + mIds.login;
									} else {
										alert("잘못된 입력값이 있습니다.");
										$(result.selector).focus();
									}

									break;
								default:
									$(result.selector).focus();
									alert(result.errMsg);
									break;
							}
						}
						return false;
					}
				}).fail(function () {
					alert("서버와 통신 중 에러가 발생했습니다.");
				});
			}

			return false;
		});

		$("#chk_unique_user_id").click(function(e) {
			var userIdPatternMatched = new RegExp(validation_regxp.userId).exec($("#userId").val());
			if (userIdPatternMatched) {
				$.ajax({
					type: "post", "url": "/portal/user/check/id/unique.do", "data": { "userId": $("#userId").val() },
					success: function(data) {
						var result = $.parseJSON(data);
						if (result.success) {
							$("#flag_check_unique_id").val("Y");
							alert("사용 가능한 아이디입니다.");
						} else {
							$("#flag_check_unique_id").val("N");
							alert("사용 불가능한 아이디입니다.");
						}
					}
				}).fail(function() { alert("서버와 통신 중 오류가 발생했습니다."); });
			} else {
				$("#userId").focus();
				alert($("#userId").val() ? "회원 아이디는 4자 이상의 영숫자 조합 문자열만 입력 가능합니다." : "회원 아이디를 입력해주세요.");
			}
			return false;
		});
	});

	function additional_validator() {
		if (!(new RegExp(validation_regxp.userId).exec($("#userId").val()))) {
			alert("회원 ID는 첫 글자가 숫자가 아닌, 4자 이상의 영숫자 조합이어야 합니다.");
			$("#userId").focus();
			return false;
		} else if ($("#flag_check_unique_id").val() != "Y") {
			alert("아이디 중복 여부를 확인해주세요.");
			$("#chk_unique_user_id").focus();
			return false;
		} else if ($("#password").val() != $("#retypePw").val()) {
			alert("입력하신 비밀번호가 일치하지 않습니다.");
			$("#retypePw").focus();
			return false;
		} else if ($("#ageType").val() == "1" && $("#parentConfirm").val() == "N") {
			alert("보호자 인증을 받아야합니다.");
			$("#parentConfirmBtn").focus();
			return false;
		}

		return pw_validator("#password", "#retypePw");
	}

	var SIREN_window;
	function openSIRENWindow(){
		SIREN_window = window.open('${pageContext.request.contextPath}/SIREN24/pcc_vname_Input.jsp?returnUrl=close&cancelUrl=&diOnly=Y', 'SIRENWindow', 'width=410, height=450, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200' );
	}
//]]>
</script>
<security:authentication var="user" property="principal"/>
<form:form commandName="userVO" name="join" id="join" action="/portal/user/joinProc.do?mId=${menuVO.mId}" methodParam="post">
	<input type="hidden" id="flag_check_unique_id" value="N"/>
	<input type="hidden" id="ageType" value="${userVO.confirm.ageType }"/>

	<fieldset>
		<legend class="hidden">회원 신청서 작성</legend>
		<p class="txtR pad_b5">(<span class="red">*</span>)표시는 필수 입력 항목입니다.</p>
		<div class="tbl_form">
			<dl>
				<dt><label for="userName">이름</label></dt>
				<dd>${user.userName}</dd>
			</dl>
			<dl>
				<dt>
					<label for="userId">아이디(ID)</label>
					<span title="필수입력">*</span>
				</dt>
				<dd>
					<form:input path="userId" maxlength="20" data-validator="on" data-required="y" data-fieldName="아이디"/>
					<input id="chk_unique_user_id" type="button" class="bt_white" value="아이디 중복 여부" title="입력한 회원 아이디 사용 가능 여부 확인하기"/>
					<p class="red">※ 회원 아이디는 첫 글자가 숫자가 아닌, 최소 4자 이상의 영숫자 조합의 문자열만 사용 가능합니다.</p>
				</dd>
			</dl>
			<dl>
				<dt>
					<label for="password">비밀번호</label>
					<span title="필수입력">*</span>
				</dt>
				<dd>
					<form:password path="password" maxlength="15" data-validator="on" data-required="y" data-minLength="9" data-fieldName="비밀번호"/>
					<p class="red">※ 비밀번호는 특수 문자, 숫자, 영문자의 조합으로 구성된 9자리 이상의 문자열이어야 합니다.</p>
					<p class="red">※ 사용 가능한 특수 문자는 <strong>~!@#$%^*?_+=;:</strong> 입니다.</p>
				</dd>
			</dl>
			<dl>
				<dt>
					<label for="retypePw">재입력</label>
					<span title="필수입력">*</span>
				</dt>
				<dd>
					<form:password path="retypePw" maxlength="15" data-validator="on" data-required="y" data-minLength="9" data-fieldName="비밀번호 재입력"/>
				</dd>
			</dl>
			<dl>
				<dt>
					<label for="gender">성별</label>
					<span title="필수입력">*</span>
				</dt>
				<dd>
					<form:radiobutton path="gender" value="M" label="남" data-validator="on" data-required="y" data-fieldName="성별"/>
					<form:radiobutton path="gender" value="F" label="여"/>
				</dd>
			</dl>
			<dl>
				<dt>
					<label for="tel1">전화번호</label>
					<span title="필수입력">*</span>
				</dt>
				<dd>
					<form:select path="tel1" data-validator="on" data-required="y" data-fieldName="전화번호 앞자리">
						<form:option value="010" selected="selected">010</form:option>
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
					<form:input path="tel2" cssClass="wp20" maxlength="4" title="전화번호 가운데 자리 입력" data-validator="on" data-required="y" data-inputType="num" data-fieldName="전화번호 가운데 자리"/>
					-
					<form:input path="tel3" cssClass="wp20" maxlength="4" title="전화번호 마지막 자리 입력" data-validator="on" data-required="y" data-inputType="num" data-fieldName="전화번호 마지막 네자리"/>
				</dd>
			</dl>
			<c:if test="${userVO.confirm.ageType eq '1' }">
			<dl>
				<dt>보호자 인증</dt>
				<dd>
					<div id="sirenBtn">
						<a href="#" id="parentConfirmBtn" onclick="openSIRENWindow(); return false;" title="새 창에서 보호자 휴대폰 인증을합니다." class="btn_blue">휴대폰 인증</a>
					</div>
					<p class="red">※ 만 14세 미만인 경우 보호자 인증이 필요합니다.</p>
					<input type="hidden" id="parentConfirm" value="N"/>
				</dd>
			</dl>
			</c:if>
		</div>
	</fieldset>
	<div class="bod_foot">
		<div class="bod_btn">
			<input type="submit" class="btn_blue" value="저장"/>
			<a href="/main.do" class="btn_gray">취소하고 메인으로 돌아가기</a>
		</div>
	</div>

</form:form>
