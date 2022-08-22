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
	 * @see KaigiSyosaiList()�F�w�肵�����t�����c���e���ꗗ�Ŏ擾����B
	 * @param String date
	 * @return List<KaigiSyosai>
	 * @throws SQLException
	 */
//	public List<KaigiSyosai> KaigiSyosaiList(String date) throws ServletException, IOException, SQLException {
	public List<KaigiSyosai> KaigiSyosaiList(String date) throws SQLException {

		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL
		ResultSet rs = null; // ���ʃZ�b�g
		List<KaigiSyosai> syosai = new ArrayList<KaigiSyosai>(); // �������ʃ��X�g

		try {

			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� ��c�ڍ׎擾#KaigiSyosaiList() Start ����");

			// SQL������
			sql.append(" SELECT ");
			sql.append(" y.�\��ԍ� ");
			sql.append(" ,y.�\��� ");
			sql.append(" ,y.�J�n���� ");
			sql.append(" ,y.�I������ ");
			sql.append(" ,y.��c�� ");
			sql.append(" ,i.�A�C�e���� ");
			sql.append(" ,y.�\��Җ� ");
			sql.append(" FROM ");
			sql.append(getDefaultTable() + " y ");
			sql.append(" LEFT OUTER JOIN �A�C�e���Ǘ� i ");
			sql.append(" ON y.�A�C�e���ԍ� ");
			sql.append(" = i.�A�C�e���ԍ� ");
			sql.append(" WHERE	 ");
			sql.append(" y.�\��� ");
			sql.append(" = ? ");
			sql.append(" ORDER BY ");
			sql.append(" i.�\������ ");
			sql.append(" ,y.�J�n���� ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();

			logger.info(sql.toString());
			logger.info("param : " + date);

			// ���ʎ擾
			while (rs.next()) {
				// �f�[�^�x�[�X����擾�����l���Z�b�g
				KaigiSyosai kaigi = new KaigiSyosai(); // �C���X�^���X�̐���
				kaigi.setYoyakuNum(rs.getString("�\��ԍ�")); // �\��ԍ�
				kaigi.setYoyakuDay(rs.getString("�\���")); // �\���
				kaigi.setStartTime(rs.getString("�J�n����")); // �J�n����
				kaigi.setEndTime(rs.getString("�I������")); // �I������
				kaigi.setKaigiName(rs.getString("��c��")); // ��c��
				kaigi.setItemName(rs.getString("�A�C�e����")); // �A�C�e����
				kaigi.setYoyakuName(rs.getString("�\��Җ�")); // �\��Җ�
				syosai.add(kaigi);
			}

		} catch (SQLException e) {
			logger.error("���� ��c�ڍ׎擾#KaigiSyosaiList()		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} catch (Exception e) {
			logger.error("���� ��c�ڍ׎擾#KaigiSyosaiList()		��O���� ����");
			logger.error("����Exception����" + e.getMessage());
		} finally {
			// ���������
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� ��c�ڍ׎擾#KaigiSyosaiList()		End ����");
		}
		return syosai;
	}

	/**
	 * @see getKaigiSyosai() : �w�肵����c�̏ڍׂ��擾����
	 * @param String yoyakuNum
	 * @return KaigiSyosai
	 * @throws SQLException
	 */
	public KaigiSyosai getKaigiSyosai(String yoyakuNum) throws SQLException {

		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL
		ResultSet rs = null; // ���ʃZ�b�g
		KaigiSyosai kaigi = new KaigiSyosai(); // �C���X�^���X�̐���

		try {

			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� ��c�ڍ׎擾#getKaigiSyosai()		Start ����");

			// SQL������
			sql.append(" SELECT ");
			sql.append(" y.�\��ԍ� ");
			sql.append(" ,y.�\��� ");
			sql.append(" ,y.�J�n���� ");
			sql.append(" ,y.�I������ ");
			sql.append(" ,y.��c�� ");
			sql.append(" ,i.�A�C�e���� ");
			sql.append(" ,y.�\��Җ� ");
			sql.append(" FROM ");
			sql.append(getDefaultTable() + " y ");
			sql.append(" LEFT OUTER JOIN �A�C�e���Ǘ� i ");
			sql.append(" ON y.�A�C�e���ԍ� ");
			sql.append(" = i.�A�C�e���ԍ� ");
			sql.append(" WHERE	 ");
			sql.append(" y.�\��ԍ� ");
			sql.append(" = ? ");

			// �X�e�[�g�����g�쐬
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, yoyakuNum);
			rs = pstmt.executeQuery();

			logger.info(sql.toString());
			logger.info("param : " + yoyakuNum);

			// ���ʎ擾
			while (rs.next()) {
				// �f�[�^�x�[�X����擾�����l���Z�b�g
				kaigi.setYoyakuNum(rs.getString("�\��ԍ�")); // �\��ԍ�
				kaigi.setYoyakuDay(rs.getString("�\���")); // �\���
				kaigi.setStartTime(rs.getString("�J�n����")); // �J�n����
				kaigi.setEndTime(rs.getString("�I������")); // �I������
				kaigi.setKaigiName(rs.getString("��c��")); // ��c��
				kaigi.setItemName(rs.getString("�A�C�e����")); // �A�C�e����
				kaigi.setYoyakuName(rs.getString("�\��Җ�")); // �\��Җ�
			}

		} catch (SQLException e) {
			logger.error("���� ��c�ڍ׎擾#getKaigiSyosai()		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} catch (Exception e) {
			logger.error("���� ��c�ڍ׎擾#getKaigiSyosai()		��O���� ����");
			logger.error("����Exception����" + e.getMessage());
		} finally {
			// ���������
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� ��c�ڍ׎擾#getKaigiSyosai()		End ����");
		}
		return kaigi;
	}

	/**
	 * @see insertYoyaku() : ��c�\������s����
	 * @param String yoyakuDay, String startTime, String endTime, String kaigiName,
	 *               String itemNum, String yoyakuName
	 * @return �Ȃ�
	 * @throws SQLException
	 */
	public void insertYoyaku(String yoyakuDay, String startTime, String endTime, String kaigiName, String itemNum,
			String yoyakuName) throws SQLException {

		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL

		try {

			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� ��c�\��#insertYoyaku()			Start ����");

			// SQL������
			sql.append(" INSERT ");
			sql.append(" INTO ");
			sql.append(getDefaultTable());
			sql.append(" ( ");
			sql.append(" �\��ԍ� ");
			sql.append(" ,�\��� ");
			sql.append(" ,�J�n���� ");
			sql.append(" ,�I������ ");
			sql.append(" ,��c�� ");
			sql.append(" ,�A�C�e���ԍ� ");
			sql.append(" ,�\��Җ� ");
			sql.append(" ) ");
			sql.append(" SELECT ");
			sql.append(" LPAD(COALESCE(MAX(�\��ԍ�)+1, null), 5, '0') ");
			sql.append(" ,?	");
			sql.append(" ,?	");
			sql.append(" ,? 	");
			sql.append(" ,?	");
			sql.append(" ,?	");
			sql.append(" ,? 	");
			sql.append(" FROM ");
			sql.append(getDefaultTable());

			// �X�e�[�g�����g�쐬
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, yoyakuDay); // �\���
			pstmt.setString(2, startTime); // �J�n����
			pstmt.setString(3, endTime); // �I������
			pstmt.setString(4, kaigiName); // ��c��
			pstmt.setString(5, itemNum); // �A�C�e���ԍ�
			pstmt.setString(6, yoyakuName); // �\��Җ�

			logger.info(sql.toString());
			logger.info("INSERT :" + yoyakuDay + "," + startTime + "," + endTime + "," + kaigiName + "," + itemNum + ","
					+ yoyakuName);

			// �X�e�[�g�����g���s
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("���� ��c�\��#insertYoyaku()		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} catch (Exception e) {
			logger.error("���� ��c�\��#insertYoyaku()		��O���� ����");
			logger.error("����Exception����" + e.getMessage());
		} finally {
			// ���������
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� ��c�\��#insertYoyaku()			End ����");
		}
	}

	/**
	 * @see updateYoyaku() : �\����X�V����
	 * @param String yoyakuNum
	 * @param String yoyakuDay
	 * @param String startTime
	 * @param String endTime
	 * @param String kaigiName
	 * @param String itemNum
	 * @param String yoyakuName
	 * @return �Ȃ�
	 * @throws SQLException
	 */
	public void updateYoyaku(String yoyakuNum, String yoyakuDay, String startTime, String endTime, String kaigiName,
			String itemNum, String yoyakuName) throws SQLException {

		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL

		try {

			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� �\��X�V#updateYoyaku()			Start ����");

			// SQL������
			sql.append(" UPDATE ");
			sql.append(getDefaultTable());
			sql.append(" SET ");
			sql.append(" �\��� ");
			sql.append(" = ?	");
			sql.append(" ,�J�n���� ");
			sql.append(" = ?	");
			sql.append(" ,�I������ ");
			sql.append(" = ?	");
			sql.append(" ,��c�� ");
			sql.append(" = ?	");
			sql.append(" ,�A�C�e���ԍ� ");
			sql.append(" = ?	");
			sql.append(" ,�\��Җ� ");
			sql.append(" = ?	");
			sql.append(" WHERE	");
			sql.append(" �\��ԍ� ");
			sql.append(" = ? 	");

			// �X�e�[�g�����g�쐬
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, yoyakuDay); // �\���
			pstmt.setString(2, startTime); // �J�n����
			pstmt.setString(3, endTime); // �I������
			pstmt.setString(4, kaigiName); // ��c��
			pstmt.setString(5, itemNum); // �A�C�e���ԍ�
			pstmt.setString(6, yoyakuName); // �\��Җ�
			pstmt.setString(7, yoyakuNum); // �\��ԍ�

			logger.info(sql.toString());
			logger.info("INSERT :" + yoyakuDay + "," + startTime + "," + endTime + "," + kaigiName + "," + itemNum + ","
					+ yoyakuName + "," + yoyakuNum);

			// �X�e�[�g�����g���s
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("���� �\��X�V#updateYoyaku() ��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} catch (Exception e) {
			logger.error("���� �\��X�V#updateYoyaku() ��O���� ����");
			logger.error("����Exception����" + e.getMessage());
		} finally {
			// ���������
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� �\��X�V#updateYoyaku() End ����");
		}
	}

	/**
	 * @see String#getDafaultTable()
	 */
	public String getDefaultTable() {
		return "�\��Ǘ�";
	}

}
