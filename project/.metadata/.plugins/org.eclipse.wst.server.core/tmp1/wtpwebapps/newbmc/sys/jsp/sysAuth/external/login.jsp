<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * SNS 관리자 로그인
	 *
	 * 수정일			수정자			수정 내용
	 * -------------	------------	-----------------------------
	 * 2016.09.22		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.09.22
	 */
%>

<script type="text/javascript" src="/common/js/jquery/jquery.form.js" ></script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script  type="text/javascript" >
//<![CDATA[

	$(document).ready(function() {
		$("#btnLogin").click(function(e) {
			e.preventDefault();
			if (validator()) {
				$.ajax({
					type : "post",
					url: "/sys/${mngSiteCode}/loginProc.do",
					data : $("#form").serialize(),
					success : function (data) {
						var result = $.parseJSON(data);
						if (result.success) {
							location.href = "/sys/sysContents.do?mId=" + result.firstMid;
							return false;
						}

						alert(result.msg);
					}
				}).fail(function() {
					alert("서버와 통신 중 에러가 발생했습니다.");
				});
			}
		});
	});

//]]>
</script>

<div id="login_wrap" class="admin_external">
	<div>
		<h1>${mngSiteName} 외부 관리자</h1>
		<form:form id="form" commandName="loginVO" action="/sys/${mngSiteCode}/loginProc.do">
			<ul class="list_form">
				<li>
					<label for="adminId">아이디</label>
					<form:input path="adminId" data-validator="on" data-required="y" data-fieldName="아이디" />
				</li>
				<li>
					<label for="pwd">비밀번호</label>
					<form:password path="pwd" data-validator="on" data-required="y" data-fieldName="비밀번호" />
				</li>
			</ul>
			<div>
				<input id="btnLogin" type="submit" name="btnLogin" value="Login" />
			</div>
		</form:form>
	</div>
</div>
