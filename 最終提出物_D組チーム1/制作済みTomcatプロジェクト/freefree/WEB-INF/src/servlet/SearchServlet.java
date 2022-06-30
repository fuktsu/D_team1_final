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

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エラー時のメッセージとコマンドを初期化
		String error = "";
		String cmd = "";

		// 例外処理
		try {// セッションを取得する
			HttpSession session = request.getSession();

			// セッションから"user"のUserオブジェクトを取得する。
			User user = (User) session.getAttribute("user");
			// セッション切れ
			if (user == null) {
				error = "セッション切れの為、売上確認はできません。";
				cmd = "login";
				return;
			}

			// DAOオブジェクトの生成
			SalesDAO salesDao = new SalesDAO();

			// 画面からの情報受け取りのエンコード設定
			request.setCharacterEncoding("UTF-8");

			// 画面からの絞り込み情報を取得する
			String userName = request.getParameter("userName");
			String month = request.getParameter("month");

			// 検索結果を格納
			ArrayList<Sales> salesList = salesDao.search(userName, month);

			// リクエストスコープに売上情報が格納されたsalesListを登録
			request.setAttribute("salesList", salesList);
		} catch (Exception e) {
			// エラー時のメッセージとコマンドを格納
			error = "エラーが発生しました。";
			cmd = "login";
		} finally {
			// エラーの有無で遷移先を分岐する
			if (error.equals("")) {
				// salesConfirm.jspにフォワード
				request.getRequestDispatcher("/view/salesConfirm.jsp").forward(request, response);
			} else {
				// リクエストスコープにエラーメッセージとコマンドを登録
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				// エラーページへ遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}
	}

}
