package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.LatestMessage;;

public class LatestMessageDAO {

	/*
	 * DB接続情報のフィールド変数定義
	 */
	private static final String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/freefreedb";
	private static final String USER = "root";
	private static final String PASS = "root123";

	/**
	 * DB接続情報を基にDB接続を行うメソッド
	 *
	 * @return DB接続情報
	 * @throws IllegalStateException メソッド内部で例外が発生した場合
	 */
	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName(RDB_DRIVE);
			con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * DBのmessage_info, item_infoテーブルから全ての最新のメッセージを取得するメソッド
	 *
	 * @return 全ての最新のメッセージの{@link LatestMessage}クラスの<br>
	 *         {@link ArrayList}オブジェクト
	 * @throws IllegalStateException メソッド内部で例外が発生した場合
	 */
	public ArrayList<LatestMessage> selectAll() {
		Connection con = null;
		Statement smt = null;

		ArrayList<LatestMessage> latestMessageList = new ArrayList<LatestMessage>();

		String sql = "SELECT u.user_name, i.item_name, m.item_id, m.message FROM message_info m "
				+ "INNER JOIN (SELECT MAX(message_id) message_id FROM message_info "
				+ "GROUP BY item_id) AS n ON m.message_id=n.message_id "
				+ "INNER JOIN item_info i ON i.item_id=m.item_id " + "INNER JOIN user_info u ON u.user_id=i.seller_id";

		try {
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				LatestMessage latestMessage = new LatestMessage();

				latestMessage.setUserName(rs.getString("user_name"));
				latestMessage.setItemName(rs.getString("item_name"));
				latestMessage.setItemId(rs.getInt("item_id"));
				latestMessage.setMessage(rs.getString("message"));

				latestMessageList.add(latestMessage);
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

		return latestMessageList;
	}

}
