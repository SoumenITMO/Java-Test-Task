package com.example.fob.service;

import com.example.fob.Model.User;
import com.example.fob.Repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class DbSeederService {
      private Users users;
      private PasswordHash passwordHash;

      @Autowired
      public DbSeederService(Users users, PasswordHash passwordHash) {
          this.users = users;
          this.passwordHash = passwordHash;
      }

      public void seedDB() {
          User user = new User();
          user.setFname("Soumen");
          user.setLname("Soumen");
          user.setPassword(passwordHash.hashPassword("test123"));
          user.setEmail("soumen@soumen.com");
          user.setIdCode(5123124);
          user.setBirthday(new Date());
          users.save(user);
      }
}
