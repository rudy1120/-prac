<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 게시글 댓글 페이지
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.03.08		J.Ryeon Lee		최초 생성 및 기능 코딩
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.03.08
	 */
%>
<security:authentication var="user" property="principal"/>
<c:if test="${user == 'anonymousUser'}"><c:set var="user" value="${null}"/></c:if>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[

	/** 에러코드 알럿창 출력 함수 */
	function convertErrCodeToMsg(errCode) {
		switch (errCode) {
			case 0 : alert("로그인 정보가 없습니다."); break;
			case 1 : alert("존재하지 않는 게시글입니다."); break;
			case 2 : alert("본인이 작성한 코멘트만 수정/삭제하실 수 있습니다."); break;
			default : alert("서버와 통신 중 에러가 발생했습니다."); break;
		}
	}

	/** 인증 페이지로 이동 */
	function go_auth_page() {
		location.href = "/${siteCodeFull}/bbs/inRealName.do" +
			"?mId=${menuVO.mId}" +
			"&successUrl=" + encodeURIComponent("/${siteCodeFull}/bbs/view.do?bIdx=${bbsView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}");
	}

	/** 코멘트 등록 */
	function fn_insert_comment() {
		if (validator()) {
			jQuery.ajax({
				type : "post",
				url : "/${siteCodeFull}/bbs/comment/writeProc.do",
				data : { "ptIdx" : "${bbsConfigVO.ptIdx}", "bIdx" : "${bbsView.bIdx}", "cContent" : jQuery("#cContent").val() },
				success : function (data) {
					var result = jQuery.parseJSON(data);
					if (result.success) {
						alert("코멘트를 등록했습니다.");
						location.href = "/${siteCodeFull}/bbs/view.do?bIdx=${bbsView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}";
						return false;
					}

					convertErrCodeToMsg(result.errCode);
				}
			}).fail(function() {
				alert("서버와 통신 중 에러가 발생했습니다.");
			});
		}
	}

	/** 코멘트 수정 박스 출력 */
	function fn_show_modify_box(idx) {
		if (jQuery("#modBox" + idx).length == 0) {
			var selector = "#modify" + idx;
			var text = jQuery(selector).text();
			jQuery(selector).text("");
			jQuery(selector).append("<textarea id='modBox" + idx + "' rows='2' class='input99' maxlength='250' data-required='y' data-fieldName='코멘트 내용'>" + text + "</textarea>");
			jQuery(selector).parent().children().find("a").first().text("저장")
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
				url : "/${siteCodeFull}/bbs/comment/modifyProc.do",
				data : { "ptIdx" : "${bbsConfigVO.ptIdx}", "bIdx" : "${bbsView.bIdx}", "idx" : idx, "cContent" : jQuery(selector).val() },
				success : function (data) {
					var result = jQuery.parseJSON(data);
					if (result.success) {
						alert("코멘트를 수정했습니다.");
						location.href = "/${siteCodeFull}/bbs/view.do?bIdx=${bbsView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}";
						return false;
					}

					convertErrCodeToMsg(result.errCode);
				}
			}).fail(function() {
				alert("서버와 통신 중 에러가 발생했습니다.");
			});
		}

		jQuery("#cContent").attr("data-validator", "on"); // validator toggle
		jQuery(selector).removeAttr("data-validator");
	}

	/** 코멘트 페이징 */
	function goCommentPage(page) {
		var frm = document.getElementById("commentForm");
		frm.commentPage.value = page;
		frm.action = "/${siteCodeFull}/bbs/view.do" +
			"?mId=${menuVO.mId}" +
			"&bIdx=${bbsView.bIdx}" +
			"&ptIdx=${bbsConfigVO.ptIdx}";
		frm.submit();
	}

//]]>
</script>
<security:authorize access="hasAnyRole('ROLE_USER', 'ROLE_USER_TMP', 'ROLE_USER_TMP_IPIN')">
	<script type="text/javascript">
	//<![CDATA[

		/** 코멘트 삭제 */
		function fn_delete_comment(idx) {
			if (confirm("코멘트를 정말 삭제하시겠습니까?")) {
				jQuery.ajax({
					type : "post",
					url : "/${siteCodeFull}/bbs/comment/deleteProc.do",
					data : { "ptIdx" : "${bbsConfigVO.ptIdx}", "bIdx" : "${bbsView.bIdx}", "idx" : idx },
					success : function (data) {
						var result = jQuery.parseJSON(data);
						if (result.success) {
							alert("코멘트를 삭제했습니다.");
							location.href = "/${siteCodeFull}/bbs/view.do?bIdx=${bbsView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}";
							return false;
						}

						convertErrCodeToMsg(result.errCode);
					}
				}).fail(function() {
					alert("서버와 통신 중 에러가 발생했습니다.");
				});
			}
		}

	//]]>
	</script>
</security:authorize>

<form:form commandName="bbsView" id="commentForm" action="/${siteCodeFull}/bbs/view.do?bIdx=${bbsView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}" methodParam="post">
	<div class="commentBox">

		<c:if test="${not empty commentList}">
			<div class="commentAll">코멘트 전체 <em>${commentCnt}</em>건</div>
		</c:if>

		<ul class="commentCon">
			<c:forEach var="element" items="${commentList}">
				<li class="clFix">
					<span class="name">${element.cWriter}</span>
					<span class="date"><fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd HH:mm"/></span>
					<p class="cont" id="modify${element.idx}">${fn:escapeXml(element.cContent)}</p>
					<%-- 본인의 코멘트에만 수정/삭제 버튼 출력 --%>
					<c:if test="${not empty user && user.privatecode == element.privatecode}">
						<div class="comment_btn_wrap fR">
							<a href="#" onclick="fn_show_modify_box('${element.idx}'); return false;">수정</a>
							<a href="#" onclick="fn_delete_comment('${element.idx}'); return false;" class="del">삭제</a>
						</div>
					</c:if>
				</li>
			</c:forEach>
			<c:if test="${empty commentList}">
				<li class="empty">등록된 코멘트가 없습니다.</li>
			</c:if>
		</ul>

		<%-- 코멘트 페이징 --%>
		<c:if test="${not empty commentList}">
			<div class="bod_page">
				<input type="hidden" name="commentPage"/>
				<ui:pagination paginationInfo="${commentPaginationInfo}" type="base" jsFunction="goCommentPage"/>
			</div>
		</c:if>

		<div class="commentFom clFix">
			<label for="cContent" class="blind ">코멘트 입력란</label>
			<textarea id="cContent" name="cContent" title="코멘트 입력" placeholder="인증후 코멘트를 작성할 수 있습니다." rows="3" maxlength="250" data-validator="on" data-required="y" data-fieldName="코멘트 내용" <c:if test="${empty user}">disabled="disabled"</c:if> style="ime-mode: active;"></textarea>
			<c:choose>
				<c:when test="${not empty user}">
					<p><a href="#" onclick="fn_insert_comment(); return false;" class="btn_comment">등록</a></p>
				</c:when>
				<c:otherwise>
					<p><a href="#" onclick="go_auth_page(); return false;" class="btn_comment">본인인증</a></p>
				</c:otherwise>
			</c:choose>
		</div>

	</div>
</form:form>
