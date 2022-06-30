<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User"%>

<%
//セッションからユーザー情報を取得
User user = (User)session.getAttribute("user");
//セッション切れか確認
if(user == null){
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、メニュー画面が表示できませんでした。");
	request.setAttribute("cmd","logout");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
String authority ="";
if(user.getAuthority().equals("1")){
	authority = "管理者";
}else{
	authority = "一般ユーザー";
}
%>

<div style = "margin-left:75%;">
	名前：<%=user.getUserName() %><br>
	権限：<%=authority %>
</div>