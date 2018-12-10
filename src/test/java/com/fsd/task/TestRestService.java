package com.fsd.task;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fsd.task.service.TaskManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
public class TestRestService {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskManagerService taskManagerService;
	
	String sampleTaskJson = "{\"taskName\":\"TestTask1\",\"parentTask\":\"ParentTask1\",\"status\":\"N\",\"priority\",10}";
	
	@Test
	public void testCreateTask() throws Exception {
			
		/*Task mockTask = new Task();
		mockTask.setTaskName("TestTask1");
		mockTask.setParentTask("TestParentTask1");
		mockTask.setStatus("N");
		mockTask.setPriority(10);
		mockTask.setStartDate(new Date());
		mockTask.setEndDate(new Date());

		Mockito.when(
				taskManagerService.addTask(mockTask),
							Mockito.any(Task.class))).thenReturn(mockTask);*/

		RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/taskmanager/rest/task")
					.accept(MediaType.APPLICATION_JSON).content(sampleTaskJson)
					.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost:7070/spring-taskmanager-maven/taskmanager/rest/task/1",
					response.getHeader(HttpHeaders.LOCATION));

	}
	
	@Test
	public void testGetTask() {
		
	}

}
