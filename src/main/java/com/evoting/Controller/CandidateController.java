package com.evoting.Controller;

import com.evoting.DAO.CandidateRepository;
import com.evoting.DAO.VoteRepository;
import com.evoting.Model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;



    @PostMapping("/vote/{candidateId}")
    public String voteForCandidate(@PathVariable Long candidateId) {
        System.out.println(candidateId+"->"+"candidate Id");
        //Candidate candidate= candidateRepository.findByElectionId(candidateId);\

      Candidate candidate=  candidateRepository.findByid(candidateId);
      candidate.setVoteCount(candidate.getVoteCount()+1);
      candidate.setVoted(true);
        System.out.println(candidate.getName()+"-->"+candidate.getVoteCount()+"-->"+candidate.isVoted());

        candidateRepository.save(candidate);
        return "redirect:/casting";
    }
}