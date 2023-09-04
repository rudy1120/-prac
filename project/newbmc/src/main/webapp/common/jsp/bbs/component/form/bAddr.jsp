<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 주소 ============================== --%>
<c:if test="${isMinwon}">
	<c:set var="bAddr1Required" value="${fn:contains(bbsConfigVO.ptSaveCols, 'bAddr1')}"/>
	<c:set var="bAddr2Required" value="${fn:contains(bbsConfigVO.ptSaveCols, 'bAddr2')}"/>
	<dl>
		<dt><c:if test="${bAddr1Required}"><span class="red" title="필수 입력">*</span></c:if><label for="bAddr1">주소</label></dt>
		<dd>
			<form:input path="bAddr1" cssClass="w70" maxlength="200" data-validator="on" data-required="${bAddr1Required ? 'y' : 'n'}" data-fieldName="주소"/>
			<a href="#" onclick="addr.popup('#bAddr1', '#bAddr2'); return false;" title="도로명 주소 검색 사이트를 새창으로 표시" class="bt_white_s">
				<span class="btn_white">주소 검색</span>
			</a>
		</dd>
	</dl>
	<dl>
		<dt><c:if test="${bAddr2Required}"><span class="red" title="필수 입력">*</span></c:if><label for="bAddr2">상세 주소</label></dt>
		<dd>
			<form:input path="bAddr2" cssClass="w70" maxlength="300" data-validator="on" data-required="${bAddr2Required ? 'y' : 'n'}" data-fieldName="상세 주소"/>
		</dd>
	</dl>
	<script type="text/javascript" src="/common/js/address.js"></script>
</c:if>
