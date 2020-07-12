package com.mem.model;
import java.util.List;

public class MTest {
	
	public static void main(String[] args) {
		MemberService se = new MemberService();
		MemberJDBCDAO mj = new MemberJDBCDAO(); //���ե� 
//		se.addM("3", "qqQQ", "jjjQQj", "bbbQQb", "aaaa", 93, "bbQQbb", "bbQQbb", 123);
		
		
//		se.updateM("M0001", "qq", "jj", "bbbqqqqb", "aaaa", 93, "bbbb", "bbbb", 123);
//		se.deleteM("M0004");
		
		List<MemberVO> list = se.getAll();
		for(MemberVO mv:list) {
			System.out.println(mv.getMemNo()+",");
		}
//		mj.passpic("D:/IMG_4083.png",2); //�g�J�Ϥ�
		
//		mj.readpic(2); //Ū�Ϥ�
		
	}
}
