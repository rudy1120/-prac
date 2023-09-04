<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 첨부파일 썸네일 태그 생성
	 *
	 * 수정일			수정자				수정내용
	 * -------------	----------------	---------------------------
	 * 2014.10.23		김혜민				최초 생성 및 코딩
	 * 2015.10.20		J.Ryeon Lee			원본 파일 view 및 width 비율 썸네일 view 추가 (mode: origin, ratio)
	 * 2016.10.20		권태성				mainType 작업
	 * 2017.10.12		J.Ryeon Lee			파일 권한 검증 로직 추가에 따른 파라미터 변경 및 사용하지 않는 모드 제거(list, listNotCn 등)
	 *
	 * @see egovframework.com.cmm.web.EgovFileMngController
	 * @author 김혜민
	 */
%>
<c:set var="cntCheck" value="0" />
<c:set var="hasImg" value="${false}" />
<c:set var="done" value="${false}" />

<c:if test="${not empty imgHeight}"><c:set var="imgHeight" value="height='${imgHeight}'" /></c:if>
<c:if test="${not empty imgWidth}"><c:set var="imgWidth" value="width='${imgWidth}'" /></c:if>

<c:if test="${not empty fileList}">
	<c:forEach var="fileVO" items="${fileList}" varStatus="status">
		<c:set var="extLowercased" value="${fn:toLowerCase(fileVO.fileExtsn)}" />
		<c:set var="isImage" value="${extLowercased == 'gif' || extLowercased == 'jpg' || extLowercased == 'png' || extLowercased == 'bmp' || extLowercased == 'jpeg'}"/>
		<c:if test="${isImage}">
			<c:choose>
				<c:when test="${not empty mainType}">
					<%-- 메인에 출력하는 이미지들은 직링크 생성 --%>
					<c:if test="${mainType eq 'visualzone'}"><c:set var="size" value="931" /></c:if>
					<c:if test="${mainType eq 'popupzone'}"><c:set var="size" value="271x145" /></c:if>
					<img src="/${mainType}/origin/${fileVO.streFileNm}_originView.${fileVO.fileExtsn}" alt="<c:if test="${showComment}">${fileVO.fileCn}</c:if>" />
					<%--
					/common/imgView.do?basePath=${fileVO.fileStreCours}
					&amp;streFileNm=${fileVO.streFileNm}
					&amp;orignlFileNm=${fileVO.orignlFileNm}
					&amp;ext=${fileVO.fileExtsn}
					&amp;mode=${mode}"
					--%>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${mode == 'origin' || mode == 'originView'}">
							<c:if test="${empty all || all || cntCheck == 0}">
								<c:if test="${not empty openTag}">
									${openTag}
								</c:if>
								<img src="/common/imgView.do?attachId=${yh:ariaEnc(fileVO.atchFileId)}&fileSn=${yh:ariaEnc(fileVO.fileSn)}&mode=${mode}" <c:if test="${not empty width }">width=${width}</c:if><c:if test="${not empty height }">height=${height}</c:if>alt="<c:if test="${showComment}">${fileVO.fileCn}</c:if>" ${imgHeight} ${imgWidth}  />
								<c:if test="${empty showComment || showComment}">
									<%-- <span class="img_subtxt">[${fileVO.fileCn}]</span> --%>
								</c:if>
								<c:if test="${not empty closeTag}">
									${closeTag}
								</c:if>
								<c:set var="cntCheck" value="1" />
								<c:set var="done" value="${true}" />
								<c:set var="hasImg" value="${true}" />
							</c:if>
						</c:when>
						<c:when test="${mode == 'ratio' || mode == 'ratioView'}">
							<c:if test="${empty all || all || cntCheck == 0}">
								<c:if test="${not empty openTag}">
									${openTag}
								</c:if>
								<img src="/common/imgView.do?attachId=${yh:ariaEnc(fileVO.atchFileId)}&fileSn=${yh:ariaEnc(fileVO.fileSn)}<c:if test="${not empty width }">&width=${width}</c:if><c:if test="${not empty height }">&height=${height}</c:if>&mode=${mode}" ${imgHeight} ${imgWidth} alt="<c:if test="${showComment}">${fileVO.fileCn}</c:if>"  />
								<c:if test="${not empty closeTag}">
									${closeTag}
								</c:if>
								<c:if test="${empty showComment || showComment}">
									<%-- <span class="img_subtxt">[${fileVO.fileCn}]</span> --%>
								</c:if>
								<c:set var="cntCheck" value="1" />
								<c:set var="done" value="${true}" />
								<c:set var="hasImg" value="${true}" />
							</c:if>
						</c:when>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${mode != 'origin' && mode != 'originView' && extLowercased == 'mp4'}">
			
			<img src="/common/jsp/common/movieThumbnailViewer.jsp?basePath=${fileVO.fileStreCours}&streFileNm=${fileVO.streFileNm}<c:if test="${not empty width }">&width=${width}</c:if>" alt="${fileVO.orignlFileNm}동영상 섬네일 이미지입니다."  />
			<c:set var="cntCheck" value="1" />
			<c:set var="done" value="${true}" />
			<c:set var="hasImg" value="${true}" />
		</c:if>

		<c:if test="${insertBr}"><br/></c:if>
	</c:forEach>
</c:if>
<c:if test="${empty fileList && noImgFlush}">
	<img <c:if test="${showComment}">alt="이미지 없음"</c:if> src="/common/img/board/sample_noimage.jpg" />
</c:if>
<c:if test="${hasImg && cntCheck == 0 && noImgFlush}">
	<img <c:if test="${showComment}">alt="이미지 없음"</c:if> src="/common/img/board/sample_noimage.jpg" />
</c:if>
