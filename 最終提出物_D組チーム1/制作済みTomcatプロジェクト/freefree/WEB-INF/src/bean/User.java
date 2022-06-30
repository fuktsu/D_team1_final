package bean;

public class User {

	/*
	 * フィールド変数定義
	 */
	// ユーザーid
	private int userId;

	// 氏名
	private String name;

	// ユーザー名
	private String userName;

	// 住所
	private String address;

	// メールアドレス
	private String email;

	// パスワード
	private String password;

	// 権限 (1:管理者, 2:一般ユーザー)
	private String authority;

	/**
	 * 引数無しのコンストラクタ
	 */
	public User() {
		this.userId = 0;
		this.name = null;
		this.userName = null;
		this.address = null;
		this.email = null;
		this.password = null;
		this.authority = null;
	}

	/**
	 * ユーザーidのgetterメソッド
	 *
	 * @return ユーザーid
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 氏名のgetterメソッド
	 *
	 * @return 氏名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ユーザー名のgetterメソッド
	 *
	 * @return ユーザー名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 住所のgetterメソッド
	 *
	 * @return 住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * メールアドレスのgetterメソッド
	 *
	 * @return メールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * パスワードのgetterメソッド
	 *
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 権限のgetterメソッド
	 *
	 * @return 権限
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * ユーザーidのsetterメソッド
	 *
	 * @param userId 設定するユーザーid
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 氏名のsetterメソッド
	 *
	 * @param name 設定する氏名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ユーザー名のsetterメソッド
	 *
	 * @param userName 設定するユーザー名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 住所のsetterメソッド
	 *
	 * @param address 設定する住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * メールアドレスのsetterメソッド
	 *
	 * @param email 設定するメールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * パスワードのsetterメソッド
	 *
	 * @param password 設定するパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 権限のsetterメソッド
	 *
	 * @param authority 設定する権限
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
