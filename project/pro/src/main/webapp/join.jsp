<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>회원가입</title>
    <link rel ="stylesheet" href="css/join.css">
</head>

    <div class="join_container">
        <h2>
            회원 가입
        </h2>
        <form method="post" action="joinAction.jsp">
            <h3>아이디</h3>
            <div class="joinID">
                <input type="text" class="input" style="ime-mode:disabled;" placeholder="아이디" name="userID" title="아이디" maxlength="20">
            </div>
            <h3>비밀번호</h3>
            <div class="joinPassword">
                <input type="password" class="input" placeholder="비밀번호" name="userPassword" title="비밀번호" maxlength="20">
            </div>
            <h3>이름</h3>
            <div class="joinName">
                <input type="text" class="input" placeholder="이름" name="userName" title="이름" maxlength="20">
            </div>

            <h3>성별</h3>
            <div class="joinGender">
                <input type="radio" name="userGender" value="M" title="성별"> 남자
                <input type="radio" name="userGender" value="F" title="성별"> 여자
            </div>
            
            <h3>이메일</h3>
            <div class="joinEmail">
                <input type="text" class="input" placeholder="이메일" name="userEmail" title="이메일" maxlength="200">
            </div>
            
            
            <input type="submit" class="bt_join" value="회원가입">
        </form>
    </div>

    <footer>
        <div class="footer_container">
            <div class="footA">
                'sw'
            </div>
            <div class="footB">
                Copyright © Jo's All Rights Reserved.
            </div>
        </div>
    </footer>
</body>
</html>
