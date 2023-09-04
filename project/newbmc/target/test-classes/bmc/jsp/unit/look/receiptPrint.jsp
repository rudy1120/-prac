<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<style>
@page{ margin : 0; }
#header, #nav, .noprint { display: none; }

* { font-family : "바탕체", serif; font-size: 11px; }
.contents { padding: 30px; }
h2 { text-decoration: underline; text-align: center; font-size: 20px; padding-bottom: 20px;}
table { width: 100%; border: 1px solid #444444; border-collapse: collapse; }
.basicDiv { padding-bottom: 30px; }
.basicTable { text-align : center; }
.basicTable th { border: 1px solid #444444; border-collapse: collapse; padding: 9px; font-weight: bold;}
.basicTable td { border: 1px solid #444444; border-collapse: collapse; padding: 9px; }
.detailTable th { border: 1px solid #444444; border-collapse: collapse; padding: 9px; font-weight: bold;}
.detailTable td { border: 1px solid #444444; border-collapse: collapse; padding: 5px; }
.detailTable .ctr { text-align : center; }
.detailTable .rt { text-align : right; }
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js" ></script>
<script>
	//<![CDATA[
	$(document).ready(function() {
	    window.print();
	    window.setTimeout(function () {
	    	window.close();
	       }, 1000);
	});
	//]]>
</script>

<div class="contents">

	<h2>임대료 개인별 수납내역서</h2>
		
	<form:form commandName="lookCustomPayVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);">
		<form:hidden path="name" />
		<form:hidden path="juminId" />
		<form:hidden path="tel" />
	
		<!-- 주택명 데이터 -->
		<p>주택명 : 다대3영구임대아파트 113동 1308호</p>
		<p>기본사항</p>
		<!-- 기본사항 데이터 -->
		<div class="basicDiv">
			<table class="basicTable">
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
								<td colspan="5">조회된 자료가 없습니다.</td>
							</c:when>
							<c:otherwise>
								<td>${lookVO.custNo}</td>
								<td>${lookVO.custNo}</td>
								<td>${lookVO.name}</td>
								<td style="text-align:center">${lookVO.juminId}-2******</td>
								<td>${lookVO.tel}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</tbody>
			</table>
		</div>
		
	<c:if test="${!empty result}">
		<div>
			<table class="detailTable">
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
					<tr>
						<td class="ctr">2018-11</td>
						<td class="ctr">2018-11-30</td>
						<td class="ctr">2019-01-10</td>
						<td class="rt">49400</td>
						<td class="rt">0</td>
						<td class="rt">49400</td>
						<td class="ctr">2018-12-01</td>
						<td class="ctr">2019-01-10</td>
						<td class="rt">360</td>
						<td class="rt">49760</td>
					</tr>
					<tr>
						<td class="ctr">2018-12</td>
						<td class="ctr">2018-12-31</td>
						<td class="ctr">2019-01-10</td>
						<td class="rt">49400</td>
						<td class="rt">0</td>
						<td class="rt">49400</td>
						<td class="ctr">2019-01-01</td>
						<td class="ctr">2019-01-10</td>
						<td class="rt">90</td>
						<td class="rt">49490</td>
					</tr>
					<tr>
						<td class="ctr">2018-12</td>
						<td class="ctr">2018-11-30</td>
						<td class="ctr">2019-01-10</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td>수납일자계</td>
						<td></td>
						<td></td>
						<td class="rt">98800</td>
						<td class="rt">0</td>
						<td class="rt">98800</td>
						<td></td>
						<td></td>
						<td class="rt">2100</td>
						<td class="rt">100900</td>
					</tr>
					<tr>
						<td class="ctr">2019-01</td>
						<td class="ctr">2019-01-31</td>
						<td class="ctr">2019-02-28</td>
						<td class="rt">49400</td>
						<td class="rt">0</td>
						<td class="rt">49400</td>
						<td class="ctr">2019-01-01</td>
						<td class="ctr">2019-01-10</td>
						<td class="rt">90</td>
						<td class="rt">49490</td>
					</tr>
					<tr>
						<td class="ctr">2019-02</td>
						<td class="ctr">2019-02-28</td>
						<td class="ctr">2019-02-28</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td>수납일자계</td>
						<td></td>
						<td></td>
						<td class="rt">98800</td>
						<td class="rt">0</td>
						<td class="rt">98800</td>
						<td></td>
						<td></td>
						<td class="rt">2100</td>
						<td class="rt">100900</td>
					</tr>
					<tr>
						<td class="ctr">2019-03</td>
						<td class="ctr">2019-03-31</td>
						<td class="ctr">2019-05-24</td>
						<td class="rt">49400</td>
						<td class="rt">0</td>
						<td class="rt">49400</td>
						<td class="ctr">2018-12-01</td>
						<td class="ctr">2019-01-10</td>
						<td class="rt">360</td>
						<td class="rt">49760</td>
					</tr>
					<tr>
						<td class="ctr">2019-04</td>
						<td class="ctr">2019-04-30</td>
						<td class="ctr">2019-05-24</td>
						<td class="rt">49400</td>
						<td class="rt">0</td>
						<td class="rt">49400</td>
						<td class="ctr">2019-01-01</td>
						<td class="ctr">2019-01-10</td>
						<td class="rt">90</td>
						<td class="rt">49490</td>
					</tr>
					<tr>
						<td class="ctr">2019-05</td>
						<td class="ctr">2019-05-31</td>
						<td class="ctr">2019-05-24</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td class="ctr">2019-05</td>
						<td class="ctr">2019-05-31</td>
						<td class="ctr">2019-05-24</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td>수납일자계</td>
						<td></td>
						<td></td>
						<td class="rt">98800</td>
						<td class="rt">0</td>
						<td class="rt">98800</td>
						<td></td>
						<td></td>
						<td class="rt">2100</td>
						<td class="rt">100900</td>
					</tr>
					<tr>
						<td class="ctr">2019-06</td>
						<td class="ctr">2019-06-30</td>
						<td class="ctr">2019-07-31</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td class="ctr">2019-07</td>
						<td class="ctr">2019-07-31</td>
						<td class="ctr">2019-07-31</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td>수납일자계</td>
						<td></td>
						<td></td>
						<td class="rt">98800</td>
						<td class="rt">0</td>
						<td class="rt">98800</td>
						<td></td>
						<td></td>
						<td class="rt">2100</td>
						<td class="rt">100900</td>
					</tr>
					<tr>
						<td class="ctr">2019-08</td>
						<td class="ctr">2019-08-31</td>
						<td class="ctr">2019-09-30</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td class="ctr">2019-09</td>
						<td class="ctr">2019-09-30</td>
						<td class="ctr">2019-09-30</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td class="ctr">2019-09</td>
						<td class="ctr">2019-09-30</td>
						<td class="ctr">2019-09-30</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="rt">0</td>
						<td class="ctr">2018-08-01</td>
						<td class="ctr">2018-10-31</td>
						<td class="rt">1650</td>
						<td class="rt">1650</td>
					</tr>
					<tr>
						<td>수납일자계</td>
						<td></td>
						<td></td>
						<td class="rt">98800</td>
						<td class="rt">0</td>
						<td class="rt">98800</td>
						<td></td>
						<td></td>
						<td class="rt">2100</td>
						<td class="rt">100900</td>
					</tr>
					<tr>
						<td>총계</td>
						<td></td>
						<td></td>
						<td class="rt">543400</td>
						<td class="rt">0</td>
						<td class="rt">543400</td>
						<td></td>
						<td></td>
						<td class="rt">3310</td>
						<td class="rt">546710</td>
					</tr>
				</tbody>
			</table>
		</div>
	</c:if>
	
	</form:form>
	
	<!-- 직인 필요 -->
	<p style="text-align:center; font-size:15px; padding-bottom: 20px;">*상기 임대료 납부금은 국세청 홈택스 현금영수증으로 등록되었습니다.</p>
	<p style="text-align:center; font-size:25px; letter-spacing:15px; position: relative; height: 93px;">부산도시공사 사장
		<span style="position: absolute; top: -30px; left: 61%;"><img src="/bmc/images/look/injang.png"/></span>
	</p>

</div>