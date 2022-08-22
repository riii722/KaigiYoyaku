package jp.co.yoyaku.entity;

import java.util.Calendar;

public class MyDateTask {

	/**
	 * @see MyDate#setDate() 概要：jspからクエリパラメータを受け取り、日付をセットする return : String date
	 */
	public MyDate getDate(String year, String month, String day) {
		// ---------------------------------------------------------------------------
		// 日付管理のインスタンス生成
		// ---------------------------------------------------------------------------
		MyDate mydate = new MyDate(); // 日付管理用インスタンスの生成
		Calendar cal = Calendar.getInstance(); // カレンダークラスのオブジェクトを生成する

		// ---------------------------------------------------------------------------
		// 年パラメータの取得
		// ---------------------------------------------------------------------------
		if (year == null || year.length() == 0) {
			year = Integer.toString(cal.get(Calendar.YEAR));
			mydate.setYear(cal.get(Calendar.YEAR));
		} else {
			mydate.setYear(Integer.parseInt(year));
		}

		// ---------------------------------------------------------------------------
		// 月パラメータの取得
		// ---------------------------------------------------------------------------
		if (month == null || month.length() == 0) {
			month = String.format("%2s", Integer.toString(cal.get(Calendar.MONTH) + 1)).replace(" ", "0"); // 0埋めする
			mydate.setMonth(cal.get(Calendar.MONTH) + 1);
		} else {
			// ---------------------------------------------------------------------------
			// 月が「0」の場合１年前の12月にする
			// 月が「13」の場合１年後の1月にする
			// ---------------------------------------------------------------------------
			int iyear = mydate.getYear();
			int imonth = Integer.parseInt(month);
			if (imonth == 0) {
				iyear--;
				imonth = 12;
			} else if (imonth == 13) {
				iyear++;
				imonth = 1;
			}
			year = Integer.toString(iyear);
			month = String.format("%2s", Integer.toString(imonth)).replace(" ", "0"); // 0埋めする
			mydate.setYear(iyear);
			mydate.setMonth(imonth);
		}

		// ---------------------------------------------------------------------------
		// 日パラメータの取得
		// ---------------------------------------------------------------------------
		if (day == null || day.length() == 0) {
			day = String.format("%2s", Integer.toString(cal.get(Calendar.DATE))).replace(" ", "0"); // 0埋めする
			mydate.setDay(cal.get(Calendar.DATE));
		} else {
			// ---------------------------------------------------------------------------
			// 日が「0」の場合１ヶ月前の最終日にする
			// 日がその月の最終日より大きい場合1ヶ月後の初日にする
			// ---------------------------------------------------------------------------
			int imonth = mydate.getMonth();
			int iday = Integer.parseInt(day);
			// その月の日の最大を取得
			cal.set(Calendar.YEAR, mydate.getYear());
			cal.set(Calendar.MONTH, mydate.getMonth() - 1);
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;

			if (iday == lastDay) {
				imonth++;
				iday = 1;

			} else if (iday == 0) {
				imonth--;
				cal.set(Calendar.MONTH, mydate.getMonth() - 2);
				iday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			}
			month = String.format("%2s", Integer.toString(imonth)).replace(" ", "0"); // 0埋めする
			day = String.format("%2s", iday).replace(" ", "0"); // 0埋めする
			mydate.setMonth(imonth);
			mydate.setDay(iday);
		}

		// ---------------------------------------------------------------------------
		// SQL用の日付をセットする
		// ---------------------------------------------------------------------------
		mydate.setDate(year + month + day);

		// ---------------------------------------------------------------------------
		// 日付から曜日を取得する
		// ---------------------------------------------------------------------------
		cal.set(mydate.getYear(), mydate.getMonth() - 1, mydate.getDay());
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			mydate.setYobi("日");
			break;
		case Calendar.MONDAY:
			mydate.setYobi("月");
			break;
		case Calendar.TUESDAY:
			mydate.setYobi("火");
			break;
		case Calendar.WEDNESDAY:
			mydate.setYobi("水");
			break;
		case Calendar.THURSDAY:
			mydate.setYobi("木");
			break;
		case Calendar.FRIDAY:
			mydate.setYobi("金");
			break;
		case Calendar.SATURDAY:
			mydate.setYobi("土");
			break;
		}

		return mydate;
	}

}
