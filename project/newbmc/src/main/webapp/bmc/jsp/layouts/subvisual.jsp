<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:set var="div_class" value="" />
<c:if test="${menuVO.mId >= '0800000000'}">
	<c:set var="div_class" value="sub7" />
</c:if>
<c:if test="${menuVO.mId >= '0700000000' and menuVO.mId < '0800000000'}">
	<c:set var="div_class" value="sub1" />
</c:if>
<c:if test="${menuVO.mId >= '0600000000' and menuVO.mId < '0700000000'}">
	<c:set var="div_class" value="sub7" />
</c:if>
<c:if test="${menuVO.mId >= '0500000000' and menuVO.mId < '0600000000'}">
	<c:set var="div_class" value="sub6" />
</c:if>
<c:if test="${menuVO.mId >= '0400000000' and menuVO.mId < '0500000000'}">
	<c:set var="div_class" value="sub5" />
</c:if>
<c:if test="${menuVO.mId >= '0300000000' and menuVO.mId < '0400000000'}">
	<c:set var="div_class" value="sub4" />
</c:if>
<c:if test="${menuVO.mId >= '0200000000' and menuVO.mId < '0300000000'}">
	<c:set var="div_class" value="sub3" />
</c:if>
<c:if test="${menuVO.mId >= '0100000000' and menuVO.mId < '0200000000'}">
	<c:set var="div_class" value="sub2" />
</c:if>

<div class="subPageVisual ${div_class}">
	<h2 class="tit">
		<c:if test="${empty activeMenu.spotTitle and menuVO.mId > '0200000000' and menuVO.mId < '0300000000'}">
			<strong>
				사업안내
			</strong>
		</c:if>
		<c:if test="${empty activeMenu.spotTitle and menuVO.mId >= '0804010100' and menuVO.mId <= '0804010400'}">
			<strong>
				개인정보처리방침
			</strong>
		</c:if>
		<c:if test="${empty activeMenu.spotTitle and menuVO.mId == '0803000000'}">
			<strong>
				전체 메뉴
			</strong>
		</c:if>
		<c:if test="${not empty activeMenu.spotTitle}">
			<strong>
			${activeMenu.spotTitle}
			</strong>
		</c:if>
	</h2>
</div>