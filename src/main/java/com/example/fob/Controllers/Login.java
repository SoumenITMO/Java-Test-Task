package com.example.fob.Controllers;

import com.example.fob.Model.User;
import com.example.fob.service.LoginService;
import com.example.fob.service.PasswordHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Controller
public class Login {
    private LoginService loginService;
    private PasswordHash passwordHash;

    public Login(LoginService loginService, PasswordHash passwordHash) {
        this.loginService = loginService;
        this.passwordHash = passwordHash;
    }

    @GetMapping("/login")
    public ModelAndView LoginPage(HttpSession session) {
        ModelAndView loginView = new ModelAndView("login");
        return loginView;
    }

    @GetMapping("/logout")
    public String logoutPage(HttpSession session) {
        removeSessionData(session);
        return "redirect:login";
    }

    @PostMapping("/userLogin")
    public String UserLogin(@RequestParam("id_code") String idCode, @RequestParam("email") String email,
                      @RequestParam("password") String password, HttpSession session) {
        Optional<User> user = loginService.checkLogin(idCode, email, passwordHash.hashPassword(password));
        if(user.isPresent()) {
            String tokenKey = UUID.randomUUID().toString();
            session.setAttribute("logged_in_token", tokenKey);
            session.setAttribute("logged_in", 1);
            session.setAttribute("email", email);
            session.setAttribute("idCode", idCode);
            return "redirect:userDetails";
        }
        return "redirect:login";
    }

    private void removeSessionData(HttpSession session) {
        if(session.getAttribute("RefreshCount") != null || session.getAttribute("logged_in_token") != null ||
                session.getAttribute("logged_in") != null || session.getAttribute("email") != null ||
                session.getAttribute("idCode") != null || session.getAttribute("RefreshCount") != null) {
            session.removeAttribute("logged_in");
            session.removeAttribute("logged_in_token");
            session.removeAttribute("email");
            session.removeAttribute("idCode");
            session.removeAttribute("RefreshCount");
        }
    }
}
