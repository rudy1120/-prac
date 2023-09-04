<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- ============================== 작성자 ============================== --%>
<c:set var="max" value="${fn:length(mainNum)}" />
<form:hidden path="exOrder" value="${bbsMngVO.mainOrder}" />
<tr>
	<th scope="row"><span class="red">*</span><label for="bWrite">작성자</label></th>
	<td><span style="display:inline-block;width:500px;">${bbsMngVO.bWrite}</span>&nbsp;&nbsp;
	
	<c:if test="${bbsMngVO.ptIdx == 788 or bbsMngVO.ptIdx == 768 or bbsMngVO.ptIdx == 769 or bbsMngVO.ptIdx == 801 or bbsMngVO.ptIdx == 773 or bbsMngVO.ptIdx == 770}">
		<form:checkbox path="mainNotice" id="mainNotice" value="Y" /><label for="mainNotice">메인글</label>&nbsp;&nbsp;
		<span class="tip red">주의 : 게시판의 메인화면에 게시하고 싶은 경우에만 체크해주세요 </span>
		</td>
	</c:if>
</tr>
<tr>
	<th scope="row"><span class="red">*</span><label for="bWrite">작성 관련부서</label></th>
	<td>
		<span style="display:inline-block;width:500px;">
			<input class="input200" id="rltdDeptNm" name="rltdDeptNm" value="${bbsMngVO.rltdDeptNm}"data-required="y" data-fieldname="작성 관련부서" data-validator="on" type="text" value="" maxlength="500">
			<span class="tip red">홈페이지에서 작성자로 표기됩니다.</span>
		</span>
	</td>
</tr>
<tr>
	<th scope="row"><label for="bEnquiry">문의처</label></th>
	<td>
		<span style="display:inline-block;width:500px;">
			<input class="input200" id="bEnquiry" name="bEnquiry" value="${bbsMngVO.bEnquiry}"data-required="y" data-fieldname="문의처" data-validator="off" type="text" value="" maxlength="20">
		</span>
	</td>
</tr>

