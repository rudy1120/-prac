<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 게시글 제목 ============================== --%>
<tr>
	<th scope="row"><span class="red">*</span><label for="bTitle">제목</label></th>
	<td>
		<form:input path="bTitle" id="bTitle" cssClass="input500" maxlength="500" data-validator="on" data-required="y" data-fieldName="제목"/>
		<c:if test="${isBasic}">
			&nbsp;
			<c:if test="${bbsConfigVO.ptSiteCode == 'common'}">
				<form:checkbox path="bMainYn" id="bMainN" value="N" label="부서 사이트에만 노출" onchange=" if ($('#bMainN').is(':checked')) alert('해당 옵션 체크시 대표 포털에 본 게시글을 게재하지 않습니다.'); "/>
				&nbsp;
			</c:if>
			<form:checkbox path="bNotice" id="bNoticeY" value="Y" label="공지글"/>
			<span class="tip red">주의 : 게시판의 최상단에 [공지]하는 경우에만 공지글로 설정해주세요. </span>
		</c:if>
		<c:if test="${!isBasic}">
			<form:hidden path="bNotice" value="N"/>
		</c:if>
	</td>
</tr>
