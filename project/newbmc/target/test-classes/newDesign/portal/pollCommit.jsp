<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/newDesign/layout/header.jsp" %>

<form name="listFrm" id="listFrm" action="" method="post">	
	<input type="hidden" name="page" id="page" value="${page}" />
	<input type="hidden" name="searchType" id="searchType" value="${searchType}" />
	<input type="hidden" name="searchText" id="searchText" value="${searchText}" />
</form>

<div class="title_poll">
<h4 class="mT_im0" id="tit_poll">2018년도 예산편성을 위한 설문조사</h4>
	<div class="poll_top">
		<div class="poll_img">
			<img src="/common/img/board/sample_noimage.jpg" alt="이미지가 없습니다." />
		</div>
		<div class="poll_info_wrap">
			<dl class="poll_info">
				<dt>설문조사기간</dt>
				<dd>2017-06-05 ~ 2017-07-28</dd>
			</dl>
			<dl class="poll_info">
				<dt>참여대상</dt>
				<dd>안성시민 중 1960년~1980년생 대상</dd>
			</dl>
			<dl class="poll_info">
				<dt>상태</dt>
				<dd>진행중</dd>
			</dl>
			<dl class="poll_info">
				<dt>참여자 현황</dt>
				<dd>49명</dd>
			</dl>
			<dl class="poll_info">
				<dt>결과 공개여부</dt>
				<dd><span>공개</span></dd>
			</dl>
		</div>
	</div>
	<div class="poll_txt">
		 시민 여러분 안녕하십니까?<br />
		 안성시는“시민이 행복한 맞춤도시, 안성”이라는 시정목표를 지향하면서 2018년도 예산편성의 효율성을 제고하고, 건전재정 운용을 위하여 시민 여러분들의 의견을 수렴하고자 합니다.<br /><br />
		 시민 여러분들의 의견을 바탕으로 내년도 안성시 예산이 내실 있게 편성될 수 있도록 많은 관심과 참여를 당부드립니다. 아울러, 귀하께서 답변해 주신 자료는 본 조사와 관련된 목적에만 사용되며, 개인정보는 비밀이 보장됨을 알려드립니다.<br /><br />
		 ▷ 궁금하신 사항은 정책기획담당관 예산팀(☏678-2032)에게 문의하여 주시면 성심껏 답변 드리겠습니다.
	</div>
	
	<dl class="poll_Qlist commit_list">
		<dt>
			<span class="tit">질문 1</span>
			<span>2018년도 우선적으로 투자해야 할 분야를 2가지만 선택하여 주시기 바랍니다.</span>
		</dt>
		<dd>
			<ul class="list_ul_h4">
				<li>
					<p>경제&middot;산업 (기업유치, 소상공인 및 중소기업 지원, 일자리 창출 등)</p>
					<div class="bar_warp">
						<p class="bar">
							<span class="bar_on" style="width: 23.5%;"></span>
						</p>
					</div>
					<span class="bar_txt">투표수 : 8명 / <strong>23.5%</strong></span>
				</li>
				<li>
					<p>복지․보건 (취약계층 지원, 보육, 가족 및 여성, 노인․청소년, 보건의료 등)</p>
					<div class="bar_warp">
						<p class="bar">
							<span class="bar_on" style="width: 40%; "></span>
						</p>
					</div>
					<span class="bar_txt">투표수 : 16명 / <strong>40%</strong></span>
				</li>
				<li>
					<p>문화&middot;관광&middot;체육 (문화재, 체육시설, 문화행사, 관광상품 등)</p>
					<div class="bar_warp">
						<p class="bar">
							<span class="bar_on" style="width: 10%; "></span>
						</p>
					</div>
					<span class="bar_txt">투표수 : 3명 / <strong>10%</strong></span>
				</li>
				<li>
					<p>교육 (유아 및 초등교육, 고등교육, 평생학습 등)</p>
					<div class="bar_warp">
						<p class="bar">
							<span class="bar_on" style="width: 70%; "></span>
						</p>
					</div>
					<span class="bar_txt">투표수 : 21명 / <strong>70%</strong></span>
				</li>
			</ul>
		</dd>
	</dl>
	
	<dl class="poll_Qlist commit_list">
		<dt>
			<span class="tit">질문 2</span>
			<span>인구유출 방지를 위해 가장 중점적으로 추진해야 할 사업은?</span>
		</dt>
		<dd>
			<ul class="list_ul_h4">
				<li>
					<p>기업투자 환경개선</p>
					<div class="bar_warp">
						<p class="bar">
							<span class="bar_on" style="width: 23.5%;"></span>
						</p>
					</div>
					<span class="bar_txt">투표수 : 8명 / <strong>23.5%</strong></span>
				</li>
				<li>
					<p>교육 인프라 확충</p>
					<div class="bar_warp">
						<p class="bar">
							<span class="bar_on" style="width: 10%; "></span>
						</p>
					</div>
					<span class="bar_txt">투표수 : 3명 / <strong>10%</strong></span>
				</li>
				<li>
					<p>문화 예술공간 확충</p>
					<div class="bar_warp">
						<p class="bar">
							<span class="bar_on" style="width: 70%; "></span>
						</p>
					</div>
					<span class="bar_txt">투표수 : 3명 / <strong>70%</strong></span>
				</li>
			</ul>
			<ul class="list_ul_h4">
				<li>
					<p>기타 <a href="" class="btn_sblue">답변보기</a></p>
					<div class="bar_warp">
						<p class="bar">
							<span class="bar_on" style="width: 4%; "></span>
						</p>
					</div>
					<span class="bar_txt">투표수 : 3명 / <strong>4%</strong></span>
				</li>
			</ul>
		</dd>
	</dl>
	
	<dl class="poll_Qlist qBasic">
		<dt>
			<span class="tit">질문 3</span>
			<span>2018년도 우선적으로 투자해야 할 분야를 기재하여 주시기 바랍니다. </span>
		</dt>
		<dd>
			<ul>
				<li>
					<p>주관식항목은 관리자만 열람가능합니다.</p>
				</li>
			</ul>
		</dd>
	</dl>
	
	<dl class="poll_Qlist qBasic">
		<dt>
			<span class="tit">질문 4</span>
			<span>안전한 생명도시 조성을 위한 재난․안전에 우선적으로 투자해야 할 사업의 순위를 책정해 주시기 바랍니다. </span>
		</dt>
		<dd>
			<ul>
				<li>
					<p>주관식항목은 관리자만 열람가능합니다.</p>
				</li>
			</ul>
		</dd>
	</dl>
	
	<dl class="poll_Qlist qBasic commit_list">
		<dt>
			<span class="tit">질문 5</span>
			<span>안전한 생명도시 조성을 위한 재난&middot;안전에 우선적으로 투자해야 할 사업의 순위를 책정해 주시기 바랍니다.</span>
		</dt>
		<dd>
			<ul class="list_ul_h4">
				<li>
					재난예방시설․장비 확충
					<ul>
						<li class="oneline">
							<span>1순위</span>
							<div class="bar_warp">
								<p class="bar">
									<span class="bar_on" style="width: 4%; "></span>
								</p>
							</div>
							<span class="bar_txt">투표수 : 3명 / <strong>4%</strong></span>
						</li>
						<li class="oneline">
							<span>2순위</span>
							<div class="bar_warp">
								<p class="bar">
									<span class="bar_on" style="width: 16%; "></span>
								</p>
							</div>
							<span class="bar_txt">투표수 : 3명 / <strong>16%</strong></span>
						</li>
						<li class="oneline">
							<span>3순위</span>
							<div class="bar_warp">
								<p class="bar">
									<span class="bar_on" style="width: 50%; "></span>
								</p>
							</div>
							<span class="bar_txt">투표수 : 3명 / <strong>50%</strong></span>
						</li>
						<li class="oneline">
							<span>4순위</span>
							<div class="bar_warp">
								<p class="bar">
									<span class="bar_on" style="width: 1%; "></span>
								</p>
							</div>
							<span class="bar_txt">투표수 : 3명 / <strong>1%</strong></span>
						</li>
					</ul>
				</li>
			</ul>
		</dd>
	</dl>
	
	<dl class="poll_Qlist commit_list">
		<dt>
			<span class="tit">질문 6</span>
			<span>안전한 생명도시 조성을 위한 재난․안전에 우선적으로 투자해야 할 사업의 순위를 책정해 주시기 바랍니다. </span>
		</dt>
		<dd>
			<ul>
				<li>
					<p><img src="https://www.anseong.go.kr/common/jsp/common/imgViewer.jsp?basePath=/home/webdata/egov_uploadFile/VISUALZONE/&streFileNm=VIS_201708221024084960&orignlFileNm=VIS_201708221024084960.jpg&ext=jpg&mode=originView" alt="" /></p>
					<div class="cB answer">
						<p class="fR"><span class="sure">그렇다</span><span class="so">보통이다</span><span class="no">아니다</span></p>
					</div>
					<table class="table_st taC">
						<caption>설문조사 질문 6 - 질문6번에 대해 문항과 결과를 안내하는 표입니다.</caption>
						<colgroup>
							<col span="1" class="col30 w30">
							<col span="1" class="col70 w70">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">문항</th>
								<th scope="col">결과</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row" class="taL">오늘이 몇 월이고, 무슨 요일인지 잘 모른다.</th>
								<td>
									<ul>
										<li class="oneline">
											<span class="hidden">그렇다</span>
											<div class="bar_warp">
												<p class="bar sure">
													<span class="bar_on" style="width: 4%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>4%</strong></span>
										</li>
										<li class="oneline">
											<span class="hidden">보통이다</span>
											<div class="bar_warp">
												<p class="bar so">
													<span class="bar_on" style="width: 16%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>16%</strong></span>
										</li>
										<li class="oneline">
											<span class="hidden">아니다</span>
											<div class="bar_warp">
												<p class="bar no">
													<span class="bar_on" style="width: 50%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>50%</strong></span>
										</li>
									</ul>
								</td>
							</tr>
							<tr>
								<th scope="row" class="taL">자기가 놔둔 물건을 찾지 못한다.</th>
								<td>
									<ul>
										<li class="oneline">
											<span class="hidden">그렇다</span>
											<div class="bar_warp">
												<p class="bar sure">
													<span class="bar_on" style="width: 4%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>4%</strong></span>
										</li>
										<li class="oneline">
											<span class="hidden">보통이다</span>
											<div class="bar_warp">
												<p class="bar so">
													<span class="bar_on" style="width: 16%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>16%</strong></span>
										</li>
										<li class="oneline">
											<span class="hidden">아니다</span>
											<div class="bar_warp">
												<p class="bar no">
													<span class="bar_on" style="width: 50%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>50%</strong></span>
										</li>
									</ul>
								</td>
							</tr>
							<tr>
								<th scope="row" class="taL">같은 질문을 반복해서 한다.</th>
								<td>
									<ul>
										<li class="oneline">
											<span class="hidden">그렇다</span>
											<div class="bar_warp">
												<p class="bar sure">
													<span class="bar_on" style="width: 4%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>4%</strong></span>
										</li>
										<li class="oneline">
											<span class="hidden">보통이다</span>
											<div class="bar_warp">
												<p class="bar so">
													<span class="bar_on" style="width: 16%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>16%</strong></span>
										</li>
										<li class="oneline">
											<span class="hidden">아니다</span>
											<div class="bar_warp">
												<p class="bar no">
													<span class="bar_on" style="width: 50%; "></span>
												</p>
											</div>
											<span class="bar_txt">투표수 : 3명 / <strong>50%</strong></span>
										</li>
									</ul>
								</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</dd>
	</dl>
</div>
<div class="btnboxR">
	<a href="/newDesign/portal/pollWrite.jsp" class="btn_blue">설문참여하기</a>
	<a href="/newDesign/portal/pollList.jsp" class="btn_blue">목록</a>
</div>

