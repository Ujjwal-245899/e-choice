package com.evoting.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinksController {

    @GetMapping("/index")
    public String showTestPage()
    {
        return "index";
    }
    @GetMapping("/about")
    public  String testPage()
    {
        return "about.html";
    }

    @GetMapping("/work")
    public String showWrokPage()
    {
        return "work.html";
    }
    @GetMapping("/category")
    public String showCategoryPage()
    {
        return "category.html";
    }
    @GetMapping("/casting")
    public String showCastingPage()
    {
        return "casting.html";
    }
}
