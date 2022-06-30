<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.*,util.*"%>
<%
	MyFormat myFormat = new MyFormat();
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
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td width="80">[<a
				href="<%=request.getContextPath()%>/view/registration.jsp">出品</a>]
			</td>
			<td width="508" align="center"><font size="5">取引一覧</font></td>
			<td width="80">&nbsp;</td>
			<td width="80">&nbsp;</td>
		</tr>
	</table>
	<hr align="center" size="2" color="black"></hr>

	<br>

	<%
		ArrayList<Trade> list = (ArrayList<Trade>) request.getAttribute("tradeList");
		if (list != null && list.size() > 0) {
	%>
	<table align="center">
		<tr>
			<th style="background-color: #6666ff;">出品者</th>
			<th style="background-color: #6666ff;">商品名</th>
			<th style="background-color: #6666ff;">カテゴリー</th>
			<th style="background-color: #6666ff;">商品の状態</th>
			<th style="background-color: #6666ff;">価格</th>
			<th style="background-color: #6666ff;">取引状況</th>
			<th style="background-color: #6666ff;">取引詳細</th>
			<th style="background-color: #6666ff;">メッセージ</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
					Trade trade = (Trade) list.get(i);
		%>
		<tr>
			<td style="text-align: center;"><%=trade.getUserName()%></td>
			<td style="text-align: center;"><%=trade.getItemName()%></td>
			<td style="text-align: center;"><%=myFormat.categoryFormat(trade.getCategory())%></td>
			<td style="text-align: center;"><%=myFormat.itemStateFormat(trade.getItemState())%></td>
			<td style="text-align: center;"><%=myFormat.moneyFormat(trade.getPrice())%></td>
			<td style="text-align: center;"><%=myFormat.tradeFormat(trade.getTradeState())%></td>
			<td align="center"><a
				href="<%=request.getContextPath()%>/detail?itemId=<%=trade.getItemId()%>">取引詳細へ</a>
			</td>
			<td align="center"><a
				href="<%=request.getContextPath()%>/messageDetail?itemId=<%=trade.getItemId()%>">メッセージへ</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		} else {
	%>
	<p style="text-align: center;">取引一覧はありません。</p>
	<%
		}
	%>

	<br>
	<br>
	<br>
	<%@include file="/common/footer.jsp"%>
</body>
</html>