<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	$(document).ready(function() {

		if($("#smsyn").val()=='Y'){
			$('input:radio[id=smsY]').attr("checked",true);
			$(".selTr").attr('style', "display:table-row;");
		}else if($("#smsyn").val()=='N'){
			$('input:radio[id=smsN]').attr("checked",true);
			$(".selTr").attr('style', "display:none;");
		}
		//문자전송 선택시 
		$("input:radio[name=smsYN]").click(function() {

			if (this.value == 'Y') {
				$(".selTr").attr('style', "display:table-row;");
			} else if (this.value == 'N') {
				$(".selTr").attr('style', "display:none;");
				
				//smsmsg 초기화
				/* $("#smsSubject").val('');
				$("#smsMsg").val('');
				//smsoptions 체크 초기화
				$('input:checkbox[name="smsOptions"]').prop("checked",false); */
			}
		});
		
		//선택옵션 
		var optionVal = $("#options").val();
		var arr = optionVal.split(',');

		for( var i = 0 ; i < arr.length; i++ ){
			$('input:checkbox[id="'+arr[i]+'"]').prop("checked", true); //checked 처리
		}
	});
</script>

<%-- ============================== 문자발송 서비스 ============================== --%>
<c:choose>
	<c:when test="${bbsConfigVO.ptIdx eq '768' || bbsConfigVO.ptIdx eq '769'}">
		<tr>
			<th scope="row"><label for="smsService">문자서비스</label></th>
			<td>
				<p class="red mB10">※ 문자서비스를 이용하실 경우 발송을 선택해주세요.</p>
				<p class="tip mB10 mT_im0">※ 게시기간을 설정하신 경우, 설정한 게시기간에 발송됩니다. 게시기간 미설정시, 글등록과 함께 전송됩니다.</p>
					<input type="hidden" id="smsyn" value="${bbsMngVO.smsYN}"/>	
					<label for="smsY"><input type="radio" id="smsY" name="smsYN" value="Y" />발송</label> <label for="smsN"><input type="radio" id="smsN" name="smsYN" value="N" checked />미발송</label>
			</td>
		</tr>
		<tr class="selTr" style="display: none;">
			<th scope="row"><span class="red">*</span><label for="smsSubject">문자메시지 제목</label></th>
			<td>
				<input type="text" name="smsSubject" id="smsSubject" maxlength="40" value="${bbsMngVO.smsSubject}"/>
			</td>
		</tr>
		<tr class="selTr" style="display: none;">
			<th scope="row"><span class="red">*</span><label for="smsMsg">문자메시지 내용</label></th>
			<td>
				<!-- 			<p class="red mB10">※ 문자서비스를 보내실 사업지구/공급용도 유형을 선택해주세요. 해당 유형 문자알림신청자들에게 문자를 발송합니다.</p> -->
				<!-- <textarea rows="8" cols="50" name="smsMsg" id="smsMsg" class="selTr" style="display:none;">공사홈페이지 및 온비드에서 확인하시기 바랍니다.&#10;부산도시공사 홈페이지(http://www.bmc.busan.kr)&#10;온비드홈페이지(http://www.onbid.co.kr)</textarea> -->
				<textarea rows="8" cols="50" name="smsMsg" id="smsMsg"  maxlength="2000" rows="8" cols="50">${bbsMngVO.smsMsg}</textarea> <!-- <input type="text" name="smsMsg" id="smsMsg"  value="공사홈페이지 및 온비드에서 확인하시기 바랍니다.&#10;부산도시공사 홈페이지(http://www.bmc.busan.kr)&#10;온비드홈페이지(http://www.onbid.co.kr)"/> -->
			</td>
		</tr>
		<tr class="selTr" style="display: none;">
			<th scope="row"><span class="red">*</span><label>문자 발신번호</label></th>
			<td><input type="hidden" name="trCallback" id="trCallback" >
				<input type="text" style="width:80px;" id="tel1" value="${fn:substring(bbsMngVO.trCallback,0,3)}" /> <span>-</span> 
				<input type="text" style="width:80px;" id="tel2" value="${fn:substring(bbsMngVO.trCallback,3,6)}" /> <span>-</span>
				<input type="text" style="width:120px;" id="tel3" value="${fn:substring(bbsMngVO.trCallback,6,10)}"/>
			</td>
		</tr>
	</c:when>
</c:choose>
<%-- ==== 분양공고 ==== --%>
<c:if test="${bbsConfigVO.ptIdx eq '768' or bbsConfigVO.ptIdx eq '769'}">
	 <input type="hidden" name="smsGbn" value="A" />
		<tr class="selTr" style="display: none;">
			<th scope="row"><span class="red">*</span><label for="smsService">공급용도</label></th>
			<td>
				<p class="red mB10">※ 문자서비스를 보내실 공급용도 유형을 선택해주세요. 해당 유형
					문자알림신청자들에게 문자를 발송합니다.</p> 
					<input type="hidden" id="options" value="${bbsMngVO.smsOptions}"/>
					<c:forEach var="uses" items="${usesList}">
					<!-- <option value="${uses.idx}">${uses.codename}</option> -->
					<label for="${uses.idx}"> <input type="checkbox"
						name="smsOptions" id="${uses.idx}" value="${uses.idx}" />${uses.codename}
					</label>
				</c:forEach>
			</td>
		</tr>

<%-- ==== 임대공고 ==== --%>

		<!-- <input type="hidden" name="smsGbn" value="B" /> -->
		<tr class="selTr" style="display: none;">
			<th scope="row"><span class="red">*</span><label for="smsService">주택유형</label></th>
			<td>
				<p class="red mB10">※ 문자서비스를 보내실 주택유형 유형을 선택해주세요. 해당 유형
					문자알림신청자들에게 문자를 발송합니다.</p> 
					<input type="hidden" id="options" value="${bbsMngVO.smsOptions}"/>
					<c:forEach var="house" items="${housingList}">
					<!-- <option value="${uses.idx}">${uses.codename}</option> -->
					<label for="${house.idx}"> <input type="checkbox"
						name="smsOptions" id="${house.idx}" value="${house.idx}" />${house.codename}
					</label>
				</c:forEach>
			</td>
		</tr>
</c:if>