<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="ko">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <title>부산도시공사</title>
        <meta name="description"lang="ko"content="부산의 일상을 만듭니다. 주거복지, 도시공간 전문 공기업">
		<meta property="og:title"content="부산도시공사"/>
		<meta property="og:description"content="부산의 일상을 만듭니다. 주거복지, 도시공간 전문 공기업"/>
       <style>


@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap');
    * { 
        padding: 0; margin: 0; border: 0; box-sizing: border-box; 
        text-decoration: none; list-style-type: none;
    }
    html { height: 100%; }
    body { 
        /* position: absolute; top: 0; bottom: 0; left: 0; right: 0; */
        width: 100%; height: 100%; overflow-x: hidden;
        background-color: #fdfdff;
        font-family: 'Noto Sans KR', sans-serif; word-break: keep-all; 
    }
    .intro-wrap { 
        position: relative; 
        width: 100%; min-height: 100%; height: auto; 
        font-size: 0;
    }
    .intro-wrap::before,
    .intro-wrap::after {
        content: '';
        position: absolute;
        display: block;
        width: 100%; height: 100%;
        /* background-image: url(images/intro_bg_new.jpg); */
        background-position: 50% 50%;
        background-repeat: no-repeat;
        /* background-size: contain; */
        background-size: cover;
        z-index: -2;
    }
    .intro-wrap::before { 
        top: 0; 
        background-image: url(images/intro_bg_t.png); 
        background-position: center top; 
    }
    .intro-wrap::after { 
        bottom: 0; 
        background-image: url(images/intro_bg_b.png); 
        background-position: center bottom; 
    }

    h1 {padding-top: 140px; padding-bottom: 20px; }
    .section { display: flex; flex-wrap: wrap; justify-content: center; flex-direction: row; padding-bottom: 40px; }
    .section > a { 
        width: 370px; height: 370px; border-radius: 185px; 
        overflow: hidden;
        margin: 0 10px 40px; 
        background-repeat: no-repeat;
        background-position: 50% 50%;
        background-size: cover;
        position: relative;
        transition: .4s;
        cursor: pointer;
    }
    /* background-image */
    .section > a.a1 { background-image: url(images/b1_03.png); }
    .section > a.a2 { background-image: url(images/b1_03_02.jpg); }
    

    .section > a > span.blind { 
        display: block; 
        width: 100%; height: 100%;
        background-color: rgba(15, 90, 175, 0.8); 
        transition: .4s;
    }
    .section > a:nth-of-type(2n) > span.blind { background-color: rgba(20, 125, 135, 0.8); }
    .section > a:hover > span.blind { background-color: rgba(0, 0, 0, 0.4); }

    .section > a > div.text {
        position: absolute; top: 40%; transform: translateY(-50%); 
        width: 100%; text-align: center;
        letter-spacing: -2;
        color: #fff; 
    }
    .section > a > div.text > p:nth-of-type(1) { font-size: 28px; font-weight: 200; }
    .section > a > div.text > p:nth-of-type(2) { font-size: 32px; font-weight: 500; }

    .section > a > div.introBtn { 
        position: absolute; bottom: 70px; left: 50%; transform: translateX(-50%); 
        width: 160px; height: 50px; line-height: 48px; 
        font-size: 16px; color: #fff; text-align: center;
        border: 1px solid #fff; box-sizing: border-box;
        transition: .4s;
    }
    .section > a:hover > div.introBtn { background-color: #fff; font-weight: 400; color: #111; }

	@media screen and (max-width: 1400px) {
		.intro-wrap::before, .intro-wrap::after { background-size: contain; }
	}
    @media screen and (max-width: 1280px) {
        .section > a { max-width: 370px; width: 30%; }
    }
    @media screen and (max-width: 960px) {
        .section > a { width: 45%; }
        .intro-wrap::before, .intro-wrap::after { background-size: contain; }
    }
    @media screen and (max-width: 640px) {
        h1 { padding-top: 80px; }
        .section { flex-direction: column; align-content: center; padding-bottom: 100px; }
        .section > a { height: 280px; border-radius: 15px; width: 60%; margin-bottom: 10px; }
    }
    @media screen and (max-width: 480px) {
        .section > a { width: 80%; }
        .section > a > div.text > p:nth-of-type(1) { font-size: 20px; }
        .section > a > div.text > p:nth-of-type(2) { font-size: 24px; }
        .section > a > div.introBtn { font-size: 16px; }
    }
			
        </style>
        

    </head>
    <script type="text/javaScript">
		
		let today = new Date(); 
		
		var date = new Date();
        var year = date.getFullYear().toString();

        var month = date.getMonth() + 1;
        month = month < 10 ? '0' + month.toString() : month.toString();

        var day = date.getDate();
        day = day < 10 ? '0' + day.toString() : day.toString();

        var hour = date.getHours();
        hour = hour < 10 ? '0' + hour.toString() : hour.toString();

        var minites = date.getMinutes();
        minites = minites < 10 ? '0' + minites.toString() : minites.toString();

        var seconds = date.getSeconds();
        seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString();

        var todate = year + "-" + month + "-" + day + " " + hour + ":" + minites + ":" + seconds;
		

	

	</script>
    <body>
    
		<div class="intro-wrap">
	        <h1><img src="images/logo.png" alt="부산도시공사" style="display: block; margin: 0 auto; margin-bottom: 50px;"></h1>
	        <div class="section">
	            <a href="/nfChk.jsp" id="a1" class="a1" title="부산도시공사 홈페이지 바로가기">
	                <span class="blind"></span>
	                <div class="text">
	                    <p>부산도시공사</p>
	                    <p>홈페이지 바로가기</p>
	                </div>
	                <div class="introBtn">자세히 보기</div>
	                <!-- btn 01 -->
	            </a> 
	            
	            <a href="/nfMoveRes.jsp" id="a2" class="a2" title="아미4 행복주택 이사날짜 예약 바로가기">
	                <span class="blind"></span>
	                <div class="text">
	                    <p>아미4 행복주택</p>
	                    <p>이사날짜 예약 바로가기</p>
	                </div>
	                <div class="introBtn">자세히 보기</div>
	                <!-- btn 01 -->
	            </a> 

	        </div>
	    </div>         
    </body>
    
<script>

	
	if(todate > '2023-11-30 24:00:00'){
		$("#a2").remove();
	}
	
	
</script>
</html>
