package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Sales;

public class SalesDAO {
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
	 * データベース上の売上情報をすべて取得するメソッド
	 *
	 * @return ArrayList<Sales> 取得した売上情報のリスト
	 */
	public ArrayList<Sales> selectAll() {

		ArrayList<Sales> list = new ArrayList<Sales>();

		// 実行する検索用SQL文
		String sql = "SELECT u.user_name,i.item_name,i.price,i.trade_date FROM item_info i INNER JOIN user_info u ON u.user_id=i.seller_id WHERE trade=4 ORDER BY trade_date";

		Connection con = null;
		Statement smt = null;
		try {
			// JDBCドライバの読み込みとデータベースに接続する
			con = getConnection();
			// データ操作の準備
			smt = con.createStatement();

			// SQL文を実行し、売上の結果セットを取得する
			ResultSet rs = smt.executeQuery(sql);

			// データベースから各売上データを取得する
			while (rs.next()) {
				// 売上データを格納する変数を生成する
				Sales sales = new Sales();

				// 売上情報１個の各データを格納する
				sales.setUserName(rs.getString("user_name"));
				sales.setItemName(rs.getString("item_name"));
				sales.setPrice(rs.getInt("price"));
				sales.setTradeDate(rs.getString("trade_date"));

				// 売上クラスをリストに追加する。
				list.add(sales);
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

	/**
	 * ユーザー名と日時で絞り込んだ売上情報を取得するメソッド
	 *
	 * @param userName
	 * @param month
	 * @return ArrayList<Sales> 取得した売上情報のリスト
	 */
	public ArrayList<Sales> search(String userName, String month) {

		ArrayList<Sales> salesList = new ArrayList<Sales>();
		// ユーザー名が空文字か否かでsql文を部分的に変更する
		String sqlPart;
		if (userName == "") {
			// 空文字の場合あいまい検索にする（期間のみの絞り込みが可能になる）
			sqlPart = "LIKE '%" + userName + "%'";
		} else {
			// それ以外はノーマルの検索にする(あいまい検索であると、ユーザー名に"test"と"test2"が存在するとき"test"のみに絞れない)
			sqlPart = "= '" + userName + "'";
		}
		String sql = "SELECT u.user_name,i.item_name,i.price,i.trade_date FROM item_info i INNER JOIN user_info u ON u.user_id=i.seller_id WHERE trade=4 AND user_name "
				+ sqlPart + " AND trade_date LIKE '%" + month + "%' ORDER BY trade_date";

		Connection con = null;
		Statement smt = null;

		try {
			con = SalesDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Sales sales = new Sales();
				sales.setItemName(rs.getString("item_name"));
				sales.setUserName(rs.getString("user_name"));
				sales.setPrice(rs.getInt("price"));
				sales.setTradeDate(rs.getString("trade_date"));

				salesList.add(sales);
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
		return salesList;

	}
}
