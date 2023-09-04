<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
/**
 * 주소 → 좌표 변환 페이지
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2017.12.07		권태성			최초 생성 및 코드 수정
 *
 *
 * @author 권태성
 * @since 2017.12.07
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>주소 → 좌표 변환 페이지</title>
		<link rel="stylesheet" type="text/css" class="ui" href="/common/js/semanticui/semantic.min.css" />
		<script type="text/javascript" src="/common/js/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ccb38c34be0a013c0603d8333eda9eb5&libraries=services"></script>
		<script type="text/javascript" src="/common/js/semanticui/semantic.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				$("#ret").hide();
				$("#convertBtn").click(function () {
					if ($("#addr").val() != "") {
						// 주소-좌표 변환 객체를 생성합니다
						var geocoder = new daum.maps.services.Geocoder();
						// 주소로 좌표를 검색합니다
						geocoder.addressSearch($("#addr").val(), function(result, status) {
							// 정상적으로 검색이 완료됐으면 
							if (status === daum.maps.services.Status.OK) {
								$("#ret").text("lat : " + result[0].y + " / lng : " + result[0].x);
							}  else {
								$("#ret").removeClass("positive").addClass("error");
								$("#ret").text("변환에 실패했습니다.");
							}
							$("#ret").show();
						});
					}
				});
			});
		</script>
	</head>
	<body style="margin-left:10px; margin-right:10px;">
		<h1>Addr2GeoLocation</h1>
		<div class="ui info message">
			<div class="header">
				사용 안내
			</div>
			<ul class="list">
				<li>주소를 좌표로 변환해주는 프로그램입니다.</li>
				<li><strong>KAKAO의 API를 사용하기 때문에 한 번에, 짧은 시간내에 많은 양의 요청을 하면 차단될 수 있습니다.</strong></li>
			</ul>
		</div>
		<div>
			<div class="ui icon input">
				<input type="text" id="addr" name="addr" placeholder="주소를 입력하세요." value="" style="width: 500px;" />
				<i id="convertBtn" class="circular search link icon"></i>
			</div>
		</div>
		<div id="ret" class="ui ignored positive message" style="width: 500px;"></div>
	</body>
</html>