package com.krogerServiceB.exception;

import java.util.List;

public class EmployeeNotFoundBYIDsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	String resorceName;
	String fieldName;
	List<Integer> fieldValue;

	public EmployeeNotFoundBYIDsException(String resorceName, String fieldName, List<Integer> fieldValue) {
		super(String.format("%s Not Found with %s : %s", resorceName, fieldName, fieldValue));
		this.resorceName = resorceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	


	public String getResorceName() {
		return resorceName;
	}

	public void setResorceName(String resorceName) {
		this.resorceName = resorceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<Integer> getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(List<Integer> fieldValue) {
		this.fieldValue = fieldValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

