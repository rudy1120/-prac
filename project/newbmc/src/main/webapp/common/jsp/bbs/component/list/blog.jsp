<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:set var="formId" value="${not empty p_formId ? p_formId : 'list'}"/>
<div class="bod_blog">
	<ul>
		<c:if test="${not empty result}">
			<c:forEach var="element" items="${result}" varStatus="status">
				<c:set var="toggled_by_attachId" value="area_img"/>
				<li>
					<a href="#" onclick="goTo.view('${formId}','${element.bIdx}','${bbsConfigVO.ptIdx}','${menuVO.mId}'); return false;" data-action="/${siteCodeFull}/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}">
						<div class="clFix">
							<div class="thumb">
								<figure>
									<c:choose>
										<c:when test="${empty element.attachId}">
											<img alt="이미지 없음" src="${pageContext.request.contextPath}/common/img/board/sample_noimage.jpg"/>
										</c:when>
										<c:otherwise>
											<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
												<c:param name="param_atchFileId" value="${element.attachId}"/>
												<c:param name="mode" value="ratio"/>
												<c:param name="width" value="240"/>
												<c:param name="height" value="220"/>
												<c:param name="all" value="${false}"/>
												<c:param name="showComment" value="${false}"/>
											</c:import>
										</c:otherwise>
									</c:choose>
								</figure>
							</div>
							<div class="cont">
								<span class="tit">
									<c:set var="dispDeptNm" value=""/>
									<c:if test="${not empty element.bDeptGroup}">
										<c:forEach var="code" items="${deptList}" >
											<c:if test="${fn:indexOf(code.fullChildPath, element.bDeptGroup) > -1}">
												<c:set var="dispDeptNm" value="${code.deptName}"/>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${useCategory && useDept}">[${empty dispDeptNm ? element.bDeptNm : dispDeptNm}]</c:if>
									<c:if test="${useCategory && useCustom}">[${element.bCategory}]</c:if>
									${element.bTitle}
									<c:if test="${bbsConfigVO.ptCommentYn == 'Y' && element.cmtCnt > 0}">[${element.cmtCnt}]</c:if>
								</span>
								<span class="date">
									<span>작성일 : <fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/></span>
									<span>작성자 : ${element.bWrite}</span>
									<span>조회수 : ${element.bCnt}</span>
								</span>
								<span class="txt">
									<c:set var="bContent" value="${yh:removeTag(element.bContent)}"/>
									<c:set var="length" value="${fn:length(bContent)}"/>
									<c:if test="${length > 150}">${fn:substring(bContent,0,150)}...</c:if>
									<c:if test="${length <= 150}">${bContent}</c:if>
								</span>
							</div>
						</div>
					</a>
				</li>
			</c:forEach>
		</c:if>
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
