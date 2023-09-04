<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
//<![CDATA[
jQuery(document).ready(function() {

    var type = '${result.qrcType}';

    if(type == 'url'){
    	$('#trName').hide();
    	$('#trCompany').hide();
    	$('#trTel').hide();
    	$('#trEmail').hide();
    	$('#trAddress').hide();
    	$('#trMemo').hide();
    }

});
	$(function(){	
		// QR코드 수정 폼 submit
		$("#modifyBtn").unbind("click").click(function(){
			var options = {
				url: "${pageContext.request.contextPath}/sys/qrcode/updateQrcodeDataProc.do",
				type: "post",
				beforeSubmit: validateForm,
				success: resultResponse,
				dataType: 'json',
				resetForm: false
			};
	
			// bind form using 'ajaxForm'
			$("#qrcodeForm").ajaxForm(options);
		});	
	
		// QR코드 폼 Validation pre-submit callback
		function validateForm(formData, jqForm, options) {
			var form = jqForm[0];

			if(!form.qrcTitle.value) {
				alert("제목을입력 하세요.");
				form.qrcTitle.focus();	
				return false;
			}

		}
	
		// QR코드 수정 결과 post-submit callback
		function resultResponse(data) {
			if(data.result == "success") {
				alert(data.message);
				document.location.href = "${pageContext.request.contextPath}/sys/qrcode/qrcodeInfoMng.do?mId=${curMid}";
			} else alert(data.message);
		}
	});

	setInterval( function() {

		var type = document.getElementById('qrcType').value;

		if(type == 'namecard'){
			var url = "${pageContext.request.contextPath}/sys/jsp/qrcode/qrcode.jsp";

			var title = document.getElementById("qrcTitle").value;
			var qrSize = document.getElementById("qrcSize").value;
			var imgObj = document.getElementById("qrcode_img");
			var fileName = document.getElementById("fileName").value;

			if( title ) {
				title = encodeURIComponent(title);

				var name = document.getElementById("qrcName").value;
				var company = document.getElementById("qrcCompany").value;
				var tel = document.getElementById("qrcTel").value;
				var email = document.getElementById("qrcEmail").value;
				var address = document.getElementById("qrcAddress").value;
				var website = document.getElementById("qrcLink").value;
				var memo = document.getElementById("qrcMemo").value;
				
				if( qrSize > 0 && qrSize < 500 ) {

					url += "?width=" + qrSize + "&height=" + qrSize + "&title=" + title + "&name=" + name + "&company=" + company + "&tel=" + tel + "&email=" + email + "&address=" + address + "&website=" + website + "&memo=" + memo + "&fileName=" + fileName;							
					imgObj.src = url;
				}
			}
		}else{
			var url = "${pageContext.request.contextPath}/sys/jsp/qrcode/qrcodeUrl.jsp";
			var title = document.getElementById("qrcTitle").value;
			var qrSize = document.getElementById("qrcSize").value;
			var imgObj = document.getElementById("qrcode_img");
			var fileName = document.getElementById("fileName").value;

			if( title ) {
				title = encodeURIComponent(title);

				var website = document.getElementById("qrcLink").value;
				website = website.split("?").join("@");
				website = website.split("&").join("^");
				website = website.split("=").join(";");
				
				if( qrSize > 0 && qrSize < 500 ) {

					url += "?width=" + qrSize + "&height=" + qrSize + "&website=" + website + "&fileName=" + fileName;							
					imgObj.src = url;
				}
			}
		}

	}, 1000 );

	function changeType(obj){
		if(obj.value == 'url'){
			$('#trName').hide();
			$('#trCompany').hide();
			$('#trTel').hide();
			$('#trEmail').hide();
			$('#trAddress').hide();
			$('#trMemo').hide();
		}else{
			$('#trName').show();
			$('#trCompany').show();
			$('#trTel').show();
			$('#trEmail').show();
			$('#trAddress').show();
			$('#trMemo').show();
		}
	}

	function imageError(obj){
		$(obj).attr('src', '${pageContext.request.contextPath}/sys/img/qrcode/noImg.gif');
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

			var form = document.getElementById("qrcodeForm");
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
	<form name="qrcodeForm" id="qrcodeForm">
		<input type="hidden" id="mId" name="mId" value="${curMid}"/>
		<input type="hidden" id="qrcIdx" name="qrcIdx" value="${result.qrcIdx}"/>
		<input type="hidden" id="fileName" name="fileName" value="${result.qrcPrintFileName}"/>
		<input type="hidden" id="qrcWebFileName" name="qrcWebFileName" value="${result.qrcWebFileName}"/>
		<input type="hidden" id="qrcPrintFileName" name="qrcPrintFileName" value="${result.qrcPrintFileName}"/>
		
		<p class="info"><span class="red">*</span>는 필수항목입니다.</p>
		<table class="tableSt_write">
			<caption>QR코드 관리</caption>
			<colgroup>
				<col span="1"  style="width:20%;"/>
				<col span="1"  style="width:30%;"/>
				<col span="1"  style="width:20%;"/>
				<col span="1"  style="width:30%;"/>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="qrcType">코드 타입</label></th>
					<td colspan="3">
						<select name="qrcType" id="qrcType" class="input100" onchange="changeType(this); return false;">
							<option value="namecard" <c:if test="${result.qrcType eq 'namecard' }">selected="selected"</c:if>>명함</option>
							<option value="url" <c:if test="${result.qrcType eq 'url'}">selected="selected"</c:if>>URL</option>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="title">코드제목</label></th>
					<td colspan="3">
						<input type="text" id="qrcTitle" name="qrcTitle" title="코드제목" value="${result.qrcTitle}" blankText="코드제목을 입력해 주세요." class="input500"/>
					</td>
				</tr>
				<tr id="trName">
					<th scope="row"><label for="name">이름</label></th>
					<td>
						<input type="text" id="qrcName" name="qrcName" title="이름" value="${result.qrcName}"  group="mecard"/>
					</td>
					<th scope="row"><label for="company">회사</label></th>
					<td>
						<input type="text" id="qrcCompany" name="qrcCompany" title="부서" value="${result.qrcCompany}" group="mecard"/>
					</td>
				</tr>
				<tr id="trTel">
					<th scope="row"><label for="tel">전화번호</label></th>
					<td>
						<input type="text" id="qrcTel" name="qrcTel" title="전화번호" value="${result.qrcTel}" group="mecard" />
					</td>								
					<th scope="row"><label for="email">이메일</label></th>
					<td>
						<input type="text" id="qrcEmail" name="qrcEmail" title="이메일" value="${result.qrcEmail}" group="mecard" />
					</td>								
				</tr>
				<tr id="trAddress">
					<th scope="row"><label for="address">주소</label></th>
					<td colspan="3">
						<input type="text" id="qrcAddress" name="qrcAddress" title="주소" value="${result.qrcAddress}" group="mecard" class="input400"/>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="website">웹사이트</label></th>
					<td colspan="3">
						<input type="text" id="qrcLink" name="qrcLink" title="웹사이트" value="${result.qrcLink}"group="mecard" class="input400"/>
					</td>
				</tr>
				<tr id="trMemo">
					<th scope="row"><label for="memo">메모</label></th>
					<td colspan="3">
						<textarea name="qrcMemo" id="qrcMemo" rows="5" class="input99" group="mecard">${result.qrcMemo}</textarea>
					</td>								
				</tr>
				<tr>
					<th scope="row"><label for="qrSize">QR코드 사이즈</label></th>
					<td>
						<select id="qrcSize" name="qrcSize">
							<option value="80" <c:if test="${result.qrcSize=='80'}">selected="selected"</c:if>>80</option>
							<option value="120" <c:if test="${result.qrcSize=='120'}">selected="selected"</c:if>>120</option>
							<option value="160" <c:if test="${result.qrcSize=='160'}">selected="selected"</c:if>>160</option>
							<option value="200" <c:if test="${result.qrcSize=='200'}">selected="selected"</c:if>>200</option>
							<option value="240" <c:if test="${result.qrcSize=='240'}">selected="selected"</c:if>>240</option>
							<option value="300" <c:if test="${result.qrcSize=='300'}">selected="selected"</c:if>>300</option>
						</select>
					</td>								
					<th scope="row"><label for="qrSize">QR코드 이미지</label></th>
					<td>
						<img id="qrcode_img" src="${pageContext.request.contextPath}/common/sysFile_down.jsp?fileName=${result.qrcPrintFileName}&filePath=&fileMode=QR&site=&sysFilename=${result.qrcPrintFileName}" onerror="imageError(this);"/>
					</td>								
				</tr>
			</tbody>
		</table>
		<div class="btn_boxR">
			<input id="modifyBtn" type="submit" class="btn_cyan" value="수정"/>
			<input type="button" onclick="deleteQrcodeInfo('${result.qrcIdx}'); return false;"  class="btn_dblue" value="삭제"/>
			<input type="button" onclick="history.back(); return false;" class="btn_dblue" value="취소"/>
		</div>
	</form>
