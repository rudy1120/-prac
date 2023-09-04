<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : list.jsp
* 설       명 : 관리메뉴관리 리스트
* 작   성  자 : 엄동건
* 작   성  일 : 2014.09.
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
    $(document).ready(function(){

        $("#menuFrm").hide();
        $("#browser").treeview({
            toggle: function() {
                //console.log("%s was toggled.", $(this).find(">span").text());
            }
        });

        $("#submitBtn").click(function() {
           var options = {
               url: "${pageContext.request.contextPath}/sys/totalAdminMng/menuSysMng/writeMenuProc.do",
               type: "post",
               beforeSubmit: validateForm,
               success: resultResponse,
               dataType: 'json',
               resetForm: false
           };

           $("#menuFrm").ajaxForm(options);
        });

        function validateForm() {
            if(!$("#menuName").val()) {
                alert("메뉴명은 필수 입력항목입니다.");
                $("#menuName").focus();
                return false;
            }
        }

        function resultResponse(data) {
            if(data.flag == "success") {
                alert(data.message);
                document.location.href = "/sys/totalAdminMng/menuSysMng/menuList.do?mId=${curMid}&procMid="+data.procMid;
            } else {
                alert(data.message);
            }
        }
    });

    function showActionBox(menuType) {
        if("C" == menuType) {
            $("#cmsSelectorBox").show();
            $("#fileUrlSelectorBox").hide();
            $("#bbsSelectorBox").hide();
            $("#programSelectorBox").hide();
            $("#linkSelectorBox").hide();
        } else if("F" == menuType) {
            $("#cmsSelectorBox").hide();
            $("#fileUrlSelectorBox").show();
            $("#bbsSelectorBox").hide();
            $("#programSelectorBox").hide();
            $("#linkSelectorBox").hide();
        } else if("B" == menuType) {
            $("#cmsSelectorBox").hide();
            $("#fileUrlSelectorBox").hide();
            $("#bbsSelectorBox").show();
            $("#programSelectorBox").hide();
            $("#linkSelectorBox").hide();
        } else if("P" == menuType) {
            $("#cmsSelectorBox").hide();
            $("#fileUrlSelectorBox").hide();
            $("#bbsSelectorBox").hide();
            $("#programSelectorBox").show();
            $("#linkSelectorBox").hide();
        } else if("L" == menuType) {
            $("#cmsSelectorBox").hide();
            $("#fileUrlSelectorBox").hide();
            $("#bbsSelectorBox").hide();
            $("#programSelectorBox").hide();
            $("#linkSelectorBox").show();
        }
    }

    function showCclControlBox(type) {
        if(type == 'Y') {
            $("#cclChangeBox").show();
            $("#isCclProfitBox").show();
        } else {
            $("#cclChangeBox").hide();
            $("#isCclProfitBox").hide();
        }
    }

    function getAddMenuInfo(mId) {
        $("#menuFrm").show();
        $("#writeKind").val("insert");
        $("#menuTitle").html("메뉴등록");
        $.ajax(
                {
                    url  : "/sys/totalAdminMng/menuSysMng/getAddMenuInfo.do",
                    type : "post",
                    dataType: 'json',
                    data : {mId : mId},
                    success : function(result) {
                        if(result.flag == 'success') {
                            $("#mId").val(result.mId);
                            $("#menuLevel").val(result.menuLevel);
                            $("#menuName").val("");
                            $("input:radio[name=target][value=_self]").prop("checked","checked");
                            $("#programUrl").val("");
                            $("input:radio[name=isUse][value=Y]").prop("checked","checked");
                            $("input:radio[name=privacyDataYn]").removeAttr("chekced");
							$("input:radio[name=privacyDataYn][value=N]").prop("checked","checked");
                            $("#menuName").focus();
                        } else {
                            alert(result.message);
                        }
                    }
                }
        );
    }

    function getEditMenuInfo(mId) {
        $("#menuFrm").show();
        $("#writeKind").val("update");
        $("#menuTitle").html("메뉴수정");
        $.ajax(
                {
                    url  : "/sys/totalAdminMng/menuSysMng/getEditMenuInfo.do",
                    type : "post",
                    dataType: 'json',
                    data : {mId : mId },
                    success : function(result) {
                        if(result.flag == 'success') {
                            // 메뉴아이디
                            $("#mId").val(result.mId);
                            // 메뉴명
                            $("#menuName").val(result.menuName);

                            // 새창여부
                            var target = result.target;
                            $("input:radio[name=target][value="+target+"]").prop("checked","checked");

                            // 프로그램 URL
                            if(result.programUrl == undefined) $("#programUrl").val("");
                            else $("#programUrl").val(result.programUrl);

                            // 메뉴 활성화여부
                            var isUse = result.isUse;
                            $("input:radio[name=isUse][value="+isUse+"]").prop("checked","checked");

                         	// 개인정보취급여부
							var privacyDataYn = result.privacyDataYn;
							$("input:radio[name=privacyDataYn]").removeAttr("checked");
							$("input:radio[name=privacyDataYn][value=" + privacyDataYn + "]").prop("checked", "checked");

                    	} else {
                        	alert(result.message);
                        }
                    }
                }
        );
    }

    function deleteMenu() {
        if(confirm("[주의]이 메뉴를 정말 삭제하시겠습니까? \r\n 하위메뉴가 있을경우 하위메뉴도 함께 삭제됩니다.")) {
            $.ajax(
                    {
                        url  : "/sys/totalAdminMng/menuSysMng/deleteMenuProc.do",
                        type : "post",
                        dataType: 'json',
                        data : {mId : $("#mId").val()},
                        success : function(result) {
                            if(result.flag == "success") {
                                alert(result.message);
                                document.location.href = "/sys/totalAdminMng/menuSysMng/menuList.do?mId=${curMid}&procMid="+result.procMid;
                            } else {
                                alert(result.message);
                            }
                        }
                    });
        }
    }

    function moveMenuUp() {
        $.ajax(
                {
                    url  : "/sys/totalAdminMng/menuSysMng/moveMenuProc.do",
                    type : "post",
                    dataType: 'json',
                    data : {mId : $("#mId").val(), moveKind: 'up'},
                    success : function(result) {
                        if(result.flag == "success") {
                            alert(result.message);
                            document.location.href = "/sys/totalAdminMng/menuSysMng/menuList.do?mId=${curMid}&procMid="+result.procMid;
                        } else {
                            alert(result.message);
                        }
                    }
                });
    }

    function moveMenuDown() {
        $.ajax(
                {
                    url  : "/sys/totalAdminMng/menuSysMng/moveMenuProc.do",
                    type : "post",
                    dataType: 'json',
                    data : {mId : $("#mId").val(), moveKind: 'down'},
                    success : function(result) {
                        if(result.flag == "success") {
                            alert(result.message);
                            document.location.href = "/sys/totalAdminMng/menuSysMng/menuList.do?mId=${curMid}&procMid="+result.procMid;
                        } else {
                            alert(result.message);
                        }
                    }
                });
    }

//]]>
</script>
<h2>메뉴관리</h2>
<p class="info">최상단의 메뉴는 삭제 및 추가 하실 수 없습니다.</p>

<div id="folderBox" class="folder_box">
    <ul id="browser" class="filetree">
       <c:forEach var="depth1" items="${siteMenuList}">
           <c:set var="isOpenDepth1" value="closed"/>
           <c:if test="${fn:substring(depth1.mId,0,2) == fn:substring(procMid, 0, 2)}"><c:set var="isOpenDepth1" value="open"/></c:if>
           <li id="${depth1.mId}" class="${isOpenDepth1}">
               <c:set var="depth1Class" value="folder"/>
               <c:if test="${empty depth1.depth2MenuList}"><c:set var="depth1Class" value="file"/></c:if>
               <span class="${depth1Class}">${depth1.menuName}
                   <a href="javascript:getAddMenuInfo('${depth1.mId}')"><img src="/sys/img/treeView/registration1_add.gif" /></a>
                   <a href="javascript:getEditMenuInfo('${depth1.mId}')"><img src="/sys/img/treeView/registration1_edit.gif" /></a>
               </span>
               <c:if test="${!empty depth1.depth2MenuList}">
                   <c:forEach var="depth2" items="${depth1.depth2MenuList}">
                   <c:set var="isOpenDepth2" value="closed"/>
                   <c:if test="${fn:substring(depth2.mId,0,4) == fn:substring(procMid, 0, 4)}"><c:set var="isOpenDepth2" value="open"/></c:if>
                   <ul>
                       <li class="${isOpenDepth2}">
                           <c:set var="depth2Class" value="folder"/>
                           <c:if test="${empty depth2.depth3MenuList}">
                               <c:set var="depth2Class" value="file"/>
                           </c:if>
                           <span class="${depth2Class}">${depth2.menuName}
                               <a href="javascript:getAddMenuInfo('${depth2.mId}')"><img src="/sys/img/treeView/registration1_add.gif" /></a>
                               <a href="javascript:getEditMenuInfo('${depth2.mId}')"><img src="/sys/img/treeView/registration1_edit.gif" /></a>
                           </span>
                           <c:if test="${!empty depth2.depth3MenuList}">
                               <c:forEach var="depth3" items="${depth2.depth3MenuList}">
                                   <c:set var="isOpenDepth3" value="closed"/>
                                   <c:if test="${fn:substring(depth3.mId,0,6) == fn:substring(procMid, 0, 6)}"><c:set var="isOpenDepth3" value="open"/></c:if>
                                   <ul>
                                       <li class="${isOpenDepth3}">
                                           <c:set var="depth3Class" value="folder"/>
                                           <c:if test="${empty depth3.depth4MenuList}">
                                               <c:set var="depth3Class" value="file"/>
                                           </c:if>
                                           <span class="${depth3Class}">${depth3.menuName}
                                               <a href="javascript:getAddMenuInfo('${depth3.mId}')"><img src="/sys/img/treeView/registration1_add.gif" /></a>
                                               <a href="javascript:getEditMenuInfo('${depth3.mId}')"><img src="/sys/img/treeView/registration1_edit.gif" /></a>
                                           </span>
                                           <c:if test="${!empty depth3.depth4MenuList}">
                                               <c:forEach var="depth4" items="${depth3.depth4MenuList}">
                                                   <c:set var="isOpenDepth4" value="closed"/>
                                                   <c:if test="${fn:substring(depth4.mId,0,8) == fn:substring(procMid, 0, 8)}"><c:set var="isOpenDepth4" value="open"/></c:if>
                                                    <ul>
                                                        <li class="${isOpenDepth4}">
                                                            <c:set var="depth4Class" value="folder" />
                                                            <c:if test="${empty depth4.depth5MenuList}">
                                                                <c:set var="depth4Class" value="file"/>
                                                            </c:if>
                                                            <span class="${depth4Class}">${depth4.menuName}
                                                               <a href="javascript:getAddMenuInfo('${depth4.mId}')"><img src="/sys/img/treeView/registration1_add.gif" /></a>
                                                               <a href="javascript:getEditMenuInfo('${depth4.mId}')"><img src="/sys/img/treeView/registration1_edit.gif" /></a>
                                                            </span>
                                                            <c:if test="${!empty depth4.depth5MenuList}">
                                                                <c:forEach var="depth5" items="${depth4.depth5MenuList}">
                                                                    <c:set var="isOpenDepth5" value="closed"/>
                                                                    <c:if test="${fn:substring(depth4.mId,0,10) == fn:substring(procMid, 0, 10)}"><c:set var="isOpenDepth5" value="open"/></c:if>
                                                                    <ul>
                                                                        <li class="${isOpenDepth5}">
                                                                            <c:set var="depth5Class" value="folder" />
                                                                            <c:if test="${empty depth4.depth5MenuList}">
                                                                                <c:set var="depth5Class" value="file"/>
                                                                            </c:if>
                                                                            <span class="${depth5Class}">${depth5.menuName}
                                                                               <a href="javascript:getAddMenuInfo('${depth5.mId}')"><img src="/sys/img/treeView/registration1_add.gif" /></a>
                                                                               <a href="javascript:getEditMenuInfo('${depth5.mId}')"><img src="/sys/img/treeView/registration1_edit.gif" /></a>
                                                                            </span>
                                                                        </li>
                                                                    </ul>
                                                                </c:forEach>
                                                            </c:if>
                                                        </li>
                                                    </ul>
                                               </c:forEach>
                                           </c:if>
                                       </li>
                                   </ul>
                               </c:forEach>
                           </c:if>
                       </li>
                   </ul>
                   </c:forEach>
               </c:if>
           </li>
       </c:forEach>
    </ul>
</div>


<form name="menuFrm" id="menuFrm" action="${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/writeMenuProc.do" method="post">
    <input type="hidden" id="writeKind" name="writeKind" value=""/>
    <input type="hidden" id="menuLevel" name="menuLevel" value="0"/>
    <div class="folder_box02">
        <h3><span id="menuTitle">메뉴 등록</span></h3>
        <table class="tableSt_write">
            <caption>메뉴 관리 하기</caption>
           <colgroup>
                <col span="1" style="width:20%"/>
                <col span="1" style="width:80%"/>
            </colgroup>
            <tbody>
	            <tr>
	                <th scope="row"><label for="menuName">메뉴명</label></th>
	                <td><input type="text" name="menuName" id="menuName" class="input500"/></td>
	            </tr>
	            <tr>
	                <th scope="row"><label for="mId">메뉴ID</label></th>
	                <td><input type="text" name="mId" id="mId" readonly /></td>
	            </tr>

	            <tr>
	                <th scope="row"><label>새창여부</label></th>
	                <td>
	                    <input type="radio" name="target" id="targetS" value="_self" checked><label for="targetS">현재창</label>
	                    <input type="radio"	name="target" id="targetB" value="_blank"><label for="targetB">새창</label>
	                </td>
	            </tr>
	            <tr id="programSelectorBox">
	                <th scope="row"><label for="programUrl">프로그램 URL</label></th>
	                <td>
	                    <input type="text" name="programUrl" id="programUrl" class="input500"/>
	                </td>
	            </tr>

	            <tr>
	                <th scope="row"><label>메뉴활성화여부</label></th>
	                <td>
	                    <input type="radio" name="isUse" id="isUseY" value="Y" checked><label for="isUseY">활성화</label>
	                    <input type="radio"	name="isUse" id="isUseN" value="N"><label for="isUseN">비활성화</label>
	                </td>
	            </tr>
	            <tr>
					<th scope="row"><label>개인정보취급여부</label></th>
					<td>
						<input type="radio"	name="privacyDataYn" id="privacyDataN" value="N" checked="checked"/><label for="privacyDataN">취급안함</label>
						<input type="radio" name="privacyDataYn" id="privacyDataY" value="Y"/><label for="privacyDataY">취급</label>
					</td>
				</tr>
            </tbody>
        </table>

        <div class="btn_boxR">
            <input type="button" id="orderMoveUp" onclick="moveMenuUp()" class="btn_dblue" value="메뉴순서올림"/>
            <input type="button" id="orderMoveDown" onclick="moveMenuDown()" class="btn_dblue" value="메뉴순서내림"/>
            <input type="button" onclick="deleteMenu()" id="deleteBtn" class="btn_dblue" value="메뉴삭제"/>
            <input type="submit" id="submitBtn"  class="btn_cyan"  value="확인"/>
        </div>
    </div>
</form>
<div style="clear:both"></div>



