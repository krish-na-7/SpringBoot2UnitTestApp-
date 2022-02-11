package in.nareshit.raghu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class TestEmployeeRestController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Disabled
	public void testSaveEmployee() throws Exception{

		// 1. create dummy request
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders
				.post("/api/v1/employee/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"empName \" : \"Ram\",\"empSal\" : 3300.0}");

		// 2. execute request and get result  	
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3. read response
		MockHttpServletResponse response = result.getResponse();

		// 4. test using assert
		//is status code is 200 or not
		assertEquals(HttpStatus.OK.value(),response.getStatus());
		if(!response.getContentAsString().contains("Employee Saved")) {
			fail("SAVE EMPLOYEE NOT WORKING");
		}
	}

	@Test
	@Disabled
	public void testGetAllEmployees() throws Exception {
		// 1. create dummy request
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders
				.get("/api/v1/employee/all");

		// 2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();    

		// 3. read response
		MockHttpServletResponse response = result.getResponse();

		// 4. test using assert
		//is status code is 200 or not
		assertEquals(HttpStatus.OK.value(),response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE,response.getContentType());
		if(response.getContentAsString().length()<=0) {
			fail("NO DATA PROVIDED");
		}
	}

	@Test
	@Disabled
	public void testGetOneEmployeeExist() throws Exception {
		// 1. create dummy request
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders
				.get("/api/v1/employee/one/3");

		// 2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3. read response
		MockHttpServletResponse response = result.getResponse();

		// 4. test using assert
		//is status code is 200 or not
		assertEquals(HttpStatus.OK.value(),response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE,response.getContentType());
		if(response.getContentAsString().isEmpty()) {
			fail("Employee data not provided");
		}
	}

	@Test
	@Disabled
	public void testGetOneEmployeeNotExists() throws Exception {
		// 1. create dummy request
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders
				.get("/api/v1/employee/one/30");

		// 2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3. read response
		MockHttpServletResponse response = result.getResponse();

		// 4. test using assert
		//is status code is 404 or not
		assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
		if(!response.getContentAsString().equals("Employee not exists")) {
			fail("May be data exist, please check again");
		}
	}

	@Test
	@Disabled
	public void testDeleteEmployee() throws Exception {
		// 1. create dummy request
		MockHttpServletRequestBuilder request  = 
				MockMvcRequestBuilders.delete("/api/v1/employee/remove/3");

		// 2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3. read response
		MockHttpServletResponse response = result.getResponse();

		// 4. test using assert
		//is status code is 200 or not
		assertEquals(HttpStatus.OK.value(),response.getStatus());
		assertEquals("Employee With 3 is deleted",response.getContentAsString());
	}

	@Test
	public void testDeleteEmployeeNotExist() throws Exception {
		// 1. create dummy request
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.delete("/api/v1/employee/remove/30");

		// 2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3. read response
		MockHttpServletResponse response = result.getResponse();

		// 4. test using assert
		//is status code is 404 or not
		assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
		assertEquals("Employee not exists",response.getContentAsString());
	}

	@Test
	public void testupdateEmployee() throws Exception {
		//1. create Dummy Request
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders
				.put("/api/v1/employee/modify/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"empName\":\"Sita SA\",\"empSal\":700000.0}");

		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();

		//3. Read Response
		MockHttpServletResponse response = result.getResponse();

		//4. Test using assert Method --status-200
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("Employee Updated", response.getContentAsString());
	}
}
// 1. create dummy request
// 2. execute request and get result
// 3. read response
// 4. test using assert
//is status code is 200 or not
