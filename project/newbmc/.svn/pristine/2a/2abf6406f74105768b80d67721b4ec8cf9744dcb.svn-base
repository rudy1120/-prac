<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 관리자 정책연구용역보고서 수정 및 등록 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.22		박선민			최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.22
 */
%>
<script>
$(document).ready(function () {
	
	
	var year = new Date().getFullYear();
	
	for( var i = 0 ; i < 50; i++ ){
		
		var yearNum = year-i;
		var options = $("<option value='"+yearNum +"'>"+yearNum+"</option>");
		$("#issYear").append(options);
		
	}
	
	$("#issYearVal").val();
	
	$("#issYear").val($("#issYearVal").val()).prop("selected",true);
	
});
</script>
<c:set var="isNew"   value="${prismVO.idx eq 0}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>

<form:form commandName="prismVO" id="cancel" name="cancel"  methodParam="post" action="/sys/prismMng/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="prismVO" id="modify" name="modify" action="/sys/prismMng/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<input type="hidden" id="isNew" value="${postUrl}" />
	<h2>정책연구용역보고서 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<h2>과제정보</h2>
	<table class="tableSt_write">
<!-- 		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup> -->
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="projectNm">과제명</label></th>
				<td>
					<form:input path="projectNm" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="과제명"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="orgnNm">기관명</label></th>
				<td>
					<form:input path="orgnNm" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="기관명"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="depNm">담당부서</label></th>
				<td>
					<form:input path="depNm" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="담당부서" />
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="phoneNb">전화번호</label></th>
				<td>
					<form:input path="phoneNb" maxlength="100" cssClass="input200" data-validator="on" data-required="y"  data-fieldName="전화번호" placeholder="'-'을 포함하여 입력해주세요." />
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span>연구기간</th>
				<td>
					<form:input path="rSdate" cssClass="input100" data-date="y" data-isMinDateFor="sdate" data-validator="on" data-required="y" data-inputType="date" data-fieldName="연구기간시작일" autocomplete="off" readonly="true"/> ~
					<form:input path="rEdate" cssClass="input100" data-date="y" data-isMaxDateFor="edate" data-validator="on" data-required="y" data-inputType="date" data-fieldName="연구기간종료일" autocomplete="off" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="rField">연구분야</label></th>
				<td>
					<form:input path="rField" maxlength="300" cssClass="input500"  data-validator="on" data-required="y" data-fieldName="연구분야"  />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="summary">개요</label></th>
				<td>
					<form:textarea path="summary" rows="20" maxlength="4000" style="width:500px;"  data-required="y" data-fieldName="개요"></form:textarea>
				</td>
			</tr>
		</tbody>
	</table>	
 	<h2>계약정보</h2>
	<table class="tableSt_write">
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="executeNm">수행기관</label></th>
				<td>
					<form:input path="executeNm" maxlength="300" cssClass="input500"  data-validator="on" data-required="y" data-fieldName="수행기관" />
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="researcher">수행연구원</label></th>
				<td>
					<form:input path="researcher" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="수행연구원"  />
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="contDt">계약일자</label></th>
				<td>
					<form:input path="contDt" cssClass="input100" data-date="y" data-isMinDateFor="contDt"  data-validator="on" data-required="y" data-inputType="date" data-fieldName="계약일자" autocomplete="off" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="contMethod">계약방식</label></th>
				<td>
					<form:input path="contMethod" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="계약방식"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="contPrice">계약금액</label></th>
				<td>
					<form:input path="contPrice" maxlength="100" cssClass="input300" data-validator="on" data-required="y" data-fieldName="계약금액" placeholder="ex)19,000,000" />
				</td>
			</tr>
		</tbody>
	</table>	
	<h2>연구보고서</h2>
	<table class="tableSt_write">
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="title">제목</label></th>
				<td>
					<form:input path="title" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="제목" />
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="eTitle">영문제목</label></th>
				<td>
					<form:input path="eTitle" maxlength="300" cssClass="input500" data-fieldName="영문제목"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="subtitle">부제목</label></th>
				<td>
					<form:input path="subtitle" maxlength="300" cssClass="input500"  data-fieldName="부제목"/>
				</td>
			</tr>
			<!-- 파일첨부 -->
			<tr>
				<th scope="row"><label for="resReport">결과 보고서</label></th>
				<td>
					<input type="hidden" name="resReport" value="${prismVO.resReport}" />
					<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${prismVO.resReport}" />
						<c:param name="downloadYn" value="Y"/>
					</c:import>
					<input type="file" id="resReport_file" name="resReport_file" data-validator="on" data-fieldName="결과보고서 첨부파일" class="input300 mB10" value="" "/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="topic">주제어</label></th>
				<td>
					<form:input path="topic" maxlength="300" cssClass="input500" data-fieldName="주제어"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="prdtDt">제작일자</label></th>
				<td>
					<form:input path="prdtDt" cssClass="input100" data-date="y" data-isMinDateFor="contDt"  data-inputType="date" data-fieldName="제작일자" autocomplete="off" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="contributor">공헌자</label></th>
				<td>
					<form:input path="contributor" maxlength="100" cssClass="input300" data-fieldName="공헌자" />
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="issYear"><span class="red">*</span> 발행년도</label></th>
				<td>
					<input type="hidden" id="issYearVal" value="${prismVO.issYear}" />
					<form:select path="issYear" maxlength="50" cssClass="input100" data-fieldName="발행년도" data-validator="on" data-required="y">
						<form:option value="">--선택--</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="subDt">제출일</label></th>
				<td>
					<form:input path="subDt" cssClass="input100" data-date="y" data-isMinDateFor="subDt"  data-inputType="date" data-fieldName="제출일" autocomplete="off" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="contents">목차</label></th>
				<td>
					<form:textarea path="contents" rows="20" maxlength="4000" style="width:500px;"  data-fieldName="목차"></form:textarea>
				</td>
			</tr>
		</tbody>
	</table>	
 	<h2>연구결과 평가 및 활용보고서</h2>
	<table class="tableSt_write">
		<tbody>
			<!-- 파일첨부 -->
			<tr>
				<th scope="row" class="w20"><label for="evalResult">평가결과서</label></th>
				<td>
					<input type="hidden" name="evalResult" value="${prismVO.evalResult}" />
					<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${prismVO.evalResult}" />
						<c:param name="downloadYn" value="Y"/>
					</c:import>
					<input type="file" id="evalResult_file" name="evalResult_file" data-validator="on" data-fieldName="평가결과서 첨부파일" class="input300 mB10" value="" />
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="applReport">활용결과 보고서</label></th>
				<td>
					<input type="hidden" name="applReport" value="${prismVO.applReport}" />
					<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${prismVO.applReport}" />
						<c:param name="downloadYn" value="Y"/>
					</c:import>
					<input type="file" id="applReport_file" name="applReport_file" data-validator="on" data-fieldName="활용결과 보고서 첨부파일" class="input300 mB10" value="" />
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
	yh.yearRange = "2017:+0";
	

	$(document).ready(function() {

		$('.updateFileList').parent('td').find('[type=file]').hide();
		$('.btn_dele').click(function() {

			 defaultTime = setInterval(function() {
				$('.updateFileList>ul:not(:has(li))').parents('td').find('[type=file]').show();
				clearInterval(defaultTime);
			 }, 100);

		});
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
