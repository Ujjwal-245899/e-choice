package com.evoting.Controller;

import com.evoting.DAO.ElectionRepository;
import com.evoting.Model.Candidate;
import com.evoting.Model.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ResultController {


        @Autowired
        private ElectionRepository electionRepository;

        @GetMapping("/election/{id}/results")
        public String getElectionResults(@PathVariable Long id, Model model) {
            Optional<Election> electionOptional = electionRepository.findById(id);
            if (electionOptional.isPresent()) {
                Election election = electionOptional.get();
                List<Candidate> candidates = new ArrayList<>(election.getCandidates());
                Candidate winner = candidates.stream()
                        .max(Comparator.comparingInt(Candidate::getVoteCount))
                        .orElse(null);

                int totalVotes = candidates.stream().mapToInt(Candidate::getVoteCount).sum();
                Map<String, Double> votePercentages = candidates.stream()
                        .collect(Collectors.toMap(
                                Candidate::getName,
                                candidate -> (candidate.getVoteCount() * 100.0) / totalVotes
                        ));

                model.addAttribute("election", election);
                model.addAttribute("candidates", candidates);
                model.addAttribute("winner", winner);
                model.addAttribute("totalVotes", totalVotes);
                model.addAttribute("votePercentages", votePercentages);
                return "results";
            } else {
                return "election_not_found";
            }
        }
    }


