<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * FAQ 게시글 목록
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

<c:set var="elements" value="${result}"/>
<c:set var="totalCol" value="6"/>
<table class="tableSt_list row_over">
	<caption>공통게시판 리스트</caption>
	<thead>
	<tr>
		<c:if test="${useBundleDel && !p_fromView}">
			<th scope="col" class="w2"><input type="checkbox" name="getCheckedAll" id="getCheckedAll"/></th>
		</c:if>
		<th scope="col" class="w4">번호</th>
		<c:if test="${useCategory && useCustom}">
			<th scope="col" class="w10">분야</th>
			<c:set var="totalCol" value="${totalCol + 1}"/>
		</c:if>
		<th scope="col">질문</th>
		<th scope="col" class="w6">삭제</th>
		<c:if test="${useCategory && useDept}">
			<th scope="col" class="w10">담당부서</th>
			<c:set var="totalCol" value="${totalCol + 1}"/>
		</c:if>
		<th scope="col" class="w8">작성자</th>
		<th scope="col" class="w10">작성일</th>
	</tr>
	</thead>
	<tbody>
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
				<%-- ====================== 분류 ====================== --%>
				<c:if test="${useCategory && useCustom}">
					<td>${element.bCategory}</td>
				</c:if>
				<%-- ====================== 제목(질문) ====================== --%>
				<td class="taL">
					<a href="#" onclick="faq.view('${element.bIdx}'); return false;" <c:if test="${element.isDel == 'Y'}">class="list_del"</c:if>>
						${element.bTitle}
					</a>
				</td>
				<%-- ====================== 삭제 ====================== --%>
				<td>
					<c:if test="${element.isDel == 'Y'}"><span class="red">삭제</span></c:if>
					<c:if test="${element.isDel eq 'N'}"><span class="dgray">&nbsp;</span></c:if>
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
				<%-- ====================== 작성일 ====================== --%>
				<td><fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<%-- ====================== 내용(답변) ====================== --%>
			<tr class="element_detail" id="${element.bIdx}" style="display: none;">
				<td colspan="${totalCol}" class="faq_answer">
					<dl class="list_faq">
						<dt>
							<span <c:if test="${element.isDel == 'Y'}">class="list_del"</c:if>>${element.bTitle}</span>
							&nbsp;&nbsp;
							<a href="#" onclick="goTo.modify('list', '${element.bIdx}'); return false;" class="btn_scyan">수정</a>
							<a href="#" onclick="goTo.delet('list', '${element.bIdx}'); return false;" class="btn_sblack">삭제</a>
						</dt>
						<dd>${element.bContent}</dd>
					</dl>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	</tbody>
</table>

<c:if test="${empty result && empty noti}">
	<div class="no_data">등록된 게시물이 없습니다.</div>
</c:if>

<c:if test="${not empty result}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>

<script type="text/javascript" src="/sys/js/bbs/faq.js"></script>
