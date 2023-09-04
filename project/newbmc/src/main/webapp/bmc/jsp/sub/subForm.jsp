<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
String now = format.format(new Date());
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <link rel="stylesheet" type="text/css" href="/bmc/font/font.css"> -->
<link rel="stylesheet" type="text/css" href="../font/font.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>웹진 구독서비스 신청</title>
<link rel="stylesheet" type="text/css" href="/bmc/css/subscribe.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script> 
<script>

$(document).ready(function () {
	
	
});

//유효성검사
function fn_validation(){
	var blank_pattern = /[\s]/g;
	
	if(blank_pattern.test($("#name").val()) == true){
	    alert('이름에 공백은 사용할 수 없습니다. ');
	    return false;
	}
	
	if(blank_pattern.test($("#email").val()) == true){
	    alert('이메일에 공백은 사용할 수 없습니다. ');
	    return false;
	}
	
	if($("#name").val().trim() == ""){
		alert("이름을 입력하세요");
		return;
	}
	if($("#email").val().trim()== ""){
		alert("이메일을 입력하세요.");
		return;
	}

	if(!$("#agree").is(":checked")){
		alert('개인정보제공에 동의해주세요.');
		return;
	}

	var email =  $("#email").val();
	
	fn_save();
	//$("#supplyForm").submit();
	
}
//신청내용 저장
function fn_save(){
	
	//if (validator("#supplyForm") && confirm("알림서비스를 신청하시겠습니까")) {
	
	if(confirm("알림서비스를 신청하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/sub/subWebzine.do',
			data : $("#supplyForm").serialize(),
			dataType : "html",
			success : function(data) {
				alert(data);
				location.href = "/bmc/sub/subForm.do";
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
	}

}
</script></head>




<body>
	<h3 style="font-size:3em;margin-bottom:20px;" class="tit">웹진 구독서비스 신청</h3>
    <p style="text-align:center; color:#fff;">- 구독서비스를 신청하시면 새로운 웹진 발간 시 이메일로 알려드립니다 -</p>
	<div class="all_wrap">
		<div class="inr vision">
			<div class="area_box box2 recruit-box1">
			<p class="box-comment"><strong class="strong_st">웹진 구독서비스 신청</strong><br>이메일 신청 페이지 입니다.</p>
			</div>
		</div>
		<div class="bt_wrap">
			<a href="/bmc/sub/subForm.do" class="bt bt1 on" title="구독 알림서비스 신청">구독 신청</a>
			<a href="/bmc/sub/updtForm.do" class="bt bt2 " title="구독 알림서비스 수정">구독정보 수정</a>
			<a href="/bmc/sub/delForm.do" class="bt bt3" title="구독 알림서비스 철회">구독 철회</a>
		</div>
		<div class="form_wrap">
			<form id="supplyForm" name="supplyForm" action="/bmc/apply/applyForm.do" method="post">
			<div id="nppfs-loading-modal" style="display:none;"></div>
			<div class="nppfs-elements" style="display:none;"></div>
				<fieldset>
					<input type="hidden" name="phone" id="phone" value="">
					<table class="bod_maintain">
						<caption class="blind">웹진구독 알림 서비스 신청을 위한 성명, 휴대폰 번호, 공급용도, 개인정보 수집동의로 이루어진 표</caption>
							<thead>
								<tr><th scope="col"></th><th scope="col"></th></tr>
							</thead>
							<tbody>
							<tr>
								<td class="first fTxt">성명</td>
								<td class="first sod">
									<label for="name" class="blind">성명</label>
									<input class="inp1" type="text" id="name" name="name" maxlength="30" data-validator="on" data-required="y" data-fieldname="아이디" placeholder="이름 입력">
								</td>
							</tr>
						
							<tr>
								<td class="fTxt">이메일</td>
								<td>
									<input class="inputT inp1" style="width:300px;" type="email" id="email" name="email" maxlength="60" data-validator="on" data-required="y" data-fieldname="이메일" placeholder="이메일형식으로 입력해주세요">
								
								</td>
							</tr>
							
							<tr>
								<td class="fTxt">
									개인정보 수집동의
								</td>
								<td>
									<div class="terms_overflow shot text-justify mb30">
										<h5 class="text-center txt_b2">※ 개인정보 수집·이용에 대한 동의 『개인정보보호법』 제15조</h5>
										<p class="ehicheight ehiccolor">「개인정보보호법」시행으로 정보주체자이신 귀하께서 제공하시는 개인정보의 수집·이용에 대한 귀하의 동의가 필요하며, 해당 내용을 읽고 충분히 숙지하시고 동의 하여 주시기 바랍니다.<br/><br/> 
											1.개인정보의 수집·이용에 관한 사용<br>
											○ 개인정보 수집·이용·목적 : 부산도시공사는 귀하의 최소한의 개인정보를 통하여 부산도시공사 웹진 "바다가" 구독서비스를 제공하기 위한 목적으로 개인정보가 사용됨을 알려드립니다.<br/> 
											○ 수집하려는 개인정보의 항목 : 성명,  이메일주소  <br/>
											○ 개인정보의 보유 및 이용기간 : 웹진 구독서비스 신청시부터 ~ 웹진 구독서비스 철회시까지<br/> 
											○ 개인정보 수집·이용을 거부할 권리가 있으며 이 경우 웹진 구독서비스 이용이 제한 됩니다.</p>
										<p class="ehicheight ehiccolor"> 
											2.개인정보의 제3자 제공에 관한 사항<br>
											○ 관련 법률(개인정보보호법 제 17조)에 따라 제공가능한 경우를 제외하고는 별도의 동의 없이 제3자에게 개인정보를 제공하지 않습니다.</p>
									</div>
									<br>
									<label for="agree"><input type="checkbox" id="agree">상기내용을 확인하고 동의합니다.</label>
									<br>
								</td>
							</tr>
					</tbody></table>
					
					<div id="checkVals">
						
					</div>
					<br/>
					<div style="width:100%; height:10%; display:flex; justify-content:center; align-items:center;">
						<input type="button" onclick="fn_validation(); return false;" value="신청" class="btn_submit">
					</div>
				</fieldset>
			</form>
		</div>
	</div>

</body></html>