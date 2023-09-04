<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<html lang="ko">
	<head>
		<title>디자인가이드</title>
		<meta charset="UTF-8">
		<meta name="description" content="Smart CMS"/>
		<meta name="keywords" content=""/>
		<meta name="author" content="YHDatabase Co., Ltd"/>
		<meta name="Title" content="Smart CMS"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/layout.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/board.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/application.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/opendata.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/jquery-ui.css" />
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/treeView/jquery.treeview.css" />
	    

		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.10.0.custom.min.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/common.js" charset="UTF-8" ></script>

	    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/treeView/jquery.cookie.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/treeView/jquery.treeview.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/prefixfree.min.js" ></script>
		<%@ include file="/sys/jsp/component/default.jsp" %>
    	<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

		<!--[if IE 8]>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/ie8_only.css" />
		<![endif]-->


		<!--[if lt IE 8]>
			<link rel="stylesheet" type="text/css" href="/sys/css/ie_access.css" />
			<script type="text/javascript">
			    document.write('<div  class="ie_06"><p>본사이트는 IE8이상의 브라우저에 최적화가 되어있습니다.<br/>현재 브라우저 버전에서는 편리하게 이용 하실 수 없습니다. Windows Internet Explorer를 업데이트를 받으세요</p><a href="http://windows.microsoft.com/ko-KR/internet-explorer/downloads/ie" target="_blank" class="btn_update">Internet Explorer 다운로드 받기</a></div>');
			</script>
		<![endif]-->
		
		<style type="text/css">
			/* 가이드용 디자인 */
			.contanier{background: #3e4959; min-width: auto;}
			.content{margin: 0 auto; width: 1100px; background: #fff; padding: 30px;}
			
			h4{font-size: 22px; font-weight: 600;}
			h4:BEFORE{content: ''; display: block; width: 22px; height: 3px; background: #f52712; margin-bottom: 10px; margin-top: 30px;}
			h5{font-size: 20px; font-weight: 600; margin-left: 15px; margin-top: 10px; color: #969696}
			h5:BEFORE{content: ''; display: inline-block; width: 5px; height: 5px; background: #f52712; margin-bottom: 10px; margin-top: 30px; border-radius:50%; position: relative; top: 5px; margin-right: 5px;}
			h6{font-size: 18px; font-weight: 600; margin-left: 30px; color: #969696;}
			h6:BEFORE{content: ''; display: block; width: 22px; height: 3px; background: #f52712; margin-bottom: 10px; margin-top: 30px;}
			
			.content > p, form > p{padding-left: 0;}
			
			p.p_h5{margin-left: 25px;}
		</style>

	</head>
	<body>
		<div class="wrap">
			<div class="contanier">
				<div class="content">
					<h4>관리자단 디자인가이드</h4>
					<p class="p_h4">관리자단 디자인 가이드입니다. 개발팀 참고용으로 작성되었습니다.</p>
					<p class="p_h4">사용자 버튼 클래스와는 상이할 수 있습니다.</p>
					<p class="p_h4">이미지폴더가 images에서 img로 변경되었습니다. 이미지가 엑박으로 나오면 img로 변경해주세요.</p>
					
					<h5>버튼 속성들</h5>
					<p class="p_h5">a와 button, input에 사용가능합니다.</p>
					<h6>색상별</h6>
					<div class="btnboxC">
						<a href="" class="btn_white">흰색(베이직)</a>
						<a href="" class="btn_red">빨간색</a>
						<a href="" class="btn_orange">주황색</a>
						<a href="" class="btn_green">초록색</a>
						<a href="" class="btn_mint">하늘색</a>
						<a href="" class="btn_blue">파란색</a>
						<a href="" class="btn_black">검은색</a>
						<a href="" class="btn_off">비활성화버튼(인증해주세요 버튼같은)</a>
					</div>
					<h6>아이콘별</h6>
					<div class="btnboxC">
						<a href="" class="btn_return">설정</a>
					</div>
					<h6>버튼이 테이블안에 있을 때</h6>
					<ul class="list_ul_h6">
						<li>주소검색, 아이디찾기 등 폼 안에 버튼이 들어갈 경우 버튼클래스명 옆에 inner를 넣어주세요.</li>
						<li>클래스명은 띄어쓰기 후 넣어주시면됩니다.</li>
						<li>ex) class="btn_whtie inner"</li>
					</ul>
					<table class="table_st Thead">
						<caption>테이블 캡션은 꼭! thead 안에 있는 텍스트가 모두 들어가줘야합니다.</caption>
						<colgroup>
							<col span="1" class="w20"/>
							<col span="1" class="w80"/>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">이름</th>
								<td>홍길동</td>
							</tr>
							<tr>
								<th scope="row">주소검색</th>
								<td>
									<a href="" class="btn_white inner">주소검색</a>
								</td>
							</tr>
							<tr>
								<th scope="row">버튼 컬러여러개</th>
								<td><a href="" class="btn_black inner">까만색</a><a href="" class="btn_red inner">빨간색</a></td>
							</tr>
						</tbody>
					</table>
					
					<h6>버튼 정렬클래스</h6>
					<div class="btnboxL">
						<p>btnboxL</p>
						<a href="" class="btn_white">버튼 왼쪽정렬</a>
					</div>
					<div class="btnboxC">
						<p>btnboxC</p>
						<a href="" class="btn_white">버튼 센터정렬</a>
					</div>
					<div class="btnboxR">
						<p>btnboxR</p>
						<a href="" class="btn_white">버튼 오른쪽정렬</a>
					</div>
					
					<h6>마진값, 패딩값</h6>
					<p class="p_h6">마진값이나 패딩값을 넣을경우 가장먼저 먹히도록 수정되었습니다. 과거엔 _im 을 달아야했으나 이제는 mT0 으로 깔끔히 달 수 있습니다.</p>
					<p class="p_h6">마진값 범위는 5, 10, 15, 20, 30, 40 만 작성되어있습니다. 미세한 정렬이 필요할 시 디자인팀을 소환해주세요.</p>
					<ul class="list_ul_h6">
						<li>mT0 = margin-top="0"</li>
						<li>mB0 = margin-bottom="0"</li>
						<li>mL0 = margin-left="0"</li>
						<li>mR0 = margin-right="0"</li>
					</ul>
					
					<h5>Tip 아이콘</h5>
					<p class="info mT20">단순 정보는 <span class="red">i 모양의 info</span>를 써주세요.</p>
					<p class="check">꼭 살피고 넘어가야할 정보는 <span class="red">체크모양 check</span>를 써주세요.</p>
					<p class="caution">경고는 caution을 써주세요.텍스트가 전부 빨갛고 진하게 표시됩니다.</p>
					<p class="mL20">ex)</p>
					<p class="caution">이미지 등록시 이미지 설명글을 필수로 작성해주세요.</p>
				</div>
			</div>
		</div>
	</body>
</html>