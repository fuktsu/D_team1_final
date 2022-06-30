package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Message;
import bean.User;
import dao.MessageDAO;

public class SendMessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エラーメッセージ格納用の変数
		String error = "";

		// error.jspからの遷移先指定用の変数
		String cmd = "";

		// itemId格納用の変数
		int itemId = 0;

		try {
			// セッションオブジェクトの生成
			HttpSession session = request.getSession();

			// Messageクラスのオブジェクトを生成
			Message message = new Message();

			// MessageDAOクラスのオブジェクトを生成
			MessageDAO messageDAO = new MessageDAO();

			// セッションから"user"のUserオブジェクトを取得
			User user = (User) session.getAttribute("user");

			// 画面から送信されるitemId情報を受け取るためのエンコードを設定
			request.setCharacterEncoding("UTF-8");

			// 画面から送信されるitemId情報と入力したメッセージ、受信者のuserIdを受け取る
			itemId = Integer.parseInt(request.getParameter("itemId"));
			String messageText = request.getParameter("message");
			int receiverId = Integer.parseInt(request.getParameter("receiver"));

			// 画面からの入力情報を受け取り、Messageオブジェクトに格納
			message.setItemId(itemId);
			message.setSender(user.getUserId());
			message.setReceiver(receiverId);
			message.setMessage(messageText);

			// MessageDAOクラスに定義したinsert()メソッドを利用して、Messageオブジェクトに格納されたメッセージをデータベースに登録
			messageDAO.insert(message);
		} catch (IllegalStateException e) {
			// DB接続エラー
			error = "DB接続エラーが発生しました。";
			cmd = "login";
		} catch (NumberFormatException e) {
			// itemIdが数値以外
			error = "";
			cmd = "";
		} finally {
			// エラーの有無でフォワード先を分岐させる
			if (error.equals("")) {
				// エラーが無い場合、messageDetail.jspにリダイレクト
				response.sendRedirect(request.getContextPath() + "/messageDetail?itemId=" + itemId);
			} else {
				// エラーが有る場合、error.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
