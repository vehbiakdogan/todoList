package com.vehbiakdogan.todo;


import com.vehbiakdogan.todo.controller.AppController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AppController.class)
public class InitializerApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenContextRootUrlIsAccessed_thenStatusIsOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(200));
    }

    @Test
    public void whenContextRootUrlIsAccesed_thenCorrectStringIsReturned() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("InitializerApplication  running!")));
    }
}
