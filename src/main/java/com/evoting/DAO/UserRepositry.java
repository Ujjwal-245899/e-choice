package com.evoting.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.evoting.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositry extends JpaRepository<User,Long> {
     User findByid(Long id);
     User findByemail(String email);
     User findBypassword (String password);

}
