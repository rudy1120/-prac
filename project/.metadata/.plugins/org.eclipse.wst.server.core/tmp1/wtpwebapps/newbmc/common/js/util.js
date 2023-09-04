/**
 * util.js
 *
 * @author taeseong
 * @since 2017.04.12
 */

/**
 * target에 start부터 end까지의 option을 생성한다. (end가 now 일 경우 현재 년도까지)
 *
 * @param target
 * @param start
 * @param end
 * @param value
 */
var dateBox = function(target, start, end, value) {
	if (target) {
		if (end == "now") {
			end = new Date().getFullYear();
		}
		for (var i = start; i <= end; i++) {
			$(target).append(
					"<option value=\"" + i + "\" " + (i == value ? "selected=\"selected\"" : "") + ">" + i + "</option>");
		}
	} else {
		console.log(":: dateBox target is null");
	}
};

/**
 * replaceAll 함수
 *
 * @param org
 * @param dest
 * @returns
 */
String.prototype.replaceAll = function(org, dest) {
	return this.split(org).join(dest);
}

/**
 * 문자열이 숫자형인지의 여부를 반환한다.
 *
 * @param 추가 허용할 문자
 * @return 숫자형여부
 */
String.prototype.isNum = function(exceptChar) {
	return (/^[0-9]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열을 숫자형으로 캐스팅한다.
 *
 * @return 캐스팅된 숫자
 */
String.prototype.toNum = function() {
	if (this.isNum()) {
		return Number(this);
	} else {
		return -1;
	}
}

/**
 * 문자열 좌우 공백을 제거한다.
 *
 * @return 좌우 공백 제거된 문자열
 */
String.prototype.trim = function() {
	return this.replace(/^\s+/g, '').replace(/\s+$/g, '');
}

/**
 * 문자열 좌 공백을 제거한다.
 *
 * @return 좌 공백 제거된 문자열
 */
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/, "");
}

/**
 * 문자열 우 공백을 제거한다.
 *
 * @return 우 공백 제거된 문자열
 */
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/, "");
}

/**
 * 문자열을 거꾸로 치환한다.
 *
 * @return 거꾸로 치환된 문자열
 */
String.prototype.reverse = function() {
	var result = '';

	for (var i = this.length - 1; i > -1; i--) {
		result += this.substring(i, i + 1);
	}
	return result;
}

/**
 * 지정한 길이만큼 원본 문자열 왼쪽에 패딩문자열을 채운다.
 *
 * @param 채울 길이
 * @param 채울 문자열
 * @return 채워진 문자열
 */
String.prototype.lpad = function(len, padStr) {
	var result = '';
	var loop = Number(len) - this.length;
	for (var i = 0; i < loop; i++) {
		result += padStr.toString();
	}
	return result + this;
}

/**
 * 지정한 길이만큼 원본 문자열 오른쪽에 패딩문자열을 채운다.
 *
 * @param 채울 길이
 * @param 채울 문자열
 * @return 채워진 문자열
 */
String.prototype.rpad = function(len, padStr) {

	var result = '';
	var loop = Number(len) - this.length;
	for (var i = 0; i < loop; i++) {
		result += padStr.toString();
	}
	return this + result;
}

/**
 * 문자열이 공백이나 널인지의 여부를 반환한다.
 *
 * @return 공백이나 널인지의 여부
 */
String.prototype.isBlank = function() {
	var str = this.trim();
	for (var i = 0; i < str.length; i++) {
		if ((str.charAt(i) != "\t") && (str.charAt(i) != "\n")
				&& (str.charAt(i) != "\r")) {
			return false;
		}
	}
	return true;
}

/**
 * 문자열이 영어만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param exceptChar -
 *            추가 허용할 문자
 * @return 영어만으로 구성되어 있는지의 여부
 */
String.prototype.isEng = function(exceptChar) {
	return (/^[a-zA-Z]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열이 숫자와 영어만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param 추가 허용할 문자
 * @return 숫자와 영어만으로 구성되어 있는지의 여부
 */
String.prototype.isEngNum = function(exceptChar) {
	return (/^[0-9a-zA-Z]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열이 한글만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param 추가 허용할 문자
 * @return 한글만으로 구성되어 있는지의 여부
 */
String.prototype.isKor = function(exceptChar) {
	return (/^[가-힣]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열이 숫자와 한글만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param 추가 허용할 문자
 * @return 숫자와 한글만으로 구성되어 있는지의 여부
 */
String.prototype.isKorNum = function(exceptChar) {
	return (/^[0-9가-힣]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열이 영문과 한글만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param 추가 허용할 문자
 * @return 영문과 한글만으로 구성되어 있는지의 여부
 */
String.prototype.isEngKor = function(exceptChar) {
	return (/^[a-zA-Z가-힣]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 이메일 주소의 유효성 여부를 반환한다.
 *
 * @return 유효성 여부
 */
String.prototype.isEmail = function() {
	return (/\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/).test(this.trim());
}

/**
 * 전화번호의 유효성 여부를 반환한다.
 *
 * @param 구분자(default : '-')
 * @return 유효성 여부
 */
String.prototype.isPhone = function(dlm) {
	var arg = dlm != null && typeof (dlm) != 'undefined' && dlm.neq('') ? dlm
			: '-';
	return eval("(/(02|0[3-9]{1}[0-9]{1})" + arg + "[1-9]{1}[0-9]{2,3}" + arg
			+ "[0-9]{4}$/).test(this)");
}

/**
 * 휴대폰번호 유효성 여부를 반환한다.
 *
 * @param 구분자(default : '-')
 * @return 유효성 여부
 */
String.prototype.isMobile = function(dlm) {
	var arg = dlm != null && typeof (dlm) != 'undefined' && dlm.neq('') ? dlm
			: '-';
	return eval("(/01[016789]" + arg + "[1-9]{1}[0-9]{2,3}" + arg
			+ "[0-9]{4}$/).test(this)");
}

/**
 * 정규식에 쓰이는 특수문자를 찾아서 escape하여 반환한다.
 *
 * @return 특수문자가 escape된 문자열
 */
String.prototype.remove = function(pattern) {
	return (pattern == null) ? this : eval("this.replace(/[" + pattern.meta()
			+ "]/g, \"\")");
}



