<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%/**
 * 고객경영참여 참가자 등록
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
 */
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String now = format.format(new Date());
%>

<script>
var i = 1;
var addFunc = function(){
	$("#btnAdd").on("click", function(){

		if(i < 3){
			i++;
			$("#cntN").val(i);
			if(i == 2){
				$("#addStr1").css("display", "block"); 
				
				$("#sname1").attr({"data-required":"y"});
				$("#attach5_file").attr({"data-required":"y"});
				$("#attach6_file").attr({"data-required":"y"});
				$("#attach7_file").attr({"data-required":"y"});
				$("#attach8_file").attr({"data-required":"y"});
			} 
			if(i == 3){
				if($("#addStr1").css("display") == "none"){
					$("#addStr1").css("display", "block");
					
					$("#sname1").attr({"data-required":"y"});
					$("#attach5_file").attr({"data-required":"y"});
					$("#attach6_file").attr({"data-required":"y"});
					$("#attach7_file").attr({"data-required":"y"});
					$("#attach8_file").attr({"data-required":"y"});
					
				} else if($("#addStr1").css("display") == "block"){
					$("#addStr2").css("display", "block");
					
					$("#sname2").attr({"data-required":"y"});
					$("#attach9_file").attr({"data-required":"y"});
					$("#attach10_file").attr({"data-required":"y"});
					$("#attach11_file").attr({"data-required":"y"});
					$("#attach12_file").attr({"data-required":"y"});
					
				}
				$("#btnAdd").css("display","none");
				
			}
		} else{
			alert("최대 3명까지 가능합니다.");
		}
		
	});
}

var delFunc = function(){
	$("#btnDel1").on("click", function(){
		$("#sname1").val("");
		$("#attach5_file").val("");
		$("#attach6_file").val("");
		$("#attach7_file").val("");
		$("#attach8_file").val("");
		
		$("#sname1").attr({"data-required":"n"});
		$("#attach5_file").attr({"data-required":"n"});
		$("#attach6_file").attr({"data-required":"n"});
		$("#attach7_file").attr({"data-required":"n"});
		$("#attach8_file").attr({"data-required":"n"});
		
		$("#btnAdd").css("display","block");
		$("#addStr1").css("display","none");
		i--;
	    $("#cntN").val(i);
	});
	$("#btnDel2").on("click", function(){
		$("#sname2").val("");
		$("#attach9_file").val("");
		$("#attach10_file").val("");
		$("#attach11_file").val("");
		$("#attach12_file").val("");
		
		$("#sname2").attr({"data-required":"n"});
		$("#attach9_file").attr({"data-required":"n"});
		$("#attach10_file").attr({"data-required":"n"});
		$("#attach11_file").attr({"data-required":"n"});
		$("#attach12_file").attr({"data-required":"n"});
		
		$("#btnAdd").css("display","block");
		$("#addStr2").css("display","none");
		i--;
	    $("#cntN").val(i);
	});
}


$(document).ready(function () {
	
	addFunc();
	delFunc();

});


</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<style>
	.red {color:red;}
</style>

<div id="contents"><div class="content">
<form:form commandName="receiptVO" id="list" name="list" action="/bmc/receipt/registerProc.do?mId=${menuVO.mId}" onsubmit="return validator() && confirm('등록하시겠습니까?') " methodParam="post" enctype="multipart/form-data">
	<form:hidden path="idx"/>
	<!-- DUPINFO -->
	<input type="hidden" name="dupinfo" value="${user.privatecode}"/>
	<input type="hidden" name="usernm" value="${user.userName}"/>
	<input type="hidden" name="tel" value="${user.tel1}-${user.tel2}-${user.tel3}"/>
	<input type="hidden" name="cntN" id="cntN" value="1"/>

	<c:if test="${result.gbn eq 'A'}">

		<div class="bod_wrap">
			<fieldset>
				<legend class="hidden">${result.gbn}</legend>
				<p class="taR pB5">(<span class="red">*</span>)표시는 필수 입력 사항입니다.<br/>추천학생은 최대 3명까지 가능합니다.</p>
				<div class="gap"></div>
				
				<div class="bod_write bod_write2">
					<dl>
						<dt><label for="title">제목</label></dt>
						<dd>
							<span class="spantxt">${result.title}</span>
							<input type="hidden" name="gbn" value="${result.gbn}"/>
						</dd>
					</dl>
					
					<dl>
						<dt><label for="school"><span class="red">*</span>소속학교</label></dt> 
						<dd>
							<form:input path="school" maxlength="50" data-validator="on" data-required="y" data-fieldName="소속학교" />
							<span>※ 학교명은 전체 이름으로 작성해주세요. ex) ㅇㅇ고(X), ㅇㅇ고등학교(O)</span>
						</dd>
					</dl>
					
					<dl>
						<dt><label for="usernm"><span class="red">*</span>담당교사 이름</label></dt>
						<dd>
							<span class="spantxt">${user.userName}</span>
	
						</dd>
					</dl>
					
					<dl>
						<dt><label for="tel"><span class="red">*</span>담당교사 휴대폰</label></dt>
						<dd>
							<span class="spantxt">${user.tel1}-${user.tel2}-${user.tel3}</span>
						</dd> 
					</dl>
					<dl>
						<dt><span class="red">*</span>추천심사위원회 의결서</dt>
						<dd>
							<input type="hidden" name="resolution" value="${receiptVO.resolution}" />
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${receiptVO.resolution}" />
								<c:param name="downloadYn" value="Y"/>
							</c:import>
							<input type="file" id="resolution_file" name="resolution_file" data-validator="on" data-required="y" data-fieldName="추천심사위원회 의결서" class="input300 mB10" value="" />
						</dd>
					</dl>  
					<dl>
						<dt><span class="red">*</span>추천현황표</dt>
						<dd>
							<input type="hidden" name="recStatus" value="${receiptVO.recStatus}" />
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${receiptVO.recStatus}" />
								<c:param name="downloadYn" value="Y"/>
							</c:import>
							<input type="file" id="recStatus_file" name="recStatus_file" data-validator="on" data-required="y" data-fieldName="추천현황표" class="input300 mB10" value="" />
						</dd>
					</dl>
	
					<dl>
						<dt><label for="sname"><span class="red">*</span>추천학생 이름</label></dt>
						<dd>
							<input type="text" id="sname" name="sname" maxlength="50" data-validator="on" data-required="y" data-fieldName="추천학생 이름" />
						</dd>
						<input type="button" id="btnAdd" class="btnDel2" value="+"/>
					</dl>
					<dl>
						<dt><span class="red">*</span>기능인재추천서</dt>
						<dd>
							<input type="hidden" name="attach1" value="${receiptVO.attach1}" />
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${receiptVO.attach1}" />
								<c:param name="downloadYn" value="Y"/>
							</c:import>
							<input type="file" id="attach1_file" name="attach1_file" data-validator="on" data-required="y" data-fieldName="기능인재추천서" class="input300 mB10" value="" />
						</dd>
					</dl>
					<dl>
						<dt><span class="red">*</span>개인정보수집 및 위탁동의서</dt>
						<dd>
							<input type="hidden" name="attach2" value="${receiptVO.attach2}" />
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${receiptVO.attach2}" />
								<c:param name="downloadYn" value="Y"/>
							</c:import>
							<input type="file" id="attach2_file" name="attach2_file" data-validator="on" data-required="y" data-fieldName="개인정보수집 및 위탁동의서" class="input300 mB10" value="" />
						</dd>
					</dl>
					<dl>
						<dt><span class="red">*</span>성적증명서</dt>
						<dd>
							<input type="hidden" name="attach3" value="${receiptVO.attach3}" />
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${receiptVO.attach3}" />
								<c:param name="downloadYn" value="Y"/>
							</c:import>
							<input type="file" id="attach3_file" name="attach3_file" data-validator="on" data-required="y" data-fieldName="성적증명서" class="input300 mB10" value="" />
						</dd>
					</dl>
					<dl>
						<dt><span class="red">*</span>졸업(예정)증명서</dt>
						<dd>
							<input type="hidden" name="attach4" value="${receiptVO.attach4}" />
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${receiptVO.attach4}" />
								<c:param name="downloadYn" value="Y"/>
							</c:import>
							<input type="file" id="attach4_file" name="attach4_file" data-validator="on" data-required="y" data-fieldName="졸업(예정)증명서" class="input300 mB10" value="" />
						</dd>
					</dl>
	
				<!-- 두번째 추천학생 시작 -->				
					<div id="addStr1" style="display: none;">
						<dl style="border-top: 1px solid black">
							<dt><label for="sname1"><span class="red">*</span>추천학생 이름</label></dt>
							<dd>
								<input type="text" id="sname1" name="sname1" maxlength="50" data-validator="on" data-required="n" data-fieldName="추천학생 이름" />
							</dd>
							<input type="button" id="btnDel1" class="btnDel2" value="-" />
						</dl>
						<dl>
							<dt><span class="red">*</span>기능인재추천서</dt>
							<dd>
								<input type="hidden" name="attach5" value="${receiptVO.attach5}" />
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${receiptVO.attach5}" />
									<c:param name="downloadYn" value="Y"/>
								</c:import>
								<input type="file" id="attach5_file" name="attach5_file" data-validator="on" data-required="n" data-fieldName="기능인재추천서" class="input300 mB10" value="" />
							</dd>
						</dl>
						<dl>
							<dt><span class="red">*</span>개인정보수집 및 위탁동의서</dt>
							<dd>
								<input type="hidden" name="attach6" value="${receiptVO.attach6}" />
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${receiptVO.attach6}" />
									<c:param name="downloadYn" value="Y"/>
								</c:import>
								<input type="file" id="attach6_file" name="attach6_file" data-validator="on" data-required="n" data-fieldName="개인정보수집 및 위탁동의서" class="input300 mB10" value="" />
							</dd>
						</dl>
						<dl>
							<dt><span class="red">*</span>성적증명서</dt>
							<dd>
								<input type="hidden" name="attach7" value="${receiptVO.attach7}" />
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${receiptVO.attach7}" />
									<c:param name="downloadYn" value="Y"/>
								</c:import>
								<input type="file" id="attach7_file" name="attach7_file" data-validator="on" data-required="n" data-fieldName="성적증명서" class="input300 mB10" value="" />
							</dd>
						</dl>
						<dl style="border-bottom: 1px solid black">
							<dt><span class="red">*</span>졸업(예정)증명서</dt>
							<dd>
								<input type="hidden" name="attach8" value="${receiptVO.attach8}" />
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${receiptVO.attach8}" />
									<c:param name="downloadYn" value="Y"/>
								</c:import>
								<input type="file" id="attach8_file" name="attach8_file" data-validator="on" data-required="n" data-fieldName="졸업(예정)증명서" class="input300 mB10" value="" />
							</dd>
						</dl>
					</div>
				<!-- 두번째 추천학생 끝 -->	
				
				<!-- 세번째 추천학생 시작 -->		
					<div id="addStr2" style="display:none;" >
						<dl>
							<dt><label for="sname2"><span class="red">*</span>추천학생 이름</label></dt>
							<dd>
								<input type="text" id="sname2" name="sname2" maxlength="50" data-validator="on" data-required="n" data-fieldName="추천학생 이름" />
							</dd>
							<input type="button" id="btnDel2" class="btnDel2" value="-"/>
						</dl>
						<dl>
							<dt><span class="red">*</span>기능인재추천서</dt>
							<dd>
								<input type="hidden" name="attach9" value="${receiptVO.attach9}" />
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${receiptVO.attach9}" />
									<c:param name="downloadYn" value="Y"/>
								</c:import>
								<input type="file" id="attach9_file" name="attach9_file" data-validator="on" data-required="n" data-fieldName="기능인재추천서" class="input300 mB10" value="" />
							</dd>
						</dl>
						<dl>
							<dt><span class="red">*</span>개인정보수집 및 위탁동의서</dt>
							<dd>
								<input type="hidden" name="attach10" value="${receiptVO.attach10}" />
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${receiptVO.attach10}" />
									<c:param name="downloadYn" value="Y"/>
								</c:import>
								<input type="file" id="attach10_file" name="attach10_file" data-validator="on" data-required="n" data-fieldName="개인정보수집 및 위탁동의서" class="input300 mB10" value="" />
							</dd>
						</dl>
						<dl>
							<dt><span class="red">*</span>성적증명서</dt>
							<dd>
								<input type="hidden" name="attach11" value="${receiptVO.attach11}" />
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${receiptVO.attach11}" />
									<c:param name="downloadYn" value="Y"/>
								</c:import>
								<input type="file" id="attach11_file" name="attach11_file" data-validator="on" data-required="n" data-fieldName="성적증명서" class="input300 mB10" value="" />
							</dd>
						</dl>
						<dl style="border-bottom: 1px solid black">
							<dt><span class="red">*</span>졸업(예정)증명서</dt>
							<dd>
								<input type="hidden" name="attach12" value="${receiptVO.attach12}" />
								<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${receiptVO.attach12}" />
									<c:param name="downloadYn" value="Y"/>
								</c:import>
								<input type="file" id="attach12_file" name="attach12_file" data-validator="on" data-required="n" data-fieldName="졸업(예정)증명서" class="input300 mB10" value="" />
							</dd>
						</dl>
					</div>
					<dl>
						<dt><span class="red">*</span>개인정보 수집 &middot;이용에<br/>관한 사항</dt>
						<dd>
							<div class="terms_overflow shot text-justify mb30">
								<p class="ehicheight ehiccolor">
									<strong>&middot; 수집하는 개인정보 항목</strong><br/>
									- 필수항목 : 성명, 핸드폰번호, 학교명<br/><br/>
									<strong>&middot; 개인정보의 처리 및 보유기간</strong><br/>
									- 180일 (* 지원자 요청 시 즉시 삭제)<br/><br/>
									<strong>&middot; 개인정보의 수집목적 및 이용목적</strong><br/>
									- 본인 식별, 채용 관련 개별 연락 등<br/><br/>
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
	</c:if>
	
	<c:if test="${result.gbn eq 'B'}">
		<div class="bod_wrap">
			<fieldset>
				<legend class="hidden">${result.gbn}</legend>
				<p class="taR pB5">(<span class="red">*</span>)표시는 필수 입력 사항입니다.</p>
				<div class="gap"></div>
				
				<div class="bod_write">
					<dl>
						<dt><label for="title">제목</label></dt>
						<dd>
							<span class="spantxt">${result.title}</span>
							<input type="hidden" name="gbn" value="${result.gbn}"/>
						</dd>
					</dl>
					
					<dl>
						<dt><label for="usernm"><span class="red">*</span>지원자</label></dt>
						<dd>
							<span class="spantxt">${user.userName}</span>
	
						</dd>
					</dl>
					
					<dl>
						<dt><label for="tel"><span class="red">*</span>지원자 핸드폰번호</label></dt>
						<dd>
							<span class="spantxt">${user.tel1}-${user.tel2}-${user.tel3}</span>
						</dd> 
					</dl>
					<dl>
						<dt><span class="red">*</span>입사지원서</dt>
						<dd>
							<input type="hidden" name="attachId" value="${receiptVO.attachId}" />
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${receiptVO.attachId}" />
								<c:param name="downloadYn" value="Y"/>
							</c:import>
							<input type="file" id="attachId_file" name="attachId_file" data-validator="on" data-required="y" data-fieldName="입사지원서" class="input300 mB10" value="" />
						</dd>
					</dl>
					<dl>
						<dt><span class="red">*</span>개인정보 수집 &middot;이용에<br/>관한 사항</dt>
						<dd>
							<div class="terms_overflow shot text-justify mb30">
								<p class="ehicheight ehiccolor">
									<strong>&middot; 수집하는 개인정보 항목</strong><br/>
									- 필수항목 : 성명, 핸드폰번호<br/><br/>
									<strong>&middot; 개인정보의 처리 및 보유기간</strong><br/>
									- 180일 (* 지원자 요청 시 즉시 삭제)<br/><br/>
									<strong>&middot; 개인정보의 수집목적 및 이용목적</strong><br/>
									- 본인 식별, 채용 관련 개별 연락 등<br/><br/>
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
			</fieldset>
		</div>
	</c:if>
	
	<div class="btnall_wrap">
		<div class="btn_wrap">
			<div class="btn_in">
				<input id="submitBtn" type="submit" class="btn_blue" value="저장"/>
				<input type="button" class="btn-blue" onclick="location.href='/bmc/receipt/view.do?idx=${result.idx}&mId=${menuVO.mId}'" value="취소"/>
<%-- 				<input type="button" class="btn-blue btntolist" onclick="location.href='/bmc/receipt/list.do?mId=${menuVO.mId}'" value="목록"/> --%>
			</div>
		</div>
	</div>
</form:form>

</div></div>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script>

<script>
	
	$(function() {

		//[2019.11.20.] 키보드 보안모듈
// 		$("#userAgent").text(navigator.userAgent);
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
// 		npPfsStartup(null, false, true, false, false, "npkencrypt", "on");
		
	});
	
</script>




