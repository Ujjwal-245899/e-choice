package com.evoting.DAO;

import com.evoting.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    Optional<Vote> findByUserNameAndCandidateId(String userName, Long candidateId);
}
