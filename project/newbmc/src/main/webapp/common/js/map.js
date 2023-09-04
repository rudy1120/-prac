/** @author J.Ryeon Lee */
function popup_map(name, lat, lng) {
	window.open(
		"http://map.daum.net/" +
		"?urlX=" + lat +
		"&urlY=" + lng +
		"&q=" + encodeURI(name),
		"위치정보",
		"width=850, height=700, left=150, top=100, resizable=no, scrollbars=true, status=no,toolbar=no"
	);
}
