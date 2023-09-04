<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/eng/jsp/common/taglib.jsp"%>

<!-- header -->
<header id="header"> 
  <!-- headWrap -->
  <div class="headWrap">
  	<div class="inner">
      <div class="logo">
        <h1> <a href="/eng/main.do" title="BMC ENG Main"><img src="/eng/img/common/logo.png" alt="BMC Busan Metropolitan Corporation" /><span class="hide-txt">BMC ENG Main</span></a></h1>
      </div>
      <div class="sideBtn">
        <div class="dropdown">
          <button class="dropbtn">LANGUAGE
            <i class="fa fa-caret-down"></i>
          </button>
          <div class="dropdown-content">
            <li><a href="/main.do" target="_blank">KOR</a></li>
              <li><a href="/eng/main.do" target="_blank">ENG</a></li>
          </div>
        </div>      
        <button class="sideBtn_box" type="button"> <span class="blind">menu open</span>
          <div class="line"></div>
          <div class="line"></div>
          <div class="line"></div>
          <div class="line"></div>
        </button>
        <!--sidemenu -->
        <div class="side-bar">
          <div class="side-bar-top">
            <div class="inner">
              <div class="side-logo"></div>
              <button class="side-close-btn"><span class="blind">close</span></button>
            </div>
          </div>
          <div class="side-menu">			
			<c:set var="p_elements" value="${menus}" scope="request" />
			<c:set var="p_curMid"   value="${curMid}" scope="request" />
			<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
			<c:set var="p_maxDepth" value="${3}" scope="request" />
			<c:set var="p_depth"    value="${1}" scope="request" />
			<c:set var="isSide"    value="Y" scope="request" />
			<jsp:include page="/eng/jsp/layouts/component/headerBody.jsp" />			
          </div>          
        </div>
        <!--//sidemenu --> 
      </div>        		
  	</div>
  </div>
  <!-- //headWrap -->
  <!-- gnb -->
  <div class="gnb">
	<c:set var="p_elements" value="${menus}" scope="request" />
	<c:set var="p_curMid"   value="${curMid}" scope="request" />
	<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
	<c:set var="p_maxDepth" value="${3}" scope="request" />
	<c:set var="p_depth"    value="${1}" scope="request" />
	<c:set var="isSide"    value="N" scope="request" />  
  	<jsp:include page="/eng/jsp/layouts/component/headerBody.jsp" />
  </div>
  <!-- //gnb -->
</header>
<!-- //header -->