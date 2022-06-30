package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.User;

public class UserDAO {

	// データベース接続用情報
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/freefreedb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	/**
	 * DB接続用メソッド
	 *
	 * @return
	 */
	public static Connection getConnection() throws IllegalStateException {

		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * ユーザー登録用メソッド
	 *
	 * @param user
	 */
	public void insert(User user) {

		// SQL文の定義
		String sql = "INSERT INTO `user_info`(`user_id`, `user_name`, `name`, `address`, `email`, `password`, `authority`) VALUES (NULL,'"
				+ user.getUserName() + "','" + user.getName() + "','" + user.getAddress() + "','" + user.getEmail()
				+ "','" + user.getPassword() + "','" + user.getAuthority() + "')";

		Connection con = null;
		Statement smt = null;
		try {

			con = UserDAO.getConnection();
			smt = con.createStatement();

			// SQL文を発行
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
	 * ユーザー名とパスワードによるユーザー情報取得用メソッド（ログイン用）
	 *
	 * @param userName,password
	 * @return user
	 */
	public User login(String userName, String password) {

		// SQL文の定義
		String sql = "SELECT * FROM user_info WHERE user_name=BINARY'" + userName + "' AND password =BINARY'" + password
				+ "'";

		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setUserName(rs.getString("user_name"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setAuthority(rs.getString("authority"));
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
		return user;
	}

	/**
	 * 全ユーザー情報取得用メソッド(ユーザー一覧用)
	 *
	 * @return
	 */
	public ArrayList<User> selectAll() {

		ArrayList<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM user_info ORDER BY user_id";

		Connection con = null;
		Statement smt = null;

		try {
			// DB接続
			con = UserDAO.getConnection();
			// Statementオブジェクトの生成
			smt = con.createStatement();

			// SQL文を実行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			// 結果セットからデータを一行ずつ取得
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setUserName(rs.getString("user_name"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setAuthority(rs.getString("authority"));

				// 各データを持つUserクラスをArrayListに格納
				list.add(user);
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
	 * 指定したユーザーIDをもつデータを取得するメソッド
	 *
	 * @param userId
	 * @return user
	 */
	public User selectByUserId(int userId) {

		// SQL文の定義
		String sql = "SELECT * FROM user_info WHERE user_id =" + userId;

		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {
			con = UserDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setUserName(rs.getString("user_name"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setAuthority(rs.getString("authority"));

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
		return user;
	}

}
