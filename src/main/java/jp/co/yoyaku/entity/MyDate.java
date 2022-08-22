package jp.co.yoyaku.entity;

import java.io.Serializable;

public class MyDate implements Serializable  {
    private static final long serialVersionUID = 1L;

	private int year;				// 年
	private int month;				// 月
	private int day;					// 日
	private String date;			//日付
	private String yobi;			//曜日

    //コンストラクタ
    public MyDate(){}
    
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getYobi() {
		return yobi;
	}
	public void setYobi(String yobi) {
		this.yobi = yobi;
	}

}
