<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js" ></script>
<script  type="text/javascript">

/*  if (document.location.protocol == 'http:') {
    document.location.href = document.location.href.replace('http:', 'https:');
} */

	jQuery(document).ready(function() {
		// 로그인 처리
		$("#btnLogin").click(function(){
			var options = {
					url: "/sys/superLoginProc.do",
					type: "post",
					beforeSubmit: validateForm,
					success: resultResponse,
					dataType: 'json',
					resetForm: false
				};
				// bind form using 'ajaxForm'
				$("#loginForm").ajaxForm(options);
		});
	});

	function validateForm(formData, jqForm, options) {
		var form = jqForm[0];

		if(!form.adminId.value) {
			alert("아이디를 입력해 주십시오");
			form.adminId.focus();
			return false;
		} else if(!form.pwd.value) {
			alert("암호를 입력해 주십시오.");
			form.pwd.focus();
			return false;
		}

	}

	function resultResponse(data) {
		if (data.flag == "success") {
			document.location.href = "${pageContext.request.contextPath}/sys/intro.do";
		} else {
			alert(data.message);
			if (data.flag == "mypage") {
				document.location.href = "${pageContext.request.contextPath}/sys/superMng/adminMng/modify.do?mId=0112010000";
			}
		}
	}

</script>


<div id="login_wrap">
	<div>
		<h1>포털 통합관리자</h1>
		<form name="loginForm" id="loginForm" >
			<ul class="list_form">
				<li>
					<label for="adminId">아이디</label>
					<input type="text" name="adminId" id="adminId" value=""/>
				</li>
				<li>
					<label for="pwd">비밀번호</label>
					<input type="password" name="pwd" id="pwd"  value=""/>
				</li>
			</ul>
			<div>
				<input type="submit" id="btnLogin" name="btnLogin" value="Login"/>
			</div>
		</form>
	</div>
</div>
