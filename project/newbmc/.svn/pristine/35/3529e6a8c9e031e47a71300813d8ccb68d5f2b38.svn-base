/* **********************
   Event Handlers
   These are my custom event handlers to make my
   web application behave the way I went when SWFUpload
   completes different tasks.  These aren't part of the SWFUpload
   package.  They are part of my application.  Without these none
   of the actions SWFUpload makes will show up in my application.
   ********************** */
// 배너 업로드용 File Queue Error 처리 함수
function appIconFileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
			return;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

// 배너 파일 선택
function appIconFileDialogStart() {
	deleteappIconFile();
}

// 배너 업로드용
function appIconFileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesQueued > 0) {
			this.startUpload();
		}
	} catch (ex)  {
        this.debug(ex);
	}
}

//배너 파일 업로드 진행율 처리
function appIconUploadProgress(file, bytesLoaded, bytesTotal) {
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

		var progress = new FileProgress(file, this.customSettings.upload_target);
		progress.setProgress(percent);
		progress.setStatus( status );
		if (percent === 100) progress.toggleCancel(false, this);
		else progress.toggleCancel(true, this);
	} catch (ex) {
		this.debug(ex);
	}
}

// 배너 파일 업로드 성공 처리
function appIconUploadSuccess(file, serverData) {
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
	file.saveFileName =	serverData.substring( serverData.indexOf("<FILENAME>") + 10, serverData.lastIndexOf("</FILENAME>") );
	file.oldFileName = serverData.substring( serverData.indexOf("<OLDFILENM>") + 11, serverData.lastIndexOf("</OLDFILENM>") );
	file.saveFolder = serverData.substring( serverData.indexOf("<SAVEFOLDER>") + 12, serverData.lastIndexOf("</SAVEFOLDER>") );
	
	file.status = -4;
	
	appIconPreview(file);
	
	try {
		var progress = new FileProgress(file, this.customSettings.upload_target);
		progress.setComplete();
		progress.setStatus("Complete.");
		progress.toggleCancel(false, this);
	
	} catch (ex) {
		this.debug(ex);
	}
}

// 배너 파일 업로드 Error 처리
function appIconUploadError(file, errorCode, message) {
	var progress;

	try {
		var progress = new FileProgress(file, this.customSettings.upload_target);
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

// 배너 업로드용 Complete 함수
function appIconUploadComplete(file) {
	try {
		/*  I want the next upload to continue automatically so I'll call startUpload here */
		if (this.getStats().files_queued > 0) {
			this.startUpload();
		} else {
			var progress = new FileProgress(file,  this.customSettings.upload_target);
			progress.setComplete();
			progress.setStatus("Upload Complete.");
			progress.toggleCancel(false);
			
			// 메시지 출력 후 1초 후 사라짐
			setTimeout( function () {
				 progress.disappear();
			}, 1000 );
		}
	} catch (ex) {
		this.debug(ex);
	}
}

// This event comes from the Queue Plugin
function queueComplete(numFilesUploaded) {
	var status = document.getElementById("divStatus");
	status.innerHTML = numFilesUploaded + " file" + (numFilesUploaded === 1 ? "" : "s") + " uploaded.";
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

// 배너 미리보기
function appIconPreview(file, context) {
	var previewObj = document.getElementById("banImgPreview");
	var fileNewName = document.getElementById("fileNewName");
	var fileOldName = document.getElementById("fileOldName");
	var changeYnFile = document.getElementById("changeYnFile");
	var defaultMessage = "<span id='banImgPreview'>No Image</span>";
	
	// 프리뷰 시킬 데이터가 있다면 확장자를 검사한다.
	if( file ) {
		var previewPath = "/fileUpDown.do?mode=downloadFile&kind=appIcon&saveFolder=" + file.saveFolder + "&fileOldName=" + file.oldFileName + "&fileNewName=" + file.saveFileName;
		
		previewObj.innerHTML = '<img src="' + previewPath + '" width=80 height=80/>';
		fileNewName.value = file.saveFileName;
		fileOldName.value = 	file.oldFileName;
		changeYnFile.value = "Y";
	} else {
		previewObj.innerHTML  = defaultMessage;
	}
}
