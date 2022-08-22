package jp.co.yoyaku.entity;

import java.io.Serializable;

public class KaigiSyosai implements Serializable  {
    private static final long serialVersionUID = 1L;

	private String yoyakuNum;					// �\��ԍ�
	private String yoyakuDay;					// �\���
	private String startTime;						// �J�n����
	private String endTime;						// �I������
	private String itemName;						// �A�C�e����
	private String kaigiName;					// ��c��
	private String yoyakuName;				// �\���

    //�R���X�g���N�^
    public KaigiSyosai(){}
    
	public String getYoyakuNum() {
		return yoyakuNum;
	}
	public void setYoyakuNum(String yoyakuNum) {
		this.yoyakuNum = yoyakuNum;
	}
	
	public String getYoyakuDay() {
		return yoyakuDay;
	}
	public void setYoyakuDay(String yoyakuDay) {
		this.yoyakuDay = yoyakuDay;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getKaigiName() {
		return kaigiName;
	}
	public void setKaigiName(String kaigiName) {
		this.kaigiName = kaigiName;
	}
	
	public String getYoyakuName() {
		return yoyakuName;
	}
	public void setYoyakuName(String yoyakuName) {
		this.yoyakuName = yoyakuName;
	}
}
