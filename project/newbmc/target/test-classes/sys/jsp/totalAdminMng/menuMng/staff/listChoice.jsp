<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파 일 명 : listChoice.jsp
* 설       명 : 직원정보 선택 리스트
* 작   성  자 : 김장섭
* 작   성  일 : 2016.06.14
*********************************************************************
--%>
<script type="text/javascript" src="/sys/js/treeView/jquery.treemenu.js" charset="UTF-8"></script>
<link rel="stylesheet" type="text/css" href="/sys/css/treeView/jquery_treemenu.css" media="all" />
<link rel="stylesheet" type="text/css" href="/sys/css/treeView/jquery_tree_taxlist.css" media="all" />
<script language="javascript">window.focus();</script>
<h2>담당자 찾기</h2>
<div class="box_tip">
	왼쪽 트리에서 탐색하거나, 부서명을 직접 입력하여 부서를 찾아 선택하고 [화살표]를 클립합니다.
</div>

<div class="box_staff">
	<div class="box_popgray left">
		<h4>조직도</h4>
		<%-- 검색카테고리 --%>
		<div class="mT5">
			<form id="searchform" method="post" action="" onsubmit="return orgTreeSearch('BMC');">
				<select id="searchCondition">
					<option selected value="0">이름</option>
					<option value="1">부서명</option>
				</select>
				<input type="text" name="keyword" id="keyword" class="input100" title="검색어를 입력하세요"/>
				<input type="submit" class="btn_sblack" value="검색" title="검색"/>
				<input type="button" class="btn_sblack" value="초기화" title="검색결과초기화" onclick="form_reset();return false;"/>
			</form>
		</div>
		<%-- 조직도 트리구조나오는곳 --%>
		<div class="box_popscroll">
			<div>
				<ul class="treeview" id="tree1"></ul>
			</div>
		</div>
	</div>

	<div class="box_popgray right">
		<h4>직원</h4>
		<%-- 직원리스트나오는곳 --%>
		<div class="box_h282px">
			<ul class="list_table" id="selectOrg">
				<li class="no_data">선택된 항목이 없습니다.</li>
			</ul>
		</div>
	</div>
	<div class="cleB"></div>
</div>

<div class="btn_boxR">
 	<a href="#" onclick="setStaffOnlyCodeInfo('${param.openType}');return false;" class="btn_cyan"><span class="check"></span>확인<span class="bg"></span></a>
 	<a href="javascript:self.close();"  class="btn_cyan"><span class="del"></span>취소<span class="bg"></span></a>
</div>
<c:set var="domain" value="${yh:isLiveMode() eq true ? yh:getProperty('document.domain') : 'localhost' }" />
<script src="/sys/js/staffOnlySelect.js<%-- ?<%=System.currentTimeMillis() %> --%>" type="text/javascript"></script>
<script type="text/javascript">
//<![CDATA[
	makeOrgTree('BMC');
try {
	document.domain = "${domain}";
} catch (e) {
	alert("document.domain 세팅이 되어있지 않습니다. 세팅을 확인하세요.");
}
//initUserInfo();
//]]>
</script>