<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 홍보 자료 등록/수정
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.07.18		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.07.18
	 */
%>
<c:set var="isNew" value="${empty searchVO.idx}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>
<c:set var="prmtOldOrder" value="${isNew ? '1' : searchVO.prmtOrder}"/>
<form:form commandName="searchVO" id="cancel" name="cancel"  methodParam="post" action="/sys/${searchVO.type.path}/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>
<form:form commandName="searchVO" id="modify" name="modify" action="/sys/${searchVO.type.path}/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator() && promotion.addtional_validator();" htmlEscape="false" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<form:hidden path="prmtOldOrder" value="${prmtOldOrder}" />
	<form:hidden path="searchSiteIdx"/>

	<h2>${searchVO.type.desc} 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<%-- ============================ 제목 ============================ --%>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="prmtName">제목</label></th>
				<td>
					<form:input path="prmtName" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="제목"/>
					<form:checkbox path="useYn" value="Y" label="사용"/>
				</td>
			</tr>
			<%-- ============================ 사이트 ============================ --%>
			<tr>
				<th scope="row"><label for="siteIdxs">사이트</label></th>
				<td>
					<form:select path="siteIdxs" multiple="false" cssClass="input500" data-validator="on" htmlEscape="false">
						<c:forEach var="site" items="${siteList}">
							<form:option value="${site.idx}">${site.siteName}</form:option>
						</c:forEach>
					</form:select>
					<p class="tip">사이트 <span class="red">미선택시 전체 사이트에 게시</span>됩니다.</p>
				</td>
			</tr>
			<%-- ============================ URL ============================ --%>
			<tr>
				<th scope="row"><label for="prmtSiteIdxs">URL</label></th>
				<td>
					<form:input path="prmtUrl" maxlength="500" cssClass="input500" data-validator="on" data-fieldName="URL"/>
					<form:checkbox path="blankYn" value="Y" label="새창열림"/>
					<p class="tip"><span class="red">http://부터 입력</span>해주세요.</p>
				</td>
			</tr>
			<%-- ============================ 내용(배너 관리에서는 미출력) ============================ --%>
			<c:if test="${searchVO.type.code != prmtTypeList[0].code}">
				<tr>
					<th scope="row"><label for="prmtContent">내용</label></th>
					<td>
						<form:textarea path="prmtContent" maxlength="4000" rows="10" cssClass="input500" data-validator="on"/>
					</td>
				</tr>
			</c:if>
			<%-- ============================ 첨부파일(배너 관리에서는 미출력) ============================ --%>
			<c:if test="${searchVO.type.code != prmtTypeList[0].code}">
				<tr>
					<th scope="row"><label for="egovComFileUploader">첨부 파일</label></th>
					<td>
						<c:set var="param_maxFileNum" value="${1}" scope="request"/>
						<c:set var="param_attachId" value="${searchVO.attachId}" scope="request"/>
						<jsp:include page="/sys/jsp/component/fileAttachForm.jsp"/>
						<form:hidden path="attachId"/>
						<input type="hidden" data-validator="on" data-inputType="file" data-fileType="img"/>
					</td>
				</tr>
			</c:if>
			<%-- ============================ 템플릿(팝업존 ONLY) ============================ --%>
			<%--
			<c:if test="${searchVO.type.code == prmtTypeList[2].code}">
				<tr>
					<th scope="row"><label for="prmtTemplate">템플릿</label></th>
					<td>
						<!-- TODO 템플릿 미리보기 이미지 작성 디자인팀 의뢰 -->
						<p class="tip">내용에 들어가는 부분만 노출됩니다. <strong class="red">텍스트는 20자 내외</strong>가 가장 적당합니다.</p>
						<ul class="popupZone_temp">
							<li class="template0"><form:radiobutton path="prmtTemplate" value="0" label="선택안함"/></li>
							<li class="template1"><form:radiobutton path="prmtTemplate" value="1" label="템플릿 1"/></li>
							<li class="template2"><form:radiobutton path="prmtTemplate" value="2" label="템플릿 2"/></li>
							<li class="template3"><form:radiobutton path="prmtTemplate" value="3" label="템플릿 3"/></li>
							<li class="template4"><form:radiobutton path="prmtTemplate" value="4" label="템플릿 4"/></li>
							<li class="template5"><form:radiobutton path="prmtTemplate" value="5" label="템플릿 5"/></li>
						</ul>
						<p class="tip">템플릿 선택시 제목과 템플릿을 조합한 화면을 사용자에게 제공합니다.</p>
					</td>
				</tr>
			</c:if>
			--%>
			<%-- ============================ 시작/종료일 ============================ --%>
			<tr>
				<th scope="row">시작/종료일</th>
				<td>
					<form:input path="prmtSday" cssClass="input100" data-date="y" data-isMinDateFor="prmtEday" data-validator="on" data-inputType="date" data-fieldName="시작일"/> ~
					<form:input path="prmtEday" cssClass="input100" data-date="y" data-isMaxDateFor="prmtSday" data-validator="on" data-inputType="date" data-fieldName="종료일"/>
					<p class="tip"><span class="red">미입력시 상시 게시</span>됩니다.</p>
				</td>
			</tr>
			<%-- ============================ 정렬순서 ============================ --%>
			<tr>
				<th scope="row">정렬순서</th>
				<td>
					<form:select path="prmtOrder">
						<c:choose>
							<c:when test="${isNew}">
							<form:option value="1" >1</form:option>
							</c:when>
							<c:otherwise>
							<c:forEach var="oelement" items="${prmtOrderList}" varStatus="ostatus">
								<form:option value="${oelement.prmtOrder}" >${oelement.prmtOrder}</form:option>
							</c:forEach>
							<c:if test="${empty prmtOrderList}">
								<form:option value="1" >1</form:option>
							</c:if>
							</c:otherwise>
						</c:choose>
					</form:select>
					<p class="tip"><span class="red">정렬순서 변경시 기존의 정렬순서를 가진 정보와 정렬순서가 교환 됩니다 됩니다.</span></p>
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
	yh.prmtType = "${searchVO.type.code}";
	yh.banner = "${prmtTypeList[0].code}";
	yh.visualzone = "${prmtTypeList[1].code}";
	yh.popupzone = "${prmtTypeList[2].code}";
	yh.yearRange = "2017:+0";
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
<script type="text/javascript" src="/sys/js/basic/promotion.js"></script>
