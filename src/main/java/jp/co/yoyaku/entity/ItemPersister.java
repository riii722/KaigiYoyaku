package jp.co.yoyaku.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ItemPersister {

	Logger logger = Logger.getLogger(ItemPersister.class.getName());

	final String URL = "jdbc:oracle:thin:@160.240.55.239:1521:CRMPROF";
	final String User = "TERADA";
	final String Password = "TERADA";

	/**
	 * @see ItemList() : アイテム一覧を取得してセットする
	 * @return items#List<Item>
	 * @throws SQLException
	 */
	public List<Item> ItemList() throws SQLException {
		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL
		ResultSet rs = null; // 結果セット
		List<Item> items = new ArrayList<Item>(); // 検索結果リスト

		try {

			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ アイテム一覧取得#ItemList()		Start ■■");

			// SQL文生成
			sql.append(" SELECT 	");
			sql.append(" アイテム番号	");
			sql.append(" ,アイテム名 ");
			sql.append(" ,REPLACE(LTRIM(REPLACE(表示順序,'0',' ')),' ','0') AS zero表示順序 ");
			sql.append(" FROM ");
			sql.append(getDefaultTable());
			sql.append(" ORDER BY	");
			sql.append(" 表示順序 ASC	");

			// ステートメント作成
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			logger.info(sql.toString());

			// 結果取得
			while (rs.next()) {
				// データベースから取得した値をセット
				Item item = new Item(); // インスタンスの生成
				item.setItemNum(rs.getString("アイテム番号")); // アイテム番号
				item.setItemName(rs.getString("アイテム名")); // アイテム名
				item.setItemOrder(rs.getString("zero表示順序")); // 表示順序
				items.add(item);
			}

		} catch (SQLException e) {
			logger.error("☆☆ アイテム一覧取得#ItemList()		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ アイテム一覧取得 #ItemList()		End ■■");
		}
		return items;
	}

	/**
	 * @see getItem() : アイテムを取得してセットする
	 * @param String itemNum
	 * @return item#Item
	 * @throws SQLException
	 */
	public Item getItem(String itemNum) throws SQLException {
		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL
		ResultSet rs = null; // 結果セット
		Item item = new Item(); // インスタンスの生成

		try {
			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ アイテム取得#getItem()			Start ■■");

			// SQL文生成
			sql.append(" SELECT ");
			sql.append(" アイテム番号 ");
			sql.append(" ,アイテム名 ");
			sql.append(" ,REPLACE(LTRIM(REPLACE(表示順序,'0',' ')),' ','0') AS 表示順序 ");
			sql.append(" FROM ");
			sql.append(getDefaultTable());
			sql.append(" WHERE ");
			sql.append(" アイテム番号 = ? ");

			// ステートメント作成
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, itemNum);
			rs = pstmt.executeQuery();

			logger.info(sql.toString());
			logger.info("param : " + itemNum);

			// 結果取得
			while (rs.next()) {
				// データベースから取得した値をセット
				item.setItemNum(rs.getString("アイテム番号")); // アイテム番号
				item.setItemName(rs.getString("アイテム名")); // アイテム名
				item.setItemOrder(rs.getString("表示順序")); // 表示順序
			}

		} catch (SQLException e) {
			logger.error("☆☆ アイテム取得#getItem()		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ アイテム取得 #getItem()		End ■■");
		}
		return item;
	}

	/**
	 * @see InsertItem() : アイテムを登録する
	 * @param String itemName, String itemOrder
	 * @return なし
	 * @throws SQLException
	 */
	public void InsertItem(String itemName, String itemOrder) throws SQLException {

		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL

		try {
			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ アイテム登録#InsertItem()			Start ■■");

			// SQL文生成
			sql.append(" INSERT ");
			sql.append(" INTO ");
			sql.append(getDefaultTable());
			sql.append(" ( ");
			sql.append(" アイテム番号 ");
			sql.append(" ,アイテム名 ");
			sql.append(" ,表示順序 ");
			sql.append(" ) ");
			sql.append(" SELECT ");
			sql.append(" LPAD(COALESCE(MAX(アイテム番号)+1, null), 3, '0') ");
			sql.append(" ,? ");
			sql.append(" ,? ");
			sql.append(" FROM ");
			sql.append(getDefaultTable());

			// ステートメント作成
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, itemName);
			pstmt.setString(2, itemOrder);

			logger.info(sql.toString());
			logger.info("INSERT :" + itemName + "," + itemOrder);

			// ステートメント実行
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("☆☆ アイテム登録#InsertItem()		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ アイテム登録 #InsertItem()		End ■■");
		}
	}

	/**
	 * @see UpdateItem() : アイテムを更新する
	 * @param String ItemNum, String itemName, String itemOrder
	 * @return なし
	 * @throws SQLException
	 */
	public void UpdateItem(String itemNum, String itemName, String itemOrder) throws SQLException {

		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL

		try {
			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ アイテム更新#UpdateItem()			Start ■■");

			// SQL文生成
			sql.append(" UPDATE ");
			sql.append(getDefaultTable());
			sql.append(" SET ");

			// アイテム名が空白の場合、更新しない
			if (itemName != "") {
				sql.append(" アイテム名 	");
				sql.append(" = ? ");
			}

			// アイテム名が空白でないかつ、表示順序が空白でない場合は[,]をつける
			// アイテム名が空白の場合、表示順序に[,]をつけない
			if (itemName != "" && !(itemOrder.equals("000"))) {
				sql.append(" ,表示順序 ");
				sql.append(" = ? ");
			} else if (itemName == "" && !(itemOrder.equals("000"))) {
				sql.append(" 表示順序 ");
				sql.append(" = ? ");
			}

			sql.append(" WHERE ");
			sql.append(" アイテム番号 ");
			sql.append(" = ? ");

			// ステートメント作成
			pstmt = conn.prepareStatement(sql.toString());
			int x = 1;
			if (itemName != "") {
				pstmt.setString(x, itemName);
				x = x + 1;
			}
			if (!(itemOrder.equals("000"))) {
				pstmt.setString(x, itemOrder);
				x = x + 1;
			}
			pstmt.setString(x, itemNum);

			logger.info(sql.toString());
			logger.info("UPDATE :" + itemNum + "," + itemName + "," + itemOrder);

			// ステートメント実行
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("☆☆ アイテム更新 #UpdateItem()		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ アイテム更新 #UpdateItem()		End ■■");
		}
	}

	/**
	 * @see DeleteItem() : アイテムを削除する
	 * @param String ItemNum
	 * @return なし
	 * @throws SQLException
	 */
	public void DeleteItem(String itemNum) throws SQLException {

		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL
		try {

			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ アイテム削除#DeleteItem()			Start ■■");

			// SQL文生成
			sql.append(" DELETE ");
			sql.append(" FROM ");
			sql.append(getDefaultTable());
			sql.append(" WHERE ");
			sql.append(" アイテム番号 ");
			sql.append(" = ? ");

			// ステートメント作成
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, itemNum);

			logger.info(sql.toString());
			logger.info("DELETE :" + itemNum);

			// ステートメント実行
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("☆☆ アイテム削除#DeleteItem()		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ アイテム削除#DeleteItem()		End ■■");
		}
	}

	/**
	 * @see String#getDafaultTable()
	 */
	public String getDefaultTable() {
		return "アイテム管理";
	}

}
