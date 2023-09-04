<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/common/jsp/bbs/component/variables.jsp" %>
<%
	/**
	 * 게시판 목록 열람
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.11.14		김현호			최초 생성 및 코드 작성
	 * 2015.08.03		J.Ryeon Lee		스크립틀릿 코드 제거, jstl 태그를 쓰도록 수정/버튼 출력 부분 공통화, 목록 출력 컴포넌트 처리
	 * 2017.05.15		J.Ryeon Lee		script 분리, 게시판 타입별 목록 통합 처리
	 *
	 *
	 * @author 김현호
	 * @since 2014.11.14
	 */
%>

<div id="contents">
	<div class="content">

<div class="gap"></div>


<c:if test="${not empty bbsConfigVO.ptTopText}"> <%-- GUIDE HEADER --%>
	<div class="inr vision">
		<div class="area_box box2">
			<p>${bbsConfigVO.ptTopText}</p>
		</div>
	</div>
</c:if>
<form:form commandName="searchVO" name="list" id="list" action="/${siteCodeFull}/bbs/list.do?ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}" methodParam="post">
	<input type="hidden" name="cancelUrl" id="prevUrl" value="/${siteCodeFull}/bbs/list.do?ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}"/>
	<form:hidden path="page"/>

	<%-- ============================== SEARCH FORM ============================== --%>
	<div class="bod_head clFix">
	<c:if test="${useCategory && useCustom}">	
		<c:if test="${isFAQ}">
			<div class="faqbtn_wrap">
				<button class="b1" name="searchCategory" title="전체${'' eq searchVO.searchCategory ? ' 선택됨' : ''}" value="" onclick="$('#page').val(1);">전체 보기</button>
				<c:forEach var="_category" items="${categoryList}">
					<button class="b1" name="searchCategory" title="${fn:trim(_category)} 버튼${fn:trim(_category) eq searchVO.searchCategory ? ' 선택됨' : ''}" value="${fn:trim(_category)}" onclick="$('#page').val(1);">${fn:trim(_category)}</button>
				</c:forEach>
			</div>
			
		</c:if>
	</c:if>
		
		<p class="page_num">
			현재 페이지 <em>${page}</em> / 전체 페이지 ${paginationInfo.lastPageNo}
		</p>
		<fieldset class="bod_search">
			<legend class="blind">게시판 검색</legend>
			
			<c:if test="${isFAQ}">
			  
			</c:if>
				<c:if test="${not isFAQ}">
			<%-- 부서 선택 항목 (공통 게시판의 경우는 대표에서만 표출) --%>
			<c:if test="${useCategory && useDept && (bbsConfigVO.ptSiteCode != 'common' || (bbsConfigVO.ptSiteCode == 'common' && siteCode == 'portal'))}">
				<label for="searchDept" class="blind">부서 선택 항목</label>
				<form:select path="searchDept">
					<form:option value="">전체 부서</form:option>
					<c:forEach var="codeCa" items="${deptList}">
						<form:option value="${codeCa.deptId}">${codeCa.deptName}</form:option>
					</c:forEach>
				</form:select>
			</c:if>

			<%-- 직접 입력 분류 선택 항목 --%>
			<c:if test="${useCategory && useCustom}">
				<c:if test="${not isFAQ}">
					<label for="searchCategory" class="blind">분류 선택 항목</label>
					<form:select path="searchCategory">
					<form:option value="">전체 분류</form:option>
					<c:forEach var="_category" items="${categoryList}">
					<form:option value="${fn:trim(_category)}">${fn:trim(_category)}</form:option>
					</c:forEach>
					</form:select>
					<input type="submit" value="보기" name="searchBt" title="보기" onclick="$('#page').val(1);">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				
			</c:if>
			
			
			<%-- 검색 조건 --%>
			<label for="searchType" class="blind">검색구분 선택 : </label>
			<form:select path="searchType" title="검색유형 선택">
				<form:option value="b_title">제목</form:option>
				<form:option value="b_write">작성자</form:option>
				<form:option value="b_content">내용</form:option>
			</form:select>
			<label for="searchTxt" class="blind">검색어 입력: </label>
			<form:input path="searchTxt" title="검색어 입력"/>

			<input type="submit" value="검색" name="searchBt" title="검색" onclick="$('#page').val(1);">
			</c:if>
		</fieldset>
	</div>

	<%-- ============================== BOARD LIST ============================== --%>
	<c:choose>
		<c:when test="${isPhoto}">
			<jsp:include page="/common/jsp/bbs/component/list/photo.jsp"/>
		</c:when>
		<c:when test="${isMovie}">
			<jsp:include page="/common/jsp/bbs/component/list/movie.jsp"/>
		</c:when>
		<c:when test="${isBlog}">
			<jsp:include page="/common/jsp/bbs/component/list/blog.jsp"/>
		</c:when>
		<c:when test="${isMinwon}">
			<jsp:include page="/common/jsp/bbs/component/list/minwon.jsp"/>
		</c:when>
		<c:when test="${isFAQ}">
			<jsp:include page="/common/jsp/bbs/component/list/faq.jsp"/>
		</c:when>
		<c:when test="${isDownload}">
			<jsp:include page="/common/jsp/bbs/component/list/download.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="/common/jsp/bbs/component/list/basic.jsp"/>
		</c:otherwise>
	</c:choose>

	<%-- ============================== PROCESS BUTTON ============================== --%>
	<c:if test="${allowedWriting && !isFAQ}">
		<div class="btn_wrap taR">
			<a href="#" class="btn" onclick="goTo.write('list'); return false;" title="${activeMenu.menuName} 게시글 작성하기">글쓰기</a>
			<c:if test="${not empty yh:getUser()}">
				<a href="/logout?successUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" class="btn" title="본인 인증 해제">인증 취소</a>
			</c:if>
		</div>
	</c:if>
</form:form>
</div>
</div>

<script type="text/javascript">
	yh.siteCodeFull = "${siteCodeFull}";
	yh.ptIdx = "${bbsConfigVO.ptIdx}";
	yh.bIdx = "${searchVO.bIdx}";
	yh.isNew = eval("${isNew}");
	yh.formId = "list";
	yh.formName = "litForm";
</script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/bbs/pageMove.js"></script>


