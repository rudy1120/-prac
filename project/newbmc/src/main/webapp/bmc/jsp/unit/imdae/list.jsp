<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 입주대기자 현황 메인 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.09.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.09.30
 */
%>

<!-- <style>
	.read_wrap {text-align:center;}
	.read_label {display:inline-block; width:20%; min-width:50px;}
	input[class^="btn_"] {width:200px; }
	.info_table {width:60%;margin:auto;}
	.stayman_search { display:inline-block; width:27%; min-width:200px; margin:10px 0;}
	.stayman_search input { width:70%; }
	@media all and (max-width: 768px) {
		.stayman_search { width:70%; }
		input[class^="btn_"] { float:right; width:90%; margin-right:10px;  }
	}
</style> -->

<div id="contents"><div class="content">

<form:form commandName="imdaeWatingInfoVO" id="list" name="list" methodParam="post" action="/bmc/imdae/list.do?mId=${menuVO.mId}" onsubmit="return validator() && confirm('조회하시겠습니까?');">
<form:hidden path="gb" />

<div class="gap20"></div>
<div class="read_wrap">
	<div class="gap"></div>
	<!-- 20230411 주석 --> 
<!-- 	<div class="stayman_search"> -->
<!-- 		<label for="urlsel" class="read_label">구분</label> -->
<!-- 		<select id="urlsel" name="url" onchange="moveurl(this.value);"> -->
<%-- 			<option value="/bmc/imdae/list.do?gb=2&mId=0102040100" <c:if test= "${gb ==2}">selected</c:if>> --%>
<!-- 			공공임대주택 -->
<!-- 			</option> -->
<%-- 			<option value="/bmc/imdae/list.do?gb=0&mId=0102040200" <c:if test= "${gb ==0}">selected</c:if>> --%>
<!-- 			영구임대주택 -->
<!-- 			</option> -->
<%-- 			<option value="/bmc/imdae/list.do?gb=3&mId=0102040300" <c:if test= "${gb ==3}">selected</c:if>> --%>
<!-- 			재개발임대주택 -->
<!-- 			</option> -->
<!-- 		</select> -->
<!-- 	</div> -->
	<div class="stayman_search">
	<label for="username" class="read_label">성명</label>
	<form:input path="username"  maxlength="100" data-validator="on" data-required="y" data-fieldName="성명"/>	
	</div>
	<div class="stayman_search">
	<label for="juminNo" class="read_label">생년월일</label>
	<form:input path="juminNo" maxlength="6" minlength="6" data-inputType="birth" data-validator="on" data-required="y" data-fieldName="생년월일" placeholder="생년월일 6자리를 입력해주세요."/>
	</div>
	<input id="submitBtn" type="submit" class="btn_blue" value="조회하기"/>
	<div class="gap20"></div>
	<div class="orgTable_wrap jobTable_wrap">
		<table class="orgTable jobTable">
		<caption>입주대기자 검색 결과창</caption>
		<thead>
		<tr>
			<th style="width:20%;" scope="col">단지명</th>
			<th style="width:20%;" scope="col">평형</th>
			<th style="width:20%;" scope="col">순번</th>
			<th style="width:20%;" scope="col">이름</th>
			<th style="width:20%;" scope="col">연락처</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="e" items="${watingList}" varStatus="status">
			<tr>
				<td>${e.jiguname}</td>
				<td>${e.pyungsu}</td>
				<td style="text-align:center">${e.num}</td>
				<td style="text-align:center">${e.uname}</td>
<%-- 				<td style="text-align:center">${e.aDate}</td> --%>
				<td>${fn:substring(e.telnum,0,3)} - **** - ${fn:substring(e.telnum,7,12)}</td>
<%-- 				<td>${e.gu}</td> <!-- 출신구 --> --%>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	</div>
	<c:if test="${empty watingList}">
		<div class="gap"></div>	
		<div class="no_data" style="padding-bottom: 30px;">입주자 명단이 없습니다.</div>
	</c:if>
</div>

<div class="gap20"></div>
<c:if test="${gb == 3}">
	<h3 style="text-align:center;">&#8251; 민락1구역(39형) 당첨자분의 순번은 39A형에서 조회하시기 바랍니다.</h3>
</c:if>
<c:if test="${gb != 5}">
	<p class="scroll_txt">좌우로 스크롤 하세요</p>
	<div class="gap"></div>
	<div class="orgTable_wrap jobTable_wrap">
			<table class="orgTable jobTable">
		<caption>입주대기자 현황</caption>
		<thead>
			<tr>
				<th style="width:10%" scope="col">단지명</th>
				<th style="width:40%" scope="col">소재지</th>
				<th style="width:10%" scope="col">면적(m<sup>2</sup>)</th>
				<th style="width:10%" scope="col">총세대수</th>
				<th style="width:10%" scope="col">대기자수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${jiguList}" varStatus="status">
				<c:if test="${status.count != fn:length(jiguList) }">
				<tr>
					<td>${e.jiguname}</td>
					<td>${e.jiguso}</td>
					<td style="text-align:center">${e.pyung}</td>
					<td style="text-align:center">${e.sedaetotal}</td>
					<td>${e.wating}</td>
				</tr>
				</c:if>
				<c:if test="${status.count == fn:length(jiguList) }">
					<tr>
						<td>계</td>
						<td></td>
						<td></td>
						<td style="text-align:center">${e.sedaetotal}</td>
						<td>${e.wating}</td>
					</tr>
				</c:if>
			</c:forEach>
			</tbody>
	</table>
	</div>
</c:if>
</form:form>
<c:if test="${gb ==5}">
<form:form commandName="imdaeJiguVO" name="happyHouseForm" id="happyHouseForm" action="/bmc/imdae/list.do?gb=5&mId=0102040500" methodParam="post">
	<div style="padding:10px 30px 30px 0; float:right;">
		<form:select path="hgbn">
			<form:option value="1">행복주택(동래)</form:option>
			<form:option value="2">행복주택(아미4)</form:option>
			<form:option value="3">행복주택(일광지구)</form:option>
			<form:option value="4">행복주택(시청앞)</form:option>
			<form:option value="5">행복주택(용호)</form:option>
		</form:select>
	<button class="btnmove">이동</button>
	</div>
	<p class="scroll_txt">좌우로 스크롤 하세요</p>
	<div class="gap"></div>
	<div class="orgTable_wrap jobTable_wrap">
			<table class="orgTable jobTable">
		<caption>입주대기자 현황</caption>
		<thead>
			<tr>
				<th width="10%">단지명</th>
				<th width="40%">소재지</th>
				<th width="10%">면적(m<sup>2</sup>)</th>
				<th width="10%">공급대상</th>
				<th width="10%">총세대수</th>
				<th width="10%">대기자수</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="totcnt" value="1"/>
			<c:set var="jiname" value=""/>
			<c:forEach var="e" items="${happyList}" varStatus="status">
				<c:if test="${status.count != fn:length(happyList) }">
					<tr>
						<c:if test="${e.cnt ne '1' && totcnt eq '1' && e.jiguname ne jiname}">
							<c:set var="totcnt" value="${e.cnt}"/>
						</c:if>
						<c:if test="${e.jiguname ne jiname}">
							<c:set var="jiname" value="${e.jiguname}"/>
						</c:if>
						
						<c:if test="${e.cnt eq totcnt}">
							<td rowspan="${e.cnt}">${e.jiguname}</td>
							<td rowspan="${e.cnt}">${e.jiguso}</td>
							<td rowspan="${e.cnt}" style="text-align:center">${e.pyung}</td>
						</c:if>
						
						<c:if test="${e.cnt ne '1' && totcnt ne '1'}">
							<c:set var="totcnt" value="${totcnt - 1}"/>
						</c:if>
						
						<td style="text-align:center">${e.supply}</td>
						<td style="text-align:center">${e.sedaetotal}</td>
						<td style="text-align:center">${e.wating}</td>
					</tr>
				</c:if>
				<c:if test="${status.count == fn:length(happyList) }">
					<tr>
						<td>계</td>
						<td></td>
						<td></td>
						<td></td>
						<td style="text-align:center">${e.sedaetotal}</td>
						<td>${e.wating}</td>
					</tr>
				</c:if>
			</c:forEach>
			</tbody>
	</table>
	</div>
	<c:if test="${empty happyList}">
		<div class="no_data">소재지가 없습니다.</div>
	</c:if>
</form:form>
</c:if>
<c:if test="${empty jiguList}">
	<div class="no_data">소재지가 없습니다.</div>
</c:if>

</div></div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/common/js/inputFormValidator.js?v=3"></script>
<script>
$(function () {
	// 20230411 주석 $(".tabmenu").hide();
});

function moveurl(url) { 
    location.href = url;
} 

</script>






