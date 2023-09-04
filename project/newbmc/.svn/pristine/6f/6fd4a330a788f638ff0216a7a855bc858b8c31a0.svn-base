<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : list.jsp
* 설       명 : 사이트관리 리스트
* 작   성  자 : 엄동건
* 작   성  일 : 2015.01.13
*********************************************************************
--%>
<script type="text/javascript">
    //<![CDATA[
    function boardSubmit(){
        var frm = document.getElementById('frm');
        frm.page.value = 1;
        frm.action = '${pageContext.request.contextPath}/sys/sysMemberMng/auth/list.do?mId=${curMid}';
        frm.submit();
    }


    function goPage(curPage){
        var frm = document.getElementById('frm');
        frm.page.value = curPage;
        frm.action = '${pageContext.request.contextPath}/sys/sysMemberMng/auth/list.do?mId=${curMid}';
        frm.submit();
    }


    function goSysMenuAuthPop(deptId) {
        var url = "${pageContext.request.contextPath}/sys/sysMemberMng/auth/menu/listPop.do?deptId="+deptId;
        /* var option = "width=500, height=500, toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no"; */
        /* 20160512 손영식 */
        var option = "width=800, height=500, toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
        window.open(url,"goSysMenuAuthPop", option);
    }

    function goSysSiteAuthPop(deptId) {
        var url = "${pageContext.request.contextPath}/sys/sysMemberMng/auth/site/listPop.do?deptId="+deptId;
        var option = "width=460, height=600, toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
        window.open(url,"goSysSiteAuthPop", option);
    }
    //]]>
</script>

<h2>${menuName}</h2>
<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}" method="post" onsubmit="chkFrm();">
    <input type="hidden" name="page" id="page" value="${page}" />
    <div class="search_box">
        <div class="fL">
            <select name="searchType" id="searchType">
                <option value="DEP_CODE_NM" <c:if test="${searchVO.searchType == 'DEP_CODE_NM'}">selected</c:if>>부서명</option>
            </select>
            <input type="text" name="searchTxt" id="searchTxt" value="${searchVO.searchTxt}"/>
            <input type="submit" onclick="boardSubmit(); return false;" class="btn_white" value="검색"/>
        </div>

        <div class="cleB"></div>
    </div>

    <table class="tableSt_list row_over">
        <caption>${menuName} 리스트</caption>
        <colgroup>
            <col span="1" style="width:7%"/>
            <col span="1" style="width:50%"/>
            <col span="1" />
        </colgroup>
        <thead>
        <tr>
            <th scope="col">번호</th>
            <th scope="col">부서명(부서코드)</th>
            <th scope="col">권한등록</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty resultList}">
            <c:forEach var="result" items="${resultList}" varStatus="status">
            	<c:set var="strTab" value=""/>
            	<c:forEach begin="2" end="${result.level-1 }">
            		<c:set var="strTab" value="${strTab }<span class='dept_arrow'><img src='/sys/img/board/arrow_right.png'/></span>"/>
            	</c:forEach>
            	
                <tr>
                    <td>${resultCnt - result.rnum + 1 }</td>
                    <td class="taL">${strTab }${result.deptName} (${result.deptId})</td>
                    <td>
                        <a href="#" onclick="javascript:goSysSiteAuthPop('${result.deptId }');return false;" class="btn_sblack">사이트권한</a>
                        <a href="#" onclick="javascript:goSysMenuAuthPop('${result.deptId }');return false;" class="btn_sblack">메뉴접근권한</a>
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

    <c:if test="${not empty resultList}">
        <div class="box_page">
            <ui:pagination paginationInfo = "${paginationInfo}" type="sys" jsFunction="goPage"/>
        </div>
    </c:if>
</form>
