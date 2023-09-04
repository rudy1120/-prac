<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	String svrName = (String)request.getServerName().toString(); %>
<%-- <div id="debugUms" style="display:none;"><%=svrName %></div> --%>
<% String httpUrl = javax.servlet.http.HttpUtils.getRequestURL(request).toString(); %>

<%if( httpUrl.indexOf("https://") > -1){%>
	<%if("118.45.184.67".indexOf(svrName) > -1 && request.getServerPort()==5555) {%>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=0581324cf4e2c054bb16e67415da4d4e"></script>
	<%}else if("localhost".indexOf(svrName) > -1 && request.getServerPort()==8080) {%>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=0581324cf4e2c054bb16e67415da4d4e"></script>
	<%} else if("gumi.yhproject.kr".indexOf(svrName) > -1) {%>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=4b9bd47cec74ce3df11e89281a735d45"></script>
	<%} else if("www.gumi.go.kr".indexOf(svrName) > -1) {%>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=4b9bd47cec74ce3df11e89281a735d45"></script>
	<%} %>
	<!-- <script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script> -->
<%} else{ %>
	<%if("118.45.184.67".indexOf(svrName) > -1 && request.getServerPort()==5555) {%>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=0581324cf4e2c054bb16e67415da4d4e"></script>
	<%}else if("localhost".indexOf(svrName) > -1&& request.getServerPort()==8080) {%>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=0581324cf4e2c054bb16e67415da4d4e"></script>
	<%} else if("gumi.yhproject.kr".indexOf(svrName) > -1) {%>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=4b9bd47cec74ce3df11e89281a735d45"></script>
	<%} else if("www.gumi.go.kr".indexOf(svrName) > -1) {%>
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=4b9bd47cec74ce3df11e89281a735d45"></script>
	<%} %>
	<!-- <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> -->
<%}%>


<%--
<%} else if("61.85.36.146".indexOf(svrName) > -1) {%>
<script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&amp;key=3baf2c69b30dbe583889e4a9315937e0"></script>
--%>