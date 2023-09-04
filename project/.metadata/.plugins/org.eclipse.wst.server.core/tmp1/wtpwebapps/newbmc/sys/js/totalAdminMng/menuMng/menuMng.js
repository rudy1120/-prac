/**
* menuMng.js
*
* @author taeseong
* @since 2017.06.15
*/

$(document).ready(function() {
	var arr_all = $.map(map_all, function(v, k) {
		return k;
	});
	var arr_main = $.map(map_main, function(v, k) {
		return k;
	});
	var arr_dept = $.map(map_dept, function(v, k) {
		return k;
	});
	var arr_csc = $.map(map_csc, function(v, k) {
		return k;
	});
	var arr_etc = $.map(map_etc, function(v, k) {
		return k;
	});

	$("input[name=menu_category]").click(function() {
		var opts = "";
		switch ($(this).val()) {
			case 'menu_all':
			$(arr_all).each(function(i, v) {
				opts += "<option value='" + v + "'>" + map_all[v] + "</option>";
			});
			$("#searchForm #siteCode").empty().append(opts);
			break;
			case 'menu_main':
			$(arr_main).each(function(i, v) {
				opts += "<option value='" + v + "'>" + map_main[v] + "</option>";
			});
			$("#searchForm #siteCode").empty().append(opts);
			break;
			case 'menu_dept':
			$(arr_dept).each(function(i, v) {
				opts += "<option value='" + v + "'>" + map_dept[v] + "</option>";
			});
			$("#searchForm #siteCode").empty().append(opts);
			break;
			case 'menu_csc':
			$(arr_csc).each(function(i, v) {
				opts += "<option value='" + v + "'>" + map_csc[v] + "</option>";
			});
			$("#searchForm #siteCode").empty().append(opts);
			break;
			case 'menu_etc':
			$(arr_etc).each(function(i, v) {
				opts += "<option value='" + v + "'>" + map_etc[v] + "</option>";
			});
			$("#searchForm #siteCode").empty().append(opts);
			break;

		}
	});


	if (menuMng.siteCode === "") {
		$("#btn_addFirst").hide();
		$("#btn_sendMenu").hide();
		$("#btn_statusMenu").hide();
		$("#btn_uploadExcel").hide();
		$("#folderBox").hide();
	}

	$("#siteCode").change(function() {
		$("#btn_addFirst").hide();
		$("#btn_sendMenu").hide();
		$("#btn_statusMenu").hide();
		$("#btn_uploadExcel").hide();
	});

	$("#btn_addFirst").click(function() {
		getAddMenuInfo("0000000000");
	});

	$("#menuFrm").hide();

	$("#folderBox").jstree({
		'core' : { "multiple" : false },
		'search': {
	        'case_insensitive': true,
	        'show_only_matches' : true
	    },
	    'plugins': ['search']
	});

	$(".jstree-anchor").click(function(){
	    document.location.href = this;
	});

	$('#menuSearchBtn').click(function(){
	    $('#folderBox').jstree('search', $('#menuSearchTxt').val());
	});

	/*
	$("#browser").treeview({
		toggle: function() {
			//console.log("%s was toggled.", $(this).find(">span").text());
		}
	});*/



	$("#submitBtn").click(function() {
		var options = {
			url: yh.contextPath + "/sys/totalAdminMng/menuMng/writeMenuProc.do",
			type: "post",
			beforeSubmit: function() {
				if (!$("#siteCode_menuFrm").val()) {
					alert("현재 사이트코드를 찾을 수 없습니다. 사이트를 다시 선택해주세요.");
					return false;
				} else if (!$("#menuName").val()) {
					alert("메뉴명은 필수 입력항목입니다.");
					$("#menuName").focus();
					return false;
				}
			},
			success: function(data) {
				if (data.flag == "success") {
					//CMS 관련 메뉴일경우 메뉴명 변경 사항 CMS 반영
					if($('input[name="menuType"]:checked').val()=="C" && $("#cmsSiteCode").val() != "" && $("#cmsPageId").val() != "") {
						var siteCode = $("#cmsSiteCode").val();
						var folderCode = $("#cmsPageId").val();
						var folderName = $("#menuName").val();

						var rtv = cmsModifyFolderName(siteCode, folderCode, folderName);
					}

					alert(data.message);
					document.location.href = yh.contextPath + "/sys/totalAdminMng/menuMng/menuList.do?mId=0101000000&siteCode=" + menuMng.siteCode + "&procMid=" + data.procMid;
				} else {
					alert(data.message);
				}
			},
			dataType: "json",
			resetForm: false
		};

		$("#menuFrm").ajaxForm(options);
	});


	//담당자이력 정보 폼 다이얼로그 초기화
	dialog = $("#dialogChargeHistoryList" ).dialog({
		autoOpen: false,
		width: 600,
		modal: true,
		resizable: false,
		buttons: {
	        "닫기": function() {
		          dialog.dialog( "close" );
		    }
	   },
       close: function() {
    	   go_record_alldel();
      }
	});
});

function cmsModifyFolderName(siteCode, folderCode, folderName) {
	$.ajax({
		url : menuMng.cmsUrl + "/linkage/modifyFolderName?key=" + menuMng.cmsKey,
		type: "post",
		dataType: "json",
		data: {
			'siteCode' : siteCode,
	    	'folderCode' : folderCode,
	    	'folderName' : encodeURI(folderName)
		},
		success: function(result) {
			console.log("변경성공");
	    	return true;
		},
		error : function(xhr, status, error) {
	    	console.log("변경실패");
	    	return false;
	    }
	});
}

function showActionBox(menuType) {

	("B" != menuType ? $("[name=nuriType]").closest("tr").show() : ""); // 게시판이 아닌 경우 공공누리 설정 여부 노출

	if ("C" == menuType) {
		$("#cmsSelectorBox").show();
		$("#fileUrlSelectorBox").hide();
		$("#bbsSelectorBox").hide();
		$("#programSelectorBox").hide();
		$("#linkSelectorBox").hide();
	} else if ("F" == menuType) {
		$("#cmsSelectorBox").hide();
		$("#fileUrlSelectorBox").show();
		$("#bbsSelectorBox").hide();
		$("#programSelectorBox").hide();
		$("#linkSelectorBox").hide();
	} else if ("B" == menuType) {
		$("#cmsSelectorBox").hide();
		$("#fileUrlSelectorBox").hide();
		$("#bbsSelectorBox").show();
		$("#programSelectorBox").hide();
		$("#linkSelectorBox").hide();
		$("[name=nuriType]").closest("tr").hide();	//게시판인 경우 공공누리 비활성화
	} else if ("P" == menuType) {
		$("#cmsSelectorBox").hide();
		$("#fileUrlSelectorBox").hide();
		$("#bbsSelectorBox").hide();
		$("#programSelectorBox").show();
		$("#linkSelectorBox").hide();
	} else if ("L" == menuType) {
		$("#cmsSelectorBox").hide();
		$("#fileUrlSelectorBox").hide();
		$("#bbsSelectorBox").hide();
		$("#programSelectorBox").hide();
		$("#linkSelectorBox").show();
	}
}

function showTermControlBox(type) {
	if (type == 'Y') {
		$("#termDtBox").show();
	} else {
		$("#termDtBox").hide();
	}
}

function restoreDefaultValue() {
	$("#bbsMstIdx").val(0);
	$("#bbsIdx").val(0);
	$("#bbsName").text("게시판을 선택하세요.");
}

function getAddMenuInfo(mId) {
	$("#chargeTable > tbody > tr").remove();
	$("#chargeTable > tbody").append("<tr><td colspan=\"6\">등록된 담당자가 없습니다.</td></tr>");
	$("#menuFrm").show();
	$("#writeKind").val("insert");
	$("#menuTitle").html("메뉴등록");
	restoreDefaultValue();
	$.ajax({
		url: yh.contextPath + "/sys/totalAdminMng/menuMng/getAddMenuInfo.do",
		type: "post",
		dataType: "json",
		data: {
			mId: mId,
			siteCode: menuMng.siteCode
		},
		success: function(result) {
			if (result.flag == "success") {
				$("#targetMid").val(result.mId);
				$("#menuLevel").val(result.menuLevel);
				$("#menuName").val("");
				$("input:radio[name=isIncContent][value=Y]").prop("checked", "checked");
				$("input:radio[name=menuType][value=C]").prop("checked", "checked");
				showActionBox("C");
				$("input:radio[name=target][value=_self]").prop("checked", "checked");
				$("#contentFilePath").val("");
				$("#programUrl").val("");
				$("#linkUrl").val("");
				$("input:radio[name=isPoll][value=N]").prop("checked", "checked");
				$("[name=nuriType][value=0]").prop("checked", "checked");
				$("input:radio[name=isFirst][value=0]").prop("checked", "checked");
				$("[name=cclType][value=0]").prop("checked", "checked");
				$("input:radio[name=isUse][value=Y]").prop("checked", "checked");
				$("input:radio[name=isTermset][value=N]").prop("checked", "checked");
				$("input:radio[name=isSnsComment][value=N]").prop("checked", "checked");
				$("#chargeTel").val("");
				$("#menuName").focus();
				$("input:radio[name=isTermset][value=N]").prop("checked", "checked");
				$("#termDtBox").hide();
			} else {
				alert(result.message);
			}
		}
	});
}

function getEditMenuInfo(mId) {
	$("#chargeTable > tbody > tr").remove();
	$("#chargeTable > tbody").append("<tr><td colspan=\"6\">등록된 담당자가 없습니다.</td></tr>");
	$("#menuFrm").show();
	$("#writeKind").val("update");
	$("#menuTitle").html("메뉴수정");
	$.ajax({
		url: yh.contextPath + "/sys/totalAdminMng/menuMng/getEditMenuInfo.do",
		type: "post",
		dataType: "json",
		data: {
			mId: mId,
			siteCode: menuMng.siteCode
		},
		success: function(result) {
			if (result.flag == "success") {
				$("#targetMid").val(result.mId);	// 메뉴아이디
				$("#menuName").val(result.menuName);	// 메뉴명
				$("#menuSummary").val(result.menuSummary);	// 메뉴개요
				$("input:radio[name=isIncContent][value=" + result.isIncContent + "]").prop("checked", "checked");	// 컨텐츠포함여부
				$("input:radio[name=menuType][value=" + result.menuType + "]").prop("checked", "checked");	// 메뉴 컨텐츠 타입
				showActionBox(result.menuType);
				$("input:radio[name=target][value=" + result.target + "]").prop("checked", "checked");	// 새창여부

				// 컨텐츠
				$("#cmsSiteCode").val(result.cmsSiteCode);
				$("#cmsPageId").val(result.cmsPageId);
				(result.cmsPageId ? $("#cmsName").html("Page ID : " + result.cmsPageId) : '');

				// 게시판 MST, BBS IDX
				$("#bbsMstIdx").val(result.bbsMstIdx);
				$("#bbsIdx").val(result.bbsIdx);
				if (result.bbsName) {
					$("#init_bbs").show();
					var initBbs = $("#init_bbs").clone();
					$("#bbsName").text(result.bbsName);
					initBbs.appendTo($("#bbsName"));
				}

				(!result.contentFilePath ? $("#contentFilePath").val("") : $("#contentFilePath").val(result.contentFilePath));	// 컨텐츠 파일URL
				(!result.programUrl ? $("#programUrl").val("") : $("#programUrl").val(result.programUrl));	// 프로그램 URL

				(!result.linkUrl ? $("#linkUrl").val("") : $("#linkUrl").val(result.linkUrl));	// 링크URL
				$("input:radio[name=isPoll][value=" + result.isPoll + "]").prop("checked", "checked");	// 설문조사형 포함여부
				$("[name=nuriType][value=" + result.nuriType + "]").prop("checked", "checked");	// 공공누리 유형
				$("[name=cclType][value=" + result.cclType + "]").prop("checked", "checked");	// CCL 유형
				$("input:radio[name=isFirst][value=" + result.isFirst + "]").prop("checked", "checked");	// 메뉴우선노출 사용여부

				(!result.chargeTel ? $("#usrTel").val("") : $("#usrTel").val(result.chargeTel));	// 담당자전화번호
				(!result.chargeFnm ? $("#usrNm").val("") : $("#usrNm").val(result.chargeFnm));	// 담당자
				//(!result.chargeId ? $("#usrId").val("") : $("#usrId").val(result.chargeId)+$("#oldChargeId").val(result.chargeId)); // 담당자 아이디
				(!result.chargeId ? $("#usrId").val("") : $("#usrId").val(result.chargeId)); // 담당자 아이디
				(!result.chargeDepCode ? $("#deptCode").val("") : $("#deptCode").val(result.chargeDepCode));	// 담당부서코드
				(!result.chargeDepNm ? $("#deptNm").val("") : $("#deptNm").val(result.chargeDepNm));	// 담당부서명

				$("input:radio[name=isUse][value=" + result.isUse + "]").prop("checked", "checked");	// 메뉴 활성화여부
				$("input:radio[name=isTermset][value=" + result.isTermset + "]").prop("checked", "checked");	// 메뉴 기간설정여부

				(!result.termStartDt ? $("#termStartDtStr").val("") : $("#termStartDtStr").val(result.termStartDt));	// 기간시작일
				(!result.termEndDt ? $("#termEndDtStr").val("") : $("#termEndDtStr").val(result.termEndDt));	// 기간종료일
				(result.isTermset == "Y" ? $("#termDtBox").show() : $("#termDtBox").hide());
				(result.isSnsComment ? $("input:radio[name=isSnsComment][value=" + result.isSnsComment + "]").prop("checked", "checked") : '');	// 소셜댓글 사용여부

				//메뉴별 담당자 세팅
				if (result.chargeList.length > 0) {
					if ($("#chargeTable > tbody > tr").eq(0).find("td").attr("colspan") === "6") {
						$("#chargeTable > tbody > tr").remove();
					}
					$(result.chargeList).each(function(i) {
						$("#chargeTable > tbody").append("<tr data-chargeId=\""+this.chargeId+"\">" +
								"<td><span>"+(i+1)+"</span>"+
									getHiddenEle((i+1), "usrId", "chargeId", this.chargeId) +
									getHiddenEle((i+1), "deptCode", "chargeDepCode", this.chargeDepCode) +
									getHiddenEle((i+1), "usrNm", "chargeFnm", this.chargeFnm) +
									getHiddenEle((i+1), "usrTel", "chargeTel", this.chargeTel) +
									getHiddenEle((i+1), "deptNm", "chargeDepNm", this.chargeDepNm) +
								"</td>" +
								"<td>"+this.chargeFnm+"</td>" +
								"<td>"+this.chargeDepNm+"</td>" +
								"<td>"+this.chargeTel+"</td>" +
								"<td><span class='sortSpan'>"+this.sortNum+"</span>" +
								"<input type='text' name='sortNum' value='"+ this.sortNum+"'class='sortInput input50 textCenter hidden' onKeyPress='return numkeyCheck(event)' maxlength='2'/></td>" +
								"<td><a href=\"#\" onclick=\"rmCharge('"+this.chargeId+"', '"+this.chargeDepCode+"', '"+this.chargeFnm+"', '"+this.chargeDepNm+"', '"+this.chargeTel+"'); return false;\" class=\"btn_swhite\">삭제</a></td>" +
								"</tr>");
					});
					
				}
			} else {
				alert(result.message);
			}
		}
	});
}

function deleteMenu() {
	if (confirm("[주의]이 메뉴를 정말 삭제하시겠습니까? \r\n 하위메뉴가 있을경우 하위메뉴도 함께 삭제됩니다.")) {
		$.ajax({
			url: yh.contextPath + "/sys/totalAdminMng/menuMng/deleteMenuProc.do",
			type: "post",
			dataType: "json",
			data: {
				targetMid: $("#targetMid").val(),
				siteCode: menuMng.siteCode
			},
			success: function(result) {
				if (result.flag == "success") {
					alert(result.message);
					document.location.href = yh.contextPath + "/sys/totalAdminMng/menuMng/menuList.do?mId=0101000000&siteCode=" + menuMng.siteCode + "&procMid=" + result.procMid;
				} else {
					alert(result.message);
				}
			}
		});
	}
}

function moveMenuUp() {
	$.ajax({
		url: yh.contextPath + "/sys/totalAdminMng/menuMng/moveMenuProc.do",
		type: "post",
		dataType: "json",
		data: {
			targetMid: $("#targetMid").val(),
			moveKind: "up",
			siteCode: menuMng.siteCode
		},
		success: function(result) {
			if (result.flag == "success") {
				alert(result.message);
				document.location.href = yh.contextPath + "/sys/totalAdminMng/menuMng/menuList.do?mId=0101000000&siteCode=" + menuMng.siteCode + "&procMid=" + result.procMid;
			} else {
				alert(result.message);
			}
		}
	});
}

function moveMenuDown() {
	$.ajax({
		url: yh.contextPath + "/sys/totalAdminMng/menuMng/moveMenuProc.do",
		type: "post",
		dataType: "json",
		data: {
			targetMid: $("#targetMid").val(),
			moveKind: "down",
			siteCode: menuMng.siteCode
		},
		success: function(result) {
			if (result.flag == "success") {
				alert(result.message);
				document.location.href = yh.contextPath + "/sys/totalAdminMng/menuMng/menuList.do?mId=0101000000&siteCode=" + menuMng.siteCode + "&procMid=" + result.procMid;
			} else {
				alert(result.message);
			}
		}
	});
}

function getBbsInfoPop() {
	var url = yh.contextPath + "/sys/totalAdminMng/menuMng/menuInBbsList.do?siteCode=" + menuMng.siteCode;
	var option = "width=600, height=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
	window.open(url, "getBbsInfo", option);
}

function getCmsInfoPop() {
	var url = menuMng.cmsUrl + "/menuLinkageSite?key=" + menuMng.cmsKey;
	var option = "width=800, height=600, toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
	var cmsWindow = window.open(url, "getCmsInfo", option);
	var timer = setInterval(function() {
		if (cmsWindow.closed) {
			clearInterval(timer);
			$("#cmsName").html("Page ID : " + $("#cmsPageId").val());
		}
	}, 1000);
}

$(function() {
	$("#termStartDtStr").datepicker({
		dateFormat: "yy-mm-dd"
	}).val();
	$("#termEndDtStr").datepicker({
		dateFormat: "yy-mm-dd"
	}).val();
	$("#ui-datepicker-div").hide(); //레이아웃 윤곽을 숨김;
});

function getStaffInfoPop(opt) {
	var url = yh.contextPath + "/sys/totalAdminMng/menuMng/staffListChoice.do?openType=menu&opt=" + opt;
	var option = "width=800, height=600, toolbar=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no";
	var staffWindow = window.open(url, "getStaffInfo", option);
}

function sendMenu() {
	window.open(yh.contextPath + "/sys/totalAdminMng/menuMng/sendToCMS.do?siteCode=" + $("#siteCode option:selected").val(), "pop", "width=700, height=900, scrollbars=yes, resizable=yes");
}

function uploadExcel() {
	window.open(yh.contextPath + "/sys/totalAdminMng/menuMng/uploadExcel.do?siteCode=" + $("#siteCode option:selected").val(), "pop", "width=500, height=450, scrollbars=yes, resizable=yes");
}

function checkStatus() {
	window.open(yh.contextPath + "/sys/totalAdminMng/menuMng/status.do?siteCode=" + $("#siteCode option:selected").val(), "pop", "width=500, height=450, scrollbars=yes, resizable=yes");
}

function rmCharge(usrId, deptCode, usrNm, deptNm, usrTel) {

	$("#chargeTable > tbody > tr[data-chargeId="+usrId+"]").remove();
	if ($("#chargeTable > tbody > tr").length > 0) {
		$("#chargeTable > tbody > tr").each(function(index){
			$(this).find("td").eq(0).find("span").text(index+1);
		});
	} else {
		console.log("no data");
		$("#chargeTable > tbody").append("<tr><td colspan=\"6\">등록된 담당자가 없습니다.</td></tr>");
	}
}

function getHiddenEle(idx, id, name, value) {
	return "<input type=\"hidden\" id=\""+id+idx+"\" name=\""+name+"\" value=\""+value+"\"/>";
}

function initBbs() {
	$("#bbsMstIdx").val("0");
	var initBbs = $("#init_bbs").clone();
	initBbs.hide();
	$("#bbsName").text("게시판을 선택하세요.");
	initBbs.appendTo("#bbsName");
}



function getStaffHistory(siteCode) {
	$.ajax({
		url: '/sys/totalAdminMng/menuMng/getMenuChargeHistoryInfo.do',
		data : {siteCode : siteCode, mId : $("#targetMid").val()},
		type: 'post' ,
		dataType: 'json',
		success: function(rs){
			if(rs.none != 'none') {
				for (var i = 0; i < rs.list.length; i++) {
					go_record_add(rs.list[i].chargeFnm, rs.list[i].chargeId, rs.list[i].chargeDepNm, rs.list[i].chargeDepCode, rs.list[i].regDate);
				}

				dialog.dialog('open');void(0);
			} else {
				alert("담당자 이력정보가 없습니다.");
			}
		}
	});
}


	//행 추가
function go_record_add(chargeFnm, chargeId, chargeDepNm, chargeDepCode, regDate)
{
    var oRow = chargeInfoList.insertRow(-1); // insertRow(0) 인 경우는 맨처음에 삽입, insertRow(-1) 인 경우는 맨마지막에 삽입, insertRow(n)인 경우는 특정 위치에 삽입
    oRow.onmouseover=function(){chargeInfoList.clickedRowIndex=this.rowIndex};

    var oCell1 = oRow.insertCell(0); // 필드1:input-text
    var oCell2 = oRow.insertCell(1); // 필드2:input-text
    var oCell3 = oRow.insertCell(2); // 필드3:input-text
    var oCell4 = oRow.insertCell(3); // 필드4:input-text
    var oCell5 = oRow.insertCell(4); // 필드5:input-text

    //oCell1.height = "32";

    oCell1.align = "center";
    oCell2.align = "center";
    oCell3.align = "center";
    oCell4.align = "center";
    oCell5.align = "center";

    var rowCount = jQuery("table#chargeInfoList tr").length;
    oCell1.innerHTML = "<td>"+chargeFnm+"</td>";
    oCell2.innerHTML = "<td>"+chargeId+"</td>";
    oCell3.innerHTML = "<td>"+chargeDepNm+"</td>";
    oCell4.innerHTML = "<td>"+chargeDepCode+"</td>";
    oCell5.innerHTML = "<td>"+regDate+"</td>";
}

// 행 삭제
function go_record_del()
{
	chargeInfoList.deleteRow(chargeInfoList.clickedRowIndex);
}

// 모든 행 삭제
function go_record_alldel()
{
    // 매번 출력시 전체행을 삭제한다. 전체행의 삭제시 행번호 1번만 전체 행의 개수-1 만큼 반복 삭제한다.
    var rowCount = jQuery("table#chargeInfoList tr").length;
    if (rowCount > 1) {
        for (i = 1; i < rowCount; i++)
        	chargeInfoList.deleteRow(1);
    }
}

/**
 * 2022-01-12 개선사항 
 * 관리 담당자 정렬 편집기능
 * */
function setEditMode(){

	if($("#chargeTable .sortInput").hasClass('hidden')){
		$("#chargeTable .sortInput").removeClass('hidden');
	}
	
	if(!$("#chargeTable Span.sortSpan").hasClass('hidden')){
		$("#chargeTable Span.sortSpan").addClass('hidden');
	}
}

function endEditMode(){
	if(!$("#chargeTable .sortInput").hasClass('hidden')){
		$("#chargeTable .sortInput").addClass('hidden');
	}
	
	if($("#chargeTable Span.sortSpan").hasClass('hidden')){
		$("#chargeTable Span.sortSpan").removeClass('hidden');
	}
}

function numkeyCheck(event){
	var keyValue = event.keyCode; 
	if( ((keyValue >= 48) && (keyValue <= 57)) ) {
		return true; 
	}
	else return false;
}

