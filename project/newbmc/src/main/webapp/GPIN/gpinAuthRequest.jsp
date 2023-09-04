<%@ page language = "java" contentType = "text/html; charset=UTF-8"%>
<%@ page import="egovframework.portal.util.TUtil"%>
<%@ page import="gov.mogaha.gpin.sp.proxy.*" %>

<%

	String returnUrl   = TUtil.nullTobaseStr(request.getParameter("returnUrl"), "");   
    String ptIdx   = TUtil.nullTobaseStr(request.getParameter("ptIdx"), "");   
    String mId   = TUtil.nullTobaseStr(request.getParameter("mId"), ""); 
    String nightGubn   = TUtil.nullTobaseStr(request.getParameter("nightGubn"), "");
	String resDate   = TUtil.nullTobaseStr(request.getParameter("resDate"), "");
	
    
	String redirUrl = "";
    
    if( returnUrl.indexOf("bIdx") > -1 ){
    	redirUrl  = returnUrl + "&ptIdx=" + ptIdx ;                           // 본인인증 완료 후 이동할 URL
    	//+ "&mId=" + mId
    }else{
    	redirUrl  = returnUrl;                           // 본인인증 완료 후 이동할 URL
    }
    
    if(!nightGubn.equals("")){//야간교육 파라미터 처리
    	redirUrl  = returnUrl + "&nightGubn=" + nightGubn;                         
    }

    session.setAttribute("redirUrl", redirUrl);
    session.setAttribute("mId", mId);
	session.setAttribute("resDate", resDate);

	//out.println(redirUrl);
	//out.println(mId);

    /**
     * 사용자 본인인증을 요청하는 페이지입니다.
     * 회원가입, 게시판 글쓰기등 본인인증이 필요한 경우에 이 페이지를 호출하시면 됩니다.
     * 인증이 완료되면 session에 사용자정보가 설정됩니다.
     * 설정된 사용자 정보를 참조하는 방법은 Sample-AuthResponse를 참조하시기 바랍니다.
     */
    // 인증완료후 session에 저장된 사용자정보를 참조할 페이지, (이용기관 인증수신페이지와 다릅니다.)
    // TODO 이용기관에서 사용하실 페이지를 지정합니다.
    session.setAttribute("gpinAuthRetPage", "Sample-AuthResponse.jsp");
    // 인증 수신시 요청처와 동일한 위치인지를 확인할 요청자IP를 session에 저장합니다.
    session.setAttribute("gpinUserIP", request.getRemoteAddr());

/*
    GPinProxy proxy = GPinProxy.getInstance(this.getServletConfig().getServletContext());

    String requestHTML = "인증요청 메시지생성 실패";
    try
    {
        if (request.getParameter("Attr") != null)
        {
            requestHTML = proxy.makeAuthRequest(Integer.parseInt(request.getParameter("Attr")));
        }
        else
        {
            requestHTML = proxy.makeAuthRequest();
        }
    }
    catch(Exception e)
    {
        // 에러에 대한 처리는 이용기관에 맞게 처리할 수 있습니다.
        e.printStackTrace();
        out.println(e.getMessage());
    }
    // 인증 요청페이지를 생성하여 자동으로 공공I-PIN으로 forwarding 합니다.
    out.println(requestHTML);
*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>GPIN 인증 중</title>
<script language="javascript" type="text/javascript">
<!--
function body_onload() 
{
    document.reqForm.SAMLRequest.value = "PEF1dGhuUmVxdWVzdCB4bWxucz0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnByb3RvY29s"
+"IiBBc3NlcnRpb25Db25zdW1lclNlcnZpY2VVUkw9Ik5vVXNlIiBBdHRyaWJ1dGVDb25zdW1pbmdT"
+"ZXJ2aWNlSW5kZXg9IjAiIEZvcmNlQXV0aG49ImZhbHNlIiBJRD0iX2E5MGZmNjBiZDgxOGIyMWNm"
+"ZTVhOTlmZWJhMDM2YjAwMDRkZWY2ZGEiIElzUGFzc2l2ZT0iZmFsc2UiIElzc3VlSW5zdGFudD0i"
+"MjAxNi0wMi0xNVQyMDowNzozNS40MjYrMDk6MDAiIFByb3RvY29sQmluZGluZz0idXJuOm9hc2lz"
+"Om5hbWVzOnRjOlNBTUw6Mi4wOmJpbmRpbmdzOkhUVFAtUE9TVCIgUHJvdmlkZXJOYW1lPSJIWVQw"
+"S0dXQzNNNkEiIFZlcnNpb249IjIuMCI+PG5zMTpJc3N1ZXIgeG1sbnM6bnMxPSJ1cm46b2FzaXM6"
+"bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIiBGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpT"
+"QU1MOjIuMDpuYW1laWQtZm9ybWF0OmVudGl0eSI+SFlUMEtHV0MzTTZBPC9uczE6SXNzdWVyPjxk"
+"czpTaWduYXR1cmUgeG1sbnM6ZHM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyMi"
+"Pgo8ZHM6U2lnbmVkSW5mbz4KPGRzOkNhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJo"
+"dHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiPjwvZHM6Q2Fub25pY2FsaXph"
+"dGlvbk1ldGhvZD4KPGRzOlNpZ25hdHVyZU1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMu"
+"b3JnLzIwMDAvMDkveG1sZHNpZyNyc2Etc2hhMSI+PC9kczpTaWduYXR1cmVNZXRob2Q+CjxkczpS"
+"ZWZlcmVuY2UgVVJJPSIjX2E5MGZmNjBiZDgxOGIyMWNmZTVhOTlmZWJhMDM2YjAwMDRkZWY2ZGEi"
+"Pgo8ZHM6VHJhbnNmb3Jtcz4KPGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMu"
+"b3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJlIj48L2RzOlRyYW5zZm9ybT4K"
+"PGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4"
+"Yy1jMTRuIyI+PC9kczpUcmFuc2Zvcm0+CjwvZHM6VHJhbnNmb3Jtcz4KPGRzOkRpZ2VzdE1ldGhv"
+"ZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNzaGExIj48L2Rz"
+"OkRpZ2VzdE1ldGhvZD4KPGRzOkRpZ2VzdFZhbHVlPmhEVFh2dS9pRHNuUXRFekVYVHBwdTJrd0NX"
+"VT08L2RzOkRpZ2VzdFZhbHVlPgo8L2RzOlJlZmVyZW5jZT4KPC9kczpTaWduZWRJbmZvPgo8ZHM6"
+"U2lnbmF0dXJlVmFsdWU+CkRGdzFnUkpDaTJUWFdPSGozWjR5OEk1cHNHUTNVWGVYV3NocEk5eFZm"
+"VnM1bUdpS2NleDd4WFlQT3RHOTd6ZkJlSUxVU1hFbW5tSEYKamRmWjltcnNIWFVVWXBQa3NodDNG"
+"ZXJ2bjFXcERDa2h0UXVKbWVyWlRSVTZITDdBbEl5UVFRc3FxUnl2cUlXemdtajlNOWI3WURBbAph"
+"WEpDRzhNejcxb2h1UVJZblVVPQo8L2RzOlNpZ25hdHVyZVZhbHVlPgo8ZHM6S2V5SW5mbz4KPGRz"
+"Olg1MDlEYXRhPgo8ZHM6WDUwOUNlcnRpZmljYXRlPgpNSUlENVRDQ0FzMmdBd0lCQWdJUVNzQTgy"
+"UU9yMmpROFJaZGoxVEg2TURBTkJna3Foa2lHOXcwQkFRVUZBREJRTVFzd0NRWURWUVFHCkV3SkxV"
+"akVjTUJvR0ExVUVDaE1UUjI5MlpYSnViV1Z1ZENCdlppQkxiM0psWVRFTk1Bc0dBMVVFQ3hNRVIx"
+"QkxTVEVVTUJJR0ExVUUKQXhNTFEwRXhNekV3TURBd01ERXdIaGNOTURrd09USTRNRFF6TkRNeldo"
+"Y05NVEV4TWpJNE1EUXpORE16V2pCZE1Rc3dDUVlEVlFRRwpFd0pMVWpFY01Cb0dBMVVFQ2d3VFIy"
+"OTJaWEp1YldWdWRDQnZaaUJMYjNKbFlURVlNQllHQTFVRUN3d1BSM0p2ZFhBZ2IyWWdVMlZ5CmRt"
+"VnlNUll3RkFZRFZRUUREQTFUVmxJMU16Z3dNVEEzTURBek1JR2VNQTBHQ1NxR1NJYjNEUUVCQVFV"
+"QUE0R01BRENCaUFLQmdFbTMKSWh4eVZHelpWWUNNVEY3SjhzcWp0VHR6M3NaVUlXUmFiNHNBVUFp"
+"cEdvS2lvNmM5NjBZWUFvK2xqN1NDdTUyS1VpRDdMZFBkMHFocwovTDdvdXNwREF1RTFxa1o1TXo3"
+"dUhUcUNkNURHYXRydzYvWFpXam82dElpbmZZQTN1YlkwMW1tL2QzQmdKVlhpWjhnaE1tTWZxZitW"
+"CnJzU3hqQVVta1VJeU9WYWxBZ01CQUFHamdnRXhNSUlCTFRBZkJnTlZIU01FR0RBV2dCUUJ6eGV1"
+"QkkySHpMZFZDM0ZzYTBpYzVyR3kKbFRBZEJnTlZIUTRFRmdRVUdMTXkvMzRLMEl4NEZWOGZlZEM4"
+"WHk5K293d3dEZ1lEVlIwUEFRSC9CQVFEQWdVZ01CZ0dBMVVkSUFRUgpNQTh3RFFZSktvTWFobzBo"
+"QWdFQ01BQXdnWWdHQTFVZEh3U0JnREIrTUh5Z2VxQjRoblpzWkdGd09pOHZZMlZ1TG1ScGNpNW5i"
+"eTVyCmNqb3pPRGt2WTI0OVkzSnNNREF5TlN4amJqMURRVEV6TVRBd01EQXdNU3h2ZFQxSFVFdEpM"
+"Rzg5UjI5MlpYSnViV1Z1ZENCdlppQkwKYjNKbFlTeGpQVXRTUDJObGNuUnBabWxqWVhSbFVtVjJi"
+"Mk5oZEdsdmJteHBjM1E3WW1sdVlYSjVNRFlHQ0NzR0FRVUZCd0VCQkNvdwpLREFtQmdnckJnRUZC"
+"UWN3QVlZYWFIUjBjRG92TDJkMllTNW5jR3RwTG1kdkxtdHlPamd3T0RBd0RRWUpLb1pJaHZjTkFR"
+"RUZCUUFECmdnRUJBRmpRbXdua1VoRGREUHVwUldKaWw1SmVuN2dFeERaSURVR3RXM0ZSWkcxWTJs"
+"R25qNlArSjZHNFZsRFpKZWdLaTU4Wkw2K2IKN2s3WlQ4Zlg3QUtDcnhJQ1VpTFFvZGZNemdWd0Zs"
+"M3BxTWlQOUJPUm5zQ0NyMXFaVzNFQ2ZZYml1MDBNU1RwWFZHMDVMb3kzMmFlaApjVlljYmQzd1gw"
+"RWwyTTJPbGM1VkViKyt1S1IrTGYxR3FxMm1BeHlHMTJIek9EQlliWXcwWWNWWmZCTnpJSjQ1elF2"
+"K00vRDUwdjh1CnUxYVllRGp2Qm54R2FjRGgyRlJFU3ZxajFsaU02YVRUTzNhdlR5QlppUnJhWWwx"
+"Sk5KaVBQSnFZWTZ6WEwvSVhHcU05UDk4R1FMRWYKbmE3dlYrc2pHdUw0aDBaZTNBckJoQU4wWXRP"
+"ejJEeGUvQ0hNRndmQS9wST0KPC9kczpYNTA5Q2VydGlmaWNhdGU+CjwvZHM6WDUwOURhdGE+Cjwv"
+"ZHM6S2V5SW5mbz4KPC9kczpTaWduYXR1cmU+PE5hbWVJRFBvbGljeSBBbGxvd0NyZWF0ZT0idHJ1"
+"ZSIgRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6bmFtZWlkLWZvcm1hdDpwZXJz"
+"aXN0ZW50Ij48L05hbWVJRFBvbGljeT48UmVxdWVzdGVkQXV0aG5Db250ZXh0IENvbXBhcmlzb249"
+"ImV4YWN0Ij48bnMyOkF1dGhuQ29udGV4dENsYXNzUmVmIHhtbG5zOm5zMj0idXJuOm9hc2lzOm5h"
+"bWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI+dXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFj"
+"OmNsYXNzZXM6UGFzc3dvcmQ8L25zMjpBdXRobkNvbnRleHRDbGFzc1JlZj48L1JlcXVlc3RlZEF1"
+"dGhuQ29udGV4dD48L0F1dGhuUmVxdWVzdD4=";
    document.reqForm.submit();
}
// -->
</script>
</head>
<body onload="body_onload()" style="font-family: 굴림">
    <form name="reqForm" id="reqForm" method="post" action="http://www.g-pin.go.kr/VerifyRequestService/Post">
        <input type="hidden" name="SAMLRequest" />
     <input type="submit" name="input1" style="display:none" />
    </form>
<div id="divLogging" style="width: 400; left: 0%; position: absolute; top: 0px; font-size: 10pt; font-family: Arial; text-align: center; vertical-align: middle; z-index: 2; visibility: visible;">
<table border="0" cellspacing="1" cellpadding="10" align="center" bgcolor="#dbdee8">
<tr>
<td width="378" height="93" align="center" valign="top" background="http://www.g-pin.go.kr/common/images/connect/ad_mainbar_back02.gif">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="25%" height="30">&nbsp;</td>
<td width="75%">&nbsp;</td>
</tr>
<tr>
<td height="30">&nbsp;</td>
<td><img src='http://www.g-pin.go.kr/common/images/connect/progress.gif' alt='진행중' width="210" height="11" alt=""/></td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
</table>
</td>
</tr>
</table>
</div>
</body>
</html>