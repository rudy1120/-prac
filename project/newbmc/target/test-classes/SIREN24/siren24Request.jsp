<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
 /**
  * @Class Name : passwordModify.jsp
  * @Description : 비밀번호변경
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2014-02-06   박동환          최초 생성
  * @ ------------------------------
  * 수정일자/작성자: 2014-02-09 김현호
  * 사유: 디자인코딩
  * @ ------------------------------
  */
%>
<script type="text/javascript">
//<![CDATA[
	
	function nameCheck(){
		CBA_window = window.open('${pageContext.request.contextPath}/SIREN24/pcc_vname_Input.jsp?mode=join&returnUrl=', 'CbaWindow', 'width=410, height=450, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200' );
	}   
	
//-->
</script>
<a href="javascript:nameCheck();" title="새창으로열림" class="btn_dgray">본인인증<span></span></a>	