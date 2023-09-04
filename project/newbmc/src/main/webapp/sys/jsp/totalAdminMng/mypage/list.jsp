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



    var openDialog = function(uri, name, options) {
	    var win = window.open(uri, name, options);
	};

    function getStaffInfoPop(opt, sCode, mid) {
    	var url = yh.contextPath + "/sys/totalAdminMng/menuMng/staffListChoice.do?openType=" + opt;
    	var option = "width=800, height=600, toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
    	document.getElementById('siteCode').value = sCode;
       	document.getElementById('mId').value = mid;
    	var staffWindow = window.open(url, "getStaffInfo", option);
    }

    function moveCharge () {
    	var sCode = document.getElementById('siteCode').value;
    	var mid = document.getElementById('mId').value;

    	var usrId = document.getElementById('usrId').value;
        var usrNm = document.getElementById('usrNm').value;
        var deptCode = document.getElementById('deptCode').value;
        var deptNm = document.getElementById('deptNm').value;
        var usrTel = document.getElementById('usrTel').value;

        if(usrId!="" && usrNm!="" && deptCode!="" && deptNm!="" && usrTel!="" ) {

        	$.ajax({
                url  : "/sys/myPageMng/requestProc.do",
                type : "post",
                dataType: 'json',
                data : {siteCode : sCode, mId : mid, moveChargeId : usrId, moveChargeFnm : usrNm, moveChargeDepCode : deptCode, moveChargeDepNm : deptNm, moveChargeTel : usrTel},
                success : function(result) {
                    if(result.flag == "success") {
                        alert(result.message);

                        var frm = document.getElementById('frm');
                        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/myPage/list.do?mId=${curMid}';
                        frm.submit();

                    } else {
                        alert(result.message);
                    }
                }
            });
        }
    }

    function moveChargeCancel(reqIdx) {
    	$.ajax({
            url  : "/sys/myPageMng/requestCancelProc.do",
            type : "post",
            dataType: 'json',
            data : {idx : reqIdx},
            success : function(result) {
                if(result.flag == "success") {
                    alert(result.message);

                    var frm = document.getElementById('frm');
                    frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/myPage/list.do?mId=${curMid}';
                    frm.submit();

                } else {
                    alert(result.message);
                }
            }
        });
    }
//]]>
</script>
<h2>${menuName}</h2>
<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}" method="post" onsubmit="chkFrm();">
    <input type="hidden" name="page" id="page" value="${page}" />
    <input type="hidden" name="siteCode" id="siteCode" value="" />
    <input type="hidden" name="mId" id="mId" value="" />
    <input type="hidden" name="moveChargeFnm" id="usrNm" value="" />
    <input type="hidden" name="moveChargeId" id="usrId" value=""/>
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
            <th scope="col">담당자</th>
            <th scope="col">상태</th>
            <th scope="col">인계업무</th>
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
                    <td>[${result.chargeDepNm}] ${result.chargeFnm}</td>
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
                    	<c:choose>
                    		<c:when test="${empty result.reqState}">
                    		<a href="javascript:getStaffInfoPop('onlyMyPage','${result.siteCode}','${result.mid}');" class="btn_swhite">인계하기</a>
                    		</c:when>
                    		<c:when test="${result.reqState=='C' || result.reqState=='R'}">
                    		<a href="javascript:moveChargeCancel('${result.reqIdx}');" class="btn_sblack">인계취소</a>
                    		</c:when>
                    	</c:choose>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${empty resultList}">
        <div class="no_data">
            	담당하고 있는 메뉴가 없습니다.
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
