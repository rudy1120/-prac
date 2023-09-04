<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<script>
$(function(){
	var category = '${searchVO.searchCategory}';
	$('.faqbtn_wrap > [value=' + '"' + category + '"' + ']').addClass('on');
	$('.b1').on('click', function() {
		$('#sc1').remove();
	});
	var on = $('.on').html();
	
	var mId = '${menuVO.mId}';
	if (mId =='0303000000') {
		$('title').html('자주하는 질문(FAQ) - ' + on + ' | 부산도시공사');
	} else {
		if (mId =='0406000000') {
			$('title').html('정보공개 FAQ - ' + on + ' | 부산도시공사');
		}	
	}
	
})
</script>
<form:hidden path="searchCategory" id="sc1"/>
<table class="bod_list faq">
	<caption>
		${activeMenu.menuName} 게시글을 번호,
		<c:if test="${useCategory && useCustom}">분류,</c:if>		
		<c:if test="${useCategory && useDept}">담당부서,</c:if>
		질문 항목별로 상세히 안내하는 표입니다.
	</caption>
	<thead>
		<tr>
			<c:set var="colLength" value="4"/>
			<th scope="col" id="tbl_num" class="list_num">번호</th>
			<c:if test="${useCategory && useCustom}">
			<th scope="col" id="tbl_cate">분류</th>
			<c:set var="colLength" value="${colLength + 1}"/>
			</c:if>
			<th scope="col" id="tbl_tit" class="list_tit">질문</th>
			<c:if test="${useCategory && useDept}">
			<th scope="col" id="tbl_dept" class="list_dept">담당부서</th>
			<c:set var="colLength" value="${colLength + 1}"/>
			</c:if>
			<!-- <th scope="col" id="tbl_write" class="list_write">작성자</th>
			<th scope="col" id="tbl_date" class="list_date">작성일</th> -->
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty result}">
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td headers="tbl_num" class="list_num">${listOrder - status.index}</td>
					<c:if test="${useCategory && useCustom}">
						<td headers="tbl_cate" class="list_category"><span>${element.bCategory}</span></td>
					</c:if>
					<td headers="tbl_tit" class="list_tit">
						<a href="#" onclick="jQuery('#${element.bIdx}').toggle(); return false;">
							${element.bTitle}
						</a>
					</td>
					<c:if test="${useCategory && useDept}">
						<td headers="tbl_dept" class="list_dept">
							<c:forEach var="code" items="${deptList}">
								<c:if test="${code.deptId == element.bDeptGroup}">
									<c:set var="dispDeptNm" value="${code.deptName}"/>
								</c:if>
							</c:forEach>
							${empty dispDeptNm ? element.bDeptNm : dispDeptNm}
						</td>
					</c:if>
					<%-- <td headers="tbl_write" class="list_write">${not empty element.bDeptNm ? element.bDeptNm : element.bWrite}</td>
					<td headers="tbl_date" class="list_date"><fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/></td> --%>
				</tr>
				<tr id="${element.bIdx}" class="element_detail" style="display: none;">
					<td colspan="${colLength}" class="faq_answer">
						<dl class="list_q clFix">
							<dt><span class="blind">질문 :</span></dt>
							<dd><span>${element.bTitle}</span></dd>
						</dl>
						<dl class="list_a clFix">
							<dt><span class="blind">답변 :</span></dt>
							<dd>
								${element.bContent}
								<%-- ##### 공공누리 이용 안내 ##### --%>
								<c:if test="${not empty element.bNuriType && element.bNuriType != '0'}">
									<c:set var="p_nuriType" value="${element.bNuriType}" scope="request"/>
									<jsp:include page="/common/jsp/component/nuriLisence.jsp"/>
								</c:if>
							</dd>
						</dl>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty result}">
			<tr>
				<td colspan="${colLength}">등록된 게시물이 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>

<c:if test="${!empty result}">
	<div class="bod_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
	</div>
</c:if>
