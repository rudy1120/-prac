<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 사용자 화면 분양매물목록
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.09.27		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.09.27
 */
%>
<script src="/bmc/js/wheelzoom.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

<script>

function chkLink(link){
	
 	if (typeof link == "undefined" || link == null || link == ""){
		alert("내용을 준비중입니다.");
	} else{
		window.location.href = link;
	}
}


$(function(){
	$('.b1').on('click', function() {
		$('#sc1').remove();
	});
	var area = '${saleVO.searchArea}';
	$('.faqbtn_wrap > [value=' + '"' + area + '"' + ']').addClass('on');
	
	$('#inner img').attr('class', 'zoom');
	$(window).resize(function() {
		
		$('#inner img').css('background-position', '0px 0px');
		$('#inner img').css('background-size', 'cover');
		 if(this.resizeTO) {
			clearTimeout(this.resizeTO);
		}
		this.resizeTO = setTimeout(function() {

	            $(this).trigger('resizeEnd');

	        }, 0);
	});
	
	$(window).on('resizeEnd', function() {
	
		wheelzoom(document.querySelector('img.zoom'));
		$('#inner img').css('background-image', 'url('+ src + ')');
		
	});
	var src = $('#inner img').attr('src');
	/* $('#inner').draggable({
		cursor:"pointer",
		axis:'y'
		
		
	});  */
	
	$('#inner img').width('100%');
	wheelzoom(document.querySelector('img.zoom'));
	
	
	$('#updateFileList').css('text-align','right');
	$('#updateFileList a').css('line-height', '1');
	$('#updateFileList a').removeClass();
	$('#updateFileList a').addClass('button icon down');
})

</script>



<div class="gap20"></div>
<div id="contents">
	<div class="content">
		<form:form commandName="saleVO" name="list" id="list" onclick="$('#page').val(1);" methodParam="post">
			<form:hidden path="page"/>
			<%-- <form:hidden path="searchArea" id="sc1"/> --%>
				<%-- <div class="faqbtn_wrap">
					
					<c:forEach var="area" items="${areaList}">
						<button class="b1" name="searchArea" value="${area.idx}" onclick="$('#page').val(1);">${area.name}</button>
					</c:forEach>
				</div> --%>
		</form:form>
				
			<c:if test="${!empty mapInfo}">	
			<div class="gap"></div>
			<p class="depth2-title">위치도</p>
			
				<div class="gap"></div>
				<div class="locationInfo">
				
					<div id="inner">
					<c:choose>
				
					<c:when test="${saleVO.searchArea eq 39 }">
					<img src="/bmc/images/content/buy/buy2.jpg" alt="일광지구 분양현황도. 위로는 횡계마을,삼덕마을 오른쪽으로 일광역,일광해수욕장 왼쪽으로는 부산 울산 고속도로 아래로는 기장경찰서를 기준으로 둘러쌓인 일광지구 ">
					</c:when>
					<c:when test="${saleVO.searchArea eq 40 }">
					<img src="/bmc/images/content/buy/buy3.jpg" alt="오리일반산업단지 분양현황도">
					</c:when>
					<%-- <c:when test="${saleVO.searchArea eq 9 }">
<!-- 					<img src="/bmc/images/content/buy/buy4.jpg" alt="부산신항배후부지 토지이용계획도. 부지용도로는 항만물류, 주거, 상업, 업무, 전시, 교육, 문화, 공공청사, 공원, 녹지, 공공공지, 배수지, 펌프장, 변전소, 주차장, 보행자전용도로, 철도, 항만시설(후도등), 도로로 이루어져 있습니다."> -->
					</c:when>  --%>
					<c:when test="${saleVO.searchArea eq 12 }">
					<img src="/bmc/images/content/buy/buy5.jpg" alt="부산문현혁신도시 개발사업 토지이용계획도. 부지용도로는 통합개발용지, 계획시설용지, 일반용지, 공원, 주차장, 도로로 이루어져있습니다. 통합개발용지는 통합용지 1,2,3으로 구분되어 있습니다. 게획시설용지에는 기술보증기금, 한국은행 부산본부, 부산은행이 사용중입니다. 공원은 문화공원 A,B 두군데로 나누어져있습니다.">
					</c:when>
					<c:when test="${saleVO.searchArea eq 1 }">
					<img src="/bmc/images/content/buy/buy6.jpg" alt="부산진해 경제자유구역 미음지구개발사업. 업종별배치계획도. 외투기업 장기임대. R&D 유치부지 진입도로 부산과학산업단지 가락IC 외투기업 장기임대 신항만. 범례. ">
					</c:when>
					<c:when test="${saleVO.searchArea eq 6}">
					<img src="/bmc/images/content/buy/buy7.jpg" alt="장안일반산업단지 위치도. 부지용도로는 자동차부품 관련, 기계관련, 전기관련, 기계⋅전기⋅전자 관련, 지원시설, 주차장으로 이루어져있습니다. ">
					</c:when>
					<c:when test="${saleVO.searchArea eq 45}">
					<img src="/bmc/images/content/buy/buy8.jpg" alt="생곡지구 가구 및 획지에 관한결정[변경]도. 범례로는 구역계, 획지경계선, 산업시설용지, 지원시설용지, 연구시설용지, 단독주택용지, 주차장용지로 이루어져있습니다.">
					</c:when>
					<c:when test="${saleVO.searchArea eq 27}">
					<img src="/bmc/images/content/buy/buy9.jpg" alt="장림에이스밀(임대) 위치도. 부산광역시 사하구 하신중앙로27번길 17">
					</c:when> 
					<c:otherwise>
						<img src="/bmc/images/content/buy/buy1.jpg" alt="국제산업물류도시 분양현황도. 부지용도로는 구역계, 주변사업구역계, 행정경계, 단독주택, 공동주택, 상업용지, 산업지원시설, 산업시설, 물류시설, 연구시설, 공원, 녹지, 하천, 방수설비, 유수지, 배수지, 주차장, 자동차 정류장, 학교, 폐기물 처리시설, 주유소, 오수중계펌프장, 기압펌프장, 종교용지, 변전소, 정압실로 구분되어 있습니다.">
					</c:otherwise>
					</c:choose>
				<%-- 	<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${mapInfo}"/>
						<c:param name="mode" value="ratio"/>
						<c:param name="all" value="${false}"/>
						<c:param name="showComment" value="${false}"/>
					</c:import> --%>
					</div>
				</div> 
			</c:if>	
			<div class="gap"></div>
			<c:set var="fileExts" value="${mapInfo}" scope="request"/>
				<c:if test="${!empty fileExts}">
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="showDownload" value="Y"/>
					<c:param name="updateFlag" value="N"/>
					<c:param name="downloadYn" value="Y"/>
					<c:param name="param_atchFileId" value="${fileExts}"/>
				</c:import>
				</c:if>	
			<div class="gap20"></div>
			<p class="depth2-title">매물목록</p>	
			<p class="scroll_txt">좌우로 스크롤 하세요</p>
			
			<div class="bod_head clFix">
				<p class="page_num">
				Total <em>${totalCnt}</em>건
				</p>
				<div class="gap"></div>
			</div>
			
			<div class="orgTable_wrap">
		 		<table class="orgTable">
					<c:choose>				
					<c:when test="${saleVO.searchArea eq 39 }"><caption>일광지구 매물목록</caption></c:when>
					<c:when test="${saleVO.searchArea eq 40 }"><caption>오리일반산업단지 매물목록</caption></c:when>
					<c:when test="${saleVO.searchArea eq 9 }">
<!-- 					<caption>부산신항배후부지 매물목록</caption> -->
					</c:when> 
					<c:when test="${saleVO.searchArea eq 12 }"><caption>부산문현혁신도시 매물목록</caption></c:when>
					<c:when test="${saleVO.searchArea eq 1 }"><caption>부산진해 경제자유구역 미음지구개발사업 매물목록</caption></c:when>
					<c:when test="${saleVO.searchArea eq 6}"><caption>장안일반산업단지 매물목록</caption></c:when>
					<c:when test="${saleVO.searchArea eq 45}"><caption>생곡지구 매물목록</caption></c:when>
					<c:when test="${saleVO.searchArea eq 27}"><caption>장림에이스밀 매물목록</caption></c:when>
					<c:when test="${saleVO.searchArea eq 32}"><caption>출자택지등 매물목록</caption></c:when>
					<c:otherwise><caption>국제산업물류도시 매물목록</caption></c:otherwise>
					</c:choose>
						<%-- <c:if test="${saleVO.searchArea != 27 && saleVO.searchArea != 32}">
							분양매물목록을 알려주는 표로 번호,지역,도상번호(블록),용도,공급가액(단가,금액), 면적(㎡), 비고로 구성되어 있습니다.
						</c:if>
						<c:if test="${saleVO.searchArea == 27}">
							분양매물목록을 알려주는 표로 번호,지역,도상번호(블록),용도,공급가액[분양금액(VAT 포함), 임대보증금, 임대료(VAT 별도)], 전용면적, 비고로 구성되어 있습니다.
						</c:if>
						<c:if test="${saleVO.searchArea == 32}">
							분양매물목록을 알려주는 표로 번호,지구,지번,용도,공급가액(단가, 금액), 면적, 비고로 구성되어 있습니다.
						</c:if> --%>
						<colgroup>
							<col style="width:8%"><!-- 번호 -->
							<c:if test="${saleVO.searchArea == 32}">
								<col style="width:11%"><!-- 지역 -->
								<col style="width:12%">
							</c:if>
							<c:if test="${saleVO.searchArea != 32}">
								<col style="width:13%"><!-- 지역 -->
								<col style="width:10%"> <!-- 도상번호 -->
							</c:if>
							<col style="width:12%"><!-- 용도 -->
							<c:if test="${saleVO.searchArea == 27}">
								<col style="width:10%"><!-- 단가 -->
							</c:if>
							<col style="width:10%"><!-- 단가 -->
							<col style="width:10%"><!-- 금액 -->
							<col style="width:10%"><!-- 면적 -->
							<col><!-- 비고 -->
						</colgroup>
					<thead>
						<tr>
							<th rowspan="2" scope="col">번호</th>
							
							<c:if test="${saleVO.searchArea == 32}">
								<th rowspan="2" class="buyList" scope="col">지구</th>
								<th rowspan="2" class="buyList" scope="col">지번</th>
							</c:if>
							<c:if test="${saleVO.searchArea != 32}">
								<th rowspan="2" class="buyList" scope="col">지역</th>
								<th rowspan="2" class="buyList" scope="col">도상번호<br/>(블록)</th>
							</c:if>
							<c:if test="${saleVO.searchArea == 12 }">
								<th rowspan="2" class="buyList" scope="col">용지</th>
							</c:if>
							<th rowspan="2" class="buyList" scope="col">용도</th>
							<c:if test="${saleVO.searchArea != 27}">
								<th colspan="2" class="buyList" scope="col">공급가액</th>
								<th rowspan="2" class="buyList" scope="col">면적(㎡)</th>
							</c:if>
							<c:if test="${saleVO.searchArea == 27}">
								<th colspan="3" class="buyList" scope="col">공급가액(원)</th>
								<th rowspan="2" class="buyList" scope="col">전용면적(㎡)</th>
							</c:if>
							
							<th rowspan="2" class="buyList" scope="col">비고</th>
							
						</tr>
						<tr>
							<c:if test="${saleVO.searchArea != 27}">
								<th class="thboard" scope="col">단가(원/㎡)</th>
								<th class="thboard" style="border-right:1px solid #d6d6d6;" scope="col">금액(원)</th>
							</c:if>
							<c:if test="${saleVO.searchArea == 27}">
								<th class="thboard" scope="col">분양금액<br/>(VAT 포함)</th>
								<th class="thboard" scope="col">임대보증금</th>
								<th class="thboard" style="border-right:1px solid #d6d6d6;" scope="col">임대료<br/>(VAT 별도)</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:set var="elements" value="${result}" scope="request"/>
						
						<c:if test="${empty result}">
							<tr>
								<td colspan="8">등록된 매물목록이 없습니다.</td>
							</tr>
						</c:if>
						
						
						<c:if test="${not empty elements}">
							<c:forEach var="element" items="${elements}" varStatus="status">
								<tr>
									<%-- ====================== 번호 ====================== --%>
									<td>${listOrder - status.index}</td>
									<%-- ====================== 지역 ====================== --%>
									<c:if test="${saleVO.searchArea == 32}">
										<td class="buyList">${element.dnum}</td>
									</c:if>
									<c:if test="${saleVO.searchArea != 32}">
										<td class="buyList"><a class="happyhouse_line" href="#" onclick="chkLink('${element.link}')">${element.name}</a></td>
									</c:if>
									<%-- ====================== 도상번호 ====================== --%>
									
									<c:if test="${saleVO.searchArea == 32}">
										<td class="buyList">${element.location}</td>
									</c:if>
									<c:if test="${saleVO.searchArea != 32}">
										<td class="buyList">${element.dnum}</td>
									</c:if>
									
									<%-- ====================== 용도 ====================== --%>
									<td class="buyList">${fn:replace(element.uses, '(', '<br />(')}</td>
									<%-- ====================== 금액 ====================== --%>
									<c:if test="${saleVO.searchArea != 27}">
										<td class="buyList">${element.price1}</td>
										<td class="buyList">${fn:replace(element.price2, '(', '<br />(')}</td>
									</c:if>
									<c:if test="${saleVO.searchArea == 27}">
										<td class="buyList">${element.location}</td>
										<td class="buyList">${element.price1}</td>
										<td class="buyList">${fn:replace(element.price2, '(', '<br />(')}</td>
									</c:if>
									<%-- ====================== 면적 ====================== --%>
									<td class="buyList">${element.space}</td>
									<%-- ====================== 비고 ====================== --%>
									<td class="buyList">${element.height}</td>			
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		<c:choose>
		<c:when test="${saleVO.searchArea == 1}">
			<p style="margin-top:3px;line-height:1.5;">▪ R&D허브단지, 역외기업부지, 외투기업 장기임대  : 부산시 및 부산&middot;진해경제자유구역청 입주기업 유치중</p>
		<!--<p style="margin-top:3px;line-height:1.5;">▪ 폐기물처리시설용지 : 폐기물관리법 등 관련법령에 따라 폐기물처리시설을 운영하고자 하는 자가 부산&middot;진해경제자유구역청 등 유관기관 협의 후 용지매매계약 체결</p> -->
			<br />
		</c:when>
		<c:when test="${saleVO.searchArea == 45}">
			<p></p>
		
		</c:when>
		<%-- <c:when test="${saleVO.searchArea == 9}">
			<p style="margin-top:3px;line-height:1.5;">▪ 초등학교 : 경상남도 창원교육지원청 구매대상 부지</p>
			<p style="margin-top:3px;line-height:1.5;">▪ 고등학교 : 경상남도 교육청 구매대상 부지</p>
			<p style="margin-top:3px;line-height:1.5;">▪ 공공청사 : 파출소, 우체국, 소방서 등 구매대상 부지</p>
			<br />
		</c:when> --%>
		<c:when test="${saleVO.searchArea == 12}">

			<p style="margin-top:3px;line-height:1.5;">▪ 일반상업, 주차장 : 금융공기업 유치를 위한 정책 용지로 일반 분양 대상 용지는 아님</p>
			<br />
		</c:when>
		<c:when test="${saleVO.searchArea == 6}">
			<p style="margin-top:3px;line-height:1.5;">▪ 주차장 : 수의계약 (부동산 중개알선수수료 지급대상, 분양가 0.9%)</p>
			<br />
		</c:when>
		</c:choose>	
			
			
			
		<c:choose>
		<c:when	test="${saleVO.searchArea == 12}">
		
		</c:when>
		<c:when	test="${saleVO.searchArea == 27}">
			<p style="margin-top:3px;line-height:1.5;">&#8251; 입주제한 업종(공해발생업종 등) 등 별도 문의</p>
		</c:when>
		<c:otherwise>
			<p style="margin-top:3px;line-height:1.5;">&#8251; 상기 건폐율, 용적률 등은 참고자료로, 반드시 지구단위계획 및 환경영평가 등 관련 도서를 확인하여 주시기 바랍니다.</p>
		</c:otherwise>
		</c:choose>
		
		<div class="gap20"></div>	
		<c:if test="${not empty result}">
			<div class="bod_page">
				<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
			</div>
		</c:if>
	</div>
</div>


<script type="text/javascript" src="/common/js/commonProcess.js"></script>
