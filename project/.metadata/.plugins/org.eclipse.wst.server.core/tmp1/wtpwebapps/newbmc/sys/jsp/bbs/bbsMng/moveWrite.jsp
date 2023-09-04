<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시글 이동
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.11.03		김혜민			최초 생성 및 코드 작성
	 * 2015.08.03		J.Ryeon Lee		검증 스크립트 공통화, 이동 조건 정비, 이동 게시판 금칙어 검증 추가
	 * 2017.05.15		J.Ryeon Lee		컨텐츠 입력 disabled 처리, 코드 정비
	 *
	 *
	 * @author 김현호
	 * @since 2014.11.14
	 */
%>
<h2>게시글 이동</h2>
<form:form commandName="bbsMngVO" id="list" name="list" action="/sys/${siteCode}/bbs/bbsMng/view.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="ptIdx"/>
	<form:hidden path="bIdx"/>
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchDelete"/>
	<form:hidden path="searchCategory"/>
	<form:hidden path="searchDept"/>
</form:form>
<form:form commandName="bbsMngVO" id="detailForm" name="detailForm" action="/sys/${siteCode}/bbs/bbsMng/moveWriteProc.do?mId=${menuVO.mId}" onsubmit="return additional_validator();" methodParam="post" htmlEscape="false">
	<form:hidden path="ptIdx"/>
	<form:hidden path="bIdx"/>
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchDelete"/>
	<form:hidden path="searchCategory"/>
	<form:hidden path="searchDept"/>

	<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>

	<table class="tableSt_write">
		<caption>글 이동하기</caption>
		<colgroup>
			<col span="1" style="width:20%;" />
			<col span="1" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="bTitle">제목</label></th>
				<td>${preBoard.bTitle}</td>
			</tr>
			<tr>
				<th scope="row"><label for="bWrite">작성자</label></th>
				<td>${preBoard.bWrite}</td>
			</tr>
			<tr>
				<th scope="row">등록일</th>
				<td><fmt:formatDate value="${preBoard.createDate}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td>${yh:nl2br(preBoard.bContent)}</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="aftAdIdx">이동할 게시판</label></th>
				<td>
					<c:choose>
						<c:when test="${empty configBoardList}">
							이동할 수 있는 게시판이 없습니다. 게시판 타입을 확인해주세요.
						</c:when>
						<c:otherwise>
							<select name="aftAdIdx" id="aftAdIdx" data-validator="on" data-required="y" data-fieldName="이동할 게시판">
								<c:forEach var="configBoardList" items="${configBoardList}" varStatus="status">
									<option value="${configBoardList.ptIdx}">[${configBoardList.ptIdx}] ${configBoardList.ptTitle}</option>
								</c:forEach>
							</select>
						</c:otherwise>
					</c:choose>
					<p class="tip">동일한 타입인 게시판만 표시됩니다.</p>
				</td>
			</tr>
		</tbody>
	</table>

	<p class="info red">※ 게시글에 달린 코멘트는 이동되지 않습니다.</p>

	<div class="btn_boxR">
		<input type="submit" class="btn_cyan" value="저장" />
		<a href="#" onclick="document.getElementById('list').submit();" class="btn_dblue">취소</a>
	</div>
</form:form>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/jquery/fastselect.css" />
<script type="text/javascript" src="/common/js/jquery/fastselect.standalone.js"></script>
<script type="text/javascript">
//<![CDATA[
    $(document).ready(function() {
		$("#aftAdIdx").fastselect(); // 게시판 셀렉트 디자인 적용
	});

	function additional_validator() {
		var rtn = false;
		$.ajax({
			type : "get", async: false, dataType: "json",
			url : "/sys/${siteCode}/bbs/bbsMng/forbiddenWordList.do",
			data : { ptIdx : jQuery("[name=aftAdIdx]").val() },
			success : function (result) {
				if (result.ptCheckWord) { // 규제 단어 존재
					var hit = forbiddenWordChecker(result.ptCheckWord, jQuery("#bContent").val());
					if (hit) {
						alert("[" + hit + "](은)는 선택하신 게시판에서 사용이 규제되어 있습니다.");
						jQuery("#bContent").focus();
					} else {
						rtn = true;
					}
				} else {
					rtn = true;
				}
			}
		}).fail(function() { alert("서버와 통신에 실패했습니다."); });

		return rtn;
	}

//]]>
</script>
