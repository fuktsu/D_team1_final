<%@page contentType="text/html; charset=UTF-8"%>
<%@page
	import="java.util.ArrayList, bean.Item, bean.Message, bean.User, dao.UserDAO"%>

<%
	int itemId = (int) request.getAttribute("itemId");
	Item item = (Item) request.getAttribute("item");
	int sellerId = (int) request.getAttribute("sellerId");
	ArrayList<Message> messageList = (ArrayList<Message>) request.getAttribute("messageList");
	User user = (User) session.getAttribute("user");
	UserDAO userDAO = new UserDAO();

	// セッション切れか確認
	if (user == null) {
		// セッション切れならerror.jspへフォワード
		//request.setAttribute("error","セッション切れの為、メニュー画面が表示できませんでした。");
		//request.setAttribute("cmd","logout");
		request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		return;
	}
%>

<html>
<head>
<meta charset="utf-8">
<title>メッセージ</title>
</head>

<body>
	<!-- ヘッダー -->
	<%@include file="/common/header.jsp"%>

	<!-- メニュー -->
	<table align="center" width="100%">
		<tr>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/messageList">メッセージ一覧</a>]
			</td>

			<!-- ページタイトル -->
			<td width="508" align="center"><font size="5">メッセージ</font></td>
			<td width="80">&nbsp;</td>
			<td width="80">&nbsp;</td>
		</tr>
	</table>
	<hr align="center" size="2" color="black">
	</hr>

	<br>

	<!-- メッセージのコンテンツ -->
	<%
		if (messageList != null) {
			if (messageList.size() > 0) {
	%>
	<table align="center" style="width: 80%;">
		<thead>
			<tr>
				<th style="background-color: #6666ff; width: 40%;">受信済みメッセージ</th>
				<th style="background-color: #6666ff; width: 40%;">送信済みメッセージ</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Message message : messageList) {
							if (user.getUserId() == message.getSender()) {
			%>
			<tr>
				<td align="center"></td>
				<td style="text-align: center;"><%=message.getMessage()%></td>
			</tr>
			<%
				} else {
			%>
			<tr>
				<td style="text-align: center;"><%=message.getMessage()%>(<%=userDAO.selectByUserId(message.getSender()).getUserName()%>さん)</td>
				<td align="center"></td>
			</tr>
			<%
				}
						}
					}
			%>
		</tbody>
	</table>
	<%
		}
	%>

	<br>
	<br>

	<form action="<%=request.getContextPath()%>/sendMessage" method="get">
		<table align="center" style="width: 60%">
			<tr>
				<td><textarea required name="message" maxlength="140"
						style="resize: vertical; width: 100%;"></textarea></td>
			</tr>
			<tr>
				<td align="center"><input type="hidden" name="itemId"
					value="<%=itemId%>"> <input type="hidden" name="receiver"
					value="<%=sellerId%>"><input type="submit" value="送信"></td>
			</tr>
		</table>
	</form>

	<br>
	<br>
	<br>
	<br>
	<br>

	<!-- フッター -->
	<%@include file="/common/footer.jsp"%>
</body>
</html>