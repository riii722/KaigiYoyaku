package jp.co.yoyaku.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class KaigiSyosaiPersister {

	Logger logger = Logger.getLogger(KaigiSyosaiPersister.class.getName());

	final String URL = "jdbc:oracle:thin:@160.240.55.239:1521:CRMPROF";
	final String User = "TERADA";
	final String Password = "TERADA";

	/**
	 * @see KaigiSyosaiList()：指定した日付から会議内容を一覧で取得する。
	 * @param String date
	 * @return List<KaigiSyosai>
	 * @throws SQLException
	 */
//	public List<KaigiSyosai> KaigiSyosaiList(String date) throws ServletException, IOException, SQLException {
	public List<KaigiSyosai> KaigiSyosaiList(String date) throws SQLException {

		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL
		ResultSet rs = null; // 結果セット
		List<KaigiSyosai> syosai = new ArrayList<KaigiSyosai>(); // 検索結果リスト

		try {

			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ 会議詳細取得#KaigiSyosaiList() Start ■■");

			// SQL文生成
			sql.append(" SELECT ");
			sql.append(" y.予約番号 ");
			sql.append(" ,y.予約日 ");
			sql.append(" ,y.開始時間 ");
			sql.append(" ,y.終了時間 ");
			sql.append(" ,y.会議名 ");
			sql.append(" ,i.アイテム名 ");
			sql.append(" ,y.予約者名 ");
			sql.append(" FROM ");
			sql.append(getDefaultTable() + " y ");
			sql.append(" LEFT OUTER JOIN アイテム管理 i ");
			sql.append(" ON y.アイテム番号 ");
			sql.append(" = i.アイテム番号 ");
			sql.append(" WHERE	 ");
			sql.append(" y.予約日 ");
			sql.append(" = ? ");
			sql.append(" ORDER BY ");
			sql.append(" i.表示順序 ");
			sql.append(" ,y.開始時間 ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();

			logger.info(sql.toString());
			logger.info("param : " + date);

			// 結果取得
			while (rs.next()) {
				// データベースから取得した値をセット
				KaigiSyosai kaigi = new KaigiSyosai(); // インスタンスの生成
				kaigi.setYoyakuNum(rs.getString("予約番号")); // 予約番号
				kaigi.setYoyakuDay(rs.getString("予約日")); // 予約日
				kaigi.setStartTime(rs.getString("開始時間")); // 開始時間
				kaigi.setEndTime(rs.getString("終了時間")); // 終了時間
				kaigi.setKaigiName(rs.getString("会議名")); // 会議名
				kaigi.setItemName(rs.getString("アイテム名")); // アイテム名
				kaigi.setYoyakuName(rs.getString("予約者名")); // 予約者名
				syosai.add(kaigi);
			}

		} catch (SQLException e) {
			logger.error("☆☆ 会議詳細取得#KaigiSyosaiList()		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} catch (Exception e) {
			logger.error("☆☆ 会議詳細取得#KaigiSyosaiList()		例外発生 ☆☆");
			logger.error("☆☆Exception☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ 会議詳細取得#KaigiSyosaiList()		End ■■");
		}
		return syosai;
	}

	/**
	 * @see getKaigiSyosai() : 指定した会議の詳細を取得する
	 * @param String yoyakuNum
	 * @return KaigiSyosai
	 * @throws SQLException
	 */
	public KaigiSyosai getKaigiSyosai(String yoyakuNum) throws SQLException {

		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL
		ResultSet rs = null; // 結果セット
		KaigiSyosai kaigi = new KaigiSyosai(); // インスタンスの生成

		try {

			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ 会議詳細取得#getKaigiSyosai()		Start ■■");

			// SQL文生成
			sql.append(" SELECT ");
			sql.append(" y.予約番号 ");
			sql.append(" ,y.予約日 ");
			sql.append(" ,y.開始時間 ");
			sql.append(" ,y.終了時間 ");
			sql.append(" ,y.会議名 ");
			sql.append(" ,i.アイテム名 ");
			sql.append(" ,y.予約者名 ");
			sql.append(" FROM ");
			sql.append(getDefaultTable() + " y ");
			sql.append(" LEFT OUTER JOIN アイテム管理 i ");
			sql.append(" ON y.アイテム番号 ");
			sql.append(" = i.アイテム番号 ");
			sql.append(" WHERE	 ");
			sql.append(" y.予約番号 ");
			sql.append(" = ? ");

			// ステートメント作成
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, yoyakuNum);
			rs = pstmt.executeQuery();

			logger.info(sql.toString());
			logger.info("param : " + yoyakuNum);

			// 結果取得
			while (rs.next()) {
				// データベースから取得した値をセット
				kaigi.setYoyakuNum(rs.getString("予約番号")); // 予約番号
				kaigi.setYoyakuDay(rs.getString("予約日")); // 予約日
				kaigi.setStartTime(rs.getString("開始時間")); // 開始時間
				kaigi.setEndTime(rs.getString("終了時間")); // 終了時間
				kaigi.setKaigiName(rs.getString("会議名")); // 会議名
				kaigi.setItemName(rs.getString("アイテム名")); // アイテム名
				kaigi.setYoyakuName(rs.getString("予約者名")); // 予約者名
			}

		} catch (SQLException e) {
			logger.error("☆☆ 会議詳細取得#getKaigiSyosai()		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} catch (Exception e) {
			logger.error("☆☆ 会議詳細取得#getKaigiSyosai()		例外発生 ☆☆");
			logger.error("☆☆Exception☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ 会議詳細取得#getKaigiSyosai()		End ■■");
		}
		return kaigi;
	}

	/**
	 * @see insertYoyaku() : 会議予約を実行する
	 * @param String yoyakuDay, String startTime, String endTime, String kaigiName,
	 *               String itemNum, String yoyakuName
	 * @return なし
	 * @throws SQLException
	 */
	public void insertYoyaku(String yoyakuDay, String startTime, String endTime, String kaigiName, String itemNum,
			String yoyakuName) throws SQLException {

		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL

		try {

			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ 会議予約#insertYoyaku()			Start ■■");

			// SQL文生成
			sql.append(" INSERT ");
			sql.append(" INTO ");
			sql.append(getDefaultTable());
			sql.append(" ( ");
			sql.append(" 予約番号 ");
			sql.append(" ,予約日 ");
			sql.append(" ,開始時間 ");
			sql.append(" ,終了時間 ");
			sql.append(" ,会議名 ");
			sql.append(" ,アイテム番号 ");
			sql.append(" ,予約者名 ");
			sql.append(" ) ");
			sql.append(" SELECT ");
			sql.append(" LPAD(COALESCE(MAX(予約番号)+1, null), 5, '0') ");
			sql.append(" ,?	");
			sql.append(" ,?	");
			sql.append(" ,? 	");
			sql.append(" ,?	");
			sql.append(" ,?	");
			sql.append(" ,? 	");
			sql.append(" FROM ");
			sql.append(getDefaultTable());

			// ステートメント作成
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, yoyakuDay); // 予約日
			pstmt.setString(2, startTime); // 開始時間
			pstmt.setString(3, endTime); // 終了時間
			pstmt.setString(4, kaigiName); // 会議名
			pstmt.setString(5, itemNum); // アイテム番号
			pstmt.setString(6, yoyakuName); // 予約者名

			logger.info(sql.toString());
			logger.info("INSERT :" + yoyakuDay + "," + startTime + "," + endTime + "," + kaigiName + "," + itemNum + ","
					+ yoyakuName);

			// ステートメント実行
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("☆☆ 会議予約#insertYoyaku()		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} catch (Exception e) {
			logger.error("☆☆ 会議予約#insertYoyaku()		例外発生 ☆☆");
			logger.error("☆☆Exception☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ 会議予約#insertYoyaku()			End ■■");
		}
	}

	/**
	 * @see updateYoyaku() : 予約を更新する
	 * @param String yoyakuNum
	 * @param String yoyakuDay
	 * @param String startTime
	 * @param String endTime
	 * @param String kaigiName
	 * @param String itemNum
	 * @param String yoyakuName
	 * @return なし
	 * @throws SQLException
	 */
	public void updateYoyaku(String yoyakuNum, String yoyakuDay, String startTime, String endTime, String kaigiName,
			String itemNum, String yoyakuName) throws SQLException {

		Connection conn = null; // データベースコネクション
		PreparedStatement pstmt = null; // ステートメント
		StringBuffer sql = new StringBuffer(); // SQL

		try {

			// DB接続
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("■■ 予約更新#updateYoyaku()			Start ■■");

			// SQL文生成
			sql.append(" UPDATE ");
			sql.append(getDefaultTable());
			sql.append(" SET ");
			sql.append(" 予約日 ");
			sql.append(" = ?	");
			sql.append(" ,開始時間 ");
			sql.append(" = ?	");
			sql.append(" ,終了時間 ");
			sql.append(" = ?	");
			sql.append(" ,会議名 ");
			sql.append(" = ?	");
			sql.append(" ,アイテム番号 ");
			sql.append(" = ?	");
			sql.append(" ,予約者名 ");
			sql.append(" = ?	");
			sql.append(" WHERE	");
			sql.append(" 予約番号 ");
			sql.append(" = ? 	");

			// ステートメント作成
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, yoyakuDay); // 予約日
			pstmt.setString(2, startTime); // 開始時間
			pstmt.setString(3, endTime); // 終了時間
			pstmt.setString(4, kaigiName); // 会議名
			pstmt.setString(5, itemNum); // アイテム番号
			pstmt.setString(6, yoyakuName); // 予約者名
			pstmt.setString(7, yoyakuNum); // 予約番号

			logger.info(sql.toString());
			logger.info("INSERT :" + yoyakuDay + "," + startTime + "," + endTime + "," + kaigiName + "," + itemNum + ","
					+ yoyakuName + "," + yoyakuNum);

			// ステートメント実行
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("☆☆ 予約更新#updateYoyaku() 例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
		} catch (Exception e) {
			logger.error("☆☆ 予約更新#updateYoyaku() 例外発生 ☆☆");
			logger.error("☆☆Exception☆☆" + e.getMessage());
		} finally {
			// 資源を閉じる
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("■■ 予約更新#updateYoyaku() End ■■");
		}
	}

	/**
	 * @see String#getDafaultTable()
	 */
	public String getDefaultTable() {
		return "予約管理";
	}

}
