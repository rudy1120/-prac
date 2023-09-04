<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 양산 대표 포탈 상단 타이틀 및 localhistroy영역
	 *
	 * 수정일				수정자				수정내용
	 * ------------	------------	-----------------------------
	 * 2014.10.07		엄동건				최초 생성
	 * 2015.08.26		J.Ryeon Lee		common.jsp 의존성 제거
	 * 2017.06.16                김선영                             마크업 수정
	 *
	 *
	 * @author 엄동건
	 * @since 2014.10.07
	 */
%>

<header id="titWrap">
	<c:set var="headTitle" value="${fn:split(activeMenu.subHeadTitle, '>')}" />
	<h3>${activeMenu.menuName}</h3>
	<div id="spotWrap" class="clFix">
		<ul class="spot_list">
			<c:forEach var="element" items="${headTitle}" end="2">
				<c:if test="${not empty element}">
					<li><a href="#">${element}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		<ul class="sub_acc">
			<li><a href="#" onclick="window.open('${pageContext.request.contextPath}/common/printPage.jsp', 'printPopup', 'width=730,height=600,top=100,left=100,scrollbars=yes,resizable=yes');" title="새창 열림" class="btn_print"><span class="blind">본문 인쇄</span></a></li>
			<li>
				<a href="#" title="새창 열림" class="btn_share"><span class="blind">sns 공유 리스트 열기</span></a>
				<div class="share_list">
					<div>
						<ul class="clFix">
							<li><button title="트위터 새창열림" id="snsTwitter" class="btn_twitter"><span class="blind">트위터</span></button></li>
							<li><button title="페이스북 새창열림" id="snsFacebook" class="btn_facebook"><span class="blind">페이스북</span></button></li>
							<li><button title="네이버블로그 새창열림" class="btn_blog"><span class="blind">네이버블로그</span></button></li>
							<li><button title="카카오스토리 새창열림" class="btn_kakaostory"><span class="blind">카카오스토리</span></button></li>
							<li><button title="카카오톡 새창열림" class="btn_kakaotalk"><span class="blind">카카오톡</span></button></li>
							<li class="qrcode">
								<button onclick="if($('#contentsQrcode').css('display') == 'none'){$('#contentsQrcode').show();}else{$('#contentsQrcode').hide();}" title="QRCode 보기" class="btn_qrcode"><span class="blind">QRCode</span></button>	
							</li>
						</ul>
						<button type="button" class="close"><span class="blind">sns 공유 리스트 닫기</span></button>
					</div>
				</div>
				<div id="contentsQrcode">
				</div>
			</li>
		<!-- 	<li><a href="#" title="새창 열림" id="snsTwitter">트위터</a></li>
			<li><a href="#" title="새창 열림" id="snsFacebook">페이스북</a></li> -->
	
			<c:if test="${yh:getProperty('Globals.pageQrcode') eq 'Y' }">
			<c:set var="req" value="${pageContext.request}" />
			<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}" />
			<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>
			<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
			<c:set var="pageUrl" value="${baseURL}${requestPath}"/>
			<c:if test="${!empty params}"><c:set var="pageUrl" value="${baseURL}${requestPath}?${params}"/></c:if>
	
	
			<!-- <li class="qrcode">
				<a href="#" onclick="if($('#contentsQrcode').css('display') == 'none'){$('#contentsQrcode').show();}else{$('#contentsQrcode').hide();}" id="snsFacebook">QRCode</a>
				<div id="contentsQrcode">
				</div>
			</li> -->
	
			<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.qrcode.min.js"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					$('#contentsQrcode').qrcode("${pageUrl}");
					$('#contentsQrcode').hide();
				});
			</script>
			</c:if>
		</ul>
	</div>
</header>