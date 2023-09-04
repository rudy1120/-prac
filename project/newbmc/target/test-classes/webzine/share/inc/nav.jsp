<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div class="gnbOpen">
        <div class="navClose"></div>
        <div class="gnbWrap">
            <ul class="gnbArea">
                <li class="depth1">
                    <a href="/webzine/sub1_1.jsp">부산테마<span class="gnb_more"><img src="/webzine/images/main/bx_slide_btn_on.png" alt=""></span></a>
                    <ul class="m" style="">
                        <li><a href="/webzine/sub1_1.jsp"><span>케렌시아, 지친 영혼을 위로하다</span></a></li>
                        <li><a href="/webzine/sub1_2.jsp"><span>케렌시아를 찾는 그대, 가장 순수했던 ‘나’로 돌아가라</span></a></li>
                        <li><a href="/webzine/sub1_3.jsp"><span>태양, 나무, 바다가 만든 우리들의 케렌시아 오시리아</span></a></li>
                        <li><a href="/webzine/sub1_4.jsp"><span>당신은 자신만의 케렌시아가 있나요?</span></a></li>
                    </ul>
                </li>

                <li class="depth1">
                    <a href="/webzine/sub2_1.jsp">부산동네<span class="gnb_more"><img src="/webzine/images/main/bx_slide_btn_on.png" alt=""></span></a>
                    <ul class="m" style="">
                        <li><a href="/webzine/sub2_1.jsp"><span>부산, 동네여행</span></a></li>
                        <li><a href="/webzine/sub2_2.jsp"><span>나날이 새로워지는 서면의 색다른 얼굴을 찾아서</span></a></li>
                        <li><a href="/webzine/sub2_3.jsp"><span>우리 가족 ‘뒷집 할매’ 광무식당 고무순 씨</span></a></li>
                        <li><a href="/webzine/sub2_4.jsp"><span>두근두근 첫 걸음 두두커피&떡방 전창엽, 박혜전 부부</span></a></li>
                    </ul>
                </li>

                <li class="depth1">
                    <a href="/webzine/sub3.jsp">부산청년<span class="gnb_more"><img src="/webzine/images/main/bx_slide_btn_on.png" alt=""></span></a>
                    <ul class="m" style="">
                        <li><a href="/webzine/sub3.jsp"><span>청년의 시간을 말하다</span></a></li>
                    </ul>
                </li>

                <li class="depth1">
                    <a href="/webzine/sub4_1.jsp">바다가 소식<span class="gnb_more"><img src="/webzine/images/main/bx_slide_btn_on.png" alt=""></span></a>
                    <ul class="m" style="">
                        <li><a href="/webzine/sub4_1.jsp"><span>오시리아 테마파크 착공식·관광일자리 창출 협약식 개최</span></a></li>
                        <li><a href="/webzine/sub4_2.jsp"><span>동래 행복주택 입주자 모집</span></a></li>
                        <li><a href="/webzine/sub4_3.jsp"><span>BMC 핫이슈</span></a></li>
                        <li><a href="/webzine/sub4_4.jsp"><span>BMC 사업현황</span></a></li>
                    </ul>
                </li>

                <li class="depth1">
                    <a href="/webzine/sub5.jsp">독자의견<span class="gnb_more"><img src="/webzine/images/main/bx_slide_btn_on.png" alt=""></span></a>
                    <ul class="m" style="">
                        <li><a href="/webzine/sub5.jsp"><span>바다가 독자의견</span></a></li>
                    </ul>
                </li>
            <ul>
        </div>

        <div class="menu_box">
            <p>
                이 공간의 이름은 ‘<i style="color:#3559a7;"> 바다가</i> ’ 입니다.<br />
                부산도시공사의 자음만 모아보니,<br />
                <span>‘ㅂㅅㄷㅅㄱㅅ’</span>가 되었고,<br />
                사이사이에 있는 ㅅ을 살짝 기울여보니,<br />
                 ‘<i style="color:#3559a7;"> 바다가</i> ’ 가 되었습니다.<br />
                부산의 바다처럼 더 많은 것을 품고,<br />
                더 넓은 세상으로 나아가는 도시글,<br />
                <span>우리의 이야기가 담긴 공간</span><br />
                 ‘<i style="color:#3559a7;"> 바다가</i> ’ 를 만들어 나가겠습니다.
            </p>
        </div>
    </div>
	<script>
		$(function(){
			$('.gnb > a').click(function(e){
				e.preventDefault();
				$('.gnbOpen').animate({
					left: 0
				}, 500);
			});
			$('.navClose').click(function(e){
				e.preventDefault();
				$('.gnbOpen').animate({
					left: '-100%'
				}, 500);
			});
			$('.depth1 > a').click(function(e){
				e.preventDefault();
				if($(this).next().css('display') == 'block') {
					$(this).next().slideUp(400);
				}else {
					$('ul.m').slideUp(400);
					$(this).next().slideDown(400);
				}
			});
		});
	</script>