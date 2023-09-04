
$(document).ready(function () {
	var slider = $(".visual");
	slider.bxSlider({
		auto: true, //자동실행
		autoHover: true, //마우스 호버시 정지 여부
		// mveSliders:1, //슬라이드 이동 개수
		/* slideWidth:650, //슬라이드 가로폭 */
		pager: true, //하단도트버튼 숨기기
		controls: true,  //이전 다음 버튼 노출 여부
		autoControls: true  //autocontrols 추가
	});

	var slider = $(".popup");
	slider.bxSlider({
		auto: true, //자동실행
		autoHover: true, //마우스 호버시 정지 여부
		mveSliders: 1,   //슬라이드 이동 개수
		/* slideWidth:650, //슬라이드 가로폭 */
		pager: true, //하단도트버튼 숨기기
		controls: false,  //이전 다음 버튼 노출 여부
		autoControls: true  //autocontrols 추가
	});
});