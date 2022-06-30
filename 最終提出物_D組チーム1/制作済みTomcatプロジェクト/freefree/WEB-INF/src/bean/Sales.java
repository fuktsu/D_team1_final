package bean;

public class Sales {

	/*
	 * フィールド変数定義
	 */
	// ユーザー名
	private String userName;

	// 商品名
	private String itemName;

	// 価格
	private int price;

	// 取引日時
	private String tradeDate;

	/**
	 * 引数無しのコンストラクタ
	 */
	public Sales() {
		// TODO 自動生成されたコンストラクター・スタブ
		userName = null;
		itemName = null;
		price = 0;
		tradeDate = null;
	}

	/**
	 * ユーザー名を取得するゲッターメソッド
	 *
	 * @return String ユーザー名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 価格を取得するゲッターメソッド
	 *
	 * @return int 価格
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 商品名を取得するゲッターメソッド
	 *
	 * @return String 商品名
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 取引日時を取得するゲッターメソッド
	 *
	 * @return String 取引日時
	 */
	public String getTradeDate() {
		return tradeDate;
	}

	/**
	 * ユーザー名を設定するセッターメソッド
	 *
	 * @param userName 設定するユーザー名（String型）
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 価格を設定するセッターメソッド
	 *
	 * @param price 設定する価格（int型）
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 商品名を設定するセッターメソッド
	 *
	 * @param itemName 設定する商品名（String型）
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 取引日時を設定するセッターメソッド
	 *
	 * @param tradeDate 登録する取引日時（String型）
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
}
