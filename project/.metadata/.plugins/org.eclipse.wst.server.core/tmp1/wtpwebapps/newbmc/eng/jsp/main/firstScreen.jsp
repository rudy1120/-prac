<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

 <!-- container -->
 <div id="mContainer">
  <c:set var="visualList" value="${visualzoneList}" scope="request"/>
	
  <jsp:include page="/eng/jsp/layouts/component/mainVisual.jsp"/>
 </div>

  <div class="sec01" id="trg">
    <div class="inner row">
      <div class="name_box ky" >
        <p><i class="yll">BMC CEO</i> Kim Yong-hak</p>
      </div>
      <div class="lf_area">
        <h3>CEO Greetings</h3>
        <h4>We think of the future of Busan.</h4>
        <span>We built homes over the past 30 years by developing and providing housing in different sites and delivered hope to non-homeowners by engaging in projects to relieve housing issues.<br/>Facing the wave of change, we once again think of the future of Busan.</span>
        <div class="quick_box">
          <ul>
            <li><a href="/eng/contents.do?mId=0101000000" class="qk01">
              <div class="sec01_icon"> </div>
              <i>About BMC</i> </a> </li>
            <li> <a href="/eng/contents.do?mId=0102000000" class="qk02">
              <div class="sec01_icon"> </div>
              <i>CEO Greetings</i> </a> </li>
            <li> <a href="/eng/contents.do?mId=0201000000" class="qk03">
              <div class="sec01_icon"> </div>
              <i>History</i> </a> </li>
            <li> <a href="/eng/contents.do?mId=0104000000" class="qk04">
              <div class="sec01_icon"> </div>
              <i>Organization</i> </a> </li>
          </ul>
        </div>
      </div>
      <div class="rt_area"><img src="/eng/img/main/ceo_img.png" alt="bmc ceo" class="ceo" ></div>
    </div>
  </div>
  <div class="sec02">
    <span class="sec2_bg"></span>
    <div class="inner">
      <h3>BMC Projects</h3>
      <div class="column3"> 
        <div class="cln">
          <h4>My Home</h4>
          <span class="clnTtl">We Value Rest for Our Citizens</span>
          <div class="bd_pic">
            <div class="swiper swiper2">
              <div class="swiper-wrapper">
                <div class="swiper-slide zmEff"><a href="/eng/contents.do?mId=0301010000" class="bmcImg_box"><img src="/eng/img/main/sec02_img1.png" alt="Ami 4 Happy Housing"></a>
                  <p class="ttl">Ami 4 Happy Housing</p>
                </div>
                <!--
                <div class="swiper-slide"><a href="#" class="bmcImg_box"><img src="/eng/img/main/sec02_img1-5.png"></a>
                  <p class="ttl">Panoramic view of Banyeo District</p>
                </div>
                -->
              </div>
              <div class="pagWrap">
                <div class="swiper-pagination swper1-pagination"> </div>
                <!--
                <div class="btns">
                  <button class="btn-stop on using-mouse"><span class="blind">슬라이드 재생</span></button>
                </div>
                -->
              </div>
            </div>
          </div>
        </div>
        <div class="cln">
          <h4>Our Workplace</h4>
          <span class="clnTtl">We Envision a Future for Our Citizens</span>
          <div class="bd_pic">
            <div class="swiper swiper3">
              <div class="swiper-wrapper">
                <div class="swiper-slide"><a href="/eng/contents.do?mId=0302030000" class="bmcImg_box"><img src="/eng/img/main/sec02_img2.png" alt="Busan Munhyeon Innovation City Mixed-Used Development Project (Phase 3)"></a>
                  <p class="ttl">Busan Munhyeon Innovation City Mixed-Used Development Project (Phase 3)</p>
                </div>
                <!--
                <div class="swiper-slide"><a href="#" class="bmcImg_box"><img src="/eng/img/main/sec02_img2-2.png"></a>
                  <p class="ttl">Eco Delta City</p>
                </div>
                -->
              </div>
              <div class="pagWrap">
                <div class="swiper-pagination swper1-pagination"></div>
                <!--
                <div class="btns">
                  <button class="btn-stop on using-mouse" ><span class="blind">슬라이드 재생</span></button>
                </div>
                -->
              </div>
            </div>
          </div>
        </div>
        <div class="cln">
          <h4>Everyone's City</h4>
          <span class="clnTtl">We Care About What Citizens Think and Feel</span>
          <div class="bd_pic">
            <div class="swiper swiper4">
              <div class="swiper-wrapper">
                <div class="swiper-slide"><a href="/eng/contents.do?mId=0303010000" class="bmcImg_box"><img src="/eng/img/main/sec02_img3.png" alt="Osiria Tourist Complex"></a>
                  <p class="ttl">Osiria Tourist Complex</p>
                </div>
                <!--
                <div class="swiper-slide"><a href="#" class="bmcImg_box"><img src="/eng/img/main/sec02_img3-2.png"></a>
                  <p class="ttl">Busan International Arts Center</p>
                </div>
                -->
              </div>
              <div class="pagWrap">
                <div class="swiper-pagination swper1-pagination"></div>
                <!-- 
                <div class="btns">
                  <button class="btn-stop on using-mouse" ><span class="blind">슬라이드 재생</span></button>
                </div>
                -->
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="more"><a href="/eng/contents.do?mId=0300000000"></a></div>
    </div>
  </div>
  <div class="sec03">
    <div class="inner">
      <div class="brochureSec">
        <h3>Promotion</h3>
        <div class="brContents">
          <!-- <p><img src="/eng/img/main/sec03_img1.png"></p> -->
          <div class="name_box">
            <h3>2022 BMC Brochure</h3>
          </div>
          </p>
        </div>
        <div class="more"><a href="/eng/contents.do?mId=0204000000"></a></div>
      </div>
      <div class="esgSec">
        <h3>ESG Management</h3>
        <div class="esgContents">
          <p>BMC realizes a better tomorrow by realizing ESG management.<br/><span class="yll">We strive to last, be loved, and become the main public enterprise of the region.</span> </p>
          <ul class="esg_list">
            <li>
              <div class="esgImg_box">
                <a href="/eng/contents.do?mId=0401000000"><img src="/eng/img/main/sec03_img2.png" alt="go to Environmental Management"></a>
              </div>
              <span>Environmental<br>
              Management</span></li>
            <li>
              <div class="esgImg_box">
                <a href="/eng/contents.do?mId=0402000000"><img src="/eng/img/main/sec03_img3.png" alt="go to Win-Win management"></a>
              </div>
              <span>Win-Win<br>
              Management</span></li>
            <li>
              <div class="esgImg_box">
                <a href="/eng/contents.do?mId=0403000000"><img src="/eng/img/main/sec03_img4.png" alt="go to Open Management"></a>
              </div>
              <span>Open<br>
              Management</span></li>
          </ul>
        </div>
        <div class="more"><a href="/eng/contents.do?mId=0400000000"></a></div>
      </div>
    </div>
  </div>
  <!-- //container --> 


