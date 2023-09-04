/*
* advanceInfo.js
*
* @author taeseong
* @since 2017.08.25
*/

/** require setting */
requirejs.config({ paths: { "commonProcess": yh.contextPath + "/common/js/commonProcess" }, shim: { "commonProcess": { exports: "commonProcess" }, } });
requirejs(["commonProcess"], function (commonProcess) { console.log("** require loaded **"); });

var advance = {
	mode: "",
	dept: [{ name: "본청", code: "00", "deptList": [
				{ name: "기획관", code: "53802040000" },
				{ name: "공보관", code: "53802050000" },
				{ name: "감사관", code: "53802060000" },
				{ name: "행정국", code: "53801760000" },
				{ name: "경제환경국", code: "53802070000" },
				{ name: "복지문화국", code: "53802330000" },
				{ name: "안전도시국", code: "53802100000" },
				{ name: "개발주택국", code: "53802040000" }]},
			{ name: "직속기관", code: "01", "deptList": [
				{ name: "보건소", code: "53800210000" },
				{ name: "농업기술센터", code: "53800920000" }]},
			{ name: "사업소", code: "02", "deptList": [
				{ name: "상하수도사업소", code: "53801010000" },
				{ name: "차량등록사업소", code: "53801520000" }]},
			//베이직 프로젝트에서는 읍면동을 통합 각자 사이트에 맞게 수정해서 사용
			{ name: "읍면동", code: "03", "deptList": [
				{ name: "동면", code: "53800330000" },
				{ name: "원동면", code: "53800340000" },
				{ name: "상북면", code: "53800350000" },
				{ name: "하북면", code: "53800360000" },
				{ name: "중앙동", code: "53800370000" },
				{ name: "양주동", code: "53801440000" },
				{ name: "삼성동", code: "53800380000" },
				{ name: "강서동", code: "53800390000" },
				{ name: "서창동", code: "53801140000" },
				{ name: "소주동", code: "53801150000" },
				{ name: "평산동", code: "53801160000" },
				{ name: "덕계동", code: "53801170000" }
	]}],
	setTab: function(tab) {
		if (tab === "brm") {
			$("#brmTab").show();
			$("#deptTab").hide();
			advance.tab = "brm";
		} else {
			$("#deptTab").show();
			$("#brmTab").hide();
			advance.tab = "dept";
		}
	},
	setBrmOn: function() {
		$("#brmTab > ul > li > a").each(function(idx) {
			if ($(this).data("brm-idx")) {
				if ($(this).data("brm-idx").toString() === advance.searchMethod) {
					$(this).removeClass("off").addClass("on");
				} else {
					$(this).removeClass("on").addClass("off");
				}
			}
		});
	},
	getBrm: function(depth, uppIdx, callback) {
		advance.ajax("/" + yh.siteCode + "/advanceInfo/brm.do", {"depth": depth, "uppIdx": uppIdx}, function(data) {
			if (data != null) {
				$(data).each(function(idx, obj) {
					if (obj.depth == "1") {
						var span = $("<span>", { class: "ico" });
						var a = $("<a>", { href: "#", class: "off", "data-brm-idx": obj.brmIdx, onclick:"advance.goSearch('brm', '" + obj.brmIdx + "'); return false;" });
						var li = $("<li>", { class: "tabArea" });
						$(a).html($(span)[0].outerHTML + obj.name).appendTo($(li).appendTo($("#brmTab > ul")));
					} else {
						//베이스 프로젝트에서는 BRM 1단계 까지만 구현 추가 단계는 각자 사이트에 맞게 처리.
					}
				}).promise().done(function() {
					if (typeof callback === "function") {
						callback();
					}
				});
			} else {
				console.log("BRM data load fail");
			}
		});
	},
	goSearch: function(type, value) {
		if (type && value) {
			$("#method").val((type === "brm" ? value : ""));
			$("#deptCode").val((type === "dept" ? value : ""));
			$("#list").submit();
		} else {
			document.location.href = "/portal/contents.do?mId=" + yh.mId;
		}
	},
	openDept: function(obj) {
		$("#deptTab > ul > li > div").each(function(idx, obj) {
			$(obj).removeAttr("style");
			$(obj).attr("style","display:none;");
		});
		$(obj).next().removeAttr("style");
	},
	setDept: function() {
		$(advance.dept).each(function(idx, obj) {
			var span = $("<span>", { class: "ico" });
			$("<li>").appendTo($("#deptTab > ul"));
			$("<a>", { href: "#", class: "", onclick: "advance.openDept(this); return false;" }).html($(span)[0].outerHTML + obj.name).appendTo($("#deptTab > ul > li").eq(idx));
			$("<div>", { id: "deptTabSub0" + (idx + 1), class: "", style: "display:none;" }).appendTo($("#deptTab > ul > li").eq(idx));
			$("<ul>").appendTo($("#deptTabSub0" + (idx + 1)));
			for (var i = 0; i < obj.deptList.length; i++) {
				var sub = obj.deptList[i];
				var attrs = { href: "#", onclick: "advance.goSearch('dept', '" + sub.code + "'); return false;", text: sub.name };
				if (advance.searchDept === sub.code) {
					$("#deptTabSub0" + (idx + 1)).removeAttr("style");
					attrs.class = "on";
				}
				$("<a>", attrs).appendTo($("<li>").appendTo($("#deptTabSub0" + (idx + 1) + " > ul")));
			}
		});
	},
	ajax: function(url, data, success) {
		$.ajax({
			url: yh.contextPath + url,
			dataType: "json",
			data: data,
			success: success,
			error : function(e) {
				alert("서버와의 통신 중 오류가 발생하였습니다.");
				console.log(e);
				return false;
			}
		});
	}
};

$(document).ready(function() {
	/** default brm */
	advance.getBrm("1", "0", advance.setBrmOn);
	advance.setDept();
	advance.setTab(advance.tab);
});
