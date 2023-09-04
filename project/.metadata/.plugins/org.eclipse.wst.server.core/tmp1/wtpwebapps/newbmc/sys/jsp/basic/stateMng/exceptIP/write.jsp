<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : write.jsp
* 설       명 : 통계 제외 IP 등록화면
* 작   성  자 : 김장섭
* 작   성  일 : 2018.10.11
*********************************************************************
--%>
<script type="text/javascript">
    //<![CDATA[
    $(document).ready(function(){
        showActionBox('A');

        $("#submitBtn").click(function() {
            var options = {
                url: "${pageContext.request.contextPath}/sys/stateExceptIPMng/writeProc.do",
                type: "post",
                beforeSubmit: validateForm,
                success: resultResponse,
                dataType: 'json',
                resetForm: false
            };

            $("#frm").ajaxForm(options);
        });

        function validateForm() {
            if(!$("#title").val()) {
                alert("설명은 필수 입력항목입니다.");
                $("#title").focus();
                return false;
            } else if($("input[name='type']:radio:checked").val() == "A") {
                if(!$("#ipBandA").val() || !$("#ipBandB").val()) {
                    if(!$("#ipBandA").val()) {
                        $("ipBandA").focus();
                    }

                    if(!$("#ipBandB").val()) {
                        $("ipBandB").focus();
                    }

                    alert("아이피 대역정보는 필수 입력항목입니다.");
                    return false;
                }
            } else if($("input[name='type']:radio:checked").val() == "B") {
                if(!$("#ip").val()) {
                    alert("아이피는 필수 입력항목입니다.");
                    return false;
                }
            }
        }

        function resultResponse(data) {
            if(data.flag == "success") {
                alert(data.message);
                goList();
            } else {
                alert(data.message);
            }
        }
    });

    function goList(){
        var frm = document.getElementById('frm');
        frm.action = '${pageContext.request.contextPath}/sys/stateExceptIPMng/list.do';
        frm.submit();
    }

    function showActionBox(type) {
        if(type == "A") {
            $("#ipTypeB").hide();
            $("#ipTypeA").show();
        } else {
            $("#ipTypeA").hide();
            $("#ipTypeB").show();
        }
    }

    //]]>
</script>
<!-- 내용시작 -->
<h2>${menuName}</h2>
<form id="frm" name="frm"  method="post" action="${pageContext.request.contextPath}/sys/stateExceptIPMng/writeProc.do">
    <table class="tableSt_write">
        <input type="hidden" name="mId" id="mId" value="${curMid}" />
        <caption>제외 아이피 등록</caption>
         <colgroup>
            <col span="1" style="width:20%;"/>
            <col span="1" style="width:80%;"/>
        </colgroup>
        <tbody>
        <tr>
            <th scope="row">제외명</th>
            <td><input type="text" id="title" name="title" class="input300" maxlength="50" title="접근명" value=""/></td>
        </tr>
        <tr>
            <th scope="row">종류</th>
            <td>
                <input type="radio" id="typeA" name="type" value="A" onclick="showActionBox('A');" checked/><label for="typeA">대역</label>
                <input type="radio" id="typeB" name="type" value="B" onclick="showActionBox('B');"/><label for="typeB">개별</label>
            </td>
        </tr>
        <tr>
            <th scope="row">아이피 및 대역</th>
            <td>
                <div id="ipTypeA">
                    <input type="text" id="ipBandA" name="ipBandA" class="input100" maxlength="20" title="아이피대역 첫번째 자리" value="" /> ~ <input type="text" id="ipBandB" name="ipBandB" class="input100" maxlength="20" title="아이피대역 두번째 자리" value="" />
                </div>
                <div id="ipTypeB">
                    <input type="text" id="ip"  name="ip"  maxlength="20" title="아이피주소" value=""/>
                </div>
            </td>
        </tr>
        <tr>
            <th scope="row">제외여부</th>
            <td>
                <input type="radio" id="useY" name="useYn" value="Y" checked /><label for="useY">제외</label>
                <input type="radio" id="useN" name="useYn" value="N" /><label for="useN">미제외</label>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="btn_boxR">
        <input type="submit" id="submitBtn" value="등록"  class="btn_cyan"/>
        <input type="button" onclick="document.location.href='${pageContext.request.contextPath}/sys/stateExceptIPMng/list.do?mId=${curMid}';" value="취소" class="btn_dblue"/>
    </div>
</form>