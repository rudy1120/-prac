<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객참여이벤트 등록 정보입력 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.18		박상혁		    최초 생성 및 코드 수정
 * 2021.04.23       손문배          내용 및 첨부파일 추가, 개인정보 동의 추가
 *
 * @author 박상혁
 * @since 2019.10.18
 */
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String now = format.format(new Date());
%>

<div id="contents"><div class="content">
<form:form commandName="eventParticipantVO" id="list" name="list" action="/bmc/event/register2.do?mId=${menuVO.mId}" onsubmit="return validator() &&agreechk()&& confirm('등록하시겠습니까?');" methodParam="post" enctype="multipart/form-data" htmlEscape="false">
	<form:hidden path="idx"/>	

<div class="bod_wrap">
	<fieldset>
	<div class="gap20"></div>
		<div class="bod_write">
			<dl>
				<dt><label for="subject">제목</label></dt>
				<dd class="bod_pd">
					${result.subject}
				</dd>
			</dl>
			<dl>
				<dt><label for="subject">기간</label></dt>
				<dd class="bod_pd">
					${result.exedate}
				</dd>
				
			</dl>
			<dl>
				<dt><label for="subject">발표일</label></dt>
				<dd class="bod_pd">
					${result.pubdate}
				</dd>
			</dl>
			<dl>
				<dt><label for="usernm">이름</label></dt>
				<dd>
					<form:input path="usernm" maxlength="50" data-validator="on" data-required="y" data-fieldName="이름"/>
				</dd>
			</dl>
			<dl>
				<dt><label for="tel">휴대폰</label></dt>
				<dd>
					<form:input path="tel" maxlength="50" data-validator="on" data-inputType="phone" data-required="y" data-fieldName="휴대전화" />&nbsp;&nbsp;
					<span class="tip">01012345678 형식으로 입력해주세요.</span>
				</dd>
			</dl>
		
			<c:if test="${result.gubun eq 1}">
<%-- 			<dl>
					<dt><label for="content">내용</label></dt>
					<dd>
						<form:textarea path="content" cols="100" rows="3"/>

					</dd>
				</dl> --%>
				<dl>
					<dt><label for="birth">생년월일</label></dt>
					<dd>
						<form:input path="birth" maxlength="6" data-validator="on" data-inputType="num"  data-required="y" data-fieldName="생년월일"/>&nbsp;&nbsp;
						<span class="tip">920101 형식으로 입력해주세요.</span>
					</dd>
				</dl>
				<dl>
					<dt>파일 첨부</dt>
					<dd>
						<p class="red mB10">※ 파일은 최대 2개까지 첨부하실 수 있습니다.</p>
						<c:set var="param_showComment" value="${false}" scope="request"/>
						<c:set var="param_maxFileNum" value="2" scope="request"/>
						<jsp:include page="/common/jsp/component/fileAttachForm.jsp"/>
						<form:hidden path="attachId"/>
					</dd>
				
				</dl>
			</c:if> 
			
				
			
		<dl>
		<dt><label for="bAddr1">주소</label></dt>
		<dd>
			<form:input path="bAddr1" cssClass="w70" maxlength="200" data-validator="on" data-required="y"  data-fieldName="주소"/>
			<a href="#" onclick="addr.popup('#bAddr1', '#bAddr2'); return false;" title="도로명 주소 검색 사이트를 새창으로 표시" class="bt_white_s" style="display: inline-block; background: #02768a;  color: #fff;padding: 1.5rem 2.4rem;">
				<span class="btn_white">주소 검색</span>
			</a>
		</dd>
	</dl>
	<dl>
		<dt><label for="bAddr2">상세 주소</label></dt>
		<dd>
			<form:input path="bAddr2" cssClass="w70" maxlength="300" data-validator="on"  data-required="y" data-fieldName="상세 주소"/>
		</dd>
	</dl>
	<c:if test="${result.gubun eq 2}">
				<dl>
					<dt>파일 첨부</dt>
					<dd>
						<p class="red mB10">※ 파일은 최대 2개까지 첨부하실 수 있습니다.</p>
						<c:set var="param_showComment" value="${false}" scope="request"/>
						<c:set var="param_maxFileNum" value="2" scope="request"/>
						<jsp:include page="/common/jsp/component/fileAttachForm.jsp"/>
						<form:hidden path="attachId"/>
					</dd>
				
				</dl>
	</c:if> 
	
	
	<c:if test="${result.gubun eq 1}">
				<dl>
					<dt>개인정보처리방침</dt>
					<dd>
					<div class="terms_overflow shot text-justify mb30" style="padding:25px 20px;">

 					<p class="ehicheight ehiccolor" style="">
 					□ 개인정보 수집 및 이용 동의<br>
 					<span class="pl1">▫ 수집항목 : 계약자 이름, 계약자 연락처, 수령 받을 주소<br></span>
 					<span class="pl1">▫ 수집목적 : 계약자 확인 및 상품권 발송<br></span>
 					<span class="pl1">▫ 보유기간 : 응모 완료일로부터 1개월 이내 (02.13~03.31.)<br></span>
					<span class="pl2">※ 개인정보 수집·이용에 대한 동의를 거부할 권리가 있으며, 이 경우 응모 참여가 제한됩니다.<br></span> 
					<span class="pl1">▫ 기타약관 동의<br></span>
 					<span class="pl2">- 본 인센티브 사업은 부산도시공사에서 주최하는 이벤트이며, 아미4 행복주택 계약자를 대상으로 진행합니다.<br></span>
				   	<span class="pl2">- 본인 신청이 원칙이며, 대리인(가족)의 경우 계약자의 개인정보로만 응모 가능합니다.<br></span>
				   	<span class="pl2">- 이벤트 참여 정보 오기입으로 인한 추첨, 선정에 대한 책임은 신청자에게 있습니다. 정확하게 정보를 기입하시기 바랍니다.<br></span>
				   	<span class="pl2">- 당첨자 발표는 2023년 2월말 기제출된 신청자료 확인 후 프로그램 추첨을 통해 선정하며 입력하신 휴대번호로 문자 안내 드릴예정입니다.<br></span>
				   	<span class="pl2">- 경품 수령은 입력하신 주소지로 우편을 통해 송부드릴 예정입니다.<br></span>
					</p>
				
					</div>
						<label for="agree"><input type="checkbox" id="agree">상기내용을 확인하고 동의합니다.</label>
					</dd>
				</dl>
		
	</c:if>
	<c:if test="${result.gubun eq 2}">
				<dl>
					<dt>개인정보처리방침</dt>
					<dd>
					<div class="terms_overflow shot text-justify mb30">

 					<p class="ehicheight ehiccolor" style="">`
 					○ 개인정보의 수집·이용 목적<br>
 					- 작성 내용에 따른 본인식별, 실명확인<br>
					 - 부산도시공사 시민참여 이벤트 관련 상품 전달<br><br>
					○ 수집하는 개인정보의 항목<br>
					 - 성명, 휴대폰번호<br><br>
					○ 개인정보의 보유 및 이용 기간<br>
					 - 개인정보의 수집 또는 제공받은 목적 달성시 및 작성자 요청 시 지체없이 파기<br>
					 - 작성자의 내용 재확인을 위해 다른 삭제 요청이 없을시 일정기간 보존<br><br>
					○ 개인정보의 수집·이용 동의 거부에 관한 권리<br>
					 - 개인정보의 수집ㆍ이용에 관한 동의를 거부할 권리가 있으며, 동의를 하지 않을 경우 고객참여이벤트 참여에 제한을 받으실 수 있습니다.<br>
					<br>
					◻ 개인정보 수집·이용에 동의합니다.<br>
					◻ 내용 입력란에 개인정보가 없음을 확인하였습니다.<br>
					</p>
				
					</div>
						<label for="agree"><input type="checkbox" id="agree">상기내용을 확인하고 동의합니다.</label>
					</dd>
				</dl>
		</c:if>		
		</div>
	</fieldset>
</div>

<div class="btnall_wrap">
	<div class="btn_wrap">
		<div class="btn_in">
			<input id="submitBtn" type="submit" class="btn_blue" value="등록"/>
			<input type="button" class="btn_blue" onclick="javascript:history.back()" title="이전 페이지로 이동" value="취소" />
		</div>
	</div>
</div>
</form:form>

</div></div>
 <script type="text/javascript" src="/common/js/address.js"></script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script>
<script>
$(document).ready(function () {
	//[2019.11.20.] 키보드 보안모듈
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
function agreechk(){
	
if(!$("#agree").is(":checked")){
	alert('개인정보제공에 동의해주세요.');
	return false;
} 
} 
</script>