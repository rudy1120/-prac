<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="/plugin/SmartEditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
    elPlaceHolder: "bContent",
    sSkinURI: "/plugin/SmartEditor/SmartEditor2Skin.html",
    fCreator: "createSEditor2",
    id: "editoriframe",
    fOnAppLoad: function () {
//         oEditors.getById["seBody"].exec("PASTE_HTML", []);
    }
});
</script>