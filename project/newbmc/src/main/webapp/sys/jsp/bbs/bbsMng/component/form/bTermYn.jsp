<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/common/js/jquery/timepicker/jquery.ui.timepicker.css"/>
<script src="/common/js/jquery/timepicker/jquery.ui.timepicker.js"></script>

<%-- ============================== 게시기간 ============================== --%>
<c:if test="${isRootArticle && useTerm}">
	<script type="text/javascript" src="/common/js/datePicker.js"></script>
	<script type="text/javascript">
	//<![CDATA[
	jQuery(document).ready(function() {
		dp.bind(jQuery("[data-date=y]"));
	});
	//]]>
	
	</script>
	<tr>
		<th scope="row"><label for="bTermSdate">게시기간</label></th>
		<td>
			<form:input path="bTermSdate" id="bTermSdate" cssClass="input100" maxlength="10" data-validator="on" data-inputType="date" data-date="y" data-fieldName="게시기간시작일" autocomplete="off" />
			<form:input path="bTermStime" id="bTermStime" cssClass="input100" maxlength="10" data-validator="on" autocomplete="off"/>
			부터&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path="bTermEdate" id="bTermEdate" cssClass="input100" maxlength="10" data-validator="on" data-inputType="date" data-date="y" data-fieldName="게시기간종료일" autocomplete="off" />
			<form:input path="bTermEtime" id="bTermEtime" cssClass="input100" maxlength="10" autocomplete="off"/> 까지
		</td>
	</tr>
</c:if>

<script>
	$(document).ready(function() {
		$("#bTermStime").timepicker({});
		$("#bTermEtime").timepicker({});
	});
</script>