<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 휴대전화 ============================== --%>
<c:if test="${isCalendar}">
	<script type="text/javascript" src="/common/js/datePicker.js"></script>
	<script type="text/javascript">
	//<![CDATA[
	jQuery(document).ready(function() {
		dp.bind(jQuery("[data-date=y]"));
	});
	//]]>
	</script>
	<tr>
		<th scope="row"><label for="bClass">분류</label></th>
		<td>
			<form:input path="bClass" cssClass="input200" maxlength="200" data-validator="on" data-fieldName="분류" />&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="bPlace">장소</label></th>
		<td>
			<form:input path="bPlace" cssClass="input200" maxlength="200" data-validator="on" data-fieldName="장소" />&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="bOrganize">주관</label></th>
		<td>
			<form:input path="bOrganize" cssClass="input200" maxlength="200" data-validator="on" data-fieldName="주관" />&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="bSdate">기간</label></th>
		<td>
			시작일 : <form:input path="bSdate" id="bSdate" cssClass="input100" maxlength="10" data-validator="on" data-inputType="date" data-date="y" data-fieldName="시작일자"/>
			~
			종료일 : <form:input path="bEdate" id="bEdate" cssClass="input100" maxlength="10" data-validator="on" data-inputType="date" data-date="y" data-fieldName="종료일자"/>
			,
			시작시간 : <form:input path="bStime" id="bStime" cssClass="input50" maxlength="5" data-validator="on" data-inputType="time" data-fieldName="시작시간"/>
			~
			종료시간 : <form:input path="bEtime" id="bEtime" cssClass="input50" maxlength="5" data-validator="on" data-inputType="time" data-fieldName="종료시간"/>
			<span class="tip">시간은 00:00 형식으로 입력해주세요.</span>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="showYn">노출유무</label></th>
		<td>
			<c:if test="${empty searchVO.showYn}">
				<form:radiobutton path="showYn" id="showYnN" value="N" checked="checked" label="미노출"/>
			</c:if>
			<c:if test="${not empty searchVO.showYn}">
				<form:radiobutton path="showYn" id="showYnN" value="N" label="미노출"/>
			</c:if>
			<form:radiobutton path="showYn" id="showYnY" value="Y" label="노출"/>
		</td>
	</tr>
</c:if>