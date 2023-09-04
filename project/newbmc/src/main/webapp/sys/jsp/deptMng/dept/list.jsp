<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<h2>부서 목록</h2>
<form:form commandName="searchVO" id="list" name="list" methodParam="post" onsubmit="$('#page').val(1);" htmlEscape="false">
	<form:hidden path="depCode"/>
	<form:hidden path="page"/>

	<%-- ============================ 검색 영역 ============================ --%>
	<div class="search_box">
		<div class="fL">
			<form:select path="searchType">
				<form:option value="nm">부서명</form:option>
			</form:select>
			<form:input type="text" path="searchTxt"/>
			<input type="button" onclick="return goPage(1);" class="btn_white" value="검색"/>
		</div>
		<div class="btn_boxR">
			<a href="#" data-action="/sys/deptMng/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
		</div>
		<div class="cleB"></div>
	</div>
	
	<%-- =========================== 현재 depth 최고 3 // 늘어날 경우 추가 수정 --%>
	<%-- ============================ 결과 영역 ============================ --%>
	<table class="tableSt_list row_over">
		<thead>
			<tr>
				<th scope="col" class="w10">부서코드</th>
				<th scope="col" class="w20">부서명</th>
				<th scope="col">부서별 업무</th>
				<th scope="col" class="w10">사용여부</th>
				<th scope="col" class="w10">수정일</th>
				<th scope="col" class="w10">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${searchVO.searchTxt == ''}">
			
			<c:forEach var="depth1" items="${result}" varStatus="status">
				<c:if test="${depth1.newDepth == 1}">
					<tr>
						
						<td>${depth1.depCode}</td>
						<td style="text-align:left;font-weight:600;">${depth1.newSname}</td>
						<td>${depth1.depComment}</td>
						<td>${depth1.useYn == 'Y' ? '사용' : '미사용'}</td>
						<td>${depth1.dtUpdate}</td>
						<td>
							<a href="#" data-action="/sys/deptMng/modify.do" data-keyset="{'depCode': '${depth1.depCode}'}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
							<a href="#" data-action="/sys/deptMng/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'depCode': '${depth1.depCode}', 'useYn': '${depth1.useYn}'}" onclick="req.ajax(this); return false;" class="btn_sblack">${depth1.useYn == 'Y' ? '삭제' : '복구'}</a>
						</td>
					</tr>
					<c:forEach var="depth2" items="${result}">
						 <c:if test="${depth2.newDepth == 2 and depth1.depCode == depth2.newParent}">
						 	<tr>
								<td>${depth2.depCode}</td>
								<td style="text-align:left;">${depth2.newSname}</td>
								<td>${depth2.depComment}</td>
								<td>${depth2.useYn == 'Y' ? '사용' : '미사용'}</td>
								<td>${depth2.dtUpdate}</td>
								<td>
									<a href="#" data-action="/sys/deptMng/modify.do" data-keyset="{'depCode': '${depth2.depCode}'}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
									<a href="#" data-action="/sys/deptMng/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'depCode': '${depth2.depCode}', 'useYn': '${depth2.useYn}'}" onclick="req.ajax(this); return false;" class="btn_sblack">${depth2.useYn == 'Y' ? '삭제' : '복구'}</a>
								</td>
							</tr>
							<c:forEach var="depth3" items="${result}">
								<c:if test="${depth3.newDepth == 3 and depth2.depCode == depth3.newParent}">
									<tr>
										<td>${depth3.depCode}</td>
										<td style="text-align:left;padding-left:10px;"> - ${depth3.newSname}</td>
										<td>${depth3.depComment}</td>
										<td>${depth3.useYn == 'Y' ? '사용' : '미사용'}</td>
										<td>${depth3.dtUpdate}</td>
										<td>
											<a href="#" data-action="/sys/deptMng/modify.do" data-keyset="{'depCode': '${depth3.depCode}'}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
											<a href="#" data-action="/sys/deptMng/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'depCode': '${depth3.depCode}', 'useYn': '${depth3.useYn}'}" onclick="req.ajax(this); return false;" class="btn_sblack">${depth3.useYn == 'Y' ? '삭제' : '복구'}</a>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
			</c:if>
			<c:if test="${searchVO.searchTxt != ''}">
				<c:forEach var="element" items="${result}" varStatus="status">
					<tr>
						<td>${element.depCode}</td>
						<td style="text-align:left;">${element.newSname}</td>
						<td>${element.depComment}</td>
						<td>${element.useYn == 'Y' ? '사용' : '미사용'}</td>
						<td>${element.dtUpdate}</td>
						<td>
							<a href="#" data-action="/sys/deptMng/modify.do" data-keyset="{'depCode': '${element.depCode}'}" onclick="req.post(this); return false;" class="btn_swhite">수정</a>
							<a href="#" data-action="/sys/deptMng/deleteProc.do?mId=${menuVO.mId}" data-keyset="{'depCode': '${element.depCode}'}" onclick="req.ajax(this); return false;" class="btn_sblack">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	
	<c:if test="${empty result}">
		<div class="no_data">등록된 데이터가 없습니다.</div>
	</c:if>

	<div class="btn_boxR">
		<a href="#" data-action="/sys/deptMng/write.do" onclick="req.post(this); return false;" class="btn_cyan">등록</a>
	</div>
	
</form:form>
<script>

</script>
<script type="text/javascript"> yh.siteCode = "sys"; </script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
