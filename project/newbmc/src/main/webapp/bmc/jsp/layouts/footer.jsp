<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<footer id="footer">
        <div class="footerCenter">
            <div class="footerInnr">
                <div class="footTit">
                    <h2 class="tit"><strong>고객참여</strong></h2>
                    <p class="subtit">부산도시공사는 겸허한 자세로<br>고객의 소리에 귀를 기울이겠습니다.</p>
                </div>
                <div class="footCont">
                    <ul class="btnList">
                        <li>
                        	<a href="/bmc/contents.do?mId=0304020000"><img src="/bmc/images/ico_center_01.png" alt=""><p class="f-txt">고객의 소리</p></a>
                        </li>
                        <li>
                        	<a href="/bmc/contents.do?mId=0307000000"><img src="/bmc/images/ico_center_03.png" alt=""><p class="f-txt">청렴신고</p></a>
                        </li>
                     	<li class="li_cle li_mar">
                        	<a href="/bmc/participation/list.do?mId=0310000000"><img src="/bmc/images/ico_center_05.png" alt=""><p class="f-txt">고객경영참여</p></a>
                        </li>
                        <li class="li_mar">
                        	<a href="/bmc/event/list.do?mId=0312000000"><img src="/bmc/images/ico_center_02.png" alt=""><p class="f-txt">이벤트</p></a>
                        </li>
                        <li class="li_cle li_mar">
                        	<a href="https://www.busan.go.kr/depart/subcontract01"><img src="/bmc/images/ico_center_06.png" alt=""><p class="f-txt" style="font-size: 0.9em;">불법하도급신고</p><p style="text-align: center; color: #fff; font-size: 0.9em;">(부산시)</p></a>
                        </li>                        
                    </ul>
                </div><!--footCont--> 
            </div><!--footerInnr--> 
        </div><!--footerCenter--> 
        <div class="footerContent">
            <div class="footerInnr">
                <ul class="footerUtils">
                    <li class="bold"><a style="color:#108CCF;" href="/bmc/contents.do?mId=0804010100">개인정보처리방침</a></li>
                    <li class="bold"><a href="/bmc/contents.do?mId=0804010200">영상처리기기 운영·관리 방침</a></li>
  
                    <li class="bold"><a href="/bmc/contents.do?mId=0804010500">저작권보호정책</a></li>
                    <li class="bold"><a href="/bmc/contents.do?mId=0606000000">찾아오시는길</a></li>
                </ul>
	                <div class="footerLogo">
	                	<div> 
	                	 	<a href="/bmc/main.do" target="_blank">
	                		<img src="/bmc/images/logo.png" alt="BMC 부산도시공사">
			                </a>
	                	</div>
	                	<div>
	                		<a href="http://www.wa.or.kr/board/list.asp?search=total&SearchString=%BA%CE%BB%EA%B5%B5%BD%C3%B0%F8%BB%E7&BoardID=0006" target="_blank" title="새창">
		                	<img src="/bmc/images/web_access_logo3.png" alt="(사)한국장애인단체총연합회 한국웹접근성인증평가원 웹 접근성 우수사이트 인증마크(WA인증마크)" style="margin-left: 50px;width: 150px;">
		                	</a>
		                </div>
	                </div>
                <div class="address"> <p>[47281] 부산광역시 부산진구 신천대로 156 TEL. 051 ) 810-1200</p> <p>홈페이지에 게시된 전자우편주소는 무단으로 수집할 수 없으며,<br>위반시 정보통신망법에 의해 처벌됩니다. </p> </div>
                <div class="selectLink">
                    <label for="selectLinkList1" class="blind">유관기관 선택</label> 
                    <select id="selectLinkList1" class="selectLinkList">
                        <option value="" selected>유관기관 선택</option> 
                        <option value="http://www.busan.go.kr">부산광역시청</option> 
                        <option value="http://bfma.or.kr">부산시설공단</option>
                        <option value="http://www.beco.go.kr">부산환경공단</option>
                        <option value="http://www.busanmc.or.kr">부산의료원</option>
                        <option value="https://www.spo1.or.kr/main/main.do">부산지방공단스포원</option>
                        <option value="http://www.humetro.busan.kr">부산교통공사</option>
                        <option value="http://www.bto.or.kr">부산관광공사</option>
                        <option value="http://www.mois.go.kr">행정안전부</option>
                        <option value="http://www.egov.go.kr">전자정부</option>
                        <option value="https://www.busan.go.kr/bsapc">부산광역시자치경찰위원회</option>
                        <option value="http://www.cleaneye.go.kr">지방공기업 경영정보 공개</option>
                        <option value="http://www.arpina.co.kr">아르피나</option>
                        <option value="https://www.bjfez.go.kr/main.web">부산진해경제자유구역청</option>
                    </select>
                    <span class="btn"> <button id="btnGo1" class="btnGo" title="새 창">이동</button> </span>
                    <label for="selectLinkList2" class="blind">전국도시개발공사 선택</label>
                    <select id="selectLinkList2" class="selectLinkList">
                        <option value="" selected="">전국도시개발공사 선택</option>
                        <option value="http://www.i-sh.co.kr">SH공사</option>
                        <option value="http://www.imcd.co.kr">인천도시공사</option>
                        <option value="http://www.gico.or.kr/index.do">경기도시공사</option>
                        <option value="http://www.dcco.kr">대전도시공사</option>
                        <option value="http://www.duco.or.kr">대구도시공사</option>
                        <option value="http://www.gmcc.co.kr">광주도시공사</option>
                        <option value="http://www.umca.co.kr">울산도시공사</option>
                        <option value="http://www.gdco.co.kr/index.php">강원도개발공사</option> 
                        <option value="http://www.cbdc.co.kr/home/index.do">충북개발공사</option> 
                        <option value="https://www.cndc.kr">충남개발공사</option>
                        <option value="http://www.jbdc.co.kr/index.do">전북개발공사</option>
                        <option value="https://jndc.co.kr/cf/index.do">전남개발공사</option>
                        <option value="https://www.gbdc.co.kr/home/main.do">경상북도개발공사</option>
                        <option value="http://www.gndc.co.kr">경남개발공사</option>
                        <option value="http://www.jpdc.co.kr">제주특별자치도개발공사</option>
                    </select>
                    <span class="btn"> <button id="btnGo2" class="btnGo" title="새 창">이동</button> </span>
                </div><!--selectLink-->
            </div><!--footerInnr-->  
             <button id="top">최상단 바로가기</button> 
        </div><!--footerContent--> 
        <div class="copyright">
            <div class="footerInnr">COPYRIGHTⓒ2019 BY BMC. ALL RIGHT RESERVED.</div>
        </div>
    </footer>