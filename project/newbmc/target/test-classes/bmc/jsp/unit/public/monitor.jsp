<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<script>



$(function(){
	
	//20.04.09 웹접근성 반영 - 각페이지 title 정보 수정 
	var titleVal = "사전정보공표 - ";

	titleVal +=  "사전정보 모니터링| 부산도시공사";
	
	$("title").html(titleVal);
	
});
</script>
<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>
<div id="contents">
	<div class="content">
		

		<div class="inr vision">
			<p class="depth2-title">사전정보 모니터링(건의함)</p>
			<div class="area_box box2 area_box_color">
			✓ 부산도시공사 홈페이지에 공표하고 있는 사전정보에 대해 자유롭게 의견을 제시할 수 있는 공간입니다.<br> 
			&nbsp;&nbsp;&nbsp;- 수정·추가해야할 정보, 사전정보공표 게시판 운영 관련 개선이 필요한 사항 등<br>
			✓ 등록해주신 의견을 반영하여 보다 나은 정보를 제공할 수 있도록 노력하겠습니다.<br> 
			✓ 욕설, 비방, 광고 등 게시판의 취지에 맞지 않는 게시물은 삭제될 수 있습니다.<br> 
			</div>
			
			<p class="depth2-title">〔개인정보 보호를 위한 이용자 동의사항〕</p>
			<div class="area_box box2 area_box_color">
			<strong>○ 개인정보의 수집·이용목적</strong><br>
			 &nbsp;- 작성 내용에 따른 본인식별, 실명확인<br>
			 &nbsp;- 민원처리 의사소통 경로 확보<br>
			 &nbsp;- 기타 원활한 양질의 서비스 제공 등<br>
			<strong>○ 수집하는 개인정보의 항목</strong><br>
			 &nbsp;- 성명, 휴대폰번호, 이메일<br>
			<strong>○ 개인정보의 보유 및 이용기간</strong><br>
			 &nbsp;- 개인정보의 수집 또는 제공받은 목적 달성시 및 작성자 요청 시 지체없이 파기<br>
			 &nbsp;- 작성자의 내용 재확인을 위해 다른 삭제 요청이 없을시 일정기간 보존<br>
			<strong>○ 개인정보의 수집·이용 동의 거부에 관한 권리</strong><br>
			 &nbsp;- 위 개인정보의 수집ㆍ이용에 관한 동의를 거부하실 수 있으나, 동의를 하지 않을 경우 사전정보 건의함 게시글 등록에 제한을 받으실 수 있습니다.<br> 
				
			<br><br> 
				<p style="text-align:center;"><label for="agree"><input type="checkbox" id="agree">개인정보 수집·이용에 동의합니다.</label><p>
			</div>
			
			
			<form:form commandName="publicVO" id="list" name="list" action="/bmc/public/registerProc.do?mId=${menuVO.mId}" onsubmit="return agreechk() && confirm('등록하시겠습니까?') && alert('등록되었습니다.');" methodParam="post" enctype="multipart/form-data">
			<div class="bod_wrap">
			<input type="hidden" name="tel" value="${user.tel1}${user.tel2}${user.tel3}"/>
			<input type="hidden" name="usernm" value="${user.userName}"/>
			<div class="bod_write">
			<dl>
					<dt><label for="title"><span class="red" style="color:red;">*</span>제목</label></dt>
					<dd>
						<form:input path="title" cssClass="w90" maxlength="100" />

					</dd>
				</dl>
			<dl>
					<dt><label for="usernm"><span class="red" style="color:red;">*</span>이름</label></dt>
					<dd>
						<input type="text" value="${user.userName}" readonly>

					</dd>
				</dl>
			<dl>
					<dt><label for="tel"><span class="red" style="color:red;">*</span>전화번호</label></dt>
					<dd>
					<input type="text" value="${user.tel1}${user.tel2}${user.tel3}" readonly>
					</dd>
				</dl>
				<dl>
					<dt><label for="email"><span class="red" style="color:red;">*</span>이메일</label></dt>
					<dd>
						<form:input path="email" cssClass="w90" maxlength="100"/>

					</dd>
				</dl>
				<dl>
					<dt><label for="content"><span class="red" style="color:red;">*</span>내용</label></dt>
					<dd>
						<form:textarea path="content" cols="100" rows="10"/>



					</dd>
				</dl>
				</div>
				</div>
				
				<div class="btnall_wrap">
		<div class="btn_wrap">
			<div class="btn_in">
				<input id="submitBtn" type="submit" class="btn_blue" value="저장"/>
				<input type="button" class="btn-blue btntolist" onclick="location.href='/bmc/public/list.do?mId=0402000000'" value="취소"/>
			</div>
		</div>
			</form:form>
		</div>
	</div>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script>

	function agreechk(){
		
		if(!$("#agree").is(":checked")){
			alert('개인정보 수집·이용에 동의해주세요.');
			return false;
		} 
		if($("#title").val() == ""){
			alert('제목은 필수입력 항목입니다.');
			return false;
		}
		if($("#email").val() == ""){
			alert('이메일은 필수입력 항목입니다.');
			return false;
		}
		if($("#content").val() == ""){
			alert('내용은 필수입력 항목입니다.');
			return false;
		}
		
		
		} 
	


</script>
