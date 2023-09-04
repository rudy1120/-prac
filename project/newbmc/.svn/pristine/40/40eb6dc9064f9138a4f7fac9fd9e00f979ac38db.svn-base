<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 등록/수정
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.09.24		김혜민			최초 생성 및 코드 수정
	 * 2017.06.15		J.Ryeon Lee		리팩토링
	 * 2017.06.21		J.Ryeon Lee		개인 정보 설정 추가
	 *
	 *
	 * @author 김혜민
	 * @since 2014.09.24
	 */
%>
<c:set var="isNew" value="${empty searchVO.ptIdx}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>
<c:set var="cancelUrl" value="/sys/${siteCode}/bbs/configMng/list.do?mId=${menuVO.mId}"/>
<c:if test="${not empty cancelMid}"><c:set var="cancelUrl" value="/sys/privacy/prm/list.do?mId=${cancelMid}"/></c:if>
<h2>게시판 등록/수정</h2>
<form:form commandName="bbsConfigVO" id="list" name="list" action="${cancelUrl}" methodParam="post">
	<form:hidden path="searchPtType"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="page"/>
</form:form>
<form:form commandName="bbsConfigVO" id="write" name="write" action="/sys/${siteCode}/bbs/configMng/${postUrl}.do?mId=${menuVO.mId}&cancelMid=${cancelMid}" onsubmit="return validator() && config.additional_validator() && confirm('등록하시겠습니까?');" methodParam="post" htmlEscape="false">
	<form:hidden path="searchPtType"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="page"/>
	<form:hidden path="ptIdx"/>
	<form:hidden path="ptLevelReply" value="YN"/><%-- 답변글 등록 레벨 --%>

	<p class="info"><span class="red">*</span>는 필수항목입니다.</p>
	<%-- =================================== 기본 설정 =================================== --%>
	<div class="accordion">
		<h3><span class="red">*</span>기본 설정</h3>
	</div>
	<div class="accordion_view">
		<table class="tableSt_write">
			<caption>게시판 등록 기본 설정</caption>
			<tbody>
				<tr>
					<%-- ##### 사이트 ##### --%>
					<th scope="row" class="w20"><span class="red">*</span><label for="ptSiteCode">사이트 선택</label></th>
					<td class="w30">
						<form:select path="ptSiteCode" data-validator="on" data-required="y" data-fieldName="사이트">
							<option value="">사이트 선택</option>
							<form:option value="common">공통</form:option>
							<c:forEach var="code" items="${siteList}" >
								<form:option value="${code.siteCode}">${code.siteName}</form:option>
							</c:forEach>
						</form:select>
						<form:hidden path="siteName"/>
					</td>
					<%-- ##### 게시판 타입 ##### --%>
					<th scope="row" class="w20"><span class="red">*</span><label for="ptType">게시판 타입</label></th>
					<td class="w30">
						<form:select path="ptType" data-validator="on" data-required="y" data-fieldName="게시판 타입">
							<option value="">게시판 타입 선택</option>
							<c:forEach var="code" items="${boardList}" >
								<form:option value="${code.codeId}">${code.codeNm}</form:option>
							</c:forEach>
						</form:select>
						<span class="tip">게시판 등록 후 수정할 수 없습니다.</span>
					</td>
				</tr>
				<%-- ##### 글쓰기 권한 ##### --%>
				<tr id="replyAuth_container">
					<th scope="row"><span class="red">*</span>글쓰기 권한</th>
					<td colspan="3">
						<form:radiobutton path="ptLevelInput" value="YN" label="관리자"/>
						<form:radiobutton path="ptLevelInput" value="YY" label="관리자&사용자"/>
						<span class="tip">게시판 등록 후 수정할 수 없습니다.</span>
					</td>
				</tr>
				<%-- ##### 답글 사용 여부 ##### --%>
				<tr id="replyYn_container">
					<th><span class="red">*</span>답글 사용 여부</th>
					<td colspan="3">
						<form:radiobutton path="ptReplyYn" value="Y" label="사용"/>
						<form:radiobutton path="ptReplyYn" value="N" label="사용 안 함"/>
					</td>
				</tr>
				<%-- ##### 비밀글 사용 여부 ##### --%>
				<tr id="publicYn_container">
					<th scope="row"><span class="red">*</span>비밀글 사용</th>
					<td colspan="3">
						<form:radiobutton path="ptPublicYn" value="Y" label="사용"/>
						<form:radiobutton path="ptPublicYn" value="N" label="사용 안 함"/>
						<span class="tip">게시글 작성시 공개/비공개 여부를 선택할 수 있습니다.</span>
					</td>
				</tr>
				<tr id="publicYnTip_container" style="display:none;">
					<th scope="row"><span class="red">*</span>비밀글 사용</th>
					<td colspan="3">
						<span class="tip red">민원형 게시글은 전체 비공개로 작성됩니다.</span>
					</td>
				</tr>
				<%-- ##### 파일 첨부 허용 개수 ##### --%>
				<tr id="fileCnt_container">
					<th scope="row">파일 첨부 허용 개수</th>
					<td colspan="3">
						<form:input path="ptFileCnt" cssClass="input50" maxlength="2" placeholder="예) 10" data-validator="on" data-inputType="num" data-fieldName="파일 첨부 허용 개수"/>
						<span class="tip">포토/동영상 게시판은 0개 입력을 지원하지 않습니다.</span>
					</td>
				</tr>
				<%-- ##### 파일 용량 설정 ##### --%>
				<tr>
					<th scope="row">파일 업로드 용량</th>
					<td colspan="3">
						<form:input path="ptLimitFileSize" cssClass="input50" maxlength="2" placeholder="예) 10" data-validator="on" data-inputType="num" data-fieldName="파일 업로드 용량"/>
						<span class="tip">첨부 파일당 최대 용량을 설정합니다. 미입력시 10MB로 설정됩니다.</span>
					</td>
				</tr>
				<%-- ##### 코멘트 작성 여부 ##### --%>
				<tr id="commentYn_container">
					<th scope="row"><span class="red">*</span>코멘트 작성 여부</th>
					<td colspan="3">
						<form:radiobutton id="ptCommentY" path="ptCommentYn" value="Y" label="사용"/>
						<form:radiobutton id="ptCommentN" path="ptCommentYn" value="N" label="사용 안 함"/>
					</td>
				</tr>
				<%-- ##### 컨텐츠 메뉴 여부 ##### --%>
				<tr>
					<th scope="row"><span class="red">*</span>컨텐츠 메뉴 여부</th>
					<td colspan="3">
						<form:radiobutton id="ptIsolateY" path="ptIsolateYn" value="Y" label="사용"/>
						<form:radiobutton id="ptIsolateN" path="ptIsolateYn" value="N" label="사용 안 함"/>
						<span class="tip">메뉴 관리에서 게시판을 연결하지 않고 컨텐츠 안내 페이지를 경유하는 경우</span>
					</td>
				</tr>
				<%-- ##### 자기글만 목록 노출 여부 ##### --%>
				<tr id="reportYn_container">
					<th scope="row"><span class="red">*</span><label for="ptReportY">자기글만 보기</label></th>
					<td colspan="3">
						<form:radiobutton id="ptReportY" path="ptReportYn" value="Y" label="사용"/>
						<form:radiobutton id="ptReportN" path="ptReportYn" value="N" label="사용 안 함"/>
						<span class="tip">본인인증을 마친 사용자 본인이 작성한 글만 목록에 노출됩니다.</span>
					</td>
				</tr>
				<%-- ##### RSS 제공 여부 ##### --%>
				<tr id="rssYn_container">
					<th scope="row"><span class="red">*</span><label for="ptRssYn">RSS 제공</label></th>
					<td colspan="3">
						<form:radiobutton id="ptRssY" path="ptRssYn" value="Y" label="사용"/>
						<form:radiobutton id="ptRssN" path="ptRssYn" value="N" label="사용 안 함"/>
						<span class="tip">RSS 제공시 <span class="red">모든 글 목록이 해당 링크를 통해 외부에 노출</span>됩니다. 신중하게 선택해주세요.</span>
					</td>
				</tr>
				<%-- ##### 게시글 일괄 삭제 옵션 ##### --%>
				<tr>
					<th scope="row"><span class="red">*</span><label for="ptBundleDelYn">게시글 일괄 삭제 옵션</label></th>
					<td colspan="3">
						<form:radiobutton id="ptBundleDelY" path="ptBundleDelYn" value="Y" label="사용"/>
						<form:radiobutton id="ptBundleDelN" path="ptBundleDelYn" value="N" label="사용 안 함"/>
						<span class="tip">관리자 화면에서 복수의 게시글을 일괄 삭제할 수 있습니다.</span>
					</td>
				</tr>
				<%-- ##### 게시글 게시기간 사용 옵션 ##### --%>
				<tr id="termYn_container">
					<th scope="row"><span class="red">*</span><label for="ptTermYn">게시글 게시기간 사용 옵션</label></th>
					<td colspan="3">
						<form:radiobutton id="ptTermY" path="ptTermYn" value="Y" label="사용"/>
						<form:radiobutton id="ptTermN" path="ptTermYn" value="N" label="사용 안 함"/>
						<span class="tip">게시글 작성시 게시기간을 사용할 수 있습니다.</span>
					</td>
				</tr>
				<tr id="termYnTip_container" style="display:none;">
					<th scope="row"><span class="red">*</span>게시글 게시기간 사용 옵션</th>
					<td colspan="3">
						<span class="tip red">사용자 글쓰기 가능시 게시글 게시기간 옵션 사용이 제한 됩니다.</span>
					</td>
				</tr>
				<%-- ##### 공지글 게시기간 사용 옵션 ##### --%>
				<tr id="ntermYn_container">
					<th scope="row"><span class="red">*</span><label for="ptNtermYn">공지글 게시기간 사용 옵션</label></th>
					<td colspan="3">
						<form:radiobutton id="ptNtermY" path="ptNtermYn" value="Y" label="사용"/>
						<form:radiobutton id="ptNtermN" path="ptNtermYn" value="N" label="사용 안 함"/>
						<span class="tip">공지글 작성시 게시기간을 사용할 수 있습니다.</span>
					</td>
				</tr>
				<tr id="ntermYnTip_container" style="display:none;">
					<th scope="row"><span class="red">*</span>공지글 게시기간 사용 옵션</th>
					<td colspan="3">
						<span class="tip red">사용자 글쓰기 가능시 공지글 게시기간 옵션 사용이 제한 됩니다.</span>
					</td>
				</tr>
				<%-- ##### 게시판명 ##### --%>
				<tr>
					<th scope="row"><span class="red">*</span><label for="ptTitle">게시판명</label></th>
					<td>
						<form:input path="ptTitle" cssClass="input99" placeholder="예) 대표포털 > 전자민원 > 민원서식" maxlength="100" data-validator="on" data-required="y" data-fieldName="게시판명"/>
					</td>
				</tr>
				<%-- ##### 게시판 이용 안내 머리글 ##### --%>
				<tr>
					<th scope="row"><label for="ptTopText">게시판 이용 안내 머리글</label></th>
					<td colspan="3">
						<form:textarea path="ptTopText" rows="5" cssClass="input99" maxlength="4000" data-validator="on" data-fieldName="게시판 이용 안내 머리글"/>
						<p class="tip">개행코드 포함 최대 4000byte까지 입력 가능합니다.</p>
					</td>
				</tr>
				<%-- ##### 카테고리 ##### --%>
				<tr>
					<th scope="row"><span class="red">*</span>카테고리</th>
					<td colspan="3">
						<form:radiobutton path="ptCategoryYn" value="Y" label="사용"/>
						<form:radiobutton path="ptCategoryYn" value="N" label="사용 안 함"/>
						<span class="tip red" style="display: none;">공통 사이트는 카테고리 필수입니다. 사이트를 다시 선택해주세요.</span>
					</td>
				</tr>
				<tr id="category_container" style="display: none;">
					<th scope="row"><span class="red">*</span>카테고리 타입/목록</th>
					<td colspan="3">
						<form:radiobutton path="ptCategoryGubun" value="A" label="부서&커스텀"/>
						<form:radiobutton path="ptCategoryGubun" value="B" label="부서"/>
						<form:radiobutton path="ptCategoryGubun" value="W" label="커스텀"/>
						<span class="tip">커스텀 카테고리 목록은 <span class="red">엔터(줄바꿈)로 항목이 분류</span>됩니다.</span>
						<div style="margin-top:10px; display: none;">
							<form:textarea path="ptCategory" rows="10" cssClass="input99" maxlength="4000" data-validator="on" data-fieldName="카테고리명" placeholder="예) 카테고리1&#13;&#10;카테고리2&#13;&#10;카테고리3" htmlEscape="false"/>
						</div>
					</td>
				</tr>
				<%-- ##### 페이지당 게시글 출력 건수 ##### --%>
				<tr>
					<th scope="row"><label for="ptPageSize">페이지당 게시글 출력 건수</label></th>
					<td colspan="3">
						<form:input path="ptPageSize" cssClass="input50" maxlength="3" placeholder="예) 10" data-validator="on" data-inputType="num" data-fieldName="페이지당 게시글 출력 건수"/>
						<span class="tip">해당 옵션은 <span class="red">사용자에만 적용</span>되며, 미입력시 기본 10건을 한 페이지에 출력합니다.</span>
					</td>
				</tr>
				<%-- ##### 상세뷰 게시글 목록 제공 여부/이미지 프리뷰 제공 여부 ##### --%>
				<tr id="viewOpt_container">
					<th scope="row">게시글 상세 페이지 출력 옵션</th>
					<td colspan="3">
						<form:radiobutton path="ptOutFields" value="NN" label="사용 안 함"/>
						<form:radiobutton path="ptOutFields" value="YY" label="게시글 목록&이미지 파일 미리보기"/>
						<form:radiobutton path="ptOutFields" value="YN" label="게시글 목록"/>
						<form:radiobutton path="ptOutFields" value="NY" label="이미지 파일 미리보기"/>
					</td>
				</tr>
				<%-- ##### 게시글 제목 길이 제한 ##### --%>
				<tr>
					<th scope="row">게시글 제목 길이 제한</th>
					<td colspan="3">
						<form:input path="ptLimitTitLen" cssClass="input50" maxlength="3" placeholder="예) 10" data-validator="on" data-inputType="num" data-max="500" data-fieldName="게시글 제목 길이 제한"/> Byte(한글 2Byte, 영어 1Byte)
						<span class="tip">미입력시 200byte로 설정됩니다.</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%-- =================================== 개인 정보 설정 =================================== --%>
	<div class="accordion" style="display: none;">
		<h3><span class="red">*</span>개인 정보 수집 및 관리 설정</h3>
	</div>
	<div id="privacy_setting_container" class="accordion_view" style="display: none;">
		<table class="tableSt_write">
			<caption>개인 정보 수집 및 관리 설정</caption>
			<tbody>
				<%-- ##### 수집 목적 ##### --%>
				<tr>
					<th scope="row" class="w20"><label for="ptSavePurpose">수집 목적</label></th>
					<td>
						<form:input path="ptSavePurpose" cssClass="input99" placeholder="예) 신청 접수 서비스 제공" maxlength="500" data-validator="on" data-fieldName="수집 목적"/>
						<p class="tip">미입력시 기본값(민원 접수 처리 및 사후 관리 서비스 제공)이 표시됩니다.</p>
					</td>
				</tr>
				<%-- ##### 수집 대상 ##### --%>
				<tr>
					<th scope="row"><span class="red">*</span><label for="ptSaveColList">필수 수집 대상</label></th>
					<td>
						<form:checkbox path="ptSaveColList" value="name" checked="checked" disabled="true" label="이름"/>
						<c:forEach var="i" begin="4" end="${fn:length(colTypeList) - 1}" step="1">
							<form:checkbox path="ptSaveColList" value="${colTypeList[i].code}" label="${colTypeList[i].name}"/>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="ptSaveOptColList">부가 수집 대상</label></th>
					<td>
						<c:forEach var="i" begin="4" end="${fn:length(colTypeList) - 1}" step="1">
							<form:checkbox path="ptSaveOptColList" value="${colTypeList[i].code}" label="${colTypeList[i].name}"/>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th scope="row"><span class="red">*</span><label for="ptSaveAutoColList">자동 수집 대상</label></th>
					<td>
						<input type="checkbox" id="ptSaveAutoCol1" name="ptSaveAutoColList" value="ip" checked="checked" disabled="true"/><label for="">IP(Internet Protocol) 주소</label>
						<input type="checkbox" id="ptSaveAutoCol2" name="ptSaveAutoColList" value="log" checked="checked" disabled="true"/><label for="">이용 내역 기록</label>
					</td>
				</tr>
				<%-- ##### 보유 기간 ##### --%>
				<tr>
					<th scope="row"><span class="red">*</span><label for="ptSavePeriod">보유 기간</label></th>
					<td>
						<form:select path="ptSavePeriod" data-validator="on" data-required="y" data-fieldName="보유 기간">
							<form:option value="1">1개월</form:option>
							<form:option value="3">3개월</form:option>
							<form:option value="6">6개월</form:option>
							<c:forEach var="y" begin="1" end="10" step="1">
								<form:option value="${y * 12}">${y}년</form:option>
							</c:forEach>
						</form:select>
						<p class="tip">
							게시글 작성 후 설정한 보유 기간이 지나면 <span class="red">자동으로 게시글이 DB에서 완전히 삭제</span>됩니다.<br/>
							해당 처리를 통해 <span class="red">삭제된 데이터는 복구할 수 없습니다.</span> 신중하게 설정해주세요.
						</p>
					</td>
				</tr>
				<%-- ##### 수집 동의 문구 ##### --%>
				<tr>
					<th scope="row"><label for="ptSaveGuide">개인 정보<br/>수집 동의 문구</label></th>
					<td>
						<div id="guide_preview" class="bod_privacy mB10">
							<div class="cont">
								<p class="privacy_tit">개인 정보 수집 및 이용 안내</p>
								<ul id="guide_conts_container" class="list_ul_h4">
									<li id="li_ptSavePurpose"><strong>개인 정보 수집&middot;이용 목적</strong> : 민원 접수&middot;처리&middot;사후 관리 서비스 제공</li>
									<li><strong>개인 정보 수집 대상</strong>
										<ul>
											<li id="li_ptSaveCols">필수 항목 : 이름</li>
											<li id="li_ptSaveOptCols">부가 항목 : 주소, 전화번호</li>
											<li>자동 수집 항목 : 개인 식별값, IP(Internet Protocol) 주소, 이용 내용 기록</li>
										</ul>
									</li>
									<li id="li_ptSavePeriod">개인 정보의 보유 및 이용 기간 : 10년</li>
									<li><p class="red bold">동의를 거부할 권리가 있으나 동의하지 않을 시 서비스 이용에 제한이 있습니다.</p></li>
								</ul>
							</div>
						</div>
						<form:textarea path="ptSaveGuide" cols="100" rows="5" maxlength="4000" placeholder="예) 해당 게시판 이용에 따른 이용 안내" data-validator="on" data-fieldName="개인 정보 수집 동의 문구" htmlEscape="false"/>
						<p class="tip">[개인 정보 수집 및 이용 안내]에 추가할 내용이 있는 경우에만 입력해주세요.</p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%-- =================================== 권한 설정 =================================== --%>
	<div class="accordion">
		<h3>권한 설정</h3>
	</div>
	<div class="accordion_view">
		<table class="tableSt_write">
			<caption>권한 설정</caption>
			<tbody>
				<%-- <tr>
					##### 담당자 ID #####
					<th scope="row" class="w20"><label for="ptMngIds">담당자ID</label></th>
					<td>
						<form:select path="ptMngIds" multiple="true" cssClass="input500" data-validator="on" htmlEscape="false">
							<c:forEach var="staff" items="${staffInfoList}">
								<option value="${staff.usrId}" ${fn:contains(bbsConfigVO.ptMngIds, staff.usrId) ? 'selected' : ''}>${staff.usrNm}[${staff.realUseDepNm}]</option>
							</c:forEach>
						</form:select>
						<p class="tip">콤마(,)로 구분합니다. e.g. id1,id2,id3 </p>
					</td>
				</tr> --%>
				<tr>
					<%-- ##### 부서 ID ##### --%>
					<th scope="row" class="w20"><label for="ptMngDeptIds">부서ID</label></th>
					<td>
						<form:select path="ptMngDeptIds" multiple="true" cssClass="input500" data-validator="on" htmlEscape="false">
							<c:forEach var="dept" items="${deptInfoList}">
								<option value="${dept.deptId}" ${fn:contains(bbsConfigVO.ptMngDeptIds, dept.deptId) ? 'selected' : ''}>${dept.deptName}</option>
							</c:forEach>
						</form:select>
						<p class="tip">콤마(,)로 구분합니다. e.g. id1,id2,id3 </p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%-- =================================== 기타 설정 =================================== --%>
	<div class="accordion">
		<h3>기타 설정</h3>
	</div>
	<div class="accordion_view">
		<table class="tableSt_write">
			<caption>기타 설정</caption>
			<tbody>
				<%-- ##### 규제 단어 ##### --%>
				<tr>
					<th scope="row" class="w20"><label for="ptCheckWord">규제 단어</label></th>
					<td>
						<form:input path="ptCheckWord" cssClass="input99" maxlength="4000" data-validator="on" data-fieldName="규제 단어"/>
						<p class="tip">구분은 ';'으로 합니다. e.g. 광고;Sex;</p>
					</td>
				</tr>
				<%-- ##### 접근불가 IP ##### --%>
				<tr>
					<th scope="row"><label for="ptCheckIp">접근불가 IP</label></th>
					<td>
						<form:input path="ptCheckIp" id="ptCheckIp" cssClass="input99" maxlength="4000" data-validator="on"/>
						<p class="tip">구분은 ';'으로 합니다. e.g. 111.111.11.11;222.22.222.22;</p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="accordion"></div>
	<div class="accordion_view"></div>

	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장"/>
		<a href="#" onclick="document.list.submit(); return false;" class="btn_dblue">취소</a>
	</div>

</form:form>

<script type="text/javascript">
	yh.isNew = eval("${isNew}");
	yh.ptCheckWord = "${bbsConfigVO.ptCheckWord}";
	yh.privacyCols = "${bbsConfigVO.ptSaveCols}" ? "${bbsConfigVO.ptSaveCols}".split(",") : null;
	yh.optPrivacyCols = "${bbsConfigVO.ptSaveOptCols}" ? "${bbsConfigVO.ptSaveOptCols}".split(",") : null;
	yh.limitFileSize = '${bbsConfigVO.ptLimitFileSize}';
</script>
<script type="text/javascript" src="/sys/js/bbs/config.js"></script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
