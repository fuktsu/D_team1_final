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
import util.SendMail;

public class UpdateStatusServlet extends HttpServlet {
	public enum TransitionTarget {
		tradeDetail, tradeList, itemList
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		String itemId = "";
		TransitionTarget transitionTarget = TransitionTarget.tradeList;
		// 出品削除：商品削除
		// 購入：trade変更、購入者変更
		// 購入キャンセル：trade変更、購入者変更（NULL）
		// 入金：trade変更
		// 発送：trade変更、取引日時変更

		// 出品削除、購入、購入キャンセル、入金、発送

		String tradeStateArray[] = { "itemDelete", "buy", "buyCancel", "payment", "delivery" };
		try {

			// セッションを取得する
			HttpSession session = request.getSession();

			// セッションから"user"のUserオブジェクトを取得する。
			User user = (User) session.getAttribute("user");
			// セッション切れ
			if (user == null) {
				error = "セッション切れの為、取引一覧表示はできません。 ";
				throw new Exception("Session has expired");
			}

			// 画面から送信される情報を受け取るためのエンコードを設定します。
			request.setCharacterEncoding("UTF-8");

			// 画面から送信されるitemId情報を受け取ります。
			itemId = request.getParameter("itemId");

			// 画面から送信されるmode情報を受け取ります。
			String tradeMode = request.getParameter("cmd");

			// itemDAOクラスに定義したselectById（）メソッドを利用して商品情報を取得します。
			ItemDAO itemDAO = new ItemDAO();
			Item item = itemDAO.selectById(Integer.parseInt(itemId));

			// 該当商品がなければ
			if (item.getItemId() == 0) {
				throw new Exception("not item");
			}

			// userDAOクラスに定義したselectByUserId（）メソッドを利用し、
			// ユーザー情報を取得します。
			UserDAO userDAO = new UserDAO();

			// 出品者
			User sellerUser = userDAO.selectByUserId(item.getSellerId());
			// 購入者
			User buyerUser = userDAO.selectByUserId(item.getBuyerId());

			// モードが「出品削除なら」
			if (tradeMode.equals(tradeStateArray[0])) {
				itemDAO.delete(item.getItemId());
				transitionTarget = TransitionTarget.tradeList;
				return;
			}
			// モードが「購入なら」
			if (tradeMode.equals(tradeStateArray[1])) {
				itemDAO.update(item.getItemId(), user.getUserId(), 1);
				String mainMessage = sellerUser.getUserName() + "様\n\n" + "あなたの出品した商品「" + item.getItemName() + "」を、\n"
						+ user.getUserName() + "さんが購入しました。\n\nこのメールは自動送信メールのため、\n返信頂いても対応できません。";
				SendMail.sendEmail(sellerUser.getEmail(), "出品商品購入メール", mainMessage);
				transitionTarget = TransitionTarget.tradeDetail;
				return;
			}

			// モードが「購入キャンセルなら」
			if (tradeMode.equals(tradeStateArray[2])) {
				itemDAO.update(item.getItemId(), user.getUserId(), 2);
				transitionTarget = TransitionTarget.tradeList;
				return;
			}

			// モードが「入金なら」
			if (tradeMode.equals(tradeStateArray[3])) {
				itemDAO.update(item.getItemId(), user.getUserId(), 3);
				transitionTarget = TransitionTarget.tradeDetail;
				return;
			}

			// モードが「発送なら」
			if (tradeMode.equals(tradeStateArray[4])) {
				itemDAO.update(item.getItemId(), user.getUserId(), 4);
				String mainMessage = buyerUser.getUserName() + "様\n\n" + "あなたが購入した商品「" + item.getItemName()
						+ "」が、発送されました。\n\nこのメールは自動送信メールのため、\n返信頂いても対応できません。";
				SendMail.sendEmail(sellerUser.getEmail(), "出品商品発送メール", mainMessage);
				transitionTarget = TransitionTarget.tradeDetail;
				return;
			}
		} catch (Exception e) {
			System.out.println(e);
			error = "エラーが発生しました。";
		} finally {
			if (error.equals("")) {
				if (transitionTarget == TransitionTarget.tradeDetail) {
					System.out.println("jump tradeDetail");
					// 「/tradeList.jsp」へフォワードします。
					response.sendRedirect(request.getContextPath() + "/detail?itemId=" + itemId);
				} else if (transitionTarget == TransitionTarget.tradeList) {
					// 「/tradeList.jsp」へフォワードします。
					System.out.println("jump tradelist");
					response.sendRedirect(request.getContextPath() + "/tradeList");
				}
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);

				// error.jspにフォワードする。
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
