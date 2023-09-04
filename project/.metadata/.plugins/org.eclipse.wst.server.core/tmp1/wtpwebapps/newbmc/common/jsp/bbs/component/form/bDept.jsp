<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 부서 ============================== --%>
<c:if test="${isRootArticle && useCategory && useDept}">
	<dl>
		<dt><span class="red" title="필수입력">*</span><label for="bDeptGroup">부서</label></dt>
		<dd>
			<form:select path="bDeptGroup" title="부서를 선택하세요." data-validator="on" data-required="y" data-fieldName="부서">
				<option value="">부서 선택</option>
				<c:forEach var="code" items="${deptList}">
					<form:option value="${code.deptId}">${code.deptName}</form:option>
				</c:forEach>
			</form:select>
			<label for="bDeptNm" style="display: none;">부서명</label>
			<form:hidden path="bDeptNm"/>
		</dd>
	</dl>
</c:if>
