<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 목록 상세 (동영상)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.15		J.Ryeon Lee		컴포넌트화
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.05.15
	 */
%>

<table class="tableSt_write">
	<caption>게시글 상세</caption>
	<colgroup>
		<col span="1" style="width:10%;"/>
		<col span="1"/>
		<col span="1" style="width:10%;"/>
		<col span="1" style="width:20%;"/>
		<col span="1" style="width:10%;"/>
		<col span="1" style="width:20%;"/>
	</colgroup>
	<tbody>
		<tr>
			<th scope="col" colspan="6" class="title">
				${bbsMngView.bTitle}
			</th>
		</tr>
		<tr>
			<th scope="row">작성일</th>
			<td><fmt:formatDate value="${bbsMngView.createDate}" pattern="yyyy-MM-dd"/></td>
			<th scope="row">작성자</th>
			<c:choose>
				<c:when test="${empty bbsMngView.bPrivatecode && not empty bbsMngView.bWriteDeptNm}">
					<td>[${bbsMngView.bWriteDeptNm}] ${bbsMngView.bWrite}<c:if test="${not empty bbsMngView.bWriteDeptTel}">(${bbsMngView.bWriteDeptTel})</c:if></td>
				</c:when>
				<c:otherwise>
					<td>${bbsMngView.bWrite}</td>
				</c:otherwise>
			</c:choose>
			<th scope="row">조회수</th>
			<td>${bbsMngView.bCnt}</td>
		</tr>
		<tr>
			<td colspan="6" class="cont_box">
				<div id="movie">
					<c:if test="${not empty bbsMngView.bYoutube}">
						<iframe width="960" height="540"
							src="https://www.youtube.com/embed/${bbsMngView.bYoutube}" frameborder="0" 
							allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
							allowfullscreen>
						</iframe>
					</c:if>
					<c:if test="${empty bbsMngView.bYoutube}">
							<video poster="/video/${fn:replace(movieData.fileStreCours, '/home/webdata/egov_uploadFile/BBS/', '')}${movieData.streFileNm}.mp4" width="960" height="540" controls="controls">
								<c:choose>
									<c:when test="${fn:indexOf(movieData.streFileNm, '.') > -1 }">
										<source src="/video/${fn:replace(movieData.fileStreCours, '/home/webdata/egov_uploadFile/BBS/', '')}${fn:substring(movieData.streFileNm, 0, fn:indexOf(movieData.streFileNm, '.') ) }.mp4" type="video/mp4" />
									</c:when>
									<c:otherwise>
										<source src="/video/${fn:replace(movieData.fileStreCours, '/home/webdata/egov_uploadFile/BBS/', '')}${movieData.streFileNm}.mp4" type="video/mp4" />
									</c:otherwise>
								</c:choose>
							</video>
					</c:if>
					<div class="movie_txt taL">
						<c:set var="content" value="${fn:replace(bbsMngView.bContent, '  ', '&nbsp;&nbsp;')}"/>
						${yh:nl2br(content)}
					</div>
				</div>
			</td>
		</tr>
		<c:if test="${not empty bbsMngView.attachId }">
			<tr>
				<th scope="row">첨부 파일</th>
				<td colspan="5" class="taL">
					<c:set var="hasFile" value="${not empty bbsMngView.fileCnt && bbsMngView.fileCnt != '0'}"/>
					<c:if test="${hasFile}">
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${bbsMngView.attachId}"/>
						</c:import>
					</c:if>
					<c:if test="${!hasFile}">파일이 없습니다.</c:if>
				</td>
			</tr>
		</c:if>
	</tbody>
</table>

<c:set var="p_formId" value="viewForm" scope="request"/>
<c:set var="p_article" value="${bbsMngView}" scope="request"/>
<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/btn.jsp"/>
