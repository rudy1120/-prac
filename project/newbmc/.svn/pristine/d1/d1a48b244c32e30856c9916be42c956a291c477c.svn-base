<%--
***************************************************************
* 파  일  명 : sysLeftMenu.jsp
* 설      명 : 레이아웃 - 관리자_관리자메뉴 왼쪽 소메뉴
* 작  성  자 : 엄동건
* 작  성  일 : 2014.10.20
***************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<script type="text/javascript">
	function bbsPartChange(obj){
		document.location.href = obj.options[obj.selectedIndex].value; //selected.val();

	}
	$(document).ready(function() {
		$('#leftMenu > li > div >ul').slideToggle("500");
	});
	function setToggle(obj){
		$(obj).next("div").slideToggle("500");
	}
</script>
<%-- 왼쪽 메뉴시작 선택시 a태그 class="on" --%>
<div class="box_snb">
	<c:choose>
		<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_hr' or 'bmc_human'}">
			<h2>관리자 페이지</h2>
		</c:when>

		<c:otherwise>			
			<h2>${parentVO.menuName }</h2>
		</c:otherwise>
	</c:choose>
	<ul class="snb">
		<c:forEach var="depth1" items="${menuList}" varStatus="depth1Seq">
			<c:if test="${fn:substring(depth1.mId, 0, 2) == fn:substring(curMid, 0, 2)}">
				<c:choose>
						<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_hr'}">
							<c:if test="${curMid eq 0121010000}">
								<li>
									<a href="/sys/sysContents.do?mId=0121010000" class="on">채용응시원서 접수 관리</a>
								</li>
								<li>
									<a href="/sys/sysContents.do?mId=0112010000">마이페이지</a>
								</li>
							</c:if>
							<c:if test="${curMid eq 0112010000}">
								<li>
									<a href="/sys/sysContents.do?mId=0121010000">채용응시원서 접수 관리</a>
								</li>
								<li>
									<a href="/sys/sysContents.do?mId=0112010000" class="on">마이페이지</a>
								</li>
							</c:if>
						</c:when>
						<c:when test="${ADMIN_LOGIN_INFO.adminId eq 'bmc_human'}">
							<c:if test="${curMid eq 0122010000}">
								<li>
									<a href="/sys/sysContents.do?mId=0122010000" class="on">인권침해 신고센터 관리</a>
								</li>
								<li>
									<a href="/sys/sysContents.do?mId=0112010000">마이페이지</a>
								</li>
							</c:if>
							<c:if test="${curMid eq 0112010000}">
								<li>
									<a href="/sys/sysContents.do?mId=0122010000">인권침해 신고센터 관리</a>
								</li>
								<li>
									<a href="/sys/sysContents.do?mId=0112010000" class="on">마이페이지</a>
								</li>
							</c:if>
						</c:when>
						<c:otherwise>
						
							<c:forEach var="depth2" items="${depth1.depth2MenuList}">
								<c:if test="${fn:substring(depth2.mId, 0, 4) == fn:substring(curMid, 0, 4)}">
									<c:set var="parentProgram" value="${depth2.programUrl}"/>
			
			
									<c:forEach var="depth3" items="${depth2.depth3MenuList}">
										<c:set var="ProgramID" value=""/>
										<li>
			
											<c:choose>
												<c:when test="${empty depth3.programUrl}">
													<c:set var="attributes" value='href="javascript:alert(\'작업중입니다.\');" '/>
												</c:when>
												<c:otherwise>
													<c:set var="url_replace" value="${pageContext.request.contextPath}${depth3.programUrl}"/>
													<c:if test="${fn:length(depth3.depth4MenuList) == 1}">
														<c:set var="url_replace" value="${pageContext.request.contextPath}${depth3.depth4MenuList[0].programUrl}"/>
													</c:if>
													<c:if test="${depth3.target eq '_self'}">
														<c:choose>
															<c:when test="${fn:indexOf(depth3.programUrl,'?')>-1}">
																<c:set var="url_replace" value="/sys/sysContents.do?mId=${depth3.mId}"/>
															</c:when>
															<c:otherwise>
																<c:set var="url_replace" value="/sys/sysContents.do?mId=${depth3.mId}"/>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${not empty depth3.depth4MenuList}">
														<c:set var="url_replace" value='#" onclick="setToggle(this); return false;'/>
													</c:if>
													<c:set var="attributes" value='href="${url_replace}" '/>
			
													<c:choose>
														<c:when test="${fn:substring(depth3.mId,0,6) eq fn:substring(curMid,0,6) }">
															<c:set var="attributes" value='href="${url_replace}" class="on" '/>
															<c:if test="${!empty depth3.depth4MenuList}">
																<c:forEach var="depth4" items="${depth3.depth4MenuList}">
																	<c:if test="${depth3.programUrl eq depth4.programUrl}">
																		<c:set var="ProgramID" value="${depth4.mId}"/>
																	</c:if>
																</c:forEach>
															</c:if>
														</c:when>
														<c:when test="${fn:substring(curMid,4,10) eq '000000' && depth3.programUrl eq  parentProgram}">
															<c:set var="attributes" value='href="${url_replace}" class="on" '/>
															<c:if test="${!empty depth3.depth4MenuList}">
																<c:forEach var="depth4" items="${depth3.depth4MenuList}">
																	<c:if test="${depth3.programUrl eq depth4.programUrl}">
																		<c:set var="ProgramID" value="${depth4.mId}"/>
																	</c:if>
																</c:forEach>
															</c:if>
														</c:when>
													</c:choose>
			
			
			
			
												</c:otherwise>
											</c:choose>
			
			
											<c:choose>
												<c:when test="${fn:length(depth3.depth4MenuList) == 1}">
													<a ${attributes} target="${depth3.depth4MenuList[0].target}">${depth3.menuName}</a>
												</c:when>
												<c:otherwise>
													<a ${attributes} target="${depth3.target}">${depth3.menuName}</a>
												</c:otherwise>
											</c:choose>
			
											<c:if test="${!empty depth3.depth4MenuList}">
												<c:set var="parentProgram1" value="${depth3.programUrl}"/>
			
			
			
			
			
			
												<div class="snb_sub">
													<ul>
														<c:forEach var="depth4" items="${depth3.depth4MenuList}" varStatus="status4">
															<li>
																<c:choose>
																	<c:when test="${empty depth4.programUrl}">
																		<c:set var="attributes" value='href="javascript:alert(\'작업중입니다.\');" '/>
																	</c:when>
																	<c:otherwise>
																		<c:set var="url_replace" value="${pageContext.request.contextPath}${depth4.programUrl}"/>
																		<c:if test="${depth4.target eq '_self'}">
																			<c:choose>
																				<c:when test="${fn:indexOf(depth4.programUrl,'?')>-1}">
																					<c:set var="url_replace" value="/sys/sysContents.do?mId=${depth4.mId}"/>
																				</c:when>
																				<c:otherwise>
																					<c:set var="url_replace" value="/sys/sysContents.do?mId=${depth4.mId}"/>
																				</c:otherwise>
																			</c:choose>
																		</c:if>
			
																		<c:set var="attributes" value='href="${url_replace}" '/>
																		<c:choose>
																			<c:when test="${fn:substring(depth4.mId,0,8) eq fn:substring(curMid,0,8) }">
																				<c:set var="attributes" value='href="${url_replace}" class="on" '/>
																			</c:when>
																			<%-- <c:when test="${fn:substring(curMid,0,6) eq fn:substring(depth4.mId,0,6) && fn:substring(depth3.mId,0,6) eq fn:substring(depth4.mId,0,6) && depth4.programUrl eq  parentProgram1}"> --%>
																			<c:when test="${depth4.mId eq ProgramID  && fn:substring(curMid,6,10) eq '0000' }">
																				<c:set var="attributes" value='href="${url_replace}" class="on" '/>
																			</c:when>
																			<c:when test="${status4.index == 0 && curMid == depth3.mId}">
																				<c:set var="attributes" value='href="${url_replace}" class="on" '/>
																			</c:when>
																		</c:choose>
																	</c:otherwise>
																</c:choose>
			
																<a ${attributes} target="${depth4.target}">${depth4.menuName}</a>
																<c:if test="${!empty depth4.depth5MenuList}">
																	<div class="depth5">
																		<ul>
																			<c:forEach var="depth5" items="${depth4.depth5MenuList}" varStatus="status5">
																				<c:choose>
																					<c:when test="${empty depth5.programUrl}">
																						<c:set var="attributes" value='href="javascript:alert(\'작업중입니다.\');" '/>
																					</c:when>
																					<c:otherwise>
																						<c:set var="url_replace" value="${pageContext.request.contextPath}${depth5.programUrl}"/>
																						<c:if test="${depth5.target eq '_self'}">
																							<c:choose>
																								<c:when test="${fn:indexOf(depth5.programUrl,'?')>-1}">
																									<c:set var="url_replace" value="/sys/sysContents.do?mId=${depth5.mId}"/>
																								</c:when>
																								<c:otherwise>
																									<c:set var="url_replace" value="/sys/sysContents.do?mId=${depth5.mId}"/>
																								</c:otherwise>
																							</c:choose>
																						</c:if>
			
																						<c:set var="attributes" value='href="${url_replace}" '/>
																						<c:choose>
																							<c:when test="${fn:substring(depth5.mId,0,10) eq fn:substring(curMid,0,10) }">
																								<c:set var="attributes" value='href="${url_replace}" class="on" '/>
																							</c:when>
																							<c:when test="${depth5.mId eq ProgramID  && fn:substring(curMid,8,10) eq '00' }">
																								<c:set var="attributes" value='href="${url_replace}" class="on" '/>
																							</c:when>
																							<c:when test="${status5.index == 0 && curMid == depth4.mId}">
																								<c:set var="attributes" value='href="${url_replace}" class="on" '/>
																							</c:when>
																						</c:choose>
																					</c:otherwise>
																				</c:choose>
																				<li><a ${attributes} target="${depth5.target}">${depth5.menuName}</a></li>
																			</c:forEach>
																		</ul>
																	</div>
																</c:if>
															</li>
														</c:forEach>
													</ul>
												</div>
											 </c:if>
										</li>
									</c:forEach>
								</c:if>
							</c:forEach>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
	</ul>
</div>