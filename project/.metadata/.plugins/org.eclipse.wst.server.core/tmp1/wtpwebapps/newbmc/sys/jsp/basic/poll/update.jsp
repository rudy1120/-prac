<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.08.02		권태성			최초 생성 및 코드 수정
	 *
	 *
	 * @author 권태성
	 * @since 2017.08.02
	 */
%>

<h2>${menuVO.menuName}</h2>
<form name="list" id="list" method="post" action="${_context}/sys/pollMng/list.do?mId=${menuVO.mId}">
	<input type="hidden" name="page" value="${searchVO.page}" />
	<input type="hidden" name="searchSiteCode" value="${searchVO.searchSiteCode}" />
	<input type="hidden" name="searchState" value="${searchVO.searchState}" />
	<input type="hidden" name="searchViewYn" value="${searchVO.searchViewYn}" />
	<input type="hidden" name="searchTxt" value="${searchVO.searchTxt}" />
</form>
<form:form commandName="searchVO" name="write" id="write" method="post" action="${_context}/sys/pollMng/${not empty isNew ? 'writeProc' : 'updateProc'}.do?mId=${menuVO.mId}" enctype="multipart/form-data" onsubmit="return pollMng.validProc();">
	<form:hidden path="idx" />
	<form:hidden path="page" />

	<table class="tableSt_write">
		<caption>항목별 입력을 제공하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width:15%"/>
			<col span="1" style="width:15%"/>
			<col span="1" style="width:15%"/>
			<col span="1" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="title"><span class="red">*</span> 설문조사명</label></th>
				<td colspan="3">
					<form:input path="title" cssClass="input500" maxlength="500" data-validator="on" data-required="y" data-fieldName="설문조사명" />
					<p><span class="red"><form:errors path="title" /></span></p>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="startDate"><span class="red">*</span> 설문기간</label></th>
				<td colspan="3">
					<form:input path="startDate" cssClass="input100" readonly="true" maxlength="100" data-date="y" data-ismindatefor="endDate" data-validator="on" data-required="y" data-inputType="date" data-fieldName="설문 시작 기간" />
					~
					<form:input path="endDate" cssClass="input100" readonly="true" maxlength="100" data-date="y" data-ismaxdatefor="startDate" data-validator="on" data-required="y" data-inputType="date" data-fieldName="설문 종료 기간" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="siteCode"><span class="red">*</span>사이트 선택</label></th>
				<td colspan="3">
					<form:select path="siteCode" cssClass="input200" data-validator="on" data-required="y" data-fieldName="사이트 선택">
						<form:option value="">사이트 선택</form:option>
						<c:forEach var="site" items="${siteList }">
							<form:option value="${site.siteCode }">${site.siteName }</form:option>
						</c:forEach>
					</form:select>
					<p><span class="red"><form:errors path="siteCode" /></span></p>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="dupType"><span class="red">*</span>중복투표</label></th>
				<td colspan="3">
					<form:select path="dupType" cssClass="input200" data-validator="on" data-required="y" data-fieldName="중복투표">
						<form:option value="1">중복 투표 가능</form:option>
						<form:option value="2">IP 체크</form:option>
						<form:option value="3">쿠키 체크</form:option>
						<form:option value="4">본인 인증 체크</form:option>
					</form:select>
					<p><span class="red">중복투표 방식은 수정할 수 없습니다.</span></p>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="joinTarget"><span class="red">*</span>참여대상</label></th>
				<td colspan="3">
					<form:input path="joinTarget" cssClass="input500" maxlength="500" data-validator="on" data-required="y" data-fieldName="참여대상" />
					<p><span class="red"><form:errors path="joinTarget" /></span></p>
					<p><span class="red">※ 예 ) ㅇㅇ시민</span></p>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="joinGender">참여대상<br/>상세 설정</label></th>
				<td colspan="3">
					<strong>성별</strong>
					<form:select path="joinGender" cssClass="input100" data-validator="on" data-required="n" data-fieldName="성별">
						<form:option value="A">전체</form:option>
						<form:option value="M">남성</form:option>
						<form:option value="W">여성</form:option>
					</form:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<strong>연령</strong>
					<form:radiobutton path="joinAgeYn" value="N" checked="checked" /><label for="joinAgeYn1">&nbsp;전체</label>
					<form:radiobutton path="joinAgeYn" value="Y" /><label for="joinAgeYn2">&nbsp;기간선택</label>
					<form:input path="joinStAge" cssClass="input50" readonly="true" maxlength="4" data-validator="on" data-required="n" data-inputType="num" data-fieldName="참여 대상 시작 연령" />
					~
					<form:input path="joinEdAge" cssClass="input50" readonly="true" maxlength="4" data-validator="on" data-required="n" data-inputType="num" data-fieldName="참여 대상 종료 연령" />
					<span class="tip"></span>연령 작성 예) 1960 ~ 1980
					<span class="red"><form:errors path="joinStAge" /><form:errors path="joinEdAge" /></span>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span> 참여자 추첨 설정</th>
				<td>
					<form:radiobutton path="lotteryYn" value="N" /><label for="lotteryYn1">미사용</label>
					<form:radiobutton path="lotteryYn" value="Y" /><label for="lotteryYn2">사용</label>
				</td>
				<th scope="row"><label for="lotteryNum">추첨인원 설정</label></th>
				<td>
					<form:input path="lotteryNum" cssClass="input50" readonly="true" maxlength="4" data-validator="on" data-required="n" data-inputType="num" data-fieldName="추첨인원" />
					<p><span class="red"><form:errors path="lotteryNum" /></span></p>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span> 설문결과 공개여부</th>
				<td colspan="3">
					<form:radiobutton path="publicYn" value="N" /><label for="publicYn1">비공개</label>
					<form:radiobutton path="publicYn" value="Y" /><label for="publicYn2">공개</label>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="header">머릿말</label></th>
				<td colspan="3">
					<form:textarea path="header" cols="80" rows="5" maxlength="4000" data-validator="on" data-required="n" data-fieldName="머릿말" />
					<p><span class="red"><form:errors path="header" /></span></p>
				</td>
			</tr>
			<tr>
				<th scope="row">머릿말 첨부파일</th>
				<td colspan="3" data-fileName="${searchVO.headerFile}">
					<c:if test="${empty isNew }">
						<input type="hidden" name="headerFile" value="${searchVO.headerFile}" />
						<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${searchVO.headerFile}" />
							<c:param name="param_showThumbnail" value="${true}" />
							<c:param name="param_thumbWidth" value="200" />
							<c:param name="param_thumbHeight" value="200" />
							<c:param name="downloadYn" value="Y"/>
							<c:param name="param_showComment" value="${true}" />
							<c:param name="fileCnId" value="headerFileCn" />
							<c:param name="fileCnName" value="headerFileCn" />
						</c:import>
					</c:if>
					<c:if test="${not empty isNew or empty searchVO.headerFile }">
						<input type="file" id="header_file" name="header_file" data-validator="on" data-fieldName="머릿말 첨부파일" class="input300 mB10" value="" />
						<div class="pT5"><label for="headerFileCn">설명글</label>: <input id="headerFileCn" type="text" name="headerFileCn" value="" class="input400" /></div>
						<p class="tip">※ 접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요(확장자 jpg, gif, png, bmp 파일)</p>
						<p class="tip">* 머릿말 이미지는 783 x 522 사이즈를 권장합니다.</p>
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="footer">꼬릿말</label></th>
				<td colspan="3">
					<form:textarea path="footer" cols="80" rows="5" maxlength="4000" data-validator="on" data-required="n" data-fieldName="꼬릿말" />
					<p><span class="red"><form:errors path="footer" /></span></p>
				</td>
			</tr>
			<tr>
				<th scope="row">대표 이미지</th>
				<td colspan="3" data-fileName="${searchVO.mainFile}">
					<c:if test="${empty isNew }">
						<input type="hidden" name="mainFile" value="${searchVO.mainFile}" />
						<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${searchVO.mainFile}" />
							<c:param name="param_showThumbnail" value="${true}" />
							<c:param name="param_thumbWidth" value="200" />
							<c:param name="param_thumbHeight" value="200" />
							<c:param name="downloadYn" value="Y"/>
							<c:param name="param_showComment" value="${true}" />
							<c:param name="fileCnId" value="mainFileCn" />
							<c:param name="fileCnName" value="mainFileCn" />
						</c:import>
					</c:if>
					<c:if test="${not empty isNew or empty searchVO.mainFile }">
						<input type="file" id="main_file" name="main_file" data-validator="on" data-fieldName="머릿말 첨부파일" class="input300 mB10" value="" />
						<div class="pT5"><label for="mainFileCn">설명글</label>: <input id="mainFileCn" type="text" name="mainFileCn" value="" class="input400" /></div>
					</c:if>
					<div>
						<p>* 설문조사 리스트 화면에 보여질 이미지를 등록해 주세요.</p>
						<p>* 대표 이미지는 314 x 245 사이즈를 권장합니다.</p>
						<p>※ 이미지 파일 설명글 작성 방법 (시각장애인의 웹 접근성 확보)</p>
						<p>- 사진 : 사진의 내용을 자세히 묘사 (예시. 청사 정문에 활짝 핀 매화 꽃 사진입니다.)</p>
						<p>- 리플릿 및 포스터 : 리플릿 등에 기재되어 있는 모든 내용(제목, 기간, 장소, 내용, 문의전화 등)을 한글로 작성</p>
						<p>※ 파일은 최대 1개까지 첨부하실 수 있습니다. 첨부 가능한 하나의 파일 사이즈는 최대 100Mbyte 입니다.</p>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span> 설문 상태</th>
				<td colspan="3">
					<form:radiobutton path="stateYn" value="N" /><label for="stateYn1">중단</label>
					<form:radiobutton path="stateYn" value="Y" checked="checked" /><label for="stateYn2">진행</label>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span> 사용자 화면 노출여부</th>
				<td colspan="3">
					<form:radiobutton path="viewYn" value="N" /><label for="viewYn1">미노출</label>
					<form:radiobutton path="viewYn" value="Y" /><label for="viewYn2">노출</label>
				</td>
			</tr>
			<c:if test="${isNew eq 'Y'}">
				<tr>
					<th scope="row"><span class="red">*</span> 질문 등록 방법 선택</th>
					<td colspan="3">
						<form:radiobutton path="questionYn" value="N" /><label for="questionYn1">신규 질문 설정</label>
						<form:radiobutton path="questionYn" value="Y" /><label for="questionYn2">기존 질문 불러오기</label>
						<select id="beforeIdx" name="beforeIdx" style="display:none;">
							<option value="">설문 선택</option>
							<c:forEach var="poll" items="${pollList}" varStatus="pollStatus">
								<option value="${poll.idx}">${poll.title}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" id="submitBtn" class="btn_cyan" value="저장" />
		<a href="#" id="backBtn" class="btn_dblue" data-action="${_context}/sys/pollMng/list.do?mId=${menuVO.mId}" data-formname="list" data-formserialize="y" onclick="req.post(this); return false;">취소</a>
	</div>

</form:form>

<script type="text/javascript" src="${_context}/common/js/common/require.js"></script>
<script type="text/javascript" src="${_context}/sys/js/basic/pollMng/pollMng.js"></script>
<script type="text/javascript">
pollMng.mode = "${isNew eq 'Y' ? 'new' : 'update'}";
</script>
