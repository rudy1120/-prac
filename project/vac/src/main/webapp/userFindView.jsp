<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <%@ include file="./view/header.jspf" %>
  <body>
    <div class="container-fluid">
      <div class="row d-flex d-md-block flex-nowrap wrapper">
        <%@ include file="./view/navigation.jspf" %>
        <main id="main" class="col-md-9 float-left col pl-md-5 pt-3 main">
          <div class="page-header mt-3">
            <h2>비밀번호 찾기</h2>
          </div>
          <p class="lead">비밀번호를 찾습니다.</p>
          <hr>
          <form action="userFindAction.reservation" method="POST" class="pt-3" style="max-width:720px;">
            <div class="form-group">
              <label>아이디</label>
              <input name="userID" type="text" class="form-control" placeholder="아이디를 입력하세요.">
            </div>
            <div class="form-group">
              <label>이메일</label>
              <input name="userEmail" type="email" class="form-control" placeholder="이메일을 입력하세요.">
            </div>
            <button type="submit" class="btn btn-primary">새 비밀번호 발송</button>
          </form>
        </main>
      </div>
    </div>
    <%@ include file="./view/footer.jspf" %>
    <%@ include file="./view/modal.jspf" %>
  </body>
</html>
