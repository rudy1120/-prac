<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>임대 알림서비스 수정</title>

<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

$(document).ready(function () {
	//서비스 이용기간 체크값 하나만 설정
	$('input[type="checkbox"][name="termCk"]').click(function(){
		if($(this).prop('checked')){
			$('input[type="checkbox"][name="termCk"]').prop('checked',false);
			$(this).prop('checked',true);
		}
	});
});

//유효성검사
function fn_validation1(){
	
	if($("#name").val() == ""){
		alert("이름을 입력하세요");
		return;
	}
	if($("#tel2").val() == ""){
		alert("핸드폰 중간번호를 입력하세요.");
		return;
	}
	if($("#tel3").val() == ""){
		alert("핸드폰 끝번호를 입력하세요.");
		return;
	}
/* 	if($("input[name=areauses]").val() == undefined){
		alert("관심 분양정보를 선택해주세요.");
		return;
	} */

	//핸드폰번호
	var tel1 = $("#tel option:selected").val();
	var tel2 =  $("#tel2").val();
	var tel3 = $("#tel3").val();
	var telNum =  tel1 + tel2 + tel3;
	
	
	$("#phone").val(telNum);
	
	fn_regtYn();
	//fn_check();
	//$("#supplyForm").submit();
	
}

function fn_validation2(){
	if($("input:checkbox[name=housingtype]").is(":checked") == false){
		alert("관심 주택유형을 선택해주세요.");
		return;
	}
	if($("input:checkbox[name=termCk]").is(":checked") == false){
		alert("서비스 이용기간을 선택해주세요.");
		return;
	}
	
	fn_save();
	
}
//정보 일치 여부
function fn_regtYn(){
	$.ajax({
		type:'post',
		url:'/bmc/apply/chkSMS.do',
		data : $("#chkForm").serialize(),
		success : function(data) {
			var obj = JSON.parse(data);

			var cnt = obj.length;
			
			//신청내역 있는경우
			if(cnt >0){
				
				$("#chkDiv").css("display","none");
				$("#uptDiv").css("display","block");
				
				$("#name2").val(obj[0].name);
				$("#phone2").val(obj[0].phone);
				
 				for( var i = 0 ; i < obj.length; i++ ){
					$('input:checkbox[id="'+obj[i].housingtype+'"]').prop("checked", true); //checked 처리
					$('input:checkbox[id="'+obj[i].termcode+'"]').prop("checked", true); //checked 처리
				}
			}else{
				alert("신청하신 내역이 없습니다.")
			}
			

		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

//신청내용 저장
function fn_save(){
	
	if(confirm("알림서비스를 수정하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/apply/updtSMS.do',
			data : $("#updtForm").serialize(),
			dataType : "html",
			success : function(data) {
/* 				gfn_loadingClose();
				$('#insertupdateCk').val(data.insertupdateCk);
				gfn_smpAlert('알림','저장되었습니다.');
				fn_reset(); 
				fn_empSearch(); */
				
				
				alert(data);
				self.close();
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
	}

}
</script></head>
<body>
	<h3 class="tit" style="font-size:3em;margin-bottom:20px;">임대 알림서비스 신청</h3>
	<p style="text-align:center; color:#fff;">- 알림 서비스를 신청하시면 새로운 정보가 있을 때 휴대폰 문자로 알려드립니다 -</p>
	<div class="all_wrap">
		<div class="inr vision">
			<div class="area_box box2 recruit-box1">
			<p class="box-comment"><strong class="strong_st">임대 알림서비스 신청</strong><br>SMS수정 페이지 입니다.</p>
			</div>
		</div>
		<div class="bt_wrap">
		<span><a href="/bmc/apply/applyForm2.do" class="bt bt1" title="임대 알림서비스 신청">SMS 신청</a></span>
		<span><a href="/bmc/apply/updtForm2.do" class="bt bt2 on" title="임대 알림서비스 수정">SMS 수정</a></span>
		<span><a href="/bmc/apply/delForm2.do" class="bt bt3" title="임대 알림서비스 철회">SMS 철회</a></span>
	</div>
	<div id="chkDiv" class="form_wrap">
		<form:form commandName="applySmsVO" name="chkForm" id="chkForm" action="" methodParam="post">
			<fieldset>
				<input type="hidden" name="savetype" value="H"/>
				<input type="hidden" name="phone" id="phone" value=""/>
				<table class="bod_maintain">
					<caption class="blind">임대정보 알림 서비스(SMS) 신청정보확인을 위해 성명, 휴대폰 번호로 이루어진 표</caption>
						<tbody>
						<tr>
							<td class="first fTxt">성명</td>
							<td class="first sod">
								<label for="name" class="blind">성명</label>
								<input class="inp1" type="text" id="name" name="name" maxlength="30" 
									data-validator="on" data-required="y" data-fieldName="아이디" placeholder="이름 입력" oninput="this.value=this.value.replace(/[^가-힣ㄱ-ㅎㅏ-ㅣㆍᆢa-zA-Z]/gi,'');"/>
							</td>
						</tr>
					
						<tr>
							<td class="fTxt">휴대폰 번호</td>
							<td>
								<label for="tel" class="blind">휴대폰 앞자리번호 선택</label>
								<select id="tel" class="inp1">
									<option>010</option>
									<option>011</option>
									<option>016</option>
									<option>017</option>
									<option>018</option>
									<option>019</option>
								</select>
								<span>-</span>
								<label for="tel2" class="blind">휴대폰 중간번호 입력</label>
								<input type="text" class="inp1 inp2" id="tel2" name="tel2" maxlength="4" 
									data-validator="on" data-required="y" data-fieldName="휴대전화 중간자리" placeholder="휴대전화 중간자리" oninput="this.value=this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"/>
								<span>-</span>
								<label for="tel3" class="blind">휴대폰 끝번호 입력</label>
								<input type="text" class="inp1" id="tel3" name="tel3" maxlength="4" 
									data-validator="on" data-required="y" data-fieldName="휴대전화 뒷자리" placeholder="휴대전화 뒷자리" oninput="this.value=this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"/>
							</td>
						</tr>
					</tbody>
					</table>
					<br/>
					<div style="width:100%; height:10%; display:flex; justify-content:center; align-items:center;">
						<input type="button" onclick="fn_validation1(); return false;"  value="확인" class="btn_submit"/>
					</div>
			</fieldset>
		</form:form>
	</div>
	<div id="uptDiv" style="display:none;" class="form_wrap">
		<form:form commandName="applySmsVO" name="updtForm" id="updtForm" action="" methodParam="post">
			<fieldset>
				<table class="bod_maintain">
					<caption class="blind">임대정보 알림 서비스(SMS) 신청정보 수정을 위해 성명, 휴대폰 번호,공급용도로 이루어진 표</caption>
						<thead><tr><th scope="col"></th><th scope="col"></th></tr></thead>
						<tbody>
						<tr>
							<td class="fTxt">성명</td>
							<td>
								<label for="name2" class="blind">성명</label>
								<input type="text" id="name2" name="name2" readonly placeholder="이름 입력"/>
							</td>
						</tr>
					
						<tr>
							<td class="fTxt">휴대폰 번호</td>
							<td>
								<label for="phone2" class="blind">휴대폰 번호</label>
								<input type="text" id="phone2" name="phone2" readonly placeholder="휴대전화 번호"/>
							</td>
						</tr>
						<tr>
							<td>
								<label class="fTxt">주택유형</label>
							</td>
							<td>
								<c:forEach var="list" items="${resultList}" varStatus="status">
									<label for="${list.idx}" style="display:flex;">
										<input type="checkbox" name="housingtype" id="${list.idx}" value="${list.idx}" />${list.codename}
									</label>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>
								<label class="fTxt">서비스 이용기간</label>
							</td>
							<td>
								<c:forEach var="term" items="${termList}" varStatus="status">
 									<div style="display:inline-block;width:29%; vertical-align: top">
										<label for="${term.idx}" style="display:flex;">
											<input type="checkbox" name="termCk" id="${term.idx}" value="${term.idx}" />${term.codename}
										</label>
									</div>
								</c:forEach>
								</td>
						</tr>
					</tbody>
				</table>
				<div id="checkVals">
					
				</div>
				<br/>
				<div style="width:100%; height:10%; display:flex; justify-content:center; align-items:center;">
					<input type="button" onclick="fn_validation2(); return false;"  value="수정" class="btn_submit"/>
				</div>
			</fieldset>
		</form:form>
	</div>
	</div>
</body>
</html>