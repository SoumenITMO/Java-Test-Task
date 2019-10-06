package com.example.fob.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Controller
public class InvalidSession {
    private int refreshCount = 0;

    @GetMapping("/401")
    public ModelAndView page401(HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView tokenExpire = new ModelAndView();
        tokenExpire.setViewName("401");
        session.setAttribute("logged_in_token", null);
        if(session.getAttribute("logged_in") != null) {
            session.setAttribute("RefreshCount", ++this.refreshCount);
            if (session.getAttribute("RefreshCount").equals(2) && session.getAttribute("logged_in") != null) {
                String tokenKey = UUID.randomUUID().toString();
                session.setAttribute("logged_in_token", tokenKey);
                this.refreshCount = 0;
                response.sendRedirect("userDetails");
            }
        } else {
            tokenExpire.setViewName("login");
            return tokenExpire;
        }
        response.setHeader("Refresh", 300 + "; URL=/login");
        return tokenExpire;
    }
}
