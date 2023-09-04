<%@page import="egovframework.portal.util.UserUtil"%>
<%@ page  contentType = "text/html;charset=UTF-8"%>
<%@ page import ="java.net.URLDecoder" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="egovframework.portal.util.TUtil"%>
<%@ page import="egovframework.portal.unit.common.UserType"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.authentication.UsernamePasswordAuthenticationToken"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%@ page import="egovframework.portal.security.vo.Role"%>
<%@ page import="egovframework.portal.security.RoleType"%>
<%@ page import="egovframework.portal.security.user.vo.User"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%
	/**************************************************************************************************************************
	 * Program Name  : 본인확인 결과 수신 Sample JSP
	 * File Name	 : pcc_V3_result_seed.jsp
	 * Comment	   :
	 * History	   :
	 *
	 **************************************************************************************************************************/
%>

<%
	// 변수 --------------------------------------------------------------------------------
	String retInfo		= "";																// 결과정보

	String name			= "";															   //성명
	String sex			= "";																//성별
	String birYMD		= "";																//생년월일
	String fgnGbn		= "";																//내외국인 구분값

	String di			= "";																//DI
	String ci1			= "";																//CI
	String ci2			= "";																//CI
	String civersion	= "";															   //CI Version

	String reqNum		= "";															   // 본인확인 요청번호
	String result		= "";															   // 본인확인결과 (Y/N)
	String certDate		= "";															   // 검증시간
	String certGb		= "";															   // 인증수단
	String cellNo		= "";																// 핸드폰 번호
	String cellCorp		= "";																// 이동통신사
	String addVar		= "";


	//복화화용 변수
	String encPara		= "";
	String encMsg		= "";
	String msgChk	   = "N";

	String redirUrl = session.getAttribute("redirUrl")+"";
	redirUrl = TUtil.rplcDecRedirectUrl(redirUrl);
	redirUrl = URLDecoder.decode(redirUrl, "UTF-8");
	String mId = session.getAttribute("mId")+"";
	String svrName = (String)request.getServerName().toString();
	String diOnly = session.getAttribute("diOnly")+"";
	//-----------------------------------------------------------------------------------------------------------------

	//쿠키값 가져 오기
	Cookie[] cookies = request.getCookies();
	String cookiename = "";
	String cookiereqNum = "";
	if(cookies!=null){
		for (int i = 0; i < cookies.length; i++){
			Cookie c = cookies[i];
			cookiename = c.getName();
			cookiereqNum = c.getValue();
			if(cookiename.compareTo("reqNum")==0) break;

			cookiereqNum = null;
		}
	}

	try{

		// Parameter 수신 --------------------------------------------------------------------
		retInfo  = request.getParameter("retInfo").trim();

%>
<%
	// 1. 암호화 모듈 (jar) Loading
	com.sci.v2.pcc.secu.SciSecuManager sciSecuMg = new com.sci.v2.pcc.secu.SciSecuManager();
	//쿠키에서 생성한 값을 Key로 생성 한다.
	retInfo  = sciSecuMg.getDec(retInfo, cookiereqNum);

	// 2.1차 파싱---------------------------------------------------------------
	String[] aRetInfo1 = retInfo.split("\\^");

	encPara  = aRetInfo1[0];		 //암호화된 통합 파라미터
	encMsg   = aRetInfo1[1];	//암호화된 통합 파라미터의 Hash값

	String  encMsg2   = sciSecuMg.getMsg(encPara);
	// 3.위/변조 검증 ---------------------------------------------------------------
	if(encMsg2.equals(encMsg)){
		msgChk="Y";
	}

	if(msgChk.equals("N")){
%>
<script language=javascript>
	alert("비정상적인 접근입니다.!!<%=msgChk%>");
</script>
<%
		return;
	}


	// 복호화 및 위/변조 검증 ---------------------------------------------------------------
	retInfo  = sciSecuMg.getDec(encPara, cookiereqNum);

	String[] aRetInfo = retInfo.split("\\^");

	name		= aRetInfo[0];
	birYMD		= aRetInfo[1];
	sex			= aRetInfo[2];
	fgnGbn		= aRetInfo[3];
	di			= aRetInfo[4];
	ci1			= aRetInfo[5];
	ci2			= aRetInfo[6];
	civersion	= aRetInfo[7];
	reqNum		= aRetInfo[8];
	result		= aRetInfo[9];
	certGb		= aRetInfo[10];
	cellNo		= aRetInfo[11];
	cellCorp	= aRetInfo[12];
	certDate	= aRetInfo[13];
	addVar		= aRetInfo[14];

	name = URLDecoder.decode(name,"utf-8");

	// 웹취약점 처리(2014.10.01)
	name.replaceAll("<","&lt;").replaceAll(">","&gt;");
	di.replaceAll("<","&lt;").replaceAll(">","&gt;");
	reqNum.replaceAll("<","&lt;").replaceAll(">","&gt;");
	redirUrl.replaceAll("<","&lt;").replaceAll(">","&gt;");

	// 정상 확인 되는경우
	if (msgChk.equals("Y")) {
		if ( di != null && !"".equals(di) ) {
			HttpSession s = request.getSession(true);

			if (diOnly.equals("Y")) {
				session.setAttribute("parentDI", di);
				session.setAttribute("parentName", name);
			} else {

				try {
					User user = new User();
					user.setUserName(name); // name
					user.setPrivatecode(di); // di
					user.setBirthday(birYMD); // birthday
					if (cellNo.length() < 11) {
						user.setTel1(cellNo.substring(0, 3));
						user.setTel2(cellNo.substring(3, 6));
						user.setTel3(cellNo.substring(6, 10));
					} else {
						user.setTel1(cellNo.substring(0, 3));
						user.setTel2(cellNo.substring(3, 7));
						user.setTel3(cellNo.substring(7, 11));
					}
					user.setGender(sex); // sex
					user.setAge(UserUtil.calculateManAge(birYMD)); // age
					user.setUserType(UserType.PHONE.getCode()); // userType

					List<Role> roles = new ArrayList<Role>();
					roles.add(new Role(RoleType.USER_TMP.getCode()));
					user.setAuthorities(roles);

					Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 성공
				} catch (Exception e) {
					e.printStackTrace();
					SecurityContextHolder.getContext().setAuthentication(null); // 인증 세션 초기화
				}

			}

			if("".equals(redirUrl)){
				redirUrl = addVar;
				// if(!mId.equals("")) redirUrl +="&mId="+mId;
			}else{
				//redirUrl = redirUrl;
				if(!mId.equals("")&&!mId.equals("0000000001")&&redirUrl.indexOf("mId") == -1) redirUrl +="&mId="+mId;
				redirUrl = redirUrl.replaceAll("amp=", "");
			}
		}else{
			msgChk = "N";
		}
	}
%>
<html>
<head>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/common/js/jquery/jquery-1.9.1.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript">
		function end() {
			<%if("Y".equals(msgChk)) {%>
				<%if (redirUrl.equals("close")) {%>
					<%if (UserUtil.calculateManAge(birYMD) >= 14) {%>
						$("#sirenBtn", opener.document ).html("<span class=\"red\">인증되었습니다.</span>");
						$("#parentConfirm", opener.document ).val("Y");
						self.close();
					<%} else {%>
						alert("만 14세 미만의 정보로는 보호자 인증을 할 수 없습니다.");
						<% session.removeAttribute("parentDI"); %>
						self.close();
					<%}%>
				<%} else {%>
					opener.location.href = "<%=redirUrl%>";
					self.close();
				<%}%>
			<%} else {%>
				alert("비정상적인 접근입니다.");
				self.close();
			<%}%>
		}
	</script>
</head>
<body onload="end();">
<%--
본인확인 response TEST!
<br/>
name : <%=name%><br/>
birYMD : <%=birYMD%><br/>
sex : <%=sex%><br/>
fgnGbn : <%=fgnGbn%><br/>
di : <%=di%><br/>
ci1 : <%=ci1%><br/>
ci2 : <%=ci2%><br/>
civersion : <%=civersion%><br/>
reqNum : <%=reqNum%><br/>
result : <%=result%><br/>
certGb : <%=certGb%><br/>
cellNo : <%=cellNo%><br/>
cellCorp : <%=cellCorp%><br/>
certDate : <%=certDate%><br/>
addVar : <%=addVar%><br/>
redirUrl : <%=redirUrl%><br/>
mId : <%=mId%><br/>

 --%>
</body>
</html>
<%
		// ----------------------------------------------------------------------------------

	} catch(Exception ex) {
		System.out.println("[pcc] Receive Error -"+ex.getMessage());
	}
%>
