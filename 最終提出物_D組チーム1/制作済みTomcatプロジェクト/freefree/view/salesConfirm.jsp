<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.Sales,util.MyFormat"%>

<%
	MyFormat mf = new MyFormat();
	ArrayList<Sales> salesList = (ArrayList<Sales>)request.getAttribute("salesList");
%>



<html>
	<head>
		<title>売り上げ確認</title>
	</head>
	<body>
		<!-- ヘッダー -->
		<%@include file="/common/header.jsp"%>

		<!-- メニュー -->
		<table align="center" width="100%">
			<tr>
				<td width="80">&nbsp;</td>
				<td width="80">[<a href="<%= request.getContextPath() %>/view/adminMenu.jsp">メニュー</a>]</td>
				<td width="508" align="center" ><font size="5">売上確認</font></td>
				<td width="80">&nbsp;</td>
				<td width="80">&nbsp;</td>
			</tr>
		</table>
		<hr align="center" size="2" color="black"></hr>

		<br>
		<table align="center">
			<tr>
				<td>
					<form action="<%=request.getContextPath()%>/search">
						ユーザー名：<input type="text" size="15" name="userName"></input>
						年/月：<input type="month" name="month"></input>
						<input type="submit" name="search" value="絞り込み"></input>
					</form>
				</td>
				<td>
					<form action="<%=request.getContextPath()%>/salesConfirm">
						<input type="submit" name="searchall" value="全件表示"></input>
					</form>
				</td>
			</tr>
		</table>

		<!-- ユーザー一覧のコンテンツ -->
		<%
			if (salesList != null && salesList.size() > 0) {
		%>
		<table align="center">
			<tr>
				<th style="background-color:#66ff66; width:150px">　商品　</th>
				<th style="background-color:#66ff66; width:150px">　ユーザー名　</th>
				<th style="background-color:#66ff66; width:150px">　売上　</th>
				<th style="background-color:#66ff66; width:150px">　取引完了日時　</th>
			</tr>
			<%
				int total = 0;
				for (int i = 0; i < salesList.size(); i++) {
					Sales sales = salesList.get(i);
					total += sales.getPrice();
			%>
			<tr>
				<td style="text-align:center;"><%= sales.getItemName() %></td>
				<td style="text-align:center;"><%= sales.getUserName() %></td>
				<td style="text-align:center;"><%= mf.moneyFormat(sales.getPrice()) %></td>
				<td style="text-align:center;"><%= sales.getTradeDate() %></td>
			</tr>
			<%
				}

			%>
		</table>
		<hr align="center" size="2" color="gray" width="500"></hr>
		<table>
			<tr>
				<td width="800">&nbsp;</td>
				<th align="center" bgcolor="#66ff66" width="150">合計</th>
				<td align="center" width="100"><%= mf.moneyFormat(total) %></td>
			</tr>
			<tr>
				<td width="800">&nbsp;</td>
				<th align="center" bgcolor="#66ff66" width="150">管理者売上</th>
				<td align="center" width="100"><%= mf.moneyFormat(total/10) %></td>
			</tr>
		</table>
		<%
		} else {
		%>
			<p style="text-align: center;">売上情報はありません。</p>
		<%
		}
		%>

		<br><br><br><br><br>

		<!-- フッター -->
		<%@include file="/common/footer.jsp"%>
	</body>
</html>