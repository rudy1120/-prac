<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : searchDept.jsp
* 설       명 : 권한조회-부서별
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
        
        if( "${param.deptId}"!=""){
        	$("#${param.deptId}").css({"font-weight":"800"});
        }
        
        $("#browser * input[type='checkbox']").attr("disabled",true);
        

        //담당자 실시간 검색 위한 변수 선언
        deptData = $("#deptSearchResult tr");   
        
    });
    

    
    //실시간 검색
    function searchDept(){

    	var target = $("input[name='dept_nm']").val().trim();
    	var mData = deptData;
    	var searchResult1 = "";
    	var searchResult2 = "";
    	
    	var dept;
    	for(var i=0;i<mData.length;i++){
    		dept = $(mData[i]).html();		
			if( dept.search(target) > 0 && target.length>0 ){
				dept = dept.replace(/\<td/g,"<td class=\"searchData\"");
				searchResult1 += "<tr>"+dept+"</tr>";
			}
			else{
				dept = dept.replace(/td class=\"searchData\"/g,"td");  
				searchResult2 += "<tr>"+dept+"</tr>";
			}
    	}
   	
   		$("#deptSearchResult").html(searchResult1+searchResult2);
   		$("#deptList").scrollTop(0);
    }
    
    //]]>
</script>

<style>
	div#deptList,div#menuList { height:600px; border:1px solid #ddd ;overflow:auto}
	div#deptList { width:40%;float:left }
	div#menuList { width:59%;float:right }
	td.searchData { font-weight:800 }
</style>

<h2>${menuName}</h2>
<p class="info">특정부서의 메뉴사용 권한을 조회 합니다..</p>


<!-- 직원검색 -->
<div class="search_box">
    <div class="fL">
        통합검색 : <input type="text" name="dept_nm" onkeyup="searchDept()" />
    </div>

    <div class="cleB"></div>
</div>

<div id="deptList">
<table class="tableSt_list row_over">
    <caption>${menuName} 리스트</caption>
    <colgroup>
        <col span="1" style="width:15%"/>
        <col span="1" style="width:45%"/>
        <col span="1" style="width:40%"/>
    </colgroup>
    <thead>
    <tr>
        <th scope="col">번호</th>
        <th scope="col">부서명</th>
        <th scope="col">부서코드</th>
    </tr>
    </thead>
    <tbody id="deptSearchResult">
    <c:if test="${not empty resultList}">
    	<c:set var="n" value="1"/>
        <c:forEach var="result" items="${resultList}" varStatus="status">
        	<c:set var="strTab" value=""/>
        	<c:forEach begin="2" end="${result.level-1 }">
        		<c:set var="strTab" value="${strTab }<span class='dept_arrow'><img src='/sys/img/board/arrow_right.png'/></span> "/>
        	</c:forEach>
            <tr id="${result.deptId}">
            	<td>${n<10?'0':''}${n}</td>
                <td class="taL">${strTab}<a href="dept.do?mId=${param.mId}&deptId=${result.deptId}#${result.deptId}">${result.deptName}</a></td>
                <td>${result.deptId}</td> 
            </tr>
            <c:set var="n" value="${n+1}"/>
        </c:forEach>
    </c:if>
    <c:if test="${empty resultList}">
            <tr>
                <td>등록된 부서가 없습니다.</td> 
            </tr>
    </c:if>
    </tbody>
</table>
</div>

<div id="menuList">
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

