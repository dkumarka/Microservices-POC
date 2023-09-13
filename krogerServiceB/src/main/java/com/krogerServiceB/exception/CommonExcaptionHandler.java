package com.krogerServiceB.exception;

public class CommonExcaptionHandler extends RuntimeException {
	private static final long serialVersionUID = 1L;

	String str;
	public CommonExcaptionHandler() {
	}

	public CommonExcaptionHandler(String str) {
		super(String.format("Please Provide Valide Input : %s", str));
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}