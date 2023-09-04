<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 결과
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.10.10		권태성			최초 생성 및 코드 수정
	 * 2019.05.30		김선영			마크업수정
	 *
	 * @author 권태성
	 * @since 2017.10.10
	 */
%>
<div id="contents"><div class="content">
<form:form commandName="searchVO" name="list" id="list" method="post">
	<form:hidden path="page" />
	<form:hidden path="idx" />
</form:form>

<div class="poll_view">
	<h4>${poll.title}</h4>
	<div class="info">
		<figure>
			<c:if test="${not empty poll.mainFile}">
				<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${poll.mainFile}"/>
					<c:param name="mode" value="ratio"/>
					<c:param name="width" value="360"/>
					<c:param name="height" value="245"/>
				</c:import>
			</c:if>
			<c:if test="${empty poll.mainFile}">
				<img src="/common/img/board/poll_noimg.jpg" alt="이미지가 없습니다." />
			</c:if>
		</figure>
		<dl class="clFix">
			<dt>설문조사기간</dt>
			<dd>${poll.startDate}&nbsp;~&nbsp;${poll.endDate}</dd>
			<dt>참여대상</dt>
			<dd>
				<%-- ${yh:getProperty('Globals.org.name')} --%>
				${poll.joinTarget}
				<c:if test="${poll.joinAgeYn eq 'Y'}">
					<%-- &nbsp;중 --%>&nbsp;${poll.joinStAge}년&nbsp;~&nbsp;${poll.joinEdAge}년생 ${poll.joinGender eq 'M' ? '(남성) ' : (poll.joinGender eq 'W' ? '(여성) ' : '')}대상
				</c:if>
			</dd>
			<dt>상태</dt>
			<dd>${poll.stateYn eq 'N' ? '설문 중단' : poll.stateFlag }</dd>
			<dt>참여자 현황</dt>
			<dd>${poll.userCnt}명</dd>
			<dt>결과 공개여부</dt>
			<dd><span>${poll.publicYn eq 'Y' ? '공개' : '비공개'}</span></dd>
		</dl>
	</div>
	<div class="tit">
		 ${yh:nl2br(poll.header)}
	</div>

	<ul class="question_list">
	<c:forEach var="question" items="${searchVO.questionList}" varStatus="status">
		<c:if test="${question.type eq '1' or question.type eq '2'}">
		<%-- 객관식 중복 / 단답 --%>
		<li>
			<dl class="poll_Qlist commit_list">
				<dt>
					<span>질문 ${status.count}</span>
				</dt>
				<dd>
					<span>${question.question}</span>
				</dd>
			</dl>
			<ul>
			<c:set var="answerEtc" value="${false}" />
			<c:set var="answerEtcIdx" value="" />
			<c:set var="answerEtcCnt" value="0" />
			<c:set var="answerTotal" value="" />
			<c:forEach var="answer" items="${question.answerList}" varStatus="aswStatus">
				<c:if test="${answer.answer eq '기타'}">
					<c:set var="answerEtc" value="${true}" />
					<c:set var="answerEtcIdx" value="${answer.idx}" />
					<c:set var="answerEtcCnt" value="${answer.answerCnt}" />
					<c:set var="etcPercent" value="${answer.answerCnt ne 0 ? ((answer.answerCnt / answer.totalCnt) * 100) : 0}" />
					<c:set var="etcPercent" value="${etcPercent > 0.0 ? fn:substring(etcPercent, 0, fn:indexOf(etcPercent, '.') + 2) : 0}" />
				</c:if>
				<c:if test="${answer.answer ne '기타'}">
					<c:set var="percent" value="${answer.answerCnt ne 0 ? ((answer.answerCnt / answer.totalCnt) * 100) : 0}" />
					<c:set var="percent" value="${percent > 0.0 ? fn:substring(percent, 0, fn:indexOf(percent, '.') + 2) : 0}" />
					<li class="clFix">
						<p>${answer.answer}</p>
						<div class="bar_wrap">
							<p class="bar">
								<span class="on" style="width: ${percent}%;"></span>
							</p>
						</div>
						<span class="txt">투표수 : ${answer.answerCnt}명 / <strong>${percent}%</strong></span>
					</li>
				</c:if>
			</c:forEach>
			<c:if test="${answerEtc}">
				<li>
					<p>기타</p>
					<div class="bar_wrap">
						<p class="bar">
							<span class="on" style="width: ${etcPercent}%; "></span>
						</p>
					</div>
					<span class="txt">투표수 : ${answerEtcCnt}명 / <strong>${etcPercent}%</strong></span>
				</li>
			</c:if>
		</ul>
	</li>
	</c:if>
	
	<c:if test="${question.type eq '3' or question.type eq '4'}">
	<%-- 주관식 단답 --%>
	<li>
		<dl class="poll_Qlist qBasic">
			<dt>
				<span>질문 ${status.count}</span>
			</dt>
			<dd>
				<span>${question.question}</span>
			</dd>
		</dl>
		<ul>
			<li>
				<p class="notice"><em>주관식항목은 관리자만 열람가능합니다.</em></p>
			</li>
		</ul>
	</li>
	</c:if>
	
	<c:if test="${question.type eq '5'}">
	<%-- 순위 선정형 --%>
	<li>
		<dl class="poll_Qlist qBasic commit_list">
			<dt>
				<span>질문 ${status.count}</span>
			</dt>
			<dd>
				<span>${question.question}</span>
			</dd>
		</dl>
		<ul>
		<c:forEach var="answer" items="${question.answerList}" varStatus="aswStatus">
			<li>
				<p>${answer.answer}</p>
				<ul>
				<c:set var="retTotal" value="${fn:length(answer.responseList)}" />
				<c:forEach var="i" begin="0" end="${fn:length(question.answerList) - 1}">
					<li>
						<c:set var="retCnt" value="0" />
						<c:forEach var="ret" items="${answer.responseList}" varStatus="retStatus">
							<c:if test="${ret.value eq (i + 1)}">
								<c:set var="retCnt" value="${retCnt + 1}" />
							</c:if>
						</c:forEach>
						<c:set var="percent" value="${retCnt ne 0 ? ((retCnt / retTotal) * 100) : 0}" />
						<c:set var="percent" value="${percent > 0.0 ? fn:substring(percent, 0, fn:indexOf(percent, '.') + 2) : 0}" />
						<span>${i + 1}순위</span>
						<div class="bar_warp">
							<p class="bar">
								<span class="on" style="width: ${percent}%; "></span>
							</p>
						</div>
						<span class="txt">투표수 : ${retCnt}명 / <strong>${percent}%</strong></span>
					</li>
				</c:forEach>
				</ul>
			</li>
		</c:forEach>
		</ul>
	</li>
	</c:if>
	
	<c:if test="${question.type eq '6'}">
	<%-- 매트릭스 형 --%>
	<li>
		<dl class="poll_Qlist commit_list">
			<dt>
				<span>질문 ${status.count}</span>
			</dt>
			<dd>
				<span>${question.question}</span>
			</dd>
		</dl>
		<ul>
			<li>
				<div class="clFix answer">
					<c:forEach var="matCol" items="${question.matrixColList}" varStatus="matColStatus">
						<span class="item${matColStatus.count < 10 ? '0' : '' }${matColStatus.count}">${matCol.matrix}</span>
					</c:forEach>
					<!-- <span class="sure">그렇다</span><span class="so">보통이다</span><span class="no">아니다</span> -->	
				</div>
				<div class="tbl_wrap">
					<p class="tbl_guide">모바일환경에서는 좌우로 이동하여 내용(표)을 보실 수 있습니다.</p>
					<table class="tbl">
						<caption>설문조사 질문 ${status.count}의 응답 결과에 대해 문항과 결과를 안내하는 표입니다.</caption>
						<colgroup>
							<col class="w30">
							<col class="w70">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">문항</th>
								<th scope="col">결과</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="matRow" items="${question.matrixList}">
								<tr>
									<th scope="row" class="taL">${matRow.matrixQuestion}</th>
									<td>
										<ul>
											<c:forEach var="matCol" items="${question.matrixColList}" varStatus="matColStatus">
												<c:set var="retTotal" value="${fn:length(matRow.responseList)}" />
												<c:set var="retCnt" value="0" />
												<c:forEach var="ret" items="${matRow.responseList}">
													<c:if test="${ret.matrixAnswerIdx eq matCol.idx}">
														<c:set var="retCnt" value="${retCnt + 1}" />
													</c:if>
												</c:forEach>
												<c:set var="percent" value="${retCnt ne 0 ? ((retCnt / retTotal) * 100) : 0}" />
												<c:set var="percent" value="${percent > 0.0 ? fn:substring(percent, 0, fn:indexOf(percent, '.') + 2) : 0}" />
												<li class="clFix">
													<span class="blind">${matCol.matrix}</span>
													<div class="bar_wrap">
														<p class="bar item${matColStatus.count < 10 ? '0' : '' }${matColStatus.count}">
															<span class="on" style="width: ${percent}%; "></span>
														</p>
													</div>
													<span class="txt">투표수 : ${retCnt}명 <br/><strong>${percent}%</strong></span>
												</li>
											</c:forEach>
										</ul>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</li>
		</ul>
	</li>
	</c:if>
</c:forEach>
</ul>
</div>

<div class="btn_wrap taR">
	<a data-action="${_context}/${siteCodeFull}/poll/list.do?mId=${curMid}" data-formname="list" onclick="req.post(this); return false;" href="#">목록</a>
</div>
</div></div>
<script type="text/javascript" src="${_context}/common/js/commonProcess.js"></script>