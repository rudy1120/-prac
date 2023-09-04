<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String now = format.format(new Date());
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<title>임대 알림서비스 신청</title>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script>
<script>
$(document).ready(function () {
	
	if (document.location.protocol == 'http:') {
	    document.location.href = document.location.href.replace('http:', 'https:');
	}
	
	//[2019.11.20.] 키보드 보안모듈
	$("#userAgent").text(navigator.userAgent);
	/*
	npPfsStartup(document.form1, false, true, true, "enc", "on");
	1. form 양식 : 기본값 DOM Document 상의 첫번째 form
	2. 개인방화벽 사용여부 : 기본값 false
	3. 키보드보안 사용여부 : 기본값 false
	4. 단말정보수집 사용여부 : 기본값 false
	5. 키보드보안 E2E 필드 설정 속성명 : 기본값 "enc"
	6. 키보드보안 E2E 필드 설정 속성값: 기본값 "on"
	부가적인 설정은(예, 설치확인 등) /pluginfree/js/nppfs-1.0.0.js를 수정하여 설정하십시오.
	*/
	npPfsStartup(null, false, true, false, false, "npkencrypt", "on");
	
	//서비스 이용기간 체크값 하나만 설정
	$('input[type="checkbox"][name="termCk"]').click(function(){
		if($(this).prop('checked')){
			$('input[type="checkbox"][name="termCk"]').prop('checked',false);
			$(this).prop('checked',true);
		}
	});
});

//유효성검사
function fn_validation(){
	
	if($("#name").val() == "" || $("#name").val().match(/[ㄱ-ㅎ ㅏ-ㅣ]/gi)){
		alert("정확한 이름 형식으로 입력하세요.");
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
	/* if(isNaN($("#tel2").val())){
		alert("숫자만 입력가능합니다");
		return;
	}
	if(isNaN($("#tel3").val())){
		alert("숫자만 입력가능합니다");
		return;
	} */
	
	if($("input:checkbox[name=housingtype]").is(":checked") == false){
		alert("관심 주택유형을 선택해주세요.");
		return;
	}
	if($("input:checkbox[name=termCk]").is(":checked") == false){
		alert("서비스 이용기간을 선택해주세요.");
		return;
	}
	if(!$("#agree").is(":checked")){
		alert('개인정보제공에 동의해주세요.');
		return;
	}

	//핸드폰번호
	var tel1 = $("#tel option:selected").val();
	var tel2 =  $("#tel2").val();
	var tel3 = $("#tel3").val();
	var telNum =  tel1 + tel2 + tel3;
	
	
	$("#phone").val(telNum);
	
	fn_save();

	
}
//신청내용 저장
function fn_save(){
	

	
	if(confirm("알림서비스를 신청하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/apply/applySMS.do',
			data : $("#supplyForm").serialize(),
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
<h3 style="font-size:3em;margin-bottom:20px;" class="tit">임대 알림서비스 신청</h3>
    <p style="text-align:center; color:#fff;">- 알림 서비스를 신청하시면 새로운 정보가 있을 때 휴대폰 문자로 알려드립니다 -</p>
	
	<div class="all_wrap">
	
		<div class="inr vision">
			<div class="area_box box2 recruit-box1">
			<p class="box-comment"><strong class="strong_st">임대 알림서비스 신청</strong><br>SMS신청 페이지 입니다.</p>
			</div>
		</div>
		<div class="bt_wrap">
			<a href="/bmc/apply/applyForm2.do"  class="bt bt1 on" title="임대 알림서비스 신청">SMS 신청</a>
			<a href="/bmc/apply/updtForm2.do" class="bt bt2" title="임대 알림서비스 수정">SMS 수정</a>
			<a href="/bmc/apply/delForm2.do" class="bt bt3" title="임대 알림서비스 철회">SMS 철회</a>
		</div>
		<div class="form_wrap">
			<form:form commandName="applySmsVO" name="supplyForm" id="supplyForm" action="" methodParam="post">
				<fieldset>
					<input type="hidden" name="savetype" value="H"/>
					<input type="hidden" name="phone" id="phone" value=""/>
					<table class="bod_maintain">
						<caption class="blind">임대정보 알림 서비스(SMS) 신청을 위한 성명, 휴대폰 번호, 공급용도, 개인정보 수집동의로 이루어진 표</caption>
							<thead><tr><th scope="col"></th><th scope="col"></th></tr></thead>
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
									<label for="tel2" class="blind">휴대폰 중간자리번호 입력</label>
									<input class="inputT inp1 inp2" type="text" id="tel2" name="tel2" maxlength="4" 
										data-validator="on" data-required="y" data-fieldName="휴대전화 중간자리" placeholder="휴대전화 중간자리" oninput="this.value=this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"/>
									<span>-</span>
									<label for="tel3" class="blind">휴대폰 끝자리번호 입력</label>
									<input class="inputT inp1" type="text" id="tel3" name="tel3" maxlength="4" 
										data-validator="on" data-required="y" data-fieldName="휴대전화 뒷자리" placeholder="휴대전화 뒷자리" oninput="this.value=this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"/>
								</td>
							</tr>
							<tr>
								<td>
									<label class="fTxt">주택유형</label>
								</td>
								<td>
									<c:forEach var="list" items="${resultList}" varStatus="status">
										<div style="display:inline-block;width:29%; vertical-align:top">
											<label for="${list.idx}" style="display:flex;">
												<input type="checkbox" name="housingtype" id="${list.idx}" value="${list.idx}" />${list.codename}
											</label>
										</div>
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
							<tr>
								<td class="fTxt">
									개인정보 수집동의
								</td>
								<td>
									<div class="terms_overflow shot text-justify mb30">
										<h5 class="text-center txt_b2">※ 개인정보 수집·이용에 대한 동의 『개인정보보호법』 제15조</h5>
										<p class="ehicheight ehiccolor">「개인정보보호법」시행으로 정보주체자이신 귀하께서 제공하시는 개인정보의 수집·이용에 대한 귀하의 동의가 필요하며, 해당 내용을 읽고 충분히 숙지하시고 동의 하여 주시기 바랍니다.<br/><br/> 
											1.개인정보의 수집·이용에 관한 사용<br>
											○ 개인정보 수집·이용·목적 : 부산도시공사는 귀하의 최소한의 개인정보를 통하여 분양정보 관련 문자알림서비스를 제공하기 위한 목적으로 개인정보가 사용됨을 알려드립니다.<br/> 
											○ 수집하려는 개인정보의 항목 : 성명,  휴대폰번호  <br/>
											○ 개인정보의 보유 및 이용기간 : 문자알림 서비스 신청시부터 ~ 서비스 이용기간 만료 및 서비스 철회시까지<br/> 
											○ 개인정보 수집·이용을 거부할 권리가 있으며 이 경우 문자 알림 서비스 이용이 제한 됩니다.</p>
										<p class="ehicheight ehiccolor"> 
											2.개인정보의 제3자 제공에 관한 사항<br>
											○ 관련 법률(개인정보보호법 제 17조)에 따라 제공가능한 경우를 제외하고는 별도의 동의 없이 제3자에게 개인정보를 제공하지 않습니다.</p>
									</div>
									<br>
									<label for="agree"><input type="checkbox" id="agree">상기내용을 확인하고 동의합니다.</label>
									<br>
								</td>
								
							</tr>
							</tbody>
					</table>
					<div id="checkVals">
						
					</div>
					<br/>
					<div style="width:100%; height:10%; display:flex; justify-content:center; align-items:center;">
						<input type="button" onclick="fn_validation(); return false;"  value="신청" class="btn_submit"/>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</body>
</html>