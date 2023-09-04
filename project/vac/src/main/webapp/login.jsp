<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>로그인</title>
  <link rel ="stylesheet" href="css/login.css">
</head>
<body>
<div class = "loginbox">
  <h2>로그인</h2>
  <form action="loginAtcion.jsp">
  	<fieldset>
  		<legend>로그인 구역</legend>
  		<label for="loginid">아이디</label>
  		<input type="text" id="loginid" placeholder="아이디를 입력해 주세요">
  		<label for="loginpw">비밀번호</label>  
  		<input type="password"
		id="loginpw"
		placeholder="비밀번호를 입력하세요">
		<ul>
			<li><a href="#">아이디 비밀번호 찾기</a></li>
			<li><a href="#">회원가입</a></li>
		</ul>
		<button type ="submit">로그인</button>

<body>
<div class="wrap">
     <a class="kakao" href="https://kauth.kakao.com/oauth/authorize?client_id=REST_API키&redirect_uri=REDIRECT_URI&response_type=code">
     	<!-- REST_API키 및 REDIRECT_URI는 본인걸로 수정하세요 -->
        
      	<div class="kakao_i"></div>
      	<div class="kakao_txt">카카오톡으로 간편로그인 </div>
   	</a>
</div>
  		</fieldset>
  	</form>
  	</div>
</body>
</html>
