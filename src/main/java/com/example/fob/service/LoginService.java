package com.example.fob.service;

import com.example.fob.Model.User;
import com.example.fob.Repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LoginService {

    private Users users;

    @Autowired
    public LoginService(Users users) {
        this.users = users;
    }

    public Optional<User> checkLogin(String idCode, String email, String password) {
        return users.checkUserExists(idCode, email, password);
    }
}
