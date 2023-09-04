<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 목록 상세 (민원)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.15		J.Ryeon Lee		컴포넌트화
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.05.15
	 */
%>

<%-- ============================== 민원글 ============================== --%>
<table class="tableSt_write">
	<caption>게시판 상세 정보</caption>
	<colgroup>
		<col span="1" style="width:10%;"/>
		<col span="1"/>
		<col span="1" style="width:10%;"/>
		<col span="1" style="width:20%;"/>
		<col span="1" style="width:10%;"/>
		<col span="1" style="width:20%;"/>
	</colgroup>
	<tbody>
		<tr>
			<th scope="col" colspan="6" class="title">${bbsMngView.bTitle}</th>
		</tr>
		<tr>
			<th scope="row">작성일</th>
			<td><fmt:formatDate value="${bbsMngView.createDate}" pattern="yyyy-MM-dd"/></td>
			<th scope="row">작성자</th>
			<td>${bbsMngView.bWrite}</td>
			<th scope="row">조회수</th>
			<td>${bbsMngView.bCnt}</td>
		</tr>
		<tr>
			<th scope="row">주소</th>
			<td colspan="5">${bbsMngView.bAddr1} ${bbsMngView.bAddr2}</td>
		</tr>
		<tr>
			<th scope="row">휴대전화</th>
			<td colspan="5">${bbsMngView.bPhone}</td>
		</tr>
		<tr>
			<td colspan="6" class="cont_box">
				<c:if test="${showThumb && bbsMngView.fileCnt > 0}">
					<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${bbsMngView.attachId}"/>
						<c:param name="mode" value="origin"/>
						<c:param name="insertBr" value="${true}"/>
					</c:import>
				</c:if>
				<div class="mL20">
					<c:set var="content" value="${fn:replace(bbsMngView.bContent, '  ', '&nbsp;&nbsp;')}"/>
					${yh:nl2br(content)}
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row">첨부 파일</th>
			<td colspan="5" class="taL">
				<c:set var="hasFile" value="${not empty bbsMngView.fileCnt && bbsMngView.fileCnt != '0'}"/>
				<c:if test="${hasFile}">
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${bbsMngView.attachId}"/>
						<c:param name="convertYn" value="Y"/>
					</c:import>
				</c:if>
				<c:if test="${!hasFile}">파일이 없습니다.</c:if>
			</td>
		</tr>
	</tbody>
</table>

<c:set var="p_formId" value="viewForm" scope="request"/>
<c:set var="p_article" value="${bbsMngView}" scope="request"/>
<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/btn.jsp"/>

<%-- ============================== 답변 ============================== --%>
<c:if test="${not empty replyList}">
	<h3>답변</h3>
	<c:forEach var="answer" items="${replyList}" varStatus="status">
		<c:if test="${answer.isDel == 'N'}">
			<table class="tableSt_write">
				<caption>게시판 답변 상세 정보</caption>
				<colgroup>
					<col span="1" style="width:10%;"/>
					<col span="1"/>
					<col span="1" style="width:10%;"/>
					<col span="1" style="width:20%;"/>
					<col span="1" style="width:10%;"/>
					<col span="1" style="width:20%;"/>
				</colgroup>
				<tbody>
					<tr>
						<th scope="col" colspan="6" class="title">
							${answer.bTitle}
						</th>
					</tr>
					<tr>
						<th scope="row">작성일</th>
						<td><fmt:formatDate value="${answer.createDate}" pattern="yyyy-MM-dd"/></td>
						<th scope="row">작성자</th>
						<td colspan="2">
							<c:if test="${not empty bbsMngView.bWriteDeptNm}">[${bbsMngView.bWriteDeptNm}] </c:if>${bbsMngView.bWrite}<c:if test="${not empty bbsMngView.bWriteDeptTel}">(${bbsMngView.bWriteDeptTel})</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="6" class="cont_box">
							<c:if test="${fn:substring(bbsConfigVO.ptOutFields, 1, 2) eq 'Y'}">
								<c:if test="${answer.fileCnt > 0}">
									<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${answer.attachId}"/>
										<c:param name="mode" value="origin"/>
										<c:param name="insertBr" value="${true}"/>
									</c:import>
								</c:if>
							</c:if>
							<c:set var="r_content" value="${fn:replace(answer.bContent, '  ', '&nbsp;&nbsp;')}"/>
							${yh:nl2br(r_content)}
						</td>
					</tr>
					<tr>
						<th scope="row">첨부 파일</th>
						<td colspan="5" class="taL">
							<c:set var="hasFile" value="${not empty answer.fileCnt && answer.fileCnt != '0'}"/>
							<c:if test="${hasFile}">
								<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${answer.attachId}"/>
								</c:import>
							</c:if>
							<c:if test="${!hasFile}">파일이 없습니다.</c:if>
						</td>
					</tr>
				</tbody>
			</table>

			<c:set var="p_formId" value="viewForm" scope="request"/>
			<c:set var="p_article" value="${answer}" scope="request"/>
			<c:set var="p_parent" value="${bbsMngView}" scope="request"/>
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/btn.jsp"/>
		</c:if>
	</c:forEach>
</c:if>
