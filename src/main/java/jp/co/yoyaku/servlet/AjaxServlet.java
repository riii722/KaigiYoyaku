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
	 * �V���A���ԍ�
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���K�[
	 */
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * �G���[���b�Z�[�W
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
	 * ���N�G�X�g���ʎq
	 */
	protected final String REQUEST_STRING = "requestJs";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ������
		this.errorMessage = "";

		// �߂�l
		Map<String, String> resMap = new HashMap<String, String>();

		// ���X�|���X�̃Z�b�g
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// ���ʃN���X��execute���R�[��
		try {
			logger.info("���� Ajax�T�[�u���b�g �g�����U�N�V���� Start ����");

			// �T�[�u���b�g����
			resMap = execute(request);

			// �G���[�Ȃ��ŃZ�b�g
			resMap.put("errorMessage", "");

			logger.info("���� Ajax�T�[�u���b�g �g�����U�N�V���� Commit ����");
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorMessage", "");
			// ���b�Z�[�W�̃Z�b�g
			if (!"".equals(this.errorMessage)) {
				resMap.put("errorMessage", this.errorMessage);
			} else {
				resMap.put("errorMessage", "");
			}
		}

		// ���ʂ̏o��
		out.print(CommonUtil.encodeJSON(resMap));
	}

	/**
	 * 
	 * �T�[�u���b�g���̊e����.
	 * 
	 * @param cmdCtx
	 * @param request
	 * @return
	 */
	protected abstract Map<String, String> execute(HttpServletRequest request);

}
