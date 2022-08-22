package jp.co.yoyaku.entity;

import java.util.Calendar;

public class MyDateTask {

	/**
	 * @see MyDate#setDate() �T�v�Fjsp����N�G���p�����[�^���󂯎��A���t���Z�b�g���� return : String date
	 */
	public MyDate getDate(String year, String month, String day) {
		// ---------------------------------------------------------------------------
		// ���t�Ǘ��̃C���X�^���X����
		// ---------------------------------------------------------------------------
		MyDate mydate = new MyDate(); // ���t�Ǘ��p�C���X�^���X�̐���
		Calendar cal = Calendar.getInstance(); // �J�����_�[�N���X�̃I�u�W�F�N�g�𐶐�����

		// ---------------------------------------------------------------------------
		// �N�p�����[�^�̎擾
		// ---------------------------------------------------------------------------
		if (year == null || year.length() == 0) {
			year = Integer.toString(cal.get(Calendar.YEAR));
			mydate.setYear(cal.get(Calendar.YEAR));
		} else {
			mydate.setYear(Integer.parseInt(year));
		}

		// ---------------------------------------------------------------------------
		// ���p�����[�^�̎擾
		// ---------------------------------------------------------------------------
		if (month == null || month.length() == 0) {
			month = String.format("%2s", Integer.toString(cal.get(Calendar.MONTH) + 1)).replace(" ", "0"); // 0���߂���
			mydate.setMonth(cal.get(Calendar.MONTH) + 1);
		} else {
			// ---------------------------------------------------------------------------
			// �����u0�v�̏ꍇ�P�N�O��12���ɂ���
			// �����u13�v�̏ꍇ�P�N���1���ɂ���
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
			month = String.format("%2s", Integer.toString(imonth)).replace(" ", "0"); // 0���߂���
			mydate.setYear(iyear);
			mydate.setMonth(imonth);
		}

		// ---------------------------------------------------------------------------
		// ���p�����[�^�̎擾
		// ---------------------------------------------------------------------------
		if (day == null || day.length() == 0) {
			day = String.format("%2s", Integer.toString(cal.get(Calendar.DATE))).replace(" ", "0"); // 0���߂���
			mydate.setDay(cal.get(Calendar.DATE));
		} else {
			// ---------------------------------------------------------------------------
			// �����u0�v�̏ꍇ�P�����O�̍ŏI���ɂ���
			// �������̌��̍ŏI�����傫���ꍇ1������̏����ɂ���
			// ---------------------------------------------------------------------------
			int imonth = mydate.getMonth();
			int iday = Integer.parseInt(day);
			// ���̌��̓��̍ő���擾
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
			month = String.format("%2s", Integer.toString(imonth)).replace(" ", "0"); // 0���߂���
			day = String.format("%2s", iday).replace(" ", "0"); // 0���߂���
			mydate.setMonth(imonth);
			mydate.setDay(iday);
		}

		// ---------------------------------------------------------------------------
		// SQL�p�̓��t���Z�b�g����
		// ---------------------------------------------------------------------------
		mydate.setDate(year + month + day);

		// ---------------------------------------------------------------------------
		// ���t����j�����擾����
		// ---------------------------------------------------------------------------
		cal.set(mydate.getYear(), mydate.getMonth() - 1, mydate.getDay());
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			mydate.setYobi("��");
			break;
		case Calendar.MONDAY:
			mydate.setYobi("��");
			break;
		case Calendar.TUESDAY:
			mydate.setYobi("��");
			break;
		case Calendar.WEDNESDAY:
			mydate.setYobi("��");
			break;
		case Calendar.THURSDAY:
			mydate.setYobi("��");
			break;
		case Calendar.FRIDAY:
			mydate.setYobi("��");
			break;
		case Calendar.SATURDAY:
			mydate.setYobi("�y");
			break;
		}

		return mydate;
	}

}
