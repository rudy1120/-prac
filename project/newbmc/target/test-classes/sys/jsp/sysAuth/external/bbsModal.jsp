<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 선택창
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.11.06		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.11.06
	 */
%>
<style>
	.modal {
		display: none;
		position: fixed;
		z-index: 1;
		padding-top: 100px;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		overflow: auto;
		background-color: rgb(0, 0, 0);
		background-color: rgba(0, 0, 0, 0.4);
	}

	.modal-content {
		background-color: #fefefe;
		margin: auto;
		padding: 20px;
		border: 1px solid #888;
		width: 50%;
		min-height:712px;
		box-sizing:border-box;
		position:relative;
	}
	.modal-content .btn_boxR {
		position:absolute;
		right:0;
		bottom:10px;
	}
</style>
<form id="bbs_searcher" name="bbs_searcher" action="/sys/bbs/list/json.do?mId=${menuVO.mId}" onsubmit="modal.init(); return false;" method="POST">
	<input type="hidden" name="mpage" value="1"/>
	<div class="modal" style="display: none;">
		<div class="modal-content">

			<p class="info">권한을 부여하려는 게시판을 선택하세요.</p>
			<p class="pL20 mL20 mB10 red">선택한 정보는 페이지를 변경해도 유지되지만, [검색] 처리시에는 초기화됩니다.</p>

			<div class="search_box">
				<div class="fR">
					<label for="title" class="hidden">게시판명</label>
					<input type="text" id="title" name="title" placeholder="게시판명"/>
					<input type="submit" class="btn_white" value="검색"/>
				</div>
				<div class="cleB"></div>
			</div>

			<table id="bbs_container" class="tableSt_list row_over">
				<caption></caption>
				<thead>
					<th scope="col" class="w6">선택</th>
					<th scope="col">게시판명</th>
				</thead>
				<tbody></tbody>
			</table>
			<div id="bbs_paging" class="box_page"></div>

			<div class="btn_boxR mT10">
				<a href="#" onclick="modal.save(); return false;" class="btn_cyan">저장</a>
				<a href="#" onclick="modal.close(); return false;" class="btn_white">닫기</a>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="/sys/js/sysAuth/external.js"></script>
