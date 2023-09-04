$(function(){
    //메인 스크립트

	cookiedata = document.cookie;
	
/*	if(cookiedata.indexOf("popup_cookie10=done")<0){
		var imageWin;
		imageWin = window.open("", "", "top=" + 50 + ",left=" + 250 + ",width=" + 450 + ",height=" + 827 +",scrollbars=yes,resizable=yes,status=no"); 
		imageWin.document.write("<html><title>popup</title><body style='margin:0;' >"); 
		imageWin.document.write("<div><img src='./img/main/0830_popup.png' onclick='window.close();' style='cursor:pointer;' alt='부산도시공사의 처렴한 계약거래를 위한 4가지 제도 1. 청렴 체크리스트 제도 시행. 청렴도 및 부패위험도를 점검하기 위한 담당자 자가진단 체크리스트 시행 2. 청렴 방명록 작성 담당자의 투명한 업무처리를 담보하여 고객만족도 제고를 위한 계약자 청렴방명록 작성 3. 출자토지 체크리스트 제도 시행 출자토지 업무 시 공정한 업무추진을 위해 담당자의 청렴수준을 방문고객이 직접 측정 4. 개인정보제공 동의서 작성  개인정보 수집목적을 명확하게 하여 해당 목적에만 활용할 것을 정보제공자에게 인지 .  부패방지 수칙4! 1. 부패방지 법규를 준수한다! 2.부패방지 목표를 수립하고, 이해한다! 3.ISO37001(부패방지경영시스템)을 준수, 실천한다! 4. 부패방지 방침문은 사무실 또는 홈페이지에 게시한다!'/></div>")
		imageWin.document.write("<p style='text-align:right; margin:0;background: #333;padding: 0px 10px 0px 10px;font-size: 13px;font-weight: 300; line-height: 25px;'><a href='javascript:window.opener.todaycloseWin(1); window.close();' style='background: #666;color: #f2f2f2;border-radius: 3px;padding: 0px 10px;'>24시간동안 보지않기</a></p>");
		imageWin.document.write("</body></html>");	
	}*/
/*	if(cookiedata.indexOf("popup_cookie2=done")<0){
		var imageWin;
		imageWin = window.open("", "", "top=" + 100 + ",left=" + 100 + ",width=" + 588 + ",height=" + 520 +",scrollbars=yes,resizable=yes,status=no"); 
		imageWin.document.write("<html><title>popup</title><body style='margin:0;' >"); 
		imageWin.document.write("<div><img src='./images/main/0531_popup.png' onclick='window.opener.jump(2); window.close();' style='cursor:pointer;'/></div>")
		imageWin.document.write("<p style='text-align:right; margin:0;background: #333;padding: 0px 10px 0px 10px;font-size: 13px;font-weight: 300; line-height: 25px;'><a href='javascript:window.opener.todaycloseWin(2); window.close();' style='background: #666;color: #f2f2f2;border-radius: 3px;padding: 0px 10px;'>24시간동안 보지않기</a></p>");
		imageWin.document.write("</body></html>");	
	}*/
	
	/*
	if(cookiedata.indexOf("popup_cookie3=done")<0){
		var imageWin;
		imageWin = window.open("", "", "top=" + 200 + ",left=" + 200 + ",width=" + 588 + ",height=" + 520 +",scrollbars=yes,resizable=yes,status=no"); 
		imageWin.document.write("<html><title>popup</title><body style='margin:0;' >"); 
		imageWin.document.write("<div><img src='./images/main/0907_popup2.jpg' onclick='window.opener.jump(3); window.close();' style='cursor:pointer;' alt='일광신도시 또는 일광모습 어린이 그림그리기 공모전 시행. 공모주제는 일광신도시 또는 일광모습 그림 그리기. 접수기간은 2020년 9월 7일 월요일 부터 9월 30일 수요일 까지 . 공모대상 일광초등학교 학생누구나 가능'/></div>")
		imageWin.document.write("<p style='text-align:right; margin:0;background: #333;padding: 0px 10px 0px 10px;font-size: 13px;font-weight: 300; line-height: 25px;'><a href='javascript:window.opener.todaycloseWin(3); window.close();' style='background: #666;color: #f2f2f2;border-radius: 3px;padding: 0px 10px;'>24시간동안 보지않기</a></p>");
		imageWin.document.write("</body></html>");	
	}
	*/

	
	/*if(cookiedata.indexOf("popup_cookie2=done")<0){
		var imageWin;
		imageWin = window.open("", "", "top=" + 200 + ",left=" + 200 + ",width=" + 588 + ",height=" + 520 +",scrollbars=yes,resizable=yes,status=no"); 
		imageWin.document.write("<html><title>popup</title><body style='margin:0;' >"); 
		imageWin.document.write("<div><img src='./images/main/1006-popup.jpg' onclick='window.opener.jump(2); window.close();' style='cursor:pointer;' alt= ' 더 나은 서비스로 더 가까이 고객에게 다가가고자 고객서비스헌장 개정(안) 의견 수렴'/></div>")
		imageWin.document.write("<p style='text-align:right; margin:0;background: #333;padding: 0px 10px 0px 10px;font-size: 13px;font-weight: 300; line-height: 25px;'><a href='javascript:window.opener.todaycloseWin(2); window.close();' style='background: #666;color: #f2f2f2;border-radius: 3px;padding: 0px 10px;'>24시간동안 보지않기</a></p>");
		imageWin.document.write("</body></html>");	
	}*/

   





    
  
    
    //웹 접근성 탭키로 동작가능
    $(".noticeTab").find("button").on("focus", function(){
        $(this).trigger("click");
    });


 
});

function jump(num){
	
//	imageWin.close();
	if(num ==1){
		window.open("");
	}
	
	/*else if(num == 2){
		window.open("https://www.bmc.busan.kr/bmc/bbs/view.do?bIdx=704608&ptIdx=788&mId=0707000000");
	}*/
	/*else if(num ==4){
		window.open("https://www.bmc.busan.kr/bmc/bbs/view.do?bIdx=704323&ptIdx=788&mId=0707000000");
	}*/
}

//팝업
function Pop_close(num){
	$("#pop"+num).hide();
}

function todaycloseWin(num){

	if(num ==10){
		setCookie("popup_cookie10","done",1);
	}
	/*else if(num ==2){
		setCookie("popup_cookie2","done",1);
	}*/
	/*else if(num ==4){
		setCookie("popup_cookie4","done",1);
	}*/
	
	//$("#pop1").hide();
}
/*function setCookie(c_name,value,exdays)
{
	var exdate=new Date();
	exdate.setDate (todayDate.getDate() + exdays);
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + exdate.toGMTString() + ";";

	document.cookie=c_name + "=" + c_value;
}
*/
function setCookie( name, value, expire ) {   

	var todayValue = new Date();

	todayValue.setDate(todayValue.getDate() + expire);
	document.cookie = name + "=" + encodeURI(value) + "; expires=" + todayValue.toGMTString() + "; path=/;";

}


