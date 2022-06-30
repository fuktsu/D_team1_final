<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User"%>

<%
	// 各データをセッションから取得
	String userName = "";
	String password = "";
	String error = (String) request.getAttribute("error");
	Cookie[] cookieList = request.getCookies(); // クッキーの取得
	if (cookieList != null) {
		for (int i = 0; i < cookieList.length; i++) {
			//クッキーからユーザー情報の取得
			if (cookieList[i].getName().equals("userName")) {
				userName = cookieList[i].getValue();
			}
			//クッキーからパスワード情報の取得
			if (cookieList[i].getName().equals("password")) {
				password = cookieList[i].getValue();
			}
		}
	}

	if (error == null) {
		error = "";
	}
	String cmd = (String) request.getAttribute("cmd");
	if (cmd == null) {
		cmd = "menu";
	}
%>

<html>
<head>
<title>フリフリ</title>
<meta charset="utf-8">
</head>

<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>

	<hr align="center" size="2" color="black"></hr>
	<p align="center">
		<font size="5">ログイン</font>
	</p>
	<hr align="center" size="2" color="black"></hr>
	<p>&nbsp;</p>

	<p style="color: red; text-align: center;">
		<%
			if (cmd.equals("login")) {
		%>
		<%=error%>
		<%
			}
		%>
	</p>

	<form action="<%=request.getContextPath()%>/login" method="post">
		<table align="center">

			<tr>
				<th bgcolor="#6666ff" width="150">ユーザー名</th>
				<td><input type="text" name="userName" required
					value="<%=userName%>"></td>
			</tr>
			<tr>
				<th bgcolor="#6666ff" width="150">パスワード</th>
				<td><input type="password" name="password" required
					value="<%=password%>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">&nbsp;</td>
				<td></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="ログイン"></td>
				<td></td>
			</tr>
		</table>
	</form>
	<form action="<%=request.getContextPath()%>/view/signup.jsp"
		method="get">
		<table align="center">
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="新規登録"></td>
				<td></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>

	<!-- フッター部分 -->
	<%@include file="/common/footer.jsp"%>
</body>
</html>