package com.example.fob.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Index {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index");
        return indexView;
    }
}
