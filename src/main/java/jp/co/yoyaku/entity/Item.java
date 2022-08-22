package jp.co.yoyaku.entity;

import java.io.Serializable;

public class Item implements Serializable  {
    private static final long serialVersionUID = 1L;

	private String itemNum;				// �A�C�e���ԍ�
	private String itemName;				// �A�C�e����
	private String itemOrder;				// �\������
	
    //�R���X�g���N�^
    public Item(){}
    
    // �A�C�e���ԍ�
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	
	// �A�C�e����
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	// �\������
	public String getItemOrder() {
		return itemOrder;
	}
	public void setItemOrder(String itemOrder) {
		this.itemOrder = itemOrder;
	}
}
