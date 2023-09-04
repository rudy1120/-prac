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
<title>이사날짜 예약</title>
<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script> 
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>

<c:set var="successUrl" value="/bmc/moveRes/movement.do" />
<c:set var="cancelUrl" value="/bmc/moveRes/movement.do" />
<c:set var="sAuthType" value="M" />

<script>

// if (document.location.protocol == 'http:') {
//     document.location.href = document.location.href.replace('http:', 'https:');
// }

$(document).ready(function () {

	if($("#gbn2").val() == "A"){
		document.title = "이사날짜 예약 | 아미4 행복주택";
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

$(function() {
	
	$("#datepick").datepicker("disable").removeAttr("disabled");

    //input을 datepick로 선언
	$("#datepick").datepicker({ dateFormat: 'yy-mm-dd'
		,minDate: "2023-10-01" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전, 0:오늘)
        ,maxDate: "2023-11-30" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후) 
        ,dayNamesMin: ['일', '월', '화', '수', '목', '금', '토']
    	,onSelect: function(dateString){
    		$("#dateSel").text(dateString);
    		$("#dateSel").val(dateString);
    		fn_chkTime();
    	}	
	});
   
});

function fn_chkTime(){
	$.ajax({
		type:'post',
		url:'/bmc/moveRes/chkTime.do',
		data : $("#chkForm").serialize(),
		success : function(data) {
			var obj = JSON.parse(data);
			var cnt = obj.length;
			var timeSel = document.getElementById("timeSel");
			
			if(cnt >= 3){
				$("#timeSel").css("display","none");
				$("#noSel").css("display","inline-block");
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
				$("#timeSel").css("display","inline-block");
			}

		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
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

</script>

</head>

<body>

	<h3 style="font-size:3em;margin-bottom:20px;" class="tit">
		<c:if test="${chkCust.gbn2 eq 'A'}">아미4 행복주택</c:if>
		이사날짜 예약
	</h3>
    <p style="text-align:center; color:#fff;">- 이사날짜를 예약하고 확인할 수 있습니다 -</p>
	<div class="all_wrap">
		<div class="inr vision">
			<div class="area_box box2 recruit-box1">
			<p class="box-comment"><strong class="strong_st">이사날짜 예약</strong><br>
				<c:if test="${chkCust.gbn2 eq 'A'}">아미4 행복주택</c:if>
				이사날짜 예약 페이지 입니다.
			</p>
			</div>
		</div>
		
		<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
			<security:authentication var="user" property="principal"/>
		</security:authorize>
		<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>
		
		<c:choose>
			<c:when test="${not empty user}">
				<div class="bt_wrap">
					<a href="/bmc/moveRes/movement.do" class="bt-il bt1 on" title="날짜 예약하기">날짜 예약하기</a>
					<a href="/bmc/moveRes/dateConfirm.do" class="bt-il bt2" title="날짜 확인하기">날짜 확인하기</a>
				</div>
				<form:form commandName="ilgwangCustVO" id="chkForm" name="chkForm" action="/bmc/moveRes/reserve.do" 
					methodParam="post" htmlEscape="false" target="_self" onsubmit="return validator() && confirm('예약하시겠습니까?')" >
					<input type="hidden" id="gbn2" value="${chkCust.gbn2}" />
					<input type="hidden" id="cust_id" name="cust_id" value="${chkCust.cust_id}"/>
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
										<span>${chkCust.addr1} 동</span>
										<span>${chkCust.addr2} 호</span>
										<input type="hidden" id="addr1" name="addr1" value="${chkCust.addr1}"/>
									</dd>
								</dl>
								<dl>
									<dt><label for="date">날짜 및 시간</label></dt>
									<dd>
										<input type="text" id="datepick" data-validator="on" data-required="y" data-fieldName="이사날짜" readonly/><!--  style="float:left;margin-right:10px;" -->
										<select id="timeSel" name="timeSel" style="display:none;height:43px;width: 120px;text-align: center;"
											data-validator="on" data-required="y" data-fieldName="이사시간">
										</select>
										<sapn id="noSel" style="display:none;">** 선택 가능한 시간이 없습니다 **</sapn>
										<input type="hidden" id="dateSel" name="dateSel"/>
									</dd>
								</dl>
								<dl>
									<dt>개인정보 수집 <br/>및 이용 동의 </dt>
									<dd>
										<div class="terms_overflow shot text-justify mb30">
											<h5 class="text-center txt_b2">개인정보 활용에 대한 내용을 모두 읽고 동의해주세요.<br/>※ 고객님은 동의를 거부할 권리가 있으나, 이 경우 서비스를 이용하실 수 없습니다.</h5>
											<p class="ehicheight ehiccolor">부산도시공사는 당사가 공급하는 <c:if test="${chkCust.gbn2 eq 'A'}">아미4 행복주택</c:if>의 이사날짜예약과 관련한 서비스를 제공하기 위하여 아래와 같이 개인정보를 수집하며, 수집한 정보는 해당 목적 외에는 이용하지 않으며 보유기간이 경과한 후에는 폐기합니다.<br/><br/> 
												가. 개인정보를 수집하는 자 및 수집 목적<br>
												&nbsp;&nbsp;▷ 부산도시공사 <c:if test="${chkCust.gbn2 eq 'A'}">아미4</c:if> 행복주택 이사날짜 예약서비스<br/> 
												나. 수집하는 개인정보의 항목  <br/>
												&nbsp;&nbsp;▷ 성명(한글), 생년월일, 동호수(<c:if test="${chkCust.gbn2 eq 'A'}">아미4 행복주택</c:if>), 휴대전화번호<br/> 
												다. 개인정보 보유 및 이용기간  <br/>
												&nbsp;&nbsp;▷ 이사날짜 예약신청 시부터 입주지정기간 종료일(<c:if test="${chkCust.gbn2 eq 'A'}">2023.11.30</c:if>) 까지<br/><br/>
												※ 파기절차 : 보유기간 경과 후 지체없이(5일 이내) 파기<br/>
												※ 파기방법 : 종이출력정보·분쇄 또는 소각, 전자파일정보·재생 불가능한 기술적 방법 사용
											</p>
										</div>
										<br/>
										<label for="agree"><input type="checkbox" id="agree" name="agree" data-validator="on" data-required="y" data-fieldName="개인정보 수집 및 이용 동의">상기내용을 확인하고 동의합니다.</label>
										<br/>
									</dd>
								</dl>
							</div>
						
							<div class="btnall_wrap">
								<div style="float: right;">
									<div class="btn_in">
										<!-- receiptList.jsp에서 가져온 submit -->
										<!-- <input onclick="chkBtn(); return false;" type="button" class="btn_blue btn_wrap-il" value="조회"/> -->
										<input type="submit" class="btn_blue btn_wrap-il" value="예약"/>
									</div>
								</div>
							</div>
						</fieldset>
					</div>
				</form:form>
			</c:when>
			<c:otherwise>
				<div class="bt_wrap">
					<a href="/bmc/moveRes/movement.do" class="bt-il bt1 on" title="날짜 예약하기">날짜 예약하기</a>
					<a href="/bmc/moveRes/dateConfirm.do" class="bt-il bt2" title="날짜 확인하기">날짜 확인하기</a>
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