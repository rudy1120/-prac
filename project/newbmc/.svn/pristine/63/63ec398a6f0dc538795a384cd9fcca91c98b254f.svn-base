<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 메뉴 코멘트 페이지
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.06.17		J.Ryeon Lee		최초 생성 및 기능 코딩
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.06.17
	 */
%>
<security:authentication var="user" property="principal"/>
<c:if test="${user == 'anonymousUser'}"><c:set var="user" value="${null}"/></c:if>
<%-- 디자인팀 TODO: 기본 프로필 사진 추가 --%>
<c:choose>
	<c:when test="${not empty user}">
		<c:set var="logined" value="${true}"/>
		<c:set var="userType" value="${user.userType}"/>
	</c:when>
	<c:when test="${userType == 'F'}">
		<c:set var="logined" value="${true}"/>
		<c:set var="profile_img" value="http://graph.facebook.com/${fbUserId}/picture"/>
	</c:when>
	<c:when test="${userType == 'T'}">
		<c:set var="logined" value="${true}"/>
		<c:set var="profile_img" value="${twProfileImgUrl}"/>
	</c:when>
	<c:otherwise>
		<c:set var="logined" value="${false}"/>
		<c:set var="profile_img" value="https://abs.twimg.com/sticky/default_profile_images/default_profile_0_bigger.png"/>
	</c:otherwise>
</c:choose>

<div id="fb-root"></div>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[

	window.fbAsyncInit = function() {
		FB.init({
			appId  : "${yh:getProperty('facebook.appId')}", // 앱 ID
			status : true,              // 로그인 상태를 확인
			cookie : true,              // 쿠키허용
			xfbml  : true,              // parse XFBML
			version : "v2.6"
		});

		FB.Event.subscribe('auth.login', function(response) {
			if (response.status === "connected") {
				saveFbUserInfo(response);
			}
		});
	};

	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {return;}
		js = d.createElement(s); js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	function fb_login() {
		FB.getLoginStatus(function(response) {
			if (response.status === "connected") {
				saveFbUserInfo(response);
			} else if (response.status === "not_authorized") {
				FB.login();
			} else {
				FB.login();
			}
		});
	}

	function saveFbUserInfo(response) {
		jQuery.ajax({
			type : "post",
			url : "/fb/login.do",
			data : { "accessToken" : response.authResponse.accessToken },
			success : function(data) {
				location.href = "/${siteCodeFull}/contents.do?mId=${menuVO.mId}";
			}
		});
	}

	/** 에러코드 알럿창 출력 함수 */
	function errCodeToMsg(errCode) {
		switch (errCode) {
			case 0 : alert("로그인 정보가 없습니다."); break;
			case 1 : alert("존재하지 않는 게시글입니다."); break;
			case 2 : alert("본인이 작성한 코멘트만 수정/삭제하실 수 있습니다."); break;
			case 3 : alert("잘못된 접근입니다."); break;
			default : alert("서버와 통신 중 에러가 발생했습니다."); break;
		}
	}

	/** 인증 페이지로 이동 */
	function go_mc_auth_page() {
		location.href = "/${siteCodeFull}/bbs/inRealName.do" +
			"?mId=${menuVO.mId}" +
			"&successUrl=" + encodeURIComponent("/${siteCodeFull}/contents.do?mId=${menuVO.mId}");
	}

	/** 코멘트 등록 */
	function fn_insert_mc(upIdx, cContentSelector) {
		if (validator()) {
			jQuery.ajax({
				type : "post",
				url : "/${siteCodeFull}/menu/comment/writeProc.do?mId=${menuVO.mId}",
				data : { "upIdx" : upIdx, "cContent" : jQuery(cContentSelector).val() },
				success : function (data) {
					var result = jQuery.parseJSON(data);
					if (result.success) {
						alert("코멘트를 등록했습니다.");
						location.href = "/${siteCodeFull}/contents.do?mId=${menuVO.mId}&menuCommentPage=${menuCommentPaginationInfo.currentPageNo}";
						return false;
					}

					errCodeToMsg(result.errCode);
				}
			}).fail(function() {
				alert("서버와 통신 중 에러가 발생했습니다.");
			});
		}
	}

	/** 코멘트 수정 박스 출력 */
	function fn_show_mc_modify_box(idx) {
		if (jQuery("#modBox" + idx).length == 0) {
			var selector = "#modify" + idx;
			var text = jQuery(selector).text();
			jQuery(selector).text("");
			jQuery(selector).append("<textarea id='modBox" + idx + "' rows='2' class='input99' maxlength='250' data-required='y' data-fieldName='코멘트 내용'>" + text.trim() + "</textarea>");
			jQuery(selector).parent().children().find("a").first().text("저장");
		} else {
			fn_update_comment(idx);
		}
	}

	/** 코멘트 수정 */
	function fn_update_comment(idx) {
		var selector = "#modBox" + idx;
		jQuery("#cContent").removeAttr("data-validator");
		jQuery(selector).attr("data-validator", "on");
		if (validator()) {
			jQuery.ajax({
				type : "post",
				url : "/${siteCodeFull}/menu/comment/modifyProc.do?mId=${menuVO.mId}",
				data : { "idx" : idx, "cContent" : jQuery(selector).val() },
				success : function (data) {
					var result = jQuery.parseJSON(data);
					if (result.success) {
						alert("코멘트를 수정했습니다.");
						location.href = "/${siteCodeFull}/contents.do?mId=${menuVO.mId}&menuCommentPage=${menuCommentPaginationInfo.currentPageNo}";
						return false;
					}

					errCodeToMsg(result.errCode);
				}
			}).fail(function() {
				alert("서버와 통신 중 에러가 발생했습니다.");
			});
		}

		jQuery("#cContent").attr("data-validator", "on"); // validator toggle
		jQuery(selector).removeAttr("data-validator");
	}

	/** 답글 등록 박스 출력 */
	function fn_show_reply_box(upIdx) {
		if ("${userType}") {
			if (jQuery("#rpBox" + upIdx).length == 0) {
				var selector = "#modify" + upIdx;
				jQuery(selector).parent().append("<textarea id='rpBox" + upIdx + "' rows='2' class='input99' maxlength='250' data-required='y' data-fieldName='답글 내용'></textarea>");
			} else {
				jQuery("#cContent").removeAttr("data-validator");
				fn_insert_mc(upIdx, "#rpBox" + upIdx);
				jQuery("#cContent").attr("data-validator", "on");
			}
		} else {
			alert("로그인 후 이용하실 수 있습니다.");
		}
	}

	/** 코멘트 페이징 */
	function go_mc_page(page) {
		location.href = "/${siteCodeFull}/contents.do?mId=${menuVO.mId}&menuCommentPage=" + page;
	}

	/** 코멘트 삭제 */
	function fn_delete_mc(idx) {
		if (confirm("코멘트를 정말 삭제하시겠습니까? 삭제된 코멘트는 복구되지 않습니다.")) {
			jQuery.ajax({
				type : "post",
				url : "/${siteCodeFull}/menu/comment/deleteProc.do?mId=${menuVO.mId}",
				data : { "idx" : idx },
				success : function (data) {
					var result = jQuery.parseJSON(data);
					if (result.success) {
						alert("코멘트를 삭제했습니다.");
						location.href = "/${siteCodeFull}/contents.do?mId=${menuVO.mId}";
						return false;
					}

					errCodeToMsg(result.errCode);
				}
			}).fail(function() {
				alert("서버와 통신 중 에러가 발생했습니다.");
			});
		}
	}

//]]>
</script>
<div class="commentBox">
	<c:if test="${!logined}">
<%-- 		<a href="https://graph.facebook.com/oauth/authorize?client_id=580346595471881&redirect_uri=http://yhdev.iptime.org:8888/fb/callback.do?backUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}&scope=user_about_me" class="btn_blue" target="_blank">FB</a> --%>
		<a id="fb_login" href="#" onclick="fb_login(); return false;" class="btn_blue">FB</a>
		<a id="tw_login" href="/tw/login.do?backUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" class="btn_blue">TW</a>
		<a id="member_login" href="#" onclick="go_mc_auth_page(); return false;" class="btn_blue">본인인증/로그인</a>
	</c:if>
	<c:if test="${logined}">
		<c:choose>
			<c:when test="${userType == 'F'}"><a id="fb" href="/fb/logout.do?backUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" class="btn_blue">FB Logout</a></c:when>
			<c:when test="${userType == 'T'}"><a id="fb" href="/tw/logout.do?backUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" class="btn_blue">TW Logout</a></c:when>
			<c:when test="${not empty user}"><a id="fb" href="/logout?successUrl=/${siteCodeFull}/contents.do?mId=${menuVO.mId}" class="btn_blue">Logout</a></c:when>
			<c:otherwise>?</c:otherwise>
		</c:choose>
	</c:if>

	<div class="mT10 btnboxR">
		<img id="profile_img" src="${profile_img}" alt="profile image"/>
		<textarea id="cContent" name="cContent" title="코멘트 입력" rows="3" class="input99 mB10" maxlength="250" data-validator="on" data-required="y" data-fieldName="코멘트 내용" <c:if test="${!logined}">disabled="disabled"</c:if>></textarea>
		<c:if test="${logined}"><a href="#" onclick="fn_insert_mc('', '#cContent'); return false;" class="btn_blue">등록</a></c:if>
	</div>

	<c:if test="${not empty menuCommentList}">
		<div class="btnboxR commentAll">코멘트 전체 <span class="blue">${menuCommentCnt}</span>건</div>
	</c:if>

	<ul>
		<c:forEach var="element" items="${menuCommentList}">
			<li>
				<p id="modify${element.idx}">
					${element.isDel == 'Y' ? '삭제된 댓글입니다.' : fn:escapeXml(element.cContent)}
				</p>
				<span class="blue">${element.userName}</span><span class="date"><fmt:formatDate value="${element.createDate}" pattern="yyyy.MM.dd HH:mm"/></span>
				<div id="btnBox${element.idx}" class="commentBt">
					<c:if test="${logined && userType == element.userType && element.isDel == 'N' && (
						(userType == 'F' && fbUserId == element.userId) ||
						(userType == 'T' && twUserId == element.userId) ||
						(userType == 'M' && user.privatecode == element.privatecode)
					)}">
						<a href="#" onclick="fn_show_mc_modify_box('${element.idx}'); return false;">수정</a>
						<a href="#" onclick="fn_delete_mc('${element.idx}'); return false;">삭제</a>
					</c:if>
					<%-- <a href="#" onclick="fn_show_reply_box('${element.idx}'); return false;">댓글</a> --%>
				</div>
			</li>
		</c:forEach>
	</ul>

	<%-- 코멘트 페이징 --%>
	<c:if test="${not empty menuCommentList}">
		<div class="box_page">
			<input type="hidden" name="menuCommentPage" value="${menuCommentPaginationInfo.currentPageNo}"/>
			<ui:pagination paginationInfo="${menuCommentPaginationInfo}" type="base" jsFunction="go_mc_page"/>
		</div>
	</c:if>


</div>
