package com.example.Todoz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodozController {

    @GetMapping("/")
    public String books(Model model) {


        return "/index";
    }

}
