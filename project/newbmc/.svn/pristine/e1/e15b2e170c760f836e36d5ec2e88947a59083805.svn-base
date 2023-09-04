<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/bmc/jsp/common/taglib.jsp"%>

<div class="headerTop">
	<div class="headerInnr">
		<%-- <ul id="utils">
			<li>
			<c:choose>
				<c:when test="${not empty pageContext.request.remoteUser}">
					<a href="/logout" class="">로그아웃</a>
				</c:when>
				<c:otherwise>
					<a href="/bmc/user/loginForm.do?mId=0801000000" class="login">로그인</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="#" class="join">회원가입</a></li> 
			 <li>
				<select id="language">
					<option value="" selected>Language</option>
					<option value="#">Korea</option>
					<option value="#">English</option>
					<option value="#">China</option>
				</select>
			</li> 
		</ul> --%>
		
		<div id="search">
			<div class="familySite">
				<a class="btn_family" href="javascript:void();" title="주요 사업 사이트 링크 선택">주요 사업 사이트</a>
				<ul class="familyList">
					<li><a href="https://osiria.bmc.busan.kr/" target="_blank" title="오시리아 관광단지 사계절체류형 명품복합해양리조트 문화관광 쇼핑이 한데 어우러진 꿈의 관광단지" class="open">오시리아</a></li>
					<li><a href="http://www.arpina.co.kr" target="_blank" title="부산도시공사 아르피나 해운대 명품 컨벤션 휴양시설">아르피나</a></li>
				</ul>
			</div>
			<div class="searchWrap">
				<form action="/bmc/search/totalSearch.do" name="srchFrm" id="srchFrm" data-methodParam="post">
					<input type="hidden" name="mId" value="0805000000" />
					<input type="hidden" name="category" id="category" value="TOTAL">
					<label for="kwd" class="blind">검색어 입력창</label>
					<input type="text" id="kwd" name="kwd" placeholder="검색어를 입력하세요" maxlength="20">
					<button class="submit">검색</button>
				</form>
			</div>
			<div class="languageButton">
				<a href="/eng/main.do" title="영문 홈페이지 바로가기">
					<span class="blind">영문 홈페이지 바로가기</span>
					<img src="/bmc/images/main/btn_eng.png" alt="영문 홈페이지 바로가기 " />
				</a>
			</div>
			<div class="siteMap">
				<a href="/bmc/guide/siteMap.do?mId=0803000000"><span>전체메뉴</span></a>
			</div>
		</div>
	</div>
</div>