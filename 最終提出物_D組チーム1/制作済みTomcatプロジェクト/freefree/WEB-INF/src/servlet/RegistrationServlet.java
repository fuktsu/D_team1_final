package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Item;
import bean.User;
import dao.ItemDAO;

public class RegistrationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションオブジェクトの生成
		HttpSession session = request.getSession();

		// DAOクラスのオブジェクト生成
		ItemDAO objDao = new ItemDAO();

		// DTOクラスのオブジェクト生成
		Item item = new Item();

		// エラーメッセージ格納用変数
		String error = "";

		// cmd用変数
		String cmd = "";

		// 文字エンコーディングの指定
		request.setCharacterEncoding("UTF-8");

		try {

			// ユーザー情報をセッションから取得
			User user = (User) session.getAttribute("user");

			// パラメータ取得
			String itemName = request.getParameter("itemName");
			String category = request.getParameter("category");
			String itemState = request.getParameter("itemState");
			String strPrice = request.getParameter("price");
			int price = 0;

			/*
			 * // 商品名の空文字チェック if (itemName.equals("")) { // エラーメッセージ登録 error =
			 * "商品名が未入力の為、出品は行えませんでした。"; // cmd登録 cmd = "menu";
			 * 
			 * return; }
			 * 
			 * // 価格の空文字チェック if (strPrice.equals("")) { // エラーメッセージ登録 error =
			 * "価格が未入力の為、出品は行えませんでした。"; // cmd登録 cmd = "menu";
			 * 
			 * return; }
			 */

			// 価格が数値かどうかのチェック
			try {
				price = Integer.parseInt(strPrice);
			} catch (NumberFormatException e) {
				// エラーメッセージ登録
				error = "価格の値が不正の為、出品は行えませんでした。";
				// cmd登録
				cmd = "menu";

				return;
			}

			// パラメータ設定
			item.setItemName(itemName);
			item.setCategory(category);
			item.setItemState(itemState);
			item.setPrice(price);
			item.setSellerId(user.getUserId());
			item.setTradeState("1");

			// 出品
			objDao.insert(item);

			// その他エラーのとき
		} catch (Exception e) {
			System.out.println(e);
			// エラーメッセージ登録
			error = "エラーが発生したため、出品は行えませんでした。";
			// cmd登録
			cmd = "logout";

			// フォワード処理
		} finally {
			// エラーがないとき
			if (error == "") {
//				request.getRequestDispatcher("/itemList").forward(request, response);

				// 複数登録できないようにリダイレクトが必要
				response.sendRedirect(request.getContextPath() + "/itemList");

				// エラーがあるとき
			} else {
				// エラーメッセージとcmdをリクエストスコープに登録
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);

				// エラー画面にフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}

	}

}
