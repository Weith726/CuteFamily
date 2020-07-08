/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Appt {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("seqno".equals(columnName)|| "symphoto".equals(columnName) || "optstate".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("memName".equals(columnName) || "optSession".equals(columnName)||"symdesc".equals(columnName)||"docname".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("optDate".equals(columnName))                          // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	
	public static String get_aCondition_For_Oracle_opt(String columnName, String value) {

		String aCondition = null;

		if ("maximum".equals(columnName)|| "currentCount".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("sessionNo".equals(columnName) || "docName".equals(columnName)||"optSession".equals(columnName)|| "divName".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("optDate".equals(columnName))                          // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}
	



	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();

		map.put("optDate", new String[] { "2020-07-27" });
		
		map.put("optSession", new String[] { "14:00~17:00" }); // 注意Map裡面會含有action的key

		map.put("docname", new String[] { "李美玲" });
		String finalSQL = "SELECT memName,docname,to_char(optDate,'yyyy-mm-dd')optDate,optSession,seqno,symdesc,symphoto,optstate "+
				"FROM APPOINTMENT "+
				"JOIN OPTSESSION ON APPOINTMENT.sessionNo = OPTSESSION.sessionNo "+
	            "JOIN MEMBER ON APPOINTMENT.MEMNO = MEMBER.MEMNO "+
	            "JOIN DOCTOR ON OPTSESSION.DOCNO = DOCTOR.DOCNO "+
	            jdbcUtil_CompositeQuery_Appt.get_WhereCondition(map)
	            + " order by seqno";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
