<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 민원 게시글 목록(BODY)
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
					<c:when test="${element.bNotice == 'Y'}"><img src="/common/img/board/icon_notice.gif" alt="공지글"/></c:when>
					<c:otherwise>${listOrder - status.index}</c:otherwise>
				</c:choose>
			</td>
			<%-- ====================== 분야 ====================== --%>
			<c:if test="${useCategory && useCustom}">
				<td>${element.bCategory}</td>
			</c:if>
			<%-- ====================== 제목 ====================== --%>
			<td class="list_tit txtL">
				<a href="#" onclick="goTo.view('${formId}','${element.bIdx}','${bbsConfigVO.ptIdx}','${menuVO.mId}'); return false;" data-action="/${siteCodeFull}/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}">
					<c:set var="bTitle" value="${element.bTitle}"/>
					<c:if test="${fn:length(bTitle) > 20}"><c:set var="bTitle" value="${fn:substring(element.bTitle, 0, 20)}..."/></c:if>
					${bTitle}<c:if test="${not empty element.cmtCnt && element.cmtCnt != '0'}"> [${element.cmtCnt}]</c:if>
				</a>
			</td>
			<%-- ====================== 첨부파일 ====================== --%>
			<td class="list_file">
				<c:set var="fileExts" value="${element.fileExts}" scope="request"/>
				<jsp:include page="/common/jsp/bbs/component/fileExtImg.jsp"/>
			</td>
			<%-- ====================== 담당부서 ====================== --%>
			<c:if test="${useCategory && useDept}">
				<td>
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
				<c:set var="bWrite" value="${element.bWrite}"/>
				<c:choose>
					<c:when test="${element.bPublic == 'N' && not empty element.bPrivatecode}">${fn:substring(bWrite, 0, 1)}**</c:when>
					<c:otherwise>${bWrite}</c:otherwise>
				</c:choose>
			</td>
			<%-- ====================== 답변 ====================== --%>
			<td class="list_answer">
				<c:choose>
					<c:when test="${element.bNotice == 'Y'}">&nbsp;</c:when>
					<c:when test="${element.replyCnt == 0}"><span>답변 대기</span></c:when>
					<c:otherwise><span class="blue">답변 완료</span></c:otherwise>
				</c:choose>
			</td>
			<%-- ====================== 등록일 ====================== --%>
			<td class="list_date"><fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/></td>
			<%-- ====================== 조회수 ====================== --%>
			<td class="list_hit">${element.bCnt}</td>
		</tr>
	</c:forEach>
</c:if>
