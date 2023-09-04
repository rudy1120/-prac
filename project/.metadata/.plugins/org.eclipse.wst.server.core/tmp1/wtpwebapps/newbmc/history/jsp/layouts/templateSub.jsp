<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		<title>부산도시공사 역사관</title>
	    <!-- css -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/font/font.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/base.css?var=1">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/plugin/slick/slick.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/common.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/content.css?var=1">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/print.css">
	    
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/board2.css?var=1">
	
	    <!-- js -->
		<script src="${pageContext.request.contextPath}/history/plugin/jquery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/history/plugin/slick/slick.js"></script>
		<script src="${pageContext.request.contextPath}/history/js/common.js?var=1"></script>
		<script src="${pageContext.request.contextPath}/history/js/content.js"></script>
		
		<script id="img-wrapper-tmpl" type="text/x-jquery-tmpl">
        <div class="rg-image-wrapper">
        	
            {{if itemsCount > 1}}
                <div class="rg-image-nav">
                    <a href="#" class="rg-image-nav-prev"><img src="/history/img/common/img_arrow.png" alt="left_arrow"></a>
                    <a href="#" class="rg-image-nav-next"><img src="/history/img/common/img_arrow.png" alt="right_arrow"></a>
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
	
	<%@ include file="/history/jsp/component/default.jsp" %>
	</head>
	<body>
		<!--건너뛰기 링크-->
	    <div id="skip"></div>

		<tiles:insertAttribute name="header" />
	
		<div id="container">
			<tiles:insertAttribute name="subvisual"/>
			<tiles:insertAttribute name="subpath"/>
			
			<div id="contents">
            	<div class="contents_inner">
            		<!-- div class=content 부터 cms에서 등록 -->
					<c:if test="${menuVO.menuType == 'B'}">
				  <div class="content">
                    <h3 class="sub_tit">${menuVO.menuName}</h3>
						
					
				  </c:if>
				
				 <tiles:insertAttribute name="body" />
			
					<c:if test="${menuVO.menuType == 'B'}">
						</div>
					</c:if>		 
					
					
					
					
				</div>
			</div>
		</div>
		
		<tiles:insertAttribute name="footer" />
	</body>
	<script type="text/javascript" src="/history/js/jquery.tmpl.min.js"></script>
    <script type="text/javascript" src="/history/js/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="/history/js/jquery.elastislide.js"></script>
    <script type="text/javascript" src="/history/js/gallery.js?var=2"></script>
</html>
