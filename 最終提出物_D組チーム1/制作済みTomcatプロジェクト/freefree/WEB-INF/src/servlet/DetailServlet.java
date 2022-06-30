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
import dao.UserDAO;

public class DetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		String error = "";
		String cmd = "";

		try {

			// セッションを取得する
			HttpSession session = request.getSession();

			// セッションから"user"のUserオブジェクトを取得する。
			User user = (User) session.getAttribute("user");
			// セッション切れ
			if (user == null) {
				error = "セッション切れの為、取引詳細表示はできません。 ";
				cmd = "login";
				return;
			}

			// 画面から送信されるitemId情報を受け取るためのエンコードを設定します。
			request.setCharacterEncoding("UTF-8");

			// 画面から送信されるitemId情報を受け取ります。
			String itemId = request.getParameter("itemId");

			// itemDAOクラスに定義したselectById（）メソッドを利用して商品情報を取得します。
			ItemDAO itemDAO = new ItemDAO();
			Item item = itemDAO.selectById(Integer.parseInt(itemId));

			if (item.getItemId() == 0) {
				error = "その商品は存在しないか、既に購入されています。";
				cmd = "menu";
				return;
			}

			// userDAOクラスに定義したselectByUserId（）メソッドを利用し、
			// 出品者、購入者のユーザー情報を取得します。
			UserDAO userDAO = new UserDAO();

			if (item.getSellerId() != user.getUserId() && item.getBuyerId() != user.getUserId()) {
				error = "既に他のユーザーが購入した商品です。";
				cmd = "menu";
				return;
			}

			// 出品者
			User sellerUser = userDAO.selectByUserId(item.getSellerId());
			// 購入者
			User buyerUser = userDAO.selectByUserId(item.getBuyerId());

			// 取得した商品情報を「item」という名前でリクエストスコープに登録します。
			request.setAttribute("item", item);

			request.setAttribute("myUser", user);

			// 取得した出品者情報を「sellerUser」という名前でリクエストスコープに登録します。
			request.setAttribute("sellerUser", sellerUser);

			// 取得した購入者情報を「buyerUser」という名前でリクエストスコープに登録します。
			request.setAttribute("buyerUser", buyerUser);

			// 「/tradeList.jsp」へフォワードします。
			request.getRequestDispatcher("/view/tradeDetail.jsp").forward(request, response);
		} catch (IllegalStateException e) {
			System.out.println(e);
			error = "DB接続エラーが発生しました。";
			cmd = "login";
		} catch (NumberFormatException e) {
			error = "不正な入力です。";
			cmd = "menu";
		} finally {
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);

			// error.jspにフォワードする。
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}
}
