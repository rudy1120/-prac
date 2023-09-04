<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 분양 사업 등록/수정 화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.07		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.07
 */
%>
<script type="text/javascript">
$(document).ready(function () {
	
	//선택 공급용도 가져오기
	var areaVal = $("#idx").val();
	
	$.ajax({
		url : "/bmc/apply/getUses.do",
		method : 'POST',
		data : {idx : areaVal},
		success : function(data){
			var obj = JSON.parse(data);

			for( var i = 0 ; i < obj.length; i++ ){
				$("input[name=usesCk][value='"+obj[i].usescode+"']").attr("checked",true);
			}
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	

	
});
</script>
<style>
.usesLabel{display: inline-block;width: 200px;}
</style>
<h2>사업정보</h2>
<c:set var="isNew"   value="${saleVO.idx == null or saleVO.idx eq 0}"/>
<c:set var="postUrl" value="${isNew ? 'writeProc' : 'modifyProc'}"/>
<form:form commandName="saleVO" id="cancel" name="cancel"  methodParam="post" action="/sys/buy/list.do?mId=${menuVO.mId}">
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
</form:form>

<form:form commandName="saleVO" id="modify" name="modify" methodParam="post" action="/sys/buy/sell/${postUrl}.do?mId=${menuVO.mId}"  onsubmit="return validator();" htmlEscape="false"  enctype="multipart/form-data">
	<form:hidden path="page" />
	<form:hidden path="idx" value="${saleVO.idx}"/>
	<table class="tableSt_write">
		<caption>사업정보을 등록/수정하는 표입니다.</caption>
		<colgroup>
			<col span="1" style="width:15%"/>
			<col span="1" style="width:15%"/>
			<col span="1" style="width:15%"/>
			<col span="1" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="name"><span class="red">*</span> 사업명 </label></th>
				<td colspan="3">
					<form:input path="name" id="name" cssClass="input200"  maxlength="200" data-validator="on" data-required="y" data-fieldName="사업명" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="state"><span class="red">*</span> 분양상태</label></th>
				<td colspan="3">
					<form:radiobutton path="state" id="stat1" value="1" checked="checked"/><label for="stat1">분양중</label>
					<form:radiobutton path="state" id="stat4" value="4" /><label for="stat4">분양완료</label>
					<form:radiobutton path="state" id="stat9" value="9" /><label for="stat9">분양계획</label>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row"><label for="usesCk"><span class="red">*</span> 공급용도</label></th>
				<td colspan="3">
					<c:forEach var="uses" items="${resultList}">
						<label class="usesLabel" for="${uses.codename}">
							<form:checkbox path="usesCk" id="${uses.codename}" value="${uses.codename}" data-validator="on" data-required="y" data-fieldName="공급용도" />${uses.codename}
						</label>
					</c:forEach>
				</td>
			</tr> --%>
			<tr>
				<th scope="row"><label for="link"> 사업관련링크</label></th>
				<td colspan="3">
					<form:input path="link"  cssClass="input200" maxlength="200" data-validator="on" data-required="y" data-fieldName="사업관련링크" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="mapInfo"> 위치도</label></th>
				<td colspan="3"  data-fileName="${saleVO.mapInfo}">
					<input type="hidden" name="mapInfo" value="${saleVO.mapInfo}" />
					<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${saleVO.mapInfo}" />
						<c:param name="param_showThumbnail" value="${true}" />
						<c:param name="param_thumbWidth" value="200" />
						<c:param name="param_thumbHeight" value="200" />
						<c:param name="downloadYn" value="Y"/>
						<c:param name="param_showComment" value="${true}" />
						<c:param name="fileCnName" value="locationFileCn" />
					</c:import>
				
					<input type="file" id="location_file" name="location_file" data-validator="on" data-fieldName="위치도 첨부파일" class="input300 mB10" value="" />
					<div id="exTxt"><label for="locationFileCn">설명글</label>: <input id="locationFileCn" type="text" name="locationFileCn"   class="input400" /></div>
					<div class="pT5" id="exTxt2">
						<p>※ 이미지 파일 설명글 작성 방법 (시각장애인의 웹 접근성 확보)</p>
						<p>- 사진 : 사진의 내용을 자세히 묘사 (예시. 청사 정문에 활짝 핀 매화 꽃 사진입니다.)</p>
						<p>※ 파일은 최대 1개까지 첨부하실 수 있습니다. 첨부 가능한 하나의 파일 사이즈는 최대 100Mbyte 입니다.</p>
					</div>

				</td>
			</tr>
			
			
			
		</tbody>
	</table>
	<div class="btn_boxR">
		<input type="submit" id="submitBtn"  class="btn_cyan" value="저장" />
		<a href="#" id="backBtn" class="btn_dblue" onclick="document.cancel.submit(); return false;">취소</a>
	</div>
</form:form>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>
<script>
	jQuery(document).ready(function(){
		$('.updateFileList').parent('td').find('[type=file]').hide();
		$('.updateFileList').parent('td').find($("#exTxt")).remove();
		$('.updateFileList').parent('td').find($("#exTxt2")).remove();
		//$('.updateFileList').parent('td').find($(".pT5")).hide();
		$('.btn_dele').click(function() {
			//history.go(0);
			 defaultTime = setInterval(function() {
				$('.updateFileList>ul:not(:has(li))').parents('td').find('[type=file]').show();
				clearInterval(defaultTime);
			 }, 100);
		});
	});
</script>
