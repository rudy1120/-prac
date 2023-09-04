/**
 * 페이스북 로그인 연동 스크립트
 *
 * @author J.Ryeon Lee
 * @since 2016.06.21
 */
window.fbAsyncInit = function() {
	FB.init({
		appId  : "580346595471881", // 앱 ID
		status : true,              // 로그인 상태를 확인
		cookie : true,              // 쿠키허용
		xfbml  : true               // parse XFBML
	});

//	FB.getLoginStatus(function(response) {
//		if (response.status === "connected") { // TODO
//			saveFbUserInfo(response);
//		} else if (response.status === "not_authorized") {
//
//		} else {
//
//		}
//	});

	FB.Event.subscribe('auth.login', function(response) {
		if (response.status === "connected") {
			saveFbUserInfo(response);
			document.location.reload();
		}
	});
//	FB.Event.subscribe('auth.login', function(response) {
//		if (response.status === "connected") {
//			FB.api("/me", function(user) {
//				if (user) {
//					jQuery("#cContent").removeAttr("disabled");
//					jQuery("#profile_img").attr("src", "http://graph.facebook.com/" + user.id + "/picture");
//					saveFbUserInfo(user);
//					console.log("login success");
//				} else {
//					jQuery("#cContent").attr("disabled", "disabled");
//					jQuery("#profile_img").attr("src", "http://graph.facebook.com/undefined/picture");
//					saveFbUserInfo(null);
//					console.log("login failed");
//				}
//			});
//		}
//	});

	FB.Event.subscribe("auth.logout", function(response) {
		jQuery("#cContent").attr("disabled", "disabled");
		jQuery("#profile_img").attr("src", "http://graph.facebook.com/undefined/picture");
		saveFbUserInfo(null);
	});
};

function saveFbUserInfo(response) {
	jQuery.ajax({
		type : "post",
		url : "/fb/login.do",
		data : {
			"accessToken" : response.authResponse.accessToken,
		},
	});
}

// Load the SDK Asynchronously
(function(d){
	var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	if (d.getElementById(id)) {return;}
	js = d.createElement('script'); js.id = id; js.async = true;
	js.src = "//connect.facebook.net/en_US/all.js";
	ref.parentNode.insertBefore(js, ref);
} (document));

(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) return;
	js = d.createElement(s); js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6&appId=580346595471881";
	fjs.parentNode.insertBefore(js, fjs);
} (document, 'script', 'facebook-jssdk'));