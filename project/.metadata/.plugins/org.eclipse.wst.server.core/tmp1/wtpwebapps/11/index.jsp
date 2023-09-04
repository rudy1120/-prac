<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> Ticket </title>

</head>
<body>
<h1>티켓 예매</h1>
<header>
 <div class="menu" > 
  <ul class="clearfix">
	<li><a href="login.jsp">로그인</a></li>
	<li><a href="signup.jsp">회원가입</a></li>
    <li><a href="booking.jsp">마이티켓</a></li>
  </ul>
</div>
</header>

<div class="wrap">
  <div class="tab_menu">
    <ul class="list">
      <li class="is_on">
        <a href="#tab1" class="btn">Tab Button1</a>
      </li>
      <li>
        <a href="#tab2" class="btn">Tab Button2</a>
      </li>
      <li>
        <a href="#tab3" class="btn">Tab Button3</a>
      </li>
    </ul>

    <div class="cont_area">
      <div id="tab1" class="cont" style="display:block;">
        Tab Content1
      </div>
      <div id="tab2" class="cont">
        Tab Content2
      </div>
      <div id="tab3" class="cont">
        Tab Content3
      </div>
    </div>
  </div>
</div>
</body>
</html>