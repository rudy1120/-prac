<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<c:set var="isNew" value="${empty searchVO.depCode}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>
<form:form commandName="searchVO" id="cancel" name="cancel"  methodParam="post" action="/sys/deptMng/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>
<form:form commandName="searchVO" id="modify" name="modify" action="/sys/deptMng/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false">
	<form:hidden path="depCode" />

	<h2>부서 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span><label for="depComment">상위부서</label></th>
			    <td>
			   		<form:select path="newParent" data-validator="on" data-required="y" data-fieldName="상위부서">
			    	<option value="">-- 상위부서 --</option>
						<form:option value="0">상위부서없음</form:option>
						<c:forEach var="d" items="${departList}">
							<c:if test="${d.newParent == '0'}">
								<form:option value="${d.depCode}" style="font-weight:600">${d.depCodeNm}</form:option>
							</c:if>
							<c:if test="${d.newParent != '0'}">
								<form:option value="${d.depCode}">${d.depCodeNm}</form:option>
							</c:if>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="newSname">부서명</label></th>
				<td>
					<form:input path="newSname" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="부서명"/>
					<br/>
					* 같은 이름의 부서를 상위로 등록해야 하는 경우 (상위) 를 같이 써주세요. 예) 청렴감사실(상위) > 청렴감사실
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="depComment">부서업무</label></th>
				<td>
					<form:textarea path="depComment" rows="10" class="input500" maxlength="1000" data-validator="on" data-required="y" data-fieldName="부서업무"></form:textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="repTelNo">연락처</label></th>
				<td>
					<form:input path="repTelNo" class="input500" maxlength="300" data-fieldName="연락처"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="repFaxNo">팩스번호</label></th>
				<td>
					<form:input path="repFaxNo" class="input500" maxlength="300" data-fieldName="팩스번호"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="newLocation">위치정보</label></th>
				<td>
					<form:input path="newLocation" class="input500" maxlength="300" data-fieldName="위치정보"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="newOrder">정렬순서</label></th>
				<td>
					<form:input path="newOrder" class="input100" data-validator="on" data-inputType='num' maxlength="300" data-fieldName="정렬순서"/>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script type="text/javascript">
	yh.siteCode = "sys";
	yh.isNew = eval("${isNew}");
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
