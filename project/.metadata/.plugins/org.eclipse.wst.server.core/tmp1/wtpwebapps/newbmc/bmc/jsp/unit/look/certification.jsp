<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 증명서발급 메인 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 
 * 
 *
 * @author 
 * @since 
 */
%>
<script src="${pageContext.request.contextPath}/bmc/js/aes.js"></script>
<script>
   /* if (document.location.protocol == 'http:') {
    document.location.href = document.location.href.replace('http:', 'https:');
} */


$(document).ready(function(){
	//이름 암호화
	var name =  document.list.name.value;

    var key = CryptoJS.enc.Utf8.parse(name);               
    var base64 = CryptoJS.enc.Base64.stringify(key);  

    document.list.name.value = base64;
    
	//생년월일 암호화
    var juminId =  document.list.juminId.value;

    var key = CryptoJS.enc.Utf8.parse(juminId);               
    var base64 = CryptoJS.enc.Base64.stringify(key);  
    
    document.list.juminId.value = base64;
    
	//전화번호 암호화
    var tel =  document.list.tel.value;
    var key = CryptoJS.enc.Utf8.parse(tel);            
    var base64 = CryptoJS.enc.Base64.stringify(key);
    
    document.list.tel.value = base64;
    
    // decrypt
/*    var decrypt = CryptoJS.enc.Base64.parse(base64);
    var hashData = decrypt.toString(CryptoJS.enc.Utf8);
    console.log("decrypt hash:", hashData); */
    
   //document.getElementById('name1').setAttribute('value',base64);
    
});
</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<form:form commandName="lookCustomPayVO" id="list" name="list" action="https://cert.bmc.busan.kr/common/login/loginGenOutPost.do " onsubmit="return validator() && confirm('조회하시겠습니까?');" methodParam="post" htmlEscape="false"  target="_blank">

<input type="hidden" name="name"  value="${user.userName}"/>
<input type="hidden" name="juminId" value="${fn:substring(user.birthday, 2, 8)}"/>
<input type="hidden" name="gender" value="${user.gender}"/>
<%-- <input type="hidden" name="tel" value="${user.tel1}${user.tel2}${user.tel3}"/> --%>

<div id="contents"><div class="content">
<div class="gap20"></div>
<div class="subSec cpProfile clearfix">
	<div class="ceoInfo aniBox active">
		<div class="txtBox" style="width:1200px;">
			<strong>
				부산도시공사에서 온라인으로 필요하신 증명서를 발급 할 수 있도록 제공하는 서비스입니다.
			</strong>
		</div>	
	</div>
	
</div>
<div class="gap"></div>


<c:choose>
	<c:when test="${not empty user}"> 
		<div class="gap20"></div>
		<p class="depth2-title" id="subtitle"></p>
		<!-- 본인인증 정보존재 -->
		<div class="bod_wrap">
			<fieldset>
				<div class="bod_write">
					<dl>
						<dt><label for="name">이름</label></dt>
						<dd>
						 <span class="spantxt">${user.userName}</span> 
						<%-- <form:input path="name" maxlength="50" data-validator="on" data-required="y" data-fieldName="이름"/>  --%>
						</dd>
					</dl>
					<dl>
						<dt><label for="juminId">생년월일</label></dt>
						<dd>
						 <span class="spantxt">${fn:substring(user.birthday, 2, 8)}</span>
							<%-- <form:input path="juminId" maxlength="50" data-validator="on" data-inputType="birth" data-required="y" data-fieldName="생년월일" />&nbsp;&nbsp; --%>
							<!-- <span class="tip">앞자리 6자리를 입력해주세요.</span> -->
						</dd>
					</dl>
				<%-- 	<dl>
						<dt><label for="tel">휴대폰번호</label></dt>
						<dd>
						<span class="spantxt">${user.tel1}-${user.tel2}-${user.tel3}</span>
							 <form:input path="tel" maxlength="50" data-validator="on" data-inputType="tel" data-required="y" data-fieldName="휴대폰번호" />&nbsp;&nbsp;
							<span class="tip">010-1234-1234 형식으로 입력해주세요.</span> 
						</dd>
					</dl> --%>
				</div>
			</fieldset>
		</div>
		<div class="btnall_wrap">
			<div class="btn_wrap">
				<div class="btn_in">
					<input id="submitBtn" type="submit" class="btn_blue" value="조회"/>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="gap20"></div>
		<!-- 본인인증 정보없음 -->
		<p class="depth2-title">공사 승인 불필요 증명서</p>
	<img src="/bmc/images/look/process1.png" alt="증명서 신청[고객님] / 증명서 출력[고객님] / 수요기관 제출[고객님] / 증명서 진위확인[수요기관담당자]"/>
	<div class="gap20"></div>
	<p class="depth2-title">공사 승인 필요 증명서</p>
	
<img src="/bmc/images/look/process2.png" alt="증명서 신청[고객님] / 요청내역 검토 및 승인[부산도시공사] / 증명서 출력[고객님] / 수요기관 제출[고객님] / 증명서 진위확인[수요기관담당자]"/>
	<div class="area_box box2">
	<p>[증명서 신청] : 고객님이 공인인증서로 본인 확인을 하신 후 해당 증명서를 신청하는 단계입니다.</p>
	<p>[증명서 출력] : 고객님이 증명서를 출력하는 단계입니다.</p>
	<p>[요청내역 검토 및 승인] : 공사 승인이 필요한 출력물의 경우 공사 담당부서 및 전체 관리자 검토 및 승인이 진행되는 단계입니다.</p>
	<p>[수요기관 제출] : 고객님이 수요기관에 증명서를 제출하는 단계입니다.</p>
	<p>[증명서 진위확인] : 증명서를 받은 수요기관 담당자가 문서확인번호를 통해 진위여부를 확인하는 단계입니다.</p>
	<p>※ 본 서비스를 이용하기 위해서는 '팝업차단'을 해제하시기 바랍니다.</p>
	
</div>
	<div class="orgTable_wrap jobTable_wrap">
				<table class="orgTable jobTable">
				<caption>증명서 종류</caption>
				<thead>
					<tr>
						<th style="width: 10%">번호</th>
						<th style="width: 50%">증명서명</th>
						<th style="width: 20%">발급 절차</th>

					</tr>
				</thead>
					<tr>
						<td>1</td>
						<td>토지사용승낙서(착공신고)</td>
						<td style="text-align: center;">승인 후 발급</td>
					</tr>
						<tr>
						<td>2</td>
						<td>토지사용동의서(인허가, 지반조사, 토지임대, 기타)</td>
						<td style="text-align: center;">승인 후 발급</td>
					</tr>
					<tr>
						<td>3</td>
						<td>상가분양금 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>4</td>
						<td>주택분양금 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>5</td>
						<td>용지분양금 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>6</td>
						<td>임대보증금 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>7</td>
						<td>매입임대보증금 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>8</td>
						<td>전세임대보증금 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>9</td>
						<td>재개발임대보증금 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>10</td>
						<td>상가임대보증금 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>11</td>
						<td>임대료 개인별 수납내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>12</td>
						<td>변상금 개인별 수납개별 내역서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>13</td>
						<td>임대차계약사실 확인서(계약사실 확인용)</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>14</td>
						<td>임대차계약사실 확인서(권리침해 확인용)</td>
						<td style="text-align: center;">즉시</td>
					</tr>
					<tr>
						<td>15</td>
						<td>임대차계약 해약 확인서</td>
						<td style="text-align: center;">즉시</td>
					</tr>
	
				</table>
		</div>

		<div class="gap20"></div>
		<div class="btn_wrap btn_wrap2" style="text-align:right;">
			<a href="/bmc/user/inRealName.do?successUrl=/bmc/look/certification.do&mId=${menuVO.mId}" onclick="req.post(this); return false;">증명서 발급시스템 바로가기</a>
		</div>
	</c:otherwise>
</c:choose> 

</div></div>
</form:form>


<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
