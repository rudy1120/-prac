<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 블로그 게시글 목록
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
<ul id="blog_articles_table" class="blog_list">
	<li class="bloglist_header">
		<c:if test="${bbsConfigVO.ptBundleDelYn == 'Y' && !p_fromView}">
			<input type="checkbox" name="getCheckedAll" id="getCheckedAll"/>
		</c:if>
		<div>이미지</div>
		<span class="list_title">내용</span>
	</li>
	<c:if test="${not empty elements}">
		<c:forEach var="element" items="${elements}" varStatus="status">
			<li>
				<%-- ====================== 일괄삭제 ====================== --%>
				<c:if test="${useBundleDel && !p_fromView}">
					<input type="checkbox" name="delChecked" id="delChecked" value="${element.bIdx}" class="bloglist_inputbox"/>
				</c:if>
				<a href="#" onclick="goTo.view('${formId}', '${element.bIdx}', '${element.bPublic}'); return false;">
					<%-- ====================== 이미지 ====================== --%>
					<div>
						<c:choose>
							<c:when test="${not empty element.attachId}">
								<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${element.attachId}"/>
									<c:param name="mode" value="ratio"/>
									<c:param name="width" value="250"/>
									<c:param name="height" value="220"/>
									<c:param name="all" value="${false}"/>
								</c:import>
							</c:when>
							<c:otherwise>
								<img alt="이미지 없음" src="/common/img/board/sample_noimage.jpg" width="160" height="120"/>
							</c:otherwise>
						</c:choose>
					</div>
					<%-- ====================== 부서/분류 정보 세팅 ====================== --%>
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
					<%-- ====================== 제목 ====================== --%>
					<span class="list_title <c:if test="${element.isDel == 'Y'}">list_del</c:if>">
						<c:if test="${element.isDel == 'Y'}"><span class="red">[삭제]</span></c:if>
						<c:if test="${useCategory}">
							[${fn:trim(dept_name)}]
						</c:if>
						${element.bTitle} <c:if test="${bbsConfigVO.ptCommentYn == 'Y' && element.cmtCnt > 0}">[${element.cmtCnt}]</c:if>
					</span>
					<%-- ====================== 내용 ====================== --%>
					<span class="list_txt">
						<c:set var="length" value="${fn:length(element.bContent)}"/>
						<c:choose>
							<c:when test="${length > 150}">
								<c:out value="${fn:substring(element.bContent,0,150)}..."/>
							</c:when>
							<c:otherwise>
								${element.bContent}
							</c:otherwise>
						</c:choose>
					</span>
					<%-- ====================== 작성일 ====================== --%>
					<span class="list_data">작성일 <fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/> 조회수 ${element.bCnt}</span>
					<span class="cleB"></span>
				</a>
			</li>
		</c:forEach>
	</c:if>
	<c:if test="${empty elements}">
		<li class="no_data">등록된 게시물이 없습니다.</li>
	</c:if>
</ul>

<c:if test="${not empty elements}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>
