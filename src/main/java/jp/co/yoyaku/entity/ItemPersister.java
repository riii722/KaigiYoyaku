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
	 * @see ItemList() : �A�C�e���ꗗ���擾���ăZ�b�g����
	 * @return items#List<Item>
	 * @throws SQLException
	 */
	public List<Item> ItemList() throws SQLException {
		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL
		ResultSet rs = null; // ���ʃZ�b�g
		List<Item> items = new ArrayList<Item>(); // �������ʃ��X�g

		try {

			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� �A�C�e���ꗗ�擾#ItemList()		Start ����");

			// SQL������
			sql.append(" SELECT 	");
			sql.append(" �A�C�e���ԍ�	");
			sql.append(" ,�A�C�e���� ");
			sql.append(" ,REPLACE(LTRIM(REPLACE(�\������,'0',' ')),' ','0') AS zero�\������ ");
			sql.append(" FROM ");
			sql.append(getDefaultTable());
			sql.append(" ORDER BY	");
			sql.append(" �\������ ASC	");

			// �X�e�[�g�����g�쐬
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			logger.info(sql.toString());

			// ���ʎ擾
			while (rs.next()) {
				// �f�[�^�x�[�X����擾�����l���Z�b�g
				Item item = new Item(); // �C���X�^���X�̐���
				item.setItemNum(rs.getString("�A�C�e���ԍ�")); // �A�C�e���ԍ�
				item.setItemName(rs.getString("�A�C�e����")); // �A�C�e����
				item.setItemOrder(rs.getString("zero�\������")); // �\������
				items.add(item);
			}

		} catch (SQLException e) {
			logger.error("���� �A�C�e���ꗗ�擾#ItemList()		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} finally {
			// ���������
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� �A�C�e���ꗗ�擾 #ItemList()		End ����");
		}
		return items;
	}

	/**
	 * @see getItem() : �A�C�e�����擾���ăZ�b�g����
	 * @param String itemNum
	 * @return item#Item
	 * @throws SQLException
	 */
	public Item getItem(String itemNum) throws SQLException {
		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL
		ResultSet rs = null; // ���ʃZ�b�g
		Item item = new Item(); // �C���X�^���X�̐���

		try {
			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� �A�C�e���擾#getItem()			Start ����");

			// SQL������
			sql.append(" SELECT ");
			sql.append(" �A�C�e���ԍ� ");
			sql.append(" ,�A�C�e���� ");
			sql.append(" ,REPLACE(LTRIM(REPLACE(�\������,'0',' ')),' ','0') AS �\������ ");
			sql.append(" FROM ");
			sql.append(getDefaultTable());
			sql.append(" WHERE ");
			sql.append(" �A�C�e���ԍ� = ? ");

			// �X�e�[�g�����g�쐬
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, itemNum);
			rs = pstmt.executeQuery();

			logger.info(sql.toString());
			logger.info("param : " + itemNum);

			// ���ʎ擾
			while (rs.next()) {
				// �f�[�^�x�[�X����擾�����l���Z�b�g
				item.setItemNum(rs.getString("�A�C�e���ԍ�")); // �A�C�e���ԍ�
				item.setItemName(rs.getString("�A�C�e����")); // �A�C�e����
				item.setItemOrder(rs.getString("�\������")); // �\������
			}

		} catch (SQLException e) {
			logger.error("���� �A�C�e���擾#getItem()		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} finally {
			// ���������
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� �A�C�e���擾 #getItem()		End ����");
		}
		return item;
	}

	/**
	 * @see InsertItem() : �A�C�e����o�^����
	 * @param String itemName, String itemOrder
	 * @return �Ȃ�
	 * @throws SQLException
	 */
	public void InsertItem(String itemName, String itemOrder) throws SQLException {

		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL

		try {
			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� �A�C�e���o�^#InsertItem()			Start ����");

			// SQL������
			sql.append(" INSERT ");
			sql.append(" INTO ");
			sql.append(getDefaultTable());
			sql.append(" ( ");
			sql.append(" �A�C�e���ԍ� ");
			sql.append(" ,�A�C�e���� ");
			sql.append(" ,�\������ ");
			sql.append(" ) ");
			sql.append(" SELECT ");
			sql.append(" LPAD(COALESCE(MAX(�A�C�e���ԍ�)+1, null), 3, '0') ");
			sql.append(" ,? ");
			sql.append(" ,? ");
			sql.append(" FROM ");
			sql.append(getDefaultTable());

			// �X�e�[�g�����g�쐬
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, itemName);
			pstmt.setString(2, itemOrder);

			logger.info(sql.toString());
			logger.info("INSERT :" + itemName + "," + itemOrder);

			// �X�e�[�g�����g���s
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("���� �A�C�e���o�^#InsertItem()		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} finally {
			// ���������
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� �A�C�e���o�^ #InsertItem()		End ����");
		}
	}

	/**
	 * @see UpdateItem() : �A�C�e�����X�V����
	 * @param String ItemNum, String itemName, String itemOrder
	 * @return �Ȃ�
	 * @throws SQLException
	 */
	public void UpdateItem(String itemNum, String itemName, String itemOrder) throws SQLException {

		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL

		try {
			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� �A�C�e���X�V#UpdateItem()			Start ����");

			// SQL������
			sql.append(" UPDATE ");
			sql.append(getDefaultTable());
			sql.append(" SET ");

			// �A�C�e�������󔒂̏ꍇ�A�X�V���Ȃ�
			if (itemName != "") {
				sql.append(" �A�C�e���� 	");
				sql.append(" = ? ");
			}

			// �A�C�e�������󔒂łȂ����A�\���������󔒂łȂ��ꍇ��[,]������
			// �A�C�e�������󔒂̏ꍇ�A�\��������[,]�����Ȃ�
			if (itemName != "" && !(itemOrder.equals("000"))) {
				sql.append(" ,�\������ ");
				sql.append(" = ? ");
			} else if (itemName == "" && !(itemOrder.equals("000"))) {
				sql.append(" �\������ ");
				sql.append(" = ? ");
			}

			sql.append(" WHERE ");
			sql.append(" �A�C�e���ԍ� ");
			sql.append(" = ? ");

			// �X�e�[�g�����g�쐬
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

			// �X�e�[�g�����g���s
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("���� �A�C�e���X�V #UpdateItem()		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} finally {
			// ���������
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� �A�C�e���X�V #UpdateItem()		End ����");
		}
	}

	/**
	 * @see DeleteItem() : �A�C�e�����폜����
	 * @param String ItemNum
	 * @return �Ȃ�
	 * @throws SQLException
	 */
	public void DeleteItem(String itemNum) throws SQLException {

		Connection conn = null; // �f�[�^�x�[�X�R�l�N�V����
		PreparedStatement pstmt = null; // �X�e�[�g�����g
		StringBuffer sql = new StringBuffer(); // SQL
		try {

			// DB�ڑ�
			conn = DriverManager.getConnection(URL, User, Password);
			logger.info("���� �A�C�e���폜#DeleteItem()			Start ����");

			// SQL������
			sql.append(" DELETE ");
			sql.append(" FROM ");
			sql.append(getDefaultTable());
			sql.append(" WHERE ");
			sql.append(" �A�C�e���ԍ� ");
			sql.append(" = ? ");

			// �X�e�[�g�����g�쐬
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, itemNum);

			logger.info(sql.toString());
			logger.info("DELETE :" + itemNum);

			// �X�e�[�g�����g���s
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("���� �A�C�e���폜#DeleteItem()		��O���� ����");
			logger.error("����SQLException����" + e.getMessage());
		} finally {
			// ���������
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			logger.info("���� �A�C�e���폜#DeleteItem()		End ����");
		}
	}

	/**
	 * @see String#getDafaultTable()
	 */
	public String getDefaultTable() {
		return "�A�C�e���Ǘ�";
	}

}
