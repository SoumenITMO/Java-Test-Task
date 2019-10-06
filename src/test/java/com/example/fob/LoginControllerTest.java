package com.example.fob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testLoginPage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testLoginProcess() throws Exception{
        this.mockMvc.perform(post("/userLogin").contentType(MediaType.APPLICATION_JSON)
                .param("id_code", "7986986")
                .param("email", "test@test.com")
                .param("password","test123")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testLogout() throws Exception{
        this.mockMvc.perform(get("/logout")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status()
                        .is3xxRedirection());
    }
}
