package com.evoting.DAO;

import com.evoting.Model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    List<Candidate> findByElectionId(Long electionId);
    Candidate findByid(Long id);


    @Query("SELECT SUM(c.voteCount) FROM Candidate c WHERE c.election.id = :electionId")
    Integer sumVoteCountByElectionId(@Param("electionId") Long electionId);
}
