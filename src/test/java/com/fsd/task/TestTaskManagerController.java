package com.fsd.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fsd.task.bean.Task;
import com.fsd.task.controller.TaskMngrRestController;
import com.fsd.task.service.TaskManagerService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TaskMngrRestController.class })
@WebAppConfiguration
public class TestTaskManagerController {

	private MockMvc mockMvc;
	
	@MockBean
	TaskManagerService taskManagerService;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    @Test
    public void testAddTask() throws Exception {
        Task task = new Task();
        task.setTaskName("TestTask1");
        String json = new Gson().toJson(task);

        mockMvc.perform(
                post("/taskmanager/rest/task")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testUpdateTask() throws Exception {
        Task task = new Task();
        task.setTaskName("TestTask1");
        task.setStatus("TestTask1");
        String json = new Gson().toJson(task);

        mockMvc.perform(
                put("/taskmanager/rest/task")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetTask() throws Exception {
        
        mockMvc.perform(get("/taskmanager/rest/task/1"))
        .andExpect(status().isOk());
    }
    
    @Test
    public void testGetTasks() throws Exception {
        
        mockMvc.perform(get("/taskmanager/rest/task"))
        .andExpect(status().isOk());
    }
    
    
}
