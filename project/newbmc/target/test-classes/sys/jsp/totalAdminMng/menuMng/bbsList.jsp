<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
 /*
  * @Class Name : list.jsp
  * @Description : 게시판 관리 공통화면
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2014-01-25	박동환          최초 생성
  * @ ------------------------------
  */
%>
<c:set var="domain" value="${yh:isLiveMode() ? yh:getProperty('document.domain') : 'localhost' }" />
<script type="text/javascript">
//<![CDATA[
function setBbs(bbsMstIdx, bbsName) {
	try {
		document.domain = "${domain}";
	} catch (e) {
		alert("document.domain 세팅이 되어있지 않습니다. 세팅을 확인하세요.");
	}
	opener.document.getElementById("bbsMstIdx").value= bbsMstIdx;
	$("#bbsName", opener.document).html(bbsName);
	window.close();
}
//]]>
</script>
<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/bbsMng/auth.do" method="post">
     <h2>게시판 연결안내</h2>
     <p class="info">본 목록에는 메뉴에 연결되어 있지 않은 게시판만 출력됩니다.</p>

    <!-- 내용시작 -->
    <table class="tableSt_list row_over">
        <caption >게시판 리스트</caption>
        <colgroup>
             <col span="1" style="width:15%"/>
             <col span="1" style=""/>
             <col span="1" style="width:15%;"/>
         </colgroup>
        <thead>
            <tr>
                <th scope="col">번호</th>
                <th scope="col">게시판명</th>
                <th scope="col">선택</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${menuNotInBbsList }" var="bbsList" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td class="taL">${bbsList.bbsName}</td>
                    <td><a href="javascript:setBbs('${bbsList.bbsMstIdx}', '${bbsList.bbsName}');" class="btn_sblack"> 선택</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty menuNotInBbsList }">
        <div class="no_data">
            등록된 게시물이 없습니다.
        </div>
    </c:if>
</form>
