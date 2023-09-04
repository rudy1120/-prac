<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 사이트 관리 등록
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.10.07		엄동건			최초 생성 및 코드 수정
	 * 2017.04.24		상천규			사이트 구분값, 담당부서 추가
	 * 2017.06.19		J.Ryeon Lee		사이트 그룹 입력 필드 추가
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.06.15
	 */
%>
<script type="text/javascript">
	//<![CDATA[
	$(document).ready(function(){
		$("#siteName").focus();
		$("#submitBtn").click(function() {
			var options = {
				url: "${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/insertProc.do",
				type: "post",
				beforeSubmit: validateForm,
				success: resultResponse,
				dataType: 'json',
				resetForm: false
			};

			$("#frm").ajaxForm(options);
		});

		function validateForm() {
			if(!$("#siteName").val()) {
				alert("사이트명은 필수 입력항목입니다.");
				return false;
			} else if(!$("#siteCode").val()) {
				alert("사이트코드는 필수 입력항목입니다.");
				$("#siteCode").focus();
				return false;
			} else if(!$("#siteOrder").val()) {
				alert("정렬순서 필수 입력항목입니다.");
				$("#siteOrder").focus();
				return false;
			}
		}

		function resultResponse(data) {
			if(data.flag == "success") {
				alert(data.message);
				goList();
			} else {
				alert(data.message);
			}
		}
	});

	function goList(){
		var frm = document.getElementById('frm');
		frm.action = '${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do';
		frm.submit();
	}
	//]]>
</script>

<h2>사이트 등록</h2>
<form id="frm" name="frm" method="post" action="${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/insertProc.do">
	<table class="tableSt_write">
		<input type="hidden" name="page" id="page" value="${searchVO.page}" />
		<input type="hidden" name="mId" id="mId" value="${curMid}" />
		<caption>사이트정보 등록</caption>
		<colgroup>
			<col span="1" style="width:20%;"/>
			<col span="1" style="width:80%;"/>
		</colgroup>
		<tbody>
		<tr>
			<th scope="row"><span class="red">*</span>사이트명</th>
			<td><input type="text" id="siteName" name="siteName" title="사이트명" value=""/></td>
		</tr>
		<tr>
			<th scope="row">사이트 그룹</th>
			<td>
				<input type="text" id="siteGroup" name="siteGroup" title="사이트 그룹"/>
				<span class="tip">2뎁스 사이트인 경우에만 입력하세요.</span>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="red">*</span>사이트 코드</th>
			<td><input type="text" id="siteCode" name="siteCode" title="사이트코드" value="" style="ime-mode:inactive;"/></td>
		</tr>
		<tr>
			<th scope="row"><span class="red">*</span>정렬 순서</th>
			<td><input type="text" id="siteOrder" name="siteOrder" title="정렬순서" value="" onKeyPress="onlyNumber2();" onBlur="onlyNumber2(this);"/></td>
		</tr>
		</tr>
		<tr>
			<th scope="row"><span class="red">*</span>구분</th>
			<td>
				<select id="siteGubun" name="siteGubun" title="구분값을 선택하세요." data-validator="on" data-required="y" data-fieldName="구분">
					<option value="">선택</option>
					<c:forEach var="gubunCode" items="${siteGubunList}" >
						<option value="${gubunCode.code}">
							${fn:replace(gubunCode.name, ' ', '&nbsp;')}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row">부서</th>
			<td>
				<select id="siteDeptCode" name="siteDeptCode" title="부서를 선택하세요." data-validator="on" data-required="y" data-fieldName="부서">
					<option value="">선택</option>
					<c:forEach var="code" items="${deptList}" >
						<option value="${code.deptId}"
							<c:if test="${fn:indexOf(code.fullChildPath, sessionScope.ADMIN_LOGIN_INFO.deptId) > 0}">selected="selected"</c:if>
						>
							${fn:replace(code.deptName, ' ', '&nbsp;')}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<!-- <tr>
			<th scope="row">PT_IDX</th>
			<td><input type="text" id="ptIdx" name="ptIdx" title="PT_IDX" value=""/></td>
		</tr>
		<tr>
			<th scope="row">TM_IDX</th>
			<td><input type="text" id="tmIdx" name="tmIdx" title="TM_IDX" value=""/></td>
		</tr> -->
		<%--
		 <tr>
			<th scope="row">비고</th>
			<td><textarea id="orderInfo" name="orderInfo" rows="25" class="input99"></textarea></td>
		</tr>
		 --%>
		<tr>
			<th scope="row"><span class="red">*</span>사용 여부</th>
			<td>
				<input type="radio" id="isUseY" name="isUse" value="Y" checked /><label for="isUseY">사용</label>
				<input type="radio" id="isUseN" name="isUse" value="N" /><label for="isUseN">사용안함</label>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="btn_boxR">
		<input type="submit" id="submitBtn" value="등록" class="btn_cyan"/>
		<input type="button" onclick="document.location.href='${pageContext.request.contextPath}/sys/totalAdminMng/siteMng/list.do?mId=${curMid}';" value="취소" class="btn_dblue"/>
	</div>
</form>
