/** 공공누리 스크립트 (KISA 제공) */

//유형 선택에 따른 공공누리 유형 미리보기 처리
$(document).ready(function () {
	$('#bNuriType1').click(function () {
		$(".codeView02, .codeView03,.codeView04,.codeView05").hide();
		$(".codeView01").show();
	}); //type1 check
	$('#bNuriType2').click(function () {
		$(".codeView01, .codeView03,.codeView04,.codeView05").hide();
		$(".codeView02").show();
	}); //type2 check
	$('#bNuriType3').click(function () {
		$(".codeView02, .codeView01,.codeView04,.codeView05").hide();
		$(".codeView03").show();
	}); //type3 check
	$('#bNuriType4').click(function () {
		$(".codeView02, .codeView03,.codeView01,.codeView05").hide();
		$(".codeView04").show();
	}); //type4 check
	$('#bNuriType5').click(function () {
		$(".codeView02, .codeView03,.codeView04,.codeView01").hide();
		$(".codeView05").show();
	}); //적용불가 check

	$("[name=bNuriType]:checked").trigger("click");
});
