<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 부서 ============================== --%>
<c:if test="${isRootArticle && useCategory && useDept}">
	<tr>
		<th scope="row"><span class="red">*</span><label for="bDeptGroup">부서</label></th>
		<td>
			<form:select path="bDeptGroup" data-validator="on" data-required="y" data-fieldName="부서">
				<option value="">부서 선택</option>
				<c:if test="${isNew}">
					<c:forEach var="code" items="${deptList}" >
						<option value="${code.deptId}"
							<c:if test="${fn:indexOf(code.fullChildPath, ADMIN_LOGIN_INFO.deptId) > 0}">selected="selected"</c:if>
						>
							${fn:replace(code.deptName, ' ', '&nbsp;')}
						</option>
					</c:forEach>
				</c:if>
				<c:if test="${!isNew}">
					<c:forEach var="code" items="${deptList}" >
						<form:option value="${code.deptId}">${fn:replace(code.deptName, ' ', '&nbsp;')}</form:option>
					</c:forEach>
				</c:if>
			</form:select>
			<label for="bDeptNm" style="display: none;">부서명</label>
			<form:hidden path="bDeptNm"/>
		</td>
	</tr>
</c:if>
