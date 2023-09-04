<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
	/**
	 * 디자인 가이드
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.06.28		김은진			최초 생성 및 퍼블리싱
	 * 2019.05.20		김선영			바로가기메뉴 마크업 수정
	 *
	 * @author 김은진
	 * @since 2017.06.28
	 */
%>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>디자인 가이드 | YH데이타베이스</title>

		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/common.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/all_layout.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/board.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/application.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/layout.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/sub.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/portal/css/sub${fn:substring(menuVO.mId, 0, 2)}.css" />
		<%@ include file="/common/jsp/component/default.jsp" %>

	</head>
	<body>
		<div id="skipNav">
			<a href="#container">본문 바로가기</a>
			<a href="#lnbWrap">주메뉴 바로가기</a>
		</div>

		<div id="wrap">
		<%-- gnb공통영역 include --%>
		<jsp:include page="/common/jsp/layouts/header.jsp" />
			<div id="container" class="cB">
				<section id="content">
				<jsp:include page="/common/jsp/layouts/spot.jsp" />
				<jsp:include page="/common/jsp/layouts/tab.jsp" />
					<div id="conts" class="conts">
					<nav class="tab_depth04">
						<ul class="list03">
							<li><a href="#" title="탭1" class="on">탭1</a> </li>
							<li><a href="#" title="탭2" class="">탭2</a> </li>
							<li><a href="#" title="탭3" class="">탭3</a></li>
						</ul>
					</nav>
					<div class="box_h4 mB20">
						<button class="btn01" onclick="fn_spread('hiddenContent');">To.개발팀 참고사항</button><br />
						<div id="hiddenContent" class="example01" style="display: none;">
							<p class="p_h4"><strong>table에서 합계가 사용되는 현황 등 페이지 사용시</strong></p>
							<ul class="list_ul_h4">
								<li>html5.1 변경으로 인해 tfoot 위치가 변동되었습니다.</li>
								<li>table 안에는 thead > tbody > tfoot 순으로 들어가야 합니다.</li>
								<li>관리자 디자인 가이드는 <a href="/sys/sys_guide.jsp" target="_blank" title="새창열림"><strong>여기</strong></a>에서 확인 가능합니다.</li>
							</ul>
							<p class="p_h4"><strong>클래스명 변동사항</strong></p>
							<ul class="list_ul_h4">
								<li>테이블에 사용되는 클래스명 통일 = w숫자</li>
								<li>게시판 클래스명 변경 = bod_분류 ex) bod_list, bod_blog</li>
							</ul>
						</div>

						<button class="btn01" onclick="fn_spread('hiddenContent2');">To.개발팀 주의사항</button><br />
						<div id="hiddenContent2" class="example01" style="display: none;">
							<ul class="list_ul_h4">
								<li>select 에는 label 연결 또는 title 입력</li>
								<li>ID 중복 주의 (한 페이지에 동일한 ID는 한번만)</li>
								<li>input label 미제공</li>
								<li>iframe title삽입/ marginHeight, marginWidth, frameBorder, width, height 등 속성 넣지 말기(css에서 제어)</li>
								<li>ul 태그 바로 하위에는 li 태그만 삽입 가능 br, div등 삽입 불가!</li>
								<li>window.open 사용 시 a 태그에 title="새창열림" 추가</li>
								<li><strong>엔티티 코드 사용요청(새올에서 불러오거나 사용자가 작성시)</strong>
									<p>&옆 여백은 지우고 사용해주세요.</p>
									<ul>
										<li>"" 따옴표는 <strong>& quot;</strong> </li>
										<li>여백은 <strong>& nbsp;</strong></li>
										<li>& 은 <strong>& amp;</strong></li>
										<li>&lt; 은 <strong>& lt;</strong>, &gt; 은<strong>& gt;</strong> </li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
					<h4 class="mT0">컨텐츠에서 제일큰제목 제일먼저 선언된 h4에는 mT_im0 이라는 클래스 넣기</h4>
					<h4>제일큰제목</h4>
					<h5>그다음제목</h5>
					<h6>그다음제목</h6>
					<p class="p_h6">태그는 꼭 4>5>6 순서로 써주세요.</p>

					<h4>박스태그사용법</h4>
					<p class="box_h4">집중이 필요한 글귀를 넣는곳입니다.</p>
					<div class="box_h4">div로도 사용가능합니다.</div>
					<p class="p_h4">일반p태그만사용할때</p>
					<h5>h5제목</h5>
					<p class="box_h5">h5다음으로 오는 박스 글귀영역 입니다.</p>
					<div class="box_h5">div로도 사용가능합니다.</div>
					<p class="p_h5">h5다음으로 오는 일반p태그만사용할때</p>
					<h6>h6제목</h6>
					<p class="box_h6">h6다음으로 오는 박스 글귀영역입니다.</p>
					<p class="p_h6">h6다음으로 오는 일반p태그만사용할때</p>

					<h4>목록태그사용법</h4>
					<p class="box_h4">목록태그도 위에 따라오는 제목에 따라 클래스가 다르므로 꼼꼼히 확인하세요.</p>
					<ul class="list_ul_h4">
						<li>순서목록입니다.순서목록입니다순서목록입니다순서목록입니다<br/>순서목록입니다순서목록입니다</li>
						<li>순서목록입니다.</li>
						<li>순서목록입니다.</li>
						<li>순서목록입니다.
							<ul>
								<li>그하위목록</li>
								<li>그하위목록
									<ul>
										<li>제일마지막목록</li>
										<li>제일마지막목록</li>
										<li>제일마지막목록</li>
									</ul>
								</li>
								<li>그하위목록</li>
								<li>그하위목록</li>
							</ul>
						</li>
						<li>순서목록입니다.</li>
					</ul>

					<h5>순서있는 목록사용법</h5>
					<ol class="list_num_h5">
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.
							<ul>
								<li>그하위목록</li>
								<li>그하위목록
									<ul>
										<li>제일마지막목록</li>
										<li>제일마지막목록</li>
										<li>제일마지막목록</li>
									</ul>
								</li>
								<li>그하위목록</li>
								<li>그하위목록</li>
							</ul>
						</li>
						
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
						<li>순서있는 목록입니다.</li>
					</ol>

					<h5>h5태그다음으로 오는 목록</h5>
					<ul class="list_ul_h5">
						<li>순서목록입니다.순서목록입니다순서목록입니다순서목록입니다<br/>순서목록입니다순서목록입니다</li>
						<li>순서목록입니다.</li>
						<li>순서목록입니다.</li>
						<li>순서목록입니다.</li>
					</ul>

					<h6>h6태그다음으로 오는 목록</h6>
					<ul class="list_ul_h6">
						<li>순서목록입니다.순서목록입니다순서목록입니다순서목록입니다<br/>순서목록입니다순서목록입니다</li>
						<li>순서목록입니다.</li>
						<li>순서목록입니다.</li>
						<li>순서목록입니다.
							<ul>
								<li>제일마지막목록</li>
								<li>제일마지막목록</li>
								<li>제일마지막목록</li>
							</ul>
						</li>
					</ul>


					<h4>table사용방법</h4>
					<ul class="list_ul_h4">
						<li>colgroup 사용시 꼭 갯수에 맞게 사용해주세요.</li>
						<li>콜그룹의 콜 너비 클래스명은 w숫자 입니다. ex) w20</li>
						<li>열이 행의 타이틀값을 가졌을 때 꼭 th로 써주세요.</li>
						<li>반응형 테이블이 있습니다. 양이 많은건 반응형 처리해주시고, 줄어들어도 깨지지 않는건 그냥 놔둬주세요.</li>
						<li>h5다음으로 오는건 tbl_h5를 써서 정렬을 맞춰주세요</li>
					</ul>
					<div class="tbl_respon">
						<table class="tbl">
							<caption>html5기반으로 summary가 없습니다. 여기에 summary에 적듯이 다 적으시면되요</caption>
							<colgroup>
								<col span="1" class="w20"/>
								<col span="1" class="w20"/>
								<col span="1" class="w20"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">제목</th>
									<th scope="col">제목</th>
									<th scope="col">제목</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">제목</th>
									<td>내용</td>
									<td>내용</td>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<td class="taR">부분적으로 정렬하고싶을때</td>
									<td>내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td class="taC">부분적으로 정렬하고싶을때 내용</td>
									<td>내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td>내용</td>
									<td>내용</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<th scope="row">표의 합계</th>
									<td>내용</td>
									<td>내용</td>
								</tr>
							</tfoot>
						</table>
					</div>

					<h5>table 전체적으로 가운데정렬 및 rowspan 있는경우</h5>
					<div class="tbl_respon tbl_h5">
						<table class="tbl taC">
							<caption>html5기반으로 summary가 없습니다. 여기에 summary에 적듯이 다 적으시면되요</caption>
							<colgroup>
								<col span="1" class="w20"/>
								<col span="1" class="w20"/>
								<col span="1" class="w20"/>
								<col span="1" class="w20"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col" rowspan="2">제목</th>
									<th scope="col" colspan="2">제목</th>
									<th scope="col" rowspan="2">제목</th>
								</tr>
								<tr>
									<th scope="col">제목</th>
									<th scope="col">제목</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row" rowspan="2">제목</th>
									<td>내용</td>
									<td>
										<ul>
											<li>내용</li>
											<li>내용
												<ul>
													<li>내용</li>
													<li>내용</li>
												</ul>
											</li>
											<li>내용</li>
										</ul>
									</td>
									<td>내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td>내용</td>
									<td>내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td colspan="3">내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td>내용</td>
									<td>내용</td>
									<td>내용</td>
								</tr>
							</tbody>
						</table>
					</div>
					<h5>thead가 없는 table table에 Thead 라는 클래스 추가해주세요</h5>
					<div class="tbl_respon tbl_h5">
						<table class="tbl Thead">
							<caption>html5기반으로 summary가 없습니다. 여기에 summary에 적듯이 다 적으시면되요</caption>
							<colgroup>
								<col span="1" class="w20"/>
								<col span="1" class="w20"/>
								<col span="1" class="w20"/>
								<col span="1" class="w20"/>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row" rowspan="3">제목</th>
									<td>내용</td>
									<td>내용</td>
									<td>내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td>내용</td>
									<td>내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td colspan="2">내용</td>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<td>내용</td>
									<td>
										<ul>
											<li>내용</li>
											<li>내용</li>
											<li>내용</li>
										</ul>
									</td>
									<td>내용</td>
								</tr>
							</tbody>
						</table>
					</div>
					<h5>테이블 반응형시</h5>
					<p class="p_h5">div 로 감싸고 클래스명을 tbl_respon 로 넣어줍니다. 그리고 모바일시에만 안내문이 나타나도록 처리해주세요.</p>
					<div class="tbl_respon tbl_h5">
						<p class="p_notice">모바일 사용시 표를 좌우로 이동하여 내용을 확인 할 수 있습니다.</p>
						<table class="tbl">
							<caption>html5기반으로 summary가 없습니다. 여기에 summary에 적듯이 다 적으시면되요</caption>
							<colgroup>
								<col span="3" class="w20"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">제목</th>
									<th scope="col">제목</th>
									<th scope="col">제목</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">제목</th>
									<td>내용</td>
									<td>내용</td>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<td class="taR">부분적으로 정렬하고싶을때</td>
									<td>내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td class="taC">부분적으로 정렬하고싶을때 내용</td>
									<td>내용</td>
								</tr>
								<tr>
									<td>내용</td>
									<td>내용</td>
									<td>내용</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<th scope="row">표의 합계</th>
									<td>내용</td>
									<td>내용</td>
								</tr>
							</tfoot>
						</table>
					</div>

					<h4>A태그코딩</h4>
					<a href="#">링크</a><br/>
					<a href="#" target="_blank">새창링크</a>
					<ul class="list_ul_h4">
						<li>리스트 안에 들어가는 새창버튼 <a href="#" target="_blank" class="btn_blue_blank">새창링크</a></li>
					</ul>


					<h4>이미지박스</h4>
					<p class="p_h4">이미지박스는 가운데정렬이 기본이며 아래처럼 경로는 절대경로로 넣어줍니다.</p>
					<p class="p_h4 red">alt값은 꼭 정확하게 적어주세요. 이미지안에 들어간 텍스트와 정확히 일치해야합니다. (간략히 적는 거 안됩니다.)</p>
					<div class="box_img img_respon">
						<img src="/portal/img/content/img_con0506_01.gif" longdesc="/portal/contents/02/long_05_06.html" alt="사전심사청구제도 처리절차"/>
					</div>

					<h4>이미지박스 반응형</h4>
					<p class="p_h4">반응형시  img_respon 클래스를 추가해주고 클릭시 화면이 확대되도록 a태그를 추가해주세요.</p>
					<p class="p_h4">a태그에 link_imgview 클래스를 추가해주세요.</p>
					<div class="box_img img_respon">
						<a href="" class="link_imgview">이미지 크게보기</a>
						<img src="/portal/img/content/img_con0506_01.gif" longdesc="/portal/contents/02/long_05_06.html" alt="사전심사청구제도 처리절차"/>
					</div>


					<h4>버튼</h4>
					<div class="btnboxC">
						<a href="#" class="btn_blue_down">민원별 구비서류 및 처리기간 양식 다운로드</a>
						<a href="#" class="btn_blue_down">사전심사청구서 양식 다운로드</a>
						<a href="#" class="btn_blue_blank">새창링크인데 버튼형식으로 따로 처리할경우</a>
						<a href="#" class="btn_blue_view">파일보기할경우</a>
						<a href="#" class="btn_blue_hwp">한글파일</a>
						<a href="#" class="btn_blue_pdf">pdf파일</a>
						<a href="#" class="btn_blue_jpg">사진파일</a>
						<a href="#" class="btn_blue_xlsx">엑셀파일</a>
					</div>

					<h4>지도 작업시 </h4>
					<p>api 로 작업됩니다. 웹표준으로 인해 지도칸 위에 건너뛰기 버튼을 생성해주세요. 생성후 오시는 길 텍스트가 있는 부분에 아이디를 걸고, href 값에 아이디값을 넣어주세요.<br />
					지도가 한 개 이상인 경우 뒤에는 숫자를 붙여줍니다. ex) mapJ mapJ2</p>
					<div class="btnboxR">
						<a href="#mapJ" class="btn_blue_skip">지도이미지영역 건너뛰기</a>
					</div>

					<div class="box_img">
					지도이미지 영역
					</div>

					<div class="guide_box" id="mapJ">
						<ul>
							<li class="icon_car">
								<h5>자가용 이용</h5>
								<ul class="list_ul_h5">
									<li>경부고속도로 안성IC → 보개면 주민센터 (약 21분)</li>
									<li>중부고속도로 일죽IC → 보개면 주민센터 (약 22분)</li>
								</ul>
							</li>
							<li class="icon_add">
								<h5>주소</h5>
								<p class="p_h5">경기도 안성시 보개면 보개원삼로 220</p>
							</li>
							<li class="icon_call">
								<h5>연락처</h5>
								<p class="p_h5">대표전화 ☎031-678-36800</p>
							</li>
						</ul>
					</div>

					<h4>컨텐츠 공통 다운로드 파일링크 예시</h4>
					<p class="p_h4">
						1. goFile 공통스크립트는 /common/js/common/common.js 안에 선언하여 공통으로 사용하였으니 별도로 컨텐츠 내부에 따로 선언하지 마시기 바랍니다.<br/>
						2. 인자는 '파일명', '파일명을 제외한 전체경로' 입니다. 파일명을 제외한 전체경로를 생성시 '/사이트코드/메뉴id' 가 기본이며 하위 추가 디렉토리에 대해서는 추가 명시해주시면 됩니다.<br/>
					   물론 대상파일은 서버상에 위치해 있어야 합니다. (http://www.anseong.go.kr/download/사이트코드/메뉴ID 로 생성하여 첨부파일 위치 )
					</p>
					<ul>
						<li><a href="#" onclick="goFile('civ01010201_2.hwp','/portal/0000000000');return false;" class="btn_blue_down" title="새창으로 파일다운로드">「민원별 구비서류 및 처리기간」 다운로드</a></li>
						<li><a href="#" onclick="goFile('civ01010201_3.hwp','/portal/0000000000');return false;" class="btn_blue_down" title="새창으로 파일다운로드">사전심사청구서 양식 다운로드 </a></li>
						<li><a href="#" onclick="goFile('civ01010201_4.hwp','/portal/0000000000');return false;" class="btn_blue_down" title="새창으로 파일다운로드">이의신청서양식 다운로드 </a></li>
					</ul>

					<h4>게시판 머리말</h4>
					<br />
					<div class="board_head">
						<span class="icon_head"></span>
						<p class="title_st">타이틀</p>
						<p>그냥 p 태그</p>
						<ul>
							<li>ul안의 li 리스트</li>
							<li>리스트</li>
						</ul>
					</div>

					<h4>게시판 버튼</h4>
					<br />
					<div class="btnboxR">
						<a href="#" class="btn_blue_blank">바로가기</a>
						<a href="#" class="btn_blue_blank">새창열기</a>
						<a href="#" class="btn_blue_down">다운로드</a>
						<a href="#" class="btn_blue_view">파일보기</a>
						<a href="#" class="btn_blue_normal">목록</a>
						<a href="#" class="btn_sky_normal">수정</a>
						<a href="#" class="btn_red_normal">삭제</a>
						<a href="#" class="btn_blue_normal">글쓰기</a>
					</div>
					<div class="box_page">
							<a href="#" onclick="goPage(1); return false;" class="btn_frist">맨처음페이지</a>
							<a href="#" onclick="goPage(1); return false;" class="btn_10prev">10페이지 앞으로</a>
							<span>1</span>
							<a href="#" onclick="goPage(2); return false;">2</a>
							<a href="#" onclick="goPage(3); return false;">3</a>
							<a href="#" onclick="goPage(4); return false;">4</a>
							<a href="#" onclick="goPage(11); return false;" class="btn_10next">10페이지 뒤로</a>
							<a href="#" onclick="goPage(600); return false;" class="btn_end">맨끝페이지</a>
					</div>

					<h4>YH캘린더</h4>
					<p class="p_h4">yh캘린더는 기존에 사용중인 디자인을 이용해주시고, 꼭 디자인팀에 디자인 검토해달라고 말씀해주세요.</p>
				
					<h4 class="mB10">일정보기/수정</h4>
					<table class="tbl Thead">
							<caption>3&middot;1운동기념관 예약 등록</caption>
							<colgroup>
								<col span="1" class="col20">
								<col span="1">
								<col span="1" class="col20">
								<col span="1">
							</colgroup>
							<tbody>
								<tr>
									<th><label>회차 정보</label></th>
									<td>1회차</td>
									<th><label>관람시각</label></th>
									<td>09:30 ~ 10:00</td>
								</tr>
								<tr>
									<th scope="row"><label>예약자명</label></th>
									<td>
										상천규(본인인증 이름 자동 입력)
										<input name="rName" id="rName" type="hidden" value="상천규">
					<!-- 					<input name="rName" id="rName" type="text" data-required="y" data-fieldname="예약자명" data-validator="on" title="input"  class="input100" maxLength="20" title="예약자명"/> -->
									</td>
									<th scope="row"><label for="hp1"> 예약자 휴대전화</label></th>
									<td>
										<select id="hp1" name="hp1" data-required="y" data-fieldname="휴대폰 앞자리" data-validator="on">
											<option selected="selected" value="010">010</option>
											<option value="011">011</option>
											<option value="016">016</option>
											<option value="017">017</option>
											<option value="018">018</option>
											<option value="019">019</option>
										</select>
										<input name="hp2" id="hp2" class="input50" data-required="y" data-fieldname="폰번호 가운데자리" data-validator="on" value="" maxlength="4" type="text" onkeypress="return numkeyCheck(event)" title="연락처2">
										<input name="hp3" id="hp3" class="input50" data-required="y" data-fieldname="폰번호 마지막자리" data-validator="on" value="" maxlength="4" type="text" onkeypress="return numkeyCheck(event)" title="연락처3">
									</td>
								</tr>

								<tr>
									<th scope="row"><label for="rLeaderName">인솔자명</label></th>
									<td>
										<input name="rLeaderName" id="rLeaderName" type="text" data-required="y" data-fieldname="인솔자명" data-validator="on" title="input" class="input100" maxlength="20">
										<input class="sameName" id="sameName" type="checkbox" onclick="fnCheck(this);">예약자와 동일
									</td>
									<th scope="row"><label for="lhp1"> 인솔자 휴대전화</label></th>
									<td>
										<select id="lhp1" name="lhp1" data-required="y" data-fieldname="휴대폰 앞자리" data-validator="on">
											<option selected="selected" value="010">010</option>
											<option value="011">011</option>
											<option value="016">016</option>
											<option value="017">017</option>
											<option value="018">018</option>
											<option value="019">019</option>
										</select>
										<input name="lhp2" id="lhp2" class="input50" data-required="y" data-fieldname="폰번호 가운데자리" data-validator="on" value="" maxlength="4" type="text" onkeypress="return numkeyCheck(event)" title="연락처2">
										<input name="lhp3" id="lhp3" class="input50" data-required="y" data-fieldname="폰번호 마지막자리" data-validator="on" value="" maxlength="4" type="text" onkeypress="return numkeyCheck(event)" title="연락처3">
									</td>
								</tr>

								<tr>
									<th scope="row"><label for="rGroupName">단체명</label></th>
									<td>
										<input name="rGroupName" id="rGroupName" type="text" data-required="y" data-fieldname="단체명" data-validator="on" title="input" class="input100" maxlength="20">
									</td>
									<th scope="row"><label for="rPeopleNumber">관람인원</label></th>
									<td>
										<input name="rPeopleNumber" id="rPeopleNumber" type="text" data-required="y" data-fieldname="관람인원" data-validator="on" title="input" class="input100" maxlength="2" onkeypress="return numkeyCheck(event)">명
									</td>
								</tr>

							</tbody>
						</table>

					<h4 class="mB10">설문조사</h4>
					<div class="bod_survey">
						<ul>
							<li>
								<a href="" class="servey_wrap">
									<div href="#" class="col22">
										<p class="survey_pic">
											<span><img alt="이미지 없음" src="/common/img/board/poll_noimg.jpg" /></span>
										</p>
									</div>
									<div class="survey_con">
										<span class="survey_tit">yh데이타베이스 설문조사입니다.</span>
										<span class="survey_date"><strong>설문기간</strong> : 2017-06-05 ~ 2017-07-28</span>
										<span class="survey_txt"> <strong>상태</strong> : <span class="blue">진행</span> </span>
										<span class="survey_people"> <strong>참여자</strong> : 10명 </span>
								        <span class="survey_end"><strong>결과공개여부</strong> : <span class="open">공개</span> <span class="secret">비공개</span></span>
						        			<%-- <a href="#" class="btn_blue btn_blog_white">준비중</a>
											<a href="javascript:pollResult('${page}', '${element.cfgCode}');" class="btn_blue btn_blog_white">결과(공개)</a>
											<a href="javascript:pollView('${page}', '${element.cfgCode}');" class="btn_blue btn_blog_white">참여하기</a> --%>
									</div>
									<a href="/newDesign/portal/pollView.jsp" class="btn_poll poll_start">참여하기</a>
					 		        <a href="/newDesign/portal/pollView.jsp" class="btn_poll poll_end">설문종료</a>
					 		        <a href="/newDesign/portal/pollView.jsp" class="btn_poll poll_ready">진행예정</a>
								</a>
							</li>
							<li class="no_data"><span>등록된 게시물이 없습니다.</span></li>
						</ul>
						
					</div>

					<h4 class="mB10">블로그게시판</h4>
					<div class="bod_wrap">
						<div class="bod_blog">
							<ul>
								<li class="noData">등록된 게시물이 없습니다.</li>
								<li>
									<a href="">
										<p class="blog_pic"><img src="/common/images/board/sample_noimage.jpg" alt=""></p>
										<div class="blog_con">
											<span class="blog_tit">기사회생 평택 브레인시티 [기호일보 2016년07월22일]</span>
											<span class="blog_day">작성일 2016.07.25 <span class="line"></span> 조회 6</span>
											<span class="blog_txt">기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]</span>
										</div>
									</a>
								</li>
								<li>
									<a href="">
										<p class="blog_pic"><img src="/common/images/board/sample_noimage.jpg" alt=""></p>
										<div class="blog_con">
											<span class="blog_tit">기사회생 평택 브레인시티 [기호일보 2016년07월22일]기호일보 2016년07월22일]기호일보 2016년07월22일]</span>
											<span class="blog_day">작성일 2016.07.25 <span class="line"></span> 조회 6</span>
											<span class="blog_txt">기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일] 기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]기사회생 평택 브레인시티 [기호일보 2016년07월22일]</span>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</div>

					<h4 class="mB10">일반게시판</h4>
					<div class="bod_wrap">
						<div class="bod_head">
							<p class="page_num">현재 페이지 <span class="bold red">1</span> / 전체 페이지 2</p>
							<fieldset class="bod_search">
								<legend class="hidden">게시판 검색</legend>
								<label for="searchType" class="hidden">검색구분 선택 : </label> <select
									id="searchType" name="searchType" title="검색유형 선택">
									<option value="b_title">제목</option>
									<option value="b_write">작성자</option>
									<option value="b_content">내용</option>
								</select>
								<label for="searchTxt2" class="hidden">검색어 입력: </label>
								<input id="searchTxt2" name="searchTxt2" title="검색어 입력" type="text" value="">
								<input type="submit" value="검색" name="searchBt" title="검색" onclick="$('#page').val(1);">
							</fieldset>
						</div>
						<table class="bod_list">
							<caption>게시판제목에 대해 번호, 제목, 첨부, 작성자, 작성일, 조회수로 구분하여 나타내는 표입니다.</caption>
							<thead>
								<tr>
									<th scope="col" class="list_num">번호</th>
									<th scope="col" class="list_tit">제목</th>
									<th scope="col" class="list_file">첨부</th>
									<th scope="col" class="list_write">작성자</th>
									<th scope="col" class="list_date">작성일</th>
									<th scope="col" class="list_hit">조회수</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="list_num">2156</td>
									<td class="list_tit txtL">
										<a class="uline board" href="board_view.html"> 인동향교 한량들의 낙. 휴 콘서트&nbsp; 두 번... </a>
										<img src="/common/img/board/ico_new.gif" alt="새글" class="ico_new" />
									</td>
									<td class="list_file"><a href=""><img alt="파일명 : 별첨1" src="/common/img/board/pptx.gif"></a></td>
									<td class="list_write">박은진</td>
									<td class="list_date">2016-07-14</td>
									<td class="list_hit">138</td>
								</tr>
								<tr>
									<td class="list_num">2155</td>
									<td class="list_tit txtL">
										<a class="uline board" href=""> 2016 마을기업 온라인 페어 홍보 2016 마을기업 온라인 페어 홍보2016 마을기업 온라인 페어 홍보2016 마을기업 온라인 페어 홍보</a>
									</td>
									<td class="list_file">&nbsp;</td>
									<td class="list_write">노다해</td>
									<td class="list_date">2016-07-13</td>
									<td class="list_hit">163</td>
								</tr>
								<tr>
									<td class="list_num">2154</td>
									<td class="list_tit txtL"><a class="uline board" href=""> 맛있는 닭요리, 건강하게 즐기세요~ </a></td>
									<td class="list_file">
										<a href=""><img alt="첨부파일 2개가 있습니다." src="/common/img/board/pptx.gif"></a>
										<a href=""><img alt="첨부파일 2개가 있습니다." src="/common/img/board/xls.gif"></a>
									</td>
									<td class="list_write">전영아</td>
									<td class="list_date">2016-07-13</td>
									<td class="list_hit">263</td>
								</tr>
								<tr>
									<td class="list_num">2153</td>
									<td class="list_tit txtL"><a class="uline board" href=""> 2016.7.25. 개정 대부업법 시행 안내 및 ... </a></td>
									<td class="list_file"><img alt="파일명 : 별첨1" src="/common/img/board/hwp.gif"></td>
									<td class="list_write">김보우</td>
									<td class="list_date">2016-07-12</td>
									<td class="list_hit">229</td>
								</tr>
								<tr>
									<td class="list_num">2152</td>
									<td class="list_tit txtL"><a class="uline board" href=""> 긴급신고전화! 이제 3개 번호만 기억하세... </a></td>
									<td class="list_file"><img alt="첨부파일 3개가 있습니다." src="/common/img/board/jpg.gif"></td>
									<td class="list_write">윤세영</td>
									<td class="list_date">2016-07-12</td>
									<td class="list_hit">404</td>
								</tr>
							</tbody>
						</table>

						<div class="bod_foot">
							<div class="bod_btn">
								<a href="board_write.html" class="btn_blue_normal">글쓰기</a>
							</div>
							<div class="box_page">
								<a href="#" onclick="goPage(1); return false;" class="btn_frist">맨처음페이지</a>
								<a href="#" onclick="goPage(1); return false;" class="btn_10prev">10페이지 앞으로</a>
								<span>1</span>
								<a href="#" onclick="goPage(2); return false;">2</a>
								<a href="#" onclick="goPage(3); return false;">3</a>
								<a href="#" onclick="goPage(4); return false;">4</a>
								<a href="#" onclick="goPage(11); return false;" class="btn_10next">10페이지 뒤로</a>
								<a href="#" onclick="goPage(600); return false;" class="btn_end">맨끝페이지</a>
							</div>
						</div>
					</div>



					<h4 class="mB10">게시판보기</h4>
					<div class="bod_wrap">
						<div class="bod_view">
							<h4>낭독극장 〈연극으로 읽는 책_ 시인동주 〉 공연 안내</h4>
							<div class="view_info">
								<ul>
									<li class="view_write"><span>작성자</span> : 관리자</li>
									<li class="view_date"><span>등록일</span> : 2016-07-15</li>
									<li class="view_hit"><span>조회</span> : 43</li>
								</ul>
							</div>

							<div class="view_cont">
								<div class="view_cont_img"><img src="/common/images/board/sample.jpg" alt="" /></div>
								소사삼거리부터 진영실업고등학교까지 바짝 가로수를 잘라주셔서 밤이면 가로등이 환하게 도로를 비추어 줍니다.
								지금은 주민들이 가로수가 가로등을 막지않아 교통사고도 줄어들고, 주민들 또한 밤길을 걸어가는데 웃음을 줍니다.
								더불어 도로변에 장사하시는 분들도 흐뭇하게 생각합니다.
								앞으로도 소사구를 위해 열심히 관심을 갖고 일해 주시길 바랍니다.
								도시관리과 여러분께 감사드립니다.
							</div>

							<dl class="view_file">
								<dt><span>첨부파일(2)</span></dt>
								<dd>
									<ul>
										<li><a href="">청소년게임제공업 등록취소(예고) 공고문.hwp</a></li>
										<li><a href="">청소년게임제공업 등록취소(예고) 공고문.hwp</a></li>
									</ul>
								</dd>
							</dl>
						</div>

						<div class="bod_foot">
							<div class="bod_btn taR">
								<a href="board_list.html" class="btn_normal">목록</a>
							</div>
						</div>
						<div class="commentBox">
							<ul class="commentCon">
								<li>등록된 코멘트가 없습니다.</li>
							</ul>
							<div class="commentFom">
								<textarea id="cContent" name="cContent" title="코멘트 입력" rows="3"
									class="input99 mB10" maxlength="250" data-validator="on"
									data-required="y" data-fieldname="코멘트 내용" style="ime-mode: active;"></textarea>
								<p>
									<a href="#" onclick="fn_insert_comment(); return false;"
										class="btn_comment_submit"><span>등록</span></a>
								</p>
							</div>
						</div>
					
					<h4>갤러리 게시판</h4>
					<div class="bod_photo">
						<ul>
							<li>
								<a href="#">
									<span class="thumb"><img src="http://www.gj.go.kr/upload/board/4925/2016/3//20160304095933_1.jpg" alt="" /></span>
									<span class="subject">동물과 자연이 어우러진 세종시 베어트리파크</span>
									<span class="date">2016-07-19</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="thumb"><img src="http://www.sejong.go.kr/cmm/fms/getImage.do?kind=340&atchFileId=FILE_000000000071009&fileSn=0" alt="" /></span>
									<span class="subject">동물과 자연이 어우러진 세종시 베어트리파크</span>
									<span class="date">2016-07-19</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="thumb"><img src="http://www.sejong.go.kr/cmm/fms/getImage.do?kind=340&atchFileId=FILE_000000000071009&fileSn=0" alt="" /></span>
									<span class="subject">동물과 자연이 어우러진 세종시 베어트리파크</span>
									<span class="date">2016-07-19</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="thumb"><img src="http://www.sejong.go.kr/cmm/fms/getImage.do?kind=340&atchFileId=FILE_000000000071009&fileSn=0" alt="" /></span>
									<span class="subject">동물과 자연이 어우러진 세종시 베어트리파크</span>
									<span class="date">2016-07-19</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="thumb"><img src="http://www.sejong.go.kr/cmm/fms/getImage.do?kind=340&atchFileId=FILE_000000000071009&fileSn=0" alt="" /></span>
									<span class="subject">동물과 자연이 어우러진 세종시 베어트리파크</span>
									<span class="date">2016-07-19</span>
								</a>
							</li><li>
								<a href="#">
									<span class="thumb"><img src="http://www.sejong.go.kr/cmm/fms/getImage.do?kind=340&atchFileId=FILE_000000000071009&fileSn=0" alt="" /></span>
									<span class="subject">동물과 자연이 어우러진 세종시 베어트리파크</span>
									<span class="date">2016-07-19</span>
								</a>
							</li><li>
								<a href="#">
									<span class="thumb"><img src="http://www.sejong.go.kr/cmm/fms/getImage.do?kind=340&atchFileId=FILE_000000000071009&fileSn=0" alt="" /></span>
									<span class="subject">동물과 자연이 어우러진 세종시 베어트리파크</span>
									<span class="date">2016-07-19</span>
								</a>
							</li><li>
								<a href="#">
									<span class="thumb"><img src="http://www.sejong.go.kr/cmm/fms/getImage.do?kind=340&atchFileId=FILE_000000000071009&fileSn=0" alt="" /></span>
									<span class="subject">동물과 자연이 어우러진 세종시 베어트리파크</span>
									<span class="date">2016-07-19</span>
								</a>
							</li>
						</ul>
					</div>
						<div class="bod_foot">
							<div class="bod_btn taR">
								<a href="board_write.html" class="btn_blue_normal">글쓰기</a>
							</div>

							<div class="bod_page">
								<a href="#" onclick="goPage(1); return false;" class="btn_frist">맨처음페이지</a>
								<a href="#" onclick="goPage(1); return false;" class="btn_10prev">10페이지 앞으로</a>
								<span>1</span>
								<a href="#" onclick="goPage(2); return false;">2</a>
								<a href="#" onclick="goPage(3); return false;">3</a>
								<a href="#" onclick="goPage(4); return false;">4</a>
								<a href="#" onclick="goPage(11); return false;" class="btn_10next">10페이지 뒤로</a>
								<a href="#" onclick="goPage(600); return false;" class="btn_end">맨끝페이지</a>
							</div>
						</div>
					</div>



					<h4>게시판쓰기</h4>
						<div class="bod_wrap">
							<fieldset>
								<legend class="hidden">일반 게시판 글쓰기</legend>
								<p class="taR pB5">(<span class="red">*</span>)표시는 필수 입력 사항입니다.</p>
								<div class="bod_write">
									<dl>
										<dt>
											<span class="red" title="필수입력">*</span><label for="bTitle">제목</label>
										</dt>
										<dd>
											<input id="bTitle" name="bTitle" class="w90" data-required="y"
												data-fieldname="제목" data-validator="on" type="text" value=""
												maxlength="500">
										</dd>
									</dl>
									<dl>
										<dt>
											<span class="red" title="필수입력">*</span>작성자
										</dt>
										<dd>상가지</dd>
									</dl>
									<dl>
										<dt>
											<span class="red" title="필수 입력">*</span>비밀글
										</dt>
										<dd>
											<input id="bPublicY" name="bPublic" checked="checked" type="radio"
												value="Y"><label for="bPublicY">공개</label> <input
												id="bPublicN" name="bPublic" type="radio" value="N"><label
												for="bPublicN">비공개</label>
										</dd>
									</dl>
									<dl>
										<dt>
											<span class="red" title="필수입력">*</span><label for="bContent">내용</label>
										</dt>
										<dd>
											<textarea id="bContent" name="bContent" data-required="y"
												class="w90" data-fieldname="내용" data-validator="on" rows="20"
												cols="30"></textarea>
										</dd>
									</dl>
									<dl>
										<dt>
											<label for="egovComFileUploader">첨부 파일</label>
										</dt>
										<dd>
											<p class="orange">※ 파일은 최대 11개까지 첨부하실 수 있습니다. 첨부 가능한 하나의 파일
												사이즈는 최대 100Mbyte 입니다.</p>
											<script type="text/javascript"
												src="/common/js/common/EgovMultiFile.js"></script>
											<!-- DWR -->
											<script type="text/javascript"
												src="/dwr/interface/EgovFileMngDwr.js"></script>
											<script type="text/javascript" src="/dwr/engine.js"></script>
											<script type="text/javascript" src="/dwr/util.js"></script>
											<script type="text/javascript"
												src="/common/js/common/EgovFileUtils.js"></script>
											<div class="mT10">
												<input type="file" name="file_0" id="egovComFileUploader"
													title="파일첨부" class="w90"> <input type="file"
													name="hidden_dummy_file" style="display: none;">
											</div>
											<ul id="egovComFileList" class="pT10">
												<li id="noFile">선택된 파일이 없습니다.</li>
											</ul>
											<script type="text/javascript">
												//<![CDATA[  var multi_selector = new MultiSelector(document.getElementById("egovComFileList"), "11"); multi_selector.addElement(document.getElementById("egovComFileUploader"));  jQuery(document).ready(function() { var file_cnt = jQuery("[id^=FILE_]").length; if (0 < file_cnt && "11" <= file_cnt) { jQuery("#egovComFileUploader").prop("disabled", true); } });  //]]>
											</script>
											<input id="attachId" name="attachId" type="hidden" value="">
										</dd>
									</dl>
									<input type="hidden" id="viewGubun" name="viewGubun" value="">
									<dl class="pwWrap">
										<dt>
											<span class="red" title="필수입력">*</span><label for="bPassword">비밀번호</label>
										</dt>
										<dd>
											<input type="password" id="bPassword" name="bPassword"
												maxlength="50" data-validator="on" data-required="y"
												data-fieldname="비밀번호"> <span class="orange">※
												수정/삭제시 필요하오니 반드시 기억하시기 바랍니다.</span>
										</dd>
									</dl>
								</div>
								<div class="bod_foot">
									<div class="btnboxC">
										<input id="submitBtn" type="submit" class="btn" value="저장">
										<a href="#"
											onclick="document.getElementById('list').submit(); return false;"
											class="btn" title="이전 페이지로 이동">취소</a>
									</div>
								</div>
							</fieldset>
						</div>
						
						<h4>멀티 검색박스</h4>
						<ul class="list_ul_h4">
							<li>한 줄에 여러 검색 타입을 넣게 될 경우에는 li안에 div class="con" 으로 구분하여 여러개 넣어주세요</li>
							<li>마지막 div.con 에게는 div class="con bbn last" 라는 클래스를 넣어주세요.</li>
							<li>여러개가 아닌 경우에는 div로 쌀 필요 없이 해당 검색타입만 넣어주세요.</li>
							<li>마지막 줄 li에는 bbn 이라는 클래스를 넣어주세요.</li>
							<li>상세한 코드는 하단을 참고해주세요.</li>
						</ul>
						<%-- ##### 가이드 헤더 ##### --%>
						<div class="bod_guide">
							<p class="tit">개찰결과 이용 안내</p>
							<ul>
								<li>본 목록은 나라장터에서 제공하는 창원시 개찰결과로 매일 오전 8시에 데이터가 업데이트 됩니다.</li>
								<li>
									나라장터에서 제공하는 정보와 상이할 수 있으며, 보다 정확한 정보 및 상세한 내용 확인을 원하시는 분께서는
									<a href="http://www.g2b.go.kr/" target="_blank" title="나라장터 페이지로 새창이동">나라장터(조달청)</a>를 이용하여 주시기 바랍니다.
								</li>
							</ul>
						</div>
					
						<%-- ##### 검색 조건 ##### --%>
						<div class="multSearch">
							<fieldset>
								<legend class="hidden">검색제목</legend>
								<ul>
									<li>
										<div class="con">
											<label for="searchSubCode" class="tit">구분</label>
											<form:select path="searchSubCode" title="구분">
												<form:option value="">전체</form:option>
												<c:forEach var="type" items="${subTypeList}">
													<form:option value="${type.code}">${type.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
										<div class="con">
											<label for="searchPrgrss" class="tit mL10">진행사항</label>
											<form:select path="searchPrgrss" title="진행사항">
												<form:option value="">전체</form:option>
												<c:forEach var="prgrss" items="${prgrssList}">
													<form:option value="${prgrss}">${prgrss}</form:option>
												</c:forEach>
											</form:select>
										</div>
										<div class="con bbn last">
											<div class="day_start">
												<label for="searchSday" class="tit mL10">게시일자</label>
												<form:input path="searchSday" class="mR5" placeholder="예) 2017-08-15" data-validator="on" data-inputType="date" data-date="y" data-ismindatefor="searchEday" data-wa="y"/>
											</div>
											<span>~</span>
											<div class="day_end">
												<label for="searchEday" class="hidden">게시일자 검색 종료일 선택/입력</label>
												<form:input path="searchEday" class="mR5" placeholder="예) 2017-08-15" data-validator="on" data-inputType="date" data-date="y" data-ismaxdatefor="searchSday" data-wa="y"/>
											</div>
										</div>
									</li>
									<li>
										<label for="searchIdx" class="tit">공고번호</label>
										<form:input path="searchIdx" class="input500"/>
									</li>
									<li class="bbn">
										<label for="searchTxt" class="tit">공고명</label>
										<form:input path="searchTxt" class="input500"/>
										<div class="btn_box">
											<input type="submit" class="btn small search" name="searchBt" title="검색" onclick="$('#page').val(1);" value="검색"/>
											<input type="button" class="btn small" onclick="window.open('/portal/nara/sbid/list/downProc.do'); return false;" value="엑셀다운로드"/>
										</div>
									</li>
								</ul>
							</fieldset>
						</div>
					</div>
				</section>
			</div>
		</div>
	</body>
</html>
