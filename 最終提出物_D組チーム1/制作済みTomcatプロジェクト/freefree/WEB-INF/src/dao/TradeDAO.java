package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Trade;

public class TradeDAO {
	// データベース接続情報
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/freefreedb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	/**
	 * JDBCドライバの読み込みとデータベースに接続を行うメソッド
	 *
	 * @return Connectionオブジェクト
	 */
	private static Connection getConnection() {
		try {
			// JDBCドライバの読み込みをする
			Class.forName(RDB_DRIVE);

			// データベースに接続する
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);

			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * 該当idが出品者または購入者のidである取引情報を取得するメソッド
	 *
	 * @param userId 検索するユーザーid
	 * @return ArrayList<Trade> 検索結果の取引情報のリスト
	 */
	public ArrayList<Trade> selectByUserId(int userId) {

		ArrayList<Trade> list = new ArrayList<Trade>();

		// 実行する検索用SQL文
		String sql = "SELECT u.user_name,i.item_id,i.item_name,i.state,i.category,i.price,i.trade,i.seller_id,i.buyer_id FROM item_info i INNER JOIN user_info u ON i.seller_id=u.user_id where seller_id="
				+ userId + " or buyer_id=" + userId;

		Connection con = null;
		Statement smt = null;
		try {
			// JDBCドライバの読み込みとデータベースに接続する
			con = getConnection();
			// データ操作の準備
			smt = con.createStatement();

			// SQL文を実行し、取引情報の結果セットを取得する
			ResultSet rs = smt.executeQuery(sql);

			// データベースから各取引データを取得する
			while (rs.next()) {
				// 取引データを格納する変数を生成する
				Trade trade = new Trade();

				// 取引情報１個の各データを格納する
				trade.setUserName(rs.getString("user_name"));
				trade.setItemId(rs.getInt("item_id"));
				trade.setItemName(rs.getString("item_name"));
				trade.setItemState(rs.getString("state"));
				trade.setCategory(rs.getString("category"));
				trade.setPrice(rs.getInt("price"));
				trade.setTradeState(rs.getString("trade"));
				trade.setSellerId(rs.getInt("seller_id"));
				trade.setBuyerId(rs.getInt("buyer_id"));

				// 取引クラスをリストに追加する。
				list.add(trade);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list;

	}
}
