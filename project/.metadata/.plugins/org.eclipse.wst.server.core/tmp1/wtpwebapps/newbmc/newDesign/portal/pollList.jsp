<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/newDesign/layout/header.jsp" %>

<!--게시물검색-->
<form name="frm" id="frm" action="${pageContext.request.contextPath}/${siteCodeFull}/poll/pollList.do?mId=${curMid}" method="post">
<input type="hidden" name="page" id="page" value="${page}" />
<div class="search_box">
	<div>
		<label for="searchType" class="hidden">설문상태 선택</label>							
		<select id="searchType" name="searchType" title="검색조건선택">
			<option value="cfg_title">설문상태 선택</option>
		</select>
		<label for="searchType" class="hidden">검색어조건을 선택하세요.</label>							
		<select id="searchType" name="searchType" title="검색조건선택">
			<option value="cfg_title">제목</option>
		</select>
		<label for="searchText" class="hidden">검색어를 입력하세요.</label>								
		<input type="text" value="${searchText }" class="input200" name="searchText" id="searchText" title="검색어 입력" />
		<input type="submit" value="검색" class="btn_sgray" onclick="pollSubmit(); return false;" />
	</div>
	<div class="board_page">Total Page <span class="red"><strong>1</strong></span> / 1</div>
</div>
</form>

<!-- 내용시작 -->
<div class="bod_survey">
	<ul>
		<li>
			<a href="" class="servey_wrap">
				<div href="#" class="col22">
					<p class="survey_pic">
						<span><img alt="이미지 없음" src="/common/img/board/poll_noimg.jpg" /></span>
					</p>
				</div>
				<div class="survey_con">
					<span class="survey_tit">yh데이타베이스 설문조사입니다.</span>
					<span class="survey_date"><strong>설문기간</strong> : 2017-06-05 ~ 2017-07-28</span>
					<span class="survey_txt"> <strong>상태</strong> : <span class="blue">진행</span> </span>
					<span class="survey_people"> <strong>참여자</strong> : 10명 </span>
			        <span class="survey_end"><strong>결과공개여부</strong> : <span class="open">공개</span> <span class="secret">비공개</span></span>
	        			<%-- <a href="#" class="btn_blue btn_blog_white">준비중</a>
						<a href="javascript:pollResult('${page}', '${element.cfgCode}');" class="btn_blue btn_blog_white">결과(공개)</a>
						<a href="javascript:pollView('${page}', '${element.cfgCode}');" class="btn_blue btn_blog_white">참여하기</a> --%>
				</div>
				<a href="/newDesign/portal/pollView.jsp" class="btn_poll poll_start">참여하기</a>
 		        <a href="/newDesign/portal/pollView.jsp" class="btn_poll poll_end">설문종료</a>
 		        <a href="/newDesign/portal/pollView.jsp" class="btn_poll poll_ready">진행예정</a>
			</a>
		</li>
		<li class="no_data"><span>등록된 게시물이 없습니다.</span></li>
	</ul>
	
</div>

<c:if test="${not empty resultList}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage" />
	</div>
</c:if>

<%@ include file="/newDesign/layout/footer.jsp" %>
