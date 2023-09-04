<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
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
<link rel="stylesheet" type="text/css" href="../css/board2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>일광행복주택 테스트 예약</title>
<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script> 
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>

<c:set var="successUrl" value="/bmc/movetest/ilgwangMove.do" />
<c:set var="cancelUrl" value="/bmc/movetest/ilgwangMove.do" />
<c:set var="sAuthType" value="M" />

<script>

// if (document.location.protocol == 'http:') {
//     document.location.href = document.location.href.replace('http:', 'https:');
// }

$(document).ready(function () {
	
	
});
</script>

<script>

function joiner(value) { return value.indexOf("?") > -1 ? "&" : "?"; }

function open_namecheck(postUrl) {
	
	window.open("/NICE/checkplus_input.jsp?successUrl=" + postUrl + "&idx=${idx}&cancelUrl=${cancelUrl}&sAuthType=${sAuthType}", "popupChk", "width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no");
}

function replaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

function go_prev_page() {
	self.close();
}

$(function(){
	//20.04.16 웹접근성 반영 -탭이동시 title 정보 수정 
// 	titleVal = "본인확인 - " + $("title").html();
// 	$("title").html(titleVal);
	
//  	$("#userAgent").text(navigator.userAgent);
//  	/*
//  	npPfsStartup(document.form1, false, true, true, "enc", "on");
//  	1. form 양식 : 기본값 DOM Document 상의 첫번째 form
// 	2. 개인방화벽 사용여부 : 기본값 false
//  	3. 키보드보안 사용여부 : 기본값 false
//  	4. 단말정보수집 사용여부 : 기본값 false
// 	5. 키보드보안 E2E 필드 설정 속성명 : 기본값 "enc"
// 	6. 키보드보안 E2E 필드 설정 속성값: 기본값 "on"
//  	부가적인 설정은(예, 설치확인 등) /pluginfree/js/nppfs-1.0.0.js를 수정하여 설정하십시오.
// 	*/
// 	npPfsStartup(null, false, true, false, false, "npkencrypt", "on");
});

//유효성검사
function chkBtn(){
	if($("#addr1").val() == ""){
		alert("동을 입력해주세요");
		return false;
	}
	if($("#addr2").val() == ""){
		alert("호수를 입력해주세요");
		return false;
	}
	if(!$("#agree").is(":checked")){
		alert('개인정보제공에 동의해주세요.');
		return false;
	}

}

</script>

</head>

<body>

	<h3 style="font-size:3em;margin-bottom:20px;" class="tit">일광행복주택 테스트</h3>
    <p style="text-align:center; color:#fff;">- 이사날짜를 예약하고 확인할 수 있습니다 -</p>
	<div class="all_wrap">
		<div class="inr vision">
			<div class="area_box box2 recruit-box1">
			<p class="box-comment"><strong class="strong_st">이사날짜 예약</strong><br>날짜 예약 페이지 입니다.</p>
			</div>
		</div>
		
		<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
			<security:authentication var="user" property="principal"/>
		</security:authorize>
		<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>
		
		<c:choose>
			<c:when test="${not empty user}">
				<div class="bt_wrap">
					<a href="/bmc/movetest/ilgwangMove.do" class="bt-il bt1 on" title="분양 알림서비스 신청">날짜 예약하기</a>
					<a href="/bmc/movetest/dateConfirm.do" class="bt-il bt2" title="분양 알림서비스 수정">날짜 확인하기</a>
				</div>
				<form:form commandName="MoveTestVO" id="chkForm" name="chkForm" action="/bmc/movetest/chkCust.do" 
					methodParam="post" htmlEscape="false" target="_self" onsubmit="return chkBtn(this);" >
				
					<input type="hidden" id="name" name="name"  value="${user.userName}"/>
					<input type="hidden" id="tel" name="tel" value="${user.tel1}-${user.tel2}-${user.tel3}"/>
					<div class="gap20"></div>
					<p class="depth2-title" id="subtitle"></p>
					<!-- 본인인증 정보존재 -->
					<div id="chkUpDiv" class="bod_wrap">
						<fieldset>
							<div class="bod_write-il">
								<dl>
									<dt><label for="name">이름</label></dt>
									<dd>
										<span class="spantxt-il">${user.userName}</span> 
									 	<%-- <span id="ckname" name="ckname" class="spantxt-il">${user.userName}</span>  --%>
									</dd>
								</dl>
								<dl>
									<dt><label for="tel">휴대폰번호</label></dt>
									<dd>
										<span class="spantxt-il">${user.tel1}-${user.tel2}-${user.tel3}</span>
										<%-- <span id="cktel" name="cktel" class="spantxt-il">010-5431-5530${user.tel1}-${user.tel2}-${user.tel3}</span> --%>
									</dd>
								</dl>
								<dl>
									<dt><label for="address">동&middot;호수</label></dt>
									<dd style="padding-bottom:10px;">
										<input class="spantxt-il" type="text" id="addr1" name="addr1" 
											maxlength="3" data-validator="on" data-required="y" data-fieldName="동"
											oninput="this.value=this.value.replace(/[^0-9]/gi, '').replace(/(\..*)\./g, '$1');"/> 동
										<input class="spantxt-il" type="text" id="addr2" name="addr2" 
											maxlength="4" data-validator="on" data-required="y" data-fieldName="호"
											oninput="this.value=this.value.replace(/[^0-9]/gi, '').replace(/(\..*)\./g, '$1');"/> 호
									</dd>
								</dl>
								<dl>
									<dt>개인정보 수집 <br/>및 이용 동의 </dt>
									<dd>
										<div class="terms_overflow shot text-justify mb30">
											<h5 class="text-center txt_b2">개인정보 활용에 대한 내용을 모두 읽고 동의해주세요.</h5>
											<h5 class="text-center txt_b2">※ 고객님은 동의를 거부할 권리가 있으나, 이 경우 서비스를 이용하실 수 없습니다.</h5>
											<p class="ehicheight ehiccolor">부산도시공사는 당사가 공급하는 일광행복주택의 이사날짜예약과 관련한 서비스를 제공하기 위하여 아래와 같이 개인정보를 수집하며, 수집한 정보는 해당 목적 외에는 이용하지 않으며 보유기간이 경과한 후에는 폐기합니다.<br/><br/> 
												가. 개인정보를 수집하는 자 및 수집 목적<br>
												▷ 부산도시공사 일광행복주택 이사날짜 예약서비스<br/> 
												나. 수집하는 개인정보의 항목  <br/>
												▷ 신청자 이름(한글), 신청자 일광행복주택 동호수, 신청자 휴대전화번호<br/> 
												다. 개인정보 보유 및 이용기간  <br/>
												▷ 이사날짜 예약신청 시작일로부터 입주지정기간 종료일까지(2023. 3.13. ~ 2023. 6.29.)<br/><br/>
												※ 파기절차 : 보유기간 경과 후 지체없이(5일 이내) 파기<br/>
												※ 파기방법 : 종이출력정보·분쇄 또는 소각, 전자파일정보·재생 불가능한 기술적 방법 사용
											</p>
										</div>
										<br/>
										<label for="agree"><input type="checkbox" id="agree">상기내용을 확인하고 동의합니다.</label>
										<br/>
									</dd>
								</dl>
							</div>
						
							<div class="btnall_wrap">
								<div style="float: right;">
									<div class="btn_in">
										<!-- receiptList.jsp에서 가져온 submit -->
										<!-- <input onclick="chkBtn(); return false;" type="button" class="btn_blue btn_wrap-il" value="조회"/> -->
										<input type="submit" class="btn_blue btn_wrap-il" value="조회"/>
									</div>
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</c:when>
			<c:otherwise>
				<div class="bt_wrap">
					<a href="/bmc/movetest/ilgwangMove.do" class="bt-il bt1 on" title="분양 알림서비스 신청">날짜 예약하기</a>
					<a href="/bmc/movetest/dateConfirm.do" class="bt-il bt2" title="분양 알림서비스 수정">날짜 확인하기</a>
				</div>
				<div>
					<form name="reqSIRENForm" id="frmRealName" title="본인인증" action="#" onsubmit="return false;" method="post">
						<div>
							<div class="gap"></div>
							<span class="intro-txt-il ReName-txt-il">본인확인이 필요한 서비스입니다.<br/>
							아래 "<strong class="ReNameS-il">본인확인</strong>" 버튼을 클릭하여 인증 절차를 진행해주세요.</span>
							<div class="gap20-il"></div>
							<img src="/bmc/images/content/ReName_icon.jpg" alt=""  class="ReNameImg-il"/>
							<div class="btnall_wrap">
								<div class="btn_wrap" style="float:none; text-align:center;">
									<div class="btn_in">
										<input type="submit" onclick="open_namecheck('${successUrl}');" class="btn_wrap-il" value="본인확인" title="새창 열림" />
										<input type="button" onclick="go_prev_page(); return false;" class="btn_wrap-il" value="취소" title="창 닫기"/>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

</body></html>