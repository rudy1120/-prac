<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황 정보 엑셀 업로드
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.03.03		김혜민			최초 생성 및 코드 작성
	 * 2016.05.09		J.Ryeon Lee		[동적 현황 정보] 기능에 맞춰 내용 변경
	 * 2016.11.22		권태성			엑셀 업로드 옵션 추가
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.10.13
	 */
%>

<script type="text/javascript">
//<![CDATA[

	$(function() {
		// 태그 등록 폼 submit
		$("#registerBtn").click(function() {

			var msg = "";
			var mode = $(':radio[name="mode"]:checked').val();
			if (mode == "copy") {
				msg = "기존 데이터를 전체 삭제 후 파일 내용을 등록합니다. 파일을 업로드 하시겠습니까?";
			} else {
				msg = "파일을 업로드 하시겠습니까?";
			}

			if (confirm(msg)) {
				var options = {
					url: "/sys/dataMng/${urlName}/excelUpload.do",
					type: "post",
					beforeSubmit: validateForm,
					success: resultResponse,
					dataType: "json",
					resetForm: false
				};

				$("#xlsUploadFrm").ajaxForm(options);
			}
		});

		// 태그 등록 폼 Validation pre-submit callback
		function validateForm() {
			var form = document.xlsUploadFrm;

			if (!form.excelFile.value) {
				alert("파일을 선택하여 주십시오.");
				return false;
			} else if (!checkFileType(form.excelFile.value)) {
				alert("확장자가 xls인 파일만 업로드 가능합니다.");
				return false;
			}

			$("#registerBtn").attr("disabled", "disabled");
			$("#excelFile").css("display", "none");
			$("#progressBar").show();
		}

		// 태그 등록 결과 post-submit callback
		function resultResponse(data) {
			if (data.cnt > 0 && data.success) {
				alert(data.cnt + "개의 행을 입력했습니다.");
				$("#registerBtn").attr("disabled", "");
				$("#excelFile").css("display", "");
				$("#progressBar").hide();
				opener.location.href="/sys/dataMng/${urlName}/list.do?mId=${mId}";
				self.close();
			} else {
				alert("데이터 등록 중 에러가 발생했습니다. 진행된 처리를 롤백합니다.");
				$("#registerBtn").attr("disabled", "");
				$("#excelFile").css("display", "");
				$("#progressBar").hide();
			}
		}
	});


	// 파일 타입 확인 후 xls 파일만 업로드 가능하도록 한다.
	function checkFileType(filePath) {
		var fileLen = filePath.length;
		var fileFormat = filePath.substring(filePath.lastIndexOf("."), fileLen);
		fileFormat = fileFormat.toLowerCase();

		return fileFormat == ".xls";
	}

//]]>
</script>

<c:set var="p_lang" value="${lang}" scope="request" />

<h2>엑셀 업로드</h2>
<p class="info" style="margin-top:10px;"><span class="red">엑셀 파일의 모든 값은 텍스트로 설정되어 있어야 합니다. <br/>(덮어쓰기를 하면 기존 데이터가 모두 삭제됩니다.)</span></p>
<form:form commandName="searchVO" id="xlsUploadFrm" name="xlsUploadFrm">
	<table class="tableSt_write">
		<caption>엑셀 업로드</caption>
		<tbody>
			<tr>
				<th scope="row" class="w20">양식다운로드</th>
				<td>
					<a href="/sys/dataMng/${urlName}/dynamicDataDownloader.do?format=Y" class="btn_white"><span class="wicon_down"></span>업로드양식 다운로드</a><br>
				</td>
			</tr>
			<tr>
				<th scope="row">등록방식</th>
				<td>
					<input type="radio" id="mode2" name="mode" checked="checked" value="append" />&nbsp;<label for="mode2">추가하기</label>
					<input type="radio" id="mode1" name="mode" value="copy" />&nbsp;<label for="mode1">덮어쓰기</label>&nbsp;
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="excelFile">파일선택</label></th>
				<td>
					<input type="file" id="excelFile" name="excelFile" class="input300" />
					<div id="progressBar" style="display: none;">
						<img src="/sys/img/loading.gif" width="220" height="19" alt="Loading" />
						<p class="mL10">업로드 중입니다.</p>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="btn_boxR">
		<input type="submit" id="registerBtn" class="btn_cyan"  value="등록" />
	</div>

</form:form>
