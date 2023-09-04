<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 로그보관주기 설정/관리
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.06.08		J.Ryeon Lee		최초 생성 및 코드 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.06.08
	 */
%>

<h2>통합 로그 보관 주기 설정/관리</h2>
<form:form commandName="searchVO" name="listForm" id="listForm" action="/sys/logging/config/list.do?mId=${menuVO.mId}" methodParam="post">
	<table class="tableSt_list row_over">
		<caption></caption>
		<thead>
			<tr>
				<th scope="col" class="wp7">번호</th>
				<th scope="col">항목</th>
				<th scope="col" class="wp15">기간 설정</th>
				<th scope="col" class="wp10">변경이력조회</th>
				<th scope="col" class="wp10">처리이력조회</th>
				<!-- <th scope="col" class="wp10">수동삭제</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="element" items="${result}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td class="taL">${element.logName}</td>
					<td>
						<select name="logPeriod_${status.index}" data-idx="${element.idx}">
							<c:forEach var="y" begin="1" end="10" step="1">
								<option value="${y}" <c:if test="${y == element.logPeriod}">selected="selected"</c:if>>${y}년</option>
							</c:forEach>
						</select>
					</td>
					<td><a href="/sys/logging/config/history/list.do?mId=${menuVO.mId}&amp;configIdx=${element.idx}" class="btn_cyan">조회</a></td>
					<td><a href="/sys/logging/history/list.do?mId=${menuVO.mId}&amp;configIdx=${element.idx}" class="btn_cyan">조회</a></td>
					<%-- <td><a href="#" class="btn_cyan" data-idx="${element.idx}">삭제</a></td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$("td > select").change(function() {
			$.ajax({
				data: {idx: $(this).attr("data-idx"), logPeriod: $(this).val()}, dataType: "json",
				url: "/sys/logging/config/modifyProc.do",
				success: function(result) { alert(result.success ? "저장되었습니다." : "처리 중 오류가 발생했습니다."); }
			}).fail(function() { alert("서버와 통신 중 오류가 발생했습니다."); });
		});

		<%--
		$("tr > td:nth-child(6) > a").click(function() {
			if (confirm("현재 세팅된 기간을 지난 데이터를 수동으로 전체 삭제합니다.\n해당 처리는 취소되지 않습니다.\n진행하시겠습니까?")) {
				$.ajax({
					data: { idx: $(this).attr("data-idx") }, dataType: "json",
					url: "/logging/deleteBatchProc.do",
					success: function(result) { alert(result.success ? "삭제되었습니다." : "처리 중 오류가 발생했습니다."); }
				}).fail(function() { alert("서버와 통신 중 오류가 발생했습니다."); });
			}
		});
		--%>
	});
</script>
