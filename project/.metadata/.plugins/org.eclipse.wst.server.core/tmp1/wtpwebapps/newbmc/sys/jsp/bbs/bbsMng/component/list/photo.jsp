<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 사진 게시글 목록
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
<c:set var="elements" value="${result}"/>
<div class="list_gellery">
	<ul>
		<c:if test="${not empty elements}">
			<c:forEach var="element" items="${elements}" varStatus="status">
				<li>
					<a href="#" onclick="goTo.view('${formId}', '${element.bIdx}', '${element.bPublic}'); return false;">
						<div>
							<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${element.attachId}"/>
								<c:param name="mode" value="ratio"/>
								<c:param name="width" value="260"/>
								<c:param name="height" value="220"/>
								<c:param name="all" value="${false}"/>
							</c:import>
						</div>
					</a>
					<c:if test="${useBundleDel && !p_fromView}">
						<input type="checkbox" id="delChecked${element.bIdx}" name="delChecked" value="${element.bIdx}"/>
					</c:if>
					<c:set var="dept_name" value=""/>
					<c:if test="${useCategory}">
						<c:forEach var="code" items="${deptList}">
							<c:if test="${code.deptId == element.bDeptGroup}">
								<c:set var="dept_name" value="${code.deptName}"/>
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${useDept && useCustom}"><c:set var="dept_name" value="${dept_name} ${element.bCategory}"/></c:when>
							<c:when test="${useDept}"><c:set var="dept_name" value="${dept_name}"/></c:when>
							<c:otherwise><c:set var="dept_name" value="${element.bCategory}"/></c:otherwise>
						</c:choose>
						<c:if test="${empty dept_name}"><c:set var="dept_name" value="-"/></c:if>
					</c:if>
					<label for="delChecked${element.bIdx}">
						<span <c:if test="${element.isDel == 'Y'}">class="list_del"</c:if>>
							<c:if test="${useCategory}">[${dept_name}]</c:if>
							<c:if test="${element.isDel == 'Y'}"><span class="red">[삭제]</span></c:if>
							${element.bTitle}
							<c:if test="${useComment && element.cmtCnt > 0}">[${element.cmtCnt}]</c:if>
						</span>
					</label>
				</li>
			</c:forEach>
		</c:if>
		<c:if test="${empty elements}">
			<li class="no_data">등록된 게시물이 없습니다.</li>
		</c:if>
	</ul>
	<div class="cleB"></div>
</div>

<c:if test="${not empty elements}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>
