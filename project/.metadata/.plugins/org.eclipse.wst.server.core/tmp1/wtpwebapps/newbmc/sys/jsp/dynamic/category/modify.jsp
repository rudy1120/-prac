<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황관리
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 *
	 * @author 권태성
	 * @since 2016.11.04
	 */
%>
<c:set var="isNew" value="${empty searchVO.idx}" />
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/sys/js/dynamicTable.js"></script>
<script type="text/javascript">
//<![CDATA[
var index=0;
var rmFields = function rmFields() {
	showLog("RMFIELDS FUNCTION");
	//console.log($(".tableSt_list tbody tr td").length);
	if ($("tbody > tr").length > 1) {
		$("tbody > tr").last().remove();
		index--;
	} else {
		alert("더 이상 지울 수 없습니다.");
		return false;
	}
	//$(target).parent().parent().remove();
	//console.log($(".tableSt_list tbody tr td").length);
	//$(".tableSt_list tbody tr td:last-child").remove();
};
var resetOpenerValues = function resetOpenerValues() {
	showLog("RESETOPENERVALUES FUNCTION");
	//$("#categoryValues", opener.document).html("");
	$("[name^=category${categoryIdx}List]", opener.document).remove();
	$("[id^=categoryDefList${categoryIdx-1}]", opener.document).remove();
}
var addFields = function addFields(target) {
	showLog("ADDFIELDS FUNCTION");
	index++;
	$("tbody").append("<tr>"+
							"<td><input type=\"text\" name=\"category${categoryIdx}List["+index+"].categoryKey\" id=\"category${categoryIdx}List"+index+".categoryKey\" class=\"input100\" value=\"\" /></td>"+
							"<td><input type=\"text\" name=\"category${categoryIdx}List["+index+"].categoryValue\" id=\"category${categoryIdx}List"+index+".categoryValue\" class=\"input100\" value=\"\" /></td>"+
							"<td><select name=\"category${categoryIdx}List["+index+"].useYn\" id=\"category${categoryIdx}List"+index+".useYn\" class=\"input100\"><option value=\"Y\" selected=\"selected\">사용</option><option value=\"N\">미사용</option></select></td>"+
							"<td><input type=\"text\" name=\"category${categoryIdx}List["+index+"].categoryOrder\" id=\"category${categoryIdx}List"+index+".categoryOrder\" class=\"input100\" value=\"\" /></td>"+
							"<td class=\"taC\">"+
								"<a href=\"#\" onclick=\"addFields(this); return false;\" class=\"btn_white\" data-type=\"insert\">+</a>"+
								//"<a href=\"#\" onclick=\"remove_column_def(this); return false;\" class=\"btn_white\" data-type=\"delete\">-</a>"+
							"</td>"+
						"</tr>");
	console.log("index : "+index);
};
var save = function save() {
	showLog("SAVE FUNCTION");

	if (validator() && tableValid()) {
		var length = $("[name^=category${categoryIdx}List]").length;
		resetOpenerValues();
		for (var i=0; i<length; i++) {
			var obj = $("[name^=category${categoryIdx}List]")[i];
			var name = $(obj).attr("name");
			if ($(obj).attr("name").indexOf("categoryKey") != -1) {
				$("#category${categoryIdx}Length", opener.document).after("<input type=\"hidden\" name=\""+name+"\" id=\""+name+"\" value=\""+$(obj).val()+"\"/>");
			} else if ($(obj).attr("name").indexOf("categoryValue") != -1) {
				$("#category${categoryIdx}Length", opener.document).after("<input type=\"hidden\" name=\""+name+"\" id=\""+name+"\" value=\""+$(obj).val()+"\"/>");
			} else if ($(obj).attr("name").indexOf("useYn") != -1) {
				$("#category${categoryIdx}Length", opener.document).after("<input type=\"hidden\" name=\""+name+"\" id=\""+name+"\" value=\""+$(obj).val()+"\"/>");
			} else if ($(obj).attr("name").indexOf("categoryOrder") != -1) {
				$("#category${categoryIdx}Length", opener.document).after("<input type=\"hidden\" name=\""+name+"\" id=\""+name+"\" value=\""+$(obj).val()+"\"/>");
			}
			if (i < (length/4)) {
				$("#category${categoryIdx}Length", opener.document).after("<input type=\"hidden\" name=\"category${categoryIdx}List["+i+"].categoryIdx\" id=\"category${categoryIdx}List["+i+"]categoryIdx\" value=\"${categoryIdx}\"/>");
			}
		}
		$("#category${categoryIdx}Length", opener.document).val(index+1);
		//카테고리 설정 값 저장
		var appendHtml = "<input type=\"hidden\" name=\"categoryDefList[${categoryIdx-1}].name\" id=\"categoryDefList${categoryIdx-1}.name\" value=\""+$("#name").val()+"\" />";
		appendHtml += "<input type=\"hidden\" name=\"categoryDefList[${categoryIdx-1}].width\" id=\"categoryDefList${categoryIdx-1}.width\" value=\""+$("#width").val()+"\" />";
		appendHtml += "<input type=\"hidden\" name=\"categoryDefList[${categoryIdx-1}].showYn\" id=\"categoryDefList${categoryIdx-1}.showYn\" value=\""+$("#showYn").val()+"\" />";
		appendHtml += "<input type=\"hidden\" name=\"categoryDefList[${categoryIdx-1}].showListYn\" id=\"categoryDefList${categoryIdx-1}.showListYn\" value=\""+$("#showListYn").val()+"\" />";
		appendHtml += "<input type=\"hidden\" name=\"categoryDefList[${categoryIdx-1}].showSearchYn\" id=\"categoryDefList${categoryIdx-1}.showSearchYn\" value=\""+$("#showSearchYn").val()+"\" />";
		$("#categoryDef", opener.document).append(appendHtml);
		window.close();
	}
}
var tableValid = function () {
	var ret = true;
	$("tr").each(function(index, el) {
		if (index > 0) {
			var $key = $(this).children("td:eq(0)").children("input");
			var $value = $(this).children("td:eq(1)").children("input");
			var $order = $(this).children("td:eq(3)").children("input");
			if (!$key.val() && $value.val()) {
				alert("표시값을 입력하세요.");
				$key.focus();
				ret = false;
			} else if ($key.val() && !$value.val()) {
				alert("실제값을 입력하세요.");
				$value.focus();
				ret = false;
			} else if ($key.val() && $value.val() && !$order.val()) {
				alert("정렬순서를 입력하세요.");
				$order.focus();
				ret = false;
			}
		}
	});
	return ret;
};
var showLog = function showLog(name) {
	console.log("=================");
	console.log(name);
	console.log("=================");
}
$(document).ready(function() {
	var categoryKey, categoryValue, categoryUseYN, categoryOrder;
	var listlength = $("#category${categoryIdx}Length", opener.document).val();
	var check = true;
	var idx = 0;

	if (listlength != 0) {
		index = listlength-1;
		$("tbody > tr").remove();
		for (var i=0; i<listlength; i++) {
			var select1="", select2="";
			console.log(">> useYn : "+$("input[name='category${categoryIdx}List["+i+"].useYn']", opener.document).val());
			if ($("input[name='category${categoryIdx}List["+i+"].useYn']", opener.document).val() == "Y") select2 = "selected=\"selected\"";
			else select1 = "selected=\"selected\"";

			$("tbody").append("<tr>"+
								"<td><input type=\"text\" name=\"category${categoryIdx}List["+i+"].categoryKey\" id=\"category${categoryIdx}List"+i+".categoryKey\" class=\"input100\" value=\""+$("input[name='category${categoryIdx}List["+i+"].categoryKey']", opener.document).val()+"\" /></td>"+
								"<td><input type=\"text\" name=\"category${categoryIdx}List["+i+"].categoryValue\" id=\"category${categoryIdx}List"+i+".categoryValue\" class=\"input100\" value=\""+$("input[name='category${categoryIdx}List["+i+"].categoryValue']", opener.document).val()+"\" /></td>"+
								"<td>"+
								"<select name=\"category${categoryIdx}List["+i+"].useYn\" id=\"category${categoryIdx}List"+i+".useYn\" class=\"input100\">"+
								//"<option value=\"\" "+select1+">선택</option>"+
								"<option value=\"Y\" "+select2+">사용</option>"+
								"<option value=\"N\" "+select1+">미사용</option></select>"+
								"</td>"+
								"<td><input type=\"text\" name=\"category${categoryIdx}List["+i+"].categoryOrder\" id=\"category${categoryIdx}List"+i+".categoryOrder\" class=\"input100\" value=\""+$("input[name='category${categoryIdx}List["+i+"].categoryOrder']", opener.document).val()+"\" /></td>"+
								"<td class=\"taC\">"+
								"<a href=\"#\" onclick=\"addFields(this); return false;\" class=\"btn_white\" data-type=\"insert\">+</a>"+
								//"<a href=\"#\" onclick=\"remove_column_def(this); return false;\" class=\"btn_white\" data-type=\"delete\">-</a>"+
								"</td>"+
								"</tr>");
		}
	}

	$("#name").val($("#categoryDefList${categoryIdx-1}\\.name", opener.document).val());
	$("#width").val($("#categoryDefList${categoryIdx-1}\\.width", opener.document).val());
	$("#showYn").val($("#categoryDefList${categoryIdx-1}\\.showYn", opener.document).val());
	$("#showListYn").val($("#categoryDefList${categoryIdx-1}\\.showListYn", opener.document).val());
	$("#showSearchYn").val($("#categoryDefList${categoryIdx-1}\\.showSearchYn", opener.document).val());
});
//]]>
</script>

<h2>카테고리 설정</h2>
<div class="mB10">
	<span class="mR10 mL10 mR10"><b><label for="name">이름</label></b></span><input type="text" id="name" name="name" class="input200" value="" data-validator="on" data-required="y" data-fieldName="이름"/>
	<span class="mR10 mL10 mR10"><b><label for="width">컬럼폭</label></b></span><input type="text" id="width" name="width" class="input50" value="" data-validator="on" data-required="y" data-fieldName="컬럼폭"/>
</div>
<div>
	<span class="mR10 mL10 mR10"><b><label for="showYn">전체 사용여부</label></b></span>
	<select id="showYn" name="showYn" data-validator="on" data-required="n" data-fieldName="전체 사용여부">
		<option value="Y" selected="selected">표시</option>
		<option value="N">숨김</option>
	</select>
	<span class="mR10 mL10 mR10"><b><label for="showListYn">목록 출력여부</label></b></span>
	<select id="showListYn" name="showListYn" data-validator="on" data-required="n" data-fieldName="목록 출력여부">
		<option value="Y" selected="selected">표시</option>
		<option value="N">숨김</option>
	</select>
	<span class="mR10 mL10 mR10"><b><label for="showSearchYn">검색 사용여부</label></b></span>
	<select id="showSearchYn" name="showSearchYn" data-validator="on" data-required="n" data-fieldName="검색 사용여부">
		<option value="Y" selected="selected">표시</option>
		<option value="N">숨김</option>
	</select>
<div>

<h2>카테고리 관리</h2>

<form:form commandName="searchVO" name="form" id="form">
	<table class="tableSt_list row_over">
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<colgroup>
			<col style="width: 8%" />
			<col span="1" />
			<col style="width: 20%" />
			<col style="width: 20%" />
			<col style="width: 10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">표시값</th>
				<th scope="col">실제값</th>
				<th scope="col">사용여부</th>
				<th scope="col">정렬순서</th>
				<th scope="col">추가</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" name="category${categoryIdx}List[0].categoryKey" id="category${categoryIdx}List0.categoryKey" class="input100" value="" /></td>
				<td><input type="text" name="category${categoryIdx}List[0].categoryValue" id="category${categoryIdx}List0.categoryValue" class="input100" value="" /></td>
				<td>
					<select name="category${categoryIdx}List[0].useYn" id="category${categoryIdx}List0.useYn" class="input100">
						<option value="Y" selected="selected">사용</option>
						<option value="N">미사용</option>
					</select>
				</td>
				<td><input type="text" name="category${categoryIdx}List[0].categoryOrder" id="category${categoryIdx}List0.categoryOrder" class="input100" value="" /></td>
				<td class="taC">
					<a href="#" onclick="addFields(this); return false;" class="btn_white" data-type="insert">+</a>
					<%--
					<a href="#" onclick="rmFields(this); return false;" class="btn_white" data-type="delete">-</a>
					--%>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="btn_boxR">
		<a href="#" onclick="rmFields(); return false;" class="btn_dblue">라인 제거</a>
		<a href="#" onclick="save(); return false;" class="btn_cyan">저장</a>
	</div>
</form:form>
