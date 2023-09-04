<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 조회 리스트 화면
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
<style>
	a.btn_dblue {
		padding: 8px 13px;
		vertical-align: middle;
		line-height: 19px;
		display: inline-block;
		border-radius: 3px;
		background: #3e4959;
		color: #ffffff !important;
		letter-spacing: -1px;
		border: 0px;
	}
</style>

<jsp:useBean id="currTime" class="java.util.Date" />
<fmt:formatDate var="currTime" value="${currTime}" pattern="yyyy-MM-dd" />

<div id="contents"><div class="content">
	
	<div class="gap20"></div>
	<p class="depth2-title">${receiptVO.tname}님 조회 결과</p>

<div class="gap"></div>

<form:form commandName="receiptVO" name="list" id="list" methodParam="post" onsubmit="$('#page').val(1);">
	<form:hidden path="page"/>
	<input type="hidden" name="tname" value="${receiptVO.tname}"/>
	<input type="hidden" name="tel" value="${receiptVO.tel}"/>
	
	<div class="bod_head clFix">
<!-- 		<p class="page_num"> -->
<%-- 			총 <span class="bold blue">${totalCnt}</span>건의 데이터가 있습니다. --%>
<!-- 		</p> -->
	</div>
	<div class="gap"></div>
	<div class="orgTable_wrap jobTable_wrap">
	ㅇㄴㄻㅇㄴ롱ㄴㄹ;ㅑㅐ
		<c:if test="${receiptVO.gbn eq 'A'}">
			<table class="orgTable jobTable">
				<caption>기능인재 접수 목록</caption>
				<thead>
					<tr>
<!-- 						<th width="6%">삭제</th> -->
						<th width="10%">소속학교</th>
						<th width="7%">이름</th>
						<th width="10%">연락처</th>
						<th width="10%">추천심사위원회 의결서</th>
						<th width="10%">추천현황표</th>
						<th width="7%">추천학생</th>
						<th width="10%">기능인재추천서</th>
						<th width="10%">개인정보수집 및 위탁동의서</th>
						<th width="10%">성적증명서</th>
						<th width="10%">졸업(예정)증명서</th>
						<th width="6%">삭제</th>
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
<%-- 								<td rowspan="${e.tCnt}"> --%>
<%-- 									<c:choose> --%>
<%-- 										<c:when test="${currTime < e.edate}">		 --%>
<%-- 											<a href="#" data-action="/bmc/receipt/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'sttidx': ${e.sttidx}}" onclick="req.ajax(this); return false;" class="btn_sblack"> --%>
<!-- 												삭제 -->
<!-- 											</a> -->
<%-- 										</c:when> --%>
<%-- 										<c:otherwise> --%>
<!-- 											마감  -->
<%-- 										</c:otherwise> --%>
<%-- 									</c:choose> --%>
<!-- 								</td> -->
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
								
<%-- 							<c:if test="${e.tCnt ne '1' && totcnt ne '1'  }"> --%>
<%-- 								<c:set var="totcnt" value="${totcnt - 1}" /> --%>
<%-- 							</c:if> --%>
		
							
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
							<c:if test="${e.tCnt eq totcnt }">
								<td rowspan="${e.tCnt}">
									<c:choose>
										<c:when test="${currTime < e.edate}">		
											<a href="#" data-action="/bmc/receipt/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'sttidx': ${e.sttidx}}" onclick="req.ajax(this); return false;" class="btn_sblack">
												삭제
											</a>
										</c:when>
										<c:otherwise>
											마감 
										</c:otherwise>
									</c:choose>
								</td>
							</c:if>
							<c:if test="${e.tCnt ne '1' && totcnt ne '1'  }">
								<c:set var="totcnt" value="${totcnt - 1}" />
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${receiptVO.gbn eq 'B'}">
			<table class="orgTable jobTable">
			<caption>기간제직원 접수 목록</caption>
			<thead>
				<tr>
					<th width="5%">번호</th>
					<th width="10%">입사지원서</th>
					<th width="10%">최종 업로드일</th>
					<th width="10%">수정하기</th>
					<th width="10%">삭제하기</th>
				</tr>
			</thead>
			<c:forEach var="e" items="${result}" varStatus="status">
				<tr>
					<td>
						<c:set var="fileExts" value="${e.attachId}" scope="request"/>
						<c:if test="${!empty fileExts}">
							<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
								<c:param name="showDownload" value="Y"/>
								<c:param name="updateFlag" value="N"/>
								<c:param name="downloadYn" value="Y"/>
								<c:param name="param_atchFileId" value="${fileExts}"/>
							</c:import>
						</c:if>
					</td>
	<%-- 				<td>${fn:substring(e.createDate, 0, 10)}</td> --%>
					<td style="text-align: center">
						<c:if test="${empty e.updateDate}">
							${e.createDate}
						</c:if>
						<c:if test="${!empty e.updateDate}">
							${e.updateDate}
						</c:if>
					</td>
					<td>
						<c:choose>
							<c:when test="${currTime < e.edate}">		
								<a href="#" data-action="/bmc/receipt/update.do?mId=${menuVO.mId}" data-keyset="{'sttidx': ${e.sttidx}}" onclick="req.post(this); return false;" class="btn_sblack">
									수정
								</a>
							</c:when>
							<c:otherwise>
								마감
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${currTime < e.edate}">		
								<a href="#" data-action="/bmc/receipt/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'sttidx': ${e.sttidx}}" onclick="req.ajax(this); return false;" class="btn_sblack">
									삭제
								</a>
							</c:when>
							<c:otherwise>
								마감
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
	</div>
	<c:if test="${empty result}">
		<div class="gap"></div>	
		<div class="no_data">조회 자료가 없습니다.</div>
	</c:if>

<%-- 	<c:if test="${not empty result}"> --%>
<!-- 		<div class="bod_page"> -->
<%-- 			<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/> --%>
<!-- 		</div> -->
<%-- 	</c:if> --%>

</form:form>

		<div class="btn_wrap">
			<c:if test="${receiptVO.gbn eq 'A'}">
<%-- 				<input type="button" class="btn-blue" onclick="location.href='/bmc/receipt/view.do?idx=${result.idx}&mId=${menuVO.mId}'" value="취소"/> --%>
			</c:if>
			<c:if test="${receiptVO.gbn eq 'B'}">
<%-- 				<input type="button" class="btn-blue" onclick="location.href='/bmc/receipt/view.do?idx=${result.idx}&mId=${menuVO.mId}'" value="취소"/> --%>
			</c:if>
			<input type="button" class="btn-blue" onclick="location.href='/bmc/receipt/list.do?mId=${menuVO.mId}'" value="목록"/>
			
		</div>

</div></div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>