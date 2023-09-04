<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 참가자 리스트 조회
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
 */
%>

<h2>${receiptContVO.title} 접수자 목록</h2>
<form:form commandName="searchVO" name="list" id="list" methodParam="post" onclick="$('#page').val(1);">
	<form:hidden path="page"/>

	<table class="tableSt_list row_over mB60">
		<caption>접수자 목록 조회화면입니다.</caption>
		<thead>
			<tr>
				<th scope="col">구분</th>
				<th scope="col">제목</th>
				<th scope="col">기간</th>
				<th scope="col">접수인원</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
				<c:choose>
					<c:when test="${receiptContVO.gbn == 'A'}">
						기능인재 접수
					</c:when>
					<c:when test="${receiptContVO.gbn == 'B'}">
						기간제직원 접수
					</c:when>
				</c:choose>
				</td>
				<td>${receiptContVO.title}</td>
				<td>${receiptContVO.sdate} ~ ${receiptContVO.edate}</td>
				<td>${totalCnt}</td>
			</tr>
		</tbody>
	</table>
	
	<c:if test="${receiptContVO.gbn eq 'A'}">
	
		<div class="search_box">
			<div class="fL">
				<label for="searchType" class="hidden">검색조건선택</label>
				<form:select path="searchType">
					<form:option value="snm">소속학교</form:option>
					<form:option value="tnm">담당교사</form:option>
				</form:select>
				<form:input path="searchTxt"/>
				<input type="submit"  class="btn_white" value="검색"/>
			</div>
			<div class="fR">
				총 <span class="bold blue">${totalCnt}</span>건의 데이터가 있습니다.
			</div>
			<div class="cleB"></div>
		</div>
	
		<table class="tableSt_list row_over">
			<caption>참여자 명단</caption>
			<thead>
				<tr>
					<th scope="col" class="w11">소속학교</th>
					<th scope="col" class="w5">담당교사</th>
					<th scope="col" class="w10">핸드폰 번호</th>
					<th scope="col" class="w11">추천심사위원회<br/>의결서</th>
					<th scope="col" class="w11">추천현황표</th>
					<th scope="col" class="w5">추천학생</th>
					<th scope="col" class="w11">기능인재추천서</th>
					<th scope="col" class="w11">개인정보수집 및 <br/>위탁동의서</th>
					<th scope="col" class="w11">성적증명서</th>
					<th scope="col" class="w11">졸업(예정)증명서</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="totcnt" value="1" />
				<c:set var="bfsh" value="" />
				<c:forEach var="e" items="${result}" varStatus="status">
					<tr>
						<c:if test="${e.tCnt ne '1' && totcnt eq '1' &&  e.school ne bfsh }">
							<c:set var="totcnt" value="${e.tCnt}" />
						</c:if>		
						<c:if test="${e.school ne bfsh }">
							<c:set var="bfsh" value="${e.school}" />	
						</c:if>
		
						<c:if test="${e.tCnt eq totcnt }">
							<td rowspan="${e.tCnt}">${e.school}</td>
							<td rowspan="${e.tCnt}">${e.tname}</td>
							<td rowspan="${e.tCnt}">${e.tel}</td>
							<!-- 추천심사위원회 의결서 -->
							<td rowspan="${e.tCnt}" class="list_file">
							<c:set var="fileExts" value="${e.resolution}" scope="request"/>
								<c:if test="${!empty fileExts}">
									<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
										<c:param name="showDownload" value="Y"/>
										<c:param name="updateFlag" value="N"/>
										<c:param name="downloadYn" value="Y"/>
										<c:param name="param_atchFileId" value="${fileExts}"/>
									</c:import>
								</c:if>
								<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
							</td>
							<!-- 추천현황표 -->
							<td rowspan="${e.tCnt}" class="list_file">
							<c:set var="fileExts" value="${e.recStatus}" scope="request"/>
								<c:if test="${!empty fileExts}">
									<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
										<c:param name="showDownload" value="Y"/>
										<c:param name="updateFlag" value="N"/>
										<c:param name="downloadYn" value="Y"/>
										<c:param name="param_atchFileId" value="${fileExts}"/>
									</c:import>
								</c:if>
								<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
							</td>	
						</c:if>
							
						<c:if test="${e.tCnt ne '1' && totcnt ne '1'  }">
							<c:set var="totcnt" value="${totcnt - 1}" />
						</c:if>
	
						
						<td>${e.sname}</td>
						<!-- 기능인재추천서 -->
						<td class="list_file">
						<c:set var="fileExts" value="${e.attach1}" scope="request"/>
							<c:if test="${!empty fileExts}">
								<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
									<c:param name="showDownload" value="Y"/>
									<c:param name="updateFlag" value="N"/>
									<c:param name="downloadYn" value="Y"/>
									<c:param name="param_atchFileId" value="${fileExts}"/>
								</c:import>
							</c:if>
							<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
						</td>
						<!-- 개인정보수집 및 위탁동의서 -->
						<td class="list_file">
						<c:set var="fileExts" value="${e.attach2}" scope="request"/>
							<c:if test="${!empty fileExts}">
								<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
									<c:param name="showDownload" value="Y"/>
									<c:param name="updateFlag" value="N"/>
									<c:param name="downloadYn" value="Y"/>
									<c:param name="param_atchFileId" value="${fileExts}"/>
								</c:import>
							</c:if>
							<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
						</td>
						<!-- 성적증명서 -->
						<td class="list_file">
						<c:set var="fileExts" value="${e.attach3}" scope="request"/>
							<c:if test="${!empty fileExts}">
								<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
									<c:param name="showDownload" value="Y"/>
									<c:param name="updateFlag" value="N"/>
									<c:param name="downloadYn" value="Y"/>
									<c:param name="param_atchFileId" value="${fileExts}"/>
								</c:import>
							</c:if>
							<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
						</td>
						<!-- 졸업(예정)증명서 -->
						<td class="list_file">
						<c:set var="fileExts" value="${e.attach4}" scope="request"/>
							<c:if test="${!empty fileExts}">
								<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
									<c:param name="showDownload" value="Y"/>
									<c:param name="updateFlag" value="N"/>
									<c:param name="downloadYn" value="Y"/>
									<c:param name="param_atchFileId" value="${fileExts}"/>
								</c:import>
							</c:if>
							<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	
	<c:if test="${receiptContVO.gbn eq 'B'}">
		<div class="search_box">
			<div class="fL">
				<label for="searchType" class="hidden">검색조건선택</label>
				<form:select path="searchType">
					<form:option value="tnm">지원자</form:option>
					<form:option value="tel">연락처</form:option>
				</form:select>
				<form:input path="searchTxt"/>
				<input type="submit"  class="btn_white" value="검색"/>
			</div>
			<div class="fR">
				총 <span class="bold blue">${totalCnt}</span>건의 데이터가 있습니다.
			</div>
			<div class="cleB"></div>
		</div>
	
		<table class="tableSt_list row_over">
			<caption>접수자 명단</caption>
			<thead>
				<tr>
					<th scope="col" class="w5">접수번호</th>
					<th scope="col" class="w5">지원자</th>
					<th scope="col" class="w10">핸드폰 번호</th>
					<th scope="col" class="w2">입사지원서</th>
					<th scope="col" class="w10">최종업로드 일시</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${result}" varStatus="status">
					<tr>
						<td>${listOrder - status.index}</td>
						<td>${e.tname}</td>
						<td>${e.tel}</td>
						<!-- 추천심사위원회 의결서 -->
						<td class="list_file">
						<c:set var="fileExts" value="${e.attachId}" scope="request"/>
							<c:if test="${!empty fileExts}">
								<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
									<c:param name="showDownload" value="Y"/>
									<c:param name="updateFlag" value="N"/>
									<c:param name="downloadYn" value="Y"/>
									<c:param name="param_atchFileId" value="${fileExts}"/>
								</c:import>
							</c:if>
							<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
						</td>
						<c:if test="${empty e.updateDate}">
							<td>${e.createDate}</td>
						</c:if>
						<c:if test="${!empty e.updateDate}">
							<td>${e.updateDate}</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${empty result}">
		<div class="no_data">참여자가 없습니다.</div>
	</c:if>
</form:form>

<c:if test="${not empty result}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>

<div class="btn_boxR">
	<a href="/sys/receipt/list.do?mId=${menuVO.mId}" class="btn_dblue">목록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script> 
