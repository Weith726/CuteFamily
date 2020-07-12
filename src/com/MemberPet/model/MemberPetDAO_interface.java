package com.MemberPet.model;

import java.util.List;

public interface MemberPetDAO_interface {

	public void insert(MemberPetVO memberPetVO);

	public void update(MemberPetVO memberPetVO);

	public void delete(String petNo);

	public MemberPetVO findByPrimaryKey(String petNo);
	
	public List<MemberPetVO> getPetsFromThisMember(String memNo);

	public List<MemberPetVO> getAll();
}
