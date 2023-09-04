<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : qrcodeList.jsp
* 설      명 : QR코드 관리 - 리스트 
* 작   성  자 : 권태성
* 작   성  일 : 2014.12.12
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
function showQrcode(qrcIdx) {
	var url = "${pageContext.request.contextPath}/sys/qrcode/previewQrcodeInfo.do?qrcIdx="+qrcIdx;

    window.open(url, "index", "left=100, top=100,  width=400,height=400,toolbar=no,status=no,scrollbars=yes,resizable=no,menubar=no");
    top.window.opener = top;
	top.window.open('','_parent', '');
}

function deleteQrcodeInfo(qrcIdx) {		
	if(qrcIdx == "") {
		alert("삭제할 QR코드를을 선택하시기바랍니다.");
		return;
	} else {
		var confirm = window.confirm("해당 QR코드를 삭제하시겠습니까?");
		
		if(confirm) {
			$.ajax({
			    type: "POST",
			    url: "${pageContext.request.contextPath}/sys/qrcode/deleteQrcodeDataProc.do",
			    dataType: "json",
			    data: {'qrcIdx' : qrcIdx },
			    success: resDeleteResponse,
				error: function(xhr, status, error) {
					alert("서버에 요청중 오류가 발생하였습니다.");
				},
				complete: function() {
				}
			});
		}
	}
}

function resDeleteResponse(data) {

	if(data.result == "success") {
		alert(data.message);

		var form = document.getElementById("searchForm");
		form.method = "post";
		form.action = "${pageContext.request.contextPath}/sys/qrcode/qrcodeInfoMng.do";
		form.submit();
	} else {
		alert(result.message);
		document.location.reload();
	}

}

//]]>
</script>

	<h2>QR코드 관리</h2>
	<form name="searchForm" id="searchForm">
		<input type="hidden" id="mId" name="mId" value="${curMid}"/>
		<table class="tableSt_list row_over">
			<caption>QR코드 관리</caption>
			<colgroup>
				<col span="1"  style="width:5%;"/>
				<col span="1" />
				<col span="2"  style="width:10%;"/>
				<col span="1"  style="width:15%;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">QR코드 제목</th>
					<th scope="col">미리보기</th>
					<th scope="col">QR코드</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty resultList}">
					<c:forEach items="${resultList}" var="list" varStatus="idx">
						<tr>
							<td>${idx.count}</td>
							<td class="taL"><a href="${pageContext.request.contextPath}/sys/qrcode/selectQrcodeDataDetail.do?mId=${curMid}&amp;qrcIdx=${list.qrcIdx}">${list.qrcTitle}</a></td>
							<td><a href="javascript:showQrcode(${list.qrcIdx})" class="btn_sblack">미리보기</a></td>
							<td><a href="${pageContext.request.contextPath}/common/sysFile_down.jsp?fileName=${list.qrcPrintFileName}&filePath=&fileMode=QR&site=&sysFilename=${list.qrcPrintFileName}" class="btn_sblack">다운로드</a></td>
							<td>
								<a href="${pageContext.request.contextPath}/sys/qrcode/moveQrcodeDataUpdatePage.do?mId=${curMid}&amp;qrcIdx=${list.qrcIdx}" class="btn_swhite">수정</a>
								<a href="#" onclick="deleteQrcodeInfo('${list.qrcIdx}'); return false;" class="btn_swhite">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty resultList}">
					<tr>
						<td colspan="5">조회된 QR코드가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</form>
	<div class="btn_boxR">
		<a href="${pageContext.request.contextPath}/sys/qrcode/moveQrcodeDataInsertPage.do?mId=${curMid}" class="btn_dblue">등록</a>
	</div>
