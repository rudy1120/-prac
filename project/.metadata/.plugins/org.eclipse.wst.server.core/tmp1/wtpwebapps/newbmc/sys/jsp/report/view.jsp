<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 웹진 구독신청 
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2022.12.23		이정화		    최초 생성 및 코드 수정
 *
 *
 * @author 이정화
 * @since 2022.12.23
 */
%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<script type="text/javascript" charaset="UTF-8">
	
</script>

<h2>인권침해신고센터 신고 내용</h2>
<form:form commandName="reportVO" name="list" id="list" action="/sys/report/list.do?idx=${element.idx}&mId=${menuVO.mId}" method="POST">
	<form:hidden path="page"/>
	<form:hidden path="searchTxt" />
	<form:hidden path="searchType" />

	<table class="tableSt_write">
		<tbody>
			<tr>
				<th scope="row" class="w20"><label for="title">제목</label></th>
				<td>
					${result.title}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="uname">성명</label></th>
				<td>
					${result.uname}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="tel">연락처</label></th>
				<td>
					${result.tel}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="email">이메일</label></th>
				<td>
					${result.email}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="creatDate">신고 일자</label></th>
				<td>
					${result.creatDate}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="cont">신고 내용</th>
				<td>
					<pre><c:out value="${result.cont}" /></pre>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="attachId">첨부파일</label></th>
				<td>
					<c:set var="fileExts" value="${result.attachId}" scope="request"/>
					<c:if test="${!empty fileExts}">
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_showThumbnail" value="${true}" />
							<c:param name="updateFlag" value="N"/>
							<c:param name="downloadYn" value="Y"/>
							<c:param name="param_atchFileId" value="${fileExts}"/>
						</c:import>
					</c:if>
					<c:if test="${empty fileExts}">파일이 없습니다.</c:if>
				</td>
			</tr>
		</tbody>
	</table>	
</form:form>

<div class="btn_boxR">
	<a href="#" onclick="document.getElementById('list').submit(); return false;" title="${activeMenu.menuName} 게시글 목록 페이지로 이동" class="btn_dblue">목록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
