package com.evoting.DAO;

import com.evoting.Model.Election;
import com.evoting.Model.EnrolledUser;
import com.evoting.Model.VotePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotePermissionRepsitory extends JpaRepository<VotePermission,Long> {

  boolean existsByEnrollNumberAndElectionId(String enrollNumber, String electionId);
}
