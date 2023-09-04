<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/common/js/jquery/timepicker/jquery.ui.timepicker.css"/>
<script src="/common/js/jquery/timepicker/jquery.ui.timepicker.js"></script>

<%-- ============================== 게시기간 ============================== --%>
	<script type="text/javascript" src="/common/js/datePicker.js"></script>
	<script type="text/javascript">
	//<![CDATA[
	jQuery(document).ready(function() {
		dp.bind(jQuery("[data-date=y]"));
	});
	//]]>
	
	</script>
	<tr>
		<th scope="row"><span class="red">*</span><label for="termSdate">접수기간</label></th>
		<td>
			<form:input path="sdate" id="sdate" cssClass="input100" maxlength="10" data-validator="on" data-inputType="date" data-date="y" data-fieldName="접수기간시작일" autocomplete="off" />
			<form:input path="stime" id="stime" cssClass="input100" maxlength="10" data-validator="on" autocomplete="off"/>
			부터&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path="edate" id="edate" cssClass="input100" maxlength="10" data-validator="on" data-inputType="date" data-date="y" data-fieldName="접수기간종료일" autocomplete="off" />
			<form:input path="etime" id="etime" cssClass="input100" maxlength="10" autocomplete="off"/> 까지
		</td>
	</tr>
	
<script>
	$(document).ready(function() {
		$("#stime").timepicker({});
		$("#etime").timepicker({});
	});
</script>