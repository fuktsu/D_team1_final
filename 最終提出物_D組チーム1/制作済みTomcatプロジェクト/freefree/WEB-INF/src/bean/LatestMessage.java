package bean;

public class LatestMessage {

	/*
	 * フィールド変数定義
	 */
	// ユーザー名
	private String userName;

	// 商品名
	private String itemName;

	// 商品id
	private int itemId;

	// メッセージ
	private String message;

	/**
	 * 引数無しのコンストラクタ
	 */
	public LatestMessage() {
		this.userName = null;
		this.itemName = null;
		this.itemId = 0;
		this.message = null;
	}

	/**
	 * ユーザー名のgetterメソッド
	 *
	 * @return 出品者のユーザー名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 商品名のgetterメソッド
	 *
	 * @return 商品名
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 商品idのgetterメソッド
	 *
	 * @return 商品id
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * メッセージのgetterメソッド
	 *
	 * @return メッセージ
	 */
	public String getMessage() {
		return message;
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
	 * 商品名のsetterメソッド
	 * 
	 * @param itemName 設定する商品名
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 商品idのsetterメソッド
	 * 
	 * @param itemId 設定する商品id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * メッセージのsetterメソッド
	 * 
	 * @param message 設定するメッセージ
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
