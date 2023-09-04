<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<%
/**
 * 사용자 화면 기구표 및 업무내용
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.12.23		박상혁		    최초 생성 및 코드 수정
 *
 *
 * @author 박상혁
 * @since 2019.12.23
 */
%>

<div id="contents"><!--콘텐츠 시작-->
<div class="content"><img alt="조직도 : 기구표 및 업무내용 사장 아래 안전관리단, 기획경영본부, 도시창조본부, 시민복지사업본부로 나누어져있습니다. 
	독립된 감사 소속으로는 청렴감사실이 있습니다. 기획경영본부 소속으로는 기획관리실, 경영지원실, 분양보상처,재무관리처가 있으며 도시창조본부 소속으로는 미래전략실, 단지사업처, 주택사업처, 기전사업처, 공공사업처가 있습니다. 시민복지사업본부 소속으로는 복지사업처, 맞춤임대처, 시설관리처가 있습니다." src="/bmc/images/content/org_img.png" /> 
	<div class="org_hidden">
        <h1>부산도시공사의 기구표입니다.</h1>
        <ul>
            <h2>부산도시공사는 사장 산하 안전관리단, 기획경영본부, 도시창조본부, 시민복지사업본부로 구성되어있고, 독립적인 감사 기관이 있습니다.</h2>
            <li>사장
                <ul>
                    <li>안전관리단</li>
                    <li>기획경영본부
                        <span>기획경영본부 산하 4개 부서가 있습니다.</span>
                        <ul>
                            <li>기획관리실</li>
                            <li>경영지원실</li>
                            <li>분양보상처</li>
                            <li>재무관리처</li>
                        </ul>
                    </li>
                    <li>도시창조본부
                        <ul>
                            <span>도시창조본부 산하 5개 부서가 있습니다.</span>
                            <li>미래전략실</li>
                            <li>단지사업처</li>
                            <li>주택사업처</li>
                            <li>기전사업처</li>
                            <li>공공사업처</li>
                        </ul>
                    </li>
                    <li>시민복지사업본부
                        <span>시민복지사업본부 산하 3개 부서가 있습니다.</span>
                        <ul>
                            <li>복지사업처</li>
                            <li>맞춤임대처</li>
                            <li>시설관리처</li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li>감사
            <li>청렴감사실</li>
            </li>
        </ul>
    </div><!-- .org_hidden 끝 -->
    
    <!-- depth 1 ~ 2 나오는 페이지 -->
	<c:forEach var="depth1" items="${result}" varStatus="status">
		<c:if test="${depth1.newDepth == 1}">
			<c:set var="newname" value="${fn:split(depth1.newSname, '(상위') }"/>
			<p class="depth2-title" id="${depth1.depCode}">${newname[0]}</p>
			<p class="scroll_txt">좌우로 스크롤 하세요</p>
			<br />
			<div class="orgTable_wrap">
				<table class="orgTable">
					<caption>${newname[0]}</caption>
					<colgroup> <col style="width: 20%;" /> <col /> <col style="width: 22%;" /> <col style="width: 10%;" /> </colgroup>
					<thead>
						<tr><th scope="col">부서명</th><th scope="col">부서업무</th><th scope="col">연락처</th><th scope="col">부서위치</th></tr>
					</thead>
					<tbody>
						<c:forEach var="depth2" items="${result}">
						<c:if test="${depth2.newDepth == 2 and depth1.depCode == depth2.newParent}">
							<tr>
								<td>${depth2.newSname}</td>
								<td>${depth2.depComment}</td>
								<c:set var="telNo" value="${fn:replace(depth2.repTelNo, '/', '<br>') }" />
								<c:set var="faxNo" value="${fn:replace(depth2.repFaxNo, '/', '<br>') }" />
								<td> Tel. ${telNo}
								<br /> Fax. ${faxNo}</td>
								<td>${depth2.newLocation}</td>
							</tr>
						</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<div class="gap20"></div>	
		</c:if>
	</c:forEach>
	</div>
</div>
<!-- 
	<p class="depth2-title" id="53800100000">청렴감사실</p>
    <p class="scroll_txt">좌우로 스크롤 하세요</p>
    <br />
    <div class="orgTable_wrap">
        <table class="orgTable">
            <caption>청렴감사실</caption>
            <colgroup> <col style="width: 20%;" /> <col /> <col style="width: 22%;" /> <col style="width: 10%;" /> </colgroup>
            <thead>
                <tr><th scope="col">부서명</th><th scope="col">부서업무</th><th scope="col">연락처</th><th scope="col">부서위치</th></tr>
            </thead>
            <tbody>
                    <tr>
                        <td>청렴감사실</td>
                        <td>외부감사 수감업무 및 자체감사 · 감찰, 일상감사, 윤리경영 · 청렴업무 등</td>
                        <td> Tel. 051-810-1445
                        <br /> Fax. 051-810-1449</td>
                        <td>10층</td>
                    </tr>
            </tbody>
        </table>
    </div>
    <div class="gap20"></div>   
    
    <p class="depth2-title" id="53802800000">안전관리단</p>
    <p class="scroll_txt">좌우로 스크롤 하세요</p>
    <br />
    <div class="orgTable_wrap">
        <table class="orgTable">
            <caption>안전관리단</caption>
            <colgroup> <col style="width: 20%;" /> <col /> <col style="width: 22%;" /> <col style="width: 10%;" /> </colgroup>
            <thead>
                <tr><th scope="col">부서명</th><th scope="col">부서업무</th><th scope="col">연락처</th><th scope="col">부서위치</th></tr>
            </thead>
            <tbody>
                    <tr>
                        <td>안전관리단</td>
                        <td>안전보건계획 수립, 중대재해 예방 종합계획 수립, 중대재해 관련 각종 지침 및 가이드라인 수립, 중대재해처벌법 이행사항 지도점검, 재난안전 관련 용역 추진, 시설물 안전 및 유지관리계획 수립 및 관리, 안전관리 수준평가 수행  등</td>
                        <td> Tel. 051-810-9994
                        <br /> Fax. 051-810-9999</td>
                        <td>5층</td>
                    </tr>
            </tbody>
        </table>
    </div>
    <div class="gap20"></div>   

	<p class="depth2-title" id="53805500004">기획경영본부</p>
    <p class="scroll_txt">좌우로 스크롤 하세요</p>
    <br />
    <div class="orgTable_wrap">
        <table class="orgTable">
            <caption>기획경영본부</caption>
            <colgroup> <col style="width: 20%;" /> <col /> <col style="width: 22%;" /> <col style="width: 10%;" /> </colgroup>
            <thead>
                <tr><th scope="col">부서명</th><th scope="col">부서업무</th><th scope="col">연락처</th><th scope="col">부서위치</th></tr>
            </thead>
            <tbody>
                <tr>
                    <td>기획관리실</td>
                    <td>조직 및 정원관리, 경영전략 및 경영혁신, 법무, 예산, 자금관리, 시의회, 경영평가, CEO 평가, 내부평가, 사회적 가치실현, 인권경영, 사업관리 등</td>
                    <td> Tel. 051-810-1216
                    <br /> Fax. 051-810-1239</td>
                    <td>6층</td>
                </tr>
                <tr>
                    <td>경영지원실</td>
                    <td>인사, 노무, 교육, 보수 및 복리후생, 의전 및 행사, 사옥, 차량, 홍보, 사회공헌활동, 고객만족도, 경영공시, 사보발간, 양궁팀 운영, 정보시스템 운영, 개인정보 및 정보보안, 기록물관리, 홈페이지 운영 등</td>
                    <td> Tel. 051-810-1245
                    <br /> Fax. 051-810-1400</td>
                    <td>8층</td>
                </tr>
                <tr>
                    <td>분양보상처</td>
                    <td>아파트, 택지, 상가, 산업단지 등 공급 관리 및 분양, 출자토지 공급 관리, 토지 · 지장물 · 이주비 등 보상업무, 이주 및 생활대책 수립 등</td>
                    <td> Tel. 051-810-1257
                    <br /> Fax. 051-810-1259</td>
                    <td>3층</td>
                </tr>
                <tr>
                    <td>재무관리처</td>
                    <td>회계결산 및 재무분석, 세무회계 업무, 지출업무, 각종 계약업무, 공공구매 관리업무, 물품구매, 재물조사, 수입금의 수납결정 및 정산, 각종 수입금의 납부독려 및 체납금 수납 등</td>
                    <td> Tel. 051-810-1285
                    <br /> Fax. 051-810-1269</td>
                    <td>11층</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="gap20"></div>   
	
	<p class="depth2-title" id="53805500005">도시창조본부</p>
    <p class="scroll_txt">좌우로 스크롤 하세요</p>
    <br />
    <div class="orgTable_wrap">
        <table class="orgTable">
            <caption>도시창조본부</caption>
            <colgroup> <col style="width: 20%;" /> <col /> <col style="width: 22%;" /> <col style="width: 10%;" /> </colgroup>
            <thead>
                <tr><th scope="col">부서명</th><th scope="col">부서업무</th><th scope="col">연락처</th><th scope="col">부서위치</th></tr>
            </thead>
            <tbody>
                <tr>
                    <td>미래전략실</td>
                    <td>중장기 신규사업 후보지 발굴 · 관리, 정부정책 주택사업 참여 검토, 민간참여 공공주택사업 · 건설임대리츠 및 SPC/PF사업 검토 등 </td>
                    <td> Tel. 051-810-1475
                    <br /> Fax. 051-810-1479</td>
                    <td>11층</td>
                </tr>
                <tr>
                    <td>토목사업처</td>
                    <td>오시리아관광단지 조성사업, 센텀2지구 도시첨단산업단지 조성사업, 국제산업물류도시(1-2단계) 조성사업, 일광지구 도시개발사업, 오리일반산업단지 개발사업, 사상재생사업지구(일반산업단지) 활성화구역 조성사업, 서부산권 복합산업유통단지 조성사업, 다대포항 다기능어항 개발사업, 개발사업 조경업무 등</td>
                    <td> Tel. 051-810-1495
                    <br /> Fax. 051-810-1369</td>
                    <td>5층</td>
                </tr>
                <tr>
                    <td>주택사업처</td>
                    <td>주택건설사업 시행 및 하자관리(소송업무), 주거환경개선 등 도시정비사업 시행, 프로젝트 파이낸싱 등 민간투자유치사업 시행, 건축공사 대행사업 등</td>
                    <td> Tel. 051-810-1419
                    <br /> Fax. 051-810-1439</td>
                    <td>9층</td>
                </tr>
                <tr>
                    <td>기전사업처</td>
                    <td>기계, 전기, 정보통신, 소방 설비공사, 단위사업별 설계용역 및 건설사업 관리용역, 단위사업별 공사발주 및 관리, 사업준공업무, 하자관리 등</td>
                    <td> Tel. 051-810-1458
                    <br /> Fax. 051-810-1459</td>
                    <td>4층</td>
                </tr>
                <tr>
                    <td>도시재생처</td>
                    <td>도시재생사업, 도시재생 뉴딜사업, 소규모주택정비사업, 정비사업시행 등</td>
                    <td> Tel. 051-810-8518
                    <br /> Fax. 051-810-1399</td>
                    <td>6층</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="gap20"></div>   
	
	<p class="depth2-title" id="53805500006">시민복지사업본부</p>
    <p class="scroll_txt">좌우로 스크롤 하세요</p>
    <br />
    <div class="orgTable_wrap">
        <table class="orgTable">
            <caption>시민복지사업본부</caption>
            <colgroup> <col style="width: 20%;" /> <col /> <col style="width: 22%;" /> <col style="width: 10%;" /> </colgroup>
            <thead>
                <tr><th scope="col">부서명</th><th scope="col">부서업무</th><th scope="col">연락처</th><th scope="col">부서위치</th></tr>
            </thead>
            <tbody>
                <tr>
                    <td>복지사업처</td>
                    <td>주거복지 기획 총괄, 영구임대·공공임대·국민임대·행복주택 공급 및 관리, 아르피나 관리</td>
                    <td> Tel. 051-810-1328
                    <br /> Fax. 051-810-1329</td>
                    <td>3층</td>
                </tr>
                <tr>
                    <td>맞춤임대처</td>
                    <td>임대주택(매입, 전세, 재개발 등) 관리, 市 주거복지센터 운영</td>
                    <td> Tel. 051-810-1316
                    <br /> Fax. 051-810-1208</td>
                    <td>2층</td>
                </tr>
                <tr>
                    <td>시설관리처</td>
                    <td>공공리모델링주택 건립공사, 임대주택 승강기 추가설치공사 및 노후승강기 교체공사, 임대주택 시설물 수선 및 유지관리</td>
                    <td> Tel. 051-810-1398 
                    <br /> Fax. 051-810-1395</td>
                    <td>3층</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="gap20"></div>   
		
</div>
</div>
<div class="gap80"></div>
 -->
<!--콘텐츠 끝-->

