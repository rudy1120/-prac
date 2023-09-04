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
<title>개인정보 제3자 제공 동의서</title>
<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script> 
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/bmc/js/netfunnel.js" charset="UTF-8"></script>

<c:set var="successUrl" value="/bmc/happyhouse/chkProc.do" />
<c:set var="sAuthType" value="M" />

<script>
// window.onload = function(){
// 	NetFunnel_Complete();
// }
</script>

<style>

table { width: 95%; border-collapse: collapse; border-top: 1px solid #ccc; margin: auto; }
thead { z-index: 1000; position: relative; }
th,td { 
    text-align: center; border-right: 1px solid #ccc; border-bottom: 1px solid #ccc; 
    background-clip: padding-box; scroll-snap-align: start; 
    padding: 0.6rem; min-width: 6rem; margin: 1px 0 0 0;
}
th:last-of-type,td:last-of-type { border-right: 0; }
thead th { position: sticky; top: 0; background-clip: padding-box; border-left: 0; }
tbody { z-index: 10; position: relative; }
tbody th { position: sticky; left: 0; }
thead th, tbody th { background-color: #f8f8f8; }

.red{ color: #e11f2e; }
.gap_20 { height: 20px; }
.gap_70 { height: 70px; }

</style>

<script>

// if (document.location.protocol == 'http:') {
//     document.location.href = document.location.href.replace('http:', 'https:');
// }

$(document).ready(function () {
	$('input[type="checkbox"][name="chk1"]').click(function(){
		if($(this).prop('checked')){
			$('input[type="checkbox"][name="chk1"]').prop('checked',false);
			$(this).prop('checked',true);
		}
	});
	$('input[type="checkbox"][name="chk2"]').click(function(){
		if($(this).prop('checked')){
			$('input[type="checkbox"][name="chk2"]').prop('checked',false);
			$(this).prop('checked',true);
		}
	});
	$('input[type="checkbox"][name="chk3"]').click(function(){
		if($(this).prop('checked')){
			$('input[type="checkbox"][name="chk3"]').prop('checked',false);
			$(this).prop('checked',true);
		}
	});
	
	if($("#gbn").val() == "S"){
		document.title = "개인정보 제3자 제공 동의서 | 시청앞 행복주택 2단지";
	}
	if($("#gbn").val() == "A"){
		document.title = "개인정보 제3자 제공 동의서 | 아미4 행복주택";
	}

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
	titleVal = "본인확인 - " + $("title").html();
	$("title").html(titleVal);
	
 	//$("#userAgent").text(navigator.userAgent);
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
	//npPfsStartup(null, false, true, false, false, "npkencrypt", "on");
});

//유효성검사
function chkBtn(){
	
	for(var i = 1;i <= 3; i++){
		if(!$("input[name='chk" + i + "']").is(":checked")){
			alert("필수항목 " + i + "번의 동의여부를 선택해주세요.");
			return false;
		}
	}
	
	if(!$("input[name='understand']").is(":checked")){
		alert("필수항목 3번이 완료되지 않았습니다.");
		return false;
	}
	
	for(var i = 1;i <= 3; i++){
		if($("input[name='chk" + i + "'][value='N']").is(":checked")){
			alert("필수항목 " + i + "번을 동의해주세요.");
			return false;
		}
	}
}

</script>

</head>

<body>

	<h3 style="font-size:3em;margin-bottom:20px;" class="tit">개인정보 제3자 제공 동의서
		<c:if test="${custList.gbn eq 'A'}">
			<br/>[아미4 행복주택]
		</c:if>
		<c:if test="${custList.gbn eq 'S'}">
			<br/>[시청앞 행복주택 2단지]
		</c:if>
		<input type="hidden" id="gbn" value="${custList.gbn}">
	</h3>
<!--     <p style="text-align:center; color:#fff;">- 시청앞 행복주택 2단지 계약자 대상 -</p> -->
	<div class="all_wrap">
		<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
			<security:authentication var="user" property="principal"/>
		</security:authorize>
		<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>
		
		<c:choose>
			<c:when test="${!empty user}">
				<div class="inr vision">
					<div class="area_box box3 recruit-box1">
					<c:if test="${custList.gbn eq 'A'}">
						<p class="box-comment2">아미4 행복주택의 계약자(이하 본인)는 청약신청 시 개인정보 수집, 이용 및 제3자 동의서를 작성하였습니다.</p>
						<p class="box-comment2">본인의 원활한 입주지원을 위해 부산도시공사가 수집한 개인정보를 제3자(경동건설)에 제공하고자 합니다.</p>
						<p class="box-comment2">이사 예약 등 입주관련 서비스를 편리하게 이용하실 수 있으며, 개인정보 제3자 제공 동의를 하지 않으실 경우, 경동건설의 입주지원에 차질이 있을 수 있습니다.</p>
					</c:if>
					<c:if test="${custList.gbn eq 'S'}">
						<p class="box-comment2">시청앞 행복주택 2단지의 계약자(이하 본인)는 청약신청 시 개인정보 수집, 이용 및 제3자 동의서를 작성하였습니다.</p>
						<p class="box-comment2">본인의 원활한 입주지원을 위해 부산도시공사가 수집한 개인정보를 제3자(GS건설)에 제공하고자 합니다.</p>
						<p class="box-comment2">GS건설 자체 어플리케이션을 활용하여 사전점검행사 예약, 이사 예약, 하자 접수 등 입주관련 서비스를 편리하게 이용하실 수 있으며,
							<br/>개인정보 제3자 제공 동의를 하지 않으실 경우, GS건설의 입주지원에 차질이 있을 수 있습니다.</p>
					</c:if>
					</div>
				</div>
				<form:form commandName="moveAgreeVO" id="chkForm" name="chkForm" action="/bmc/happyhouse/agreeProc.do" 
					methodParam="post" htmlEscape="false" target="_self" onsubmit="return chkBtn(this);" >
					<p align="right"><strong>※ 계약자 정보 : ${user.userName}님은 ${custList.addr1}동 ${custList.addr2}호 입니다.</strong></p>
					
					<input type="hidden" id="username" name="username"  value="${user.userName}"/>
					<input type="hidden" id="usertel" name="usertel" value="${user.tel1}-${user.tel2}-${user.tel3}"/>
					<div class="gap_20"></div>
					
					<p class="depth2-title" id="subtitle"></p>
					<!-- 본인인증 정보존재 -->
					<p><strong>1. [<span class="red">필수</span>] 개인정보의 제3자 제공 동의 안내</strong></p>
					<div class="inr vision">
						<div class="area_box box4 recruit-box1">
							<span>본인은 개인정보 수집&middot;이용자(부산도시공사)가 본인의 개인정보를 다음과 같이 제3자에게 제공하는 것에 대하여</span>
							<br/>
							<span style="float: right; padding-right: 10px;">
								<input type="checkbox" id="chkY1" name="chk1" value="Y"> 동의합니다.
								<input type="checkbox" id="chkN1" name="chk1" value="N"> 동의하지 않습니다.
							</span>
							<br/>
							<span>
								<strong>가. 개인정보를 제공받는 자 : 
									<c:if test="${custList.gbn eq 'A'}">경동</c:if>
									<c:if test="${custList.gbn eq 'S'}">GS</c:if>건설
									<br/>나. 개인정보를 제공받는 자의 개인정보 이용 목적 및 제공항목
								</strong>
							</span>
							<table>
								<colgroup>
							        <col style="width: 20%;">
							        <col style="width: 20%;">
							        <col style="width: 30%;">
							    </colgroup>
							    
							    <thead>
							        <tr>
							            <th>개인정보를 제공받는 자</th>
							            <th>개인정보 이용목적</th>
							            <th>제공항목</th>
							        </tr>
							    </thead>
							    <tbody>
							        <tr>
							        	<c:if test="${custList.gbn eq 'A'}"><td>경동건설</td></c:if>
							        	<c:if test="${custList.gbn eq 'S'}"><td>GS건설</td></c:if>
							            <td>입주지원</td>
							            <td>성명, 주소, 연락처, 계약내역, 생년월일</td>
							        </tr>
							    </tbody>
							</table>
							<span>다. 개인정보 보유 및 이용기간 : [계약자] 임대차계약기간</span>
						</div>
					</div>
					<p><strong>2. [<span class="red">필수</span>] 동의거부 시 불이익 안내</strong></p>
					<div class="inr vision">
						<div class="area_box box4 recruit-box1">
							<span>
								<strong>본인은 위 1~2호에 대하여 동의를 거부할 수 있다는 것과, 동의를 거부할 경우 
									<c:if test="${custList.gbn eq 'A'}">아미4 행복주택의</c:if>
									<c:if test="${custList.gbn eq 'S'}">시청앞 행복주택 2단지</c:if> 사전점검, 입주, 하자접수 지원에 대한 
									<c:if test="${custList.gbn eq 'A'}">경동건설의</c:if>
									<c:if test="${custList.gbn eq 'S'}">GS건설의</c:if> 입주지원에 제한이 있을 수 있음을</strong>
							</span>
							<br/>
							<span style="float: right; padding-right: 10px;">
								<input type="checkbox" id="chkY2" name="chk2" value="Y"> 동의합니다.
								<input type="checkbox" id="chkN2" name="chk2" value="N"> 동의하지 않습니다.
							</span>
						</div>
					</div>
					<p><strong>3. [<span class="red">필수</span>]</strong></p>
					<div class="inr vision">
						<div class="area_box box4 recruit-box1">
							<span>본인은 위 1~3호의 동의사항에 대하여 「개인정보 보호법」 제17조제2항 및 제18조제3항에 따른 안내로 갈음하는 것에 대하여</span>
							<br/>
							<span style="float: right; padding-right: 10px;">
								<input type="checkbox" id="chkY3" name="chk3" value="Y"> 동의합니다.
								<input type="checkbox" id="chkN3" name="chk3" value="N"> 동의하지 않습니다.
							</span>
							<br/><br/>
							<span>본인은 본 동의서 내용과 제3자 제공에 관한 본인 권리에 대하여 </span>
							<span style="float: right; padding-right: 10px;">
								<input type="checkbox" id="understand" name="understand" value="Y"> 이해하였습니다.
							</span>
						</div>
					</div>
					<div class="btnall_wrap">
						<div style="float: right;">
							<div class="btn_in">
								<input type="submit" class="btn_blue btn_wrap-il" value="제출"/>
							</div>
						</div>
					</div>
				</form:form>
			</c:when>
			<c:otherwise>
<!-- 				<div class="inr vision"> -->
<!-- 					<div class="area_box box2 recruit-box1"> -->
<!-- 					<p class="box-comment">개인정보 제3자 제공 동의서 페이지입니다.</p> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="gap_70"></div>
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