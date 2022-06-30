<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User"%>

<%
//セッションからユーザー情報を取得
User user2 = (User)session.getAttribute("user");
%>
<%if(user2 != null&&user2.getAuthority().equals("1")){%>
	<hr align = "center" size = "5" color = "green" >
<%}else{%>
	<hr align = "center" size = "5" color = "blue" >
<%}%>
<table border=0 align="center" summary="フッター表示">
	<tr><td>copyright (c) 2022 all rights reserved.</td></tr>
</table>