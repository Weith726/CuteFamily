package com.MemberPet.model;

public class MemberPetVO implements java.io.Serializable {

	private String petNo;
	private String memNo;
	private String petName;
	private String petVariety;
	private Integer petAge;
	private String petGender;
	private Integer petStatus;
	private byte[] petPic;

	public String getPetNo() {
		return petNo;
	}

	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetVariety() {
		return petVariety;
	}

	public void setPetVariety(String petVariety) {
		this.petVariety = petVariety;
	}

	public Integer getPetAge() {
		return petAge;
	}

	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}

	public String getPetGender() {
		return petGender;
	}

	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}

	public Integer getPetStatus() {
		return petStatus;
	}

	public void setPetStatus(Integer petStatus) {
		this.petStatus = petStatus;
	}

	public byte[] getPetPic() {
		return petPic;
	}

	public void setPetPic(byte[] petPic) {
		this.petPic = petPic;
	}

}
