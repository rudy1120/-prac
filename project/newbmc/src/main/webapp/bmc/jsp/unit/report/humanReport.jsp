<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 사용자 리스트 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
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

//유효성검사
function chkBtn(){

	if(!$("#agree").is(":checked")){
		alert('개인정보제공에 동의해주세요.');
		return false;
	}

}

</script>

<style>
	.red {color:red;}
</style>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>

<%-- <form:form commandName="reportVO" id="list" name="list" action="/bmc/report/notifyProc.do?mId=${menuVO.mId}"  --%>
<%-- 	onsubmit="return validator() && confirm('등록하시겠습니까?') " methodParam="post" enctype="multipart/form-data"> --%>

<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<div id="contents"><div class="content">

			<div class="gap20"></div>
			<p class="depth2-title">인권침해 신고센터 안내</p>
				<div class="inr vision">
					<div class="area_box box2">
						<p>부산도시공사는 공사의 경영활동과 관련한 모든 이해관계자의 인권침해를 방지하고 구제하기 위해  「인권침해 신고센터」를 설치하여 운영하고 있습니다.</p>
					</div>
				</div>
			<p class="depth2-title">신고대상</p>
			<p class="con_in">
				<span class="intro-txt intro-txt-padding">
					부산도시공사의 경영활동에 따른 모든 모든 인권침해 행위 
				</span>
			</p>
			<div class="gap20"></div>
			<p class="depth2-title">신고자의 비밀보장 및 처리절차</p>
			<p class="con_in">
				<span class="intro-txt intro-txt-padding">
					신고인의 비밀은 절대 보장되며 신고한 내용은 담당자 외엔 열람할 수 없으며, 소정의 절차에 의해 조사·처리한 후 결과를 회신해 드립니다.
				</span>
			</p>
			
			<div class="gap"></div>
			
			<div class="btn_wrap btn_wrap2">
				<c:choose>
					<c:when test="${not empty user}">
						<!-- 본인인증 정보존재 -->
						<a href="/bmc/report/write.do?mId=${menuVO.mId}">신고하기</a>
						
					</c:when>
					<c:otherwise>
						<!-- 본인인증 정보없음 -->
						<a href="/bmc/user/inRealName.do?mId=${menuVO.mId}&successUrl=/bmc/report/write.do">신고하기</a>
					</c:otherwise>
				</c:choose>
<%-- 				<a href="/bmc/user/inRealName.do?successUrl=/bmc/report/write.do&mId=${menuVO.mId}&sAuthType=M" onclick="req.post(this); return false;">신고하기</a> --%>
				<c:choose>
					<c:when test="${not empty user}">
						<!-- 본인인증 정보존재 -->
						<a href="/bmc/report/read.do?mId=${menuVO.mId}">조회하기</a>
						
					</c:when>
					<c:otherwise>
						<!-- 본인인증 정보없음 -->
						<a href="/bmc/user/inRealName.do?mId=${menuVO.mId}&successUrl=/bmc/report/read.do">조회하기</a>
					</c:otherwise>
				</c:choose>
<%-- 				<a href="/bmc/user/inRealName.do?successUrl=/bmc/report/view.do&mId=${menuVO.mId}&sAuthType=M" onclick="req.post(this); return false;">조회하기</a> --%>
			</div>

</div></div>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>

