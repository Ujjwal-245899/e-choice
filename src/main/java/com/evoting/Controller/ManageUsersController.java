package com.evoting.Controller;

import com.evoting.DAO.UserRepositry;
import com.evoting.Model.User;
import com.evoting.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class ManageUsersController {

    // Autowire the UserRepository
    @Autowired
    private UserRepositry userRepository;
    @Autowired
    private EmailService emailService;

    @GetMapping("/manageusers")
    public String manageUsers(Model model) {
        // Fetch all users from the database
       List<User> users =userRepository.findAll();

       model.addAttribute("users",users);
//        // Add the users object to the model
//      for (User user :users)
//      {
//          System.out.println(user.getName()+"->"+user.getEmail());
//      }

        // Return the Thymeleaf template name
        return "testManageUsers";
    }
    @GetMapping("/testingButtons")
    public String showSucess(@RequestParam("approved") String checked , @RequestParam("userId") String userid)
    {
        Long userId = Long.valueOf(userid);
        User user = new User();
        System.out.println(userid+"-->"+checked);

        if(checked.equals("true"))
        {
            user= userRepository.findByid(userId);
            emailService.sendEnrollmentNumber(user);
            System.out.println(user.getState()+"-->"+user.getName());

         user.setStatus("true");
         userRepository.save(user);
        }
        else
        {
            user= userRepository.findByid(userId);
            System.out.println(user.getState()+"-->"+user.getName());
            user.setStatus("false");
            userRepository.save(user);
        }


        return "redirect:/manageusers";
    }
    @GetMapping("/approvedusers")
    public String showApprovedUsers(Model model)
    {
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);


        return "approvedUsersPage";
    }
    @GetMapping("/rejectedusers")
    public String showNotApprovedUsers(Model model)
    {
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);


        return "notApprovedUsers.html";
    }
}
