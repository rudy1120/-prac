<%@page import="egovframework.portal.util.TUtil"%>
<%
    /**************************************************************************************************************************
     * Program Name  : 본인확인 요청
     * File Name     : pcc_vname_Input.jsp
     * Comment       :
     * History       :
     *
     **************************************************************************************************************************/
%>
<%@ page import ="java.text.SimpleDateFormat,java.util.Calendar"%>
<%@ page import="java.util.Random" %>
<%
    response.setHeader("Pragma", "no-cache" );
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-store");
    response.setHeader("Cache-Control", "no-cache" );
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //날짜 생성
    Calendar today = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String day = sdf.format(today.getTime());

    java.util.Random ran = new Random();
    //랜덤 문자 길이
    int numLength = 6;
    String randomStr = "";

    for (int i = 0; i < numLength; i++) {
        //0 ~ 9 랜덤 숫자 생성
        randomStr += ran.nextInt(10);
    }
%>
<%
    String id       = "SKGT001";                                                     // 본인실명확인 회원사 아이디
    String srvNo    = "002004";                                                      // 본인실명확인 서비스번호
    String retUrl   = "32http://www.yangsan.go.kr/SIREN24/pcc_vname_Output.jsp";    // 본인실명확인 결과수신 URL
    String reqNum = day + randomStr;                                                // 본인실명확인 요청번호
    String exVar    = "0000000000000000";                                           // 복호화용 임시필드
    String certDate=day;                                                            // 본인실명확인 요청시간
    String certGb	= "H";                                                          // 본인실명확인 본인확인 인증수단
    String addVar	= request.getParameter("addVar");                               // 본인실명확인 추가 파라메터

    String svrName = (String)request.getServerName().toString();
    
    //본인인증 완료 후 이동할 URL 세팅 BBS일경우
    String returnUrl   = TUtil.nullTobaseStr(request.getParameter("returnUrl"), "");   
    String ptIdx   = TUtil.nullTobaseStr(request.getParameter("ptIdx"), "");   
    String nightGubn   = TUtil.nullTobaseStr(request.getParameter("nightGubn"), "");
    String mId   = TUtil.nullTobaseStr(request.getParameter("mId"), ""); 
    String redirUrl = "";
    
    
    if( returnUrl.indexOf("bIdx") > -1 ){
    	redirUrl  = returnUrl + "&ptIdx=" + ptIdx;                           // 본인인증 완료 후 이동할 URL
    	// + "&mId=" + mId
    }else{
    	redirUrl  = returnUrl;                           // 본인인증 완료 후 이동할 URL
    }
    
    if(!nightGubn.equals("")){//야간교육 파라미터 처리
    	redirUrl  = returnUrl + "&nightGubn=" + nightGubn;                         
    }
  
    if( svrName.indexOf("61.85.36.154") > -1 ){
        srvNo  = "011001";
        retUrl = "32http://61.85.36.154:8888/SIREN24/pcc_vname_Output.jsp";
        addVar = "http://61.85.36.154:8888/mayor/main.do";
    }else if( svrName.indexOf("yangsan.go.kr") > -1 ){
        srvNo  = "002004";
        retUrl = "32http://www.yangsan.go.kr/SIREN24/pcc_vname_Output.jsp";
        addVar = "http://www.yangsan.go.kr/mayor/main.do";
    }else if( svrName.indexOf("mayor.yangsan.go.kr") > -1 ){
        srvNo  = "008004";
        retUrl = "32http://mayor.yangsan.go.kr/SIREN24/pcc_vname_Output.jsp";
        addVar = "http://mayor.yangsan.go.kr";
    }else{
        srvNo  = "012001";
        retUrl = "32http://yangsan.yhproject.kr:8180/SIREN24/pcc_vname_Output.jsp";
        addVar = "http://yangsan.yhproject.kr:8180/mayor/main.do";
    }

    /**
     *
     * reqNum 값은 최종 결과값 복호화를 위한 SecuKey로 활용 되므로 중요합니다.
     * reqNum 은 본인 확인 요청시 항상 새로운 값으로 중복 되지 않게 생성 해야 합니다.
     * 쿠키 또는 Session및 기타 방법을 사용해서 reqNum 값을
     * pcc_V3_result_seed.jsp에서 가져 올 수 있도록 해야 함.
     * 샘플을 위해서 쿠키를 사용한 것이므로 참고 하시길 바랍니다.
     *
     */
    Cookie c = new Cookie("reqNum", reqNum);
    //c.setMaxAge(1800);  // <== 필요시 설정(초단위로 설정됩니다)
    response.addCookie(c);

    session.setAttribute("reqNum", reqNum);
    session.setAttribute("redirUrl", redirUrl);
    session.setAttribute("mId", mId);

    //01. 암호화 모듈 선언
    com.sci.v2.pcc.secu.SciSecuManager seed  = new com.sci.v2.pcc.secu.SciSecuManager();

    //02. 1차 암호화
    String encStr = "";
    String reqInfo      = id+"^"+srvNo+"^"+reqNum+"^"+certDate+"^"+certGb+"^"+addVar+"^"+exVar;  // 데이터 암호화
    encStr              = seed.getEncPublic(reqInfo);

    //03. 위변조 검증 값 생성
    com.sci.v2.pcc.secu.hmac.SciHmac hmac = new com.sci.v2.pcc.secu.hmac.SciHmac();
    String hmacMsg = hmac.HMacEncriptPublic(encStr);

    //03. 2차 암호화
    reqInfo  = seed.getEncPublic(encStr + "^" + hmacMsg + "^" + "0000000000000000");  //2차암호화

    reqInfo.replaceAll("<","&lt;").replaceAll(">","&gt;");
%>
<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko" xml:lang="ko">-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <title>양산시 가상식별 실명확인 서비스 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script language=javascript>
        <!--
        var PCC_window;

        function openPCCWindow(){
            document.reqPCCForm.action = 'https://pcc.siren24.com/pcc_V3/jsp/pcc_V3_j10.jsp';
            document.reqPCCForm.target = '_self';
            document.reqPCCForm.submit();
        }

        //-->
    </script>
</head>
<body onload="openPCCWindow()">
<!-- 본인실명확인서비스 요청 form --------------------------->
<form name="reqPCCForm" method="post" action = "" target="_self">
    <input type="hidden" name="reqInfo"     value = "<%=reqInfo%>">
    <input type="hidden" name="retUrl"      value = "<%=retUrl%>">
</form>
<!--End 가상식별실명확인서비스 요청 form ----------------------->
</body>
</html>