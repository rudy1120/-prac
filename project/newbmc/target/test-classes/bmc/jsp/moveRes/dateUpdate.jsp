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
<title>이사날짜 수정</title>
<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script> 
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="/common/js/datePicker.js"></script>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script>

// if (document.location.protocol == 'http:') {
//     document.location.href = document.location.href.replace('http:', 'https:');
// }

$(document).ready(function () {
	if($("#gbn2").val() == 'A'){
		document.title = "이사날짜 수정 | 아미4 행복주택";
	}

});
</script>

<script>
$(function() {
	if($("#gbn1").val() == 'T'){
		//input을 datepick로 선언
		$("#datepick").datepicker({ dateFormat: 'yy-mm-dd'
			,minDate: "2023-10-01" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	        ,maxDate: "2023-11-30" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후) 
	        ,dayNamesMin: ['일', '월', '화', '수', '목', '금', '토']
	    	,onSelect: function(dateString){
	    		$("#dateSel").text(dateString);
	    		$("#dateSel").val(dateString);
	    		fn_chkTime();
	    	}	
		});
	}
	else{
	    //input을 datepick로 선언
		$("#datepick").datepicker({ dateFormat: 'yy-mm-dd'
			,minDate: "2023-10-01" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전, 0: 현재 날짜)
	        ,maxDate: "2023-11-30" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후) 
	        ,dayNamesMin: ['일', '월', '화', '수', '목', '금', '토']
	    	,onSelect: function(dateString){
	    		$("#dateSel").text(dateString);
	    		$("#dateSel").val(dateString);
	    		fn_chkTime();
	    	}	
		});
	}
   
});

function fn_chkTime(){
	$.ajax({
		type:'post',
		url:'/bmc/moveRes/chkTime.do',
		data : $("#resForm").serialize(),
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

</script>

</head>

<body>
	<h3 style="font-size:3em;margin-bottom:20px;" class="tit"><c:if test="${custList.gbn2 eq 'A'}">아미4 행복주택</c:if> 이사날짜 수정</h3>
    <p style="text-align:center; color:#fff;">- 이사날짜를 예약하고 확인할 수 있습니다 -</p>
	<div class="all_wrap">
		<div class="inr vision">
			<div class="area_box box2 recruit-box1">
			<p class="box-comment"><strong class="strong_st">이사날짜 수정</strong><br>
				<c:if test="${custList.gbn2 eq 'A'}">아미4 행복주택 </c:if>
				이사날짜 수정 페이지 입니다.
			</p>
			</div>
		</div>
		<div class="bt_wrap">
			<a href="/bmc/moveRes/movement.do" class="bt-il bt1" title="날짜 예약하기">날짜 예약하기</a>
			<a href="/bmc/moveRes/dateConfirm.do" class="bt-il bt2 on" title="날짜 확인하기">날짜 확인하기</a>
		</div>
	<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
		<security:authentication var="user" property="principal"/>
	</security:authorize>
	<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

		<form:form commandName="ilgwangCustVO" id="resForm" name="resForm" action="/bmc/moveRes/resUpdate.do"
			methodParam="post" htmlEscape="false"  target="_self" onSubmit="return validator() && confirm('수정은 1회만 가능합니다. 수정 하시겠습니까?')">
		
			<div class="gap20"></div>
			<p class="depth2-title" id="subtitle"></p>
			<!-- 본인인증 정보존재 -->
			<div class="bod_wrap">
				<fieldset>
					<div class="bod_write-il">
						<input type="hidden" id="gbn2" value="${custList.gbn2}" />
						<input type="hidden" id="cust_id" name="cust_id" value="${custList.cust_id}"/>
						<input type="hidden" id="gbn1" name="gbn1" value="${custList.gbn1}"/>
						<dl>
							<dt><label for="name">이름</label></dt>
							<dd>
								<span class="spantxt-il">${user.userName}</span> 
							 	<%-- <span id="ckname" name="ckname" class="spantxt-il">${user.userName}</span>  --%>
							 	<!-- <input type="text" id="name" name="name" readonly/> -->
							 	
							</dd>
						</dl>
						<dl>
							<dt><label for="tel">휴대폰번호</label></dt>
							<dd>
								<span class="spantxt-il">${user.tel1}-${user.tel2}-${user.tel3}</span>
								<%-- <span id="cktel" name="cktel" class="spantxt-il">010-5431-5530${user.tel1}-${user.tel2}-${user.tel3}</span> --%>
								<!-- <input type="text" id="tel" name="tel" readonly/> -->
							</dd>
						</dl>
						<dl>
							<dt><label for="address">동&middot;호수</label></dt>
							<dd style="padding-bottom:10px;">
								<span>${custList.addr1} 동</span>
								<span>${custList.addr2} 호</span>
								<input type="hidden" id="addr1" name="addr1" value="${custList.addr1}"/>
							</dd>
						</dl>
						<dl>
							<dt><label for="move">기존 날짜 및 시간</label></dt>
							<dd style="padding-botton:10px">
								<span style="padding-right:20px">${custList.move_date}</span>
								<c:set var="m_time" value="${custList.move_time}"/>
								<c:if test="${m_time eq 'T1'}">
									<c:out value="09:00 ~ 12:00" />
								</c:if>
								<c:if test="${m_time eq 'T2'}">
									<c:out value="12:00 ~ 15:00" />
								</c:if>
								<c:if test="${m_time eq 'T3'}">
									<c:out value="15:00 ~ 18:00" />
								</c:if>
								
								<c:if test="${m_time eq 'T4'}">
									<c:out value="07:00 ~ 09:00" />
								</c:if>
								<c:if test="${m_time eq 'T5'}">
									<c:out value="09:00 ~ 11:00" />
								</c:if>
								<c:if test="${m_time eq 'T6'}">
									<c:out value="11:00 ~ 13:00" />
								</c:if>
								<c:if test="${m_time eq 'T7'}">
									<c:out value="12:00 ~ 14:00" />
								</c:if>
								<c:if test="${m_time eq 'T8'}">
									<c:out value="13:00 ~ 15:00" />
								</c:if>
								<c:if test="${m_time eq 'T9'}">
									<c:out value="14:00 ~ 16:00" />
								</c:if>
								<c:if test="${m_time eq 'T10'}">
									<c:out value="15:00 ~ 17:00" />
								</c:if>
								<c:if test="${m_time eq 'T11'}">
									<c:out value="16:00 ~ 18:00" />
								</c:if>
								<c:if test="${m_time eq 'T12'}">
									<c:out value="17:00 ~ 19:00" />
								</c:if>
								<c:if test="${m_time eq 'T13'}">
									<c:out value="18:00 ~ 20:00" />
								</c:if>
								<c:if test="${m_time eq '14'}">
									<c:out value="19:00 ~ 21:00" />
								</c:if>
							</dd>
						</dl>
						<dl>
							<dt><label for="date">수정 날짜 및 시간</label></dt>
							<dd>
								<input type="text" id="datepick" data-validator="on" data-required="y" data-fieldName="이사날짜" readonly/>
								<select id="timeSel" name="timeSel" style="display:none;height:43px;width: 120px;text-align: center;"
									 data-validator="on" data-required="y" data-fieldName="이사시간">
								</select>
								<sapn id="noSel" style="display:none;">** 선택 가능한 시간이 없습니다 **</sapn>
								<input type="hidden" id="dateSel" name="dateSel"/>
							</dd>
						</dl>
					</div>
				
					<div class="btnall_wrap">
						<div style="float: right;">
							<div class="btn_in">
								<!-- receiptList.jsp에서 가져온 submit -->
								<input type="submit" class="btn_blue btn_wrap-il" value="예약 수정"/>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
		</form:form>
	</div>

</body></html>