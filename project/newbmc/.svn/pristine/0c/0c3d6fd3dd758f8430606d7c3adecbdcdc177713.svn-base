<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 외부 관리자 정보 등록/수정
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.09.22		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.09.22
	 */
%>
<c:set var="isNotLoginable" value="${5 <= searchVO.loginFailedCnt}" />
<c:set var="isNew" value="${empty searchVO.id}" />
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}" />

<h2>외부 관리자 정보 등록/수정</h2>
<form:form commandName="searchVO" name="listForm" id="listForm" action="/sys/externalAdminMng/list.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="searchTxt" />
	<form:hidden path="searchType" />
	<form:hidden path="page" />
</form:form>
<form:form commandName="searchVO" name="writeForm" id="writeForm" action="/sys/externalAdminMng/${postUrl}.do?mId=${menuVO.mId}" methodParam="post" onsubmit="return validator() && additional_validator();" htmlEscape="false">
	<form:hidden path="id" />
	<p class="info">상세 확인 및 등록/수정이 가능합니다. <span class="red">*</span>는 필수 항목입니다.</p>
	<p class="pL20 mL20 mB10 red">외부 관리자는 IP 검증 없이 관리자 메뉴에 접근할 수 있습니다. 꼭 필요한 관리자만 등록해주세요.</p>

	<table class="tableSt_write mB10">
		<caption>외부 관리자 정보 등록/수정. 아이디, 수요자구분 상세 및 이름, 사용여부 항목별 입력을 제공하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width:15%" />
			<col span="1" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span><label for="adminId">외부 관리자 ID</label></th>
				<td>
					<c:if test="${isNew}">
						<form:input path="adminId" maxlength="50" data-validator="on" data-required="y" data-fieldName="외부 관리자 ID" />
					</c:if>
					<c:if test="${!isNew}">
						${searchVO.adminId}
						<form:hidden path="adminId"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="name">외부 관리자명</label></th>
				<td><form:input path="name" maxlength="50" data-validator="on" data-required="y" data-fieldName="외부 관리자명" /></td>
			</tr>
			<tr>
				<th scope="row"><c:if test="${isNew}"><span class="red">*</span></c:if><label for="pwd">비밀번호</label></th>
				<td>
					<input type="password" id="pwd" name="pwd" maxlength="20" class="mB5" data-validator="on" data-fieldName="비밀번호" <c:if test="${isNew}">data-required="y"</c:if> />
					<c:if test="${not isNew}"><span class="tip">※ 비밀번호를 변경하시는 경우에만 입력해주세요.</span></c:if>
					<p class="red">※ 비밀번호는 특수 문자, 숫자, 영문자의 조합으로 구성된 9자리 이상의 문자열이어야 합니다.</p>
					<p class="red">※ 사용 가능한 특수 문자는 <strong>~!@#$%^*?_+=;:</strong> 입니다.</p>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="adminTypeCode">관리자 타입</label></th>
				<td>
					<c:if test="${isNew}">
						<form:select path="adminTypeCode" data-validator="on" data-required="y" data-fieldName="관리자 타입">
							<form:option value="">선택</form:option>
							<c:forEach var="element" items="${adminTypeList}">
								<c:if test="${element.code != '0'}">
									<form:option value="${element.code}">${element.name}</form:option>
								</c:if>
							</c:forEach>
						</form:select>
					</c:if>
					<c:if test="${!isNew}">
						<c:forEach var="element" items="${adminTypeList}">
							<c:if test="${element.code == searchVO.adminTypeCode}">${element.name} 외부 관리자</c:if>
						</c:forEach>
						<form:hidden path="adminTypeCode"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="ptIdxList">게시판 코드</label></th>
				<td>
					<span id="bbs_guide">
						<c:if test="${empty searchVO.ptIdxs}">선택된 게시판이 없습니다.</c:if>
						<c:if test="${not empty searchVO.ptIdxs}">${fn:length(fn:split(searchVO.ptIdxs, ","))}개의 게시판이 선택되어 있습니다.</c:if>
					</span>
					<a href="#" onclick="modal.open(); return false;" class="btn_white">게시판 검색</a>
					<form:hidden path="ptIdxs"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="red">*</span><label for="midList">메뉴 ID 목록</label></th>
				<td>
					<div id="folderBox" class="folder_box_pop">
						<ul id="browser" class="filetree">
							<c:forEach var="depth1" items="${menuList}" varStatus="depth1Seq">
								<li class="closed">
									<span class="folder">
										<label for="${depth1.mId}">${depth1.menuName}</label>
										<form:checkbox id="${depth1.mId}" path="midList" value="${depth1.mId}" />
									</span>
									<c:if test="${not empty depth1.depth2MenuList}">
										<ul>
											<c:forEach var="depth2" items="${depth1.depth2MenuList}">
												<c:set var="treeClass2" value="folder"/>
												<c:if test="${empty depth2.depth3MenuList}"><c:set var="treeClass2" value="file"/></c:if>

												<li class="closed">
													<span class="${treeClass2}">
														<label for="${depth2.mId}">${depth2.menuName}</label>
														<form:checkbox id="${depth2.mId}" path="midList" value="${depth2.mId}" />
													</span>
												<c:if test="${not empty depth2.depth3MenuList}">
													<ul>
														<c:forEach var="depth3" items="${depth2.depth3MenuList}">
															<c:set var="treeClass3" value="folder"/>
															<c:if test="${empty depth3.depth4MenuList}"><c:set var="treeClass3" value="file"/></c:if>
															<li class="closed">
																<span class="${treeClass3}">
																	<label for="${depth3.mId}">${depth3.menuName}</label>
																	<form:checkbox id="${depth3.mId}" path="midList" value="${depth3.mId}" />
																</span>
																<c:if test="${not empty depth3.depth4MenuList}">
																	<ul>
																		<c:forEach var="depth4" items="${depth3.depth4MenuList}">
																			<li class="closed">
																				<span class="file">
																					<label for="${depth4.mId}">${depth4.menuName}</label>
																					<form:checkbox id="${depth4.mId}" path="midList" value="${depth4.mId}" />
																				</span>
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
									</c:if>
								</li>
							</c:forEach>
						</ul>
					</div>
					<p class="check mT10">
						특별한 경우를 제외하고 [마이페이지 > 정보수정] 메뉴에 대한 접근 권한은 공통적으로 설정해주세요.<br/>
						비밀번호 유효 기간 만료시 외부 관리자 본인이 계정을 수정할 수 있습니다.
					</p>
				</td>
			</tr>
			<c:if test="${!isNew}">
				<tr>
					<th scope="row">상태</th>
					<td>${isNotLoginable ? '<span class="red">로그인 제한</span>' : '로그인 가능'}</td>
				</tr>
				<tr>
					<th scope="row">최종 로그인 일자</th>
					<td><fmt:formatDate value="${searchVO.lastestLoginDt}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</c:if>
		</tbody>
	</table>

	<div class="btn_boxR">
		<c:if test="${isNotLoginable}">
			<a href="#" onclick="fn_reset_login_failed_cnt(); return false;" class="btn_cyan">로그인 제한 해제</a>
		</c:if>
		<input type="submit" id="submitBtn" value="등록" class="btn_cyan" />
		<a href="#" onclick="document.getElementById('listForm').submit(); return false;" class="btn_dblue">취소</a>
	</div>

</form:form>

<jsp:include page="/sys/jsp/sysAuth/external/bbsModal.jsp"/>

<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<c:if test="${isNotLoginable}">
<script type="text/javascript">

	function fn_reset_login_failed_cnt() {
		if (confirm("로그인 실패 횟수를 초기화하시겠습니까?")) {
			$.ajax({
				type: "post", dataType: "json", url: "/sys/externalAdminMng/resetLoginFailedCntProc.do", data: { id: "${searchVO.id}" },
				success: function(result) {
					if (result.success) {
						alert("로그인 실패 횟수를 초기화했습니다.");
						location.href = "/sys/externalAdminMng/list.do?mId=${menuVO.mId}";
					} else {
						alert("처리 중 오류가 발생했습니다.");
					}
				}
			}).fail(function(e) { alert("서버와 통신 중 오류가 발생했습니다."); });
		}
	}

</script>
</c:if>
<script type="text/javascript">
//<![CDATA[

	$(document).ready(function() {
		$ ("input[type=checkbox]").click(function() {
			if($(this).is(":checked")) {
				//상위 트리 선택
				if ($(this).closest("ul").attr("id")!="browser") {
					var _step1=$(this).closest("ul").parent().find("span input[type=checkbox]:eq(0)").each(function(){this.checked=true;}).closest("ul");

					if ($(_step1).attr("id")!= "browser") {
						$(_step1).parent().find("span input[type=checkbox]:eq(0)").each(function(){this.checked=true;});
					}

				}

				//하위 트리 선택
				$(this).closest("li").find("span input[type=checkbox]").each(function(){this.checked=true;});
			} else {
				if ($(this).closest("li").siblings().find("span input[type=checkbox]:checked").length==0) {
					//상위 트리 취소
					$(this).closest("ul").prev().find("span input[type=checkbox]").eq(0).attr("checked",false);
					if($(this).closest("ul").closest("li").siblings("li").find("span input[type=checkbox]:checked").length==0){
						var dept2 = $(this).closest("ul");
						var dept1 = $(dept2).parent();
						$(dept1).find("span input[type=checkbox]").eq(0).attr("checked",false);
					}
				}

				//하위 트리 취소
				$(this).closest("li").find("span input[type=checkbox]").each(function(){this.checked=false;});
			}
		});
		$("#browser").treeview({
			toggle: function() {
				//console.log("%s was toggled.", $(this).find(">span").text());
			}
		});
	});

	function additional_validator() {
		if ($("[name=midList]:checked").length == 0) {
			alert("허가할 메뉴를 최소 1개 이상 선택해주세요.");
			return false;
		}

		if ($("#pwd").val()) {
			return pw_validator("#pwd" ,"#pwd");
		}

		return true;
	}

	function open_menu_popup() {
		window.open("/sys/externalAdminMng/listPop.do", "메뉴 선택", "width=730,height=600,top=100,left=100,scrollbars=yes,resizable=yes");
	}

	$(document).ready(function(){
		$("#submitBtn").click(function() {
			var options = {
				url: "/sys/externalAdminMng/${postUrl}.do",
				type: "post",
				success: function(result) {
					if (result.success) {
						alert("외부 관리자 정보를 ${isNew ? '등록' : '수정'}했습니다.");
						location.href = "/sys/externalAdminMng/list.do?mId=${menuVO.mId}";
					} else {
						if (result.errCode == "NOT_UNIQUE") {
							alert("이미 사용 중인 ID입니다.");
							$("#adminId").focus();
						} else {
							alert("처리 중 오류가 발생했습니다.");
						}
					}
				},
				dataType: 'json',
				resetForm: false
			};

			$("#writeForm").ajaxForm(options);
		});
	});

//]]>
</script>
