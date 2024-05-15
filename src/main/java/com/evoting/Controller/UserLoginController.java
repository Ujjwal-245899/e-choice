package com.evoting.Controller;

import com.evoting.DAO.EnrolledUserRepository;
import com.evoting.Model.EnrolledUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserLoginController {

    @Autowired
    private EnrolledUserRepository enrolledUserRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String showLogin()
    {
        return "loginUser";
    }

    @PostMapping("/verifyLogin")
    public String verifyloginUser(@RequestParam("enrollNumber") String enrollment)
    {
      List<EnrolledUser> users = enrolledUserRepository.findAll();
      session.setAttribute("enrollment", enrollment);
      EnrolledUser user=null;

        for (EnrolledUser i:
             users) {

            if(i.getEnrollmentNumber().trim().equals(enrollment.trim()))
            {
                user=i;
                break;
            }


        }

        if(user!=null)
        {
            return "redirect:/echoice/electionlist";
        }
        else
        {
            return  "error";
        }



    }
}
