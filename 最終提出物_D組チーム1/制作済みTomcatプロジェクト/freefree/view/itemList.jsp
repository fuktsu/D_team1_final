<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.Item, util.MyFormat"%>
<%
	ArrayList<Item> list = (ArrayList<Item>) request.getAttribute("item_list");
	MyFormat myFormat = new MyFormat();
	User user = (User) (session.getAttribute("user"));
	//セッション切れか確認
	if (user == null) {
		//セッション切れならerror.jspへフォワード
		request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
		request.setAttribute("cmd", "logout");
		request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		return;
	}
%>

<html>
<head>
<title>フリフリ</title>
<meta charset="utf-8">
</head>
<body>

	<%@include file="/common/header.jsp"%>

	<table align="center" width="100%">
		<tr>
			<%
				if (user.getAuthority().equals("2")) {
			%>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/registration.jsp">出品</a>]
			</td>
			<%
				} else {
			%>
			<td width="160">[<a
				href="<%=request.getContextPath()%>/view/adminMenu.jsp">メニュー</a>]
			</td>
			<%
				}
			%>
			<td width="508" align="center"><font size="5">出品一覧</font></td>
			<td width="80">&nbsp;</td>
			<td width="80">&nbsp;</td>
		</tr>
	</table>
	<hr align="center" size="2" color="black"></hr>

	<br>

	<table align="center">
		<tr>
			<%
				if (list != null && list.size() > 0) {
					if (user.getAuthority().equals("1")) {
			%>
			<th style="background-color: #66ff66;">商品名</th>
			<th style="background-color: #66ff66;">カテゴリー</th>
			<th style="background-color: #66ff66;">商品の状態</th>
			<th style="background-color: #66ff66;">価格</th>
			<%
				}
					if (user.getAuthority().equals("2")) {
			%>
			<th style="background-color: #6666ff;">商品名</th>
			<th style="background-color: #6666ff;">カテゴリー</th>
			<th style="background-color: #6666ff;">商品の状態</th>
			<th style="background-color: #6666ff;">価格</th>
			<th style="background-color: #6666ff;">メッセージ</th>
			<th style="background-color: #6666ff;">購入/出品キャンセル</th>
			<%
				}
			%>
		</tr>

		<%
			for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getTradeState().equals("1")) {
		%>
		<tr>
			<td style="text-align: center;"><%=list.get(i).getItemName()%></td>
			<td style="text-align: center;"><%=myFormat.categoryFormat(list.get(i).getCategory())%></td>
			<td style="text-align: center;"><%=myFormat.itemStateFormat(list.get(i).getItemState())%></td>
			<td style="text-align: center;"><%=myFormat.moneyFormat(list.get(i).getPrice())%></td>
			<%
				if (user.getAuthority().equals("2")) {
			%>
			<td style="text-align: center;"><a
				href="<%=request.getContextPath()%>/messageDetail?itemId=<%=list.get(i).getItemId()%>">メッセージへ</a>
			</td>
			<td style="text-align: center;">
				<form method="post"
					action="<%=request.getContextPath()%>/updateStatus">
					<input type="hidden" name="itemId"
						value="<%=list.get(i).getItemId()%>">
					<%
						if (list.get(i).getSellerId() != (user.getUserId())) {
					%>
					<input type="hidden" name="cmd" value="buy"> <input
						type="submit" value="購入">
					<%
						} else {
					%>
					<input type="hidden" name="cmd" value="itemDelete"> <input
						type="submit" value="出品キャンセル">
					<%
						}
					%>
				</form>
			</td>
			<%
				}
			%>
		</tr>
		<%
			}
				}
		%>
	</table>
	<%
		} else {
	%>
	<p style="text-align: center;">出品された商品はまだありません。</p>
	<%
		}
	%>

	<br>
	<br>
	<br>
	<br>
	<br>

	<%@include file="/common/footer.jsp"%>

</body>
</html>