package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Item;
import dao.ItemDAO;

public class ItemListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException {
		//DAOクラスのオブジェクト生成
		ItemDAO objDao = new ItemDAO();

		//エラーメッセージ格納用変数
		String error = "";

		//cmd用変数
		String cmd = "";

		//全商品リストを取得、リクエストスコープに登録
		try {
			//全商品リストを取得
			ArrayList<Item> list = objDao.selectAll();

			//リクエストスコープに格納
			request.setAttribute("item_list", list);

		//エラーのとき
		} catch(Exception e) {
			//エラーメッセージ登録
			error = "エラーが発生したため、一覧表示は行えませんでした。";
			//cmd登録
			cmd = "logout";

		//フォワード処理
		} finally {
			//エラーがないとき
			if (error == "") {
				request.getRequestDispatcher("/view/itemList.jsp").forward(request, response);

			//エラーがあるとき
			} else {
				//エラーメッセージとcmdをリクエストスコープに登録
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);

				//エラー画面にフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}

