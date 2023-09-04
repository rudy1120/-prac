<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황 가이드 헤더 변경 폼
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.02.22		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.02.22
	 */
%>
<c:if test="${tableDef.headerModifyYn == 'Y'}">
<form name="headerForm" action="/sys/dataMng/headerModifyProc.do?mId=${menuVO.mId}" method="post">
	<div class="box_gray">
		<label for="headerContent" style="display:none;">가이드 변경</label>
		<textarea id="headerContent" name="headerContent" maxlength="4000" rows="8" class="input99">${tableDef.headerContent}</textarea>
		<input type="hidden" name="idx" value="${tableDef.idx}" />
		<input type="hidden" name="urlName" value="${tableDef.urlName}" />
		<div class="mT10">
			<input type="submit" class="btn_header" value="가이드 변경">
		</div>
	</div>
</form>
</c:if>
