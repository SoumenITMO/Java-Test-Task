package com.example.fob;

import com.example.fob.Model.User;
import com.example.fob.service.PasswordHash;
import com.example.fob.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    PasswordHash passwordHash;

    @Test
    public void testToGetUserDetailsByIdCodeOrEmail() {
        User user;

        user = userService.checkIfUserExists(510000, "soumen@soumen.com");
        assertSame(true, user.getFirstName().equals("Soumen"));
        user = userService.checkIfUserExists(5123124, "soumen@soumen.com1");
        assertSame(true, user.getFirstName().equals("Soumen"));
    }

    @Test
    public void testToCreateNewUser() {
        User userEntity = new User();
        userEntity.setFname("Alexy");
        userEntity.setIdCode(88888);
        userEntity.setEmail("alexy@yandex.ru");
        userService.createNewUser(userEntity);

        userEntity = userService.checkIfUserExists(88888, "alexy@yandex.ru");
        assertSame(true, userEntity.getFirstName().equals("Alexy"));
    }
}
