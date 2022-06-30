<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.LatestMessage"%>

<%
	ArrayList<LatestMessage> latestMessageList = (ArrayList<LatestMessage>) request
			.getAttribute("latestMessageList");
%>

<html>
<head>
<title>メッセージ一覧</title>
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
			<td width="80">&nbsp;</td>
			<!-- ページタイトル -->
			<td width="508" align="center"><font size="5">メッセージ一覧</font></td>
			<td width="80">&nbsp;</td>
			<td width="80">&nbsp;</td>
		</tr>
	</table>
	<hr align="center" size="2" color="black">
	</hr>

	<br>

	<!-- メッセージ一覧のコンテンツ -->
	<%
		if (latestMessageList != null && latestMessageList.size() > 0) {
	%>
	<table align="center">
		<thead>
			<tr>
				<th style="background-color: #6666ff;">出品者</th>
				<th style="background-color: #6666ff;">商品名</th>
				<th style="background-color: #6666ff;">最新のメッセージ内容</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (LatestMessage latestMessage : latestMessageList) {
			%>
			<tr>
				<td style="text-align: center;"><%=latestMessage.getUserName()%></td>
				<td style="text-align: center;"><%=latestMessage.getItemName()%></td>
				<td align="center"><a
					href="<%=request.getContextPath()%>/messageDetail?itemId=<%=latestMessage.getItemId()%>"><%=latestMessage.getMessage()%></a>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		} else {
	%>
	<p style="text-align: center;">まだメッセージのやり取りはしていません。</p>
	<%
		}
	%>

	<br>
	<br>
	<br>
	<br>
	<br>

	<!-- フッター -->
	<%@include file="/common/footer.jsp"%>
</body>
</html>