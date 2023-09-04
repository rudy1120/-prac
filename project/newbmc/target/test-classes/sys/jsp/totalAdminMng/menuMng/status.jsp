<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%	String cmsUrl = EgovProperties.getProperty("ySmartCMS.url"); %>
<c:set var="cmsUrl" value="<%=cmsUrl %>" />
<%--
*********************************************************************
* 파 일 명 : status.jsp
* 설		명 : 사이트 메뉴 상태를 확인
* 작	성  자 : 권태성
* 작	성  일 : 2017-07-04
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

	var siteCode = '${siteCode}';
	var depthNowCount = 1;
	var depth = 1;
	var totalCount = $("input[name=\"depth"+depth+"_Idxs\"]").size();
	var depthCnt = 1;
	/**
	 *
	 * 메뉴 마다 개별적으로 실행하는 함수 입니다.
	 *
	 **/
	var start = function () {
		var folderName = $("#depth"+depth+"_"+depthNowCount+"_menuName").val();
		var folderCode = $("#depth"+depth+"_"+depthNowCount+"_mId").val();
		var parentCode =  $("#depth"+depth+"_"+depthNowCount+"_parentMid").val();
/*
		console.log("now selector : "+"#depth"+depth+"_"+depthNowCount+"_menuName");
		console.log("folderName : "+folderName);
		console.log("folderCode : "+folderCode);
 */
		var idx = $("#depth"+depth+"_"+depthNowCount+"_idx").val();

		$("#depth"+depth+"_"+depthNowCount+"_parentMid").after("<img id=\"depth"+depth+"_status_"+depthNowCount+"\" src=\"${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-progresss-ani-white-1.gif\" width=\"24\" height=\"22\"/>");
		$.ajax({
			type : "POST",
			url : "/sys/totalAdminMng/menuMng/statusCheck.do",
			data : {
					"siteCode" : siteCode,
					"mId" : folderCode,
			},
			dataType : "json",
			async: false,
			success : function(data) {
				if (data.flag == true) {
					if (data.code == 200) {
						$("#depth"+depth+"_status_"+depthNowCount).attr("src","${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-noti-complete.png");
					} else {
						$("#depth"+depth+"_status_"+depthNowCount).attr("src","${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-noti-caution.png");
						if (data.message != null) {
							console.log("error message : " + data.message);
						}
					}
					$("#depth"+depth+"_status_"+depthNowCount).attr("title", data.code);
				} else {
					$("#depth"+depth+"_status_"+depthNowCount).attr("src","${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-noti-caution.png");
					$("#depth"+depth+"_status_"+depthNowCount).attr("title", data.message);
				}
				depthNowCount++;
			},
			error : function(xhr, status, error) {
				$("#depth"+depth+"_status_"+depthNowCount).attr("src","${pageContext.request.contextPath}/sys/AXJ/ui/arongi/images/dx-noti-caution.png");
				$("#depth"+depth+"_status_"+depthNowCount).attr("title","서버에 연결하지 못하였습니다.");
				depthNowCount++;
			},
			complete : function(){
				if(depthNowCount <= totalCount){
					start(depth, totalCount);
				}else{
					if(depth <= 5){
						depth = depth+1;
						if($("input[name=\"depth"+(depth)+"_Idxs\"]").size() >= 1){
							depthNowCount = 1;
							totalCount = $("input[name=\"depth"+(depth)+"_Idxs\"]").size();
							start();
						}
					}
				}
			}
		});
	}

	$("#sendBtn").click(function(){
		//최초 1depth만 호출하면 5depth까지 함수 내부에서 호출 합니다.
		start();
	});

});
//]]>
</script>
<c:set var="depth2TotalCnt" value="1" />
<c:set var="depth3TotalCnt" value="1" />
<c:set var="depth4TotalCnt" value="1" />
<c:set var="depth5TotalCnt" value="1" />
<h2>메뉴 확인</h2>
<div class="mB10">
	<span class="tip"><b>에러 아이콘 위에 마우스를 올리시면 메시지를 확인할 수 있습니다.</b></span>
</div>

<div id="folderBox" class="">
	<ul id="browser" class="filetree">
		<c:forEach var="depth1" items="${siteMenuList}" varStatus="depth1Sts">
			<c:set var="isOpenDepth1" value="closed"/>
			<c:if test="${fn:substring(depth1.mId,0,2) == fn:substring(procMid, 0, 2)}"><c:set var="isOpenDepth1" value="open"/></c:if>
			<li id="${depth1.mId}" class="${isOpenDepth1}">
				<c:set var="depth1Class" value="folder"/>
				<c:if test="${empty depth1.depth2MenuList}"><c:set var="depth1Class" value="file"/></c:if>
				<span class="${depth1Class}" id="${depth1.idx }">
					${depth1.menuName}
					<input type="hidden" id="depth1_${depth1Sts.count }_idx" name="depth1_Idxs" value="${depth1.idx }" />
					<input type="hidden" id="depth1_${depth1Sts.count }_mId" value="${depth1.mId }" />
					<input type="hidden" id="depth1_${depth1Sts.count }_menuName" value="${depth1.menuName }" />
					<input type="hidden" id="depth1_${depth1Sts.count }_parentMid" value="0" />
				</span>
				<c:if test="${!empty depth1.depth2MenuList}">
					<c:forEach var="depth2" items="${depth1.depth2MenuList}" varStatus="depth2Sts">
						<c:set var="isOpenDepth2" value="closed"/>
						<c:if test="${fn:substring(depth2.mId,0,4) == fn:substring(procMid, 0, 4)}"><c:set var="isOpenDepth2" value="open"/></c:if>
						<ul>
							<li class="${isOpenDepth2}">
								<c:set var="depth2Class" value="folder"/>
								<c:if test="${empty depth2.depth3MenuList}">
									<c:set var="depth2Class" value="file"/>
								</c:if>
								<span class="${depth2Class}" id="${depth2.idx }">
										${depth2.menuName}
										<input type="hidden" id="depth2_${depth2TotalCnt }_idx" name="depth2_Idxs" value="${depth2.idx }" />
										<input type="hidden" id="depth2_${depth2TotalCnt }_mId" value="${depth2.mId }" />
										<input type="hidden" id="depth2_${depth2TotalCnt }_menuName" value="${depth2.menuName }" />
										<input type="hidden" id="depth2_${depth2TotalCnt }_parentMid" value="${depth1.mId }" />
									</span>
								<c:if test="${!empty depth2.depth3MenuList}">
									<c:forEach var="depth3" items="${depth2.depth3MenuList}" varStatus="depth3Sts">
										<c:set var="isOpenDepth3" value="closed"/>
										<c:if test="${fn:substring(depth3.mId,0,6) == fn:substring(procMid, 0, 6)}"><c:set var="isOpenDepth3" value="open"/></c:if>
										<ul>
											<li class="${isOpenDepth3}">
												<c:set var="depth3Class" value="folder"/>
												<c:if test="${empty depth3.depth4MenuList}">
													<c:set var="depth3Class" value="file"/>
												</c:if>
												<span class="${depth3Class}" id="${depth3.idx }">
														${depth3.menuName}
													<input type="hidden" id="depth3_${depth3TotalCnt }_idx" name="depth3_Idxs" value="${depth3.idx }" />
														<input type="hidden" id="depth3_${depth3TotalCnt }_mId" value="${depth3.mId }" />
														<input type="hidden" id="depth3_${depth3TotalCnt }_menuName" value="${depth3.menuName }" />
														<input type="hidden" id="depth3_${depth3TotalCnt }_parentMid" value="${depth2.mId }" />
												</span>
												<c:if test="${!empty depth3.depth4MenuList}">
													<c:forEach var="depth4" items="${depth3.depth4MenuList}" varStatus="depth4Sts">
														<c:set var="isOpenDepth4" value="closed"/>
														<c:if test="${fn:substring(depth4.mId,0,8) == fn:substring(procMid, 0, 8)}"><c:set var="isOpenDepth4" value="open"/></c:if>
														<ul>
															<li class="${isOpenDepth4}">
																<c:set var="depth4Class" value="folder" />
																<c:if test="${empty depth4.depth5MenuList}">
																	<c:set var="depth4Class" value="file"/>
																</c:if>
																<span class="${depth4Class}" id="${depth4.idx }">
																	${depth4.menuName}
																	<input type="hidden" id="depth4_${depth4TotalCnt }_idx" name="depth4_Idxs" value="${depth4.idx }" />
																		<input type="hidden" id="depth4_${depth4TotalCnt }_mId" value="${depth4.mId }" />
																		<input type="hidden" id="depth4_${depth4TotalCnt }_menuName" value="${depth4.menuName }" />
																		<input type="hidden" id="depth4_${depth4TotalCnt }_parentMid" value="${depth3.mId }" />
																</span>
																<c:if test="${!empty depth4.depth5MenuList}">
																	<c:forEach var="depth5" items="${depth4.depth5MenuList}" varStatus="depth5Sts">
																		<c:set var="isOpenDepth5" value="closed"/>
																		<c:if test="${fn:substring(depth4.mId,0,10) == fn:substring(procMid, 0, 10)}"><c:set var="isOpenDepth5" value="open"/></c:if>
																		<ul>
																			<li class="${isOpenDepth5}">
																				<c:set var="depth5Class" value="folder" />
																				<c:if test="${empty depth4.depth5MenuList}">
																					<c:set var="depth5Class" value="file"/>
																				</c:if>
																				<span class="${depth5Class}" id="${depth5.idx }">
																					${depth5.menuName}
																					<input type="hidden" id="depth5_${depth5TotalCnt }_idx" name="depth5_Idxs" value="${depth5.idx }" />
																					<input type="hidden" id="depth5_${depth5TotalCnt }_mId" value="${depth5.mId }" />
																					<input type="hidden" id="depth5_${depth5TotalCnt }_menuName" value="${depth5.menuName }" />
																					<input type="hidden" id="depth5_${depth5TotalCnt }_parentMid" value="${depth4.mId }" />
																				</span>
																			</li>
																		</ul>
																		<c:set var="depth5TotalCnt" value="${depth5TotalCnt+1 }" />
																	</c:forEach>
																</c:if>
															</li>
														</ul>
														<c:set var="depth4TotalCnt" value="${depth4TotalCnt+1 }" />
													</c:forEach>
												</c:if>
											</li>
										</ul>
										<c:set var="depth3TotalCnt" value="${depth3TotalCnt+1 }" />
									</c:forEach>
								</c:if>
							</li>
						</ul>
						<c:set var="depth2TotalCnt" value="${depth2TotalCnt+1 }" />
					</c:forEach>
				</c:if>
			</li>
		</c:forEach>
	</ul>
</div>

<c:if test="${empty siteMenuList}">
	<div class="no_data">
		등록된 메뉴가 없습니다.
	</div>
</c:if>

<div class="btn_boxR">
	<a href="#" id="sendBtn" class="btn_cyan">확인</a>
</div>
