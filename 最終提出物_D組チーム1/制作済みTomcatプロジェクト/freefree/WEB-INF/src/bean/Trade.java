package bean;

public class Trade {

	/*
	 * フィールド変数定義
	 */
	// ユーザー名
	private String userName;

	// 商品id
	private int itemId;

	// 商品名
	private String itemName;

	// 商品の状態
	private String itemState;

	// 商品のカテゴリー
	private String category;

	// 価格
	private int price;

	// 取引状況
	private String tradeState;

	// 出品者のid
	private int sellerId;

	// 購入者のid
	private int buyerId;

	/**
	 * 引数無しのコンストラクタ
	 */
	public Trade() {
		userName = null;
		itemId = 0;
		itemName = null;
		itemState = null;
		category = null;
		price = 0;
		tradeState = null;
		sellerId = 0;
		buyerId = 0;
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
	 * 商品idを取得するゲッターメソッド
	 *
	 * @return int 商品id
	 */
	public int getItemId() {
		return itemId;
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
	 * 商品の状態を取得するゲッターメソッド
	 *
	 * @return String 商品の状態
	 */
	public String getItemState() {
		return itemState;
	}

	/**
	 * 商品のカテゴリーを取得するゲッターメソッド
	 *
	 * @return String 商品のカテゴリー
	 */
	public String getCategory() {
		return category;
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
	 * 取引状況を取得するゲッターメソッド
	 *
	 * @return String 取引状況
	 */
	public String getTradeState() {
		return tradeState;
	}

	/**
	 * 出品者のidを取得するゲッターメソッド
	 *
	 * @return int 出品者id
	 */
	public int getSellerId() {
		return sellerId;
	}

	/**
	 * 購入者のidを取得するゲッターメソッド
	 *
	 * @return int 購入者のid
	 */
	public int getBuyerId() {
		return buyerId;
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
	 * 商品idを設定するセッターメソッド
	 *
	 * @param itemId 設定する商品id（int型）
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	 * 商品の状態を設定するセッターメソッド
	 *
	 * @param itemState 設定する商品の状態（int型）
	 */
	public void setItemState(String itemState) {
		this.itemState = itemState;
	}

	/**
	 * 商品のカテゴリーを設定するセッターメソッド
	 *
	 * @param category 設定する商品のカテゴリー（String）
	 */
	public void setCategory(String category) {
		this.category = category;
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
	 * 取引状況を設定するセッターメソッド
	 *
	 * @param tradeState 設定する取引状況 （String型）
	 */
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	/**
	 * 出品者のidを設定するセッターメソッド
	 *
	 * @param sellerId 設定する出品者のid（int型）
	 */
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * 購入者のidを設定するセッターメソッド
	 *
	 * @param buyerId 設定する購入者のid（int型）
	 */
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

}
