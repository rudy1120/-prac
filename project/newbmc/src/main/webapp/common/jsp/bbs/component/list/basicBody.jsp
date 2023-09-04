<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 일반 게시글 목록(BODY)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.16		J.Ryeon Lee		컴포넌트화, script 분리
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.05.16
	 */
%>

<c:set var="formId" value="${not empty p_formId ? p_formId : 'list'}"/>
<c:if test="${not empty elements}">
	<c:forEach var="element" items="${elements}" varStatus="status">
		<tr>
			<%-- ====================== 번호 ====================== --%>
			<td class="list_num">
				<c:choose>
					<c:when test="${element.bNotice == 'Y' && isNoti == 'Y'}"><img src="/common/img/board/icon_notice.gif" alt="공지"/></c:when>
					<c:otherwise>${listOrder - status.index}</c:otherwise>
				</c:choose>
			</td>
			<%-- ====================== 분류 ====================== --%>
			<c:if test="${useCategory && useCustom}">
				<td>${element.bCategory}</td>
			</c:if>
			<%-- ====================== 제목 ====================== --%>
			<td class="list_tit">
				<c:if test="${element.bLevel != 0}">
					<span style="width:${element.bLevel*10}px"></span>
					<img src="/common/img/board/ico_reply.gif" alt="답글">
					<c:if test="${element.bDeptNm != 'null' && empty element.bPrivatecode}">
						<strong>시 답변</strong>
					</c:if>
				</c:if>
				<a href="#" onclick="goTo.view('${formId}','${element.bIdx}','${bbsConfigVO.ptIdx}','${menuVO.mId}'); return false;">
					<c:set var="bTitle" value="${element.bTitle}"/>
					<c:if test="${fn:length(bTitle) > 60}"><c:set var="bTitle" value="${fn:substring(element.bTitle, 0, 60)}..."/></c:if>
					<c:choose>
						<c:when test="${(element.bNotice == 'Y' && isNoti == 'Y') || element.bIdx == param_bIdx}"><b>${bTitle}</b></c:when>
						<c:otherwise>${bTitle}</c:otherwise>
					</c:choose>
					<c:if test="${bbsConfigVO.ptCommentYn == 'Y' && element.cmtCnt != 0}">[${element.cmtCnt}]</c:if>
					<c:if test="${element.newMode == 'Y'}">&nbsp;
						<img src="/common/img/board/ico_new.gif" alt="새글" class="ico_new">
					</c:if>
				</a>
			</td>
			<%-- ====================== 첨부파일 ====================== --%>
			<td class="list_file">
				<c:set var="fileExts" value="${element.fileExts}" scope="request"/>
				<jsp:include page="/common/jsp/bbs/component/fileExtImg.jsp"/>
			</td>
			<%-- ====================== 부서 ====================== --%>
			<c:if test="${useCategory && useDept}">
				<td class="list_part">
					<c:set var="dispDeptNm" value=""/>
					<c:if test="${not empty element.bDeptGroup}">
						<c:forEach var="code" items="${deptList}" >
							<c:if test="${fn:indexOf(code.fullChildPath, element.bDeptGroup) > -1}">
								<c:set var="dispDeptNm" value="${code.deptName}"/>
							</c:if>
						</c:forEach>
					</c:if>
					${empty dispDeptNm ? element.bDeptNm : dispDeptNm}
				</td>
			</c:if>
			<%-- ====================== 작성자 ====================== --%>
			<td class="list_write">
				<c:choose>
					<c:when test="${element.bPublic == 'N' && not empty element.bPrivatecode}">
						${fn:substring(element.bWrite, 0, 1)}**
					</c:when>
					<%-- <c:otherwise>${element.bWrite}</c:otherwise> --%>
					<c:otherwise>${element.rltdDeptNm}</c:otherwise>
				</c:choose>
			</td>
			<%-- ====================== 등록일 ====================== --%>
			<td class="list_date">
			<c:if test="${empty element.bTermSdate}">
				<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
			</c:if>
			<c:if test="${not empty element.bTermSdate}">
				<c:if test="${fn:length(element.bTermSdate) >= 10}">
					${fn:substring(element.bTermSdate, 0 ,10)}
				</c:if>
			</c:if>
			</td>
			<%-- ====================== 조회수 ====================== --%>
			<td class="list_hit">${element.bCnt}</td>
		</tr>
	</c:forEach>
</c:if>
