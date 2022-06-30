package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Message;;

public class MessageDAO {

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
	 * DBのmessage_infoテーブルから対象商品のメッセージ詳細を取得するメソッド
	 *
	 * @param itemId 表示対象の商品id
	 * @return 対象商品idの全ての{@link Message}クラスの<br>
	 *         {@link ArrayList}オブジェクト
	 * @throws IllegalStateException メソッド内部で例外が発生した場合
	 */
	public ArrayList<Message> selectByItemId(int itemId) {
		Connection con = null;
		Statement smt = null;

		ArrayList<Message> messageList = new ArrayList<Message>();

		String sql = "SELECT * FROM message_info WHERE item_id=" + itemId;

		try {
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Message message = new Message();

				message.setMessageId(rs.getInt("message_id"));
				message.setItemId(rs.getInt("item_id"));
				message.setSender(rs.getInt("sender"));
				message.setReceiver(rs.getInt("receiver"));
				message.setMessage(rs.getString("message"));

				messageList.add(message);
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

		return messageList;
	}

	/**
	 * 引数のメッセージ内容を元にDBのmessage_infoテーブルに新規登録処理を行うメソッド
	 *
	 * @param message 新規登録対象の{@link Message}オブジェクト
	 * @throws IllegalStateException メソッド内部で例外が発生した場合
	 */
	public void insert(Message message) {
		Connection con = null;
		Statement smt = null;

		String sql = "INSERT INTO message_info(message_id, item_id, sender, receiver, message) " + "VALUES(" + "NULL,"
				+ message.getItemId() + "," + message.getSender() + "," + message.getReceiver() + ",'"
				+ message.getMessage() + "')";

		try {
			con = getConnection();
			smt = con.createStatement();

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
