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

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testRegitrationPage() throws Exception {
        this.mockMvc.perform(get("/registrationPage"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterNewUser() throws Exception{
        this.mockMvc.perform(post("").contentType(MediaType.APPLICATION_JSON)
        .param("fname", "Soumen")
        .param("lname", "Banerjee")
        .param("idcode","970709")
        .param("email", "test@test.com")
        .param("bday", "12/12/2018")
        .param("password", "test123"));
    }
}
