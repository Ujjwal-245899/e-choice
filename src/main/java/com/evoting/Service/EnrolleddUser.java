package com.evoting.Service;

import com.evoting.Model.User;

public interface EnrolleddUser {

    void saveEnrollment(User user , String enrollmentNo);
    void deleteEnrollment(User user);
}
