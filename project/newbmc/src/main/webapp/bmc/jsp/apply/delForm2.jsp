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

<title>임대주택 모집공고 알리미 서비스 철회</title>

<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

//유효성검사
function fn_validation(){
	
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
			
				fn_save();

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

	if(confirm("알림서비스를 철회하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/apply/delSMS.do',
			data : $("#chkForm").serialize(),
			dataType : "html",
			success : function(data) {
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
			<p class="box-comment"><strong class="strong_st">임대 알림서비스 신청</strong><br>SMS철회 페이지 입니다.</p>
			</div>
		</div>
		<div class="bt_wrap">
			<span><a href="/bmc/apply/applyForm2.do" class="bt bt1" title="임대 알림서비스 신청">SMS 신청</a></span>
			<span><a href="/bmc/apply/updtForm2.do" class="bt bt2" title="임대 알림서비스 수정">SMS 수정</a></span>
			<span><a href="/bmc/apply/delForm2.do" class="bt b3 on" title="임대 알림서비스 철회">SMS 철회</a></span>
		</div>
		<div id="chkDiv" class="form_wrap">
			<form:form commandName="applySmsVO" name="chkForm" id="chkForm" action="" methodParam="post">
				<fieldset>
					<input type="hidden" name="savetype" value="H"/>
					<input type="hidden" name="phone" id="phone" value=""/>
					<table class="bod_maintain">
						<caption class="blind">임대정보 알림 서비스(SMS) 철회를 위한 성명, 휴대폰 번호 입력칸으로 이루어진 표</caption>
							<thead><tr><th scope="col"></th><th scope="col"></th></tr></thead>
							<tbody>
							<tr>
								<td class="first fTxt">성명</td>
								<td class="first sod">
									<label for="name"></label>
									<input class="inp1" type="text" id="name" name="name" maxlength="30" 
										data-validator="on" data-required="y" data-fieldName="아이디" placeholder="이름 입력" oninput="this.value=this.value.replace(/[^가-힣ㄱ-ㅎㅏ-ㅣㆍᆢa-zA-Z]/gi,'');"/>
								</td>
							</tr>
							<tr>
								<td class="fTxt">휴대폰 번호</td>
								<td>
									<label for="tel" class="blind">휴대폰 앞자리번호 입력</label>
									<select id="tel" class="inp1">
										<option>010</option>
										<option>011</option>
										<option>016</option>
										<option>017</option>
										<option>018</option>
										<option>019</option>
									</select>
									<span>-</span>
									<label for="tel2" class="blind">휴대폰 중간자리번호 입력</label>
									<input class="inp1 inp2" type="text" id="tel2" name="tel2" maxlength="4" 
										data-validator="on" data-required="y" data-fieldName="휴대전화 중간자리" placeholder="휴대전화 중간자리" oninput="this.value=this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"/>
									<span>-</span>
									<label for="tel3" class="blind">휴대폰 끝자리번호 입력</label>
									<input class="inp1" type="text" id="tel3" name="tel3" maxlength="4" 
										data-validator="on" data-required="y" data-fieldName="휴대전화 뒷자리" placeholder="휴대전화 뒷자리" oninput="this.value=this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"/>
								</td>
							</tr>
							</tbody>
						</table>
						<br/>
						<div style="width:100%; height:10%; display:flex; justify-content:center; align-items:center;">
							<input type="button" onclick="fn_validation(); return false;"  value="확인" class="btn_submit"/>
						</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</body>
</html>