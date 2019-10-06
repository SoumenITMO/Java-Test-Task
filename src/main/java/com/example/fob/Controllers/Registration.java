package com.example.fob.Controllers;

import com.example.fob.Model.User;
import com.example.fob.service.PasswordHash;
import com.example.fob.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
public class Registration {
    private UserService userService;
    private PasswordHash passwordHash;

    public Registration(UserService userService, PasswordHash passwordHash) {
        this.userService = userService;
        this.passwordHash = passwordHash;
    }

    @GetMapping("/registrationPage")
    public ModelAndView registrationPage() {
        ModelAndView registrationView = new ModelAndView();
        registrationView.setViewName("register");
        return registrationView;
    }

    @PostMapping("/registerNewUser")
    public ModelAndView registration(@RequestParam("fname") String firstName, @RequestParam("lname") String lastName,
                               @RequestParam("idcode") String idCode, @RequestParam("email") String email ,
                               @RequestParam("bday") String birthDay, @RequestParam("password") String password) {

        ModelAndView sendBackDataToRegisterForm = new ModelAndView();
        User registerNewUser = new User();

        if(!firstName.isEmpty() && !lastName.isEmpty() && !idCode.isEmpty() && !email.isEmpty() && // Check validation of Email
                !birthDay.isEmpty() && !password.isEmpty()) {
            if (!email.contains("@") && !email.contains(".")) {
                    sendBackDataToRegisterForm.addObject("invalidEmail", "Invalid Email.");
                    sendBackDataToRegisterForm.setViewName("register");
                    return sendBackDataToRegisterForm;
            }

            try {                                                               // Check validation of ID Code
                if(Integer.parseInt(idCode) != 0) {
                registerNewUser.setIdCode(Integer.parseInt(idCode));
                    if(checkIfUserExists(email, Integer.parseInt(idCode))) {
                        sendBackDataToRegisterForm.addObject("showError", "User Already Exists.");
                        sendBackDataToRegisterForm.setViewName("register");
                        return sendBackDataToRegisterForm;
                    }
                } else {
                    sendBackDataToRegisterForm.addObject("invalidIdCode", "Invalid ID Code.");
                    sendBackDataToRegisterForm.setViewName("register");
                    return sendBackDataToRegisterForm;
                }
            } catch (NumberFormatException invalidIdCode) {
                sendBackDataToRegisterForm.addObject("invalidIdCode", "Invalid ID Code.");
                sendBackDataToRegisterForm.setViewName("register");
                return sendBackDataToRegisterForm;
            }

            try {                                                               // Check validation of Birthday
                registerNewUser.setFname(firstName);
                registerNewUser.setLname(lastName);
                registerNewUser.setBirthday(new SimpleDateFormat("dd-MM-yyyy").parse(birthDay));
                registerNewUser.setPassword(passwordHash.hashPassword(password));
                registerNewUser.setEmail(email);
                userService.createNewUser(registerNewUser);
            } catch (ParseException dateParseException) {
                sendBackDataToRegisterForm.addObject("invalidBirthDate", "Invalid Date Format.");
                sendBackDataToRegisterForm.setViewName("register");
                return sendBackDataToRegisterForm;
            }
            sendBackDataToRegisterForm.setViewName("login");
            return sendBackDataToRegisterForm;
        } else {
            sendBackDataToRegisterForm.addObject("missingData", "Look Like Missing Data.");
            sendBackDataToRegisterForm.setViewName("register");
            return sendBackDataToRegisterForm;
        }
    }

    private boolean checkIfUserExists(String email, int idCode) {
        Optional<User> userExists = Optional.ofNullable(userService.checkIfUserExists(idCode, email));
        return userExists.isPresent();
    }
}