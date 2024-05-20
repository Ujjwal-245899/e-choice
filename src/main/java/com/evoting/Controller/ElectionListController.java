package com.evoting.Controller;

import com.evoting.DAO.CandidateRepository;
import com.evoting.DAO.ElectionRepository;
import com.evoting.DAO.EnrolledUserRepository;
import com.evoting.DAO.VotePermissionRepsitory;
import com.evoting.Model.Candidate;
import com.evoting.Model.Election;
import com.evoting.Model.EnrolledUser;
import com.evoting.Model.VotePermission;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ElectionListController {

    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private EnrolledUserRepository enrolledUserRepository;
    @Autowired
    private VotePermissionRepsitory votePermissionRepsitory;

    @Autowired
    HttpSession session;

    @GetMapping("/echoice/electionlist")
    public ModelAndView getVotingPage() {
        List<Election> elections = electionRepository.findAll();

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern to match your date format

        elections.forEach(election -> {
            LocalDate endDate = LocalDate.parse(election.getEndDate(), formatter);
            if (endDate.isAfter(currentDate.minusDays(15))) {
                election.setStatus("new");
            } else if (endDate.isBefore(currentDate)) {
                election.setStatus("expired");
            }
        });

        elections.sort((e1, e2) -> LocalDate.parse(e2.getEndDate(), formatter).compareTo(LocalDate.parse(e1.getEndDate(), formatter)));

        ModelAndView modelAndView = new ModelAndView("electionList");
        modelAndView.addObject("elections", elections);
        return modelAndView;


    }
    @PostMapping("/echoice/votingPage")
    public String SaveElection(@RequestParam("selectedElectionId") Election election , Model model) {
        //Here I will add the validation if the user Enrollment User is Already has Voted for the Election
        //id --> enrolled User
        String enrollmentno = (String) session.getAttribute("enrollment");
        Long id = election.getId();
        String electionId = id.toString();


        boolean flag = votePermissionRepsitory.existsByEnrollNumberAndElectionId(enrollmentno, electionId);

        System.out.println(flag + "--> " + electionId + "--> " + enrollmentno);

        if (flag == false) {

            VotePermission votePermission = new VotePermission();
            votePermission.setElectionId(electionId);
            votePermission.setEnrollNumber(enrollmentno);
            votePermissionRepsitory.save(votePermission);

            System.out.println(election.getId() + "-->" + "Election Id" + enrollmentno);
            List<Candidate> candidates = candidateRepository.findByElectionId(id);
            model.addAttribute("candidates", candidates);
            return "candidatePage";
        } else {

            return "alreadyVoted";
        }
    }
        @GetMapping("/candidateImage/{id}")
        public ResponseEntity<byte[]> getCandidateImage(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid candidate ID"));
        byte[] image = candidate.getImage();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Assuming the image is in JPEG format
        return new ResponseEntity<>(image, headers, HttpStatus.OK);


    }
}
