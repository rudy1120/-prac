<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
/**
 * 계약현황 상세페이지
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.02		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.10.02
 */
%>
<style>
	.bod_write dl dd{line-height:2;}
</style>

<div id="contents"><div class="content">
<div class="gap20"></div>
<p class="depth2-title">${element.cntrNm}</p>
<div class="gap20"></div>
<div class="bod_write">
	<dl>
		<dt>계약번호</dt>
		<dd>
			${element.cntrNo}
		</dd>
	</dl>
	<dl>
		<dt>계약구분</dt>
		<dd>
			<c:if test="${element.cntrGb == '1'}">공사</c:if>
			<c:if test="${element.cntrGb == '2'}">용역</c:if>
			<c:if test="${element.cntrGb == '3'}">물품</c:if>
		</dd>
	</dl>
	<dl>
		<dt>진행구분</dt>
		<dd>
			${element.prgsGb}		
		</dd>
	</dl>
	<dl>
		<dt>담당부서</dt>
		<dd>
			${element.dept}	
		</dd>
	</dl>
	<dl>
		<dt>계약방법</dt>
		<dd>
			${element.cntrWay}
		</dd>
	</dl>
	<dl>
		<dt>예정금액</dt>
		<dd>
			<fmt:formatNumber value="${element.estimatedAmt}" pattern="#,###"/>원
		</dd>
	</dl>
	<dl>
		<dt>최초계약금액</dt>
		<dd>
			<fmt:formatNumber value="${element.cntrAmt}" pattern="#,###"/>원
		</dd>
	</dl>
	<dl>
		<dt>최종계약금액</dt>
		<dd>
			<fmt:formatNumber value="${element.finalcntrAmt}" pattern="#,###"/>원
		</dd>
	</dl>
	<dl>
		<dt>낙찰율</dt>
		<dd>
			${element.succbidRate}%
		</dd>
	</dl>
	<dl>
		<dt>계약일</dt>
		<dd>
			${element.cntrYrmd}
		</dd>
	</dl>
	<dl>
		<dt>착공일</dt>
		<dd>
			${element.beginYrmd}
		</dd>
	</dl>
	<dl>
		<dt>준공일</dt>
		<dd>
			${element.finishYrmd}
		</dd>
	</dl>
	<dl>
		<dt>업체명</dt>
		<dd>
			${element.compNm}
		</dd>
	</dl>
	<dl>
		<dt>계약사유</dt>
		<dd>
			${element.cntrReason}
		</dd>
	</dl>
	<dl>
		<dt>계약변경</dt>
		<dd>
			<div class="gap"></div>
			<div class="orgTable_wrap jobTable_wrap">
				<table class="orgTable jobTable">
				<caption>변경차수, 변경일, 증감액, 변경금액, 변경사유로 이루어진 계약변경표</caption>
				<thead>
					<tr>
						<th width="20%">변경차수</th>
						<th width="20%">변경일</th>
						<th width="20%">증감액</th>
						<th width="20%">변경금액</th>
						<th width="20%">변경사유</th>
					</tr>
				</thead>
				<c:forEach var="e" items="${changeList}">
					<tr>
						<td>${e.chgNo}</td>
						<td>${e.plchangeYrmd}</td>
						<td><fmt:formatNumber value="${e.plchangeAmt}" pattern="#,###"/>원</td>
						<td><fmt:formatNumber value="${e.contractAmt}" pattern="#,###"/>원</td>
						<td>${e.chgResn}</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<c:if test="${empty changeList}">
				<div class="gap"></div>	
				<div class="no_data">계약변경 자료가 없습니다.</div>
			</c:if>
		</dd>
	</dl>
	<dl>
		<dt>지급총계</dt>
		<dd>
			<fmt:formatNumber value="${element.totalAmt}" pattern="#,###"/>원
		</dd>
	</dl>
	<dl>
		<dt>선금누계액</dt>
		<dd>
			<fmt:formatNumber value="${element.prepayAmt}" pattern="#,###"/>원
		</dd>
	</dl>
	<dl>
		<dt>선금지급일</dt>
		<dd>
			${element.prepayYrmd}
		</dd>
	</dl>
	<dl>
		<dt>기성금누계액</dt>
		<dd>
			<fmt:formatNumber value="${element.readymadeAmt}" pattern="#,###"/>원
		</dd>
	</dl>
	<dl>
		<dt>기성금지급일</dt>
		<dd>
			<div class="gap"></div>
			<div class="orgTable_wrap jobTable_wrap">
				<table class="orgTable jobTable">
				<caption>차수, 지급일로 이루어진 기성금지급일표</caption>
				<thead>
					<tr>
						<th width="20%">차수</th>
						<th width="*">지급일</th>
					</tr>
				</thead>
				<c:forEach var="e" items="${inspectList}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${e.readymadeYrmd}</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<c:if test="${empty inspectList}">
				<div class="gap"></div>	
				<div class="no_data">기성금지급일 자료가 없습니다.</div>
			</c:if>
		</dd>
	</dl>
	<dl>
		<dt>준공금누계액</dt>
		<dd>
			<fmt:formatNumber value="${element.completionAmt}" pattern="#,###"/>원
		</dd>
	</dl>
	<dl>
		<dt>준공금지급일</dt>
		<dd>
			${element.completionYrmd}
		</dd>
	</dl>
	<dl>
		<dt>검사현황</dt>
		<dd>
			<div class="gap"></div>
			<div class="orgTable_wrap jobTable_wrap">
				<table class="orgTable jobTable">
				<caption>검사일, 검사금액, 검사원 및 입회원으로 이루어진 검사현황표</caption>
				<thead>
					<tr>
						<th width="20%">검사일</th>
						<th width="20%">검사금액</th>
						<th width="*">검사원 및 입회원</th>
					</tr>
				</thead>
				<c:forEach var="e" items="${inspectList}" varStatus="status">
					<tr>
						<td>${e.inspectYrmd}</td>
						<td><fmt:formatNumber value="${e.inspectAmt}" pattern="#,###"/>원</td>
						<td>${e.inspectStff}</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<c:if test="${empty inspectList}">
				<div class="gap"></div>	
				<div class="no_data">검사현황 자료가 없습니다.</div>
			</c:if>
		</dd>
	</dl>
	<dl>
		<dt>감독현황</dt>
		<dd>
			${element.supervision}
		</dd>
	</dl>
	<dl>
		<dt>감리현황</dt>
		<dd>
			${element.extSupervision}
		</dd>
	</dl>
	<dl>
		<dt>하도급현황</dt>
		<dd>
			<div class="gap"></div>
			<div class="orgTable_wrap jobTable_wrap">
				<table class="orgTable jobTable">
				<caption>공종, 업체명, 계약일자, 하도급액, 하도급율로 이루어진 하도급현황표</caption>
				<thead>
					<tr>
						<th width="20%">공종</th>
						<th width="20%">업체명</th>
						<th width="20%">계약일자</th>
						<th width="20%">하도급액</th>
						<th width="20%">하도급율</th>
					</tr>
				</thead>
				<c:forEach var="e" items="${subcontractList}">
					<tr>
						<td>${e.workKind}</td>
						<td>${e.subcompNm}</td>
						<td>${e.cntrYrmd}</td>
						<td><fmt:formatNumber value="${e.hcntrAmt}" pattern="#,###"/>원</td>
						<td>${e.contractRate}%</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<c:if test="${empty subcontractList}">
				<div class="gap"></div>	
				<div class="no_data">하도급 자료가 없습니다.</div>
			</c:if>
		</dd>
	</dl>
	<dl>
		<dt>수의계약사유(낙찰자결정)</dt>
		<dd>
			${element.succBidder}
		</dd>
	</dl>
	<dl>
		<dt>수의계약사유(관련법령)</dt>
		<dd>
			${element.rltdStatute}
		</dd>
	</dl>
	<dl>
		<dt>사업장소</dt>
		<dd>
			${element.businessSite}
		</dd>
	</dl>
</div>
	<div class="btnall_wrap">
		<div class="btn_wrap">
			
			<input type="button" class="btn-blue" onclick="location.href='/bmc/contract/list.do?mId=${menuVO.mId}'" value="목록"/>
		</div>
	</div>
</div></div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>