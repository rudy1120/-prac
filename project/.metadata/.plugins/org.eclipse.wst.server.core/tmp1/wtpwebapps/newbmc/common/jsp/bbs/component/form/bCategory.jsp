<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 분류 ============================== --%>
<c:if test="${isRootArticle && useCategory && useCustom}">
	<dl>
		<c:set var="categoryNm" value="분류"/>
		<dt><span class="red" title="필수입력">*</span><label for="bCategory">${categoryNm}</label></dt>
		<dd>
			<form:select path="bCategory" data-validator="on" data-required="y" data-fieldName="${categoryNm}">
				<option value="">${categoryNm} 선택</option>
				<c:forEach var="_category" items="${categoryList}">
					<form:option value="${fn:trim(_category)}">${fn:trim(_category)}</form:option>
				</c:forEach>
			</form:select>
		</dd>
	</dl>
</c:if>
