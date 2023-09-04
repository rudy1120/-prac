<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<script  type="text/javascript" >
    function checkCmsLogin() {
        jQuery.ajax({
            type : "POST",
            url: "${pageContext.request.contextPath}/cms/AdminLogin/loginDo?callback=?",
            data : {user_id: "${adminSession.id}", user_pw: "", user_name: "${adminSession.name}", admin_level_code: "10"},
            contentType: "text/plain; charset=utf-8",
            jsonp : "callback",
            dataType : "json",
            withCredentials: true,
            success : function(j) {
                if(j.flag == "success"){
                    document.location.href = "${pageContext.request.contextPath}/cms/Site/select";
                }else{
                    alert(j.message);
                }
            }
        });
    }

    function logout() {
        $.ajax({
        	url : "${pageContext.request.contextPath}/sys/logout.do",
            //jsonp : "callback",
            //dataType: 'jsonp',
            withCredentials: true,
            success : function(result) {
				<c:if test="${ADMIN_LOGIN_INFO.adminAccessLevelCode == 6}">
					location.href = "${pageContext.request.contextPath}/sys/${ADMIN_LOGIN_INFO.adminType.mngSiteCode}/login.do";
				</c:if>
				<c:if test="${ADMIN_LOGIN_INFO.adminAccessLevelCode != 6}">
					location.href = "${pageContext.request.contextPath}/sys/login.do";
				</c:if>

                /*
                if(result.flag == "success") {
                    parent.document.location.replace("${pageContext.request.contextPath}/sys/logout.do");
                } else {
                    parent.document.location.replace("${pageContext.request.contextPath}/sys/logout.do");
                }
                */
            }
        });
    }

</script>
<div class="header">
	<h1>Website Management</h1>
	<div class="gnb">
		<ul>
			<li>${ADMIN_LOGIN_INFO.name} (${ADMIN_LOGIN_INFO.adminId })님</li>
			<li><a href="#" onclick="logout(); return false;">로그아웃</a></li>
			<%-- 외부 관리자에게는 이하의 기능을 제공하지 않음 --%>
			<c:if test="${ADMIN_LOGIN_INFO.adminAccessLevelCode != 6}">
				<li><a href="#" onclick="parent.document.location.replace('${pageContext.request.contextPath}/sys/intro.do');">통합메인</a></li>
				<li><a href="${yh:getProperty('ySmartCMS.url')}/linkage?key=${sessionScope.cmsLoginKey}" target="_blank">CMS</a></li>
<%-- 				<li><a href="${empty hmsLoginKey ? 'http://hms.yhdatabase.com/' : 'http://hms.yhdatabase.com/linkage.do?key='}${hmsLoginKey}" target="_blank">HMS (유지보수요청)</a></li> --%>
			</c:if>
		</ul>
	</div>

	<%-- 메뉴시작 li,a 태그에 선택되었을때 class="tabon" 추가 --%>
	<div class="lnb">
		<ul>
			<c:forEach var="depth1" items="${menuList}" varStatus="depth1Seq">
				<c:if test="${fn:substring(depth1.mId, 0, 2) == fn:substring(curMid, 0, 2)}">
					<c:choose>
						<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_hr'}"></c:when>
						<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_human'}"></c:when>
		
						<c:otherwise>
							<c:forEach var="depth2" items="${depth1.depth2MenuList}">
		
								<c:set var="flag_commonAuth" value="true"/>
		
								<c:if test="${fn:substring(depth2.mId, 0, 4) eq '0102' }">
									<c:if test="${sessionScope.ADMIN_LOGIN_INFO.adminAccessLevelCode!=10 && !(sessionScope.ADMIN_LOGIN_INFO.adminId eq 'chun0125')}">
										<c:set var="flag_commonAuth" value="false"/>
									</c:if>
								</c:if>
		
		
								<%-- 공통메뉴 권한-총괄 및 천혜진주무관님 --%>
								<%-- 20151229 J.Ryeon Lee 원뎁 메뉴 링크 수정 --%>
								<c:if test="${flag_commonAuth && not empty depth2.depth3MenuList}">
									<c:set var="_menu" value="${depth2.depth3MenuList[0]}" />
									<c:if test="${not empty depth2.depth3MenuList[0].depth4MenuList}">
										<c:set var="_menu" value="${depth2.depth3MenuList[0].depth4MenuList[0]}" />
									</c:if>
		
									<li>
										<c:choose>
											<c:when test="${fn:indexOf(_menu.programUrl,'?') >-1}">
												<c:set var="url_replace" value="${pageContext.request.contextPath}/sys/sysContents.do?mId=${_menu.mId}"/>
											</c:when>
											<c:otherwise>
												<c:set var="url_replace" value="${pageContext.request.contextPath}/sys/sysContents.do?mId=${_menu.mId}"/>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${empty depth2.programUrl}">
												<a href="javascript:alert('작업중입니다.');" <c:if test="${fn:substring(depth2.mId,0,4) eq fn:substring(curMid,0,4)}">class="on"</c:if>>${depth2.menuName}</a>
											</c:when>
											<c:otherwise>
												<a href="${url_replace}" <c:if test="${fn:substring(depth2.mId,0,4) eq fn:substring(curMid,0,4)}">class="on"</c:if>>${depth2.menuName}</a>
											</c:otherwise>
										</c:choose>
		
									</li>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
		</ul>
		<div class="cleB"></div>
	</div>
</div>
