<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : list.jsp
* 설       명 : 직원업무관리 리스트
* 작   성  자 : 김혜민
* 작   성  일 : 2015.02.17
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[

	function fn_select_search_list(frm){
		frm.page.value = 1;
		frm.submit();
		return false;
	}

	function goPage(curPage){
		var frm = document.getElementById('listForm');
		frm.page.value = curPage;
		frm.submit();
	}

//]]>
</script>

<h2>직원업무관리</h2>

<form:form commandName="searchVO" name="listForm" id="listForm" onsubmit="return fn_select_list(document.getElementById('listForm'));" methodParam="post">
<form:hidden path="page" />

	<div class="search_box">
        <div class="fL">
       		<select name="deptCode" id="deptCode">
				<option value="" <c:if test="${searchVO.deptCode eq ''}">selected="selected"</c:if>>부서선택</option>
				<c:forEach var="code" items="${deptList}" >
                	<option value="${code.deptId}" <c:if test="${searchVO.deptCode eq code.deptId}">selected="selected"</c:if>>${code.deptName}</option>
                </c:forEach>
			</select>
			<label for="searchType" style="display:none">검색항목선택영역</label>
            <select name="searchType" id="searchType">
				<option value="" <c:if test="${searchVO.searchType eq ''}">selected="selected"</c:if>>선택하세요.</option>
				<option value="usr_nm" <c:if test="${searchVO.searchType eq 'usr_nm'}">selected="selected"</c:if>>이름</option>
				<option value="adi_info7" <c:if test="${searchVO.searchType eq 'adi_info7'}">selected="selected"</c:if>>업무내용</option>
				<option value="telno" <c:if test="${searchVO.searchType eq 'telno'}">selected="selected"</c:if>>전화번호</option>
			</select>
            <form:input type="text" path="searchTxt" value="${searchVO.searchTxt}"/>
            <input type="button" onclick="fn_select_search_list(document.getElementById('listForm')); return false;" class="btn_white" value="검색"/>
        </div>
        <div class="cleB"></div>
    </div>


    <table class="tableSt_list row_over">
		<caption>직원정보리스트</caption>
		<colgroup>
			<col span="1"  style="width:18%;"/>
			<col span="2"  style="width:13%;"/>
			<col span="1"  style="width:17%;"/>
			<col span="1" />
			<col span="1"  style="width:12%;"/>
		</colgroup>
        <thead>
			<tr>
				<th scope="col">부서</th>
				<th scope="col">직위</th>
				<th scope="col">이름</th>
				<th scope="col">전화번호</th>
				<th scope="col">업무내용</th>
				<th scope="col">새올연동일시</th>
			</tr>
		</thead>
        <tbody>
        	<c:if test="${not empty result}">
				<c:forEach var="result" items="${result}" varStatus="status">
					<tr>
						<td>${result.realUseDepNm}</td>
	                	<td>
							<c:choose>
								<c:when test="${!empty result.positNm}">${result.positNm}</c:when>
								<c:otherwise>${result.blgTeamNm}</c:otherwise>
							</c:choose>
						</td>
						<td>${result.usrNm}</td>
						<td>
						<c:choose>
							<c:when test="${empty result.telno}">
							-
							</c:when>
							<c:when test="${fn:length(result.telno) <= 4}">
							055-392-${result.telno}
							</c:when>
							<c:otherwise>
							055-${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(result.telno,'*',''),'055-',''),'(055)',''),'055)',''),'505-','')}
							</c:otherwise>
						</c:choose>
						</td>
						<td class="taL">${fn:replace(result.adiInfo7,'？','')}</td>
						<td><fmt:formatDate value="${result.dtUpdate}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
	            </c:forEach>
	        </c:if>
	         <c:if test="${empty result}">
	        	<tr>
	        		<td colspan="5">등록된 게시물이 없습니다.</td>
	        	</tr>
	        </c:if>
        </tbody>
	</table>

    <c:if test="${not empty result}">
        <div class="box_page">
            <ui:pagination paginationInfo = "${paginationInfo}" type="sys" jsFunction="goPage"/>
        </div>
    </c:if>

</form:form>
