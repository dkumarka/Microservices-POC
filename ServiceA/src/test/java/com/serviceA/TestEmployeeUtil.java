package com.serviceA;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class TestEmployeeUtil {
	
	public static final Integer Get_Employee_Id = 102;
	public static final Integer Update_Employee_Id = 101;
	public static final Integer Department_Id = 1;
	public static final Integer Delete_Employe_Id = 101;
	public static final Integer Get_Employee_With_NON_Existing_ID = 130;
	public static final Integer NON_Existing_Employee_ID = 1200;
	public static final String Get_All_Emp_URL = "http://localhost:8082/api/v1/getEmployees";
	public static final String Create_Emp_URL = "http://localhost:8082/api/v1/saveEmployee?departmentId=";
	public static final String Get_Emp_By_Id_URL = "http://localhost:8082/api/v1/getEmployee/";
	public static final String Update_Emp_URL = "http://localhost:8082/api/v1/updateEmployee/";
	public static final String Delete_Emp_By_Id_URL = "http://localhost:8082/api/v1/deleteEmployee/";
	public static final String Patch_Emp_URL = "";
	
	public static HttpEntity<String> createEntityForExchange(String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, token);
		return new HttpEntity<String>(headers);

	}

}
