<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : list.jsp
* 설       명 : 접근허용 등록화면
* 작   성  자 : 엄동건
* 작   성  일 : 2015.01.15
*********************************************************************
--%>
<script type="text/javascript">
    /** jquery datepicker 형태를 한글로 한다.*/
    jQuery(function($){
        $.datepicker.regional['ko'] = {
            closeText: '닫기',
            prevText: '이전',
            nextText: '다음',
            currentText: '오늘',
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
            dayNames: ['일','월','화','수','목','금','토'],
            dayNamesShort: ['일','월','화','수','목','금','토'],
            dayNamesMin: ['일','월','화','수','목','금','토'],
            weekHeader: 'Wk',
            dateFormat: 'yy-mm-dd',
            firstDay: 0,
            isRTL: false,
            showMonthAfterYear: true,
            yearSuffix: '년'
        };
        $.datepicker.setDefaults($.datepicker.regional['ko']);
    });
</script>
<script type="text/javascript">
    //<![CDATA[
    $(document).ready(function(){
        showActionBox('A');
        $( "#allowDateStS" ).datepicker();
        $( "#allowDateEndS" ).datepicker();

        $("#submitBtn").click(function() {
            var options = {
                url: "${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/insertProc.do",
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
            } else if($(":radio[name='allowDateUseYn']:checked").val() == "Y") {
                if(!$("#allowDateStS").val() || !$("#allowDateEndS").val()) {
                    if(!$("#allowDateStS").val()) {
                        $("allowDateStS").focus();
                    }

                    if(!$("#allowDateEndS").val()) {
                        $("allowDateEndS").focus();
                    }

                    alert("허용기간은 필수 입력항목입니다.");
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
        frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/list.do';
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

    function showAllowDateBox(useYn) {
        if(useYn == "Y") {
            $("#allowDate").show();
        } else {
            $("#allowDate").hide();
        }
    }
    //]]>
</script>
<!-- 내용시작 -->
<h2>${menuName}</h2>
<form id="frm" name="frm"  method="post" action="${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/insertProc.do">
    <table class="tableSt_write">
        <input type="hidden" name="mId" id="mId" value="${curMid}" />
        <caption>접근허용 아이피 등록</caption>
         <colgroup>
            <col span="1" style="width:20%;"/>
            <col span="1" style="width:80%;"/>
        </colgroup>
        <tbody>
        <tr>
            <th scope="row">접근명</th>
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
            <th scope="row">접근기간</th>
            <td>
                <input type="radio" id="allowDateUseY" name="allowDateUseYn" value="Y" onclick="showAllowDateBox('Y');" checked/><label for="allowDateUseY">사용</label>
                <input type="radio" id="allowDateUseN" name="allowDateUseYn" value="N" onclick="showAllowDateBox('N');"/><label for="allowDateUseN">무제한</label>
                <div id="allowDate" class="mT10">
                    <input type="text" id="allowDateStS" name="allowDateStS" class="input100"/> ~ <input type="text" id="allowDateEndS" name="allowDateEndS" class="input100"/>
                </div>

            </td>
        </tr>
        <tr>
            <th scope="row">접근여부</th>
            <td>
                <input type="radio" id="useY" name="useYn" value="Y" checked /><label for="useY">허가</label>
                <input type="radio" id="useN" name="useYn" value="N" /><label for="useN">차단</label>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="btn_boxR">
        <input type="submit" id="submitBtn" value="등록"  class="btn_cyan"/>
        <input type="button" onclick="document.location.href='${pageContext.request.contextPath}/sys/totalAdminMng/siteAccessMng/list.do?mId=${curMid}';" value="취소" class="btn_dblue"/>
    </div>
</form>