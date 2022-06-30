<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.User"%>


<%
	ArrayList<User> userList = (ArrayList<User>)request.getAttribute("userList");
%>


<html>
	<head>
		<title>ユーザー一覧</title>
	</head>
	<body>
		<!-- ヘッダー -->
		<%@include file="/common/header.jsp"%>

		<!-- メニュー -->
		<table align="center" width="100%">
			<tr>
				<td width="80">&nbsp;</td>
				<td width="80">[<a href="<%= request.getContextPath() %>/view/adminMenu.jsp">メニュー</a>]</td>
				<td width="508" align="center" ><font size="5">ユーザー一覧</font></td>
				<td width="80">&nbsp;</td>
				<td width="80">&nbsp;</td>
			</tr>
		</table>
		<hr align="center" size="2" color="black"></hr>

		<br>

		<!-- ユーザー一覧のコンテンツ -->
		<%
		if (userList != null && userList.size() > 1) {
		%>
		<table align="center">
			<tr>
				<th style="background-color:#66ff66;">　氏名　</th>
				<th style="background-color:#66ff66;">　ユーザー名　</th>
				<th style="background-color:#66ff66;">　住所　</th>
				<th style="background-color:#66ff66;">　メールアドレス　</th>
			</tr>
			<%
					for (int i = 0; i < userList.size(); i++) {
						User user = userList.get(i);
						if (user.getAuthority().equals("2")){
			%>
			<tr>
				<td style="text-align:center; width:100px"><%= user.getName() %></td>
				<td style="text-align:center; "><%= user.getUserName() %></td>
				<td style="text-align:center;"><%= user.getAddress() %></td>
				<td style="text-align:center;"><%= user.getEmail() %></td>
				<td align="center">
			</tr>
			<%
						}
					}
			%>
		</table>
		<%
		} else {
		%>
			<p style="text-align: center;">登録ユーザーはいません。</p>
		<%
		}
		%>


		<br><br><br><br><br>
		<!-- フッター -->
		<%@include file="/common/footer.jsp"%>
	</body>
</html>