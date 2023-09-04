<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : menuListPop.jsp
* 설       명 : 관리자 메뉴권한용 리스트
* 작   성  자 : 엄동건
* 작   성  일 : 2015.01.13
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
    $(document).ready(function(){
    	$("input[type=checkbox]").click(function(){
    		if($(this).is(":checked")){
    			//상위 트리 선택
    			if($(this).closest("ul").attr("id")!="browser"){
    				var _step1=$(this).closest("ul").parent().find("span input[type=checkbox]:eq(0)").each(function(){this.checked=true;}).closest("ul");

    				if($(_step1).attr("id")!="browser"){
    					$(_step1).parent().find("span input[type=checkbox]:eq(0)").each(function(){this.checked=true;});
    				}

    			}

    			//하위 트리 선택
    			$(this).closest("li").find("span input[type=checkbox]").each(function(){this.checked=true;});
    		}else{

    			if( $(this).closest("li").siblings().find("span input[type=checkbox]:checked").length==0){

	    			//상위 트리 취소
	    			$(this).closest("ul").prev().find("span input[type=checkbox]").eq(0).attr("checked",false);
	    			if($(this).closest("ul").closest("li").siblings("li").find("span input[type=checkbox]:checked").length==0){
	    				var dept2=$(this).closest("ul");
	    				var dept1=$(dept2).parent();

	    				$(dept1).find("span input[type=checkbox]").eq(0).attr("checked",false);
	    			}
    			}

    			//하위 트리 취소
    			$(this).closest("li").find("span input[type=checkbox]").each(function(){this.checked=false;});


    		}

        });
        $("#browser").treeview({
            toggle: function() {
                //console.log("%s was toggled.", $(this).find(">span").text());
            }
        });

        $("#submitBtn").click(function() {
            var options = {
                url: "${pageContext.request.contextPath}/sys/sysMemberMng/auth/sysmenu/insertMenuAuthProc.do",
                type: "post",
                beforeSubmit: validateForm,
                success: resultResponse,
                dataType: 'json',
                resetForm: false
            };
            $("#frm").ajaxForm(options);
        });

        function validateForm() {
            var message = "해당부서 선택한 메뉴의 권한을 부여하시겠습니까?";
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

    function deleteMenuAuth() {
        var message = "[주의]해당 부서의 관리자 메뉴 접근권한을 전부 삭제하시겠습니까?";
        if(confirm(message)) {
            $.ajax({
                url  : "${pageContext.request.contextPath}/sys/sysMemberMng/auth/sysmenu/deleteMenuAuthProc.do",
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

<style>
 /* 직원개인 권한 추가 작업 20160510 손영식  */
 #folderBox {height:265px;}
 #folderBox #user {width:260px;height:260px; float:left; padding:3px;overflow: auto;border-right:1px solid #d7d7d7}
 #folderBox #user li { padding:5px 0px; border-bottom:1px dashed #d7d7d7;}
 #folderBox #user li a.on { font-weight:800;color:red }
 #folderBox #user li a { color:black }
 #folderBox #user li a:hover { color:red;cursor:pointer;font-weight:800 }

 #folderBox #browser{width:500px;height:260px; float:left; padding:3px;overflow: auto}
</style>

<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/sysMemberMng/auth/sysmenu/insertMenuAuthProc.do" method="post">
    <input type="hidden" id="deptId" name="deptId" value="${searchVO.deptId}"/>
    <input type="hidden" id="usrId"  name="usrId"  value="${searchVO.usrId}"/>
     <h2>관리자 메뉴접근권한부여 안내</h2>
    <p class="info">※ 체크박스를 선택하신 후 <span style='color:red'>권한등록</span>버튼을 클릭하시면 해당부서에  메뉴접근권한이 등록 됩니다.</p>

    <div id="folderBox"  class="folder_box_pop">

    	<!-- 20160512 손영식 -->
        <ul id="user" class="filetree">
        	<li><a class="${empty searchVO.usrId?'on':''}" href="./listPop.do?deptId=${searchVO.deptId}">부서고유권한 ${empty searchVO.usrId?'▶':''}</a></li>
    	<c:forEach var="usr" items="${usrList}">
    		<li id="${usr.usr_id}"><a class="${searchVO.usrId==usr.usr_id?'on':''}" href="./listPop.do?usrId=${usr.usr_id}&deptId=${searchVO.deptId}#${usr.usr_id}">${usr.usr_nm}(${usr.clss_nm}) ${searchVO.usrId==usr.usr_id?'▶':''}</a></li>
		</c:forEach>
    	</ul>

        <ul id="browser" class="filetree">
            <c:forEach var="depth1" items="${menuList}" varStatus="depth1Seq">
				<li class="closed">
					<span class="folder"><label for="${depth1.mId}">${depth1.menuName}</label><input type="checkbox" id="${depth1.mId}" name="mIds" value="${depth1.mId}"
                    	<c:forEach var="auths" items="${authList}">
                        	<c:if test="${auths.mId == depth1.mId}">checked</c:if>
						</c:forEach>/>
					</span>

                    <c:if test="${not empty depth1.depth2MenuList}">
                        <ul>
                            <c:forEach var="depth2" items="${depth1.depth2MenuList}">
                            	<c:set var="treeClass2" value="folder"/>
                            	<c:if test="${empty depth2.depth3MenuList}"><c:set var="treeClass2" value="file"/></c:if>

                                <li class="closed"><span class="${treeClass2 }"><label for="${depth2.mId}">${depth2.menuName}</label><input type="checkbox" id="${depth2.mId}" name="mIds" value="${depth2.mId}"
                                        <c:forEach var="auths" items="${authList}">
                                            <c:if test="${auths.mId == depth2.mId}">checked</c:if>
                                        </c:forEach>/></span>
                                <c:if test="${not empty depth2.depth3MenuList}">
                                    <ul>
                                        <c:forEach var="depth3" items="${depth2.depth3MenuList}">
                                        	<c:set var="treeClass3" value="folder"/>
                            				<c:if test="${empty depth3.depth4MenuList}"><c:set var="treeClass3" value="file"/></c:if>
                                            <li class="closed"><span class="${treeClass3 }"><label for="${depth3.mId}">${depth3.menuName}</label><input type="checkbox" id="${depth3.mId}" name="mIds" value="${depth3.mId}"
                                                <c:forEach var="auths" items="${authList}">
                                                    <c:if test="${auths.mId == depth3.mId}">checked</c:if>
                                                </c:forEach>/></span>
                                                <c:if test="${not empty depth3.depth4MenuList}">
                                    				<ul>
                                        				<c:forEach var="depth4" items="${depth3.depth4MenuList}">
                                            				<li class="closed"><span class="file"><label for="${depth4.mId}">${depth4.menuName}</label><input type="checkbox" id="${depth4.mId}" name="mIds" value="${depth4.mId}"
                                                    			<c:forEach var="auths" items="${authList}">
                                                        			<c:if test="${auths.mId == depth4.mId}">checked</c:if>
                                                    			</c:forEach>/></span></li>
                                        				</c:forEach>
                                    				</ul>
                                				</c:if>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
				</li>
            </c:forEach>
        </ul>
    </div>
    <div class="btn_boxR">
        <input type="submit" id="submitBtn" class="btn_cyan" value="권한등록"  />
        <input type="button" id="deleteBtn" onclick="deleteMenuAuth();" class="btn_dblue" value="전체권한삭제" />
    </div>
</form>

