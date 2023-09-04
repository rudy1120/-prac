<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : list.jsp
* 설       명 : 접근허용 리스트
* 작   성  자 : 엄동건
* 작   성  일 : 2015.01.15
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
    function boardSubmit(){
        var frm = document.getElementById('frm');
        frm.page.value = 1;
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/list.do?mId=${curMid}';
        frm.submit();
    }


    function goPage(curPage){
        var frm = document.getElementById('frm');
        frm.page.value = curPage;
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/list.do?mId=${curMid}';
        frm.submit();
    }

    function insertAccessAllow() {
        var frm = document.getElementById('frm');
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/insertPage.do?mId=${curMid}';
        frm.submit();
    }

    function modifyAccessAllow(seq) {
        var frm = document.getElementById('frm');
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/modifyPage.do?seq='+seq+'&mId=${curMid}';
        frm.submit();
    }

    function deleteAccessAllow(seq) {
        var message = "[주의]해당 접근허용 아이피를 삭제하시겠습니까?";
        if(confirm(message)) {
            $.ajax({
                url  : "${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/deleteProc.do",
                type : "post",
                dataType: 'json',
                data : {seq : seq},
                success : function(result) {
                    if(result.flag == "success") {
                        alert(result.message);
                        location.href = "${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/list.do?mId=${curMid}";
                    } else {
                        alert(result.message);
                    }
                }
            });
        }

    }
//]]>
</script>
<h2>${menuName}</h2>
<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/list.do?mId=${curMid}" method="post">
    <div class="search_box">
        <div class="fL">
            <select name="searchType" id="searchType">
                <option value="ip" <c:if test="${searchVO.searchType == 'ip'}">selected</c:if>>아이피</option>
            </select>
            <input type="text" name="searchTxt" id="searchTxt" value="${searchVO.searchTxt}"/>
            <input type="submit" onclick="boardSubmit(); return false;" class="btn_white" value="검색"/>
        </div>

        <div class="cleB"></div>
    </div>

    <table class="tableSt_list row_over">
        <caption>${menuName} 리스트</caption>
        <colgroup>
            <col span="1" style="width:5%"/>
            <col span="1" style="width:20%"/>
            <col span="1"/>
            <col span="1" style="width:15%"/>
            <col span="1" style="width:10%"/>
            <col span="1" style="width:10%"/>
            <col span="1" style="width:10%"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">번호</th>
            <th scope="col">접근명</th>
            <th scope="col">아이피 및 대역</th>
            <th scope="col">종류</th>
            <th scope="col">접근기간</th>
            <th scope="col">접근여부</th>
            <th scope="col">관리</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty resultList}">
            <c:forEach var="result" items="${resultList}" varStatus="status">
                <tr>
                    <td>${totalCnt - result.rnum + 1 }</td>
                    <td class="taL">${result.title}</td>
                    <td class="taL">
                        <c:choose>
                            <c:when test="${result.type eq 'A'}">${result.ipBandA}~${result.ipBandB}</c:when>
                            <c:when test="${result.type eq 'B'}">${result.ip}</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${result.type eq 'A'}">대역설정</c:when>
                            <c:when test="${result.type eq 'B'}">개별아이피</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${result.allowDateUseYn eq 'Y'}">
                                <fmt:formatDate value="${result.allowDateSt}" pattern="yyyy-MM-dd" />~<fmt:formatDate value="${result.allowDateEnd}" pattern="yyyy-MM-dd" />
                            </c:when>
                            <c:when test="${result.allowDateUseYn eq 'N'}">무제한</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${result.useYn == 'Y'}">허가</c:when>
                            <c:otherwise>차단</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="#" onclick="javascript:modifyAccessAllow('${result.seq }');return false;" class="btn_sblack">수정</a>
                        <a href="#" onclick="javascript:deleteAccessAllow('${result.seq}');return false;" class="btn_sblack">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${empty resultList}">
        <div class="no_data">
            조회된 데이터가 없습니다.
        </div>
    </c:if>

    <div class="btn_boxR">
        <a href="javascript:insertAccessAllow()" class="btn_cyan">등록</a>
    </div>
</form>
