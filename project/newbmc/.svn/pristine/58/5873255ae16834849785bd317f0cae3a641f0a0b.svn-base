<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 공지기간 ============================== --%>
<c:if test="${isRootArticle && useNterm}">
	<script type="text/javascript" src="/common/js/datePicker.js"></script>
	<script type="text/javascript">
	//<![CDATA[
	jQuery(document).ready(function() {
		dp.bind(jQuery("[data-date=y]"));
		jQuery("#bNtermSdate").datepicker("option", "beforeShowDay", noBeforeToday);
		jQuery("#bNtermSdate").datepicker("option", "onSelect", function(date) {
			jQuery("#bNtermEdate").datepicker("option", "beforeShowDay", noBeforeStartday);
		});

		isShowNterm();
		$("input[name=bNotice]").click(function() {
			isShowNterm();
		});
	});

	function formatDate(date) {
		var d = new Date(date),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();

		if (month.length < 2) month = '0' + month;
		if (day.length < 2) day = '0' + day;
		return [year, month, day].join('-');
	}


	// 이전 날짜들은 선택막기
	function noBeforeToday(date){
	    if (formatDate(date) < formatDate(new Date()))
	        return [false];
	    return [true];
	}

	// 선택 날짜들 이전 선택막기
	function noBeforeStartday(date){
		if (formatDate(date) < formatDate(new Date(jQuery("#bNtermSdate").val())))
	        return [false];
	    return [true];
	}

	function isShowNterm() {
		if($('input:checkbox[name="bNotice"]').is(":checked")) {
			$("#bNtermBox").show();
			$("#bNtermSdate").attr("data-required","y");
			$("#bNtermEdate").attr("data-required","y");
		} else {
			$("#bNtermBox").hide();
			$("#bNtermSdate").attr("data-required","n");
			$("#bNtermEdate").attr("data-required","n");
		}
	}
	//]]>
	</script>
	<tr id="bNtermBox">
		<th scope="row"><label for="bNtermSdate">공지기간</label></th>
		<td>
			<form:input path="bNtermSdate" id="bNtermSdate" cssClass="input100" maxlength="10" data-validator="on" data-inputType="date" data-date="y" data-required="y" data-fieldName="공지기간시작일" autocomplete="off"/>
			~
			<form:input path="bNtermEdate" id="bNtermEdate" cssClass="input100" maxlength="10" data-validator="on" data-inputType="date" data-date="y" data-required="y" data-fieldName="공지기간종료일" autocomplete="off"/>
		</td>
	</tr>
</c:if>
