<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Item, util.MyFormat"%>
<html>
	<head>
		<title>フリフリ</title>
		<meta charset="utf-8">
	</head>
	<body>
		<%@include file="/common/header.jsp" %>

		<table align="center" width="100%">
			<tr>
				<td width="80">[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td width="80">[<a href="<%= request.getContextPath() %>/tradeList">取引一覧</a>]</td>
				<td width="508" align="center" ><font size="5">出品</font></td>
				<td width="80">&nbsp;</td>
				<td width="80">&nbsp;</td>
			</tr>
		</table>
		<hr align="center" size="2" color="black"></hr>

		<br><br>
		<form action="<%= request.getContextPath()%>/registration" method="get">
			<table align="center">

				<tr>
					<td bgcolor="#6666ff" width="150">　商品名</td>
					<td><input required type="text" name="itemName"></td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="150">　カテゴリー</td>
					<td>
						<select name="category">
							<option value="1">服</option>
							<option value="2">インテリア・住まい・小物</option>
							<option value="3">本・音楽・ゲーム</option>
							<option value="4">おもちゃ・ホビー・グッズ</option>
							<option value="5">コスメ・香水・美容</option>
							<option value="6">家電・スマホ・カメラ</option>
							<option value="7">スポーツ・レジャー</option>
							<option value="8">ハンドメイド</option>
							<option value="9">自動車・オートバイ</option>
							<option value="0">その他</option>
						</select>
					</td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="150">　商品の状態</td>
					<td>
						<select name="itemState">
							<option value="1">新品・未使用</option>
							<option value="2">未使用に近い</option>
							<option value="3">目立った傷や汚れなし</option>
							<option value="4">やや傷や汚れあり</option>
							<option value="5">傷や汚れあり</option>
							<option value="6">全体に状態が悪い</option>
						</select>
					</td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="150">　価格</td>
					<td><input required type="number" min="100" max="999999999" name="price"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit"value="送信">
					</td>
				</tr>

			</table>
		</form>
		<br><br><br>

		<%@include file="/common/footer.jsp" %>
	</body>
</html>