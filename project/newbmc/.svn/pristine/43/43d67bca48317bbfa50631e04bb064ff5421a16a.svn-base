<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="egovframework.portal.util.TUtil"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<jsp:include page="/bmc/jsp/unit/user/mIds.jsp" />

<c:set var="successUrl" value="${successUrl}" />
<c:set var="cancelUrl" value="${cancelUrl}" />
<c:set var="sAuthType" value="${sAuthType}" />

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript">
//<![CDATA[
	
	function joiner(value) { return value.indexOf("?") > -1 ? "&" : "?"; }

	function open_namecheck(postUrl) {
		var successUrl = "${successUrl}" ? "${successUrl}" : postUrl + joiner(postUrl) + "mId=${menuVO.mId}";
		window.open("/NICE/checkplus_input.jsp?successUrl=" + successUrl + "&mId=${menuVO.mId}&idx=${idx}&cancelUrl=${cancelUrl}&sAuthType=${sAuthType}", "popupChk", "width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no");
	}
	
	function replaceAll(str, searchStr, replaceStr) {
		return str.split(searchStr).join(replaceStr);
	}

	function go_prev_page() {
		var cancelUrl = "${cancelUrl}";
		if (cancelUrl) {
			location.href = cancelUrl;
		} else {
			location.href = "/${siteCodeFull}/contents.do?mId=${menuVO.mId}";
		}
	}
	
	$(function(){
		//20.04.16 웹접근성 반영 -탭이동시 title 정보 수정 
		titleVal = "본인확인 - " + $("title").html();
		$("title").html(titleVal);
	});

//]]>
</script>
<div id="contents">
	<div class="content">
		<div>
			<form name="reqSIRENForm" id="frmRealName" title="본인인증" action="#" onsubmit="return false;" method="post">
				<div>
					<div class="gap"></div>
					<span class="intro-txt ReName-txt">본인확인이 필요한 서비스입니다.<br/>
					아래 "<strong class="ReNameS">본인확인</strong>" 버튼을 클릭하여 인증 절차를 진행해주세요.</span>
					<div class="gap20"></div>
					<img src="/bmc/images/content/ReName_icon.jpg" alt=""  class="ReNameImg"/>
					<div class="btnall_wrap">
						<div class="btn_wrap" style="float:none; text-align:center;">
							<div class="btn_in">
								<input type="submit" onclick="open_namecheck('${successUrl}');" value="본인확인" title="새창 열림" />
								<input type="button" onclick="go_prev_page(); return false;" class="cancel" value="취소" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
