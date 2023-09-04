<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
//<![CDATA[
//]]>
</script>

	<h2>QR코드 관리</h2>
	<form:form name="frm" id="frm" commandName="registFrm" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" name="mId" id="mId" value="${curMid}"/>
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
					<th scope="col" colspan="4" class="title">${result.qrcTitle}</th>
				</tr>
				<tr>
					<th scope="row"><label for="name">이름</label></th>
					<td>${result.qrcName}</td>
					<th scope="row"><label for="company">회사</label></th>
					<td>${result.qrcCompany}</td>
				</tr>
				<tr>
					<th scope="row"><label for="tel">전화번호</label></th>
					<td>${result.qrcTel}</td>
					<th scope="row"><label for="email">이메일</label></th>
					<td>${result.qrcEmail}</td>
				</tr>
				<tr>
					<th scope="row"><label for="address">주소</label></th>
					<td colspan="3">${result.qrcAddress}</td>
				</tr>
				<tr>
					<th scope="row"><label for="website">웹사이트</label></th>
					<td colspan="3">${result.qrcLink}</td>
				</tr>
				<tr>
					<th scope="row"><label for="memo">메모</label></th>
					<td colspan="3">${result.qrcMemo}</td>
				</tr>
				<tr>
					<th scope="row"><label for="qrSize">QR코드 사이즈</label></th>
					<td>${result.qrcSize}</td>
					<th scope="row"><label for="qrSize">QR코드 이미지</label></th>
					<td>
						<img id="qrcode_img" src="/common/sysFile_down.jsp?fileName=${result.qrcPrintFileName}&filePath=&fileMode=QR&site=&sysFilename=${result.qrcPrintFileName}" onerror="imageError(this);"/>
<%-- 						<img src="${pageContext.request.contextPath}/FileDown_direct.do?path=/qrcode/&file=${result.qrcPrintFileName}" width="${result.qrcSize}" height="${result.qrcSize}"/> --%>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_boxR">
			<a href="${pageContext.request.contextPath}/sys/qrcode/moveQrcodeDataUpdatePage.do?mId=${curMid}&amp;qrcIdx=${result.qrcIdx}" class="btn_cyan">수정</a>
			<a href="#" onclick="history.back(); return false;" class="btn_dblue">목록</a>
		</div>
	</form:form>
