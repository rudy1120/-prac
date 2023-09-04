<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<!-- <style>
	.bod_write dl dd{line-height:2;}
</style>
 -->
<div id="contents">
	<div class="content">
		<div class="gap20"></div>
		
		<div class="bod_write">
			<dl>
				<dt>문서제목</dt>
				<dd>${element.docTitle}</dd>
			</dl>
			<dl>
				<dt>담당자</dt>
				<dd>${element.docMgr}</dd>
			</dl>
			<dl>
				<dt>등록일자</dt>
				<dd>${element.regDate}</dd>
			</dl>
			<dl>
				<dt>생산년도</dt>
				<dd>${element.prodYear}</dd>
			</dl>
			<dl>
				<dt>문서번호</dt>
				<dd>${element.docNo}</dd>
			</dl>
			<dl>
				<dt>보존기간</dt>
				<dd>${element.docPeriod}</dd>
			</dl>
			<dl>
				<dt>공개여부</dt>
				<dd>${element.openYn}</dd>
			</dl>
		</div>
		
		<div class="gap"></div>

		<div class="btnall_wrap">
			<div class="btn_wrap">
				<!-- <input type="button" onclick="window.open('https://www.open.go.kr/')" value="정보공개 신청 바로가기"/> -->
				<input type="button" onclick="location.href='/bmc/opendoc/List.do?mId=${menuVO.mId}'" value="목록"/>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="/common/js/commonProcess.js"></script>