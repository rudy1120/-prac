/**
 * 첨부파일 관련 함수 모음
 * - 파일삭제 : DWR 활용
 * Made by : silver_star@daum.net
 * Date : 2011.02.09
 */
function fn_egov_downFile(atchFileId, fileSn) {
	jQuery.ajax({ // file download counting
		type : "post",
		url : "/cmm/fms/fileDown/cnt.do",
		data : { "atchFileId" : atchFileId, "fileSn" : fileSn },
	});

	if(window.G_CONTEXT_PATH != null) {
		window.open("/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn);
	} else {
		window.open("/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn);
	}
}

/**
 * 도정컨텐츠관리 양식 다운로드
 * Made by : 엄동건
 * Date : 2014.09.01
 */
function fn_egov_downFile_presentation(fileId, fileName) {

	if(window.G_CONTEXT_PATH != null) {
		window.open(G_CONTEXT_PATH + "/cmm/fms/FileDown_Presentation.do?fileId="+fileId+"&fileName="+fileName+".xlsx");
	} else {
		window.open("/cmm/fms/FileDown_Presentation.do?fileId="+fileId+"&fileName="+fileName+".xlsx");
	}
}


function fn_egov_downFileBySort(atchFileId, sort) {
	if(window.G_CONTEXT_PATH != null) {
		window.open(G_CONTEXT_PATH + "/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&sort="+sort);
	} else {
		window.open("/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&sort="+sort);
	}
}

function fn_egov_deleteFile(atchFileId, fileSn) {

	if (confirm("첨부파일은 복구 불가합니다. 바로 삭제하시겠습니까?")) {
		var obj = {"atchFileId" : atchFileId, "fileSn" : fileSn};
		EgovFileMngDwr.deleteFileInf( obj, function(result) {
			jQuery("#"+atchFileId + "-" + fileSn).remove();
			/*
			var divObj = document.getElementById(atchFileId + "-" + fileSn);
			if(divObj != null) {
				divObj.style.display = "none";
			}
			*/
		});
		// 20151027 J.Ryeon Lee activate input tag
		jQuery("[name^=file_]").removeProp("disabled").prop("disabled", false);
//		jQuery("#egovComFileUploader").parent().children().each(function() {
//			jQuery(this).prop("disabled", false);
//			this.disabled = false;
//		});
		((jQuery("#"+atchFileId+"_btn").parent().next().children().children().length-1) <= 0 ? jQuery("#"+atchFileId+"_btn").remove() : "");
		//(jQuery("#updateFileList > ul > li").length <= 0 ? jQuery("#"+atchFileId+"_btn").remove() : "");
		return true;
	} else {
		return false;
	}
}

function fn_egov_deleteFile_CA(atchFileId, fileSn) {
	if(confirm("첨부파일은 복구 불가합니다. 바로 삭제하시겠습니까?")) {
		var obj = {"atchFileId" : atchFileId, "fileSn" : fileSn};
		EgovFileMngDwr.deleteFileInf( obj, function(result) {
			jQuery("#"+atchFileId + "-" + fileSn).remove();
			/*
			var divObj = document.getElementById(atchFileId + "-" + fileSn);
			if(divObj != null) {
				divObj.style.display = "none";
			}
			*/
		});
		// 20151027 J.Ryeon Lee activate input tag
		jQuery("[name^=file_]").removeProp("disabled").prop("disabled", false);
//		jQuery("#egovComFileUploader").parent().children().each(function() {
//			jQuery(this).prop("disabled", false);
//			this.disabled = false;
//		});

		window.location.reload();

	}
}

function fn_egov_check_file(flag) {
	if (flag=="Y") {
		document.getElementById('file_upload_posbl').style.display = "block";
		document.getElementById('file_upload_imposbl').style.display = "none";
	} else {
		document.getElementById('file_upload_posbl').style.display = "none";
		document.getElementById('file_upload_imposbl').style.display  = "block";
	}
}

/**
 * 첨부파일 순서변경 스크립트 추가
 * 2013.08.06
 * dkymm@sayinfo.co.kr
 * @param fileId
 * @param fieldName
 * @returns {Boolean}
 */
function fn_edosi_file_sort_up(fileId, fieldName) {
	var boxCnt = jQuery('input[id^='+fileId+'_]').length;
	for(var i=0; i<boxCnt; i++) {
		if(jQuery('input[id^='+fileId+'_]').eq(i).is(':checked')) {
			var fileObj = jQuery('div[id^='+fileId+'-]').eq(i).clone();
			jQuery('div[id^='+fileId+'-]').eq(i).remove();
			jQuery('div[id^='+fileId+'-]').eq(i-1).before(fileObj);
		}
	}
	fn_edosi_file_arr(fileId, fieldName);
	return false;
}

function fn_edosi_file_sort_down(fileId, fieldName) {
	var boxCnt = jQuery('input[id^='+fileId+'_]').length;
	for(var i=(boxCnt-1); i>-1; i--) {
		if(jQuery('input[id^='+fileId+'_]').eq(i).is(':checked')) {
			if((i+1) >= boxCnt) continue;
			var fileObj = jQuery('div[id^='+fileId+'-]').eq(i).clone();
			jQuery('div[id^='+fileId+'-]').eq(i).remove();
			jQuery('div[id^='+fileId+'-]').eq(i).after(fileObj);
		}
	}
	fn_edosi_file_arr(fileId, fieldName);
	return false;
}

function fn_edosi_file_arr(fileId, fieldName) {
	var fileArr = '';
	var boxCnt = jQuery('input[id^='+fileId+'_]').length;
	for(var i=0; i<boxCnt; i++) {
		if(i > 0)fileArr += ",";
		fileArr += jQuery('div[id^='+fileId+'-]').eq(i).attr('id');
	}
	jQuery('#fileArrSort').val(fileArr);
}

/** 동영상 전용 수정 폼*/
function fn_egov_deleteFile_Movie(atchFileId, fileSn, fileNm) {
	if(confirm("첨부파일은 복구 불가합니다. 바로 삭제하시겠습니까?")) {
		var obj = {"atchFileId" : atchFileId, "fileSn" : fileSn};
		EgovFileMngDwr.deleteFileInf( obj, function(result) {
			var divObj = document.getElementById(atchFileId + "-" + fileSn);
			if(divObj != null) {
				divObj.style.display = "none";
			}
		});
		jQuery("."+fileNm).css("display", "");
	}
}

function fn_egov_preview(arg0,arg1,arg2,arg3,arg4) {
	var frm = document.getElementById("synapPreviewFrm");

	frm.atchFileId.value = arg0;
	frm.fileSn.value = arg1;
	frm.streFileNm.value = arg2;
	frm.fileStreCours.value = arg3;
	frm.orignlFileNm.value = arg4;

	frm.submit();
}
