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
<title>웹진 구독서비스 수정</title>

<link rel="stylesheet" type="text/css" href="/bmc/css/subscribe.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(function () {

	
});


function fn_check(obj){
	
	//area 값
	var areaVal = $("#areaSel").val();
	var areausesVal = areaVal +"-" +obj;
	
	
 	var dupCk = $("#"+areausesVal).val();


	if(dupCk == undefined){
		
		var chkVal = $("<input type='hidden' id='"+areausesVal+"' name='areauses' value='"+areausesVal+"'>");
		$("#checkVals").append(chkVal);

	}else{
		//해당 값 존재하면 삭제
		$("#"+areausesVal).remove();
	}
	
	
}


//유효성검사
function fn_validation1(){
	
	if($("#name").val() == ""){
		alert("이름을 입력하세요");
		return;
	}
	if($("#email").val() == ""){
		alert("이메일 주소를 입력하세요.");
		return;
	}
	
	fn_regtYn();
	//fn_check();
	//$("#supplyForm").submit();
	}

function fn_validation2(){
	if($("#email3").val() == ""){
		alert("변경할 이메일 주소를 입력하세요.");
		return;
	}
	fn_save();
	
}
//정보 일치 여부
function fn_regtYn(){
	$.ajax({
		type:'post',
		url:'/bmc/sub/chkEmail.do',
		data : $("#chkForm").serialize(),
		success : function(data) {
			var obj = JSON.parse(data);

			var cnt = obj.length;
			
			//신청내역 있는경우
			if(cnt >0){
				
				$("#chkDiv").css("display","none");
				$("#uptDiv").css("display","block");
				
				$("#idx").val(obj[0].idx);
				$("#name2").val(obj[0].name);
				$("#email2").val(obj[0].email);

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
	
	
	if(confirm("구독서비스 정보를 수정하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/sub/upWebzine.do',
			data : $("#updtForm").serialize(),
			dataType : "html",
			success : function(data) {

				alert(data);
				self.close();
				location.href = "/bmc/sub/updtForm.do";
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
	}

}
</script></head>
<body>
<h3 style="font-size:3em;margin-bottom:20px;" class="tit">웹진 구독서비스 수정</h3>
    <p style="text-align:center; color:#fff;">- 구독서비스를 신청하시면 새로운 웹진 발간 시 이메일로 알려드립니다 -</p>
	<div class="all_wrap">
		<div class="inr vision">
			<div class="area_box box2 recruit-box1">
			<p class="box-comment"><strong class="strong_st">웹진 구독서비스 수정</strong><br>이메일 수정 페이지 입니다.</p>
			</div>
		</div>
		<div class="bt_wrap">
			<a href="/bmc/sub/subForm.do" class="bt bt1 " title="구독 알림서비스 신청">구독 신청</a>
			<a href="/bmc/sub/updtForm.do" class="bt bt2 on" title="구독 알림서비스 수정">구독정보 수정</a>
			<a href="/bmc/sub/delForm.do" class="bt bt3" title="구독 알림서비스 철회">구독 철회</a>
		</div>
		<div id="chkDiv" class="form_wrap">
			<form:form commandName="subscribeVO" name="chkForm" id="chkForm" action="" methodParam="post">
				<fieldset>
					<table class="bod_maintain">
						<caption class="blind">웹진구독서비스  신청정보확인을 위해 성명, 이메일로 이루어진 표</caption>
							<thead><tr><th scope="col"></th><th scope="col"></th></tr></thead>
							<tbody>
							<tr>
								<td class="first fTxt">성명</td>
								<td class="first sod">
									<label for="name" class="blind">성명</label>
									<input class="inp1" type="text" id="name" name="name" maxlength="30" data-validator="on" data-required="y" data-fieldName="아이디" placeholder="이름 입력"/>
								</td>
							</tr>
							<tr>
								<td class="fTxt">이메일</td>
								<td>
									<input class="inputT inp1" style="width:300px;" type="email" id="email" name="email" maxlength="60" data-validator="on" data-required="y" data-fieldname="이메일" placeholder="이메일형식으로 입력해주세요">
								
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
			<form:form commandName="subscribeVO" name="updtForm" id="updtForm" action="" methodParam="post">
				<fieldset>
					<table class="bod_maintain">
						<caption class="blind">구독알림서비스 수정사항 입력 표</caption>
						<thead><tr><th scope="col"></th><th scope="col"></th></tr></thead>
						<tbody>
						<input type ="hidden" id="idx" name="idx" >
						<tr>
							<td>성명</td>
							<td>
								<label for="name2" class="blind">성명</label>
								<input type="text" id="name2" name="name2" readonly placeholder="이름 입력"/>
							</td>
						</tr>
					
						<tr>
							<td>기존 이메일</td>
							<td>
								<label for="email2" class="blind">이메일</label>
								<input type="text" style="width:300px;"  id="email2" name="email2" readonly placeholder="이메일"/>
							</td>
						</tr>
						<tr>
							<td>수정 이메일</td>
							<td>
								<label for="email3" class="blind">이메일</label>
								<input type="text" style="width:300px;"  id="email3" name="email3" placeholder="변경할 이메일 주소를 입력해주세요"/>
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