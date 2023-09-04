/**
 * sns.js
 * create : 권태성
 * date : 2014. 12. 30
 * comment : 트위터와 페이스북에 현재 페이지를 공유하기 위한 스크립트 입니다.
 */
var shortUrl = "";
var nowLocation = encodeURI(document.location.href);
var CurrentZoomSize = 100;
var sns = {
	"makeShortUrl" : function() {
		var login = "gumicity";
		var apiKey = "R_25ae05c692f64b47838ed4f002086275";
		var longUrl = nowLocation;

		//ie 구버전에서 jsnop로 처리하기 위해 callback 파라미터 추가
		var apiCallUrl= "//api.bit.ly/v3/shorten?callback=?&"
			+ "login=" + login
			+ "&apiKey=" + apiKey
			+ "&longUrl=" + encodeURIComponent(longUrl);
		$.getJSON(apiCallUrl, function(data){
			if ( data.status_code == "200" ) {
				shortUrl = data.data.url;
			}else{
				shortUrl = longUrl;
			}
		});
	},
	"SetZoomSize" : function(SizeFlag) {

		if (SizeFlag == "B") {
			CurrentZoomSize = CurrentZoomSize + 10;	//크게
		} else {
			(CurrentZoomSize > 0 ? CurrentZoomSize = CurrentZoomSize - 10 : "");	//작게
		}

		$("body").css("zoom", CurrentZoomSize+"%");
		$("body").css("-moz-transform", "scale("+(CurrentZoomSize/100)+")");
		$("body").css("position", "relative");

	},
	"SetSummarySize" : function() {
		if (strlength == "strshort") {
			document.getElementById("img_more").Style.display = "none";
		}
	}
};


$(document).ready(function() {

	sns.makeShortUrl();

	$("#snsTwitter").click(function() {
		var strTitle="";
		$("#tit_wrap > ul > li").each(function(i){
			(i > 0 && strTitle != "Home" && strTitle != "HOME" ? strTitle += ">" : "");
			(strTitle == "Home" || strTitle == "HOME" ? strTitle = "" : "");
			strTitle += $(this).text();
		});
		var twitSubject = encodeURIComponent(strTitle);
		var twitterUrl = "https://twitter.com/intent/tweet?text=" + twitSubject + " " + shortUrl;
		window.open(twitterUrl, "_blank");
	});

	$("#snsFacebook").click(function() {
		//var fbUrl = "https://facebook.com/sharer/sharer.php?s=100&p[title]=" + fbSubject + "&p[url]=" + shortUrl;
		var fbUrl = "https://facebook.com/sharer/sharer.php?u=" + shortUrl;
		window.open(fbUrl, "_blank");
	});
	
	/* sns 공유 리스트 여닫기 */
	$(".sub_acc .btn_share").click(function(e){
		e.preventDefault();
		$(this).addClass("on");
		$(".sub_acc .share_list").stop().animate({opacity:1});
		$(".sub_acc .share_list").css('display','block');
		$(".sub_acc .share_list >div").stop().animate({right:0});
	});
	$(".share_list button.close").click(function(e){
		e.preventDefault();
		$(".sub_acc .btn_share").removeClass("on");
		$(this).parents(".share_list").stop().animate({opacity:0})
		$(".sub_acc .share_list >div").stop().animate({right:-230});
		$("#contentsQrcode").css('display','none');
	});
});