package com.example.fob;

import com.example.fob.service.LoginService;
import com.example.fob.service.PasswordHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertSame;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class LoginServiceTest {

    @Autowired
    LoginService loginServiceTest;
    @Autowired
    PasswordHash passwordHash;

    @Test
    public void testIfCheckUser() {

        assertSame(true, loginServiceTest.checkLogin("5123124", "soumen@soumen.com",
                passwordHash.hashPassword("test123")).isPresent());
    }
}
