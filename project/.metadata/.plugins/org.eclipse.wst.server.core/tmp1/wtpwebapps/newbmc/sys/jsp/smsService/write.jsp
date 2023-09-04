<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
    request.setCharacterEncoding("UTF-8");
%>

<link rel="stylesheet" href="/common/js/jquery/timepicker/jquery.ui.timepicker.css"/>
<script src="/common/js/jquery/timepicker/jquery.ui.timepicker.js"></script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>

<script type="text/javascript">
$(document).ready(function() {

// 	if($("#smsyn").val()=='Y'){
// 		$('input:radio[id=smsY]').attr("checked",true);
// 		$(".selTr").attr('style', "display:table-row;");
// 	}else if($("#smsyn").val()=='N'){
// 		$('input:radio[id=smsN]').attr("checked",true);
// 		$(".selTr").attr('style', "display:none;");
// 	}
	//문자전송 선택시 
// 	$("input:radio[name=smsYN]").click(function() {

// 		if (this.value == 'Y') {
// 			$(".selTr").attr('style', "display:table-row;");
// 		} else if (this.value == 'N') {
// 			$(".selTr").attr('style', "display:none;");
			
// 			//smsmsg 초기화
// 			/* $("#smsSubject").val('');
// 			$("#smsMsg").val('');
// 			//smsoptions 체크 초기화
// 			$('input:checkbox[name="smsOptions"]').prop("checked",false); */
// 		}
// 	});

	//선택옵션 
// 	var optionVal = $("#options").val();
// 	var arr = optionVal.split(',');

// 	for( var i = 0 ; i < arr.length; i++ ){
// 		$('input:checkbox[id="'+arr[i]+'"]').prop("checked", true); //checked 처리
// 	}
	
	
});

function inputTel(){
	$("#trCallback").val($("#tel1").val() + $("#tel2").val() + $("#tel3").val());
}
</script>
<h2>문자발송</h2>

<%-- ============================ 검색 영역 ============================ --%>
<form:form commandName="searchVO" id="smsForm" name="smsForm" action="/sys/smsApply/sendSms.do"
			methodParam="post" htmlEscape="false"  target="_self" onSubmit="return validator() && confirm('발송 후 취소는 불가합니다. 선택하신 유형으로 문자 발송 하시겠습니까?') && inputTel()">
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<caption>문자 메시지 발송</caption>
		<colgroup>
			<col span="1" style="width:20%;"/>
			<col span="1"/>
		</colgroup>
		<tbody>
			<tr class="selTr">
				<th scope="row"><span class="red">*</span><label for="smsSubject">문자메시지 제목</label></th>
				<td>
					<input type="text" name="smsSubject" id="smsSubject" maxlength="40" data-validator="on" data-required="y" data-fieldName="제목"/>
				</td>
			</tr>
			<tr class="selTr">
				<th scope="row"><span class="red">*</span><label for="smsMsg">문자메시지 내용</label></th>
				<td>
					<!-- 			<p class="red mB10">※ 문자서비스를 보내실 사업지구/공급용도 유형을 선택해주세요. 해당 유형 문자알림신청자들에게 문자를 발송합니다.</p> -->
					<!-- <textarea rows="8" cols="50" name="smsMsg" id="smsMsg" class="selTr" style="display:none;">공사홈페이지 및 온비드에서 확인하시기 바랍니다.&#10;부산도시공사 홈페이지(http://www.bmc.busan.kr)&#10;온비드홈페이지(http://www.onbid.co.kr)</textarea> -->
					<textarea rows="8" cols="50" name="smsMsg" id="smsMsg"  maxlength="2000" rows="8" cols="50" data-validator="on" data-required="y" data-fieldName="내용"></textarea> <!-- <input type="text" name="smsMsg" id="smsMsg"  value="공사홈페이지 및 온비드에서 확인하시기 바랍니다.&#10;부산도시공사 홈페이지(http://www.bmc.busan.kr)&#10;온비드홈페이지(http://www.onbid.co.kr)"/> -->
				</td>
			</tr>
			<tr class="selTr">
				<th scope="row"><span class="red">*</span><label>문자 발신번호</label></th>
				<td><input type="hidden" name="trCallback" id="trCallback" >
					<input type="text" style="width:80px;" id="tel1" data-validator="on" data-required="y" data-fieldName="발신번호"/> <span>-</span> 
					<input type="text" style="width:80px;" id="tel2" data-validator="on" data-required="y" data-fieldName="발신번호"/> <span>-</span>
					<input type="text" style="width:120px;" id="tel3" data-validator="on" data-required="y" data-fieldName="발신번호"/>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<th scope="row"><label for="smsService">서비스 유형</label></th> -->
<!-- 				<td> -->
<%-- 					<input type="hidden" id="smsyn" value="${bbsMngVO.smsYN}"/>	 --%>
<!-- 					<label for="smsY"><input type="radio" id="smsY" name="smsYN" value="Y"/>분양 알림서비스</label> <label for="smsN"><input type="radio" id="smsN" name="smsYN" value="N"/>임대 알림서비스</label> -->
<!-- 				</td> -->
<!-- 			</tr> -->

	<%-- ==== 분양공고 ==== --%>
	
		 <input type="hidden" name="smsGbn" value="A" />
			<tr class="selTr">
				<th scope="row"><label for="smsService">공급용도</label></th>
				<td>
					<p class="red mB10">※ 문자서비스를 보내실 공급용도 유형을 선택해주세요. 해당 유형
						문자알림신청자들에게 문자를 발송합니다.</p> 
						<c:forEach var="uses" items="${usesList}">
						<!-- <option value="${uses.idx}">${uses.codename}</option> -->
						<label for="${uses.idx}"> <input type="checkbox"
							name="smsOptions" id="${uses.idx}" value="${uses.idx}" data-validator="on" data-required="n" data-fieldName="공급용도"/>${uses.codename}
						</label>
					</c:forEach>
				</td>
			</tr>
	
	<%-- ==== 임대공고 ==== --%>
	
			<!-- <input type="hidden" name="smsGbn" value="B" /> -->
			<tr class="selTr">
				<th scope="row"><label for="smsService">주택유형</label></th>
				<td>
					<p class="red mB10">※ 문자서비스를 보내실 주택유형 유형을 선택해주세요. 해당 유형
						문자알림신청자들에게 문자를 발송합니다.</p> 
						<c:forEach var="house" items="${housingList}">
						<!-- <option value="${uses.idx}">${uses.codename}</option> -->
						<label for="${house.idx}"> <input type="checkbox"
							name="smsOptions" id="${house.idx}" value="${house.idx}" />${house.codename}
						</label>
					</c:forEach>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" id="subtn" value="문자 발송"/>
	</div>
</form:form>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
