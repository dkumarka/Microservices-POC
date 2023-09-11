package com.krogerServiceB.exception;

public class EmployeeNotFoundBYIDException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	String resorceName;
	String fieldName;
	long fieldValue;

	public EmployeeNotFoundBYIDException(String resorceName, String fieldName, long fieldValue) {
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

	public long getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
