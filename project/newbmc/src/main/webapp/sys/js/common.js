/*
* ======================================
* 파 일 명 : common.js
* 설       명 : 공통 스크립트 관련 javascript
* 작   성  자 : 박준오

* 작   성  일 : 2009.07.16
* ======================================
* 변경자/변경일 : 2013-11-27 김혜민          최초 생성
* 변경사유/내역 :
* ======================================
* 변경자/변경일 :
* 변경사유/내역 :
* ======================================
*/

/**
 * 동영상 플레이를 위한 Embed 객체
 * @param srcFile
 * @return
 */


/**
 * HTML 태그 제거
 * @param string
 * @return
 */
function stripHTMLtag(string) {
   	var objStrip = new RegExp();
   	objStrip = /[<][^>]*[>]/gi;
   	return string.replace(objStrip, "");
}

/**
 * 입력창의 Byte 수 계산
 * @param input_name
 * @param max_byte
 * @return
 */
function input_cal_byte(input_name, max_byte){
    var input_name_str, byte_count=0, input_name_length=0, one_str, ext_byte;

    input_name_str = new String(input_name.value);
    input_name_length = input_name_str.length;

    for(i=0;i<input_name_length;i++){
        one_str=input_name_str.charAt(i);
        if(escape(one_str).length > 4){
            byte_count+=2;
        }else if(one_str != '\r'){
            byte_count++;
        }
    }

    var msgCnt = document.getElementById("msgCnt");
    msgCnt.innerHTML = byte_count + "";

    if(byte_count > max_byte){
        ext_byte = byte_count - max_byte;
        alert('\n문자내용은 '+max_byte+'자 이상 입력하실수 없습니다.\n\n입력하신 내용 중 초과 '+ext_byte+'자는 자동 삭제 됩니다.\n');
        input_cut_text(input_name,max_byte);
        msgCnt.innerHTML = max_byte;
    }
}

/**
 * 입력창 byte대로 자르기
 * @param input_name
 * @param max_byte
 * @return
 */
function input_cut_text(input_name, max_byte){
    var input_name_str, byte_count=0, input_name_length=0, one_str;

    input_name_str = new String(input_name.value);
    input_name_length = input_name_str.length;

    for(i=0;i<input_name_length;i++){
        if(byte_count < max_byte){
            one_str=input_name_str.charAt(i);
            if(escape(one_str).length > 4){
                byte_count+=2;
            }else if(one_str != '\r'){
                byte_count++;
            }
        }else{
            input_name_str = input_name_str.substring(0,i);
            break;
        }
    }

    if((max_byte%2) ==1){
        input_name_length =(input_name_str.length-1);
        if(escape(input_name_str.charAt(input_name_length)).length > 4){
            input_name_str = input_name_str.substring(0,input_name_length);
        }
    }

    input_name.value = input_name_str;
    return input_name_str;
}

/**
 * 파일의 확장자를 가져옮
 * @param filePath
 * @return
 */
function getFileExtension(filePath) {
    var lastIndex = -1;
    lastIndex = filePath.lastIndexOf('.');
    var extension = "";

    if(lastIndex != -1) {
    	extension = filePath.substring( lastIndex+1, filePath.len );
    } else {
    	extension = "";
    }
    return extension;
}

/**
 * 파일을 선택 후 포커스 이동시 호출
 * @param value
 * @return
 */
function uploadImg_Change(value) {
    var src = getFileExtension(value);
    if(src == "") {
        alert("올바른 파일을 입력하세요");
        return;
    } else if(!((src.toLowerCase() == "gif") || (src.toLowerCase() == "jpg") || (src.toLowerCase() == "jpeg"))) {
        alert("gif 와 jpg 파일만 지원합니다.");
        return;
    }
}

/**
 * 숫자만 입력이 가능함. 특수기호('-','.',...) 포함.
 * @return
 */
function onlyNumber() {
	if((event.keyCode > 31) && (event.keyCode < 45) || (event.keyCode > 57)) {
		event.returnValue = false;
	}
}

/**
 * 오직 숫자만 입력이 가능함.
 * @param loc
 * @return
 */
function onlyNumber2(loc) {
	if(/[^0123456789]/g.test(loc.value)) {
		alert("숫자 이외의 문자는 입력할 수 없습니다.");
		loc.value = "";
		loc.focus();
	}
}

/**
 * 공백 제거
 * @param s
 * @return
 */
function trim(s) {
	s += ''; // 숫자라도 문자열로 변환
	return s.replace(/^\s*|\s*$/g, '');
}

/**
 * 숫자만 입력받기
 * @param e
 * @param decimal
 * @return
 */
function numbersonly(e, decimal) {
    var key;
    var keychar;

    if (window.event) {
        key = window.event.keyCode;
    } else if (e) {
        key = e.which;
    } else {
        return true;
    }
    keychar = String.fromCharCode(key);

    if ((key == null) || (key == 0) || (key == 8) || (key == 9) || (key == 13)
            || (key == 27)) {
        return true;
    } else if ((("0123456789").indexOf(keychar) > -1)) {
        return true;
    } else if (decimal && (keychar == ".")) {
        return true;
    } else
        return false;
}

/**
  * 숫자만 입력받기
  * @param e
  * @param decimal
  * @return
  */
function numbersonly2(e, decimal) {
	var key;
    var keychar;

    if (window.event) {
        key = window.event.keyCode;
    } else if (e) {
        key = e.which;
    } else {
        return true;
    }
    keychar = String.fromCharCode(key);

    if ((key == null) || (key == 0) || (key == 8) || (key == 9) || (key == 13)
            || (key == 27)) {
        return true;
    } else if ((("0123456789").indexOf(keychar) > -1)) {
        return true;
    } else if (decimal && (keychar == "-")) {
        return true;
    } else
        return false;
 }

/**
 * 두 날짜의 차이를 일자로 구한다.(조회 종료일 - 조회 시작일)
 *
 * @param val1 - 조회 시작일(날짜 ex.2002-01-01)
 * @param val2 - 조회 종료일(날짜 ex.2002-01-01)
 * @return 기간에 해당하는 일자
 */
function calDateRange(val1, val2) {
    var FORMAT = "-";

    // FORMAT을 포함한 길이 체크
    if (val1.length != 10 || val2.length != 10)
        return null;

    // FORMAT이 있는지 체크
    if (val1.indexOf(FORMAT) < 0 || val2.indexOf(FORMAT) < 0)
        return null;

    // 년도, 월, 일로 분리
    var start_dt = val1.split(FORMAT);
    var end_dt = val2.split(FORMAT);

    // 월 - 1(자바스크립트는 월이 0부터 시작하기 때문에...)
    // Number()를 이용하여 08, 09월을 10진수로 인식하게 함.
    start_dt[1] = (Number(start_dt[1]) - 1) + "";
    end_dt[1] = (Number(end_dt[1]) - 1) + "";

    var from_dt = new Date(start_dt[0], start_dt[1], start_dt[2]);
    var to_dt = new Date(end_dt[0], end_dt[1], end_dt[2]);

    return (to_dt.getTime() - from_dt.getTime()) / 1000 / 60 / 60 / 24;
}


var tgs = new Array("div","td","tr","p");
var szs = new Array("xx-small","x-small","small","medium","large","x-large","xx-large");
var startSz = 2;
function ts( trgt,inc ) {
	if(!document.getElementById) return;
	var d = document,cEl = null,sz = startSz,i,j,cTags;

	sz += inc;
	if(sz < 0) sz = 0;
	if(sz > 6) sz = 6;
	startSz = sz;

	if(!(cEl = d.getElementById(trgt))) cEl = d.getElementsByTagName(trgt)[0];

	cEl.style.fontSize = szs[sz];

	for(i = 0;i < tgs.length; i++) {
    	 cTags = cEl.getElementsByTagName(tgs[i]);
         for(j = 0;j < cTags.length; j++ ) cTags[j].style.fontSize = szs[sz];
	}
}

/**
 * 글자 크기 조절 함수
 * @param container
 * @return
 */
var setFontEventHandler = function(container) {
	document.getElementById("fontsize-bigger").onclick = function bigger() {
		ts(container,1);
		return false;
	}
	document.getElementById("fontsize-smaller").onclick = function smaller() {
		ts(container,-1);
		return false;
	}
};

/**
 * String을 URLEncode을 이용하여 Encoding한다.
 * @return
 */
String.prototype.URLEncode = function URLEncode() {
	var SAFECHARS = "0123456789" +     // Numeric
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ" + // Alphabetic
			"abcdefghijklmnopqrstuvwxyz" +
			"-_.!~*'()";     // RFC2396 Mark characters
	var HEX = "0123456789ABCDEF";
	var plaintext = this;
	var encoded = "";

	for (var i = 0; i < plaintext.length; i++ ) {
		var ch = plaintext.charAt(i);
		if (ch == " ") {
			encoded += "+";    // x-www-urlencoded, rather than %20
		} else if (SAFECHARS.indexOf(ch) != -1) {
			encoded += ch;
		} else {
			var charCode = ch.charCodeAt(0);
			if (charCode > 255) {
				alert( "Unicode Character '"
					+ ch
					+ "' cannot be encoded using standard URL encoding.\n" +
				      "(URL encoding only supports 8-bit characters.)\n" +
						"A space (+) will be substituted." );
				encoded += "+";
			} else {
				encoded += "%";
 				encoded += HEX.charAt((charCode >> 4) & 0xF);
				encoded += HEX.charAt(charCode & 0xF);
			}
		}
	} // for
	return encoded;
};

/**
 * URLEncode로 넘어온 String을 decoding한다.
 * @return
 */
String.prototype.URLDecode = function URLDecode() {
	var HEXCHARS = "0123456789ABCDEFabcdef";
	var encoded = this;
	var plaintext = "";
	var i = 0;
	while (i < encoded.length) {
		var ch = encoded.charAt(i);
		if (ch == "+") {
			plaintext += " ";
			i++;
		} else if (ch == "%") {
			if (i < (encoded.length-2)
				&& HEXCHARS.indexOf(encoded.charAt(i+1)) != -1
				&& HEXCHARS.indexOf(encoded.charAt(i+2)) != -1 ) {
				plaintext += unescape( encoded.substr(i,3) );
				i += 3;
			} else {
				alert( 'Bad escape combination near ...' + encoded.substr(i) );
				plaintext += "%[ERROR]";
				i++;
			}
		} else {
			plaintext += ch;
			i++;
		}
	} // while
	return plaintext;
};

var old_menu = '';
/**
 * 서브 메뉴 클릭시 하위 메뉴 펼침/접힘
 * @param submenu
 * @return
 */
function menuclick(url) {
	if(url != '') {
		document.location.href = url;
	}
}

var left_var = "off";
/**
 * //좌측메뉴 초기화 셋팅
 * @param total_menu_cnt
 * @param cur_id_num
 * @param cur_sub_id_num
 * @return
 */
function set_left_menu(total_menu_cnt,cur_id_num,cur_sub_id_num) {
	for (var i = 1; i <= total_menu_cnt; i++) {
		if(i<10) division = "menu_sub_0"+i;
		else division = "menu_sub_"+i;

		element = document.getElementById(division);
		if(element != null) {
			if(cur_sub_id_num == "all") {
				if(left_var=="off") element.style.display = "block";
				else {
					if(i == (cur_id_num/10)) {
						element.style.display = "block";
					} else {
						element.style.display = "none";
					}
				}
			} else {
				if(i == (cur_id_num/10)) {
					element.style.display = "block";
				} else {
					element.style.display = "none";
				}
			}
		}
	}

	if(cur_sub_id_num == "all") {
		if(left_var == "off") left_var = "on";
		else left_var = "off";
	}
}

//이미지 처리함수들
//팝업창 - ggompig
function MM_openBrWindow(theURL,winName,features) { //v2.0
window.open(theURL,winName,features);
}

//flash
function GetFlash(file,wid,hei) {
     document.write('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="'+wid+'" height="'+hei+'">');
     document.write('<param name="movie" value="'+file+'">');
     document.write('<param name="quality" value="high">');
		document.write('<param name="wmode" value="transparent">');
     document.write('<embed src="'+file+'" wmode="transparent" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="'+wid+'" height="'+hei+'"></embed></object>');
}


//popup
function win_pop(path, win_name, wi, he, leftm, topm, scr) {
	window.open(path,win_name,'width='+wi+',height='+he+', left='+leftm+', top='+topm+', resizable=no,scrollbars='+scr+', status=no,toolbar=no');
}

function resizeFrame(ifr_id,re){
	//가로길이는 유동적인 경우가 드물기 때문에 주석처리!
	var ifr= document.getElementById(ifr_id) ;
	var innerBody = ifr.contentWindow.document.body;
	var innerHeight = innerBody.scrollHeight + (innerBody.offsetHeight - innerBody.clientHeight);
	//var innerWidth = document.body.scrollWidth + (document.body.offsetWidth - document.body.clientWidth);

	if (ifr.style.height != innerHeight) //주석제거시 다음 구문으로 교체 -> if (ifr.style.height != innerHeight || ifr.style.width != innerWidth)
	{
		ifr.style.height = innerHeight;
	}
	/*else if (innerHeight<600){
		ifr.style.height = 600;
	}*/
}

/* rollover */
function MM_preloadImages() { //v3.0
var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
 var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
 if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
 d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function MM_showHideLayers() { //v6.0
	  var i,p,v,obj,args=MM_showHideLayers.arguments;
	  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
		if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
		obj.visibility=v; }
	}
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
 document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);

function swapImgRestore(curObj, obj, imgUrl) {
	MM_swapImgRestore();
	if(curObj == obj) {
		MM_swapImage(obj,'',imgUrl,1);
	}
}

function setCookie( name, value, expiredays ){
	var today = new Date();
	today.setDate( today.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + today.toGMTString() + ";";
}

//사이트별팝업창 쿠키값 get 스크립트
function getCookie( name ){
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length )
	{
			var y = (x+nameOfCookie.length);
			if ( document.cookie.substring( x, y ) == nameOfCookie ) {
					if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
							endOfCookie = document.cookie.length;
					return unescape( document.cookie.substring( y, endOfCookie ) );
			}
			x = document.cookie.indexOf( " ", x ) + 1;
			if ( x == 0 )
					break;
	}
	return "";
}

//Layer Show Hide 공통 함수
//인자 : on_off 플래그, Layer 아이디
function showHideLayer(on_off, id){
if(on_off=="on"){
	document.getElementById(id).style.visibility="visible";
}
else{
document.getElementById(id).style.visibility="hidden";
}
}

var left_var = "off";
//좌측메뉴 초기화 셋팅
function set_left_menu(total_menu_cnt,cur_id_num,cur_sub_id_num)
{
	for (var i = 1; i <= total_menu_cnt; i++) {
		division = "lnb_"+i+"0_sub";

		element = document.getElementById(division);
		if(element!=null) {
			if(cur_sub_id_num=="all") {
				if(left_var=="off") element.style.display = "block";
				else {
					if((i+"0")==cur_id_num) {
						element.style.display = "block";
					}
					else {
						element.style.display = "none";
					}
				}
			}
			else {
				if(i==cur_id_num) {
					element.style.display = "block";
				}
				else {
					element.style.display = "none";
				}
			}
		}
	}

	if(cur_sub_id_num=="all") {
		if(left_var=="off") left_var = "on";
		else  left_var = "off";
	}
}

function Sub_Menu_Active( sub_id, src, gubun )
{
	var objMenu = document.getElementById(sub_id);

	if(objMenu!=null) {
		if ( objMenu.style.display == '' ) {
			objMenu.style.display = 'none';
		} else {
			objMenu.style.display = '';
		}
	}
}

function left_menu_view(all_left_cnt, cur_left_num)
{
	for (var i = 1; i <= all_left_cnt; i++) {

		division = "lnb_sub_"+i;
		division_bt = "lnb_sub_bt_"+i;

		element = document.getElementById(division);
		element_bt = document.getElementById(division_bt);
		if(element!=null) {
			if(i==cur_left_num) {
				element.style.display = "block";
				element_bt.style.display = "block";
			}
			else {
				element.style.display = "none";
				element_bt.style.display = "none";
			}
		}
	}
}

//서브메뉴(lnb) bg_over

function class_switcher(objId, classes, curId, cur_classes) {

	var obj = document.getElementById(objId);
	if(obj!=null) {
		obj.className = classes;
		if(curId!="")
		{
			var cur_obj = document.getElementById(curId);
			cur_obj.className = cur_classes;
		}
	}
}


/**
 * 실수 반올림
 * @param n
 * @param digits
 * @return
 */
function roundXL(n, digits) {
	if (digits >= 0) return parseFloat(n.toFixed(digits)); // 소수부 반올림

	digits = Math.pow(10, digits); // 정수부 반올림
	var t = Math.round(n * digits) / digits;

	return parseFloat(t.toFixed(0));
}

/**
 * 숫자만 입력받기
 * @param e
 * @param decimal
 * @return
 */
function numbersonly(e, decimal) {
    var key;
    var keychar;

    if (window.event) {
        key = window.event.keyCode;
    } else if (e) {
        key = e.which;
    } else {
        return true;
    }
    keychar = String.fromCharCode(key);

    if ((key == null) || (key == 0) || (key == 8) || (key == 9) || (key == 13)
            || (key == 27)) {
        return true;
    } else if ((("0123456789").indexOf(keychar) > -1)) {
        return true;
    } else if (decimal && (keychar == ".")) {
        return true;
    } else
        return false;
}

/* table tr over */
jQuery(document).ready(function () {
	jQuery('.row_over tr:gt(0)').hover(
	        function () { jQuery(this).addClass('highlight'); },
	        function () { jQuery(this).removeClass('highlight'); }
	    );
});