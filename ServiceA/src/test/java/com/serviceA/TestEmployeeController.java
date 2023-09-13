/*
 * package com.serviceA;
 * 
 * import static com.serviceA.TestEmployeeUtil.*;
 * 
 * import static com.serviceA.TestEmployeeUtil.createEntityForExchange;
 * 
 * import java.util.HashMap; import java.util.Map;
 * 
 * import org.junit.jupiter.api.Assertions; import
 * org.junit.jupiter.api.BeforeAll; import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.Answers;
 * import org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.Mockito; import org.mockito.junit.jupiter.MockitoExtension;
 * import org.springframework.http.HttpMethod; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import com.serviceA.model.Employee; import
 * com.serviceA.service.EmployeeServiceImpl;
 * 
 * import io.restassured.RestAssured; import io.restassured.response.Response;
 * 
 * @ExtendWith(MockitoExtension.class) public class TestEmployeeController {
 * 
 * @Mock(answer = Answers.RETURNS_DEEP_STUBS) private RestTemplate restTemplate;
 * 
 * @InjectMocks private EmployeeServiceImpl empService = new
 * EmployeeServiceImpl();
 * 
 * 
 * @Mock Employee employee;
 * 
 * 
 * private static String access_token;
 * 
 * @BeforeAll public static void setup() { access_token =
 * obtainAccessToken("clientapp", "clientapp", "9999"); }
 * 
 * 
 * @Test public void testGetEmployeeById() {
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(new
 * Employee(), HttpStatus.ACCEPTED);
 * Mockito.when(restTemplate.exchange(Get_Emp_By_Id_URL + Get_Employee_Id,
 * HttpMethod.GET, createEntityForExchange(access_token),
 * Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Employee employee = empService.getEmployeeById(Get_Employee_Id,
 * access_token); Assertions.assertEquals(employee, mock);
 * 
 * }
 * 
 * 
 * 
 * @Test public void testGetEmployeeById_NonExisting_Id() {
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(new
 * Employee(), HttpStatus.ACCEPTED);
 * Mockito.when(restTemplate.exchange(Get_Emp_By_Id_URL +
 * Get_Employee_With_NON_Existing_ID, HttpMethod.GET,
 * createEntityForExchange(access_token), Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Employee employee =
 * empService.getEmployeeById(Get_Employee_With_NON_Existing_ID, access_token);
 * Assertions.assertEquals(employee, mock);
 * 
 * }
 * 
 * 
 * @Test public void testGetAllEmployees() {
 * 
 * Employee[] empList = new Employee[] {}; ResponseEntity<Employee[]> response =
 * new ResponseEntity<Employee[]>(empList, HttpStatus.OK);
 * Mockito.when(restTemplate.exchange(Get_All_Emp_URL, HttpMethod.GET,
 * createEntityForExchange(access_token),
 * Employee[].class)).thenReturn(response);
 * 
 * Employee[] mock = response.getBody();
 * 
 * Assertions.assertEquals(empService.getAllEmployee(access_token).size(),
 * mock.length);
 * 
 * }
 * 
 * @Test public void testCreateEmployee() {
 * 
 * Employee newEmployee = new Employee(120, "Priyanka", 80000.0, "Active");
 * 
 * ResponseEntity<Employee> employee = empService.createEmployee(1, newEmployee,
 * access_token);
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(newEmployee,
 * HttpStatus.OK);
 * 
 * Mockito.when(restTemplate.exchange(Create_Emp_URL + 1, HttpMethod.GET,
 * createEntityForExchange(access_token), Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Assertions.assertEquals(employee.getBody(), mock); }
 * 
 * @Test public void testCreateEmployee_NotCreated() {
 * 
 * Employee newEmployee = new Employee(1001, "Priyanka", 80000.0, "Active");
 * 
 * ResponseEntity<Employee> employee = empService.createEmployee(12,
 * newEmployee, access_token);
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(new
 * Employee(), HttpStatus.ACCEPTED);
 * 
 * Mockito.when(restTemplate.exchange(Get_Emp_By_Id_URL + 1001, HttpMethod.GET,
 * createEntityForExchange(access_token), Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Assertions.assertEquals(employee.getBody(), null);
 * Assertions.assertEquals(mock, null); }
 * 
 * @Test public void testCreateEmployee_with_Existing_Id() {
 * 
 * Employee newEmployee = new Employee(101, "Priyanka", 80000.0, "Active");
 * 
 * ResponseEntity<Employee> employee = empService.createEmployee(12,
 * newEmployee, access_token);
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(new
 * Employee(), HttpStatus.ACCEPTED);
 * 
 * Mockito.when(restTemplate.exchange(Get_Emp_By_Id_URL + 101, HttpMethod.GET,
 * createEntityForExchange(access_token), Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Assertions.assertEquals(employee.getBody(), mock); }
 * 
 * @Test public void testUpdateEmployee() {
 * 
 * Employee updateEmployee = new Employee(101, "Priyanka", 80000.0, "Active");
 * ResponseEntity<Employee> employee = empService.updateEmployee(updateEmployee,
 * Update_Employee_Id, access_token);
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(new
 * Employee(), HttpStatus.ACCEPTED);
 * 
 * Mockito.when(restTemplate.exchange(Get_Emp_By_Id_URL + 101, HttpMethod.GET,
 * createEntityForExchange(access_token), Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Assertions.assertEquals(employee.getBody(), mock); }
 * 
 * @Test public void testUpdateEmployee_with_Non_Existing_Id() {
 * 
 * Employee updateEmployee = new Employee(NON_Existing_Employee_ID, "Priyanka",
 * 80000.0, "Active"); ResponseEntity<Employee> employee =
 * empService.updateEmployee(updateEmployee, Update_Employee_Id, access_token);
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(new
 * Employee(), HttpStatus.ACCEPTED);
 * 
 * Mockito.when(restTemplate.exchange(Get_Emp_By_Id_URL +
 * NON_Existing_Employee_ID, HttpMethod.GET,
 * createEntityForExchange(access_token), Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Assertions.assertEquals(employee.getBody(), mock); }
 * 
 * @Test public void testDeleteEmployee() {
 * 
 * Employee deleteEmployee = new Employee(101, "Priyanka", 80000.0, "Active");
 * String employee = empService.deleteEmployee(deleteEmployee , access_token);
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(new
 * Employee(), HttpStatus.OK);
 * 
 * Mockito.when(restTemplate.exchange(Get_Emp_By_Id_URL + Delete_Employe_Id,
 * HttpMethod.GET, createEntityForExchange(access_token),
 * Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Assertions.assertEquals("Employee Deleted Successfully!!", employee);
 * Assertions.assertEquals(mock, null);
 * 
 * }
 * 
 * @Test public void testDeleteEmployee_with_Non_Existing_Id() {
 * 
 * Employee deleteEmployee = new Employee(101, "Priyanka", 80000.0, "Active");
 * String employee = empService.deleteEmployee(deleteEmployee, access_token);
 * 
 * ResponseEntity<Employee> response = new ResponseEntity<Employee>(new
 * Employee(), HttpStatus.OK);
 * 
 * Mockito.when(restTemplate.exchange(Get_Emp_By_Id_URL +
 * NON_Existing_Employee_ID, HttpMethod.GET,
 * createEntityForExchange(access_token), Employee.class)).thenReturn(response);
 * 
 * Employee mock = response.getBody();
 * 
 * Assertions.assertEquals("Employee Not Delete!!", employee);
 * Assertions.assertEquals(mock, null);
 * 
 * }
 * 
 * private static String obtainAccessToken(String clientId, String username,
 * String password) {
 * 
 * Map<String, String> params = new HashMap<>(); params.put("grant_type",
 * "client_credentials"); params.put("client_id", clientId);
 * params.put("username", username); params.put("password", password); Response
 * response = RestAssured.given().auth().preemptive().basic(clientId,
 * password).and().with()
 * .params(params).when().post("http://localhost:8083/oauth/token");
 * 
 * System.out.println(response.getBody()); return
 * response.jsonPath().getString("access_token"); }
 * 
 * 
 * 
 * 
 * }
 */