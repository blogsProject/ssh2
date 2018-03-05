package com.luotianyi.ssh2.entiy;

public enum GenderEnum {
	woman("女"), man("男");
	private final String gender;

	public String getGender() {
		return gender;
	}

	private GenderEnum(String gender) {
		this.gender = gender;
	}

}
