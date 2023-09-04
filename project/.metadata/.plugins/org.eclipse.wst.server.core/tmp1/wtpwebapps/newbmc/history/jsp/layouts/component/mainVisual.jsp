<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<div class="swiper-button-prev"></div>   
	<div class="SlideArea">
		<h2><strong>오시리아</strong>
                관광단지</h2>
       	<p>사계절체류형 명품복합해양리조트</p>
       	<p>문화관광쇼핑이 한데 어우러진 꿈의 관광단지</p>
    </div>            
   	<div class="swiper-container">
    	<div class="swiper-wrapper">
        	<c:forEach var="element" items="${visualList}">
				<div class="swiper-slide ss1">
					<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${element.attachId}"/>
						<c:param name="mode" value="origin"/>
					</c:import> 
				</div>
			</c:forEach>
        </div>
            <div class="swiper-pagination"></div>
        </div>
    <div class="swiper-button-next"></div>
    
    <script>
        var swiper = new Swiper('.swiper-container',{
            pagination: {
                el: '.swiper-pagination'
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev'
            },
            autoplay: {
                delay: 5000
            }
        });
	</script>