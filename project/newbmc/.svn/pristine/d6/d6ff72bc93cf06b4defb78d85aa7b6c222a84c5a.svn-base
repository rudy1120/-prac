<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : searchDept.jsp
* 설       명 : 권한조회-담당자별
* 작   성  자 : 손영식
* 작   성  일 : 2016.05.17
*********************************************************************
--%>
<script type="text/javascript">
    //<![CDATA[
    $(document).ready(function(){


        $("#browser").treeview({
            toggle: function() {
                //console.log("%s was toggled.", $(this).find(">span").text());
            }
        });

        $("#browser2").treeview({
            toggle: function() {
                //console.log("%s was toggled.", $(this).find(">span").text());
            }
        });


        //메뉴 체크박스 비활성화
        $("#browser * input[type='checkbox']").attr("disabled",true);
        $("#browser2 * input[type='checkbox']").attr("disabled",true);


        //담당자 선택시 활성화
        if( "${param.usrId}"!=""){
        	$("#${param.usrId}").css({"font-weight":"800"});
        }

        //담당자 실시간 검색 위한 변수 선언
        memberData = $("#memberSearchResult tr");

    });


    //실시간 검색
    function searchMember(){

    	var target = $("input[name='searchTxt']").val().trim();
    	var mData = memberData;
    	var searchResult1 = "";
    	var searchResult2 = "";

    	var member;
    	for(var i=0;i<mData.length;i++){
    		member = $(mData[i]).html();
			if( member.search(target) > 0 && target.length>0 ){
				member = member.replace(/\<td/g,"<td class=\"searchData\"");
				searchResult1 += "<tr>"+member+"</tr>";
			}
			else{
	    		member = member.replace("td class=\"searchData\"","td");
				searchResult2 += "<tr>"+member+"</tr>";
			}
    	}

   		$("#memberSearchResult").html(searchResult1+searchResult2);
   		$("#memberList").scrollTop(0);
    }

    function searchSubmit(usrId, deptId){
        var frm = document.getElementById('frm');
        frm.usrId.value = usrId;
        frm.deptId.value = deptId;
        frm.action = '${pageContext.request.contextPath}/sys/sysMemberMng/auth/search/member.do?mId=${curMid}#'+usrId;
        frm.submit();
    }

    //]]>
</script>

<style>
	div#memberList,div#menuList,div#deptList { height:600px; border:1px solid #ddd ;overflow:auto}
	div#memberList { width:38%;float:left }
	div#menuList,div#deptList { width:30%;float:left }
	td.searchData { font-weight:800 }
</style>

<h2>${menuName}</h2>
<p class="info">특정담당자의 메뉴사용 권한을 조회 합니다..</p>

<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/sysMemberMng/auth/search/member.do?mId=${curMid}" method="post" onsubmit="chkFrm();">
<input type="hidden" name="usrId" id="usrId" value="" />
<input type="hidden" name="deptId" id="deptId" value="" />
<!-- 직원검색 -->
<div class="search_box">
    <div class="fL">
        	<input type="text" name="searchTxt" value="${searchVO.searchTxt}"/>
        	<input type="submit" onclick="searchMember(); return false;" class="btn_white" value="검색"/>
    </div>

    <div class="cleB"></div>
</div>
</form>

<!-- 직원 목록 -->
<h2>담당자 목록</h2>
<div id="memberList">
	<table class="tableSt_list row_over">
	    <caption>${menuName} 리스트</caption>
	    <colgroup>
	        <col span="1" style="width:10%"/>
	        <col span="1" style="width:10%"/>
	        <col span="1" style="width:20%"/>
	<!--         <col span="1" style="width:20%"/> -->
	        <col span="1" style="width:60%"/>
	    </colgroup>
	    <thead>
	    <tr>
	        <th scope="col">번호</th>
	        <th scope="col">담당자명</th>
	        <th scope="col">부서명</th>
	<!--         <th scope="col">직무</th> -->
	        <th scope="col">담당업무</th>
	    </tr>
	    </thead>
	    <tbody id="memberSearchResult">
	    <c:if test="${not empty usrList}">
	    	<c:set var="n" value="1"/>
	        <c:forEach var="result" items="${usrList}" varStatus="status">
	            <tr id="${result.usr_id}">
	            	<td>${ n<1000 ? (n<100?(n<10?'000':'00') :'0') :''}${n}</td>
	                <td class="mName"><a href="#" onclick="searchSubmit('${result.usr_id}','${result.real_use_dep_code}');return false;"  href="member.do?mId=${param.mId}&usrId=${result.usr_id}#${result.usr_id}">${result.usr_nm}</a></td>
	                <td>${result.real_use_dep_nm}</td>
	<%--                 <td class="taL">${result.CLSS_NM}</td> --%>
	                <td class="taL">${result.adi_info7}</td>
	            </tr>
	            <c:set var="n" value="${n+1}"/>
	        </c:forEach>
	    </c:if>
	    <c:if test="${empty usrList}">
	            <tr>
	                <td colspan="4">등록된 담당자가 없습니다.</td>
	            </tr>
	    </c:if>
	    </tbody>
	</table>
</div>

<c:if test="${!empty searchVO.usrId}">
<div id="menuList">
		<h2>개인권한</h2>
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


<div id="deptList">
	<h2>부서권한</h2>
        <ul id="browser2" class="filetree">
            <c:forEach var="depth1" items="${menuList}" varStatus="depth1Seq">
				<li class="closed">
					<span class="folder"><label for="${depth1.mId}">${depth1.menuName}</label><input type="checkbox" id="${depth1.mId}" name="mIds" value="${depth1.mId}"
                    	<c:forEach var="auths" items="${authList2}">
                        	<c:if test="${auths.mId == depth1.mId}">checked</c:if>
						</c:forEach>/>
					</span>

                    <c:if test="${not empty depth1.depth2MenuList}">
                        <ul>
                            <c:forEach var="depth2" items="${depth1.depth2MenuList}">
                            	<c:set var="treeClass2" value="folder"/>
                            	<c:if test="${empty depth2.depth3MenuList}"><c:set var="treeClass2" value="file"/></c:if>

                                <li class="closed"><span class="${treeClass2 }"><label for="${depth2.mId}">${depth2.menuName}</label><input type="checkbox" id="${depth2.mId}" name="mIds" value="${depth2.mId}"
                                        <c:forEach var="auths" items="${authList2}">
                                            <c:if test="${auths.mId == depth2.mId}">checked</c:if>
                                        </c:forEach>/></span>
                                <c:if test="${not empty depth2.depth3MenuList}">
                                    <ul>
                                        <c:forEach var="depth3" items="${depth2.depth3MenuList}">
                                        	<c:set var="treeClass3" value="folder"/>
                            				<c:if test="${empty depth3.depth4MenuList}"><c:set var="treeClass3" value="file"/></c:if>
                                            <li class="closed"><span class="${treeClass3 }"><label for="${depth3.mId}">${depth3.menuName}</label><input type="checkbox" id="${depth3.mId}" name="mIds" value="${depth3.mId}"
                                                <c:forEach var="auths" items="${authList2}">
                                                    <c:if test="${auths.mId == depth3.mId}">checked</c:if>
                                                </c:forEach>/></span>
                                                <c:if test="${not empty depth3.depth4MenuList}">
                                    				<ul>
                                        				<c:forEach var="depth4" items="${depth3.depth4MenuList}">
                                            				<li class="closed"><span class="file"><label for="${depth4.mId}">${depth4.menuName}</label><input type="checkbox" id="${depth4.mId}" name="mIds" value="${depth4.mId}"
                                                    			<c:forEach var="auths" items="${authList2}">
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
</c:if>
