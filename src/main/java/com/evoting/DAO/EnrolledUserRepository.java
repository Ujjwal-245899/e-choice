package com.evoting.DAO;

import com.evoting.Model.EnrolledUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolledUserRepository extends JpaRepository<EnrolledUser, Long> {
}
