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

<style>
	.red {color:red;}
</style>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>

<form:form commandName="reportVO" id="list" name="list" action="/bmc/report/notifyProc.do?mId=${menuVO.mId}" 
	onsubmit="return validator() && confirm('등록하시겠습니까?') " methodParam="post" enctype="multipart/form-data">

<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<div id="contents"><div class="content">
	<c:choose>
	<c:when test="${not empty user}"> 
		<div class="gap20"></div>
			<!-- DUPINFO -->
			<input type="hidden" name="dupinfo" value="${user.privatecode}"/>
			<input type="hidden" name="uname" value="${user.userName}"/>
			<input type="hidden" name="tel" value="${user.tel1}${user.tel2}${user.tel3}"/>
		
			<div class="bod_wrap">
				<fieldset>
					<p class="taR pB5">(<span class="red">*</span>)표시는 필수 입력 사항입니다.</p>
					<div class="gap"></div>
					
					<div class="bod_write">
		
						<dl>
							<dt><label for="usernm"><span class="red">*</span>이름</label></dt>
							<dd>
								<span class="spantxt">${user.userName}</span>
							</dd>
						</dl>
						
						<dl>
							<dt><label for="tel"><span class="red">*</span>연락처</label></dt>
							<dd>
								<span class="spantxt">${user.tel1}-${user.tel2}-${user.tel3}</span>
							</dd>
						</dl>
						<dl>
							<dt><label for="sName1"><span class="red">*</span>이메일</label></dt>
							<dd>
								<input class="inputT inp1" style="width: 300px; border: 1px solid #ddd !important;" type="email" id="email" name="email" maxlength="60" data-validator="on" data-required="y" data-fieldname="이메일" placeholder="이메일형식으로 입력해주세요">
							</dd>
						</dl>
						<dl>
							<dt><label for="title"><span class="red">*</span>제목</label></dt>
							<dd>
								<input type="text" id="title" name="title" style="width: 100%;" maxlength="50" data-validator="on" data-required="y" data-fieldName="제목" />
							</dd>
						</dl>
						<dl>
							<dt><label for="cont"><span class="red">*</span>내용</label></dt>
							<dd>
								<textarea id="cont" name="cont" rows="20" cols="106" data-validator="on" data-required="y" data-fieldName="내용"></textarea>
							</dd>
						</dl>
						<dl>
							<dt>파일 첨부</dt>
							<dd>
<!-- 								<p class="red mB10">※ 파일은 최대 2개까지 첨부하실 수 있습니다.</p> -->
								<c:set var="param_showComment" value="${false}" scope="request"/>
								<c:set var="param_maxFileNum" value="1" scope="request"/>
								<jsp:include page="/common/jsp/component/fileAttachForm.jsp"/>
								<form:hidden path="attachId"/>
							</dd>
						</dl>
						<dl>
							<dt><span class="red">*</span>개인정보 수집 &middot; 이용에<br/>관한 사항</dt>
							<dd>
								<div class="terms_overflow shot text-justify mb30">
									<p class="ehicheight ehiccolor">
										<strong>&middot; 수집하는 개인정보 항목</strong><br/>
										- 필수항목 : 성명, 핸드폰번호, 이메일<br/><br/>
										<strong>&middot; 개인정보의 처리 및 보유기간</strong><br/>
										- 2년<br/><br/>
										<strong>&middot; 개인정보의 수집목적 및 이용목적</strong><br/>
										- 본인 식별, 조치사항 회신 등 원활한 의사소통 경로 확보 등<br/><br/>
										<strong>&middot; 개인정보의 수집&middot;이용 동의 거부에 관한 권리</strong><br/>
										위의 개인정보 수집&middot;이용에 대한 동의를 거부하실 수 있으나, 동의하지 않을 경우 서비스 이용에 일부 제한을 받을 수 있습니다.
									</p>
								</div>
								<br/>
									<label for="agree"><input type="checkbox" id="agree" name="agree" data-validator="on" data-required="y" data-fieldName="개인정보수집 동의">
										상기내용을 확인하고 동의합니다.</label>
								<br/>
							</dd>
						</dl>
					</div>
				</fieldset>
			</div>
			
			<div class="btnall_wrap">
				<div class="btn_wrap">
					<div class="btn_in">
						<input id="submitBtn" type="submit" class="btn-blue btntolist" value="저장"/>
						<input type="button" class="btn-blue btntolist" onclick="location.href='/bmc/report/humanReport.do?mId=${menuVO.mId}'" value="취소"/>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
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
				<a href="/bmc/user/inRealName.do?successUrl=/bmc/report/write.do&mId=${menuVO.mId}&sAuthType=M" onclick="req.post(this); return false;">신고하기</a>
				<a href="/bmc/report/view.do" onclick="req.post(this); return false;">조회하기</a>
			</div>
		</c:otherwise>
	</c:choose>
	
</form:form>	
	

</div></div>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>

