<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : list.jsp
* 설       명 : 사이트관리 리스트
* 작   성  자 : 엄동건
* 작   성  일 : 2014-10-07
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
    function boardSubmit(){
        var frm = document.getElementById('frm');
        frm.page.value = 1;
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}';
        frm.submit();
    }


    function goPage(curPage){
        var frm = document.getElementById('frm');
        frm.page.value = curPage;
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}';
        frm.submit();
    }

    function insertSite() {
        var frm = document.getElementById('frm');
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/insertPage.do?mId=${curMid}';
        frm.submit();
    }

    function modifySite(idx) {
        var frm = document.getElementById('frm');
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/modifyPage.do?idx='+idx+'&mId=${curMid}';
        frm.submit();
    }

    function deleteSite(idx) {
        var message = "[주의]해당 사이트코드를 삭제하시겠습니까?\r\n사용중인 사이트정보를 삭제하면 해당 사이트 운영에 문제가 생길 수 있습니다.";
        if(confirm(message)) {
            $.ajax({
                url  : "/sys/totalAdminMng/siteMng/deleteProc.do",
                type : "post",
                dataType: 'json',
                data : {idx : idx},
                success : function(result) {
                    if(result.flag == "success") {
                        alert(result.message);

                        var frm = document.getElementById('frm');
                        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}';
                        frm.submit();

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
<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}" method="post" onsubmit="chkFrm();">
    <input type="hidden" name="page" id="page" value="${page}" />
    <div class="search_box">
        <div class="fL">
            <select name="searchType" id="searchType">
                <option value="site_name" <c:if test="${searchVO.searchType == 'site_name'}">selected</c:if>>사이트명</option>
                <option value="site_code" <c:if test="${searchVO.searchType == 'site_code'}">selected</c:if>>사이트코드</option>
            </select>
            <input type="text" name="searchTxt" id="searchTxt" value="${searchVO.searchTxt}"/>
            <input type="submit" onclick="boardSubmit(); return false;" class="btn_white" value="검색"/>
        </div>
        <div class="cleB"></div>
    </div>

    <table class="tableSt_list row_over">
        <caption>${menuName} 리스트</caption>
        <colgroup>
            <col span="1" style="width:7%;"/>
            <col span="1" />
            <col span="1" style="width:20%;"/>
            <col span="1" style="width:20%;"/>
            <col span="1" style="width:10%;"/>
            <col span="1" style="width:8%;"/>
            <col span="1" style="width:20%;"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">번호</th>
            <th scope="col">사이트명</th>
            <th scope="col">사이트코드</th>
            <th scope="col">사이트구분</th>
            <th scope="col">사용여부</th>
            <th scope="col">정렬순서</th>
            <th scope="col">관리</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty siteList}">
            <c:forEach var="result" items="${siteList}" varStatus="status">
                <tr>
                    <td>${siteListCnt - result.rnum + 1 }</td>
                    <td>${result.siteName}</td>
                    <td>${result.siteCode}</td>
                    <td>
                    	<c:forEach var="gubunCode" items="${siteGubunList}" >
							<c:if test="${result.siteGubun eq gubunCode.code }">${gubunCode.name }</c:if>
						</c:forEach>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${result.isUse == 'Y'}">사용</c:when>
                            <c:otherwise>사용안함</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${result.siteOrder}</td>
                    <td>
                        <a href="#" onclick="javascript:modifySite('${result.idx }');return false;" class="btn_sblack">수정</a>
                        <a href="#" onclick="javascript:deleteSite('${result.idx}');return false;" class="btn_sblack">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${empty siteList}">
        <div class="no_data">
            등록된 게시물이 없습니다.
        </div>
    </c:if>

    <c:if test="${not empty siteList}">
        <div class="box_page">
            <ui:pagination paginationInfo = "${paginationInfo}" type="sys" jsFunction="goPage"/>
        </div>
    </c:if>
    
    <div class="btn_boxR">
        <a href="#" onclick="window.open('${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/sendToCMS.do', 'pop', 'width=900, height=500, scrollbars=yes, resizable=yes'); return false;" class="btn_cyan">CMS로 보내기</a> <a href="javascript:insertSite()" class="btn_cyan">등록</a>
    </div>
</form>
