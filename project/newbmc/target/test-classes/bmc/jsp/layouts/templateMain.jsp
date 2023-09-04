<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>
<%@ include file="/bmc/jsp/common/taglib.jsp" %>
<compress:html compressJavaScript="${yh:isLiveMode()}" enabled="${yh:isLiveMode()}" jsCompressor="closure" closureOptLevel="simple">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>부산도시공사</title>
		<meta name="description" lang="ko" content="부산의 일상을 만듭니다. 주거복지, 도시공간 전문 공기업" />
		<meta property="og:title" content="부산도시공사" />
		<meta property="og:description" content="부산의 일상을 만듭니다. 주거복지, 도시공간 전문 공기업" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/font/font.css">
		<!-- 사이트 기본 베이스 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/base.css">
		<!-- 메인/서브 공통 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/common.css">
		<!-- 메인페이지 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/main_new.css">
		
		<!-- jQuery -->
		<script src="${pageContext.request.contextPath}/bmc/js/jquery-3.1.1.min.js"></script>
		
		<!-- slick slider plug-in -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/plug/slick/slick.css">
		<script src="${pageContext.request.contextPath}/bmc/plug/slick/slick.js"></script>
		
		<!-- 커스터마이징 스크립트 -->
		<script src="${pageContext.request.contextPath}/bmc/js/common.js"></script>
		<script src="${pageContext.request.contextPath}/bmc/js/main.js"></script>
		<script src="${pageContext.request.contextPath}/bmc/js/main_new.js?var=1"></script>
		
		<%@ include file="/bmc/jsp/component/default.jsp" %>

	</head>
	<body>
		<div class="skipToNav">
			<a href="#whole" class="page-link">본문 바로가기</a>
		</div>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
		
		<div class="main_layer_pop">
<!-- 			<div class="layer_pop" style="position:fixed; transform: translate(-50%, -50%);left:15%; top:41%; z-index:999999;" id="pop1"> -->
			<div class="layer_pop" id="pop1">
				<a href="https://www.bmc.busan.kr/bmc/bbs/view.do?bIdx=706678&ptIdx=768&mId=0707000000" title="아미4 행복주택 단지 내 상가 분양공고 바로가기"> 
				<img src="./images/main/main_popup7.jpg" border="0" usemap="#event_notice" alt="아미4 행복주택 단지 내 상가 분양공고 바로가기">
				</a>
				<div class='mainPopFoot'>
					<form method="post" action="" name="pop_form">
		                <span id="check" class="popTxt"><input type="checkbox" value="checkbox" name="chkbox" id="chkday"/><label for="chkday">24시간 보지 않기</label></span>
		                <button type="button" id="close">Close</button>
		            </form>
				</div>
			</div>
			
<!-- 			<div class="layer_pop" style="position:fixed; transform: translate(-50%, -50%);left:40%; top:41%; z-index:999999;" id="pop2"> -->
			<div class="layer_pop" id="pop2">
				<a href="https://www.bmc.busan.kr/bmc/poll/list.do?mId=0309000000" title="2023 부산도시공사 정보공개 설문조사 바로가기"> 
				<img src="./images/main/main_popup10.jpg" border="0" usemap="#event_notice" alt="2023 부산도시공사 정보공개 설문조사 바로가기">
				</a>
				<div class='mainPopFoot'>
					<form method="post" action="" name="pop_form">
		                <span id="check" class="popTxt"><input type="checkbox" value="checkbox" name="chkbox2" id="chkday2"/><label for="chkday2">24시간 보지 않기</label></span>
		                <button type="button" id="close2">Close</button>
		            </form>
				</div>
			</div>
			<div class="layer_pop" id="pop3">
				<a href="https://www.bmc.busan.kr/bmc/bbs/view.do?bIdx=706641&ptIdx=768&mId=0701000000" title="시청앞(2단지) 행복주택 단지 내 상가 분양공고 바로가기"> 
				<img src="./images/main/main_popup9.jpg" border="0" usemap="#event_notice" alt="시청앞(2단지) 행복주택 단지 내 상가 분양공고 바로가기">
				</a>
				<div class='mainPopFoot'>
					<form method="post" action="" name="pop_form">
		                <span id="check" class="popTxt"><input type="checkbox" value="checkbox" name="chkbox3" id="chkday3"/><label for="chkday3">24시간 보지 않기</label></span>
		                <button type="button" id="close3">Close</button>
		            </form>
				</div>
			</div>
			<div class="layer_pop" id="pop4">
				<a href="https://www.arpina.co.kr/kor/CMS/Board/Board.do?mCode=MN072&mode=view&mgr_seq=1&board_seq=180" target="_blank" title="새창열기"> 
				<img src="./images/main/main_popup8.jpg" border="0" usemap="#event_notice" alt="2023 부산여행 체험수기 공모전">
				</a>
				<div class='mainPopFoot'>
					<form method="post" action="" name="pop_form">
		                <span id="check" class="popTxt"><input type="checkbox" value="checkbox" name="chkbox4" id="chkday4"/><label for="chkday4">24시간 보지 않기</label></span>
		                <button type="button" id="close4">Close</button>
		            </form>
				</div>
			</div>
			

			
			<div class="layer_pop" id="pop6">
				<a href="https://www.bmc.busan.kr/bmc/bbs/view.do?bIdx=706682&ptIdx=788&mId=0701000000" target="_blank" title="부산도시공사의 청렴한 계약거래를 위한 4가지 제도"> 
				<img src="./images/main/20230825_popup.png" border="0" usemap="#event_notice" alt="부산도시공사의 청렴한 계약거래를 위한 4가지 제도">
				</a>
				<div class='mainPopFoot'>
					<form method="post" action="" name="pop_form">
		                <span id="check" class="popTxt"><input type="checkbox" value="checkbox" name="chkbox6" id="chkday6"/><label for="chkday6">24시간 보지 않기</label></span>
		                <button type="button" id="close6">Close</button>
		            </form>
				</div>
			</div>
			
			<div class="layer_pop" id="pop5">
				<a href="/nfMoveRes.jsp" target="_blank" title="새창열기"> 
				<img src="./images/main/main_popup3.png" border="0" usemap="#event_notice" alt="아미4행복주택 이사날짜 예약">
				</a>
				<div class='mainPopFoot'>
					<form method="post" action="" name="pop_form">
		                <span id="check" class="popTxt"><input type="checkbox" value="checkbox" name="chkbox5" id="chkday5"/><label for="chkday5">24시간 보지 않기</label></span>
		                <button type="button" id="close5">Close</button>
		            </form>
				</div>
			</div>
			
		</div>
		
	</body>
</html>
</compress:html>