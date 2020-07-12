package com.MemberPet.model;

import java.util.List;

public class TestMemberPetDAO_JDBC {

	public static void main(String[] args) {

		MemberPetDAO dao = new MemberPetDAO();

		// 新增
//		MemberPetVO memberPetVO1 = new MemberPetVO();
//		memberPetVO1.setPetNo("P0016");
//		memberPetVO1.setMemNo("M0001");
//		memberPetVO1.setPetName("咖啡");
//		memberPetVO1.setPetVariety("蠻牛");
//		memberPetVO1.setPetAge(10);
//		memberPetVO1.setPetGender("女");
//		memberPetVO1.setPetStatus(1);
//
//		dao.insert(memberPetVO1);
//		System.out.println("新增成功!");
//
//		// 修改
//		MemberPetVO memberPetVO2 = new MemberPetVO();
//
//		memberPetVO2.setPetNo("P0016");
//		memberPetVO2.setMemNo("M0002");
//		memberPetVO2.setPetName("咖啡2");
//		memberPetVO2.setPetVariety("蠻牛2");
//		memberPetVO2.setPetAge(10);
//		memberPetVO2.setPetGender("女");
//		memberPetVO2.setPetStatus(1);
//
//		dao.update(memberPetVO2);
//		System.out.println("修改成功!");
//
//		// 刪除
//		dao.delete("P0016");
//		System.out.println("刪除成功");

		// 查詢(單筆)
//		MemberPetVO memberPetVO3 = dao.findByPrimaryKey("P0015");
//
//		System.out.println("寵物編號:" + memberPetVO3.getPetNo());
//		System.out.println("會員編號:" + memberPetVO3.getMemNo());
//		System.out.println("寵物名稱:" + memberPetVO3.getPetName());
//		System.out.println("寵物品種:" + memberPetVO3.getPetVariety());
//		System.out.println("寵物年齡:" + memberPetVO3.getPetAge());
//		System.out.println("寵物性別:" + memberPetVO3.getPetGender());
//		System.out.println("寵物狀態:" + memberPetVO3.getPetStatus());
//		System.out.println("寵物圖片:" + memberPetVO3.getPetPic());
//		System.out.println("--------------------------------------------------");

		// 查詢(全部)
//		List<MemberPetVO> list = dao.getAll();
//
//		for (MemberPetVO memberPetAll : list) {
//			System.out.println("寵物編號:" + memberPetAll.getPetNo());
//			System.out.println("會員編號:" + memberPetAll.getMemNo());
//			System.out.println("寵物名稱:" + memberPetAll.getPetName());
//			System.out.println("寵物品種:" + memberPetAll.getPetVariety());
//			System.out.println("寵物年齡:" + memberPetAll.getPetAge());
//			System.out.println("寵物性別:" + memberPetAll.getPetGender());
//			System.out.println("寵物狀態:" + memberPetAll.getPetStatus());
//			System.out.println("寵物圖片:" + memberPetAll.getPetPic());
//			System.out.println("--------------------------------------------------");
//		}
		
		//查詢相對應的會員所擁有的全部寵物
		List<MemberPetVO> list2 = dao.getPetsFromThisMember("M0001");

		for (MemberPetVO memberPetAll : list2) {
			System.out.println("寵物編號:" + memberPetAll.getPetNo());
			System.out.println("寵物名稱:" + memberPetAll.getPetName());
			System.out.println("--------------------------------------------------");
		}
	
	}
}
