var webFilterServerAddress = "filter.bmc.busan.kr"; 
var webFilterServerPort = "80"; 
var webFilterServerSSLPort = "443"; 
var wfcontextRoot = "";
var WFcharSet="utf8";
//var WFcharSet="euc-kr"; 

var WFdebug = false; //�Լ� debug

var WFdenyExtUse = false; 
var disableFileElements = new Array();

var webfilterFixAction = "/webfilterSubmitAction.do";
var WForgActionUrl = "";
var WFprotocol = "http://";

var destinationTarget = "";
var destinationURL = "";
var targetForm = null;

//var denyActionArr = new Array("multiBbzView!view.action"); 
//var denyParamArr = new Array("cmd=write"); 
var denyActionArr = null; 
var denyParamArr = null; 

var webFilterTargetServerAddress=''; 
var webFilterTargetServerPort=''; 

var targetServerAddress=''; 
var targetServerPort=''; 

var brCheck = browserCheck();
var orgTarget = "_self";

wf_flag = "off";
/*
 *		�����ͷ� ���� �� ���� Ÿ�� �� �׼�URL ���� Ÿ�� �� �׼�URL�� ����
 */
function restoreForm(formName){
	try{
		if(WFdenyExtUse){
			WFuncheckForm(formName);
		}

		if(destinationTarget != null && destinationTarget != "" && destinationTarget.length != 0 && destinationTarget != "webfilterTargetFrame"){
			targetForm.target = destinationTarget;
		}else{
			targetForm.target = orgTarget;
		}

		var act = targetForm.getAttributeNode('action');
		if(destinationURL != null && destinationURL != "" && destinationURL.length != 0 ){
			act.value = destinationURL;
		}
	}catch(e){
		if(WFdebug){
			alert("restoreForm() function error message = "+e.description);
		}
	}
}

/*
 *		���� ���ܵ� ÷������ Ȯ���� üũ
 */
function WFdenyExt(fileValue){
	var deny_ext_reg = /.(avi|wmv|mpeg|mpg|asf|mkv|mka|tp|ts|flv|mp4|mov|k3g|vob|skm|asx|raw|ram)$/;
    return deny_ext_reg.test(fileValue);
}

/*
 *		���� ���ܵ� ÷������ ����
 */
function WFcheckForm( formName ){
	try{
		targetForm = document.forms[formName];
		elements = targetForm.elements;
		for(j=0; elements.length > j; j++){
			element = elements[j];
			if(element.type == 'file'){
				if(WFdenyExt(element.value)){	
					element.disabled = true;	
				}
			}
		}
	}catch(e){
		if(WFdebug){
			alert("WFcheckForm() function error message = "+e.description);
		}
	}
}

/*
 *		���� ���ܵ� ÷������ ����ó��
 */
function WFuncheckForm( formName ){
	try{
		targetForm = document.forms[formName];
		disableElementCount = 0;
		elements = targetForm.elements;
		for(j=0; elements.length > j; j++){
			element = elements[j];
			if(element.type == 'file'){
				element.disabled = false;	
			}
		}
	}catch(e){
		if(WFdebug){
			alert("WFuncheckForm() function error message = "+e.description);
		}
	}
}

/*
 *		�����ͷ� ���� �� �Ķ���� ����
 */ 
function getURI(actURL, targetServerAddress, targetServerPort, formName){
	try{
		if( actURL.indexOf('http://') >=0 ){
			actionURL = actURL.substring( actURL.indexOf('/', 8 ), actURL.length);
		}else{
			actionURL = actURL;
		}
		if(WForgActionUrl.indexOf("?")>-1){
			actionURL +='?'+WForgActionUrl.substring(WForgActionUrl.indexOf("?")+1,WForgActionUrl.length);
		}
		if(targetServerAddress != null && targetServerAddress !='' && targetServerPort!=null && targetServerPort != ''){
			if( actionURL.indexOf('?') >= 0 ){
				actionURL += '&';
			}else{
				actionURL += '?';
			}
			actionURL += 'targetServer_='+targetServerAddress+'&targetPort_='+targetServerPort;
		}
		
		if( actionURL.indexOf('?') >= 0 ){
			actionURL += '&';
		}else{
			actionURL += '?';
		}
		tempListUrl = document.referrer;
		if(tempListUrl.indexOf("http://") > -1) {
			tempListUrl = tempListUrl.substring(tempListUrl.indexOf("/",tempListUrl.indexOf("http://")+7),tempListUrl.length);
		} else if(tempListUrl.indexOf("https://") > -1){
			tempListUrl = tempListUrl.substring(tempListUrl.indexOf("/",tempListUrl.indexOf("https://")+8),tempListUrl.length);
		}
		tempListUrl = WFreplaceAll(tempListUrl,"\&","WF-AMP");
		actionURL += 'serverDomain_='+window.location.hostname+'&serverPort_='+location.port+'&writeFormName_='+formName+'&serverProtocol_='+location.protocol+'&WFcharSet_='+WFcharSet;
		if(wfcontextRoot != ""){
			actionURL += '&contextRoot_='+wfcontextRoot+'/webfilter';      
		}
		WForgActionUrl = WFreplaceAll(WForgActionUrl,"\&","WF-AMP");
		actionURL += '&WFOrgAction_='+WForgActionUrl;
		actionURL += '&WFlistUrl_='+tempListUrl;
	}catch(e){
		if(WFdebug){
			alert("getURI() function error message = "+e.description);
		}
	}
	return actionURL
}


/*
 *		�����ͷ� ����
 */
function submitWebFilter( formName ){
	try{
		if(WFdebug) alert("1. ������ ���� ���� : " + formName);
		if(brCheck.indexOf("chrome") >  -1){
			restoreForm(formName);
		}
		if(WFdenyExtUse){
			WFcheckForm(formName);
		}
		targetForm = document.forms[formName];

		if(orgTarget == "_self"){
			orgTarget = targetForm.target;
		}
		destinationTarget = targetForm.target;

		if(WFdebug) alert("2. �� Ÿ�� ��� : " + destinationTarget);

		var act = targetForm.getAttributeNode('action');

		if(WFdebug) alert("3. �׼�URL ���� : " + act.value);

		destinationURL = act.value;

		if(WFdebug) alert("4. �׼�URL ��� : " + destinationURL);

		targetForm.target = 'webfilterTargetFrame';

		if(WFdebug) alert("5.  �� Ÿ�� ���� : " + targetForm.target);

		webFilterTargetServerAddress = WFtargetServerAddress(act.value);

		if(WFdebug) alert("5.1.  Ÿ�ټ��������� : " + webFilterTargetServerAddress);

		webFilterTargetServerPort = WFtargetServerPort(act.value);

		if(WFdebug) alert("5.2.  Ÿ�ټ�����Ʈ : " + webFilterTargetServerPort);

		act.value = WFcheckActionValue(act.value);
		
		if(WFdebug) alert("6. �׼�URL �����η� ���� : " + act.value);

		if(denyParamCheck(act, targetForm) && denyActionCheck(act, targetForm)){
			if(document.domain != webFilterTargetServerAddress){
				targetServerAddress = webFilterTargetServerAddress;
				targetServerPort = webFilterTargetServerPort;
			}else if(document.domain == webFilterTargetServerAddress && location.port != webFilterTargetServerPort){
				targetServerPort = WFtargetServerPort(act.value);
			}

			WFprotocol=WFcheckProtocol(act.value);

			if(WFdebug) alert("7. �������� ���� : " + WFprotocol);

			WForgActionUrl = act.value;
			if(!document.getElementById('WFCookie')){
				var cEle;
				cEle=document.createElement("INPUT");
				cEle.type="hidden";
				cEle.id="WFCookie";
				cEle.name="WFCookie";
				targetForm.appendChild(cEle);
				targetForm.WFCookie.value=WFgetCookie();
			}
			
			act.value = webfilterFixAction;

			if(WFprotocol == "https://"){
				act.value = WFprotocol + webFilterServerAddress + ":" + webFilterServerSSLPort + getURI( act.value, targetServerAddress, targetServerPort, formName );
			}else{
				act.value = WFprotocol + webFilterServerAddress  + ":" + webFilterServerPort + getURI( act.value, targetServerAddress, targetServerPort, formName );
            }
			
			if(WFdebug) alert("8. �׼�URL �����ͷ� ���� ���� : " + act.value);

			targetForm.submit();

			if(brCheck.indexOf("ie") >  -1 || brCheck.indexOf("firefox") > -1){
				restoreForm(formName);
			}

			if(WFdebug) alert("9. ������ ���� �Ϸ�");
			
		}else{
			if(WFdebug) alert("4. ������ ���� ó��");
			restoreForm(formName);
			targetForm.submit();
		}
	}catch(e){
		if(WFdebug){
			alert("submitWebFilter() function error message = "+e.description);
		}
	}
}

/*
 *		��Ű�� �˻�
 */
function WFgetCookie(){
	cookieVal = "";
	if(document.cookie.length > 0){
		cookieVal = unescape(document.cookie);
	}else{
		cookieVal = "null";
	}
	return cookieVal;
}

/*
 *		�Է¹��� actValue�� �����η� ����
 */
function WFcheckActionValue(actValue){
	tempActVal = "";
	tempDomain = actValue.substring(actValue.indexOf("://")+3,actValue.indexOf("/",actValue.indexOf("://")+3));
	tempActVal = window.location.href+"";
	tempActVal = tempActVal.substring(tempActVal.indexOf("://")+3,tempActVal.length);
	tempActVal = tempActVal.substring(tempActVal.indexOf("/"),tempActVal.length);
	if(actValue.indexOf("?") == 0){
		if(tempActVal.indexOf("?") > -1){
			tempActVal = tempActVal.substring(0,tempActVal.indexOf("?"))+actValue;
		}else{
			tempActVal = tempActVal+actValue;
		}
	}else{
		if(actValue.indexOf("://") > 0 && actValue.indexOf("://") < 6){ 
			tempActVal = actValue.substring(actValue.indexOf("://")+3,actValue.length);
			tempActVal = tempActVal.substring(tempActVal.indexOf("/"),tempActVal.length);
		}else if(actValue == ""){ 
		}else if(actValue.indexOf("..") == 0){ 
			if(tempActVal.indexOf("?") > -1){
				tempActVal = tempActVal.substring(0,tempActVal.indexOf("?"));
			}
			tempActValArray = tempActVal.split("/");
			tempActVal2 = actValue;
			actValArray = tempActVal2.split("..");
			cntPos = actValArray.length;  
			tempActionValue = "";
			for(i=0;i<cntPos;i++){
				if(tempActValArray[i] != ""){
					tempActionValue += "/"+tempActValArray[i];
				}
			}
			paramStr = "";
			for(i=0;i<actValArray.length;i++){
				if(actValArray[i] != ""){
					paramStr = actValArray[i];
				}
			}
			tempActionValue += paramStr;
			tempActVal = tempActionValue;
		}else if(actValue.indexOf(".") == 0){ 
			if(tempActVal.indexOf("?") > -1){
				tempActVal = tempActVal.substring(0,tempActVal.indexOf("?"));
			}
			tempActVal2 = actValue.substring(1,actValue.length);
			tempActionValue = tempActVal.substring(0,tempActVal.lastIndexOf("/"));
			tempActionValue += tempActVal2;
			tempActVal = tempActionValue;
		}else if(actValue.substring(0,1) == "/"){
			tempActVal = actValue;
		}else{
			if(tempActVal.indexOf("?") > -1){
				tempActVal = tempActVal.substring(0,tempActVal.indexOf("?"));
			}
			tempActValArray = tempActVal.split("/");
			tempActVal2 = actValue;
			tempActionValue = "";
			for(i=0;i<tempActValArray.length-1;i++){
				if(tempActValArray[i] != ""){
					tempActionValue += "/"+tempActValArray[i];
				}
			}
			tempActionValue += "/"+actValue;
			tempActVal = tempActionValue;
		}
	}
	return tempActVal;
}

/*
 *		�Է¹��� actValue���� �������� �˻�
 */
function WFcheckProtocol(actValue){
	tempProtocol = "http://";
	if(actValue.indexOf("https://") > -1){
		tempProtocol = "https://";
	}else{
		tempProtocol = location.protocol+"//";
	}
	
	return tempProtocol;
}

/*
 *		�Է¹��� actValue���� �׼�URL Ÿ�ټ��� ������ �˻�
 */
function WFtargetServerAddress(actValue){
	tempServerAddress = "";
	try{
		if(actValue.indexOf("://") >-1){
			tempServerAddress = actValue.substring(actValue.indexOf("://")+3,actValue.indexOf("/",actValue.indexOf("://")+3));
			if(tempServerAddress.indexOf(":") > 0 ){ 
				tempServerAddress = tempServerAddress.substring(0,tempServerAddress.indexOf(":"));
			}else{
				tempServerAddress = tempServerAddress.substring(0,tempServerAddress.length);
			}
		}
	}catch(e){
		if(WFdebug){
			alert("WFtargetServerAddress() function error message = "+e.description);
		}
	}
	return tempServerAddress;
}

/*
 *		�Է¹��� actValue���� �׼�URL ������Ʈ �˻�
 */
function WFtargetServerPort(actValue){
	tempServerPort = "";
	try{
		if(actValue.indexOf("://") >-1){
			tempServerPort = actValue.substring(actValue.indexOf("://")+3,actValue.indexOf("/",actValue.indexOf("://")+3));
			if(tempServerPort.indexOf(":") > 0 ){ 
				tempServerPort = tempServerPort.substring(tempServerPort.indexOf(":")+1,tempServerPort.length);
			}else{
				tempServerPort = "80";
			}
		}
	}catch(e){
		if(WFdebug){
			alert("WFtargetServerPort() function error message = "+e.description);
		}
	}
	return tempServerPort;
}

/*
 *		�Է¹��� strval���� str1�� str2�� ġȯ
 */
function WFreplaceAll(strval, str1,str2){
	while(1){
		if(strval.indexOf(str1) != -1)
			strval = strval.replace(str1,str2);
		else
			break;
	}
	return strval;
}

/*
 *		�Է¹��� paramUrl����  key �� �˻�
 */
function getParameter(paramUrl, key){
	var url = paramUrl;
	var spoint = url.indexOf("?");
	var query = url.substring(spoint,url.length);
	var keys = new Array;
	var values = new Array;
	var nextStartPoint = 0;
	while(query.indexOf("&",(nextStartPoint+1) ) >-1 ){
		var item = query.substring(nextStartPoint, query.indexOf("&",(nextStartPoint+1) ) );
		var p = item.indexOf("=");
		keys[keys.length] = item.substring(1,p);
		values[values.length] = item.substring(p+1,item.length);
		nextStartPoint = query.indexOf("&", (nextStartPoint+1) );
  	}
	item = query.substring(nextStartPoint, query.length);
	p = item.indexOf("=");
	keys[keys.length] = item.substring(1,p);
	values[values.length] = item.substring(p+1,item.length);
	var value = "";
	for(var i=0; i<keys.length; i++){
		if(keys[i]==key){
			value = values[i];
		}
	}
	return value;
}

/*
 *		���� �Ķ���� üũ
 */
function denyParamCheck(action, tForm){
	var denyResult = true;
	var denyPos2 = -1;
	var denyPos3 = -1;
	if(denyParamArr !=null){
		for(i=0;i<denyParamArr.length;i++){
			denyParamName = denyParamArr[i].substring(0,denyParamArr[i].indexOf("="));
			denyParamValue = denyParamArr[i].substring(denyParamArr[i].indexOf("=")+1,denyParamArr[i].length);
			if(denyParamName !=null && getParameter(action.value,denyParamName).indexOf(denyParamValue) > -1){
				denyPos2 = 0;
			}else{
				denyPos2 = -1;
			}
			if(denyPos2 != 0){
				elements = tForm.elements;
				for(j=0; elements.length>j; j++){
					element = elements[j];
					if(element.type == 'radio'){
						objRadio=document.all(element.name);
						for(k=0; objRadio.length>k; k++){
							if(objRadio[k].name == denyParamName && objRadio[k].checked == true){
								if(objRadio[k].value !=null && objRadio[k].value != "" && objRadio[k].value != "undefined" && objRadio[k].value == denyParamValue){
									denyPos3 = 0;
									break;
								}else{
									denyPos3 = -1;
								}
							}
						}
					}else{
						if(element.name == denyParamName){
							if(element.value !=null && element.value != "" && element.value != "undefined" && element.value == denyParamValue){
								denyPos3 = 0;
								break;
							}else{
								denyPos3 = -1;
							}
						}
					}
					if(denyPos3 == 0){
						break;
					}
				}
			}
			if(denyPos2 == 0 || denyPos3 == 0){
				denyResult = false;
				break;
			}else{
				denyResult = true;
			}
		}
	}else{
		denyResult = true;
	}
	return denyResult;
}

/*
 *		���� �׼�URL üũ
 */
function denyActionCheck(action, tForm){
	var denyActionResult = true;
	if(denyActionArr != null){
		for(i=0;i<denyActionArr.length;i++){
			denyActionValue = denyActionArr[i];
			if(denyActionArr[i] != null && action.value.indexOf(denyActionArr[i]) > -1){
				denyActionResult = false;
				break;
			}else{
				denyActionResult = true;
			}
		}
	}else{
		denyActionResult = true;
	}
	return denyActionResult;
}

function browserCheck(){
	var agent = navigator.userAgent.toLowerCase(),
        name = navigator.appName,
        browser = 'ie';
    
    // MS �迭 �������� �����ϱ� ����.
    if(name === 'Microsoft Internet Explorer' || agent.indexOf('trident') > -1 || agent.indexOf('edge/') > -1) {
        browser = 'ie';
    } else if(agent.indexOf('safari') > -1) { // Chrome or Safari
        if(agent.indexOf('opr') > -1) { // Opera
            browser = 'opera';
        } else if(agent.indexOf('chrome') > -1) { // Chrome
            browser = 'chrome';
        } else { // Safari
            browser = 'safari';
        }
    } else if(agent.indexOf('firefox') > -1) { // Firefox
        browser = 'firefox';
    }
	return browser;
}