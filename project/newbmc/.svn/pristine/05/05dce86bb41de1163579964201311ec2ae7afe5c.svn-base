<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 분양정보 상세보기
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.01		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.01
 */
%>
<script type="text/javascript">

</script>
<h2>분양정보</h2>

<form:form commandName="saleVO" name="list" id="list" methodParam="post" onsubmit="$('#page').val(1);">
	<form:hidden path="page"/>

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
				<th scope="row"><label for="name"><span class="red">*</span>사업명</label></th>
				<td colspan="3">
					${element.name}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="state"><span class="red">*</span>분양상태</label></th>
				<td colspan="3">
					<c:choose>
						<c:when test="${element.state eq 4}">
							분양완료
						</c:when>
						<c:when test="${element.state eq 1}">
							분양중
						</c:when>
						<c:otherwise>
							분양계획중
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row"><label for="name"><span class="red">*</span>공급용도</label></th>
				<td colspan="3">
					<c:forEach var="usesList" items="${usesList}" varStatus="status">
						${usesList}
					</c:forEach>
				</td>
			</tr> --%>
			<tr>
				<th scope="row"><label for="name"><span class="red">*</span>사업링크</label></th>
				<td colspan="3">
					<a href="${element.link}" target="_blank">${element.link}</a>
				</td>
			</tr>
		</tbody>
	</table>
</form:form>
<!--<div class="btn_boxR">
	<a href="#"  class="btn_swhite"  data-action="/sys/buy/saleWrite.do?pidx=${element.idx}" onclick="req.post(this); return false;">사업 수정</a>
</div>  -->
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
			<!--<th scope="col" rowspan="2" class="w5">수정|삭제</th>-->
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
<!--					<td>
  					<a href="?MG=<?=$_REQUEST[MG]?>&code=<?=$_REQUEST[code]?>&M=Mumr&idx=<?=$_REQUEST[idx]?>&idxx=<?=$sales[idx]?>">수정</a> |
						<a href="#" onclick="javascript:saleDel('<?=$sales[idx]?>');">삭제</a>
						<a href="/sys/buy/sale/viewSale.do?idx=${resultList.idx}">수정</a> 
						<a href="#" data-action="/sys/buy/saleUpdate.do?idx=${resultList.idx}" onclick="req.post(this); return false;">수정</a> |
						<a href="#" data-action="/sys/buy/deleteProc.do?mId=${menuVO.mId}&pidx=${resultList.pidx}&idx=${resultList.idx}"  onclick="if(confirm('삭제하시겠습니까?'))req.post(this); return false;" >삭제</a>
					</td>-->
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>



<!--<div class="btn_boxR">
	<a href="#"  class="btn_swhite"  data-action="/sys/buy/saleWrite.do?pidx=${element.idx}" onclick="req.post(this); return false;">매물 등록</a>
</div>-->
<div class="btn_boxR">
	<a href="/sys/buy/list.do?mId=${menuVO.mId}" class="btn_dblue">목록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
