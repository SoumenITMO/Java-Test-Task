package com.example.fob.Controllers;

import com.example.fob.Model.User;
import com.example.fob.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.Format;
import java.text.SimpleDateFormat;

@Controller
public class UserDetails {
    private UserService userService;

    public UserDetails(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userDetails")
    public String userDetails(Model userDataModel, HttpSession session, HttpServletResponse response) {
        session.setAttribute("RefreshCount", 0);
        if(session.getAttribute("logged_in_token") != null) {
            if(session.getAttribute("logged_in").equals(1)) {
                Format formatter = new SimpleDateFormat("d, MMMM, yyyy");
                String idCode = session.getAttribute("idCode") != null ? session.getAttribute("idCode").toString() : "";
                String email = session.getAttribute("email") != null ? session.getAttribute("email").toString() : "";
                User getUserDetails = getUserInformation(Integer.parseInt(idCode), email);
                userDataModel.addAttribute("firstName", getUserDetails.getFirstName());
                userDataModel.addAttribute("lastName", getUserDetails.getLastName());
                userDataModel.addAttribute("email", getUserDetails.getEmail());
                userDataModel.addAttribute("idCode", getUserDetails.getIdCode());
                userDataModel.addAttribute("birthDay", formatter.format(getUserDetails.getBirthday()));
                response.setHeader("Refresh", 10 + "; URL=/401");
                return "userDetailsPage";
            }
        }
        else if(session.getAttribute("logged_in") == null) {
            return "redirect:login";
        }
        else if(session.getAttribute("logged_in_token") == null) {
            return "redirect:401";
        }
        return "redirect:login";
    }

    private User getUserInformation(int idCode, String email) {
        return userService.getUserDetails(idCode, email);
    }
}