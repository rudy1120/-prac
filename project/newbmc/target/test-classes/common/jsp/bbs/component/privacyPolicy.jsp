<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<c:set var="useOptCols" value="${not empty bbsConfigVO.ptSaveOptCols}"/>
<c:set var="crlf" value="
"/>
<div class="bod_guide">
	<span class="icon icon02"></span>
	<div class="cont minGuide">
		<p class="bold blue">개인 정보 수집 및 이용 안내</p>
		<ul class="list_ul_h4">
			<li>
				개인 정보의 수집&middot;이용 목적 :
					${empty bbsConfigVO.ptSavePurpose ? '민원 접수 처리 및 사후 관리 서비스 제공' : fn:escapeXml(bbsConfigVO.ptSavePurpose)}
			</li>
			<li>개인 정보 수집 대상
				<ul>
					<li>
						필수 항목 :
							<c:set var="colNames" value="이름"/>
							<c:forEach var="col" items="${bbsConfigVO.ptSaveColList}">
								<c:forEach var="c" items="${colTypeList}"><c:if test="${c.code == col}"><c:set var="colNames" value="${colNames}, ${c.name}"/></c:if></c:forEach>
							</c:forEach>
							${colNames}
					</li>
					<c:if test="${useOptCols}">
						<li>
							선택 항목 :
								<c:set var="optColNames" value=""/>
								<c:forEach var="col" items="${bbsConfigVO.ptSaveOptColList}">
									<c:forEach var="c" items="${colTypeList}">
										<c:if test="${c.code == col}">
											<c:if test="${not empty optColNames}"><c:set var="optColNames" value="${optColNames}, ${c.name}"/></c:if>
											<c:if test="${empty optColNames}"><c:set var="optColNames" value="${c.name}"/></c:if>
										</c:if>
									</c:forEach>
								</c:forEach>
								${optColNames}
						</li>
					</c:if>
					<li>자동 수집 항목 : IP(Internet Protocol) 주소, 이용 내역 기록</li>
				</ul>
			</li>
			<li>
				개인 정보의 보유 및 이용 기간 :
				<c:if test="${bbsConfigVO.ptSavePeriod < 12}">${bbsConfigVO.ptSavePeriod}개월</c:if>
				<c:if test="${bbsConfigVO.ptSavePeriod > 11}"><fmt:formatNumber value="${bbsConfigVO.ptSavePeriod / 12}" pattern="#"/>년</c:if>
			</li>
			<c:if test="${not empty bbsConfigVO.ptSaveGuide}">
				<c:set var="lines" value="${fn:split(bbsConfigVO.ptSaveGuide, crlf)}"/>
				<c:forEach var="line" items="${lines}">
					<li>${line}</li>
				</c:forEach>
			</c:if>
			<li>동의를 거부할 권리가 있으나 동의하지 않을 시 서비스 이용에 제한이 있습니다.</li>
		</ul>
		<p class="minAgree btnboxC">
			<form:checkbox id="privacyYn" path="privacyYn" value="Y" label="필수·자동 수집 항목 이용 동의"/>
			<c:if test="${useOptCols}">
				<br/><form:checkbox id="optPrivacyYn" path="optPrivacyYn" value="Y" label="선택 항목 이용 동의"/>
			</c:if>
		</p>
	</div>
</div>
