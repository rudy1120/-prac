<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 결과
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.09.28		권태성			최초 생성 및 코드 수정
	 *
	 *
	 * @author 권태성
	 * @since 2017.09.28
	 */
%>
<style>
.qtit{padding:2px 10px; border-radius:20px; color:#333; display:inline-block; border:1px solid #7393ad;}
.graph_wrap{width:30%; float:left;}
.graph { position: relative; /* IE is dumb */width: 200px; border: 1px solid #2196f3; font-size:11px;font-family:tahoma;margin:5px 0; display:inline-block;}
.graph .bar {  display: block;position: relative;background: #2196f3;  text-align: center; color: #fff; height: 2em;  line-height: 2em; width:60%;}
.graph .bar span { position: absolute; left: 1em; }
.bar_txt{margin-left:5px; color:#9c9c9c;}
.title_poll{padding:10px 30px;}
.list_ul_h4{margin:10px;}
.poll_h5{margin:0 0 10px;}
.poll_Qlist {margin:30px 10px; overflow:hidden;}
.list_q{width:70%; float:left;}
.btn_sblue{padding:5px 10px; background:#3e4959; display:table-cell!important; border-radius:0!important; color:#fff!important;}
.btnboxR{padding:0 40px 20px;}
.qBasic dd{margin-left:10px;}
</style>

<h2>${menuVO.menuName}</h2>
<h3 class="hidden">결과보기</h3>

<form name="list" id="list" action="" method="post">
	<input type="hidden" name="page" value="${searchVO.page}" />
	<input type="hidden" name="searchSiteCode" value="${searchVO.searchSiteCode}" />
	<input type="hidden" name="searchState" value="${searchVO.searchState}" />
	<input type="hidden" name="searchViewYn" value="${searchVO.searchViewYn}" />
	<input type="hidden" name="searchTxt" value="${searchVO.searchTxt}" />
	<input type="hidden" name="idx" value="0" />
</form>

<div class="title_poll">
	<div class="poll_top">
	<div class="poll_h5"><h4 class="tit">${poll.title}</h4></div>
		<table class="tableSt_write table_plus">
			<caption>설문조사 정보를 표시하는 표입니다.</caption>
			<colgroup>
				<col class="col15 w15">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">조사기간</th>
					<td>
						${poll.startDate} ~ ${poll.endDate}
					</td>
					<th scope="row">결과공개</th>
					<td>
						${poll.publicYn eq 'Y' ? '공개' : '비공개'}
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<c:if test="${not empty searchVO.header}">
		<div class="poll_txt">
			${poll.header}
		</div>
	</c:if>

	<c:forEach var="question" items="${searchVO.questionList}" varStatus="status">
		<c:if test="${question.type eq '1' or question.type eq '2'}">
			<%-- 객관식 중복 / 단답 --%>
			<dl class="poll_Qlist commit_list">
				<dt>
					<span class="tit qtit">질문 ${status.count}</span>
					<span>${question.question}</span>
				</dt>
				<dd>
					<ul class="list_ul_h4">
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
								<li>
									<p class="list_q">${aswStatus.count}. ${answer.answer}</p>
									<%-- <div class="bar_warp">
										<p class="bar">
											<span class="bar_on" style="width: ${percent}%;"></span>
										</p>
									</div> --%>
									<%-- <span class="bar_txt">투표수 : ${answer.answerCnt}명 / <strong>${percent}%</strong></span> --%>
									<div class="graph_wrap">
										<div class="graph">
										    <strong class="bar" style="width: 60%;">${percent}%</strong>
										</div>
										<span class="bar_txt">투표수 : ${answer.answerCnt}명 / <strong>${percent}%</strong></span>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
					<c:if test="${answerEtc}">
						<ul class="list_ul_h4">
							<li>
								<p class="list_q">${fn:length(question.answerList)}. 기타<br/><br/><a href="#" onclick="resultMng.showComment(${answerEtcIdx}, '${question.type}'); return false;" class="btn_sblue">답변보기</a></p>
								<div class="graph_wrap">
									<div class="graph">
									    <strong class="bar" style="width: 60%;">${etcPercent}%</strong>
									</div>
									<span class="bar_txt">투표수 :  ${answerEtcCnt}명 / <strong>${etcPercent}%</strong></span>
								</div>
								<%-- <div class="bar_warp">
									<p class="bar">
										<span class="bar_on" style="width: ${etcPercent}%; "></span>
									</p>
								</div>
								<span class="bar_txt">투표수 : ${answerEtcCnt}명 / <strong>${etcPercent}%</strong></span> --%>
							</li>
						</ul>
					</c:if>
				</dd>
			</dl>
		</c:if>
		<c:if test="${question.type eq '3' or question.type eq '4'}">
			<%-- 주관식 단답 --%>
			<dl class="poll_Qlist qBasic">
				<dt>
					<span class="tit qtit">질문 ${status.count}</span>
					<span>${question.question}</span>
				</dt>
				<dd>
					<ul>
						<li>
							<p><br/><a href="#" onclick="resultMng.showComment(${question.idx}, '${question.type}'); return false;" class="btn_sblue">답변보기</a></p>
						</li>
					</ul>
				</dd>
			</dl>
		</c:if>
		<c:if test="${question.type eq '5'}">
			<%-- 순위 선정형 --%>
			<dl class="poll_Qlist qBasic commit_list">
				<dt>
					<span class="tit">질문 ${status.count}</span>
					<span>${question.question}</span>
				</dt>
				<dd>
					<ul class="list_ul_h4">
						<c:forEach var="answer" items="${question.answerList}" varStatus="aswStatus">
							<li>
								${answer.answer}
								<ul>
									<c:set var="retTotal" value="${fn:length(answer.responseList)}" />
									<c:forEach var="i" begin="0" end="${fn:length(question.answerList) - 1}">
										<li class="oneline">
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
													<span class="bar_on" style="width: ${percent}%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : ${retCnt}명 / <strong>${percent}%</strong></span> 
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</dd>
			</dl>
		</c:if>
		<c:if test="${question.type eq '6'}">
			<%-- 매트릭스 형 --%>
			<dl class="poll_Qlist commit_list">
				<dt>
					<span class="tit">질문 ${status.count}</span>
					<span>${question.question}</span>
				</dt>
				<dd>
					<ul>
						<li>
							<div class="cB answer">
								<p class="fR">
									<c:forEach var="matCol" items="${question.matrixColList}" varStatus="matColStatus">
										<span class="answer${matColStatus.count < 10 ? '0' : '' }${matColStatus.count}">${matCol.matrix}</span>
									</c:forEach>
									<!-- <span class="sure">그렇다</span><span class="so">보통이다</span><span class="no">아니다</span> -->
								</p>
							</div>
							<table class="table_st taC">
								<caption>설문조사 질문 ${status.count}의 응답 결과에 대해 문항과 결과를 안내하는 표입니다.</caption>
								<colgroup>
									<col span="1" class="col30 w30">
									<col span="1" class="col70 w70">
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
														<li class="oneline">
															<span class="hidden">${matCol.matrix}</span>
															<div class="bar_warp">
																<p class="bar answer${matColStatus.count < 10 ? '0' : '' }${matColStatus.count}">
																	<span class="bar_on" style="width: ${percent}%; "></span>
																</p>
															</div>
															<span class="bar_txt">투표수 : ${retCnt}명 <br/><strong>${percent}%</strong></span>
														</li>
													</c:forEach>
												</ul>
											</td>
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
</div>

<div class="btnboxR">
	<a href="${_context}/sys/pollMng/result/excel.do?idx=${searchVO.idx}" target="_blank" class="btn_blue">엑셀다운로드</a>
	<a data-action="${_context}/sys/pollMng/list.do?mId=0102040000" data-formname="list" onclick="req.post(this); return false;" href="#" class="btn_blue">목록</a>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/require.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/basic/pollMng/resultMng.js"></script>