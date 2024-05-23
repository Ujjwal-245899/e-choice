package com.evoting.Controller;

import com.evoting.DAO.UserRepositry;
import com.evoting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailLoginController {

    @Autowired
    private UserRepositry userRepositry;

    @GetMapping("/loginpage")
    public String showloginPage()
    {
        return "login";
    }

    @PostMapping("/echoice/userverify")
    @ResponseBody
    public ModelAndView verifyLoginPage(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userRepositry.findByemail(email);

        if (user != null && user.getPassword().equals(password)) {

            ModelAndView mdl = new ModelAndView("casting.html");
            mdl.addObject("name",user.getName());
            return mdl;
        } else {
            return new ModelAndView("error.html");
        }
    }
}
