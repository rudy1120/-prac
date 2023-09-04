<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/bmc/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>개인정보처리방침 이전 적용 지침</title>
	<link rel="stylesheet" type="text/css" href="/bmc/font/font.css">
	<link rel="stylesheet" type="text/css" href="/bmc/css/base.css">
	<link rel="stylesheet" type="text/css" href="/bmc/css/common.css">
	<link rel="stylesheet" type="text/css" href="/bmc/css/content.css">
	
	<!-- jQuery -->
	<script src="/bmc/js/jquery-3.1.1.min.js"></script>
	<script>
	$(function () {
		$(".tab_content").hide();
		$(".tab_content:first").show();
		$("ul.tabs li:first").addClass("activeClass").css("color", "darkred");
		
		$("ul.tabs li").click(function () {
			$("ul.tabs li").removeClass("activeClass").css("color", "#333");
			$(this).addClass("activeClass").css("color", "darkred");
			$(".tab_content").hide();
			var activeTab = $(this).attr("rel");
			$("#" + activeTab).fadeIn();
		});
		
		 $("#GoPre").on("click", function(){
		    	var lnk = $("#privacy_select").val();
		    	if(lnk != "") window.open(lnk, '_blank', 'width=800,height=800,scrollbars=yes');
		    });
	});
	</script>
</head>
<body style="overflow-x:initial !important;">

<div id="subContents">
	<h3 class="tit" style="padding-bottom: 10px;text-align: center;">개인정보처리방침 2015.05.06</h3>
	<p style="text-align: center;padding-bottom: 20px;">적용기간(2015.05.06 ~ 2016.12.27)</p>
	<div id="contents">
		<div class="content" style="margin-bottom: 0px;">
			<div class="gap20"></div>
			<div class="tabmenu">
				<div class="guide_tab">
				<ul class="tabs">
					<li class="activeClass" style="width:30%;" rel="tab1">
						<div class="#tab01" style="cursor: pointer;">개인정보처리방침</div>
					</li>
					<li style="width:40%;" rel="tab2">
						<div class="#tab02" style="cursor: pointer;">영상처리기기 운영·관리 방침</div>
					</li>
				</ul>
				</div>
			</div>
			
			<div id="container">
				<div class="tab_container">
					<div id="tab1" class="tab_content">
        			<!-- TODO 첫번째 메뉴-->
						<p class="depth2-title">개인정보 처리방침 안내</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>부산도시공사가 취급하는 모든 개인정보는 관련법령에 근거하거나 정보주체의 동의에 의하여 수집·보유 및 처리되고 있습니다.</p>
								<p>「개인정보보호법」은 이러한 개인정보의 취급에 대한 일반적 규범을 제시하고 있으며, 부산도시공사는 이러한 법령의 규정에 따라 수집·보유 및 처리하는 개인정보를 공공업무의 적절한 수행과 시민의 권익을 보호하기 위해 적법하고 적정하게 취급할 것입니다. 
								또한, 부산도시공사는 관련 법령에서 규정한 바에 따라 보유하고 있는 개인정보에 대한 열람, 정정·삭제, 처리정지 요구 등 정보주체의 권익을 존중할 것이며, 
								정보주체는 이러한 법령상 권익의 침해 등에 대하여 행정심판법에서 정하는 바에 따라 행정심판을 청구할 수 있습니다.</p>
								<p>부산도시공사는 개인정보보호법 등 관련법령상의 개인정보보호 규정을 준수하며, 개인정보처리방침은 아래와 같습니다.
								이 개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경내용의 추가, 삭제 및 정정이 있는 경우 홈페이지를 통해 고지할 것입니다.</p>
							</div>
						</div>
						
						<p class="depth2-title">1조. 개인정보의 처리 목적</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>개인정보를 다음의 목적을 위해 처리합니다. 처리한 개인정보는 다음의 목적이외의 용도로는 사용되지 않으며 이용 목적이 변경될 시에는 사전 동의를 구할 예정입니다.</p>
								<p><strong class="strong_st">1. 홈페이지 관리</strong></p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>본인 식별·인증, 고충처리 등을 목적으로 개인정보를 처리합니다.</li>
									</ul>
								</span>
								<p><strong class="strong_st">2. 민원사무 처리</strong></p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>민원인의 신원 확인, 민원사항 확인, 사실 조사를 위한 연락·통지, 처리결과 통보 등을 목적으로 개인정보를 처리합니다.</li>
									</ul>
								</span>
								<p><strong class="strong_st">3. 서비스 제공</strong></p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>부산도시공사 사업관련 컨텐츠 제공, 임대·토지·보상관리 등 서비스 제공과 관련한 목적으로 개인정보를 처리합니다.</li>
									</ul>
								</span>
							</div>
						</div>
						
						<p class="depth2-title">2조. 개인정보 처리 및 보유기간, 처리하는 개인정보 항목</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p><strong class="strong_st">1. 도시공사에서 보유하고 있는 개인정보 파일</strong></p>
								<table class="orgTable protectTable">
									<tbody>
										<tr>
											<th colspan="5">개인정보파일 및 상세내용</th>
										</tr>
										<tr>
											<th scope="col">개인정보파일</th>
											<th scope="col">보유근거</th>
											<th scope="col">보유목적</th>
											<th scope="col">주요항목</th>
											<th scope="col">보유기간</th>
										</tr>
										<tr>
											<td scope="col">주택임대</td>
											<td scope="col" rowspan="4">주택공급에 관한<br> 규칙</td>
											<td scope="col">주택임대 입주자현황 관리</td>
											<td scope="col">성명,주소,주민번호<br>,연락처,세대원정보</td>
											<td scope="col">영구</td>
										</tr>
										<tr>
											<td scope="col">매입임대</td>
											<td scope="col">매입임대 입주자현황 관리</td>
											<td scope="col">성명,주소,주민번호<br>,연락처</td>
											<td scope="col">영구</td>
										</tr>
										<tr>
											<td scope="col">전세임대</td>
											<td scope="col">전세임대 입주자현황 관리</td>
											<td scope="col">성명,주소,주민번호<br>,연락처</td>
											<td scope="col">영구</td>
										</tr>
										<tr>
											<td scope="col">재개발임대</td>
											<td scope="col">재개발임대 입주자현황 관리</td>
											<td scope="col">성명,주소,주민번호<br>,연락처</td>
											<td scope="col">영구</td>
										</tr>
										<tr>
											<td scope="col">상가임대</td>
											<td scope="col">상가건물임대차보호법</td>
											<td scope="col">상가임대 입주자현황 관리</td>
											<td scope="col">성명,주소,주민번호<br>,연락처</td>
											<td scope="col">영구</td>
										</tr>
										<tr>
											<td scope="col">부금수납정보파일</td>
											<td scope="col">주택공급에 관한<br>규칙</td>
											<td scope="col">부금수납관리</td>
											<td scope="col">성명,주소,주민번호<br>,연락처</td>
											<td scope="col">영구</td>
										</tr>
										<tr>
											<td scope="col">보상관련대상자</td>
											<td scope="col">공익사업을 위한 토지 <br>등의 취득 및 보상에 관한<br>법률</td>
											<td scope="col">토지 등의 취득 및 보상</td>
											<td scope="col">성명,주소,주민번호<br>,보상내역</td>
											<td scope="col">영구</td>
										</tr>
										<tr>
											<td scope="col">분양계약자<br>인적사항 관리</td>
											<td scope="col">우리공사 분양규정</td>
											<td scope="col">계약자 신상정보 관리</td>
											<td scope="col">성명,주소,주민번호<br>,연락처,사업자등록번호</td>
											<td scope="col">영구</td>
										</tr>
										<tr>
											<td scope="col">출자토지 계약자<br>인적사항 관리</td>
											<td scope="col">우리공사 재산관리규정</td>
											<td scope="col">계약자 신상정보 관리</td>
											<td scope="col">성명,주소,주민번호<br>,연락처,사업자등록번호</td>
											<td scope="col">영구</td>
										</tr>
									</tbody>
								</table>
								<p><strong class="strong_st">2. 상기의 자료는 개인정보보호 종합포털에서도 조회가 가능합니다.</strong></p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>개인정보보호 종합포털(<a href="www.privacy.go.kr" target="blank">www.privacy.go.kr</a>)에서, 개인정보민원 ▷ 개인정보의 열람 등 요구 ▷개인정보파일 목록 검색 ▷ 기관명 입력 후 조회</li>
									</ul>
								</span>
							</div>
						</div>
						
						<p class="depth2-title">3조. 개인정보 제3자 제공에 관한 사항</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>부산도시공사가 수집·보유하고 있는 개인정보는 이용자의 동의 없이는 제3자에게 제공하지 않으며 다음의 경우에는 개인정보를 제3자에게 제공할 수 있습니다.</p>
								<p><strong class="strong_st">1. 정보주체로부터 별도의 동의를 받은 경우</strong></p>
								<p><strong class="strong_st">2. 법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</strong></p>
								<p><strong class="strong_st">3. 정보주체 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 정보주체 또는
								 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우</strong></p>
								<p><strong class="strong_st">4. 다음 각 호의 어느 하나에 해당하는 경우에는 정보주체 또는 제3자의 이익을 부당하게 침해할 우려가 있을 때를 제외하고는 이용자의 개인정보를 목적 외의 용도로 이용하거나 
								이를 제3자에게 제공할 수 있습니다.</strong></p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>가. 정보주체로부터 별도의 동의를 받은 경우</li>
										<li>나. 다른 법률에 특별한 규정이 있는 경우</li>
										<li>다. 정보주체 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 정보주체 또는 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우</li>
										<li>라. 통계작성 및 학술연구 등의 목적을 위하여 필요한 경우로서 특정 개인을 알아볼 수 없는 형태로 개인정보를 제공하는 경우</li>
										<li>마. 개인정보를 목적 외의 용도로 이용하거나 이를 제3자에게 제공하지 아니하면 다른 법률에서 정하는 소관업무를 수행할 수 없는 경우로서 보호위원회의 심의·의결을 거친 경우</li>
										<li>바. 조약, 그 밖의 국제협정의 이행을 위하여 외국정부 또는 국제기구에 제공하기 위하여 필요한 경우</li>
										<li>사. 범죄의 수사와 공소의 제기 및 유지를 위하여 필요한 경우</li>
										<li>아. 법원의 재판업무 수행을 위하여 필요한 경우</li>
										<li>자. 형(刑) 및 감호, 보호처분의 집행을 위하여 필요한 경우</li>
									</ul>
								</span>
							</div>
						</div>
						
						<p class="depth2-title">4조. 개인정보처리 위탁에 관한 사항</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p><strong class="strong_st">개인정보 처리를 위탁하는 사항은 다음과 같습니다.</strong></p>
								<table class="orgTable protectTable">
									<tbody>
										<tr>
											<th scope="col"><b>위탁하는 업무 내용</b></th>
											<th scope="col"><b>취급개인정보</b></th>
											<th scope="col"><b>수탁업체</b></th>
										</tr>
										<tr>
											<td>용지보상채권 지급대행</td>
											<td>용지보상채권 지급(성명,주민번호,계좌)</td>
											<td>㈜우리은행</td>
										</tr>
										<tr>
											<td rowspan="3">임대주택관리</td>
											<td rowspan="3">입주민관련 개인정보(성명,주민번호,주소)</td>
											<td>㈜남부공영</td>
										</tr>
										<tr><td>㈜장천</td></tr>
										<tr><td>㈜삼성티엠티스</td></tr>
										<tr>
											<td>정보시스템 통합유지보수 관리</td>
											<td>UBIS 보유 개인정보(성명,주민번호,주소)</td>
											<td>㈜앤시정보기술</td>
										</tr>
									</tbody>
								</table>
								<p>위탁계약 시 개인정보보호 관련 법규의 준수, 개인정보에 관한 제3자 제공 금지 및 책임부담 등을 명확히 규정하고, 당해 계약내용을 서면 및 전자 보관하고 있습니다. 업체 변경 시 공지사항 및 개인정보처리방침을 통해 고지하겠습니다.</p>
							</div>
						</div>
						
						<p class="depth2-title">5조. 정보주체 권리·의무 및 그 행사방법에 관한 사항</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>이용자는 정보주체로서 다음과 같은 권리를 행사할 수 있습니다.</p>
								<p><strong class="strong_st">1. 정보주체로부터 별도의 동의를 받은 경우</strong></p>
								<p>부산도시공사에서 보유하고 있는 개인정보파일은「개인정보보호법」제35조(개인정보의 열람)에 따라 열람을 요구할 수 있습니다. 다만 개인정보 열람 요구는 「개인정보보호법」 제35조 제5항에 의하여 다음과 같이 제한될 수 있습니다.</p>
								<p>가. 법률에 따라 열람이 금지되거나 제한되는 경우</p>
								<p>나. 다른 사람의 생명·신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해 할 우려가있는 경우</p>
								<p>다. 공공기관이 다음 각 목의 어느 하나에 해당하는 업무를 수행할 때 중대한 지장을 초래하는 경우</p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>① 조세의 부과·징수 또는 환급에 관한 업무</li>
										<li>②「초·중등교육법」및「고등교육법」에 따른 각급 학교,「평생교육법」에 따른 평생교육시설, 그 밖의 다른 법률에 따라 설치된 고등교육기관에서의 성적 평가 또는 입학자 선발에 관한 업무</li>
										<li>③ 학력·기능 및 채용에 관한 시험, 자격 심사에 관한 업무</li>
										<li>④ 보상금·급부금 산정 등에 대하여 진행 중인 평가 또는 판단에 관한 업무</li>
										<li>⑤ 다른 법률에 따라 진행 중인 감사 및 조사에 관한 업무</li>
									</ul>
								</span>
								<p><strong class="strong_st">2. 개인정보 정정·삭제 요구</strong></p>
								<p>부산도시공사에서 보유하고 있는 개인정보파일에 대해서는「개인정보보호법」제36조 (개인정보의 정정·삭제)에 따라 부산도시공사에 개인정보의 정정·삭제를 요구할 수 있습니다. 
								다만, 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.</p>
								<p><strong class="strong_st">3. 개인정보 처리정지 요구</strong></p>
								<p>부산도시공사에서 보유하고 있는 개인정보파일에 대해서는「개인정보보호법」제37조(개인정보의 처리정지 등)에 따라 부산도시공사에 개인정보의 처리정지를 요구할 수 있습니다. 
								또한 만 14세 미만 아동의 법정대리인은 부산도시공사에 그 아동의 개인정보의 열람, 정정·삭제, 처리정지 요구를 할 수 있습니다. 
								다만, 개인정보처리정지 요구시「개인정보보호법」제 37조 제 2항에 의하여 처리정지 요구가 거절될 수 있습니다.</p>
								<p>가. 법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</p>
								<p>나. 다른 사람의 생명·신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해 할 우려가 있는 경우</p>
								<p>다. 공공기관이 개인정보를 처리하지 아니하면 다른 법률에서 정하는 소관 업무를 수행할 수 없는 경우</p>
								<p>라. 개인정보를 처리하지 아니하면 정보주체와 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 정보주체가 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우</p>
								<p><strong class="strong_st">4. 개인정보의 열람, 정정·삭제, 처리정지 요구에 대해서는 10일 이내에 해당 사항에 대한 부산도시공사의 조치를 통지합니다. 
								개인정보의 열람, 정정·삭제, 처리정지 요구는 아래와 같이 해당 부서를 통해서 가능합니다.</strong></p>
								<table class="orgTable protectTable">
									<tbody>
										<tr>
											<th colspan="5">개인정보 열람, 정정, 삭제청구 등 처리부서 현황</th>
										</tr>
										<tr>
											<th scope="col">개인정보파일</th>
											<th scope="col">보유부서(처리부서)</th>
											<th scope="col">열람 등 청구장소</th>
											<th scope="col">전화번호</th>
											<th scope="col">비고</th>
										</tr>
										<tr>
											<td>주택임대</td>
											<td>주택관리단</td>
											<td>종합상담실(당사 사옥 1층)</td>
											<td>051-810-1271<br>051-810-1273</td>
											<td></td>
										</tr>
										<tr>
											<td>매입임대</td>
											<td>주택관리단</td>
											<td rowspan="4">종합상담실(당사 사옥 2층)</td>
											<td>051-810-1323</td>
											<td></td>
										</tr>
										<tr>
											<td>전세임대</td>
											<td>주택관리단</td>
											<td>051-810-1494</td>
											<td></td>
										</tr>
										<tr>
											<td>재개발임대</td>
											<td>주택관리단</td>
											<td>051-810-1324</td>
											<td></td>
										</tr>
										<tr>
											<td>상가임대</td>
											<td>주택관리단</td>
											<td>051-810-1291</td>
											<td></td>
										</tr>
										<tr>
											<td>부금수납정보관리</td>
											<td>재무팀</td>
											<td>종합상담실(당사 사옥 1층)</td>
											<td>051-810-1258</td>
											<td></td>
										</tr>
										<tr>
											<td>보상관련대상자</td>
											<td>보상팀</td>
											<td>보상팀(당사 사옥 3층)</td>
											<td>051-810-1453</td>
											<td></td>
										</tr>
										<tr>
											<td>분양계약자<br>인적사항 관리</td>
											<td rowspan="2">마케팅실</td>
											<td>마케팅실(당사 사옥 3층)</td>
											<td>051-810-1253</td>
											<td></td>
										</tr>
										<tr>
											<td>출자토지 계약자<br>인적사항 관리</td>
											<td>마케팅실(당사 사옥 3층)</td>
											<td>051-810-1274</td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						
						<p class="depth2-title">6조. 개인정보 파기 절차 및 방법</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>부산도시공사는 원칙적으로 개인정보의 보유기간이 경과했거나 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다.</p>
								<p><strong class="strong_st">1. 파기절차</strong></p>
								<p>이용자가 입력한 정보는 보유기간이 경과했거나 처리목적 달성 후 내부 방침 및 관련 법령에 따라 파기합니다.</p>
								<p><strong class="strong_st">2. 파기기한</strong></p>
								<p>이용자의 개인정보는 개인정보의 보유기간이 경과된 경우 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 
								5일 이내에 그 개인정보를 파기합니다.</p>
								<p><strong class="strong_st">3. 파기방법</strong></p>
								<p>전자적 파일 형태인 경우 : 복원이 불가능한 방법으로 영구 삭제<br/>전자적 파일 외의 기록물, 인쇄물 등 : 파쇄 또는 소각</p>
							</div>
						</div>
						
						<p class="depth2-title">7조. 개인정보 안전성 확보 조치</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>부산도시공사는 「개인정보보호법」제29조에 따라 다음과 같이 안전성 확보에 필요한 기술적, 관리적, 물리적 조치를 하고 있습니다.</p>
								<p><strong class="strong_st">1. 내부관리계획의 수립 및 시행</strong></p>
								<p>부산도시공사는 ‘개인정보의 안전성 확보조치 기준’(행정안전부고시 제2011-제43호)에 의거하여 내부관리계획(‘11.12)을 수립 및 시행하고 있습니다.</p>
								<p><strong class="strong_st">2. 개인정보취급자 지정의 최소화 및 교육</strong></p>
								<p>개인정보취급자의 지정을 최소화하고 정기적인 교육을 시행하고 있습니다.</p>
								<p><strong class="strong_st">3. 개인정보에 대한 접근 제한</strong></p>
								<p>개인정보처리시스템의 접근에 대한 차등 권한 부여 및 시스템 접속에 대한 접근 통제를 실시하고 있으며, 침입차단시스템 등<br> 정보보안시스템을 이용하여 외부로부터의 무단 접근을 방지하고 있고, 개인정보취급자가 정보통신망을 통해 외부에서
								 개인정보처리시스템에 접속하는 경우에는 가상사설망(VPN:Virtual Private Network)을 이용하고 있습니다.</p>
								<p><strong class="strong_st">4. 개인정보의 암호화</strong></p>
								<p>개인정보는 암호화되어 저장 및 관리되고 있으며, 중요한 데이터 저장·전송 시 별도 보안기능을 사용하여 암호화하고 있습니다.</p>
								<p><strong class="strong_st">5. 해킹 등에 대비한 기술적 대책</strong></p>
								<p>해킹이나 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신·점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적, 물리적으로 감시 및 차단하고 있습니다.</p>
								<p><strong class="strong_st">6. 비인가자에 대한 출입 통제</strong></p>
								<p>개인정보를 보관하고 있는 개인정보처리시스템의 물리적 보관 장소를 별도로 두고 이에 대해 출입통제 절차를 수립, 운영하고 있습니다.</p>
							</div>
						</div>
						
						<p class="depth2-title">8조. 권익침해 구제방법</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>개인정보주체는 개인정보침해로 인한 구제를 받기 위하여 한국인터넷진흥원 개인정보 침해신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다.</p>
								<p>1. 개인정보 침해신고센터, 개인정보 분쟁조정위원회 : 국번없이 118번</p>
								<p>2. 대검찰청 첨단범죄수사과 : 02-3480-2000 (<a href="http://www.spo.go.kr" target="_blank">http://www.spo.go.kr</a>)</p>
								<p>3. 경찰청 사이버테러대응센터 : 02-392-0330 (<a href="http://www.ctrc.go.kr" target="blank">http://www.ctrc.go.kr</a>)</p>
								<p>「개인정보보호법」제35조(개인정보의 열람), 제36조(개인정보의 정정ㆍ삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에 대하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익의 침해를 받은 자는 
								행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다. 
								※ 행정심판에 대해 자세한 사항은 법제처 (<a href="http://www.moleg.go.kr" target="blank">http://www.moleg.go.kr</a>) 홈페이지를 참고하시기 바랍니다.</p>
							</div>
						</div>
						
						<p class="depth2-title">9조. 개인정보 보호책임자</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>부산도시공사는 개인정보의 적법성 및 절차의 적정성을 확보하여 정보주체의 권익보호 및 공공업무의 적정한 수행을 도모하기 위해 개인정보보호책임관 및 실무담당자를 다음과 같이 지정·운영하고 있습니다.</p>
								<table class="orgTable protectTable">
									<tbody>
										<tr>
											<th scope="col">구분</th>
											<th scope="col">개인정보 보호책임관</th>
											<th scope="col">개인정보 보호담당자</th>
										</tr>
										<tr>
											<td>담당부서</td>
											<td>총무팀</td>
											<td>총무팀</td>
										</tr>
										<tr>
											<td>성명(직책)</td>
											<td>김태용 팀장</td>
											<td>김민 과장</td>
										</tr>
										<tr>
											<td>전화</td>
											<td>051-810-1240</td>
											<td>051-810-1304</td>
										</tr>
										<tr>
											<td>이메일</td>
											<td>be@bmc.busan.kr</td>
											<td>ubisman@bmc.busan.kr</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						
						<p class="depth2-title">10조. 개인정보처리방침 변경</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p><strong class="strong_st">이 개인정보처리방침은 2015.05. 부터 적용됩니다.</strong></p>
								<p><strong class="strong_st">이전의 개인정보처리방침은 아래에서 확인할 수 있습니다.</strong></p>
								<!-- <p><a href="javascript:void(window.open('/page/privacy_pre_4.jsp', '_blank', 'width=800,height=800,scrollbars=yes'))">2011.11.02 ~ 2015.05.05 적용 지침 (클릭)</a></p>
								<p><a href="javascript:void(window.open('/page/privacy_pre_3.jsp', '_blank', 'width=800,height=800,scrollbars=yes'))">2011.07.14 ~ 2011.11.01 적용 지침 (클릭)</a></p>
								<p><a href="javascript:void(window.open('/page/privacy_pre_2.jsp', '_blank', 'width=800,height=800,scrollbars=yes'))">2010.07.27 ~ 2011.07.13 적용 지침 (클릭)</a></p>
								<p><a href="javascript:void(window.open('/page/privacy_pre_1.jsp', '_blank', 'width=800,height=800,scrollbars=yes'))">2010.07.27 이전 적용 지침 (클릭)</a></p> -->
								<select id="privacy_select" title="이전지침 클릭" >
									<option value="/page/privacy_pre_4.jsp">2011.11.02 ~ 2015.05.05 이전 적용 지침</option>
									<option value="/page/privacy_pre_3.jsp">2011.07.14 ~ 2011.11.01 이전 적용 지침</option>
									<option value="/page/privacy_pre_2.jsp">2010.07.27 ~ 2011.07.13 이전 적용 지침</option>
								<option value="/page/privacy_pre_1.jsp">2010.07.27 이전 적용 지침</option>
								</select>
							<button id="GoPre" class="btnGO" title="새창">이동</button>
							</div>
						</div>
						
						<p class="lawbtn_wrap">
							<a href="javascript:window.close();" class="buttonT3 icon down"><span style="color:#ffffff;">닫기</span></a>
						</p>
						
					</div>
					
					<!-- TODO 두번째 메뉴 -->
					<div id="tab2" class="tab_content">
						<p class="depth2-title">영상정보처리기기 운영·관리 방침</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>부산도시공사(이하 본 기관이라 함)는 영상정보처리기기 운영·관리 방침을 통해 본 기관에서 처리하는 영상정보가 어떠한 용도와 방식으로 이용 관리되고 있는지 알려드립니다.</p>
							</div>
						</div>
						
						<p class="depth2-title">1. 영상정보처리기기의 설치 근거 및 설치 목적</p>
						<div class="inr vision">
							<div class="area_box box2">
								<p>본 기관은 개인정보보호법 제25조 제1항에 따라 다음과 같은 목적으로 영상정보처리기기를 설치·운영 합니다.</p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>시설안전 및 화재 예방</li>
										<li>고객의 안전을 위한 범죄 예방</li>
									</ul>
								</span>
								<p><strong class="strong_st">※주차장에 설치하는 경우</strong></p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>차량도난 및 파손방지<br/>→ 주차대수 30대를 초과하는 규모의 경우「주차장법 시행규칙」제6조제1항을 근거로 설치·운영 가능</li>
									</ul>
								</span>
							</div>
						</div>
						
						<p class="depth2-title">2. 설치 대수, 설치 위치 및 촬영범위</p>							
						<div class="inr vision">
							<div class="area_box box2"><br/>
								<table class="orgTable protectTable">
									<tbody>
									<tr>
									  <th width="20%">설치 대수</th>
									  <th>설치 위치 및 촬영 범위</th>
									</tr>
									<tr>
										<td style="text-align :center;">20대</td>
										<td>건물로비, 지하주차장, 승강기 및 사무실 내부</td>
									  </tr>
								  </tbody>
								</table>
							</div>
						</div>
						
						<p class="depth2-title">3. 관리책임자 및 접근권한자</p>							
						<div class="inr vision">
							<div class="area_box box2">
								<p>귀하의 영상정보를 보호하고 개인영상정보와 관련한 불만을 처리하기 위하여 아래와 같이 개인영상정보 보호책임자를 두고 있습니다.</p>
								<table class="orgTable protectTable">
									<tbody>
										<tr>
											<th colspan="2">구분</th>
											<th>이름</th>
											<th>직위</th>
											<th>소속</th>
											<th>연락처</th>
										</tr>
										<tr>
											<td rowspan="3">지하주차장,<br>승강기,로비,<br>전산실</td>
											<td>관리책임자</td>
											<td>김태용</td>
											<td>총무팀장</td>
											<td rowspan="3">총무팀</td>
											<td>051-810-1240</td>
										</tr>
										<tr>
											<td rowspan="2">접근권한자</td>
											<td>조진호</td>
											<td>차장</td>
											<td>051-810-1242</td>
										</tr>
										<tr>
											<td>김민</td>
											<td>과장</td>
											<td>051-810-1304</td>
										</tr>
										<tr>
											<td rowspan="2">보상팀</td>
											<td>관리책임자</td>
											<td>전진안</td>
											<td>보상팀장</td>
											<td rowspan="2">보상팀</td>
											<td>051-810-1310</td>
										</tr>
										<tr>
											<td>접근권한자</td>
											<td>구경완</td>
											<td>주임</td>
											<td>051-810-1463</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						
						<p class="depth2-title">4. 영상정보의 촬영시간, 보관기간, 보관장소 및 처리방법</p>							
						<div class="inr vision">
							<div class="area_box box2"><br/>
								<table class="orgTable protectTable">
									<tbody>
									<tr>
									  <th width="20%">촬영시간</th>									  
									  <th width="30%">보관기간</th>
									  <th width="*">보관장소</th>
									</tr>
									<tr>
									  <td>24시간</td>
									  <td>촬영일로부터 30일</td>
									  <td>로비안내데스크(접근방지 시건장치)</td>
									</tr>
									</tbody>
								</table>
								<p>- 처리방법 : 개인영상정보의 목적 외 이용, 제3자 제공, 파기, 열람 등 요구에 관한 사항을 기록·관리하고, 보관기간 만료 시 복원이 불가능한 방법으로 영구 삭제(출력물의 경우 파쇄 또는 소각)합니다.</p>
							</div>
						</div>
						
						<p class="depth2-title">5. 개인영상정보의 확인 방법 및 장소에 관한 사항</p>							
						<div class="inr vision">
							<div class="area_box box2">
								<p><strong class="strong_st">확인 방법</strong></p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>영상정보 관리책임자에게 미리 연락하고 본 기관을 방문하시면 확인 가능합니다.</li>
									</ul>
								</span>
								<p><strong class="strong_st">확인 장소</strong></p>
								<span class="intro-txt">
									<ul class="amendsUl">
										<li>총무팀</li>
									</ul>
								</span>
							</div>
						</div>
						
						<p class="depth2-title">6. 정보주체의 영상정보 열람 등 요구에 대한 조치</p>							
						<div class="inr vision">
							<div class="area_box box2">
								<p>귀하는 개인영상정보에 관하여 열람 또는 존재확인·삭제를 원하는 경우 언제든지 영상정보처리기기 운영자에게 요구하실 수 있습니다. 
								단, 귀하가 촬영된 개인영상정보 및 명백히 정보주체의 급박한 생명, 신체, 재산의 이익을 위하여 필요한 개인영상정보에 한정됩니다. 본 기관은 개인영상정보에 관하여 열람 또는 존재확인·삭제를 요구한 경우 지체 없이 필요한 조치를 하겠습니다.</p>
							</div>
						</div>
						
						<p class="depth2-title">7. 영상정보의 안전성 확보조치</p>							
						<div class="inr vision">
							<div class="area_box box2">
								<p>본 기관에서 처리하는 영상정보는 암호화 및 통제구역 지정을 통하여 안전하게 관리되고 있습니다. 또한 본 기관은 개인영상정보보호를 위한 관리적 대책으로서 개인정보에 대한 접근 권한을 차등부여하고 있고, 
								개인영상정보의 위·변조 방지를 위하여 개인영상정보의 생성 일시, 열람 시 열람 목적 · 열람자 · 열람 일시 등을 기록하여 관리하고 있습니다. 
								이 외에도 개인영상정보의 안전한 물리적 보관을 위하여 잠금장치를 설치하고 있습니다.</p>
							</div>
						</div>
						
						<p class="depth2-title">8. 개인정보 처리방침 변경에 관한 사항</p>							
						<div class="inr vision">
							<div class="area_box box2">
								<p>이 영상정보처리기기 운영ㆍ관리방침은 2015년 4월에 제정되었으며 법령ㆍ정책 또는 보안기술의 변경에 따라 내용의 추가ㆍ삭제 및 수정이 있을 시에는 시행하기 최소 7일전에 본 기관 홈페이지를 통해 변경사유 및 내용 등을 공지하도록 하겠습니다.</p>
								<p>- 공고일자 : 2015년 4월 27일 / 시행일자 : 2015년 4월 27일</p>
							</div>
						</div>
						
						<p class="lawbtn_wrap">
							<a href="javascript:window.close();" class="buttonT3 icon down"><span style="color:#ffffff;">닫기</span></a>
						</p>
					</div>
					<!-- #tab2 -->
				</div>
				<!-- .tab_container -->
			</div>
			<!-- #container -->
		</div>
	</div>
</div>

</body>
</html>