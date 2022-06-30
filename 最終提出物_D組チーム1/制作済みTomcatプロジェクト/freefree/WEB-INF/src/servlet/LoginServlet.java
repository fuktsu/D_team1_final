package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		User user = new User();

		String error = "";
		String cmd = "";

		try {
			// userName, password入力パラメータを取得
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			// userDAOをインスタンス化し、関連メソッドを呼び出す
			UserDAO userDaoObj = new UserDAO();
			user = userDaoObj.login(userName, password);

			if (user.getUserId() == 0) {
				// throw new Exception("not login");
				error = "ユーザー名またはパスワードが間違っています。";
				cmd = "login";
				return;
			}
			// ユーザーID・パスワードを格納するクッキーの作成
			// クッキーを生成する
			Cookie userNameCookie = new Cookie("userName", userName);
			Cookie passwordCookie = new Cookie("password", password);

			// クッキーを追加する
			response.addCookie(userNameCookie);
			response.addCookie(passwordCookie);

			// セッションを作成しユーザー情報(Userオブジェクト)を
			// スコープに"user"という名前で登録する
			// セッションの作成
			HttpSession session = request.getSession();
			// セッションスコープにユーザー情報(userオブジェクト)を登録
			session.setAttribute("user", user);

		} catch (IllegalStateException e) {
			// DB接続エラー
			error = "DB接続エラーの為、ログインは出来ません。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				// メニュー画面に遷移（データベースより取得したデータの権限を参照し処理を分岐
				// 管理者ログインの場合→admin.jspにフォワードする
				if (user.getAuthority().equals("1")) {
					request.getRequestDispatcher("/view/adminMenu.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
				}
			} else {
				// エラーが有る場合
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);

				// cmdの値でフォワード先を分岐させる
				if (cmd.equals("login")) {
					// login.jspにフォワードする
					request.getRequestDispatcher("/view/login.jsp").forward(request, response);
				} else if (cmd.equals("logout")) {
					// error.jspにフォワードする
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}
		}
	}
}
