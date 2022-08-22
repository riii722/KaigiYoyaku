package jp.co.yoyaku.cmn;

import java.util.HashMap;
import java.util.Map;

public class CommonUtil {

	/**
	 * JSONパラメータの生成
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
		// 最後のカンマは除去
		buf.deleteCharAt(buf.length() - 1);
		buf.append("}");

		return buf.toString();
	}

	/**
	 * JSONパラメータのMAP化.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> decodeJSON(String jsonString){
		
		Map<String, String> resultMap = new HashMap<String, String>();
		
		// 両端のかっことダブルクオーテーションを除去し、カンマでリスト生成
		String[] workStrArray = jsonString.replaceAll("^\\{|\"|\\}$","").split(",");
		
		// パラメータのループ
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
