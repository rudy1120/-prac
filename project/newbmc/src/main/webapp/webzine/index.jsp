<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/webzine/share/inc/header.jsp" %>

    <div id="container">

        <div id="banner">
            <!-- 롤링 이미지 -->
            <div class="cont">
                <div class="slider">
                    <div class="m_imgbg m_img01"></div>
					<div class="m_imgbg m_img02"></div>
					<div class="m_imgbg m_img03"></div>
					<div class="m_imgbg m_img04"></div>
                </div>
            </div>
            <!-- //롤링 이미지 -->
			<style>
				.bx-has-pager{
					position: absolute;
					bottom: 100px;
					width: 100px;
					left: 50%;
					margin-left: -50px;
				}
			</style>
			<script>
				$(document).ready(function(){
					$('.slider').bxSlider({
						speen : 1000,
						pager : true,
						controls : false,
						auto : true,
						pause : 7000
					});
				});
			</script>

            <!-- text -->
            <div id="#back-bottom" class="inner">
                <p class="txtPp">소소하고 따뜻한</p>
                <h1 class="txtPp"><img src="/webzine/images/main/badaga_img.png" alt="바다가"/></h1>
                <p class="mtM50">부산의 일상을 담은 공간</p>
                <a href="#" class="sclDown">
                    시작하기
                </a>
            </div>
            <!-- //text -->
        </div>

        <script>
        //to bottom
        $(document).ready(function(){

            // hide #back-top first

            $('.sclDown').click(function(e){
                e.preventDefault();
                $('body,html').animate({ scrollTop: 1000 }, 1000);

            });
        });
        </script>


        <!-- 메인 컨텐츠 -->

        <!-- 여는글 -->
        <div id="one" class="intro">
            <div class="inner">
                <div>
                    <div class="box">
                        <div class="introtxt">
                            <h2>여는 글</h2>
                            <em>
                            누구에게나 특별한 곳이 있습니다. 어릴 적 고향 친구들과 뛰어놀던 정든 동네<br />
                            청춘의 꿈을 위해 공부하고, 도전하고, 일하던 곳<br />
                            태어나고 자라 한평생을 사는 도시<br />
                            지친 일상의 탈출, 한여름의 추억이 담긴 꿈의 여행지<br />
                            당신에게 부산은 어떤 존재인가요?<br />
                            우리가 살아가는 도시 부산이 우리에게, 누군가에게, 언젠가 인연이 닿을 누구에게나<br />
                            소중한 곳이 되기를 바랍니다.<br />
                            ‘바다가’는 그런 마음으로 부산의 공간을 이야기하고 우리가 살고 있고, <br />
                            살아갈 부산 동네를 탐방하며 다양한 색을 지닌 부산사람들의 삶까지 함께 <br />
                            나누어 가겠습니다.<br />
                            ‘바다가’를 통해 당신이 떠올리는 부산이 점점 더 좋아졌으면 좋겠습니다.
                            </em>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- //여는글 -->


        <!-- 부산테마 -->
        <div id="t_con" class="two intro style">
            <div class="inner">
                <div class="con_title">
                    <span class="light_txt">Busan Theme</span>
                    <h3><span>부산</span>테마</h3>
                </div>

                <div class="seccon">
                    <div class="sec">
                        <div>
                            <a href="/webzine/sub1_1.jsp">
                                <span class="tofcon">프롤로그 _</span>
                                <img src="/webzine/images/main/img001.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>케렌시아, 지친 영혼을 위로하다</h4>
                            <p>많은 이들이 매일 아침 출근하며 ‘전장...</p>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub1_2.jsp">
                                <span class="tofcon">테마 에세이 _</span>
                                <img src="/webzine/images/main/img002.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>케렌시아를 찾는 그대, 가장 순수했던 ‘나’로 돌아가라</h4>
                            <p>어떤 이들은 요가를 하며 맥주를 마시고...</p>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub1_3.jsp">
                                <span class="tofcon">테마 플레이스 _</span>
                                <img src="/webzine/images/main/img003.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>태양, 나무, 바다가 만든 우리들의 케렌시아 오시리아</h4>
                            <p>문득 어디론가 떠나고 싶은 날이면 자연...</p>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub1_4.jsp">
                                <span class="tofcon">테마 리포트 _</span>
                                <img src="/webzine/images/main/img004.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>당신은 자신만의 케렌시아가 있나요?</h4>
                            <p>아침 사과가 금이라면 케렌시아는 직장인...</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- //부산테마 -->


        <!-- 부산동네 -->
        <div id="t_con" class="three intro style">
            <div class="inner">
                <div class="con_title">
                    <span class="light_txt">Busan Neighborhood</span>
                    <h3><span>부산</span>동네</h3>
                </div>

                <div class="seccon">
                    <div class="sec">
                        <div>
                            <a href="/webzine/sub2_1.jsp">
                                <span class="tofcon">프롤로그 _</span>
                                <img src="/webzine/images/main/img005.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>부산, 동네여행</h4>
                            <p>우리는 부산에 산다. 바다가 예쁜 도시...</p>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub2_2.jsp">
                                <span class="tofcon">서면 서쪽 동네 이야기 _</span>
                                <img src="/webzine/images/main/img006.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>나날이 새로워지는 서면의 색다른 얼굴을 찾아서</h4>
                            <p>동서고가도로에서 서면롯데백화점을 지...</p>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub2_3.jsp">
                                <span class="tofcon">인터뷰① _</span>
                                <img src="/webzine/images/main/img007.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>우리가족‘뒷집 할매’</h4>
                            <p>광무식당 고무순 씨 “할매, 할매 세 끼 먹...</p>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub2_4.jsp">
                                <span class="tofcon">인터뷰② _</span>
                                <img src="/webzine/images/main/img008.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>두근두근 첫 걸음</h4>
                            <p>광무식당을 따라 더 안쪽 골목으로 들어...</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- //부산동네 -->


        <!-- 부산청년 -->
        <div id="t_con2" class="five intro style">
            <div class="inner">
                <div class="con_title">
                    <span class="light_txt">Busan Youth</span>
                    <h3><span>부산</span>청년</h3>
                </div>

                <div class="seccon">
                    <div class="sec">
                        <div>
                            <a href="/webzine/sub3.jsp">
                                <img src="/webzine/images/main/img013.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>청년의 시간을 말하다</h4>
                            <p>공채 15기 신입사원과 함께</p>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="https://youtu.be/EFFIDhsox9w" target="_blank" title="동영상 새창열기">
                                <img src="/webzine/images/main/img014.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>(영상)부산청년하우스콘서트</h4>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="https://youtu.be/vahNkMz1Wwk" target="_blank" title="동영상 새창열기">
                                <img src="/webzine/images/main/img015.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>(영상)바다가 들려주는 행복한 우리집 콘서트</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- //부산청년 -->


        <!-- 바다가소식 -->
        <div id="t_con" class="four intro style">
            <div class="inner">
                <div class="con_title">
                    <span class="light_txt">Badaga News</span>
                    <h3><span>바다가</span>소식</h3>
                </div>

                <div class="seccon">
                    <div class="sec">
                        <div>
                            <a href="/webzine/sub4_1.jsp">
                                <span class="tofcon">BMC 특집① _</span>
                                <img src="/webzine/images/main/img009.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>오시리아 테마파크 착공식·관광일자리 창출 협약식 개최</h4>
                            <p>더 많은 부산 청년이 생겨나도록, 계속해서 우...</p>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub4_2.jsp">
                                <span class="tofcon">BMC 특집② _</span>
                                <img src="/webzine/images/main/img010.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>동래 행복주택 입주자 모집</h4>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub4_3.jsp">
                                <span class="tofcon">BMC 핫이슈 _</span>
                                <img src="/webzine/images/main/img011.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>바다가 선택한 2019년 상반기 주요 뉴스</h4>
                        </div>
                    </div>

                    <div class="sec">
                        <div>
                            <a href="/webzine/sub4_4.jsp">
                                <span class="tofcon">바다가 프로젝트 _</span>
                                <img src="/webzine/images/main/img012.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="sec_txt">
                            <h4>도시에 행복을 심는 바다가 PROJECT</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- //바다가소식 -->




        <div class="inner main_banner">
            <div class="">
                <a href="/webzine/sub5.jsp" class="banner_L" title="독자의견 기사읽기">
                    <img src="/webzine/images/main/banner_01.gif" alt="독자의견 기사읽기" class="banner_img" />
                </a>
                <a href="/webzine/magazine01.pdf" target="_blank" class="banner_R" title="웹진 PDF 보기 바로보기">
                    <img src="/webzine/images/main/banner_02.gif" alt="웹진 PDF 보기 바로보기" class="banner_img" />
                </a>
            </div>
        </div>

    </div>
    <!-- //메인 컨텐츠 -->

<%@ include file="/webzine/share/inc/footer.jsp" %>
