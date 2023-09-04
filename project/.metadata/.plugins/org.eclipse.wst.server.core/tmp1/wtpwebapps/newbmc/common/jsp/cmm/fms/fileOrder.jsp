<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
 /**
  * @File Name : fileOrder.jsp
  * @Description : 파일 순서 변경 화면
  * @Modification Information
  *
  * 수정일			수정자				수정내용
  * ------------	---------------		---------------------------
  * 2017.04.25		권태성				최초 생성
  * 2017.05.10		권태성				입력 폼 바인딩 함수 실행하도록 수정
  */
%>

<c:choose>
	<c:when test="${not empty type }">
		<c:if test="${not empty showThumbnail }">
			<c:set var="param_showThumbnail" value="${showThumbnail eq 'true' ? true : false }" scope="request" />
			<c:set var="param_thumbWidth" value="${thumbWidth }" scope="request" />
			<%-- 이미지만 업로드 가능하도록 --%>
			<input type="hidden" data-validator="on" data-inputType="file" />
		</c:if>
		<c:set var="param_maxFileNum" value="${fileCnt}" scope="request" />
		<c:set var="param_attachId" value="${atchFileId}" scope="request" />
		<c:set var="downloadYn" value="Y" scope="request" />
		<c:set var="orderEdit" value="Y" scope="request" />
		<jsp:include page="/sys/jsp/component/fileAttachForm.jsp" />
		<input type="hidden" name="attach_id" value="${atchFileId}" />
		<script type="text/javascript">
		//<![CDATA[
		$(document).ready(function() {
			$("#btn_${atchFileId}", opener.document).parent().parent().html("<div id=\"div_${atchFileId}\" class=\"hidden\"></div>");
			$("#div_${atchFileId}", opener.document).after($(".content_pop").clone());
			opener.parent.multiSelector();
			window.close();
		//]]>
		});
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
		$(document).ready(function() {
			$("#submitBtn").click(function() {
				var options = {
					url: "${pageContext.request.contextPath}/cmm/cms/fileOrder.do",
					type: "post",
					dataType: 'json',
					success: function(data) {
						if (data.success) {
							alert("저장되었습니다.");
							document.location.href = "${pageContext.request.contextPath}/cmm/cms/fileOrder.do?type=Y&atchFileId=${atchFileId}&showThumbnail=${showThumbnail }&thumbWidth=${thumbWidth }&fileCnt=${fileCnt}&TOKEN_KEY=<%=session.getAttribute("TOKEN_KEY")%>";
						} else {
							alert("저장 처리 중 오류가 발생하였습니다.");
							return false;
						}
					}
				};
				$("#frm").ajaxForm(options);
			});
		});
		</script>
		<div>
			<h2>파일 순서 변경</h2>
			<div class="mT10"><span class="red">※ 파일 순서는 낮을수록 상단에 표시됩니다.</span></div>
			<form id="frm" name="frm" method="post" action="/cmm/cms/fileOrder.do">
				<input type="hidden" name="attchFileId" value="${atchFileId }" />
				<input type="hidden" name="TOKEN_KEY" value="<%=session.getAttribute("TOKEN_KEY")%>" />
				<table class="tableSt_list row_over mT20">
					<caption class="hidden">
						첨부파일 목록을 번호, 이름, 파일유형, 파일순서 항목별 상세히 안내하는 표입니다.
					</caption>
					<thead>
						<tr>
							<th scope="col" class="w6">번호</th>
							<th scope="col" class="w10">이름</th>
							<th scope="col" class="w10">파일유형</th>
							<th scope="col" class="w10">파일순서</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="file" items="${fileList }" varStatus="sts">
							<tr>
								<td class="taC">${sts.count }</td>
								<td class="taC">
									${file.orignlFileNm }
								</td>
								<td class="taC">
									<c:import url="/cmm/fms/fileInfo.do" charEncoding="utf-8">
										<c:param name="attchId" value="${file.atchFileId }" />
										<c:param name="mode" value="fileView" />
										<c:param name="fileSn" value="${file.fileSn }" />
									</c:import>
								</td>
								<td class="taC">
									<input type="hidden" name="streFileNm" value="${file.streFileNm }" />
									<select id="fileSn" name="fileSn" class="input50">
										<c:forEach var="i" begin="0" end="${fn:length(fileList)-1 }">
											<option value="${i}" ${file.fileSn eq i ? 'selected="selected"' : '' }>${i}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="btn_boxR">
					<input type="submit" id="submitBtn" class="btn_blue" value="저장"/>
				</div>
			</form>
		</div>
	</c:otherwise>
</c:choose>