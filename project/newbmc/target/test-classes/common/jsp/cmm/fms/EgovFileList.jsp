<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
 /**
  * @Class Name : EgovFileList.jsp
  * @Description : 파일 목록화면
  * @Modification Information
  *
  * 수정일			수정자				수정내용
  * ------------	---------------		---------------------------
  * 2014.06.20		김장섭				최초 생성 및 코딩
  * 2015.11.04		J.Ryeon Lee			코멘트 입력폼 스위치 처리
  * 2016.07.29		권태성				썸네일 출력 처리 추가
  * 2016.09.01		김장섭				사이넵컨버터 처리 추가
  * 2017.03.22		권태성				downloadYn 처리 merge
  * 2017.07.03		권태성				이미지 처리 방식 변경
  * 2017.07.03		권태성				첨부파일 파라미터 암호화
  */
%>
<!-- DWR -->
<script type="text/javascript" src="<c:url value="/dwr/interface/EgovFileMngDwr.js"/>"></script>
<script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>

<script type="text/javascript" src="<c:url value='/common/js/common/EgovFileUtils.js' />"></script>
<%--
<script type="text/javascript">
	function fn_egov_downFile_pubc(atchFileId, fileSn) {
		if(window.G_CONTEXT_PATH != null) {
			window.open(G_CONTEXT_PATH + "${pageContext.request.contextPath}/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn);
		} else {
			window.open("${pageContext.request.contextPath}/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn);
		}
	}
	<c:if test="${convertYn == 'Y'}">
	function fn_egov_preview(arg0,arg1,arg2,arg3,arg4) {
		var frm = document.getElementById("synapPreviewFrm");

		frm.atchFileId.value = arg0;
		frm.fileSn.value = arg1;
		frm.streFileNm.value = arg2;
		frm.fileStreCours.value = arg3;
		frm.orignlFileNm.value = arg4;

		frm.submit();
	}
	</c:if>
//
</script>
--%>
<c:if test="${empty fileCnId }"><c:set var="fileCnYn" value="${false }" /></c:if>
<c:if test="${!empty fileList}">
	<c:if test="${orderEdit eq 'Y' }">
		<p class="mB10">
			<a href="#" id="btn_${fileList[0].atchFileId}" onclick="window.open('${pageContext.request.contextPath}/cmm/cms/fileOrder.do?atchFileId=${fileList[0].atchFileId}&showThumbnail=${showThumbnail }&thumbWidth=${thumbWidth }&thumbHeight=${thumbHeight }&fileCnt=${maxFileNum }&TOKEN_KEY=<%=request.getAttribute("TOKEN_KEY")%>', '', 'width=600, height=450, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200'); return false;" class="btn_blue">파일 순서 설정</a>
		</p>
	</c:if>
	<div id="updateFileList" class="updateFileList">
		<div class="gap"></div>
		<ul>
			<c:forEach var="fileVO" items="${fileList}" varStatus="status">
				<c:set var="isConvert" value="N"/>
				<c:set var="isImg" value="${false}"/>
				<c:set var="lowFileExtsn" value="${fn:toLowerCase(fileVO.fileExtsn)}" />
				<c:if test="${lowFileExtsn == 'png' or lowFileExtsn == 'jpg' or lowFileExtsn == 'jpeg' or lowFileExtsn == 'bmp' or lowFileExtsn == 'gif'}"><c:set var="isImg" value="${true}"/></c:if>
				<c:if test="${isImg or lowFileExtsn == 'pdf' or lowFileExtsn == 'doc' or lowFileExtsn == 'hwp' or lowFileExtsn == 'ppt' or lowFileExtsn == 'pptx' or lowFileExtsn == 'xls' or lowFileExtsn == 'xlsx' or lowFileExtsn == 'docx' or lowFileExtsn == 'txt'}"><c:set var="isConvert" value="Y"/></c:if>

				<c:choose>
					<c:when test="${updateFlag == 'Y'}">
						<li id="<c:if test="${empty fileCnId }"><c:out value="${fileVO.atchFileId}"/>-<c:out value="${fileVO.fileSn}"/></c:if>">
							<c:if test="${showThumbnail and (fn:indexOf(fn:toLowerCase(fileVO.orignlFileNm),'.png') > -1 or fn:indexOf(fn:toLowerCase(fileVO.orignlFileNm),'.jpg') > -1 or fn:indexOf(fn:toLowerCase(fileVO.orignlFileNm),'.gif') > -1 or fn:indexOf(fn:toLowerCase(fileVO.orignlFileNm),'.bmp') > -1) }">
								<%--
								* 썸네일 출력 옵션을 받은경우 해당 파일의 썸네일을 출력합니다. (해당 파일이 이미지의 경우에만)
								* 썸네일 사이즈를 입력하지 않으면 기본 사이즈 123
								--%>
								<p class="mT10 mB5">
									<img src="/common/imgView.do?attachId=${yh:ariaEnc(fileVO.atchFileId)}&fileSn=${yh:ariaEnc(fileVO.fileSn)}&width=${not empty thumbWidth ? thumbWidth : '250'}&height=${not empty thumbHeight ? thumbHeight : '100'}&mode=ratio" width="${thumbWidth}" height="&height=${thumbHeight}"/>
								</p>
							</c:if>
							<c:choose>
								<c:when test="${downloadYn eq 'Y' }">
									<span><a href="#" onclick="fn_egov_downFile('${yh:ariaEnc(fileVO.atchFileId)}','${yh:ariaEnc(fileVO.fileSn)}'); return false;">${fileVO.orignlFileNm}</a></span>&nbsp;[${fileVO.fileMgUnit}]
								</c:when>
								<c:otherwise>
									<span><c:out value="${fileVO.orignlFileNm}"/></span>&nbsp;[${fileVO.fileMgUnit}]
								</c:otherwise>
							</c:choose>
							<input type="button" onclick="fn_egov_deleteFile('${fileVO.atchFileId}','${fileVO.fileSn}'); return false;" class="btn_dele" title="파일 삭제" />
							<c:if test="${showPreviewBtn}">
								<a title="새창으로 열림" class="btn_swhite" href="#" target="_blank" onclick="fn_egov_preview('${fileVO.atchFileId}','${fileVO.fileSn}','${fileVO.streFileNm}','${fileVO.fileStreCours}','${fileVO.orignlFileNm}'); return false;"><span class="blank">바로 보기</span></a>
							</c:if>
							<c:if test="${isImg && (empty showComment || showComment)}">
								<c:if test="${empty fileCnName }">
									<c:if test="${fileCnYn }">
										<%-- fileCn에 대한 Id값을 개별적으로 줘야하는 경우에 사용 (예:현황관리 개별파일) --%>
										<c:set var="fileCnId" value="file_cn${status.index}" />
										<c:set var="fileCnName" value="file_cn" />
									</c:if>
									<c:if test="${!fileCnYn}">
										<c:set var="fileCnName" value="file_cn" />
									</c:if>
								</c:if>
								<div class="pT5"><label for="${fileCnId}">설명글</label>: <input id="${fileCnId}" type="text" name="${empty fileCnName ? fileCnId : fileCnName}" value="${fileVO.fileCn}" class="input400" /></div>
							</c:if>
						</li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${pubrYn == 'Y'}">
								<li>
									<a href="#" onclick="fn_egov_downFile_pubc('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')">
										<span><c:out value="${fileVO.orignlFileNm}"/></span>&nbsp;<c:if test="${not empty fileVO.fileMg && fileVO.fileMg != '0'}">[${fileVO.fileMgUnit}]</c:if>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<c:choose>
										<c:when test ="${showDownload eq 'Y'}">
											<a href="#" onclick="fn_egov_downFile('${yh:ariaEnc(fileVO.atchFileId)}','${yh:ariaEnc(fileVO.fileSn)}'); return false;"
											class = "showDownload">
											<span>다운로드</span>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="fn_egov_downFile('${yh:ariaEnc(fileVO.atchFileId)}','${yh:ariaEnc(fileVO.fileSn)}'); return false;">
												<span>${fileVO.orignlFileNm}</span>&nbsp;
											<c:if test="${not empty fileVO.fileMg && fileVO.fileMg != '0'}">[${fileVO.fileMgUnit}]</c:if>
										</c:otherwise>
									</c:choose>
									</a>
									
									<c:if test="${showDownload ne 'Y' && isConvert == 'Y'}">
										<c:if test="${bIdx ne '70618'}">
											<a title="새창으로 열림" href="#" target="_blank" onclick="fn_egov_preview('${fileVO.atchFileId}','${fileVO.fileSn}','${fileVO.streFileNm}','${fileVO.fileStreCours}','${fileVO.orignlFileNm}'); return false;"><span style="background-color: #026273; margin-left: 10px; padding: 5px; color: white;">미리보기</span></a>
										</c:if>
										<c:if test="${bIdx eq '70618'}">
											<c:if test="${fileVO.fileSn ne '1'}">
												<a title="새창으로 열림" href="#" target="_blank" onclick="fn_egov_preview('${fileVO.atchFileId}','${fileVO.fileSn}','${fileVO.streFileNm}','${fileVO.fileStreCours}','${fileVO.orignlFileNm}'); return false;"><span style="background-color: #026273; margin-left: 10px; padding: 5px; color: white;">미리보기</span></a>	
											</c:if>							
										</c:if>
									</c:if>
									<c:if test="${delYn == 'Y'}">
										<input type="button" onclick="fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>'); return false;" class="btn_dele" title="파일 삭제" />
									</c:if>
					 			</li>
			 				</c:otherwise>
			 			</c:choose>
			 		</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		<!-- 문서뷰어 변환 form -->
		<form name="synapPreviewFrm" id="synapPreviewFrm" action="/sn3hcvConvert.jsp" method="post" target="_blank">
			<input type="hidden" name="atchFileId" id="atchFileId" value="">
			<input type="hidden" name="fileSn" id="fileSn" value="">
			<input type="hidden" name="streFileNm" id="streFileNm" value="">
			<input type="hidden" name="fileStreCours" id="fileStreCours" value="">
			<input type="hidden" name="orignlFileNm" id="orignlFileNm" value="">
		</form>
	</div>
</c:if>

<c:if test="${fn:length(fileList) == 0}"><span class="noFile"></span></c:if>


