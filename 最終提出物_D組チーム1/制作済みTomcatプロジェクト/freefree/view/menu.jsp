<%@page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>フリフリ</title>
		<meta charset="utf-8">
	</head>
	<body>
		<%@include file= "../common/header.jsp" %>
		<%@include file= "../common/userInfo.jsp" %>

		<hr align="center" size="2" color="black" ></hr>
		<p align="center"><font size="5">MENU</font></p>
		<hr align="center" size="2" color="black"></hr>
		<p>&nbsp;</p>

		<ul style="list-style: none; padding-left: 0;" align="center">
			<li><a href="<%= request.getContextPath() %>/view/registration.jsp">【出品】</a></li>
			<li><a href="<%= request.getContextPath() %>/itemList">【出品一覧】</a></li>
			<li><a href="<%= request.getContextPath() %>/tradeList">【取引一覧】</a></li>
			<li><a href="<%= request.getContextPath() %>/messageList">【メッセージ一覧】</a></li>
			<li>&nbsp;</li>
			<li><a href="<%= request.getContextPath() %>/logout">【ログアウト】</a></li>
		</ul>
		<br><br><br>
		<%@include file="/common/footer.jsp" %>
	</body>
</html>