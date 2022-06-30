package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Trade;
import bean.User;
import dao.TradeDAO;

public class TradeListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {

			// セッションを取得する
			HttpSession session = request.getSession();

			// セッションから"user"のUserオブジェクトを取得する。
			User user = (User) session.getAttribute("user");
			// セッション切れ
			if (user == null) {
				error = "セッション切れの為、取引一覧表示はできません。 ";
				cmd = "login";
				return;
			}

			// TradeDAOクラスのオブジェクトを生成します。
			TradeDAO objDao = new TradeDAO();

			// TradeDAOクラスに定義したselectByUserId（）メソッドを利用して取引情報を取得します。
			ArrayList<Trade> list = objDao.selectByUserId(user.getUserId());

			// 取得したすべての取引情報を「tradeList」という名前でリクエストスコープに登録します。
			request.setAttribute("tradeList", list);

			// 「/tradeList.jsp」へフォワードします。
			request.getRequestDispatcher("/view/tradeList.jsp").forward(request, response);
		} catch (IllegalStateException e) {
			error = "DB接続エラーが発生しました。";
			cmd = "login";
		} finally {
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);

			// error.jspにフォワードする。
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}
}
