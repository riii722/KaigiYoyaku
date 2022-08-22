package jp.co.yoyaku.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import jp.co.yoyaku.cmn.CommonUtil;
import jp.co.yoyaku.entity.Item;
import jp.co.yoyaku.entity.ItemPersister;
import jp.co.yoyaku.entity.KaigiSyosai;
import jp.co.yoyaku.entity.KaigiSyosaiPersister;
import jp.co.yoyaku.entity.MyDate;
import jp.co.yoyaku.entity.MyDateTask;

/**
 * Servlet implementation class SYS00100
 */
@WebServlet("/SYS00100")
public class SYS00100 extends AjaxServlet {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(SYS00100.class.getName());

	final String KYS100 = "/WEB-INF/jsp/SYS00100.jsp"; // URL

	/**
	 * Default constructor.
	 */
	public SYS00100() {
		
	}

	/**
	 * リクエスト識別子
	 */
	private final String REQUEST_STRING = "requestJs";

	@Override
	protected Map<String, String> execute(HttpServletRequest request) {

		// =============================================================
		// JSONオブジェクトを分解
		// =============================================================
		Map<String, String> resultMap = CommonUtil.decodeJSON(request.getParameter(REQUEST_STRING));

		// ---------------------------------------------------------------------------
		// 日付と曜日を取得する
		// ---------------------------------------------------------------------------
		String year = resultMap.get("year");
		String month = resultMap.get("month");
		String day = resultMap.get("day");

		// ---------------------------------------------------------------------------
		// 日付をセット
		// ---------------------------------------------------------------------------
		MyDate mydate = new MyDate(); // インスタンス生成
		MyDateTask datePstr = new MyDateTask(); // インスタンス生成
		mydate = datePstr.getDate(year, month, day);

		// テーブルアイテムの取得
		ItemPersister ItemPstr = new ItemPersister(); // インスタンス生成
		List<Item> items = null; // 検索結果リスト

		// 予約データの取得
		KaigiSyosaiPersister syosaiPstr = new KaigiSyosaiPersister(); // 日付管理用インスタンスの生成
		List<KaigiSyosai> syosai = null; // 検索結果リスト

		// 返却用マップを生成
		Map<String, String> resMap = new HashMap<String, String>();

		try {
			// アイテム名を取得する
			items = ItemPstr.ItemList();
			// 予約データを取得する
			syosai = syosaiPstr.KaigiSyosaiList(mydate.getDate());

		} catch (SQLException e) {
			logger.error("☆☆SYS00100#execute		例外発生 ☆☆");
			logger.error("☆☆SQLException☆☆" + e.getMessage());
			resMap.put("errorMessage", "例外発生");
		} finally {
			resMap.put("errorMessage", "");
		}

		return resMap;
	}

//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// Jspからデータ受け取り
//		request.setCharacterEncoding("UTF8");
//		String yoyakuDay = request.getParameter("yoyakuDay").replace("/", ""); // 予約日
//		String startTime = request.getParameter("startTime").replace(":", ""); // 開始時間
//		String endTime = request.getParameter("endTime").replace(":", ""); // 終了時間
//		String kaigiName = request.getParameter("kaigiName"); // 会議名
//		String itemNum = request.getParameter("itemNum"); // アイテム番号
//		String yoyakuName = request.getParameter("yoyakuName"); // 予約者
//
//		KaigiSyosaiPersister syosaiPstr = new KaigiSyosaiPersister(); // インスタンスの生成
//		try {
//			syosaiPstr.insertYoyaku(yoyakuDay, startTime, endTime, kaigiName, itemNum, yoyakuName);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		// KYS100へリダイレクト
//		response.sendRedirect(getServletContext().getContextPath() + "/top");
//
//	}

}
