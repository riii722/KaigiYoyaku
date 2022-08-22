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
	 * ���N�G�X�g���ʎq
	 */
	private final String REQUEST_STRING = "requestJs";

	@Override
	protected Map<String, String> execute(HttpServletRequest request) {

		// =============================================================
		// JSON�I�u�W�F�N�g�𕪉�
		// =============================================================
		Map<String, String> resultMap = CommonUtil.decodeJSON(request.getParameter(REQUEST_STRING));

		// ---------------------------------------------------------------------------
		// ���t�Ɨj�����擾����
		// ---------------------------------------------------------------------------
		String year = resultMap.get("year");
		String month = resultMap.get("month");
		String day = resultMap.get("day");

		// ---------------------------------------------------------------------------
		// ���t���Z�b�g
		// ---------------------------------------------------------------------------
		MyDate mydate = new MyDate(); // �C���X�^���X����
		MyDateTask datePstr = new MyDateTask(); // �C���X�^���X����
		mydate = datePstr.getDate(year, month, day);

		// �e�[�u���A�C�e���̎擾
		ItemPersister ItemPstr = new ItemPersister(); // �C���X�^���X����
		List<Item> items = null; // �������ʃ��X�g

		// �\��f�[�^�̎擾
		KaigiSyosaiPersister syosaiPstr = new KaigiSyosaiPersister(); // ���t�Ǘ��p�C���X�^���X�̐���
		List<KaigiSyosai> syosai = null; // �������ʃ��X�g

		// �ԋp�p�}�b�v�𐶐�
		Map<String, String> resMap = new HashMap<String, String>();

		try {
			// �A�C�e�������擾����
			items = ItemPstr.ItemList();
			// �\��f�[�^���擾����
			syosai = syosaiPstr.KaigiSyosaiList(mydate.getDate());

		} catch (SQLException e) {
			logger.error("����SYS00100#execute		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
			resMap.put("errorMessage", "��O����");
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
//		// Jsp����f�[�^�󂯎��
//		request.setCharacterEncoding("UTF8");
//		String yoyakuDay = request.getParameter("yoyakuDay").replace("/", ""); // �\���
//		String startTime = request.getParameter("startTime").replace(":", ""); // �J�n����
//		String endTime = request.getParameter("endTime").replace(":", ""); // �I������
//		String kaigiName = request.getParameter("kaigiName"); // ��c��
//		String itemNum = request.getParameter("itemNum"); // �A�C�e���ԍ�
//		String yoyakuName = request.getParameter("yoyakuName"); // �\���
//
//		KaigiSyosaiPersister syosaiPstr = new KaigiSyosaiPersister(); // �C���X�^���X�̐���
//		try {
//			syosaiPstr.insertYoyaku(yoyakuDay, startTime, endTime, kaigiName, itemNum, yoyakuName);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		// KYS100�փ��_�C���N�g
//		response.sendRedirect(getServletContext().getContextPath() + "/top");
//
//	}

}
