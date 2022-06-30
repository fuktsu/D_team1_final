package util;

import java.text.DecimalFormat;

public class MyFormat {

	/**
	 * 金額のフォーマットに修正するメソッド
	 *
	 * @param price
	 * @return
	 */
	public String moneyFormat(int price) {

		DecimalFormat format01 = new DecimalFormat("###,###");

		return "\\" + format01.format(price);

	}

	/**
	 * DBに格納されているカテゴリーの数字文字列を名称文字列に変換するメソッド
	 * 使用例：myformat.categoryFormat(item.getCategory())
	 *
	 * @param category カテゴリーの数字文字列
	 * @return String カテゴリーの名称の文字列
	 */
	public String categoryFormat(String category) {
		try {
			String[] categoryArrray = { "その他", "服", "インテリア・住まい・小物", "本・音楽・ゲーム", "おもちゃ・ホビー・グッズ", "コスメ・香水・美容",
					"家電・スマホ・カメラ", "スポーツ・レジャー", "ハンドメイド", "自動車・オートバイ" };
			return categoryArrray[Integer.parseInt(category)];
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * DBに格納されている商品の状態の数字文字列を名称文字列に変換するメソッド
	 * 使用例：myformat.itemStateFormat(item.getItemState())
	 *
	 * @param itemState 商品の状態の数字文字列
	 * @return String 商品の状態の名称の文字列
	 */
	public String itemStateFormat(String itemState) {
		try {
			String[] itemStateArrray = { "", "新品・未使用", "未使用に近い", "目立った傷や汚れなし", "やや傷や汚れあり", "傷や汚れあり", "全体的に状態が悪い" };
			return itemStateArrray[Integer.parseInt(itemState)];
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * DBに格納されている取引状況の数字文字列を名称文字列に変換するメソッド
	 * 使用例：myformat.tradeFormat(trade.getTradeState())
	 *
	 * @param trade 取引状況の数字文字列
	 * @return String 取引状況の名称の文字列
	 */
	public String tradeFormat(String trade) {
		try {
			String[] tradeArrray = { "", "出品中", "購入済み", "入金済み", "発送済み" };
			return tradeArrray[Integer.parseInt(trade)];
		} catch (Exception e) {
			throw e;
		}

	}
}
