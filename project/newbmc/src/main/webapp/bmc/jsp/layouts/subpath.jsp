<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<div class="path-wrap">
	<div class="path-box">
		<div class="path">
			<a class="path-home" href="/"><span class="home">처음으로</span></a>
			<div class="path-depth-wrap">
				<ul>
					<c:set var="p_elements" value="${menus}" scope="request" />
					<c:set var="p_curMid" value="${curMid}" scope="request" />
					<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
					<c:set var="p_maxDepth" value="${3}" scope="request" />
					<c:set var="p_depth" value="${1}" scope="request" />
					<jsp:include page="/bmc/jsp/layouts/component/subpathBody.jsp" />
				</ul>
				<p class="print-pos">
					<!-- <a class="path-facebook" href="#"><span class="facebook">페이스북</span></a>
					<a class="path-twitter" href="#"><span class="twitter">트위터</span></a>
					<a class="path-blog" href="#"><span class="blog">블로그</span></a>-->
					<a class="path-print" id="print" href="#" title="프린트 하기"><span class="print">프린트</span></a>
				</p>
			</div>
		</div>
	</div>
</div>