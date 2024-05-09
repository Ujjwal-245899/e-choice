package com.evoting.Service;

import com.evoting.DAO.CandidateRepository;
import com.evoting.DAO.ElectionRepository;
import com.evoting.Model.Candidate;
import com.evoting.Model.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ElectionServiceImpl implements ElectionService{
    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public boolean saveElection(String name , String startDate, String endDate, Map<String, String> params) {


        List<Candidate> candidates = new ArrayList<>();

// Create a new Election object
        Election election = new Election();
        election.setName(name);
        election.setStartDate(startDate);
        election.setEndDate(endDate);

// Iterate over the request parameters to extract candidate names
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();

            // Check if the parameter key starts with "candidate"
            if (paramName.startsWith("candidate")) {
                // Create a new Candidate object and set its name
                Candidate candidate = new Candidate();
                candidate.setName(paramValue);

                // Associate the Candidate with the Election
                candidate.setElection(election);

                // Add the candidate to the list
                candidates.add(candidate);
            }
        }

// Set the list of candidates to the election object
        election.setCandidates(candidates);

        electionRepository.save(election);


        return true;
    }
}
