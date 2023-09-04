<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 현황 관리 목록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.05.02		J.Ryeon Lee		최초 생성 및 코드 수정
	 * 2017.06.21		권태성			목록에서 관리 URL 바로갈 수 있도록 링크 추가
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.05.02
	 */
%>
<script type="text/javascript">
//<![CDATA[

	function fn_delete(idx) {
		if (confirm("삭제하면 복구할 수 없습니다. 삭제하시겠습니까?")) {
			$("#idx").val(idx);
			$.ajax({
				type : "post",
				url : "/sys/dataMng/deleteProc.do",
				data : $("#form").serialize(),
				success : function (data) {
					var result = $.parseJSON(data);
					if (result.success) {
						alert("현황 정보를 삭제했습니다.");
						location.href = "/sys/dataMng/list.do?mId=${menuVO.mId}";
						return false;
					}

					alert("서버와 통신 중 에러가 발생했습니다.");
				}
			}).fail(function() {
				alert("서버와 통신 중 에러가 발생했습니다.");
			});

			return false;
		}
	}

	/** PAGING */
	function goPage(page) {
		var frm = document.getElementById("form");
		frm.page.value = page;
		frm.submit();
	}

//]]>
</script>

<h2>현황 관리</h2>
<form:form commandName="searchVO" name="form" id="form" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="idx" />
	<form:hidden path="page" />

	<div class="search_box">
		<div class="fL">
			<form:select path="searchType">
				<form:option value="table_desc">현황명</form:option>
				<form:option value="table_name">테이블명</form:option>
			</form:select>
			<form:input type="text" path="searchTxt" />
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색" />
		</div>
		<div class="cleB"></div>
	</div>

	<table class="tableSt_list row_over">
		<caption>항목별 상세히 안내하는 표입니다.</caption>
		<colgroup>
			<col style="width: 8%" />
			<col span="1" />
			<col style="width: 20%" />
			<col style="width: 20%" />
			<col style="width: 20%" />
			<col style="width: 12%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">현황명</th>
				<th scope="col">테이블명</th>
				<th scope="col">관리 URL</th>
				<th scope="col">사용자 URL</th>
				<th scope="col">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${listOrder - status.index}</td>
					<td>${element.tableDesc}</td>
					<td class="taL">${element.tableName}</td>
					<td class="taL"><a href="/sys/dataMng/${element.urlName}/list.do?mId=${menuVO.mId}">/sys/dataMng/${element.urlName}/list.do</a></td>
					<td class="taL">/${element.urlName}/list.do</td>
					<td>
						<a href="/sys/dataMng/modify.do?mId=${menuVO.mId}&amp;idx=${element.idx}" class="btn_swhite">보기</a>
						<a href="#" onclick="fn_delete('${element.idx}'); return false;" class="btn_swhite">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty result}">
		<div class="no_data">등록된 현황 정보가 없습니다.</div>
	</c:if>

	<c:if test="${not empty result}">
		<div class="box_page">
			<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage" />
		</div>
	</c:if>

	<div class="btn_boxR">
		<a href="/sys/dataMng/write.do?mId=${menuVO.mId}" class="btn_cyan">등록</a>
	</div>

</form:form>