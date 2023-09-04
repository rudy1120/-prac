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

    
});


</script>

<c:set var="successUrl" value="/bmc/report/viewList.do" />
<c:set var="cancelUrl" value="/bmc/report/viewList.do" />
<c:set var="sAuthType" value="M" />

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
});

</script>

<style>
	.red {color:red;}
	.rCont { white-space: break-spaces; }
</style>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>

<form:form commandName="reportVO" id="list" name="list" action="/bmc/report/view.do?mId=${menuVO.mId}" 
	onsubmit="return validator() && confirm('등록하시겠습니까?') " methodParam="post" enctype="multipart/form-data">

<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<div id="contents"><div class="content">
	<c:choose>
	<c:when test="${not empty user}"> 
		<div class="gap20"></div>
			<!-- DUPINFO -->
			<input type="hidden" name="dupinfo" value="${user.privatecode}"/>
			<input type="hidden" name="uname" value="${user.userName}"/>
			<input type="hidden" name="tel" value="${user.tel1}-${user.tel2}-${user.tel3}"/>
		
			<div class="bod_view">
				<h4>${result.title}</h4>										
				<div class="view_cont">	
					<div style="text-align: right;">
						등록일 :
						<fmt:parseDate value="${result.creatDate}" var="cdate" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate var="cDate" value="${cdate}" pattern="yyyy-MM-dd" />
						${cDate }
					</div>
					<div class="gap"></div>
					<div class="mT10">							
						<div class="rCont"><c:out value="${result.cont}" /></div>
					</div>
				</div>
				<dl class="view_file clFix">
					<dt><span>고객첨부 파일</span></dt>
					<dd style="text-align: right; color: #777;">※ 첨부파일은 파일명만 표기됩니다.</dd>
					<dd>				
						&nbsp;${fileNm}
					</dd>
				</dl>	
			</div>
			
			<div class="btn_wrap">
				<input type="button" class="btn-blue" onclick="location.href='/bmc/report/read.do?mId=${menuVO.mId}'" value="목록"/>
			</div>
		</c:when>
		<c:otherwise>
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
	
</form:form>	
	

</div></div>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>

