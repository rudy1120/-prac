<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
/**
 * 주소검색 팝업
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2017.12.13		권태성			반응형에서 호출 할 수 있도록 적용
 *
 *
 * @author 권태성
 * @since 2017.12.11
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도로명 주소 검색 팝업</title>
<%
	//request.setCharacterEncoding("UTF-8");
	String inputYn = request.getParameter("inputYn");
	String roadFullAddr = request.getParameter("roadFullAddr");
	String roadAddrPart1 = request.getParameter("roadAddrPart1");
	String roadAddrPart2 = request.getParameter("roadAddrPart2");
	String engAddr = request.getParameter("engAddr");
	String jibunAddr = request.getParameter("jibunAddr");
	String zipNo = request.getParameter("zipNo");
	String addrDetail = request.getParameter("addrDetail");

	String mobile = request.getParameter("mobile");
	// 실서버 환경 문제로 주석 처리 >> 로컬에서는 문자가 깨짐
// 	if(roadFullAddr != null) roadFullAddr = new String (roadFullAddr.getBytes("ISO-8859-1"), "UTF-8");
// 	if(roadAddrPart1 != null) roadAddrPart1 = new String (roadAddrPart1.getBytes("ISO-8859-1"), "UTF-8");
// 	if(roadAddrPart2 != null) roadAddrPart2 = new String (roadAddrPart2.getBytes("ISO-8859-1"), "UTF-8");
// 	if(jibunAddr != null) jibunAddr = new String (jibunAddr.getBytes("ISO-8859-1"), "UTF-8");
// 	if(addrDetail != null) addrDetail = new String (addrDetail.getBytes("ISO-8859-1"), "UTF-8");

%>
</head>
<script language="javascript">
//<![CDATA[

	function init() {
		var mobile = "<%=mobile %>";
		var url = location.href;
		<%-- 현재 설정된 키는 안성시 키입니다. 해당 기관 키 발급 받아서 사용하세요. --%>
		var confmKey = (mobile == "null" ? "U01TX0FVVEgyMDE1MDgyMDE3MTcyMA==" : "U01TX0FVVEgyMDE3MDkyNzA5NDYzMTEwNzM3OTM=");
		var inputYn= "<%=inputYn%>";
		if (inputYn != "Y") {
			document.form.confmKey.value = confmKey;
			document.form.returnUrl.value = url;
			document.form.action = (mobile == "null" ? "//www.juso.go.kr/addrlink/addrLinkUrl.do" : "http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do");
			document.form.submit();
		} else {
			opener.jusoCallBack("<%=roadFullAddr%>","<%=roadAddrPart1%>","<%=addrDetail%>","<%=roadAddrPart2%>","<%=engAddr%>","<%=jibunAddr%>","<%=zipNo%>");
			window.close();
		}
	}


</script>
<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value="" />
		<input type="hidden" id="returnUrl" name="returnUrl" value="" />
	</form>
</body>
</html>