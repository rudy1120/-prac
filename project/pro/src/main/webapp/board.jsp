<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
        <link rel ="stylesheet" href="css/board.css">
  <body>
    <div class="container-fluid">
      <div class="row d-flex d-md-block flex-nowrap wrapper">

        <main id="main" class="col-md-9 float-left col pl-md-5 pt-3 main">
          <div class="page-header mt-3">
            <h2>자유게시판</h2>
          </div>
          <p class="lead">다른 회원들과 자유롭게 소통하세요.</p>
          <hr>
          <table class="table table-striped" style="max-width:1080px;">
            <thead>
              <tr>
                <th class="mobile" style="width:55px; text-align:center;">번호</th>
                <th style="text-align:center;">제목</th>
                <th class="mobile" style="width:80px; text-align:center;">작성자</th>
                <th class="mobile" style="width:120px; text-align:center;">날짜</th>
              </tr>
            </thead>
            <tbody>
         	  <c:forEach items="${ list }" var="bbs">
              <tr>
                <td style="text-align: center;">${ bbs.bbsID }</td>
                <td><a href="./boardInfoView.reservation?bbsID=${ bbs.bbsID }" style="color:#000000;">
                <c:choose>
                	<c:when test="${bbs.bbsAvailable == 0}">
                		삭제된 게시글입니다.
                	</c:when>
                	<c:otherwise>
                		${ bbs.bbsTitle }
                	</c:otherwise>
                </c:choose>
                </a></td>
                <td style="text-align: center;">${ bbs.userID }</td>
                <td style="text-align: center;">${ bbs.bbsDate }</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
          <div style="max-width:1080px;">
            <a href="./boardWrite.jsp" class="btn btn-primary float-right">글쓰기</a>
          </div>
          <ul class="pagination">
			<c:choose>
				<c:when test="${pageNumber != 1}">
					<a href="boardListView.reservation?pageNumber=${ pageNumber - 1 }" class="btn btn-success btn-arrow-left">이전</a>
				</c:when>
				<c:when test="${nextExist == true}">
					<a href="boardListView.reservation?pageNumber=${ pageNumber + 1 }" class="btn btn-success btn-arrow-right">다음</a>
				</c:when>
			</c:choose>
          </ul>
        </main>
      </div>
    </div>

  </body>
</html>