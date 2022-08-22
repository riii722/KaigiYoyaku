package jp.co.yoyaku.servlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class ChangeDateServlet
 */
@WebServlet("/ChangeDateServlet")
public class ChangeDateServlet extends AjaxServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeDateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Map<String, String> execute(HttpServletRequest request) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
