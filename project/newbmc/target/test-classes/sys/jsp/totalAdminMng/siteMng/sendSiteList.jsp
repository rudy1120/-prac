<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%	String cmsUrl = EgovProperties.getProperty("ySmartCMS.url"); %>
<c:set var="cmsUrl" value="<%=cmsUrl %>" />
<%--
*********************************************************************
* 파 일 명 : sendSiteList.jsp
* 설       명 : CMS로 사이트정보 전송
* 작   성  자 : 권태성
* 작   성  일 : 2016-06-30
*********************************************************************
--%>
<script type="text/javascript">
//<![CDATA[
function sleep(num){	//[1/1000초]
	var now = new Date();
	var stop = now.getTime() + num;
	while(true){
		now = new Date();
		if(now.getTime() > stop)return;
	}
}

$(document).ready(function(){
	<%-- 사이트 그룹 사용여부 가져오기 --%>
	$("#sendBtn").click(function(){
		
		<c:if test="${not empty groupList }">
		var groupOk = "N";
		$('input[name="targetSite"]').each(function(i){
			if($(this).is(":checked")){
				var siteGroup = $("#siteGroup"+(i+1)+" option:selected").val();
				if(siteGroup == "0"){
					alert("사이트 그룹을 선택하세요.");
					$("#siteGroup"+(i+1)).focus();
					groupOk = "N";
					return false;
				}else{
					groupOk = "Y";
				}
			}
		});
		</c:if>
		$('input[name="targetSite"]').each(function(i){

			if($(this).is(":checked") && groupOk == "Y"){
				var siteName = $(this).parent().next().text();
				var siteCode = $(this).parent().next().next().text();
				var bUse = $(this).parent().next().next().next().text().replace(/ /gi,'');
				var siteOrder = $(this).parent().next().next().next().next().text();
				<c:if test="${not empty groupList }">
				var siteGroup = $("#siteGroup"+(i+1)+" option:selected").val();
				</c:if>

				if(bUse == "사용"){
					bUse = "Y";
				}else{
					bUse = "N";
				}

				//$(this).hide();
				//재시도일 경우 삭제
				$("#status"+(i+1)).remove();
				//상태 추가
		    	$(this).after("<img id=\"status"+(i+1)+"\" src=\"${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-progresss-ani-white-1.gif\" />");
		    	$("#status"+(i+1)).attr("title","처리중 입니다");
				$.ajax({
				    //url  : "http://ysmartcms.yhproject.kr:8989/getSettings",
				    type : "POST",
				    url : "${cmsUrl}/linkage/addSite?key=${sessionScope.cmsLoginKey}",
				    data : {
					    	'siteName' : encodeURI(siteName), 
					    	//'siteName' : siteName,
					    	'siteCode' : siteCode, 
					    	'bUse' : bUse, 
					    	'siteOrder' : siteOrder,
					    	<c:if test="${not empty groupList }">
					    	'siteGroup' : siteGroup
					    	</c:if>
				    },
				    dataType : "jsonp",
				    success : function(data) {
				    	//sleep(8000);
				        if(data.flag == "success") {
							//alert(data.groups.length);
							$("#status"+(i+1)).attr("src","${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-noti-complete.png");
							$("#status"+(i+1)).attr("title", data.message);
							
				        }else{
				            $("#status"+(i+1)).attr("src","${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-noti-caution.png");
				            $("#status"+(i+1)).attr("title", data.message);
				        }
				    },
				    error: function(xhr, status, error) {
				    	//sleep(8000);
				    	//console.log("y-SmartCMS 서버에 연결하지 못하였습니다. (code : "+status+")");
			            $("#status"+(i+1)).attr("src","${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-noti-caution.png");
			            $("#status"+(i+1)).attr("title","y-SmartCMS 서버에 연결하지 못하였습니다.");
				    }
				});

			}

		});

	});

});
//]]>
</script>
<h2>사이트선택</h2>
<div class="mB10">
	<span class="info red">사이트 등록후에 CMS에서 사이트 수정을 통해 상세값을 입력하셔야 합니다.</span>
</div>
<form name="frm" id="frm" action="${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/sendToCMS.do" method="post" onsubmit="chkFrm();">

    <table class="tableSt_list row_over">
        <caption>사이트 리스트</caption>
        <colgroup>
            <col span="1" style="width:7%;"/>
            <col span="1" />
            <col span="1" style="width:20%;"/>
            <col span="1" style="width:10%;"/>
            <col span="1" style="width:10%;"/>
            <c:if test="${not empty groupList }">
            <col span="1" style="width:20%;"/>
            </c:if>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">선택</th>
            <th scope="col">사이트명</th>
            <th scope="col">사이트코드</th>
            <th scope="col">사용여부</th>
            <th scope="col">정렬순서</th>
            <c:if test="${not empty groupList }">
            <th scope="col">그룹</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty siteList}">
            <c:forEach var="result" items="${siteList}" varStatus="status">
                <tr>
                    <td>
                    	<input type="checkbox" id="" name="targetSite" value="${status.count }" />
                    </td>
                    <td>${result.siteName}</td>
                    <td>${result.siteCode}</td>
                    <td>
                        <c:choose>
                            <c:when test="${result.isUse == 'Y'}">사용</c:when>
                            <c:otherwise>사용안함</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${result.siteOrder}</td>
                    <c:if test="${not empty groupList }">
                    <td>
                    	<select id="siteGroup${status.count }">
                    		<option value="0">그룹선택</option>
                    		<c:forEach items="${groupList }" var="group" varStatus="sts">
                    			<option value="${group.idx }">${group.groupName }</option>
                    		</c:forEach>
                    	</select>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${empty siteList}">
        <div class="no_data">
            등록된 게시물이 없습니다.
        </div>
    </c:if>

    <div class="btn_boxR">
        <a href="#" id="sendBtn" class="btn_cyan">보내기</a>
    </div>
</form>
