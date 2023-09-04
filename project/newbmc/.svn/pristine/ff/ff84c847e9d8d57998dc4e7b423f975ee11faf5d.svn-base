/* 목록 화면 function */
function fn_egov_selectList(frm) {
	frm.bmode.value = "list";
	frm.submit();
}

function fn_egov_searchSelectList(bmode) {
	var frm = document.getElementById("searchForm");
	if(bmode == 'init') {
		frm.searchKeyword.value = '';
	}
	frm.pageIndex.value = 1;
	frm.submit();
}


/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	var frm = document.getElementById("listForm");
	frm.bmode.value = "list";
	frm.pageIndex.value = pageNo;
	frm.submit();
}

/* 글 내용 화면 function */
function fn_egov_select(frm, userBoardSeq) {
	frm.userBoardSeq.value = userBoardSeq;
	frm.bmode.value = "detail";
	frm.submit();
}	

/* 등록 화면 function */
function fn_egov_addView(frm) {
	frm.bmode.value = "add";
	frm.submit();
}

/* 수정 화면  function */
function fn_egov_updateView(frm) {
	frm.bmode.value = "modify";
	frm.submit();
}  

/* 등록 function */
function fn_egov_add(frm, parentUrl) {
	if(!formCheck(frm)){
		return false;
	}
	
	if(window.G_CONTEXT_PATH != null) {
		frm.action = G_CONTEXT_PATH + "/UserBoard/addUserBoard.do?parentUrl=" + parentUrl;
	} else {
		frm.action = "/UserBoard/addUserBoard.do?parentUrl=" + parentUrl;
	}
	frm.bmode.value = "detail";
	frm.submit();
}

/* 수정 function */
function fn_egov_update(frm, parentUrl) {
	if(!formCheck(frm)){
		return false;
	}
	
	if(window.G_CONTEXT_PATH != null) {
		frm.action = G_CONTEXT_PATH + "/UserBoard/updateUserBoard.do?parentUrl=" + parentUrl;
	} else {
		frm.action = "/UserBoard/updateUserBoard.do?parentUrl=" + parentUrl;
	}
	frm.bmode.value = "detail";
		frm.submit();
}

/* 삭제 function */
function fn_egov_delete(frm, parentUrl) {
	if(confirm(fn_common_message('MSG_DELETE'))){
		if(window.G_CONTEXT_PATH != null) {
			frm.action = G_CONTEXT_PATH + "/UserBoard/deleteUserBoard.do?parentUrl=" + parentUrl;
		} else {
			frm.action = "/UserBoard/deleteUserBoard.do?parentUrl=" + parentUrl;
		}
		frm.submit();
	}
}

function formCheck(form){
	if(form.userBoardSubject.value == '') {
		alert('제목을 입력하세요.');
		form.userBoardSubject.focus();
		return false;
	}
	if(form.userBoardContents.value == '') {
		alert('내용을 입력하세요.');
		form.userBoardContents.focus();
		return false;
	}
	return true;
}