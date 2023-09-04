<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : search.jsp
* 설       명 : 권한조회-메뉴별
* 작   성  자 : 손영식
* 작   성  일 : 2016.05.16
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
    $(document).ready(function(){

        $("#browser").treeview({ persist: "location"});

    	//span.file에 클릭이벤트 20160517 손영식
    	$("ul#browser * span.file").on("click", function(){
    		location.href="menu.do?mId=${param.mId}"+"&dataMid="+this.id;
    	});

    	if("${param.dataMid}"!=""){ $("#${param.dataMid}").css({"font-weight":"800"}); }
    });

//]]>
</script>

<style>
	div#folderBox ul#browser * span { }
	div#folderBox ul#browser * span:hover { cursor:pointer; }

	div#deptTable, div#memberTable { margin-right:1%;float:right;height:600px; border:1px solid #ddd; overflow:auto; }
	div#deptTable { width:26% }
	div#memberTable { width:46% }

</style>

<h2>메뉴별 권한조회</h2>
<p class="info">특정 메뉴에 대한 권한을 보유한 부서 및 사용자를 조회 합니다.</p>

<div id="folderBox" class="folder_box">

    <ul id="browser" class="filetree">
       <c:forEach var="depth1" items="${siteMenuList}">

           <li id="${depth1.mId}" class="${fn:substring(depth1.mId,0,2) == fn:substring(param.dataMid, 0, 2)?'open':'closed'}">
               <span id="${depth1.mId}" class="${empty depth1.depth2MenuList?'file':'folder'}">${depth1.menuName}</span>

               <c:if test="${!empty depth1.depth2MenuList}">
                   <c:forEach var="depth2" items="${depth1.depth2MenuList}">
                   <ul>
                       <li class="${fn:substring(depth2.mId,0,4) == fn:substring(param.dataMid, 0, 4)?'open':'closed'}">
                           <span id="${depth2.mId}" class="${empty depth2.depth3MenuList?'file':'folder'}">${depth2.menuName}</span>

                           <c:if test="${!empty depth2.depth3MenuList}">
                               <c:forEach var="depth3" items="${depth2.depth3MenuList}">
                               		<ul>
                                       <li class="${fn:substring(depth3.mId,0,6) == fn:substring(param.dataMid, 0, 6)?'open':'closed'}">
                                           <span id="${depth3.mId}" class="${empty depth3.depth4MenuList?'file':'folder'}">${depth3.menuName}</span>

                                           <c:if test="${!empty depth3.depth4MenuList}">
                                               <c:forEach var="depth4" items="${depth3.depth4MenuList}">

                                                    <ul>
                                                        <li class="${fn:substring(depth4.mId,0,8) == fn:substring(param.dataMid, 0, 8)?'open':'closed'}">
                                                            <span id="${depth4.mId}" class="${empty depth4.depth5MenuList?'file':'folder'}">${depth4.menuName}</span>

                                                            <c:if test="${!empty depth4.depth5MenuList}">
                                                                <c:forEach var="depth5" items="${depth4.depth5MenuList}">

                                                                    <ul>
                                                                        <li class="${fn:substring(depth4.mId,0,10) == fn:substring(param.dataMid, 0, 10)?'open':'closed'}">
                                                                            <span id="${depth5.mId}" class="${empty depth4.depth5MenuList?'file':'folder'}">${depth5.menuName}</span>
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


<div id="memberTable">
    <table class="tableSt_list row_over">
        <caption>${menuName} 리스트</caption>
        <colgroup>
            <col span="1" style="width:20%;"/>
            <col span="1" style="width:30%"/>
            <col span="1" style="width:20%"/>
            <col span="1" style="width:15%"/>
            <col span="1" style="width:15%"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">담당자명</th>
            <th scope="col">직무</th>
            <th scope="col">부서명</th>
            <th scope="col">부여자</th>
            <th scope="col">부여일</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty memberList}">
            <c:forEach var="result" items="${memberList}" varStatus="status">
                <tr>
                    <td>${result.usr_nm}</td>
                    <td style="text-align:left">${result.clss_nm}</td>
                    <td>${result.real_use_dep_nm}</td>
                    <td>${result.reg_usr_id}</td>
                    <td>${result.reg_dt}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty memberList}">
                <tr>
                    <td colspan="5">권한이 부여된 담당자가 없습니다.</td>
                </tr>
        </c:if>
        </tbody>
    </table>
</div>


<div id="deptTable">

    <table class="tableSt_list row_over">
        <colgroup>
            <col span="1" style="width:60%"/>
            <col span="1" style="width:40%"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">부서명(코드)</th>
            <th scope="col">부여일</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty deptList}">
            <c:forEach var="result" items="${deptList}" varStatus="status">
                <tr>
                    <td style="text-align:left">${result.dep_code_nm}(${result.mid})</td>
                    <td>${result.reg_dt}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty deptList}">
                <tr>
                    <td colspan="2">권한이 부여된 부서가 없습니다.</td>
                </tr>
        </c:if>
        </tbody>
    </table>
</div>

<div style="clear:both"></div>



