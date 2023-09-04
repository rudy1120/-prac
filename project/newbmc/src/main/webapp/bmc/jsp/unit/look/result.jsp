<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 납부금조회 결과 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.21		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.10.21
 */
%>
<style>
	.star_info {line-height:1.2; margin-top:5px;}
</style>

<script>
	window.onload = function(){
		document.title = "납부금 조회/신청(임대) 조회결과 | 부산도시공사";
	}
</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<form:form commandName="lookCustomPayVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);">
	<form:hidden path="name" />
	<form:hidden path="juminId" />

<div id="contents"><div class="content">
<div class="gap20"></div>
<p class="depth2-title">납부금 조회</p>
<div class="subSec cpProfile clearfix">
	<div class="ceoInfo aniBox active">
		<div class="txtBox">
			<strong>
				빠르고 편리한 부산도시공사의 인터넷 납부금 조회
			</strong>
		</div>	
	</div>
</div>
<div class="gap"></div>
<p>본 서비스를 통해 고객님의 계약번호로 기준일까지의 체납정보를 즉시 확인하실 수 있습니다.</p>
<div class="gap20"></div>
<!-- <h4 class="c-tit02">임대료, 연부대지&middot;연부주택 분양금 문의 : 마케팅실 손덕순 051-810-1258</h4>
<h4 class="c-tit02">임대주택, 보증금, 분양금 문의 : 마케팅실 하미경 051-810-1284</h4>
<h4 class="c-tit02">정관지구 택지대행사업은 납부금 조회가 되지 않습니다.</h4>
<div class="gap20"></div> -->
<p class="depth2-title">고객정보</p>
<div class="orgTable_wrap jobTable_wrap">
	<table class="orgTable jobTable">
		<caption>고객정보</caption>
		<tbody>
			<tr>
				<th width="10%">이름</th>
				<th width="*">주택명</th>
				<th width="15%">최종계약일</th>
				<th width="15%">임대보증금</th>
				<th width="15%">월임대료</th>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${empty result}">
						<td colspan="5">
							<span style="color: blue; font-size: 17px;">${user.userName}</span>&nbsp;님의 정보에 해당하는 데이터가 없습니다.<br/>
							<span style="color: #ff4e00;">인증하신 핸드폰번호가 계약정보와 맞지 않을시, 해당 계약부서로 연락바랍니다.</span>
						</td>
					</c:when>
					<c:otherwise>
						<td>${lookVO.name}</td>
						<td>${lookVO.houseName}</td>
						<td style="text-align:center">${lookVO.geyakDate}</td>
						<td><c:if test="${not empty lookVO.rentalDeposit}"><fmt:formatNumber value="${lookVO.rentalDeposit}" pattern="#,###"/>원</c:if></td>
						<td><c:if test="${not empty lookVO.fstReaseAmt}"><fmt:formatNumber value="${lookVO.fstReaseAmt}" pattern="#,###"/>원</c:if></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</tbody>
	</table>
</div>
<div class="gap20"></div>
<c:if test="${!empty result}">
	<p class="depth2-title">${lookVO.name} 고객님의 가상계좌번호는 ${lookVO.virtualAccount} 입니다. </p>
	<div class="gap20"></div> 
	<p class="depth2-title">납부금정보</p>
	<div class="orgTable_wrap jobTable_wrap">
		<table class="orgTable jobTable">
			<caption>납부금정보</caption>
			<tbody>
				<tr>
					<th>과목</th>
					<th>납입기한</th>
					<th>당월임대료</th>
					<th>체납금액</th>
					<th>연체이자</th>
					<th class="dwelling">*주거급여예정액</th>
					<th>배상금</th>
					<th>**총납부하실금액</th>
				</tr>
			
				<tr>
					<td>${lookVO.subject}</td>
					<td>${lookVO.dueDate}</td>
					<td style="text-align:center"><c:if test="${not empty lookVO.fstReaseAmt}"><fmt:formatNumber value="${lookVO.fstReaseAmt}" pattern="#,###"/>원</c:if></td>
					<td style="text-align:center"><c:if test="${not empty lookVO.cheMoney}"><fmt:formatNumber value="${lookVO.cheMoney}" pattern="#,###"/>원</c:if></td>
					<td>${lookVO.overdueInterest}</td>
					<td class="dwelling"><c:if test="${not empty lookVO.dwellingMoney}"><fmt:formatNumber value="${lookVO.dwellingMoney}" pattern="#,###"/>원</c:if></td>
					<td><c:if test="${not empty lookVO.reparations}"><fmt:formatNumber value="${lookVO.reparations}" pattern="#,###"/>원</c:if></td>
					<td><c:if test="${not empty lookVO.notiAmt2}"><fmt:formatNumber value="${lookVO.notiAmt2}" pattern="#,###"/>원</c:if></td>
				</tr>
			
			</tbody>
		</table> 
	</div>
	<p class="star_info">* 주거급여 예정액 : 매월 15일 이후 확정되므로, 주거급여 해당 고객님은 16일 이후 재조회 바랍니다.</p>
	<p class="star_info">** 실제와 상이할 시 전화 문의 바랍니다. 수납부 051-810-1258</p>
	<%--  
	<div class="orgTable_wrap jobTable_wrap">
		<table class="orgTable jobTable">
			<caption>납부금정보</caption>
			<tbody>
				<tr>
					<th rowspan="2">과목</th>
					<th rowspan="2">계약번호</th>
					<th>계약상태</th>
					<th>계약일</th>
					<th>계약(실입주)일자</th>
					<th>계약만료일자</th>
				</tr>
				<tr>
					<th>수납금</th>
					<th>체납금</th>
					<th>미도래금액</th>
					<th>1차임대료</th>
				</tr>
				<c:forEach var="e" items="${result}">
				<tr>
					<td rowspan="2">
						<c:if test="${e.subject == '01'}">택지분양</c:if>
						<c:if test="${e.subject == '02'}">주택분양</c:if>
						<c:if test="${e.subject == '03'}">영구임대보증금</c:if>
						<c:if test="${e.subject == '04'}">공공임대보증금</c:if>
						<c:if test="${e.subject == '05'}">상가임대보증금</c:if>
						<c:if test="${e.subject == '06'}">상가분양</c:if>
						<c:if test="${e.subject == '08'}">출자토지</c:if>
						<c:if test="${e.subject == '10'}">사옥</c:if>
						<c:if test="${e.subject == '12'}">연부주택분양금</c:if>
						<c:if test="${e.subject == '14'}">연부상가분양금</c:if>
						<c:if test="${e.subject == '41'}">융자금상환</c:if>
						<c:if test="${e.subject == '62'}">연부대지매각</c:if>
						<c:if test="${e.subject == '31'}">영구임대료</c:if>
						<c:if test="${e.subject == '32'}">공공임대료</c:if>
						<c:if test="${e.subject == '34'}">상가임대료</c:if>
						<c:if test="${e.subject == '36'}">사옥임대료</c:if>
						<c:if test="${e.subject == '71'}">사옥관리비</c:if>
					</td>
					<td rowspan="2">${e.cntrNo}</td>
					<td>${e.cntrStusNm}</td>
					<td style="text-align:center">${e.geyakDate}</td>
					<td>${e.moveinYrmd}</td>
					<td>${e.secdReaseClsYrmd}</td>
				</tr>
				<tr>
					<td style="text-align:center"><fmt:formatNumber value="${e.suMoney}" pattern="#,###"/>원</td>
					<td><fmt:formatNumber value="${e.cheMoney}" pattern="#,###"/>원</td>
					<td><fmt:formatNumber value="${e.midoreMoney}" pattern="#,###"/>원</td>
					<td style="text-align:center"><fmt:formatNumber value="${e.fstReaseAmt}" pattern="#,###"/>원</td>
				</tr>
				</c:forEach>
			</tbody>
		</table> 
	</div>--%>
</c:if>

</form:form>

</div></div>

<script>
$(function() {
	var today = new Date();
	var dd = today.getDate();
	if (dd <= 5) {
		$('.dwelling').css('display', 'none');
	}  
	
});	
	
</script>