/* **********************
   Event Handlers
   These are my custom event handlers to make my
   web application behave the way I went when SWFUpload
   completes different tasks.  These aren't part of the SWFUpload
   package.  They are part of my application.  Without these none
   of the actions SWFUpload makes will show up in my application.
   ********************** */
function fileQueued(file) {

	// 파일을 대기 상태로 만들고 배열에 추가
	file.status = -1;
	fileList.push( file );
	showFileList();

	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setFileName(file.name);
		progress.setStatus("Pending...");
		progress.toggleCancel(true, this);
	} catch (ex) {
		this.debug(ex);
	}

}


function fileQueued2(file) {

	// 파일을 대기 상태로 만들고 배열에 추가
	file.status = -1;
	fileList2.push( file );
	showFileList2();

	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setFileName(file.name);
		progress.setStatus("Pending...");
		progress.toggleCancel(true, this);
	} catch (ex) {
		this.debug(ex);
	}

}


function fileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			/*alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));*/
			alert("등록 가능한 파일 수는 1개 입니다.");
			return;
		}

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		// 에러 발생시 메시지 출력 후 1초 후 사라짐
		setTimeout( function () {
			 progress.disappear();
		}, 1000 );

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus("업로드 가능한 파일크기를 초과하였습니다.");
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus("파일크기가 0Byte인 파일은 업로드할 수 없습니다.");
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus("잘못된 File Type입니다.");
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				progress.setStatus("Unhandled Error");
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function fileQueueError2(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
			return;
		}

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		// 에러 발생시 메시지 출력 후 1초 후 사라짐
		setTimeout( function () {
			 progress.disappear();
		}, 1000 );

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus("업로드 가능한 파일크기를 초과하였습니다.");
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus("파일크기가 0Byte인 파일은 업로드할 수 없습니다.");
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus("잘못된 File Type입니다.");
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				progress.setStatus("Unhandled Error");
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}


function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesSelected > 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}

		if( numFilesSelected > 0 ) showProgressBarLayer(); // 상태바 보여주기 함수
	
		/* I want auto start the upload and I can do that here */
		//this.startUpload(); 업로드 시작 함수를 셀렉트 박스 줄인 다음 실행하도록 즉 showProgressBarLayer 으로 이동
	} catch (ex)  {
        this.debug(ex);
	}
}

function fileDialogComplete2(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesSelected > 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}

		if( numFilesSelected > 0 ) showProgressBarLayer2(); // 상태바 보여주기 함수
	
		/* I want auto start the upload and I can do that here */
		//this.startUpload(); 업로드 시작 함수를 셀렉트 박스 줄인 다음 실행하도록 즉 showProgressBarLayer 으로 이동
	} catch (ex)  {
        this.debug(ex);
	}
}


function uploadStart(file) {
	try {
		// 파일을 업로드 상태로 변경하고 기존 배열에서 해당 파일을 찾아 상태 변경		
		file.status = -2;
		changeFileStatus( file );
		showFileList();

		this.checkSpeed = 0; // 업로드 속도 저장 변수
		this.checkTime = 0;
		this.checkByte = 0;
		this.checkCount = 0;

		/* I don't want to do any file validation or anything,  I'll just update the UI and
		return true to indicate that the upload should start.
		It's important to update the UI here because in Linux no uploadProgress events are called. The best
		we can do is say we are uploading.
		 */
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setFileName(file.name);
		progress.setStatus("Uploading...");
		progress.toggleCancel(true, this);
	}
	catch (ex) {}
	return true;
}

function uploadStart2(file) {
	try {
		// 파일을 업로드 상태로 변경하고 기존 배열에서 해당 파일을 찾아 상태 변경
		file.status = -2;
		changeFileStatus2( file );
		showFileList2();

		this.checkSpeed = 0; // 업로드 속도 저장 변수
		this.checkTime = 0;
		this.checkByte = 0;
		this.checkCount = 0;

		/* I don't want to do any file validation or anything,  I'll just update the UI and
		return true to indicate that the upload should start.
		It's important to update the UI here because in Linux no uploadProgress events are called. The best
		we can do is say we are uploading.
		 */
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setFileName(file.name);
		progress.setStatus("Uploading...");
		progress.toggleCancel(true, this);
	}
	catch (ex) {}
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		var status = "Uploading..." + percent + "% - (" + calculateFileSize(bytesLoaded) + "/" + calculateFileSize(bytesTotal) + ")";

		// 속도 체크 ( 3번 갱신 될 때의 평균을 냄 )
		if( this.checkCount == 0 ) {
			this.checkTime = new Date().getTime();
			this.checkByte = bytesLoaded;
			this.checkCount++;
		} else {
			if( this.checkCount == 3 ) {
				this.checkSpeed = ( bytesLoaded - this.checkByte ) * ( 1 / ( ( new Date().getTime() - this.checkTime ) / 1000 ) );
				this.checkTime = null;
				this.checkByte = 0;
				this.checkCount = 0;
			} else {
				this.checkCount++;
			}
		}

		if( this.checkSpeed > 0 ) {
			status += " - " + calculateFileSize( this.checkSpeed ) + "/s";
		}

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setProgress(percent);
		progress.setStatus( status );
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadProgress2(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		var status = "Uploading..." + percent + "% - (" + calculateFileSize(bytesLoaded) + "/" + calculateFileSize(bytesTotal) + ")";

		// 속도 체크 ( 3번 갱신 될 때의 평균을 냄 )
		if( this.checkCount == 0 ) {
			this.checkTime = new Date().getTime();
			this.checkByte = bytesLoaded;
			this.checkCount++;
		} else {
			if( this.checkCount == 3 ) {
				this.checkSpeed = ( bytesLoaded - this.checkByte ) * ( 1 / ( ( new Date().getTime() - this.checkTime ) / 1000 ) );
				this.checkTime = null;
				this.checkByte = 0;
				this.checkCount = 0;
			} else {
				this.checkCount++;
			}
		}

		if( this.checkSpeed > 0 ) {
			status += " - " + calculateFileSize( this.checkSpeed ) + "/s";
		}

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setProgress(percent);
		progress.setStatus( status );
	} catch (ex) {
		this.debug(ex);
	}
}

// 첨부된 파일 중 개인정보가 포함된 파일만 선택
function setSelectBlockFile(blockFileId) {
	var selFile = document.getElementById("selectFileList");

	for(var i = 0; i < selFile.length; i++) {
		var temp = selFile.options[i].value.split(";");
		if(blockFileId == temp[0]) selFile.options[i].selected = true;
	}
}

//첨부된 파일 중 개인정보가 포함된 파일만 선택
function setSelectBlockFile2(blockFileId) {
	var selFile = document.getElementById("selectFileList2");

	for(var i = 0; i < selFile.length; i++) {
		var temp = selFile.options[i].value.split(";");
		if(blockFileId == temp[0]) selFile.options[i].selected = true;
	}
}

function uploadSuccess(file, serverData) {
	/*
		기본적인 file 객체의 내용은 아래와 같다.

		name= msxml6.msi 
		filestatus= -4 
		id= SWFUpload_0_0 
		index= 0 
		modificationdate= Fri Jul 18 13:59:18 UTC+0900 2008 
		size= 1528320 
		type= .msi 
		creationdate= Fri Jul 18 13:59:18 UTC+0900 2008 
		post= [object Object] 

		아래 부분에서는 해당 file 객체에 saveFileName( 서버에 저장된 파일명 ),
		width, height( 이미지 파일일 경우 가로, 세로 크기( 이미지가 아니면 -1 ) ) 의 속성을 추가한다.
		참고로 <FILENAME> 과 같은 태그는 업로드 처리 파일에서 업로드 후에 php(echo), jsp(out.println) 으로
		출력해주면 플래쉬 9 버전 이후론 그 데이터를 읽어올 수 있다.( serverData 인자로 값이 넘어옴 )

	*/
	// 실제 서버에 저장되는 파일명을 저장한다.
	file.saveFileName =	serverData.substring( serverData.indexOf("<OLDFILENM>") + 11, serverData.lastIndexOf("</OLDFILENM>") );
	
	file.oldFileName = serverData.substring( serverData.indexOf("<FILENAME>") + 10, serverData.lastIndexOf("</FILENAME>") );
	
	file.status = -4;

	changeFileStatus( file );

	showFileList(this.customSettings.fileListObjId);

	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("Complete.");
		progress.toggleCancel(false);
		
		// webfilter에서 차단된 파일인지 확인, 차단된 파일이면 업로드 취소 처리
		var isBlock = serverData.substring( serverData.indexOf("<BLOCK>") + 7, serverData.lastIndexOf("</BLOCK>") );
		if(isBlock != null && typeof isBlock != "undefined" && isBlock != "") {
			if(isBlock.indexOf("webfilter") > -1) {
				alert("파일에 개인정보가 포함되어 있어 해당 파일을 차단합니다.\n개인정보를 삭제하신 후 다시 첨부해주시기 바랍니다.\n\n차단된 파일명 : " + file.saveFileName);
				setSelectBlockFile(file.oldFileName);
				deleteBoardFile("01");
			}
		}

	} catch (ex) {
		this.debug(ex);
	}
}

function uploadSuccess2(file, serverData) {
	/*
		기본적인 file 객체의 내용은 아래와 같다.

		name= msxml6.msi 
		filestatus= -4 
		id= SWFUpload_0_0 
		index= 0 
		modificationdate= Fri Jul 18 13:59:18 UTC+0900 2008 
		size= 1528320 
		type= .msi 
		creationdate= Fri Jul 18 13:59:18 UTC+0900 2008 
		post= [object Object] 

		아래 부분에서는 해당 file 객체에 saveFileName( 서버에 저장된 파일명 ),
		width, height( 이미지 파일일 경우 가로, 세로 크기( 이미지가 아니면 -1 ) ) 의 속성을 추가한다.
		참고로 <FILENAME> 과 같은 태그는 업로드 처리 파일에서 업로드 후에 php(echo), jsp(out.println) 으로
		출력해주면 플래쉬 9 버전 이후론 그 데이터를 읽어올 수 있다.( serverData 인자로 값이 넘어옴 )

	*/
	// 실제 서버에 저장되는 파일명을 저장한다.
	file.saveFileName =	serverData.substring( serverData.indexOf("<OLDFILENM>") + 11, serverData.lastIndexOf("</OLDFILENM>") );
	
	file.oldFileName = serverData.substring( serverData.indexOf("<FILENAME>") + 10, serverData.lastIndexOf("</FILENAME>") );

	file.status = -4;

	changeFileStatus2( file );

	showFileList2(this.customSettings.fileListObjId);

	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("Complete.");
		progress.toggleCancel(false);

		// webfilter에서 차단된 파일인지 확인, 차단된 파일이면 업로드 취소 처리
		var isBlock = serverData.substring( serverData.indexOf("<BLOCK>") + 7, serverData.lastIndexOf("</BLOCK>") );
		if(isBlock != null && typeof isBlock != "undefined" && isBlock != "") {
			if(isBlock.indexOf("webfilter") > -1) {
				alert("파일에 개인정보가 포함되어 있어 해당 파일을 차단합니다.\n개인정보를 삭제하신 후 다시 첨부해주시기 바랍니다.\n\n차단된 파일명 : " + file.saveFileName);
				setSelectBlockFile2(file.oldFileName);
				deleteBoardFile2("01");
			}
		}
	} catch (ex) {
		this.debug(ex);
	}
}


function uploadError(file, errorCode, message) {
	file.status = -3;
	changeFileStatus( file );
	showFileList();

	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		// 에러 발생시 메시지 출력 후 1초 후 사라짐
		setTimeout( function () {
			 progress.disappear();
		}, 1000 );

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("Upload Error: " + message);
			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("Upload Failed.");
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus("Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("Failed Validation.  Upload skipped.");
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			progress.setStatus("Cancelled");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("Stopped");
			break;
		default:
			progress.setStatus("Unhandled Error: " + errorCode);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadError2(file, errorCode, message) {
	file.status = -3;
	changeFileStatus2( file );
	showFileList2();

	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		// 에러 발생시 메시지 출력 후 1초 후 사라짐
		setTimeout( function () {
			 progress.disappear();
		}, 1000 );

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("Upload Error: " + message);
			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("Upload Failed.");
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus("Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("Failed Validation.  Upload skipped.");
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			progress.setStatus("Cancelled");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("Stopped");
			break;
		default:
			progress.setStatus("Unhandled Error: " + errorCode);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadComplete(file) {

	// 파일 큐가 비워진다면( 모두 완료되면 )
	if (this.getStats().files_queued === 0) {
		document.getElementById(this.customSettings.cancelButtonId).disabled = true;

		var progress = new FileProgress(file, this.customSettings.progressTarget);

		// 에러 발생시 메시지 출력 후 1초 후 사라짐
		setTimeout( function () {
			 progress.disappear();
		}, 1000 );
	}
}

function uploadComplete2(file) {

	// 파일 큐가 비워진다면( 모두 완료되면 )
	if (this.getStats().files_queued === 0) {
		document.getElementById(this.customSettings.cancelButtonId).disabled = true;

		var progress = new FileProgress(file, this.customSettings.progressTarget);

		// 에러 발생시 메시지 출력 후 1초 후 사라짐
		setTimeout( function () {
			 progress.disappear();
		}, 1000 );
	}
}

// This event comes from the Queue Plugin
function queueComplete(numFilesUploaded) {
	var status = document.getElementById("divStatus");
	status.innerHTML = numFilesUploaded + " file" + (numFilesUploaded === 1 ? "" : "s") + " uploaded.";
}

function queueComplete2(numFilesUploaded) {
	var status = document.getElementById("divStatus");
	status.innerHTML = numFilesUploaded + " file" + (numFilesUploaded === 1 ? "" : "s") + " uploaded.";
}


var fileList = new Array(); // 파일 목록 저장 배열 추가
var fileList2 = new Array(); // 파일 목록 저장 배열 추가

/**
 * 현재 fileList 에 있는 파일 데이터들 중 상태가 변경된 데이터를 찾아서 교체한다.
 *
 * @param file 새로 업로드 되거나 정보가 변경된 파일
 */
function changeFileStatus( file ) {
	for( var i = 0; i < fileList.length; i++ ) {
		if( fileList[i].id == file.id ) {
			fileList[i] = file;
			break;
		}
	}
}

function changeFileStatus2( file ) {
	for( var i = 0; i < fileList2.length; i++ ) {
		if( fileList2[i].id == file.id ) {
			fileList2[i] = file;
			break;
		}
	}
}

/**
 * 셀렉트 박스에 파일 정보를 업데이트 한다.
 */
function showFileList() {
	var selectObj = document.getElementById("selectFileList");

	var length = selectObj.childNodes.length - 1;

	while(selectObj.childNodes.length > 0) {
		selectObj.removeChild(selectObj.childNodes[length--]);
	}

	var text;

	var totalSize = 0;

	for( var i = 0; i < fileList.length; i++ ) {
		text = "";

		if( fileList[i] != null ) {

			// 파일 상태에 따른 데이터 변경
			/*
				QUEUED		 : -1,
				IN_PROGRESS	 : -2,
				ERROR		 : -3,
				COMPLETE	 : -4,
				CANCELLED	 : -5
			*/
			if( fileList[i].status == -1 ) {
				text = fileList[i].name + "(" + calculateFileSize( fileList[i].size ) + ") 대기 중..";
			} else if( fileList[i].status == -2 ) {
				text = fileList[i].name + "(" + calculateFileSize( fileList[i].size ) + ") 업로드 중..";
			} else if( fileList[i].status == -4 ) {
				text = fileList[i].name + "(" + calculateFileSize( fileList[i].size ) + ")";

				// 업로드가 완료된 데이터에 한해서만 사이즈를 저장한다.
				totalSize += fileList[i].size;
			}

			if( text ) {
				var optionElement = document.createElement("OPTION");
				var textNode = document.createTextNode( text );

				optionElement.appendChild( textNode );
				optionElement.setAttribute( "value", fileList[i].oldFileName + ";" +  fileList[i].saveFileName);
				selectObj.appendChild( optionElement );
			}
		}
	}

	// 총 업로드 사이즈 출력
	document.getElementById("totalSize").innerHTML = calculateFileSize( totalSize );
}

function showFileList2() {
	var selectObj = document.getElementById("selectFileList2");

	var length = selectObj.childNodes.length - 1;

	while(selectObj.childNodes.length > 0) {
		selectObj.removeChild(selectObj.childNodes[length--]);
	}

	var text;

	var totalSize = 0;

	for( var i = 0; i < fileList2.length; i++ ) {
		text = "";

		if( fileList2[i] != null ) {

			// 파일 상태에 따른 데이터 변경
			/*
				QUEUED		 : -1,
				IN_PROGRESS	 : -2,
				ERROR		 : -3,
				COMPLETE	 : -4,
				CANCELLED	 : -5
			*/
			if( fileList2[i].status == -1 ) {
				text = fileList2[i].name + "(" + calculateFileSize( fileList2[i].size ) + ") 대기 중..";
			} else if( fileList2[i].status == -2 ) {
				text = fileList2[i].name + "(" + calculateFileSize( fileList2[i].size ) + ") 업로드 중..";
			} else if( fileList2[i].status == -4 ) {
				text = fileList2[i].name + "(" + calculateFileSize( fileList2[i].size ) + ")";

				// 업로드가 완료된 데이터에 한해서만 사이즈를 저장한다.
				totalSize += fileList2[i].size;
			}

			if( text ) {
				var optionElement = document.createElement("OPTION");
				var textNode = document.createTextNode( text );

				optionElement.appendChild( textNode );
				optionElement.setAttribute( "value", fileList2[i].oldFileName + ";" +  fileList2[i].saveFileName);
				selectObj.appendChild( optionElement );
			}
		}
	}

	// 총 업로드 사이즈 출력
	document.getElementById("totalSize2").innerHTML = calculateFileSize( totalSize );
}

/**
 * 가독성을 위해 byte 단위의 사이즈를 KB 나 MB 로 출력
 */
function calculateFileSize( fileSize ) {
	// 사이즈가 1메가 초과일 경우
	if( fileSize > 1048576 )
		fileSize = Math.floor( ( ( fileSize / 1024 ) / 1024  ) * 100 ) / 100 + "MB";
	else if( fileSize > 1024 )
		fileSize = Math.floor( ( fileSize / 1024 ) * 100 ) / 100 + "KB";
	else
		fileSize += "Byte";

	return fileSize;
}

function showProgressBarLayer() {

	var selectObj = document.getElementById("selectFileList");

	// 셀렉트 박스 크기를 줄이고 
	var height = selectObj.offsetHeight;

	if( height > 80 ) {
		selectObj.style.height = parseInt(height) - 5 + 'px';
		setTimeout("showProgressBarLayer()", 5); 
	} else {
		swfu.startUpload(); // 업로드 시작( swfu 는 index.jsp 에서 생성한 변수명 )
	}
}

function showProgressBarLayer2() {

	var selectObj = document.getElementById("selectFileList2");

	// 셀렉트 박스 크기를 줄이고 
	var height = selectObj.offsetHeight;

	if( height > 80 ) {
		selectObj.style.height = parseInt(height) - 5 + 'px';
		setTimeout("showProgressBarLayer2()", 5); 
	} else {
		swfu2.startUpload(); // 업로드 시작( swfu 는 index.jsp 에서 생성한 변수명 )
	}
}

function hideProgressBarLayer() {

	var selectObj = document.getElementById("selectFileList2");

	// 셀렉트 박스 크기를 틀이고 
	var height = selectObj.offsetHeight;

	if( height < 130 ) {
		selectObj.style.height = height + 5;
		setTimeout("hideProgressBarLayer()", 5); 
	}
}

function hideProgressBarLayer2() {

	var selectObj = document.getElementById("selectFileList2");

	// 셀렉트 박스 크기를 틀이고 
	var height = selectObj.offsetHeight;

	if( height < 130 ) {
		selectObj.style.height = height + 5;
		setTimeout("hideProgressBarLayer2()", 5); 
	}
}


/**
 * 파일 삭제 처리(공통게시판 첨부파일)
 */
function deleteBoardFile(boardKind) {
	var selectObj = document.getElementById("selectFileList");

	if( selectObj.selectedIndex < 0 ) {
		alert("삭제할 파일을 선택하여 주십시오.");
		return false;
	}

	var fileId;

	while( true ) {
		if( selectObj.selectedIndex != -1 ) {
			var temp = selectObj.options[selectObj.selectedIndex].value.split(";");
			fileId = temp[0];

			for( var i = 0; i < fileList.length; i++ ) {
				// 같은 아이디를 가지고 있는 파일을 찾는다.
				if( fileList[i].oldFileName == fileId ) {
					fileList[i].status = -5; // 취소 처리
					break;
				}
			}
			$.ajax (
					{
						url  : "/common/deleteFile.do",
						type : "post",					
						data : {kind : boardKind, selectFileList : fileId},
					    dataType: 'json',
					    success : function(data) {
					    		
					    } 
					}
				);
			selectObj.remove( selectObj.selectedIndex );
		} else {
			break;
		}
	}

	showFileList();
}

