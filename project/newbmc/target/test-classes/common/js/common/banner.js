var yhMain = {
	bannerMax: 0,
	bannerNow: 1,
	nowScrollLeft : 0,
	bannerInterVal : null,
	bannerFlag: 1,

	bannerInit : function() {
		//$(".btn .btn_bplay").addClass("hidden");

		$(".banner_box ul").bind({
			mouseenter: function() {
				yhMain.bannerStop();
			},
			mouseleave: function() {
				yhMain.bannerStart();
			}
		});

		$(".banner_box ul a").bind({
			focus: function() {
				yhMain.bannerStop();
			},
			blur: function() {
				yhMain.bannerStart();
			}
		});

		yhMain.bannerStart();
	},

	bannerPrev : function() {
		var _this = this;
		_this.bannerStop();
		$(".banner_box ul li:first-child").before( $("<li>").html($(".banner_box ul li:nth-child("+bannerMax+")").html()) );
		$(".banner_box ul li:nth-child("+(bannerMax+1)+")").remove();

	},

	bannerNext : function() {
		var _this = this;
		_this.bannerStop();
		_this.bannerRoll();
	},

	bannerStop : function() {
		window.clearInterval(bannerInterVal);
		$("#banner_btn").removeClass("stop");
		$("#banner_btn").addClass("play");
		$("#banner_btn").text("배너순환 재생");
		$("#banner_btn").removeAttr("onclick");
		$("#banner_btn").attr("onclick", "yhMain.bannerStart(); return false;");
		this.bannerFlag = 0;
	},

	bannerStart : function() {
		bannerMax = $(".banner_box ul").children("li").length;
		bannerInterVal = window.setInterval(function(){
			yhMain.bannerRoll();
		}, 3000);
		$("#banner_btn").removeClass("play");
		$("#banner_btn").addClass("stop");
		$("#banner_btn").text("배너순환 일시정지");
		$("#banner_btn").removeAttr("onclick");
		$("#banner_btn").attr("onclick", "yhMain.bannerStop(); return false;");
		this.bannerFlag = 1;
	},

	bannerOnOff : function() {
		if(this.bannerFlag==0) {
			this.bannerStart();
		}
		else {
			this.bannerStop();
		}
	},

	bannerRoll: function() {
		var _this = this;
		obj = $(".banner_box ul").children("li:first-child");
		wid = $(obj).outerWidth();
		wid = 168;

		$(".banner_box ul").animate({ "left" : "-"+wid+"px" }, "slow", function() {
			$("<li>").html($(".banner_box ul li:first-child").html()).appendTo( $(".banner_box ul") );
			$(".banner_box ul li:first-child").remove();
			$(".banner_box ul").css("left","0px");
			$(".banner_box ul").animate({ "left" : "-10px" }, "fast", function() {
				$(".banner_box ul").animate({ "left" : "0px" }, "fast", function() {});
			});
		});
	}
};
