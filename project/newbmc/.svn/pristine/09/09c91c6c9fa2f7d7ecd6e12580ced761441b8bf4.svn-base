<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>NetFUNNEL Wait Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/bmc/js/netfunnel.js" charset="UTF-8"></script>

<script type="text/javascript">
function load(){
NetFunnel_Action({action_id:"main"},function(ev,ret) {
		frm.nf_key.value = ret.data.key;
		frm.submit();
	}
	);
}
</script>
</head>
<body onload="load()">
<form id="frm" name="frm" action="/bmc/main.do" method="post" >
	<input type="hidden" id="nf_key" name="nf_key"/>
</form>
</body>
</html>
