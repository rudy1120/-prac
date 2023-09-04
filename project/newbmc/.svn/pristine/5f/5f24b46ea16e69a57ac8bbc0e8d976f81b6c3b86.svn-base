<%@ include file="/bmc/jsp/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko" >

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<c:if test="${not empty bbsView.bTitle}">
		<c:set var="bTitle" value = "${ bbsView.bTitle } | " />
	</c:if>
	<title>${ bTitle }  ${menuVO.menuName} | 오시리아관광단지</title>

    <!-- css -->
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/swiper.css">
	<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/osiria/slick/slick.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/osiria/slick/slick-theme.css"> --%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/osiria/font/font.css">
	<%-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/osiria/img/favicon.ico"> --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/board2.css">
	
	
	<!-- js -->
	<script src="${pageContext.request.contextPath}/osiria/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/osiria/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/osiria/js/global.js"></script>
	<script src="${pageContext.request.contextPath}/osiria/js/swiper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/osiria/slick/slick.min.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/osiria/slick/slick.js"></script>
	 
	<!-- <script type="text/javascript">
	$(document).ready(function(){
	  $('.Main_businessWrap_mobile').slick({
	    dots: true,
	    infinite: true
	  });
	});
	</script> -->
	<script id="img-wrapper-tmpl" type="text/x-jquery-tmpl">
        <div class="rg-image-wrapper">
        	
            {{if itemsCount > 1}}
                <div class="rg-image-nav">
                    <a href="#" class="rg-image-nav-prev" title="이전 슬라이드 이동"><img src="/osiria/img/img_arrow.png" alt="left_arrow"></a>
                    <a href="#" class="rg-image-nav-next" title="다음 슬라이드 이동"><img src="/osiria/img/img_arrow.png" alt="right_arrow"></a>
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
    $(document).ready(function(){
	    $('.facilities_tab ul li').first().addClass("active");
	    $(".tab-contents").not(':first').hide();
	    
	    $('.facilities_tab ul li').on('click',function(){
	        $(this).addClass("active").siblings().removeClass("active");
	        var link = $(this).find("div").attr("class");
	        var link_num = link.substr(link.length-1);
	       
	        $("select#tabmenu option").eq(link_num-1).prop("selected", "selected");
	        $(".tab-contents").hide();
	        $(link).show();
	      }); 
	   
		  $('.history_tab li').first().addClass("activeClass");
		  $(".history_list").not(':first').hide();
		  
		  //20230707 주석
// 		    $('.history_tab li').on('click',function(){
// 		      $(this).addClass("activeClass").siblings().removeClass("activeClass");
// 		      var link = $(this).find("div").attr("class");
// 		      var link_num = link.substr(link.length-1);
// 		      $("select#tabmenu option").eq(link_num-1).prop("selected", "selected");
// 		      $(".history_list").hide();
// 		      $(link).show();
// 		    });
		    
// 		    $("select#tabmenu").on("change",function(){
// 		      var select_link = $("select#tabmenu").val();
// 		      var select_num = $(this).prop('selectedIndex');
// 		      $('.history_tab li').eq(select_num).addClass("activeClass").siblings().removeClass('activeClass');
// 		      $(".history_list").hide();
// 		      $(select_link).show();
		      
// 		    });
		    
// 		    $(".sub_map").not(':first').hide(); 2023.04.07 주석
		    
		    $('.map_tab ul li').on('click',function(){
		        $(this).addClass("active").siblings().removeClass("active");
// 		        var link = $(this).find("div").attr("class"); 2023.04.07 주석
		       
// 		        var link_num = link.substr(link.length-1);
// 		        $("select#tabmenu option").eq(link_num-1).prop("selected", "selected");
// 		        $(".sub_map").hide();
// 		        $(link).show();
		      }); 
		    
	
    });
    
    </script>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.min.js"></script>
		<script src="js/respond.min.js"></script>
		<![endif]-->
	<%@ include file="/osiria/jsp/component/default.jsp" %>
</head>
	
	<body>
		<div class="skipToNav">
			<a href="#sub_content" onclick="fn_skipNav();">본문 바로가기</a>
		</div>
		<tiles:insertAttribute name="header" />
		<div id="container">
			<tiles:insertAttribute name="subvisual"/>
			<tiles:insertAttribute name="subpath"/>
			<div id="sub_content" class="sub_wrap">
				 <c:if test="${menuVO.menuType == 'B'}">
				  <div class="subContent_wrap">
					  <div class="subContent_tit">	
						<h1>${menuVO.menuName}</h1>
						<p>FOR YOUR LIFE</p>
					</div>
				  </c:if>
				
				 <tiles:insertAttribute name="body" />
			
					<c:if test="${menuVO.menuType == 'B'}">
					</div>
					</c:if>			
			</div>
			
<!-- ====================================================================  -->
		</div>
		<tiles:insertAttribute name="footer" />
		
	
		
    <c:if test="${menuVO.mId >= '0200000000' and menuVO.mId <= '0510000000'}">
    	
    	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    	
    </c:if>
	    <script type="text/javascript" src="/osiria/js/jquery.tmpl.min.js"></script>
	    <script type="text/javascript" src="/osiria/js/jquery.easing.1.3.js"></script>
	    <script type="text/javascript" src="/osiria/js/jquery.elastislide.js"></script>
	    <script type="text/javascript" src="/osiria/js/gallery.js"></script>
    </body>
</html>
