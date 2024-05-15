package com.evoting.Service;

import com.evoting.DAO.EnrolledUserRepository;
import com.evoting.Model.EnrolledUser;
import com.evoting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrolledUserImpl implements EnrolleddUser{

    @Autowired
    private EnrolledUserRepository enrolledUserRepository;


    @Override
    public void saveEnrollment(User user, String enrollmentNo) {

        EnrolledUser enrolledUser = new EnrolledUser();
        enrolledUser.setUserId(user.getId());
        enrolledUser.setName(user.getName());
        enrolledUser.setErollmentNumber(enrollmentNo);
        enrolledUserRepository.save(enrolledUser);
    }

    @Override
    public void deleteEnrollment(User user) {
        EnrolledUser enrolledUser = enrolledUserRepository.findByuserId(user.getId());
        if(enrolledUser!=null)
        {
            enrolledUserRepository.delete(enrolledUser);
            System.out.println("Enrolled user has been deleted "+ enrolledUser.getEnrollmentNumber() +" "+ enrolledUser.getName());
        }
        else
        {
            System.out.println("User is Not Approved Originally");
        }

    }
}
