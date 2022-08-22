package jp.co.yoyaku.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import jp.co.yoyaku.cmn.CommonUtil;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/AjaxServlet")
public abstract class AjaxServlet extends HttpServlet {

	/**
	 * シリアル番号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ロガー
	 */
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * エラーメッセージ
	 */
	protected String errorMessage;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * リクエスト識別子
	 */
	protected final String REQUEST_STRING = "requestJs";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 初期化
		this.errorMessage = "";

		// 戻り値
		Map<String, String> resMap = new HashMap<String, String>();

		// レスポンスのセット
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 下位クラスのexecuteをコール
		try {
			logger.info("■■ Ajaxサーブレット トランザクション Start ■■");

			// サーブレット処理
			resMap = execute(request);

			// エラーなしでセット
			resMap.put("errorMessage", "");

			logger.info("■■ Ajaxサーブレット トランザクション Commit ■■");
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorMessage", "");
			// メッセージのセット
			if (!"".equals(this.errorMessage)) {
				resMap.put("errorMessage", this.errorMessage);
			} else {
				resMap.put("errorMessage", "");
			}
		}

		// 結果の出力
		out.print(CommonUtil.encodeJSON(resMap));
	}

	/**
	 * 
	 * サーブレット内の各処理.
	 * 
	 * @param cmdCtx
	 * @param request
	 * @return
	 */
	protected abstract Map<String, String> execute(HttpServletRequest request);

}
