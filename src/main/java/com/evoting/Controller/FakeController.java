package com.evoting.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FakeController {

    @GetMapping("/index")
    public String showTestPage()
    {
        return "index";
    }
    @GetMapping("/test")
    public  String testPage()
    {
        return "test";
    }
}
