package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Item;

public class ItemDAO {

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
	 * データベース上の商品情報をすべて取得するメソッド
	 *
	 * @return ArrayList<Item> 取得した商品情報のリスト
	 */
	public ArrayList<Item> selectAll() {

		ArrayList<Item> list = new ArrayList<Item>();

		// 実行する検索用SQL文
		String sql = "SELECT * FROM `item_info`";

		Connection con = null;
		Statement smt = null;

		try {
			// JDBCドライバの読み込みとデータベースに接続する
			con = getConnection();
			// データ操作の準備
			smt = con.createStatement();

			// SQL文を実行し、売上の結果セットを取得する
			ResultSet rs = smt.executeQuery(sql);

			// データベースから各商品データを取得する
			while (rs.next()) {
				// 売上データを格納する変数を生成する
				Item item = new Item();

				// 商品情報１個の各データを格納する
				item.setItemId(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemState(rs.getString("state"));
				item.setCategory(rs.getString("category"));
				item.setPrice(rs.getInt("price"));
				item.setSellerId(rs.getInt("seller_id"));
				item.setTradeState(rs.getString("trade"));
				item.setBuyerId(rs.getInt("buyer_id"));
				item.setTradeDate(rs.getString("trade_date"));

				// 売上クラスをリストに追加する。
				list.add(item);
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
	 * 出品用メソッド
	 *
	 * @param item 出品する商品（Item型）
	 */
	public void insert(Item item) {

		// 実行する追加用SQL文
		String sql = "INSERT INTO `item_info`(`item_id`, `item_name`, `state`, `category`, `price`, `seller_id`, `trade`, `buyer_id`, `trade_date`)"
				+ "VALUES (NULL, '" + item.getItemName() + "', '" + item.getItemState() + "', '" + item.getCategory()
				+ "', " + item.getPrice() + ", " + item.getSellerId() + ", '" + item.getTradeState() + "', NULL, NULL)";

		Connection con = null;
		Statement smt = null;

		try {
			// JDBCドライバの読み込みとデータベースに接続する
			con = getConnection();
			// データ操作の準備
			smt = con.createStatement();

			// 登録
			smt.executeUpdate(sql);

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

	}

	/**
	 * 商品削除用メソッド
	 *
	 * @param itemId 削除する商品（int型）
	 */
	public void delete(int itemId) {

		// 実行する削除用SQL文
		String sql = "DELETE FROM `item_info` WHERE `item_id` = " + itemId;

		Connection con = null;
		Statement smt = null;

		try {
			// JDBCドライバの読み込みとデータベースに接続する
			con = getConnection();
			// データ操作の準備
			smt = con.createStatement();

			// 削除
			smt.executeUpdate(sql);

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
	}

	/**
	 * idから商品を取得するメソッド
	 *
	 * @param itemId 取得する商品（int型）
	 * @return Item 商品
	 */
	public Item selectById(int itemId) {

		// 実行する検索用SQL文
		String sql = "SELECT * FROM `item_info` WHERE `item_id` = " + itemId;

		Connection con = null;
		Statement smt = null;

		Item item = new Item();

		try {
			// JDBCドライバの読み込みとデータベースに接続する
			con = getConnection();
			// データ操作の準備
			smt = con.createStatement();

			// 検索
			ResultSet rs = smt.executeQuery(sql);

			if (rs.next()) {

				// 商品情報１個の各データを格納する
				item.setItemId(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemState(rs.getString("state"));
				item.setCategory(rs.getString("category"));
				item.setPrice(rs.getInt("price"));
				item.setSellerId(rs.getInt("seller_id"));
				item.setTradeState(rs.getString("trade"));
				item.setBuyerId(rs.getInt("buyer_id"));
				item.setTradeDate(rs.getString("trade_date"));

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

		return item;

	}

	/**
	 * 商品更新用メソッド
	 *
	 * 購入：trade変更、 購入者変更
	 *
	 * 購入キャンセル：trade変更、購入者変更（NULL）
	 *
	 * 入金：trade変更
	 *
	 * 発送：trade変更、取引日時変更
	 *
	 * (mode 1：購入,2：購入キャンセル,3：入金,,4：発送）
	 *
	 * @param itemId  商品id（int型）
	 * @param buyerId 購入者id（int型）
	 * @param mode    更新モード（int型）
	 *
	 */
	public void update(int itemId, int buyerId, int mode) {

		// sql文を格納する変数
		String sql = "";
		// 購入
		if (mode == 1) {
			// 実行する更新用SQL文
			sql = "UPDATE `item_info` SET trade = '2',buyer_id=" + buyerId + " WHERE `item_id` = " + itemId;
		}

		// 購入キャンセル
		if (mode == 2) {
			// 実行する更新用SQL文
			sql = "UPDATE `item_info` SET trade = '1', buyer_id=NULL WHERE `item_id` = " + itemId;
		}

		// 入金
		if (mode == 3) {
			// 実行する更新用SQL文
			sql = "UPDATE `item_info` SET trade = '3' WHERE `item_id` = " + itemId;
		}

		// 発送
		if (mode == 4) {
			// 実行する更新用SQL文
			sql = "UPDATE `item_info` SET trade = '4',`trade_date`=CURRENT_TIMESTAMP WHERE `item_id` = " + itemId;
		}

		Connection con = null;
		Statement smt = null;

		try {
			// JDBCドライバの読み込みとデータベースに接続する
			con = getConnection();
			// データ操作の準備
			smt = con.createStatement();

			// 削除
			smt.executeUpdate(sql);

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
	}

}
