<%@page contentType="text/html; charset=UTF-8" %>

<%
String error = (String)request.getAttribute("error");
String cmd = (String)request.getAttribute("cmd");
if(error==null){
	error="エラーが発生しました。";
}
%>
<html>
	<head>
		<title>フリフリ</title>
	</head>
	<body>
		<%@include file="/common/header.jsp" %>
		<p align="center"><font size="5">エラー</font></p>
		<hr align="center" size="2" color="black"></hr>
		<p>&nbsp;</p>
		<p align="center"><%= error%></p>
		<p>&nbsp;</p>
		<p align="center">
			<%if(cmd!=null&&cmd.equals("menu")){%>
				<a href="<%=request.getContextPath() %>/view/menu.jsp">メニューに戻る</a>
			<%}else{%>
				<a href="<%=request.getContextPath() %>/logout">ログイン画面へ</a>
			<%} %>
		</p>
		<br><br><br><br>

		<%@include file="/common/footer.jsp" %>
	</body>
</html>