package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エラーコマンドとメッセージの格納変数
		String error = "";
		String cmd = "";

		// 例外処理
		try {
			// Userオブジェクトの生成
			User user = new User();
			// UserDAOオブジェクトの生成
			UserDAO userDao = new UserDAO();

			// 画面からの情報のエンコード設定
			request.setCharacterEncoding("UTF-8");

			// 以下ユーザー名の重複確認

			// 全ユーザー情報を取得
			ArrayList<User> userList = userDao.selectAll();

			// 新規登録情報のユーザー名格納用変数
			String newUserName;

			// 全ユーザーのユーザー名との重複を確認
			for (int i = 0; i < userList.size(); i++) {
				// 画面からの情報を取得
				newUserName = request.getParameter("user_name");

				// 新規ユーザー名と既存のユーザー名を比較
				if (newUserName.equals(userList.get(i).getUserName())) {
					// エラーメッセージをリクエストスコープに格納する
					error = "そのユーザー名は既に使われています！";
					request.setAttribute("error", error);

					// 再度新規登録画面に遷移する
					request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
					return;
				}
			}

			// 画面からの情報取得およびUserオブジェクトに格納
			user.setName(request.getParameter("name"));
			user.setUserName(request.getParameter("user_name"));
			user.setAddress(request.getParameter("address"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setAuthority("2");

			// 新規ユーザー登録を行う
			userDao.insert(user);

			// 登録完了後ログイン画面に遷移
			request.setAttribute("error", "ユーザーの新規登録が完了しました。");
			request.setAttribute("cmd", "login");
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);

		} catch (IllegalStateException e) {
			// DB接続エラー
			error = "DB接続エラーが発生しました。";
			cmd = "login";
			// リクエストスコープにメッセージとコマンドを格納しエラー画面に遷移
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}

	}
}
