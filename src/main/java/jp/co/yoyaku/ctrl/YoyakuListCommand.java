package jp.co.yoyaku.ctrl;

public class YoyakuListCommand {

	public void execute() {
		//---------------------------------------------------------------------------
		// 日付をと曜日を取得する 
		//---------------------------------------------------------------------------
//		YoyakuListCommandParameter param = new YoyakuListCommandParameter();
//		String year = param.getParameter("YEAR");
//		String month = param.getParameter("MONTH");
//		String day = param.getParameter("DAY");
//		MyDate mydate = new MyDate();							// インスタンス生成
//		MyDatePersister datePstr = new MyDatePersister();		// インスタンス生成
//	    mydate = datePstr.getDate(year, month, day);
//	
//		ItemPersister ItemPstr = new ItemPersister();		// アイテム取得インスタンス生成
//		List<Item> items = null;							// 検索結果リスト
//		KaigiSyosaiPersister syosaiPstr = new KaigiSyosaiPersister();	//日付管理用インスタンスの生成
//		List<KaigiSyosai> syosai = null;				// 検索結果リスト
//		
//	    try{
//	    	// アイテム名を取得する
//	    	items = ItemPstr.ItemList();
//			// 予約データを取得する
//			syosai = syosaiPstr.KaigiSyosaiList(mydate.getDate());
//	
//		} catch (SQLException e) {
//	    	System.out.println("☆☆ KYS100#doPost		例外発生 ☆☆");
//	    	System.out.println( "☆☆SQLException☆☆" + e.getMessage() );
//		}finally{
//			//---------------------------------------------------------------------------
//			// データをjspに渡す
//			//---------------------------------------------------------------------------
//			request.setAttribute("mydate", mydate);
//			request.setAttribute("item", items);
//			request.setAttribute("kaigiSyosai", syosai);
//				
//			// KYS100 にページ遷移
//	        RequestDispatcher dispatcher = request.getRequestDispatcher(KYS100);
//	        dispatcher.forward(request, response);
//		}
	}
}
