<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : siteListPop.jsp
* 설       명 : 사이트권한 부여리스트
* 작   성  자 : 엄동건
* 작   성  일 : 2015.01.13
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
    $(document).ready(function(){
        $("#submitBtn").click(function() {
            var options = {
                url: "${pageContext.request.contextPath}/sys/sysMemberMng/auth/sysmenu/insertSiteAuthProc.do",
                type: "post",
                beforeSubmit: validateForm,
                success: resultResponse,
                dataType: 'json',
                resetForm: false
            };
            $("#frm").ajaxForm(options);
        });

        function validateForm() {
            var message = "해당부서 선택한 사이트 권한을 부여하시겠습니까?";
            if(confirm(message)) {

            } else return false;
        }
        function resultResponse(data) {
            if(data.flag == "success") {
                alert(data.message);
                self.close();
            } else {
                alert(data.message);
            }
        }
    });

    function deleteSiteAuth() {
        var message = "[주의]해당 부서의 관리자 사이트권한을 전부 삭제하시겠습니까?";
        if(confirm(message)) {
            $.ajax({
                url  : "${pageContext.request.contextPath}/sys/sysMemberMng/auth/sysmenu/deleteSiteAuthProc.do",
                type : "post",
                dataType: 'json',
                data : {deptId : $("#deptId").val()},
                success : function(result) {
                    if(result.flag == "success") {
                        alert(result.message);
                        self.close();
                    } else {
                        alert(result.message);
                    }
                }
            });
        }
    }
//]]>
</script>
<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/sysMemberMng/auth/sysmenu/insertSiteAuthProc.do" method="post">
    <input type="hidden" id="deptId" name="deptId" value="${deptId}"/>
    
    <h2>관리자 사이트권한부여 안내</h2>
    <p class="info">※ 체크박스를 선택하신 후 <span style='color:red'>권한등록</span>버튼을 클릭하시면 해당부서에 사이트권한이 등록 됩니다.</p>
        
    <!-- 내용시작 -->
    <table class="tableSt_list row_over">
        <caption >사이트 리스트</caption>
        <colgroup>
            <col span="1" styl="width:7%;"/>
            <col span="1"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">선택</th>
            <th scope="col">사이트명</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="site" items="${authList}"  varStatus="status">
            <tr>
            	<td>
                    <input type="checkbox" id="${site.siteCode}" name="siteCodes" value="${site.siteCode}_${site.idx}"
                    <c:forEach var="auths" items="${deptInAuthList}">
                        <c:set var="chkVal" value="${auths.siteCode}_${auths.siteIdx}"/>
                        <c:set var="oriVal" value="${site.siteCode}_${site.idx}"/>
                        <c:if test="${chkVal == oriVal}">checked</c:if>
                    </c:forEach>/>
                </td>
                <td class="taL"><label for="${site.siteCode}">${site.siteName}</label></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty authList }">
        <div class="no_data">
            조회된 데이터가 없습니다.
        </div>
    </c:if>
    <div class="btn_boxR">
        <input type="submit" id="submitBtn" class="btn_cyan" value="권한등록"  />
        <input type="button" id="deleteBtn" onclick="deleteSiteAuth();" class="btn_dblue" value="전체권한삭제" />
    </div>
</form>

