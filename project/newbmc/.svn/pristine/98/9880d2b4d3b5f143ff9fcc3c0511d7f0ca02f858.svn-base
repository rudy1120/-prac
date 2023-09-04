<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/osiria/jsp/common/taglib.jsp"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<script>
if(cookiedata.indexOf("close3=Y")<0){
    $("#pop3").show();
}else{
    $("#pop3").hide();
}
$("#close3").click(function(){
    couponClose3();
});

// 레이어 팝업 키보드 접근 시
$("#close3").on("keydown", function(e){
	if(e.key === "Enter" || e.key === " "){
		couponClose3();
		//$(".page-link").focus();
		$("#close4").focus();
		e.preventDefault();
	}
});

var couponClose3 = function(){
    if($("input[name='chkbox3']").is(":checked") == true){
        setCookie("close3","Y",1);   //기간( ex. 1은 하루, 7은 일주일)
    }
    $("#pop3").hide();
}
</script>


			<div class="layer_pop" id="pop3">
				<a href="https://www.bmc.busan.kr/bmc/bbs/view.do?bIdx=706641&ptIdx=768&mId=0701000000" title="시청앞(2단지) 행복주택 단지 내 상가 분양공고 바로가기"> 
				<img src="/osiria/img/main/20230825_popup.png" border="0" usemap="#event_notice" alt="시청앞(2단지) 행복주택 단지 내 상가 분양공고 바로가기">
				</a>
				<div class='mainPopFoot'>
					<form method="post" action="" name="pop_form">
		                <span id="check" class="popTxt"><input type="checkbox" value="checkbox" name="chkbox3" id="chkday3"/><label for="chkday3">24시간 보지 않기</label></span>
		                <button type="button" id="close3">Close</button>
		            </form>
				</div>
			</div>
			
			
			
			
			
<div class="swiper-button-prev"></div>   
	<div class="SlideArea">
		<h2><strong>오시리아</strong>
                관광단지</h2>
       	<p>지속가능한 융복합 친환경 해양관광도시</p>
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