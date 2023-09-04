<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 정책연구용역 보고서 사용자 상세화면
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.28		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.28
 */
%>
<!-- <style>
	.developerInfo {display:inline-block;width:100px;}
	.btntolist{margin:30px 0; position:absolute; bottom:0; right:0;}
	.btn_wrap{width:400px}
</style>
 -->
<style>
	.bod_write dl dd {width:35%; line-height:2;}
</style>
<div id="contents"><div class="content">
<form:form commandName="prismVO" name="list" id="list" action="/bmc/prism/list.do?ptIdx=${element.idx}&mId=${menuVO.mId}" method="POST">
	<form:hidden path="page" />
	<form:hidden path="searchTxt" />
	<form:hidden path="searchType" />
</form:form>
<div class="gap20"></div>
<div class="bod_wrap">
	<fieldset>
		<p class="taR pB5">
		<div class="gap"></div>
		<h2>과제정보</h2>
		<div class="gap"></div>
		<div class="bod_write">
			<div>
				<dl>
					<dt><label for="projectNm">과제명</label></dt>
					<dd>
						${element.projectNm}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="orgnNm">기관명</label></dt>
					<dd>
						${element.orgnNm}
					</dd>
					<dt><label for="depNm">담당부서</label></dt>
					<dd>
						${element.depNm}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="phoneNb">전화번호</label></dt>
					<dd>
						${element.phoneNb}
					</dd>
					<dt>연구기간</dt>
					<dd>
						${element.rSdate} ~
						${element.rEdate}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="rField">연구분야</label></dt>
					<dd>
						${element.rField}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="summary">개요</label></dt>
					<dd>
						${element.summary}
					</dd>
				</dl>
			</div>
		</div>
		<div class="gap"></div>
		<h2>계약정보</h2>
		<div class="gap"></div>
		<div class="bod_write">
			<div>
				<dl>
					<dt><label for="executeNm">수행기관</label></dt>
					<dd>
						${element.executeNm}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="researcher">수행연구원</label></dt>
					<dd>
						${element.researcher}
					</dd>
					<dt><label for="contDt">계약일자</label></dt>
					<dd>
						${element.contDt}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="contMethod">계약방식</label></dt>
					<dd>
						${element.contMethod}
					</dd>
					<dt><label for="contPrice">계약금액</label></dt>
					<dd>
						${element.contPrice}
					</dd>
				</dl>
			</div>
		</div>
		<div class="gap"></div>
		<h2>연구보고서</h2>
		<div class="gap"></div>
		<div class="bod_write">
			<div>
				<dl>
					<dt><label for="title">제목</label></dt>
					<dd>
						${element.title}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="eTitle">영문제목</label></dt>
					<dd>
						${element.eTitle}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="subtitle">부제목</label></dt>
					<dd>
						${element.subtitle}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt>결과 보고서</dt>
					<dd>
						<c:set var="fileExts" value="${element.resReport}" scope="request"/>
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_showThumbnail" value="${true}" />
							<c:param name="updateFlag" value="N"/>
							<c:param name="param_atchFileId" value="${fileExts}"/>
						</c:import>
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="topic">주제어</label></dt>
					<dd>
						${element.topic}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="prdtDt">제작일자</label></dt>
					<dd>
						${element.prdtDt}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="contributor">공헌자</label></dt>
					<dd>
						${element.contributor}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="issYear">발행년도</label></dt>
					<dd>
						${element.issYear}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="subDt">제출일</label></dt>
					<dd>
						${element.subDt}
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt><label for="contents">목차</label></dt>
					<dd>
						${element.contents}
					</dd>
				</dl>
			</div>
		</div>
		<div class="gap"></div>
		<h2>연구결과 평가 및 활용보고서</h2>
		<div class="gap"></div>
		<div class="bod_write">
			<div>
				<dl>
					<dt>평가결과서</dt>
					<dd>
						<c:set var="fileExts" value="${element.evalResult}" scope="request"/>
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_showThumbnail" value="${true}" />
							<c:param name="updateFlag" value="N"/>
							<c:param name="param_atchFileId" value="${fileExts}"/>
						</c:import>
					</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt>활용결과 보고서</dt>
					<dd>
						<c:set var="fileExts" value="${element.applReport}" scope="request"/>
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_showThumbnail" value="${true}" />
							<c:param name="updateFlag" value="N"/>
							<c:param name="param_atchFileId" value="${fileExts}"/>
						</c:import>
					</dd>
				</dl>
			</div>
		</div>
	</fieldset>

</div>
<div class="btnall_wrap">
	<div class="btn_wrap">
		<a href="#" onclick="document.getElementById('list').submit(); return false;" title="${activeMenu.menuName} 게시글 목록 페이지로 이동">목록</a>
	</div>
</div>
</div></div>
<script type="text/javascript" src="/common/js/commonProcess.js"></script>

