<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:set var="formId" value="${not empty p_formId ? p_formId : 'list'}"/>
<c:if test="${not empty result}">
	<div class="bod_movie">
		<ul class="clFix">
			<c:forEach var="element" items="${result}" varStatus="status">
				<li>
					<c:if test="${not empty element.bYoutube}">
						<a href="https://youtu.be/${element.bYoutube}" target="_blank" title="${element.bTitle} 새 창 열기">
							<div class="thumb">
								<figure>
									<img src="https://img.youtube.com/vi/${element.bYoutube}/mqdefault.jpg" alt="${element.bTitle}" />
								</figure>
							</div>
					</c:if>
					<c:if test="${empty element.bYoutube}">
						<a href="#" onclick="goTo.view('${formId}','${element.bIdx}','${bbsConfigVO.ptIdx}','${menuVO.mId}'); return false;" data-action="/${siteCodeFull}/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}">
							<div class="thumb">
								<figure>
									<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${element.attachId}"/>
									<c:param name="mode" value="movieThumbnail"/>
									<c:param name="width" value="250"/>
									<c:param name="height" value="220"/>
									</c:import>
								</figure>
							</div>
					</c:if>
						<div class="cont">
							<c:if test="${useCategory}">
								<c:set var="categoryInfo" value=""/>
								<span class="category">
									<c:if test="${useDept}">
										<c:forEach var="d" items="${deptList}">
											<c:if test="${d.deptId == element.bDeptGroup}"><c:set var="categoryInfo" value="[${d.deptName}]"/></c:if>
										</c:forEach>
									</c:if>
									<c:if test="${useCustom}">
										<c:set var="categoryInfo" value="${categoryInfo}[${element.bCategory}]"/>
									</c:if>
									${categoryInfo}
								</span>
							</c:if>
							<span class="tit">${element.bTitle}</span>
							<span class="date">
								<c:if test="${empty element.bTermSdate}">
									<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
								</c:if>
								<c:if test="${not empty element.bTermSdate}">
									<c:if test="${fn:length(element.bTermSdate) >= 10}">
										${fn:substring(element.bTermSdate, 0 ,10)}
									</c:if>
								</c:if>
							</span>
						</div>
					</a>
				</li>
			</c:forEach>
			<c:if test="${empty result}">
				<li class="no_data"><span>등록된 게시물이 없습니다.</span></li>
			</c:if>
		</ul>
	</div>
</c:if>

<c:if test="${not empty result}">
	<div class="bod_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
	</div>
</c:if>
