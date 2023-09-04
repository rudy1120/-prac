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
	
	$("#find_addr1").on("click", function(){
		window.open("/bmc/jsp/subpopup/addrPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
	});	
	$("#find_addr2").on("click", function(){
		window.open("/bmc/jsp/subpopup/addrUpChk.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	});
	$("#find_addr3").on("click", function(){
		window.open("/bmc/jsp/subpopup/addrUpPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	});
	$("#find_addr3").on("click", function(){
		window.open("/bmc/jsp/subpopup/addrDelPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	});

});

//신청 유효성검사
function fn_validation(num){
	var blank_pattern = /[\s]/g;
	
	/* if(blank_pattern.test($("#name1").val()) == true){
	    alert('이름에 공백은 사용할 수 없습니다. ');
	    return false;
	} */
	// 사보 구독신청
	if(num == 1){
		if($("#name1").val() == ""){
			alert("이름을 입력하세요.");
			return;
		}

		if($("#addr1").val().trim()== ""){
			alert("주소를 입력하세요.");
			return;
		}

		if(!$("#agree").is(":checked")){
			alert('개인정보제공에 동의해주세요.');
			return;
		}

		fn_save();
		//$("#saboSubForm").submit();
		
	} //사보 구독 철회
	else if(num == 4){
		if($("#name4").val() == ""){
			alert("이름을 입력하세요");
			return;
		}
		
		if($("#addr4").val() == ""){
			alert("주소를 입력하세요.");
			return;
		}

		fn_delete();
		//$("#chkDelForm").submit();
		
	} 
	
	//$("#supplyForm").submit();
	
}

//신청내용 저장
function fn_save(){
	
	//if (validator("#supplyForm") && confirm("알림서비스를 신청하시겠습니까")) 
	
	if(confirm("구독서비스를 신청하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/sabo/subSabo.do',
			data : $("#saboSubForm").serialize(),
			dataType : "html",
			success : function(data) {
				alert(data);
				location.href = "/bmc/sabo/saboForm.do?mId=0604070100"; //테스트서버
				//location.href = "/bmc/sabo/saboForm.do?mId=0604090100"; //실서버
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
	}

}


// 구독 철회 신청
function fn_delete(){

	if(confirm("구독서비스를 철회하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/sabo/delSabo.do',
			data : $("#DelForm").serialize(),
			dataType : "html",
			success : function(data) {
				alert(data);
				location.href = "/bmc/sabo/saboForm.do?mId=0604070100"; //테스트서버
				//location.href = "/bmc/sabo/saboForm.do?mId=0604090100"; //실서버
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
	}
}

</script>

<script>
// 수정 유효성검사
function fn_validation1(){
	
	if($("#upname").val() == ""){
		alert("이름을 입력하세요");
		return;
	}
	
	if($("#upaddr").val() == ""){
		alert("주소를 입력하세요.");
		return;
	}
	
	fn_regtYn();
	//fn_check();
	//$("#supplyForm").submit();
}

function fn_validation2(){
	
	if($("#upaddr3").val() == ""){
		alert("변경할 주소를 입력하세요.");
		return;
	}

	fn_upsave();
	
}
//정보 일치 여부
function fn_regtYn(){
	$.ajax({
		type:'post',
		url:'/bmc/sabo/chkAddr.do',
		data : $("#chkUpForm").serialize(),
		success : function(data) {
			var obj = JSON.parse(data);
			var cnt = obj.length;
			
			//신청내역 있는경우
			if(cnt >0){
				$("#chkUpDiv").css("display","none");
				$("#uptDiv").css("display","block");
				
				$("#idx").val(obj[0].idx);
				$("#upname2").val(obj[0].name);
				$("#upaddress2").val(obj[0].address);
			}else{
				alert("신청하신 내역이 없습니다.");
			}
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

//수정한 신청내용 저장
function fn_upsave(){
	
	if(confirm("구독서비스 정보를 수정하시겠습니까?")==true){
		$.ajax({
			type:'post',
			url:'/bmc/sabo/upSabo.do',
			data : $("#updtForm").serialize(),
			dataType : "html",
			success : function(data) {
				alert(data);
				self.close();
				location.href = "/bmc/sabo/saboForm.do?mId=0604070100"; //테스트서버
				//location.href = "/bmc/sabo/saboForm.do?mId=0604090100"; //실서버
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}

}
</script>

<script>
//신청 주소팝업
function jusoCallBack1(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, 
		rnMgtSn, bdMgtSn , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, 
		buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
	$("#address").val(roadFullAddr);
	$("#addr1").val(roadAddrPart1);
	$("#addr_detail1").val(addrDetail);
}

//수정확인 주소팝업
function jusoCallBack2(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, 
		rnMgtSn, bdMgtSn , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, 
		buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
	$("#upaddress").val(roadFullAddr);
	$("#upaddr").val(roadAddrPart1);
	$("#upaddr_detail").val(addrDetail);
}

//수정 주소팝업
function jusoCallBack3(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, 
		rnMgtSn, bdMgtSn , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, 
		buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
	$("#upaddress3").val(roadFullAddr);
	$("#upaddr3").val(roadAddrPart1);
	$("#upaddr_detail3").val(addrDetail);
}

//철회 주소팝업
function jusoCallBack4(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, 
		rnMgtSn, bdMgtSn , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, 
		buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
	$("#deladdress").val(roadFullAddr);
	$("#addr4").val(roadAddrPart1);
	$("#addr_detail4").val(addrDetail);
}

function subPg(){
	$("#tabs-1").css("display","block");
	$("#tabs-2").css("display","none");
	$("#tabs-3").css("display","none");
	
	$("#name").val("");
	$("#addr1").val("");
	$("#addr_detail1").val("");
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
		$("#upaddr").val("");
		$("#upaddr_detail").val("");
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
	
	$("#name4").val("");
	$("#addr4").val("");
	$("#addr_detail4").val("");
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
					<p class="box-comment"><strong class="strong_st">사보 구독서비스 신청</strong><br>우편발송 신청 페이지 입니다.</p>
					</div>
				</div> 
				<div class="form_wrap">
					<form:form commandName="saboSubscribeVO" name="saboSubForm" id="saboSubForm" action="/bmc/sabo/subSabo.do" methodParam="post">
					<div id="nppfs-loading-modal" style="display:none;"></div>
					<div class="nppfs-elements" style="display:none;"></div>
						<fieldset>
							<input type="hidden" name="phone" id="phone" value="">
							<table class="bod_maintain">
								<thead>
								</thead>
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
										<td class="fTxt">주소</td> 
										<td>
											<input id="address" name="address" type="hidden" value="">
											<label for="addr1" class="blind">주소</label>
											<input type="button" id="find_addr1" class="addrBtn" title="우편번호 찾기" value="우편번호 찾기"/>
											<input type="text" id="addr1" name="addr1" class="w33per" placeholder="주소" readonly>
											<label for="addr_detail1" class="blind">상세주소</label>
											<input type="text" id="addr_detail1" name="addr_detail1" class="w33per" placeholder="상세주소" readonly>
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
													○ 개인정보 수집·이용·목적 : 부산도시공사는 귀하의 최소한의 개인정보를 통하여 부산도시공사 사보 "바다가" 구독서비스를 제공하기 위한 목적으로 개인정보가 사용됨을 알려드립니다.<br/> 
													○ 수집하려는 개인정보의 항목 : 성명,  주소  <br/>
													○ 개인정보의 보유 및 이용기간 : 사보 구독서비스 신청시부터 ~ 사보 구독서비스 철회시까지<br/> 
													○ 개인정보 수집·이용을 거부할 권리가 있으며 이 경우 사보 구독서비스 이용이 제한 됩니다.</p>
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
					<p class="box-comment"><strong class="strong_st">사보 구독서비스 수정</strong><br>우편발송 수정 페이지 입니다.</p>
					</div>
				</div>
				<div id="chkUpDiv" class="form_wrap">
					<form:form commandName="saboSubscribeVO" name="chkUpForm" id="chkUpForm" action="/bmc/sabo/chkAddr.do" methodParam="post">
						<fieldset>
							<table class="bod_maintain">
								<thead></thead>
								<tbody>
									<tr>
										<td class="first fTxt">성명</td>
										<td class="first sod">
											<label for="upname" class="blind">성명</label>
											<input class="inp1" type="text" id="upname" name="upname" maxlength="30" data-validator="on" data-required="y" data-fieldName="아이디" placeholder="이름 입력"/>
										</td>
									</tr>
									<tr>
										<td class="fTxt">주소</td>
										<td>
											<input id="upaddress" name="upaddress" type="hidden" value="">
											<label for="upaddr" class="blind">주소</label>
											<input type="button" id="find_addr2" class="addrBtn" title="우편번호 찾기" value="우편번호 찾기"/>
											<input type="text" id="upaddr" name="upaddr" class="w33per" placeholder="주소" readonly>
											<label for="upaddr_detail" class="blind">상세주소</label>
											<input type="text" id="upaddr_detail" name="upaddr_detail" class="w33per" placeholder="상세주소" readonly>
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
					<form:form commandName="saboSubscribeVO" name="updtForm" id="updtForm" action="/bmc/sabo/upSabo.do" methodParam="post">
						<fieldset>
							<table class="bod_maintain">
								<thead></thead>
								<tbody>
									<input type ="hidden" id="idx" name="idx" >
									<tr>
										<td>성명</td>
										<td>
											<label for="upname2" class="blind">성명</label>
											<input id="upname2" name="upname2" readonly placeholder="이름 입력" />
										</td>
									</tr>
								
									<tr>
										<td>기존 주소</td>
										<td>
											<label for="upaddress2" class="blind">기존 주소</label>
											<input type="text" id="upaddress2" name="upaddress2" class="w33per" value="" readonly>
										</td>
									</tr>
									<tr>
										<td>수정 주소</td>
										<td>
											<input id="upaddress3" name="upaddress3" type="hidden" value="">
											<label for="upaddr3" class="blind">주소</label>
											<input type="button" id="find_addr3" class="addrBtn" title="우편번호 찾기" value="우편번호 찾기"/>
											<input type="text" id="upaddr3" name="upaddr3" class="w33per" placeholder="주소" readonly>
											<label for="upaddr_detail3" class="blind">상세주소</label>
											<input type="text" id="upaddr_detail3" name="upaddr_detail3" class="w33per" placeholder="상세주소" readonly>
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
					<p class="box-comment"><strong class="strong_st">사보 구독서비스 철회</strong><br>우편발송 철회 페이지 입니다.</p>
					</div>
				</div>
				<div id="chkDiv" class="form_wrap">
					<form:form commandName="saboSubscribeVO" name="DelForm" id="DelForm" action="/bmc/sabo/delSabo.do" methodParam="post">
						<fieldset>
							<table class="bod_maintain">
								<thead></thead>
								<tbody>
									<tr>
										<td class="first fTxt">성명</td>
										<td class="first sod">
											<label for="delname" class="blind">성명</label>
											<input class="inp1" type="text" id="delname" name="delname" maxlength="30" data-validator="on" data-required="y" 
												data-fieldName="아이디" placeholder="이름 입력" oninput="this.value=this.value.replace(/[^(가-힣ㄱ-ㅎㅏ-ㅣㆍᆢa-zA-Z0-9\-)]/gi,''));"/>
										</td>
									</tr>
									<tr>
										<td class="fTxt">주소</td>  
										<td>
											<input id="deladdress" name="deladdress" type="hidden" value="">
											<label for="addr4" class="blind">주소</label>
											<input type="button" id="find_addr3" class="addrBtn" title="우편번호 찾기" value="우편번호 찾기"/>
											<input type="text" id="addr4" name="addr4" class="w33per" placeholder="주소" readonly>
											<label for="addr_detail4" class="blind">상세주소</label>
											<input type="text" id="addr_detail4" name="addr_detail4" class="w33per" placeholder="상세주소" readonly>
										</td> 
									</tr>
								</tbody>
							</table>
							<br/>
							<div style="width:100%; height:10%; display:flex; justify-content:center; align-items:center;">
								<input type="button" onclick="fn_validation(4); return false;"  value="확인" class="btn_submit"/>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
