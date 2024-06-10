package com.evoting.Controller;

import com.evoting.DAO.UserRepositry;
import com.evoting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TrackMyApplicationController {

    @Autowired
    private UserRepositry userRepositry;

    @GetMapping("/trackmyapplication/{userId}")
    public String trackMyApplication(@PathVariable String userId, Model model) {
        // Fetch user information based on the userId
        // Add user information to the model

        User user = userRepositry.findByid(Long.valueOf(userId));

        model.addAttribute("user",user);

        // Return the name of the Thymeleaf template to render
        return "trackmyapplication";
    }
}
