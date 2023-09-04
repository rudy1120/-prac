<%
    /*
     * @Class Name : printPage.jsp
     * @Description : 컨텐츠 영역 프린트용 페이지
     * @  수정일      수정자            수정내용
     * @ -------   --------    ---------------------------
     * @ 2014-12-31  권태성          최초 생성
     * @ ------------------------------------------------------------
     * @ 호출한 페이지의 CSS를 읽어와서 인쇄 페이지에 적용 후 해당 컨텐츠를 인쇄합니다.
     * @ 호출한 페이지의 CSS를 가져와서 적용하므로 이 페이지에는 CSS를 넣으시면 안됩니다.
     * @ ------------------------------------------------------------
     */
%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Pragma" content="no-cache" />		
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		
		<title>부산도시공사 홈페이지 인쇄 페이지</title>
		<!-- common -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.10.0.custom.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js" ></script>
	</head>
	<body>
		<script type="text/javascript">
		//<![CDATA[
		$(document).ready(function() {
			
			var links = opener.document.getElementsByTagName('link');
			$(links).each(function(){
				var link = $(this);
				$('title').after(link.context.outerHTML);	
			});
			
			var styles = opener.document.getElementsByTagName('style');
			$(styles).each(function(){
				var styles = $(this);
				$('title').after(styles.context.outerHTML);	
			});

			document.title = opener.document.title;
			$("#conts")[0].innerHTML = $("#subContents", opener.document).html();
 		    window.print();
		    window.setTimeout(function () {
		    	window.close();
	        }, 1000);
		});
		//]]>
		</script>
	
		<article id="conts" style="-webkit-print-color-adjust: exact;">
		</article>
		
	</body>
</html>