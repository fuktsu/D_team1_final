package bean;

public class Item {
	/**
	 * フィールド変数定義
	 */
	//商品id
	private int itemId;

	//商品名
	private String itemName;

	//状態
	private String itemState;

	//カテゴリー
	private String category;

	//価格
	private int price;

	//出品者id
	private int sellerId;

	//取引状況
	private String tradeState;

	//購入者id
	private int buyerId;

	//取引日時
	private String tradeDate;

	/**
	 * 引数無しのコンストラクタ
	 */
	public Item() {
		this.itemId = 0;
		this.itemName = null;
		this.itemState = null;
		this.category = null;
		this.price = 0;
		this.sellerId = 0;
		this.tradeState = null;
		this.buyerId = 0;
		this.tradeDate = null;

	}
	/**
	 * 商品idを取得するゲッターメソッド
	 *
	 * @return int 商品id
	 */
	public int getItemId() {
		return this.itemId;
	}

	/**
	 * 商品名を取得するゲッターメソッド
	 *
	 * @return String 商品名
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 状態を取得するゲッターメソッド
	 *
	 * @return String 状態
	 */
	public String getItemState() {
		return this.itemState;
	}

	/**
	 * カテゴリーを取得するゲッターメソッド
	 *
	 * @return String カテゴリー
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * 価格を取得するゲッターメソッド
	 *
	 * @return int 価格
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * 出品者idを取得するゲッターメソッド
	 *
	 * @return int 価格
	 */
	public int getSellerId() {
		return this.sellerId;
	}

	/**
	 * 取引状況を取得するゲッターメソッド
	 *
	 * @return String 取引状況
	 */
	public String getTradeState() {
		return this.tradeState;
	}

	/**
	 * 購入者idを取得するゲッターメソッド
	 *
	 * @return int 購入者id
	 */
	public int getBuyerId() {
		return this.buyerId;
	}

	/**
	 * 取引日時を取得するゲッターメソッド
	 *
	 * @return int 購入者id
	 */
	public String getTradeDate() {
		return this.tradeDate;
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
	 * 状態を設定するセッターメソッド
	 *
	 * @param itemState 設定する状態（String型）
	 */
	public void setItemState(String itemState) {
		this.itemState = itemState;
	}

	/**
	 * カテゴリーを設定するセッターメソッド
	 *
	 * @param category 設定するカテゴリー（String型）
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
	 * 出品者idを設定するセッターメソッド
	 *
	 * @param sellerId 設定する出品者id（int型）
	 */
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * 取引状況を設定するセッターメソッド
	 *
	 * @param tradeState 設定する取引状況（String型）
	 */
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	/**
	 * 購入者idを設定するセッターメソッド
	 *
	 * @param sellerId 設定する購入者id（int型）
	 */
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	/**
	 * 取引日時を設定するセッターメソッド
	 *
	 * @param tradeDate 設定する取引日時（String型）
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

}
