<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황관리 데이터 등록/수정
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.05.04		J.Ryeon Lee		최초 생성 및 코드 수정
	 * 2017.04.07		권태성			개별 첨부파일 컬럼 소스 적용
	 * 2017.05.29		권태성			설명글 옵션 적용
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.05.04
	 */
%>
<c:set var="isNew" value="${empty entity['IDX']}" />
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>
<script type="text/javascript" src="/common/js/datePicker.js"></script>
<script type="text/javascript">
//<![CDATA[

	jQuery(document).ready(function() {
		dp.bind(jQuery("[data-date=y]"));
		<c:if test="${!isNew}">
		$("td[data-fileName]").each(function(index, el) {	//기존 첨부파일이 있는 경우 신규 추가 파일입력 폼은 제거
			if ($(this).find("ul")) {
				$("#fileDiv_"+$(this).attr("data-fileName")).remove();
			}
			if ($(this).children("[id='updateFileList']").children("ul").length > 0) {
				//파일삭제 이벤트를 받기 위해 기존의 파일삭제 onclick 이벤트를 교체
				var event = $(this).children("[id='updateFileList']").children("ul").children("li").children("input[type='button']").attr("onclick").replace("return false;", "").replace("fn_egov_deleteFile('", "").replace("'); ", "").replace("','", ",");
				$(this).children("[id='updateFileList']").children("ul").children("li").children("input[type='button']").attr("onclick", "fileDelete('"+event.split(",")[0]+"', '"+event.split(",")[1]+"', '"+$(this).attr("data-fileName")+"','"+$(this).attr("data-desc")+"','"+$(this).attr("data-require")+"'); return false;");
			}
			if ($(this).children("[id='updateFileList']").find("input").length <= 0) {
				//혹시라도 ATTACH_ID만 가지고 있고 실제 파일은 없는 경우 파일 입력폼 추가해줌
				$("td[data-fileName='"+$(this).attr("data-fileName")+"']").html(getFileFormHTML($(this).attr("data-fileName"), $(this).attr("data-desc"), $(this).attr("data-require")));
			}
		});
		</c:if>
	});

	/** 기존의 파일 삭제 함수를 대체해서 이벤트를 처리 할 함수 **/
	function fileDelete(atchId, fileSn, target, desc, require) {
		if (fn_egov_deleteFile(atchId, fileSn)) {
			$("td[data-fileName='"+target+"']").html(getFileFormHTML(target, desc, require));
		}
	}

	/** 파일 입력폼 HTML 함수 **/
	function getFileFormHTML(target, desc, require) {
		var html = "<input type=\"file\" id=\"id_"+target+"\" name=\"dmt_"+target+"\" data-validator=\"on\" data-fieldName=\""+desc+"\" data-required=\""+require+"\" class=\"input300 mB10\" value=\"\" />";
		html += "<div class=\"pT5\"><label for=\"file_cn_"+target+"\">설명글</label>: <input id=\"dmt_cn_"+target+"\" type=\"text\" name=\"dmt_cn_"+target+"\" value=\"\" class=\"input400\" /></div>";
		html += "<p class=\"tip\">※ 접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요(확장자 jpg, gif, png, bmp 파일)</p>";
		return html;
	}

	function fn_add_proc() {
		if (validator() && confirm("등록하시겠습니까?")) {
			// beforeSubmit
			jQuery("#save").hide();
			return true;
		}

		return false;
	}

//]]>
</script>

<h2>${tableDef.tableDesc} 현황 등록/수정</h2>
<p class="info"><span class="red">*</span>는 필수 항목입니다.</p>
<form name="listForm" id="listForm" method="post" action="/sys/dataMng/${tableDef.urlName}/list.do?mId=${menuVO.mId}">
	<input type="hidden" name="page" value="${searchVO['page']}" />
	<input type="hidden" name="searchType" value="${searchVO['searchType']}" />
	<input type="hidden" name="searchTxt" value="${searchVO['searchTxt']}" />
</form>
<form name="form" id="form" method="post" action="/sys/dataMng/${tableDef.urlName}/${isNew ? 'writeProc' : 'modifyProc'}.do?mId=${menuVO.mId}" onsubmit="return fn_add_proc();" enctype="multipart/form-data">
	<input type="hidden" name="page" value="${searchVO['page']}" />
	<input type="hidden" name="searchType" value="${searchVO['searchType']}" />
	<input type="hidden" name="searchTxt" value="${searchVO['searchTxt']}" />
	<input type="hidden" name="IDX" value="${entity['IDX']}" />

	<table class="tableSt_write">
		<caption>항목별 입력을 제공하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width:15%"/>
			<col span="1" />
		</colgroup>
		<tbody>
			<%-- ============================ 입력 필드 ============================ --%>
			<c:forEach var="def" items="${tableDef.columnDefList}" varStatus="status">
				<c:if test="${def.isUse == 'Y'}">
					<c:set var="colName" value="COL_${def.name}" />
					<tr>
						<th scope="row">
							<c:if test="${def.nullable == 'N'}"><span class="red">*</span></c:if>
							<label for="id_${def.name}">${def.description}</label>
						</th>
						<td <c:if test="${def.type == 'file'}">data-fileName="${def.name}" data-desc="${def.description}" data-require="${def.nullable eq 'N' ? 'y' : 'n'}"</c:if>>
							<c:choose>
								<c:when test="${def.type == 'file'}">
									<%-- col.type이 file 타입인 경우 파일 정보가 있을때는 파일 목록을 출력해주고 없을때는 파일 입력폼을 출력 --%>
									<c:choose>
										<c:when test="${isNew eq false && not empty entity[colName]}">
											<input type="hidden" name="${def.name}" value="${entity[colName]}" />
											<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
												<c:param name="param_atchFileId" value="${entity[colName]}" />
												<c:param name="param_showThumbnail" value="${true}" />
												<c:param name="param_thumbWidth" value="200" />
												<c:param name="downloadYn" value="Y"/>
												<c:param name="param_showComment" value="${true}" />
												<c:param name="fileCnId" value="dmt_cn_${def.name}" />
											</c:import>
											<div id="fileDiv_${def.name}">
												<input type="file" id="id_${def.name}" name="dmt_${def.name}" data-validator="on" data-fieldName="${def.description}" <c:if test="${def.nullable == 'N'}">data-required="y"</c:if> class="input300 mB10 mT10" value="" />
												<div class="pT5"><label for="file_cn_${def.name}">설명글</label>: <input id="dmt_cn_${def.name}" type="text" name="dmt_cn_${def.name}" value="" class="input400" /></div>
												<p class="tip">※ 접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요(확장자 jpg, gif, png, bmp 파일)</p>
											</div>
										</c:when>
										<c:otherwise>
											<input type="file" id="id_${def.name}" name="dmt_${def.name}" data-validator="on" data-fieldName="${def.description}" <c:if test="${def.nullable == 'N'}">data-required="y"</c:if> class="input300 mB10" value="" />
											<div class="pT5"><label for="file_cn_${def.name}">설명글</label>: <input id="dmt_cn_${def.name}" type="text" name="dmt_cn_${def.name}" value="" class="input400" /></div>
											<p class="tip">※ 접근성 향상을 위해 이미지 파일은 설명글을 필수로 작성해주세요(확장자 jpg, gif, png, bmp 파일)</p>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${def.type == 'clob'}">
									<textarea id="id_${def.name}" name="${def.name}" maxlength="${def.size}" class="input500" rows="10"
										data-validator="on" data-fieldName="${def.description}"
										<c:if test="${def.nullable == 'N'}">data-required="y"</c:if>
										<c:if test="${def.type == 'number'}">data-inputType="num"</c:if>
									>${entity[colName]}</textarea>
								</c:when>
								<c:when test="${def.type == 'date'}">
									<input type="text" id="id_${def.name}" name="${def.name}" class="input100 mR10" maxlength="${def.size}"
										<c:if test="${!isNew}">value="${entity[colName]}"</c:if>
										data-validator="on" data-inputType="date" data-date="y" data-fieldName="${def.description}"
										<c:if test="${def.nullable == 'N'}">data-required="y"</c:if>
									/>
								</c:when>
								<c:when test="${def.selectType ne 'N' and not empty def.optionValues }">
									<c:set var="optionsList" value="${fn:split(def.optionValues, ',')}" />
									<c:if test="${def.selectType eq 'S'}">
										<select id="id_${def.name }" name="${def.name }">
											<c:forEach var="option" items="${optionsList}">
												<c:set var="option" value="${fn:trim(option)}" />
												<option value="${option}" <c:if test="${option eq entity[colName]}">selected="selected"</c:if>>${option}</option>
											</c:forEach>
										</select>
									</c:if>
									<c:if test="${def.selectType eq 'R'}">
										<c:forEach var="option" items="${optionsList}" varStatus="sts">
											<input type="radio" id="id_${def.name}_${sts.count}" name="${def.name}" value="${option}"
												<c:if test="${sts.index == 0 && def.nullable == 'N'}">data-validator="on" data-required="y" data-fieldName="${def.description}"</c:if>
												<c:if test="${option eq entity[colName]}">checked="checked"</c:if>
											/>
											<label for="id_${def.name}_${sts.count}">${option}</label>&nbsp;
										</c:forEach>
									</c:if>
									<c:if test="${def.selectType eq 'C'}">
										<c:set var="valueList" value="${fn:split(entity[colName], ',')}" />
										<c:forEach var="option" items="${optionsList}" varStatus="sts">
											<c:set var="checked" value="" />
											<c:forEach var="val" items="${valueList}">
												<c:if test="${option eq val}"><c:set var="checked" value="checked='checked'" /></c:if>
											</c:forEach>
											<input type="checkbox" id="id_${def.name}_${sts.count}" name="${def.name}" value="${option}" ${checked}
												<c:if test="${sts.index == 0 && def.nullable == 'N'}">data-validator="on" data-required="y" data-fieldName="${def.description}"</c:if>
											/>
											<label for="id_${def.name}_${sts.count}">${option}</label>&nbsp;
										</c:forEach>
									</c:if>
								</c:when>
								<c:otherwise>
									<input type="text" id="id_${def.name}" name="${def.name}" maxlength="${def.size}"
										<c:choose>
											<c:when test="${def.size >= 500}">class="input500"</c:when>
											<c:when test="${def.size >= 300}">class="input300"</c:when>
											<c:when test="${def.size <= 100}">class="input100"</c:when>
										</c:choose>
										<c:if test="${!isNew}">value="${entity[colName]}"</c:if>
										data-validator="on" data-fieldName="${def.description}"
										<c:if test="${def.nullable == 'N'}">data-required="y"</c:if>
										<c:if test="${def.type == 'number'}">data-inputType="num"</c:if>
										<c:if test="${def.type == 'time'}">data-inputType="time"</c:if>
									/>
								</c:otherwise>
							</c:choose>
							<c:if test="${not empty def.infoMsg}">
								<p class="tip">${def.infoMsg}</p>
							</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			<%-- ============================ 카테고리 ============================ --%>
			<c:forEach var="categoryDef" items="${tableDef.categoryDefList }" varStatus="sts">
				<c:if test="${categoryDef.showYn eq 'Y' }">
					<c:if test="${sts.count eq 1 }"><c:set var="categoryList" value="${tableDef.category1List}" /></c:if>
					<c:if test="${sts.count eq 2 }"><c:set var="categoryList" value="${tableDef.category2List}" /></c:if>
					<c:if test="${not empty categoryList}">
						<tr>
							<th scope="row"><span class="red">*</span><label for="CATEGORY">${categoryDef.name }</label></th>
							<td>
								<select name="CATEGORY${sts.count eq 1 ? '' : '2' }" data-validator="on" data-required="y" data-fieldName="카테고리">
									<option value="">선택</option>
									<c:forEach var="category" items="${categoryList}">
										<c:if test="${category.useYn eq 'Y' }">
											<option value="${category.categoryValue}" ${sts.count eq 1 and category.categoryValue eq entity['CATEGORY'] ? 'selected' : sts.count eq 2 and category.categoryValue eq entity['CATEGORY2'] ? 'selected' : ''}>${category.categoryKey}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
					</c:if>
				</c:if>
			</c:forEach>
			<%-- ============================ 첨부파일 ============================ --%>
			<c:if test="${tableDef.useFile == 'Y'}">
				<tr>
					<th scope="row"><label for="egovComFileUploader">첨부파일</label></th>
					<td id="test">
						<c:if test="${tableDef.imageOnly eq 'Y' }">
							<c:set var="param_showThumbnail" value="true" scope="request" />
							<c:set var="param_thumbWidth" value="200" scope="request" />
							<%-- 이미지만 업로드 가능하도록 --%>
							<input type="hidden" data-validator="on" data-inputType="file" />
						</c:if>
						<c:set var="fileCnt" value="${tableDef.fileCnt < 1 ? 1 : tableDef.fileCnt}" />
						<c:set var="param_maxFileNum" value="${fileCnt}" scope="request" />
						<c:set var="param_attachId" value="${entity['ATTACH_ID']}" scope="request" />
						<c:set var="downloadYn" value="Y" scope="request" />
						<c:set var="orderEdit" value="Y" scope="request" />
						<jsp:include page="/sys/jsp/component/fileAttachForm.jsp" />
						<input type="hidden" name="attach_id" value="${entity['ATTACH_ID']}" />
					</td>
				</tr>
			</c:if>
			<%-- ============================ 링크주소 ============================ --%>
			<c:if test="${tableDef.linkType eq '2' or tableDef.linkType eq '2'}">
				<tr>
					<th scope="row"><label for="HP_URL">링크주소</label></th>
					<td>
						<input type="text" name="HP_URL" maxlength="100" class="input300" value="${entity['COL_HP_URL']}" data-validator="on" data-fieldName="홈페이지" />
						<span class="tip">※ http://부터 기입해주세요.</span>
					</td>
				</tr>
			</c:if>
			<%-- ============================ 위도/경도 ============================ --%>
			<c:if test="${tableDef.useMap == 'Y'}">
				<tr>
					<th scope="row"><label for="LAT">위도</label></th>
					<td>
						<input type="text" name="LAT" maxlength="20" class="input100" value="${entity['COL_LAT']}" data-validator="on" data-inputType="float" data-digit="3" data-fieldName="위도" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="LNG">경도</label></th>
					<td>
						<input type="text" name="LNG" maxlength="20" class="input100" value="${entity['COL_LNG']}" data-validator="on" data-inputType="float" data-digit="3" data-fieldName="경도" />
					</td>
				</tr>
			</c:if>
			<%-- ============================ 사용자 공개 여부 ============================ --%>
			<c:if test="${tableDef.useSecret == 'Y'}">
				<tr>
					<th scope="row"><span class="red">*</span><label for="ISSECRET">비공개</label></th>
					<td>
						<select name="ISSECRET" data-validator="on" data-required="y" data-fieldName="비공개">
							<option value="">선택</option>
							<option value="N" <c:if test="${entity['IS_SECRET'] eq 'N' }">selected="selected"</c:if>>공개</option>
							<option value="Y" <c:if test="${entity['IS_SECRET'] eq 'Y' }">selected="selected"</c:if>>비공개</option>
						</select>
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>

	<div class="btn_boxR">
		<input type="submit" id="save" class="btn_cyan" value="저장"/>
		<a href="/sys/dataMng/${tableDef.urlName}/list.do?mId=${menuVO.mId}" onclick="document.getElementById('listForm').submit(); return false;" class="btn_dblue">취소</a>
	</div>

</form>
