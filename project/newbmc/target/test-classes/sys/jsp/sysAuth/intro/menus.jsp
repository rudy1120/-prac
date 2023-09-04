<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 즐겨찾기 메뉴 추가 팝업창
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.04.18		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.04.18
	 */
%>

<script type="text/javascript">
//<![CDATA[

	$(document).ready(function() {
		$("#browser").treeview({
			collapsed: false,
			toggle: function() {
				//console.log("%s was toggled.", $(this).find(">span").text());
			}
		});

		$("#saveBtn").click(function() {
			if ($("[name=mIds]:checked").length == 0) {
				alert("메뉴를 선택해주세요.");
			} else {
				var menu_info = {
					mId : null,
					title : null,
					url : null,
				};
				$("[name=mIds]:checked").each(function(i) {
					menu_info.mId = $(this).val();
					menu_info.title = $("#title" + menu_info.mId).val();
					menu_info.url = $("#url" + menu_info.mId).val();
					localStorage[localStorage.length + i] = JSON.stringify(menu_info);
				});
				alert("즐겨찾기를 추가했습니다.");
				window.opener.location.reload(true);
				window.close();
			}
		});

		$("#delBtn").click(function() {
			if (confirm("현재 등록되어 있는 즐겨찾기 메뉴를 리셋합니다.")) {
				localStorage.clear();
				alert("즐겨찾기 메뉴를 전체 리셋했습니다.");
			}
		});
	});

//]]>
</script>

<form name="frm" id="frm" action="/sys/sysMemberMng/auth/sysmenu/insertMenuAuthProc.do" method="post">
	<input type="hidden" id="deptId" name="deptId" value="${searchVO.deptId}"/>
	<h2>즐겨찾는 메뉴 선택하기</h2>
	<p class="info">※ 체크박스를 클릭하신 후 <span style="color:red">등록</span>버튼을 클릭하시면 즐겨찾는 메뉴에 해당 메뉴가 등록됩니다.</p>

	<div id="folderBox" class="folder_box_pop">
		<ul id="browser" class="filetree">
			<c:forEach var="depth1" items="${menuList}" varStatus="depth1Seq">
				<li class="closed">
					<span class="folder">
						<label for="${depth1.mId}">${depth1.menuName}</label>
						<input type="checkbox" name="mIds" value="${depth1.mId}" />
						<input type="hidden" id="title${depth1.mId}" value="${depth1.menuName}" />
						<input type="hidden" id="url${depth1.mId}" value="${depth1.programUrl}" />
					</span>

					<c:if test="${not empty depth1.depth2MenuList}">
						<ul>
							<c:forEach var="depth2" items="${depth1.depth2MenuList}">
								<c:set var="treeClass2" value="folder"/>
								<c:if test="${empty depth2.depth3MenuList}"><c:set var="treeClass2" value="file"/></c:if>

								<li class="closed">
									<span class="${treeClass2}">
										<label for="${depth2.mId}">${depth2.menuName}</label>
										<input type="checkbox" id="${depth2.mId}" name="mIds" value="${depth2.mId}" />
										<input type="hidden" id="title${depth2.mId}" value="${depth2.menuName}" />
										<input type="hidden" id="url${depth2.mId}" value="${depth2.programUrl}" />
									</span>
									<c:if test="${not empty depth2.depth3MenuList}">
										<ul>
											<c:forEach var="depth3" items="${depth2.depth3MenuList}">
												<c:set var="treeClass3" value="folder"/>
												<c:if test="${empty depth3.depth4MenuList}"><c:set var="treeClass3" value="file"/></c:if>
												<li class="closed">
													<span class="${treeClass3}">
														<label for="${depth3.mId}">${depth3.menuName}</label>
														<input type="checkbox" id="${depth3.mId}" name="mIds" value="${depth3.mId}" />
														<input type="hidden" id="title${depth3.mId}" value="${depth3.menuName}" />
														<input type="hidden" id="url${depth3.mId}" value="${depth3.programUrl}" />
													</span>
													<c:if test="${not empty depth3.depth4MenuList}">
														<ul>
															<c:forEach var="depth4" items="${depth3.depth4MenuList}">
																<li class="closed">
																	<span class="file">
																		<label for="${depth4.mId}">${depth4.menuName}</label>
																		<input type="checkbox" id="${depth4.mId}" name="mIds" value="${depth4.mId}" />
																		<input type="hidden" id="title${depth4.mId}" value="${depth4.menuName}" />
																		<input type="hidden" id="url${depth4.mId}" value="${depth4.programUrl}" />
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
	<div class="btn_boxR">
		<a href="#" id="saveBtn" class="btn_cyan">등록</a>
		<a href="#" id="delBtn" class="btn_dblue">전체삭제</a>
	</div>
</form>
