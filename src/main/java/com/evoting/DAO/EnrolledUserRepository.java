package com.evoting.DAO;

import com.evoting.Model.EnrolledUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolledUserRepository extends JpaRepository<EnrolledUser, Long> {

    EnrolledUser findByenrollmentNumber(String enrollmentNumber);
    EnrolledUser findByuserId(Long userId);



}
