<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<%
	/**
	 * gnb영역이 모든사이트의 상단에 들어가게 되나 빌트인에 따라서 header파일이 중복이 되므로
	 * 해당부분을 include로 변경합니다.
	 *
	 * 수정일				수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.09.04		김현호			최초생성
	 * 2019.05.08		김선영			디자인 표준화에 따른 마크업 수정
	 *
	 *
	 * @author 김현호
	 * @since 2015.09.04
	 * @version 2.0
	 */
%>
<script>
//<![CDATA[

	function LayoutShow(showpop) {
		var objDiv = document.getElementById(showpop);

		if (objDiv.style.display=="block") {
			objDiv.style.display = "none";
		} else {
			objDiv.style.display = "block";
		}
	}

//]]>
</script>
<header id="headerWrap">
	<div id="gnbWrap">
		<div>
			<ul class="gnb_nav clFix">
				<li><a href="/main.do" <c:if test="${siteCodeFull == 'portal'}"> class="on"</c:if>>메인</a></li>
				<li><a href="/mayor/main.do" <c:if test="${siteCodeFull == 'mayor'}"> class="on"</c:if>>열린시장실</a></li>
			</ul>
			<ul class="gnb_option clFix">
				<c:choose>
					<c:when test="${not empty pageContext.request.remoteUser}">
						<li class="logout"><a href="/logout">로그아웃</a></li>
						<li class="modify"><a href="/portal/user/modify.do?mId=0304000000">회원정보 수정</a></li>
						<c:if test="${!yh:isLiveMode()}">
							<li class="modify"><a href="#" onclick="alert('di: ${yh:getUser().privatecode}\nID: ${yh.getUser().userId}');">사용자 정보</a></li>
						</c:if>
					</c:when>
					<c:otherwise>
						<li><a href="/portal/user/loginForm.do?mId=0103000000">로그인</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

	<%-- 사이트로고설정 --%>
	<c:set var="logoTitle" value=""/>
	<c:set var="logoUrl" value=""/>
	<c:set var="mainUrl" value=""/>
	<c:choose>
		<c:when test="${siteCodeFull == 'yangsan'}">
			<c:set var="logoTitle" value="양산시"/>
			<c:set var="logoUrl" value="yangsan"/>
			<c:set var="mainUrl" value="/main.do"/>
		</c:when>
		<c:otherwise>
			<c:set var="logoTitle" value="대표"/>
			<c:set var="logoUrl" value="portal"/>
			<c:set var="mainUrl" value="/${siteCode}/"/>
		</c:otherwise>
	</c:choose>

	<div id="innerHead">
		<div>
	      <h1><a href="${mainUrl}">로고</a></h1>
	      <div id="searchWrap">
	        <form name="searchForm" method="post" action="" target="_blank" onsubmit="return searchEngine()">
	          <ul class="total_search">
	            <li>
	              <label for="searchKey" class="blind">검색조건을 선택하세요.</label>
	              <select title="통합검색조건 선택항목" name="searchKey" id="searchKey">
	                <option value="all">통합검색</option>
	                <option value="web">웹페이지검색</option>
	                <option value="board">게시판검색</option>
	                <option value="content">컨텐츠검색</option>
	                <option value="file">파일검색</option>
	                <option value="emp">직원검색</option>
	              </select>
	            </li>
	            <li>
	              <label for="qt" class="blind">검색어 입력</label>
	              <input type="text" value="" name="qt" id="qt" title="검색어입력" placeholder="검색어를 입력하세요">
	            </li>
	            <li><input type="submit" title="통합검색 새창이동" value="검색"></li>
	          </ul>
	        </form>
	      </div>
		  <!-- 모바일 메뉴 -->
		     <button id="openMenu">
		       <span class="blind">메뉴 열기</span>
		       <!-- <span class="menu-btn"> -->
		         <span class="bar"></span>
		         <span class="bar"></span>
		      <!--  </span> -->
		     </button>
	      <!-- //모바일 메뉴 -->
	      <c:if test="${siteCodeFull == 'portal' || siteCodeFull == 'tour'}">
	        <button id="openSearch" onclick="LayoutShow('searchWrap');return false;"><span class="blind">검색창 열기</span></button>
	      </c:if>
	    </div>
	</div>