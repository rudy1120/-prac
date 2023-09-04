<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황관리 등록/수정
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.05.03		J.Ryeon Lee		최초 생성 및 코드 수정
	 * 2016.09.02		권태성			커스텀 뷰 기능 추가
	 * 2016.09.05		권태성			목록 출력갯수 옵션 추가
	 * 2016.09.27		권태성			셀렉트 입력옵션 추가
	 * 2016.11.03		권태성			카테고리 key/value 관리 기능 추가
	 * 2016.12.16		권태성			다중 카테고리 관련 추가
	 * 2017.01.12		J.Ryeon Lee		사용자단 엑셀 다운로드 링크 토글 기능 추가
	 * 2017.01.17		권태성			정렬 옵션 추가
	 * 2017.01.18		권태성			번호 출력 옵션 추가
	 * 2017.04.05		권태성			화면 개선 / 셀렉트 종류별 옵션 추가 / 링크 타입 처리 추가
	 * 2017.04.10		권태성			카테고리 관련 옵션 통합으로 수정
	 * 2017.05.29		권태성			필드 설정에 설명글 옵션 추가
	 * @author J.Ryeon Lee
	 * @since 2016.05.03
	 */
%>
<c:set var="isNew" value="${empty searchVO.idx}" />
<script type="text/javascript">
	var isNew = ${empty searchVO.idx ? true : false};
	var mId = "${menuVO.mId}";
</script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/sys/js/dynamic/dynamicTable.js"></script>
<script type="text/javascript" src="/sys/js/dynamic/creator.js"></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		if (isNew == false) {
			$("#column_def_container").children().find("input[type!=hidden]").each(function() {
				if ($(this).attr("data-modifable") != "y") {
					$(this).attr("disabled", "disabled");
				}
			});
			$("#column_def_container").children().find("select").each(function() {
				if ($(this).attr("data-modifable") != "y") {
					$(this).attr("disabled", "disabled");
				}
			});
			var lastAIndex = $("#column_def_container").children().find("a").length - 2;
			$("#column_def_container").children().find("a").each(function(i) {
				if ($(this).attr("data-type") == "insert" && i != lastAIndex) { // hide additinal tags (except the last element)
					$(this).hide();
				}
			});
		}

		useFileToggle();
		/** toggle hide **/
		$("#accordion_view1").hide();
		$("#accordion_view2").hide();
		$("#accordion_view3").hide();
	});
//]]>
</script>

<h2>현황 관리</h2>

<form:form commandName="searchVO" name="form" id="form" methodParam="post" action="/sys/dataMng/${isNew ? 'writeProc' : 'modifyProc'}.do">
	<form:hidden path="idx" />
	<%-- 카테고리 설정값 세팅 --%>
	<div id="categoryDef">
		<c:forEach var="element" items="${searchVO.categoryDefList }" varStatus="status">
			<input type="hidden" name="categoryDefList[${status.index}].name" id="categoryDefList${status.index}.name" value="${element.name }" />
			<input type="hidden" name="categoryDefList[${status.index}].width" id="categoryDefList${status.index}.width" value="${element.width }" />
			<input type="hidden" name="categoryDefList[${status.index}].showYn" id="categoryDefList${status.index}.showYn" value="${element.showYn }" />
			<input type="hidden" name="categoryDefList[${status.index}].showListYn" id="categoryDefList${status.index}.showListYn" value="${element.showListYn }" />
			<input type="hidden" name="categoryDefList[${status.index}].showSearchYn" id="categoryDefList${status.index}.showSearchYn" value="${element.showSearchYn }" />
		</c:forEach>
	</div>
	<%-- 카테고리 key / value 세팅 --%>
	<div id="category1">
		<input type="hidden" id="category1Length" name="category1Length" value="<c:if test="${not empty searchVO.category1List}">${fn:length(searchVO.category1List)}</c:if><c:if test="${empty searchVO.category1List}">0</c:if>" />
		<c:forEach var="element" items="${searchVO.category1List}" varStatus="status">
			<input type="hidden" name="category1List[${status.index}].categoryKey" id="category1List${status.index}.categoryKey" value="${searchVO.category1List[status.index].categoryKey}" />
			<input type="hidden" name="category1List[${status.index}].categoryValue" id="category1List${status.index}.categoryValue" value="${searchVO.category1List[status.index].categoryValue}" />
			<input type="hidden" name="category1List[${status.index}].useYn" id="category1List${status.index}.useYn" value="${searchVO.category1List[status.index].useYn}" />
			<input type="hidden" name="category1List[${status.index}].categoryOrder" id="category1List${status.index}.categoryOrder" value="${searchVO.category1List[status.index].categoryOrder}" />
			<input type="hidden" name="category1List[${status.index}].categoryIdx" id="category1List${status.index}.categoryIdx" value="1" />
		</c:forEach>
	</div>
	<div id="category2">
		<input type="hidden" id="category2Length" name="category2Length" value="<c:if test="${not empty searchVO.category2List}">${fn:length(searchVO.category2List)}</c:if><c:if test="${empty searchVO.category2List}">0</c:if>" />
		<c:forEach var="element" items="${searchVO.category2List}" varStatus="status">
			<input type="hidden" name="category2List[${status.index}].categoryKey" id="category2List${status.index}.categoryKey" value="${searchVO.category2List[status.index].categoryKey}" />
			<input type="hidden" name="category2List[${status.index}].categoryValue" id="category2List${status.index}.categoryValue" value="${searchVO.category2List[status.index].categoryValue}" />
			<input type="hidden" name="category2List[${status.index}].useYn" id="category2List${status.index}.useYn" value="${searchVO.category2List[status.index].useYn}" />
			<input type="hidden" name="category2List[${status.index}].categoryOrder" id="category2List${status.index}.categoryOrder" value="${searchVO.category2List[status.index].categoryOrder}" />
			<input type="hidden" name="category2List[${status.index}].categoryIdx" id="category2List${status.index}.categoryIdx" value="2" />
		</c:forEach>
	</div>

	<div class="accordion" onclick="sectionViewToggle(0); return false;">
		<h3><span class="red">*</span> 필수 항목</h3>
	</div>
	<div class="accordion_view" id="accordion_view0">
		<table class="tableSt_write">
			<caption>항목별 입력을 제공하는 표입니다.</caption>
			<colgroup>
				<col span="1" style="width:15%"/>
				<col span="1" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><span class="red">*</span> <label for="tableDesc">현황명</label></th>
					<td>
						<form:input path="tableDesc" cssClass="input100" maxlength="300" data-validator="on" data-required="y" data-fieldName="현황명" />
						<span class="tip"><span class="red">*</span>는 필수항목입니다.</span>
					</td>
				</tr>
				<tr>
					<th scope="row"><span class="red">*</span> <label for="tableName">테이블명</label></th>
					<td>
						<c:choose>
							<c:when test="${isNew}">
								<form:input path="tableName" cssClass="input150" maxlength="20" data-validator="on" data-required="y" data-inputType="alpNum" data-fieldName="테이블명" />
								<a id="anchor_chk_tableName" href="#" onclick="fn_is_unique_tableName(); return false;" class="btn_white">중복확인</a>
								<span class="tip">※ 영숫자로 입력해주세요.</span>
							</c:when>
							<c:otherwise>
								${searchVO.tableName}
								<form:hidden path="tableName" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="accordion" onclick="sectionViewToggle(1); return false;">
		<h3>추가 설정</h3>
	</div>
	<div class="accordion_view" id="accordion_view1">
		<table class="tableSt_write">
			<caption>항목별 입력을 제공하는 표입니다.</caption>
			<colgroup>
				<col span="1" style="width:15%"/>
				<col span="1" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="useFile">첨부파일<br/>사용여부</label></th>
					<td>
						<form:select path="useFile" onchange="useFileToggle(); return false;">
							<form:option value="N">사용안함</form:option>
							<form:option value="Y">사용</form:option>
						</form:select>
					</td>
				</tr>
				<tr style="display:none;">
					<th scope="row"><label for="fileCnt">첨부파일 개수</label></th>
					<td>
						<form:input path="fileCnt" cssClass="input100" maxlength="2" data-validator="on" data-inputType="num" data-fieldName="첨부파일 개수" />
						<span class="tip">※ 지정하지 않는 경우는 기본 1개로 제한됩니다.</span>
					</td>
				</tr>
				<tr style="display:none;">
					<th scope="row"><label for="imageOnly">첨부파일<br/>파일형식</label></th>
					<td>
						<form:radiobutton path="imageOnly" value="N" label="모든 파일" checked="checked"/>&nbsp;
						<form:radiobutton path="imageOnly" value="Y" label="이미지만" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="listCnt">목록 출력 개수</label></th>
					<td>
						<form:input path="listCnt" cssClass="input50" maxlength="3" data-validator="on" data-fieldName="목록 출력 개수" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="useSecret">비공개 데이터</label></th>
					<td>
						<form:radiobutton path="useSecret" value="N" label="사용안함" checked="checked"/>&nbsp;
						<form:radiobutton path="useSecret" value="Y" label="사용함" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="fileDownYn">엑셀 다운로드<br/>링크 제공</label></th>
					<td>
						<form:radiobutton path="fileDownYn" value="N" label="사용안함" checked="checked"/>&nbsp;
						<form:radiobutton path="fileDownYn" value="Y" label="사용함" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="numYn">번호 출력</label></th>
					<td>
						<form:radiobutton path="numYn" value="Y" label="출력" checked="checked"/>&nbsp;
						<form:radiobutton path="numYn" value="N" label="미출력" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="searchYn">검색 영역 출력</label></th>
					<td>
						<form:radiobutton path="searchYn" value="Y" label="출력" checked="checked"/>&nbsp;
						<form:radiobutton path="searchYn" value="N" label="미출력" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="accordion" onclick="sectionViewToggle(2); return false;">
		<h3>기타 설정</h3>
	</div>
	<div class="accordion_view" id="accordion_view2">
		<table class="tableSt_write">
			<caption>항목별 입력을 제공하는 표입니다.</caption>
			<colgroup>
				<col span="1" style="width:15%"/>
				<col span="1" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">카테고리 설정</th>
					<td>
						<a href="#" onclick="window.open('/sys/dataMng/categoryModify.do?categoryIdx=1&tableName='+document.getElementById('tableName').value, 'pop', 'width=600, height=500, scrollbars=yes, resizable=yes'); return false;" class="btn_white">카테고리1 관리</a>
						<a href="#" onclick="window.open('/sys/dataMng/categoryModify.do?categoryIdx=2&tableName='+document.getElementById('tableName').value, 'pop', 'width=600, height=500, scrollbars=yes, resizable=yes'); return false;" class="btn_white">카테고리2 관리</a>
					</td>
				</tr>
				<%--
				<tr>
					<th scope="row"><label for="category">카테고리 이름</label></th>
					<td>
						<form:input path="category" cssClass="input500" maxlength="700" data-validator="on" data-fieldName="카테고리 이름" />
						<p class="tip">※ 쉼표(,)로 구분됩니다.</p>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="categoryWidth">카테고리 폭</label></th>
					<td>
						<form:input path="categoryWidth" cssClass="input100" maxlength="3" data-validator="on" data-inputType="num" data-fieldName="카테고리 폭" />
						<p class="tip">※ 카테고리를 사용하지 않는 경우는 입력하실 필요가 없습니다.</p>
					</td>
				</tr>
				--%>
				<tr>
					<th scope="row"><label for="listPath">커스텀 목록 뷰 경로</label></th>
					<td>
						<form:input path="listPath" cssClass="input500" maxlength="1000" data-validator="on" data-fieldName="커스텀 목록 뷰 경로" />
						<p class="tip">※ 커스텀 뷰 화면을 사용하는 경우에만 입력하세요. 특별한 경우가 아니라면 설정하지 않도록 해주세요.</p>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="viewPath">커스텀 상세 뷰 경로</label></th>
					<td>
						<form:input path="viewPath" cssClass="input500" maxlength="1000" data-validator="on" data-fieldName="커스텀 상세 뷰 경로" />
						<p class="tip">※ 커스텀 뷰 화면을 사용하는 경우에만 입력하세요.</p>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="sysListPath">관리자 커스텀<br/>목록 뷰 경로</label></th>
					<td>
						<form:input path="sysListPath" cssClass="input500" maxlength="1000" data-validator="on" data-fieldName="관리자 커스텀 목록 뷰 경로" />
						<p class="tip">※ 커스텀 뷰 화면을 사용하는 경우에만 입력하세요.</p>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="headerContent">안내 정보</label></th>
					<td>
						<form:textarea path="headerContent" maxlength="4000" rows="15" cssClass="input500" data-validator="on" data-fieldName="안내 정보" />
						<p class="tip">※ 기본으로 제공되는 안내 문구가 입력하신 정보로 대체됩니다. 기본값을 사용하는 경우 해당 항목은 입력하실 필요 없습니다.</p>
						<p>안내 정보를 입력하지 않으시는 경우는 기본적으로 메뉴 정보에 입력하신 값을 바탕으로 사용자에게 메뉴 이용 안내를 제공합니다.</p>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="orderColName">정렬 컬럼명</label></th>
					<td>
						<form:input path="orderColName" maxlength="15" data-validator="on" data-fieldName="정렬 컬럼명" />
						<p class="tip">※ 입력하지 않는 경우 idx값을 기준으로 합니다.</p>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="orderType">정렬 기준</label></th>
					<td>
						<form:radiobutton path="orderType" value="DESC" label="역방향" checked="checked"/>&nbsp;
						<form:radiobutton path="orderType" value="ASC" label="정방향" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%-- ====================== JSON DATA로 생성하는 경우(개발자들만 사용하는 옵션이므로 기본 숨김 처리) ====================== --%>
	<c:if test="${isNew && !yh:isLiveMode()}">
		<div class="accordion">
			<h3>테이블 정의</h3>
		</div>
		<table class="tableSt_write">
			<tbody>
				<tr>
					<th scope="row" class="w15">JSON</th>
					<td><textarea id="tableDefJson" name="tableDefJson" cols="200" rows="10"></textarea></td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<div class="accordion"></div>

	<h3>컬럼 정보</h3>
	<table id="column_def_table" class="tableSt_list row_over">
		<caption>항목별 입력을 제공하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width: 7%;"/>
			<col span="2" />
			<col span="4" style="width: 9%" />
			<col span="1" style="width: 8%;"/>
			<col span="1" style="width: 8%;"/>
			<col span="1" style="width: 8%;"/>
		</colgroup>
		<thead>
			<th>No.</th>
			<th>컬럼명</th>
			<th>컬럼설명</th>
			<th>데이터<br />타입</th>
			<th>길이</th>
			<th>필수</th>
			<th>표시순서</th>
			<th>컬럼폭</th>
			<th>추가 설정</th>
			<th>항목 추가</th>
		</thead>
		<tbody id="column_def_container">
			<c:forEach var="element" items="${searchVO.columnDefList}" varStatus="status">
				<tr data-idx="${status.index}">
					<td class="taC">${status.index + 1}</td>
					<td class="taC">
						<input id="columnDefList${status.index}.oldName" type="hidden" name="columnDefList[${status.index}].oldName" value="${searchVO.columnDefList[status.index].name}" />
						<form:input path="columnDefList[${status.index}].name" cssClass="input100" maxlength="100" style="ime-mode: disabled;" data-validator="on" data-required="y" data-inputType="alpNum" data-fieldName="컬럼명" />
					</td>
					<td class="taC"><form:input path="columnDefList[${status.index}].description" cssClass="input200" maxlength="100" data-validator="on" data-required="y" data-fieldName="컬럼설명" data-modifable="y" /></td>
					<td class="taC">
						<form:select path="columnDefList[${status.index}].type" onchange="disableSize(this); return false;">
							<form:option value="varchar2">문자</form:option>
							<form:option value="number">숫자</form:option>
							<form:option value="date">날짜</form:option>
							<form:option value="time">시간</form:option>
							<form:option value="clob">CLOB</form:option>
							<form:option value="file">첨부파일</form:option>
						</form:select>
					</td>
					<td class="taC"><form:input path="columnDefList[${status.index}].size" cssClass="input50" maxlength="100" data-validator="on" data-required="y" data-inputType="num" data-fieldName="길이" /></td>
					<td class="taC">
						<form:select path="columnDefList[${status.index}].nullable" data-modifable="y">
							<form:option value="N">필수</form:option>
							<form:option value="Y">선택</form:option>
						</form:select>
					</td>
					<td class="taC">
						<form:input path="columnDefList[${status.index}].order" maxlength="3" cssClass="input50" data-validator="on" data-required="y" data-inputType="num" data-fieldName="표시순서" data-modifable="y" />
					</td>
					<td class="taC">
						<form:input path="columnDefList[${status.index}].width" maxlength="3" cssClass="input50" data-validator="on" data-inputType="num" data-fieldName="컬럼폭" data-modifable="y" />
					</td>
					<td class="taC">
						<a href="#" onclick="showAddItem(this); return false;" class="btn_blue">설정</a>
					</td>
					<td class="taC">
						<a href="#" onclick="add_column_def(this); return false;" class="btn_white" data-type="insert">+</a>
						<a href="#" onclick="remove_column_def(this); return false;" class="btn_white" data-type="delete">-</a>
					</td>
				</tr>
				<tr style="display:none;">
					<td colspan="10" class="taR">
						<div>
							<span class="mR10"><b>목록표시</b></span>
							<form:select path="columnDefList[${status.index}].display" data-modifable="y">
								<form:option value="Y">표시</form:option>
								<form:option value="N">숨김</form:option>
							</form:select>
							<span class="mL20 mR10"><b>사용여부</b></span>
							<form:select path="columnDefList[${status.index}].isUse" data-modifable="y">
								<form:option value="Y">사용</form:option>
								<form:option value="N">숨김</form:option>
							</form:select>
							<span class="mL20 mR10"><b>검색여부</b></span>
							<form:select path="columnDefList[${status.index}].isSearch" data-modifable="y" data-fieldName="검색여부">
								<form:option value="Y">사용</form:option>
								<form:option value="N">숨김</form:option>
							</form:select>
							<span class="mL20 mR10"><b>컬럼정렬</b></span>
							<form:select path="columnDefList[${status.index}].align" data-modifable="y">
								<form:option value="C">가운데</form:option>
								<form:option value="L">왼쪽</form:option>
								<form:option value="R">오른쪽</form:option>
							</form:select>
							<span class="mL20 mR10"><b>셀렉트 종류</b></span>
							<form:select path="columnDefList[${status.index}].selectType" data-modifable="y">
								<form:option value="N">사용안함</form:option>
								<form:option value="S">셀렉트</form:option>
								<form:option value="R">라디오</form:option>
								<form:option value="C">체크박스</form:option>
							</form:select>
							<span class="mL20 mR10"><b>셀렉트 옵션</b></span>
							<form:input path="columnDefList[${status.index}].optionValues" cssClass="input200" maxlength="200" data-validator="on" data-required="n" data-modifable="y" data-fieldName="셀렉트 옵션" />
						</div>
						<div class="mT10">
							<span class="mR10"><b>링크속성</b></span>
							<form:select path="columnDefList[${status.index}].linkType" data-modifable="y">
								<form:option value="0">사용안함</form:option>
								<form:option value="1">상세페이지</form:option>
								<form:option value="2">URL(현재창)</form:option>
								<form:option value="3">URL(새창)</form:option>
								<form:option value="4">지도(새창)</form:option>
							</form:select>
							<span class="mL20 mR10"><b>목록에서 줄바꿈</b></span>
							<form:select path="columnDefList[${status.index}].newLine" data-modifable="y">
								<form:option value="N">사용안함</form:option>
								<form:option value="Y">사용</form:option>
							</form:select>
							<span class="mL20 mR10"><b>설명글</b></span>
							<form:input path="columnDefList[${status.index}].infoMsg" cssClass="input200" maxlength="200" data-validator="on" data-required="n" data-modifable="y" data-fieldName="설명글" />
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p class="tip mL10">※ 컬럼은 한번 등록하면 일부 항목값만 수정 가능합니다. 신중하게 생성해주세요.</p>

	<div class="btn_boxR">
		<a href="#" id="save" onclick="fn_add_proc(); return false;" class="btn_cyan">저장</a>
		<a href="/sys/dataMng/list.do?mId=${menuVO.mId}" class="btn_dblue">취소</a>
	</div>

</form:form>
