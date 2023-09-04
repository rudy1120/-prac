<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파  일  명 : checkList.jsp
* 설      명 : 메뉴점검목록관리 리스트
* 작  성  자 : 김장섭
* 작  성  일 : 2016.06.28
*********************************************************************
--%>
<%
//대표(메인/열린시장실)
LinkedHashMap map_main=new LinkedHashMap();
map_main.put("portal", "대표포털");
map_main.put("mayor", "열린시장실");
pageContext.setAttribute("map_main", map_main);

//읍면동 코드맵
LinkedHashMap map_csc = new LinkedHashMap();
pageContext.setAttribute("map_csc", map_csc);

//부서/관리사무소 및 빌트인
LinkedHashMap map_dept=new LinkedHashMap();
pageContext.setAttribute("map_dept", map_dept);

LinkedHashMap map_etc=new LinkedHashMap();
pageContext.setAttribute("map_etc", map_etc);
%>
<c:set var="domain" value="${yh:isLiveMode() eq true ? yh:getProperty('document.domain') : 'localhost' }" />
<script type="text/javascript">
//CMS연동시 관련도메인으로 변경  CMS도메인과 관리도메인이 동일하여야함(단, 서브도메인은 제외)
try {
	document.domain = "${domain}";
} catch (e) {
	alert("document.domain 세팅이 되어있지 않습니다. 세팅을 확인하세요.");
}

$(document).ready(function(){
	var map_all = new Object(); // 대표(메인/열린시장실)
	var map_main = new Object(); // 대표(메인/열린시장실)
	var map_dept = new Object(); // 부서및빌트인
	var map_csc = new Object(); // 읍면동
	var map_etc = new Object(); // 기타


	<c:forEach var="el" items="${menusMngList}">
		map_all["${el.siteCode}"]= "${el.siteName}";
    </c:forEach>
	<c:forEach var="el" items="${map_main}">
		<c:forEach var="site" items="${menusMngList}">
			<c:if test="${site.siteCode eq el.key}">
				map_main["${el.key}"]= "${el.value}";
			</c:if>
        </c:forEach>
	</c:forEach>
	<c:forEach var="el" items="${map_dept}">
		<c:forEach var="site" items="${menusMngList}">
			<c:if test="${site.siteCode eq el.key}">
				map_dept["${el.key}"]="${el.value}";
			</c:if>
        </c:forEach>
	</c:forEach>
	<c:forEach var="el" items="${map_csc}">
		<c:forEach var="site" items="${menusMngList}">
			<c:if test="${site.siteCode eq el.key}">
				map_csc["${el.key}"]= "${el.value}";
			</c:if>
		</c:forEach>
	</c:forEach>
	<c:forEach var="el" items="${map_etc}">
		<c:forEach var="site" items="${menusMngList}">
			<c:if test="${site.siteCode eq el.key}">
				map_etc["${el.key}"]= "${el.value}";
			</c:if>
		</c:forEach>
	</c:forEach>



	var arr_all = $.map(map_all,function(v,k){
		return k;
	});
	var arr_main = $.map(map_main,function(v,k){
		return k;
	});
	var arr_dept = $.map(map_dept,function(v,k){
		return k;
	});
	var arr_csc = $.map(map_csc,function(v,k){
		return k;
	});
	var arr_etc = $.map(map_etc,function(v,k){
		return k;
	});

	$("input[name=menu_category]").click(function(){
		var opts="";
		switch($(this).val()){
			case 'menu_all':
				$(arr_all).each(function(i,v){
					opts+="<option value='"+v+"'>"+map_all[v]+"</option>";
				});
				$("#searchForm #siteCode").empty().append(opts);
			break;
			case 'menu_main':
				$(arr_main).each(function(i,v){
					opts+="<option value='"+v+"'>"+map_main[v]+"</option>";
				});
				$("#searchForm #siteCode").empty().append(opts);
			break;
			case 'menu_dept':
				$(arr_dept).each(function(i,v){
					opts+="<option value='"+v+"'>"+map_dept[v]+"</option>";
				});
				$("#searchForm #siteCode").empty().append(opts);
			break;
			case 'menu_csc':
				$(arr_csc).each(function(i,v){
					opts+="<option value='"+v+"'>"+map_csc[v]+"</option>";
				});
				$("#searchForm #siteCode").empty().append(opts);
			break;
			case 'menu_etc':
				$(arr_etc).each(function(i,v){
					opts+="<option value='"+v+"'>"+map_etc[v]+"</option>";
				});
				$("#searchForm #siteCode").empty().append(opts);
			break;

		}
	});


	$("#getCheckedAll").change(function() { // 삭제/복원 관련 전체 선택
		if($("#getCheckedAll").is(":checked")) {
			$("input[name=ackChecked]:checkbox").each(function() {
				$(this).prop("checked", true);
			});
		} else {
			$("input[name=ackChecked]:checkbox").each(function() {
				$(this).prop("checked", false);
			});
		}
	});

	$("#batch_ack_btn").click(function() { // 게시글 일괄 삭제
		if ($("input[name=ackChecked]:checked").length == 0) {
			alert("승인할 메뉴를 선택해주세요.");
			return;
		}

		if (confirm("선택하신 메뉴를 승인하시겠습니까?")) {
			var frm = document.getElementById("searchForm");
			frm.page.value = 1;
			frm.action = "/sys/myPageMng/ackListProc.do?mId=${menuVO.mId}";
			frm.submit();
		}

		return false;
	});
});

function downloadExcel(){
	if($("#siteCode").val() == ""){
		alert("사이트를 선택하세요.");
		return false;
	}else{
		document.location.href="${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/menuCheckListDown.do?siteCode="+$("#siteCode").val();
	}
}


function moveCharge(reqIdx) {
	var message = "해당 메뉴의 인계정보를 승인하시겠습니까?";
    if(confirm(message)) {
    	$.ajax({
            url  : "/sys/myPageMng/ackProc.do",
            type : "post",
            dataType: 'json',
            data : {idx : reqIdx},
            success : function(result) {
                if(result.flag == "success") {
                    alert(result.message);

                    var frm = document.getElementById('searchForm');
                    frm.cmd.value = '';
                    frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/menuCheckList.do?mId=${menuVO.mId}';
                    frm.submit();

                } else {
                    alert(result.message);
                }
            }
        });
    }
}

function cancelCharge(reqIdx) {
	var message = "해당 메뉴의 인계정보를 취소하시겠습니까?";
    if(confirm(message)) {
    	$.ajax({
            url  : "/sys/myPageMng/ackCancelProc.do",
            type : "post",
            dataType: 'json',
            data : {idx : reqIdx},
            success : function(result) {
                if(result.flag == "success") {
                    alert(result.message);

                    var frm = document.getElementById('searchForm');
                    frm.cmd.value = '';
                    frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/menuCheckList.do?mId=${menuVO.mId}';
                    frm.submit();

                } else {
                    alert(result.message);
                }
            }
        });
    }
}

function goPage(curPage){
	var frm = document.getElementById('searchForm');
	frm.page.value = curPage;
	frm.cmd.value = '';
	frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/menuCheckList.do?mId=${curMid}';
	frm.submit();
}
</script>


<h2>메뉴관리</h2>
<!--
<div class="board_head" style="margin-bottom:0px;">
	<input type="radio" name="menu_category" id="menu_category_all" value="menu_all" checked="checked"><label for="menu_category_all">전체</label>
	<input type="radio" name="menu_category" id="menu_category_main" value="menu_main" ><label for="menu_category_main">대표(메인/열린시장실)</label>
	<input type="radio" name="menu_category" id="menu_category_dept" value="menu_dept" ><label for="menu_category_dept">부서및빌트인</label>
	<input type="radio" name="menu_category" id="menu_category_csc" value="menu_csc" ><label for="menu_category_csc">읍면동</label>
	<input type="radio" name="menu_category" id="menu_category_etc" value="menu_etc" ><label for="menu_category_etc">기타</label>
</div>
 -->
<form id="searchForm" name="searchForm" method="post" action="${pageContext.request.contextPath}/sys/totalAdminMng/menuMng/menuCheckList.do?mId=${menuVO.mId}">
<input type="hidden" name="page" id="page" value="${page}" />
<input type="hidden" name="cmd" id="cmd" value="" />
<div class="search_box">
    <select id="siteCode" name="siteCode">
        <option value="">선택하세요.</option>
        <c:forEach var="site" items="${menusMngList}">
        <option value="${site.siteCode}" <c:if test="${searchVO.siteCode == site.siteCode}">selected="selected"</c:if>>${site.siteName}</option>
        </c:forEach>
    </select>
    | 점검구분 :
    <select id="searchType" name="searchType">
        <option value="">선택하세요.</option>
        <option value="NOT_CHARGE" <c:if test="${searchVO.searchType == 'NOT_CHARGE'}">selected="selected"</c:if>>담당자 미지정</option>
        <option value="REQ" <c:if test="${searchVO.searchType == 'REQ'}">selected="selected"</c:if>>인계승인</option>
        <option value="ACK" <c:if test="${searchVO.searchType == 'ACK'}">selected="selected"</c:if>>인계이력</option>
    </select>
    <input type="submit" value="조회" class="btn_white"/>
    <c:if test="${searchVO.searchType == 'REQ'}">
	<input id="batch_ack_btn" type="button" class="btn_white" value="전체 승인"/>
	</c:if>
    <!-- <input type="button" value="Excel 다운" onclick="downloadExcel(); return false;" class="btn_white" id="btn_downloadExcel"/>  -->
</div>


<table class="tableSt_list row_over">
    <caption>${menuName} 리스트</caption>
    <colgroup>
    	<c:if test="${searchVO.searchType == 'REQ'}">
    	<col span="1" style="width:8%;"/>
    	</c:if>
    	<col span="1" />
        <col span="1" style="width:8%;"/>
        <col span="1" style="width:8%;"/>
        <col span="1" style="width:8%;"/>
        <col span="1" style="width:10%;"/>
        <c:if test="${searchVO.searchType != 'ACK'}">
        <col span="1" style="width:10%;"/>
        <col span="1" style="width:10%;"/>
        </c:if>
        <c:if test="${searchVO.searchType == 'NOT_MAPPING' || searchVO.searchType == 'REQ'}">
        <col span="1" style="width:10%;"/>
        <col span="1" style="width:12%;"/>
        <col span="1" style="width:12%;"/>
        </c:if>
        <c:if test="${searchVO.searchType == 'ACK'}">
        <col span="1" style="width:10%;"/>
        <col span="1" style="width:12%;"/>
        <col span="1" style="width:12%;"/>
        </c:if>
    </colgroup>
    <thead>
    <tr>
    	<c:if test="${searchVO.searchType == 'REQ'}">
    	<th scope="col"><input type="checkbox" name="getCheckedAll" id="getCheckedAll"/></th>
    	</c:if>
        <th scope="col">메뉴명</th>
        <th scope="col">메뉴ID</th>
        <th scope="col">메뉴레벨</th>
        <th scope="col">메뉴타입</th>
        <c:choose>
        <c:when test="${searchVO.searchType == 'ACK'}">
        <th scope="col">인계자</th>
        </c:when>
        <c:when test="${searchVO.searchType == 'REQ'}">
        <th scope="col">요청자</th>
        </c:when>
        <c:otherwise>
        <th scope="col">담당자</th>
        </c:otherwise>
        </c:choose>

        <c:if test="${searchVO.searchType != 'ACK' && searchVO.searchType != 'REQ'}">
        <th scope="col">요점검내용</th>
        <th scope="col">관리</th>
        </c:if>
        <c:if test="${searchVO.searchType == 'NOT_MAPPING' || searchVO.searchType == 'REQ'}">
        <th scope="col">인계요청</th>
        <th scope="col">신규담당자</th>
        <th scope="col">인계관리</th>
        </c:if>
        <c:if test="${searchVO.searchType == 'ACK'}">
        <th scope="col">인계상태</th>
        <th scope="col">신규담당자</th>
        <th scope="col">승인일자</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty menuCheckList}">
        <c:forEach var="result" items="${menuCheckList}" varStatus="status">
            <tr>
            	<c:if test="${searchVO.searchType == 'REQ'}">
				<td>
					<c:if test="${!empty result.reqState && result.reqState=='R'}">
					<input type="checkbox" id="ackChecked${result.reqIdx}" name="ackChecked" value="${result.reqIdx}"/>
					</c:if>
				</td>
				</c:if>
                <td class="taL">&nbsp;&nbsp;${result.menuName}</td>
                <td>${result.mId }</td>
                <td>${result.menuLevel}</td>
                <td>
                    <c:choose>
                        <c:when test="${result.menuType== 'C'}">CMS 콘텐츠</c:when>
                        <c:when test="${result.menuType== 'F'}">파일직접선택</c:when>
                        <c:when test="${result.menuType== 'B'}">게시판</c:when>
                        <c:when test="${result.menuType== 'P'}">프로그램</c:when>
                        <c:when test="${result.menuType== 'L'}">링크</c:when>
                    </c:choose>
                </td>
                <td>
               	<c:choose>
			        <c:when test="${searchVO.searchType == 'ACK'}">
			        <c:if test="${!empty result.reqChargeDepNm}">[${result.reqChargeDepNm}] </c:if>${result.reqChargeFnm}
			        </c:when>
			        <c:when test="${searchVO.searchType == 'REQ'}">
			        <c:if test="${!empty result.reqChargeDepNm}">[${result.reqChargeDepNm}] </c:if>${result.reqChargeFnm}
			        </c:when>
			        <c:otherwise>
			        <c:choose>
	                   	<c:when test="${empty result.chargeId}">-</c:when>
	                   	<c:otherwise>
	                   		<c:if test="${!empty result.chargeDepNm}">[${result.chargeDepNm}] </c:if>${result.chargeFnm}
	                   	</c:otherwise>
	                   </c:choose>
			        </c:otherwise>
		        </c:choose>
                </td>
                <c:if test="${searchVO.searchType != 'ACK' && searchVO.searchType != 'REQ'}">
                <td>
                	<c:choose>
                        <c:when test="${empty result.chargeId}">담당자 미지정</c:when>
                        <c:when test="${result.notMappingVal=='ID'}">부서 불일치</c:when>
                        <c:when test="${result.notMappingVal=='TEL'}">전화번호 불일치</c:when>
                        <c:otherwise>담당자 정보 불일치</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="/sys/totalAdminMng/menuMng/menuList.do?mId=0101000000&siteCode=${result.siteCode}&procMid=${result.mId}" class="btn_sblack">메뉴바로가기</a>
                </td>
                </c:if>

                <c:if test="${searchVO.searchType == 'NOT_MAPPING' || searchVO.searchType == 'REQ'}">
		        <td>
		        	<c:choose>
                        <c:when test="${!empty result.reqState && result.reqState=='R'}"><span class="red b">승인대기</span></c:when>
                        <c:when test="${!empty result.reqState && result.reqState=='A'}"><span class="blue b">인계완료</span></c:when>
                        <c:when test="${!empty result.reqState && result.reqState=='C'}"><span class="orange b">승인취소</span></c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
		        </td>
		        <td>
		        	<c:choose>
                        <c:when test="${!empty result.reqState}"><span class="blue b">[${result.moveChargeDepNm}] ${result.moveChargeFnm}</span></c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
		        </td>
		        <td>
		        	<c:choose>
                        <c:when test="${!empty result.reqState && result.reqState=='R'}">
                        <a href="javascript:moveCharge('${result.reqIdx}');" class="btn_swhite">승인</a>
                        <a href="javascript:cancelCharge('${result.reqIdx}');" class="btn_sblack">취소</a>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
		        </td>
		        </c:if>
		        <c:if test="${searchVO.searchType == 'ACK'}">
		        <td>
		        	<c:choose>
                        <c:when test="${!empty result.reqState && result.reqState=='R'}"><span class="red b">승인대기</span></c:when>
                        <c:when test="${!empty result.reqState && result.reqState=='A'}"><span class="blue b">인계완료</span></c:when>
                        <c:when test="${!empty result.reqState && result.reqState=='C'}"><span class="orange b">승인취소</span></c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
		        </td>
		        <td>
		        	<c:choose>
                        <c:when test="${!empty result.reqState}"><span class="blue b">[${result.moveChargeDepNm}] ${result.moveChargeFnm}</span></c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
		        </td>
		        <td>
		        	<c:choose>
                        <c:when test="${!empty result.reqState && result.reqState=='A'}">
                        <fmt:formatDate value="${result.ackDt}" pattern="yyyy-MM-dd"/>
                        </c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
		        </td>
		        </c:if>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
</form>
<c:if test="${empty menuCheckList}">
    <div class="no_data">
        점검메뉴가 없습니다.
    </div>
</c:if>

<c:if test="${not empty menuCheckList}">
	<div class="box_page">
		<ui:pagination paginationInfo = "${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>