<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 매물관리
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2019.10.01		박선민			최초 생성 및 코드 수정
	 *
	 *
	 * @author 박선민
	 * @since 2019.10.01
	 */
%>
<c:set var="isNew"   value="${saleVO.idx == null or saleVO.idx eq 0}"/>
<c:set var="postUrl" value="${saleVO.saveType eq 'new' ? 'writeProc' : 'modifyProc'}"/>
<h2>분양매물 등록/수정</h2>
<form:form commandName="saleVO" id="cancel" name="cancel"  methodParam="post" action="/sys/buy/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="saleVO" id="modify" name="modify" methodParam="post" action="/sys/buy/sale/${postUrl}.do?mId=${menuVO.mId}"  onsubmit="return validator();" htmlEscape="false"  enctype="multipart/form-data">
	<form:hidden path="page" />
	<form:hidden path="pidx" value="${saleVO.pidx}"/>
	<form:hidden path="idx" value="${saleVO.idx}"/>
	<table class="tableSt_write">
		<caption>분양매물을 등록/수정하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width:15%"/>
			<col span="1" style="width:15%"/>
			<col span="1" style="width:15%"/>
			<col span="1" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="title"><span class="red">*</span>용도선택 </label></th>
				<td colspan="3">
					<form:select path="uses" id="uses"  data-validator="on" data-required="y" data-fieldName="용도" style="width:200px;">
						<form:option value="">--선택--</form:option>
						<c:forEach var="uses" items="${usesList}">
							<form:option value="${uses.codename}">${uses.codename}</form:option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="dnum"><span class="red">*</span>도상번호</label></th>
				<td colspan="3">
					<form:input path="dnum"  cssClass="input200"  maxlength="200" data-validator="on" data-required="y" data-fieldName="도상번호" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="price1"><span class="red">*</span>단가예정</label></th>
				<td colspan="3">
					<form:input path="price1"  cssClass="input200" maxlength="200" data-validator="on" data-required="y" data-fieldName="단가예정" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="price2"><span class="red">*</span>금액예정</label></th>
				<td colspan="3">
					<form:input path="price2" cssClass="input200" maxlength="200" data-validator="on" data-required="y" data-fieldName="금액예정" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="space"><span class="red">*</span>면적</label></th>
				<td colspan="3">
					<form:input path="space" cssClass="input200" maxlength="200" data-validator="on" data-required="y" data-fieldName="면적"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="height"><span class="red">*</span>비고</label></th>
				<td colspan="3">
					<form:textarea path="height" cols="80" rows="5" maxlength="4000" data-fieldName="높이" />
					<p><span class="red"><form:errors path="height" /></span></p>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="location">예외사항</label></th>
				<td colspan="3">
					<form:input path="location" cssClass="input200" maxlength="200" data-fieldName="예외"/>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row"><label for="location"> 위치도</label></th>
				<td colspan="3"  data-fileName="${saleVO.location}">
					<input type="hidden" name="location" value="${saleVO.location}" />
					<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${saleVO.location}" />
						<c:param name="param_showThumbnail" value="${true}" />
						<c:param name="param_thumbWidth" value="200" />
						<c:param name="param_thumbHeight" value="200" />
						<c:param name="downloadYn" value="Y"/>
						<c:param name="param_showComment" value="${true}" />
						<c:param name="fileCnName" value="locationFileCn" />
					</c:import>
				
					<input type="file" id="location_file" name="location_file" data-validator="on" data-fieldName="위치도 첨부파일" class="input300 mB10" value="" />
					<div id="exTxt"><label for="locationFileCn">설명글</label>: <input id="locationFileCn" type="text" name="locationFileCn"   class="input400" /></div>
					<div class="pT5" id="exTxt2">
						<p>※ 이미지 파일 설명글 작성 방법 (시각장애인의 웹 접근성 확보)</p>
						<p>- 사진 : 사진의 내용을 자세히 묘사 (예시. 청사 정문에 활짝 핀 매화 꽃 사진입니다.)</p>
						<p>※ 파일은 최대 1개까지 첨부하실 수 있습니다. 첨부 가능한 하나의 파일 사이즈는 최대 100Mbyte 입니다.</p>
					</div>

				</td>
			</tr> --%>
		</tbody>
	</table>
	<div class="btn_boxR">
		<input type="submit" id="submitBtn"  class="btn_cyan" value="저장" />
		<a href="#" id="backBtn" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>
<br/>
<span>&nbsp;&nbsp;&nbsp;&nbsp; * 지구마다 차이가 있어 칼럼명과 값이 일치하지 않을 수 있습니다.
 홈페이지(분양매물목록)의 표의 칼럼과 값을 비교하여 등록 및 수정하여 주세요.
</span>
<br/>
<h2>분양매물</h2>
<table class="tableSt_list row_over">
	<thead>
		<tr>
			<th scope="col" rowspan="2" class="w7">도상번호</th>
			<th scope="col" rowspan="2" class="w10">용도</th>
			<th scope="col" colspan="2" class="w15">공급가액</th>
			<th scope="col" rowspan="2" class="w7">면적</th>
			<th scope="col" rowspan="2" class="w15">비고</th>
			<th scope="col" rowspan="2" class="w10">예외사항</th>
			<th scope="col" rowspan="2" class="w5">수정|삭제</th>
		</tr>
		<tr>
			<th>단가</th>
			<th>금액</th>
		</tr>
	</thead>
	<c:choose>
		<c:when test="${empty resultList}">
			<tr>
				<td colspan="7">
					등록된 매물이 없습니다.
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="resultList" items="${resultList}" varStatus="status">
				<tr height="30">
					<td><a href="/sys/buy/saleUpdate.do?mId=${menuVO.mId}&idx=${resultList.idx}" >${resultList.dnum}</a></td>
					<td>${resultList.uses}</td>
					<td>${resultList.price1}</td>
					<td>${resultList.price2}</td>
					<td>${resultList.space}</td>
					<td>${resultList.height}</td>
					<td>${resultList.location}</td>
					<td>
<!--  					<a href="?MG=<?=$_REQUEST[MG]?>&code=<?=$_REQUEST[code]?>&M=Mumr&idx=<?=$_REQUEST[idx]?>&idxx=<?=$sales[idx]?>">수정</a> |
						<a href="#" onclick="javascript:saleDel('<?=$sales[idx]?>');">삭제</a>
						<a href="/sys/buy/sale/viewSale.do?idx=${resultList.idx}">수정</a> -->
						<a href="/sys/buy/saleUpdate.do?mId=${menuVO.mId}&idx=${resultList.idx}"  onclick="req.post(this); return false;">수정</a> |
						<a href="/sys/buy/saleDeleteProc.do?mId=${menuVO.mId}&pidx=${resultList.pidx}&idx=${resultList.idx}"  onclick="if(confirm('삭제하시겠습니까?'))req.post(this); return false;" >삭제</a>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>


<script type="text/javascript" src="${_context}/common/js/common/require.js"></script>
<script type="text/javascript" src="${_context}/sys/js/basic/pollMng/pollMng.js"></script>
<script>
	jQuery(document).ready(function(){
		$('.updateFileList').parent('td').find('[type=file]').hide();
		$('.updateFileList').parent('td').find($("#exTxt")).remove();
		$('.updateFileList').parent('td').find($("#exTxt2")).remove();
		//$('.updateFileList').parent('td').find($(".pT5")).hide();
		$('.btn_dele').click(function() {
			//history.go(0);
			 defaultTime = setInterval(function() {
				$('.updateFileList>ul:not(:has(li))').parents('td').find('[type=file]').show();
				clearInterval(defaultTime);
			 }, 100);
		});
	});
</script>
