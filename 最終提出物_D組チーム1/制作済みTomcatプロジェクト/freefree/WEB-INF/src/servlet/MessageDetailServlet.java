package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Item;
import bean.Message;
import dao.ItemDAO;
import dao.MessageDAO;

public class MessageDetailServlet extends HttpServlet {

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
			// MessageDAOクラスのオブジェクトを生成
			MessageDAO messageDAO = new MessageDAO();

			// ItemDAOクラスのオブジェクトを生成
			ItemDAO itemDAO = new ItemDAO();

			// 画面から送信されるitemId情報を受け取るためのエンコードを設定
			request.setCharacterEncoding("UTF-8");

			// 画面から送信されるitemId情報を受け取る
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			// Itemクラスのオブジェクトを生成し、対象商品のitemIdの情報を格納
			Item item = itemDAO.selectById(itemId);

			// 対象商品の出品者idを格納
			int sellerId = item.getSellerId();

			// 表示するメッセージ詳細を格納するMessageクラスのArrayListオブジェクトを生成
			ArrayList<Message> messageList = messageDAO.selectByItemId(itemId);

			// 取得したitemIdを「itemId」という名前でリクエストスコープに登録
			request.setAttribute("itemId", itemId);

			// 取得したitemを「item」という名前でリクエストスコープに登録
			request.setAttribute("item", item);

			// 取得したsellerIdを「sellerId」という名前でリクエストスコープに登録
			request.setAttribute("sellerId", sellerId);

			// 取得したメッセージ詳細を「messageList」という名前でリクエストスコープに登録
			request.setAttribute("messageList", messageList);
		} catch (IllegalStateException e) {
			// DB接続エラー
			error = "DB接続エラーが発生しました。";
			cmd = "login";
		} catch (NumberFormatException e) {
			// itemIdが数値以外
			error = "不正な入力です。";
			cmd = "menu";
		} finally {
			// エラーの有無でフォワード先を分岐させる
			if (error.equals("")) {
				// エラーが無い場合、messageDetail.jspにフォワード
				request.getRequestDispatcher("/view/messageDetail.jsp").forward(request, response);
			} else {
				// エラーが有る場合、error.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
