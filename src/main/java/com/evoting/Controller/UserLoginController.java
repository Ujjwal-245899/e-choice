package com.evoting.Controller;

import com.evoting.DAO.EnrolledUserRepository;
import com.evoting.Model.EnrolledUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/electionList")
    public String showelectionList()
    {
        return "electionList";
    }

    @PostMapping("/checkUserLogin")
    public String checkUserLogin(@RequestParam("enrollmentId") String enrollmentId) {

        boolean f=false;

            EnrolledUser user = enrolledUserRepository.findByenrollmentNumber(enrollmentId);
            if(user!=null)
            {
                return "redirect:/page";
            }
            else
            {
                return "";
            }

    }
}
