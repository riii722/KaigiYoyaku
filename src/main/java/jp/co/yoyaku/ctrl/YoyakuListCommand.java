package jp.co.yoyaku.ctrl;

public class YoyakuListCommand {

	public void execute() {
		//---------------------------------------------------------------------------
		// ���t���Ɨj�����擾���� 
		//---------------------------------------------------------------------------
//		YoyakuListCommandParameter param = new YoyakuListCommandParameter();
//		String year = param.getParameter("YEAR");
//		String month = param.getParameter("MONTH");
//		String day = param.getParameter("DAY");
//		MyDate mydate = new MyDate();							// �C���X�^���X����
//		MyDatePersister datePstr = new MyDatePersister();		// �C���X�^���X����
//	    mydate = datePstr.getDate(year, month, day);
//	
//		ItemPersister ItemPstr = new ItemPersister();		// �A�C�e���擾�C���X�^���X����
//		List<Item> items = null;							// �������ʃ��X�g
//		KaigiSyosaiPersister syosaiPstr = new KaigiSyosaiPersister();	//���t�Ǘ��p�C���X�^���X�̐���
//		List<KaigiSyosai> syosai = null;				// �������ʃ��X�g
//		
//	    try{
//	    	// �A�C�e�������擾����
//	    	items = ItemPstr.ItemList();
//			// �\��f�[�^���擾����
//			syosai = syosaiPstr.KaigiSyosaiList(mydate.getDate());
//	
//		} catch (SQLException e) {
//	    	System.out.println("���� KYS100#doPost		��O���� ����");
//	    	System.out.println( "����SQLException����" + e.getMessage() );
//		}finally{
//			//---------------------------------------------------------------------------
//			// �f�[�^��jsp�ɓn��
//			//---------------------------------------------------------------------------
//			request.setAttribute("mydate", mydate);
//			request.setAttribute("item", items);
//			request.setAttribute("kaigiSyosai", syosai);
//				
//			// KYS100 �Ƀy�[�W�J��
//	        RequestDispatcher dispatcher = request.getRequestDispatcher(KYS100);
//	        dispatcher.forward(request, response);
//		}
	}
}
