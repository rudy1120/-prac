<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 참여자 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.08.25		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.08.25
	 */
%>
<c:set var="lotteryTypeList" value="<%=egovframework.portal.sys.basic.poll.PollLotteryType.values() %>"/>
<c:set var="listUrl" value="${_context}/sys/pollMng/responder/list.do?mId=${menuVO.mId}"/>
<c:set var="downUrl" value="${_context}/sys/pollMng/responder/downProc.do?mId=${menuVO.mId}"/>

<h2>${poll.title} 참여자 목록</h2>
<form:form commandName="searchVO" name="list" id="list" methodParam="post" action="${listUrl}">
	<form:hidden path="page"/>
	<form:hidden path="pollIdx"/>

	<table class="tableSt_list row_over mB60">
		<caption>설문조사 정보에 대한 내용을 사이트, 설문조사명, 설문기간, 참여인원 항목 순서대로 간략히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col">사이트</th>
				<th scope="col">설문조사명</th>
				<th scope="col">설문기간</th>
				<th scope="col">참여인원</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${poll.siteName}</td>
				<td>${poll.title}</td>
				<td>${poll.startDate} ~ ${poll.endDate}</td>
				<td>${poll.userCnt}</td>
			</tr>
		</tbody>
	</table>

	<%-- ========================================= 검색 조건 ========================================= --%>
	<div class="search_box">
		<div class="fL">
			<%-- ##### 추첨상태 ##### --%>
			<label for="searchState" class="hidden">추첨상태선택</label>
			<form:select path="searchState">
				<form:option value="">전체</form:option>
				<c:forEach var="type" items="${lotteryTypeList}">
					<form:option value="${type.code}">${type.desc}</form:option>
				</c:forEach>
			</form:select>
			<%-- ##### 성별 ##### --%>
			<label for="searchGender" class="hidden">성별선택</label>
			<form:select path="searchGender">
				<form:option value="">전체</form:option>
				<form:option value="M">남</form:option>
				<form:option value="F">여</form:option>
			</form:select>
			<%-- ##### 기타 조건 ##### --%>
			<label for="searchType" class="hidden">검색조건선택</label>
			<form:select path="searchType">
				<form:option value="nm">참여자명</form:option>
				<form:option value="ip">IP</form:option>
			</form:select>
			<form:input path="searchTxt"/>
			<input type="submit" onclick="document.list.action = '${listUrl}'; $('#page').val(1);" class="btn_white" value="검색"/>
			<a href="${_context}/sys/pollMng/list.do?mId=${menuVO.mId}" class="btn_return">설문목록</a>
		</div>
		<div class="fR">
			총 <span class="bold blue">${totalCnt}</span>건의 데이터가 있습니다.
		</div>
		<div class="cleB"></div>
	</div>

	<%-- ========================================= 검색 결과 ========================================= --%>
	<table class="tableSt_list row_over">
		<caption>설문조사 참여자 목록을 참여자명, 성별, 생년월일, 연락처, IP, 추첨상태, 참여일자, 관리 항목 순서대로 안내하는 표입니다..</caption>
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col">참여자명</th>
				<th scope="col" class="w7">성별</th>
				<th scope="col" class="w13">생년월일</th>
				<th scope="col" class="w11">연락처</th>
				<th scope="col" class="w11">IP</th>
				<th scope="col" class="w8">추첨상태</th>
				<th scope="col" class="w11">참여일자</th>
				<c:if test="${not empty poll.lotDate}"><th scope="col" class="w11">추첨일자</th></c:if>
				<th scope="col" class="w11">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${e.name}</td>
					<td>${e.gender == 'M' ? '남' : '여'}</td>
					<td>${fn:substring(e.birth, 0, 4)}-${fn:substring(e.birth, 4, 6)}-${fn:substring(e.birth, 6, 8)}</td>
					<td>
						<c:if test="${not empty e.tel}">
							<c:choose>
								<c:when test="${fn:length(e.tel) == 11}">
									${fn:substring(e.tel, 0 ,3)}-${fn:substring(e.tel, 3, 7)}-${fn:substring(e.tel, 7, 11)}
								</c:when>
								<c:when test="${fn:length(e.tel) == 10}">
									${fn:substring(e.tel, 0 ,3)}-${fn:substring(e.tel, 3, 6)}-${fn:substring(e.tel, 6, 10)}
								</c:when>
								<c:otherwise>${e.tel}</c:otherwise>
							</c:choose>
						</c:if>
					</td>
					<td>${e.ip}</td>
					<td>
						<c:forEach var="type" items="${lotteryTypeList}">
							<c:if test="${type.code == e.lottery}">${type.desc}</c:if>
						</c:forEach>
					</td>
					<td><fmt:formatDate value="${e.createDate}" pattern="yyyy-MM-dd"/></td>
					<c:if test="${not empty poll.lotDate}">
					<td><fmt:formatDate value="${poll.lotDate}" pattern="yyyy-MM-dd"/></td>
					</c:if>
					<td>
						<%-- ##### 당첨자 ##### --%>
						<c:if test="${lotteryTypeList[1].code == e.lottery}">
							<a href="#" class="btn_swhite"
								data-action="${_context}/sys/pollMng/responder/lottery/cancel.do?mId=${menuVO.mId}"
								data-keyset="{'idx' : ${e.idx}, 'pollIdx': '${e.pollIdx}'}"
								data-success="${_context}/sys/pollMng/responder/list.do?pollIdx=${e.pollIdx}&mId=${menuVO.mId}"
								data-confirmmsg="해당 참여자의 당첨을 취소하시겠습니까?"
								onclick="req.ajax(this); return false;">당첨 취소</a>
						</c:if>
						<%-- ##### 비당첨자 ##### --%>
						<c:if test="${lotteryTypeList[1].code != e.lottery}">
							<a href="#" onclick="alert('당첨자가 아닙니다.'); return false;" href="#" class="btn_off">당첨 취소</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">참여자가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
		</div>
	</c:if>

	<div class="btn_boxR">
		<c:if test="${not empty result}">
			<c:if test="${empty poll.lotDate}"> <%-- 추첨전 --%>
				<c:if test="${lottable}">
					<a href="#" class="btn_cyan"
						data-action="${_context}/sys/pollMng/responder/lot.do?mId=${menuVO.mId}"
						data-keyset="{'pollIdx' : '${poll.idx}'}"
						data-confirmmsg="추첨하시겠습니까?"
						data-successmsg="추첨이 완료되었습니다."
						data-failedmsg="추첨 처리 중 오류가 발생했습니다."
						data-success="${_context}/sys/pollMng/responder/list.do?pollIdx=${poll.idx}&mId=${menuVO.mId}"
						onclick="req.ajax(this); return false;">추첨</a>
				</c:if>
				<c:if test="${not lottable}">
					<a href="#" class="btn_off" onclick="alert('추첨이 불가합니다.\n설문 설정을 확인해주세요..'); return false;">추첨</a>
				</c:if>
			</c:if>
			<c:if test="${not empty poll.lotDate}"> <%-- 추첨완료 --%>
				<a href="#" class="btn_off" onclick="alert('추첨이 완료된 설문입니다.'); return false;">추첨</a>
			</c:if>
			<input type="submit" class="btn_dblue" onclick="document.list.action = '${downUrl}';" value="엑셀"/>
		</c:if>
		<a href="${_context}/sys/pollMng/list.do?mId=${menuVO.mId}" class="btn_dblue">목록</a>
	</div>
</form:form>
<script type="text/javascript">
	var yh = {
		siteCode: "sys",
		mId: "${menuVO.mId}"
	};
</script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
