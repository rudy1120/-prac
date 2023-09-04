<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.08.02		권태성			최초 생성 및 코드 수정
	 *
	 *
	 * @author 권태성
	 * @since 2017.08.02
	 */
%>
<c:set var="realNameDupType" value="<%=egovframework.portal.sys.basic.poll.PollDupType.REALNAME %>"/>
<h2>설문 목록 관리</h2>
<p class="tip mL10"><span class="red">참여 현황은 설문 참여자 중 추첨을 위한 기능입니다. 추첨하지 않는 설문에서는 결과 페이지에서 현황을 확인해주세요.</span></p>
<form:form commandName="searchVO" name="list" id="list" methodParam="post" action="${_context}/sys/pollMng/list.do?mId=${menuVO.mId}">
	<input type="hidden" id="page" name="page" value="${paginationInfo.currentPageNo}" />
	<input type="hidden" id="idx" name="idx" value="0" />
	<div class="search_box">
		<div class="fL">
			<form:select path="searchSiteCode">
				<form:option value="">사이트 선택</form:option>
				<c:forEach var="site" items="${siteList }">
					<form:option value="${site.siteCode }">${site.siteName }</form:option>
				</c:forEach>
			</form:select>
			<form:select path="searchState">
				<form:option value="">설문 상태</form:option>
				<form:option value="1">진행중</form:option>
				<form:option value="2">중지</form:option>
				<form:option value="3">종료</form:option>
			</form:select>
			<form:select path="searchViewYn">
				<form:option value="">노출 여부</form:option>
				<form:option value="Y">노출</form:option>
				<form:option value="N">미노출</form:option>
			</form:select>
			<form:input path="searchTxt"/>
			<input type="submit" onclick="goPage('1'); return false;" class="btn_white" value="검색">
		</div>
		<div class="fR">
			총 <span class="bold blue">${totalCnt}</span>건의 데이터가 있습니다.
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="w7">번호</th>
				<th scope="col" class="w8">구분</th>
				<th scope="col">설문조사명</th>
				<th scope="col" class="w20">설문 기간</th>
				<th scope="col" class="w5">참여</br>인원</th>
				<th scope="col" class="w5">설문</br>상태</th>
				<th scope="col" class="w5">결과</br>공개</br>여부</th>
				<th scope="col" class="w5">노출</br>여부</th>
				<th scope="col" class="w25">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty list}">
				<c:forEach var="poll" items="${list }" varStatus="status">
					<tr>
						<td>${listOrder - status.index }</td>
						<td>${poll.siteName }</td>
						<td class="taL">${poll.title }</td>
						<td>${poll.startDate } ~ ${poll.endDate }</td>
						<td>${poll.userCnt }</td>
						<td>${poll.stateYn eq 'N' ? '설문 중단' : poll.stateFlag }</td>
						<td>${poll.publicYn eq 'Y' ? '공개' : '비공개' }</td>
						<td>
							<select data-idx="${poll.idx }">
								<option value="Y" ${poll.viewYn eq 'Y' ? 'selected="selected"' : '' }>노출</option>
								<option value="N" ${poll.viewYn ne 'Y' ? 'selected="selected"' : '' }>미노출</option>
							</select>
						</td>
						<td>
							<a href="#" class="btn_swhite" data-action="${_context}/sys/pollMng/update.do" data-formname="list" data-formserialize="y" onclick="$('#idx').val('${poll.idx}'); req.post(this); return false;">설문 관리</a>
							<a href="#" class="btn_swhite" data-action="${_context}/sys/pollMng/question/write.do" data-formname="list" data-formserialize="y" onclick="$('#idx').val('${poll.idx}'); req.post(this); return false;">질문 관리</a>
							<c:if test="${poll.dupType == realNameDupType.code}">
								<c:set var="link" value="${_context}/sys/pollMng/responder/list.do?pollIdx=${poll.idx}&amp;mId=${menuVO.mId}" />
							</c:if>
							<c:if test="${poll.dupType != realNameDupType.code}">
								<c:set var="alert" value="onclick=\"alert('중복대상이 [본인인증]으로 설정되어 있지 않거나\n설문조사 정보가 정확하지 않습니다.'); return false;\"" />
							</c:if>
							<a href="${not empty link ? link : '#' }" ${alert } class="btn_swhite">참여 현황</a>
							<a data-action="${_context}/sys/pollMng/result/view.do" data-formname="list" data-formserialize="y" onclick="$('#idx').val('${poll.idx}'); req.post(this); return false;" href="#" class="btn_swhite">결과</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<c:if test="${empty list}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>

	<c:if test="${not empty list}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage" />
		</div>
	</c:if>

	<div class="btn_boxR">
		<a href="${_context}/sys/pollMng/write.do?mId=${menuVO.mId}" class="btn_cyan">등록</a>
	</div>

</form:form>

<script type="text/javascript" src="${_context}/common/js/common/require.js"></script>
<script type="text/javascript" src="${_context}/sys/js/basic/pollMng/pollMng.js<%--=System.currentTimeMillis() --%>"></script>
<script type="text/javascript">
yh.formId = "list";
yh.isNew = "${isNew}";
pollMng.mode = "list";
</script>
