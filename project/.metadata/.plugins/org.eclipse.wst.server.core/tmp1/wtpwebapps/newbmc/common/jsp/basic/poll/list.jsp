<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 목록
	 *
	 * 수정일				수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.09.29		권태성			최초 생성 및 코드 수정
	 * 2019.05.28		김선영			마크업 수정
	 *
	 * @author 권태성
	 * @since 2017.09.29
	 */
%>

<!--게시물검색-->
<div id="contents"><div class="content">
<form:form commandName="searchVO" name="list" id="list" action="${_context}/${siteCodeFull}/poll/list.do?mId=${curMid}" method="post">
	<form:hidden path="page" />
	<input type="hidden" name="idx" id="idx" value="0" />
	<div class="bod_head clFix">
		<p class="page_num">Total Page <em>${page}</em> / ${paginationInfo.lastPageNo}
		<fieldset class="bod_search">
			<legend class="blind">설문 검색</legend>
			<label for="searchState" class="blind">설문상태 선택</label>
			<form:select path="searchState">
				<form:option value="">설문 상태</form:option>
				<form:option value="1">진행중</form:option>
				<form:option value="2">중지</form:option>
				<form:option value="3">종료</form:option>
			</form:select>
			<label for="searchType" class="blind">검색어조건을 선택하세요.</label>
			<select id="searchType" name="searchType" title="검색조건선택">
				<option value="">제목</option>
			</select>
			<label for="searchTxt" class="blind">검색어를 입력하세요.</label>
			<form:input path="searchTxt"/>
			<input type="submit" value="검색" />
		</fieldset>
	</div>
</form:form>

<!-- 내용시작 -->
<div class="bod_poll">
	<ul class="clFix">
		<c:if test="${not empty list}">
			<c:forEach var="poll" items="${list}" varStatus="status">
				<li>
					<c:choose>
						<c:when test="${poll.publicYn eq 'Y' and poll.stateFlag eq '종료'}"><c:set var="url" value="${_context}/${siteCodeFull}/poll/result.do" /></c:when>
						<c:otherwise><c:set var="url" value="${_context}/${siteCodeFull}/poll/view.do" /></c:otherwise>
					</c:choose>
					
						<div class="clFix">
							<div class="thumb">
								<figure>
									<c:if test="${not empty poll.mainFile}">
										<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
											<c:param name="param_atchFileId" value="${poll.mainFile}"/>
											<c:param name="mode" value="ratio"/>
											<c:param name="width" value="210"/>
											<c:param name="height" value="180"/>
											<c:param name="insertBr" value="${true}"/>
										</c:import>
									</c:if>
									<c:if test="${empty poll.mainFile}">
										<img src="/common/img/board/poll_noimg.jpg" alt="설문조사" width="210" height="180"/>
									</c:if>
								</figure>
							</div>
						
							<div class="cont">
								<p class="tit">${poll.title }</p>
								<dl class="clFix">
									<dt>설문기간</dt>
									<dd>${poll.startDate} ~ ${poll.endDate}</dd>
									<dt>상태</dt>
									<dd class="state">${poll.stateYn eq 'N' ? '설문 중단' : poll.stateFlag }</dd>
									<dt>참여자</dt>
									<dd>${poll.userCnt}명 </dd>
									<dt>결과공개여부</dt>
									<dd><span>${poll.publicYn eq 'Y' ? '공개' : '비공개'}</span></dd>
								</dl>
							</div>
							<button data-action="${url}?mId=${curMid}" data-keyset="{'idx' : ${poll.idx}}" data-formname="list" onclick="req.post(this); return false;" class="state_poll poll_${poll.stateFlag eq '진행중' ? 'ing' : (poll.stateFlag eq '대기중' ? 'ready' : 'finish')}">${poll.stateFlag eq '진행중' ? '참여하기' : (poll.stateFlag eq '대기중' ? '설문대기' : '설문종료')}</button>
						</div>
					<!-- </a> -->
				</li>
			</c:forEach>
		</c:if>
		<c:if test="${empty list}">
			<li class="no_data"><span>조회된 데이터가 없습니다.</span></li>
		</c:if>
	</ul>
</div>

<c:if test="${not empty list}">
	<div class="bod_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage" />
	</div>
</c:if>

<c:if test="${not empty yh:getUser()}">
	<div class="btn_wrap">
		<a href="/logout?successUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" title="본인 인증 해제" class="cancel">인증 취소</a>
	</div>
</c:if>
<div class="gap40"></div>
</div></div>
<script type="text/javascript" src="${_context}/common/js/commonProcess.js"></script>