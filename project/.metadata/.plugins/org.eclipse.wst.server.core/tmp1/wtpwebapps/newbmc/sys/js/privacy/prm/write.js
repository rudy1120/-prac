/**
 * 개인정보취급메뉴 등록/수정 JS
 *
 * @author J.Ryeon Lee
 * @since 2017.06.19
 */
$(document).ready(function() {
	$("#prmType").click(function() {
		if ($("#prmType :selected").val() == "0") {
			$("#prmTableName").val("bbs_board");
			$("#tableName_container").hide();
			$("#tmpTableName_container").show();
		} else {
			$("#tableName_container").show();
			$("#tmpTableName_container").hide();
		}
	});
	$("#prmSiteCode").click(function() {
		$("#prmSiteName").val($("#prmSiteCode option:selected").text());
	});

	$("#prmSiteCode").trigger("click");

	if (yh.isNew) {
		$("#prmPeriod option:last").prop("selected", true);
	}
});

var process = {
	additional_validator: function() {
		try {
			if ($("#prmParamsMap").val()) {
				$.parseJSON($("#prmParamsMap").val());
			}
		} catch (e) {
			alert("JSON 형식이 올바르지 않습니다.");
			$("#prmParamsMap").focus();
			return false;
		}

		return true;
	}
};
