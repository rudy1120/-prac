var selectedStaffId = '';
var selectedStaffNm = '';
var selectedStaffDept = '';
var selectedStaffDeptCode = '';
var selectedStaffTel = '';
var selectedStaffSortNum = '';


function loadOrgTree(result, topTxt){
	var root = document.getElementById('tree1');
	var max = root.childNodes.length;
	for(var i=0; i<max; i++){
		root.removeChild(root.lastChild);
	}

	var first = document.createElement('li');
	first.setAttribute('class', 'base');
	first.innerHTML = '<span class="base1">'+topTxt+'</span>';
	var node = root.appendChild(first).parentNode;
	var curNodeLevel = 0;
	var nextNodeLevel = 0;

	// 노드수만큼 루프를 돌리며 다음 노드의 뎁스와 현재노드의 뎁스를 비교하여 트리를 구성한다.
	for(var i=0; i<result.length; i++){
		// 다음노드가 존재하지 않으면 루프를 빠져나온다.
		if(result[i+1] == undefined){
			node.lastChild.setAttribute("class", "last");
			break;
		}

		curNodeLevel = result[i].deptLevel;
		nextNodeLevel = result[i+1].deptLevel;

		if(curNodeLevel == nextNodeLevel){
			// 현재노드와 다음노드의 뎁스가 같으면 부모노드에 현재노드를 추가한다.
			var el = document.createElement('li');
			var text = '<span class="list"><a href="#sel" onclick="return selectOrgPart(this);" deptId="'+result[i].deptId+'">'+result[i].deptName+'</a></span>';
			el.innerHTML = text;
			node = node.appendChild(el).parentNode;
		}
		else if(curNodeLevel < nextNodeLevel){
			// 다음노드가 현재노드 보다 크면 다음노드는 현재노드의 자식노드이기 때문에 자식노드를 가질 수 있도록 노드를 변경한다.
			var el = document.createElement('li');
			var text = '<div class="plus expandable-plus"></div><a href="#sel" onclick="return selectOrgPart(this);" deptId="'+result[i].deptId+'">'+result[i].deptName+'</a>';
				text += '<ul style="display:none;"></ul>';
			el.setAttribute('class', 'expandable');
			el.innerHTML = text;
			node = node.appendChild(el);
			node = node.lastChild;
		}
		else if(curNodeLevel > nextNodeLevel){
			// 현재노드가 다음노드보다 크면 다음 노드는 부모노드의 다른 노드일 수 있다.
			// 뎁스의 차이를 구한다음, 적당한 부모노드를 찾아 다음노드를 추가할 수 있도록 준비한다.
			var cha = curNodeLevel - nextNodeLevel;
			var el = document.createElement('li');
			el.setAttribute('class', 'last');
			var text = '<span class="list"><a href="#sel" onclick="return selectOrgPart(this);" deptId="'+result[i].deptId+'">'+result[i].deptName+'</a></span>';
			el.innerHTML = text;

			// 현재노드와 다음노드의 차이를 구해서 부모노드를 구한다.
			node = getParentOrgNode(node.appendChild(el).parentNode, cha);
		}
	}
}

function form_reset(){
	$('#searchform #keyword').val("");
	makeOrgTree();
	$("#selectOrg").empty();
	$("#selectOrg").append("<li class='no_data'>선택된 항목이 없습니다.</li>");
}


/** 검색시 새로운 트리를 만든다. */
function loadOrgTreeSearch(result, topTxt){
	var root = document.getElementById('tree1');
	var max = root.childNodes.length;
	for(var i=0; i<max; i++){
		root.removeChild(root.lastChild);
	}
	var first = document.createElement('li');
	first.setAttribute('class', 'base');
	first.innerHTML = '<span class="base1">'+topTxt+'</span>';
	var node = root.appendChild(first).parentNode;

	// 노드수만큼 루프를 돌리며 다음 노드의 뎁스와 현재노드의 뎁스를 비교하여 트리를 구성한다.
	for(var i=0; i<result.length; i++){
		var el = document.createElement('li');
		if(i == result.length-1)
			el.setAttribute('class', 'last');

		var text = '<span class="list"><a href="#sel" onclick="return selectOrgPart(this);" deptId="'+result[i].deptId+'">'+result[i].deptName+'</a></span>';
		el.innerHTML = text;
		node = node.appendChild(el).parentNode;
	}
}

/** 조직도를 구성한다. */
function makeOrgTree(topTxt){
	DwrCommonService.dwrSelectDeptInfo('', function(result){
		loadOrgTree(result, topTxt);

		$("#tree1").treeview({
			collapsed: true,
			prerendered:true,
			// 펼쳐짐 속도 빠르게 하고싶을땐 "fast"
			animated: "fast",
			// 확장취소 / 전체확장을 감싸줄div의 id값
			cookie:true,
			// 저장될 쿠키이름을 지정합니다. 여러개일경우 다른쿠키명을 지정하여야 합니다.
			cookieId:"cookie1"
		});
	});
	return false;
}

/** 부서 검색시 새로운 조직도를 구성한다. */
function orgTreeSearch(topTxt){
	var form = $('#searchform')[0];
	var index = form.searchCondition.selectedIndex;
	var keyword = form.keyword.value;
	if(keyword == ''){
		alert('이름을 입력하세요.');
		return false;
	}

	if(index == 1){
		DwrCommonService.dwrSelectDeptSearchInfo(keyword, function(result){
			loadOrgTreeSearch(result, topTxt);

			$("#tree1").treeview({
				collapsed: true,
				prerendered:true,
				// 펼쳐짐 속도 빠르게 하고싶을땐 "fast"
				animated: "fast",
				// 확장취소 / 전체확장을 감싸줄div의 id값
				cookie:true,
				// 저장될 쿠키이름을 지정합니다. 여러개일경우 다른쿠키명을 지정하여야 합니다.
				cookieId:"cookie1"
			});

		});
	}
	else{
		DwrCommonService.selectStaffInfoByStaffName(keyword, function(result){
			$("#selectOrg li").remove();
			if(result.length == 0 ){
				$("#selectOrg").append("<li class='no_data'>선택된 항목이 없습니다.</li>");
			}

			for (var i = 0; i < result.length; i++) {
				var usrId = result[i].usrId;
				var usrNm = result[i].usrNm;
				var blgTeamNm = result[i].blgTeamNm;
				var realUseDepNm = result[i].realUseDepNm;
				var realUseDepCode = result[i].realUseDepCode;
				var telno = result[i].telno;

				$("#selectOrg").append("<li id="+usrId+" onclick=\"addStaffInsert('"+usrId+"','"+realUseDepNm+"','"+realUseDepCode+"','"+telno+"');\" ><span>"+blgTeamNm+"</span><span>"+usrNm+"</span></li>");
			}

		});
	}
	return false;
}

/** 조직도의 노드를 선택하면 그 부서의 사용자 목록을 우측 셀렉트 박스에 표시한다. */
function selectOrgPart(el){
	var deptId = el.getAttribute('deptId');

	if(deptId != null && deptId != ""){
		DwrCommonService.selectDeptIdtoStaffInfo(deptId, {callback:function(result){
			$("#selectOrg li").remove();
			if(result.length == 0 ){
				$("#selectOrg").append("<li class='no_data'>선택된 항목이 없습니다.</li>");
			}

			for (var i = 0; i < result.length; i++) {
				var usrId = result[i].usrId;
				var usrNm = result[i].usrNm;
				var clssNm = result[i].clssNm;
				var realUseDepNm = result[i].realUseDepNm;
				var realUseDepCode = result[i].realUseDepCode;
				var telno = result[i].telno;

				$("#selectOrg").append("<li id="+usrId+" onclick=\"addStaffInsert('"+usrId+"','"+realUseDepNm+"','"+realUseDepCode+"','"+telno+"');\"><span>"+clssNm+"</span><span>"+usrNm+"</span></li>");
			}

		}, async:false});
	}

	return false;
}

/** 조직도의 선택된 정보를 리스트에 추가한다. */
function addUserInfo(){
	var sel = document.getElementById('selectOrg');
	var index = sel.selectedIndex;
	if(index < 0) return false;

	var form = $('#selectedform')[0];
	var displaySel = form.selectedNames;
	var obj = sel.options[index];

	var opt = new Option(obj.text, obj.value);
	displaySel.options[0] = opt;
	sel.options[index] = null;
	setUserCodeInfo();
	return false;
}

/** 리스트에서 조직도의 선택된 정보를 삭제한다. */
function removeUserInfo(){
	var form = $('#selectedform')[0];
	var displaySel = form.selectedNames;
	var index = displaySel.selectedIndex;
	if(index < 0) return false;

	displaySel.options[index] = null;
	return false;
}

/** 조직도 구성시 부모노드를 찾는다. */
function getParentOrgNode(node, cha){
	cha = cha*2;	// 자식노드를 갖는 구조에서 2단계의 노드를 추가하였으므로, 부모노드를 찾을 시에도 두번의 부모노드를 찾는다.
	while(cha != 0){
		node = node.parentNode;
		cha--;
	}
	return node;
}

/** 선택된 조직도의 부서를 셀렉트 박스에 세팅한다.(일정) */
function setUserCodeInfo(){
	var form = $('#selectedform')[0];
	var displaySel = form.selectedNames;
	var max = displaySel.length;
	if(max < 1) {
		alert('선택된 인물이 없습니다.');
		return false;
	}

	var ids = '';
	var names = '';

	for(var i=0; i<max; i++){
		var obj = displaySel.options[i];
		if(obj.value == '') continue;
		if(ids == ''){
			ids = obj.value;
			names = obj.text;
		}else{
			ids += ','+obj.value;
			names += ','+obj.text;
		}
	}
	window.returnValue = {usrId:ids,usrNm:names};
	window.close();
}

/** 인물검색 팝업을 띄었을 때 초기화 세팅을 하고자는 사용자가 있으면 세팅해준다. */
function initUserInfo(){
	var obj = window.dialogArguments;

	if(obj.uId == "") return;

	var seqArray = obj.uId.split(",");
	var nameArray = obj.uNm.split(",");
	var form = $('#selectedform')[0];
	var displaySel = form.selectedNames;
	displaySel.options.length = 0;

	for(var i=0; i<seqArray.length; i++){
		var opt = new Option(nameArray[i], seqArray[i]);
		displaySel.options[i] = opt;
	}
}


function addStaffInsert(usrId, deptNm, deptCode, telno){
	$('#selectOrg li').attr("style", "");
	jQuery("#selectOrg #"+usrId).attr("style", "background:#c0d2ec");
	selectedStaffId = usrId;
	selectedStaffNm = jQuery("#selectOrg #"+usrId+" span:last-child").text();
	selectedStaffDept = deptNm;
	selectedStaffDeptCode = deptCode;
	selectedStaffTel = telno;
	return false;
}


/** 선택된 조직도의 부서를 셀렉트 박스에 세팅한다.(일정) */
function setStaffOnlyCodeInfo(openType){
	console.log(openType);
	var usrId = (selectedStaffId ? selectedStaffId : "-");
	var usrNm = (selectedStaffNm ? selectedStaffNm : "-");
	var deptNm = (selectedStaffDept ? selectedStaffDept : "-");
	var deptCode = (selectedStaffDeptCode ? selectedStaffDeptCode : "-");
	var usrTel = (selectedStaffTel ? selectedStaffTel : "-");
	var sortNum = (selectedStaffSortNum ? selectedStaffSortNum : "0");

	if (openType === "menu") {
		if ($("#chargeTable > tbody > tr", opener.document).eq(0).find("td").attr("colspan") === "6") {	//no data
			$("#chargeTable > tbody > tr", opener.document).remove();
			$("#chargeTable > tbody", opener.document).append("<tr data-chargeId=\""+usrId+"\">" +
																"<td><span>1</span>" +
																	getHiddenEle("1", "usrId", "chargeId", usrId) +
																	getHiddenEle("1", "deptCode", "chargeDepCode", deptCode) +
																	getHiddenEle("1", "usrNm", "chargeFnm", usrNm) +
																	getHiddenEle("1", "usrTel", "chargeTel", usrTel) +
																	getHiddenEle("1", "deptNm", "chargeDepNm", deptNm) +
																"</td>" +
																"<td>"+usrNm+"</td>" +
																"<td>"+deptNm+"</td>" +
																"<td>"+usrTel+"</td>" +
																"<td><span class='sortSpan hidden'>"+sortNum+"</span>" +
																"<input type='text' name='sortNum' value='"+ sortNum+"'class='sortInput input50 textCenter' onKeyPress='return numkeyCheck(event)' onkeyup='setSortNumData()' maxlength='2'/></td>" +
																"<td><a href=\"#\" onclick=\"rmCharge('"+usrId+"', '"+deptCode+"', '"+usrNm+"', '"+deptNm+"', '"+usrTel+"'); return false;\" class=\"btn_swhite\">삭제</a></td>" +
																"</tr>");
		} else {
			var trCnt = $("#chargeTable > tbody > tr", opener.document).length;
			$("#chargeTable > tbody", opener.document).append("<tr data-chargeId=\""+usrId+"\">" +
																"<td><span>"+(trCnt+1)+"</span>"+
																	getHiddenEle((trCnt+1), "usrId", "chargeId", usrId) +
																	getHiddenEle((trCnt+1), "deptCode", "chargeDepCode", deptCode) +
																	getHiddenEle((trCnt+1), "usrNm", "chargeFnm", usrNm) +
																	getHiddenEle((trCnt+1), "usrTel", "chargeTel", usrTel) +
																	getHiddenEle((trCnt+1), "deptNm", "chargeDepNm", deptNm) +
																"</td>" +
																"<td>"+usrNm+"</td>" +
																"<td>"+deptNm+"</td>" +
																"<td>"+usrTel+"</td>" +
																"<td><span class='sortSpan hidden'>"+sortNum+"</span>" +
																"<input type='text' name='sortNum' value='"+ sortNum+"'class='sortInput input50 textCenter' onKeyPress='return numkeyCheck(event)' onkeyup='setSortNumData()' maxlength='2'/></td>" +
																"<td><a href=\"#\" onclick=\"rmCharge('"+usrId+"', '"+deptCode+"', '"+usrNm+"', '"+deptNm+"', '"+usrTel+"'); return false;\" class=\"btn_swhite\">삭제</a></td>" +
																"</tr>");
		}
	} else if(openType == 'onlyMyPage') {
		$("#usrId",opener.document).val(usrId);
		$("#usrNm",opener.document).val(usrNm);
		$("#deptNm",opener.document).val(deptNm);
		$("#deptCode",opener.document).val(deptCode);
		$("#usrTel",opener.document).val(usrTel);

		window.opener.moveCharge();
	} else {
		$("#usrId",opener.document).val($("#usrId",opener.document).val()+usrId);
		$("#usrNm",opener.document).val($("#usrNm",opener.document).val()+usrNm);
		$("#deptNm",opener.document).val($("#deptNm",opener.document).val()+deptNm);
		$("#deptCode",opener.document).val($("#deptCode",opener.document).val()+deptCode);
		$("#usrTel",opener.document).val($("#usrTel",opener.document).val()+usrTel);
	}

	window.close();
}

function getHiddenEle(idx, id, name, value) {
	return "<input type=\"hidden\" id=\""+id+idx+"\" name=\""+name+"\" value=\""+value+"\"/>";
}