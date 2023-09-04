$(document).ready(function() {
	dp.bind($("[data-date=y]"));
	$("#searchPeriodType").change(function() {
		$("[id^=div_]").hide();
		switch ($(this).val()) {
			case "month":
				$("#div_y").show();
				$("#div_m").show();
				break;
			case "range":
				$("#div_r").show();
				break;
			default:
				$("#div_y").show();
				break;
		}
	});

	$("#searchPeriodType").trigger("change");
});
