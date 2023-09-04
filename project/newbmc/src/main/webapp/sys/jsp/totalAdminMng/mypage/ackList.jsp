<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : list.jsp
* 설       명 : 마이페이지 리스트
* 작   성  자 : 김장섭
* 작   성  일 : 2016-11-20
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
    document.domain = "${yh:getProperty('document.domain')}";

    function boardSubmit(){
        var frm = document.getElementById('frm');
        frm.page.value = 1;
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}';
        frm.submit();
    }

//]]>
</script>
<h2>${menuName}</h2>
<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}" method="post" onsubmit="chkFrm();">
    <input type="hidden" name="page" id="page" value="${page}" />
    <input type="hidden" name="siteCode" id="siteCode" value="" />
    <input type="hidden" name="mId" id="mId" value="" />
    <input type="hidden" name="moveChargeFnm" id="usrNm" value="" />
    <input type="hidden" name="moveChargeId" id="usrId" value="" />
    <input type="hidden" name="moveChargeDepNm" id="deptNm" value="" />
    <input type="hidden" name="moveChargeDepCode" id="deptCode" value="" />
    <input type="hidden" name="moveChargeTel" id="usrTel" value="" />
    <!--
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
     -->
    <!--
    <div class="btn_boxL">
        <a href="javascript:moveChargeSelect();" class="btn_blue">인계하기</a>
        <a href="javascript:cancelMoveCharge();" class="btn_blue">인계취소</a>
    </div>
     -->

    <table class="tableSt_list row_over">
        <caption>${menuName} 리스트</caption>
        <colgroup>
        <!--
            <col span="1" style="width:7%;"/>
            -->
            <col span="1" style="width:10%;"/>
            <col span="1" />
            <col span="1" style="width:10%;"/>
            <col span="1" style="width:15%;"/>
            <col span="1" style="width:15%;"/>
            <col span="1" style="width:10%;"/>
            <col span="1" style="width:10%;"/>
            <col span="1" style="width:10%;"/>
        </colgroup>
        <thead>
        <tr>
         <!--
            <th scope="col"><input type="checkbox" id="" name="" /></th>
            -->
            <th scope="col">구분</th>
            <th scope="col">메뉴</th>
            <th scope="col">메뉴ID</th>
            <th scope="col">인계자</th>
            <th scope="col">인수자</th>
            <th scope="col">상태</th>
            <th scope="col">요청일</th>
            <th scope="col">인계승인일</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty resultList}">
            <c:forEach var="result" items="${resultList}" varStatus="status">
                <tr>
                <!--
                    <td><input type="checkbox" id="menuIdx${status.count}" name="menuIdx" /></td>
                     -->
                    <td>${result.siteName}</td>
                    <td class="taL"><a href="${result.url}" target="_blank">${result.menuNavi}</a></td>
                    <td>${result.mid}</td>
                    <td>[${result.reqChargeDepNm}] ${result.reqChargeFnm}</td>
                    <td>[${result.moveChargeDepNm}] ${result.moveChargeFnm}</td>
                    <td>
                    	<c:choose>
                    		<c:when test="${empty result.reqState}">
                    		관리중
                    		</c:when>
                    		<c:when test="${result.reqState=='R'}">
                    		인계승인대기
                    		</c:when>
                    		<c:when test="${result.reqState=='C'}">
                    		인계승인취소
                    		</c:when>
                    		<c:when test="${result.reqState=='A'}">
                    		인계완료
                    		</c:when>
                    	</c:choose>
                    </td>
                    <td>
                    	<fmt:formatDate value="${result.reqDt}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                    	<fmt:formatDate value="${result.ackDt}" pattern="yyyy-MM-dd"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${empty resultList}">
        <div class="no_data">
           	 인계결과가 없습니다.
        </div>
    </c:if>

    <c:if test="${not empty resultList}">
    <!--
        <div class="box_page">
            <ui:pagination paginationInfo = "${paginationInfo}" type="sys" jsFunction="goPage"/>
        </div>
     -->
    </c:if>


</form>
