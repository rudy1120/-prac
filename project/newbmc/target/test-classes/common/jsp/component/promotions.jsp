<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 홍보 자료 출력
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.07.20		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.07.20
	 */
%>
<c:forEach var="element" items="${promotionList}">
	<div>
		<c:if test="${not empty element.prmtUrl}">
			<a href="${fn:escapeXml(element.prmtUrl)}" <c:if test="${element.blankYn == 'Y'}">target="_blank" title="새창열림"</c:if>>
		</c:if>
		<c:choose>
			<c:when test="${element.prmtType == 'B'}">${element.prmtName}</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${element.prmtTemplate != '0' && element.fileCnt == 0}">
						<img src="/common/img/basic/promotion/${fn:toLowerCase(searchVO.type.path)}_template${element.prmtTemplate}.jpg" alt="${searchVO.type.desc} 템플릿${element.prmtTemplate}"/>
					</c:when>
					<c:otherwise>
						<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${element.attachId}"/>
							<c:param name="mode" value="origin"/>
						</c:import>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		<c:if test="${not empty element.prmtUrl}">
			</a>
		</c:if>
	</div>
</c:forEach>
