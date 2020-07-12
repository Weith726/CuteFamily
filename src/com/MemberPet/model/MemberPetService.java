package com.MemberPet.model;

import java.util.List;


public class MemberPetService {

	private MemberPetDAO_interface dao;

	public MemberPetService() {
		dao = new MemberPetDAO();
	}

	public MemberPetVO addMemberPet(String petNo, String memNo, String petName, String petVariety, Integer petAge,
			String petGender, Integer petStatus, byte[] petPic) {

		MemberPetVO memberPetVO = new MemberPetVO();

		memberPetVO.setPetNo(petNo);
		memberPetVO.setMemNo(memNo);
		memberPetVO.setPetName(petName);
		memberPetVO.setPetVariety(petVariety);
		memberPetVO.setPetAge(petAge);
		memberPetVO.setPetGender(petGender);
		memberPetVO.setPetStatus(petStatus);
		memberPetVO.setPetPic(petPic);
		dao.insert(memberPetVO);

		return memberPetVO;
	}

	public MemberPetVO updateMemberPet(String petNo, String memNo, String petName, String petVariety, Integer petAge,
			String petGender, Integer petStatus, byte[] petPic) {

		MemberPetVO memberPetVO = new MemberPetVO();

		memberPetVO.setPetNo(petNo);
		memberPetVO.setMemNo(memNo);
		memberPetVO.setPetName(petName);
		memberPetVO.setPetVariety(petVariety);
		memberPetVO.setPetAge(petAge);
		memberPetVO.setPetGender(petGender);
		memberPetVO.setPetStatus(petStatus);
		memberPetVO.setPetPic(petPic);
		dao.update(memberPetVO);

		return memberPetVO;
	}
	
	public List<MemberPetVO> getPetsFromThisMember(String memNo) {
		return dao.getPetsFromThisMember(memNo);
	}

	public void deleteMemberPet(String petNo) {
		dao.delete(petNo);
	}

	public MemberPetVO getOneMemberPet(String petNo) {
		return dao.findByPrimaryKey(petNo);
	}

	public List<MemberPetVO> getAll() {
		return dao.getAll();
	}

}
