<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<script type="text/javascript" src="/bmc/js/netfunnel.js" charset="UTF-8"></script>

<script>
window.onload = function(){
	NetFunnel_Complete();
}
</script>

<div id="containerWrap">
	<!-- 메인배너 -->
	<div id="main_visualWrap">
		<c:set var="visualList" value="${visualzoneList}" scope="request"/>
		<jsp:include page="/bmc/jsp/layouts/component/mainVisual.jsp"/>
	</div>
	
	<div class="mainContents" id="mainContents">
		<!-- 뉴스,공지사항 BMC소식 -->
		<div class="mainSection1">
			<div class="mainSectionInnr">
				<h2>BMC <strong>소식</strong></h2>
				<div class="mainNotice">
					<ul class="noticeTab">
						<li class="active show">
							<button id="whole" title="전체 선택됨">전체</button>
							<ul>
								<c:forEach var="element" items="${mainBoard}" varStatus="status">
									<li>
										<c:if test="${element.ptIdx == 788}">
											<p class="subject"><span class="notice">공지</span></p>
											<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0707000000">
												<p class="title">${element.bTitle}</p> 
											</a>
										</c:if>
										<c:if test="${element.ptIdx == 768}">
											<p class="subject"><span class="distribution">분양</span></p>
											<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0701000000">
												<p class="title">${element.bTitle}</p> 
											</a>
										</c:if>
										<c:if test="${element.ptIdx == 769}">
											<p class="subject"><span class="lease">임대</span></p>
											<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0702000000">
												<p class="title">${element.bTitle}</p> 
											</a>
										</c:if>
										<c:if test="${element.ptIdx == 770}">
											<p class="subject"><span class="reward">보상</span></p>
											<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0703000000">
												<p class="title">${element.bTitle}</p> 
											</a>
										</c:if>
										<c:if test="${element.ptIdx == 773}">
											<p class="subject"><span class="recruit">채용</span></p>
											<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0706000000">
												<p class="title">${element.bTitle}</p> 
											</a>
										</c:if>
										<c:if test="${element.ptIdx == 801}">
											<p class="subject"><span class="report">보도</span></p>
											<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0604050000">
												<p class="title">${element.bTitle}</p> 
											</a>
										</c:if>
	
										<p class="writer">${element.rltdDeptNm}</p>
										<p class="date">
										<c:if test="${empty element.bTermSdate}">
											<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
										</c:if>
										<c:if test="${not empty element.bTermSdate}">
											<c:if test="${fn:length(element.bTermSdate) >= 10}">
												${fn:substring(element.bTermSdate, 0 ,10)}
											</c:if>
										</c:if>
										</p>
									</li>
								</c:forEach>
							</ul>
						</li>
						
						<li>
						<button title="공지사항">공지사항</button>
							<ul>
								<c:forEach var="element" items="${boardA}" varStatus="status">
								<li>
									<p class="subject"><span class="notice">공지</span></p>
									<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0707000000">
										<p class="title">${element.bTitle}</p> 
									</a>
									<p class="writer">${element.rltdDeptNm}</p>
									<p class="date">
									<c:if test="${empty element.bTermSdate}">
										<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${not empty element.bTermSdate}">
										<c:if test="${fn:length(element.bTermSdate) >= 10}">
											${fn:substring(element.bTermSdate, 0 ,10)}
										</c:if>
									</c:if>
									</p>
								</li>
								</c:forEach>
								<li class="notice-more-link" ><a href="/bmc/bbs/list.do?ptIdx=788&mId=0707000000" title="공지사항 더보기">+ 더보기</a></li> 
							</ul>
						</li>
						
						<li>
							<button title="분양공고">분양공고</button>
							<ul>
								<c:forEach var="element" items="${boardB}" varStatus="status">
								<li>
									<p class="subject"><span class="distribution">분양</span></p>
									<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0701000000">
										<p class="title">${element.bTitle}</p> 
									</a>
									<p class="writer">${element.rltdDeptNm}</p>
									<p class="date">
									<c:if test="${empty element.bTermSdate}">
										<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${not empty element.bTermSdate}">
										<c:if test="${fn:length(element.bTermSdate) >= 10}">
											${fn:substring(element.bTermSdate, 0 ,10)}
										</c:if>
									</c:if>
									</p>
								</li>
								</c:forEach>
								<li class="notice-more-link" ><a href="/bmc/bbs/list.do?ptIdx=768&mId=0701000000" title="분양공고 더보기">+ 더보기</a></li>
							</ul>
						</li>
						<li>
							<button title="임대공고">임대공고</button>
							<ul>
								<c:forEach var="element" items="${boardC}" varStatus="status">
								<li>
									<p class="subject"><span class="lease">임대</span></p>
									<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0702000000">
										<p class="title">${element.bTitle}</p> 
									</a>
									<p class="writer">${element.rltdDeptNm}</p>
									<p class="date">
									<c:if test="${empty element.bTermSdate}">
										<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${not empty element.bTermSdate}">
										<c:if test="${fn:length(element.bTermSdate) >= 10}">
											${fn:substring(element.bTermSdate, 0 ,10)}
										</c:if>
									</c:if>
									</p>
								</li>
								</c:forEach>
								<li class="notice-more-link" ><a href="/bmc/bbs/list.do?ptIdx=769&mId=0702000000" title="임대공고 더보기">+ 더보기</a></li>
							</ul>
						</li>
						<li>
							<button title="보상공고">보상공고</button>
							<ul>
								<c:forEach var="element" items="${boardCP}" varStatus="status">
								<li>
									<p class="subject"><span class="reward">보상</span></p>
									<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0703000000">
										<p class="title">${element.bTitle}</p> 
									</a>
									<p class="writer">${element.rltdDeptNm}</p>
									<p class="date">
									<c:if test="${empty element.bTermSdate}">
										<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${not empty element.bTermSdate}">
										<c:if test="${fn:length(element.bTermSdate) >= 10}">
											${fn:substring(element.bTermSdate, 0 ,10)}
										</c:if>
									</c:if>
									</p>
								</li>
								</c:forEach>
								<li class="notice-more-link" ><a href="/bmc/bbs/list.do?ptIdx=770&mId=0703000000" title="보상공고 더보기">+ 더보기</a></li>
							</ul>
						</li>
						<li>
							<button title="채용공고">채용공고</button>
							<ul>
								<c:forEach var="element" items="${boardR}" varStatus="status">
								<li>
									<p class="subject"><span class="recruit">채용</span></p>
									<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0706000000">
										<p class="title">${element.bTitle}</p> 
									</a>
									<p class="writer">${element.rltdDeptNm}</p>
									<p class="date">
									<c:if test="${empty element.bTermSdate}">
										<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${not empty element.bTermSdate}">
										<c:if test="${fn:length(element.bTermSdate) >= 10}">
											${fn:substring(element.bTermSdate, 0 ,10)}
										</c:if>
									</c:if>
									</p>
								</li>
								</c:forEach>
								<li class="notice-more-link" ><a href="/bmc/bbs/list.do?ptIdx=773&mId=0706000000" title="채용공고 더보기">+ 더보기</a></li>
							</ul>
						</li>
						<li>
							<button title="보도자료">보도자료</button>
							<ul>
								<c:forEach var="element" items="${boardD}" varStatus="status">
								<li>
									<p class="subject"><span class="report">보도</span></p>
									<a href="/bmc/bbs/view.do?bIdx=${element.bIdx}&ptIdx=${element.ptIdx}&mId=0604050000">
										<p class="title">${element.bTitle}</p> 
									</a>
									<p class="writer">${element.rltdDeptNm}</p>
									<p class="date">
									<c:if test="${empty element.bTermSdate}">
										<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${not empty element.bTermSdate}">
										<c:if test="${fn:length(element.bTermSdate) >= 10}">
											${fn:substring(element.bTermSdate, 0 ,10)}
										</c:if>
									</c:if>
									</p>
								</li>
								</c:forEach>
								<li class="notice-more-link" ><a href="/bmc/bbs/list.do?ptIdx=801&mId=0604050000" title="보도자료 더보기">+ 더보기</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="mainSection2">
			<div class="mainSectionInnr">
			<h2>BMC <strong>홍보관</strong></h2>
				<div class="mainSection2_Lf">
<!-- 					<span style="font-size: 30px;">테스트 중입니다.</span> -->
					<div class="section2_slide">
						<c:set var="bannerList" value="${popupzoneList}" scope="request"/>
						<jsp:include page="/bmc/jsp/layouts/component/mainBanner.jsp"/>
					</div>
				</div>
			
			
				<div class="mainSection2_Rt">
					<div>
						<div class="mainPR_tit">
							<p class="h3">홍보책자ㆍ 광고ㆍ 동영상</p>
							<a href="/bmc/contents.do?mId=0604030100" title="홍보책자ㆍ 광고ㆍ 동영상 더보기"><span class="blind">홍보책자ㆍ 광고ㆍ 동영상 더보기</span><img src="./images/main/main_moreBtn.png" alt="홍보책자ㆍ 광고ㆍ 동영상 더보기"></a>
						</div>
						<ul class="mainPR_txt">
							<li>
								<span class="book">책자</span>
								<span class="title"><a href="/bmc/bbs/view.do?bIdx=${boardE[0].bIdx}&ptIdx=${boardE[0].ptIdx}&mId=0604030100">${boardE[0].bTitle}</a></span>
								<span class="date">
								<c:if test="${empty boardE[0].bTermSdate}">
									<fmt:formatDate value="${boardE[0].createDate}" pattern="yyyy-MM-dd"/>
								</c:if>
								<c:if test="${not empty boardE[0].bTermSdate}">
									<c:if test="${fn:length(boardE[0].bTermSdate) >= 10}">
										${fn:substring(boardE[0].bTermSdate, 0 ,10)}
									</c:if>
								</c:if>
								</span>
							</li>
							<li>
								<span class="advertisement">광고</span>
								<span class="title"><a href="/bmc/bbs/view.do?bIdx=${boardF[0].bIdx}&ptIdx=${boardF[0].ptIdx}&mId=0604030200">${boardF[0].bTitle}</a></span>
								<span class="date">
								<c:if test="${empty boardF[0].bTermSdate}">
									<fmt:formatDate value="${boardF[0].createDate}" pattern="yyyy-MM-dd"/>
								</c:if>
								<c:if test="${not empty boardF[0].bTermSdate}">
									<c:if test="${fn:length(boardF[0].bTermSdate) >= 10}">
										${fn:substring(boardF[0].bTermSdate, 0 ,10)}
									</c:if>
								</c:if>
								</span>
							</li>
							<li>
								<span class="video">영상</span>
								<span class="title"><a href="/bmc/bbs/view.do?bIdx=${boardG[0].bIdx}&ptIdx=${boardG[0].ptIdx}&mId=0604030300">${boardG[0].bTitle}</a></span>
								<span class="date">
								<c:if test="${empty boardG[0].bTermSdate}">
									<fmt:formatDate value="${boardG[0].createDate}" pattern="yyyy-MM-dd"/>
								</c:if>
								<c:if test="${not empty boardG[0].bTermSdate}">
									<c:if test="${fn:length(boardG[0].bTermSdate) >= 10}">
										${fn:substring(boardG[0].bTermSdate, 0 ,10)}
									</c:if>
								</c:if>
								</span>
							</li>
						</ul>
					</div>
					<div class="mainSection2_Rt_bottom">
						<dl class="webzine_img">
							<dt class="mainPR_tit">
								<p class="h3">웹진<img src="./images/main/main_section2_webzineLogo.png" alt="바다가"></p>
								<a href="/badaga/main.do" target="_blank" title="웹진 새 창 열기"><span class="blind">웹진 바로가기</span><img src="./images/main/main_moreBtn.png" alt="웹진 사이트 새창"></a>
							</dt>
						<!-- 	<dd><a href="/badaga/main.do" target="_blank"><img src="./images/main/main_section2_webzine.png" alt="웹진 사이트 새창">
								<div class="webzine_tit">2020<p>07</p></div></a>
							</dd> -->
							<dd><a href="/badaga/main.do" target="_blank" title="웹진 새 창 열기"><span class="blind">웹진 바로가기</span><img src="./images/main/main_section2_webzine2.jpg" alt="웹진 사이트 새창">
								<!-- <div class="webzine_tit">2020<p>07</p></div> --></a>
							</dd>
							<dd class="webzine_go">
							  <a href="/bmc/sub/subForm.do?mId=0604090200" style="display: inline-block; background: #0D61B1; color: #fff; text-align:center; width:274px; height:30px; padding-top:5px; padding-left:15px;" target="_blank" title="웹진 구독 신청 바로가기"><!-- <img src="./images/main/subbtn.png" alt="웹진 사이트 새창"> -->
							웹진 구독 신청  <img src="./images/main/click.png" alt="웹진 사이트 새창" ></a>
							  
							</dd>
						</dl>
						<dl>
							<dt class="mainPR_tit">
								<p class="h3">역사관</p>
								<a href="/history/main.do" target="_blank" title="역사관 새 창 열기"><span class="blind">역사관 바로가기</span><img src="./images/main/main_moreBtn.png" alt="역사관 홈페이지 새창"></a>
							</dt>
							<dd><a href="/history/main.do" target="_blank" title="역사관 새 창 열기"><span class="blind">역사관 바로가기</span><img src="./images/main/main_section2_history.png" alt="역사관 홈페이지 새창"></a></dd>
						</dl>
						<!-- <dl>
							<dt class="mainPR_tit"><p class="h3">웹진 구독하기</p>
							<a href="/history/main.do" target="_blank"><img src="./images/main/main_moreBtn.png" alt="역사관 홈페이지 새창"></a>
						
							</dt>
						</dl> -->
					</div>
				</div>
			</div>
		</div>
  
		 <div class="mainSection3">
			<div class="mainSectionInnr">
				<h2>BMC <strong>사업안내</strong></h2>
				<div class="osiria_banner">
					<a href="https://osiria.bmc.busan.kr/" target="_blank" title="오시리아 새 창 열기">
					<span class="blind">오시리아 새 창 열기</span>
					<img src="./images/main/osiriabanner_p1.jpg" alt="오시리아 관광단지 사계절체류형 명품복합해양리조트 문화관광 쇼핑이 한데 어우러진 꿈의 관광단지">
					</a>
					<a href="http://www.arpina.co.kr/" target="_blank" title="아르피나 새 창 열기">
					<span class="blind">아르피나 새 창 열기</span>
                    <img src="./images/main/arpinabanner_p2.jpg" alt="아르피나 컨벤션 휴양시설 호텔보다 더 나은 서비스, 더 다양한 서비스">
                    </a>
				</div>
				<div class="businessIcon_slideWrap">
					<div class="businessIcon_slide">          
						<a href="/bmc/contents.do?mId=0201000000"><img src="./images/main/main_section3_business_ico1.png" alt=""><p>행복주택사업</p></a>
						<a href="/bmc/contents.do?mId=0202000000"><img src="./images/main/main_section3_business_ico2.png" alt=""><p>주거복지사업</p></a>
						<a href="/bmc/contents.do?mId=0203000000"><img src="./images/main/main_section3_business_ico3.png" alt=""><p>도시재생사업</p></a>
						<a href="/bmc/contents.do?mId=0204000000"><img src="./images/main/main_section3_business_ico4.png" alt=""><p>산업단지조성사업</p></a>
						<a href="/bmc/contents.do?mId=0205000000"><img src="./images/main/main_section3_business_ico5.png" alt=""><p>도시개발사업</p></a>
						<a href="/bmc/contents.do?mId=0206000000"><img src="./images/main/main_section3_business_ico6.png" alt=""><p>혁신도시개발사업</p></a>
						<a href="/bmc/contents.do?mId=0207000000"><img src="./images/main/main_section3_business_ico7.png" alt=""><p>택지개발사업</p></a>
						<a href="/bmc/contents.do?mId=0208000000"><img src="./images/main/main_section3_business_ico8.png" alt=""><p>주택건립사업</p></a>
						<a href="/bmc/contents.do?mId=0209000000"><img src="./images/main/main_section3_business_ico9.png" alt=""><p>공공건축사업</p></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	
		