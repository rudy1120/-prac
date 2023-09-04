<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/newDesign/layout/sysHeader.jsp" %>

<form name="listFrm" id="listFrm" action="" method="post">	
	<input type="hidden" name="page" id="page" value="${page}" />
	<input type="hidden" name="searchType" id="searchType" value="${searchType}" />
	<input type="hidden" name="searchText" id="searchText" value="${searchText}" />
</form>

<h2>질문정보 등록</h2>
<h3>질문1</h3>
<table class="tableSt_write">
	<caption>질문정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">문항유형선택</th>
			<td>
				<select class="input200">
					<option value="">객관식 중복</option>
					<option value="portal">객관식 중복2</option>
				</select>
				<input type="checkbox" />필수 응답 문항
			</td>
		</tr>
		<tr>
			<th>질문</th>
			<td>
				<input type="text" class="input500" />
			</td>
		</tr>
		<tr>
			<th>질문이미지</th>
			<td>
				<input type="text" class="input500" />
				<a href="" class="btn_normal">찾아보기</a>
			</td>
		</tr>
		<tr>
			<th>다중선택 개수</th>
			<td>
				<select name="" id="">
					<option>제한없음</option>
					<option>제한없음</option>
				</select>
				<input type="radio" />이상/이하 적용안함
				<input type="radio" />이상
				<input type="radio" />이하
			</td>
		</tr>
	</tbody>
</table>
<table class="tableSt_write table_plus">
	<caption>질문정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">보기1</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<p><span>첨부파일명_노출.jpg</span> <a href="" class="btn_list_img_delete">개별삭제</a></p>
			<label for="">설명글</label>
			<input type="text" value="첨부파일 이미지 설명글 노출영역이 필요합니다." class="input500" /></td>
		</tr>
		<tr>
			<th scope="row">보기2</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<p><span>첨부파일명_노출.jpg</span> <a href="" class="btn_list_img_delete">개별삭제</a></p>
			<label for="">설명글</label>
			<input type="text" value="첨부파일 이미지 설명글 노출영역이 필요합니다." class="input500" /></td>
		</tr>
		<tr>
			<th scope="row">보기3</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			</td>
		</tr>
		<tr>
			<th scope="row">기타</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<a href="" class="btn_list_plus">보기 추가</a>
			<a href="" class="btn_etc_plus">기타</a>
			</td>
		</tr>
	</tbody>
</table>

<div class="btnboxR">
	<a href="" class="btn_normal">질문추가</a>
	<a href="" class="btn_normal">질문삭제</a>
</div>



<h3>질문2</h3>
<table class="tableSt_write">
	<caption>질문정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">문항유형선택</th>
			<td>
				<select class="input200">
					<option value="">객관식 단답</option>
					<option value="portal">객관식 중복2</option>
				</select>
				<input type="checkbox" />필수 응답 문항
			</td>
		</tr>
		<tr>
			<th>질문</th>
			<td>
				<input type="text" class="input500" />
			</td>
		</tr>
		<tr>
			<th>질문이미지</th>
			<td>
				<input type="text" class="input500" />
				<a href="" class="btn_normal">찾아보기</a>
			</td>
		</tr>
		<tr>
			<th>문항 건너뛰기</th>
			<td>
				<input type="radio" />건너뛰기 미정
				<input type="radio" />건너뛰기 설정
				<input type="text" class="input100" />번 질문에 보기 <input type="text" class="input100" /> 번으로 응답한 경우 질문 응답 가능 <a href="" class="btn_list_plus">보기 추가</a>
			</td>
		</tr>
	</tbody>
</table>
<table class="tableSt_write table_plus">
	<caption>질문정보에서 보기정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">보기1</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<p><span>첨부파일명_노출.jpg</span> <a href="" class="btn_list_img_delete">개별삭제</a></p>
			<label for="">설명글</label>
			<input type="text" value="첨부파일 이미지 설명글 노출영역이 필요합니다." class="input500" /></td>
		</tr>
		<tr>
			<th scope="row">보기2</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<p><span>첨부파일명_노출.jpg</span> <a href="" class="btn_list_img_delete">개별삭제</a></p>
			<label for="">설명글</label>
			<input type="text" value="첨부파일 이미지 설명글 노출영역이 필요합니다." class="input500" /></td>
		</tr>
		<tr>
			<th scope="row">보기3</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			</td>
		</tr>
		<tr>
			<th scope="row">기타</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<a href="" class="btn_list_plus">보기 추가</a>
			<a href="" class="btn_etc_plus">기타</a>
			</td>
		</tr>
	</tbody>
</table>

<div class="btnboxR">
	<a href="" class="btn_normal">질문추가</a>
	<a href="" class="btn_normal">질문삭제</a>
</div>



<h3>질문3</h3>
<table class="tableSt_write">
	<caption>질문정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">문항유형선택</th>
			<td>
				<select class="input200">
					<option value="">주관식단답</option>
					<option value="portal">객관식 중복2</option>
				</select>
				<input type="checkbox" />필수 응답 문항
			</td>
		</tr>
		<tr>
			<th>질문</th>
			<td>
				<input type="text" class="input500" />
			</td>
		</tr>
		<tr>
			<th>질문이미지</th>
			<td>
				<input type="text" class="input500" />
				<a href="" class="btn_normal">찾아보기</a>
			</td>
		</tr>
	</tbody>
</table>

<div class="btnboxR">
	<a href="" class="btn_normal">질문추가</a>
	<a href="" class="btn_normal">질문삭제</a>
</div>



<h3>질문4</h3>
<table class="tableSt_write">
	<caption>질문정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">문항유형선택</th>
			<td>
				<select class="input200">
					<option value="">주관식 장문</option>
					<option value="portal">객관식 중복2</option>
				</select>
				<input type="checkbox" />필수 응답 문항
			</td>
		</tr>
		<tr>
			<th>질문</th>
			<td>
				<input type="text" class="input500" />
			</td>
		</tr>
		<tr>
			<th>질문이미지</th>
			<td>
				<input type="text" class="input500" />
				<a href="" class="btn_normal">찾아보기</a>
			</td>
		</tr>
	</tbody>
</table>

<div class="btnboxR">
	<a href="" class="btn_normal">질문추가</a>
	<a href="" class="btn_normal">질문삭제</a>
</div>



<h3>질문5</h3>
<table class="tableSt_write">
	<caption>질문정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">문항유형선택</th>
			<td>
				<select class="input200">
					<option value="">순위선정형</option>
					<option value="portal">객관식 중복2</option>
				</select>
				<input type="checkbox" />필수 응답 문항
			</td>
		</tr>
		<tr>
			<th>질문</th>
			<td>
				<input type="text" class="input500" />
			</td>
		</tr>
		<tr>
			<th>질문이미지</th>
			<td>
				<input type="text" class="input500" />
				<a href="" class="btn_normal">찾아보기</a>
			</td>
		</tr>
	</tbody>
</table>
<table class="tableSt_write table_plus">
	<caption>질문정보에서 보기정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">보기1</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<p><span>첨부파일명_노출.jpg</span> <a href="" class="btn_list_img_delete">개별삭제</a></p>
			<label for="">설명글</label>
			<input type="text" value="첨부파일 이미지 설명글 노출영역이 필요합니다." class="input500" /></td>
		</tr>
		<tr>
			<th scope="row">보기2</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<p><span>첨부파일명_노출.jpg</span> <a href="" class="btn_list_img_delete">개별삭제</a></p>
			<label for="">설명글</label>
			<input type="text" value="첨부파일 이미지 설명글 노출영역이 필요합니다." class="input500" /></td>
		</tr>
		<tr>
			<th scope="row">보기3</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_img_plus">이미지 추가</a>
			<a href="" class="btn_list_delete">항목 삭제</a>
			</td>
		</tr>
		<tr>
			<th scope="row">기타</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a>
			<a href="" class="btn_list_plus">보기 추가</a>
			<a href="" class="btn_etc_plus">기타</a>
			</td>
		</tr>
	</tbody>
</table>

<div class="btnboxR">
	<a href="" class="btn_normal">질문추가</a>
	<a href="" class="btn_normal">질문삭제</a>
</div>



<h3>질문6</h3>
<table class="tableSt_write">
	<caption>질문정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">문항유형선택</th>
			<td>
				<select class="input200">
					<option value="">매트릭스형</option>
					<option value="portal">객관식 중복2</option>
				</select>
				<input type="checkbox" />필수 응답 문항
			</td>
		</tr>
		<tr>
			<th>질문</th>
			<td>
				<input type="text" class="input500" />
			</td>
		</tr>
		<tr>
			<th>질문이미지</th>
			<td>
				<input type="text" class="input500" />
				<a href="" class="btn_normal">찾아보기</a>
			</td>
		</tr>
	</tbody>
</table>
<table class="tableSt_write table_plus matrix">
	<caption>질문정보에서 보기정보를 등록하는 표입니다.</caption>
	<colgroup>
		<col class="col15 w15">
		<col>
		<col class="col15 w15">
		<col>
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">보기1</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a></td>
			<th scope="row">보기2</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a></td>
		</tr>
		<tr>
			<th scope="row">보기3</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a></td>
			<th scope="row">보기4</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a></td>
		</tr>
		<tr>
			<th scope="row">보기5</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a></td>
			<th scope="row">보기6</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a></td>
		</tr>
		<tr>
			<th scope="row">보기7</th>
			<td><input type="text"  class="input500" value="경제․산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)"/>
			<a href="" class="btn_list_delete">항목 삭제</a></td>
			<th scope="row"></th>
			<td></td>
		</tr>
	</tbody>
</table>

<div class="btnboxR">
	<a href="" class="btn_normal">질문추가</a>
	<a href="" class="btn_normal">질문삭제</a>
</div>
<div class="btnboxC">
	<a href="/newDesign/sys/pollView.jsp" class="btn_mint">저장</a>
	<a href="" class="btn_red">취소</a>
</div>