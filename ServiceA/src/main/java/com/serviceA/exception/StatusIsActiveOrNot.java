package com.serviceA.exception;

public class StatusIsActiveOrNot extends RuntimeException {
	private static final long serialVersionUID = 1L;
	/*
	 * String resorceName; String status; String fieldValue;
	 *
	 * public String getStatus() { return status; }
	 *
	 * public String getResorceName() { return resorceName; }
	 *
	 * public void setResorceName(String resorceName) { this.resorceName =
	 * resorceName; }
	 *
	 * public String getFieldValue() { return fieldValue; }
	 *
	 * public void setFieldValue(String fieldValue) { this.fieldValue = fieldValue;
	 * }
	 *
	 * public void setStatus(String status) { this.status = status; }
	 *
	 * public StatusIsActiveOrNot(String resorceName, String status, String
	 * fieldValue) { super(String.format("Status is Not Valid or required ",
	 * resorceName, status, fieldValue)); this.status = status; }
	 *
	 * public static long getserialversionuid() { return serialVersionUID; }
	 */
	public StatusIsActiveOrNot(String message) {
		super(message);
	}
}