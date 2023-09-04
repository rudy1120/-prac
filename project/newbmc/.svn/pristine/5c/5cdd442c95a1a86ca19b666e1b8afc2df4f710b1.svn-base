<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 사전 정보 공표
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.31		권태성			최초 생성 및 코드 수정
	 *
	 *
	 * @author 권태성
	 * @since 2017.05.31
	 */
%>

<h2>공표 내용</h2>
<form name="list" id="list" method="post" action="${pageContext.request.contextPath}/sys/advanceInfoMng/list.do?mId=${menuVO.mId}">
	<input type="hidden" name="page" value="${searchVO.page}" />
	<input type="hidden" name="searchType" value="${searchVO.searchType}" />
	<input type="hidden" name="searchTxt" value="${searchVO.searchTxt}" />
</form>
<form:form commandName="searchVO" name="write" id="write" method="post" action="${pageContext.request.contextPath}/sys/advanceInfoMng/${not empty isNew ? 'writeProc' : 'updateProc'}.do?mId=${menuVO.mId}" enctype="multipart/form-data">
	<input type="hidden" name="page" value="${searchVO.page}" />
	<input type="hidden" name="idx" value="${searchVO.idx }" />

	<table class="tableSt_write">
		<caption>항목별 입력을 제공하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width:15%"/>
			<col span="1" />
			<col span="1" style="width:15%"/>
			<col span="1" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="title"><span class="red">*</span> 공표 목록</label></th>
				<td colspan="3">
					<form:input path="title" cssClass="input99" maxlength="100" data-validator="on" data-required="y" data-fieldName="공표 목록" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="brm1"><span class="red">*</span> BRM 단계</label></th>
				<td colspan="3">
					<select id="brm1" name="brm1" class="input150" data-validator="on" data-required="y" data-fieldName="BRM 1단계" onchange="brmChange(1, this, false); return false;">
						<c:if test="${empty brm1List }">
							<option value="">BRM을 먼저 등록하세요</option>
						</c:if>
						<c:if test="${not empty brm1List }">
							<option value="">선택하세요</option>
							<c:forEach var="brm1" items="${brm1List }">
								<option value="${brm1.brmIdx },${brm1.name }" ${searchVO.brm1 eq brm1.brmIdx ? 'selected="selected"' : ''}>${brm1.name }</option>
							</c:forEach>
						</c:if>
					</select>
					<c:if test="${conf.brmDepth eq '2' or conf.brmDepth eq '3' }">
						<select id="brm2" name="brm2" class="input150" data-validator="on" data-required="y" data-fieldName="BRM 2단계" onchange="brmChange(2, this, false); return false;">
							<option>BRM 1단계를 먼저 선택하세요</option>
						</select>
					</c:if>
					<c:if test="${conf.brmDepth eq '3' }">
						<select id="brm3" name="brm3" class="input150" data-validator="on" data-required="y" data-fieldName="BRM 3단계">
							<option>BRM 2단계를 먼저 선택하세요</option>
						</select>
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="method"><span class="red">*</span> 관련 기능</label></th>
				<td colspan="3">
					<form:select path="method" cssClass="input150" data-validator="on" data-required="y" data-fieldName="관련 기능">
						<form:option value="">선택</form:option>
						<c:forEach var="bigo" items="${bigoList }" varStatus="sts">
							<option value="${bigo.code },${bigo.codeName }" ${searchVO.method eq bigo.code ? 'selected="selected"' : ''}>${bigo.codeName }</option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="item"><span class="red">*</span> 항목</label></th>
				<td colspan="3">
					<form:input path="item" cssClass="input99" maxlength="100" data-validator="on" data-required="y" data-fieldName="공표 목록" />
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span> 공표 부서</th>
				<td colspan="3">
					<c:set  var="deptCodeList" value="${fn:split(searchVO.deptCode,',') }" />
					<select id="depts" multiple name="depts" class="input900">
						<c:forEach var="dept" items="${deptList}" varStatus="sts">
							<option value="${dept.deptId },${fn:replace(dept.deptName, ' ', '&nbsp;')}" ${fn:contains(searchVO.deptCode, dept.deptId) ? 'selected="selected"' : '' }>${fn:replace(dept.deptName, ' ', '&nbsp;')}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="period">공표 주기</label></th>
				<td>
					<form:input path="period" cssClass="input200" maxlength="200" data-validator="on" data-required="n" data-fieldName="공표 주기" />
				</td>
				<th scope="row"><label for="moment">공표 시기</label></th>
				<td>
					<form:input path="moment" cssClass="input200" maxlength="200" data-validator="on" data-required="n" data-fieldName="공표 시기" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="respon">담당자</label></th>
				<td>
					<form:input path="respon" cssClass="input200" maxlength="200" data-validator="on" data-required="n" data-fieldName="담당자" />
				</td>
				<th scope="row"><label for="tel">연락처</label></th>
				<td>
					<form:input path="tel" cssClass="input200" maxlength="200" data-validator="on" data-required="n" data-inputType="phone" data-fieldName="연락처" />
				</td>
			</tr>
		</tbody>
	</table>

	<h2>공표 자료</h2>
	<div class="tab mT10" id="typeTab">
		<input type="hidden" id="type" name="type" value="${empty searchVO.type ? '0' : searchVO.type }" />
		<input type="hidden" id="rmIdx" name="rmIdx" value="" />

		<ul>
			<li><a href="#" ${searchVO.type eq '0' or isNew eq 'Y' ? 'class="on"' : ''} data-value="0">파일</a></li>
			<li><a href="#" ${searchVO.type eq '1' ? 'class="on"' : ''} data-value="1">링크</a></li>
		</ul>
		<div class="cleB"></div>
	</div>

	<div class="mL10" id="infoTxt">
		<p class="tip">공표 자료가 ${searchVO.type eq '0' or isNew eq 'Y' ? '파일' : '링크'}인 경우에 추가해주세요.</p>
	</div>

	<table class="tableSt_list row_over mT10" id="dataFileTable" ${searchVO.type eq '1' ? 'style="display:none;"' : ''}>
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="w10">번호</th>
				<th scope="col" class="w10">공개 정보</th>
				<th scope="col" class="w10">파일</th>
				<th scope="col" class="w10">등록 부서</th>
				<th scope="col" class="w10">등록일</th>
				<th scope="col" class="w10">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty dataList }">
				<c:forEach var="data" items="${dataList }" varStatus="sts">
					<tr>
						<td>
							${sts.count }
							<c:if test="${isNew ne 'Y' and searchVO.type eq '0' }">
								<input type="hidden" name="dataList[${sts.index }].idx" value="${data.idx }" />
							</c:if>
						</td>
						<td>파일등록</td>
						<td>
							<%--
							<c:import url="${pageContext.request.contextPath}/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${data.attachId }" />
								<c:param name="param_showThumbnail" value="${false}" />
								<c:param name="downloadYn" value="Y"/>
								<c:param name="param_showComment" value="${false}" />
							</c:import>
							--%>
							<c:import url="${pageContext.request.contextPath}/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${data.attachId }"/>
								<c:param name="convertYn" value="N"/>
							</c:import>
							<input type="hidden" name="dataList[${sts.index }].attachId" value="${data.attachId }" />
						</td>
						<td>
							<select id="dataDept1" name="dataList[${sts.index }].dept">
								<option value="">선택하세요</option>
								<c:forEach var="dept" items="${deptList}" varStatus="sts">
									<option value="${dept.deptId },${fn:replace(dept.deptName, ' ', '&nbsp;')}" ${data.deptCode eq dept.deptId ? 'selected="selected"' : ''}>${fn:replace(dept.deptName, ' ', '&nbsp;')}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<fmt:formatDate value="${data.createDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<c:import url="${pageContext.request.contextPath}/cmm/fms/fileInfo.do" charEncoding="utf-8">
								<c:param name="attchId" value="${data.attachId }" />
								<c:param name="mode" value="fileDownCnt" />
								<c:param name="fileSn" value="0" />
							</c:import>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty dataList }">
				<tr>
					<td>1</td>
					<td>파일등록</td>
					<td>
						<input type="file" id="dataFile1" name="dataList[0].dataFile" class="input300 mB10 mT10" value="" />
					</td>
					<td>
						<select id="dataDept1" name="dataList[0].dept">
							<option value="">선택하세요</option>
							<c:forEach var="dept" items="${deptList}" varStatus="sts">
								<option value="${dept.deptId },${fn:replace(dept.deptName, ' ', '&nbsp;')}">${fn:replace(dept.deptName, ' ', '&nbsp;')}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						${yh:getToDay('-')}
					</td>
					<td>
						-
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>

	<table class="tableSt_list row_over mT10" id="dataLinkTable" ${searchVO.type eq '0' or isNew eq 'Y' ? 'style="display:none;"' : ''}>
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="w10">번호</th>
				<th scope="col" class="w10">공개 정보</th>
				<th scope="col" class="w10">URL</th>
				<th scope="col" class="w10">등록 부서</th>
				<th scope="col" class="w10">등록일</th>
				<th scope="col" class="w10">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty dataList }">
				<c:forEach var="data" items="${dataList }" varStatus="sts">
					<tr>
						<td>${sts.count }
							<c:if test="${isNew ne 'Y' and searchVO.type eq '1' }">
								<input type="hidden" name="dataList[${sts.index }].idx" value="${data.idx }" />
							</c:if>
						</td>
						<td><input type="text" id="dataName1" name="dataList[${sts.index }].name" value="${data.name }" class="input150"/></td>
						<td><input type="text" id="dataUrl1" name="dataList[${sts.index }].url" value="${data.url }" class="input150"/></td>
						<td>
							<select id="dataDept1" name="dataList[${sts.index }].dept">
								<option value="">선택하세요</option>
								<c:forEach var="dept" items="${deptList}" varStatus="sts">
									<option value="${dept.deptId },${fn:replace(dept.deptName, ' ', '&nbsp;')}" ${data.deptCode eq dept.deptId ? 'selected="selected"' : ''}>${fn:replace(dept.deptName, ' ', '&nbsp;')}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							${yh:getToDay('-')}
						</td>
						<td>-</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty dataList }">
				<tr>
					<td>1</td>
					<td><input type="text" id="dataName1" name="dataList[0].name" value="" class="input150"/></td>
					<td><input type="text" id="dataUrl1" name="dataList[0].url" value="" class="input150"/></td>
					<td>
						<select id="dataDept1" name="dataList[0].dept">
							<option value="">선택하세요</option>
							<c:forEach var="dept" items="${deptList}" varStatus="sts">
								<option value="${dept.deptId },${fn:replace(dept.deptName, ' ', '&nbsp;')}">${fn:replace(dept.deptName, ' ', '&nbsp;')}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						${yh:getToDay('-')}
					</td>
					<td></td>
				</tr>
			</c:if>
		</tbody>
	</table>

	<div class="mT10 mL10">
		<input type="button" id="addField" class="btn_white" value="추가 +">
		<input type="button" id="rmField" class="btn_white" value="제거 -">
	</div>

	<div class="btn_boxR">
		<input type="submit" id="submitBtn" class="btn_cyan" value="저장" />
		<a href="#" id="backBtn" class="btn_dblue">취소</a>
	</div>

</form:form>

<script type="text/javascript">
var advance = {edit : true};
var brm1 = "${searchVO.brm1}";
var brm2 = "${searchVO.brm2}";
var brm3 = "${searchVO.brm3}";
var brm1Name = "${searchVO.brm1Name}";
var brm2Name = "${searchVO.brm2Name}";
var brm3Name = "${searchVO.brm3Name}";
var deptOptions = "<option value=\"\">선택하세요</option><c:forEach var="dept" items="${deptList}" varStatus="sts"><option value=\"${dept.deptId },${fn:replace(dept.deptName, ' ', '&nbsp;')}\">${fn:replace(dept.deptName, ' ', '&nbsp;')}</option></c:forEach>";
</script>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/js/jquery/fastselect.min.css" /> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/fastselect.standalone.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/advanceInfoMng/advanceInfoMng.js<%--=System.currentTimeMillis() --%>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/inputFormValidator.js"></script>
