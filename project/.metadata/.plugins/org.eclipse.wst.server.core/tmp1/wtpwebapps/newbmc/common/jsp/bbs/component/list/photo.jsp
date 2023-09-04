<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:set var="formId" value="${not empty p_formId ? p_formId : 'list'}"/>
<div class="bod_photo">
	<ul class="clFix">
		<c:forEach var="element" items="${result}" varStatus="status">
			
			<li style="
						<c:if test='${element.ptIdx eq 889}'>width:50%;</c:if>
      				  ">
      				  
				<a href="#" onclick="goTo.view('${formId}','${element.bIdx}','${bbsConfigVO.ptIdx}','${menuVO.mId}'); return false;" data-action="/${siteCodeFull}/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}">
					<div class="thumb">
						<c:choose>
	  						<c:when test="${element.ptIdx eq 889}" >
      							<figure>
									<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${element.attachId}"/>
									<c:param name="mode" value="ratio"/>
									<c:param name="width" value="500"/>
									<c:param name="height" value="440"/>
									<c:param name="all" value="${false}"/>
									<c:param name="showComment" value="${true}"/>
									</c:import>
								</figure>
      						</c:when> 
            				<c:otherwise>
	      						<figure>
									<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${element.attachId}"/>
									<c:param name="mode" value="ratio"/>
									<c:param name="width" value="250"/>
									<c:param name="height" value="220"/>
									<c:param name="all" value="${false}"/>
									<c:param name="showComment" value="${true}"/>
									</c:import>
								</figure>
      						</c:otherwise> 
						</c:choose> 
					</div>
					<div class="cont">
						<c:if test="${useCategory}">
							<c:set var="categoryInfo" value=""/>
							<span class="category">
								<c:if test="${useDept}">
									<c:forEach var="d" items="${deptList}">
										<c:if test="${d.deptId == element.bDeptGroup}"><c:set var="categoryInfo" value="[${d.deptName}]"/></c:if>
									</c:forEach>
								</c:if>
								<c:if test="${useCustom}"><c:set var="categoryInfo" value="${categoryInfo}[${element.bCategory}]"/></c:if>
								${categoryInfo}
							</span>
						</c:if>
						<span class="tit">${element.bTitle}</span>
						<span class="date"><c:if test="${empty element.bTermSdate}">
				<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
			</c:if>
			<c:if test="${not empty element.bTermSdate}">
				<c:if test="${fn:length(element.bTermSdate) >= 10}">
					${fn:substring(element.bTermSdate, 0 ,10)}
				</c:if>
			</c:if></span>
					</div>
				</a>
			</li>
		</c:forEach>
		<c:if test="${empty result}">
			<li class="no_data"><span>등록된 게시물이 없습니다.</span></li>
		</c:if>
	</ul>
</div>

<c:if test="${not empty result}">
	<div class="bod_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
	</div>
</c:if>
