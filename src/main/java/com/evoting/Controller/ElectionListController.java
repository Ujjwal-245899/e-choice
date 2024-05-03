package com.evoting.Controller;

import com.evoting.DAO.CandidateRepository;
import com.evoting.DAO.ElectionRepository;
import com.evoting.Model.Candidate;
import com.evoting.Model.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ElectionListController {

    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/page")
    public ModelAndView getVotingPage() {
        List<Election> elections = electionRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("electionList");
        modelAndView.addObject("elections", elections);
       // modelAndView.addObject("selectedElection", new Election());
        return modelAndView;

    }
    @PostMapping("/saveEelction")
    public String SaveElection(@RequestParam("selectedElectionId") Election election , Model model)
    {
        Long id = election.getId();
        System.out.println(election.getId()+"-->" +"Election Id");
        List<Candidate> candidates = candidateRepository.findByElectionId(id);
        model.addAttribute("candidates", candidates);
        return "candidatePage";

    }
}
