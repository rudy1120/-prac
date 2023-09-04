<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 통합검색 목록
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.10.31		박선민		    최초 생성 및 코드 수정
 *
 *
 * @author 박선민
 * @since 2019.10.31
 */
%>
<script>
$(document).ready(function(){

	var categorVal = $('#srchFrm2 [name="category"]').val();
	$('#sch_'+categorVal).addClass('active');
	
	//상단 통합검색창 숨기기
	$("#srchFrm").remove();
	
	$(".gap20").remove();
});

//카테고리 변경
function changeSearchCategory(search_category) {	
	$('#srchFrm2 [name="category"]').val(search_category);
	$('#sub_category').val("");
	$('#pageNum').val("1");
	$('#srchFrm2').submit();
}
function goPage2(page){
	//$('#srchFrm2 [name="category"]').val(search_category);
	$('#pageNum').val(page);
	$('#srchFrm2').submit();
}
//인기검색어 단어 클릭 검색화면이동
function goSearch(keyword){
	$('#srchFrm2 [name="kwd"]').val(keyword);
	$('#srchFrm2').submit();
}
</script>
<style>
	#kwd {float: left;height: 35px;border: 1px solid #00879e; padding: 0 35px 0 20px;}
    #schFormDiv{width:100%; height:100%; display:flex; justify-content:center; align-items:center; margin-top:30px;}
    #schBtn{float: left;width: 35px;background: url(../images/ico_search.png) no-repeat center center;border: 0;text-indent: -9999px;top: 0;right: 0;}
</style>
<div id="contents">
	<div class="content contmargin">
		<form id="srchFrm2" name="srchFrm2" action="/bmc/search/totalSearch.do" method="get">
			<div class="searchWrap">
				<fieldset class="searchField">
					<legend>통합검색</legend>
					<label for="txtSearch01" class="hidden">검색어 입력</label>
					<div class="inpSearchWrap" id="schFormDiv">
						<input type="text" id="kwd" name="kwd" title="검색어 입력" value="${params.kwd}"/>
						<input type="hidden" name="mId" value="0805000000"/>
						<input type="hidden" name="preKwds" id="preKwds"/>
						<input type="hidden" name="category" id="category" value="${params.category}"/>
						<input type="hidden" name="sub_category" id="sub_category"/>
						<input type="hidden" name="pageNum" id="pageNum" value="${params.pageNum}"/>
						<input type="hidden" name="orderBy" id="orderBy"/>
						<input type="hidden" name="index" id="index"/>
						<input type="hidden" name="gpStartDate" id="gpStartDate"/>
						<input type="hidden" name="gpEndDate" id="gpEndDate"/>
						 <button type="submit" id="schBtn"class="btnSearch">검색</button>
					</div>
				</fieldset>
				<!-- <div class="checkWrap">
					 <input type="checkbox" id="chkReSearch" name="reSearch">
					<label for="chkReSearch">결과내 재검색</label>
				</div> -->
			</div>
		</form>		
		<div class="searchWrap">
			<div class="searchcont1">
				<div class="section connecKeyword">
					<div class="sectionIn">
						<div class="result">
							<strong class="keyword">${params.kwd}</strong>에 대한 검색결과는 <strong class="point">총 ${total}건</strong>입니다.
						</div>
					</div>
				</div>
				<c:if test="${not empty menuList}">
					<div class="section menu">
						<div class="sectionIn">
							<div class="titGroup">
									<h2 class="tit">메뉴</h2>
									<span class="resultNum">(검색결과 ${menuTotal}건)</span>
							</div>
							<ul class="menuList">
								<c:forEach var="menuList" items="${menuList}" varStatus="status">
									<li>
										<a href="${menuList.URL}">${menuList.MENU_NAVI}</a>
									</li>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${params.category eq 'TOTAL'}">
							<div class="moreBtnWrap">
								<a href="javascript:changeSearchCategory('menu');"><span class="hidden">메뉴</span>더보기 ></a>
							</div>
						</c:if>
					</div><!-- 메뉴 -->
				</c:if>
				<c:if test="${not empty userinfoList}">
					<div class="section task">
						<div class="sectionIn">
							<div class="titGroup">
								<h2 class="tit">업무/직원</h2><span class="resultNum">(검색결과 ${userinfoTotal}건)</span>
							</div>
							<table class="orgTable searchorgTable">
								<caption>검색결과에 따른 직원의 성명, 부서, 직위, 담당업무, 전화번호</caption>
								<thead>
									<tr>
										<th scope="col" style="width:10%">성명</th>
										<th scope="col" style="width:20%">부서</th>
										<th scope="col" style="width:10%">직위</th>
										<th scope="col" style="width:26%">담당업무</th>
										<th scope="col" style="width:13%">전화번호</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="userList" items="${userinfoList}" varStatus="status">
										<tr>
											<td>${userList.usr_nm}</td>
											<td>${userList.real_use_dep_nm}</td>
											<td>${userList.clss_nm}</td>
											<td>${userList.adi_info7}</td>
											<td>${userList.telno}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${params.category eq 'TOTAL'}">
								<div class="moreBtnWrap">
								<a href="javascript:changeSearchCategory('userinfo');"><span class="hidden">업무/직원</span>더보기 ></a>
							</div>
							</c:if>
						</div>
					</div><!-- 업무/직원 -->
				</c:if>
				<c:if test="${not empty boardList}">
					<div class="section board">
						<div class="sectionIn">
							<div class="titGroup">
								<h2 class="tit">게시판</h2><span class="resultNum">(검색결과 ${boardTotal}건)</span>
							</div>
							<ul class="textList">
								<c:forEach var="boardList" items="${boardList}" varStatus="status">
									<c:choose>
										<c:when test="${fn:startsWith(boardList.pt_title, '오')}">
											<c:set var="pt_site" value="osiria" />
										</c:when>
										<c:when test="${fn:startsWith(boardList.pt_title, '역')}">
											<c:set var="pt_site" value="history" />
										</c:when>
										<c:otherwise>
											<c:set var="pt_site" value="bmc" />
										</c:otherwise>
									</c:choose>
									
									<li>
										<div class="txtWrap">
											<a href="http://www.bmc.busan.kr/${pt_site}/bbs/view.do?bIdx=${boardList.b_idx}&ptIdx=${boardList.pt_idx}&mId=${boardList.mid}">${boardList.b_title}</a>
											<span class="categoryTxt" style="color:#999; font-size:85%;">
												<span class="source">${boardList.b_regdate}</span>
												<span class="menuStep">${boardList.pt_title}</span>
												<div class="cont">${boardList.b_content}</div>
											</span>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${params.category eq 'TOTAL'}">
							<div class="moreBtnWrap">
								<a href="javascript:changeSearchCategory('board');"><span class="hidden">게시판</span>더보기 ></a>
							</div>
						</c:if>
					</div><!-- 게시판 -->
				</c:if>
				<c:if test="${not empty contentsList}">
					<div class="section webContents">
						<div class="sectionIn">
							<div class="titGroup">
								<h2 class="tit">웹콘텐츠</h2><span class="resultNum">(검색결과 ${contentsTotal}건)</span>
							</div>
							<ul class="textList">
								<c:forEach var="contents" items="${contentsList}" varStatus="status">
									<li>
										<div class="txtWrap">
											<a href="${contents.URL}" title="새 창">${contents.TITLE}</a>
											<span class="categoryTxt" style="color:#999; font-size:85%;">
												<span class="source">${contents.c_regdate}</span>
												<span class="menuStep">${contents.MENU_NAVI}</span>
												<span class="cont">${contents.CONTENTS}</span>
											</span>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${params.category eq 'TOTAL'}">
							<div class="moreBtnWrap">
								<a href="javascript:changeSearchCategory('contents');"><span class="hidden">웹콘텐츠</span>더보기 ></a>
							</div>
						</c:if>
					</div><!-- 웹컨텐츠 -->
				</c:if>
				<c:if test="${not empty imgList}">
					<div class="section image">
						<div class="sectionIn">
							<div class="titGroup">
								<h2 class="tit">이미지</h2><span class="resultNum">(검색결과 ${imgTotal}건)</span>
							</div>
							<ul class="imageList">
								<c:forEach var="imgList" items="${imgList}" varStatus="status">
									<c:import url="/cmm/fms/selectImg.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${imgList.attach_id}" />
										<c:param name="param_fileSn" value="${imgList.file_sn}" />
										<c:param name="param_showThumbnail" value="${true}" />
										<c:param name="param_thumbWidth" value="200" />
										<c:param name="param_thumbHeight" value="200" />
										<c:param name="mode" value="imgThumbnail"/>
										<c:param name="downloadYn" value="N"/>
										<c:param name="fileCnName" value="locationFileCn" />
									</c:import>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${params.category eq 'TOTAL'}">
							<div class="moreBtnWrap">
								<a href="javascript:changeSearchCategory('img');"><span class="hidden">이미지</span>더보기 ></a>
							</div>
						</c:if>
					</div><!-- 이미지 -->
				</c:if>
				<c:if test="${not empty videoList}">
					<div class="section video">
						<div class="sectionIn">
							<div class="titGroup">
								<h2 class="tit">동영상</h2>
								<span class="resultNum">(검색결과 ${videoTotal}건)</span>
							</div>
							<ul class="imageList">
								<div class="moreBtnWrap">
									<c:forEach var="videoList" items="${videoList}" varStatus="status">
										<c:import url="/cmm/fms/selectImg.do" charEncoding="utf-8">
											<c:param name="param_atchFileId" value="${videoList.attach_id}" />
											<c:param name="param_fileSn" value="${videoList.file_sn}" />
											<c:param name="param_showThumbnail" value="${true}" />
											<c:param name="downloadYn" value="N"/>
											<c:param name="mode" value="movieThumbnail"/>
											<c:param name="fileCnName" value="locationFileCn" />
										</c:import>
									</c:forEach>
								</div>
							</ul>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty fileList}">
					<div class="section file">
						<div class="sectionIn">
							<div class="titGroup">
								<h2 class="tit">파일</h2><span class="resultNum">(검색결과 ${fileTotal}건)</span>
							</div>
							<ul class="textList">
								<li>
									<c:forEach var="file" items="${fileList}" varStatus="status">
										<div class="txtWrap">
											<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
												<c:param name="param_showThumbnail" value="${true}" />
												<c:param name="updateFlag" value="N"/>
												<c:param name="param_atchFileId" value="${file.attach_id}"/>
											</c:import>
											<span class="categoryTxt" style="color:#999; font-size:85%;">
												<span class="menuStep">${file.b_title}</span>
											</span>
											<span class="categoryTxt" style="color:#999; font-size:85%;">
												<span class="menuStep">${file.pt_title}</span>
											</span>
										</div>
									</c:forEach>
								</li>
							</ul>
						</div>
						<div class="moreBtnWrap">
							<a class="btnMoreResult" href="javascript:changeSearchCategory('file');"><span class="hidden">파일</span>더보기 ></a>
						</div>
					</div><!-- 파일 -->
				</c:if>
				<c:if test="${params.category ne 'TOTAL'}">
					<c:if test="${fn:length(result) eq 1}">
						<div class="bod_page">
							<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage2"/>
						</div>
					</c:if>
					<div class="bod_page">
						<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage2"/>
					</div>
				</c:if>
			</div><!-- .searchcontent -->
			
	
			
		</div>
		<div class="searchcont2">
			<div class="section">
				<h2 class="titkeyword">인기 검색어</h2>
					<div class="tabBox">
						<div class="boxItem active">
							<a class="item" >실시간</a>
							<ul class="ranking">
								<c:forEach var="ppkList" items="${ppkList}" varStatus="status" begin="1" end="5">
									<li>
										<a href="javascript:goSearch('${ppkList.ppk}');" ">${ppkList.ppk}</a>
									</li>
								</c:forEach>
							</ul>
						</div>
						<!-- <div class="boxItem">
					    <a class="item" href="#">일간</a>
					    <ul class="ranking">
					        <li>
					            <a href="#">키워드2</a>
					        </li>
					        <li>
					            <a href="#">키워드2</a>
					        </li>
					        <li>
					            <a href="#">키워드2</a>
					        </li>
					        <li>
					            <a href="#">키워드2</a>
					        </li>
					        <li>
					            <a href="#">키워드2</a>
					        </li>
					    </ul>
						</div>
						<div class="boxItem">
						    <a class="item" href="#">주간</a>
						    <ul class="ranking">
						        <li>
						            <a href="#">키워드3</a>
						        </li>
						        <li>
						            <a href="#">키워드3</a>
						        </li>
						        <li>
						            <a href="#">키워드3</a>
						        </li>
						        <li>
						            <a href="#">키워드3</a>
						        </li>
						        <li>
						            <a href="#">키워드3</a>
						        </li>
						    </ul>
						</div>
						<div class="boxItem">
						    <a class="item" href="#">월간</a>
						    <ul class="ranking">
						        <li>
						            <a href="#">키워드4</a>
						        </li>
						        <li>
						            <a href="#">키워드4</a>
						        </li>
						        <li>
						            <a href="#">키워드4</a>
						        </li>
						        <li>
						            <a href="#">키워드4</a>
						        </li>
						        <li>
						            <a href="#">키워드4</a>
								</li>
							</ul>
						</div> -->
					</div>				
				</div>
			</div>
			<!-- 인기검색어 -->
	</div>
</div>


<script type="text/javascript" src="/common/js/commonProcess.js"></script>
