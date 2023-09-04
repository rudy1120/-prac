<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>BMC가 만든 도심 속 녹색랜드마크!</title>

	<link rel="stylesheet" type="text/css" href="/bmc/jsp/apply/green/font/font.css">
		<link rel="stylesheet" href="/bmc/jsp/apply/green/css/reset.css">
		<link rel="stylesheet" href="/bmc/jsp/apply/green/slick/slick-theme.css">
		<link rel="stylesheet" href="/bmc/jsp/apply/green/slick/slick.css">
		<link rel="stylesheet" href="/bmc/jsp/apply/green/css/landmark.css">
   		
		<script src="/bmc/jsp/apply/green/js/jquery-3.3.1.min.js"></script>
		<script src="/bmc/jsp/apply/green/js/slick.min.js"></script>
		<script src="/bmc/jsp/apply/green/js/landmark.js"></script>
		 <!--[if lt IE]>
			<script src="assets/js/html5shiv.min.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->
		<style>
			.landmark-container {position:relative;}
			.landmark-popup {position: fixed; top:250px; right: 1%; width: 200px; height: 360px; z-index:10;}
			.landmark-popup a {display: block; width: 100%; height:100%}
			.landmark-popup a img {display: block;}
			@media screen and (max-width: 1023px){
				.landmark-tabs li {width: 18%;}
				.landmark-tab {font-size: 1.2rem;}
			}
			@media screen and (max-width: 767px){
				.landmark-tabs li {width: 23%;}
				.landmark-popup {display: none;}
			}
			@media screen and (max-width: 576px){
			.landmark-container .landmark-inner {width:100%;}
				.landmark-inner .landmark-h1 {width: 80%; margin:0 auto; font-size: 2.5rem; word-break: keep-all;}
				.landmark-h1 span {display: inline;}
				.landmark-tabs li {width: 31%;}
				.landmark-cont-dd > div {float: none; width: 90%; margin: 0 auto;}
			}
		</style>
</head>
<body>
	<div class="landmark-wrap">
		<div class="landmark-header">
			<div class="landmark-inner">
				<h1 class="landmark-h1"><span>BMC가 만든 도심 속</span> 
				<span style="color:#5E7944;font-weight:500;text-shadow:-1px 0 white,0 1px white,1px 0 white,0 -1px white;">
					녹색</span>랜드마크!</h1>
			</div>
		</div>
		<div class="landmark-container">
			<div class="landmark-popup"><a href="/bmc/vr/viewVr.do" target="blank"><img src="/bmc/jsp/apply/green/images/sidebanner.png"></a> </div>
			<div class="landmark-inner">
				<div class="landmark-tabs-group">
					<ul class="landmark-tabs">
						<li class="active"><a href="#landmarkTab01" class="landmark-tab" title="오시리아 관광단지">오시리아관광단지<br>조성사업</a></li>
						<li><a href="#landmarkTab02" class="landmark-tab" title="해운대관광리조트">해운대관광리조트<br>도시개발사업</a></li>
						<li><a href="#landmarkTab03" class="landmark-tab">국제산업물류도시<br>조성사업</a></li>
						<li><a href="#landmarkTab04" class="landmark-tab">오리일반산업단지<br>조성사업</a></li>
						<li><a href="#landmarkTab05" class="landmark-tab">일광지구<br>도시개발사업</a></li>
						<li><a href="#landmarkTab06" class="landmark-tab">임대주택 조경공간<br>시설개선사업</a></li>
					</ul>
				</div>
				<div id="landmarkTab01" class="landmark-cont">
					<h2 class="landmark-h2">오시리아관광단지 조성사업</h2>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0101" class="landmark-slider landmark-left">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0101.jpg" alt="오시리아 해안산책로 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0102.jpg" alt="오시리아 해안산책로 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0103.jpg" alt="오시리아 해안산책로 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0104.jpg" alt="오시리아 해안산책로 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0105.jpg" alt="오시리아 해안산책로 전경">
								</div>
							</div>
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0101.jpg" alt="오시리아 해안산책로 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0102.jpg" alt="오시리아 해안산책로 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0103.jpg" alt="오시리아 해안산책로 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0104.jpg" alt="오시리아 해안산책로 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0105.jpg" alt="오시리아 해안산책로 전경">
								</div>
							</div>
						</div>
						<div class="landmark-list landmark-right">
							<h3 class="landmark-h3">오시리아 해안산책로</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">해안산책, 갈맷길 투어, 조약돌 쌓기, 아난티코브 체험 <small>(서점,카페 등)</small></dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 기장군 기장읍 시랑리·연화리 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span><b>지하철</b><i>동해남부선 오시리아역 하차</i></span>
										<span><b>버스</b><i>동암후문(16080,16098) 하차(100, 139, 181)</i></span>
										<span><b>자가</b><i>“더 이스트 인 부산”, “힐튼호텔” 주차가능</i></span>	
										
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0102" class="landmark-slider landmark-right">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0201.jpg" alt="천변공원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0202.jpg" alt="천변공원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0203.jpg" alt="천변공원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0204.jpg" alt="천변공원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0205.jpg" alt="천변공원 전경">
								</div>
								
							</div>
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0201.jpg" alt="천변공원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0202.jpg" alt="천변공원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0203.jpg" alt="천변공원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0204.jpg" alt="천변공원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0205.jpg" alt="천변공원 전경">
								</div>
								
							</div>
						</div>
						<div class="landmark-list landmark-left">
							<h3 class="landmark-h3">천변 공원</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">해안산책, 오시리아 기차놀이터 방문, 이케아 이용 후 휴식공간</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 기장군 기장읍 시랑리·연화리 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span><b>지하철</b><i>동해남부선 송정역 하차</i></span>
										<span><b>버스</b><i><span>송정2휴먼시아(09004,09005) 하차</span>
											<span>(38,39,40,63,139,182,200,1001,1003) 후</span> 
											<span>도보 10분 이내</span></i></span>
										<span><b>자가</b><i>오시리아 역 앞 주차장 이용</i></span>	
										
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0103" class="landmark-slider landmark-left">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0301.jpg" alt="광장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0302.jpg" alt="광장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0303.jpg" alt="광장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0304.jpg" alt="광장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0305.jpg" alt="광장 전경">
								</div>
								
							</div>
							<div class="landmark-slider-nav">
						
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0301.jpg" alt="광장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0302.jpg" alt="광장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0303.jpg" alt="광장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0304.jpg" alt="광장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0305.jpg" alt="광장 전경">
								</div>
						
							</div>
						</div>
						<div class="landmark-list landmark-right">
							<h3 class="landmark-h3">광장</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">물음표 광장에서 휴식, 만남의 장소, 아울렛 이용</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 기장군 기장읍 시랑리 430 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span>
											<b>지하철</b>
											<i>동해남부선 오시리아역 하차 후 도보 20분 이내 </i>
										</span>
										<span><b>버스</b><i><span>동부산관광단지(16350) 하차(139,1001),</span> 
											<span>동부산관광단지(16300) 하차(139,185,1001)</span></i></span>
										<span><b>자가</b><i><span>“롯데프리미엄아울렛 동부산점”,</span> 
											<span>“동부산관광단지 롯데몰”,</span>
											<span>“국립수산과학원” 주차가능</span></i></span>
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0104" class="landmark-slider landmark-right">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0401.jpg" alt="워터프론트파크 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0402.jpg" alt="워터프론트파크 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0403.jpg" alt="워터프론트파크 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0404.jpg" alt="워터프론트파크 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0405.jpg" alt="워터프론트파크 전경">
								</div>
								
								
							</div>
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0401.jpg" alt="워터프론트파크 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0402.jpg" alt="워터프론트파크 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0403.jpg" alt="워터프론트파크 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0404.jpg" alt="워터프론트파크 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0405.jpg" alt="워터프론트파크 전경">
								</div>
								
							</div>
						</div>
						<div class="landmark-list landmark-left">
							<h3 class="landmark-h3">워터프론트 파크</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">연인과 함께 카페드220볼트(카페) 이용 후 공원 산책, 추운겨울에도 히터가 있는 파고라 이용, 해안경관 및 
										야생화 감상
										</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 기장군 기장읍 시랑리 531 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span><b>지하철</b><i>동해남부선 오시리아역 하차 후 도보 20분 이내 </i></span>
										<span><b>버스</b><i><span>당사양경마을(16002,16095) 하차</span>
											<span>(100,181,185,해운대구9) 후 도보3분</span>
											</i></span>
										<span><b>자가</b><i><span>“동부산관광단지 롯데몰”,</span> 
											<span>“국립수산과학원” 주차가능</span></i></span>
									</dd>
								</dl>
							</div>
						</div>
					</div>
					
					<div class="landmark-cont-dd clear">
						<div id="landSlider0105" class="landmark-slider landmark-left">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0501.jpg" alt="생태숲 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0502.jpg" alt="생태숲 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0503.jpg" alt="생태숲 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0504.jpg" alt="생태숲 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/0505.jpg" alt="생태숲 전경">
								</div>
							</div>
							
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0501.jpg" alt="생태숲 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0502.jpg" alt="생태숲 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0503.jpg" alt="생태숲 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0504.jpg" alt="생태숲 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/0505.jpg" alt="생태숲 전경">
								</div>
						
							</div>
						</div>
						<div class="landmark-list landmark-right">
							<h3 class="landmark-h3">오시리아 생태숲</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">가벼운 숲길 하이킹, 자연 생태 학습</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산 기장군 기장읍 시랑리 산 45-31 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span>
											<b>지하철</b>
											<i>오시리아역(동해선) 하차 후 도보 11분 </i>
										</span>
										<span>
											<b>버스</b>
											<i>오시리아역.국립부산과학관입구(16348) 하차(139,185,1001) 후 도보 5분</i>
										</span>
										<span>
											<b>자가</b>
											<i><span>오시리아 역 앞 주차장,</span> 
											<span>국립부산과학관 주차장 이용</span></i>
										</span>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
				<div id="change">
					<c:if test="${not empty chargeList}">
						<div class="page_infor">
							<c:forEach var="charge" items="${chargeList }">
								<ul>
									<li class="ic1" >
										<c:if test="${!empty charge.chargeDepNm}"> 담당부서 및 담당자 : ${charge.chargeDepNm}&nbsp; </c:if>
										<c:if test="${!empty charge.chargeFnm}">${charge.chargeFnm}</c:if>	
									</li>
									<li class="ic3" >
										<c:if test="${!empty charge.chargeTel}">전화번호 : ${charge.chargeTel}</c:if>
									</li>
									<li class="mod_data">
										<c:if test="${menuVO.menuType == 'C' or menuVO.menuType == 'B' }">
											최종 수정일 : 
											<c:choose>
											<c:when test="${not empty menuVO.cmsContentDate}">
												<fmt:formatDate value="${menuVO.cmsContentDate}" pattern="yyyy-MM-dd" />
											</c:when>
											<c:otherwise>
												<fmt:formatDate value="${menuVO.modDt}" pattern="yyyy-MM-dd" />
											</c:otherwise>
											</c:choose>
										</c:if>
									</li>
								</ul>
							</c:forEach>
						</div>
					</c:if>
				</div>
				<div id="landmarkTab02" class="landmark-cont">
					<h2 class="landmark-h2">해운대관광리조트 도시개발사업</h2>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0201" class="landmark-slider landmark-left">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single slide-single01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0118.jpg" alt="해운대관광리조트 전경">
								</div>
								<div class="landmark-slide-single slide-single01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0119.jpg" alt="해운대관광리조트 전경">
								</div>
								<div class="landmark-slide-single slide-single01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0120.jpg" alt="해운대관광리조트 전경">
								</div>
								<div class="landmark-slide-single slide-single01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0121.jpg" alt="해운대관광리조트 전경">
								</div>
								<div class="landmark-slide-single slide-single01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0122.jpg" alt="해운대관광리조트 전경">
								</div>
							</div>
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav slide-nav01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0118.jpg" alt="해운대관광리조트 전경">
								</div>
								<div class="landmark-slide-nav slide-nav01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0119.jpg" alt="해운대관광리조트 전경">
								</div>
								<div class="landmark-slide-nav slide-nav01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0120.jpg" alt="해운대관광리조트 전경">
								</div>
								<div class="landmark-slide-nav slide-nav01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0121.jpg" alt="해운대관광리조트 전경">
								</div>
								<div class="landmark-slide-nav slide-nav01">
									<img src="/bmc/jsp/apply/green/images/landmark/02-0122.jpg" alt="해운대관광리조트 전경">
								</div>
							</div>
						</div>
						<div class="landmark-list landmark-right">
							<h3 class="landmark-h3">해운대관광리조트 소공원(3개소)</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">산책, 2F 전망공간(일출일몰, 백사장, 야경 데이트, 
										그물망에서 별보기) 이용, 잔디마당(작은공연) 사용
										</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 해운대구 중1동 1058-2번지 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span><b>지하철</b><i>2호선 중동역 하자 후 도보 15분 </i></span>
										<span><b>버스</b><i><span>중1동(09084,09085) 하차</span>
											<span>(39,100,115-1,139,141,180,200) 후 도보 4분</span></i></span>
										<span><b>자가</b><i><span>“인근 주차장”(미포주차장, 극동주차장 등),</span> 
											<span>“엘시티레지던스 주차장” 이용가능</span></i></span>	
										
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>

				<div id="landmarkTab03" class="landmark-cont">
					<h2 class="landmark-h2">국제산업물류도시 조성사업</h2>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0301" class="landmark-slider landmark-left">
							<div class="landmark-slider-single">
		
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0101.jpg" alt="녹산고향동산 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0102.jpg" alt="녹산고향동산 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0103.jpg" alt="녹산고향동산 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0105.jpg" alt="녹산고향동산 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0106.jpg" alt="녹산고향동산 전경">
								</div>
							</div>
													
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0101.jpg" alt="녹산고향동산 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0102.jpg" alt="녹산고향동산 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0103.jpg" alt="녹산고향동산 전경">
								</div>
								
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0105.jpg" alt="녹산고향동산 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/03-0106.jpg" alt="녹산고향동산 전경">
								</div>
							</div>
						</div>
						<div class="landmark-list landmark-right">
							<h3 class="landmark-h3">녹산고향 동산</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">서낙동강 강변산책, 잔디광장 피크닉, 야외공연장 사용, 
										전시실 및 기념관 등 관람시설 이용										
										</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 강서구 범방동 5번지 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span><b>지하철</b><i>2호선 중동역 하자 후 도보 15분 </i></span>
										<span><b>버스</b><i><span>사구(92340,92368) 하차</span><span>(강서구7,강서구7-2,1005,220,221) 후 도보 4분</span></i></span>
										<span><b>자가</b><i>“녹산고향동산 내 주차장(총 32면)” 이용가능</i></span>	
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>

				<div id="landmarkTab04" class="landmark-cont">
					<h2 class="landmark-h2">오리일반산업단지 조성사업</h2>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0401" class="landmark-slider landmark-left">
							<div class="landmark-slider-single">
								

								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0101.jpg" alt="오리중앙공원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0102.jpg" alt="오리중앙공원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0103.jpg" alt="오리중앙공원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0104.jpg" alt="오리중앙공원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0105.jpg" alt="오리중앙공원 전경">
								</div>
							
							</div>
							<div class="landmark-slider-nav">
								
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0101.jpg" alt="오리중앙공원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0102.jpg" alt="오리중앙공원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0103.jpg" alt="오리중앙공원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0104.jpg" alt="오리중앙공원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/04-0105.jpg" alt="오리중앙공원 전경">
								</div>
							
							</div>
						</div>
						<div class="landmark-list landmark-right">
							<h3 class="landmark-h3">오리중앙 공원</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">벽면녹화 감상,공원내 멀티코트 활용한 체육활동
										<small>(족구,농구,풋살 등), 공원산책로 산책</small>										
										</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 기장군 장안읍 오리 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										“산단 내 무료주차장” 이용가능	
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
				
				<div id="landmarkTab05" class="landmark-cont">
					<h2 class="landmark-h2">일광지구 도시개발사업</h2>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0501" class="landmark-slider landmark-left">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0103.jpg" alt="장미테마원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0104.jpg" alt="장미테마원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0105.jpg" alt="장미테마원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0106.jpg" alt="장미테마원 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0107.jpg" alt="장미테마원 전경">
								</div>
							
							</div>
							
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0103.jpg" alt="장미테마원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0104.jpg" alt="장미테마원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0105.jpg" alt="장미테마원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0106.jpg" alt="장미테마원 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0107.jpg" alt="장미테마원 전경">
								</div>
								
							</div>
						</div>
						<div class="landmark-list landmark-right">
							<h3 class="landmark-h3">일광 장미테마원</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">가족과 함께 장미 감상 등의 체험활동과 휴식, 
										근린공원 숲속 산책									
										</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 기장군 일광면 삼성리 421 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span><b>지하철</b><i>동해남부선 일광역 하차(2번출구) 후 도보 25분</i></span>
										<span><b>버스</b><i><span>교리초등학교(16563,16555) 하차</span>
											<span>(183,188,36,139) 후 도보 22분</span>
											 <span>※ 일광신도시 하차(181, 기장군8번) 후 </span>
											<span>도보 10분(`20.03~04.개통예정)</span></i></span>
										<span><b>자가</b><i>“주민지원센터”, “일광지구 내 사설주차장” 
											이용가능</i></span>		
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0502" class="landmark-slider landmark-right">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0208.jpg" alt="어린이물놀이장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0209.jpg" alt="어린이물놀이장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0210.jpg" alt="어린이물놀이장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0211.jpg" alt="어린이물놀이장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0212.jpg" alt="어린이물놀이장 전경">
								</div>
								
							</div>
							
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0208.jpg" alt="어린이물놀이장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0209.jpg" alt="어린이물놀이장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0210.jpg" alt="어린이물놀이장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0211.jpg" alt="어린이물놀이장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0212.jpg" alt="어린이물놀이장 전경">
								</div>
								
							</div>
						</div>
						<div class="landmark-list landmark-left">
							<h3 class="landmark-h3">일광 어린이물놀이장</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">가족동반 어린이물놀이장 이용, 
										인근 일광 해수욕장 이용										
										</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">부산광역시 기장군 일광면 삼성리 421 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span><b>지하철</b><i>동해남부선 일광역 하차(2번출구) 후 도보 25분</i></span>
										<span><b>버스</b><i><span>교리초등학교(16563,16555) 하차</span>
											<span>(183,188,36,139) 후 도보 22분</span>
											<span>※ 일광신도시 하차(181, 기장군8번) 후 </span>
											<span>도보 10분(`20.03~04.개통예정)</span></i></span>
										<span><b>자가</b><i>“일광지구 내 사설주차장” 
											이용가능</i></span>		
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<div class="landmark-cont-dd clear">
						<div id="landSlider0503" class="landmark-slider landmark-left">
							<div class="landmark-slider-single">
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0308.jpg" alt="일광역광장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0309.jpg" alt="일광역광장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0310.jpg" alt="일광역광장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0311.jpg" alt="일광역광장 전경">
								</div>
								<div class="landmark-slide-single">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0312.jpg" alt="일광역광장 전경">
								</div>
							</div>
							<div class="landmark-slider-nav">
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0308.jpg" alt="일광역광장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0309.jpg" alt="일광역광장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0310.jpg" alt="일광역광장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0311.jpg" alt="일광역광장 전경">
								</div>
								<div class="landmark-slide-nav">
									<img src="/bmc/jsp/apply/green/images/landmark/05-0312.jpg" alt="일광역광장 전경">
								</div>
							</div>
						</div>
						<div class="landmark-list landmark-right">
							<h3 class="landmark-h3">일광역 광장</h3>
							<div class="landmark-list-box">
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">추천활동</dt>
									<dd class="landmark-list-dd">만남의장소, 녹지 내 메타세콰이어 산책로 이용, 
										아름다운 야간 경관과 함께하는 유수지 이용, 
										일광해수욕장 이용														
										</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">위치</dt>
									<dd class="landmark-list-dd">일광면 이천리 745답 일원</dd>
								</dl>
								<dl class="landmark-list-group">
									<dt class="landmark-list-title">교통편</dt>
									<dd class="landmark-list-dd">
										<span><b>지하철</b><i>동해남부선 일광역 하차(2번출구)</i></span>
										<span><b>버스</b><i><span>동해선일광역(16009,16167) 하차</span>
											<span>(180,182,187,188 등) 후 도보 3분</span></i></span>
										<span><b>자가</b><i>“일광역 공용주차장
											(20분 500원, 일일 7000원)” 이용가능</i></span>		
									</dd>
								</dl>
							</div>
						</div>
					</div>
					
					<div class="landmark-cont-dd clear">
	                  <div id="landSlider0504" class="landmark-slider landmark-right">
	                     <div class="landmark-slider-single">
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0401.jpg" alt="근린공원 전경">
	                        </div>
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0402.jpg" alt="근린공원 전경">
	                        </div>
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0403.jpg" alt="근린공원 전경">
	                        </div>
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0404.jpg" alt="근린공원 전경">
	                        </div>
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0405.jpg" alt="근린공원 전경">
	                        </div>
	                        
	                     </div>
	                     
	                     <div class="landmark-slider-nav">
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0401.jpg" alt="근린공원 전경">
	                        </div>
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0402.jpg" alt="근린공원 전경">
	                        </div>
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0403.jpg" alt="근린공원 전경">
	                        </div>
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0404.jpg" alt="근린공원 전경">
	                        </div>
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/05-0405.jpg" alt="근린공원 전경">
	                        </div>
	                     </div>
                  	</div>
	                  <div class="landmark-list landmark-left">
	                     <h3 class="landmark-h3">일광지구 근린공원</h3>
	                     <div class="landmark-list-box">
	                        <dl class="landmark-list-group">
	                           <dt class="landmark-list-title">추천활동</dt>
	                           <dd class="landmark-list-dd">숲길 산책, 자연 생태 학습</dd>
	                        </dl>
	                        <dl class="landmark-list-group">
	                           <dt class="landmark-list-title">위치</dt>
	                           <dd class="landmark-list-dd">부산 기장군 일광면 삼성리 790 일원</dd>
	                        </dl>
	                        <dl class="landmark-list-group">
	                           <dt class="landmark-list-title">교통편</dt>
	                           <dd class="landmark-list-dd">
	                              <span><b>지하철</b><i>일광역(동해선) 2번출구 하차 후 도보 27분</i></span>
	                              <span><b>버스</b><i>한신더휴1단지(16397) 하차(36,181) 후 도보 5분</i></span>                              
	                              <span><b>자가</b><i>"일광지구 내 사설주차장" 이용가능</i></span>      
	                           </dd>
	                        </dl>
	                     </div>
	                  </div>
	               </div>
	               
				</div>
	            <div id="landmarkTab06" class="landmark-cont">
	               <h2 class="landmark-h2">임대주택 조경공간 시설개선사업</h2>
	               <div class="landmark-cont-dd clear">
	               
	                  <div id="landSlider0601" class="landmark-slider landmark-left">
	                     <div class="landmark-slider-single">
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0101.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0102.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0103.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0104.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                        <div class="landmark-slide-single">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0105.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                     </div>
	                     
	                     <div class="landmark-slider-nav">
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0101.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0102.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0103.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0104.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>
	                        <div class="landmark-slide-nav">
	                           <img src="/bmc/jsp/apply/green/images/landmark/06-0105.jpg" alt="다대 텃밭커뮤티니 전경">
	                        </div>                        
	                     </div>
	                  </div>
	                  <div class="landmark-list landmark-right">
	                     <h3 class="landmark-h3">다대5지구몰운대영구임대아파트<br>(텃밭커뮤니티)</h3>
	                     <div class="landmark-list-box">
	                        <dl class="landmark-list-group">
	                           <dt class="landmark-list-title">추천활동</dt>
	                           <dd class="landmark-list-dd">주민 : 커뮤니티 활성화 및 우울감 해소를 위한 텃밭 가꾸기
	                              <small> (대상 : 다대5임대주택 주민, 몰운대종합사회복지관 운영)</small>                              
	                              </dd>
	                        </dl>
	                        <dl class="landmark-list-group">
	                           <dt class="landmark-list-title">위치</dt>
	                           <dd class="landmark-list-dd">부산 사하구 다대낙조 2길 50</dd>
	                        </dl>
	                        <dl class="landmark-list-group">
	                           <dt class="landmark-list-title">교통편</dt>
	                           <dd class="landmark-list-dd">
	                              <span><b>지하철</b><i>부산지하철 1호선 다대포해수욕장역 하차(1번출구) 후 도보 15분</i></span>   
	                              <span><b>버스</b><i><span>다대포해수욕장역(10199) 하차</span>
	                              <span>(3,96,96-1,338,1000) 후 도보 15분</span></i></span>
	                              <span><b>자가</b><i>"다대5지구몰운대영구임대아파트"<span></span>
	                              <span>주차장 이용 가능</span></i></span>
	                           </dd>
	                        </dl>
	                     </div>
	                  </div>
	               </div>
	            </div>
    
			</div>
			<div id="change">
				
						<div class="page_infor">
							
								<ul>
									<li class="ic1" >
									 담당부서 및 담당자 : 토목안전처 조경사업부 곽일환&nbsp; 
									
									</li>
									<li class="ic3" >
									전화번호 : 051-810-1376
									</li>
									<li class="mod_data">
										
										
									</li>
								</ul>
						
						</div>
					
				</div>
			
		</div>
		
	</div>
	<script>
		$(document).ready(function(){
			$('.silck-next').click(function(){
				console.log(1);
			});
			
		});	
	</script>
</body>
</html>
