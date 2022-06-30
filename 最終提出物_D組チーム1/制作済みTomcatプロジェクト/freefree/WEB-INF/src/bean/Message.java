package bean;

public class Message {

	/*
	 * フィールド変数定義
	 */
	// メッセージid
	private int messageId;

	// 商品id
	private int itemId;

	// 送信者id
	private int sender;

	// 受信者id
	private int receiver;

	// メッセージ
	private String message;

	/**
	 * 引数無しのコンストラクタ
	 */
	public Message() {
		this.messageId = 0;
		this.itemId = 0;
		this.sender = 0;
		this.receiver = 0;
		this.message = null;
	}

	/**
	 * メッセージidのgetterメソッド
	 *
	 * @return メッセージid
	 */
	public int getMessageId() {
		return messageId;
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
	 * 送信者idのgetterメソッド
	 *
	 * @return 送信者id
	 */
	public int getSender() {
		return sender;
	}

	/**
	 * 受信者idのgetterメソッド
	 *
	 * @return 受信者id
	 */
	public int getReceiver() {
		return receiver;
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
	 * メッセージidのsetterメソッド
	 * 
	 * @param messageId 設定するメッセージid
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
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
	 * 送信者idのsetterメソッド
	 * 
	 * @param sender 設定する送信者id
	 */
	public void setSender(int sender) {
		this.sender = sender;
	}

	/**
	 * 受信者idのsetterメソッド
	 * 
	 * @param receiver 設定する受信者id
	 */
	public void setReceiver(int receiver) {
		this.receiver = receiver;
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