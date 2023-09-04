<%@ include file="/bmc/jsp/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<c:if test="${not empty bbsView.bTitle}">
			<c:set var="bTitle" value = "${ bbsView.bTitle } | " />
		</c:if>
		<title>${ bTitle }  ${menuVO.menuName} | 부산도시공사</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/font/font.css">
		<!-- 사이트 기본 베이스 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/base.css">
		<!-- 메인/서브 공통 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/common.css">
		<!-- 메인페이지 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/main.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/main_new.css">
		<!-- 서브페이지 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/content.css?v=1">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/layout.css">
		<!-- 구독페이지 스타일시트 -->
		<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/subscribe.css"> --%>
		<!-- 포탈 스타일시트 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/common2.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/board2.css" /> 
		<!-- 이미지 갤러리 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/css/elastislide.css" />
		
		<script id="img-wrapper-tmpl" type="text/x-jquery-tmpl">    
        	<div class="rg-image-wrapper">
            	{{if itemsCount > 1}}
                	<div class="rg-image-nav">
                   		<a href="#" class="rg-image-nav-prev" title="이전 이미지 자료"><img src="/bmc/images/img_arrow.png" alt="이전 이미지 자료" / ></a>
                    	<a href="#" class="rg-image-nav-next" title="다음 이미지 자료"><img src="/bmc/images/img_arrow.png" alt="다음 이미지 자료" /></a>
                	</div>
            	{{/if}}
            	<div class="rg-image"></div>
            	<div class="rg-loading"></div>
            	<div class="rg-caption-wrapper">
                	<div class="rg-caption" style="display:none;">
                    	<p></p>
                	</div>
            	</div>
        	</div>
   		</script>
   		<script>

   		</script>
   		<!-- jQuery -->
		<script src="${pageContext.request.contextPath}/bmc/js/jquery-3.1.1.min.js"></script> 
		<!-- slick slider plug-in -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bmc/plug/slick/slick.css">
		<script src="${pageContext.request.contextPath}/bmc/plug/slick/slick.js"></script>
		<!-- 커스터마이징 스크립트 -->
		<script src="${pageContext.request.contextPath}/bmc/js/common.js"></script>
		<script src="${pageContext.request.contextPath}/bmc/js/content.js"></script>
		<%@ include file="/bmc/jsp/component/default.jsp" %>
		<%
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma","no-cache");
			
			if(request.getProtocol().equals("HTTP/1.1") || request.getProtocol().equals("SSLv3")){
				response.setHeader("Cache-Control","no-cache");
			}
		%>
		<style>
		 	.mT10 li {float:none;color:#333;margin-left:30px;}
			@media all and (max-width:960px) {
				.mobileTable {overflow-x:scroll;white-space:nowrap;}
			}
			@media all and (max-width:900px) {
				.mT10 p {padding-left:40px!important;}
				.mT10 li {margin-left:5px;}
			}
			
			@media all and (max-width:640px) {
				.mT10 h1 {line-height:1;}
			}
			
			.faqbtn_wrap{width:100%; margin:20px 0;}
			.bod_list, .bod_maintain{margin-top:20px;}
			.b1 {font-size:1.1em;color:#000;padding:10px 10px;border:1px solid #ddd;width:14%;}
			.b1.on{color:#fff; background:#026273;}
			@media all and (max-width:960px){
				.b1{width:33%;}	
			}
			@media all and (max-width:640px){
				.b1{width:49%;}	
			}
		</style>
		<style>

		.buy_bod_list thead th{border-right:1px solid #ddd;}
		.buy_bod_list th.buyListLocation{border-right:none;}
		
		.orgTable tr, th, td{padding:20px 5px;}
		table.orgTable td:nth-child(2), table.jobTable td:nth-child(4){text-align:center;}
		
		.faqbtn_wrap{width:100%; margin:20px 0;}
		.bod_list, .bod_maintain{margin-top:20px;}
		.b1 {font-size:1.0em;color:#000;padding:10px 10px;border:1px solid #ddd;width:18%;margin-bottom:4px;}
		.b1.on{color:#fff; background:#026273;}
		@media all and (max-width:960px){
			.b1{width:33%;}
			
		}
		@media all and (max-width:640px){
			.b1{width:49%;}
		}
		
		@media all and (min-width:960px) {
			.guide_tab ul li {
				width:21%;
				height:60px;
			}
		}
		.locationInfo {
			width:100%;
			max-height:700px;
			overflow:scroll;
		}
		</style>
		
		
		<style>

		.btn_dblue {
			display: inline-block;
			border-radius: 3px;
			background: #3e4959;
			color: #ffffff !important;
			letter-spacing: -1px;
			border: 0px;
			width: 100px;
		}
		
		#excel {
			text-align:right;
		}
		
		.buylistT{background:#f7f7f7;}
		.buylistT td{text-align:left;}
		.buylisttbody{border:1px solid #ddd;}
		.buy_bod_list thead th{border-right:1px solid #ddd;}
		.buy_bod_list th.buyListLocation{border-right:none;}
		table.buylistT td{padding:20px 40px;}
		.orgTable tr, th, td{padding:20px 5px;}
		table.orgTable td:nth-child(2), table.jobTable td:nth-child(4){text-align:center;}
		input#startSpace,input#endSpace,input#startPrice,input#endPrice{width:120px;}
		.spanetc{margin:5px;}
		
		@media all and (max-width:1199px){
			table.buylistT td{padding:20px 10px;}
		}
		@media all and (max-width:960px){
		table.buylistT td{display:block; padding:10px 20px;}
		input#startSpace,input#endSpace,input#startPrice,input#endPrice,select#searchPurpose,select#searchArea{width:100%;}
		input.btn_white{width:100%; margin:10px 0;}
		.spanetc{display:block; text-align:center;}
		}
		</style>
		
		<%/* 임대 리스트 style 속성	*/%>
		<style>
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
		</style>
		
	</head>
	
	<body>
		<div class="skipToNav">
			<a href="#subContentsScroll">본문 바로가기</a>
		</div>
		<tiles:insertAttribute name="header" />
		<div id="container">
			<!-- 통합검색페이지에는 안보이게 -->
			<c:if test="${menuVO.mId != '0805000000'}">
				<tiles:insertAttribute name="subvisual"/>
				<tiles:insertAttribute name="subpath"/>
			</c:if>
			<c:if test="${menuVO.mId == '0805000000'}">
				<!-- 통합검색카테고리 -->
				<div class="gnbWrap">
					 <div class="gnbInner">
				     	<ul class="gnb">
				            <li id="sch_TOTAL"><a href="javascript:changeSearchCategory('TOTAL');">통합검색</a></li>
							<li id="sch_menu"><a href="javascript:changeSearchCategory('menu');">메뉴</a></li>
							<li id="sch_userinfo"><a href="javascript:changeSearchCategory('userinfo');">업무/직원</a></li>
							<li id="sch_board"><a href="javascript:changeSearchCategory('board');">게시판</a></li>
							<li id="sch_contents"><a href="javascript:changeSearchCategory('contents');">웹콘텐츠</a></li>			
							<li id="sch_img"><a href="javascript:changeSearchCategory('img');">이미지</a></li>
							<li id="sch_video"><a href="javascript:changeSearchCategory('video');">동영상</a></li>
							<li id="sch_file"><a href="javascript:changeSearchCategory('file');">파일</a></li>
				      	</ul>
					</div>
				</div>
			</c:if>
			<div id="subContentsScroll" tabindex="-1"></div>
			<div id="subContents">
				<c:if test="${menuVO.mId != '0805000000'}">
					<h3 class="tit"><strong id="menuName">${menuVO.menuName}</strong></h3> 
				</c:if> 
				<c:if test="${p_depth eq 4}">
					<div class="gap20"></div>
					<div class="tabmenu">
						<div class="guide_tab">
							<ul>
								<c:forEach var="element" items="${p_elements}" varStatus="status">
									<c:set var="subs_curMid" value="${fn:substring(p_curMid, 0, element.menuLevel * 2)}" />
									<c:set var="subs_mid" value="${fn:substring(element.mId, 0, element.menuLevel * 2)}" />
									<c:set var="isActiveMenu" value="${subs_mid == subs_curMid}" />
									<li ${isActiveMenu ? 'class="activeClass"' : 'class=""' }>
										<a href="/bmc/contents.do?mId=${element.mId}" ${isActiveMenu ? 'title="선택됨"' : 'title=""' }> 
												${element.menuName}
										</a>
									</li>
									
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
				<!-- 개인정보처리방침 -->
				<c:if test="${menuVO.mId >= '0804010000' and menuVO.mId <= '0804010500'}">
					<div class="gap20"></div>
					<div class="tabmenu">
						<div class="guide_tab">
							<ul>
								<li class="${menuVO.mId == '0804010100' ? 'activeClass' : ''}">
									<a href="/bmc/contents.do?mId=0804010100">
										<div> 
											개인정보처리방침
										</div>
									</a>
								</li>
								<li class="${menuVO.mId == '0804010200' ? 'activeClass' : ''}">
									<a href="/bmc/contents.do?mId=0804010200">
										<div> 
											영상처리기기 운영&middot;관리 방침
										</div>
									</a>
								</li>
								<li class="${menuVO.mId == '0804010300' ? 'activeClass' : ''}">
									<a href="/bmc/contents.do?mId=0804010300" >
										<div> 
											개인정보 목적 외 이용 및 제3자 제공
										</div>
									</a>
								</li>
								<li class="${menuVO.mId == '0804010400' ? 'activeClass' : ''}">
									<a href="/bmc/contents.do?mId=0804010400">
										<div> 
											개인정보 자료마당
										</div>
									</a>
								</li>
								<li class="${menuVO.mId == '0804010500' ? 'activeClass' : ''}">
									<a href="/bmc/contents.do?mId=0804010500">
										<div> 
											저작권보호정책
										</div>
									</a>		
								</li>
								
							</ul>
						</div>
					</div>
				</c:if>
				<div class="gap20"></div>
				<tiles:insertAttribute name="body" />
				<c:if test="${menuVO.mId >= '0200000000' and menuVO.mId < '0300000000' }">
					<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
				</c:if>
				
				<c:if test="${menuVO.mId == '0603010000'}">
					<script src="${pageContext.request.contextPath}/bmc/js/jquery.rwdImageMaps.js"></script>
				</c:if>
				
				<!-- ========================== 담당자 정보 ==================== -->
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
			</div>
		</div>
<script>
//<![CDATA[
	
	$(document).ready(function() {
		
		if($("#vocDiscomfort").length > 0){
			 var vocFrame = "<iframe  title='불평불만게시판' class='vocframe' allowfullscreen='' frameborder='0' src='https://voc.bmc.busan.kr/voc/voc1/cus_voice.tnn?act=voiceListAction&amp;typeKind=' ></iframe>";
			 $("#vocDiscomfort").append(vocFrame);
		}
		if($("#vocCompliment").length > 0){
			 var vocFrame = "<iframe  title='직원칭찬게시판' class='vocframe' allowfullscreen='' frameborder='0' src='https://voc.bmc.busan.kr/voc/voc1/cus_voice.tnn?act=voiceListAction&typeKind=3' ></iframe>";
			 $("#vocCompliment").append(vocFrame);
		}
		if($("#vocSuggest").length > 0){
			var vocFrame = "<iframe title='고객제안게시판' class='vocframe' allowfullscreen='' frameborder='0' src='https://voc.bmc.busan.kr/voc/voc1/cus_voice/cus_voice.tnn?act=voiceListAction&typeKind=6' ></iframe>";
			$("#vocSuggest").append(vocFrame); 
		}
		if($("#vocSafe").length > 0){
			var vocFrame = "<iframe title='안전신문고' class='vocframe' allowfullscreen='' frameborder='0' src='https://voc.bmc.busan.kr/voc/voc1/cus_voice.tnn?act=voiceListAction&typeKind=8' ></iframe>";
			$("#vocSafe").append(vocFrame); 
		}
		
		$('#print_form').find('.btn-blue').click(function(e){
			e.preventDefault();
				alert("클릭");
				
			});
	});

//]]>
</script>

<%-- ##### SOCIAL LOGIN & MENU COMMENT ##### --%>
<c:if test="${(empty isAuthPage || isAuthPage == 0) && menuVO.isSnsComment == 'Y' && menuVO.menuType != 'B'}">
	<jsp:include page="./menuComment.jsp" />
</c:if>
<!-- ====================================================================  -->

	<tiles:insertAttribute name="footer" />
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5115c5a901a623f55e89207cbf14b538&libraries=services"></script>
	<script>
    	$(function(){ // 실행문 시작
        // 1. gray와 modal영역이 문서에 작성되어지도록 명령.
       		/* console.log($(".es-carousel-wrapper")); */
	        $("body").append("<div class='gray'></div><div class='modal'></div>");
	        
	        // 1-1. gray를 클릭하면 gray와 modal영역이 보이지않게 처리됨.
	        $(".gray").click(function(){
	            var index = $(".gray").index(this);
	            
	            $(".gray:eq(" + index + "), .modal:eq(" + index + ")").hide();
	        }) // 1-1번명령끝
	        
	        // 2. .button li a를 클릭하면..
	        $(".morebtn-ing").click(function(){
	            // 3. 보이지않게 처리된 gray와 modal영역을 보이게 만듬
	            var index = $(".morebtn-ing").index(this);
	            $(".gray:eq(" + index + "), .modal:eq(" + index + ")").show();
	            // 4. modal안쪽에 새로운 img태그가 만들어지도록 작성. 이때 사용될 이미지의 경로값은 내가 클릭한 a태그의 href속성값과 동일.
	            var img = $(this).attr("href");
	            $(".modal:eq(" + index + ")").html("<img src='" + img + "'>");
	            return false;   
	        }) // 2번명령끝
	        
	        $('.es-carousel ul').each(function(){
				if($(this).css('display', 'block')){
					$(this).css('display', 'flex');
				}
			});
        
    	}) // 실행문 끝
    </script>
	<script>
	function big_map_print(addr, container) {
		addr = addr.replace('일원', '');
		var pjum = addr.indexOf(',');
		

 		if(pjum >=0){
 			var addr1 = addr.substring(0,pjum);
			addr = addr1;
			
		}    
			
		var geocoder = new kakao.maps.services.Geocoder();
		var callback = function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				var options = {
      				center: new kakao.maps.LatLng(result[0].y, result[0].x),
					level: 3
				}
			}
			var map = new kakao.maps.Map(container, options);
			var marker = new kakao.maps.Marker({
				position : new kakao.maps.LatLng(result[0].y, result[0].x)
			});
			marker.setMap(map);
		}
		geocoder.addressSearch(addr, callback);
	}
	
	function maps_print() {
		
		$('.addr dd').each(function(index, item) {
			var addr = item.innerText.replace('일원', '');
			var container  = document.getElementsByClassName('map')[index];
			var geocoder = new kakao.maps.services.Geocoder();
			var callback = function(result, status) {
    			if (status === kakao.maps.services.Status.OK) {
      				var options = {
						center: new kakao.maps.LatLng(result[0].y, result[0].x),
						level: 5
					};
      			}
      			var map  = new kakao.maps.StaticMap(container, options); 
			};
			geocoder.addressSearch(addr, callback);
		});
		// a 태그 새로 추가 생성되는거 제거
		$('.mapView .map a').remove();
	}
		
	$(function(){
		 
		 $(window).resize(function(){
             $('.view .map2').width('100%');
             $('.map img').width('100%');
         });
		 
		 // 이미지 때문에 프로그램으로 만들 수가 없음. 1 depth 변경시 이미지 및 jsp 수정 필요
		 if ('${menuVO.mId}' == '0603010000') {
			$('img[usemap]').rwdImageMaps();
			$('area').on('click', function(){
				var offset = 0;
				if ($('area').index(this) <= 2) {
					offset = $('#53800100000').offset(); 
				} else {
					if ($('area').index(this) < 8) {

						offset = $('#53805500004').offset();
					} else {
						if ($('area').index(this) < 14) {

							offset = $('#53805500005').offset();
						} else {

							offset = $('#53805500006').offset();
						}
					}
				}
				$('html').animate({scrollTop : offset.top - 200}, 400);	
			});
		}
			
		$(".happyhouse_btn").on('click',function(){
        	$("#lease_tab05").trigger("click");
        });
			
		if ($('.guide_tab li').find("activeClass").lenth == 0) {
	        $('.guide_tab li').first().addClass("activeClass");	
	    }
		$(".tab-contents").not(':first').hide();	 
		$('.guide_tab li').on('click',function(){
              $(this).addClass("activeClass").siblings().removeClass("activeClass");
              $(this).addClass("activeClass").siblings().find("a").attr("title","");
              var link = $(this).find("a").attr("class");
              $(this).find("a").attr("title","선택됨");
              var link_num = link.substr(link.length-1);
              $("select#tabmenu option").eq(link_num-1).prop("selected", "selected");
              $(".tab-contents").hide();
              $(link).show();
              if (link == '#tabMap') {
            	  maps_print();
              }
  			//20.04.16 웹접근성 반영 -탭이동시 title 정보 수정 
  			titleVal = $("#menuName").html().trim()+ "-" + $('.activeClass a').html().trim() + " | 부산도시공사";
  			$("title").html(titleVal);
        });
	    $("select#tabmenu").on("change",function(){
              var select_link = $("select#tabmenu").val();
              var select_num = $(this).prop('selectedIndex');
              $('.big_tab li').eq(select_num).addClass("activeClass").siblings().removeClass('activeClass');
              $(".tab-contents").hide();
              $(select_link).show();
        });
	            
        $(".tablink > a").css('cursor', 'pointer');
        
        $("#t03 > a").on('click', function() {
        	$('.guide_tab li:eq(2)').find('a').trigger('click');
        });
        
        $("#t04 > a").on('click', function() {
        	$('.guide_tab li:eq(3)').find('a').trigger('click');
        });
        
       	$('.morebtn-ing').on('click', function() {
        	$('.btn_hide').trigger('click');
        }) 	
        
        if('${menuVO.mId}' == '0102140000') {
        	 maps_print();  
        }
	    
       	// 지도보기 탭으로 기능가능하고도록 a 태그 달아줌
        $('.mapView .over').wrap('<a href="javascript:void(0)"></a>');
       	
       	$('.mapView').on('click', function() {
       		var view = $(this).parent('div').parent('li').find('.view');
    		view.css('display', 'block');
    		view.find('.map2').width('100%');
    		var addr = $(this).parent('div').parent('li').find('.addr dd')[0].innerText;
    		var container = $(this).parent('div').parent('li').find('.map2')[0];
    		big_map_print(addr, container);
    		if ($(window).width() <= 960) {
    			
    				var offset = $(this).offset();
        			$('html').animate({scrollTop : offset.top + 420}, 400);
    			
    			
    		}
    		
       	});
       	$('.btn_hide').on('click', function() {
       		if ($(window).width() <= 960) {
       			var offset = $(this).offset();
		
       			$('html').animate({scrollTop : offset.top - 1200}, 400);
       		}
       	});
       	
        if ('${menuVO.mId}' == '0606000000') {
        	$('.box_wrap').css('overflow', 'hidden');
        	var container_dts = document.getElementById('directions');
 			var geocoder_dts = new kakao.maps.services.Geocoder();
 			var callback_dts = function(result, status) {
     			if (status === kakao.maps.services.Status.OK) {
       				var options_dts = {
 						center: new kakao.maps.LatLng(result[0].y, result[0].x),
 						level: 3
 					};
       			
       				var map_dts  = new kakao.maps.Map(container_dts, options_dts); 
 					var marker_dts = new kakao.maps.Marker({
 						position : new kakao.maps.LatLng(result[0].y, result[0].x)
 					});
 					marker_dts.setMap(map_dts);
 				
 				var infowindow_dts = new kakao.maps.InfoWindow({
 					position : new kakao.maps.LatLng(result[0].y, result[0].x),
 					content : '<div style="padding-left:10px;padding-top:1px;">BMC 부산도시공사</div>'
 				});
 				
 				infowindow_dts.open(map_dts, marker_dts);
 				}
 			};
 			geocoder_dts.addressSearch('부산 부산진구 신천대로 156', callback_dts);
        }
	           	
		
        if ('${menuVO.mId}' >= '0200000000' && '${menuVO.mId}' < '0300000000') {
           	var m = '${menuVO.mId}'; 
        	if(m >= '0212000000'){
           		$('#selectbox').html(
           			'<label for="selectBusiType" class="blind">사업선택</label>'
					+ '<select id="selectBusiType" title="공유수면 매립사업 목록">'
					+ '<option value="0212010100"  selected="selected">민락동 공유수면 매립사업</option>'
					+ '</select><button class="btnmove">이동</button>'
					+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0212000000' + '\' ">단지목록</button>'
					);
					if (m == '0212000000') {
	           			$('#selectbox .btnlist').remove();
	           		}
        	}else if(m >= '0211000000'){
           		$('#selectbox').html(
            		'<label for="selectBusiType" class="blind">사업선택</label>'
					+ '<select id="selectBusiType" title="기타단지 조성사업 목록">'
					+ '<option value="0211010100"  selected="selected">기장도예촌 조성사업</option>'
					+ '<option value="0211010200">부산추모공원 조성사업</option>'
					+ '</select><button class="btnmove">이동</button>'
					+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0211000000' + '\' ">단지목록</button>'
					);
					if (m == '0211000000') {
	           			$('#selectbox .btnlist').remove();
	           		}
        	}else if (m >= '0209000000') {
           		$('#selectbox').html(
             		'<label for="selectBusiType" class="blind">사업선택</label>'
           			+ '<select id="selectBusiType" title="공공건축사업 목록">'
      				+ '<option value="0209011900" selected="selected">서부산 행정복합타운 건립사업</option>'
      				+ '<option value="0209010100">부산국제아트센터 건립사업</option>'
      				+ '<option value="0209010200">파워반도체 상용화센터 건립사업</option>'
      				/* + '<option value="0209010300">민락동 공유수면 매립사업</option>' */
      				+ '<option value="0209010400">문현지구 금융단지 개발사업</option>'
      				+ '<option value="0209010500">부산유스호스텔 아르피나 건립사업</option>'
     				+ '<option value="0209010600">자갈치시장 현대화사업</option>'
      				+ '<option value="0209010700">장림 아파트형 임대공장 건립사업</option>'
      				+ '<option value="0209010800">부산테크노파크 건립사업</option>'
      				+ '<option value="0209010900">하이테크부품소재 연구지원센터 건립사업</option>'
    				+ '<option value="0209011100">부산추모공원 내 봉안당 건립사업</option>'
     				+ '<option value="0209011200">해양생물산업육성센터 건립사업</option>'
    				+ '<option value="0209011300">과학기술교류진흥센터 건립사업</option>'
      				+ '<option value="0209011500">스마트전자부품 기술지원센터 건립사업</option>'
      				+ '<option value="0209011600">한국해양수산개발원(KMI) 건립사업</option>'
      				+ '<option value="0209011700">부산현대미술관 건립사업</option>'
      				+ '<option value="0209011800">해양융복합소재센터 건립사업</option>'
      				+ '</select><button class="btnmove">이동</button>'
      				+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0209000000' + '\' ">단지목록</button>'
      			);
           		if (m == '0209000000') {
           			$('#selectbox .btnlist').remove();
           		} 
           	} else {
           		if (m >= '0208000000') {
           			$('#selectbox').html(
                   		'<label for="selectBusiType" class="blind">사업선택</label>'
						+ '<select id="selectBusiType" title="주택건립사업 목록">'
						+ '<option selected="selected" value="0208013100">에코델타시티 11BL 공공분양주택</option>'	
						+ '<option value="0208013000">에코델타시티 12BL 공공분양주택</option>'	
						+ '<option value="0208012900">에코델타시티 24BL 공공분양주택</option>'	
						+ '<option value="0208012700">에코델타시티 18BL 공공분양주택</option>'	
						+ '<option value="0208012700">에코델타시티 19BL 공공분양주택</option>'	
						+ '<option value="0208012800">에코델타시티 20BL 공공분양주택</option>'	
						+ '<option value="0208012600">일광지구 4BL 국민임대주택</option>'
						+ '<option value="0208010100">일광 3, 5&middot;BL 공공분양주택(3BL : e편한세상 일광, 5&middot;6BL : 일광자이푸르지오)</option>'
						+ '<option value="0208010200">학장1지구 주택건립공사</option>'
						+ '<option value="0208010300">학장2지구 주택건립공사</option>'
           				+ '<option value="0208010400">다대3지구 주택건립공사</option>'
           				+ '<option value="0208010500">다대4지구 주택건립공사</option>'
           				+ '<option value="0208010600">다대5지구 주택건립공사</option>'
           				+ '<option value="0208010700">개금2지구 주택건립공사</option>'
           				+ '<option value="0208010800">덕천2지구 주택건립공사</option>'
           				+ '<option value="0208010900">동삼1지구 주택건립공사</option>'
           				+ '<option value="0208011000">동삼2지구 주택건립공사</option>'
           				+ '<option value="0208011100">반송지구 주택건립공사</option>'
           				+ '<option value="0208011200">화명2지구 주택건립공사</option>'
           				+ '<option value="0208011300">화명3지구 주택건립공사</option>'
           				+ '<option value="0208011400">화명4지구 주택건립공사</option>'
           				+ '<option value="0208011500">광안지구 주택건립공사</option>'
           				+ '<option value="0208011600">구포지구 주택건립공사</option>'
           				+ '<option value="0208011700">부곡지구 주택건립공사</option>'
         				+ '<option value="0208011800">정관지구 공공주택건립사업</option>'
           				+ '<option value="0208011900">수정1지구 주거환경개선사업</option>'
           				+ '<option value="0208012000">남부민3지구 주거환경개선사업</option>'
           				+ '<option value="0208012100">거제3지구 주거환경개선사업</option>'
           				+ '<option value="0208012200">용호5지구 주거환경개선사업</option>'
           				+ '<option value="0208012300">구서지구 재건축사업</option>'
           				+ '<option value="0208012400">반송효주택 주택건립사업</option>'
           				+ '</select><button class="btnmove">이동</button>'
          				+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0208000000' + '\' ">단지목록</button>'
        			);
	           		if (m == '0208000000') {
	           			$('#selectbox .btnlist').remove();
	           		}
           		} else {
	           		if (m >= '0207000000') {
	   					$('#selectbox').html(
	   		           		'<label for="selectBusiType" class="blind">사업선택</label>'
	   						+ '<select id="selectBusiType" title="택지개발사업 목록">'
	   						+ '<option selected="selected" value="0207010100">부곡지구 택지개발사업</option>'
	   						+ '<option value="0207010200">다대3지구 택지개발사업</option>'
	   						+ '<option value="0207010300">학장1지구 택지개발사업</option>'
	   						+ '<option value="0207010400">개금2지구 택지개발사업</option>'
	   						+ '<option value="0207010500">덕천2지구 택지개발사업</option>'
	   						+ '<option value="0207010600">동삼1지구 택지개발사업</option>'
	   						+ '<option value="0207010700">반송지구 택지개발사업</option>'
	   						+ '<option value="0207010800">다대4지구 택지개발사업</option>'
	   						+ '<option value="0207010900">동삼2지구 택지개발사업</option>'
	   						+ '<option value="0207011000">엄궁지구 택지개발사업</option>'
	   						+ '<option value="0207011100">다대5지구 택지개발사업</option>'
	   						+ '<option value="0207011200">화명3지구 택지개발사업</option>'
	   						+ '<option value="0207011300">만덕3지구 택지개발사업</option>'
	   						+ '<option value="0207011400">화명2지구 택지개발사업</option>'
	   						+ '<option value="0207011500">화명4지구 택지개발사업</option>'
	   						+ '<option value="0207011600">거제지구 택지개발사업</option>'
	   						+ '<option value="0207011700">반여지구 택지개발사업</option>'
	   						+ '<option value="0207011800">반여2지구 택지개발사업</option>'
	   						+ '<option value="0207011900">정관지구 택지개발사업</option>'
	   						+ '</select><button class="btnmove">이동</button>'
	          				+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0207000000' + '\' ">단지목록</button>'
	   					);
		   				if (m == '0207000000') {
		   					$('#selectbox .btnlist').remove();
		           		} 
           			} else {
		           		if (m >= '0206000000') {
		           			$('#selectbox').html(
	                   			'<label for="selectBusiType" class="blind">사업선택</label>'
		       					+ '<select id="selectBusiType" title="혁신도시개발사업 목록">'
		       					+ '<option value="0206010100" selected="selected">문현혁신도시 개발사업</option>'
		       					+ '<option value="0206010200">대연혁신도시 개발사업</option>'
		       					+ '<option value="0206010300">동삼혁신도시 개발사업</option>'
		       					+ '<option value="0206010400">센텀혁신도시 개발사업</option>'
		       					+ '</select><button class="btnmove">이동</button>'
		          				+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0206000000' + '\' ">단지목록</button>'
		       				);
			           		if (m == '0206000000') {
			           			$('#selectbox .btnlist').remove();
			                } 
           				} else {
           					if (m >= '0205000000') {
           						$('#selectbox').html(
           			           		'<label for="selectBusiType" class="blind">사업선택</label>'
          							+ '<select id="selectBusiType" title="도시개발사업 목록">'
	       							+ '<option value="0205010100" selected="selected">부산 에코델타시티 친수구역 조성사업</option>'
	       							+ '<option value="0205010200">오시리아관광단지 조성사업</option>'
	       							+ '<option value="0205010300">일광지구 도시개발사업</option>'
	       							+ '<option value="0205010400">해운대관광리조트 도시개발사업</option>'
	       							+ '<option value="0205010500">부산신항 북컨테이너터미널배후부지 조성사업</option>'
	       							+ '<option value="0205010600">동삼하리지구 도시개발사업</option>'
	       							+ '</select><button class="btnmove">이동</button>'
			          				+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0205000000' + '\' ">단지목록</button>'
	           					);
           						if (m == '0205000000') {
           							$('#selectbox .btnlist').remove();
	                   			}
           					} else {
           						if (m >= '0204000000') {
           							$('#selectbox').html(
           								'<label for="selectBusiType" class="blind">사업선택</label>'
           								+ '<select id="selectBusiType" title="산업단지조성사업 목록">'
           								+ '<option value="0204010100">센텀2 도시첨단산업단지 조성사업</option>'
           								+ '<option value="0204010400">사상재생사업지구(일반산업단지) 활성화구역 조성사업</option>'
           								+ '<option value="0204010500">장안일반산업단지 조성사업</option>'
           								+ '<option value="0204010200">국제산업물류도시(1단계) 일반산업단지 조성사업</option>'
           								+ '<option value="0204010300">오리일반산업단지 개발사업</option>'
           								+ '<option value="0204010600">화전산업단지 조성사업</option>'
           								+ '<option value="0204010700">미음산업단지 조성사업</option>'
           								+ '<option value="0204010800">생곡산업단지 조성사업</option>'
           								+ '</select><button class="btnmove">이동</button>'
    			          				+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0204000000' + '\' ">단지목록</button>'
           							);
           							if (m == '0204000000') {
           								$('#selectbox .btnlist').remove();
    	                   			}
           						} else {
           							if (m >= '0201000000') {
           								$('#selectbox').html(
          									'<label for="selectBusiType" class="blind">사업선택</label>'
          									+ '<select id="selectBusiType" title="행복주택사업 목록">'
           									+ '<option value="0201010800">국제물류 ap-2BL 행복주택</option>'
           									+ '<option value="0201010700">봉산마을 행복주택</option>'
           									+ '<option value="0201010200">아미4 행복주택</option>'
           									+ '<option value="0201010300">시청앞 행복주택</option>'
           									+ '<option value="0201010400">일광지구 행복주택</option>'
           									+ '<option value="0201010500">금사도시재생어울림센터 행복주택</option>'
           									+ '<option value="0201010600">환경공단부지 행복주택</option>'
           									+ '<option value="0201010100">동래역 행복주택</option>'
           									+ '</select><button class="btnmove">이동</button>'
        			          				+ '<button class="btnlist" onclick="location.href=\'' + '/bmc/contents.do?mId=0201000000' + '\' ">단지목록</button>'
           								);
           								if (m == '0201000000') {
           									$('#selectbox .btnlist').remove();
        	                   			}
           							}
           						}
           					}
       					} 
           			}
           		}
           	}
           	$('#selectbox option[value=' + m + ']').attr("selected", "selected");
           	
         	$('.btnmove').on('click', function() {
          		var business_mId = $('#selectbox option:selected').val();
          		location.href='/bmc/contents.do?mId=' + business_mId;
          	});
       }
}); 
</script>
		<!-- 이미지갤러리 -->
		<script src="${pageContext.request.contextPath}/bmc/js/jquery.tmpl.min.js"></script>
		<script src="${pageContext.request.contextPath}/bmc/js/jquery.easing.1.3.js"></script>
		<script src="${pageContext.request.contextPath}/bmc/js/jquery.elastislide.js"></script>
		<script src="${pageContext.request.contextPath}/bmc/js/gallery.js"></script>
	</body>
	

	
</html>
