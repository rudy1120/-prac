<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<c:set var="isNew" value="${empty searchVO.usrId}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>
<form:form commandName="searchVO" id="cancel" name="cancel"  methodParam="post" action="/sys/empMng/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>
<form:form commandName="searchVO" id="modify" name="modify" action="/sys/empMng/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator();" htmlEscape="false">
	<form:hidden path="usrId" />

	<h2>직원 등록/수정</h2>
	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write">
		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="w20">
					<c:if test="${isNew}"><span class="red">*</span></c:if>
					<label for="logonId">아이디</label>
				</th>
				<td>
				<c:choose>
					<c:when test="${isNew}">
						<form:input path="logonId" maxlength="300" cssClass="input200" data-validator="on" data-required="y" data-fieldName="아이디"/>
						<!-- 아이디 중복체크 -->
						<input type="button" onclick="javascript:fn_usrIdCheck(1);" class="btn_dblue" value="중복확인"/>
					</c:when>
					<c:otherwise>
						${searchVO.usrId}
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="usrId">사원번호</label></th>
				<td>
					<c:choose>
					<c:when test="${isNew}">
						<form:input path="empNum" maxlength="300" cssClass="input200" data-validator="on" data-required="y" data-fieldName="사원번호"/>
						<input type="button" onclick="javascript:fn_usrIdCheck(2);" class="btn_dblue" value="중복확인"/>
					</c:when>
					<c:otherwise>
						<form:input path="empNum" maxlength="300" cssClass="input200" data-validator="on" data-required="y" data-fieldName="사원번호"/>
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="usrNm">이름</label></th>
				<td>
					<form:input path="usrNm" maxlength="300" cssClass="input500" data-validator="on" data-required="y" data-fieldName="이름"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="realUseDepCode">부서</label></th>
				<td>
					<form:select path="realUseDepCode" data-validator="on" data-required="y" data-fieldName="부서">
						<option value="">-- 부서선택 --</option>
						<c:forEach var="d" items="${departList}">
							<c:if test="${d.newParent == '0'}">
								<form:option value="${d.depCode}" style="font-weight:600">${d.depCodeNm}</form:option>
							</c:if>
							<c:if test="${d.newParent != '0'}">
								<form:option value="${d.depCode}">${d.depCodeNm}</form:option>
							</c:if>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="blgTeamNm">직책</label></th>
				<td>
					<form:input path="blgTeamNm" maxlength="300" cssClass="input500" data-validator="on" data-fieldName="직책"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="telno">내선번호</label></th>
				<td>
					<form:input path="telno" maxlength="300" cssClass="input500" data-validator="on" data-fieldName="내선번호"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="emailAddr">이메일</label></th>
				<td>
					<form:input path="emailAddr" maxlength="300" cssClass="input500" data-validator="on" data-fieldName="이메일"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="adiInfo7">주요업무</label></th>
				<td>
					<form:textarea path="adiInfo7" rows="10" class="input500" maxlength="1000" data-validator="on" data-fieldName="주요업무"></form:textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="newOrder">정렬순서</label></th>
				<td>
					<form:input path="newOrder" class="input100" data-validator="on" data-inputType='num' maxlength="300" data-fieldName="정렬순서"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="positNm">동명이인 처리</label></th>
				<td>
					<form:input path="positNm" maxlength="300" cssClass="input500" data-validator="on" data-fieldName="positNm"/>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" onclick="return fn_dupCheck();" class="btn_cyan" value="저장"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<script type="text/javascript">
	yh.siteCode = "sys";
	yh.isNew = eval("${isNew}");
	
	var chk = "N";
	var empChk = "N";
	
	function fn_usrIdCheck(param) {
		//아이디
		var logonId = $("#logonId").val();
		//사원번호
		var empNum = $("#empNum").val();
		var str = "";
		if(param ==1){

			if($("#logonId").val() == "" || $("#logonId").val() == null) {
				alert("아이디를 입력해주세요.");
				return false;
			}
			empNum = "";
			str = "아이디";
			
		}else if(param ==2){

			if($("#empNum").val() == "" || $("#empNum").val() == null) {
				alert("사번을 입력해주세요.");
				return false;
			}
			logonId = "";
			str = "사번";
			
		}
		$.ajax({
			url  : "/sys/empMng/usrIdCheck.do",
			type : "post",
			dataType: 'json',
			data : {"usrId" : logonId, "empNum" : empNum},
			success : function(result) {
				if(result.rtcnt > 0) {
					alert("중복되는 "+str+"가(이) 존재합니다. 다른 "+str+"를(을) 사용해주세요.");
				} else {
					alert("해당 "+str+"는(은) 사용가능합니다.");
					if(param == 1){
						chk = "Y";						
					}else if(param==2){
						empChk = "Y";						
					}
				}
			}
		});
	}
	
	function fn_dupCheck() {
		<c:choose>
		<c:when test="${isNew}">
		if(chk == "N") {
			alert("아이디 중복확인이 필요합니다.");
			return false;
		} else if(empChk == "N"){
			alert("사원번호 중복확인이 필요합니다.");
			return false;
		}else {
			return true;
		}
		</c:when>
		<c:otherwise>
			return true;
		</c:otherwise>
		</c:choose>
	}
</script>

<link rel="stylesheet" type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
