package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LatestMessage;
import dao.LatestMessageDAO;

public class MessageListServlet extends HttpServlet {

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エラーメッセージ格納用の変数
		String error = "";

		// error.jspからの遷移先指定用の変数
		String cmd = "";

		try {
			// LatestMessageDAOクラスのオブジェクトを生成
			LatestMessageDAO latestMessageDAO = new LatestMessageDAO();

			// LatestMessageDAOクラスに定義した、selectAll()メソッドを利用して最新のメッセージを取得
			ArrayList<LatestMessage> latestMessageList = latestMessageDAO.selectAll();

			// 取得した最新のメッセージを「latestMessageList」という名前でリクエストスコープに登録
			request.setAttribute("latestMessageList", latestMessageList);
		} catch (IllegalStateException e) {
			// DB接続エラー
			error = "DB接続エラーが発生しました。";
			cmd = "login";
		} finally {
			// エラーの有無でフォワード先を分岐させる
			if (error.equals("")) {
				// エラーが無い場合、messageList.jspにフォワード
				request.getRequestDispatcher("/view/messageList.jsp").forward(request, response);
			} else {
				// エラーが有る場合、error.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
