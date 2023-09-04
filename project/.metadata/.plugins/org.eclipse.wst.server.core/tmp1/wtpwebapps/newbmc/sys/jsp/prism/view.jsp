<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 관리자 정책연구용역보고서 상세화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.24		박선민			최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.24
 */
%>
<script type="text/javascript">

</script>
<h2>정책연구용역보고서</h2>
<form:form commandName="prismVO" name="list" id="list" action="/sys/prismMng/list.do?idx=${element.idx}&mId=${menuVO.mId}" method="POST">
	<form:hidden path="page"/>
	<form:hidden path="searchTxt" />
	<form:hidden path="searchType" />
 	<h2>과제정보</h2>
	<table class="tableSt_write">
<!-- 		<colgroup>
			<col span="1" class="w20" />
			<col span="1" class="w80" />
		</colgroup> -->
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="projectNm">과제명</label></th>
				<td>
					${element.projectNm}
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="orgnNm">기관명</label></th>
				<td>
					${element.orgnNm}
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="depNm">담당부서</label></th>
				<td>
					${element.depNm}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="phoneNb">전화번호</label></th>
				<td>
					${element.phoneNb}
				</td>
			</tr>
			<tr>
				<th scope="row">연구기간</th>
				<td>
					${element.rSdate} ~
					${element.rEdate}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="rField">연구분야</label></th>
				<td>
					${element.rField}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="summary">개요</label></th>
				<td>
					${element.summary}
				</td>
			</tr>
		</tbody>
	</table>	
 	<h2>계약정보</h2>
	<table class="tableSt_write">
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="executeNm">수행기관</label></th>
				<td>
					${element.executeNm}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="researcher">수행연구원</label></th>
				<td>
					${element.researcher}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="contDt">계약일자</label></th>
				<td>
					${element.contDt}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="contMethod">계약방식</label></th>
				<td>
					${element.contMethod}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="contPrice">계약금액</label></th>
				<td>
					${element.contPrice}
				</td>
			</tr>
		</tbody>
	</table>	
	<h2>연구보고서</h2>
	<table class="tableSt_write">
		<tbody>
			<tr>
				<th scope="row" class="w20"><span class="red">*</span><label for="title">제목</label></th>
				<td>
					${element.title}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="eTitle">영문제목</label></th>
				<td>
					${element.eTitle}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="subtitle">부제목</label></th>
				<td>
					${element.subtitle}
				</td>
			</tr>
			<!-- 파일첨부 -->
			<tr>
				<th scope="row"><label for="resReport">결과 보고서</label></th>
				<td>
					<c:set var="fileExts" value="${element.resReport}" scope="request"/>
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_showThumbnail" value="${true}" />
						<c:param name="updateFlag" value="N"/>
						<c:param name="param_atchFileId" value="${fileExts}"/>
					</c:import>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="topic">주제어</label></th>
				<td>
					${element.topic}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="prdtDt">제작일자</label></th>
				<td>
					${element.prdtDt}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="contributor">공헌자</label></th>
				<td>
					${element.contributor}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="issYear"><span class="red">*</span> 발행년도</label></th>
				<td>
					${element.issYear}
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="subDt">제출일</label></th>
				<td>
					${element.subDt}
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="contents">목차</label></th>
				<td>
					${element.contents}
				</td>
			</tr>
		</tbody>
	</table>	
 	<h2>연구결과 평가 및 활용보고서</h2>
	<table class="tableSt_write">
		<tbody>
			<!-- 파일첨부 -->
			<tr>
				<th scope="row" class="w20"><label for="evalResult">평가결과서</label></th>
				<td>
					<c:set var="fileExts" value="${element.evalResult}" scope="request"/>
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_showThumbnail" value="${true}" />
						<c:param name="updateFlag" value="N"/>
						<c:param name="param_atchFileId" value="${fileExts}"/>
					</c:import>
				</td>
			</tr>
			<tr>
				<th scope="row" class="w20"><label for="applReport">활용결과 보고서</label></th>
				<td>
					<c:set var="fileExts" value="${element.applReport}" scope="request"/>
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_showThumbnail" value="${true}" />
						<c:param name="updateFlag" value="N"/>
						<c:param name="param_atchFileId" value="${fileExts}"/>
					</c:import>
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<div class="btn_boxR">
	<a href="#" onclick="document.getElementById('list').submit(); return false;" title="${activeMenu.menuName} 게시글 목록 페이지로 이동" class="btn_dblue">목록</a>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
