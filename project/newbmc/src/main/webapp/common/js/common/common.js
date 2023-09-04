
function goSite(obj, frm) {
	frm.action = obj.value;
	frm.submit();
}
//검색엔진
function searchEngine() {
	var speachText = document.getElementById("qt");
          if( speachText.value == '' ) {
			 alert("검색어를 입력해주세요.");
			 speachTex.focus();
            return false;
          }


          return true;
}


//layout 팝업 컨트롤
function LayoutShow(showpop) {
	var objDiv = document.getElementById(showpop);

	if(objDiv.style.display=="block"){
		objDiv.style.display = "none";
	}else{
		objDiv.style.display = "block";
	}
}

//20151118 J.Ryeon Lee 다른 항목이 선택되면 닫히도록 수정 하단사용
function footerShow(showpop) {
	jQuery("#footer_link_box").children().each(function() {
		if (jQuery(this).children().filter("div").first().attr("id") == showpop) {
			if (jQuery(this).children().filter("div").first().css("display") == "none") {
				jQuery(this).children().filter("div").first().show();
			} else {
				jQuery(this).children().filter("div").first().hide();
			}
		} else {
			jQuery(this).children().filter("div").first().hide();
		}
	});
}


function goPopup(url, w, h, l, t,ScrollbarsYn, name){

	if (ScrollbarsYn=='Y')		{
		ScrollbarsYn="yes";
	}else{
		ScrollbarsYn="no";
	}
	var x = (screen.availWidth - w) / 2;
	var y = (screen.availHeight - h) / 2;
	window.open(url, name,'width='+w+', height='+h+', left='+x+', top='+y+', location=no ,scrollbars='+ScrollbarsYn);
}


function goPopup2(url, w, h, l, t,ScrollbarsYn, resizable, name){

	if (ScrollbarsYn=='Y')		{
		ScrollbarsYn="yes";
	}else{
		ScrollbarsYn="no";
	}
	var x = (screen.availWidth - w) / 2;
	var y = (screen.availHeight - h) / 2;
	window.open(url, name,'width='+w+', height='+h+', left='+x+', top='+y+', resizable='+resizable+', location=no ,scrollbars='+ScrollbarsYn);
}




// 포탈 메인 시계 표시
var dayNames = new Array("(일)","(월)","(화)","(수)","(목)","(금)","(토)");
function clock(dobj, tobj)
{
	dt = new Date();
	// 시간과 관련된 정보를 저장한다.
	hours = dt.getHours();
	minutes = dt.getMinutes();
	seconds = dt.getSeconds();
	timeStr = hours;
	timeStr += ((minutes < 10) ? ":0" : ":") + minutes;
	timeStr += ((seconds < 10) ? ":0" : ":") + seconds;
	// 폼의 시간을 표시하는 입력란에 문자열을 출력한다.
	jQuery("#"+tobj).text(timeStr);
	// 일자와 관련된 정보를 저장한다.
	months = dt.getMonth() + 1;
	days = dt.getDate();
	years = dt.getFullYear();
	dateStr = years + "년 ";
	dateStr += ((months < 10) ? "0" : "") + months + "월 ";
	dateStr += ((days < 10) ? "0" : "") + days + "일" + dayNames[dt.getDay()];

	// 폼의 일자를 표시하는 입력란에 문자열을 출력한다.
	jQuery("#"+dobj).text(dateStr);
	// 1초마다 일자와 시간을 갱신한다.
	Timer = setTimeout("clock('"+dobj+"','"+tobj+"')", 1000);
}

function goFileOld(file, path) {
	//window.open('/common/file_down.jsp?path=/portal/download' + path + "/&filename=" + file);
	window.open('/download' + path + "/" + encodeURIComponent(file));
	//encodeURI(file)
}


function goFile(file, path) {
	//window.open('/common/file_down.jsp?path=/portal/download' + path + "/&filename=" + file);
	window.open('/common/jsp/common/contentFileDownload.jsp?path=' + path + "&file=" + encodeURIComponent(file));
	//encodeURI(file)
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


function openWindow(url,lecName) {
	window.open(url,lecName,"width=800,height=600,resizable=yes,menubar=no,scrollbars=yes");
}

function openWindow2(url,lecName) {
	window.open(url,lecName,"width=1014,height=650,resizable=yes,menubar=no,scrollbars=yes");
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
