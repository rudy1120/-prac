<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 공통 변수 선언 (관리자단)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.11		권태성			최초 생성 및 코드 작성
	 *
	 *
	 * @author 권태성
	 * @since 2017.05.11
	 */
%>
<c:set var="_context" value="${pageContext.request.contextPath}" scope="request"/>
<script type="text/javascript">
//<![CDATA[
var yh = {
	"mId" : "${menuVO.mId}",
	"menuName" : "${menuVO.menuName}",
	"programUrl" : "${menuVO.programUrl}",
	"error" : "${error}",
	"contextPath" : "${_context}",
	"siteCode": "sys"
};
//]]>
</script>
