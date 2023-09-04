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
<div class="mL3">
	<span class="tip red">문항유형을 변경하시면 입력하신 정보가 사라지니 주의바랍니다.</span>
</div>
<form name="list" id="list" method="post" action="${pageContext.request.contextPath}/sys/pollMng/list.do?mId=${menuVO.mId}">
	<input type="hidden" name="page" value="${searchVO.page}" />
	<input type="hidden" name="searchSiteCode" value="${searchVO.searchSiteCode}" />
	<input type="hidden" name="searchState" value="${searchVO.searchState}" />
	<input type="hidden" name="searchViewYn" value="${searchVO.searchViewYn}" />
	<input type="hidden" name="searchTxt" value="${searchVO.searchTxt}" />
</form>

<form:form commandName="searchVO" name="write" id="write" method="post" action="${pageContext.request.contextPath}/sys/pollMng/question/${not empty isNew ? 'writeProc' : 'updateProc'}.do?mId=${menuVO.mId}" enctype="multipart/form-data">
	<form:hidden path="idx" />
	<c:forEach var="question" items="${searchVO.questionList}" varStatus="status">
		<div id="questionDiv${status.index}" data-idx="${status.index}">
			<h3>질문${status.count}</h3>
			<table class="tableSt_write">
				<caption>질문정보를 등록하는 표입니다.</caption>
				<colgroup>
					<col class="col15 w15">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="questionList${status.index}.question"><span class="red">*</span>&nbsp;문항유형선택</label></th>
						<td>
							<form:select path="questionList[${status.index}].type" data-validator="on" data-required="y" data-fieldName="문항 유형">
								<form:option value="2">객관식 단답</form:option>
								<form:option value="1">객관식 중복</form:option>
								<form:option value="3">주관식 단답</form:option>
								<form:option value="4">주관식 장문</form:option>
								<form:option value="5">순위 선정형</form:option>
								<form:option value="6">매트릭스 형</form:option>
							</form:select>
							<input type="checkbox" id="questionList${status.index}.requiredYn" name="questionList[${status.index}].requiredYn" ${question.requiredYn eq 'Y' ? 'checked="checked"' : '' } /><label for="questionList${status.index}.requiredYn">필수 응답 문항</label>
						</td>
					</tr>
					<tr>
						<th><label for="questionList${status.index}.question"><span class="red">*</span>&nbsp;질문</label></th>
						<td>
							<form:input path="questionList[${status.index}].question" class="input500" data-validator="on" data-required="y" data-fieldName="질문" />
						</td>
					</tr>
					<tr>
						<th>질문이미지</th>
						<td>
							<p><span class="tip red">질문 이미지는 최대 804 x 536 사이즈를 권장합니다.</span></p>
							<span>
								<c:if test="${not empty question.questionFile}">
									<c:import url="/cmm/fms/fileInfo.do" charEncoding="utf-8">
										<c:param name="attchId" value="${question.questionFile }" />
										<c:param name="mode" value="fileName" />
										<c:param name="fileSn" value="0" />
									</c:import>
								</c:if>
							</span>
							<c:if test="${empty isNew}">
								<input type="hidden" id="questionList${status.index}.oldAtchFileId" name="questionList[${status.index}].oldAtchFileId" value="${question.questionFile}" />
								<input type="hidden" id="questionList${status.index}.atchFileDel" name="questionList[${status.index}].atchFileDel" value="N" />
							</c:if>
							<input type="file" id="questionList${status.index}.questionAtchfile" name="questionList[${status.index}].questionAtchfile" data-idx="${status.index}" class="disN" value="" />
							<a href="#" onclick="questionMng.question.setImg(this); return false;" class="btn_list_img_plus">찾아보기</a>
							<c:if test="${not empty question.questionFile}">
								<a href="#" onclick="questionMng.question.delImg(this); return false;" class="btn_list_img_delete mL5">개별삭제</a>
								<div>
									<input type="text" id="questionList${status.index}.questionFileCn" name="questionList[${status.index}].questionFileCn" class="input500 mT5" value="<c:import url="/cmm/fms/fileInfo.do" charEncoding="utf-8"><c:param name="attchId" value="${question.questionFile }" /><c:param name="mode" value="fileCn" /><c:param name="fileSn" value="0" /></c:import>"/>
									<p><span class="tip red">접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요.</span></p>
								</div>
							</c:if>
						</td>
					</tr>
					<%-- <c:if test="${question.type eq '2' or empty question.type }"> --%>
					<tr>
						<th><span class="red">*</span>&nbsp;문항 건너뛰기</th>
						<td>
							<input type="radio" id="questionList${status.index}.ableYn1" name="questionList[${status.index}].ableYn" value="N" ${question.ableYn eq 'Y' ? '' : 'checked="checked"' } /><label for="questionList${status.index}.ableYn1">건너뛰기 미설정</label>
							<input type="radio" id="questionList${status.index}.ableYn2" name="questionList[${status.index}].ableYn" value="Y" ${question.ableYn eq 'Y' ? 'checked="checked"' : '' } /><label for="questionList${status.index}.ableYn2">건너뛰기 설정</label>
							<div class="mT10" ${question.ableYn eq 'Y' ? '' : 'style="display:none;"'}>
								<c:if test="${empty question.ableList}">
									<p>
										<input type="text" id="questionList${status.index}.ableList0.questionNum" name="questionList[${status.index}].ableList[0].questionNum" data-questionnum="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input50" value="" /> 번 질문에 보기&nbsp;
										<input type="text" id="questionList${status.index}.ableList0.target" name="questionList[${status.index}].ableList[0].target" data-target="0" data-validator="on" data-inputType="num" class="input100" value="" /> 번으로 응답한 경우 질문 응답 가능&nbsp;
										<a href="#" onclick="questionMng.answerAble.add(this); return false;" class="btn_list_plus">보기 추가</a>
									</p>
								</c:if>
								<c:forEach var="able" items="${question.ableList}" varStatus="ableStatus">
									<p>
										<input type="${ableStatus.index ne 0 ? 'hidden' : 'text'}" id="questionList${status.index}.ableList${ableStatus.index}.questionNum" name="questionList[${status.index}].ableList[${ableStatus.index}].questionNum" data-questionnum="${ableStatus.index}" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input50" value="${able.questionNum}" />${ableStatus.index eq 0 ? ' 번 질문에 보기&nbsp;' : ''}
										<input type="text" id="questionList${status.index}.ableList${ableStatus.index}.target" name="questionList[${status.index}].ableList[${ableStatus.index}].target" data-target="${ableStatus.index}" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input100" ${ableStatus.index ne 0 ? 'style="margin-left:146px; margin-top:10px;"' : ''} value="${able.target}" /> 번으로 응답한 경우 질문 응답 가능&nbsp;
										<a href="#" onclick="questionMng.answerAble.add(this); return false;" class="btn_list_plus">보기 추가</a>
										<c:if test="${ableStatus.index ne 0}">
											<a href="#" onclick="questionMng.answerAble.del(this); return false;" class="btn_list_delete">보기 제거</a>
										</c:if>
									</p>
								</c:forEach>
							</div>
							<p class="tip red">작성 예) 1번 질문에 보기 2번으로 응답한 경우 질문 응답 가능</p>
						</td>
					</tr>
					<%-- </c:if> --%>
					<c:if test="${question.type eq '1' or empty question.type }">
						<tr>
							<th>다중선택 개수</th>
							<td>
								<form:select path="questionList[${status.index}].multipleLimit">
									<form:option value="">제한없음</form:option>
									<form:option value="2">2개</form:option>
									<form:option value="3">3개</form:option>
									<form:option value="4">4개</form:option>
									<form:option value="5">5개</form:option>
									<form:option value="6">6개</form:option>
									<form:option value="7">7개</form:option>
									<form:option value="8">8개</form:option>
									<form:option value="9">9개</form:option>
									<form:option value="10">10개</form:option>
								</form:select>
								<input type="radio" id="questionList${status.index}.isMore1" name="questionList[${status.index}].isMore" ${question.isMore ne 'U' and question.isMore ne 'L' ? 'checked="checked"' : ''} value="NONE" /><label for="questionList${status.index}.isMore1">이상/이하 적용안함</label>
								<input type="radio" id="questionList${status.index}.isMore2" name="questionList[${status.index}].isMore" ${question.isMore eq 'U' ? 'checked="checked"' : ''} value="U" /><label for="questionList${status.index}.isMore2">이상</label>
								<input type="radio" id="questionList${status.index}.isMore3" name="questionList[${status.index}].isMore" ${question.isMore eq 'L' ? 'checked="checked"' : ''} value="L" /><label for="questionList${status.index}.isMore3">이하</label>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<c:if test="${question.type eq '2' or question.type eq '1' or question.type eq '5'}">
				<table class="tableSt_write table_plus">
					<caption>질문정보에서 보기정보를 등록하는 표입니다.</caption>
					<colgroup>
						<col class="col15 w15">
						<col>
					</colgroup>
					<tbody>
						<c:if test="${empty question.answerList}">
							<tr>
								<th scope="row"><span class="red">*</span>&nbsp;보기1</th>
								<td>
									<input type="hidden" id="questionList0.answerList0.answerOrder" name="questionList[0].answerList[0].answerOrder" data-idx="0" value="0"/>
									<input type="text" id="questionList0.answerList0.answer" name="questionList[0].answerList[0].answer" class="input500" data-idx="0" value="" />
									<input type="file" id="questionList0.answerList0.answerAtchfile" name="questionList[0].answerList[0].answerAtchfile" data-idx="0" class="disN" value="" />
									<a href="#" onclick="questionMng.answer.addImg(this); return false;" class="btn_list_img_plus">이미지 추가</a>
									<a href="#" onclick="questionMng.answer.add(this); return false;" class="btn_list_plus">보기 추가</a>
									<a href="#" onclick="questionMng.answer.addEtc(this); return false;" class="btn_etc_plus">기타</a>
									<div style="display:none;">
										<p><span></span><a href="#" onclick="questionMng.answer.delImg(this); return false;" class="btn_list_img_delete mL5">개별삭제</a></p>
										<input type="text" id="questionList0.answerList0.answerFileCn" name="questionList[0].answerList[0].answerFileCn" data-idx="0" class="input500 mT5" placeholder="설명글을 입력하세요." />
										<p><span class="tip red">접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요.</span></p>
										<p><span class="tip red">보기 이미지는 최대 355 x 235 사이즈를 권장합니다.</span></p>
									</div>
								</td>
							</tr>
						</c:if>
						<c:forEach var="answer" items="${question.answerList}" varStatus="answerStatus">
							<c:if test="${answer.answer eq '기타'}">
								<c:set var="haveEtc" value="${true}" />
							</c:if>
						</c:forEach>
						<c:forEach var="answer" items="${question.answerList}" varStatus="answerStatus">
							<tr>
								<th scope="row"><span class="red">*</span>&nbsp;보기${answerStatus.count}</th>
								<td>
									<input type="hidden" id="questionList${status.index}.answerList${answerStatus.index}.answerOrder" name="questionList[${status.index}].answerList[${answerStatus.index}].answerOrder" data-idx="0" value="${answer.answerOrder}"/>
									<input type="text" id="questionList${status.index}.answerList${answerStatus.index}.answer" name="questionList[${status.index}].answerList[${answerStatus.index}].answer" class="input500" data-idx="0" value="${answer.answer}" ${answer.answer eq '기타' ? 'readonly="readonly"' : ''} />
									<input type="file" id="questionList${status.index}.answerList${answerStatus.index}.answerAtchfile" name="questionList[${status.index}].answerList[${answerStatus.index}].answerAtchfile" data-idx="0" class="disN" value="" />
									<c:if test="${answer.answer ne '기타'}"><a href="#" onclick="questionMng.answer.addImg(this); return false;" class="btn_list_img_plus">이미지 추가</a></c:if>
									<c:if test="${answer.answer ne '기타'}"><a href="#" onclick="questionMng.answer.add(this); return false;" class="btn_list_plus">보기 추가</a></c:if>
									<c:if test="${answerStatus.index ne 0}"><a href="#" class="btn_list_delete" onclick="questionMng.answer.del(this); return false;">보기 삭제</a></c:if>
									<c:if test="${!haveEtc}"><c:if test="${answerStatus.count eq fn:length(question.answerList)}"><a href="#" onclick="questionMng.answer.addEtc(this); return false;" class="btn_etc_plus">기타</a></c:if></c:if>
									<div ${fn:length(answer.answerFile) > 0 ? '' : 'style="display:none;"' } >
										<p>
											<span>
												<c:if test="${not empty answer.answerFile}">
													<c:import url="/cmm/fms/fileInfo.do" charEncoding="utf-8">
														<c:param name="attchId" value="${answer.answerFile }" />
														<c:param name="mode" value="fileName" />
														<c:param name="fileSn" value="0" />
													</c:import>
												</c:if>
											</span>
											<a href="#" onclick="questionMng.answer.delImg(this); return false;" class="btn_list_img_delete mL5">개별삭제</a>
										</p>
										<input type="text" id="questionList${status.index}.answerList${answerStatus.index}.answerFileCn" name="questionList[${status.index}].answerList[${answerStatus.index}].answerFileCn" data-idx="${answerStatus.index}" class="input500 mT5" placeholder="설명글을 입력하세요." value="<c:import url="/cmm/fms/fileInfo.do" charEncoding="utf-8"><c:param name="attchId" value="${answer.answerFile }" /><c:param name="mode" value="fileCn" /><c:param name="fileSn" value="0" /></c:import>" />
										<p><span class="tip red">접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요.</span></p>
										<p><span class="tip red">보기 이미지는 최대 355 x 235 사이즈를 권장합니다.</span></p>
									</div>
									<c:if test="${empty isNew}">
										<input type="hidden" id="questionList${status.index}.answerList${answerStatus.index}.oldAtchFileId" name="questionList[${status.index}].answerList[${answerStatus.index}].oldAtchFileId" value="${answer.answerFile}" />
										<input type="hidden" id="questionList${status.index}.answerList${answerStatus.index}.atchFileDel" name="questionList[${status.index}].answerList[${answerStatus.index}].atchFileDel" value="N" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test="${question.type eq '6'}">
				<table class="tableSt_write table_plus matrix">
					<caption>매트릭스 형 질문의 행 정보를 등록하는 표입니다.</caption>
					<colgroup>
						<col class="col15 w15">
						<col>
					</colgroup>
					<tbody>
						<c:if test="${empty question.matrixList}">
							<tr>
								<th scope="row"><span class="red">*</span>&nbsp;행1</th>
								<td>
									<input type="text" id="questionList99.matrixList0.matrixQuestion" name="questionList[99].matrixList[0].matrixQuestion" class="input300" value=""/>
									<a href="#" onclick="questionMng.matrix.row.add(this); return false;" class="btn_list_plus">보기 추가</a>
								</td>
							</tr>
						</c:if>
						<c:forEach var="matrixRow" items="${question.matrixList}" varStatus="matrixRowStatus">
							<tr>
								<th scope="row"><span class="red">*</span>&nbsp;행${matrixRowStatus.count}</th>
								<td>
									<input type="text" id="questionList${status.index}.matrixList${matrixRowStatus.index}.matrixQuestion" name="questionList[${status.index}].matrixList[${matrixRowStatus.index}].matrixQuestion" class="input300" value="${matrixRow.matrixQuestion}"/>
									<a href="#" onclick="questionMng.matrix.row.add(this); return false;" class="btn_list_plus">보기 추가</a>
									<c:if test="${matrixRowStatus.index ne 0}"><a href="#" onclick="questionMng.matrix.row.del(this); return false;" class="btn_list_delete">보기 삭제</a></c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="tableSt_write table_plus matrix">
					<caption>매트릭스 형 질문의 열 정보를 등록하는 표입니다.</caption>
					<colgroup>
						<col class="col15 w15">
						<col>
					</colgroup>
					<tbody>
						<c:if test="${empty question.matrixList}">
							<tr>
								<th scope="row"><span class="red">*</span>&nbsp;열1</th>
								<td>
									<input type="text" id="questionList99.matrixColList0.matrix" name="questionList[99].matrixColList[0].matrix" class="input300" value=""/>
									<a href="#" onclick="questionMng.matrix.col.add(this); return false;" class="btn_list_plus">보기 추가</a>
								</td>
							</tr>
						</c:if>
						<c:forEach var="matrixCol" items="${question.matrixColList}" varStatus="matrixColStatus">
							<tr>
								<th scope="row"><span class="red">*</span>&nbsp;열${matrixColStatus.count}</th>
								<td>
									<input type="text" id="questionList${status.index}.matrixColList${matrixColStatus.index}.matrix" name="questionList[${status.index}].matrixColList[${matrixColStatus.index}].matrix" class="input300" value="${matrixCol.matrix}"/>
									<a href="#" onclick="questionMng.matrix.col.add(this); return false;" class="btn_list_plus">보기 추가</a>
									<c:if test="${matrixColStatus.index ne 0}"><a href="#" onclick="questionMng.matrix.col.del(this); return false;" class="btn_list_delete">보기 삭제</a></c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<div class="btnboxR">
				<a href="#" onclick="questionMng.question.add(this); return false;" class="btn_normal">질문추가</a>
				<a href="#" onclick="questionMng.question.del(this); return false;" class="btn_normal">질문삭제</a>
			</div>
		</div>
	</c:forEach>
	<div class="btnboxC">
		<input type="submit" id="submitBtn" class="btn_mint" value="저장" />
		<a href="#" id="backBtn" class="btn_red" data-action="${_context}/sys/pollMng/list.do?mId=${menuVO.mId}" data-formname="list" data-formserialize="y" onclick="req.post(this); return false;">취소</a>
	</div>
</form:form>

<%-- #### 객관식 단답 template #### --%>
<div id="template2" style="display:none;">
	<h3>질문1</h3>
	<table class="tableSt_write">
		<caption>질문정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="questionList99.type"><span class="red">*</span>&nbsp;문항유형선택</label></th>
				<td>
					<select id="questionList99.type" name="questionList[99].type" data-required="y" data-fieldname="문항 유형" data-validator="on">
						<option value="2" selected="selected">객관식 단답</option>
						<option value="1">객관식 중복</option>
						<option value="3">주관식 단답</option>
						<option value="4">주관식 장문</option>
						<option value="5">순위 선정형</option>
						<option value="6">매트릭스 형</option>
					</select>
					<input type="checkbox" id="questionList99.requiredYn" name="questionList[99].requiredYn" checked="checked" /><label for="questionList99.requiredYn">필수 응답 문항</label>
				</td>
			</tr>
			<tr>
				<th><label for="questionList99.question"><span class="red">*</span>&nbsp;질문</label></th>
				<td>
					<input type="text" id="questionList99.question" name="questionList[99].question" class="input500" data-validator="on" data-required="y" data-fieldName="질문" />
				</td>
			</tr>
			<tr>
				<th>질문이미지</th>
				<td>
					<span></span>
					<input type="file" id="questionList99.questionAtchfile" name="questionList[99].questionAtchfile" data-idx="0" class="disN" value="" />
					<p><span class="tip red">질문 이미지는 최대 804 x 536 사이즈를 권장합니다.</span></p>
					<a href="#" onclick="questionMng.question.setImg(this); return false;" class="btn_list_img_plus">찾아보기</a>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>&nbsp;문항 건너뛰기</th>
				<td>
					<input type="radio" id="questionList99.ableYn1" name="questionList[99].ableYn" value="N" checked="checked" /><label for="questionList99.ableYn1">건너뛰기 미설정</label>
					<input type="radio" id="questionList99.ableYn2" name="questionList[99].ableYn" value="Y" /><label for="questionList99.ableYn2">건너뛰기 설정</label>
					<div class="mT10" style="display:none;">
						<p>
							<input type="text" id="questionList99.ableList0.questionNum" name="questionList[99].ableList[0].questionNum" data-questionnum="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input50" value="" /> 번 질문에 보기&nbsp;
							<input type="text" id="questionList99.ableList0.target" name="questionList[99].ableList[0].target" data-target="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input100" value="" /> 번으로 응답한 경우 질문 응답 가능&nbsp;
							<a href="#" onclick="questionMng.answerAble.add(this); return false;" class="btn_list_plus">보기 추가</a>
						</p>
					</div>
					<p class="tip red" style="display:none;">작성 예) 1번 질문에 보기 2번으로 응답한 경우 질문 응답 가능</p>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="tableSt_write table_plus">
		<caption>질문정보에서 보기정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span>&nbsp;보기1</th>
				<td>
					<input type="hidden" id="questionList99.answerList0.answerOrder" name="questionList[99].answerList[0].answerOrder" data-idx="0" value="0"/>
					<input type="text" id="questionList99.answerList0.answer" name="questionList[99].answerList[0].answer" class="input500" data-idx="0" value="" />
					<input type="file" id="questionList99.answerList0.answerAtchfile" name="questionList[99].answerList[0].answerAtchfile" data-idx="0" class="disN" value="" />
					<a href="#" onclick="questionMng.answer.addImg(this); return false;" class="btn_list_img_plus">이미지 추가</a>
					<a href="#" onclick="questionMng.answer.add(this); return false;" class="btn_list_plus">보기 추가</a>
					<a href="#" onclick="questionMng.answer.addEtc(this); return false;" class="btn_etc_plus">기타</a>
					<div style="display:none;">
						<p><span></span><a href="#" onclick="questionMng.answer.delImg(this); return false;" class="btn_list_img_delete mL5">개별삭제</a></p>
						<input type="text" id="questionList99.answerList0.answerFileCn" name="questionList[99].answerList[0].answerFileCn" data-idx="0" class="input500 mT5" placeholder="설명글을 입력하세요." />
						<p><span class="tip red">접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요.</span></p>
						<p><span class="tip red">보기 이미지는 최대 355 x 235 사이즈를 권장합니다.</span></p>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="btnboxR">
		<a href="#" onclick="questionMng.question.add(this); return false;" class="btn_normal">질문추가</a>
		<a href="#" onclick="questionMng.question.del(this); return false;" class="btn_normal">질문삭제</a>
	</div>
</div>

<%-- #### 객관식 중복 template #### --%>
<div id="template1" style="display:none;">
	<h3>질문1</h3>
	<table class="tableSt_write">
		<caption>질문정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="questionList99.type"><span class="red">*</span>&nbsp;문항유형선택</label></th>
				<td>
					<select id="questionList99.type" name="questionList[99].type" data-required="y" data-fieldname="문항 유형" data-validator="on">
						<option value="2">객관식 단답</option>
						<option value="1" selected="selected">객관식 중복</option>
						<option value="3">주관식 단답</option>
						<option value="4">주관식 장문</option>
						<option value="5">순위 선정형</option>
						<option value="6">매트릭스 형</option>
					</select>
					<input type="checkbox" id="questionList99.requiredYn" name="questionList[99].requiredYn" checked="checked" /><label for="questionList99.requiredYn">필수 응답 문항</label>
				</td>
			</tr>
			<tr>
				<th><label for="questionList99.question"><span class="red">*</span>&nbsp;질문</label></th>
				<td>
					<input type="text" id="questionList99.question" name="questionList[99].question" class="input500" data-validator="on" data-required="y" data-fieldName="질문" />
				</td>
			</tr>
			<tr>
				<th>질문이미지</th>
				<td>
					<span></span>
					<input type="file" id="questionList99.questionAtchfile" name="questionList[99].questionAtchfile" data-idx="0" class="disN" value="" />
					<p><span class="tip red">질문 이미지는 최대 804 x 536 사이즈를 권장합니다.</span></p>
					<a href="#" onclick="questionMng.question.setImg(this); return false;" class="btn_list_img_plus">찾아보기</a>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>&nbsp;문항 건너뛰기</th>
				<td>
					<input type="radio" id="questionList99.ableYn1" name="questionList[99].ableYn" value="N" checked="checked" /><label for="questionList99.ableYn1">건너뛰기 미설정</label>
					<input type="radio" id="questionList99.ableYn2" name="questionList[99].ableYn" value="Y" /><label for="questionList99.ableYn2">건너뛰기 설정</label>
					<div class="mT10" style="display:none;">
						<p>
							<input type="text" id="questionList99.ableList0.questionNum" name="questionList[99].ableList[0].questionNum" data-questionnum="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input50" value="" /> 번 질문에 보기&nbsp;
							<input type="text" id="questionList99.ableList0.target" name="questionList[99].ableList[0].target" data-target="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input100" value="" /> 번으로 응답한 경우 질문 응답 가능&nbsp;
							<a href="#" onclick="questionMng.answerAble.add(this); return false;" class="btn_list_plus">보기 추가</a>
						</p>
					</div>
					<p class="tip red" style="display:none;">작성 예) 1번 질문에 보기 2번으로 응답한 경우 질문 응답 가능</p>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>&nbsp;다중선택 개수</th>
				<td>
					<select id="questionList99.multipleLimit" name="questionList[99].multipleLimit">
						<option value="" selected="selected">제한없음</option>
						<option value="2">2개</option>
						<option value="3">3개</option>
						<option value="4">4개</option>
						<option value="5">5개</option>
						<option value="6">6개</option>
						<option value="7">7개</option>
						<option value="8">8개</option>
						<option value="9">9개</option>
						<option value="10">10개</option>
					</select>
					<input type="radio" id="questionList99.isMore1" name="questionList[99].isMore" checked="checked" value="NONE" /><label for="questionList99.isMore1">이상/이하 적용안함</label>
					<input type="radio" id="questionList99.isMore2" name="questionList[99].isMore" value="U" /><label for="questionList99.isMore2">이상</label>
					<input type="radio" id="questionList99.isMore3" name="questionList[99].isMore" value="L" /><label for="questionList99.isMore3">이하</label>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="tableSt_write table_plus">
		<caption>질문정보에서 보기정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span>&nbsp;보기1</th>
				<td>
					<input type="hidden" id="questionList99.answerList0.answerOrder" name="questionList[99].answerList[0].answerOrder" data-idx="0" value="0"/>
					<input type="text" id="questionList99.answerList0.answer" name="questionList[99].answerList[0].answer" class="input500" data-idx="0" value="" />
					<input type="file" id="questionList99.answerList0.answerAtchfile" name="questionList[99].answerList[0].answerAtchfile" data-idx="0" class="disN" value="" />
					<a href="#" onclick="questionMng.answer.addImg(this); return false;" class="btn_list_img_plus">이미지 추가</a>
					<a href="#" onclick="questionMng.answer.add(this); return false;" class="btn_list_plus">보기 추가</a>
					<a href="#" onclick="questionMng.answer.addEtc(this); return false;" class="btn_etc_plus">기타</a>
					<div style="display:none;">
						<p><span></span><a href="#" onclick="questionMng.answer.delImg(this); return false;" class="btn_list_img_delete mL5">개별삭제</a></p>
						<input type="text" id="questionList99.answerList0.answerFileCn" name="questionList[99].answerList[0].answerFileCn" data-idx="0" class="input500 mT5" placeholder="설명글을 입력하세요." />
						<p><span class="tip red">접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요.</span></p>
						<p><span class="tip red">보기 이미지는 최대 355 x 235 사이즈를 권장합니다.</span></p>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="btnboxR">
		<a href="#" onclick="questionMng.question.add(this); return false;" class="btn_normal">질문추가</a>
		<a href="#" onclick="questionMng.question.del(this); return false;" class="btn_normal">질문삭제</a>
	</div>
</div>

<%-- #### 주관식 단답 template #### --%>
<div id="template3" style="display:none;">
	<h3>질문3</h3>
	<table class="tableSt_write">
		<caption>질문정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="questionList99.type"><span class="red">*</span>&nbsp;문항유형선택</label></th>
				<td>
					<select id="questionList99.type" name="questionList[99].type" data-required="y" data-fieldname="문항 유형" data-validator="on">
						<option value="2">객관식 단답</option>
						<option value="1">객관식 중복</option>
						<option value="3" selected="selected">주관식 단답</option>
						<option value="4">주관식 장문</option>
						<option value="5">순위 선정형</option>
						<option value="6">매트릭스 형</option>
					</select>
					<input type="checkbox" id="questionList99.requiredYn" name="questionList[99].requiredYn" checked="checked" /><label for="questionList99.requiredYn">필수 응답 문항</label>
				</td>
			</tr>
			<tr>
				<th><label for="questionList99.question"><span class="red">*</span>&nbsp;질문</label></th>
				<td>
					<input type="text" id="questionList99.question" name="questionList[99].question" class="input500" data-validator="on" data-required="y" data-fieldName="질문" />
				</td>
			</tr>
			<tr>
				<th>질문이미지</th>
				<td>
					<span></span>
					<input type="file" id="questionList99.questionAtchfile" name="questionList[99].questionAtchfile" data-idx="0" class="disN" value="" />
					<p><span class="tip red">질문 이미지는 최대 804 x 536 사이즈를 권장합니다.</span></p>
					<a href="#" onclick="questionMng.question.setImg(this); return false;" class="btn_list_img_plus">찾아보기</a>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>&nbsp;문항 건너뛰기</th>
				<td>
					<input type="radio" id="questionList99.ableYn1" name="questionList[99].ableYn" value="N" checked="checked" /><label for="questionList99.ableYn1">건너뛰기 미설정</label>
					<input type="radio" id="questionList99.ableYn2" name="questionList[99].ableYn" value="Y" /><label for="questionList99.ableYn2">건너뛰기 설정</label>
					<div class="mT10" style="display:none;">
						<p>
							<input type="text" id="questionList99.ableList0.questionNum" name="questionList[99].ableList[0].questionNum" data-questionnum="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input50" value="" /> 번 질문에 보기&nbsp;
							<input type="text" id="questionList99.ableList0.target" name="questionList[99].ableList[0].target" data-target="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input100" value="" /> 번으로 응답한 경우 질문 응답 가능&nbsp;
							<a href="#" onclick="questionMng.answerAble.add(this); return false;" class="btn_list_plus">보기 추가</a>
						</p>
					</div>
					<p class="tip red" style="display:none;">작성 예) 1번 질문에 보기 2번으로 응답한 경우 질문 응답 가능</p>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btnboxR">
		<a href="#" onclick="questionMng.question.add(this); return false;" class="btn_normal">질문추가</a>
		<a href="#" onclick="questionMng.question.del(this); return false;" class="btn_normal">질문삭제</a>
	</div>
</div>


<%-- #### 주관식 장문 template #### --%>
<div id="template4" style="display:none;">
	<h3>질문3</h3>
	<table class="tableSt_write">
		<caption>질문정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="questionList99.type"><span class="red">*</span>&nbsp;문항유형선택</label></th>
				<td>
					<select id="questionList99.type" name="questionList[99].type" data-required="y" data-fieldname="문항 유형" data-validator="on">
						<option value="2">객관식 단답</option>
						<option value="1">객관식 중복</option>
						<option value="3">주관식 단답</option>
						<option value="4" selected="selected">주관식 장문</option>
						<option value="5">순위 선정형</option>
						<option value="6">매트릭스 형</option>
					</select>
					<input type="checkbox" id="questionList99.requiredYn" name="questionList[99].requiredYn" checked="checked" /><label for="questionList99.requiredYn">필수 응답 문항</label>
				</td>
			</tr>
			<tr>
				<th><label for="questionList99.question"><span class="red">*</span>&nbsp;질문</label></th>
				<td>
					<input type="text" id="questionList99.question" name="questionList[99].question" class="input500" data-validator="on" data-required="y" data-fieldName="질문" />
				</td>
			</tr>
			<tr>
				<th>질문이미지</th>
				<td>
					<span></span>
					<input type="file" id="questionList99.questionAtchfile" name="questionList[99].questionAtchfile" data-idx="0" class="disN" value="" />
					<p><span class="tip red">질문 이미지는 최대 804 x 536 사이즈를 권장합니다.</span></p>
					<a href="#" onclick="questionMng.question.setImg(this); return false;" class="btn_list_img_plus">찾아보기</a>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>&nbsp;문항 건너뛰기</th>
				<td>
					<input type="radio" id="questionList99.ableYn1" name="questionList[99].ableYn" value="N" checked="checked" /><label for="questionList99.ableYn1">건너뛰기 미설정</label>
					<input type="radio" id="questionList99.ableYn2" name="questionList[99].ableYn" value="Y" /><label for="questionList99.ableYn2">건너뛰기 설정</label>
					<div class="mT10" style="display:none;">
						<p>
							<input type="text" id="questionList99.ableList0.questionNum" name="questionList[99].ableList[0].questionNum" data-questionnum="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input50" value="" /> 번 질문에 보기&nbsp;
							<input type="text" id="questionList99.ableList0.target" name="questionList[99].ableList[0].target" data-target="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input100" value="" /> 번으로 응답한 경우 질문 응답 가능&nbsp;
							<a href="#" onclick="questionMng.answerAble.add(this); return false;" class="btn_list_plus">보기 추가</a>
						</p>
					</div>
					<p class="tip red" style="display:none;">작성 예) 1번 질문에 보기 2번으로 응답한 경우 질문 응답 가능</p>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btnboxR">
		<a href="#" onclick="questionMng.question.add(this); return false;" class="btn_normal">질문추가</a>
		<a href="#" onclick="questionMng.question.del(this); return false;" class="btn_normal">질문삭제</a>
	</div>
</div>

<%-- #### 순위선정형 template #### --%>
<div id="template5" style="display:none;">
	<h3>질문5</h3>
	<table class="tableSt_write">
		<caption>질문정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="questionList99.type"><span class="red">*</span>&nbsp;문항유형선택</label></th>
				<td>
					<select id="questionList99.type" name="questionList[99].type" data-required="y" data-fieldname="문항 유형" data-validator="on">
						<option value="2">객관식 단답</option>
						<option value="1">객관식 중복</option>
						<option value="3">주관식 단답</option>
						<option value="4">주관식 장문</option>
						<option value="5" selected="selected">순위 선정형</option>
						<option value="6">매트릭스 형</option>
					</select>
					<input type="checkbox" id="questionList99.requiredYn" name="questionList[99].requiredYn" checked="checked" /><label for="questionList99.requiredYn">필수 응답 문항</label>
				</td>
			</tr>
			<tr>
				<th><label for="questionList99.question"><span class="red">*</span>&nbsp;질문</label></th>
				<td>
					<input type="text" id="questionList99.question" name="questionList[99].question" class="input500" data-validator="on" data-required="y" data-fieldName="질문" />
				</td>
			</tr>
			<tr>
				<th>질문이미지</th>
				<td>
					<span></span>
					<input type="file" id="questionList99.questionAtchfile" name="questionList[99].questionAtchfile" data-idx="0" class="disN" value="" />
					<p><span class="tip red">질문 이미지는 최대 804 x 536 사이즈를 권장합니다.</span></p>
					<a href="#" onclick="questionMng.question.setImg(this); return false;" class="btn_list_img_plus">찾아보기</a>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>&nbsp;문항 건너뛰기</th>
				<td>
					<input type="radio" id="questionList99.ableYn1" name="questionList[99].ableYn" value="N" checked="checked" /><label for="questionList99.ableYn1">건너뛰기 미설정</label>
					<input type="radio" id="questionList99.ableYn2" name="questionList[99].ableYn" value="Y" /><label for="questionList99.ableYn2">건너뛰기 설정</label>
					<div class="mT10" style="display:none;">
						<p>
							<input type="text" id="questionList99.ableList0.questionNum" name="questionList[99].ableList[0].questionNum" data-questionnum="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input50" value="" /> 번 질문에 보기&nbsp;
							<input type="text" id="questionList99.ableList0.target" name="questionList[99].ableList[0].target" data-target="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input100" value="" /> 번으로 응답한 경우 질문 응답 가능&nbsp;
							<a href="#" onclick="questionMng.answerAble.add(this); return false;" class="btn_list_plus">보기 추가</a>
						</p>
					</div>
					<p class="tip red" style="display:none;">작성 예) 1번 질문에 보기 2번으로 응답한 경우 질문 응답 가능</p>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="tableSt_write table_plus">
		<caption>질문정보에서 보기정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span>&nbsp;보기1</th>
				<td>
					<input type="hidden" id="questionList99.answerList0.answerOrder" name="questionList[99].answerList[0].answerOrder" data-idx="0" value="0"/>
					<input type="text" id="questionList99.answerList0.answer" name="questionList[99].answerList[0].answer" class="input500" data-idx="0" value="" />
					<input type="file" id="questionList99.answerList0.answerAtchfile" name="questionList[99].answerList[0].answerAtchfile" data-idx="0" class="disN" value="" />
					<a href="#" onclick="questionMng.answer.addImg(this); return false;" class="btn_list_img_plus">이미지 추가</a>
					<a href="#" onclick="questionMng.answer.add(this); return false;" class="btn_list_plus">보기 추가</a>
					<a href="#" onclick="questionMng.answer.addEtc(this); return false;" class="btn_etc_plus">기타</a>
					<div style="display:none;">
						<p><span></span><a href="#" onclick="questionMng.answer.delImg(this); return false;" class="btn_list_img_delete mL5">개별삭제</a></p>
						<input type="text" id="questionList99.answerList0.answerFileCn" name="questionList[99].answerList[0].answerFileCn" data-idx="0" class="input500 mT5" placeholder="설명글을 입력하세요." />
						<p><span class="tip red">접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요.</span></p>
						<p><span class="tip red">보기 이미지는 최대 355 x 235 사이즈를 권장합니다.</span></p>
					</div>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btnboxR">
		<a href="#" onclick="questionMng.question.add(this); return false;" class="btn_normal">질문추가</a>
		<a href="#" onclick="questionMng.question.del(this); return false;" class="btn_normal">질문삭제</a>
	</div>
</div>

<%-- #### 매트릭스형 template #### --%>
<div id="template6" style="display:none;">
	<h3>질문6</h3>
	<table class="tableSt_write">
		<caption>질문정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="questionList99.type"><span class="red">*</span>&nbsp;문항유형선택</label></th>
				<td>
					<select id="questionList99.type" name="questionList[99].type" data-required="y" data-fieldname="문항 유형" data-validator="on">
						<option value="2">객관식 단답</option>
						<option value="1">객관식 중복</option>
						<option value="3">주관식 단답</option>
						<option value="4">주관식 장문</option>
						<option value="5">순위 선정형</option>
						<option value="6" selected="selected">매트릭스 형</option>
					</select>
					<input type="checkbox" id="questionList99.requiredYn" name="questionList[99].requiredYn" checked="checked" /><label for="questionList99.requiredYn">필수 응답 문항</label>
				</td>
			</tr>
			<tr>
				<th><label for="questionList99.question"><span class="red">*</span>&nbsp;질문</label></th>
				<td>
					<input type="text" id="questionList99.question" name="questionList[99].question" class="input500" data-validator="on" data-required="y" data-fieldName="질문" />
				</td>
			</tr>
			<tr>
				<th>질문이미지</th>
				<td>
					<span></span>
					<input type="file" id="questionList99.questionAtchfile" name="questionList[99].questionAtchfile" data-idx="0" class="disN" value="" />
					<p><span class="tip red">질문 이미지는 최대 804 x 536 사이즈를 권장합니다.</span></p>
					<a href="#" onclick="questionMng.question.setImg(this); return false;" class="btn_list_img_plus">찾아보기</a>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>&nbsp;문항 건너뛰기</th>
				<td>
					<input type="radio" id="questionList99.ableYn1" name="questionList[99].ableYn" value="N" checked="checked" /><label for="questionList99.ableYn1">건너뛰기 미설정</label>
					<input type="radio" id="questionList99.ableYn2" name="questionList[99].ableYn" value="Y" /><label for="questionList99.ableYn2">건너뛰기 설정</label>
					<div class="mT10" style="display:none;">
						<p>
							<input type="text" id="questionList99.ableList0.questionNum" name="questionList[99].ableList[0].questionNum" data-questionnum="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input50" value="" /> 번 질문에 보기&nbsp;
							<input type="text" id="questionList99.ableList0.target" name="questionList[99].ableList[0].target" data-target="0" data-validator="on" data-inputType="num" data-fieldName="문항 건너뛰기 항목" class="input100" value="" /> 번으로 응답한 경우 질문 응답 가능&nbsp;
							<a href="#" onclick="questionMng.answerAble.add(this); return false;" class="btn_list_plus">보기 추가</a>
						</p>
					</div>
					<p class="tip red" style="display:none;">작성 예) 1번 질문에 보기 2번으로 응답한 경우 질문 응답 가능</p>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="tableSt_write table_plus matrix">
		<caption>매트릭스 형 질문의 행 정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span>&nbsp;행1</th>
				<td>
					<input type="text" id="questionList99.matrixList0.matrixQuestion" name="questionList[99].matrixList[0].matrixQuestion" class="input300" value=""/>
					<a href="#" onclick="questionMng.matrix.row.add(this); return false;" class="btn_list_plus">보기 추가</a>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="tableSt_write table_plus matrix">
		<caption>매트릭스 형 질문의 열 정보를 등록하는 표입니다.</caption>
		<colgroup>
			<col class="col15 w15">
			<col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span class="red">*</span>&nbsp;열1</th>
				<td>
					<input type="text" id="questionList99.matrixColList0.matrix" name="questionList[99].matrixColList[0].matrix" class="input300" value=""/>
					<a href="#" onclick="questionMng.matrix.col.add(this); return false;" class="btn_list_plus">보기 추가</a>
					<%--<a href="#" class="btn_list_delete">항목 삭제</a>--%>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="btnboxR">
		<a href="#" onclick="questionMng.question.add(this); return false;" class="btn_normal">질문추가</a>
		<a href="#" onclick="questionMng.question.del(this); return false;" class="btn_normal">질문삭제</a>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/require.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/basic/pollMng/questionMng.js"></script>
<script type="text/javascript">
questionMng.mode = "${not empty isNew ? 'insert' : 'update'}";
</script>
