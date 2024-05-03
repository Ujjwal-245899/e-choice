package com.evoting.Service;

import com.evoting.DAO.UserRepositry;
import com.evoting.DTO.UserDTO;
import com.evoting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    EmailService emailService;


    @Override
    public boolean SaveUser(UserDTO userdto) {

        User usr = new User();
        usr.setName(userdto.getName());
        usr.setId(userdto.getId());
        usr.setEmail(userdto.getEmail());

        usr.setDateofbirth(userdto.getDob());
        usr.setPassword(userdto.getPassword());
        usr.setState(userdto.getState());

        userRepositry.save(usr);

        //emailService.sendSimpleMail(userdto.getEmail());

        return true;

    }

}
