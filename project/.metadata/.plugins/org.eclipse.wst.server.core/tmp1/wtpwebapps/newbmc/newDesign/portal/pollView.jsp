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
	<div class="assent_box ass_design mT10">
		<h4>개인정보 수집 및 이용 동의</h4>
		<h5 class="mT_im10">개인정보 수집 항목 :</h5>
		<p class="assent_txt">이름, 생년월일, 성별, 연락처</p>
		<br />
		<h5 class="mT_im10">개인정보 수집 목적 :</h5>
		<p class="assent_txt">설문조사 관리 및 이벤트 추첨용</p>
		<br />
		<h5 class="mT_im10">개인정보 보유 기간 :</h5>
		<p class="assent_txt">설문 완료일로 부터 1년</p>
		<div class="taL pL10 mT10">
			위의 개인정보 수집&middot;이용에 대한 동의를 거부할 권리가 있습니다. <br /> 그러나 동의를 거부할 경우,
			원활한 서비스 제공에 일부 제한을 받을 수 있습니다.
		</div>
		<div class="taL mT10 pL10">
			<strong>개인정보의 수집 및 이용목적에 동의합니다.<strong>
			<input type="radio" name="" id="" value="동의" />동의
			<input type="radio" name="" id="" value="미동의" />미동의
		</div>
	</div>
</div>
<div class="btnboxR">
	<a href="/newDesign/portal/pollWrite.jsp" class="btn_blue">설문참여하기</a>
	<a href="/newDesign/portal/pollList.jsp" class="btn_blue">목록</a>
</div>

