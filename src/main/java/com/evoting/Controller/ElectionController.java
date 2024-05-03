package com.evoting.Controller;

import com.evoting.DAO.CandidateRepository;
import com.evoting.DAO.ElectionRepository;
import com.evoting.DTO.ElectionRequest;
import com.evoting.Model.Candidate;
import com.evoting.Model.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ElectionController {
    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/adminpage")
    public ModelAndView showCreateElectionPage()
    {
        return new ModelAndView("adminPage");
    }
    @GetMapping("/createElection")
    public String showcreateElection(){
        return "createElection";
    }



    @PostMapping("/saveElection" )
    @ResponseBody
    public ModelAndView createElection(@RequestParam String name,
                                       @RequestParam String startDate,
                                       @RequestParam String endDate,
                                       @RequestParam Map<String, String> params) {


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



        // Return a response
        return new ModelAndView("sucess");
    }
    }


