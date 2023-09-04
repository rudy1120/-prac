<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%	String cmsUrl = EgovProperties.getProperty("ySmartCMS.url"); %>
<c:set var="cmsUrl" value="<%=cmsUrl %>" />
<%--
*********************************************************************
* 파 일 명 : uploadExcel.jsp
* 설       명 : 메뉴 엑셀 업로드
* 작   성  자 : 권태성
* 작   성  일 : 2016-06-27
*********************************************************************
--%>
<c:set var="domain" value="${yh:isLiveMode() ? yh:getProperty('document.domain') : 'localhost' }" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/AXJ/ui/arongi/AXJ.min.css">

<script type="text/javascript">
//<![CDATA[
$(document).ready(function(){

	document.domain = "${domain}";

	var statusInterval;
	$("#progressView").hide();

	$("#uploadBtn").click(function(){
	    var options = {
	    		url: "${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/uploadProc.do",
				type: "post",
				beforeSubmit: function(){
					var frm = document.getElementById("excelFrm");
					if(frm.excelFile.value == ""){
						alert("파일을 등록해주세요.");
						return false;
					}
				},
				success: function(data){
					/*
					alert(data.message);
					if(data.flag == 'success'){
						window.opener.location.reload();
						window.close();
					}
					*/
				},
				error : function(){
					alert('파일 전송을 실패했습니다. (서버접속 실패)');
					return false;
				},
				dataType: 'json',
				resetForm: false
		};

		$("#excelFrm").ajaxForm(options);

		//상태조회 interval
		statusInterval = setInterval(getStatus, 1000);
	});

});

function getStatus(){
	$("#progressView").show();
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/getUploadStatus.do",
		dataType: "json",
		success: function(data){
			if(data.flag == "success"){
				//$("#progress").text(data.nowRow+"/"+data.totalRows);
				var p = data.nowRow / data.totalRows * 100;
				$("#progressStatus").attr("style","width: "+p.toFixed(2)+"%; overflow: hidden;");
				$("#progressTxt").html(p.toFixed(2)+"%<span>"+data.nowRow+"/"+data.totalRows+"</span>");
				if(data.nowRow == data.totalRows){
					clearInterval(getStatus);

					alert("업로드가 완료되었습니다.");
					window.opener.location.reload();
					window.close();
				}
			}else{
				$("#progressView").html("등록 상태정보를 가져오지 못하였습니다.");
			}
		},
		error: function(xhr, status, error) {
			$("#progressView").html("등록 상태정보를 가져오지 못하였습니다.");
			//alert("데이터를 요청하는 과정에서 오류가 발생하였습니다.");
			//return false;
		},
	});
}
//]]>
</script>
<h2>메뉴 엑셀등록</h2>

<p class="info" style="margin-top:10px;">엑셀파일 양식작성하실 때 사용여부는 Y/N으로 입력 바랍니다.</p>
<p class="info" style="margin-top:10px;"><span class="red">엑셀로 등록하면 기존 메뉴는 삭제 됩니다. 백업후에 진행하시기 바랍니다.</span></p>

<form id="excelFrm" name="excelFrm" action="${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/uploadProc.do" method="post" enctype="multipart/form-data">
	<input type="hidden" id="siteCode" name="siteCode" value="${siteCode }" />
	<table class="tableSt_write">
		<caption>엑셀등록</caption>
		<colgroup>
			<col style="width:30%" span="1">
		</colgroup>
		<tbody>
			<tr>
				<th socpe="row">양식다운로드</th>
				<td>
					<a href="${pageContext.request.contextPath}/sys/download/MENU_TEMPLATE.xls" class="btn_white"><span class="wicon_down"></span>업로드양식 다운로드</a><br>
				</td>
			</tr>
			<tr>
				<th socpe="row">파일등록</th>
				<td>
					<input id="excelFile" name="excelFile" class="input300" type="file">
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input id="uploadBtn" value="파일저장" class="btn_cyan" type="submit">
	</div>

	<div class="AXprogressTray AXlineProgress" id="progressView" align="center" style="top: 276px;">
		<%-- progress title
		<div class="AXprogressTitle" id="" style="width: 300px;" align="left"></div>
		--%>
		<div class="AXprogress" id="" style="width: 300px;">
			<div class="AXprogressContainer" id="" align="left" style="overflow: hidden;">
				<div class="AXprogressBar" id="progressStatus" style="width: 0%; overflow: hidden;"></div>
			</div>
			<div class="AXprogressLoadedText" id="progressTxt">
				4%<span>2/50</span>
			</div>
		</div>
	</div>

</form>