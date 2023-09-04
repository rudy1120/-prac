<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 개인정보 취급 메뉴 등록/수정
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.06.15		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.06.15
	 */
%>
<c:set var="isNew" value="${empty searchVO.idx}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>
<h2>개인정보 취급 메뉴 등록/수정</h2>
<form:form commandName="searchVO" id="cancel" name="cancel" action="/sys/privacy/prm/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchPrmType"/>
	<form:hidden path="page"/>
</form:form>
<form:form commandName="searchVO" id="write" name="write" action="/sys/privacy/prm/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator() && process.additional_validator() && confirm('등록하시겠습니까?');" htmlEscape="false">
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchPrmType"/>
	<form:hidden path="page"/>
	<form:hidden path="idx"/>
	<form:hidden path="prmType" value="${prmTypeList[1].code}"/> <%-- 프로그램만 등록 가능. 게시판은 게시판 관리 페이지에서 관리하도록 되어 있음. --%>

	<c:if test="${error}">
		<span class="info tip">처리 중 오류가 발생했습니다.</span>
		<br/>
	</c:if>

	<p class="info">상세 확인 및 수정이 가능합니다. <span class="red">*</span>는 필수 항목입니다.</p>
	<table class="tableSt_write mB10">
		<caption></caption>
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="prmSiteCode">사이트</label></th>
				<td>
					<form:select path="prmSiteCode" data-validator="on" data-required="y" data-fieldName="사이트">
						<option value="">선택</option>
						<c:forEach var="s" items="${siteList}">
							<form:option value="${s.siteCode}">${s.siteName}</form:option>
						</c:forEach>
					</form:select>
					<form:hidden path="prmSiteName"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="prmMid">메뉴ID(사용자)</label></th>
				<td>
					<form:input path="prmMid" maxlength="10" placeholder="예) 0100000000" data-validator="on" data-required="y" data-inputType="num" data-fieldName="메뉴ID(사용자)"/>
					<span class="tip">사용자 메뉴 링크 생성을 위해 필요합니다. 연결한 메뉴ID를 입력하세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="prmSysMid">메뉴ID(관리자)</label></th>
				<td>
					<form:input path="prmSysMid" maxlength="10" placeholder="예) 0100000000" data-validator="on" data-required="y" data-inputType="num" data-fieldName="메뉴ID(관리자)"/>
					<span class="tip">관리자 메뉴 링크 생성을 위해 필요합니다. 연결한 메뉴ID를 입력하세요.</span>
				</td>
			</tr>
			<tr id="tableName_container">
				<th scope="row"><span class="red">*</span><label for="prmTableName">테이블명</label></th>
				<td>
					<form:input path="prmTableName" maxlength="50" placeholder="예) pt_target_table" data-validator="on" data-required="y" data-inputType="alpNum" data-fieldName="테이블명"/>
					<span class="tip">삭제할 데이터를 관리하는 테이블명을 입력하세요. </span>
				</td>
			</tr>
			<tr id="tmpTableName_container" style="display: none;">
				<th scope="row"><span class="red">*</span><label for="prmTableName">테이블명</label></th>
				<td>
					<input type="text" name="prmTableName" value="bbs_board" disabled="disabled"/>
					<span class="tip">구분값을 게시판으로 선택하신 경우는 테이블명을 입력하실 필요가 없습니다.</span>
				</td>
			</tr>
			<tr>
				<th scoper="row"><label for="prmTriggerCol">기준일 컬럼명</label></th>
				<td>
					<form:input path="prmTriggerCol" maxlength="50" placeholder="예) reg_dt" data-validator="on" data-inputType="alpNum" data-fieldName="기준일 컬럼명"/>
					<span class="tip">기준일 컬럼명이 <span class="red">[create_date]가 아닌 경우</span>에 입력해주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="prmParamsMap">추가 파라미터</label></th>
				<td>
					<form:textarea path="prmParamsMap" rows="5" cols="100" placeholder="예) {\"pk_idx\": 1}" data-validator="on" data-fieldName="추가 파라미터"/>
					<p class="tip">
						삭제할 데이터 제한 범위 검색에 사용할 수 있는 값을 json 형식으로 입력하세요.<br/>
						입력한 json 값으로 where 조건을 생성하며, <span class="red">생략하는 경우는 테이블 전 데이터를 대상</span>으로 합니다.
					</p>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="prmName">프로그램명</label></th>
				<td>
					<form:input path="prmName" maxlength="500" placeholder="예) 텃밭 가꾸기 신청" data-validator="on" data-required="y" data-fieldName="프로그램명"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="prmDeveloper">담당 개발자</label></th>
				<td>
					<form:input path="prmDeveloper" maxlength="100"  placeholder="예) 김아무개" data-validator="on" data-required="y" data-fieldName="담당 개발자"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="prmColNames">개인정보항목</label></th>
				<td>
					<form:input path="prmColNames" cssClass="input500" maxlength="1000" placeholder="예) 항목1,항목2,항목3,... 콤마(,)로 구분해주세요." data-validator="on" data-required="y" data-fieldName="개인정보항목"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="prmPeriod">보유 기간</label></th>
				<td>
					<form:select path="prmPeriod" data-validator="on" data-required="y" data-fieldName="보유 기간">
						<form:option value="1">1개월</form:option>
						<form:option value="3">3개월</form:option>
						<form:option value="6">6개월</form:option>
						<c:forEach var="y" begin="1" end="10" step="1">
							<form:option value="${y * 12}">${y}년</form:option>
						</c:forEach>
					</form:select>
					<p class="tip">
						설정한 기간이 지난 데이터는 <span class="red"><strong>DB에서 완전히 삭제</strong></span>됩니다. 신중하게 입력해주세요.<br/>
						해당 처리를 통해 <span class="red"><strong>삭제된 데이터는 복구할 수 없습니다.</span>
					</p>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="등록"/>
		<a href="#" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script type="text/javascript">
	yh.siteCode = "sys";
	yh.isNew = eval("${isNew}");
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/sys/js/privacy/prm/write.js"></script>
