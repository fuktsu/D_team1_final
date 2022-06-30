<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User"%>

<%
	//セッションからユーザー情報を取得
	User user1 = (User) session.getAttribute("user");
%>
<div style="text-align: center;">
	<img src="<%=request.getContextPath()%>/img/freefree_logo.png" alt="ロゴ"
		title="フリフリ" height="80px">
</div>
<%
	if (user1 != null && user1.getAuthority().equals("1")) {
%>
<hr align="center" size=" 5 " color=" green ">
<%
	} else {
%>
<hr align="center" size="5" color="blue">
<%
	}
%>

