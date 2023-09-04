<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
/**
 * 마커 위치정보 페이지
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2019.05.02		김유정			최초 생성 및 코드 수정
 *
 *
 * @author 김유정
 * @since 2019.05.02
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>마커 위치정보 페이지</title>
		<link rel="stylesheet" type="text/css" class="ui" href="/common/js/semanticui/semantic.min.css" />
		<script type="text/javascript" src="/common/js/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ccb38c34be0a013c0603d8333eda9eb5&libraries=services"></script>
		<script type="text/javascript" src="/common/js/semanticui/semantic.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				var mapContainer = document.getElementById("map"), // 지도를 표시할 div 
			    mapOption = { 
			        center: new daum.maps.LatLng(35.834479, 128.680502), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };
				var map = new daum.maps.Map(mapContainer, mapOption);	// 지도 생성
				
				// 지도를 클릭한 위치에 표출할 마커
				var marker = new daum.maps.Marker({ 
				    // 지도 중심좌표에 마커 생성
				    position: map.getCenter() 
				}); 
				// 지도에 마커 표시
				marker.setMap(map);
				
				// 지도에 클릭 이벤트 등록
				// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출
				daum.maps.event.addListener(map, "click", function(mouseEvent) {        
				    
				    // 클릭한 위도, 경도 정보
				    var latlng = mouseEvent.latLng; 
				    
				    // 마커 위치를 클릭한 위치로 옮김
				    marker.setPosition(latlng);
				    
				    /* getLat() = 위도
				   	   getLng() = 경도 */
				   	var latitude = latlng.getLat();
					var longitude = latlng.getLng();
				    
					$("#lat").val(latitude);
					$("#lng").val(longitude);
				    
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
				<li>마커의 위치정보를 불러오는 프로그램입니다.</li>
				<li><strong>KAKAO의 API를 사용하기 때문에 한 번에, 짧은 시간내에 많은 양의 요청을 하면 차단될 수 있습니다.</strong></li>
			</ul>
		</div>
		<div>
			<div id="map" style="width:50%;height:350px;"></div>
			<div id="clickLatlng"></div>
		</div>
		<div class="ui icon input">
			<input type="text" id="lat" name="lat" placeholder="위도가 표시됩니다." value="" style="width: 300px;" readonly />&nbsp; / &nbsp;
			<input type="text" id="lng" name="lng" placeholder="경도가 표시됩니다." value="" style="width: 300px;" readonly />
		</div>
	</body>
</html>