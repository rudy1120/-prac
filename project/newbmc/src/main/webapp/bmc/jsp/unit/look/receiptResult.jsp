<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<script>
var fn_openPop = function() {
	var popupWindow = window.open("/bmc/look/receiptPrint.do?mId=${menuVO.mId}", "_blank", "width=1250,height=800");
	//var popupWindow = window.open("/bmc/jsp/unit/look/receiptPrint.jsp", "_blank", "width=1250,height=800");
	$("#list").submit();
};
</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>

<div id="contents">
	<div class="content">
	
		<form:form commandName="lookCustomPayVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);">
			<form:hidden path="custNm" />
			<form:hidden path="resiNo" />
			<form:hidden path="phonNo" />

			<div class="bod_head clFix">
				<fieldset class="bod_search">
					<label for="schSdate">수납일자</label>
					<input id="schSdate" name="schSdate" class="input100" data-ismindatefor="schSdate" data-date="y" data-validator="on" type="text" autocomplete="off"> ~
					<input id="schEdate" name="schEdate" class="input100" data-ismaxdatefor="schEdate" data-date="y" data-validator="on" type="text" autocomplete="off">
					<input type="submit" value="검색" name="searchBt" title="검색">
					<div class="gap20"></div>
				</fieldset>
			</div>
			
			<!-- 출력버튼 -->
			<!-- 차후 문서보안솔루션 도입 후 오픈 예정  --> 
			<!-- 
			<div class="btn_wrap btn_wrap2">
				<a href="#" onclick="javascript:fn_openPop();" style="float: right;">내역 출력하기</a>
			</div>
			 -->
	
			<!-- 주택명 데이터 -->
			<p class="depth2-title">주택명 : ${lookVO.houseNm }</p>
			<p class="depth2-title">기본사항</p>
			<!-- 기본사항 데이터 -->
			<div class="orgTable_wrap jobTable_wrap">
				<table class="orgTable jobTable">
					<caption>기본사항</caption>
					<tbody>
						<tr>
							<th width="15%">계약번호</th>
							<th width="15%">고객번호</th>
							<th width="20%">성명</th>
							<th width="*">주민등록번호</th>
							<th width="20%">임대사업자등록번호</th>
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
									<td>${lookVO.cntrNo}</td>
									<td>${lookVO.custNo}</td>
									<td style="text-align:center">${lookVO.custNm}</td>
									<td style="text-align:center">${lookVO.resiNo}</td>
									<td>${lookVO.rentRegno}</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="gap20"></div>
			
		<c:if test="${!empty result}">
			<div class="orgTable_wrap jobTable_wrap">
				<table class="orgTable jobTable">
					<caption>임대료 수납내역</caption>
					<tbody>
						<tr>
							<th>진행년월</th>
							<th>납부기한</th>
							<th>수납일자</th>
							<th>원금</th>
							<th>배상금</th>
							<th>고지금액</th>
							<th>연체시작일</th>
							<th>연체종료일</th>
							<th>연체이자</th>
							<th>수납금액</th>
						</tr>
						<c:forEach var="e" items="${result}">
						<tr>
							<td>${e.progYymm}</td>
							<td>${e.paymDeadYrmd}</td>
							<td>${e.rectYrmd}</td>
							<td style="text-align:right;"><fmt:formatNumber value="${e.prin}" pattern="#,###"/></td>
							<td style="text-align:right;"><fmt:formatNumber value="${e.indem}" pattern="#,###"/></td>
							<td style="text-align:right;"><fmt:formatNumber value="${e.notiAmt}" pattern="#,###"/></td>
							<td>${e.arrStaYrmd}</td>
							<td>${e.arrClsYrmd}</td>
							<td style="text-align:right;"><fmt:formatNumber value="${e.arrInte}" pattern="#,###"/></td>
							<td style="text-align:right;"><fmt:formatNumber value="${e.rectAmt}" pattern="#,###"/></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>

		</form:form>
		
		<!-- 직인 필요 -->
		<c:if test="${!empty result}">
		<p style="text-align: center; font-size: 20px; padding: 40px;">*상기 임대료 납부금은 국세청 홈택스 현금영수증으로 등록되었습니다.</p>
		<!-- <p><span style="position: absolute; left: 65%; margin-top:-24px;"><img src="/bmc/images/look/seal.jpg" style="width:100px;"/></span></p> -->
		<p style="text-align:center; font-size:40px; letter-spacing:15px; position: relative; height: 93px; width: 100%;">부산도시공사 사장<p>
		</c:if>			

	</div>
</div>

<link rel="stylesheet" type="text/css" href="/common/css/jquery-ui.1.12.1.min.css"/>
<script type="text/javascript" src="/common/js/jquery/jquery-ui-1.10.0.custom.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>

<script>
	jQuery(document).ready(function(){
		$("#schSdate").datepicker({ dateFormat: 'yy-mm-dd' });
		$("#schEdate").datepicker({ dateFormat: 'yy-mm-dd' });
		
		$("#schSdate").datepicker("setDate", "${lookVO.schSdate}");
		$("#schEdate").datepicker("setDate", "${lookVO.schEdate}");
	});
</script>