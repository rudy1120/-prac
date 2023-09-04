<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<c:set var="isNew" value="${empty publicVO.pubIdx}"/>
<c:set var="postUrl" value="${isNew ? 'dataWriteProc' : 'dataModifyProc'}"/>
<form:form commandName="publicVO" id="cancel" name="cancel"  methodParam="post" action="/sys/public/dataList.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<input type="hidden" name="searchCatIdx" value="${searchVO.searchCatIdx}"/>
	<input type="hidden" name="searchName" value="${searchVO.searchName}"/>
<%-- 	<form:hidden path="searchCatIdx"/> --%>
<%-- 	<form:hidden path="searchName"/> --%>
</form:form>
<form:form commandName="publicVO" id="modify" name="modify" action="/sys/public/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false">
	<form:hidden path="pubIdx" />
<%-- 	<form:hidden path="searchCatIdx" /> --%>
	<input type="hidden" name="searchCatIdx" value="${searchVO.searchCatIdx}"/>
	<input type="hidden" name="searchName" value="${searchVO.searchName}"/>

	<h2>사전정보공표 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="catIdx">카테고리</label></th>
				<td>
					<form:select path="catIdx" data-validator="on" data-required="y" data-fieldName="카테고리">
						<option value="">-- 카테고리 선택 --</option>
						<c:forEach var="c" items="${catList}">
							<form:option value="${c.catIdx}">${c.catName}</form:option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="name">공표목록</label></th>
				<td>
					<form:input path="name" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="공표목록"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="article">공표항목</label></th>
				<td>
					<form:input path="article" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="공표항목"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="time">시기</label></th>
				<td>
					<form:input path="time" maxlength="300" cssClass="input500" data-validator="on" data-fieldName="시기"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="cycle">주기</label></th>
				<td>
					<form:input path="cycle" maxlength="300" cssClass="input500" data-validator="on" data-fieldName="주기"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="depart">공개부서</label></th>
				<td>
					<form:input path="depart" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="공개부서"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="link">연결링크</label></th>
				<td>
					<form:input path="link" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="연결링크"/>
					<br/>* http:// 부터 입력해주세요.
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="orderNum">정렬순서</label></th>
				<td>
					<form:input path="orderNum" maxlength="10" cssClass="input100" data-inputType='num' data-fieldName="정렬순서"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="useYn">사용여부</label></th>
				<td>
					<form:radiobutton path="useYn" value="Y" /><label for="useYn1">사용</label>
					<form:radiobutton path="useYn" value="N" /><label for="useYn2">미사용</label>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
