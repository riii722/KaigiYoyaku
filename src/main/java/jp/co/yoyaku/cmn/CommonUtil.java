package jp.co.yoyaku.cmn;

import java.util.HashMap;
import java.util.Map;

public class CommonUtil {

	/**
	 * JSON�p�����[�^�̐���
	 * 
	 * @param resMap
	 * @return
	 */
	public static String encodeJSON(Map<String, String> resMap) {

		StringBuffer buf = new StringBuffer();
		buf.append("{");
		for (Map.Entry<String, String> e : resMap.entrySet()) {
			buf.append("\"");
			buf.append(e.getKey());
			buf.append("\" : \"");
			buf.append(e.getValue());
			buf.append("\",");
		}
		// �Ō�̃J���}�͏���
		buf.deleteCharAt(buf.length() - 1);
		buf.append("}");

		return buf.toString();
	}

	/**
	 * JSON�p�����[�^��MAP��.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> decodeJSON(String jsonString){
		
		Map<String, String> resultMap = new HashMap<String, String>();
		
		// ���[�̂������ƃ_�u���N�I�[�e�[�V�������������A�J���}�Ń��X�g����
		String[] workStrArray = jsonString.replaceAll("^\\{|\"|\\}$","").split(",");
		
		// �p�����[�^�̃��[�v
		for(String tmpStr : workStrArray){
			
			String[] tmpStrArray = tmpStr.split(":");
			if(tmpStrArray.length == 2){
				resultMap.put(tmpStrArray[0], tmpStrArray[1]);
			}else{
				resultMap.put(tmpStrArray[0], "");
			}
		}
				
		return resultMap;
		
	}
	
}
