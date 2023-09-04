<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 설문조사 참여
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.10.10		권태성			최초 생성 및 코드 수정
	 * 2019.05.30		김선영			마크업 수정
	 *
	 * @author 권태성
	 * @since 2017.10.10
	 */
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String now = format.format(new Date());
%>
<div id="contents"><div class="content">
<form:form commandName="searchVO" name="list" id="list" method="post">
	<form:hidden path="page" />
	<form:hidden path="idx" />
</form:form>

<form:form commandName="searchVO" name="apply" id="apply" action="${_context}/${siteCodeFull}/poll/applyProc.do?mId=${menuVO.mId}" method="post">
	<form:hidden path="page" />
	<form:hidden path="idx" />
<div class="poll_view">
	<h4 id="pollTitle">${searchVO.title}</h4>
	<c:if test="${searchVO.dupType eq '4'}">
		<div class="info input">
			<h5 class="mT20">설문조사 참여자 정보 입력</h5>
			<dl>
				<dt>참여자명</dt>
				<dd>${yh:getUser().userName}</dd>
			
			
				<dt>생년월일</dt>
				<dd>${fn:substring(yh:getUser().birthday,0,4)}-${fn:substring(yh:getUser().birthday,4,6)}-${fn:substring(yh:getUser().birthday,6,8)}</dd>
			
				<dt>성별</dt>
				<dd>${yh:getUser().gender eq 'M' ? '남' : '여'}</dd>
			
				<dt><em class="mR5">*<span class="blind">필수항목</span></em><label for="tel1">연락처</label></dt>
				<dd class="input_wrap">
					<input type="hidden" id="tel" name="tel" value="" />
					<select id="tel1" title="전화번호 앞자리">
						<option value="010" selected="selected">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select>
					<label for="tel2" class="blind">전화번호 가운데자리</label>
					<input id="tel2" title="전화번호 가운데자리" data-required="y" data-fieldname="전화번호 가운데자리" class="input60" data-validator="on" type="text" maxlength="4" value="" />
					<label for="tel3" class="blind">전화번호 가운데자리</label>
					<input id="tel3" title="전화번호 마지막자리" data-required="y" data-fieldname="전화번호 마지막자리" class="input60" data-validator="on" type="text" maxlength="4" value="" />
				</dd>
			</dl>
		</div>
	</c:if>

	<c:forEach var="question" items="${searchVO.questionList}" varStatus="status">
		<input type="hidden" id="responseList${status.index}.questionIdx${status.index}" name="responseList[${status.index}].questionIdx" value="${question.idx}">
		<ul class="question_list">
		<c:if test="${question.type eq '1'}">
		<%-- 객관식 중복 --%>
			<li>
				<dl class="poll_Qlist qBasic" data-questionidx="${question.idx}" data-questiontype="1" data-questionnum="${status.count}" data-ableyn="${question.ableYn}" data-require="${question.requiredYn}">
					<dt>
						<span>질문 ${status.count}</span>
					</dt>
					<dd>
						<span><c:if test="${question.requiredYn eq 'Y'}"><em class="mR5">*<span class="blind">필수문항</span></em></c:if>${question.question} <c:if test="${not empty question.multipleLimit}">(${question.multipleLimit}개${question.isMore eq 'U' ? ' 이상' : (question.isMore eq 'L' ? ' 이하' : '')}) </c:if></span>
					</dd>
				</dl>
				<c:if test="${not empty question.questionFile}">
					<figure>
						<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${question.questionFile}"/>
							<c:param name="mode" value="ratio"/>
							<c:param name="width" value="804"/>
							<c:param name="height" value="350"/>
						</c:import>
					</figure>
				</c:if>
				<ul>
					<c:set var="answerEtc" value="${false}" />
					<c:set var="answerEtcIdx" value="" />
					<c:forEach var="answer" items="${question.answerList}" varStatus="aswStatus">
						<c:if test="${answer.answer eq '기타'}">
							<c:set var="answerEtc" value="${true}" />
							<c:set var="answerEtcIdx" value="${answer.idx}" />
						</c:if>
						<c:if test="${answer.answer ne '기타'}">
							<li>
								<input type="checkbox" id="responseList${status.index}.answerIdxList${aswStatus.index}" name="responseList[${status.index}].answerIdxList" value="${answer.idx}" <c:if test="${aswStatus.index eq 0 and question.requiredYn eq 'Y'}">data-validator="on" data-required="y" data-fieldName="질문${status.count}"</c:if> />
								<label for="responseList${status.index}.answerIdxList${aswStatus.index}">${answer.answer}</label>
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
					<c:if test="${answerEtc}">
						<li>
							<input type="checkbox" id="responseList${status.index}.answerIdxList${fn:length(question.answerList) - 1}" name="responseList[${status.index}].answerIdxList" value="${answerEtcIdx}" />
							<label for="responseList${status.index}.answerIdxList${fn:length(question.answerList) - 1}" class="mR5">기타</label>
							<input type="text" id="responseList${status.index}.value" name="responseList[${status.index}].value" class="input400" readonly="readonly" value="" />
						</li>
					</c:if>
				</ul>
			</li>
		</c:if>
		<c:if test="${question.type eq '2'}">
		<%-- 객관식 단답 --%>
			<li>
				<dl class="poll_Qlist qPhoto" data-questionidx="${question.idx}" data-questiontype="2" data-questionnum="${status.count}" data-ableyn="${question.ableYn}" data-require="${question.requiredYn}">
					<dt>
						<span>질문 ${status.count}</span>
					</dt>
					<dd>
						<span><c:if test="${question.requiredYn eq 'Y'}"><em class="mR5">*<span class="blind">필수문항</span></em></c:if>${question.question}</span>
					</dd>
				</dl>
				<c:if test="${not empty question.questionFile}">
					<figure>
						<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${question.questionFile}"/>
							<c:param name="mode" value="ratio"/>
							<c:param name="width" value="804"/>
							<c:param name="height" value="350"/>
						</c:import>
					</figure>
				</c:if>
				<ul>
					<c:set var="answerEtc" value="${false}" />
					<c:set var="answerEtcIdx" value="" />
					<fieldset>
					<legend>객관식 단답 목록</legend>
					<c:forEach var="answer" items="${question.answerList}" varStatus="aswStatus">	
						<c:if test="${answer.answer eq '기타'}">
							<c:set var="answerEtc" value="${true}" />
							<c:set var="answerEtcIdx" value="${answer.idx}" />
						</c:if>
						<c:if test="${answer.answer ne '기타'}">
							<li style="height:30px">
								<input type="radio" id="responseList${status.index}.answerIdx${aswStatus.index}" name="responseList[${status.index}].answerIdx" data-idx="${aswStatus.index + 1}" value="${answer.idx}" <c:if test="${aswStatus.index eq 0 and question.requiredYn eq 'Y'}">data-validator="on" data-required="y" data-fieldName="질문${status.count}"</c:if> />
								<label for="responseList${status.index}.answerIdx${aswStatus.index}">${answer.answer}</label>
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
					
					<c:if test="${answerEtc}">			
						<li>
							<input type="radio" id="responseList${status.index}.answerIdx${fn:length(question.answerList) - 1}" name="responseList[${status.index}].answerIdx" data-idx="${fn:length(question.answerList)}" value="${answerEtcIdx}" />
							<label for="responseList${status.index}.answerIdx${fn:length(question.answerList) - 1}" class="mR5">기타</label>
							<input type="text" id="responseList${status.index}.answerIdx${fn:length(question.answerList) - 1}" name="responseList[${status.index}].value" class="input400" readonly="readonly" value="" />
						</li>
					</c:if>
					</fieldset>
				</ul>
			</li>
		</c:if>
		<c:if test="${question.type eq '3'}">
		<%-- 주관식 단답 --%>
			<li>
				<dl class="poll_Qlist qBasic" data-questionidx="${question.idx}" data-questiontype="3" data-questionnum="${status.count}" data-ableyn="${question.ableYn}" data-require="${question.requiredYn}">
					<dt>
						<span>질문 ${status.count}</span>
					</dt>
					<dd>
						<span><c:if test="${question.requiredYn eq 'Y'}"><em class="mR5">*<span class="blind">필수문항</span></em></c:if>${question.question}</span>
					</dd>
				</dl>
				<c:if test="${not empty question.questionFile}">
					<figure>
						<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${question.questionFile}"/>
							<c:param name="mode" value="ratio"/>
							<c:param name="width" value="804"/>
							<c:param name="height" value="350"/>
						</c:import>
					</figure>
				</c:if>
				<ul>
					<li>
						<label for="responseList${status.index}.value" class="blind">질문 ${status.count}의 응답 내용을 기재하여 주세요.</label>
						<input type="text" id="responseList${status.index}.value" name="responseList[${status.index}].value" class="w100" value="" <c:if test="${question.requiredYn eq 'Y'}">data-validator="on" data-required="y" data-fieldName="질문${status.count}"</c:if> />
					</li>
				</ul>
			</li>
		</c:if>
		<c:if test="${question.type eq '4'}">
		<%-- 주관식 장문 --%>
			<li>
				<dl class="poll_Qlist qBasic" data-questionidx="${question.idx}" data-questiontype="4" data-questionnum="${status.count}" data-ableyn="${question.ableYn}" data-require="${question.requiredYn}">
					<dt>
						<span>질문 ${status.count}</span>
					</dt>
					<dd>
						<span><c:if test="${question.requiredYn eq 'Y'}"><em class="mR5">*<span class="blind">필수문항</span></em></c:if>${question.question}</span>
					</dd>
				</dl>
				<c:if test="${not empty question.questionFile}">
					<figure>
						<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${question.questionFile}"/>
							<c:param name="mode" value="ratio"/>
							<c:param name="width" value="804"/>
							<c:param name="height" value="350"/>
						</c:import>
					</figure>
				</c:if>
				<ul>
					<li>
						<label for="responseList${status.index}.value" class="blind">질문 ${status.count}의 응답 내용을 기재하여 주세요.</label>
						<textarea id="responseList${status.index}.value" name="responseList[${status.index}].value" class="w100" <c:if test="${question.requiredYn eq 'Y'}">data-validator="on" data-required="y" data-fieldName="질문${status.count}"</c:if>></textarea>
					</li>
				</ul>
			</li>
		</c:if>
		<c:if test="${question.type eq '5'}">
		<%-- 순위 선정형 --%>
			<li>
				<dl class="poll_Qlist qBasic" data-questionidx="${question.idx}" data-questiontype="5" data-questionnum="${status.count}" data-ableyn="${question.ableYn}" data-require="${question.requiredYn}">
					<dt>
						<span>질문 ${status.count}</span>
					</dt>
					<dd>
						<span><c:if test="${question.requiredYn eq 'Y'}"><em class="mR5">*<span class="blind">필수문항</span></em></c:if>${question.question}</span>
					</dd>
				</dl>
				<c:if test="${not empty question.questionFile}">
					<figure>
						<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${question.questionFile}"/>
							<c:param name="mode" value="ratio"/>
							<c:param name="width" value="804"/>
							<c:param name="height" value="350"/>
						</c:import>
					</figure>
				</c:if>
				<ul>
					<c:forEach var="answer" items="${question.answerList}" varStatus="aswStatus">
						<li>
							<input type="hidden" id="responseList${status.index}.orderList${aswStatus.index}.answerIdx${aswStatus.index}" name="responseList[${status.index}].orderList[${aswStatus.index}].answerIdx" value="${answer.idx}" />
							<select id="responseList${status.index}.orderList${aswStatus.index}.value${aswStatus.index}" name="responseList[${status.index}].orderList[${aswStatus.index}].value" title="순위" class="mR5">
								<c:forEach var="i" begin="0" end="${fn:length(question.answerList)}">
									<option value="${i ne 0 ? i : ''}">${i eq 0 ? '선택' : i}</option>
								</c:forEach>
							</select>
							<label for="responseList${status.index}.orderList${aswStatus.index}.value${aswStatus.index}">&nbsp;${answer.answer}</label>
						</li>
					</c:forEach>
				</ul>
			</li>
		</c:if>
		<c:if test="${question.type eq '6'}">
		<%-- 매트릭스 형 --%>
			<li>
				<dl class="poll_Qlist qBlog" data-questionidx="${question.idx}" data-questiontype="6" data-questionnum="${status.count}" data-ableyn="${question.ableYn}" data-require="${question.requiredYn}">
					<dt>
						<span>질문 ${status.count}</span>
					</dt>
					<dd>
						<span><c:if test="${question.requiredYn eq 'Y'}"><em class="mR5">*<span class="blind">필수문항</span></em></c:if>${question.question}</span>
					</dd>
				</dl>
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
					<div class="tbl_wrap">
						<p class="tbl_guide">모바일환경에서는 좌우로 이동하여 내용(표)을 보실 수 있습니다.</p>
						<table class="tbl taC">
							<caption>설문조사 질문 ${status.count} - 질문${status.count}번에 대하여 점수를 체크 할 수 있는 표입니다. 내용별로 아니다(0점), 가끔(1점), 자주(2점) 중 택 1 할 수 있습니다.</caption>
							<!-- <colgroup>
								<col>
								<col span="3" class="w10">
							</colgroup> -->
							<thead>
								<tr>
									<th scope="col">내용</th>
									<c:forEach var="col" items="${question.matrixColList}">
										<th scope="col">${col.matrix}</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<!-- id="responseList${status.index}.answerIdx${aswStatus.index}" -->
								<!-- name="responseList[${status.index}].answerIdx" -->
								<c:forEach var="row" items="${question.matrixList}" varStatus="rowStatus">
									<tr>
										<th scope="row" class="taL">${row.matrixQuestion}</th>
										<c:forEach var="col" items="${question.matrixColList}" varStatus="colStatus">
											<td>
												<c:if test="${colStatus.count eq 1}">
													<input type="hidden" name="responseList[${status.index}].matList[${rowStatus.index}].matrixIdx" value="${row.idx}" />
												</c:if>
												<input type="radio" id="responseList${status.index}.matList${rowStatus.index}.matrixAnswerIdx${colStatus.index}" name="responseList[${status.index}].matList[${rowStatus.index}].matrixAnswerIdx" value="${col.idx}" <c:if test="${colStatus.index eq 0 and question.requiredYn eq 'Y'}">data-validator="on" data-required="y" data-fieldName="질문${status.count}"</c:if> />
												<label for="responseList${status.index}.matList${rowStatus.index}.matrixAnswerIdx${colStatus.index}" class="hidden">${row.matrixQuestion}에 대한 응답 ${col.matrix}</label>
											</td>
										</c:forEach>
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
	<div class="poll_qLast">
		<p class="tit">설문에 응해 주셔서 감사드립니다.</p>
		<p class="txt">${searchVO.footer}</p>
	</div>
	<div class="btn_wrap taR mT20">
		<input type="submit" id="submitBtn" value="참여하기" onclick="disabledOff();"/>
		<input type="button" value="목록" onclick="location.href='${_context}/${siteCodeFull}/poll/list.do?mId=${curMid}'" />
	</div>
</form:form>
</div></div>
<script type="text/javascript" src="${_context}/common/js/common/require.js"></script>
<script type="text/javascript" src="${_context}/common/js/basic/poll/apply.js"></script>
<script type="text/javascript">
apply.idx = "${searchVO.idx}";
apply.dupType = "${searchVO.dupType}";
apply.able = [
<c:forEach var="able" items="${answerAbleList}" varStatus="ableSts">
	{
		"questionNum": ${able.questionNum},
		"target": ${able.target},
		"questionIdx": ${able.questionIdx}
	}${ableSts.count < fn:length(answerAbleList) ? "," : ""}
</c:forEach>
];
apply.limit = [
<c:forEach var="question" items="${searchVO.questionList}" varStatus="status">
	<c:if test="${not empty question.isMore and question.type eq '1'}">
		{
			"idx": ${question.idx},
			"limit": ${not empty question.multipleLimit ? question.multipleLimit : '0'},
			"option": "${question.isMore}"
		},
	</c:if>
</c:forEach>
];
</script>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script type="text/javascript" src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script>
<script>
$(function(){
	//20.04.09 웹접근성 반영 - 각페이지 title 정보 수정 
	var titleVal =$("#pollTitle").html().trim() + " | " + $("title").html();
	$("title").html(titleVal);
	
});

</script>
<script>
$(document).ready(function () {
	//[2019.11.20.] 키보드 보안모듈
	//$("#userAgent").text(navigator.userAgent);
	/*
	npPfsStartup(document.form1, false, true, true, "enc", "on");
	1. form 양식 : 기본값 DOM Document 상의 첫번째 form
	2. 개인방화벽 사용여부 : 기본값 false
	3. 키보드보안 사용여부 : 기본값 false
	4. 단말정보수집 사용여부 : 기본값 false
	5. 키보드보안 E2E 필드 설정 속성명 : 기본값 "enc"
	6. 키보드보안 E2E 필드 설정 속성값: 기본값 "on"
	부가적인 설정은(예, 설치확인 등) /pluginfree/js/nppfs-1.0.0.js를 수정하여 설정하십시오.
	*/
	//npPfsStartup(null, false, true, false, false, "npkencrypt", "on");
});
</script>
