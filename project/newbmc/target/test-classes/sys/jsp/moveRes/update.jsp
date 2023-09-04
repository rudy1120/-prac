<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<script>
$(document).ready(function(){
	dp3.bind($("[data-date=y]"));
});
</script>

<script type="text/javascript">

function fn_chkTime(){
	$.ajax({
		type:'post',
		url:'/sys/moveRes/chkTime.do',
		data : $("#modify").serialize(),
		success : function(data) {
			var obj = JSON.parse(data);
			var cnt = obj.length;
			var timeSel = document.getElementById("timeSel");
			
			if(cnt >= 3){
				$("#timeSel").css("display","none");
				$("#noSel").css("display","block");
			}else{
				if(cnt ==0){
					timeSel.innerHTML =  '<option value="">----- 선택 -----</option>'
										+ '<option value="T1">09:00 ~ 12:00</option>'
										+ '<option value="T2">12:00 ~ 15:00</option>'
										+ '<option value="T3">15:00 ~ 18:00</option>';
				}
				if(cnt == 1){
					if(obj[0].move_time == "T1"){
						timeSel.innerHTML =  '<option value="">----- 선택 -----</option>'
											+ '<option value="T2">12:00 ~ 15:00</option>'
											+ '<option value="T3">15:00 ~ 18:00</option>';
					}
					if(obj[0].move_time == "T2"){
						timeSel.innerHTML =  '<option value="">----- 선택 -----</option>'
											+ '<option value="T1">09:00 ~ 12:00</option>'
											+ '<option value="T3">15:00 ~ 18:00</option>';
					}
					if(obj[0].move_time == "T3"){
						timeSel.innerHTML =  '<option value="">----- 선택 -----</option>'
											+ '<option value="T1">09:00 ~ 12:00</option>'
											+ '<option value="T2">12:00 ~ 15:00</option>';
					}
				}
				if(cnt == 2){
					if(obj[0].move_time == "T1"){
						if(obj[1].move_time == "T2"){
							timeSel.innerHTML =  '<option value="">----- 선택 -----</option>'
												+ '<option value="T3">15:00 ~ 18:00</option>';
						}
						if(obj[1].move_time == "T3"){
							timeSel.innerHTML =  '<option value="">----- 선택 -----</option>'
												+ '<option value="T2">12:00 ~ 15:00</option>';
						}
					}
					if(obj[0].move_time == "T2"){
						if(obj[1].move_time == "T3"){
							timeSel.innerHTML =  '<option value="">----- 선택 -----</option>'
												+ '<option value="T1">09:00 ~ 12:00</option>';
						}
					}
				}
				$("#noSel").css("display","none");
				$("#timeSel").css("display","block");
			}

		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
} 

</script>

<c:set var="isNew"   value="${reserveVO.idx eq 0}"/>
<c:set var="postUrl" value="modifyProc"/>

<form:form commandName="reserveVO" id="cancel" name="cancel"  methodParam="post" action="/sys/moveRes/detailList.do?mId=${menuVO.mId}&gbn2=${reserveVO.gbn2}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="reserveVO" id="modify" name="modify" action="/sys/moveRes/${postUrl}.do?mId=${menuVO.mId}&gbn2=${reserveVO.gbn2}" methodParam="post" onsubmit="return validator();" htmlEscape="false" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<h2>${title} 예약자 및 이사날짜 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="name">성함</label></th>
				<td>
					<form:input path="name" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="성함"/>
				</td>
			</tr>
			<tr hidden>
				<th class="i1" scope="row"><span class="red">*</span><label for="cust_id">고객번호</label></th>
				<td>
					<form:input path="cust_id" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="고객번호"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="tel">연락처</label></th>
				<td>
					<form:input path="tel" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="연락처"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="addr1">동</label></th>
				<td>
					<form:input path="addr1" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="동"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="addr2">호수</label></th>
				<td>
					<form:input path="addr2" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="호수"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><label for="move_date">이사일자</label></th>
				<td>
					<form:input path="move_date" readonly="true" cssClass="input100" data-validator="off" data-required="y" data-inputType="date" data-fieldName="이사일자" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><label for="m_time">이사시간</label></th>
				<td>
					<form:input path="m_time" readonly="true" maxlength="300" cssClass="input500" data-validator="off" data-required="y" data-fieldName="이사시간"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><label for="regdate">등록일시</label></th>
				<td>
					<form:input path="regdate" readonly="true" maxlength="300" cssClass="input500" data-validator="off" data-required="y" data-fieldName="등록일시"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="datepick">수정 이사일자</label></th>
				<td>
					<input id="datepick" cssClass="input100" data-date="y" data-validator="off" data-required="n" 
						data-inputType="date" data-fieldName="수정이사일자" autocomplete="off" readonly="true"
						placeholder="날짜를 선택해주세요." style="border: 1px solid #dedede !important" />
					<input type="hidden" id="dateSel" name="dateSel"/>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><span class="red">*</span><label for="timeSel">수정 이사시간</label></th>
				<td>
					<select id="timeSel" name="timeSel" maxlength="300" cssClass="input500" data-validator="off" data-required="y" data-fieldName="수정이사시간">
						<%-- <form:option value="T1">09:00 ~ 12:00</form:option>
						<form:option value="T2">13:00 ~ 16:00</form:option>
						<form:option value="T3">17:00 ~ 20:00</form:option> --%>
					</select>
					<sapn id="noSel" style="display:none;">** 선택 가능한 시간이 없습니다 **</sapn>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><label for="updt_yn">수정가능여부</label></th>
				<td>
					<form:select path="updt_yn" maxlength="300" cssClass="input500" data-validator="off" data-required="y" data-fieldName="수정가능여부">
						<form:option value="Y">수정 가능</form:option>
						<form:option value="N">수정 불가능</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><label for="gbn1">계약 차수</label></th>
				<td>
					<form:select path="gbn1" maxlength="300" cssClass="input500" data-validator="off" data-required="y" data-fieldName="기계약자여부">
						<form:option value="Y">1차 계약자</form:option>
						<form:option value="N">2차 계약자</form:option>
						<form:option value="T">3차 계약자</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<th class="i1" scope="row"><label for="use_yn">해약여부</label></th>
				<td>
					<form:select path="use_yn" maxlength="300" cssClass="input500" data-validator="off" data-required="y" data-fieldName="해약여부">
						<form:option value="Y">미해약</form:option>
						<form:option value="N">해약</form:option>
					</form:select>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script>
$(function() {
	
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
