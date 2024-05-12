package com.evoting.Controller;

import com.evoting.DAO.EnrolledUserRepository;
import com.evoting.Model.EnrolledUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserLoginController {

    @Autowired
    private EnrolledUserRepository enrolledUserRepository;

    @Autowired
    HttpSession session;

   @GetMapping("/userLogin")
    public String  showUserLogin()
   {
       return "loginUser";

    }
    @PostMapping("/login")
    public String verifyUser(@RequestParam("enrollment") String enrollment)
    {
        EnrolledUser user = enrolledUserRepository.findByenrollmentNumber(enrollment);

    session.setAttribute("enrollment",enrollment);

        System.out.println(" "+user.getEnrollmentNumber());


        if (user!=null)
        {
            return "redirect:/page";
        }
        else
        {
            return "error";
        }
    }
}
