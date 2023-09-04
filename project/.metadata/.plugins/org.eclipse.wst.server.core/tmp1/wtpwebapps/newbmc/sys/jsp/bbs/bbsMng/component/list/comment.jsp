<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 게시글 댓글 관리 페이지
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.03.15		J.Ryeon Lee		최초 생성 및 기능 코딩
	 * 2016.03.18		이경민			디자인 퍼블리싱
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.03.15
	 */
%>
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[

	/** 에러코드 알럿창 출력 함수 */
	function convertErrCodeToMsg(errCode) {
		switch (errCode) {
			case 0 : alert("필수 정보가 누락되었습니다."); break;
			case 1 : alert("사용자의 코멘트는 삭제/복구만이 가능합니다."); break;
			default : alert("서버와 통신 중 에러가 발생했습니다."); break;
		}
	}

	/** 코멘트 페이징 */
	function goCommentPage(page) {
		var frm = document.getElementById("viewForm");
		frm.commentPage.value = page;
		frm.submit();
	}

	/** 코멘트 등록 */
	function fn_insert_comment() {
		if (validator()) {
			jQuery.ajax({
				type : "post",
				url : "/sys/${siteCode}/bbs/bbsMng/comment/writeProc.do",
				data : { "ptIdx" : "${bbsConfigVO.ptIdx}", "bIdx" : "${bbsMngView.bIdx}", "cContent" : jQuery("#cContent").val() },
				success : function (data) {
					var result = jQuery.parseJSON(data);
					if (result.success) {
						alert("코멘트를 등록했습니다.");
						location.href = "/sys/${siteCode}/bbs/bbsMng/view.do?bIdx=${bbsMngView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}";
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
				url : "/sys/${siteCode}/bbs/bbsMng/comment/modifyProc.do",
				data : { "ptIdx" : "${bbsConfigVO.ptIdx}", "bIdx" : "${bbsMngView.bIdx}", "idx" : idx, "cContent" : jQuery(selector).val() },
				success : function (data) {
					var result = jQuery.parseJSON(data);
					if (result.success) {
						alert("코멘트를 수정했습니다.");
						location.href = "/sys/${siteCode}/bbs/bbsMng/view.do?bIdx=${bbsMngView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}";
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

	/** 코멘트 삭제 & 복구 */
	function fn_switch_comment_state(idx, isDel) {
		var msg = isDel == 'Y' ? "삭제" : "복구";
		if (confirm("코멘트를 " + msg + "합니다.")) {
			jQuery.ajax({
				type : "post",
				url : "/sys/${siteCode}/bbs/bbsMng/comment/switchStateProc.do",
				data : { "ptIdx" : "${bbsConfigVO.ptIdx}", "bIdx" : "${bbsMngView.bIdx}", "idx" : idx, "isDel" : isDel },
				success : function (data) {
					var result = jQuery.parseJSON(data);
					if (result.success) {
						alert("코멘트를 " + msg + "했습니다.");
						location.href = "/sys/${siteCode}/bbs/bbsMng/view.do?bIdx=${bbsMngView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}";
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

<div class="commentBox">

	<div class="mT10 btn_boxR">
		<textarea id="cContent" name="cContent" rows="3" class="input99 mB10" maxlength="250" data-validator="on" data-required="y" data-fieldName="코멘트 내용"></textarea>
		<a href="#" onclick="fn_insert_comment(); return false;" class="btn_blue">등록</a>
	</div>

	<c:if test="${not empty commentList}">
		<div class="btn_boxR commentAll"">코멘트 전체 <span class="blue">${commentCnt}</span>건</div>
	</c:if>

	<ul>
		<c:forEach var="element" items="${commentList}">
			<li>
				<p id="modify${element.idx}">${fn:escapeXml(element.cContent)}</p>
				<span class="blue">
					${element.cWriter}</span><span class="date">
					<fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd HH:mm"/>
					<c:if test="${element.isDel == 'Y'}">[삭제]</c:if>
				</span>

				<div class="commentBt">
					<c:choose>
						<c:when test="${element.isDel == 'Y'}">
							<a href="#" onclick="fn_switch_comment_state('${element.idx}', 'N'); return false;">복구</a>
						</c:when>
						<c:otherwise>
							<c:if test="${empty element.privatecode}"><a href="#" onclick="fn_show_modify_box('${element.idx}'); return false;">수정</a></c:if>
							<a href="#" onclick="fn_switch_comment_state('${element.idx}', 'Y'); return false;">삭제</a>
						</c:otherwise>
					</c:choose>
				</div>
			</li>
		</c:forEach>
		<c:if test="${empty commentList}">
			<li>등록된 코멘트가 없습니다.</li>
		</c:if>
	</ul>

	<%-- 코멘트 페이징 --%>
	<c:if test="${not empty commentList}">
		<div class="box_page">
			<input type="hidden" name="commentPage"/>
			<ui:pagination paginationInfo="${commentPaginationInfo}" type="sys" jsFunction="goCommentPage"/>
		</div>
	</c:if>
</div>
