package com.evoting.Controller;


import com.evoting.DAO.CandidateRepository;
import com.evoting.DAO.ElectionRepository;
import com.evoting.Model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping
    public List<CandidateResult> getResults() {

               List<Candidate> candidates= candidateRepository.findByElectionId(Long.valueOf("3"));

        List<CandidateResult> results = new ArrayList<>();

        for(Candidate candi:candidates)
        {
            CandidateResult candidateResult= new CandidateResult();
            candidateResult.setName(candi.getName());
            candidateResult.setVotes(candi.getVoteCount());
            results.add(candidateResult);
        }
        return results;
    }

    public static class CandidateResult {
        private String name;
        private int votes;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }
    }
}
