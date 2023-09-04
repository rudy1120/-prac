<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 고객경영참여 상세보기
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.08.30		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.08.30
 */
%>

<script>
/*  if (document.location.protocol == 'http:') {
    document.location.href = document.location.href.replace('http:', 'https:');
}  */
</script>

<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
	<security:authentication var="user" property="principal"/>
</security:authorize>
<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if> 

<style>
	.cont_top{width:100%; overflow:hidden; margin:30px 0 0;}
	.img_wrap{width:33.33%; float:left; position:relative;}
	.txt_wrap{width:66.66%; float:left; padding-left:20px;}
	.txt_wrap:after{content:''; display:block; clear:both;}
	.txt_wrap li{float:none;}
	ul.bul li{margin-bottom: 10px;padding-left: 15px;background: url(../images/content/b_gray.png) no-repeat left 6px;}
	.txt_wrap li{margin-bottom:10px; padding-left:15px;}
	.label {display: inline-block;position: absolute;top: 0px;left: 0px;padding: 1.2rem 1.5rem;background: #00879e; color: #fff;}
	.label-end{background:#333;}
	.mT10{padding:30px 0; border-top:1px solid #ddd;}
	.mT10_1{ padding:30px 0; }
	.btntolist{display:block; margin:30px auto;}
	.info_span{display:inline-block; width:80px;}
	.view_cont2{margin-top:30px;border-bottom: 1px solid #ddd;}
	.amendsUl li{float:none; color:#333;}
	.r01 li{float:none; color:#333;}
	.r02 li{float:none; color:#333;}
	.txtBox3 li {float:none; color:#333;}
	
	@media all and (max-width:1199px){
		.bod_view{margin:30px;}
	}
	@media all and (max-width:960px){
		.bod_view{margin:30px;}
		.img_wrap{width:100%;}
		.txt_wrap{width:100%; margin-top:20px; padding-left:0;}
		.intro-txt ul.r01 > li:before {left:-9999px;}
	}
	@media all and (max-width:640px){
		.btn_wrap > a {margin-top:10px;}
		.intro-txt ul.r01 > li:before {left:-9999px;}
	}
</style>

<jsp:useBean id="currTime" class="java.util.Date" />
<fmt:formatDate var="currTime" value="${currTime}" pattern="yyyy-MM-dd HH:mm:ss" />
<c:set var="deadline" value="${currTime < result.edate ? 1 : 2}" />
<c:set var="deadline" value="${currTime < result.sdate ? 3 : deadline}" />
<div id="contents">
	<div class="content">
		<div class="gap40"></div>
		<form:form commandName="receiptContVO" id="list" name="list" methodParam="post">
			<form:hidden path="page"/>
			<h4 class="blind">채용응시원서 접수 상세 보기</h4>
			<div class="bod_wrap">
				<div class="bod_view">
					<h4>${result.title}</h4>
					<div class="view_info">
						<ul class="clFix">
							<li class="view_date"><span>등록일</span> : ${result.createDate.split(' ')[0]}</li>
							<li class="view_hit"><span>조회</span> : ${result.cnt}</li>
						</ul>
						
					</div>
					<div class="view_cont2">
						<div class="mT10_1">
						<c:set var="content" value="${fn:replace(result.cont, '  ', '&nbsp;&nbsp;')}"/>
							${content}
						</div>
					</div>
				</div>	
			</div>
			<dl class="view_file clFix">
				<dt><span>첨부 파일</span></dt>
				<dd>
					<c:set var="hasFile" value="${not empty result.file_cnt && result.file_cnt != '0'}"/>
					<c:if test="${hasFile}">
						<span style="font-size: 17px;">※미리보기 시 실제문서와 형식상 차이가 있을수 있습니다.</span>
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${result.thumbnail}"/>
							<c:param name="convertYn" value="Y"/>
						</c:import>
					</c:if>
					<c:if test="${!hasFile}">파일이 없습니다.</c:if>
				</dd>
			</dl>
			<div class="btn_wrap">
				<c:if test="${deadline eq 1}">
					<c:choose>
						<c:when test="${not empty user}">
							<!-- 본인인증 정보존재 -->
							<a href="#" data-action="/bmc/receipt/register.do?idx=${result.idx}" onclick="req.post(this); return false;">접수하기</a>
						</c:when>
						<c:otherwise>
							<!-- 본인인증 정보없음 -->
							<a href="#" data-action="/bmc/user/inRealName.do?successUrl=/bmc/receipt/register.do&idx=${result.idx}" onclick="req.post(this); return false;">접수하기</a>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:choose>
					<c:when test="${not empty user}">
						<!-- 본인인증 정보존재 -->
						<a href="/bmc/receipt/read.do?idx=${result.idx}&mId=${menuVO.mId}">조회</a>
					</c:when>
					<c:otherwise>
						<!-- 본인인증 정보없음 -->
						<a href="/bmc/user/inRealName.do?idx=${result.idx}&mId=${menuVO.mId}&successUrl=/bmc/receipt/read.do">조회</a>
					</c:otherwise>
				</c:choose>
				<a href="#" data-action="/bmc/receipt/list.do?mId=${menuVO.mId}" onclick="req.post(this); return false;"  title="${activeMenu.menuName} 게시글 목록 페이지로 이동">목록</a>
			</div>
		</form:form>

	</div>
</div>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
