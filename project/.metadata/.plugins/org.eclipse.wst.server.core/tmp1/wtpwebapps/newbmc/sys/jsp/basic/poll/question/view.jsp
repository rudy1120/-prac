<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 질문 관리
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.08.31		권태성			최초 생성 및 코드 수정
	 *
	 *
	 * @author 권태성
	 * @since 2017.08.02
	 */
%>

<h2>${menuVO.menuName}</h2>
<form name="list" id="list" method="post" action="${_context}/sys/pollMng/list.do?mId=${menuVO.mId}">
	<input type="hidden" name="page" value="${searchVO.page}" />
	<input type="hidden" name="searchSiteCode" value="${searchVO.searchSiteCode}" />
	<input type="hidden" name="searchState" value="${searchVO.searchState}" />
	<input type="hidden" name="searchViewYn" value="${searchVO.searchViewYn}" />
	<input type="hidden" name="searchTxt" value="${searchVO.searchTxt}" />
	<input type="hidden" name="idx" value="${searchVO.idx}" />
</form>

<form:form commandName="searchVO" name="write" id="write" method="post" action="${_context}/sys/pollMng/question/${not empty isNew ? 'writeProc' : 'updateProc'}.do?mId=${menuVO.mId}" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<div class="poll_top">
		<div class="poll_h5"><h4 class="tit">${searchVO.title}</h4></div>
	</div>
	<%--(1:객관식 중복, 2:객관식 단답, 3:주관식 단답, 4:주관식 장문, 5:순위 선정형, 6:매트릭스 형)--%>
	<c:forEach var="question" items="${searchVO.questionList}" varStatus="status">
			<c:if test="${question.type eq '1'}">
				<%-- 객관식 중복 --%>
				<dl class="poll_Qlist qPhoto">
					<dt>
						<span class="tit">질문 ${status.count}</span>
						<span>${question.question}</span>
					</dt>
					<dd>
						<c:if test="${not empty question.questionFile}">
							<p class="mL15">
								<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${question.questionFile}"/>
									<c:param name="mode" value="ratio"/>
									<c:param name="width" value="804"/>
									<c:param name="height" value="350"/>
								</c:import>
							</p>
						</c:if>
						<ul class="w900">
							<c:set var="answerEtc" value="${false}" />
							<c:forEach var="answer" items="${question.answerList}">
								<c:if test="${answer.answer eq '기타'}">
									<c:set var="answerEtc" value="${true}" />
								</c:if>
								<c:if test="${answer.answer ne '기타'}">
									<li>
										<input type="checkbox" id="" value="${answer.idx}">
										<label for="">${answer.answer}</label>
										<c:if test="${not empty answer.answerFile}">
											<p>
												<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
													<c:param name="param_atchFileId" value="${answer.answerFile}"/>
													<c:param name="mode" value="ratio"/>
													<c:param name="width" value="389"/>
													<c:param name="height" value="208"/>
												</c:import>
											</p>
										</c:if>
									</li>
								</c:if>
							</c:forEach>
						</ul>
						<c:if test="${answerEtc}">
							<ul>
								<li>
									<input type="checkbox" id="" value="W">
									<label for="">기타</label>
									<input id="" class="input99"  type="text">
								</li>
							</ul>
						</c:if>
					</dd>
				</dl>
			</c:if>
			<c:if test="${question.type eq '2'}">
				<%-- 객관식 단답 --%>
				<dl class="poll_Qlist qPhoto">
					<dt>
						<span class="tit">질문 ${status.count}</span>
						<span>${question.question}</span>
					</dt>
					<dd>
						<c:if test="${not empty question.questionFile}">
							<p class="mL15">
								<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${question.questionFile}"/>
									<c:param name="mode" value="ratio"/>
									<c:param name="width" value="804"/>
									<c:param name="height" value="350"/>
								</c:import>
							</p>
						</c:if>
						<ul class="w900">
							<c:set var="answerEtc" value="${false}" />
							<c:forEach var="answer" items="${question.answerList}">
								<c:if test="${answer.answer eq '기타'}">
									<c:set var="answerEtc" value="${true}" />
								</c:if>
								<c:if test="${answer.answer ne '기타'}">
									<li>
										<input type="radio" id="" value="${answer.idx}">
										<label for="">${answer.answer}</label>
										<c:if test="${not empty answer.answerFile}">
											<p>
												<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
													<c:param name="param_atchFileId" value="${answer.answerFile}"/>
													<c:param name="mode" value="ratio"/>
													<c:param name="width" value="389"/>
													<c:param name="height" value="208"/>
												</c:import>
											</p>
										</c:if>
									</li>
								</c:if>
							</c:forEach>
						</ul>
						<c:if test="${answerEtc}">
							<ul>
								<li>
									<input type="radio" id="" value="W">
									<label for="">기타</label>
									<input id="" class="input99"  type="text">
								</li>
							</ul>
						</c:if>
					</dd>
				</dl>
			</c:if>
			<c:if test="${question.type eq '3'}">
				<%-- 주관식 단답 --%>
				<dl class="poll_Qlist qBasic">
					<dt>
						<span class="tit">질문 ${status.count}</span>
						<span>${question.question}</span>
					</dt>
					<dd>
						<c:if test="${not empty question.questionFile}">
							<p class="mL15">
								<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${question.questionFile}"/>
									<c:param name="mode" value="ratio"/>
									<c:param name="width" value="804"/>
									<c:param name="height" value="350"/>
								</c:import>
							</p>
						</c:if>
						<ul>
							<li>
								<label for="" class="hidden">기재하여 주세요.</label>
								<input id="" class="input99"  type="text">
							</li>
						</ul>
					</dd>
				</dl>
			</c:if>
			<c:if test="${question.type eq '4'}">
				<%-- 주관식 장문 --%>
				<dl class="poll_Qlist qBasic">
					<dt>
						<span class="tit">질문 ${status.count}</span>
						<span>${question.question}</span>
					</dt>
					<dd>
						<c:if test="${not empty question.questionFile}">
							<p class="mL15">
								<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${question.questionFile}"/>
									<c:param name="mode" value="ratio"/>
									<c:param name="width" value="804"/>
									<c:param name="height" value="350"/>
								</c:import>
							</p>
						</c:if>
						<ul>
							<li>
								<textarea class="input99"></textarea>
							</li>
						</ul>
					</dd>
				</dl>
			</c:if>
			<c:if test="${question.type eq '5'}">
				<%-- 순위 선정형 --%>
				<dl class="poll_Qlist qBasic">
					<dt>
						<span class="tit">질문 ${status.count}</span>
						<span>${question.question}</span>
					</dt>
					<dd>
						<c:if test="${not empty question.questionFile}">
							<p class="mL15">
								<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
									<c:param name="param_atchFileId" value="${question.questionFile}"/>
									<c:param name="mode" value="ratio"/>
									<c:param name="width" value="804"/>
									<c:param name="height" value="350"/>
								</c:import>
							</p>
						</c:if>
						<ul class="list_ul_h4">
							<c:forEach var="answer" items="${question.answerList}" varStatus="answerStatus">
								<li>
									<select>
										<c:forEach var="i" begin="0" end="${fn:length(question.answerList)}">
											<option value="${i}">${i == 0 ? "선택" : i}</option>
										</c:forEach>
									</select>
									<label for="">&nbsp;${answer.answer}</label>
								</li>
							</c:forEach>
						</ul>
					</dd>
				</dl>
			</c:if>
			<c:if test="${question.type eq '6'}">
				<%-- 매트릭스 형 --%>
				<dl class="poll_Qlist qBlog">
					<dt>
						<span class="tit">질문 ${status.count}</span>
						<span>${question.question}</span>
					</dt>
					<dd>
						<ul>
							<li>
								<c:if test="${not empty question.questionFile}">
									<p>
										<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
											<c:param name="param_atchFileId" value="${question.questionFile}"/>
											<c:param name="mode" value="ratio"/>
											<c:param name="width" value="804"/>
											<c:param name="height" value="350"/>
										</c:import>
									</p>
								</c:if>
								<table class="tbl taC">
									<caption>설문조사 질문 ${status.count} - 질문 ${status.count}번에 대하여 점수를 체크 할 수 있는 표입니다. 내용별로 아니다(0점), 가끔(1점), 자주(2점) 중 택 1 할 수 있습니다.</caption>
									<colgroup>
										<col />
										<col span="3" class="col10">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">내용</th>
											<c:forEach var="col" items="${question.matrixColList}">
												<th scope="col">${col.matrix}</th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row" items="${question.matrixList}">
											<tr>
												<th scope="row" class="taL">${row.matrixQuestion}</th>
												<c:forEach var="col" items="${question.matrixColList}">
													<td>
														<input type="radio" id="" value="${col.idx}">
														<label for="" class="hidden">${col.matrix}</label>
													</td>
												</c:forEach>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</li>
						</ul>
					</dd>
				</dl>
			</c:if>
	</c:forEach>

	<div class="btnboxR">
		<a href="#" class="btn_normal" data-action="${_context}/sys/pollMng/question/update.do?mId=0102040000" data-formname="list" data-formserialize="y" onclick="req.post(this); return false;">수정하기</a>
		<a href="#" class="btn_normal" data-action="${_context}/sys/pollMng/list.do?mId=0102040000" data-formname="list" data-formserialize="y" onclick="req.post(this); return false;">목록</a>
	</div>

</form:form>

<script type="text/javascript" src="${_context}/common/js/common/require.js"></script>
<script type="text/javascript" src="${_context}/sys/js/basic/pollMng/pollMng.js<%--=System.currentTimeMillis() --%>"></script>
<script type="text/javascript">
yh.formId = "write";
pollMng.mode = "view";
</script>
