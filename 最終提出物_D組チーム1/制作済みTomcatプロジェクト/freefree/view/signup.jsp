<%@page contentType="text/html; charset=UTF-8" %>

<%
	String error = (String)request.getAttribute("error");
%>
<html>
	<head>
		<title>新規登録</title>
	</head>
	<body>
		<!-- ヘッダー -->
		<%@include file="/common/header.jsp"%>

		<!-- メニュー -->
		<table align="center" width="100%">
			<tr>
				<td width="80">[<a href="<%= request.getContextPath() %>/view/login.jsp">ログイン</a>]</td>
				<td width="80">&nbsp;</td>
				<td width="508" align="center" ><font size="5">新規登録</font></td>
				<td width="80">&nbsp;</td>
				<td width="80">&nbsp;</td>
			</tr>
		</table>
		<hr align="center" size="2" color="black"></hr>

		<br><br>

		<%
		if(error != null){
		%>
		<h4 align="center"><font color="red"><%= error %></font></h4>
		<%
		}
		%>

		<!-- 登録フォーム -->
		<form action="<%= request.getContextPath()%>/signup" method="get">
			<table align="center">

				<tr>
					<th bgcolor="#6666ff" width="150">氏名</th>
					<td><input type="text" name="name" required></td>
				</tr>
				<tr>
					<th bgcolor="#6666ff" width="150">ユーザー名</th>
					<td><input type="text" name="user_name" required></td>
				</tr>
				<tr>
					<th bgcolor="#6666ff" width="150">住所</th>
					<td><textarea name="address" required></textarea></td>
				</tr>
				<tr>
					<th bgcolor="#6666ff" width="150">メールアドレス</th>
					<td><input type="text" name="email" required></td>
				</tr>
				<tr>
					<th bgcolor="#6666ff" width="150">パスワード</th>
					<td><input type="password" name="password" required></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"value="登録"></td>
					<td></td>
				</tr>

			</table>
		</form>
		<br><br><br>

		<!-- フッター -->
		<%@include file="/common/footer.jsp"%>
	</body>
</html>