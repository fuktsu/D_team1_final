<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.*,util.*"%>
<%MyFormat myFormat = new MyFormat(); %>
<%
User user = (User)request.getAttribute("myUser");
User sellerUser = (User)request.getAttribute("sellerUser");
User buyerUser = (User)request.getAttribute("buyerUser");
Item item = (Item)request.getAttribute("item");
%>
<html>
	<head>
		<title>フリフリ</title>
		<meta charset="utf-8">
	</head>
	<body>
		<%@include file="/common/header.jsp" %>
		<table align="center" width="100%">
			<tr>
				<td width="80">[<a href="<%= request.getContextPath()%>/view/menu.jsp">メニュー</a>]</td>
				<td width="80">[<a href="<%= request.getContextPath()%>/tradeList">取引一覧</a>]</td>
				<td width="508" align="center" ><font size="5">取引詳細</font></td>
				<td width="80">&nbsp;</td>
				<td width="80">&nbsp;</td>
			</tr>
		</table>
		<hr align="center" size="2" color="black"></hr>

		<br>

		<table align="center">
			<tr>
				<th style="background-color:#6666ff;">　出品者　</th>
				<td style="text-align:center;"><%=sellerUser.getUserName() %></td>
			</tr>
			<tr>
				<th style="background-color:#6666ff;">　商品名　</th>
				<td style="text-align:center;"><%=item.getItemName() %></td>
			</tr>
			<tr>
				<th style="background-color:#6666ff;">　カテゴリー　</th>
				<td style="text-align:center;"><%=myFormat.categoryFormat(item.getCategory()) %></td>
			</tr>
			<tr>
				<th style="background-color:#6666ff;">　商品の状態　</th>
				<td style="text-align:center;"><%=myFormat.itemStateFormat(item.getItemState()) %></td>
			</tr>
			<tr>
				<th style="background-color:#6666ff;">　価格　</th>
				<td style="text-align:center;"><%=myFormat.moneyFormat(item.getPrice())%></td>
			</tr>
			<tr>
				<th style="background-color:#6666ff;">　取引状況　</th>
				<td style="text-align:center;"><%=myFormat.tradeFormat(item.getTradeState()) %></td>
			</tr>
			<%
			if(item.getTradeState().equals("3")||item.getTradeState().equals("4")){
			%>
				<tr>
					<th style="background-color:#6666ff;">　出品者住所　</th>
					<td style="text-align:center;"><%=sellerUser.getAddress() %></td>
				</tr>
				<tr>
					<th style="background-color:#6666ff;">　出品者氏名　</th>
					<td style="text-align:center;"><%=sellerUser.getName() %></td>
				</tr>
				<tr>
					<th style="background-color:#6666ff;">　購入者住所　</th>
					<td style="text-align:center;"><%=buyerUser.getAddress() %></td>
				</tr>
				<tr>
					<th style="background-color:#6666ff;">　購入者氏名　</th>
					<td style="text-align:center;"><%=buyerUser.getName() %></td>
				</tr>
			<%} %>
			<tr>
				<th style="background-color:#6666ff;">　メッセージ　</th>
				<td align="center">
					<a href="<%= request.getContextPath()%>/messageDetail?itemId=<%=item.getItemId() %>">メッセージへ</a>
				</td>
			</tr>
		</table>
		<p></p>
		<table align="center">
			<tr>
				<%if(item.getSellerId()==user.getUserId()){%>
					<%if(item.getTradeState().equals("1")){%>

					<td align="center" width="150">

					</td>
					<td align="center" width="150">
						<form method="post" action="<%=request.getContextPath()%>/updateStatus">
							<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
							<input type="hidden" name="cmd" value="itemDelete">
							<input type="submit" value="出品取りやめ">
						</form>
					</td>
					<%}else if(item.getTradeState().equals("3")){%>

						<td align="center" width="150">

						</td>
						<td align="center" width="150">
							<form method="post" action="<%=request.getContextPath()%>/updateStatus">
								<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
								<input type="hidden" name="cmd" value="delivery">
								<input type="submit" value="発送">
							</form>
						</td>
					<%}else{ %>
						<td align="center" width="150">

						</td>
						<td align="center" width="150">

						</td>
					<%}%>
				<%}else if(item.getBuyerId()==user.getUserId()){%>
					<%if(item.getTradeState().equals("2")){%>
					<td align="center" width="150">
						<form method="post" action="<%=request.getContextPath()%>/updateStatus">
							<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
							<input type="hidden" name="cmd" value="buyCancel">
							<input type="submit" value="購入キャンセル">
						</form>
					</td>
					<td align="center" width="150">
						<form method="post" action="<%=request.getContextPath()%>/updateStatus">
							<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
							<input type="hidden" name="cmd" value="payment">
							<input type="submit" value="入金">
						</form>
					</td>
					<%}else{ %>
						<td align="center" width="150">

						</td>
						<td align="center" width="150">

						</td>
					<%} %>
				<%} %>
			</tr>
		</table>

		<br><br>
		<%@include file="/common/footer.jsp" %>
	</body>
</html>