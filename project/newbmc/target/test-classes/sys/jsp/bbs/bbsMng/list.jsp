<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/sys/jsp/bbs/bbsMng/component/variables.jsp" %>
<%
	/**
	 * 게시판 목록 열람
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.11.14		김현호			최초 생성 및 코드 작성
	 * 2015.08.03		J.Ryeon Lee		목록 출력 코드 외부 파일화
	 * 2017.05.15		J.Ryeon Lee		script 분리
	 *
	 *
	 * @author 김현호
	 * @since 2014.11.14
	 */
%>

<h2>${bbsConfigVO.ptTitle}</h2>
<form:form commandName="searchVO" id="list" name="list" action="/sys/${siteCode}/bbs/bbsMng/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page"/>
	<input type="hidden" name="ptIdx" value="${bbsConfigVO.ptIdx}"/>
	<input type="hidden" name="bIdx"/>

	<%-- ============================== SEARCH FORM ============================== --%>
	<div class="search_box">
		<div class="fL">
			<c:if test="${useCategory}">
				<c:if test="${useDept}">
					<form:select path="searchDept">
						<form:option value="">전체 부서</form:option>
						<c:forEach var="code" items="${deptList}">
							<form:option value="${code.deptId}">${code.deptName}</form:option>
						</c:forEach>
					</form:select>
				</c:if>
				<c:if test="${useCustom}">
					<form:select path="searchCategory">
						<form:option value="">전체 분류</form:option>
						<c:forEach var="code" items="${categoryList}">
							<form:option value="${code.bCategory}">${code.bCategory}</form:option>
						</c:forEach>
					</form:select>
				</c:if>
			</c:if>
			<form:select path="searchDelete">
				<form:option value="">전체</form:option>
				<form:option value="Y">삭제</form:option>
				<form:option value="N">비삭제</form:option>
			</form:select>
			<form:select path="searchType">
				<form:option value="b_title">제목</form:option>
				<form:option value="b_Write">작성자</form:option>
				<form:option value="b_Content">내용</form:option>
			</form:select>
			<form:input type="text" path="searchTxt" value="${searchVO.searchTxt}"/>
			<input type="button" onclick="goPage(1); return false;" class="btn_white" value="검색"/>
			<c:if test="${bbsConfigVO.ptBundleDelYn == 'Y'}">
				<input id="batch_delete_btn" type="button" class="btn_white" value="삭제"/>
				<input id="batch_restore_btn" type="button" class="btn_white" value="복원"/>
				<script type="text/javascript">
					yh.deleteUrl = "/sys/${siteCode}/bbs/bbsMng/bundleDeleteProc.do?mId=${menuVO.mId}";
					yh.restoreUrl = "/sys/${siteCode}/bbs/bbsMng/bundleRedeleteProc.do?mId=${menuVO.mId}";
				</script>
				<script type="text/javascript" src="/sys/js/bbs/batch.js"></script>
			</c:if>
			<c:if test="${bbsConfigVO.ptRssYn == 'Y'}">
				<a href="${_context}/portal/bbs/xml/list.do?ptIdx=${bbsConfigVO.ptIdx}&amp;p=1&amp;limit=20" class="btn_orange" target="_blank" title="RSS 새창열기">RSS</a>
			</c:if>
			<c:if test="${fn:substring(bbsConfigVO.ptLevelInput, 0, 1) == 'Y'}">
				<a href="#" class="btn_cyan mL5" onclick="goTo.write('list'); return false;">글쓰기</a>
			</c:if>
		</div>
		<div class="cleB"></div>
	</div>

	<%-- ============================== BOARD LIST ============================== --%>
	<c:choose>
		<c:when test="${isPhoto}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/photo.jsp"/>
		</c:when>
		<c:when test="${isMovie}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/movie.jsp"/>
		</c:when>
		<c:when test="${isBlog}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/blog.jsp"/>
		</c:when>
		<c:when test="${isMinwon}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/minwon.jsp"/>
		</c:when>
		<c:when test="${isFAQ}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/faq.jsp"/>
		</c:when>
		<c:when test="${isDownload}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/download.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/basic.jsp"/>
		</c:otherwise>
	</c:choose>


	<%-- ============================== PROCESS BUTTON ============================== --%>
	<div class="btn_boxR">
		<c:if test="${fn:substring(bbsConfigVO.ptLevelInput, 0, 1) == 'Y'}">
			<a href="#" class="btn_cyan" onclick="goTo.write('list'); return false;">글쓰기</a>
		</c:if>
		<a href="/sys/${siteCode}/bbs/configMng/list.do?mId=${menuVO.mId}" class="btn_dblue">관리목록</a>
	</div>
</form:form>

<script type="text/javascript">
	yh.siteCodeFull = "${siteCode}";
	yh.ptIdx = "${bbsConfigVO.ptIdx}";
	yh.bIdx = "${searchVO.bIdx}";
	yh.isNew = eval("${isNew}");
	yh.formId = "list";
	yh.formName = "litForm";
	yh.ptCheckWord = "${bbsConfigVO.ptCheckWord}";
</script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/sys/js/bbs/pageMove.js"></script>
