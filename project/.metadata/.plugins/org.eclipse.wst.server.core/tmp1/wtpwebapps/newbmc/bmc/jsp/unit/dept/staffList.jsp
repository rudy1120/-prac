<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 사용자 화면 담당자별 업무분장
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.12.23		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.12.23  // 현재 depth 2 하고 3에 관한 정보만 출력 및 디자인
 */
%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!-- <style>
@media all and (max-width:640px){
	.btnlist {width:100%;}
}
</style> -->
<script>

function allList(){
	location.href="/bmc/contents.do?mId=0603020000";
}
</script>

<div id="contents"><!--콘텐츠 시작-->
	<div class="content">
	<div class="gap20"></div>
<form:form commandName="searchVO" name="list" id="list" action="/bmc/staff/list.do?mId=${menuVO.mId}" methodParam="post">	
	<div class="bod_head clFix">
		<fieldset class="bod_search">
			<legend class="blind">게시판 검색</legend>
			<label for="searchType" class="blind">검색구분 선택 : </label>
			<select id="searchType" name="searchType" title="검색유형 선택">
				<option value="usr_nm">성명</option>
				<option value="adi_info7">업무</option>
			</select>
			<label for="searchTxt" class="blind">검색어 입력: </label>
			<input id="searchTxt" name="searchTxt" title="검색어 입력" type="text" value="">

			<input type="submit" value="검색" name="searchBt" title="검색" />
			<button class="btnlist" onclick="allList();">전체보기</button>
		</fieldset>
	</div>
</form:form>
<div class="gap20"></div>
		<c:choose>
		<c:when test="${searchVO.searchTxt == '' || empty searchVO.searchTxt }">
		
		<c:forEach var="depth2" items="${result}">
			<c:if test="${depth2.newDepth == 2}">
				<p class="depth2-title">${depth2.newSname}</p>
				
				<p class="scroll_txt">좌우로 스크롤 하세요</p>
				<br />
				<div class="orgTable_wrap jobTable_wrap">
					<table class="orgTable jobTable">
					<caption> ${depth2.newSname} 담당자표</caption>
					<colgroup>
						<col style="width:10%" />
						<col style="width:15%" />
						<col style="width:50%" />
						<col style="width:20%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">성명</th>
							<th scope="col">직위</th>
							<th scope="col">주요업무</th>
							<th scope="col">연락처</th>
						</tr>
					</thead>
						<c:forEach var="staff" items="${depth2.empList}">
							<c:set var="usrname" value="${fn:split(staff.usrNm, '(') }"/>
							<tr>
								<td>${usrname[0]}${staff.positNm}</td>
								<td>${staff.blgTeamNm}</td>
								<td>
									<ul class="tableUl">
										<li>${fn:replace(staff.adiInfo7, newLineChar, "</li><li>")}</li>
									</ul>
								</td>
								<td>${staff.telno}</td>
							</tr>
						</c:forEach>
					
				</table>
				</div>
				<div class="gap20"></div>
			</c:if>
			<c:forEach var="depth3" items="${result}">
				<c:if test="${depth3.newDepth == 3 and depth3.newParent == depth2.depCode}">
					<h4 class="c-tit02">${depth3.newSname}</h4>
					<br />
					<p class="scroll_txt">좌우로 스크롤 하세요</p>
					<br />
					<div class="orgTable_wrap jobTable_wrap">
						<table class="orgTable jobTable">
							<caption>담당자테이블</caption>
							<colgroup>
								<col style="width:10%" />
								<col style="width:15%" />
								<col style="width:50%" />
								<col style="width:20%" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col">성명</th>
									<th scope="col">직위</th>
									<th scope="col">주요업무</th>
									<th scope="col">연락처</th>
								</tr>						
							</thead>
							<tbody>
								<c:forEach var="staff" items="${depth3.empList}">
								<c:set var="usrname" value="${fn:split(staff.usrNm, '(') }"/>
								<tr>
									<td>${usrname[0]}${staff.positNm}</td>
									<td>${staff.blgTeamNm}</td>
									<td>
										<ul class="tableUl">
											<li>${fn:replace(staff.adiInfo7, newLineChar, "</li><li>")}</li>
										</ul>
									</td>
									<td>${staff.telno}</td>
								</tr>
						</c:forEach>
							
							</tbody>
						</table>
					</div>	
					<div class="gap20"></div>
				</c:if>
			</c:forEach>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<p class="depth2-title">"${searchVO.searchTxt}" 에 대한 검색결과</p>
			<p class="scroll_txt">좌우로 스크롤 하세요</p>
				<br />
				<div class="orgTable_wrap jobTable_wrap">
					<table class="orgTable jobTable">
					<caption>검색어 "${searchVO.searchTxt}" 에 대한 검색결과로 부서, 직위, 주요업무, 연락처로 이루어진 업무 담당자표</caption>
					<colgroup>
						<col style="width:10%" />
						<col style="width:20%" />
						<col style="width:13%" />
						<col style="width:37%" />
						<col style="width:20%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">성명</th>
							<th scope="col">부서</th>
							<th scope="col">직위</th>
							<th scope="col">주요업무</th>
							<th scope="col">연락처</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="emp" items="${empList}">
						<c:set var="usrname" value="${fn:split(emp.usrNm, '(') }"/>
						<tr>
							<td>${usrname[0]}${emp.positNm}</td>
							<c:set var="d" value="${fn:split(emp.realUseDepNm, ' ')}" />
							<c:set var="l" value="${fn:length(d)}" />
							<td>
								<c:if test="${l > 1}">
									<c:forEach var="el" items="${d}" varStatus="status">
										<c:if test="${status.index < l-1}">
											${el}&nbsp;
										</c:if>
										<c:if test="${status.index == l-1}">
											(${el})
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${l <= 1}">
									${emp.realUseDepNm}
								</c:if>
							</td>
							<td style="text-align:center;">${emp.blgTeamNm}</td>
							<td style="text-align:left;">
								<ul class="tableUl">
									<li>${fn:replace(emp.adiInfo7, newLineChar, "</li><li>")}</li>
								</ul>
							</td>
							<td>${emp.telno}</td>
						</tr>
					</c:forEach>
					</tbody>
			</table>
		</div>	
		<c:if test="${empty empList}">
			<div class="gap"></div>	
			<div class="no_data">검색결과가 없습니다.</div>
		</c:if>
		
		
		</c:otherwise>
</c:choose> 
		 
<div class="gap80"></div>
		<!--콘텐츠 끝--></div>
		</div>
