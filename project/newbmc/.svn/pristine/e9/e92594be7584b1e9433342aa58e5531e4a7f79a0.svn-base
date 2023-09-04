<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 대표 포털 메인
	 *
	 * 수정일            수정자           수정내용
	 * -------------    ------------    -----------------------------
	 * 2019-05-09       김선영           마크업 수정
	 *
	 *
	 * @author 김선영
	 * @since 2019.05.09
	 */
%>

<!-- main visual s -->
<section id="visualWrap">
	<h4 class="blind">VISUAL</h4>
	<div class="visual">
		<c:set var="promotionList" value="${visualzoneList}" scope="request"/>
		<jsp:include page="/common/jsp/component/promotions.jsp"/>
	</div>
</section>
<!-- //main visual e -->

<!-- main content01 s -->
<div class="cont_wrap">
	<div class="clFix">
		<div id="popupWrap">
			<h4 class="blind">POPUP ZONE</h4>
			<div class="popup">
				<c:set var="promotionList" value="${popupzoneList}" scope="request"/>
				<jsp:include page="/common/jsp/component/promotions.jsp"/>
			</div>
		</div>
		<h4 class="blind">콘텐츠 바로가기</h4>
		<dl class="direct_link">
			<dt>콘텐츠페이지</dt>
			<dd>
				콘텐츠페이지 입니다<br>링크를 통해 자세하게 보실 수 있습니다.
				<a href="#" target="_blank" title="새 창으로 열림">자세히보기</a>
			</dd>
		</dl>
		<dl class="direct_link">
			<dt>콘텐츠페이지</dt>
			<dd>
				콘텐츠페이지 입니다<br>링크를 통해 자세하게 보실 수 있습니다.
				<a href="#">자세히보기</a>
			</dd>
		</dl>	
	</div>
</div>
<!-- //main content01 e -->

<!-- main content02 s -->
<div class="cont_wrap">
	<div class="clFix">
		<div class="notice">
			<h4><span>공지사항</span></h4>
			<ul>
				<li><a href="#">최근 소식이 등록 순으로 노출 됩니다.</a><span>19.04.08</span></li>
				<li><a href="#">최근 소식이 등록 순으로 노출 됩니다.</a><span>19.04.06</span></li>
				<li><a href="#">최근 소식이 등록 순으로 노출 됩니다.</a><span>19.04.04</span></li>
				<li><a href="#">최근 소식이 등록 순으로 노출 됩니다.</a><span>19.04.02</span></li>
				<li><a href="#">최근 소식이 등록 순으로 노출 됩니다.</a><span>19.04.01</span></li>
			</ul>
			<a href="#" class="btn_more">+<span class="blind">더보기</span></a>
		</div>

		<div class="photo_news">
			<h4><span>포토뉴스</span></h4>
			<ul class="clFix">
				<li>
					<a href="#">
						<figure> 
							<p><img src="https://via.placeholder.com/217x125" alt="포토뉴스 이미지 - 01"></p>
							<figcaption>뉴스제목</figcaption> 
						</figure>
					</a>
				</li>
				<li>
					<a href="#">
						<figure> 
							<p><img src="https://via.placeholder.com/217x125" alt="포토뉴스 이미지 - 02"></p>
							<figcaption>뉴스제목</figcaption> 
						</figure>
					</a>
				</li>
				<li>
					<a href="#">
						<figure> 
							<p><img src="https://via.placeholder.com/217x125" alt="포토뉴스 이미지 - 03"></p>
							<figcaption>뉴스제목</figcaption> 
						</figure>
					</a>
				</li>
			</ul>
			<a href="#" class="btn_more">+<span class="blind">더보기</span></a>
		</div>

		<ul class="quick_link">
			<li><a href="#"><span class="tit">주요메뉴01</span></a></li>
			<li><a href="#"><span class="tit">주요메뉴02</span></a></li>
			<li><a href="#"><span class="tit">주요메뉴03</span></a></li>
			<li><a href="#"><span class="tit">주요메뉴04</span></a></li>
			<li><a href="#"><span class="tit">주요메뉴05</span></a></li>
			<li><a href="#"><span class="tit">주요메뉴06</span></a></li>
			<li><a href="#"><span class="tit">주요메뉴07</span></a></li>
			<li><a href="#"><span class="tit">주요메뉴08</span></a></li>
		</ul>
	</div>
</div>
<!-- main content02 e -->

<!-- banner List s -->
<div id="bannerWrap">
	<div>
		<h4>배너모음</h4>
		<div class="control banBtn">
			<a href="#" class="btn play" data-rel="play" style="display: none;"><span class="blind">재생</span></a>
			<a href="#" class="btn pause" data-rel="stop"><span class="blind">일시정지</span></a>
			<a href="/portal/contents.do?mId=0706000000" class="btn more" title="배너모음 더보기"><span class="blind">더보기</span></a>
			<a href="#" class="btn prev" rel="prev"><span class="blind">이전 배너 보기</span></a>
			<a href="#" class="btn next" rel="next"><span class="blind">다음 배너 보기</span></a>
		</div>

		<div class="banner_list">
			<ul style="left: 0px;">
				<li data-idx="61"><a href="http://easylaw.go.kr/CSP/Main.laf" target="_blank" title="새창열림">찾기쉬운 생활법령정보</a></li>
				<li data-idx="62"><a href="http://www.kopico.go.kr/main/main.do" target="_blank" title="새창열림">개인정보 분쟁조정위원회</a></li>
				<li data-idx="63"><a href="http://www.namk.or.kr" target="_blank" title="새창열림">전국시장&middot;군수&middot;구청장협의회</a></li>
				<li data-idx="64"><a href="http://www.cwurc.or.kr" target="_blank" title="새창열림">창원시도시재생지원센터</a></li>
				<li data-idx="65"><a href="http://www.mois.go.kr/frt/sub/popup/p_taegugki_banner/screen.do" target="_blank" title="새창열림">국가상징</a></li>
				<li data-idx="66"><a href="http://www.klac.or.kr/main.jsp" target="_blank" title="새창열림">대한법률구조공단</a></li>
				<li data-idx="67"><a href="http://1398.acrc.go.kr/case/ISGAcase" target="_blank" title="새창열림">청탁금지법 통합검색</a></li>
				<li data-idx="1"><a href="http://www.smartcity.or.kr" target="_blank" title="새창열림">스마트도시협회</a></li>
				<li data-idx="2"><a href="http://www.ccrs.or.kr" target="_blank" title="새창열림">신용회복위원회</a></li>
				<li data-idx="3"><a href="http://www.poba.or.kr" target="_blank" title="새창열림">행정공제회</a></li>
				<li data-idx="4"><a href="http://1398.acrc.go.kr/hpg/req/hpgPssStep1.do" target="_blank" title="새창열림">복지&middot;보조금부정신고</a></li>
				<li data-idx="5"><a href="http://www.ejorye.go.kr" target="_blank" title="새창열림">주민참여조례</a></li>
				<li data-idx="6"><a href="http://www.digitaltv.or.kr" target="_blank" title="새창열림">저소득층 DTV신청안내</a></li>
				<li data-idx="7"><a href="http://www.ev.or.kr" target="_blank" title="새창열림">환경부 전기차 충전소</a></li>
				<li data-idx="8"><a href="http://www.gndec.co.kr" target="_blank" title="새창열림">장애인 기업제품 직판장</a></li>
				<li data-idx="9"><a href="http://www.gyeongnam.go.kr/index.gyeong?contentsSid=3071" target="_blank" title="새창열림">스마트 공장  확산 홍보</a></li>
				<li data-idx="10"><a href="http://www.women1366.kr/stopds/" target="_blank" title="새창열림">디지털 성범죄 피해자 지원센터</a></li>
				<li data-idx="11"><a href="http://open.gdoc.go.kr" target="_blank" title="새창열림">문서24</a></li>
				<li data-idx="12"><a href="http://www.cwcfmap.or.kr " target="_blank" title="새창열림">창원문화지도 ‘이음’</a></li>
				<li data-idx="13"><a href="http://www.laiis.go.kr/lips/nya/lrn/localRegulationList.do" target="_blank" title="새창열림">중앙부처 법령 유권해석</a></li>
				<li data-idx="14"><a href="http://www.knnanumi.or.kr" target="_blank" title="새창열림">경남 나누미</a></li>
				<li data-idx="15"><a href="http://www.better.go.kr" target="_blank" title="새창열림">규제정보포털</a></li>
				<li data-idx="16"><a href="http://www.liveinkorea.kr" target="_blank" title="새창열림">다누리(다문화가족지원포털)</a></li>
				<li data-idx="17"><a href="http://www.acrc.go.kr/acrc/board.do?command=searchDetail&amp;menuId=0502070106&amp;menuId=05020701" target="_blank" title="새창열림">그림으로 보는 청탁금지법</a></li>
				<li data-idx="18"><a href="http://www.safemap.go.kr" target="_blank" title="새창열림">생활안전지도 서비스</a></li>
				<li data-idx="19"><a href="http://jobfunds.or.kr" target="_blank" title="새창열림">일자리 안정자금</a></li>
				<li data-idx="20"><a href="http://www.gov.kr/portal/main" target="_blank" title="새창열림">정부24</a></li>
				<li data-idx="21"><a href="http://www.opinet.co.kr/user/main/mainView.do;jsessionid=znnJQEvPFaFCrcauHgcwbMmabmZQiI1qx4sh215Rz1dReOnN97kbTUuwOQ6aROJA.opwas_2_servlet_engine2" target="_blank" title="새창열림">유가정보서비스</a></li>
				<li data-idx="22"><a href="http://www.gnrobot.or.kr" target="_blank" title="새창열림">경남로봇랜드재단</a></li>
				<li data-idx="23"><a href="http://idanury.bccard.com" target="_blank" title="새창열림">경남 i-다누리카드</a></li>
				<li data-idx="24"><a href="http://kostat.go.kr/office/dnro/index.action" target="_blank" title="새창열림">경남지방통계청</a></li>
				<li data-idx="25"><a href="http://www.acrc.go.kr/acrc/board.do?command=searchDetail&amp;menuId=050201" target="_blank" title="새창열림">국민권익위원회</a></li>
				<li data-idx="26"><a href="http://www.epeople.go.kr/jsp/user/UserMain.jsp" target="_blank" title="새창열림">국민신문고</a></li>
				<li data-idx="27"><a href="http://www.bjfez.go.kr/main.web" target="_blank" title="새창열림">부산진해 경제자유구역</a></li>
				<li data-idx="28"><a href="http://www.keco.or.kr/kr/business/water/contentsid/2863/index.do" target="_blank" title="새창열림">생태독성 관리제도 온라인 종합정보시스템</a></li>
				<li data-idx="29"><a href="http://www.elis.go.kr" target="_blank" title="새창열림">자치법규 정보시스템(ELIS)</a></li>
				<li data-idx="30"><a href="http://www.nec.go.kr/portal/main.do">중앙선거관리위원회</a></li>
				<li data-idx="31"><a href="http://www.mois.go.kr" target="_blank" title="새창열림">행정안전부</a></li>
				<li data-idx="32"><a href="http://www.boho.or.kr/main.do" target="_blank" title="새창열림">내컴퓨터 지킴이! 보호나라</a></li>
				<li data-idx="33"><a href="http://www.safekorea.go.kr/idsiSFK/index_web.jsp" target="_blank" title="새창열림">국가재난정보센터</a></li>
				<li data-idx="34"><a href="http://www.changwon.go.kr/depart/contents.do?mId=0202070100" target="_blank" title="새창열림">창원시내 전통시장 보러가기</a></li>
				<li data-idx="35"><a href="https://www.privacy.go.kr">개인정보보호종합지원시스템</a></li>
				<li data-idx="36"><a href="http://online.bokjiro.go.kr/apl/aplMain.do" target="_blank" title="새창열림">우리아이 보육료 및 유아학비 신청</a></li>
				<li data-idx="37"><a href="http://nip.cdc.go.kr/irgd/index.html" target="_blank" title="새창열림">영유아 필수예방접종 전액지원사업 안내</a></li>
				<li data-idx="38"><a href="http://cw.kugeu.org/main/main.php" target="_blank" title="새창열림">창원시 공무원노동조합</a></li>
				<li data-idx="39"><a href="http://www.naqs.go.kr/contents/sectionD-4/sectionD-4_01_01.naqs" target="_blank" title="새창열림">원산지표시 위반정보 공표(농산물)</a></li>
				<li data-idx="40"><a href="http://gn.chest.or.kr/02_collect/person/person03.jsp" target="_blank" title="새창열림">경남 사회복지공동모금회 축하기부 카드</a></li>
				<li data-idx="41"><a href="http://www.safe182.go.kr/cont/homeLogContents.do?contentsNm=intro_portal117" target="_blank" title="새창열림">117 학교폭력 신고센터</a></li>
				<li data-idx="42"><a href="http://www.1365.go.kr/vols/main.do" target="_blank" title="새창열림">1365 자원봉사</a></li>
				<li data-idx="43"><a href="http://www.hometax.go.kr/websquare/websquare.html?w2xPath=/ui/pp/index.xml" target="_blank" title="새창열림">국세청 홈택스</a></li>
				<li data-idx="44"><a href="http://www.airkorea.or.kr/index" target="_blank" title="새창열림">전국실시간 대기오염정보</a></li>
				<li data-idx="45"><a href="http://www.mfds.go.kr/index.do?mid=476" target="_blank" title="새창열림">수입식품 방사능 검사정보</a></li>
				<li data-idx="46"><a href="http://www.mois.go.kr/frt/sub/a03/corruptionDeclareInfo/screen.do" target="_blank" title="새창열림">공직비리 익명신고</a></li>
				<li data-idx="47"><a href="http://www.kcomwel.or.kr/kcomwel/main.jsp" target="_blank" title="새창열림">근로복지공단</a></li>
				<li data-idx="48"><a href="http://blog.naver.com/homedoc2013" target="_blank" title="새창열림">서민에게 힘이되는 법률홈닥터</a></li>
				<li data-idx="49"><a href="http://www.acrc.go.kr/acrc/Main.do" target="_blank" title="새창열림">공익신고제도 안내 및 신고창구</a></li>
				<li data-idx="50"><a href="http://changwon.grandculture.net/?local=changwon" target="_blank" title="새창열림">디지털창원문화대전</a></li>
				<li data-idx="51"><a href="http://www.myhome.go.kr/hws/portal/cont/selectContRentalView.do#guide=RH101" target="_blank" title="새창열림">임대주택포털</a></li>
				<li data-idx="52"><a href="http://www.safepeople.go.kr/#main" target="_blank" title="새창열림">안전신문고</a></li>
				<li data-idx="53"><a href="http://www.mois.go.kr/frt/a01/frtMain.do" target="_blank" title="새창열림">행정안전부</a></li>
				<li data-idx="54"><a href="http://www.work.go.kr/senior/main/main.do" target="_blank" title="새창열림">장년나침반 생애설계프로그램</a></li>
				<li data-idx="55"><a href="http://www.gpin.go.kr/center/main/index.gpin?" target="_blank" title="새창열림">공공 I-PIN 재인증</a></li>
				<li data-idx="56"><a href="http://ccei.creativekorea.or.kr/gyeongnam/main.do" target="_blank" title="새창열림">경남창조경제혁신센터</a></li>
				<li data-idx="57"><a href="http://www.safe182.go.kr/index.do" target="_blank" title="새창열림">안전 Dream 아동·여성·장애인 경찰지원센터</a></li>
				<li data-idx="58"><a href="http://www.law.go.kr/main.html" target="_blank" title="새창열림">법령·조례 원클릭 서비스</a></li>
				<li data-idx="59"><a href="http://www.nfqs.go.kr/2013/index.asp" target="_blank" title="새창열림">수산물 원산지표시 위반사항 공표</a></li>
				<li data-idx="60"><a href="http://www.share.go.kr/main_www_2015.jsp" target="_blank" title="새창열림">하나로민원</a></li>
			</ul>
		</div>
	</div>
</div>
<!--// banner List e -->
	
<%-- ##### BANNER ##### --%>
<!--
<h4>BANNER</h4>
<c:set var="promotionList" value="${bannerList}" scope="request"/>
<jsp:include page="/common/jsp/component/promotions.jsp"/>
-->

<script type="text/javascript" src="/common/js/main.js"></script>