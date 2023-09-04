<%@page import="egovframework.portal.common.CclType"%>
<%@page import="egovframework.portal.common.NuriType"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 사용자 메뉴 관리
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.10.07		엄동건			최초 생성 및 코드 작성
	 * 2017.06.15		권태성			JS 분리
	 * 2017.09.15		J.Ryeon Lee		공공누리 및 CCL 유형 추가, 기존 미사용 코드 및 중복 컬럼 제거
	 *
	 *
	 * @author 엄동건
	 * @since 2014.10.07
	 */
%>
<h2>메뉴 관리</h2>
<p class="info mB10">최상단 메뉴는 삭제 및 추가하실 수 없습니다.</p>

<!--
<div class="board_head" style="margin-bottom:0px;">
	<input type="radio" name="menu_category" id="menu_category_all" value="menu_all" checked="checked"><label for="menu_category_all">전체</label>
	<input type="radio" name="menu_category" id="menu_category_main" value="menu_main" ><label for="menu_category_main">대표(메인/열린시장실)</label>
	<input type="radio" name="menu_category" id="menu_category_dept" value="menu_dept" ><label for="menu_category_dept">부서및빌트인</label>
	<input type="radio" name="menu_category" id="menu_category_csc" value="menu_csc" ><label for="menu_category_csc">읍면동</label>
	<input type="radio" name="menu_category" id="menu_category_etc" value="menu_etc" ><label for="menu_category_etc">기타</label>
</div>
-->
<div class="search_box mL10">
	<form id="searchForm" name="searchForm" method="post" action="${_context}/sys/totalAdminMng/menuMng/menuList.do?mId=${menuVO.mId}">
		<select id="siteCode" name="siteCode" class="mB5">
			<option value="">선택하세요.</option>
			<c:forEach var="site" items="${menusMngList}">
				<option value="${site.siteCode}" <c:if test="${searchVO.siteCode == site.siteCode}">selected="selected"</c:if>>${site.siteName}</option>
			</c:forEach>
		</select>
		<input type="submit" value="조회" class="btn_white"/>
		<input type="button" value="메뉴추가" class="btn_white" id="btn_addFirst"/>
		<input type="button" value="CMS로 보내기" onclick="sendMenu(); return false;" class="btn_white" id="btn_sendMenu" ${fn:substring(ADMIN_LOGIN_INFO.adminId,0,2) eq 'yh' ? '' : 'style="display:none;"'} />
		<input type="button" value="Excel 등록" onclick="uploadExcel(); return false;" class="btn_white" id="btn_uploadExcel" ${fn:substring(ADMIN_LOGIN_INFO.adminId,0,2) eq 'yh' ? '' : 'style="display:none;"'} />
		<input type="button" value="상태 확인" onclick="checkStatus(); return false;" class="btn_white" id="btn_statusMenu" ${fn:substring(ADMIN_LOGIN_INFO.adminId,0,2) eq 'yh' ? '' : 'style="display:none;"'} />

		<input id="menuSearchTxt" type="text" class="input200" placeholder="검색 메뉴명 또는 담당자 입력" />
		<input id="menuSearchBtn" type="button" value="메뉴검색" class="btn_white"/>
	</form>
</div>

<div id="folderBox" class="folder_box mT10">
	<ul id="browser" class="filetree">
		<c:forEach var="depth1" items="${siteMenuList}">
			<c:set var="isOpenDepth1" value="closed"/>
			<c:if test="${fn:substring(depth1.mId,0,2) == fn:substring(procMid, 0, 2)}"><c:set var="isOpenDepth1" value="open"/></c:if>
			<li id="${depth1.mId}" class="${isOpenDepth1}">
				<c:set var="depth1Class" value="folder"/>
				<c:if test="${empty depth1.depth2MenuList}"><c:set var="depth1Class" value="file"/></c:if>
				<span class="${depth1Class}">${depth1.menuName}<span class="hidden">${depth1.chargeFnm}</span>
					<img src="/sys/img/treeView/registration1_add.gif" onclick="getAddMenuInfo('${depth1.mId}'); return false;"/>
					<img src="/sys/img/treeView/registration1_edit.gif" onclick="getEditMenuInfo('${depth1.mId}'); return false;"/>
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
							<span class="${depth2Class}">${depth2.menuName}<span class="hidden">${depth2.chargeFnm}</span>
								<img src="/sys/img/treeView/registration1_add.gif" onclick="getAddMenuInfo('${depth2.mId}'); return false;"/>
								<img src="/sys/img/treeView/registration1_edit.gif" onclick="getEditMenuInfo('${depth2.mId}'); return false;"/>
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
											<span class="${depth3Class}">${depth3.menuName}<span class="hidden">${depth3.chargeFnm}</span>
												<img src="/sys/img/treeView/registration1_add.gif" onclick="getAddMenuInfo('${depth3.mId}'); return false;"/>
												<img src="/sys/img/treeView/registration1_edit.gif" onclick="getEditMenuInfo('${depth3.mId}'); return false;"/>
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
															<span class="${depth4Class}">${depth4.menuName}<span class="hidden">${depth4.chargeFnm}</span>
																<img src="/sys/img/treeView/registration1_add.gif" onclick="getAddMenuInfo('${depth4.mId}'); return false;"/>
																<img src="/sys/img/treeView/registration1_edit.gif" onclick="getEditMenuInfo('${depth4.mId}'); return false;"/>
															</span>
															<c:if test="${!empty depth4.depth5MenuList}">
																<c:forEach var="depth5" items="${depth4.depth5MenuList}">
																	<c:set var="isOpenDepth5" value="closed"/>
																	<c:if test="${fn:substring(depth4.mId,0,10) == fn:substring(procMid, 0, 10)}"><c:set var="isOpenDepth5" value="open"/></c:if>
																	<ul>
																		<li class="${isOpenDepth5}">
																			<c:set var="depth5Class" value="file" />
																			<c:if test="${empty depth4.depth5MenuList}">
																				<c:set var="depth5Class" value="file"/>
																			</c:if>
																			<span class="${depth5Class}">${depth5.menuName}<span class="hidden">${depth5.chargeFnm}</span>
																				<!-- <a href="javascript:getAddMenuInfo('${depth5.mId}')"><img src="/sys/img/treeView/registration1_add.gif" /></a>  -->
																				<img src="/sys/img/treeView/registration1_edit.gif" onclick="getEditMenuInfo('${depth5.mId}'); return false;"/>
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


<form name="menuFrm" id="menuFrm" action="${_context}/sys/totalAdminMng/menuMng/writeMenuProc.do" method="post">
	<input type="hidden" id="writeKind" name="writeKind" value=""/>
	<input type="hidden" id="menuLevel" name="menuLevel" value="0"/>
	<input type="hidden" name="siteCode" id="siteCode_menuFrm" value="${searchVO.siteCode}" />

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
				<th scope="row"><label for="targetMid">메뉴ID${result.isFirst }</label></th>
				<td><input type="text" name="targetMid" id="targetMid" readonly /></td>
			</tr>
			<tr>
				<th scope="row">컨텐츠 포함여부</th>
				<td>
					<input type="radio" name="isIncContent" id="isIncContentY" value="Y" checked><label for="isIncContentY">포함</label>
					<input type="radio"	name="isIncContent" id="isIncContentN" value="N"><label for="isIncContentN">미포함</label>
					<p class="tip"> 하위 메뉴가 존재할 경우, <span class="red">현재 메뉴는 컨텐츠 미포함을 선택</span>해야 합니다.</p>
				</td>
			</tr>
			<tr>
				<th scope="row">하위 메뉴 우선 노출<br/>사용여부</th>
				<td>
					<input type="radio"	name="isFirst" id="isFirst1" value="1"><label for="isFirst1">비활성화</label>
					<input type="radio" name="isFirst" id="isFirst0" value="0" checked><label for="isFirst0">활성화</label>
				</td>
			</tr>
			<tr>
				<th scope="row"><label>컨텐츠 타입</label></th>
				<td>
					<input type="radio" name="menuType" id="menuTypeC" onclick="showActionBox('C');" value="C" checked><label for="menuTypeC">CMS 컨텐츠</label>
					<input type="radio"	name="menuType" id="menuTypeF" onclick="showActionBox('F');" value="F"><label for="menuTypeF">파일 직접 선택</label>
					<input type="radio"	name="menuType" id="menuTypeB" onclick="showActionBox('B');" value="B"><label for="menuTypeB">게시판</label>
					<input type="radio"	name="menuType" id="menuTypeP" onclick="showActionBox('P');" value="P"><label for="menuTypeP">프로그램</label>
					<input type="radio"	name="menuType" id="menuTypeL" onclick="showActionBox('L');" value="L"><label for="menuTypeL">링크</label>
				</td>
			</tr>
			<tr>
				<th scope="row"><label>새 창 여부</label></th>
				<td>
					<input type="radio" name="target" id="targetS" value="_self" checked><label for="targetS">현재 창</label>
					<input type="radio"	name="target" id="targetB" value="_blank"><label for="targetB">새 창</label>
				</td>
			</tr>
			<tr id="cmsSelectorBox">
				<th scope="row"><label for="cmsSiteCode">CMS 컨텐츠 안내</label></th>
				<td>
					<!-- <span class="red">CMS에서 배포한 페이지 아이디와 메뉴관리의 메뉴 아이디가 동일할때,해당 컨텐츠가 로딩됩니다.</span> -->
					<input type="button" id="cmsSelector" onclick="getCmsInfoPop();" value="선택" class="btn_sblack"/>
					<input type="hidden" name="cmsSiteCode" id="cmsSiteCode" value=""/>
					<input type="hidden" name="cmsPageId" id="cmsPageId" value=""/>
					<span class="f11">선택된 CMS : </span><span id="cmsName" class="blue">CMS을 선택하세요.</span>
				</td>
			</tr>
			<tr id="fileUrlSelectorBox">
				<th scope="row"><label for="contentFilePath">파일 URL</label></th>
				<td>
					<input type="text" name="contentFilePath" id="contentFilePath" class="input500"/>
					<br/><span class="red f11">(*FTP에 업로드된 JSP를 바로연결하는 방식으로 CMS에서 관리가 불가합니다.)</span>
				</td>
			</tr>
			<tr id="bbsSelectorBox">
				<th scope="row"><label for="bbsSelector">게시판 선택</label></th>
				<td>
					<input type="button" id="bbsSelector" onclick="getBbsInfoPop();" value="선택" class="btn_sblack"/>
					<input type="hidden" name="bbsMstIdx" id="bbsMstIdx" value="0"/>
					<input type="hidden" name="bbsIdx" id="bbsIdx" value="0"/>
					<span class="f11">선택된 게시판 : </span>
					<span id="bbsName" class="blue">게시판을 선택하세요.  <input type="button" id="init_bbs" class="btn_dele" onclick="initBbs();" style="display: none;"/></span>
				</td>
			</tr>
			<tr id="programSelectorBox">
				<th scope="row"><label for="programUrl">프로그램 URL</label></th>
				<td>
					<input type="text" name="programUrl" id="programUrl" class="input500"/>
				</td>
			</tr>
			<tr id="linkSelectorBox">
				<th scope="row"><label for="linkUrl">링크 URL</label></th>
				<td>
					<input type="text" name="linkUrl" id="linkUrl" class="input500"/>
				</td>
			</tr>
			<tr style="display: none;">
				<th scope="row">CCL 유형</th>
				<td>
					<c:set var="cclTypeList" value="<%=CclType.values()%>"/>
					<ul>
						<c:forEach var="t" items="${cclTypeList}" varStatus="s">
							<li>
								<input type="radio" id="cclType${s.count}" name="cclType" value="${t.code}" <c:if test="${s.count == 1}">checked="checked"</c:if>>
								<label for="cclType${s.count}">${t.desc}</label>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr style="display: none;">
				<th scope="row">공공누리 유형</th>
				<td>
					<c:set var="nuriTypeList" value="<%=NuriType.values()%>"/>
					<ul>
						<c:forEach var="t" items="${nuriTypeList}" varStatus="s">
							<li>
								<input type="radio" id="nuriType${s.count}" name="nuriType" value="${t.code}" <c:if test="${s.count == 1}">checked="checked"</c:if>>
								<label for="nuriType${s.count}">${t.desc}</label>
								<br/>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr style="display:none;">
				<th scope="row"><label>설문 응답 기능 포함여부</label></th>
				<td>
					<input type="radio" name="isPoll" id="isPollY" value="Y"><label for="isPollY">포함</label>
					<input type="radio"	name="isPoll" id="isPollN" value="N" checked><label for="isPollN">비포함</label>
				</td>
			</tr>
			<tr>
				<th scope="row"><label>메뉴 활성화 여부</label></th>
				<td>
					<input type="radio" name="isUse" id="isUseY" value="Y" checked><label for="isUseY">활성화</label>
					<input type="radio"	name="isUse" id="isUseN" value="N"><label for="isUseN">비활성화</label>
				</td>
			</tr>
			<tr>
				<th scope="row"><label>사용 기간 설정 여부</label></th>
				<td>
					<input type="radio" onclick="showTermControlBox('Y');" name="isTermset" id="isTermsetY" value="Y" ><label for="isTermsetY">활성화</label>
					<input type="radio" onclick="showTermControlBox('N');" name="isTermset" id="isTermsetN" value="N" checked><label for="isTermsetN">비활성화</label>
				</td>
			</tr>
			<tr id="termDtBox">
				<th scope="row"><label for="termStartDt">사용 기간 설정</label></th>
				<td>
					<input type="text" name="termStartDtStr" id="termStartDtStr" class="input100" value="" readonly="true"/> ~
					<input type="text" name="termEndDtStr" id="termEndDtStr" class="input100" value="" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th scope="row">소셜 댓글 사용 여부</th>
				<td>
					<input type="radio" name="isSnsComment" id="isSnsComment0" value="N" checked><label for="isSnsComment0">비활성화</label>
					<input type="radio"	name="isSnsComment" id="isSnsComment1" value="Y"><label for="isSnsComment1">활성화</label>
				</td>
			</tr>
			</tbody>
		</table>

		<div class="mT20">
			<h3><span id="menuTitle">관리 담당자</span></h3>
			<div class="taR">
				<input type="button" id="staffSelector1" onclick="setEditMode();" value="정렬편집" class="btn_swhite"/>
				<input type="button" id="staffSelector3" onclick="getStaffInfoPop('only');" value="담당자 추가" class="btn_swhite"/>
				<input type="button" id="staffSelector4" onclick="getStaffHistory('${searchVO.siteCode}');" value="담당자이력" class="btn_swhite"/>
			</div>
			<table class="tableSt_list row_over mT10" id="chargeTable">
				<caption>관리 담당자 리스트</caption>
				<colgroup>
					<col span="1" style="width: 10%;">
					<col span="1" style="width: 15%;">
					<col span="1" style="width: 15%;">
					<col span="1" style="width: 15%;">
					<col span="1" style="width: 10%;">
					<col span="1" style="width: 10%;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">담당자</th>
						<th scope="col">부서</th>
						<th scope="col">전화번호</th>
						<th scope="col">정렬순서</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="6">등록된 담당자가 없습니다.</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="btn_boxR">
			<input type="button" id="orderMoveUp" onclick="moveMenuUp()" class="btn_dblue" value="메뉴순서올림"/>
			<input type="button" id="orderMoveDown" onclick="moveMenuDown()" class="btn_dblue" value="메뉴순서내림"/>
			<input type="button" onclick="deleteMenu()" id="deleteBtn" class="btn_dblue" value="메뉴삭제"/>
			<input type="submit" id="submitBtn"  class="btn_cyan"  value="확인"/>
		</div>
	</div>
</form>
<div style="clear:both"></div>
<!-- 담당자이력 정보 창 -->
<div id="dialogChargeHistoryList" title="담당자 이력정보">
  <form id="chargeHistoryFrm" name="chargeHistoryFrm" onsubmit="return false">
    <fieldset>
    <div style="overflow:auto; height:400px;">
     <table id="chargeInfoList" class="tableSt_list row_over">
		<colgroup>
	        <col span="1" />
	        <col span="1" style="width:20%"/>
	    </colgroup>
	   	<thead>
	   		<tr>
	   			<th class="taC">담당자</th>
	   			<th class="taC">아이디</th>
	   			<th class="taC">부서</th>
	   			<th class="taC">부서코드</th>
	   			<th class="taC">등록일</th>
	   		</tr>
	   	</thead>
	    <tbody>

	    </tbody>
	</table>
	</div>
    </fieldset>
  </form>
</div>
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

<c:set var="domain" value="${yh:isLiveMode() ? yh:getProperty('document.domain') : 'localhost' }" />
<script type="text/javascript">
try {
	document.domain = "${domain}";	//CMS연동시 관련도메인으로 변경  CMS도메인과 관리도메인이 동일하여야함(단, 서브도메인은 제외)
} catch (e) {
	alert("document.domain 세팅이 되어있지 않습니다. 세팅을 확인하세요.");
}
var menuMng = {edit : false, siteCode : "${searchVO.siteCode}", cmsUrl : "${yh:getProperty('ySmartCMS.url')}", cmsKey : "${sessionScope.cmsLoginKey}"};
var map_all = new Object(); // 대표(메인/열린시장실)
var map_main = new Object(); // 대표(메인/열린시장실)
var map_dept = new Object(); // 부서및빌트인
var map_csc = new Object(); // 읍면동
var map_etc = new Object(); // 기타

$(document).ready(function(){

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
});
</script>
<link rel="stylesheet" type="text/css" href="${_context}/sys/css/jstree/style.min.css"/>
<script type="text/javascript" src="${_context}/sys/js/jstree/jstree.min.js"></script>
<script type="text/javascript" src="${_context}/common/js/util.js"></script>
<script type="text/javascript" src="${_context}/sys/js/totalAdminMng/menuMng/menuMng.js<%-- ?<%=System.currentTimeMillis() %> --%>"></script>
