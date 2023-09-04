<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
String now = format.format(new Date());
%>
<!-- 구독신청 스타일시트 -->
<link rel="stylesheet" type="text/css" href="/bmc/css/subscribe.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script> 
<script>

$(document).ready(function () {
	$(".sub_btn").on("click", function(){
		const num = $(".sub_btn").index($(this));
		$(".sub_btn").removeClass("active");
		$(".sub_btn").attr("title","");
		
		$(".sub_btn:eq(" + num + ")").addClass("active");
		$(".sub_btn:eq(" + num + ")").attr("title","선택됨");
	});
});

//유효성검사
function fn_validation(num){
	var blank_pattern = /[\s]/g;
	
	/* if(blank_pattern.test($("#name").val()) == true){
	    alert('이름에 공백은 사용할 수 없습니다. ');
	    return false;
	}
	
	if(blank_pattern.test($("#email").val()) == true){
	    alert('이메일에 공백은 사용할 수 없습니다. ');
	    return false;
	} */
	// 웹진 구독신청
	if(num == 1){
		
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

		//var email =  $("#email1").val();
		
		fn_save();
		//$("#supplyForm").submit();
	}
	// 웹진 구독 철회
	else if(num == 2){
		
		if($("#delname").val() == ""){
			alert("이름을 입력하세요");
			return;
		}
		
		if($("#delemail").val() == ""){
			alert("이메일 주소를 입력하세요.");
			return;
		}
		
		fn_delete();
	}
	
	
}

//신청내용 저장
function fn_save(){
	
	//if (validator("#supplyForm") && confirm("알림서비스를 신청하시겠습니까")) 
	
	if(confirm("알림서비스를 신청하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/sub/subWebzine.do',
			data : $("#webzineSubForm").serialize(),
			dataType : "html",
			success : function(data) {
				alert(data);
				location.href = "/bmc/sub/subForm.do?mId=0604070200"; //테스트 서버
				//location.href = "/bmc/sub/subForm.do?mId=0604090200"; //실서버
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
	}

}

//신청내용 저장
function fn_delete(){
	if(confirm("알림서비스를 철회하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/sub/delWebzine.do',
			data : $("#delForm").serialize(),
			dataType : "html",
			success : function(data) {
				alert(data);
				location.href = "/bmc/sub/subForm.do?mId=0604070200"; //테스트 서버
				//location.href = "/bmc/sub/subForm.do?mId=0604090200"; //실서버
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}
</script>

<script>
//유효성검사
function fn_validation1(){
	
	if($("#upname").val() == ""){
		alert("이름을 입력하세요");
		return;
	}
	if($("#upemail").val() == ""){
		alert("이메일 주소를 입력하세요.");
		return;
	}
	
	fn_regtYn();
	//fn_check();
	//$("#supplyForm").submit();
	}

function fn_validation2(){
	if($("#upemail3").val() == ""){
		alert("변경할 이메일 주소를 입력하세요.");
		return;
	}
	fn_upsave();
	
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
				$("#chkUpDiv").css("display","none");
				$("#uptDiv").css("display","block");
				
				$("#idx").val(obj[0].idx);
				$("#upname2").val(obj[0].name);
				$("#upemail2").val(obj[0].email);
			}else{
				alert("신청하신 내역이 없습니다.")
			}
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

// 수정 신청내용 저장
function fn_upsave(){
	
	if(confirm("구독서비스 정보를 수정하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/sub/upWebzine.do',
			data : $("#updtForm").serialize(),
			dataType : "html",
			success : function(data) {
				alert(data);
				self.close();
				location.href = "/bmc/sub/subForm.do?mId=0604070200"; //테스트 서버
				//location.href = "/bmc/sub/subForm.do?mId=0604090200"; //실서버
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}

}

function subPg(){
	$("#tabs-1").css("display","block");
	$("#tabs-2").css("display","none");
	$("#tabs-3").css("display","none");
	
	$("#name").val("");
	$("#email").val("");
	if($("#agree").is(":checked")){
		$("#agree").prop("checked", false);
	}
}
function upPg(){
	$("#tabs-1").css("display","none");
	$("#tabs-2").css("display","block");
	$("#tabs-3").css("display","none");
	
	if($("#chkUpDiv").css("display","block")){
		$("#upname").val("");
		$("#upemail").val("");
	}
	if($("#uptDiv").css("display","block")){
		$("#chkUpDiv").css("display","block");
		$("#uptDiv").css("display","none");
	}
}
function delPg(){
	$("#tabs-1").css("display","none");
	$("#tabs-2").css("display","none");
	$("#tabs-3").css("display","block");
	
	$("#delname").val("");
	$("#delemail").val("");
}
</script>

<div id="contents">
	<div class="content"> 
	<div class="gap20"></div> 
	<div id="tabs">
		<div>
			<input type="button" class="sub_btn active" value="구독 신청" title="선택됨" onclick="subPg()"/>
			<input type="button" class="sub_btn" value="구독 수정" onclick="upPg()"/>
			<input type="button" class="sub_btn" value="구독 철회" onclick="delPg()"/>
		</div>
		<div class="gap"></div>
		<div id="tabs-1" class="sub_tab">
			<div class="inr"> 
				<div class="area_box box2 recruit-box1">
				<p class="box-comment"><strong class="strong_st">웹진 구독서비스 신청</strong><br>이메일 신청 페이지 입니다.</p>
				</div>
			</div> 
			<div class="form_wrap">
				<form:form commandName="subscribeVO" name="webzineSubForm" id="webzineSubForm" action="/bmc/sub/subWebzine.do" methodParam="post">
				<div id="nppfs-loading-modal" style="display:none;"></div>
				<div class="nppfs-elements" style="display:none;"></div>
					<fieldset>
						<input type="hidden" name="phone" id="phone" value="">
						<table class="bod_maintain">
							<thead></thead>
							<tbody>
								<tr>
									<td class="first fTxt">성명</td>
									<td class="first sod">
										<label for="name" class="blind">성명</label>
										<input class="inp1" type="text" id="name" name="name" maxlength="30" 
											data-validator="on" data-required="y" data-fieldname="아이디" placeholder="이름 입력" oninput="this.value=this.value.replace(/[^가-힣ㄱ-ㅎㅏ-ㅣㆍᆢa-zA-Z]/gi,'');"/>
									</td>
								</tr>
							
								<tr>
									<td class="fTxt">이메일</td>
									<td>
										<label for="email" class="blind">이메일</label>
										<input class="w33per2" type="email" id="email" name="emai" maxlength="60" data-validator="on" data-required="y" data-fieldname="이메일" placeholder="이메일형식으로 입력해주세요">
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
							</tbody>
						</table>
						
						<div id="checkVals">
							
						</div>
						<br/>
						<div style="width:100%; height:10%; display:flex; justify-content:center; align-items:center;">
							<input type="button" onclick="fn_validation(1); return false;" value="신청" class="btn_submit">
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
		
		<div id="tabs-2" class="sub_tab" style="display:none;">
			<div class="inr"> 
				<div class="area_box box2 recruit-box1">
				<p class="box-comment"><strong class="strong_st">웹진 구독서비스 수정</strong><br>이메일 수정 페이지 입니다.</p>
				</div>
			</div>
			<div id="chkUpDiv" class="form_wrap">
				<form:form commandName="subscribeVO" name="chkForm" id="chkForm" action="/bmc/sub/chkEmail.do" methodParam="post">
					<fieldset>
						<table class="bod_maintain">
							<thead></thead>
							<tbody>
								<tr>
									<td class="first fTxt">성명</td>
									<td class="first sod">
										<label for="upname" class="blind">성명</label>
										<input class="inp1" type="text" id="upname" name="upname" maxlength="30" data-validator="on" data-required="y" data-fieldName="아이디" placeholder="이름 입력" />
									</td>
								</tr>
								<tr>
									<td class="fTxt">이메일</td>
									<td>
										<label for="upemail" class="blind">이메일</label>
										<input class="w33per2" type="email" id="upemail" name="upemail" maxlength="60" data-validator="on" data-required="y" data-fieldname="이메일" placeholder="이메일형식으로 입력해주세요">
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
				<form:form commandName="subscribeVO" name="updtForm" id="updtForm" action="/bmc/sub/upWebzine.do" methodParam="post">
					<fieldset>
						<table class="bod_maintain">
							<thead></thead>
							<tbody>
								<input type="hidden" id="idx" name="idx" >
								<tr>
									<td>성명</td>
									<td>
										<label for="upname2" class="blind">성명</label>
										<input type="text" id="upname2" name="upname2" readonly>
									</td>
								</tr>
								<tr>
									<td>기존 이메일</td>
									<td>
										<label for="upemail2" class="blind">기존 이메일</label>
										<input type="text" class="w33per2" type="email"  id="upemail2" name="upemail2" readonly>
									</td>
								</tr>
								<tr>
									<td>수정 이메일</td>
									<td>
										<label for="upemail3" class="blind">수정 이메일</label>
										<input type="text" class="w33per2" type="email"  id="upemail3" name="upemail3" >
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
		
		<div id="tabs-3" class="sub_tab" style="display: none;">
			<div class="inr">
				<div class="area_box box2 recruit-box1">
				<p class="box-comment"><strong class="strong_st">웹진 구독서비스 철회</strong><br>이메일 철회 페이지 입니다.</p>
				</div>
			</div>
			<div id="chkDiv" class="form_wrap">
				<form:form commandName="subscribeVO" name="delForm" id="delForm" action="/bmc/sub/delWebzine.do" methodParam="post">
					<fieldset>
						<table class="bod_maintain">
							<thead></thead>
							<tbody>
								<tr>
									<td class="first fTxt">성명</td>
									<td class="first sod">
										<label for="delname" class="blind">성명</label>
										<input class="inp1" type="text" id="delname" name="delname" maxlength="30" data-validator="on" data-required="y" data-fieldName="아이디" placeholder="이름 입력" />
									</td>
								</tr>
								<tr>
									<td class="fTxt">이메일</td>
									<td>
										<label for="delemail" class="blind">이메일</label>
										<input class="w33per2" type="email" id="delemail" name="delemail" maxlength="60" data-validator="on" data-required="y" data-fieldname="이메일" placeholder="이메일형식으로 입력해주세요">
									
									</td>
								</tr>
							</tbody>
						</table>
						<br/>
						<div style="width:100%; height:10%; display:flex; justify-content:center; align-items:center;">
							<input type="button" onclick="fn_validation(2); return false;"  value="확인" class="btn_submit"/>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
	</div>
</div>
