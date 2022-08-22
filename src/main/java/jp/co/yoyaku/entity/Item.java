package jp.co.yoyaku.entity;

import java.io.Serializable;

public class Item implements Serializable  {
    private static final long serialVersionUID = 1L;

	private String itemNum;				// アイテム番号
	private String itemName;				// アイテム名
	private String itemOrder;				// 表示順序
	
    //コンストラクタ
    public Item(){}
    
    // アイテム番号
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	
	// アイテム名
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	// 表示順序
	public String getItemOrder() {
		return itemOrder;
	}
	public void setItemOrder(String itemOrder) {
		this.itemOrder = itemOrder;
	}
}
