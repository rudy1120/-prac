/**
 * input tag 속성에 validator="on" 기술을 통해 적용할 수 있습니다.
 *
 * 2015.07.27 현재 사용 가능 속성 LIST
 * data-validator='on'		validator 함수 적용.
 * data-inputType='num'		숫자 입력 체크.
 * data-inputType='phone'	휴대폰 번호 입력 체크. (000-0000-0000 pattern only)
 * data-inputType='file'	파일 확장자 체크.
 * data-inputType='alpNum'	알파벳, 숫자, _ 입력 체크.
 * data-inputType='date'	날짜 패턴 체크. (yyyy-MM-dd only)
 * data-inputType='time'	시간 패턴 체크. (HH:mm only)
 * data-notHoliday='y'		주말 입력 불가 체크.
 * data-required='y'		필수 입력 체크.
 * maxlength='${val}'		maxlength 체크.
 * data-min='${val}'		최솟값 체크. inputType="num"인 element에 한해 검증.
 * data-max='${val}'
 * data-fieldName='${val}'	에러메세지를 출력할 필드명. 본 필드명으로 동적으로 에러메세지를 출력함.
 * data-startwith='${val}'	val로 시작하는지 여부 검증.
 *
 * e.g.
 * input ... data-validator="on"
 * required(필수 체크)의 경우 속성값을 주는 것만으로도 필수 체크 대상이 됩니다.
 * 필드값 검증은 validator() call로 실행됩니다.
 *
 *
 *
 * 2015.07.29 J.Ryeon Lee
 * IE10, FireFox 38.0.5, Chrome 43.0.2357.130 테스트 완료.
 *
 * 2015.08.13 J.Ryeon Lee
 * string value byte length를 체크하도록 수정.
 *
 * 2015.10.29 J.Ryeon Lee
 * w3c 웹접근성 고려 속성명 변경 및 에러 메세지 출력 로직 변경.
 *
 * 2016.05.18 J.Ryeon Lee
 * inputType="alpNum" 추가. 알파벳과 숫자, _만 입력 가능 속성.
 *
 * 2016.11.02 J.Ryeon Lee
 * data-notHoliday 속성 추가. 날짜 입력값 중 주말을 검증.
 *
 * 2017.05.31 J.Ryeon Lee
 * checkbox, radio 필수값 체크 기능 추가
 *
 * @author J.Ryeon Lee
 * @since 2015.07.27
 * @version 1.0.0
 */

var attr_key = {
	validator: 'data-validator',
	required: 'data-required',
	inputType: 'data-inputType',
	notHoliday: 'data-notHoliday',
	fieldName: 'data-fieldName',
	min: 'data-min',
	max: 'data-max',
	digit: 'data-digit',
	fileType: 'data-fileType',
	ulId: 'data-ulId',
	minLength: 'data-minLength',
	startwith: 'data-startwith'
};

var validation_regxp = {
	userId: "^[a-zA-Z]{1}[0-9a-zA-Z]{3,}$",
	pw_number: "^.*[0-9]+.*$",
	pw_alpha: "^.*[a-zA-Z]+.*$",
	pw_special_char: "^.*[!@#$%^*?_+=;:]+.*$"
};

function chk_privacy_policy() {
	try {
		if (jQuery("#privacyYn:checked").length === 0) { // 필수 이용 동의
			alert("필수·자동 항목 수집·이용에 동의하지 않으셨습니다.");
			jQuery("#privacyYn").focus();
			return false;
		}

		if (yh.optPrivacyCols && jQuery("#optPrivacyYn:checked").length === 0) { // 선택 이용 동의. 정보를 입력한 경우에만 동의를 받음.
			var $col = null;
			for (var i in yh.optPrivacyCols) {
				$col = jQuery("[name='" + yh.optPrivacyCols[i] + "']");
				if ($col.val()) {
					alert("선택 항목 수집·이용에 동의하지 않으셨습니다.\n입력하신 값을 공란으로 하시거나 이용에 동의해 주세요.");
					$col.focus();
					return false;
				}
			}
		}

		return true;
	} catch (e) {
		alert("검증 중 오류가 발생했습니다.");
		return false;
	}
}

function validator(formSelector) {
	try {
		formSelector = formSelector ? formSelector + " " : "";
		var fieldName = null;
		var validated = true;
		var maxFileSize = 104857600;
		
		if (navigator.appName.indexOf("Internet Explorer") != -1 && navigator.appVersion.indexOf("MSIE 9") != -1) { // ie9 예외 처리
			// ignore
		} else if ($("[type=file]").length > 1) { // file size checker [2019.07.24] 수정
			var inputTagList = $("[type=file]");
			var intputTag = null;
			
			for (var i = 1; i < inputTagList.length; i++) {
				intputTag = inputTagList[i];
				if (intputTag && intputTag.files[0] && maxFileSize < intputTag.files[0].size && intputTag.files[0].type.indexOf('video') < 0) {
					alert("최대 100MB까지 첨부 가능합니다. \n사이즈 위반 파일: " + intputTag.files[0].name);
					return false;
				}
			}
		}

		$("#updateFileList > ul > li").each(function (index) { // 수정화면에서 설명글 확인
			var fileName = $(this).find("span").text().toLowerCase().trim();
			if (fileName.match(/\.(gif|jpg|jpeg|png)$/i)) {
				if ($(this).find("div").find("input").val() == "") {
					alert("이미지 파일을 첨부할 경우 설명글은 필수로 입력해야합니다.");
					$(this).find("div").find("input").focus();
					validated = false;
					return false;
				} else if ($(this).find("div").find("input").val() != "" && $(this).find("div").find("input").val() != undefined && $(this).find("div").find("input").val().length < 10) {
					alert("웹접근성 준수를 위해 이미지 설명은 10자 이상 입력해야합니다.");
					$(this).find("div").find("input").focus();
					validated = false;
					return false;
				}
			}
		});

		$("#egovComFileList > li").each(function (index) { // 이미지 파일일 경우 설명글 확인
			var fileName = $(this).find("span").text().toLowerCase().trim();
			if (fileName.match(/\.(gif|jpg|jpeg|png)$/i)) {
				if ($(this).find("div").find("input").val() == "") {
					alert("이미지 파일을 첨부할 경우 설명글은 필수로 입력해야합니다.");
					$(this).find("div").find("input").focus();
					validated = false;
					return false;
				} else if ($(this).find("div").find("input").val() != "" && $(this).find("div").find("input").val() != undefined && $(this).find("div").find("input").val().length < 10) {
					alert("웹접근성 준수를 위해 이미지 설명은 10자 이상 입력해야합니다.");
					$(this).find("div").find("input").focus();
					validated = false;
					return false;
				}
			}
		});

		jQuery(formSelector + "[" + attr_key.validator + "=on]").not("[type=radio], [type=checkbox]").each(function (index, element) {
			fieldName = jQuery(this).attr(attr_key.fieldName)
				? jQuery(this).attr(attr_key.fieldName) + "은(는) "
				: "";

			if (maxlengthChecker(jQuery(this))) {
				alert(fieldName + jQuery(this).attr("maxlength") + "byte 이하로 입력 가능합니다.");
				jQuery(this).focus();
				validated = false;
				return false;
			}

			if (minLengthChecker(jQuery(this))) {
				alert(fieldName + "최소 " + jQuery(this).attr(attr_key.minLength) + "자 이상 입력하셔야 합니다.");
				jQuery(this).focus();
				validated = false;
				return false;
			}

			if (requiredValueChecker(jQuery(this))) {
				alert(fieldName + "필수 항목입니다.");
				jQuery(this).focus();
				validated = false;
				return false;
			}

			if (elementInputTypeChecker(jQuery(this))) {
				alert(fieldName + figureOutInputType(jQuery(this)));
				jQuery(this).focus();
				validated = false;
				return false;
			}

			if (isHoliday(jQuery(this))) {
				alert(fieldName + "주말을 입력하실 수 없습니다.");
				jQuery(this).focus();
				validated = false;
				return false;
			}

			if (minValueChecker(jQuery(this))) {
				alert(fieldName + "최소 " + jQuery(this).attr(attr_key.min) + " 이상의 값만 입력 가능합니다.");
				jQuery(this).focus();
				validated = false;
				return false;
			}

			if (maxValueChecker(jQuery(this))) {
				alert(fieldName + "최대 " + jQuery(this).attr(attr_key.max) + " 이하의 값만 입력 가능합니다.");
				jQuery(this).focus();
				validated = false;
				return false;
			}

			if (fileExtensionChecker(jQuery(this))) {
				alert(fieldName + figureOutInputType(jQuery(this)));
				jQuery(this).focus();
				validated = false;
				return false;
			}

			if (isNotStartWith(jQuery(this))) {
				alert(fieldName + "은 " + jQuery(this).data("startwith").replace(/,/g, " 또는 ") + "(으)로 시작해야 합니다.");
				jQuery(this).focus();
				validated = false;
				return false;
			}


			return validated;
		});
		
		//문자서비스 
		if($("input:radio[name=smsYN]:checked").val()=='Y'){
			
			if($("input:checkbox[name=smsOptions]").is(":checked") == false){
				alert("공급용도를 선택해주세요.");
				validated = false;
				return false;
			}
			if($("#smsSubject").val() == ""){
				alert("문자메시지 제목을 입력하세요.");
				validated = false;
				return false;
			}
			if($("#smsMsg").val()==""){
				alert("문자메시지 내용을 입력하세요.");
				validated = false;
				return false;
			}
			if($("#tel1").val() == ""){
				alert("전화번호 첫번호를 입력하세요.");
				validated = false;
				return false;
			}
			if($("#tel2").val() == ""){
				alert("전화번호 중간번호를 입력하세요.");
				validated = false;
				return false;
			}
			if($("#tel3").val() == ""){
				alert("전화번호 끝번호를 입력하세요.");
				validated = false;
				return false;
			}

			alert("문자데이터 처리로 시간이 걸릴 수 있습니다. 잠시만 기다려주세요.");

			//발신 전화번호
			var tel1 = $("#tel1").val();
			var tel2 = $("#tel2").val();
			var tel3 = $("#tel3").val();
			var telNum =  tel1 + tel2 + tel3;
			
			
			$("#trCallback").val(telNum);
			
			if($("#bTermSdate").val()==""){
				if (confirm("글 등록시, 문자메시지가 발송됩니다. 문자메시지를 발송하시겠습니까?")) {
					return true;
				}
			}else{
				if (confirm("게시기간 등록 시, 문자메시지도 게시기간에 발송됩니다. 문자메시지를 발송하시겠습니까?")) {
					return true;
				}
			}
		}

		if (validated) {
			jQuery(formSelector + "[" + attr_key.validator + "=on]").filter("[type=radio],[type=checkbox]").each(function (index, element) {
				fieldName = jQuery(this).attr(attr_key.fieldName) ? jQuery(this).attr(attr_key.fieldName) + "을(를) " : "";

				if (isRequiredButNotChecked(formSelector, jQuery(this))) {
					alert(fieldName + "선택해주세요.");
					jQuery(this).focus();
					validated = false;
					return false;
				}
			});
		}

		return validated;
	} catch (e) {
		alert("검증 중 오류가 발생했습니다.");
		return false;
	}
}

function isNotStartWith(_$) {
	if (_$.val() && _$.data("startwith")) {
		var patterns = _$.data("startwith").split(",");
		for (var i = 0; i < patterns.length; i++) {
			if (_$.val().indexOf(patterns[i]) == 0) {
				return false;
			}
		}
		return true;
	}
	return false;
}

function isRequiredButNotChecked(formSelector, selector) {
	var name = selector.attr("name");
	if (selector.attr(attr_key.required) && selector.attr(attr_key.required).toLowerCase() == "y") { // required
		if (jQuery(formSelector + "[name='" + name + "']:checked").length == 0) { // not checked
			return true;
		}
	}

	return false;
}

function figureOutInputType(selector) {
	var inputType = selector.attr(attr_key.inputType);
	if (inputType == "num") {
		return "숫자만 입력하실 수 있습니다.";
	} else if (inputType == "float") {
		return selector.attr(attr_key.digit) + "자 이내의 숫자만 입력하실 수 있습니다.";
	} else if (inputType == "phone") {
		return "전화번호 양식에 맞지 않습니다.";
	} else if (inputType == "file") {
		return "정해진 형식의 파일만 업로드하실 수 있습니다.";
	} else if (inputType == "alpNum") {
		return "영숫자만 입력하실 수 있습니다.";
	} else if (inputType == "date") {
		return "날짜 형식으로만 입력하실 수 있습니다.";
	} else if (inputType == "time") {
		return "시간 형식으로만 입력하실 수 있습니다.";
	} else if (inputType == "email") {
		return "이메일 형식으로만 입력하실 수 있습니다.";
	} else if (inputType == "birth") {
		return "6자리 숫자로만 입력하실 수 있습니다.";
	} else {
		return "부적절한 입력 양식입니다.";
	}
}

function isHoliday(selector) {
	if (selector.attr(attr_key.notHoliday) == "y" && selector.val()) {
		var day = new Date(selector.val());
		if (day.getDay() == 0 || day.getDay() == 6) {
			return true;
		}
	}

	return false;
}

function maxlengthChecker(selector) {
	if (selector.attr("maxlength")) {
		var byteLength, i, str, chara;
		str = selector.val();
		for (byteLength = i = 0; chara = str.charCodeAt(i++); byteLength += chara >> 11 ? 3 : chara >> 7 ? 2 : 1);
		if (byteLength > selector.attr("maxlength")) {
			return true;
		}
	}
	return false;
}

function minLengthChecker(selector) {
	return selector.attr(attr_key.minLength) && (selector.val().length != 0 && selector.val().length < selector.attr(attr_key.minLength));
}

function requiredValueChecker(selector) {
	return selector.attr(attr_key.required) && selector.attr(attr_key.required).toLowerCase() == "y" && !jQuery.trim(selector.val());
}

function elementInputTypeChecker(selector) {
	if (selector.val()) {
		var inputType = selector.attr(attr_key.inputType);
		if (inputType == "num") { // 숫자 패턴
			return !(/^[0-9]+$/.test(selector.val()));
		} else if (inputType == "phone") { // 휴대폰 번호 패턴
			return !(/^[0-9]{2,4}[0-9]{3,4}[0-9]{4}$/.test(selector.val()));
		} else if (inputType == "float") {
//			/(?=\.(^[0-9]{1,3}$)|(^[0-9]{1,3}\.[0-9]+$))/.test("3")
			var regexp1 = new RegExp("^[0-9]{1," + selector.attr(attr_key.digit) + "}\\.[0-9]+$");
			var regexp2 = new RegExp("^[0-9]{1," + selector.attr(attr_key.digit) + "}$");
			return !(regexp1.test(selector.val()) || regexp2.test(selector.val()));
		} else if (inputType == "alpNum") {
			return !(/^[0-9a-zA-Z_]+$/.test(selector.val()));
		} else if (inputType == "date") {
			return !(/[0-9]{4}-[0-9]{2}-[0-9]{2}$/.test(selector.val()));
		} else if (inputType == "time") {
			return !(/([0-1]{1}[0-9]{1}|[2]{1}[0-3]{1}):[0-5]{1}[0-9]{1}$/.test(selector.val()));
		} else if (inputType == "email") {
			return !(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(selector.val()));
		} else if (inputType == "birth") {
			if (selector.val().length < 7) {
				return !(/([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/g.test(selector.val()));
			} else {
				return true;
			}
		}
	}

	return false;
}

function minValueChecker(selector) {
	return selector.attr(attr_key.inputType) == "num" && selector.attr(attr_key.min) && selector.val() < selector.attr(attr_key.min);
}

function maxValueChecker(selector) {
	return selector.attr(attr_key.inputType) == "num" && selector.attr(attr_key.max) && selector.val() > selector.attr(attr_key.max);
}

function fileExtensionChecker(selector) {
	if (selector.attr(attr_key.inputType.toLowerCase()) == "file") {
		var regexp = /.*(bmp|jpeg|jpg|gif|png)$/; // default
		if (selector.attr(attr_key.fileType) == "movie") {
			regexp = /.*(mp4|avi|mov|mpeg|mpg|wmv|rm)$/;
		} else if (selector.attr(attr_key.fileType) == "doc") {
			regexp = /.*(doc|docx|xls|xlsx|pdf|hwp)$/;
		}
		var fileNames = [];
		var resultUlId = selector.attr(attr_key.ulId) ? selector.attr(attr_key.ulId) : "egovComFileList";
		jQuery("#egovComFileList").children().each(function (index, element) {
//			if (jQuery(this).parent().parent().parent().attr("id") == "egovComFileList") {
//				var text = navigator.userAgent.indexOf("MSIE") > 0
//						? jQuery(this).parent().parent().contents().get(0).nodeValue
//						: jQuery(this).parent().parent().contents().get(0).wholeText;
			var text = jQuery(this).find("span").text();
			fileNames.push(text);
//			}
		});

		for (var i in fileNames) {
			if (fileNames[i] && !regexp.test(fileNames[i].toLowerCase())) {
				return true;
			}
		}
	}

	return false;
}

function pw_validator(selector, retypeSelector) {
	var hit = 0;
	if ($(selector).val()) {
		if (new RegExp(validation_regxp.pw_special_char).exec($(selector).val())) {
			hit++;
		}
		if (new RegExp(validation_regxp.pw_alpha).exec($(selector).val())) {
			hit++;
		}
		if (new RegExp(validation_regxp.pw_number).exec($(selector).val())) {
			hit++;
		}
	}

	if (hit != 3 || $(selector).val().length < 9) {
		alert("비밀번호는 숫자, 영문자, 특수문자를 포함하는 9자 이상의 문자열이어야만 합니다.");
		$(selector).focus();
		return false;
	}

	if ($(selector).val() != $(retypeSelector).val()) {
		alert("재입력된 비밀번호가 일치하지 않습니다.");
		$(retypeSelector).focus();
		return false;
	}

	return true;
}
