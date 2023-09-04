<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%-- ##### 페이지 담당자 및 만족도 ##### --%>
<div class="pageInfo">
	<c:if test="${not empty chargeList}">
	<div class="dataOffer">
		<dl class="offer">
			<dt>페이지 담당자<dt>
			<dd>
				<c:forEach var="charge" items="${chargeList }">
					<ul>
						<li>
							<c:if test="${!empty charge.chargeDepNm}">${charge.chargeDepNm} | </c:if>
							<c:if test="${!empty charge.chargeFnm}">${charge.chargeFnm} | </c:if>
							<c:if test="${!empty charge.chargeTel}">${charge.chargeTel}</c:if>
						</li>
					</ul>
				</c:forEach>
			</dd>
		</dl>
		<c:if test="${menuVO.menuType == 'C'}">
		<dl>
			<dt>최종 수정일<dt>
			<dd>
				<c:choose>
					<c:when test="${not empty menuVO.cmsContentDate} }">
						<fmt:formatDate value="${menuVO.cmsContentDate}" pattern="yyyy-MM-dd" />
					</c:when>
					<c:otherwise>
						<fmt:formatDate value="${menuVO.modDt}" pattern="yyyy-MM-dd" />
					</c:otherwise>
				</c:choose>
			</dd>
		</dl>
		</c:if>
	</div>
	</c:if>
	<div class="research">
		<form id="researchForm" name="researchForm" action="${pageContext.request.contextPath}/${siteCodeFull}/satisfaction/voteSatis.do" method="POST" onsubmit="return false;">
			<input type="hidden" value="${siteCode}" name="siteCode" id="siteCode" />
			<input type="hidden" value="${menuVO.mId}" name="mId" id="mId" />
			<fieldset>
				<legend><span>페이지 만족도</span></legend>
				<ul class="radio_list">
					<li><input name="researchPoint" id="researchPoint5" type="radio" value="5"><label for="researchPoint5">매우만족</label></li>
					<li><input name="researchPoint" id="researchPoint4" type="radio" value="4"><label for="researchPoint4">만족</label></li>
					<li><input name="researchPoint" id="researchPoint3" type="radio" value="3"><label for="researchPoint3">보통</label></li>
					<li><input name="researchPoint" id="researchPoint2" type="radio" value="2"><label for="researchPoint2">불만</label></li>
					<li><input name="researchPoint" id="researchPoint1" type="radio" value="1"><label for="researchPoint1">매우불만</label></li>
				</ul>
				<label for="researchContent" class="hidden">페이지 만족도 평가 입력</label>
				<input type="text" id="researchContent" name="researchContent" class="txtbox" placeholder="홈페이지 이용에 대한 여러분의 소중한 이용을 남겨주세요."/>
				<input name="regBtn" class="regBtn" id="regBtn" type="submit" value="등록"/>
			</fieldset>
		</form>
	</div>
	<a href="#" class="scrollup" title="페이지 최상단으로 이동">맨위로</a>
</div>
<script type="text/javascript">
//<![CDATA[

	$(document).ready(function() {
		$("#regBtn").click(function() {
			if ($("[name=researchPoint]:checked").length == 0) {
				alert("평가 점수를 선택해주세요.");
				$("#researchPoint5").focus();
				return false;
			}

			$.ajax({
				type: "post", dataType: "json", url: document.researchForm.action,
				data: $("#researchForm").serialize(),
				success: function(result) { alert(result.message); }
			}).fail(function() { alert("서버와 통신 중 오류가 발생했습니다."); });
		});
	});

//]]>
</script>

<%-- ##### SOCIAL LOGIN & MENU COMMENT ##### --%>
<c:if test="${(empty isAuthPage || isAuthPage == 0) && menuVO.isSnsComment == 'Y' && menuVO.menuType != 'B'}">
	<jsp:include page="./menuComment.jsp" />
</c:if>
