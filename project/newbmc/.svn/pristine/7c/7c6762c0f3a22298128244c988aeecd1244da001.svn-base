<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<script>
$(document).ready(function(){
	dp3.bind($("[data-date=y]"));
});
</script>

<c:set var="isNew"   value="${reserveVO.idx eq 0}"/>
<c:set var="postUrl" value="writeProc"/>

<form:form commandName="reserveVO" id="cancel" name="cancel"  methodParam="post" action="/sys/moveRes/resList.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="reserveVO" id="modify" name="modify" action="/sys/moveRes/${postUrl}.do?mId=${menuVO.mId}&gbn2=${gbn2}" methodParam="post" onsubmit="return validator();" htmlEscape="false" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<h2>${title} 계약자 등록</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="name">성함</label></th>
				<td>
					<input type="text" id="name" name="name" maxlength="50" cssClass="input500" data-validator="on" data-required="y" data-fieldName="성함" placeholder="홍길동"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="tel">연락처</label></th>
				<td>
					<input type="text" id="tel" name="tel" maxlength="13" cssClass="input500" data-validator="on" data-required="y" data-fieldName="연락처"
						oninput="this.value=this.value.replace(/[^-0-9]/gi, '').replace(/(\..*)\./g, '$1');" placeholder="010-0000-0000"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="addr1">동</label></th>
				<td>
					<input type="text" id="addr1" name="addr1" maxlength="3" cssClass="input500" data-validator="on" data-required="y" data-fieldName="동"
						oninput="this.value=this.value.replace(/[^0-9]/gi, '').replace(/(\..*)\./g, '$1');" placeholder="숫자만 입력해주세요."/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="addr2">호수</label></th>
				<td>
					<input type="text" id="addr2" name="addr2" maxlength="4" cssClass="input500" data-validator="on" data-required="y" data-fieldName="호수"
						oninput="this.value=this.value.replace(/[^0-9]/gi, '').replace(/(\..*)\./g, '$1');" placeholder="숫자만 입력해주세요."/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><label for="gbn1">계약 차수</label></th>
				<td>
					<form:select path="gbn1" maxlength="300" cssClass="input500" data-validator="off" data-required="y" data-fieldName="기계약자여부">
						<form:option value="T">3차 계약자</form:option>
						<form:option value="N">2차 계약자</form:option>
						<form:option value="Y">1차 계약자</form:option>
					</form:select>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script>
$(function() {
	
});
</script>


<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<link   rel="stylesheet"       type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
<script type="text/javascript" src="/common/js/bbs/etc.js"></script>
<script type="text/javascript" src="/sys/js/nuri.js"></script>
<link   rel="stylesheet"       type="text/css" href="/common/js/jquery/jquery-confirm.css" />
<script type="text/javascript" src="/common/js/jquery/jquery-confirm.js"></script>
<script type="text/javascript" src="/sys/js/basic/promotion.js"></script>
