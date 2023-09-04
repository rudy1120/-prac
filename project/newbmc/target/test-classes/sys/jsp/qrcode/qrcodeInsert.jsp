<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<script type="text/javascript">
//<![CDATA[
	$(function(){	
		// QR코드 등록 폼 submit
		$("#registerBtn").unbind("click").click(function(){
			var options = {
				url: "${pageContext.request.contextPath}/sys/qrcode/insertQrcodeDataProc.do",
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
	
		// QR코드 등록 결과 post-submit callback
		function resultResponse(data) {
			if(data.result == "success") {
				alert(data.message);
				document.location.href = '${pageContext.request.contextPath}/sys/qrcode/qrcodeInfoMng.do?mId=${curMid}';
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
//]]>
</script>

	<h2>QR코드 관리</h2>
	<form id="qrcodeForm" name="qrcodeForm">
		<input type="hidden" id="mId" name="mId" value="${curMid}"/>
		<input type="hidden" id="fileName" name="fileName" value="${fileName}"/>
		<input type="hidden" id="qrcWebFileName" name="qrcWebFileName" value="${qrcWebFileName}"/>
		<input type="hidden" id="qrcPrintFileName" name="qrcPrintFileName" value="${qrcPrintFileName}"/>
		
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
							<option value="namecard">명함</option>
							<option value="url">URL</option>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="title"><span class="red">*</span>코드제목</label></th>
					<td colspan="3">
						<input type="text" id="qrcTitle" name="qrcTitle" title="코드제목" value="" blankText="코드제목을 입력해 주세요."  class="input500"/>
					</td>
				</tr>
				<tr id="trName">
					<th scope="row"><label for="name">이름</label></th>
					<td>
						<input type="text" id="qrcName" name="qrcName" title="이름" value="" group="mecard"/>
					</td>
					<th scope="row"><label for="company">회사</label></th>
					<td>
						<input type="text" id="qrcCompany" name="qrcCompany" title="부서" value="" group="mecard"/>
					</td>
				</tr>
				<tr id="trTel">
					<th scope="row"><label for="tel">전화번호</label></th>
					<td>
						<input type="text" id="qrcTel" name="qrcTel" title="전화번호" value="" group="mecard"/>
					</td>								
					<th scope="row"><label for="email">이메일</label></th>
					<td>
						<input type="text" id="qrcEmail" name="qrcEmail" title="이메일" value="" group="mecard"/>
					</td>								
				</tr>
				<tr id="trAddress">
					<th scope="row"><label for="address">주소</label></th>
					<td colspan="3">
						<input type="text" id="qrcAddress" name="qrcAddress" title="주소" value="" group="mecard" class="input400"/>
					</td>								
				</tr>
				<tr>
					<th scope="row"><label for="website">웹사이트</label></th>
					<td colspan="3">
						<input type="text" id="qrcLink" name="qrcLink" title="웹사이트" value="" group="mecard" class="input400"/>
					</td>
				</tr>
				<tr id="trMemo">
					<th scope="row"><label for="memo">메모</label></th>
					<td colspan="3">
						<textarea name="qrcMemo" id="qrcMemo" rows="5" class="input99" group="mecard"></textarea>
					</td>								
				</tr>
				<tr>
					<th scope="row"><label for="qrSize">QR코드 사이즈</label></th>
					<td>
						<select id="qrcSize" name="qrcSize">
							<option value="80">80</option>
							<option value="120">120</option>
							<option value="160">160</option>
							<option value="200" selected="selected">200</option>
							<option value="240">240</option>
							<option value="300">300</option>
						</select>
					</td>								
					<th scope="row"><label for="qrSize">QR코드 이미지</label></th>
					<td>
						<img id="qrcode_img" src="${pageContext.request.contextPath}/sys/img/qrcode/noImg.gif" onerror="imageError(this);" />
					</td>								
				</tr>
			</tbody>
		</table>
		<div class="btn_boxR">
			<input id="registerBtn" type="submit" class="btn_cyan" value="확인"/>
			<input type="button" onclick="history.back(); return false;" class="btn_dblue" value="취소"/>
		</div>
	</form>
