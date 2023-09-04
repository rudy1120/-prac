<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/bmc/jsp/common/taglib.jsp"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<div class="visual">
	<div class="visual_slide">
		<c:forEach var="element" items="${visualList}">
			<div class="banner">
				<div class="visual_slideImg">
					<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${element.attachId}"/>
						<c:param name="mode" value="origin"/>
					</c:import> 
					
				</div>
				<div class="visual_slideTxt">
					
					<c:set var="slideText" value="${fn:split(element.prmtContent, newLineChar)}" /> 
					<c:choose>
					<c:when test="${element.prmtOrder eq 1 || element.prmtOrder eq 3}">
						<div class="Ydiv">
					
						<h1>${slideText[0]}</h1>
						<h1>${slideText[1]}</h1>
					
						</div>
					</c:when>
					
					<c:otherwise>
						<div class="Ndiv">
						
						<h1 class="h1f">${slideText[0]}</h1>
						<h1 class="h1s">${slideText[1]}</h1>
					
						</div> 
					</c:otherwise>
					<%-- <c:if test="${fn:length(slideText) >= 4 }">
						<p class="mainP">${slideText[0]}<!-- 시민의 행복한 꿈을 실현하는 시민공기업, --></p>
						<h1>${slideText[1]}<!-- 부산도시공사 --></h1>
						<p>${slideText[2]}<br/>${slideText[3]}<!-- 부산의 가치를 높이는 시민 중심 경영을 약속합니다. --></p>
						
					</c:if>  --%>
				
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<!-- 팝업시작 -->
<div class="visual_menuWrap">

<!-- 팝업끝 -->
<div class="visual_innr">
    <div class="visual_menu">
        <h2 class="blind">자주찾는 서비스</h2>
        <div class="menu_contentWrap">
            <div class="menu_title">
                <p>자주찾는 <strong>서비스</strong></p>
            </div>
            <div class="menu_content">  
                <div class="menu_box">
                    <ul class="page1">
                        <li><a href="/bmc/apply/applyForm.do" target="_blank" title="분양 알림서비스 신청 새 창 열기"><span class="icon">분양 알림서비스 신청</span>분양 알림서비스 신청</a></li>
                        <li><a href="/bmc/apply/applyForm2.do" target="_blank" title="임대 알림서비스 신청 새 창 열기"><span class="icon">임대 알림서비스 신청</span>임대 알림서비스 신청</a></li>
                        <li><a href="/bmc/contents.do?mId=0102040000" title="입주 대기자 명단조회 바로가기"><span class="icon">입주 대기자 명단조회</span>입주 대기자 명단조회</a></li>
                        <li><a href="/bmc/look/retrieve.do?mId=0308010100" title="납부금 조회 / 신청 바로가기"><span class="icon">납부금 조회 / 신청</span>납부금 조회 / 신청</a></li>
                        <li><a href="/bmc/contents.do?mId=0304020000" title="민원신청(VOC) 바로가기"><span class="icon">민원신청(VOC)</span>민원신청(VOC)</a></li>
                        <li><a href="/bmc/contents.do?mId=0310000000" title="고객경영참여 바로가기"><span class="icon">고객경영참여</span>고객경영참여</a></li>
                    </ul>
                </div>
                <div class="menu_box">
                    <ul class="page2">
                        <li><a href="/bmc/contents.do?mId=0307000000" title="익명신고 바로가기"><span class="icon">익명신고</span>익명신고</a></li>
                        <li><a href="https://www.redwhistle.org/report/report.asp?organ=682&RType=1" title="인권침해신고 바로가기"><span class="icon">인권침해신고</span>인권침해신고</a></li>
                        <li><a href="/bmc/contents.do?mId=0312000000" title="고객참여이벤트 바로가기"><span class="icon">고객참여이벤트</span>고객참여이벤트</a></li>
                        <li><a href="/bmc/contents.do?mId=0309000000" title="고객설문조사 바로가기"><span class="icon">고객설문조사</span>고객설문조사</a></li>
                        <li><a href="/bmc/contents.do?mId=0401010000" title="정보공개 바로가기"><span class="icon">정보공개</span>정보공개</a></li>
                        <li><a href="/bmc/contents.do?mId=0603010000" title="부서안내 바로가기"><span class="icon">부서안내</span>부서안내</a></li>
                        
                    </ul>
                </div>
                <div class="menu_box">
                    <ul class="page3">                 
                        <li><a href="/bmc/contents.do?mId=0606000000" title="오시는 길 바로가기"><span class="icon">오시는 길</span>오시는 길</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


