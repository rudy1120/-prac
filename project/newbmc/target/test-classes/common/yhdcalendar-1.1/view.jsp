<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * YHD-Calendar 일정 상세 페이지
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.11.06		J.Ryeon Lee		최초 생성 및 코드 작성
	 * 2019.06.05		김선영			마크업 수정
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.11.06
	 */
%>
<link rel="stylesheet" href="/common/yhdcalendar-1.1/yhdcalendar.css"/>
<div class="exeSchedule_view">
	<dl class="clFix">
		<c:forEach var="column" items="${tableDef.columnDefList}">
			<c:if test="${column.isUse == 'Y' && column.display == 'Y'}">
				<c:set var="key" value="COL_${column.name}" />
					<dt>${column.description}</dt>
					<dd>${yh:nl2br(entity[key])}</dd>
			</c:if>
		</c:forEach>
		
		<c:if test="${tableDef.useFile == 'Y' && not empty entity['ATTACH_ID']}">
			<dt>첨부 파일</dt>
			<dd>
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${entity['ATTACH_ID']}" />
				</c:import>
			</dd>
		</c:if>
	</dl>
</div>
<div class="btn_wrap taR mT20">
	<a href="/${siteCodeFull}/${tableDef.urlName}/calendar.do?CATEGORY=${searchVO['CATEGORY']}&amp;mId=${menuVO.mId}&amp;goTo=${searchVO['goTo']}">목록</a>
</div>