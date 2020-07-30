package com.rabbit.common.enums;

public enum Sex {
	MAN((byte)1, "男"),
	WOMAN((byte)2, "女");
	
	private final byte code;
	
	private final String codeName;
	
	private Sex(byte code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}

	public byte getCode() {
		return code;
	}

	public String getCodeName() {
		return codeName;
	}
}
