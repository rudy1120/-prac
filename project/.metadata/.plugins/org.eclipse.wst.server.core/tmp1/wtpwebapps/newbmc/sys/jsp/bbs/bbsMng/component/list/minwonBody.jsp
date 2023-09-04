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
			<%-- ====================== 일괄삭제 ====================== --%>
			<c:if test="${useBundleDel && !p_fromView}">
				<td>
					<input type="checkbox" id="delChecked${element.bIdx}" name="delChecked" value="${element.bIdx}"/>
				</td>
			</c:if>
			<%-- ====================== 번호 ====================== --%>
			<td>
				<c:if test="${element.bNotice == 'Y'}">[공지]</c:if>
				<c:if test="${element.bNotice != 'Y'}">${listOrder - status.index}</c:if>
			</td>
			<%-- ====================== 분야 ====================== --%>
			<c:if test="${useCategory && useCustom}">
				<td>${element.bCategory}</td>
			</c:if>
			<%-- ====================== 제목 ====================== --%>
			<td class="taL">
				<a href="#" onclick="goTo.view('${formId}', '${element.bIdx}', '${element.bPublic}'); return false;" <c:if test="${element.isDel == 'Y'}">class="list_del"</c:if>>
					<c:if test="${element.bNotice == 'Y'}"><b>${element.bTitle}</b></c:if>
					<c:if test="${element.bNotice != 'Y'}">${element.bTitle}<c:if test="${not empty element.cmtCnt && element.cmtCnt != '0'}"> [${element.cmtCnt}]</c:if></c:if>
				</a>
			</td>
			<%-- ====================== 담당부서 ====================== --%>
			<c:if test="${useCategory && useDept}">
				<td>
					<c:set var="dispDeptNm" value=""/>
					<c:if test="${not empty element.bDeptGroup}">
						<c:forEach var="code" items="${deptList}">
							<c:if test="${code.deptId == element.bDeptGroup}">
								<c:set var="dispDeptNm" value="${code.deptName}"/>
							</c:if>
						</c:forEach>
					</c:if>
					${empty dispDeptNm ? element.bDeptNm : dispDeptNm}
				</td>
			</c:if>
			<%-- ====================== 작성자 ====================== --%>
			<td>${element.bWrite}</td>
			<%-- ====================== 공개 ====================== --%>
			<td>${element.bPublic == 'N' ? '비공개' : '공개'}</td>
			<%-- ====================== 답변 ====================== --%>
			<td>
				<c:if test="${element.replyCnt == 0}"> - </c:if>
				<c:if test="${element.replyCnt != 0}"><font class="blue">답변</font></c:if>
			</td>
			<%-- ====================== 작성일 ====================== --%>
			<td>
				<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
			</td>
			<%-- ====================== 첨부파일 ====================== --%>
			<td>
				<c:set var="fileExts" value="${element.bodFileExt}" scope="request"/>
				<jsp:include page="/common/jsp/bbs/component/fileExtImg.jsp"/>
			</td>
		</tr>
	</c:forEach>
</c:if>
