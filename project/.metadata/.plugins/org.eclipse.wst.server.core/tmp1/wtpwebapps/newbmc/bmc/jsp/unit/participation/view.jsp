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
//  if (document.location.protocol == 'http:') {
//     document.location.href = document.location.href.replace('http:', 'https:');
// } 
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
<fmt:formatDate var="currTime" value="${currTime}" pattern="yyyy-MM-dd" />
<c:set var="deadline" value="${currTime < result.edate ? 1 : 2}" />
<c:set var="deadline" value="${currTime < result.sdate ? 3 : deadline}" />
<div id="contents">
	<div class="content">
		<div class="gap40"></div>
		<form:form commandName="partContVO" id="list" name="list" methodParam="post">
			<form:hidden path="page"/>
			<h4 class="blind">고객경영참여 상세 보기</h4>
			<div class="bod_wrap">
				<div class="bod_view">
					<h4>${result.title}</h4>
					<div class="view_info">
						<ul class="clFix">
							<li class="view_date"><span>등록일</span> : ${result.createDate}</li>
							<li class="view_hit"><span>조회</span> : ${result.cnt}</li>
						</ul>
					</div>
				<c:if test="${result.gbn ne 'C'}">
					<div class="cont_top">
						<%-- <div class="img_wrap">
							<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${result.thumbnail}"/>
								<c:param name="mode" value="ratio"/>
								<c:param name="width" value="250"/>
								<c:param name="height" value="220"/>
								<c:param name="insertBr" value="${true}"/>
							</c:import>
							<span class="label">
								<c:choose>
									<c:when test="${deadline eq 2}">
										마감
									</c:when>
									<c:when test="${deadline eq 3}">
										접수예정
									</c:when>
									<c:otherwise>
										접수중
									</c:otherwise>
								</c:choose>
							</span>
						</div> --%>
						
						<div class="txt_wrap">
							<h3>${result.title}</h3><div class="gap"></div>
							<ul class="bul">
							<c:choose>
								<c:when test="${result.gbn == 'A'}">
									<li><span class="info_span">공모기간</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc1}</li>
									<li><span class="info_span">참가자격</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc2}</li>
									<li><span class="info_span">제안내용</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc3}</li>
									<li><span class="info_span">제출방법</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc4}</li>
									<li><span class="info_span">포상금</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc5}</li>
								</c:when>
								<c:when test="${result.gbn == 'B'}">
									<li><span class="info_span">대상사업</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc1}</li>
									<li><span class="info_span">사업규모</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc2}</li>
									<li><span class="info_span">접수기간</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc3}</li>
									<li><span class="info_span">제출방법</span> :&nbsp;&nbsp;&nbsp;&nbsp;${result.etc4}</li>
								</c:when>
							</c:choose>
								</ul>
							<div class="btn_wrap btn_wrap2">
								<c:if test="${deadline eq 1}">
									<c:choose>
										<c:when test="${not empty user}">
											<!-- 본인인증 정보존재 -->
											<a href="#" data-action="/bmc/participation/register.do?idx=${result.idx}" onclick="req.post(this); return false;">참가하기</a>
										</c:when>
										<c:otherwise>
											<!-- 본인인증 정보없음 -->
											<a href="#" data-action="/bmc/user/inRealName.do?successUrl=/bmc/participation/register.do&idx=${result.idx}" onclick="req.post(this); return false;">참가하기</a>
										</c:otherwise>
									</c:choose>
									&nbsp;&nbsp;
								</c:if>
								
								
								<c:choose>
									<c:when test="${result.gbn == 'A'}">
										<a href="/FileDown_direct.do?file=homepage1.hwp">
											신청서 다운로드
										</a>&nbsp;&nbsp;
									</c:when>
									<c:when test="${result.gbn == 'B'}">
										<c:set var="fileName" value="${result.idx == '66' ? 'Jumin01.hwp' : result.idx == '68' ? 'Jumin2022.hwp' : '' }" />
										<a href="/FileDown_direct.do?file=${fileName}">
											신청서 다운로드
										</a>&nbsp;&nbsp;
									</c:when>
								</c:choose>
								<c:if test="${result.gbn == 'A'}">	
									<a href="/FileDown_direct.do?file=2021_guideline.pdf">
										공공기관 혁신 가이드 라인
									</a>
								</c:if>
								<div class="gap"></div>
								<p style="color:red;">&#8251; 아래 세부사항을 반드시 확인해 주시기 바라며, 신청서 작성 후 참가 신청을 부탁드립니다.</p>
							</div>
						</div>
					</div>
				</c:if>
					<div class="view_cont2">
						<div class="mT10">
						<c:set var="content" value="${fn:replace(result.cont, '  ', '&nbsp;&nbsp;')}"/>
							${content}
						</div>
					</div>
				</div>	
			</div>
			<div class="btn_wrap">
				<a href="#" data-action="/bmc/participation/list.do?mId=${menuVO.mId}" onclick="req.post(this); return false;"  title="${activeMenu.menuName} 게시글 목록 페이지로 이동">목록</a>
			</div>
		</form:form>

		<%-- <button onclick="location.href='/bmc/participation/list.do?mId=${menuVO.mId}'" class="btnmove btntolist">목록</button> --%>
	</div>
</div>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
