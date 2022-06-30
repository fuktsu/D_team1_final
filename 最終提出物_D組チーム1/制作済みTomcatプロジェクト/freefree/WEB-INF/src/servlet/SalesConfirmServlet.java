package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sales;
import bean.User;
import dao.SalesDAO;

public class SalesConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エラーメッセージとコマンド格納用の変数宣言
		String error = "";
		String cmd = "";

		try {
			// セッションを取得する
			HttpSession session = request.getSession();

			// セッションから"user"のUserオブジェクトを取得する。
			User user = (User) session.getAttribute("user");
			// セッション切れ
			if (user == null) {
				error = "セッション切れの為、売上確認はできません。 ";
				throw new Exception("Session has expired");
			}
			// DAOオブジェクトの生成
			SalesDAO salesDao = new SalesDAO();

			// 売り上げ一覧のArrayListを取得
			ArrayList<Sales> salesList = salesDao.selectAll();

			// リクエストスコープにユーザー一覧情報を格納
			request.setAttribute("salesList", salesList);

		} catch (Exception e) {
			// エラー発生
			error = "エラーが発生しました。";
			cmd = "login";
		} finally {
			// エラーの有無でフォワード先を分岐させる
			if (error.equals("")) {
				// エラーが無い場合、salesConfirm.jspにフォワード
				request.getRequestDispatcher("/view/salesConfirm.jsp").forward(request, response);
			} else {
				// エラーが有る場合、error.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}
