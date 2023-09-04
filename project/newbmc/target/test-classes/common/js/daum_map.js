/**
 * lat, lng, level 정보는 본 파일을 인크루드하는 파일에서 선언되어야 합니다.
 * */

var container = document.getElementById('map');

var map = new daum.maps.Map(container, options); // 지도를 생성합니다

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new daum.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도 위에 표시됩니다
// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, daum.maps.ControlPosition.BOTTOMRIGHT);

var zoomControl = new daum.maps.ZoomControl(); // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
map.addControl(zoomControl, daum.maps.ControlPosition.LEFT);

var markerPosition = options.center; // 마커가 표시될 위치입니다

var marker = new daum.maps.Marker({ // 마커를 생성합니다
	position : markerPosition
});

marker.setMap(map); // 마커가 지도 위에 표시되도록 설정합니다

var iwContent = $("#info_box").clone().show().html(), // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다.
iwPosition = options.center; //인포윈도우 표시 위치입니다

var infowindow = new daum.maps.InfoWindow({ // 인포윈도우를 생성합니다
	position : iwPosition,
	content : iwContent
});

infowindow.open(map, marker); // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다