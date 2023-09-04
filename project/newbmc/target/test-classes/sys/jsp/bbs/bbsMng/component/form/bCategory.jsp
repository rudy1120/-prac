<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ============================== 분류 ============================== --%>
<c:if test="${isRootArticle && useCategory && useCustom}">
	<tr>
		<th scope="row"><span class="red">*</span><label for="bCategory">분류</label></th>
		<td>
			<form:select path="bCategory" data-validator="on" data-required="y" data-fieldName="분류">
				<option value="">분류 선택</option>
				<c:forEach var="code" items="${categoryList}" >
					<form:option value="${code.bCategory}">${code.bCategory}</form:option>
				</c:forEach>
			</form:select>
		</td>
	</tr>
</c:if>
