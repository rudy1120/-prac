<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * YHD-Calendar 일정 상세 페이지
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.01.11		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.01.11
	 */
%>
<link rel="stylesheet" href="/common/yhdcalendar-1.0/yhdcalendar.css"/>
<script type="text/javascript">
//<![CDATA[

//]]>
</script>
<div class="exeSchedule_view">
	<%-- <c:if test="${not empty tableDef.category1List}">
		<dl>
			<dt>${fn:split(tableDef.category,',')[0] }</dt>
			<dd>
				<c:set var="chk" value="0" />
				<c:forEach var="category" items="${tableDef.category1List}">
					<c:if test="${category.categoryValue eq entity['COL_CATEGORY']}">
					${category.categoryKey}
					<c:set var="chk" value="1" />
					</c:if>
				</c:forEach>
				<c:if test="${chk eq '0' }">
					${entity['COL_CATEGORY'] }
				</c:if>
			</dd>
		</dl>
	</c:if>
	<c:if test="${not empty tableDef.category2List}">
		<dl>
			<dt><span class="red">*</span><label for="CATEGORY2">${fn:split(tableDef.category,',')[1]}</label></dt>
			<dd>
				<c:set var="chk" value="0" />
				<c:forEach var="category" items="${tableDef.category2List}">
					<c:if test="${category.categoryValue eq entity['COL_CATEGORY2']}">
					${category.categoryKey}
					<c:set var="chk" value="1" />
					</c:if>
				</c:forEach>
				<c:if test="${chk eq '0' }">
					${entity['COL_CATEGORY2'] }
				</c:if>
			</dd>
		</dl>
	</c:if> --%>
	<c:forEach var="column" items="${tableDef.columnDefList}">
		<c:if test="${column.isUse == 'Y' && column.display == 'Y'}">
			<c:set var="key" value="COL_${column.name}" />
			<dl>
				<dt>${column.description}</dt>
				<dd>${yh:nl2br(entity[key])}</dd>
			</dl>
		</c:if>
	</c:forEach>
	<c:if test="${tableDef.useFile == 'Y' && not empty entity['ATTACH_ID']}">
		<dl>
			<dt>첨부 파일</dt>
			<dd>
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${entity['ATTACH_ID']}" />
				</c:import>
			</dd>
		</dl>
	</c:if>
</div>
<div class="btnboxR">
	<a href="/${siteCodeFull}/${tableDef.urlName}/calendar.do?CATEGORY=${searchVO['CATEGORY']}&amp;mId=${menuVO.mId}&amp;goTo=${searchVO['goTo']}" class="btn_blue_normal">목록</a>
</div>

