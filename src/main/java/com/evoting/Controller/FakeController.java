package com.evoting.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FakeController {

    @GetMapping("/test")
    public String showTestPage()
    {
        return "testResul";
    }
}
